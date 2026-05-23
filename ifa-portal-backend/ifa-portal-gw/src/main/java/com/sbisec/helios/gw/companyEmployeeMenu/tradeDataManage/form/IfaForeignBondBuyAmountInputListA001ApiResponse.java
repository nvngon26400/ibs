package com.sbisec.helios.gw.companyEmployeeMenu.tradeDataManage.form;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount;

import lombok.Data;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Data
public class IfaForeignBondBuyAmountInputListA001ApiResponse
{
    /** 
     * 外債買付代金入力一覧
     */
    private List<IfaForeignBondBuyAmountInputListA001ResponseDtoForeignBondBuyAmount> foreignBondBuyAmountInputList;
    
}
