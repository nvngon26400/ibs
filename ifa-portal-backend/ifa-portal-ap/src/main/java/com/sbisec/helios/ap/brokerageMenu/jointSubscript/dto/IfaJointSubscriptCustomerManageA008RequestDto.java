package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto;

import lombok.Data;

/**
 * サービス用リクエストモデル
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * アクション：A008 顧客情報詳細
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaJointSubscriptCustomerManageA008RequestDto {

    /** 部店(半角英数字) */
    private String butenCode;

    /** 口座番号(数字) */
    private String accountNumber;

}
