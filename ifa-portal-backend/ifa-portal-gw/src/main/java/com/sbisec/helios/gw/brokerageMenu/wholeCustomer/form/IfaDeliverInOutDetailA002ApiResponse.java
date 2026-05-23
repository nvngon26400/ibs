package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class IfaDeliverInOutDetailA002ApiResponse {

    /** 取得件数. */
    private String acquireCount;

    /** データ件数. */
    private String dataCount;

    /** 入出庫明細. */
    private List<IfaDeliverInOutDetailDeliverInOutDetail> deliverInOutDetail;

}
