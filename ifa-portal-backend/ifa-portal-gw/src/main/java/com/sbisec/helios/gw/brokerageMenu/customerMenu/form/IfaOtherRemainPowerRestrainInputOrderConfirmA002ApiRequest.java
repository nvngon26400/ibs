package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 注文確認 リクエスト
 *
 * @author 大連 えん
 */
@Data
@JsonSerialize
public class IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiRequest {
    /** 口座区分 */
    private String accountType;

    /** 拘束種別 */
    @NotEmpty(message = "拘束種別")
    private String restrainType;

    /** 金額（買付余力） */
    private String netAmount;

    /** 金額（NISA成長投資枠） */
    private String isaSeityoLimitAmount;

    /** 金額（NISAつみたて投資枠） */
    private String isaTsumitateLimitAmount;

    /** 拘束期限 */
    @NotEmpty(message = "拘束期限")
    private String restrainDateTo;

    /** 拘束理由 */
    private String restrainReason;

    /** 確認項目 */
    @NotEmpty(message = "確認項目")
    private String confirmItem;

    /** ジュニアNISA口座開設フラグ */
    private String jrNisageneralAccountFlag;
}
