package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dao.model;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 */
@Data
public class IfaForeignBondBuyAmountInputListSql001ResponseModel
{
    
    // IFAシーケンス
    private String ifaAccountSeqNo;
    
    // 部店コード
    private String butenCode;
    
    // 口座番号
    private String accountNumber;
    
    // 金額
    private BigDecimal amount;
    
    // 注文日
    private String baseDate;
    
    // 約定時間
    private String orderTime;
    
    // 作成日時
    private String createTime;
    
    // 更新日時
    private String updateTime;
    
    // 顧客名_姓名(漢字)
    private String nameKanji;
    
    /** 総件数 */
    private int totalCount;
}
