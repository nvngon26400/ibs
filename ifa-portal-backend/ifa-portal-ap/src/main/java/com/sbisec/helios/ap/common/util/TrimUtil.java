package com.sbisec.helios.ap.common.util;

import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class TrimUtil {

    public static final String ZERO_PATTERN = "ZERO";
    public static final String SPACE_PATTERN = "SPACE";
    
    // 正規表現を定数として定義
    private static final String EXCLUDE_PATTERN = "(.*Type\\d+|.*Type|.*Code\\d+|.*Code|.*Kbn|.*Status|.*Flg|.*Id|.*Cd|.*Zone|.*Kind)$";
    												
    /**
     * APIエラーハンドリングに使用する項目の共通除外リストを生成する
     *
     * @param excludeFields ユーザー指定の除外フィールド
     * @return 共通除外リスト
     */
    private static List<String> createCommonExcludeList(String... excludeFields) {
        List<String> excludeList = new ArrayList<>(Arrays.asList(excludeFields));
        String[] commonExcludes = {
            "shubetu", "code", "message", "comIdR", "comId",
            "marketIdChar", "orderedMarket", "bargainMarket", "serNo",
            "fundCodeSub", "reinvest", "commissionCalcInd", "paymentLimit"
        };
        excludeList.addAll(Arrays.asList(commonExcludes));
        return excludeList;
    }
    
    /**
     * BeanのString型のプロパティをTrimする(除外対象をリストで指定）
     * 
     * @param bean 対象のBean
     * @param excludeFields 除外対象のプロパティ
     * @throws Exception 異常
     */
    public static void trimStringFields(Object bean, String... excludeFields) throws Exception {
        // 対象外フィールドをリスト化
    	List<String> excludeList = createCommonExcludeList(excludeFields);
        
        //API項目のTrim対象外項目を正規表現で指定
        trimStringFields(bean,"NONE",EXCLUDE_PATTERN,excludeList);
    }
    
    /**
     * BeanのString型のプロパティをTrimする(除外対象をリストで指定）(ゼロ変換あり）
     * 
     * @param bean 対象のBean
     * @param excludeFields 除外対象のプロパティ
     * @throws Exception 異常
     */
    public static void trimStringFieldsConvrertZero(Object bean, String... excludeFields) throws Exception {
        // 対象外フィールドをリスト化
    	List<String> excludeList = createCommonExcludeList(excludeFields);
        
        //API項目のTrim対象外項目を正規表現で指定
        trimStringFields(bean,ZERO_PATTERN,EXCLUDE_PATTERN,excludeList);
    }

    /**
     * BeanのString型のプロパティをTrimする(除外対象をリストで指定）(スペース1個に変換あり）
     * 
     * @param bean 対象のBean
     * @param excludeFields 除外対象のプロパティ
     * @throws Exception 異常
     */
    public static void trimStringFieldsConvertOneSpace(Object bean, String... excludeFields) throws Exception {
        // 対象外フィールドをリスト化
    	List<String> excludeList = createCommonExcludeList(excludeFields);
        
        //API項目のTrim対象外項目を正規表現で指定
        trimStringFields(bean,SPACE_PATTERN,EXCLUDE_PATTERN,excludeList);
    }
    
    /**
     * Bean の String 型フィールドをトリミングし、正規表現にマッチするフィールドや指定された除外リストに含まれるフィールドは除外する。
     * 
     * @param bean トリミングするオブジェクト
     * @param convPattern Trim後空の場合はゼロに変換"ZERO",スペース1個に変換"SPACE",何もしないを指定"NONE"
     * @param regexPattern 除外するフィールド名の正規表現パターン（null であれば正規表現による除外は行わない）
     * @param excludeFields 除外するフィールド名のリスト（null であればリストによる除外は行わない）
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    private static void trimStringFields(Object bean, String convPattern , String regexPattern, List<String> excludeFields) throws Exception {
        Pattern pattern = regexPattern != null ? Pattern.compile(regexPattern) : null;
        PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(bean);

        for (PropertyDescriptor descriptor : descriptors) {
            String propertyName = descriptor.getName();
            // プロパティがString型で、正規表現にマッチしない、かつ除外リストに含まれていないかチェック
            boolean isExcludedByPattern = pattern != null && pattern.matcher(propertyName).matches();
            boolean isExcludedByList = excludeFields != null && excludeFields.contains(propertyName);
            if (descriptor.getPropertyType() == String.class && !isExcludedByPattern && !isExcludedByList) {
                // 現在のプロパティ値を取得
                String value = (String) PropertyUtils.getProperty(bean, propertyName);
                if (value != null) {
                    value = value.trim();
                    // 変換あり、トリミング後の値が空文字列なら "0" or " "に変換
                    if (value.isEmpty()){ 
                    	if (ZERO_PATTERN.equals(convPattern)) {
                    		value = "0";
                    	} else if(SPACE_PATTERN.equals(convPattern)) {
                    		value = " ";                   			
                    	}
                    }
                    // 設定
                    BeanUtils.setProperty(bean, propertyName, value);
                }
            }
        }
    }
}