package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 大崎辰弥
    2024/03/01 新規作成
 */
@Data
public class IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus {
    
    /** FCT030.仲介業者営業員リスト.仲介業者コード. */
    private String brokerCode;

    /** FCT030.仲介業者営業員リスト.営業員コード. */
    private String empCode;
}