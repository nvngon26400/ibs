package com.sbisec.helios.ap.ccsApi.enums;

/**
 * 詳細区分の列挙型
 */
public enum ShousaiKbn {

    /** 0：詳細リンクなし */
    SK_00("0"),
    /** 1：詳細リンクあり */
    SK_01("1"),
    /** 2：問合せ */
    SK_02("2"),
    /** 3：書類請求 */
    SK_03("3"),
    /** 4：貸株振替 */
    SK_04("4"),
    /** 5：貸株" */
    SK_05("5");

    ShousaiKbn(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static ShousaiKbn getById(String id) {
        for (ShousaiKbn type : ShousaiKbn.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}