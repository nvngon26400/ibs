package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：通常注文算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertNormal {

    NO4("N11", "取消完了,A,0,0"), NO5("N12", "取消中,A,0,0"), NO6("N13", "注文中,B,1,1"), NO7("N1S", "注文中,A,1,1"),
    NO8("N21", "取消完了,A,0,0"), NO9("N22", "取消中,A,0,0"), NO10("N23", "訂正中,A,0,0"), NO11("N2S", "訂正中,A,0,0"),
    NO12("N31", "取消完了,A,0,0"), NO13("N32", "取消中,A,0,0"), NO14("N33", "注文中,A,1,1"), NO15("N3S", "注文中,C,1,1"),
    NO16("NS1", "取消完了,A,0,0"), NO17("NS2", "取消中,A,0,0"), NO18("NS3", "注文中,B,1,1"), NO19("NSS", "注文中,A,1,1"),
    NO20("311", "取消完了,A,0,0"), NO21("312", "取消中,A,0,0"), NO22("313", "取消完了,A,0,0"), NO23("31S", "注文不可,A,0,0"),
    NO24("321", "取消完了,A,0,0"), NO25("322", "取消中,A,0,0"), NO26("323", "取消完了,A,0,0"), NO27("32S", "訂正中,A,0,0"),
    NO28("331", "取消完了,A,0,0"), NO29("332", "取消中,A,0,0"), NO30("333", "取消完了,A,0,0"), NO31("33S", "注文不可,A,0,0"),
    NO32("3S1", "取消完了,A,0,0"), NO33("3S2", "取消中,A,0,0"), NO34("3S3", "取消完了,A,0,0"), NO35("3SS", "注文不可,A,0,0");

    private final String id;

    private final String label;

    private ConvertNormal(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertNormal valueOfId(String id) {

	ConvertNormal[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
