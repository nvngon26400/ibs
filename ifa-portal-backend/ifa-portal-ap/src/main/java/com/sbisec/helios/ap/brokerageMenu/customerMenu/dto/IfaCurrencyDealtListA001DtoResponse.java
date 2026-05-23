package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;
import lombok.Data;

/**
 * @author 池亀緑
 *
 */
@Data
public class IfaCurrencyDealtListA001DtoResponse {
    
    /** 通貨リスト */
    private List<IfaCurrencyDealtListA001DtoResponseCurrency> currencyList;
    
}
