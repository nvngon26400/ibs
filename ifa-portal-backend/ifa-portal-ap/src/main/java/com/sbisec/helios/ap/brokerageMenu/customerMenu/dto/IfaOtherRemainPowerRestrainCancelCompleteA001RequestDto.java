package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * その他余力拘束注文取消完了 A001リクエストDto
 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto {

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

    /** ジュニアNISA口座開設フラグ */
    private String jrNisageneralAccountFlag;

    /** 注文後の買付可能金額 */
    private String buyingPowerTotalAfter;

    /** 注文後NISA成長投資枠 */
    private String isaSeityoLimitAmountAfter;

    /** 注文後NISAつみたて投資枠 */
    private String isaTsumitateLimitAmountAfter;
    

}
