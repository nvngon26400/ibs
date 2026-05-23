package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;

import lombok.Data;

/**
 * 取引日記帳・顧客勘定元帳 SQL002応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaContractNoteCustomerLedgerAcquireSql002ResponseModel {
    
    /** ユーザID. */
    private String userId;
    
    /** 作成日 */
    private String createDate;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** dl */
    private String dl;
    
    /** ダウンロードファイル名 */
    private String fileName;
    
    /** 帳票種別 */
    private String bookType;
    
    /** 帳票名 */
    private String bookName;
    
    /** 件数 */
    private long totalCount;
}
