package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class BBProductMasterModel extends ModelBase {
    
    // 銘柄コード
    private String bbProductCode;
    
    // 銘柄名
    private String bbProductName;
    
    // 銘柄名(銘柄コード)
    private String bbProduct;
    
    // 売買単位
    private BigDecimal bbStock;
    
    // 売買単位(文字列)
    private String bbStockStr;
    
    // 受渡期日
    private String bbSettleDate;
    
    // 抽選日時
    private Date bbLotDate;
    
    // 抽選日時(文字列)
    private String bbLotDateStr;
    
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
    private Date bbPresentationFrom;
    
    // ブックビルディング申込期間（終了）
    private Date bbPresentationTo;
    
    // ブックビルディング申込期間
    private String bbPresentation;
    
    // ブックビルディング申込期間（開始）(文字列)
    private String bbPresentationFromStr;
    
    // ブックビルディング申込期間（終了）(文字列)
    private String bbPresentationToStr;
    
    // 緊急入力停止
    private String bbUrgentStop;
    
    // 売買単位区分
    private String bbUnitKbn;
    
    // IPO／PO区分
    private String bbIpoPoKbn;
    
    // 仮条件
    private String assumeCondition;
    
    // 裁量希望数量上限値(売買単位*上限単元数量)
    private String maxQuantitySairyou;
    
    // 電子交付のみフラグ
    private String edelivOnlyFlag;
    
    public String getBbProductCode() {
        
        return bbProductCode;
    }
    
    public void setBbProductCode(String bbProductCode) {
        
        this.bbProductCode = bbProductCode;
    }
    
    public String getBbProductName() {
        
        return bbProductName;
    }
    
    public void setBbProductName(String bbProductName) {
        
        this.bbProductName = bbProductName;
    }
    
    public String getBbProduct() {
        
        return bbProduct;
    }
    
    public void setBbProduct(String bbProduct) {
        
        this.bbProduct = bbProduct;
    }
    
    public BigDecimal getBbStock() {
        
        return bbStock;
    }
    
    public void setBbStock(BigDecimal bbStock) {
        
        this.bbStock = bbStock;
    }
    
    public String getBbStockStr() {
        
        return bbStockStr;
    }
    
    public void setBbStockStr(String bbStockStr) {
        
        this.bbStockStr = bbStockStr;
    }
    
    public String getBbSettleDate() {
        
        return bbSettleDate;
    }
    
    public void setBbSettleDate(String bbSettleDate) {
        
        this.bbSettleDate = bbSettleDate;
    }
    
    public Date getBbLotDate() {
        
        return bbLotDate;
    }
    
    public void setBbLotDate(Date bbLotDate) {
        
        this.bbLotDate = bbLotDate;
    }
    
    public String getBbLotDateStr() {
        
        return bbLotDateStr;
    }
    
    public void setBbLotDateStr(String bbLotDateStr) {
        
        this.bbLotDateStr = bbLotDateStr;
    }
    
    public String getBbGestureValue() {
        
        return bbGestureValue;
    }
    
    public void setBbGestureValue(String bbGestureValue) {
        
        this.bbGestureValue = bbGestureValue;
    }
    
    public BigDecimal getBbPriceFrom() {
        
        return bbPriceFrom;
    }
    
    public void setBbPriceFrom(BigDecimal bbPriceFrom) {
        
        this.bbPriceFrom = bbPriceFrom;
    }
    
    public BigDecimal getBbPriceTo() {
        
        return bbPriceTo;
    }
    
    public void setBbPriceTo(BigDecimal bbPriceTo) {
        
        this.bbPriceTo = bbPriceTo;
    }
    
    public BigDecimal getBbPriceCut() {
        
        return bbPriceCut;
    }
    
    public void setBbPriceCut(BigDecimal bbPriceCut) {
        
        this.bbPriceCut = bbPriceCut;
    }
    
    public BigDecimal getBbDiscountFrom() {
        
        return bbDiscountFrom;
    }
    
    public void setBbDiscountFrom(BigDecimal bbDiscountFrom) {
        
        this.bbDiscountFrom = bbDiscountFrom;
    }
    
    public BigDecimal getBbDiscountTo() {
        
        return bbDiscountTo;
    }
    
    public void setBbDiscountTo(BigDecimal bbDiscountTo) {
        
        this.bbDiscountTo = bbDiscountTo;
    }
    
    public BigDecimal getBbDiscountCut() {
        
        return bbDiscountCut;
    }
    
    public void setBbDiscountCut(BigDecimal bbDiscountCut) {
        
        this.bbDiscountCut = bbDiscountCut;
    }
    
    public String getBbStrikePrice() {
        
        return bbStrikePrice;
    }
    
    public void setBbStrikePrice(String bbStrikePrice) {
        
        this.bbStrikePrice = bbStrikePrice;
    }
    
    public Date getBbPresentationFrom() {
        
        return bbPresentationFrom;
    }
    
    public void setBbPresentationFrom(Date bbPresentationFrom) {
        
        this.bbPresentationFrom = bbPresentationFrom;
    }
    
    public Date getBbPresentationTo() {
        
        return bbPresentationTo;
    }
    
    public void setBbPresentationTo(Date bbPresentationTo) {
        
        this.bbPresentationTo = bbPresentationTo;
    }
    
    public String getBbPresentation() {
        
        return bbPresentation;
    }
    
    public void setBbPresentation(String bbPresentation) {
        
        this.bbPresentation = bbPresentation;
    }
    
    public String getBbPresentationFromStr() {
        
        return bbPresentationFromStr;
    }
    
    public void setBbPresentationFromStr(String bbPresentationFromStr) {
        
        this.bbPresentationFromStr = bbPresentationFromStr;
    }
    
    public String getBbPresentationToStr() {
        
        return bbPresentationToStr;
    }
    
    public void setBbPresentationToStr(String bbPresentationToStr) {
        
        this.bbPresentationToStr = bbPresentationToStr;
    }
    
    public String getBbUrgentStop() {
        
        return bbUrgentStop;
    }
    
    public void setBbUrgentStop(String bbUrgentStop) {
        
        this.bbUrgentStop = bbUrgentStop;
    }
    
    public String getBbUnitKbn() {
        
        return bbUnitKbn;
    }
    
    public void setBbUnitKbn(String bbUnitKbn) {
        
        this.bbUnitKbn = bbUnitKbn;
    }
    
    public String getBbIpoPoKbn() {
        
        return bbIpoPoKbn;
    }
    
    public void setBbIpoPoKbn(String bbIpoPoKbn) {
        
        this.bbIpoPoKbn = bbIpoPoKbn;
    }
    
    public String getAssumeCondition() {
        
        return assumeCondition;
    }
    
    public void setAssumeCondition(String assumeCondition) {
        
        this.assumeCondition = assumeCondition;
    }
    
    public String getMaxQuantitySairyou() {
        
        return maxQuantitySairyou;
    }
    
    public void setMaxQuantitySairyou(String maxQuantitySairyou) {
        
        this.maxQuantitySairyou = maxQuantitySairyou;
    }
    
    public String getEdelivOnlyFlag() {
        
        return edelivOnlyFlag;
    }
    
    public void setEdelivOnlyFlag(String edelivOnlyFlag) {
        
        this.edelivOnlyFlag = edelivOnlyFlag;
    }
}
