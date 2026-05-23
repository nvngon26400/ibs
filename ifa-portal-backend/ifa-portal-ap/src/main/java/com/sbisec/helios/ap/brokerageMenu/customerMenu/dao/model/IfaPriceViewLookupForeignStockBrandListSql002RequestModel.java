package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Data
public class IfaPriceViewLookupForeignStockBrandListSql002RequestModel {
    
    /** 仲介業者コード=顧客共通情報.仲介業者コード. */
    private String brokerCode;
    
    /** ティッカー選択フラグ  ティッカー：0 名称：1 */
    private String tickerSelectFlag;
    
    /** 銘柄コード=UPPER(画面.ティッカーキー). */
    private String tickerKey;
    
    /** 銘柄名=LIKE '画面.名称キー' ESCAPE '!'. */
    private String brandNameKey;
    
}
