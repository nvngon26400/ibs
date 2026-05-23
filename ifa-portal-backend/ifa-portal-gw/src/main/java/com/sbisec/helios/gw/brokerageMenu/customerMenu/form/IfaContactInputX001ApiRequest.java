package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * リクエスト
 * 画面ID：SUB0202_0106-03
 * 画面名：接触履歴入力
 * アクション：X001 追加入力/管理項目修正
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
@JsonSerialize
public class IfaContactInputX001ApiRequest {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 処理区分 */
    private String operationType;
}
