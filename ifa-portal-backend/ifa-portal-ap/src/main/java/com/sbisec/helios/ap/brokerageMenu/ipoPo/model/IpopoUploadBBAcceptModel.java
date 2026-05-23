package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoUploadBBAcceptModel extends ModelBase {
    // 銘柄コード
    private String brandCode;
    // ブックビルディング申込期間（開始）
    private String bbPresentationFrom;
    // 部店コード
    private String butenCode;
    // 口座番号
    private String accountNumber;
    // 扱者番号
    private String dealerNumber;
    // 顧客名_姓(漢字)
    private String uaiFamilyNameKanji;
    // 顧客名_名(漢字)
    private String uaiNameKanji;
    // 顧客名_姓(カナ)
    private String uaiFamilyNameKana;
    // 顧客名_名(カナ)
    private String uaiNameKana;
    // 数量
    private String bbQuantity;
    // 価格
    private String bbPrice;
    // ディスカウント率
    private String bbDiscount;
    // 投資家属性順序
    private String bbSeq;
    // 投資家属性名
    private String bbInvestorAttName;
    // 備考
    private String bbRemark;
    // 作成ユーザID
    private String userId;
    // 作成ユーザ名
    private String userName;
    // 作成セクションID
    private String sectionId;
    // 作成支店名
    private String sectionName;
    // 成行（ストライクプライス）
    private String bbStrikePrice;
    // ディマンド数量
    private String bbQuantityForSairyou;
    // 裁量希望株数
    private String quantitySairyou;
    // 裁量選定理由
    private String choseReason;

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBbPresentationFrom() {
        return bbPresentationFrom;
    }

    public void setBbPresentationFrom(String bbPresentationFrom) {
        this.bbPresentationFrom = bbPresentationFrom;
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

    public String getBbDiscount() {
        return bbDiscount;
    }

    public void setBbDiscount(String bbDiscount) {
        this.bbDiscount = bbDiscount;
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

    public String getBbRemark() {
        return bbRemark;
    }

    public void setBbRemark(String bbRemark) {
        this.bbRemark = bbRemark;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getBbStrikePrice() {
        return bbStrikePrice;
    }

    public void setBbStrikePrice(String bbStrikePrice) {
        this.bbStrikePrice = bbStrikePrice;
    }

    public String getBbQuantityForSairyou() {
        return bbQuantityForSairyou;
    }

    public void setBbQuantityForSairyou(String bbQuantityForSairyou) {
        this.bbQuantityForSairyou = bbQuantityForSairyou;
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

}
