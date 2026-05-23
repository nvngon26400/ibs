package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 売買区分(国内投信注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum JtoTradeKbn {

    TRADEKBN_U("U", "投信累投売却", "投信一般売却"), TRADEKBN_K("K", "投信累投購入", "投信一般購入"), TRADEKBN_A("A", "投信累投全額解約", ""),
    TRADEKBN_B("B", "投信累投全額買取", ""), TRADEKBN_V("V", "投信累投買取", "投信一般買取");

    private final String id;

    private final String label;

    private final String normalLabel;

    private JtoTradeKbn(String id, String label, String normalLabel) {

        this.id = id;
        this.label = label;
        this.normalLabel = normalLabel;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public String getNormalLabel() {

        return normalLabel;
    }

    public static JtoTradeKbn valueOfId(String id) {

        JtoTradeKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
