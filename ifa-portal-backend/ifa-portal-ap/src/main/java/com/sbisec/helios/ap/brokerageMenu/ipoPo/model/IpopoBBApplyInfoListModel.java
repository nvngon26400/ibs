package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import java.util.Date;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoBBApplyInfoListModel extends ModelBase {

    private int totalRow;
    // 銘柄コード
    private String bbProductCode;
    // 銘柄名
    private String bbProductName;
    // 仲介業者コード
    private String brokerCode;
    // 仲介業者名
    private String brokerName;
    // 仲介業支店コード
    private String branchCode;
    // 仲介業者支店名
    private String branchName;
    // 仲介業者営業員コード
    private String intermediaryEmpCd;
    // 仲介業者担当者名
    private String brokerChargeName;
    // 部店コード
    private String butenCode;
    // 口座番号
    private String accountNumber;
    // 顧客名_姓名(漢字)
    private String nameKanji;
    // 顧客名_姓名(カナ)
    private String nameKana;
    // 投資家属性名
    private String bbInvestorAttName;
    // 数量
    private String bbQuantity;
    // 裁量配分希望数量
    private String quantitySairyou;
    // 申込価格
    private String bbPrice;
    // 抽選結果
    private String lotteryResult;
    // 当選株数
    private String bbQuantityAlloc;
    // 注文状況
    private String orderStatus;
    // 注文株数
    private String orderCount;
    // 預り区分
    private String depositType;
    // 勧誘区分
    private String invitationType;
    // ワーニング申請済
    private String warningApply;
    // 明細番号
    private String detailNumber;
    // セクション名
    private String bbCreateSectionName;
    // 備考
    private String bbRemark;
    // 発行価格区分
    private String bbGestureValue;
    // 更新日
    private Date bbUpdateDate;
    // ブックビルディング申込期間（開始）
    private String bbPresentationFrom;
    // 作成日
    private String bbCreateDate;
    // 申込者
    private String bbCreateUserName;
    // システム日付(yyyymmddhh24miss)
    private String sysDate;
    // UPLOAD用KEY
    private String uploadKey;
    //電子交付同意
    private String edelivAgreementDate; 
    //目論見書閲覧
    private String readTime;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

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

    public String getBrokerCode() {
        return brokerCode;
    }

    public void setBrokerCode(String brokerCode) {
        this.brokerCode = brokerCode;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIntermediaryEmpCd() {
        return intermediaryEmpCd;
    }

    public void setIntermediaryEmpCd(String intermediaryEmpCd) {
        this.intermediaryEmpCd = intermediaryEmpCd;
    }

    public String getBrokerChargeName() {
        return brokerChargeName;
    }

    public void setBrokerChargeName(String brokerChargeName) {
        this.brokerChargeName = brokerChargeName;
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

    public String getNameKanji() {
        return nameKanji;
    }

    public void setNameKanji(String nameKanji) {
        this.nameKanji = nameKanji;
    }

    public String getNameKana() {
        return nameKana;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }
    
    public String getBbInvestorAttName() {
        return bbInvestorAttName;
    }

    public void setBbInvestorAttName(String bbInvestorAttName) {
        this.bbInvestorAttName = bbInvestorAttName;
    }

    public String getBbQuantity() {
        return bbQuantity;
    }

    public void setBbQuantity(String bbQuantity) {
        this.bbQuantity = bbQuantity;
    }

    public String getQuantitySairyou() {
        return quantitySairyou;
    }

    public void setQuantitySairyou(String quantitySairyou) {
        this.quantitySairyou = quantitySairyou;
    }

    public String getBbPrice() {
        return bbPrice;
    }

    public void setBbPrice(String bbPrice) {
        this.bbPrice = bbPrice;
    }

    public String getLotteryResult() {
        return lotteryResult;
    }

    public void setLotteryResult(String lotteryResult) {
        this.lotteryResult = lotteryResult;
    }

    public String getBbQuantityAlloc() {
        return bbQuantityAlloc;
    }

    public void setBbQuantityAlloc(String bbQuantityAlloc) {
        this.bbQuantityAlloc = bbQuantityAlloc;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(String orderCount) {
        this.orderCount = orderCount;
    }

    public String getDepositType() {
        return depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(String invitationType) {
        this.invitationType = invitationType;
    }

    public String getWarningApply() {
        return warningApply;
    }

    public void setWarningApply(String warningApply) {
        this.warningApply = warningApply;
    }

    public String getDetailNumber() {
        return detailNumber;
    }

    public void setDetailNumber(String detailNumber) {
        this.detailNumber = detailNumber;
    }

    public String getBbCreateSectionName() {
        return bbCreateSectionName;
    }

    public void setBbCreateSectionName(String bbCreateSectionName) {
        this.bbCreateSectionName = bbCreateSectionName;
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

    public Date getBbUpdateDate() {
        return bbUpdateDate;
    }

    public void setBbUpdateDate(Date bbUpdateDate) {
        this.bbUpdateDate = bbUpdateDate;
    }

    public String getBbPresentationFrom() {
        return bbPresentationFrom;
    }

    public void setBbPresentationFrom(String bbPresentationFrom) {
        this.bbPresentationFrom = bbPresentationFrom;
    }

    public String getBbCreateDate() {
        return bbCreateDate;
    }

    public void setBbCreateDate(String bbCreateDate) {
        this.bbCreateDate = bbCreateDate;
    }

    public String getBbCreateUserName() {
        return bbCreateUserName;
    }

    public void setBbCreateUserName(String bbCreateUserName) {
        this.bbCreateUserName = bbCreateUserName;
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getUploadKey() {
        return uploadKey;
    }

    public void setUploadKey(String uploadKey) {
        this.uploadKey = uploadKey;
    }

    public String getEdelivAgreementDate() {
        return edelivAgreementDate;
    }

    public void setEdelivAgreementDate(String edelivAgreementDate) {
        this.edelivAgreementDate = edelivAgreementDate;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

}
