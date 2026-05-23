package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 預り金不足明細 Dto.

 * @author SCSK 矢口
    2023/12/1 移植
 */
@Data
public class DeficitBalanceDetail implements Serializable {
    
    private static final long serialVersionUID = -4006064325718411874L;
    
    public DeficitBalanceDetail() {
    }
    
    /** 請求種類 */
    private String deficitRequiredType;
    
    /** 入金期限日 */
    private String depositDueDate;
    
    /** 受渡日 */
    private String valueDate;
    
    /** 預り金 */
    private String depositCash;
    
    /** 委託保証金現金 */
    private String marginCash;
    
    /** 赤残金額 */
    private String deficitBalanceAmount;
    
    /** 入金額 */
    private String depositAmount;
    
    /** 振替金額 */
    private String deficitTransferAmount;
    
    /** 赤残ステータス */
    private String deficitBalanceStatus;
    
    /** 赤残付替中止フラグ */
    private Boolean transferCancelled;
    
    /** 解消日時 */
    private String releaseDatetime;
    
}
