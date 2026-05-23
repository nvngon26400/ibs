package com.sbisec.helios.ap.fasthelp.enums;

/**
 * リクエストフラグの列挙型
 */
public enum Request {
    /** 1:あり */
    YES("1");

    Request(String id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    private final String id;

    public String getId() {
        return this == YES ? "1" : "";
    }

    public static String getVal(String val) {
        return YES.getId().equals(val) ? YES.getId() : val;
    }
}
