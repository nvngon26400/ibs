package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 預り区分(外国委託注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum FcoDepositType {

    FCO_DEPOSIT_TYPE_H("H", "NISA(成長投資枠)"), FCO_DEPOSIT_TYPE_4("4", "旧NISA"), FCO_DEPOSIT_TYPE_2("2", "特定"),
    FCO_DEPOSIT_TYPE_J("J", "ジュニアNISA口座－継続NISA"), FCO_DEPOSIT_TYPE_6("6", "ジュニアNISA口座－特定"),
    FCO_DEPOSIT_TYPE_5("5", "ジュニアNISA口座－一般"), JTO_DEPOSIT_TYPE_7("7", "ジュニアNISA口座－旧NISA"),
    FCO_DEPOSIT_TYPE_OTHER("", "一般");

    private final String id;

    private final String label;

    private FcoDepositType(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static FcoDepositType valueOfId(String id) {

        FcoDepositType[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
