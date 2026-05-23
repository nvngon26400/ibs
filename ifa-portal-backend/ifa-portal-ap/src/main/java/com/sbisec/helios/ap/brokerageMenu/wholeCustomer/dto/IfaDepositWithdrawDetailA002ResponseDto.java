package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

/**
 * 入出金明細A002レスポンスDTO
 *
 * @author SCSK
 */
@Data
public class IfaDepositWithdrawDetailA002ResponseDto {
    
    /** 取得件数. */
    private String acquireCount;
    
    /** データ件数. */
    private String dataCount;
    
    /** 入出金明細. */
    private List<IfaDepositWithdrawDetailCsvItems> depositWithdrawDetail;
    
    /** 出金額合計 合計額. */
    private String withdrawTotalAmount;
    
    /** 出金額合計 件数. */
    private String withdrawTotalCount;
    
    /** 入金額合計 合計額. */
    private String depositTotalAmount;
    
    /** 入金額合計 件数. */
    private String depositTotalCount;
    
    /** 振替出金額合計 合計額. */
    private String transferWithdrawTotalAmount;
    
    /** 振替出金額合計 件数. */
    private String transferWithdrawTotalCount;
    
    /** 振替入金額合計 合計額. */
    private String transferDepositTotalAmount;
    
    /** 振替入金額合計 件数. */
    private String transferDepositTotalCount;

}
