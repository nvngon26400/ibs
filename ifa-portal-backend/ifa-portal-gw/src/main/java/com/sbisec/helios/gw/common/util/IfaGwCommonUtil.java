package com.sbisec.helios.gw.common.util;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.service.TokenService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * IFAポータルGW共通Util
 *
 * @author 河口
 *
 */
public class IfaGwCommonUtil {
    
    /** スコープキー：要ユーザ共通情報返却フラグ */
    public static final String ATTR_REQUIRES_USER_ACCOUNT_RETURN = "requiresUserAccountReturn";
    
    /** Redisグループ名：顧客共通情報 */
    public static final String ATTR_REDIS_GROUP_CUSTOMER_COMMON = "customerCommon";
    
    /** Redisキー名：顧客共通情報 */
    public static final String ATTR_REDIS_KEY_CUSTOMER_COMMON = "customerCommon";
    
    /** Redisグループ名：環境変数 */
    public static final String ATTR_REDIS_GROUP_ENVIRONMENT = "environment";
    
    /**
     * ユーザ共通情報をAPサーバから取得する<br />
     * 登録後、Redisにユーザ情報を登録する。
     *
     * @return ユーザ共通情報
     * @throws Exception 任意の例外
     */
    public static UserAccount reloadUserAccount() throws Exception {
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // APサーバのユーザ情報取得を呼び出し
        userAccount = ApiRequestUtil.invoke(ServiceNameConstants.USER_ADMINISTRATION_SERVICE, "getUserAccount",
                new TypeReference<UserAccount>() {
                }, userAccount.getUserId(), null);
        
        // Redisにユーザ共通情報を登録する
        TokenService tokenService = IfaCommonUtil.getWebApplicationContext().getBean(TokenService.class);
        
        FrameworkSessionInfo frameworkSessionInfo = tokenService.getSessionInfo(
                IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class));
        
        tokenService.updateSessionInfo(frameworkSessionInfo, userAccount);
        
        // ユーザアカウントをリクエストスコープに設定 
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
        // 要ユーザ共通情報返却フラグを設定
        IfaCommonUtil.setRequestAttribute(IfaGwCommonUtil.ATTR_REQUIRES_USER_ACCOUNT_RETURN, Boolean.TRUE);
        
        return userAccount;
    }
    
    /**
     * 顧客共通情報を取得する
     *
     * @return 顧客共通情報
     */
    public static CustomerCommon getCustomerCommon() {
        
        return IfaCommonUtil.getCustomerCommon();
    }
    
    /**
     * 顧客共通情報をRedisから取得し、リクエストスコープに設定する
     *
     * @throws Exception 例外
     */
    public static void setCustomerCommonToRequestScope() throws Exception {
        
        // Redisから顧客共通情報を取得する
        Object data = getDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_CUSTOMER_COMMON, true,
                IfaGwCommonUtil.ATTR_REDIS_KEY_CUSTOMER_COMMON, CustomerCommon.class.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        CustomerCommon customerCommon = objectMapper.convertValue(data, CustomerCommon.class);
        
        // リクエストスコープに顧客共通情報を登録する
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON, customerCommon);
    }
    
    /**
     * 顧客共通情報をAPサーバから取得する<br />
     * 登録後、Redisに顧客共通情報を登録する。
     *
     * @return 登録した顧客共通情報
     */
    public static CustomerCommon reloadCustomerCommon() {
        
        // APサーバの顧客共通情報取得を呼び出し
        // TODO: APサーバからデータを取得する
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // Redisに顧客共通情報を登録する
        putDataToRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_CUSTOMER_COMMON, true,
                IfaGwCommonUtil.ATTR_REDIS_KEY_CUSTOMER_COMMON, customerCommon, CustomerCommon.class);
        
        // 顧客共通情報をリクエストスコープに設定する
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON, customerCommon);
        
        return customerCommon;
    }
    
    /**
     * 顧客共通情報をRedisから削除する。
     */
    public static void evictCustomerCommon() {
        
        // Redisから顧客共通情報を削除する
        evictDataFromRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_CUSTOMER_COMMON, true,
                IfaGwCommonUtil.ATTR_REDIS_KEY_CUSTOMER_COMMON);
        
    }
    
    /**
     * Redisにデータを登録する
     *
     * @param redisGroup グループ
     * @param addFrameworkSessionIdToRedisGroup グループ名にフレームワークセッションIDを付与する場合はtrue
     * @param redisKey キー
     * @param data 登録対象データ
     * @param clazz 登録対象のデータクラス
     */
    public static void putDataToRedis(String redisGroup, boolean addFrameworkSessionIdToRedisGroup, String redisKey,
            Object data, Class<?> clazz) {
        
        // Redisにデータを登録する
        CacheManager cacheManager = IfaCommonUtil.getWebApplicationContext().getBean(CacheManager.class);
        Cache cache = cacheManager
                .getCache(IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup));
        
        if (data instanceof Map) {
            // 送信された登録対象がBeanの場合、Mapで受けた内容をBeanに変換してRedisに登録する
            ObjectMapper objectMapper = IfaCommonUtil.getWebApplicationContext().getBean(ObjectMapper.class);
            Object convertedData = objectMapper.convertValue(data, clazz);
            if (convertedData != null) {
                cache.put(redisKey, convertedData);
            }
        } else {
            // 送信された登録対象が文字列の場合、文字列をそのままRedisに登録する
            cache.put(redisKey, data);
        }
    }
    
    /**
     * Redisからデータを取得する
     *
     * @param redisGroup グループ
     * @param addFrameworkSessionIdToRedisGroup グループ名にフレームワークセッションIDを付与する場合はtrue
     * @param redisKey キー
     * @param className 取得対象のデータクラス名
     * @return 取得したデータ
     * @throws Exception 例外
     */
    public static Object getDataFromRedis(String redisGroup, boolean addFrameworkSessionIdToRedisGroup, String redisKey,
            String className) throws Exception {
        
        // Redisからデータを取得する
        Class<?> clazz = className != null ? Class.forName(className) : null;
        CacheManager cacheManager = IfaCommonUtil.getWebApplicationContext().getBean(CacheManager.class);
        Cache cache = cacheManager
                .getCache(IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup));
        return cache.get(redisKey, clazz);
    }
    
    /**
     * Redisからデータを取得する
     *
     * @param redisGroup グループ
     * @param addFrameworkSessionIdToRedisGroup グループ名にフレームワークセッションIDを付与する場合はtrue
     * @param redisKey キー
     * @param clazz 取得対象のデータクラス
     * @return 取得したデータ
     * @throws Exception 例外
     */
    public static <T> T getDataFromRedis(String redisGroup, boolean addFrameworkSessionIdToRedisGroup, String redisKey,
            Class<T> clazz) throws Exception {
        
        return clazz.cast(
                getDataFromRedis(redisGroup, addFrameworkSessionIdToRedisGroup, redisKey, clazz.getCanonicalName()));
    }
    
    /**
     * Redisからデータを削除する
     *
     * @param redisGroup グループ
     * @param addFrameworkSessionIdToRedisGroup グループ名にフレームワークセッションIDを付与する場合はtrue
     * @param redisKey キー
     * @throws Exception 例外
     */
    public static void evictDataFromRedis(String redisGroup, boolean addFrameworkSessionIdToRedisGroup,
            String redisKey) {
        
        // Redisからデータを削除する
        CacheManager cacheManager = IfaCommonUtil.getWebApplicationContext().getBean(CacheManager.class);
        Cache cache = cacheManager
                .getCache(IfaGwCommonUtil.editRedisGroup(redisGroup, addFrameworkSessionIdToRedisGroup));
        cache.evict(redisKey);
        
    }
    
    /**
     * Redisグループ名生成<br/>
     * 以下の場合、グループ名にフレームワークセッションIDを付与する。<br/>
     * ・addFrameworkSessionIdToRedisGroupがtrue<br/>
     * ・リクエストスコープにフレームワークセッションIDが存在する
     *
     * @param redisGroup グループ名
     * @param addFrameworkSessionIdToRedisGroup グループ名にフレームワークセッションIDを付与する場合はtrue
     * @return 編集後のグループ名
     */
    public static String editRedisGroup(String redisGroup, boolean addFrameworkSessionIdToRedisGroup) {
        
        StringBuilder cacheGroup = new StringBuilder(redisGroup);
        if (addFrameworkSessionIdToRedisGroup) {
            String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                    String.class);
            
            if (!StringUtils.isEmpty(frameworkSessionId)) {
                // addFrameworkSessionIdToRedisGroupがtrueかつリクエストスコープにframeworkSessionIdが存在する場合のみ付与
                cacheGroup.append(".").append(frameworkSessionId);
            }
        }
        
        return cacheGroup.toString();
    }
}
