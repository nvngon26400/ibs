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
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessmentRequest {
    
    /** 自己点検項目ID（数字）. */
    @NotEmpty(message = "自己点検リスト.自己点検項目ID")
    private String selfCheckItemId;
    
    /** 回答理由（全角半角）. */
    @NotEmpty(message = "自己点検リスト.回答理由")
    private String answerReason;
    
    /** 回答結果（半角英数字）. */
    @NotEmpty(message = "自己点検リスト.回答結果")
    private String answerResult;
}
