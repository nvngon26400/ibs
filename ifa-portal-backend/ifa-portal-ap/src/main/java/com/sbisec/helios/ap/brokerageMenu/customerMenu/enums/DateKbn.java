package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 一日信用区分
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DateKbn {

    YEAR("Y", "年"), MONTH("M", "ヶ月"), DAY("D", "日");

    private final String id;

    private final String label;

    private DateKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static DateKbn valueOfId(String id) {

        DateKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
