package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaCurrencyDealtListA001ApiResponse {
    
    /** 通貨リスト. */
    private List<IfaCurrencyDealtListA001ApiResponseCurrency> currencyList;
    
}
