package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
public class IfaMonthCustomerNumSql003RequestModel {
	 /** 対象年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 最大件数 */
    private int maxRowNum;

    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
}
