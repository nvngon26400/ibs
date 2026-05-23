package com.sbisec.helios.ap.athena.protocol.fstock.securities;

import com.sbisec.helios.ap.athena.annotation.UrlParm;
import com.sbisec.helios.ap.athena.protocol.BaseRequest;

/**
 * 
 * 外国株式本日注意銘柄取得 Request
 * 
 * @author shuchen.xin
 * @date: 02/16/2022
 */
public class GetForeignStockAttentionSecuritiesReq implements BaseRequest {

	public GetForeignStockAttentionSecuritiesReq() {
	    
	}

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
	}

	public class Parameter {
		public Parameter() {
		}

		// 国コード
		@UrlParm(name = "country_code")
		private String countryCode;
		// 銘柄コード
		@UrlParm(name = "securities_code")
		private String securitiesCode;

		/**
		 * @return the countryCode
		 */
		public String getCountryCode() {
			return countryCode;
		}

		/**
		 * @param countryCode 「必須」 enums - 国コード - CountryCode
		 */
		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}

		/**
		 * @return the securitiesCode
		 */
		public String getSecuritiesCode() {
			return securitiesCode;
		}

		/**
		 * @param securitiesCode 「必須」 銘柄コード
		 */
		public void setSecuritiesCode(String securitiesCode) {
			this.securitiesCode = securitiesCode;
		}
	}
}
