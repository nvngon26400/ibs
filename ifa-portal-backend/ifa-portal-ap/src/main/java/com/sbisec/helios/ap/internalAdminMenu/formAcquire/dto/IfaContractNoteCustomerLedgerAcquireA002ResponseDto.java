package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 A002応答
 *
 * @author SCSK
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IfaContractNoteCustomerLedgerAcquireA002ResponseDto {
    
    /** 作成日A. */
    private String createDateA;
    
    /** 作成日B. */
    private String createDateB;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者名（全角半角）. */
    private String brokerName;
    
    /** 帳簿種別. */
    private String docClass;
    
    /** 対象帳簿（全角半角）. */
    private String targetDoc;
    
    /** ダウンロードファイル名. */
    private String downloadFileName;
    
    /** DL. */
    private String dl;
}
