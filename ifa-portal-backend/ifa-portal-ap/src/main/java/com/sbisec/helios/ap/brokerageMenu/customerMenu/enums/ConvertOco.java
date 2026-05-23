package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：OCO注文算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertOco {

    NO95("211", "取消完了,-,0,0"), NO96("221", "取消中,-,0,0"), NO97("231", "注文中,B,1,1"), NO98("2S1", "注文中,-,1,1"),
    NO99("212", "取消完了,-,0,0"), NO100("222", "取消中,-,0,0"), NO101("232", "訂正中,-,0,0"), NO102("2S2", "訂正中,-,0,0"),
    NO103("213", "取消完了,-,0,0"), NO104("223", "取消中,-,0,0"), NO105("233", "注文中,-,1,1"), NO106("2S3", "注文中,D,1,1"),
    NO107("21S", "取消完了,-,0,0"), NO108("22S", "取消中,-,0,0"), NO109("23S", "注文中,B,1,1"), NO110("2SS", "注文中,-,1,1"),
    NO111("311", "取消完了,-,0,0"), NO112("321", "取消中,-,0,0"), NO113("331", "注文中,B,1,1"), NO114("3S1", "注文中,-,1,1"),
    NO115("312", "取消完了,-,0,0"), NO116("322", "取消中,-,0,0"), NO117("332", "訂正中,-,0,0"), NO118("3S2", "訂正中,-,0,0"),
    NO119("313", "取消完了,-,0,0"), NO120("323", "取消中,-,0,0"), NO121("333", "注文中,-,1,1"), NO122("3S3", "注文中,D,1,1"),
    NO123("31S", "取消完了,-,0,0"), NO124("32S", "取消中,-,0,0"), NO125("33S", "注文中,B,1,1"), NO126("3SS", "注文中,-,1,1"),
    NO127("411", "取消完了,-,0,0"), NO128("421", "取消中,-,0,0"), NO129("431", "注文中,B,1,1"), NO130("4S1", "注文中,-,1,1"),
    NO131("412", "取消完了,-,0,0"), NO132("422", "取消中,-,0,0"), NO133("432", "訂正中,-,0,0"), NO134("4S2", "訂正中,-,0,0"),
    NO135("413", "取消完了,-,0,0"), NO136("423", "取消中,-,0,0"), NO137("433", "注文中,-,1,1"), NO138("4S3", "注文中,D,1,1"),
    NO139("41S", "取消完了,-,0,0"), NO140("42S", "取消中,-,0,0"), NO141("43S", "注文中,B,1,1"), NO142("4SS", "注文中,-,1,1");

    private final String id;

    private final String label;

    private ConvertOco(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertOco valueOfId(String id) {

	ConvertOco[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
