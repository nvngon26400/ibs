package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL010用リクエストモデル
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 *
 * @author SBI大連 夏
 * @date   2025/08/06
 */

@Data
public class IfaContactCorrectSql010RequestModel {
    
    /** 回答NO */
    private String kaitouNo;
    
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** IFA回答NO */
    private String ifaKaitouNo;
    
}
