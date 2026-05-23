package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql004ResponseModel {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** カテゴリー名称(中) */
    private String toiawaseMei;

    /** カテゴリー名称（小） */
    private String toiawaseSMei;

    /** 問合せ日時 */
    private String toiawaseNichiji;

    /** 対応ステータス */
    private String taiouSts;

    /** 問合せ内容 */
    private String toiawaseNaiyou;

    /** ユーザー名 */
    private String userNm;

    /** 回答IFA問合せNO */
    private String ansIfaToiawaseNo;

}
