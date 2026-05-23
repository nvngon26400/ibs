package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * その他余力拘束注文確認 A002Apiレスポンス

 * @author 大連 えん
 *
 */
@Data
public class IfaOtherRemainPowerRestrainInputConfirmA002ApiResponse {

    /** EC受注番号 */
    private String orderNo;

    /** 注文後の買付可能金額 */
    private String buyingPowerTotalAfter;

    /** 受注日時 */
    private String acceptDateTime;

    /** 受注日 */
    private String acceptDate;

    /** 受注日 */
    private String acceptTime;

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
