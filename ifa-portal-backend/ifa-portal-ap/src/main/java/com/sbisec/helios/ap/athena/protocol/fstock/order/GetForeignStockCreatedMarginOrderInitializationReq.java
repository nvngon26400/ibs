package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 外国株式取引サービス - 外国株式信用注文初期情報取得API
 * 
 * @author mengzhe.li
 * @date: 02/012/2022
 */
public class GetForeignStockCreatedMarginOrderInitializationReq implements BaseRequest {

	public GetForeignStockCreatedMarginOrderInitializationReq() {
	    
	}

	// headerとparameterインスタンス化
	private Header header = new Header();
	private Parameter parameter = new Parameter();

	@SuppressWarnings("unchecked")
	@Override
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public class Header {
		private String token;// {部店}3桁 + "-" + {口座}7桁

		public String getToken() {
			return token;
		}

		/**
		 * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
		 */
		public void setToken(String token) {
			this.token = token;
		}

	}

	public class Parameter {
		private String countryCode;// 国コード
		private String securitiesCode;// 銘柄コード
		private String stockTradeType;// 株取引区分
		private String buySellCode;// 売買区分
		private String specificAccountCode;// 預り区分

		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode 「必須」 国コード
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String getSecuritiesCode() {
			return securitiesCode;
		}

		/**
		 * @param securitiesCode 銘柄コード
		 */
		public void setSecuritiesCode(String securitiesCode) {
			this.securitiesCode = securitiesCode;
		}

		public String getStockTradeType() {
			return stockTradeType;
		}

		/**
		 * @param stockTradeType 「必須」 株取引区分
		 */
		public void setStockTradeType(String stockTradeType) {
			this.stockTradeType = stockTradeType;
		}

		public String getBuySellCode() {
			return buySellCode;
		}

		/**
		 * @param buySellCode 「必須」 売買区分
		 */
		public void setBuySellCode(String buySellCode) {
			this.buySellCode = buySellCode;
		}

		public String getSpecificAccountCode() {
			return specificAccountCode;
		}

		/**
		 * @param specificAccountCode 預り区分
		 */
		public void setSpecificAccountCode(String specificAccountCode) {
			this.specificAccountCode = specificAccountCode;
		}

	}

}
