package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * リクエスト
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * アクション：A001 初期化
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactCorrectA001ApiRequest {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** IFA回答NO */
    private String ifaKaitouNo;
    
    /** 登録グループ */
    private String tourokuGroup;
}
