package com.sbisec.helios.ap.athena.protocol.information;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.information.dto.MarketPrice;

/**
 * @Description マーケット価格情報サービス - マーケット価格取得API Response.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class ListMarketPricesResp {

	public ListMarketPricesResp() {
	}

	// マーケット価格リスト
	private List<MarketPrice> marketPrices;

	/**
	 * @return marketPrices マーケット価格リスト
	 */
	public List<MarketPrice> getMarketPrices() {
		return marketPrices;
	}

	public void setMarketPrices(List<MarketPrice> marketPrices) {
		this.marketPrices = marketPrices;
	}
}
