package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description:外国株式取引サービス - 外国株式信用新規建注文登録API
 * 
 * @author mengzhe.li
 * @date: 02/10/2022
 */
public class CreateForeignStockMarginOrderReq implements BaseRequest {

	public CreateForeignStockMarginOrderReq() {
	    
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
		public Header() {
		}

		private String token;
		private String trade_password;
		private String request_id;
		private String channel;

		public String getToken() {
			return token;
		}

		/**
		 * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
		 */
		public void setToken(String token) {
			this.token = token;
		}

		public String getTrade_password() {
			return trade_password;
		}

		/**
		 * @param 「必須」 trade_password 取引パスワード
		 */
		public void setTrade_password(String trade_password) {
			this.trade_password = trade_password;
		}

		public String getRequest_id() {
			return request_id;
		}

		/**
		 * @param 「必須」 request_id Request Id
		 */
		public void setRequest_id(String request_id) {
			this.request_id = request_id;
		}

		public String getChannel() {
			return channel;
		}

		/**
		 * @param 「必須」 チャネル
		 */
		public void setChannel(String channel) {
			this.channel = channel;
		}

	}

	public class Parameter {
		public Parameter() {
		}

		// 国コード
		private String countryCode;
		// 市場コード
		private String marketCode;
		// 銘柄コード
		private String securitiesCode;
		// 売買区分
		private String buySellCode;
		// 注文数量
		private String orderQuantity;
		// 価格条件
		private String orderPriceKindCode;
		// 注文単価
		private String orderPrice;
		// 発火条件価格
		private String stopPrice;
		// トレールストップ幅
		private String trailingStopAmount;
		// 期間条件
		private String orderLimitCode;
		// 期間
		private String orderTerm;
		// 預り区分
		private String specificAccountCode;
		// 決済方法
		private String settlementMethodCode;
		// 信用期日
		private String marginCloseLimitType;

		/**
		 * @return countryCode 国コード
		 */
		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode 国コード
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		/**
		 * @return marketCode 市場コード
		 */
		public String getMarketCode() {
			return marketCode;
		}

		/**
		 * @param marketCode 市場コード
		 */
		public void setMarketCode(String marketCode) {
			this.marketCode = marketCode;
		}

		/**
		 * @return securitiesCode 銘柄コード
		 */
		public String getSecuritiesCode() {
			return securitiesCode;
		}

		/**
		 * @param securitiesCode 銘柄コード
		 */
		public void setSecuritiesCode(String securitiesCode) {
			this.securitiesCode = securitiesCode;
		}

		/**
		 * @return buySellCode 売買区分
		 */
		public String getBuySellCode() {
			return buySellCode;
		}

		/**
		 * @param buySellCode 売買区分
		 */
		public void setBuySellCode(String buySellCode) {
			this.buySellCode = buySellCode;
		}

		/**
		 * @return orderQuantity 注文数量
		 */
		public String getOrderQuantity() {
			return orderQuantity;
		}

		/**
		 * @param orderQuantity 注文数量
		 */
		public void setOrderQuantity(String orderQuantity) {
			this.orderQuantity = orderQuantity;
		}

		/**
		 * @return orderPriceKindCode 価格条件
		 */
		public String getOrderPriceKindCode() {
			return orderPriceKindCode;
		}

		/**
		 * @param orderPriceKindCode 価格条件
		 */
		public void setOrderPriceKindCode(String orderPriceKindCode) {
			this.orderPriceKindCode = orderPriceKindCode;
		}

		/**
		 * @return orderPrice 注文単価
		 */
		public String getOrderPrice() {
			return orderPrice;
		}

		/**
		 * @param orderPrice 注文単価
		 */
		public void setOrderPrice(String orderPrice) {
			this.orderPrice = orderPrice;
		}

		/**
		 * @return stopPrice 発火条件価格
		 */
		public String getStopPrice() {
			return stopPrice;
		}

		/**
		 * @param stopPrice 発火条件価格
		 */
		public void setStopPrice(String stopPrice) {
			this.stopPrice = stopPrice;
		}

		/**
		 * @return trailingStopAmount トレールストップ幅
		 */
		public String getTrailingStopAmount() {
			return trailingStopAmount;
		}

		/**
		 * @param trailingStopAmount トレールストップ幅
		 */
		public void setTrailingStopAmount(String trailingStopAmount) {
			this.trailingStopAmount = trailingStopAmount;
		}

		/**
		 * @return orderLimitCode 期間条件
		 */
		public String getOrderLimitCode() {
			return orderLimitCode;
		}

		/**
		 * @param orderLimitCode 期間条件
		 */
		public void setOrderLimitCode(String orderLimitCode) {
			this.orderLimitCode = orderLimitCode;
		}

		/**
		 * @return orderTerm 期間
		 */
		public String getOrderTerm() {
			return orderTerm;
		}

		/**
		 * @param orderTerm 期間
		 */
		public void setOrderTerm(String orderTerm) {
			this.orderTerm = orderTerm;
		}

		/**
		 * @return specificAccountCode 預り区分
		 */
		public String getSpecificAccountCode() {
			return specificAccountCode;
		}

		/**
		 * @param specificAccountCode 預り区分
		 */
		public void setSpecificAccountCode(String specificAccountCode) {
			this.specificAccountCode = specificAccountCode;
		}

		/**
		 * @return settlementMethodCode 決済方法
		 */
		public String getSettlementMethodCode() {
			return settlementMethodCode;
		}

		/**
		 * @param settlementMethodCode 決済方法
		 */
		public void setSettlementMethodCode(String settlementMethodCode) {
			this.settlementMethodCode = settlementMethodCode;
		}

		/**
		 * @return marginCloseLimitType 信用期日
		 */
		public String getMarginCloseLimitType() {
			return marginCloseLimitType;
		}

		/**
		 * @param marginCloseLimitType 信用期日
		 */
		public void setMarginCloseLimitType(String marginCloseLimitType) {
			this.marginCloseLimitType = marginCloseLimitType;
		}

	}
}
