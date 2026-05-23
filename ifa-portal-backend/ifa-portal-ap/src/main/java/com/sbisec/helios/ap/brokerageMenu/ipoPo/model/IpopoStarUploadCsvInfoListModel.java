package com.sbisec.helios.ap.brokerageMenu.ipoPo.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class IpopoStarUploadCsvInfoListModel extends ModelBase {

    // 部店コード
    private String butenCode;
    // 口座番号
    private String accountNumber;
    // 銘柄コード
    private String productCode;
    // 注文株数
    private String orderCount;
    // 預り売買区分
    private String depositType;
    // 入金予定日
    private String paymentDate;
    // 勧誘区分
    private String invitationType;
    // 受注方法
    private String orderMethod;
    // 受注日
    private String orderDay;
    // 受注時刻
    private String orderHour;
    // 資金振替チェックアラート(信用口座)
    private String alertForMargin;
    // 資金振替チェックアラート(NISA)
    private String alertForNisa;
    // 資金振替チェックアラート(未成年口座)
    private String alertForMinor;
    // 資金振替チェックアラート(ハイブリッド口座)
    private String alertForSsnb;
    // ワーニング申請済フラグ
    private String warningApply;
    // セクション名
    private String bbCreateSectionName;

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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
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

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getInvitationType() {
        return invitationType;
    }

    public void setInvitationType(String invitationType) {
        this.invitationType = invitationType;
    }

    public String getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(String orderMethod) {
        this.orderMethod = orderMethod;
    }

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public String getOrderHour() {
        return orderHour;
    }

    public void setOrderHour(String orderHour) {
        this.orderHour = orderHour;
    }

    public String getWarningApply() {
        return warningApply;
    }

    public void setWarningApply(String warningApply) {
        this.warningApply = warningApply;
    }

    public String getAlertForMargin() {
        return alertForMargin;
    }

    public void setAlertForMargin(String alertForMargin) {
        this.alertForMargin = alertForMargin;
    }

    public String getAlertForNisa() {
        return alertForNisa;
    }

    public void setAlertForNisa(String alertForNisa) {
        this.alertForNisa = alertForNisa;
    }

    public String getAlertForMinor() {
        return alertForMinor;
    }

    public void setAlertForMinor(String alertForMinor) {
        this.alertForMinor = alertForMinor;
    }

    public String getAlertForSsnb() {
        return alertForSsnb;
    }

    public void setAlertForSsnb(String alertForSsnb) {
        this.alertForSsnb = alertForSsnb;
    }

    public String getBbCreateSectionName() {
        return bbCreateSectionName;
    }

    public void setBbCreateSectionName(String bbCreateSectionName) {
        this.bbCreateSectionName = bbCreateSectionName;
    }

}
