package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文訂正確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉 秀行
 */
@Data
public class IfaMarginRepayOrderCorrectConfirmA001bResponseDto {
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 訂正後建玉余力 */
    private String positionPower;

    /** 訂正SOR注文結果区分 */
    private String correctSorOrderResultClassification;
    
    /** リクエスト内容. */
    private IfaMarginRepayOrderCorrectConfirmA001aRequestDto request;
}
