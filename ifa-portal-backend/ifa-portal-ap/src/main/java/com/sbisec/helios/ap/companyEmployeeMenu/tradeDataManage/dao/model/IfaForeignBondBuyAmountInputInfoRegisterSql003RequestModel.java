package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model;

import java.math.BigDecimal;
import java.util.Date;

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
public class IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel
{
    
    // IFAシーケンス
    private String ifaAccountSeqNo;
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
    // 金額
    private BigDecimal amount;
    
    // 作成者
    private String createUser;
    
    // 更新者
    private String updateUser;
    
    // 顧客名_姓名(漢字)
    private String nameKanji;
}
