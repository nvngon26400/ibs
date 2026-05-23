package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * その他余力拘束注文入力 A002Apiレスポンス

 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputOrderConfirmA002ApiResponse {

    /** 注文後の買付可能金額 */
    private String buyingPowerTotalAfter;

    /** 口座区分 */
    private String accountType;

    /** 拘束種別 */
    private String restrainType;

    /** 金額（買付余力） */
    private String netAmount;

    /** 金額（NISA成長投資枠） */
    private String isaSeityoLimitAmount;

    /** 金額（NISAつみたて投資枠） */
    private String isaTsumitateLimitAmount;

    /** 拘束期限 */
    private String restrainDateTo;

    /** 拘束理由 */
    private String restrainReason;

    /** 確認項目 */
    private String confirmItem;

    /** ジュニアNISA口座開設フラグ */
    private String jrNisageneralAccountFlag;

}
