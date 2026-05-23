package com.sbisec.helios.ap.fasthelp.enums;

/**
 * 受付種別(方向)の列挙型
 */
public enum Houkou {
    /** 0:イン */
    IN("0"),
    /** 1:アウト */
    OUT("1");

    Houkou(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static Houkou getById(String id) {
        for (Houkou type : Houkou.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

    public static String getVal(String val) {
        for (Houkou type : Houkou.values()) {
            if (type.getId().equals(val)) {
                return type.getId();
            }
        }
        return "";
    }
}
