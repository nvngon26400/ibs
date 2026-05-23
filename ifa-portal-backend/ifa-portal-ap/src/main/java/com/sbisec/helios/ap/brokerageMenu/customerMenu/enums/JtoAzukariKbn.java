package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 預り区分(国内投信注文テーブル)
 * 
 * @author 趙韫慧
 * 
 */
@JsonFormat(shape = Shape.OBJECT)
public enum JtoAzukariKbn {

    JTO_AZUKARI_KBN_0("0", "特定"), JTO_AZUKARI_KBN_1("1", "一般"), JTO_AZUKARI_KBN_4("4", "旧NISA"),
    JTO_AZUKARI_KBN_5("5", "ジュニアNISA口座－特定"), JTO_AZUKARI_KBN_6("6", "ジュニアNISA口座－一般"),
    JTO_AZUKARI_KBN_7("7", "ジュニアNISA口座－旧NISA"), JTO_AZUKARI_KBN_8("8", "旧つみたてNISA"),
    JTO_AZUKARI_KBN_H("H", "NISA預り(成長投資枠)"), JTO_AZUKARI_KBN_I("I", "NISA預り(つみたて投資枠)"),
    JTO_AZUKARI_KBN_J("J", "ジュニアNISA口座－継続NISA");

    private final String id;

    private final String label;

    private JtoAzukariKbn(String id, String label) {

        this.id = id;
        this.label = label;
    }

    public String getId() {

        return id;
    }

    public String getLabel() {

        return label;
    }

    public static JtoAzukariKbn valueOfId(String id) {

        JtoAzukariKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }

        return null;
    }
}
