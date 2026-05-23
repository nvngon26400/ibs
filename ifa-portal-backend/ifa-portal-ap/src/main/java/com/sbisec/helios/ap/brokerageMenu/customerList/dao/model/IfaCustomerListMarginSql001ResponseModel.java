package com.sbisec.helios.ap.brokerageMenu.customerList.dao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;

@Data
public class IfaCustomerListMarginSql001ResponseModel extends ModelBase{

    /** 総件数 */
    private String totalRow;
    
	/** Cランク. */
	private String tcCompRank;

	/** 顧客名（カナ）（全角半角）. */
	private String customerNameKana;

	/** 顧客名（漢字）（全角半角）. */
	private String customerNameKanji;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 支店コード（数字）. */
	private String branchCode;

	/** 仲介業者コード（数字）. */
	private String brokerCode;

	/** 仲介業者営業員コード（半角英数字）. */
	private String brokerChargeCode;

	/** 部店コード（半角英数字）. */
	private String butenCode;

	/** 担当者名（全角半角）. */
	private String chargeName;

	/** 仲介業者支店名（支店名用）. */
	private String branchName;

	/** 仲介業者支店名（仲介業者名用）. */
	private String brokerName;

	/** 評価損益－信用建玉. */
	private String customerListProfitAndLossOfMarginPosition;

	/** 追証ステータス（全角半角）. */
	private String marginStatus;

	/** 不足金発生フラグ. */
	private String chargeFlag;

	/** 受入保証金. */
	private String acceptanceDeposit;

	/** 維持率. */
	private String maintenanceRate;

	/** 契約締結前交付書面コード名. */
	private String customerAttributeName;

	/** 店群. */
	private String tengun;

}
