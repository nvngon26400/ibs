package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * その他余力拘束注文確認 A002リクエストDto
 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputConfirmA002RequestDto {

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
