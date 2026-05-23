package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL007　リクエスト

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql007RequestModel {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
}
