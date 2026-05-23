package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import java.math.BigDecimal;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBrandInfoListModel extends ModelBase {
    
    private int totalRow;
    
    // IPO／PO区分
    private String bbIpoPoKbn;
    
    // 銘柄コード
    private String bbProductCode;
    
    // 1付き
    private String attachedBrand;
    
    // 銘柄名
    private String bbProductName;
    
    // 配分上限株数
    private BigDecimal maxAllocation;
    
    // 売買単位
    private String bbStock;
    
    // ブックビルディング申込期間（開始）
    private String bbPresentationFrom;
    
    // BB申込期間
    private String bbPresentation;
    
    // ステータス
    private String status;
    
    // 募集期間
    private String bbPeriod;
    
    // 入金予定日
    private String paymentDate;
    
    // 売買単位区分
    private String bbUnitKbn;
    
    // 開示有無
    private String cigaretteShowFlag;
    
    // 電子交付のみ
    private String edelivOnlyFlag;
    
    // 期間変更フラグ
    private String changeBbPeriodFlag;
    
    // 価格変更フラグ
    private String changePriceFlag;
    
    public int getTotalRow() {
        
        return totalRow;
    }
    
    public void setTotalRow(int totalRow) {
        
        this.totalRow = totalRow;
    }
    
    public String getBbIpoPoKbn() {
        
        return bbIpoPoKbn;
    }
    
    public void setBbIpoPoKbn(String bbIpoPoKbn) {
        
        this.bbIpoPoKbn = bbIpoPoKbn;
    }
    
    public String getBbProductCode() {
        
        return bbProductCode;
    }
    
    public void setBbProductCode(String bbProductCode) {
        
        this.bbProductCode = bbProductCode;
    }
    
    public String getAttachedBrand() {
        
        return attachedBrand;
    }
    
    public void setAttachedBrand(String attachedBrand) {
        
        this.attachedBrand = attachedBrand;
    }
    
    public String getBbProductName() {
        
        return bbProductName;
    }
    
    public void setBbProductName(String bbProductName) {
        
        this.bbProductName = bbProductName;
    }
    
    public BigDecimal getMaxAllocation() {
        
        return maxAllocation;
    }
    
    public void setMaxAllocation(BigDecimal maxAllocation) {
        
        this.maxAllocation = maxAllocation;
    }
    
    public String getBbStock() {
        
        return bbStock;
    }
    
    public void setBbStock(String bbStock) {
        
        this.bbStock = bbStock;
    }
    
    public String getBbPresentationFrom() {
        
        return bbPresentationFrom;
    }
    
    public void setBbPresentationFrom(String bbPresentationFrom) {
        
        this.bbPresentationFrom = bbPresentationFrom;
    }
    
    public String getBbPresentation() {
        
        return bbPresentation;
    }
    
    public void setBbPresentation(String bbPresentation) {
        
        this.bbPresentation = bbPresentation;
    }
    
    public String getStatus() {
        
        return status;
    }
    
    public void setStatus(String status) {
        
        this.status = status;
    }
    
    public String getBbPeriod() {
        
        return bbPeriod;
    }
    
    public void setBbPeriod(String bbPeriod) {
        
        this.bbPeriod = bbPeriod;
    }
    
    public String getPaymentDate() {
        
        return paymentDate;
    }
    
    public void setPaymentDate(String paymentDate) {
        
        this.paymentDate = paymentDate;
    }
    
    public String getBbUnitKbn() {
        
        return bbUnitKbn;
    }
    
    public void setBbUnitKbn(String bbUnitKbn) {
        
        this.bbUnitKbn = bbUnitKbn;
    }
    
    public String getCigaretteShowFlag() {
        
        return cigaretteShowFlag;
    }
    
    public void setCigaretteShowFlag(String cigaretteShowFlag) {
        
        this.cigaretteShowFlag = cigaretteShowFlag;
    }
    
    public String getEdelivOnlyFlag() {
        
        return edelivOnlyFlag;
    }
    
    public void setEdelivOnlyFlag(String edelivOnlyFlag) {
        
        this.edelivOnlyFlag = edelivOnlyFlag;
    }
   
    public String getChangeBbPeriodFlag() {
        
        return changeBbPeriodFlag;
    }
    
    public void setChangeBbPeriodFlag(String changeBbPeriodFlag) {
        
        this.changeBbPeriodFlag = changeBbPeriodFlag;
    }
    
    public String getChangePriceFlag() {
        
        return changePriceFlag;
    }
    
    public void setChangePriceFlag(String changePriceFlag) {
        
        this.changePriceFlag = changePriceFlag;
    }
    
}
