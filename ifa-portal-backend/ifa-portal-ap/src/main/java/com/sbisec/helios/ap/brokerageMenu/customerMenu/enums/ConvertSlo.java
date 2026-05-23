package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：逆指値注文算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertSlo {

    NO43("211", "取消完了,-,0,0"), NO44("212", "取消中,-,0,0"), NO45("213", "待機中,-,1,1"), NO46("21S", "待機中,-,1,1"),
    NO47("221", "取消完了,-,0,0"), NO48("222", "取消中,-,0,0"), NO49("223", "訂正中,-,0,0"), NO50("22S", "訂正中,-,0,0"),
    NO51("231", "取消完了,-,0,0"), NO52("232", "取消中,-,0,0"), NO53("233", "待機中,-,1,1"), NO54("23S", "待機中,-,1,1"),
    NO55("2S1", "取消完了,-,0,0"), NO56("2S2", "取消中,-,0,0"), NO57("2S3", "待機中,-,1,1"), NO58("2SS", "待機中,-,1,1"),
    NO59("311", "取消完了,-,0,0"), NO60("312", "取消中,-,0,0"), NO61("313", "待機中,-,1,1"), NO62("31S", "待機中,-,1,1"),
    NO63("321", "取消完了,-,0,0"), NO64("322", "取消中,-,0,0"), NO65("323", "訂正中,-,0,0"), NO66("32S", "訂正中,-,0,0"),
    NO67("331", "取消完了,-,0,0"), NO68("332", "取消中,-,0,0"), NO69("333", "待機中,-,1,1"), NO70("33S", "待機中,-,1,1"),
    NO71("3S1", "取消完了,-,0,0"), NO72("3S2", "取消中,-,0,0"), NO73("3S3", "待機中,-,1,1"), NO74("3SS", "待機中,-,1,1"),
    NO75("411", "取消完了,-,0,0"), NO76("412", "取消中,-,0,0"), NO77("413", "待機中,-,1,1"), NO78("41S", "待機中,-,1,1"),
    NO79("421", "取消完了,-,0,0"), NO80("422", "取消中,-,0,0"), NO81("423", "訂正中,-,0,0"), NO82("42S", "訂正中,-,0,0"),
    NO83("431", "取消完了,-,0,0"), NO84("432", "取消中,-,0,0"), NO85("433", "待機中,-,1,1"), NO86("43S", "待機中,-,1,1"),
    NO87("4S1", "取消完了,-,0,0"), NO88("4S2", "取消中,-,0,0"), NO89("4S3", "待機中,-,1,1"), NO90("4SS", "待機中,-,1,1");

    private final String id;

    private final String label;

    private ConvertSlo(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertSlo valueOfId(String id) {

	ConvertSlo[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
