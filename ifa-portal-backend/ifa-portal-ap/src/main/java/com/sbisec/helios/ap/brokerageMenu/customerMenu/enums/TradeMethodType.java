package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 価格条件区分
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TradeMethodType {

    LIMIT("1", "指値"), MARKET("2", "成行"), STOP_PRICE_LIMIT("3", "逆指値（指値）"), STOP_PRICE_MARKET("4", "逆指値（成行）");

    private final String id;

    private final String label;

    private TradeMethodType(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static TradeMethodType valueOfId(String id) {

        TradeMethodType[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
