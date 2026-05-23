package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 注文取消 リクエスト
 *
 * @author 大連 えん
 */
@Data
@JsonSerialize
public class IfaOtherRemainPowerRestrainCancelConfirmA002ApiRequest {
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

    /** EC受注番号 */
    private String orderNo;

    /** 注文後NISA成長投資枠 */
    private String isaSeityoLimitAmountAfter;

    /** 注文後NISAつみたて投資枠 */
    private String isaTsumitateLimitAmountAfter;
}
