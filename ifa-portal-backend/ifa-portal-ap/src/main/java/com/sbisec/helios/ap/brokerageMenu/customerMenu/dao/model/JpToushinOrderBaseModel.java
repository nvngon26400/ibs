package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class JpToushinOrderBaseModel {

    /** ファンドコード（回数） */
    private String fundCdKaisu;

    /** ファンドコード（号） */
    private String fundCdGou;

    /** 口数 */
    private String quantity;

    /** 乗換優遇区分 */
    private String norikaeYuguKbn;

    /** 売買区分 */
    private String tradeKbn;

    /** 特定口座区分 */
    private String specificKbn;

    /** 預り区分 */
    private String azukariKbn;

    /** ポイント種別 */
    private String pointType;

    /** 作成日時 */
    private String createTime;

    /** ユーザー名 */
    private String userNm;
}
