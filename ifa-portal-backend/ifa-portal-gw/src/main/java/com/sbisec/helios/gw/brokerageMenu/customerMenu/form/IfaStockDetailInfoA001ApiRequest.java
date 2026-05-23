package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaStockDetailInfoA001ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 市場. */
    @NotEmpty(message = "市場")
    private String market;
    
}
