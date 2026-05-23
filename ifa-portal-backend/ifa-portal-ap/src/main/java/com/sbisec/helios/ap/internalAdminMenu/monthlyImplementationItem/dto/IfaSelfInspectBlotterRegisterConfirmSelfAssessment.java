package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@Data
public class IfaSelfInspectBlotterRegisterConfirmSelfAssessment {

    /** 自己点検項目ID（数字）. */
    private String selfCheckItemId;
    
    /** 自己点検項目名. */
    private String selfAssessmentItemName;
    
    /** 理由必須フラグ. */
    private String reasonRequiredFlag;
    
    /** 確認. */
    private String confirmation;
    
    /** 回答（半角英数字）. */
    private String answer;
    
    /** 回答理由（全角半角）. */
    private String answerReason;
    
    /** 回答回数（数値(整数)）. */
    private String answerCount;
    
    /** 回答結果（半角英数字）. */
    private String answerResult;
    
}
