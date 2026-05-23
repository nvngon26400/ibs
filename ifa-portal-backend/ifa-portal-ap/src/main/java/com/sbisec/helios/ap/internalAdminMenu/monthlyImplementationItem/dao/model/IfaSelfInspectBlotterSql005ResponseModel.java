package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Data
public class IfaSelfInspectBlotterSql005ResponseModel {
    
    /** ソート番号. */
    private Integer sortNo;
    
    /** 自己点検項目ID（数字）. */
    private Integer selfCheckItemId;
    
    /** 自己点検項目名. */
    private String selfAssessmentItemName;
    
    /** 確認. */
    private String confirmation;
    
    /** 回答（半角英数字）. */
    private String answer;
    
    /** 理由必須フラグ. */
    private String reasonRequiredFlag;
    
    /** 回答理由（全角半角）. */
    private String answerReason;
    
    /** 回答回数（数値(整数)）. */
    private Integer answerCount;
    
    /** 回答結果（半角英数字）. */
    private String answerResult;
    
}
