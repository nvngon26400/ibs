package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 為替取引サービス種別(為替取引注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum FxTradeServiceType {

    STOCK_BUY("1", "IFA"), STOCK_SALE("2", "ﾘﾃｰﾙ");

    private final String id;

    private final String label;

    private FxTradeServiceType(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static FxTradeServiceType valueOfId(String id) {

        FxTradeServiceType[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
