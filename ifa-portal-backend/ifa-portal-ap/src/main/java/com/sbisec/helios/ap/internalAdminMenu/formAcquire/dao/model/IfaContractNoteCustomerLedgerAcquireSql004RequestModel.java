package com.sbisec.helios.ap.internalAdminMenu.formAcquire.dao.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取引日記帳・顧客勘定元帳 SQL004要求
 *
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IfaContractNoteCustomerLedgerAcquireSql004RequestModel {
    
    /**
     * 明細
     *
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        
        /** ファイル名. 例) STATUTORY_BOOKS_BOND_0509_20200414.pdf */
        private String fileName;
        
        /** 帳票種別.2桁コード 例) TRSTの場合 01 */
        private String ledgerClass;
        
        /** 仲介業者コード（数字）. */
        private String brokerCode;
        
        /** 作成日時.ディレクトリ名YYYYMMDD */
        private String createDate;
        
    }
    
    /** ユーザーID. */
    private String userId;
    
    /** 明細リスト */
    private List<Item> items;
    
}
