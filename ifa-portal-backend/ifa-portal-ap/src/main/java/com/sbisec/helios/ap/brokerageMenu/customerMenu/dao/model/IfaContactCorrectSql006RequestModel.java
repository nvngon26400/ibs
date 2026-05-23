package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL006用リクエストモデル
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactCorrectSql006RequestModel {
    /** 回答内容 */
    private String kaitouNaiyou;
    /** 変更日時 */
    private String henkouNichiji;
    /** 更新ユーザID */
    private String kosinUserId;
    /** CCS更新ユーザID */
    private String ccsKosinUserId;
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
}
