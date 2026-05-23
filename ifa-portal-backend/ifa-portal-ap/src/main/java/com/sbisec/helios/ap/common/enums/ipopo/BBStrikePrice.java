package com.sbisec.helios.ap.common.enums.ipopo;

public enum BBStrikePrice {

    NARIYUKI_NASHI("0", "成行なし"),
    NARIYUKI_ARI("1", "成行あり");

    private final String id;
    private final String label;

    private BBStrikePrice(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static BBStrikePrice valueOfId(String id) {

        if (id == null)
            return null;

        BBStrikePrice[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
