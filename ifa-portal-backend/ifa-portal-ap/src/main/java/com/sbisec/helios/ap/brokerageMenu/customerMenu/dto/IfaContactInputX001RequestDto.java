package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * サービス用リクエストモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * アクション：X001 追加入力/管理項目修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputX001RequestDto {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 処理区分 */
    private String operationType;
}
