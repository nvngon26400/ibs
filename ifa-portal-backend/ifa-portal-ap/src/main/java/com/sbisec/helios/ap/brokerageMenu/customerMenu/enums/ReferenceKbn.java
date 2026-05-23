package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 参照元履歴区分
 * 
 * @author 趙韫慧
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ReferenceKbn {

    REFERENCE_KBN_1("1", "CCS履歴"), 
    REFERENCE_KBN_2("2", "問合せ履歴"), 
    REFERENCE_KBN_3("3", "書類請求当日履歴"),
    REFERENCE_KBN_4("4", "国内株式当日履歴"), 
    REFERENCE_KBN_5("5", "国内投信当日履歴"), 
    REFERENCE_KBN_6("6", "その他余力拘束当日履歴"),
    REFERENCE_KBN_7("7", "外株委託当日履歴"), 
    REFERENCE_KBN_8("8", "外株委託当日履歴"), 
    REFERENCE_KBN_9("9", "為替取引当日履歴");

    private final String id;

    private final String label;

    private ReferenceKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static ReferenceKbn valueOfId(String id) {

        ReferenceKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
