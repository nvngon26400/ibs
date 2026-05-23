package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 取引種別(国内株式注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TjoTradeKbn {

    STOCK_BUY("1", "株式買付"), STOCK_SALE("2", "株式売却"), NEW_BUY("3", "新規買付"), NEW_SALE("4", "新規売却"),
    REPAYMENT_BUY("5", "返済買付"), REPAYMENT_SALE("6", "返済売却"), PRESENT("7", "現渡"), CURRENT("8", "現引");

    private final String id;

    private final String label;

    private TjoTradeKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static TjoTradeKbn valueOfId(String id) {

        TjoTradeKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
