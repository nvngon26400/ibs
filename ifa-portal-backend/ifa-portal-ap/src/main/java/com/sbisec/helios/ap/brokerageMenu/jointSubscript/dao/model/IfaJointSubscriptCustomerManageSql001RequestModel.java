package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model;

import lombok.Data;

/**
 * 共同募集 顧客管理一覧表示情報取得用リクエストモデル
 * 2024/12/04 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageSql001RequestModel {

    /** 仲介業者除外(半角英数字) */
    private String chkBrokerCodeExclude;

    /** 仲介業者コードリスト(数字) */
    private String brokerCode;

    /** 部店コード(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 手続状況(数字) */
    private String editStatus;

    /** 共同募集支店コード(数字) */
    private String jointBranchCode;
    
    /** ユーザ権限 */
    private String privId;

    /** ログインユーザID */
    private String loginId;

    /** 仲介業者ID マネプラ（0509）のみ */
    private String broker0509;

    /** 検索上限件数 */
    private int querySizeLimit;

}
