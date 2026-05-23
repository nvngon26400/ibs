package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

/**
 * 銘柄情報 Dto.
 *
 * @author shuchen.xin
 * @date 5/28/2021
 */
public class Securities implements Serializable {
	private static final long serialVersionUID = -3452218312370831354L;

	public Securities() {
	}

	// 商品コード
	private String productCode;
	// 国コード
	private String countryCode;
	// 銘柄コード
	private String securitiesCode;
	// 銘柄名
	private String securitiesName;
	// 銘柄略名
	private String securitiesShortName;
	// 銘柄英語名
	private String securitiesEnglishName;
	// RIC
	private String ric;

	/**
	 * @return 商品コード
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * @param productCode 商品コード
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * @return 国コード
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
	 * @return 銘柄コード
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
	 * @return 銘柄名
	 */
	public String getSecuritiesName() {
		return securitiesName;
	}

	/**
	 * @param securitiesName 銘柄名
	 */
	public void setSecuritiesName(String securitiesName) {
		this.securitiesName = securitiesName;
	}

	/**
	 * @return 銘柄略名
	 */
	public String getSecuritiesShortName() {
		return securitiesShortName;
	}

	/**
	 * @param securitiesShortName 銘柄略名
	 */
	public void setSecuritiesShortName(String securitiesShortName) {
		this.securitiesShortName = securitiesShortName;
	}

	/**
	 * @return 銘柄英語名
	 */
	public String getSecuritiesEnglishName() {
		return securitiesEnglishName;
	}

	/**
	 * @param securitiesEnglishName 銘柄英語名
	 */
	public void setSecuritiesEnglishName(String securitiesEnglishName) {
		this.securitiesEnglishName = securitiesEnglishName;
	}

	/**
	 * @return ric RIC
	 */
	public String getRic() {
		return ric;
	}

	/**
	 * @param ric RIC
	 */
	public void setRic(String ric) {
		this.ric = ric;
	}
}
