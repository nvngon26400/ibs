package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 初期化 リクエスト
 *
 * @author 大連 えん
 */
@Data
@JsonSerialize
public class IfaOtherRemainPowerRestrainInputConfirmA001ApiRequest {
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

    /** 注文後の買付可能金額 */
    private String buyingPowerTotalAfter;

    /** ジュニアNISA口座フラグ */
    private String jrNisageneralAccountFlag;
    
}
