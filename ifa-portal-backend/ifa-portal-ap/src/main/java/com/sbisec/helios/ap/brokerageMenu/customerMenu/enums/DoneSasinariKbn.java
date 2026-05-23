package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * DONE指成区分
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DoneSasinariKbn {

    HIKESASHI("I", "引指(H)"), HUNARI("F", "不成(F)"), NARIYUKI("N", "成行"), HIKENARI("H", "引成(H)"), PRICE(" ", "指値");

    private final String id;
    private final String label;

    private DoneSasinariKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static DoneSasinariKbn valueOfId(String id) {

        DoneSasinariKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
