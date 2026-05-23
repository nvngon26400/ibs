package com.sbisec.helios.ap.common.dto.yanap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.dto.DtoIn;
import com.sbisec.helios.ap.common.dto.QueryAccountBalanceInData;
import com.sbisec.helios.ap.common.dto.RequestHeader;

/**
 * NRI API AccountBalance 呼び出し時パラメタの具象クラス。
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NriQueryAccountBalanceIn extends DtoIn {
	/** APIエンドポイントURL */
	private static final String API = "/api/nri_query_account_balance";
	@Override
	public String getApi() {
		return NriQueryAccountBalanceIn.API;
	}

	@Override
	public boolean getRetry() {
		return true;
	}

	@lombok.Getter(onMethod = @__(@JsonProperty("header")))
	@lombok.Setter(onMethod = @__(@JsonProperty("header")))
	private RequestHeader header;

	@lombok.Getter(onMethod = @__(@JsonProperty("indata")))
	@lombok.Setter(onMethod = @__(@JsonProperty("indata")))
	private QueryAccountBalanceInData indata;

	/**
	 * コンストラクタ
	 * @param header
	 * @param indata
	 */
	public NriQueryAccountBalanceIn(RequestHeader header, QueryAccountBalanceInData indata) {
		this.header = header;
		this.indata = indata;
	}
	/**
	 * デフォルトコンストラクタ。(デシリアライズ用)
	 */
	public NriQueryAccountBalanceIn() {
	}
}
