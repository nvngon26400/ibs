package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * レスポンス
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * アクション：A001 初期化
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactCorrectA001ApiResponse {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 問合せNO */
    private String toiawaseNo;

    /** IFA回答NO */
    private String ifaKaitouNo;

    /** 回答NO */
    private String kaitouNo;

    /** 内容 */
    private String naiyou;
    
    /** 登録グループ */
    private String tourokuGroup;
    
    /** 回答情報リスト */
    private List<IfaContactAnswerList> answerList;
}
