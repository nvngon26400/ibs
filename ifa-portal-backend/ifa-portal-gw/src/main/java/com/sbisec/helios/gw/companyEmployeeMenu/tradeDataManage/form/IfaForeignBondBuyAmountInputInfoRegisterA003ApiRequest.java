package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 画面ID：SUB0504_04-02_1 
 * 画面名：外債買付代金入力情報登録 
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Data
@JsonSerialize
public class IfaForeignBondBuyAmountInputInfoRegisterA003ApiRequest
{
    
    /** 部店 */
    @NotEmpty(message = "NotEmpty")
    @Size(max = 3, message = "Size")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "Pattern")
    private String butenCode;
    
    /** 口座番号 */
    @Size(max = 7, message = "Size")
    @Pattern(regexp = "[0-9]*", message = "Pattern")
    @NotEmpty(message = "NotEmpty")
    private String accountNumber;
}
