package com.sbisec.helios.ap.common.enums.ipopo;

/**
 * 電子交付のみフラグ
 */
public enum EdelivOnlyFlag {

    NULL("", ""),
    /** 電子交付のみ */
    EDELIV_ONLY("1", "電子交付のみ");

    private final String id;
    private final String label;

    private EdelivOnlyFlag(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static EdelivOnlyFlag valueOfId(String id) {

        if (id == null)
            return null;

        EdelivOnlyFlag[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}