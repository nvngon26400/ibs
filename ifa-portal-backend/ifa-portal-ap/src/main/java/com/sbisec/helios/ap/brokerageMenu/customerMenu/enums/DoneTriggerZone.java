package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * DONEトリガ発動ゾーン
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DoneTriggerZone {

    SOR("0", "以上"), TKY("1", "以下");

    private final String id;

    private final String label;

    private DoneTriggerZone(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static DoneTriggerZone valueOfId(String id) {

        DoneTriggerZone[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
