package com.sbisec.helios.common.testtool;

import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.common.dto.GwApSharedInfo;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * スタブ用ユーティリティ<br />
 * 本クラスは環境によってはリリースされないため、呼び出す場合は下記のいずれかとして下さい。<br />
 * <ul>
 *     <li>ローカル環境で未実装のクラスを通すため一時的に利用する。</li>
 *     <li>本クラスと同様の位置付けのテスト資材(com.sbisec.helios.*.testtoolパッケージ)から呼び出す</li>
 * </ul> 
 *
 * @author 河口
 *
 */
public class IfaStubUtil {
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaStubUtil.class);
    
    /**
     * リクエストスコープに設定されたGW・AP共有情報からスタブ用のメソッドの戻り値となるオブジェクトを取得する。
     * <ul>
     *     <li>リクエストスコープからAP・GW共有情報を取得する。</li>
     *     <li>共有情報から外部APIスタブデータ用の値を取得する。</li>
     *     <li>テストデータ定義から、対象テストケース、メソッド情報に該当するレコードを特定する。</li>
     *     <li>レコードからテストデータのJSONファイルのパスを取得し、オブジェクトに変換して返却する。</li>
     * </ul>
     *
     * @param <T> 戻り値の型
     * @param resultType 戻り値の型
     * @param method メソッドのメタ情報
     * @return JSONファイルから生成したオブジェクト
     * @throws Exception 任意の例外
     */
    @SuppressWarnings("unchecked")
    public static <T> T getResultObject(Class<T> resultType, Method method) throws Exception {
        
        // JSONコンバータ
        final JsonConverter jc = JsonConverter.getInstance();
        
        // クラス名・メソッド名        
        String methodSignature = method != null ? method.getDeclaringClass().getName() + "." + method.getName() : null;
        LOGGER.debug("対象メソッド: {}", methodSignature);
        
        // 戻り値のインスタンスを取得
        T result = null;
        
        // ジェネリクスタイプ
        Class<?> genericsClass = null;
        
        if (resultType.equals(List.class)) {
            
            List<Object> resultList = new ArrayList<Object>();
            
            if (method != null && method.getGenericReturnType() instanceof ParameterizedType) {
                
                Type genericsType = ((ParameterizedType) method.getGenericReturnType()).getActualTypeArguments()[0];
                
                genericsClass = (Class<?>) genericsType;
                
                resultList.add(genericsClass.getDeclaredConstructor().newInstance());
            }
            
            result = (T) resultList;
            
        } else {
            
            result = resultType.getDeclaredConstructor().newInstance();
        }
        
        // スタブデータを取得
        GwApSharedInfo gwApSharedInfo = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_KEY_GW_AP_SHARED_INFO,
                GwApSharedInfo.class);
        
        // 対象テストケース
        String targetTestCase = gwApSharedInfo.getApiStubTargetTestCase();
        
        LOGGER.debug("対象テストケース(testCase): {}", targetTestCase);
        
        // テストデータ定義
        List<Map<String, String>> stubTestDataList = gwApSharedInfo.getApiStubTestDataList();
        
        LOGGER.debug("定義されたテストデータ(testCase): {}", jc.toString(stubTestDataList));
        
        // テストデータパス
        String testDataPath = null;
        
        if (StringUtils.isNotEmpty(targetTestCase) && CollectionUtils.isNotEmpty(stubTestDataList)) {
            
            for (Map<String, String> map : stubTestDataList) {
                
                // テストケースと指定されたデータを検出した場合
                if (StringUtils.equals(targetTestCase, map.get("testCase"))) {
                    
                    // 以下のいずれかの場合、処理中のレコードをテストデータとする
                    // ・本メソッドのパラメータでメソッド情報が渡されていない
                    // ・処理中のレコードにメソッド情報が渡されていない
                    // ・本メソッドで渡されたメソッドと、処理中のメソッドのクラス名+メソッド名が一致する
                    if (StringUtils.isEmpty(methodSignature) || StringUtils.isEmpty(map.get("methodSignature"))
                            || StringUtils.equals(methodSignature, map.get("methodSignature"))) {
                        
                        testDataPath = map.get("testData");
                        
                        break;
                    }
                }
            }
        }
        
        if (testDataPath != null) {
            
            LOGGER.debug("対象テストケースのテストデータを検出しました: {}", testDataPath);
            
            if (resultType.equals(List.class)) {
                
                String dataDataText = FileUtils.readFileToString(new File(testDataPath), "UTF-8");
                
                ObjectMapper objectMapper = new ObjectMapper();
                
                JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, genericsClass);
                
                result = objectMapper.readValue(dataDataText, javaType);
                
            } else {
                
                result = IfaStubUtil.getObjectFromJsonFilePath(testDataPath, resultType);
            }
            
        } else {
            
            LOGGER.debug("スタブロード対象のデータが存在しないため空のオブジェクトを返却します。");
        }
        
        LOGGER.debug("スタブデータ: {}", jc.toString(result));
        
        return result;
    }
    
    /**
     * リクエストスコープに設定されたGW・AP共有情報からスタブ用のメソッドの戻り値となるオブジェクトを取得する。
     * <ul>
     *     <li>リクエストスコープからAP・GW共有情報を取得する。</li>
     *     <li>共有情報から外部APIスタブデータ用の値を取得する。</li>
     *     <li>テストデータ定義から、対象テストケースに該当するレコードを特定する。</li>
     *     <li>レコードからテストデータのJSONファイルのパスを取得し、オブジェクトに変換して返却する。</li>
     * </ul>
     *
     * @param <T> 戻り値の型
     * @param resultType 戻り値の型
     * @return JSONファイルから生成したオブジェクト
     * @throws Exception 任意の例外
     */
    public static <T> T getResultObject(Class<T> resultType) throws Exception {
        
        return getResultObject(resultType, null);
    }
    
    /**
     * 指定されたJSONファイルのパスを元にオブジェクトを生成します。
     *
     * @param <T> 戻り値の型
     * @param testDataPath JSONファイルパス
     * @param resultType 戻り値の型
     * @return JSONファイルから生成したオブジェクト
     * @throws Exception 任意の例外
     */
    public static <T> T getObjectFromJsonFilePath(String testDataPath, Class<T> resultType) throws Exception {
        
        // JSONコンバータ
        JsonConverter jc = JsonConverter.getInstance();
        
        String dataDataText = FileUtils.readFileToString(new File(testDataPath), "UTF-8");
        
        // 取得したJSONファイルの中身をオブジェクト化して返却
        return jc.toObject(dataDataText, resultType);
    }
}
