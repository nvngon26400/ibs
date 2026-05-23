package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import java.util.List;

import lombok.Data;

@Data
public class IfaSbiWrapManageFeeSql001RequestModel {

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 登録日From. */
	private String registeredDateFrom;

	/** 登録日To. */
	private String registeredDateTo;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者除外（半角英数字）. */
	private String chkBrokerCodeExclude;

	/** 権限. */
	private String authority;

	/** 最大件数. */
	private int querySizeLimit;
	
    /** 仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;

    /** FCT030.仲介業者営業員リスト.仲介業者コード. */
    private String fct030BrokerCode;

    /** FCT030.仲介業者営業員リスト.営業員コード. */
    private String fct030ChargeCode;

    /**
     * 仲介業者営業員
     */
    @Data
    public static class BrokerCharge {
        
        //仲介業者コード
        private String brokerCode;
        
        //営業員コード
        private String empCode;
    }
}
