package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 注文発注 リクエスト
 *
 * @author 大連 えん
 */
@Data
@JsonSerialize
public class IfaOtherRemainPowerRestrainInputConfirmA002ApiRequest {
    /** 口座区分 */
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

    /** ジュニアNISA口座開設フラグ */
    private String jrNisageneralAccountFlag;
}
