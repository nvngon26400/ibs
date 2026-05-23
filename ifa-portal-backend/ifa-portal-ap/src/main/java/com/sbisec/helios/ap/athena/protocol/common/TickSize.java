package com.sbisec.helios.ap.athena.protocol.common;

import java.io.Serializable;

/**
 * 呼値 Dto.
 * 
 * @author shuchen.xin
 * @date: 02/21/2022
 */
public class TickSize implements Serializable {
	private static final long serialVersionUID = 4838334761499544790L;

	public TickSize() {
	}

	private String basePriceFrom;// 基準価格（From）
	private String basePriceTo;// 基準価格（To）
	private String basePriceType;// 基準値段区分
	private String tickSize;// 呼値

	/**
	 * @return 基準価格（From）
	 */
	public String getBasePriceFrom() {
		return basePriceFrom;
	}

	/**
	 * @param basePriceFrom 基準価格（From）
	 */
	public void setBasePriceFrom(String basePriceFrom) {
		this.basePriceFrom = basePriceFrom;
	}

	/**
	 * @return 基準価格（To）
	 */
	public String getBasePriceTo() {
		return basePriceTo;
	}

	/**
	 * @param basePriceTo 基準価格（To）
	 */
	public void setBasePriceTo(String basePriceTo) {
		this.basePriceTo = basePriceTo;
	}

	/**
	 * @return 基準値段区分
	 */
	public String getBasePriceType() {
		return basePriceType;
	}

	/**
	 * @param basePriceType 基準値段区分
	 */
	public void setBasePriceType(String basePriceType) {
		this.basePriceType = basePriceType;
	}

	/**
	 * @return 呼値
	 */
	public String getTickSize() {
		return tickSize;
	}

	/**
	 * @param tickSize 呼値
	 */
	public void setTickSize(String tickSize) {
		this.tickSize = tickSize;
	}
}
