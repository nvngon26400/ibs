package com.sbisec.helios.ap.internalAdminMenu.formAcquire.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 帳票ファイル
 * @author SCSK
 *
 */
@Data
@AllArgsConstructor
public class LedgerFile {
    
    private static final int LENGTH = 38;
    
    /** 対象帳票4桁 */
    private String targetLedger;
    
    /** 仲介業者コード4桁 */
    private String brokerCode;
    
    /** 基準日 YYYYMMDD */
    private String date;
    
    /**
     * ファイル名からパースする
     * @param fileName ファイル名。e.g. STATUTORY_BOOKS_BOND_0509_20200414.pdf
     * @return 生成したLedgerFile、不可の場合はnull
     */
    public static LedgerFile parse(String fileName) {
        
        if (fileName.length() == LENGTH) {
            return new LedgerFile(fileName.substring(16, 20), fileName.substring(21, 25), fileName.substring(26, 34));
        }
        return null;
    }
}
