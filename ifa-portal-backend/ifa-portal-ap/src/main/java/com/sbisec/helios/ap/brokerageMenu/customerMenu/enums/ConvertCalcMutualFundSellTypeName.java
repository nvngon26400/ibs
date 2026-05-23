package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：国内投信 取引種別の算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcMutualFundSellTypeName {

    NO1("KT", "0"), NO2("KY", "1"), NO3("UT", "7"), NO4("UY", "8"), NO5("VT", "3"), NO6("VY", "4");

    private final String id;

    private final String label;

    private ConvertCalcMutualFundSellTypeName(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertCalcMutualFundSellTypeName valueOfId(String id) {

	ConvertCalcMutualFundSellTypeName[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
