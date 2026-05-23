package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoDomesticStockDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignClaimDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignMmfDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignStockDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailA001ApiResponse {

	/** 部店. */
	private String butenCode;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 顧客名（漢字）（全角半角）. */
	private String customerNameKanji;

	/** 顧客名（カナ）（全角半角）. */
	private String customerNameKana;

	/** 銘柄コード（半角英数字）. */
	private String brandCode;

	/** 銘柄名（全角半角）. */
	private String brandName;

	/** 商品口座預り（全角半角）. */
	private String commodityAccountDeposit;

    /** 国内株式預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoDomesticStockDepositList> domesticStockDepositList;

    /** 投資信託口数預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList> mutualFundLotDepositList;

    /** 投資信託金額預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList> mutualFundAmountDepositList;

    /** 国内債券預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList> domesticClaimDepositList;

    /** 外国債券預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoForeignClaimDepositList> foreignClaimDepositList;

    /** 外国債券（外貨建）預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList> foreignClaimForeignCurrencyDepositList;

    /** 外国株式預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoForeignStockDepositList> foreignStockDepositList;

    /** 外貨建MMF預りリスト. */
    private List<IfaDepositBalanceDetailResponseDtoForeignMmfDepositList> foreignMmfDepositList;
}
