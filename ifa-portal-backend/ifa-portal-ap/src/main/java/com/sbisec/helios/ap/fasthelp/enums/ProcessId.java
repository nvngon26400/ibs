package com.sbisec.helios.ap.fasthelp.enums;

/**
 * 処理区分の列挙型
 */
public enum ProcessId {
	/** 1：問合せ登録 */
    Q_INS("1"),
    /** 2：問合せ変更 */
    Q_UPD("2"),
    /** 3：問合せ＋回答登録 */
    A_INS("3"),
    /** 4：問合せ変更＋回答登録 */
    Q_UPD_A_INS("4"),
    /** 5：回答変更 */
    A_UPD("5");

    ProcessId(String id) {
        this.id = id;
    }

    private final String id;

    public String getId() {
        return id;
    }

    public static ProcessId getById(String id) {
        for (ProcessId type : ProcessId.values()) {
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
