package com.sbisec.helios.gw.brokerageMenu.jointSubscript.form;

import lombok.Data;

/**
 * リクエストパラメータ
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A006 CSV出力
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageA006ApiRequest {

    /** 仲介業者除外(半角英数字) */
    private String chkBrokerCodeExclude;

    /** 仲介業者コード(数字) */
    private String brokerCode;

    /** 部店コード(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

    /** 手続状況(数字) */
    private String editStatus;

    /** 共同募集支店コード(数字) */
    private String jointBranchCode;

}
