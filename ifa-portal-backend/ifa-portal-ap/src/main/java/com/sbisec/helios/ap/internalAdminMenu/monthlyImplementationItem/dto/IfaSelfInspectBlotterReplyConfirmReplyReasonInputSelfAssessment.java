package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Data
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment {
    
    /** 自己点検項目ID（数字）. */
    private String selfCheckItemId;
    
    /** 回答理由（全角半角）. */
    private String answerReason;
    
    /** 回答結果（半角英数字）. */
    private String answerResult;
    
}
