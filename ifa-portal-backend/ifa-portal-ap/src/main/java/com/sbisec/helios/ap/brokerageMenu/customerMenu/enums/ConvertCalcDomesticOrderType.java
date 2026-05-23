package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：注文種別の算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcDomesticOrderType {

    NO1("S,SSSS,SSS", "0"), NO2("S,SSSS,SLO", "1"), NO3("S,SSSS,OCO", "2"), NO4("S,IFSS,SSS", "3"),
    NO5("S,IFSS,SLO", "4"), NO6("S,DONE,SSS", "5"), NO7("S,DONE,SLO", "6"), NO8("S,DONE,OCO", "7");

    private final String id;

    private final String label;

    private ConvertCalcDomesticOrderType(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertCalcDomesticOrderType valueOfId(String id) {

	ConvertCalcDomesticOrderType[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
