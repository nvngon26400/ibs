package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 投信積立買付設定情報
 */
public class ReserveSettingData {

    /** 協会コード */
    private String fundCode;

    /** ファンド名 */
    private String fundName;

    /** 決済方法 */
    private String paymentMethod;

    /** 非特定預り区分 */
    private String accountType;

    /** コース */
    private String courseType;

    /** 設定金額 */
    private BigDecimal settingAmount;

    /** 積立奇偶月設定区分 */
    private String settingReserveBimonthly;

    /** 申込設定日(毎週用) */
    private String settingReserveWeek;

    /** 申込設定日(毎月、隔月用) */
    private String settingReserveDay;

    /** 申込設定日(複数日用) */
    private String settingReserveMultiDay;

    /** nisa枠ぎりぎり買付区分 */
    private String nisaBarelyBuyingType;

    /** nisa枠超過時買付区分 */
    private String taxShiftType;

    /** ボーナス設定金額 */
    private BigDecimal settingBonusAmount;

    /** ボーナス１設定月 */
    private String settingBonus1Month;

    /** ボーナス１設定日 */
    private String settingBonus1Day;

    /** ボーナス２設定月 */
    private String settingBonus2Month;

    /** ボーナス２設定日 */
    private String settingBonus2Day;

    /** 買付予定日 */
    private String planDate;

    /** ボーナス１買付予定日 */
    private String bonusPlanDate1;

    /** ボーナス２買付予定日  */
    private String bonusPlanDate2;

    /** 1ヶ月あたりの設定金額（概算） */
    private BigDecimal oneMonthSumAmount;

    /** 1年あたりの設定金額（概算） */
    private BigDecimal oneYearSumAmount;

    /** ボーナス次回買付日 */
    private String bonusNextReserveDate;

    /** 次回買付日 */
    private String nextReserveDate;

    /** 銘柄詳細リンク表示可否 */
    private boolean showLink;

    /**
     * 協会コードを取得する。
     * @return 協会コード
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 協会コードを設定する。
     * @param fundCode 協会コード
     */
    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * ファンド名を取得する。
     * @return ファンド名
     */
    public String getFundName() {
        return fundName;
    }

    /**
     * ファンド名を設定する。
     * @param fundName ファンド名
     */
    public void setFundName(final String fundName) {
        this.fundName = fundName;
    }

    /**
     * 決済方法を取得する。
     * @return 決済方法
     */
    public String getPaymentMethod() {
        return paymentMethod;
    }

    /**
     * 決済方法を設定する。
     * @param paymentMethod 決済方法
     */
    public void setPaymentMethod(final String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 非特定預り区分を取得する。
     * @return 非特定預り区分
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 非特定預り区分を設定する。
     * @param accountType 非特定預り区分
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
    }

    /**
     * コースを取得する。
     * @return コース
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * コースを設定する。
     * @param courseType コース
     */
    public void setCourseType(final String courseType) {
        this.courseType = courseType;
    }

    /**
     * 1ヶ月あたりの設定金額（概算）を取得する。
     * @return 1ヶ月あたりの設定金額（概算）
     */
    public BigDecimal getOneMonthSumAmount() {
        return oneMonthSumAmount;
    }

    /**
     * 1ヶ月あたりの設定金額（概算）を設定する。
     * @param oneMonthSumAmount 1ヶ月あたりの設定金額（概算）
     */
    public void setOneMonthSumAmount(final BigDecimal oneMonthSumAmount) {
        this.oneMonthSumAmount = oneMonthSumAmount;
    }

    /**
     * 1年あたりの設定金額（概算）を取得する。
     * @return 1年あたりの設定金額（概算）
     */
    public BigDecimal getOneYearSumAmount() {
        return oneYearSumAmount;
    }

    /**
     * 1年あたりの設定金額（概算）を設定する。
     * @param oneYearSumAmount 1年あたりの設定金額（概算）
     */
    public void setOneYearSumAmount(final BigDecimal oneYearSumAmount) {
        this.oneYearSumAmount = oneYearSumAmount;
    }

    /**
     * 設定金額を取得する。
     * @return 設定金額
     */
    public BigDecimal getSettingAmount() {
        return settingAmount;
    }

    /**
     * 設定金額を設定する。
     * @param settingAmount 設定金額
     */
    public void setSettingAmount(final BigDecimal settingAmount) {
        this.settingAmount = settingAmount;
    }

    /**
     * 積立奇偶月設定区分を取得する。
     * @return 積立奇偶月設定区分
     */
    public String getSettingReserveBimonthly() {
        return settingReserveBimonthly;
    }

    /**
     * 積立奇偶月設定区分を設定する。
     * @param settingReserveBimonthly 積立奇偶月設定区分
     */
    public void setSettingReserveBimonthly(final String settingReserveBimonthly) {
        this.settingReserveBimonthly = settingReserveBimonthly;
    }

    /**
     * 申込設定日(毎週用)を取得する。
     * @return 申込設定日(毎週用)
     */
    public String getSettingReserveWeek() {
        return settingReserveWeek;
    }

    /**
     * 申込設定日(毎週用)を設定する。
     * @param settingReserveWeek 申込設定日(毎週用)
     */
    public void setSettingReserveWeek(final String settingReserveWeek) {
        this.settingReserveWeek = settingReserveWeek;
    }

    /**
     * 申込設定日(毎月、隔月用)を取得する。
     * @return 申込設定日(毎月、隔月用)
     */
    public String getSettingReserveDay() {
        return settingReserveDay;
    }

    /**
     * 申込設定日(毎月、隔月用)を設定する。
     * @param settingReserveDay 申込設定日(毎月、隔月用)
     */
    public void setSettingReserveDay(final String settingReserveDay) {
        this.settingReserveDay = settingReserveDay;
    }

    /**
     * 申込設定日(複数日用)を取得する。
     * @return 申込設定日(複数日用)
     */
    public String getSettingReserveMultiDay() {
        return settingReserveMultiDay;
    }

    /**
     * 申込設定日(複数日用)を設定する。
     * @param settingReserveMultiDay 申込設定日(複数日用)
     */
    public void setSettingReserveMultiDay(final String settingReserveMultiDay) {
        this.settingReserveMultiDay = settingReserveMultiDay;
    }

    /**
     * ボーナス設定金額を取得する。
     * @return ボーナス設定金額
     */
    public BigDecimal getSettingBonusAmount() {
        return settingBonusAmount;
    }

    /**
     * ボーナス設定金額を設定する。
     * @param bonusPayment ボーナス設定金額
     */
    public void setSettingBonusAmount(final BigDecimal settingBonusAmount) {
        this.settingBonusAmount = settingBonusAmount;
    }

    /**
     * ボーナス１設定月を取得する。
     * @return ボーナス１設定月
     */
    public String getSettingBonus1Month() {
        return settingBonus1Month;
    }

    /**
     * ボーナス１設定月を設定する。
     * @param settingBonus1Month ボーナス１設定月
     */
    public void setSettingBonus1Month(final String settingBonus1Month) {
        this.settingBonus1Month = settingBonus1Month;
    }

    /**
     * ボーナス１設定日を取得する。
     * @return ボーナス１設定日
     */
    public String getSettingBonus1Day() {
        return settingBonus1Day;
    }

    /**
     * ボーナス１設定日を設定する。
     * @param settingBonus1Day ボーナス１設定日
     */
    public void setSettingBonus1Day(final String settingBonus1Day) {
        this.settingBonus1Day = settingBonus1Day;
    }

    /**
     * ボーナス２設定月を取得する。
     * @return ボーナス２設定月
     */
    public String getSettingBonus2Month() {
        return settingBonus2Month;
    }

    /**
     * ボーナス２設定月を設定する。
     * @param settingBonus2Month ボーナス２設定月
     */
    public void setSettingBonus2Month(final String settingBonus2Month) {
        this.settingBonus2Month = settingBonus2Month;
    }

    /**
     * ボーナス２設定日を取得する。
     * @return ボーナス２設定日
     */
    public String getSettingBonus2Day() {
        return settingBonus2Day;
    }

    /**
     * ボーナス２設定日を設定する。
     * @param settingBonus2Day ボーナス２設定日
     */
    public void setSettingBonus2Day(final String settingBonus2Day) {
        this.settingBonus2Day = settingBonus2Day;
    }

    /**
     * nisa枠ぎりぎり買付区分を取得する。
     * @return nisa枠ぎりぎり買付区分
     */
    public String getNisaBarelyBuyingType() {
        return nisaBarelyBuyingType;
    }

    /**
     * nisa枠ぎりぎり買付区分を設定する。
     * @param nisaBarelyBuyingType nisa枠ぎりぎり買付区分
     */
    public void setNisaBarelyBuyingType(final String nisaBarelyBuyingType) {
        this.nisaBarelyBuyingType = nisaBarelyBuyingType;
    }

    /**
     * nisa枠超過時買付区分を取得する。
     * @return nisa枠超過時買付区分
     */
    public String getTaxShiftType() {
        return taxShiftType;
    }

    /**
     * nisa枠超過時買付区分を設定する。
     * @param taxShiftType nisa枠超過時買付区分
     */
    public void setTaxShiftType(final String taxShiftType) {
        this.taxShiftType = taxShiftType;
    }

    /**
     * 銘柄詳細リンク表示可否を取得する。
     * @return 銘柄詳細リンク表示可否
     */
    public boolean isShowLink() {
        return showLink;
    }

    /**
     * 銘柄詳細リンク表示可否を設定する。
     * @param showLink 銘柄詳細リンク表示可否
     */
    public void setShowLink(final boolean showLink) {
        this.showLink = showLink;
    }

    /**
     * 買付予定日を取得する。
     * @return 買付予定日
     */
    public String getPlanDate() {
        return planDate;
    }

    /**
     * 買付予定日を設定する。
     * @param planDate 買付予定日
     */
    public void setPlanDate(final String planDate) {
        this.planDate = planDate;
    }

    /**
     * ボーナス１買付予定日を取得する。
     * @return ボーナス１買付予定日
     */
    public String getBonusPlanDate1() {
        return bonusPlanDate1;
    }

    /**
     * ボーナス１買付予定日を設定する。
     * @param bonusPlanDate1 ボーナス１買付予定日
     */
    public void setBonusPlanDate1(final String bonusPlanDate1) {
        this.bonusPlanDate1 = bonusPlanDate1;
    }

    /**
     * ボーナス２買付予定日を取得する。
     * @return ボーナス２買付予定日
     */
    public String getBonusPlanDate2() {
        return bonusPlanDate2;
    }

    /**
     * ボーナス２買付予定日を設定する。
     * @param planDateBonus2 ボーナス２買付予定日
     */
    public void setBonusPlanDate2(final String bonusPlanDate2) {
        this.bonusPlanDate2 = bonusPlanDate2;
    }

    /**
     * 次回買付日を取得する。
     * @return 次回買付日
     */
    public String getNextReserveDate() {
        return nextReserveDate;
    }

    /**
     * 次回買付日を設定する。
     * @param nextReserveDate 次回買付日
     */
    public void setNextReserveDate(final String nextReserveDate) {
        this.nextReserveDate = nextReserveDate;
    }

    /**
     * ボーナス次回買付日を取得する。
     * @return ボーナス次回買付日
     */
    public String getBonusNextReserveDate() {
        return bonusNextReserveDate;
    }

    /**
     * ボーナス次回買付日を設定する。
     * @param bonusNextReserveDate ボーナス次回買付日
     */
    public void setBonusNextReserveDate(String bonusNextReserveDate) {
        this.bonusNextReserveDate = bonusNextReserveDate;
    }

}
