package com.sbisec.helios.ap.common.model;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.common.dto.GwApSharedInfo;

import lombok.Getter;

@SuppressWarnings("unused")
public class RestClientRequestModel {

	public static final String REMOTE_SERVICE_REQUEST = "REMOTE_SERVICE_REQUEST";
	@Getter
	private String systemId;
	@Getter
	@JsonIgnore
	private String url;
	@Getter
	private String functionId = "dummyFunctionId";
	@Getter
	private String threadId = "dummyThreadId";
	@Getter
	private String serviceName;
	@Getter
	private String methodName;
	@Getter
	@JsonIgnore
	private String charset = "UTF-8";

	@SuppressWarnings("rawtypes")
	@Getter
	private Class[] paramTypes = null;
	@Getter
	private Object[] params = null;

	@Getter
	private boolean compress = false;

    @Getter
    private UserAccount userAccount;
    @Getter
    private CustomerCommon customerCommon;
    @Getter
    private GwApSharedInfo gwApSharedInfo;
    
    public RestClientRequestModel() {
    
    }
    
    /**
     * [PROTO2]汎用RESTコントローラ向けリクエストモデルコンストラクタ(現行ポーティング用)
     *
     * @param serviceName サービス名
     * @param methodName  メソッド名
     * @param paramTypes  パラメータの型
     * @param params      パラメータ
     */
	public RestClientRequestModel(String serviceName, String methodName, Class[] paramTypes, Object[] params) {
		// TODO:[PROTO2] キャッシュ等から取得する
		this.systemId = "ifa";
        this.url = IfaCommonUtil.getYmlDefinition("url.helios.ap") + "/remoteService";
		this.serviceName = serviceName;
		this.methodName = methodName;
		this.paramTypes = paramTypes;
		this.params = params;
        
        // ユーザ共通情報
        this.userAccount = IfaCommonUtil.getUserAccount();
        // 顧客共通情報
        this.customerCommon = IfaCommonUtil.getCustomerCommon();
        // Redis登録情報(GW→AP連携）
        this.gwApSharedInfo = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_KEY_GW_AP_SHARED_INFO,
                GwApSharedInfo.class);
    }
    
    /**
     * [PROTO2]汎用RESTコントローラ向けリクエストモデルコンストラクタ。
     *
     * @param serviceName サービス名
     * @param methodName  メソッド名
     * @param params      パラメータ
     */
    public RestClientRequestModel(String serviceName, String methodName, Object[] params) {
        
        this(serviceName, methodName, null, params);
    }
    
	/**
	 * [PROTO2]汎用RESTコントローラ向けリクエストJSON取得処理。
	 * @return
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@JsonIgnore
	public String getRequestJsonString() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(this);
	}
}
