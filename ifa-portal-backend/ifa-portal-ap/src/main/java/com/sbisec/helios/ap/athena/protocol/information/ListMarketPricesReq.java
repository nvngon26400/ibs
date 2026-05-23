package com.sbisec.helios.ap.athena.protocol.information;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * @Description マーケット価格情報サービス - マーケット価格取得API Request.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public class ListMarketPricesReq implements BaseRequest {

	public ListMarketPricesReq() {
	    
	}

	// headerとparameterインスタンス化
	private Header header = new Header();
	private Parameter parameter = new Parameter();

	@SuppressWarnings("unchecked")
	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	@SuppressWarnings("unchecked")
	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public class Header {
		public Header() {
		}

		// Hash Token
		private String hash_token;

		public String getHash_token() {
			return hash_token;
		}

		/**
		 * @param hash_token 必須「Hash Token」
		 */
		public void setHash_token(String hash_token) {
			this.hash_token = hash_token;
		}
	}

	public class Parameter {
		public Parameter() {
		}

		// 国コード
		private String countryCode;
		// RICリスト
		private String[] rics;

		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode 「国コード」
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		public String[] getRics() {
			return rics;
		}

		/**
		 * @param rics 必須「RICリスト」
		 */
		public void setRics(String[] rics) {
			this.rics = rics;
		}
	}
}
