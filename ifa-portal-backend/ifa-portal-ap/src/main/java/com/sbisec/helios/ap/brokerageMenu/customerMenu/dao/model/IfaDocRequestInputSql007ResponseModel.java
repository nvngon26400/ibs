package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql7レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql007ResponseModel {
    
    /** 書類請求NO */
    private String shoruiSeikyuuNo;
    
    /** 枝番 */
    private String edaban;

    /** 分類 */
    private String shoruiBunruiMei;

    /** 書類名 */
    private String shoruimei;

    /** 部数 */
    private String busuu;

    /** 交付区分 */
    private String hassouSts;

    /** 内容(名前) */
    private String naiyouCaption;

    /** 内容(入力) */
    private String naiyou;

    /** 備考(名前) */
    private String bikouCaption;

    /** 備考(入力) */
    private String bikou;

    /** オプション1(名前) */
    private String option1;

    /** オプション1(選択) */
    private String sentakuMei1;

    /** オプション2(名前) */
    private String option2;

    /** オプション2(選択) */
    private String sentakuMei2;

    /** オプション3(名前) */
    private String option3;

    /** オプション3(選択) */
    private String sentakuMei3;

    /** テキスト1(名前) */
    private String txt1;

    /** テキスト1(入力) */
    private String txtNaiyou1;

    /** テキスト2(名前) */
    private String txt2;

    /** テキスト2(入力) */
    private String txtNaiyou2;

    /** テキスト3(名前) */
    private String txt3;

    /** テキスト3(入力) */
    private String txtNaiyou3;

    /** テキスト4(名前) */
    private String txt4;

    /** テキスト4(入力) */
    private String txtNaiyou4;

    /** テキスト5(名前) */
    private String txt5;

    /** テキスト5(入力) */
    private String txtNaiyou5;

    /** 取扱者 */
    private String userNm;
}
