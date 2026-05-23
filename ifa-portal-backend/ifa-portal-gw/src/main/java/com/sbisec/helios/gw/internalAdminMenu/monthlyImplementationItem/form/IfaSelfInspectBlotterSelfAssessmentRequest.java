package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Data
public class IfaSelfInspectBlotterSelfAssessmentRequest {
    
    /** 自己点検項目ID（数字）. */
    @NotEmpty(message = "自己点検リスト.自己点検項目ID")
    private String selfCheckItemId;
    
    /** 自己点検項目名. */
    @NotEmpty(message = "自己点検リスト.自己点検項目名")
    private String selfAssessmentItemName;
    
    /** 理由必須フラグ. */
    @NotEmpty(message = "自己点検リスト.理由必須フラグ")
    private String reasonRequiredFlag;
    
    /** 確認. */
    private String confirmation;
    
    /** 回答（半角英数字）. */
    private String answer;
    
    /** 回答理由（全角半角）. */
    @NotEmpty(message = "自己点検リスト.回答理由")
    private String answerReason;
    
    /** 回答回数（数値(整数)）. */
    @NotEmpty(message = "自己点検リスト.回答回数")
    private String answerCount;
    
    /** 回答結果（半角英数字）. */
    @NotEmpty(message = "自己点検リスト.回答結果")
    private String answerResult;
    
}
