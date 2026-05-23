package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 市場
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum Market {

    SOR("A", "SOR"), TKY("0", "東証"), NGY("2", "名証"), FKO("6", "福証"), JNX("7", "PTS"), SPR("8", "札証"),
    TANGENMIMAN("H", "単元未満");

    private final String id;

    private final String label;

    private Market(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static Market valueOfId(String id) {

        Market[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
