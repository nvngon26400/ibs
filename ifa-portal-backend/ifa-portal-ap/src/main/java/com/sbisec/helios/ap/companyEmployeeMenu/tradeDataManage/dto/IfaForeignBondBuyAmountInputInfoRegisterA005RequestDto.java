package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto;

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
public class IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto
{
    
    /** 部店 */
    private String butenCode;
    
    /** 口座番号 */
    private String accountNumber;
    
    private String amount;
}
