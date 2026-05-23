package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 余力追証明細 Dto.

 * @author SCSK 矢口
    2023/12/1 新規作成
 */
@Data
public class PowerMarginCallDetail implements Serializable {
    
    private static final long serialVersionUID = -9135869656449568031L;
    
    public PowerMarginCallDetail() {
    
    }
    
    /** 値洗い区分 */
    private String markToMarketStatus;
    
    /** 追証発生日（現地）（yyyy-MM-dd形式） */
    private String frnAssessmentDate;
    
    /** 追証発生日（国内）（yyyy-MM-dd形式） */
    private String assessmentDate;
    
    /** 追証状況 true:追証/false:追証でない */
    private Boolean marginCalled;
    
    /** 追証解消期限日（yyyy-MM-dd形式） */
    private String depositDueDate;
    
    /** 建玉強制返済予定日（yyyy-MM-dd形式） */
    private String forcedCloseDueDate;
    
    /** 建玉強制返済予定日の市場開始日時（"yyyy/MM/dd HH:mm"形式） */
    private String forcedCloseDueOpenDatetime;
    
    /** 追証請求額 */
    private String additionalMargin;
    
    /** 入金追証充当額 */
    private String cashDepositAmount;
    
    /** 入庫追証充当額 */
    private String collateralDepositAmount;
    
    /** 決済建玉追証充当額 */
    private String closedPositionEquivalentToDeposit;
    
    /** 追証解消必要額 */
    private String requiredDepositAmount;
    
    /** 追証強制決済状況 true:強制決済後/false:強制決済前 */
    private Boolean forcedClosed;
}
