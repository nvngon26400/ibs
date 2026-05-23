package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaUnsettleDetailA001ApiResponse_UnsettleDetail {

    /** 項番（全角半角）. */
    private String kouban;

    /** 受渡日. */
    private String settlementDate;

    /** 約定日. */
    private String tradeDate;

    /** 取引（全角半角）. */
    private String openTradeKbn;

    /** 摘要（全角半角）. */
    private String dispAbstract;

    /** 数量（数値(整数)）. */
    private String quantity;

    /** 単価（数値(小数)）. */
    private String price;

    /** 精算金額（数値(整数)）. */
    private String settlementAmount;

}
