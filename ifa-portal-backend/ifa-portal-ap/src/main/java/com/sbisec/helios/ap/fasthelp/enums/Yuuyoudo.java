package com.sbisec.helios.ap.fasthelp.enums;

/**
 * 重要度(優先度)の列挙型
 */
public enum Yuuyoudo {
    /** 1:高 */
    H("1"),
    /** 2:中 */
    M("2"),
    /** 3:低 */
    L("3");

    Yuuyoudo(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static Yuuyoudo getById(String id) {
        for (Yuuyoudo type : Yuuyoudo.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

    public static String getVal(String val) {
        for (ProcessId type : ProcessId.values()) {
            if (type.getId().equals(val)) {
                return type.getId();
            }
        }
        return "";
    }
}