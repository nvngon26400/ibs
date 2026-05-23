package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 仕組債銘柄情報　A001　DTOリクエスト

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoA001DtoRequest {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}
