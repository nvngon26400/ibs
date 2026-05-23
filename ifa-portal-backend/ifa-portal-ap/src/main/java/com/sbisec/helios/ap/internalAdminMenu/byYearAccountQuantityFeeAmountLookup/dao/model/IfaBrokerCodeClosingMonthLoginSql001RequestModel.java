package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

@Data
public class IfaBrokerCodeClosingMonthLoginSql001RequestModel {

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<BrokerCharge> brokerChargeList;
    
}
