package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

@Data
public class IfaUnsettleDetailA001DtoResponse {

	/** 顧客名（全角半角）. */
	private String customerName;

	/** 口座番号（数字）. */
	private String accountNumber;

	/** 分類表示（全角半角）. */
	private String classificationDisplay;

	/** 検索結果総数. */
	private String searchResultCount;

	/** 精算金額合計（数値(整数)）. */
	private String sumSettlementAmount;

    /** 未精算明細リスト. */
    private List<IfaUnsettleDetailA001ResponseDto_UnsettleDetail> unsettleDetail;
    
}
