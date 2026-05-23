package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：現引現渡算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertReceiptDelivery {

    NO181("11", "取消完了,,0,0"), NO182("12", "取消中,,0,0"), NO183("13", "注文中,,0,1"), NO184("1S", "注文中,,0,1"), NO185("21", "取消完了,,0,0"),
    NO186("22", "取消中,,0,0"), NO187("23", "注文中,,0,1"), NO188("2S", "注文中,,0,1"), NO189("31", "取消完了,,0,0"), NO190("32", "取消中,,0,0"),
    NO191("33", "取消完了,,0,0"), NO192("3S", "注文不可,,0,0"), NO193("S1", "取消完了,,0,0"), NO194("S2", "取消中,,0,0"), NO195("S3", "注文中,,0,1"),
    NO196("SS", "注文中,,0,1");

    private final String id;

    private final String label;

    private ConvertReceiptDelivery(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertReceiptDelivery valueOfId(String id) {

	ConvertReceiptDelivery[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
