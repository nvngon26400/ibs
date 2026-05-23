package com.sbisec.helios.ap.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;
import com.sbisec.helios.ap.common.model.RestClientRequestModel;
import com.sbisec.helios.ap.common.model.XmlServiceModel;
import com.sbisec.helios.ap.common.service.ReflectionConfigService;
import com.sbisec.helios.ap.service.Service;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.ApplicationContext;

/**
 * サービス層処理実行用共通クラス。
 */
public class CommonExecuter {
    
    /** サービス、メソッド、パラメタタイプ定義サービス名称。 */
    private static final String CONFIG_SERVICE_NAME = "reflectionConfigService";
    
    /**
     * サービス層処理実行用共通メソッド。
     *
     * @param context           Inject元となるApplicationContext。
     * @param requestJsonString サービス実行リクエスト情報(JSON)。
     * @return サービス実行結果(JSON)。Exeption発生時はnullを返却。
     */
    public static String invoke(ApplicationContext context, String requestJsonString) throws Exception {
        
        RestClientRequestModel remoteServiceRequest = JsonConverter.getInstance().toObject(requestJsonString,
                RestClientRequestModel.class);
        
        // GWのリクエストに設定されたユーザ情報をリクエストスコープに設定
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, remoteServiceRequest.getUserAccount());
        
        // GWのリクエストに設定された顧客共通情報をリクエストスコープに設定
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON,
                remoteServiceRequest.getCustomerCommon());
        
        // GWのリクエストに設定されたRedis登録情報をリクエストスコープに設定
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_GW_AP_SHARED_INFO,
                remoteServiceRequest.getGwApSharedInfo());
        
        // service-config.xmlから定義情報を取得する
        ReflectionConfigService configService = (ReflectionConfigService) context.getBean(CONFIG_SERVICE_NAME);
        XmlServiceModel serviceModel = configService.getServiceConfig(remoteServiceRequest);
        
        // service-config.xmlからサービスが取得できなかった場合、
        if (serviceModel == null) {
            throw new IfaRuntimeException("errors.targetNotFound",
                    new String[] { "サービス", createErrorDetail(remoteServiceRequest, null) });
        }
        
        // Spring BeanコンテナからBeanを取得
        Service service = (Service) context.getBean(serviceModel.getServiceName());
        
        try {
            
            // 呼び出しメソッド情報の取得
            Method method = service.getClass().getMethod(serviceModel.getMethodName(),
                    serviceModel.getParamTypeArrayList());
            
            // パラメータをキャスト
            Object[] params = addTypeToParam(remoteServiceRequest, method, remoteServiceRequest.getParams());
            
            // サービスの実行
            Object result = method.invoke(service, params);
            
            return JsonConverter.getInstance().toString(result);
            
        } catch (NoSuchMethodException e) {
            
            throw new IfaRuntimeException("errors.targetNotFound", new String[] { "サービス",
                    createErrorDetail(remoteServiceRequest, serviceModel.getParamTypeArrayList()) });
            
        } catch (InvocationTargetException e) {
            
            // 呼び出し対象のメソッドで実行時例外が発生した場合、InvocationTargetExceptionで例外がラップされてしまう
            // 後続の例外ハンドラで例外の型別判定で問題が出るため、元の例外を上位にthrowする
            if (e.getCause() != null && e.getCause() instanceof Exception) {
                
                throw (Exception) e.getCause();
            }
            throw e;
        }
    }
    
    /**
     * リモートから送信されたパラメータをサービスの型に変換する
     *
     * @param remoteServiceRequest サービス実行リクエスト情報
     * @param method メソッド情報
     * @param sourceParams リモートから送信されたパラメータ
     * @return 型の情報を付与したパラメータ
     */
    private static Object[] addTypeToParam(RestClientRequestModel remoteServiceRequest, Method method,
            Object[] sourceParams) {
        
        List<Object> params = new ArrayList<Object>();
        
        try {
            
            for (int i = 0; i < sourceParams.length; i++) {
                
                // JSONからMapとして復元されている場合、オブジェクトとして復元する
                if (sourceParams[i] instanceof Map) {

                    Class<?> class1 = method.getParameterTypes()[i];
                    TypeReference<?> typeRef = new TypeReference<>() {
                        @Override
                        public Type getType() {
                            return class1;
                        }
                    };
                    ObjectMapper ob = new ObjectMapper();
                    Object param = ob.readValue(ob.writeValueAsString(sourceParams[i]), typeRef);
                    
                    params.add(param);
                    
                } else {
                    
                    // JSONからMap以外（String、Integr等）で復元されている場合、そのまま使用する
                    params.add(sourceParams[i]);
                    
                }
            }
            
        } catch (IllegalArgumentException | SecurityException | JsonProcessingException e) {
            throw new IfaRuntimeException("errors.targetCallFailure",
                    new String[] { "サービス", createErrorDetail(remoteServiceRequest, null) }, e);
        }
        
        return params.toArray();
    }
    
    /**
     * エラー詳細として出力する情報を生成する
     *
     * @param remoteServiceRequest サービス実行リクエスト情報
     * @param clazz パラメータ情報
     * @return エラー詳細情報
     */
    private static String createErrorDetail(RestClientRequestModel remoteServiceRequest, Class<?>[] clazz) {
        
        StringBuilder errorDetail = new StringBuilder();
        
        errorDetail.append("service-config.xml").append("(");
        errorDetail.append("serviceName").append("=").append(remoteServiceRequest.getServiceName()).append(", ");
        errorDetail.append("methodName").append("=").append(remoteServiceRequest.getMethodName());
        
        if (clazz != null) {
            
            errorDetail.append(", ");
            errorDetail.append("paramTypes").append("=").append(Arrays.toString(clazz));
        }
        
        return errorDetail.append(")").toString();
    }
}
