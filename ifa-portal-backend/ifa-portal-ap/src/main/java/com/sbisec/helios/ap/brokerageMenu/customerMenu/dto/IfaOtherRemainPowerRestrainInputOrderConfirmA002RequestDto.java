package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * その他余力拘束注文入力 A002リクエストDto
 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto {

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
