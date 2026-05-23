package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * その他余力拘束注文完了 A001レスポンスDto
 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto {

    /** 選択口座 */
    private String accountType;

    /** 拘束種別 */
    private String restrainType;

    /** 拘束金額（買付余力） */
    private String netAmount;

    /** 拘束金額（NISA成長投資枠） */
    private String isaSeityoLimitAmount;

    /** 拘束金額（NISAつみたて投資枠） */
    private String isaTsumitateLimitAmount;

    /** 拘束期限 */
    private String restrainDateTo;

    /** 拘束理由 */
    private String restrainReason;

    /** 受注日時 */
    private String acceptDateTime;

    /** 注文後の買付可能金額 */
    private String buyingPowerTotalAfter;

    /** EC受注番号 */
    private String orderNo;

    /** ジュニアNISA口座開設フラグ */
    private String jrNisageneralAccountFlag;

    /** 注文後NISA成長投資枠 */
    private String isaSeityoLimitAmountAfter;

    /** 注文後NISAつみたて投資枠 */
    private String isaTsumitateLimitAmountAfter;
    

}
