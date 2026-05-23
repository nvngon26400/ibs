package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;
import lombok.Data;

/**
 * 画面ID：SUB020301_03-02,SUB020301_03-03
 * 画面名：ノックイン銘柄保有一覧,ノックアウト銘柄保有一覧

 * @author 大崎 辰弥
    2024/06/12 新規作成
 */

@Data
public class IfaKnockInKnockOutBrandHoldingListSql001RequestModel {
    /** ノックイン,ノックアウト判定フラグ ture:ノックインSQL001,false:ノックアウトSQL001 */
    private Boolean isKnockIn;

    /** 上限件数 */
    private int maxRow;

    /** 仲介業者コード. */
    private List<String> brokerCodeList;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 取引コース（全角半角）. */
    private List<IfaKnockInKnockOutBrandHoldingListSql001RequestModelCourseSelected> courseSelected;
    
    /** FCT030.仲介業者営業員リスト */
    private List<IfaKnockInKnockOutBrandHoldingListSql001RequestModelBrokerCharge> brokerChargeList;
    
    /** ユーザ共通情報.権限コード. */
    private String privId;

}
