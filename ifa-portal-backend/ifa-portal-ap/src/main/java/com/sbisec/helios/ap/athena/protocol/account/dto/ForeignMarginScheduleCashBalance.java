package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 外貨信用保証金残高スケジュールリスト Dto.

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Data
public class ForeignMarginScheduleCashBalance implements Serializable {
    
    private static final long serialVersionUID = 8420433003912347753L;
    
    public ForeignMarginScheduleCashBalance() {
    
    }
    
    /** 営業日(yyyy-MM-dd) */
    private String businessDate;
    
    /** 保証金現金 */
    private String marginCash;
    
    /** 支払額 */
    private String paymentAmount;
    
    /** 未約定信用決済損 */
    private String unexecPayBgLoss;
    
    /** 受取額 */
    private String receiveAmountValue;
    
    /** 振替予定額（現物口座→信用口座） */
    private String transferEstimatedAmount;
    
    /** 残高（保証金） */
    private String marginBalance;
    
    /** 必要保証金額 */
    private String requiredMargin;
    
}
