package com.sbisec.helios.ap.athena.protocol.fstock.securities;

/**
 * 外国株式本日注意銘柄取得 Response
 * 
 * @author shuchen.xin
 * @date 02/16/2022
 */
public class GetForeignStockAttentionSecuritiesResp {

	public GetForeignStockAttentionSecuritiesResp() {
	}

	// 国コード
	private String countryCode;
	// 銘柄コード
	private String securitiesCode;
	// 注意銘柄
	private Boolean attentionSecurities;

	/**
	 * @return enums - 国コード - CountryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return 銘柄コード
	 */
	public String getSecuritiesCode() {
		return securitiesCode;
	}

	/**
	 * @param securitiesCode the securitiesCode to set
	 */
	public void setSecuritiesCode(String securitiesCode) {
		this.securitiesCode = securitiesCode;
	}

	/**
	 * @return 注意銘柄
	 */
	public Boolean getAttentionSecurities() {
		return attentionSecurities;
	}

	/**
	 * @param attentionSecurities the attentionSecurities to set
	 */
	public void setAttentionSecurities(Boolean attentionSecurities) {
		this.attentionSecurities = attentionSecurities;
	}
}
