package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import lombok.Data;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;

@Data
public class IfaOtherFeeDetailSql001RequestModel {

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 対象年月. */
	private String targetDateYm;

    /** 権限ID. */
    private String privId;
    
    /** FCT030.仲介業者営業員リスト. */
    private List<OutputFct030Dto.BrokerCharge> brokerChargeList;
    
    /** 検索上限件数 */
    private String searchLimitRow;
}
