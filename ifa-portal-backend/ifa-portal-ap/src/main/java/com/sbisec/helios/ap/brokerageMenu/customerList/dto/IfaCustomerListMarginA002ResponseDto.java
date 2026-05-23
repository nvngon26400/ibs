package com.sbisec.helios.ap.brokerageMenu.customerList.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaCustomerListMarginA002ResponseDto {
    
    /** 顧客一覧リスト. */
    private List<IfaCustomerListMarginA002ResponseDtoCustomerList> customerListList;
}
