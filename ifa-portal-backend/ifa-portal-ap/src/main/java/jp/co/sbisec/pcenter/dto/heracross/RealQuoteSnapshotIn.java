package jp.co.sbisec.pcenter.dto.heracross;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import jp.co.sbisec.pcenter.dto.DtoIn;
import com.sbisec.helios.ap.common.dto.DtoIn;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealQuoteSnapshotIn extends DtoIn {

	// TODO:実際のAPIコールを行う後のフェーズで検証。Heracrossドキュメント上では"/{api}/stocks/GetRealQuoteSnapshot"。
	private static final String API = "/hrc/stock/GetRealQuoteSnapshot";

	@Override
	public String getApi() {
		return RealQuoteSnapshotIn.API;
	}

	@Override
	public boolean getRetry() {
		return true;
	}

	@lombok.Getter
	@lombok.Setter
	private List<RealQuoteKey> symbols;

	public String getHash() {
		return HashBuilder.get();
	}

	public RealQuoteSnapshotIn(List<RealQuoteKey> symbols) {
		this.symbols = symbols;
	}

	public RealQuoteSnapshotIn() {
	}
}