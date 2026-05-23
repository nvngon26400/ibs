package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import java.math.BigDecimal;
import java.util.Date;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBBApplyUploadModel extends ModelBase {
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
    private Date bbPresentationFrom;
    // ブックビルディング申込期間（終了）
    private Date bbPresentationTo;
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
    private Date ifaBbPresentationFrom;
    // IFAブックビルディング申込期間（終了）
    private Date ifaBbPresentationTo;
    // 電子交付のみフラグ
    private String edelivOnlyFlag;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

    public String getBbStock() {
        return bbStock;
    }

    public void setBbStock(String bbStock) {
        this.bbStock = bbStock;
    }

    public String getMaxAllocationUnits() {
        return maxAllocationUnits;
    }

    public void setMaxAllocationUnits(String maxAllocationUnits) {
        this.maxAllocationUnits = maxAllocationUnits;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getBbSeq() {
        return bbSeq;
    }

    public void setBbSeq(String bbSeq) {
        this.bbSeq = bbSeq;
    }

    public String getBbInvestorAttName() {
        return bbInvestorAttName;
    }

    public void setBbInvestorAttName(String bbInvestorAttName) {
        this.bbInvestorAttName = bbInvestorAttName;
    }

    public String getBbIpoPoKbn() {
        return bbIpoPoKbn;
    }

    public void setBbIpoPoKbn(String bbIpoPoKbn) {
        this.bbIpoPoKbn = bbIpoPoKbn;
    }

    public String getBbUnitKbn() {
        return bbUnitKbn;
    }

    public void setBbUnitKbn(String bbUnitKbn) {
        this.bbUnitKbn = bbUnitKbn;
    }

    public String getBbUrgentStop() {
        return bbUrgentStop;
    }

    public void setBbUrgentStop(String bbUrgentStop) {
        this.bbUrgentStop = bbUrgentStop;
    }

    public Date getIfaBbPresentationFrom() {
        return ifaBbPresentationFrom;
    }

    public void setIfaBbPresentationFrom(Date ifaBbPresentationFrom) {
        this.ifaBbPresentationFrom = ifaBbPresentationFrom;
    }

    public Date getIfaBbPresentationTo() {
        return ifaBbPresentationTo;
    }

    public void setIfaBbPresentationTo(Date ifaBbPresentationTo) {
        this.ifaBbPresentationTo = ifaBbPresentationTo;
    }

    public String getEdelivOnlyFlag() {
        return edelivOnlyFlag;
    }

    public void setEdelivOnlyFlag(String edelivOnlyFlag) {
        this.edelivOnlyFlag = edelivOnlyFlag;
    }
}
