package com.sbisec.helios.ap.common.enums.ipopo;

public enum BBIpoPoKbn {

    IPO("1", "IPO"),
    PO("2", "PO");

    private final String id;
    private final String label;

    private BBIpoPoKbn(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static BBIpoPoKbn valueOfId(String id) {

        if (id == null)
            return null;

        BBIpoPoKbn[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
