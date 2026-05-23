package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/05
 */

@Data
@EqualsAndHashCode(callSuper = false)
public class IfaByYearAccountQuantityFeeAmountLookupA004Items extends ModelBase {
    
    private static final long serialVersionUID = 1L;

    /** 決算確定区分. */
    private String closingKbn;
    
    /** 決算年月. */
    private String closingYearMonth;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 前期末口座数. */
    private String previousNumberOfAccounts;
    
    /** 口座数 */
    private String numberOfAccounts;
    
    /** 口座数増減. */
    private String numberOfAccountsIncreaseDecrease;
    
    /** うち期中に媒介を行った口座数 */
    private String numberOfActiveAccounts;
    
    /** 媒介手数料（円） */
    private String mediateCom;
    
    /** その他受入手数料（円） */
    private String otherCom;
    
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
