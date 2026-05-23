package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IfaResponseStatusUpdateA006ApiRequest {
    
    /** ステータス区分. */
    @NotEmpty(message = "ステータス区分")
    private String statusClassification;
    
    /** 下落率区分（数字）. */
    @NotEmpty(message = "下落率区分")
    @Pattern(regexp="0-9", message = "下落率区分")
    @Size(min = 1, max = 1, message = "下落率区分")
    private String declineRateKbn;

	/** 部店コード（半角英数字）. */
	@NotEmpty(message = "部店コード")
	@Size(min = 3, max = 3, message = "部店コード")
	private String butenCode;

	/** 口座番号（数字）. */
	@NotEmpty(message = "口座番号")
	@Pattern(regexp="0-9", message = "口座番号")
	@Size(max = 7, message = "口座番号")
	private String accountNumber;

	/** 投信協会コード（半角英数字）. */
	@NotEmpty(message = "投信協会コード")
	@Size(max = 8, message = "投信協会コード")
	private String investmentTrustAssociationCode;

	/** 基準日（To）. */
	@NotEmpty(message = "基準日（To）")
	private String standardDateTo;

	/** 対応方法区分. */
	@NotEmpty(message = "対応方法区分")
	private String responseMethodClassification;

	/** その他内容区分. */
	@NotEmpty(message = "その他内容区分")
	private String otherContentsClassification;

	/** その他詳細. */
	@NotEmpty(message = "その他詳細")
	private String otherDetail;

	/** 顧客対応日. */
	@DateTimeFormat(pattern="yy/MM/dd")
	@JsonFormat(pattern="yy/MM/dd")
	@NotEmpty(message = "顧客対応日")
	@Size(min = 10, max = 10, message = "顧客対応日")
	private String customerResponseDate;

	/** 対応完了確認日. */
	@DateTimeFormat(pattern="yy/MM/dd")
	@JsonFormat(pattern="yy/MM/dd")
	@NotEmpty(message = "対応完了確認日")
	@Size(min = 10, max = 10, message = "対応完了確認日")
	private String responseFinishConfirmDate;

}
