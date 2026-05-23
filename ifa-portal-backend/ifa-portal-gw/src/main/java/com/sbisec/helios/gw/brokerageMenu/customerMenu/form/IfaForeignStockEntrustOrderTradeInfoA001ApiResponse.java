package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * 外国株式委託注文約定情報A001レスポンス
 *
 * @author SCSK 矢口
 */
@Data
public class IfaForeignStockEntrustOrderTradeInfoA001ApiResponse {
    
    /** 顧客名（漢字）（全角半角）. */
    private String customerNameKanji;
    
    /** 顧客名（カナ）（全角半角）. */
    private String customerNameKana;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 委託注文約定情報リスト. */
    private List<IfaForeignStockEntrustOrderTradeInfoA001ApiResponseEntrustOrderTradeinfo> entrustOrderTradeinfoList;
    
}
