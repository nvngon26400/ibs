package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

@Data
public class IfaSellUnableDetailA001DtoResponse {
    
    /** 売却不可明細リスト. */
    private List<IfaSellUnableDetailA001DtoResponseSellUnableDetail> sellUnableDetailList;

}
