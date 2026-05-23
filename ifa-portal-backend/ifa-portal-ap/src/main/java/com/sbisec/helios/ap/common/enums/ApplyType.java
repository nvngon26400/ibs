package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


/**
 * 摘要コード区分
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ApplyType {
    
    /** 銘柄名 */
    BRAND_NAME("00", ""),
    /** 振込入金 */
    TRANSFER_DEPOSIT("01", "振込入金"),
    /** 現金 */
    CASH("11", "現金"),
    /**　振込出金 */
    TRANSFER_WITHDRAWAL("02", "振込出金"),
    /**　信用配当金+銘柄名 */
    CREDIT_DIVIDEND("12", "信用配当金"),
    /**　外国株式配当金+銘柄名 */
    FOREIGN_STOCK_DIVIDEND("12", "外国株式配当金"),
    /**　譲渡益税徴収金 */
    CAPITAL_GAINS_TAX("03", "譲渡益税徴収金"),
    /**　権利処理代金+銘柄名 */
    RIGHTS_PROCESSING_FEE("13", "権利処理代金"),
    /**　譲渡益税調整金 */
    CAPITAL_GAINS_TAX_ADJUSTMENT("04", "譲渡益税調整金"),
    /**　償還金+銘柄名 */
    REDEMPTION_AMOUNT("14", "償還金"),
    /**　利金／収益金+銘柄名 */
    PROFIT_AND_EARNINGS("15", "利金/収益金"),
    /**　口座管理料 */
    ACCOUNT_MANAGEMENT_FEE("16", "口座管理料"),
    /**　その他 */
    OTHER("17", "その他"),
    /**　貸株金利入金 */
    LOAN_INTEREST_DEPOSIT("18", "貸株金利"),
    /**　配当金相当額入金 */
    DIVIDEND_DEPOSIT("19", "貸株配当金相当額"),
    /** 株式配当金　*/
    STOCK_DIVIDEND("20", "株式配当金"),
    /**　定時定額買付金の入金 */
    REGULAR_PURCHASE_DEPOSIT("21", "定時定額買付金の入金"),
    /** 投信積立銀行引落による余力拘束　*/
    MF_BANK_DEBIT("22", "投信積立銀行引落による余力拘束"),
    /**　ジュニアNISA資金振替 */
    JUNIOR_NISA_TRANSFER_AMOUNT("23", "ｼﾞｭﾆｱNISA自動振替額"),
    /**　ジュニアNISA資金売却拘束 */
    JUNIOR_NISA_SALE_RESTRICTION("24", "ｼﾞｭﾆｱNISA日計り差金決済拘束額"),
    /**　Tポイント入金(ネオ) */
    TPOINT_DEPOSIT_NEO("25", "Tポイント利用分入金"),
    /**　Tポイント入金(SBI) */
    TPOINT_DEPOSIT_SBI("26", "Tポイント利用分入金"),;
    
    /** 摘要コード */
    private final String value;
    
    /** 日本語値　*/
    private final String jpName;
    
    /** 定数値の設定 */
    private ApplyType(final String value, final String jpName) {
        
        this.value = value;
        this.jpName = jpName;
    }
    
    /**
     * 定数値の取得
     *
     * @return 文字列値
     */
    public String getValue() {
        
        return this.value;
    }
    
    /**
     * 日本語値の取得
     *
     * @return 日本語値
     */
    public String getJpName() {
        
        return this.jpName;
    }
    
    public static ApplyType nameOfValue(String value) {
        
        ApplyType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getValue() == value)
                return enums[i];
        }
        
        return null;
    }
    
}
