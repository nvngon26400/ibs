package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;


import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
*
*
* @author BASE丁
*
*/
@Data
public class IfaDomesticMutualFundOrderOtherInfoSql001RequestModel {

	/** IFA注文番号（数字）. */
	private String ifaOrderNo;
	
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;
    
    /** 権限Id */
    private String privId;

}
