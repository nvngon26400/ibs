package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBBCustomerInfoModel extends ModelBase {
    // 部店コード
    private String uaiButenCode;
    // 口座番号
    private Integer uaiAccountNumber;
    // 投資方針区分
    private String uaiQaInvestmentPlan;
    // 投資方針
    private String uaiQaInvestmentPlanVal;
    // 本人職業区分
    private String uaiOccupation;
    // 本人職業
    private String uaiOccupationVal;
    // 投資経験年数（株式現物）
    private Integer uaiExpStock;
    // 投資経験年数（株式現物） + '年'
    private String uaiExpStockStr;
    // 勤務先名(漢字)
    private String uaiOfficeName;
    // 金融資産区分
    private String uaiQaFinancialAssets;
    // 金融資産
    private String uaiQaFinancialAssetsVal;
    // 法人区分
    private String uaiCorporationKbn;
    // 扱者コード
    private String dealerNumber;
    // 顧客名_姓(漢字)
    private String uaiFamilyNameKanji;
    // 顧客名_名(漢字)
    private String uaiNameKanji;
    // 顧客名_姓(カナ)
    private String uaiFamilyNameKana;
    // 顧客名_名(カナ)
    private String uaiNameKana;
    // 顧客名_姓名(漢字)
    private String nameKanji;
    // 口座開設年月日
    private String openAcctDate;
    // コンプラランク
    private String tcCompRank;
    // 本年の年間裁量配分割当回数
    private Integer sairyouAlloCount;
    // 本年の年間裁量配分割当回数 + '回'
    private String sairyouAlloCountStr;
    // 本年の年間抽選当選回数
    private Integer bbLotElecCount;
    // 本年の年間裁量配分割当上限数(画面に本年の年間裁量配分可能回数)
    private Integer maxSairyouAllo;
    // 本年の年間裁量配分割当上限数(画面に本年の年間裁量配分可能回数) + '回'
    private String maxSairyouAlloStr;
    // 本年の年間配分可能回数
    private Integer maybeSairyouCount;
    // 預り資産額
    private String currenctPriceTotal;
    // 買付余力
    private String buyingPowerTotal;
    // NISA買付可能額
    private String isaBuyLimit;
    // 電子交付承諾区分(1：承諾済 2 ：未承諾)
    private String edelivAgreementKbn;
    // 電子交付同意
    private String edelivAgreementDate;
    // 目論見書閲覧(YYYY/MM/DD HH24:MI)
    private String readTimeStr;

    public String getUaiButenCode() {
        return uaiButenCode;
    }

    public void setUaiButenCode(String uaiButenCode) {
        this.uaiButenCode = uaiButenCode;
    }

    public Integer getUaiAccountNumber() {
        return uaiAccountNumber;
    }

    public void setUaiAccountNumber(Integer uaiAccountNumber) {
        this.uaiAccountNumber = uaiAccountNumber;
    }

    public String getUaiQaInvestmentPlan() {
        return uaiQaInvestmentPlan;
    }

    public void setUaiQaInvestmentPlan(String uaiQaInvestmentPlan) {
        this.uaiQaInvestmentPlan = uaiQaInvestmentPlan;
    }

    public String getUaiQaInvestmentPlanVal() {
        return uaiQaInvestmentPlanVal;
    }

    public void setUaiQaInvestmentPlanVal(String uaiQaInvestmentPlanVal) {
        this.uaiQaInvestmentPlanVal = uaiQaInvestmentPlanVal;
    }

    public String getUaiOccupation() {
        return uaiOccupation;
    }

    public void setUaiOccupation(String uaiOccupation) {
        this.uaiOccupation = uaiOccupation;
    }

    public String getUaiOccupationVal() {
        return uaiOccupationVal;
    }

    public void setUaiOccupationVal(String uaiOccupationVal) {
        this.uaiOccupationVal = uaiOccupationVal;
    }

    public Integer getUaiExpStock() {
        return uaiExpStock;
    }

    public void setUaiExpStock(Integer uaiExpStock) {
        this.uaiExpStock = uaiExpStock;
    }

    public String getUaiExpStockStr() {
        return uaiExpStockStr;
    }

    public void setUaiExpStockStr(String uaiExpStockStr) {
        this.uaiExpStockStr = uaiExpStockStr;
    }

    public String getUaiOfficeName() {
        return uaiOfficeName;
    }

    public void setUaiOfficeName(String uaiOfficeName) {
        this.uaiOfficeName = uaiOfficeName;
    }

    public String getUaiQaFinancialAssets() {
        return uaiQaFinancialAssets;
    }

    public void setUaiQaFinancialAssets(String uaiQaFinancialAssets) {
        this.uaiQaFinancialAssets = uaiQaFinancialAssets;
    }

    public String getUaiQaFinancialAssetsVal() {
        return uaiQaFinancialAssetsVal;
    }

    public void setUaiQaFinancialAssetsVal(String uaiQaFinancialAssetsVal) {
        this.uaiQaFinancialAssetsVal = uaiQaFinancialAssetsVal;
    }

    public String getUaiCorporationKbn() {
        return uaiCorporationKbn;
    }

    public void setUaiCorporationKbn(String uaiCorporationKbn) {
        this.uaiCorporationKbn = uaiCorporationKbn;
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

    public String getNameKanji() {
        return nameKanji;
    }

    public void setNameKanji(String nameKanji) {
        this.nameKanji = nameKanji;
    }

    public String getOpenAcctDate() {
        return openAcctDate;
    }

    public void setOpenAcctDate(String openAcctDate) {
        this.openAcctDate = openAcctDate;
    }

    public String getTcCompRank() {
        return tcCompRank;
    }

    public void setTcCompRank(String tcCompRank) {
        this.tcCompRank = tcCompRank;
    }

    public Integer getSairyouAlloCount() {
        return sairyouAlloCount;
    }

    public void setSairyouAlloCount(Integer sairyouAlloCount) {
        this.sairyouAlloCount = sairyouAlloCount;
    }

    public String getSairyouAlloCountStr() {
        return sairyouAlloCountStr;
    }

    public void setSairyouAlloCountStr(String sairyouAlloCountStr) {
        this.sairyouAlloCountStr = sairyouAlloCountStr;
    }

    public Integer getBbLotElecCount() {
        return bbLotElecCount;
    }

    public void setBbLotElecCount(Integer bbLotElecCount) {
        this.bbLotElecCount = bbLotElecCount;
    }

    public Integer getMaxSairyouAllo() {
        return maxSairyouAllo;
    }

    public void setMaxSairyouAllo(Integer maxSairyouAllo) {
        this.maxSairyouAllo = maxSairyouAllo;
    }

    public String getMaxSairyouAlloStr() {
        return maxSairyouAlloStr;
    }

    public void setMaxSairyouAlloStr(String maxSairyouAlloStr) {
        this.maxSairyouAlloStr = maxSairyouAlloStr;
    }

    public Integer getMaybeSairyouCount() {
        return maybeSairyouCount;
    }

    public void setMaybeSairyouCount(Integer maybeSairyouCount) {
        this.maybeSairyouCount = maybeSairyouCount;
    }

    public String getCurrenctPriceTotal() {
        return currenctPriceTotal;
    }

    public void setCurrenctPriceTotal(String currenctPriceTotal) {
        this.currenctPriceTotal = currenctPriceTotal;
    }

    public String getBuyingPowerTotal() {
        return buyingPowerTotal;
    }

    public void setBuyingPowerTotal(String buyingPowerTotal) {
        this.buyingPowerTotal = buyingPowerTotal;
    }

    public String getIsaBuyLimit() {
        return isaBuyLimit;
    }

    public void setIsaBuyLimit(String isaBuyLimit) {
        this.isaBuyLimit = isaBuyLimit;
    }

    public String getEdelivAgreementKbn() {
        return edelivAgreementKbn;
    }

    public void setEdelivAgreementKbn(String edelivAgreementKbn) {
        this.edelivAgreementKbn = edelivAgreementKbn;
    }

    public String getEdelivAgreementDate() {
        return edelivAgreementDate;
    }

    public void setEdelivAgreementDate(String edelivAgreementDate) {
        this.edelivAgreementDate = edelivAgreementDate;
    }

    public String getReadTimeStr() {
        return readTimeStr;
    }

    public void setReadTimeStr(String readTimeStr) {
        this.readTimeStr = readTimeStr;
    }

}
