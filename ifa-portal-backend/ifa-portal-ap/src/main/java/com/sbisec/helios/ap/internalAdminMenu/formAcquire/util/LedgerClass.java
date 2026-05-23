package com.sbisec.helios.ap.internalAdminMenu.formAcquire.util;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * 帳票種別定義
 * @author SCSK
 *
 */
@Getter
public enum LedgerClass {
    
    TRST("01", "InvestmentTrustDiary"), BOND("02", "BondDiary"), CUST("03", "CustomerLedger");
    
    /** 画面からのコード=DBのコード */
    private String code;
    
    /** ディレクトリ名 */
    private String dirName;
    
    /** コンストラクタ */
    LedgerClass(String code, String dirName) {
        
        this.code = code;
        this.dirName = dirName;
    }
    
    /** コード検索(無い場合はnull) */
    public static LedgerClass valueOfCode(String code) {
        
        for (var e : values()) {
            if (StringUtils.equals(e.code, code)) {
                return e;
            }
        }
        return null;
    }
    
    /** 名前検索(無い場合はnull) */
    public static LedgerClass valueOfName(String name) {
        
        for (var e : values()) {
            if (StringUtils.equals(e.name(), name)) {
                return e;
            }
        }
        return null;
    }
    
}
