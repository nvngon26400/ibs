package com.sbisec.helios.ap.common.enums.ipopo;

public enum BBUrgentStop {

    OFF("0", "OFF"),
    ON("1", "ON");

    private final String id;
    private final String label;

    private BBUrgentStop(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static BBUrgentStop valueOfId(String id) {

        if (id == null)
            return null;

        BBUrgentStop[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
