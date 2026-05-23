package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import lombok.Data;

/**
 * 仕組債銘柄情報　早期償還判定日/利率判定日

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoDtoResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate {
    
    /** 番号. */
    private String number;
    
    /** 判定日. */
    private String judgmentDate;
    
}
