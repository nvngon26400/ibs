package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

/**
 * ステータス(店頭取引注文テーブル)
 * 
 * @author 趙韫慧
 *
 */
public enum TcoOrderStatus {

    Order_Status_10("10", "承認待ち"), Order_Status_30("30", "約定"), Order_Status_31("31", "取消"),
    Order_Status_40("40", "不出来");

    private final String id;
    private final String label;

    private TcoOrderStatus(String id, String label) {
        this.id = id;
        this.label = label;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TcoOrderStatus valueOfId(String id) {

        if (id == null)
            return null;

        TcoOrderStatus[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }

        return null;
    }
}
