package com.sbisec.helios.ap.athena.protocol.account;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 
 * 信用口座開設判定 Request
 * 
 * @author shuchen.xin
 * @date: 02/15/2022
 */
public class CheckMarginAccountOpenReq implements BaseRequest {

	public CheckMarginAccountOpenReq() {
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
		public Parameter() {
		}

		// 信用口座区分
		private String marginAccountType;

		/**
		 * @return the marginAccountType
		 */
		public String getMarginAccountType() {
			return marginAccountType;
		}

		/**
		 * @param marginAccountType 「非必須」 信用口座区分 - enums - MarginAccountType
		 */
		public void setMarginAccountType(String marginAccountType) {
			this.marginAccountType = marginAccountType;
		}

	}
}
