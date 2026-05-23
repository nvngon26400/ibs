package com.sbisec.helios.ap.fasthelp.enums;

/**
 * API連携結果
 */
public enum RtnCd {
    /** 00：正常 */
    OK("00"),
    /** -1：異常 */
    NG("-1"),
    /** -2：タイムアウト */
    OT("-2");

    RtnCd(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }
}
