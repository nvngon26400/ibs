package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBBApplyUploadCheckResultModel extends ModelBase {
    // 銘柄コード
    private String brandCode;
    // 銘柄コード(ステータス)
    private String brandCodeStatus;
    // 部店
    private String butenCode;
    // 部店(ステータス)
    private String butenCodeStatus;
    // 口座番号
    private String accountNumber;
    // 口座番号(ステータス)
    private String accountNumberStatus;
    // 顧客名
    private String customerName;
    // 顧客名(ステータス)
    private String customerNameStatus;
    // 希望株数
    private String bbQuantity;
    // 希望株数(ステータス)
    private String bbQuantityStatus;
    // 申込価格
    private String bbPrice;
    // 申込価格(ステータス)
    private String bbPriceStatus;
    // 投資家属性
    private String bbSeq;
    // 投資家属性(ステータス)
    private String bbSeqStatus;
    // 裁量希望株数
    private String quantitySairyou;
    // 裁量希望株数(ステータス)
    private String quantitySairyouStatus;
    // 裁量選定理由
    private String choseReason;
    // 裁量選定理由(ステータス)
    private String choseReasonStatus;
    // 備考
    private String bbRemark;
    // 備考(ステータス)
    private String bbRemarkStatus;
    // 勧誘区分
    private String kanyuKbn;
    // 勧誘区分(ステータス)
    private String kanyuKbnStatus;
    // 受注方法
    private String receiveOrderType;
    // 受注方法(ステータス)
    private String receiveOrderTypeStatus;
    // 存在と権限チェック用
    private String existMsg;
    // 存在と権限チェック用(ステータス)
    private String existMsgStatus;
    // 裁量配分の業務チェック用
    private String sairyou;
    // 裁量配分の業務チェック用(ステータス)
    private String sairyouStatus;
    // 登録金融資産3000万未満チェック用
    private String financialAssets;
    // 登録金融資産3000万未満チェック用(ステータス)
    private String financialAssetsStatus;
    // テンプレートチェック用
    private String template;
    // テンプレートチェック用(ステータス)
    private String templateStatus;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getButenCode() {
        return butenCode;
    }

    public void setButenCode(String butenCode) {
        this.butenCode = butenCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBbQuantity() {
        return bbQuantity;
    }

    public void setBbQuantity(String bbQuantity) {
        this.bbQuantity = bbQuantity;
    }

    public String getBbPrice() {
        return bbPrice;
    }

    public void setBbPrice(String bbPrice) {
        this.bbPrice = bbPrice;
    }

    public String getBbSeq() {
        return bbSeq;
    }

    public void setBbSeq(String bbSeq) {
        this.bbSeq = bbSeq;
    }

    public String getQuantitySairyou() {
        return quantitySairyou;
    }

    public void setQuantitySairyou(String quantitySairyou) {
        this.quantitySairyou = quantitySairyou;
    }

    public String getChoseReason() {
        return choseReason;
    }

    public void setChoseReason(String choseReason) {
        this.choseReason = choseReason;
    }

    public String getBbRemark() {
        return bbRemark;
    }

    public void setBbRemark(String bbRemark) {
        this.bbRemark = bbRemark;
    }

    public String getSairyou() {
        return sairyou;
    }

    public void setSairyou(String sairyou) {
        this.sairyou = sairyou;
    }

    public String getExistMsg() {
        return existMsg;
    }

    public void setExistMsg(String existMsg) {
        this.existMsg = existMsg;
    }

    public String getFinancialAssets() {
        return financialAssets;
    }

    public void setFinancialAssets(String financialAssets) {
        this.financialAssets = financialAssets;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getBrandCodeStatus() {
        
        return brandCodeStatus;
    }

    public void setBrandCodeStatus(String brandCodeStatus) {
        
        this.brandCodeStatus = brandCodeStatus;
    }

    public String getButenCodeStatus() {
        
        return butenCodeStatus;
    }

    public void setButenCodeStatus(String butenCodeStatus) {
        
        this.butenCodeStatus = butenCodeStatus;
    }

    public String getAccountNumberStatus() {
        
        return accountNumberStatus;
    }

    public void setAccountNumberStatus(String accountNumberStatus) {
        
        this.accountNumberStatus = accountNumberStatus;
    }

    public String getCustomerNameStatus() {
        
        return customerNameStatus;
    }

    public void setCustomerNameStatus(String customerNameStatus) {
        
        this.customerNameStatus = customerNameStatus;
    }

    public String getBbQuantityStatus() {
        
        return bbQuantityStatus;
    }

    public void setBbQuantityStatus(String bbQuantityStatus) {
        
        this.bbQuantityStatus = bbQuantityStatus;
    }

    public String getBbPriceStatus() {
        
        return bbPriceStatus;
    }

    public void setBbPriceStatus(String bbPriceStatus) {
        
        this.bbPriceStatus = bbPriceStatus;
    }

    public String getBbSeqStatus() {
        
        return bbSeqStatus;
    }

    public void setBbSeqStatus(String bbSeqStatus) {
        
        this.bbSeqStatus = bbSeqStatus;
    }

    public String getQuantitySairyouStatus() {
        
        return quantitySairyouStatus;
    }

    public void setQuantitySairyouStatus(String quantitySairyouStatus) {
        
        this.quantitySairyouStatus = quantitySairyouStatus;
    }

    public String getChoseReasonStatus() {
        
        return choseReasonStatus;
    }

    public void setChoseReasonStatus(String choseReasonStatus) {
        
        this.choseReasonStatus = choseReasonStatus;
    }

    public String getBbRemarkStatus() {
        
        return bbRemarkStatus;
    }

    public void setBbRemarkStatus(String bbRemarkStatus) {
        
        this.bbRemarkStatus = bbRemarkStatus;
    }

    public String getTemplateStatus() {
        
        return templateStatus;
    }

    public void setTemplateStatus(String templateStatus) {
        
        this.templateStatus = templateStatus;
    }

    public String getFinancialAssetsStatus() {
        
        return financialAssetsStatus;
    }

    public void setFinancialAssetsStatus(String financialAssetsStatus) {
        
        this.financialAssetsStatus = financialAssetsStatus;
    }

    public String getExistMsgStatus() {
        
        return existMsgStatus;
    }

    public void setExistMsgStatus(String existMsgStatus) {
        
        this.existMsgStatus = existMsgStatus;
    }

    public String getSairyouStatus() {
        
        return sairyouStatus;
    }

    public void setSairyouStatus(String sairyouStatus) {
        
        this.sairyouStatus = sairyouStatus;
    }

    public String getKanyuKbn() {
        
        return kanyuKbn;
    }

    public void setKanyuKbn(String kanyuKbn) {
        
        this.kanyuKbn = kanyuKbn;
    }

    public String getKanyuKbnStatus() {
        
        return kanyuKbnStatus;
    }

    public void setKanyuKbnStatus(String kanyuKbnStatus) {
        
        this.kanyuKbnStatus = kanyuKbnStatus;
    }

    public String getReceiveOrderType() {
        
        return receiveOrderType;
    }

    public void setReceiveOrderType(String receiveOrderType) {
        
        this.receiveOrderType = receiveOrderType;
    }

    public String getReceiveOrderTypeStatus() {
        
        return receiveOrderTypeStatus;
    }

    public void setReceiveOrderTypeStatus(String receiveOrderTypeStatus) {
        
        this.receiveOrderTypeStatus = receiveOrderTypeStatus;
    }

}
