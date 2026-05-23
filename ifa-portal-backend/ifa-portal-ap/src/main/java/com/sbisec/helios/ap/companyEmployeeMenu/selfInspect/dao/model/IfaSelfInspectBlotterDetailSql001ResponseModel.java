package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model;

import lombok.Data;

/**
 * SUB0506_01-02_自己点検記録簿詳細 SQL001応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaSelfInspectBlotterDetailSql001ResponseModel {
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 自己点検ID. */
    private String corSelfCheckId;
    
    /** ソート番号. */
    private String corSortNo;
    
    /** 自己点検項目ID（数字）. */
    private String corSelfCheckItemId;
    
    /** 自己点検項目名. */
    private String corSelfCheckItemName;
    
    /** 確認. */
    private String corConfirmationFlg;
    
    /** 回答理由（全角半角）. */
    private String corAnswerReason;
    
    /** 回答結果. */
    private String corAnswerResult;
    
    /** メモ */
    private String corMemo;
    
}
