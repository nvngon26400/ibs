package com.sbisec.helios.ap.testtool.service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class IfaBbApplyCsvMassRegisterSql004Output {
    
    // 銘柄コード
    private String brandCode;
    
    // 銘柄名
    private String brandName;
    
    // 発行価格区分
    private String bbGestureValue;
    
    // 価格表示（開始）
    private BigDecimal bbPriceFrom;
    
    // 価格表示（終了）
    private BigDecimal bbPriceTo;
    
    // 価格表示（刻み）
    private BigDecimal bbPriceCut;
    
    // ディスカウント率（開始）
    private BigDecimal bbDiscountFrom;
    
    // ディスカウント率（終了）
    private BigDecimal bbDiscountTo;
    
    // ディスカウント率（刻み）
    private BigDecimal bbDiscountCut;
    
    // 成行（ストライクプライス）
    private String bbStrikePrice;
    
    // ブックビルディング申込期間（開始）
    private String bbPresentationFrom;
    
    // ブックビルディング申込期間（終了）
    private String bbPresentationTo;
    
    // 売買単位
    private String bbStock;
    
    // 上限単元数量
    private String maxAllocationUnits;
    
    // 入金予定日
    private String paymentDate;
    
    // 投資家属性順序
    private String bbSeq;
    
    // 投資家属性名
    private String bbInvestorAttName;
    
    // IPO／PO区分
    private String bbIpoPoKbn;
    
    // 売買単位区分
    private String bbUnitKbn;
    
    // 緊急入力停止
    private String bbUrgentStop;
    
    // IFAブックビルディング申込期間（開始）
    private String ifaBbPresentationFrom;
    
    // IFAブックビルディング申込期間（終了）
    private String ifaBbPresentationTo;
    
    // 電子交付のみフラグ
    private String edelivOnlyFlag;
}
