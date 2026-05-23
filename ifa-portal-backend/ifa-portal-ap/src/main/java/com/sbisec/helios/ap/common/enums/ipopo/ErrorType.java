package com.sbisec.helios.ap.common.enums.ipopo;

public enum ErrorType {

    ERROR("0", "NGあり"),
    DATA_EXIST("1", "登録済み"),
    WARNING("2", "警告あり"),
    WARNING_AND_ERROR("3", "NGあり"),
    OK("4", "OK"),
    OTHERS_DATA_EXIST("5", "既登録済"),;

    private final String id;
    private final String label;

    private ErrorType(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ErrorType valueOfId(String id) {

        if (id == null)
            return null;

        ErrorType[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
