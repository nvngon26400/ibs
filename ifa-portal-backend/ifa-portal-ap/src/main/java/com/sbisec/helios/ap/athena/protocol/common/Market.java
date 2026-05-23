package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

/**
 * 市場情報 Dto.
 *
 * @author shuchen.xin
 * @date 5/28/2021
 */
public class Market implements Serializable {
	private static final long serialVersionUID = -44750493549572162L;

	public Market() {
	}

	// 国コード
	private String countryCode;
	// 市場コード
	private String marketCode;
	// 市場名
	private String marketName;
	// 市場略名
	private String marketShortName;
	// 市場英語名
	private String marketEnglishName;
	// 現地タイムゾーン
	private String timeZone;
	// タイムゾーンID
	private String timeZoneId;
	// タイムゾーン略名
	private String timeZoneShortName;

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
	 * @return 市場コード
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
	 * @return 市場名
	 */
	public String getMarketName() {
		return marketName;
	}

	/**
	 * @param marketName 市場名
	 */
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	/**
	 * @return 市場略名
	 */
	public String getMarketShortName() {
		return marketShortName;
	}

	/**
	 * @param marketShortName 市場略名
	 */
	public void setMarketShortName(String marketShortName) {
		this.marketShortName = marketShortName;
	}

	/**
	 * @return 市場英語名
	 */
	public String getMarketEnglishName() {
		return marketEnglishName;
	}

	/**
	 * @param marketEnglishName 市場英語名
	 */
	public void setMarketEnglishName(String marketEnglishName) {
		this.marketEnglishName = marketEnglishName;
	}

	/**
	 * @return 現地タイムゾーン
	 */
	public String getTimeZone() {
		return timeZone;
	}

	/**
	 * @param timeZone 現地タイムゾーン
	 */
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @return timeZoneId タイムゾーンID
	 */
	public String getTimeZoneId() {
		return timeZoneId;
	}

	/**
	 * @param timeZoneId タイムゾーンID
	 */
	public void setTimeZoneId(String timeZoneId) {
		this.timeZoneId = timeZoneId;
	}

	/**
	 * @return timeZoneShortName イムゾーン略名
	 */
	public String getTimeZoneShortName() {
		return timeZoneShortName;
	}

	/**
	 * @param timeZoneShortName イムゾーン略名
	 */
	public void setTimeZoneShortName(String timeZoneShortName) {
		this.timeZoneShortName = timeZoneShortName;
	}

}
