package com.sbisec.helios.ap.common.util;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.exception.SystemErrorException;
import com.sbisec.helios.ap.common.model.RestClientRequestModel;
import com.sbisec.helios.ap.common.model.UserAccount;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * [PROTO2]GWサーバ->APサーバ 共通RESTクライアントサービス。
 * 
 *
 */
public class ApiRequestUtil {
    
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private static ObjectMapper mapper;
    static {
        mapper = new ObjectMapper();
        mapper.configure(MapperFeature.USE_STD_BEAN_NAMING, true);
    }
    
    private static JsonConverter jc = JsonConverter.getInstance();
    
    private static int readTimeout = 1800000;
    
    private static OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout((long) readTimeout, TimeUnit.MILLISECONDS).build();
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    /**
     * [PROTO2]共通RESTコントローラ呼び出し処理。(TypeReference)
     * 
     * @param <T>
     * @param resultType  サービス実行結果の型。
     * @param serviceName 実行するサービスのクラス名。
     * @param methodName  実行するサービスのメソッド名。
     * @param params      実行するメソッドの引数配列。
     * @return サービス実行結果(resultType指定型で返却)
     */
    public static <T> T invoke(String serviceName, String methodName, TypeReference<T> resultType, Object... params)
            throws Exception {
        
        String responseJson = invokeLocal(serviceName, methodName, params);
        try {
            if (responseJson.isBlank()) {
                throw new ApiError("", "Response is blank");
            }
            T response = jc.toObject(responseJson, resultType);
            if (response instanceof DataList<?>) {
                ((DataList<?>) response).setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            }
            
            return response;
            
        } catch (UnrecognizedPropertyException e) {
            
            // APサーバ側で実行時例外発生時、DataListでJSONが返却される
            // 本メソッドの呼び出し元でサービス実行結果型にDataList以外を指定している場合にはJSONの解析に失敗するため
            // エラー情報をSystemErrorExceptionに引き継いで上位にthrowする
            // 呼び出し元でDataListを指定している場合は、本エラー処理は行われず、エラー結果を呼び出し元でメソッドの戻り値として受け取る形になる
            
            DataList<?> dataList = jc.toObject(responseJson, new TypeReference<DataList>() {
            });
            
            dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            
            throw new SystemErrorException(dataList, e);
            
        } catch (MismatchedInputException e) {
            
            // MismatchedInputExceptionに対する特別な処理
            DataList<?> dataList = jc.toObject(responseJson, new TypeReference<DataList>() {
            });
            
            dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            
            throw new SystemErrorException(dataList, e);
            
        } catch (Exception e) {

            throw e;
        }
    }
    
    /**
     * [PROTO2]リクエスト実行処理。
     * 
     * @param serviceName
     * @param methodName
     * @param params
     * @return
     * @throws Exception
     */
    private static String invokeLocal(String serviceName, String methodName, Object... params) throws Exception {
        
        RestClientRequestModel requestModel = new RestClientRequestModel(serviceName, methodName, params);
        try {
            // AP側のログ出力用にユーザIDをheaderに追加する
            String userId = "";
            try {
                UserAccount ua = IfaCommonUtil.getUserAccount();
                if (ua != null) {
                    userId = ua.getUserId();
                }
            } catch (Exception e) {
                // UserAccount取得処理で例外が発生した場合、userIdは空文字で送信する
                userId = "";
            }
            
            // FWセッションIDとコントローラURLをHeaderに追加し、AP側へ連携する
            RequestBody body = RequestBody.create(JSON, requestModel.getRequestJsonString());
            Request request = new Request.Builder().url(requestModel.getUrl())
                    .addHeader("frameworkSessionId",
                            IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class))
                    .addHeader("fid",
                            IfaCommonUtil.getRequestAttribute("fid", String.class))
                    .addHeader("tid",
                            IfaCommonUtil.getRequestAttribute("tid", String.class))
                    .addHeader("userId", userId)
                    .post(body).build();
            Response response = client.newCall(request).execute();
            return response.body().string();
            
        } catch (Exception e) {

            throw e;
        }
        
    }
    
}
