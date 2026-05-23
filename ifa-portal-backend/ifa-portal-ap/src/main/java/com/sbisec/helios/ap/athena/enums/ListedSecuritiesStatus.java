package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:銘柄上場状況
 * @author shuchen.xin
 * @date:   06/04/2021
 */
public enum ListedSecuritiesStatus {
    TRADABLE("TRADABLE", "取引可")
    , BUY_STOP("BUY_STOP", "買付停止")
    , SELL_STOP("SELL_STOP", "売付停止")
    , BUY_SELL_STOP("BUY_SELL_STOP", "売買停止")
    , DELISTING("DELISTING", "上場廃止")
    ;

    ListedSecuritiesStatus(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static ListedSecuritiesStatus getById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }

        ListedSecuritiesStatus[] enums = values();

        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) {
                return enums[i];
            }
        }

        return null;
    }
}
