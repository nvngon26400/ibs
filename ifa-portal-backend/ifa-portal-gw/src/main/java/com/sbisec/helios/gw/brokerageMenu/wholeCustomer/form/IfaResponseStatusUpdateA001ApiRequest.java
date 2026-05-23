package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class IfaResponseStatusUpdateA001ApiRequest {

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
}
