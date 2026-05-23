package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 書類請求確認A002リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmA002ApiRequest {

    /** 分類コード */
    private String bunruiCd;

    /** 分類名 */
    private String bunruimei;

    /** 書類コード */
    private String shoruiCd;

    /** 書類名 */
    private String shoruimei;

    /** 部数 */
    private String busuu;

    /** 交付区分コード */
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
}
