package com.sbisec.helios.ap.fasthelp.enums;

/**
 * ステータスの列挙型
 */
public enum TaiouSts {
    /** 0:未対応 */
    NO("0"),
    /** 1:対応済 */
    YES("1");

    TaiouSts(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static TaiouSts getById(String id) {
        for (TaiouSts type : TaiouSts.values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }

    public static String getVal(String val) {
        for (TaiouSts type : TaiouSts.values()) {
            if (type.getId().equals(val)) {
                return type.getId();
            }
        }
        return "";
    }
}
