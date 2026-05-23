package com.sbisec.helios.ap.common.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.core.env.Environment;
import org.springframework.util.MethodInvoker;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * IFAポータル共通Util
 *
 * @author 河口
 *
 */
public class IfaCommonUtil {
    
    /** スコープキー：ユーザ共通情報 */
    public static final String ATTR_KEY_USER_ACCOUNT = "userAccount";

    /** スコープキー：顧客共通情報 */
    public static final String ATTR_KEY_CUSTOMER_COMMON = "customerCommon";
    
    /** スコープキー：リクエスト日時 */
    public static final String ATTR_KEY_RTIME = "rtime";
    
    /** スコープキー：リクエスト時間(HOUR) */
    public static final String ATTR_KEY_RHOUR = "rhour";
    
    /** スコープキー：リクエスト時間(YMDHMS) */
    public static final String ATTR_KEY_RYMDHMS = "rymdhms";
    
    /** スコープキー：フレームワークセッションキー */
    public static final String ATTR_FRAMEWORK_SESSION_ID = "frameworkSessionId";
    
    /** スコープキー：認証トークン */
    public static final String ATTR_AUTH_TOKEN = "authToken";
   
    /** スコープキー：GW・APサーバ共有情報 */
    public static final String ATTR_KEY_GW_AP_SHARED_INFO = "gwApSharedInfo";
    
    /** スコープキー：外部API認可フラグ */
    public static final String ATTR_EXT_API_PERMISSION_FLAG = "extApiPermissionFlag";
    
    /** MessageSource(= messages.properties) */
    private static MessageSource messageSource;
    
    /** デバッグフラグ（環境変数） */
    private static Boolean hasDebugEnvironmentVariable = Boolean.valueOf(System.getenv().get("DEBUG"));
    
    /**
     * ユーザ共通情報を取得する
     *
     * @return ユーザ共通情報
     */
    public static UserAccount getUserAccount() {
        
        // リクエストスコープ内のユーザ共通情報を返却する
        return (UserAccount) getRequestAttributes().getAttribute(ATTR_KEY_USER_ACCOUNT,
                RequestAttributes.SCOPE_REQUEST);
    }
    
    /**
     * 顧客共通情報を取得する
     *
     * @return 顧客共通情報
     */
    public static CustomerCommon getCustomerCommon() {

        // リクエストスコープ内の顧客共通情報を返却する
        return (CustomerCommon) getRequestAttributes().getAttribute(ATTR_KEY_CUSTOMER_COMMON,
                RequestAttributes.SCOPE_REQUEST);
    }
    
    /**
     * メッセージIDからメッセージを生成する
     *
     * @param messageId メッセージID
     * @return メッセージ
     */
    public static String getMessage(String messageId) {
        
        return getMessageResource().getMessage(messageId, null, Locale.JAPANESE);
    }
    
    /**
     * メッセージID、メッセージパラメータからメッセージを生成する
     *
     * @param messageId メッセージID
     * @param messageParams メッセージパラメータ
     * @return メッセージ
     */
    public static String getMessage(String messageId, String[] messageParams) {
        
        return getMessageResource().getMessage(messageId, messageParams, Locale.JAPANESE);
    }
    
    /**
     * メッセージID、メッセージパラメータからメッセージを生成する
     *
     * @param messageId メッセージID
     * @param messageParams メッセージパラメータ
     * @return メッセージ
     */
    public static String getMessage(String messageId, Object[] messageParams) {
        
        return getMessageResource().getMessage(messageId, messageParams, Locale.JAPANESE);
    }
        
    /**
     * メッセージIDからメッセージを生成する<br />
     * メッセージが取得できなかった場合は空文字を返す。
     *
     * @param messageId メッセージID
     * @return メッセージ
     */
    public static String retrieveMessage(String messageId) {
        
        try {
            return getMessageResource().getMessage(messageId, null, Locale.JAPANESE);
            
        } catch (NoSuchMessageException e) {
            
            return StringUtils.EMPTY;
        }
    }
    
    /**
     * リクエスト日時をリクエストスコープから下記の形式で取得する
     * ・yyyy/MM/dd HH:mm:ss
     *
     * @return リクエスト日時
     * 
     */
    public static String getFormattedRequestedTime() {
        
        Date requestedTime = (Date) IfaCommonUtil.getRequestAttributes().getAttribute(ATTR_KEY_RTIME,
                RequestAttributes.SCOPE_REQUEST);
        
        if (requestedTime == null) {
            
            requestedTime = new Date();
        }
        
        return com.sbibits.earth.util.DateUtil.format(requestedTime,
                com.sbibits.earth.util.DateUtil.SEPARATED_YYYYMMDD_HHMMSS);
    }
    
    /**
     * RequestAttributes を取得します。
     *
     * @return RequestAttributesのインスタンス 
     */
    public static RequestAttributes getRequestAttributes() {
        
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        
        return requestAttributes;
    }
    
    /**
     * リクエストスコープから値を取得します。<br />
     * 取得対象がBooleanの場合、取得できなかった場合、Falseを返します。
     *
     * @param <T> 戻り値のクラス
     * @param key リクエストスコープから値を取得する際のキー
     * @param clazz 戻り値のクラス
     * @return リクエストスコープから取得した値
     */
    public static <T> T getRequestAttribute(String key, Class<T> clazz) {
        
        Object object = IfaCommonUtil.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_REQUEST);
        
        if (object == null) {
            
            if (Boolean.class.equals(clazz)) {
                
                return clazz.cast(Boolean.FALSE);
            }
            return null;
        }
        
        try {
            return clazz.cast(object);
            
        } catch (ClassCastException e) {
            
            throw new IfaRuntimeException("errors.targetPickUpFailure", e);
        }
    }
    
    /**
     * リクエストスコープに値を設定します。
     *
     * @param key リクエストスコープに値を設定する際のキー
    
     */
    public static void setRequestAttribute(String key, Object value) {
        
        IfaCommonUtil.getRequestAttributes().setAttribute(key, value, RequestAttributes.SCOPE_REQUEST);
    }
    
    /**
     * Redisからフレームワークセッション情報の削除を行う
     */
    public static void logout() {
        
        String frameworkSessionId = (String) IfaCommonUtil.getRequestAttributes()
                .getAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, RequestAttributes.SCOPE_REQUEST);
        
        if (StringUtils.isNotEmpty(frameworkSessionId)) {
            
            Object tokenService = IfaCommonUtil.getWebApplicationContext().getBean("tokenService");
            
            // APサーバで実行された場合は、tokenServiceはnullになる
            if (tokenService != null) {
                
                MethodInvoker methodInvoker = new MethodInvoker();
                methodInvoker.setTargetObject(tokenService);
                methodInvoker.setTargetMethod("evictSessionInfoFromCache");
                methodInvoker.setArguments(frameworkSessionId);
                
                try {
                    methodInvoker.prepare();
                    methodInvoker.invoke();
                    
                } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException
                        | IllegalAccessException e) {
                    
                    throw new IfaRuntimeException("errors.targetCallFailure",
                            new String[] { "evictSessionInfoFromCache", tokenService.toString() }, e);
                }
            }
        }
    }
    
    /**
     * DataListを生成する。
     *
     * @param innerDataList DataListの内部dataListに設定するリスト
     * @param errorLevel エラーレベル
     * @param returnCode リターンコード（メッセージID）
     * @param message メッセージ
     * @return DataList
     */
    public static <T> DataList<T> createDataList(List<T> innerDataList, ErrorLevel errorLevel, String returnCode,
            String message) {
        
        DataList<T> dataList = new DataList<T>();
        
        if (innerDataList != null) {
            dataList.setDataList(innerDataList);
        }
        
        dataList.setTotalSize(dataList.getDataList().size());
        dataList.setMaxRownum(dataList.getDataList().size());
        dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
        
        dataList.setErrorLevel(errorLevel.getId());
        dataList.setReturnCode(returnCode);
        dataList.setMessage(message);
        
        return dataList;
    }
    
    /**
     * DataListを生成する。<br />
     * メッセージについては、リターンコードを元にmessage.propertiesファイルから取得する。
     *
     * @param innerDataList DataListの内部dataListに設定するリスト
     * @param errorLevel エラーレベル
     * @param returnCode リターンコード（メッセージID）
     * @return DataList
     */
    public static <T> DataList<T> createDtaList(List<T> innerDataList, ErrorLevel errorLevel, String returnCode) {
        
        return IfaCommonUtil.createDataList(innerDataList, errorLevel, returnCode,
                IfaCommonUtil.retrieveMessage(returnCode));
    }
    
    /**
     * application.ymlファイルから指定されたキーの値を取得します。
     *
     * @param key application.ymlから値を取得する際のキー
     * @return application.ymlで定義された値
     */
    public static String getYmlDefinition(String key) {
        
        Environment environment = IfaCommonUtil.getWebApplicationContext().getBean(Environment.class);
        
        return environment.getProperty(key);
    }
    
    /**
     * WebApplicationContext を取得します。
     *
     * @return WebApplicationContextのインスタンス 
     */
    public static WebApplicationContext getWebApplicationContext() {
        
        ServletContext servletContext = ((ServletRequestAttributes) getRequestAttributes()).getRequest()
                .getServletContext();
        
        WebApplicationContext webApplicationContext = WebApplicationContextUtils
                .getWebApplicationContext(servletContext);
        
        //ServletContext
        return webApplicationContext;
    }
    
    /**
     * 環境変数にDEBUG=trueが設定されているかチェックします
     *
     * @return 環境変数DEBUG=trueが設定されている場合、true
     */
    public static boolean hasDebugEnvironmentVariable() {
        
        return hasDebugEnvironmentVariable.booleanValue();
    }
    
    /**
     *  環境変数DEBUGが有効な場合、DataList.dataListに例外のスタックトレースの情報を設定する。
     *
     *  @param e 例外情報
     *  @return 例外のスタックトレースを設定したList
     */
    public static List<String> createErrorDataList(Throwable e) {
        
        List<String> errorDataList = new ArrayList<String>();
        
        // 環境変数DEBUGが無効な場合
        if (!IfaCommonUtil.hasDebugEnvironmentVariable()) {
            return errorDataList;
        }
        
        for (String stackFrame : ExceptionUtils.getStackFrames(e)) {
            
            errorDataList.add(StringUtils.replace(stackFrame, "\t", "    "));
        }
        
        return errorDataList;
    }

    /**
     * Mapをジェネリクス指定でキャストする。
     * ジェネリクス付きの型のキャストについては、アノテーションによる警告の回避が必要になる。<br />
     * アノテーションを付与する箇所を集約するため、本メソッドを作成する。
     *
     * @param <K> キー
     * @param <V> 値
     * @param map キャスト対象のMap
     * @return キャストしたMap
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> castMap(Map<?, ?> map) {
        
        return (Map<K, V>) map;
    }
    
    /**
     * Listをジェネリクス指定でキャストする。
     * ジェネリクス付きの型のキャストについては、アノテーションによる警告の回避が必要になる。<br />
     * アノテーションを付与する箇所を集約するため、本メソッドを作成する。
     *
     * @param <T> List内のオブジェクトの型
     * @param list キャスト対象のList
     * @return キャストしたList
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> castList(List<?> list) {
        
        return (List<T>) list;
    }
    
    /**
     * MessageResource を取得します。
     *
     * @return MessageResourceのインスタンス
     */
    private static MessageSource getMessageResource() {
        
        if (messageSource == null) {
            
            messageSource = getWebApplicationContext().getBean(MessageSource.class);
        }
        return messageSource;
    }
}
