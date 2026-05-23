package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaMarginPowerDomesticA001ApiResponseMarginCallinfo {
    
    /** 追証情報.解消期限/入金・入庫計上日（T-4～T+0） */
    private String cancellationDeadlineRecordingDate;
    
    /** 追証情報.解消期限/入金・入庫計上日（T-4～T+0）(確定・概算) */
    private String cancellationDeadlineRecordingDateDefiniteApproximate;
    
    /** 追証情報.解消期限/入金・入庫計上日受渡日（T+1） */
    private String cancellationDeadlineRecordingDateSettlementDate;
    
    /** 追証情報.当初追証額（T-4～T+0） */
    private String initialMarginAmount;
    
    /** 追証情報.追証発生日（T-4～T+0） */
    private String marginCallDate;
    
    /** 追証情報.預り金不足額（T-4～T+0）） */
    private String depositShortage;
    
    /** 追証情報.入金額（T-4～T+0） */
    private String depositAmount;
    
    /** 追証情報.入庫額 及び 決済建玉充当額（決済建玉金額×20%）（T-4～T+0） */
    private String settlementOpenInterestYen;
    
}
