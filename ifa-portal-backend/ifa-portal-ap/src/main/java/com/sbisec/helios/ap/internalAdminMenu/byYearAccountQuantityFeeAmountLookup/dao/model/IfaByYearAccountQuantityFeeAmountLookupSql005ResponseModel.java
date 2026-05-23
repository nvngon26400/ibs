package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0406-01 
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 董
 * @date 2025/08/13
 */
@Data
public class IfaByYearAccountQuantityFeeAmountLookupSql005ResponseModel {
    /** 仲介業者コード. */
    private String brokerCode;

    /** 適用開始日. */
    private String effectiveDate;

    /** 媒介可否区分. */
    private String mediateProprietyKBN;

}
