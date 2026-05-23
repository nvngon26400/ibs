package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 指成区分
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SasinariKbn {

    YORISASHI("Z", "寄指(Y)"), HIKESASHI("I", "引指(H)"), HUNARI("F", "不成(F)"), IOC_SASHI("P", "IOC指(I)"),
    NARIYUKI("N", "成行"), YORINARI("Y", "寄成(Y)"), HIKENARI("H", "引成(H)"), IOC_NARI("O", "IOC成(I)"), PRICE(" ", "指値");

    private final String id;
    private final String label;

    private SasinariKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static SasinariKbn valueOfId(String id) {

        SasinariKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
