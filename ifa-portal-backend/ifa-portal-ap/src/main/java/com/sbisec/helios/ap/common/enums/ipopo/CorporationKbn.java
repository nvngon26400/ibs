package com.sbisec.helios.ap.common.enums.ipopo;

public enum CorporationKbn {

    INDIVIDUAL("0", "個人"),
    CORPORATION("1", "法人");

    private final String id;
    private final String label;

    private CorporationKbn(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static CorporationKbn valueOfId(String id) {

        if (id == null)
            return null;

        CorporationKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
