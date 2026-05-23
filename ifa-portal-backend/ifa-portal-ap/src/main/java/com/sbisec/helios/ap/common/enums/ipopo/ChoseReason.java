package com.sbisec.helios.ap.common.enums.ipopo;

public enum ChoseReason {

    TRADE_EXPANSION("1", "取引拡大のため"),
    TRADE_ASSETS_EXPANSION("2", "取引拡大、資産拡大のため"),
    NEW_DEVELOPMENT_ASSETS_EXPANSION("3", "新規開拓、資産拡大のため");

    private final String id;
    private final String label;

    private ChoseReason(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ChoseReason valueOfId(String id) {

        if (id == null)
            return null;

        ChoseReason[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
