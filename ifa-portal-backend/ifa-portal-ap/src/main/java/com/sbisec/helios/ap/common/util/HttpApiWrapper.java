package com.sbisec.helios.ap.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.dto.DtoIn;
import com.sbisec.helios.ap.common.dto.ErrorOut;
import com.sbisec.helios.ap.common.exception.IfaRuntimeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpApiWrapper {

	/** メディアタイプ */
	private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	
    private static final Logger logger = LoggerFactory.getLogger(HttpApiWrapper.class);

	private static HttpApiWrapper instance = new HttpApiWrapper();

	public static HttpApiWrapper get() throws Exception {
		return instance;
	}

	private ObjectMapper objmap;

	/**
	 * コンストラクタ
	 */
	private HttpApiWrapper(){
		objmap = new ObjectMapper()
//			.setSerializationInclusion(JsonInclude.Include.NON_NULL)
			.setPropertyNamingStrategy(CustomNamingStrategy.SANE_SNAKE_CASE)
		;
	}
	/**
	 * 
	 * @param <T>
	 * @param in
	 * @param valueType
	 * @return <T> T 
	 * @throws Exception
	 */
	public <T> T call(DtoIn in, Class<T> valueType) throws Exception {

		HttpClientManager mgr = HttpClientManager.get();
		
		Config cfg = Config.get("pcenter.yml");
		AppConfig appCfg = cfg.getAppConfig(in.getClass());

		List<String> servers = appCfg.getServers();
		if(servers.size() == 0){
			throw new IOException("No active servers");
		}

		String json = objmap.writeValueAsString(in);

		URI uri = URI.create(String.format("%s://%s%s"
			, appCfg.getProtocol()
			, servers.remove(0)
			, in.getApi()
		));

		Request.Builder builder = new Builder()
			.url(uri.toString())
			.header("Accept","application/json").addHeader("Content-Type", "application/json")
			.post(RequestBody.create(MEDIA_TYPE_JSON, json));

		Request request = builder.build();

		OkHttpClient client = mgr.pop();

		int retry = appCfg.getRetry();
		try{
			for(int i = 0; ; i++){

				T msg = call(client, objmap, request, valueType, (in.getRetry() && i < retry && !servers.isEmpty()));
				if(msg == null){

					uri = URI.create(String.format("%s://%s%s"
						, appCfg.getProtocol()
						, servers.remove(0)
						, in.getApi()
				));

					request = builder.url(uri.toString()).build();

					continue;
				}

				mgr.push(client);
				return msg;
			}
		}
		finally{

			mgr.push(client);
		}
	}

	/**
	 * 
	 * @param <T>
	 * @param client
	 * @param mpr
	 * @param req
	 * @param valueType
	 * @param retry
	 * @return
	 * @throws Exception
	 */
	private <T> T call(OkHttpClient client, ObjectMapper mpr, Request req, Class<T> valueType, boolean retry) throws Exception {

		try{
			
			logger.info("API Request : {}", req.toString());
			Response res = client.newCall(req).execute();
			logger.info("API Response : {}", res.toString());
			if(res.code() == 529){
				ErrorOut err = mpr.readValue(res.body().string(), ErrorOut.class);
				String result = err.getResult();
				if(retry && (result.equals("RpcError") || result.equals("ActorError"))){
					return null;
				}

				throw new Exception(String.format("Server error:%s", result));
			}

			if(res.code() != 200){
				if(retry){
					return null;
				}

				String line = res.body().string();
				throw new Exception(String.format("Unknown StatusCode:[%d] %s", res.code(), line));
			}

			if(valueType.equals(String.class)){
				//System.out.println("★★★NRI API正常終了:");
				return valueType.cast(res.body().string());
			}
			
			String line = res.body().string();
			ServerManage.delete(req.url().host());
			//System.out.println("★★★NRI API正常終了:");
			try {
			    return mpr.readValue(line, valueType);
			} catch (JsonProcessingException ex) {
			    // JSON形式以外の場合、JsonProcessingExceptionがスローされる。JSONPとして扱い、JSON部分を取り出す。"(",")"内の文字列を抽出。
			    String jsonString = line.substring(line.indexOf('(') + 1, line.lastIndexOf(')'));
			    return mpr.readValue(jsonString, valueType);
			}
		}
		catch(JsonMappingException | JsonParseException e){
			throw e;
		}
		catch(Exception e){
			ServerManage.add(req.url().host());
			if(retry){
				return null;
			}

			throw e;
		}
	}

    /**
     * GETリクエストを送信します。
     *
     * @param url URL
     * @return HTTPレスポンス
     */
    public static String callAsGet(String url) {
        
        Request request = new Request.Builder().url(url).build();
        return HttpApiWrapper.callRequest(request);
    }
    
    /**
     * POSTリクエストを送信します。
     *
     * @param url           URL
     * @param postParameter POSTパラメータ
     * @return HTTPレスポンス
     */
    public static String callAsPost(String url, Map<String, String> postParameter) {
        
        // POSTパラメータの移送
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (postParameter != null) {
            postParameter.forEach((key, value) -> formBuilder.add(key, value));
        }
        
        Request request = new Request.Builder().url(url).post(formBuilder.build()).build();
        
        return HttpApiWrapper.callRequest(request);
    }
    
    /**
     * 引き数で渡されたRequestオブジェクトを用いて、リクエストの送信を行います
     *
     * @param request リクエスト
     * @return HTTPレスポンス
     */
    private static String callRequest(Request request) {
        
        HttpClientManager httpClientManager = HttpClientManager.get();
        
        OkHttpClient client = httpClientManager.pop();
        
        try (Response response = client.newCall(request).execute()) {
            
            if (!response.isSuccessful()) {
                
                throw new IfaRuntimeException("errors.sendRequestFailure", new String[] { request.toString() });
            }
            
            return response.body().string();
            
        } catch (IOException ex) {
            
            throw new IfaRuntimeException("errors.sendRequestFailure", new String[] { request.toString() });
            
        } finally {
            
            httpClientManager.push(client);
        }
    }
    
    /**
     * パラメータからリクエストを作成する
     * @param uri
     * @param in
     * @return
     * @throws Exception
     */
    private Request makeRequest(URI uri, DtoIn in) throws Exception{
    	
		HttpUrl.Builder urlBuilder = HttpUrl.get(uri).newBuilder();
		;
		// DTOのフィールドをクエリパラメータに変換
		for (Field field : in.getClass().getDeclaredFields()) {
		    if (!field.isSynthetic() && !Modifier.isStatic(field.getModifiers())) {
		        field.setAccessible(true);
		        try {
		            Object value = field.get(in);
		            if (value != null) {
		                urlBuilder.addQueryParameter(field.getName(), value.toString());
		            }
		        } catch (IllegalAccessException e) {
		            // フィールドへのアクセスに失敗した場合の処理（ログ記録など）
		            throw new IOException("DTOアクセスエラー",e);
		        }
		    }
		}
		
		Request.Builder builder = new Builder()
			.url(urlBuilder.build().toString())
			.header("Accept","application/json").addHeader("Content-Type", "application/json");
		
		Request request = builder.build();
		
		return request;
    }
       
	/**
	 * GetでHTTPリクエストを送信する
	 * @param <T>
	 * @param in
	 * @param valueType
	 * @return <T> T 
	 * @throws Exception
	 */
	public <T> T callAsGet(DtoIn in, Class<T> valueType) throws Exception {

		HttpClientManager mgr = HttpClientManager.get();
		
		Config cfg = Config.get("pcenter.yml");
		AppConfig appCfg = cfg.getAppConfig(in.getClass());

		List<String> servers = appCfg.getServers();
		if(servers.size() == 0){
			throw new IOException("No active servers");
		}
	
		URI uri = URI.create(String.format("%s://%s%s"
			, appCfg.getProtocol()
			, servers.remove(0)
			, in.getApi()
		));
		
		Request request = makeRequest(uri, in);

		OkHttpClient client = mgr.pop();

		int retry = appCfg.getRetry();
		try{
			for(int i = 0; ; i++){

				T msg = call(client, objmap, request, valueType, (in.getRetry() && i < retry && !servers.isEmpty()));
				
				if(msg == null){
					
					uri = URI.create(String.format("%s://%s%s"
						, appCfg.getProtocol()
						, servers.remove(0)
						, in.getApi()
				));

					request = makeRequest(uri, in);

					continue;
				}

                logger.info("Heracross API Response : {}", objmap.writeValueAsString(msg));
				mgr.push(client);
				return msg;
			}
		}
		finally{

			mgr.push(client);
		}
	}


}
