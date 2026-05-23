package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Data
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputSql001RequestModel {
    
    /** 自己点検項目ID（数字）. */
    private Integer selfCheckItemId;
    
    /** 登録年月. */
    private String registerDate;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 回答理由（全角半角）. */
    private String answerReason;
    
    /** ユーザID */
    private String userId;
}
