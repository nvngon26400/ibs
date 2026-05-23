package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 委託保証金振替登録 Request
 * 
 * @author shuchen.xin
 * @date 02/23/2022
 */
public class CreateMarginTransferReq implements BaseRequest {

	public CreateMarginTransferReq() {
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

		// APP-GWより部店コード(3桁) + '-' + 顧客コード(7桁)を設定
		private String token;
		// Request Id
		private String request_id;

		public String getToken() {
			return token;
		}

		/**
		 * @param token 「必須」 部店コード(3桁) + '-' + 顧客コード(7桁)を設定
		 */
		public void setToken(String token) {
			this.token = token;
		}

		public String getRequest_id() {
			return request_id;
		}

		/**
		 * @param request_id 「必須」 Request Id
		 */
		public void setRequest_id(String request_id) {
			this.request_id = request_id;
		}

	}

	public class Parameter {
		public Parameter() {
		}

		// 「必須」通貨コード
		private String currencyCode;
		// 「必須」委託保証金振替区分
		private String cashTransferType;
		// 「必須」振替金額
		private String amount;

		/**
		 * @return the currencyCode
		 */
		public String getCurrencyCode() {
			return currencyCode;
		}

		/**
		 * @param enums - 「必須」通貨コード - CurrencyCode
		 */
		public void setCurrencyCode(String currencyCode) {
			this.currencyCode = currencyCode;
		}

		/**
		 * @return the cashTransferType
		 */
		public String getCashTransferType() {
			return cashTransferType;
		}

		/**
		 * @param 「必須」委託保証金振替区分 to set
		 */
		public void setCashTransferType(String cashTransferType) {
			this.cashTransferType = cashTransferType;
		}

		/**
		 * @return the amount
		 */
		public String getAmount() {
			return amount;
		}

		/**
		 * @param amount 「必須」振替金額<br>
		 *               サイズ範囲: 0-9999999999999.9999
		 */
		public void setAmount(String amount) {
			this.amount = amount;
		}
	}

}
