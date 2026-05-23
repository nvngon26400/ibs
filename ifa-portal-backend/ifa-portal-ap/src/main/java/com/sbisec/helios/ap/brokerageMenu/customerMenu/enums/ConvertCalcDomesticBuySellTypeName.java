package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：取引種別の算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcDomesticBuySellTypeName {

    SPOT_BUY("1K", "1"), SPOT_SELL("1U", "2"), SPOT_SUB_BUY("5K", "1"), SPOT_SUB_SELL("5U", "2"), NEW_BUY("6K", "3"), NEW_SELL("6U", "4"), REPAY_BUY("7K", "5"),
    REPAY_SELL("7U", "6");

    private final String id;

    private final String label;

    private ConvertCalcDomesticBuySellTypeName(String id, String label) {

	this.id = id;
	this.label = label;
    }

    public String getId() {

	return id;
    }

    public String getLabel() {

	return label;
    }

    public static ConvertCalcDomesticBuySellTypeName valueOfId(String id) {

	ConvertCalcDomesticBuySellTypeName[] enums = values();

	for (int i = 0; i < enums.length; i++) {
	    if (StringUtils.equals(enums[i].getId(), id))
		return enums[i];
	}

	return null;
    }
}
