package com.sbisec.helios.ap.common.enums.ipopo;

public enum TradeMethod {

    SASINE("0", "指値"),
    NARIYUKI("1", "成行");

    private final String id;
    private final String label;

    private TradeMethod(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TradeMethod valueOfId(String id) {

        if (id == null)
            return null;

        TradeMethod[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
