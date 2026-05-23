package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 新規建不足明細 Dto.

 * @author SCSK 矢口
    2023/12/1 移植
 */
@Data
public class InitialMarginShortfallDetail implements Serializable {
    
    private static final long serialVersionUID = 2489026688414145672L;
    
    public InitialMarginShortfallDetail() {
    }
    
    /** 新規建不足発生日（yyyy-MM-dd形式） */
    private String assessmentDate;
    
    /** 新規建不足解消期限日（yyyy-MM-dd形式） */
    private String depositDueDate;
    
    /** 新規建不足額合計 */
    private String totalInitialMarginShortfall;
    
    /** 入金新規建不足充当額 */
    private String cashDepositAmount;
    
    /** 入庫新規建不足充当額 */
    private String collateralDepositAmount;
    
    /** 決済建玉新規建不足充当額 */
    private String closedPositionEquivalentToDeposit;
    
    /** 新規建不足解消必要額 */
    private String requiredDepositAmount;

}
