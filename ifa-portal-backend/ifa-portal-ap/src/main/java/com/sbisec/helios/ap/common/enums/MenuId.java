package com.sbisec.helios.ap.common.enums;

/**
 * MenuId
 *
 * @author SCSK
 *
 */
public enum MenuId {

    BROKER("MAIN01"),
    INFORMATION("MAIN02"),
    ADMINISTRATION("MAIN03"),
    ADMINISTRATOR("MAIN04"),
    SHOP("MAIN05"),
    COMMISSION("MAIN06");

    private final String id;

    private MenuId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public static MenuId valueOfId(String id) {

        if (id == null) return null;

        MenuId[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) return enums[i];
        }

        return null;
    }
}
