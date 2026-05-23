package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 董
 * @date   2025/08/13
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupSql005RequestModel {

    /** 仲介業者コードリスト. */
    private List<String> brokerCodeList;
    
    /** 仲介業者除外. */
    private String chkBrokerCodeExclude;
    
    /** 権限コード（全角半角）. */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
}
