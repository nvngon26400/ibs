package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 発送ステータス
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum HassouSts {

    TKY("01", "郵送請求"), FKO("06", "営業店郵送");

    private final String id;

    private final String label;

    private HassouSts(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static HassouSts valueOfId(String id) {

        HassouSts[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
