package com.sbisec.helios.ap.extapi.servicenow.dao.model;

import lombok.Data;

/**
 * 申請情報項目一覧を取得 TB_MED_USERS_PRIV 情報モデル
 *
 * @author SCSK
 */
@Data
public class IfaA006MedUsrsPrivModel {
    
    /** ユーザーID. */
    private String userId;
    
    /** SEQ番号. */
    private String seqNumber;
    
    /** SBI証券支店コード. */
    private String sbiSecuritiesBranchCode;
    
    /** 仲介業者コード. */
    private String intermediaryAgentCode;
    
    /** 仲介業者支店コード. */
    private String intermediaryAgentBranchCode;
    
    /** 仲介業者担当者コード. */
    private String intermediaryAgentEmployeeCode;
    
    /** 仲介業者担当者名. */
    private String intermediaryAgentEmployeeName;
    
    /** 削除フラグ. */
    private String deleteFlag;
    
    /** 作成者. */
    private String creator;
    
    /** 作成日時. */
    private String creationTime;
    
    /** 削除者. */
    private String deleter;
    
    /** 削除日時. */
    private String deletionTime;
    
    /** 本支店名. */
    private String branchName;
    
    /** 仲介業者支店種別. */
    private String brokerBranchType;
    
    /** 仲介業者支店名. */
    private String brokerBranchName;
    
}
