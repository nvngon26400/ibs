package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

@Data
public class IfaSellUnableDetailA001ApiResponse {

    /** 売却不可明細リスト. */
    private List<IfaSellUnableDetailA001ApiResponseSellUnableDetail> sellUnableDetailList;

}
