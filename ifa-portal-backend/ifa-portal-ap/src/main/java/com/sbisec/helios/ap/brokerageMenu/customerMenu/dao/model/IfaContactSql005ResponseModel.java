package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql005ResponseModel {

    /** 書類分類名 */
    private String shoruiBunruiMei;

    /** ステータス遷移日時 */
    private String stsSeniNichiji;

    /** 発送ステータス */
    private String hassouSts;

    /** 書類名 */
    private String shoruimei;

    /** 理由コード */
    private String riyuuCd;

    /** 内容 */
    private String naiyou;

    /** 備考 */
    private String bikou;

    /** 目論見書No */
    private String mokuronmishoNo;

    /** 書類請求No */
    private String shoruiSeikyuuNo;

    /** ユーザー名 */
    private String userNm;
}
