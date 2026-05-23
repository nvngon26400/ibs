package com.sbisec.helios.gw.brokerageMenu.commFee.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class IfaSbiWrapManageFeeA002ApiRequest {
    
    /** 登録日From. */
    @NotEmpty(message = "登録日From")
    private String registeredDateFrom;

    /** 登録日To. */
    @NotEmpty(message = "登録日To")
    private String registeredDateTo;

    /** 仲介業者コード（数字）. */
    @NotEmpty(message = "仲介業者コード")
    @Pattern(regexp="0-9", message = "仲介業者コード")
    @Size(max = 4, message = "仲介業者コード")
    private String brokerCode;

    /** 部店コード（半角英数字）. */
    @NotEmpty(message = "部店コード")
    @Size(min = 3, max = 3, message = "部店コード")
    private String butenCode;
    
    /** 口座番号（数字）. */
    @NotEmpty(message = "口座番号")
    @Pattern(regexp="0-9", message = "口座番号")
    @Size(max = 7, message = "口座番号")
    private String accountNumber;

    /** 仲介業者除外（半角英数字）. */
    @NotEmpty(message = "仲介業者除外")
    @Size(min = 1, max = 1, message = "仲介業者除外")
    private String chkBrokerCodeExclude;

}
