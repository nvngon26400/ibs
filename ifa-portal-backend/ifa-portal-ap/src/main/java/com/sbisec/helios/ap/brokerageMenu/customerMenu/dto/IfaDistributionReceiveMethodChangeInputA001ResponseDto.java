package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_010201-02_1
 * 画面名：分配金受取方法変更入力
 * 2023/11/28 新規作成
 *
 * @author SCSK 江口
 *
 */
@Data
public class IfaDistributionReceiveMethodChangeInputA001ResponseDto {

    /** ファンド名（全角半角）. */
    private String fundName;

    /** 保有口数（数値(整数)）. */
    private String unitVolume;

    /** 売却注文中（数値(整数)）. */
    private String sellingVolume;

    /** 分配金受取方法. */
    private String method;

    /** 分配金受取区分. */
    private String distributionReceiveClassification;

    /** メッセージ. */
    private String infoMsg;

    /** 回数（数値(整数)）. */
    private String times;

    /** 号1（全角半角）. */
    private String issue1;

    /** 号2（全角半角）. */
    private String issue2;

    /** 預り区分（全角半角）. */
    private String depositType;

}
