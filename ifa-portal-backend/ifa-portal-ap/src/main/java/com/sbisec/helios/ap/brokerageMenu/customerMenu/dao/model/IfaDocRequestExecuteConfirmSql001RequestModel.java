package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求確認sql1リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmSql001RequestModel {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 顧客ID */
    private String kokyakuId;

    /** 書類コード */
    private String shoruiCd;

    /** 書類名 */
    private String shoruimei;

    /** 書類分類コード */
    private String shoruiBunruiCd;

    /** 書類分類名 */
    private String shoruiBunruiMei;

    /** 発送ステータス */
    private String hassouSts;

    /** ユーザーＩＤ */
    private String userId;

    /** 取消区分 */
    private String torikeshiKbn;

    /** 部数 */
    private String busuu;

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

    /** 内容キャプション */
    private String naiyouCaption;

    /** 内容(入力) */
    private String naiyou;

    /** 備考キャプション */
    private String bikouCaption;

    /** 備考(入力) */
    private String bikou;

    /** 処理回数 */
    private String shoriKaisuu;

    /** 削除フラグ */
    private String sakujoFlg;

    /** 前回超かんたんフラグ */
    private String zenkaiTyoukantanFlg;
}
