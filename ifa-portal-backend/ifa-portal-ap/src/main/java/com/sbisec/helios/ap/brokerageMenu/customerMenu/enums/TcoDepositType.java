package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 預り区分(店頭取引注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TcoDepositType {

    TCO_DEPOSIT_TYPE_1("1", "一般"),
    TCO_DEPOSIT_TYPE_2("2", "特定"),
    TCO_DEPOSIT_TYPE_4("4", "NISA");

    private final String id;

    private final String label;

    private TcoDepositType(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static TcoDepositType valueOfId(String id) {

        TcoDepositType[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
