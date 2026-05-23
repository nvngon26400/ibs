package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL005　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql005ResponseModel {
    
    /** 早期償還判定日. */
    private String sbepEarlyPaymentJudgeDate;
    
}
