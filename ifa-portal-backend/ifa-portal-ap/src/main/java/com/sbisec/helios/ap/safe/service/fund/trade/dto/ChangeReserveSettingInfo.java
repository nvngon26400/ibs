package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 変更対象積立設定
 */
public class ChangeReserveSettingInfo {

    /** 投資信託協会コード（投資信託コード） */
    private String fundCode;

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

    /** 設定金額 */
    private BigDecimal settingAmount;

    /** コース区分 */
    private String courseType;

    /** 積立日付 */
    private String settingReserveDay;

    /** 積立隔月設定 */
    private String settingReserveBimonthly;

    /** 積立毎週設定 */
    private String settingReserveWeek;

    /** 積立複数日設定 */
    private String settingReserveMultiDay;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public BigDecimal getSettingAmount() {
        return settingAmount;
    }

    public void setSettingAmount(BigDecimal settingAmount) {
        this.settingAmount = settingAmount;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getSettingReserveDay() {
        return settingReserveDay;
    }

    public void setSettingReserveDay(String settingReserveDay) {
        this.settingReserveDay = settingReserveDay;
    }

    public String getSettingReserveBimonthly() {
        return settingReserveBimonthly;
    }

    public void setSettingReserveBimonthly(String settingReserveBimonthly) {
        this.settingReserveBimonthly = settingReserveBimonthly;
    }

    public String getSettingReserveWeek() {
        return settingReserveWeek;
    }

    public void setSettingReserveWeek(String settingReserveWeek) {
        this.settingReserveWeek = settingReserveWeek;
    }

    public String getSettingReserveMultiDay() {
        return settingReserveMultiDay;
    }

    public void setSettingReserveMultiDay(String settingReserveMultiDay) {
        this.settingReserveMultiDay = settingReserveMultiDay;
    }
}
