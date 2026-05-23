package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IfaUnsettleDetailA001ApiRequest {
    
    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp = "0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;
    
    /** リクエストタイプ. */
    @NotEmpty(message = "リクエストタイプ")
	private String requestType;

	/** リクエスト日付区分. */
	@NotEmpty(message = "リクエスト日付区分")
	private String requestDateClassification;

	/** 受渡日. */
	@DateTimeFormat(pattern="yy/MM/dd")
	@JsonFormat(pattern="yy/MM/dd")
	@NotEmpty(message = "受渡日")
	@Size(min = 10, max = 10, message = "受渡日")
	private String settlementDate;
    
    /** 取得口座区分. */
    @NotEmpty(message="取得口座区分")
    private String accountType;
    
}
