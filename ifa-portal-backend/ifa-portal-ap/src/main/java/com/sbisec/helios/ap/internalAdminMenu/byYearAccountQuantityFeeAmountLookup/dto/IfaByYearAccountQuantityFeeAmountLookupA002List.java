package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/26
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupA002List {
    
    /** 決算確定区分. */
    private String closingKbn;
    
    /** 決算年月. */
    private String closingYearMonth;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 前年度口座数. */
    private String previousNumberOfAccounts;
    
    /** 口座数 */
    private String numberOfAccounts;
    
    /** 口座数増減. */
    private String numberOfAccountsIncreaseDecrease;
    
    /** 媒介口座数 */
    private String numberOfActiveAccounts;
    
    /** 媒介手数料 */
    private String mediateCom;
    
    /** その他受入手数料 */
    private String otherCom;
    
    /** ダウンロード可否区分 */
    private String downloadFlg;
    
    /** 計 */
    private String total;
    
    /** 媒介手数料（千円） */
    private String mediateComThousand;
    
    /** その他受入手数料（千円） */
    private String otherComThousand;
    
    /** 計（千円） */
    private String totalThousand;
    
    /** 媒介可否区分 */
    private String mediateProprietyKBN;

}
