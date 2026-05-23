package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoUploadBBCustomerInfoModel extends ModelBase {
    // 銘柄コード
    private String brandCode;
    // 銘柄名
    private String brandName;
    // 部店
    private String butenCode;
    // 口座番号
    private String accountNumber;
    // 扱者番号
    private String dealerNumber;
    // 顧客名
    private String customerName;
    // 希望株数
    private String bbQuantity;
    // 申込価格
    private String bbPrice;
    // 投資家属性順序
    private String bbSeq;
    // 投資家属性名
    private String bbInvestorAttName;
    // 裁量希望株数
    private String quantitySairyou;
    // 裁量選定理由
    private String choseReason;
    // 備考
    private String bbRemark;
    // チェック結果
    private String checkResult;
    // 発行価格区分
    private String bbGestureValue;
    // ブックビルディング申込期間（開始）
    private Date bbPresentationFrom;
    // メッセージを設定する
    private IpopoBBApplyUploadCheckResultModel msgModel;
    // 顧客名_姓(漢字)
    private String uaiFamilyNameKanji;
    // 顧客名_名(漢字)
    private String uaiNameKanji;
    // 顧客名_姓(カナ)
    private String uaiFamilyNameKana;
    // 顧客名_名(カナ)
    private String uaiNameKana;
    // 裁量選定理由表示用
    private String choseReasonForShow;
    // 備考表示用
    private String bbRemarkForShow;
    // セクションID
    private String sectionId;
    // 支店名
    private String sectionName;
    // 勧誘区分
    private String kanyuKbn;
    // 受注方法
    private String receiveOrderType;

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

    public String getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(String dealerNumber) {
        this.dealerNumber = dealerNumber;
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

    public String getBbInvestorAttName() {
        return bbInvestorAttName;
    }

    public void setBbInvestorAttName(String bbInvestorAttName) {
        this.bbInvestorAttName = bbInvestorAttName;
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

    public String getBbGestureValue() {
        return bbGestureValue;
    }

    public void setBbGestureValue(String bbGestureValue) {
        this.bbGestureValue = bbGestureValue;
    }

    public Date getBbPresentationFrom() {
        return bbPresentationFrom;
    }

    public void setBbPresentationFrom(Date bbPresentationFrom) {
        this.bbPresentationFrom = bbPresentationFrom;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public IpopoBBApplyUploadCheckResultModel getMsgModel() {
        return msgModel;
    }

    public void setMsgModel(IpopoBBApplyUploadCheckResultModel msgModel) {
        this.msgModel = msgModel;
    }

    public String getUaiFamilyNameKanji() {
        return uaiFamilyNameKanji;
    }

    public void setUaiFamilyNameKanji(String uaiFamilyNameKanji) {
        this.uaiFamilyNameKanji = uaiFamilyNameKanji;
    }

    public String getUaiNameKanji() {
        return uaiNameKanji;
    }

    public void setUaiNameKanji(String uaiNameKanji) {
        this.uaiNameKanji = uaiNameKanji;
    }

    public String getUaiFamilyNameKana() {
        return uaiFamilyNameKana;
    }

    public void setUaiFamilyNameKana(String uaiFamilyNameKana) {
        this.uaiFamilyNameKana = uaiFamilyNameKana;
    }

    public String getUaiNameKana() {
        return uaiNameKana;
    }

    public void setUaiNameKana(String uaiNameKana) {
        this.uaiNameKana = uaiNameKana;
    }

    public String getChoseReasonForShow() {
        return choseReasonForShow;
    }

    public void setChoseReasonForShow(String choseReasonForShow) {
        this.choseReasonForShow = choseReasonForShow;
    }

    public String getBbRemarkForShow() {
        return bbRemarkForShow;
    }

    public void setBbRemarkForShow(String bbRemarkForShow) {
        this.bbRemarkForShow = bbRemarkForShow;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getKanyuKbn() {
        return kanyuKbn;
    }

    public void setKanyuKbn(String kanyuKbn) {
        this.kanyuKbn = kanyuKbn;
    }

    public String getReceiveOrderType() {
        return receiveOrderType;
    }

    public void setReceiveOrderType(String receiveOrderType) {
        this.receiveOrderType = receiveOrderType;
    }
}
