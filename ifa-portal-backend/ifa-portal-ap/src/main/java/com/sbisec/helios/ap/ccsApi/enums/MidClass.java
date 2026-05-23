package com.sbisec.helios.ap.ccsApi.enums;

/**
 * 詳細区分の列挙型
 */
public enum MidClass {
    /** eﾜﾗﾝﾄ */
    MC_00("eﾜﾗﾝﾄ"),
    /** 1：詳細リンクあり */
    MC_01("積立買付"),
    /** 定額売却 */
    MC_02("定額売却");

    MidClass(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static MidClass getById(String id) {
        for (MidClass type : MidClass.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}