package jp.co.sbisec.pcenter.dto.heracross;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import jp.co.sbisec.pcenter.dto.DtoIn;
import com.sbisec.helios.ap.common.dto.DtoIn;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealQuoteSnapshotInput extends DtoIn {

	// TODO:実際のAPIコールを行う後のフェーズで検証。Heracrossドキュメント上では"/{api}/stocks/GetRealQuoteSnapshot"。
	private static final String API = "/api-json-u8/stocks/GetRealQuoteSnapshot";

	@Override
	public String getApi() {
		return RealQuoteSnapshotInput.API;
	}

	@Override
	public boolean getRetry() {
		return true;
	}

	@lombok.Getter
	@lombok.Setter
	private String inputstring;
	
	@lombok.Getter
	@lombok.Setter
	private String hashvalue;
	
	@lombok.Getter
	@lombok.Setter
	private String callback;

	public RealQuoteSnapshotInput() {
	
	}

}