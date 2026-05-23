package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 積立設定機能ベースOUT Dto
 */
public class FundReserveSettingBaseOut {

    /** 投資信託協会コード */
    private String fundCode;

    /** 投資信託協会名（投資信託名） */
    private String fundName;

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

    /** 設定金額 */
    private BigDecimal settingAmount;

    /** 1ヵ月あたりの設定金額（概算） */
    private BigDecimal oneMonthSumAmount;

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

    /** NISA枠ぎりぎり注文 */
    private String nisaBarelyBuyingType;

    /** 課税枠シフト注文 */
    private String taxShiftType;

    /** ボーナス設定有無 */
    private boolean settingBonusFlag;

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

    /** 次の買付予定日 */
    private String planDate;

    /** ボーナス月次の買付予定日１ */
    private String bonusPlanDate1;

    /** ボーナス月次の買付予定日２ */
    private String bonusPlanDate2;

    /** 設定金額概算手数料 */
    private BigDecimal estimateFundOrder;

    /** ボーナス概算手数料 */
    private BigDecimal estimateFundOrderBonus;

    /** 設定金額の次回買付日 */
    private String nextReserveDate;

    /**
     * 預り区分を取得する。
     * @return 預り区分
     */
    public String getAccountType() {
        return accountType;
    }

    /**
     * 預り区分を設定する。
     * @param accountType 預り区分
     */
    public void setAccountType(final String accountType) {
        this.accountType = accountType;
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

    public BigDecimal getOneMonthSumAmount() {
        return oneMonthSumAmount;
    }

    public void setOneMonthSumAmount(BigDecimal oneMonthSumAmount) {
        this.oneMonthSumAmount = oneMonthSumAmount;
    }

    /**
     * コース区分を取得する。
     * @return コース区分
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * コース区分を設定する。
     * @param courseType コース区分
     */
    public void setCourseType(final String courseType) {
        this.courseType = courseType;
    }

    /**
     * 積立日付を取得する。
     * @return 積立日付
     */
    public String getSettingReserveDay() {
        return settingReserveDay;
    }

    /**
     * 積立日付を設定する。
     * @param settingReserveDay 積立日付
     */
    public void setSettingReserveDay(final String settingReserveDay) {
        this.settingReserveDay = settingReserveDay;
    }

    /**
     * 積立隔月設定を取得する。
     * @return 積立隔月設定
     */
    public String getSettingReserveBimonthly() {
        return settingReserveBimonthly;
    }

    /**
     * 積立隔月設定を設定する。
     * @param settingReserveBimonthly 積立隔月設定
     */
    public void setSettingReserveBimonthly(final String settingReserveBimonthly) {
        this.settingReserveBimonthly = settingReserveBimonthly;
    }

    /**
     * 積立毎週設定を取得する。
     * @return 積立毎週設定
     */
    public String getSettingReserveWeek() {
        return settingReserveWeek;
    }

    /**
     * 積立毎週設定を設定する。
     * @param settingReserveWeek 積立毎週設定
     */
    public void setSettingReserveWeek(final String settingReserveWeek) {
        this.settingReserveWeek = settingReserveWeek;
    }

    /**
     * 積立複数日設定を取得する。
     * @return 積立複数日設定
     */
    public String getSettingReserveMultiDay() {
        return settingReserveMultiDay;
    }

    /**
     * 積立複数日設定を設定する。
     * @param settingReserveMultiDay 積立複数日設定
     */
    public void setSettingReserveMultiDay(final String settingReserveMultiDay) {
        this.settingReserveMultiDay = settingReserveMultiDay;
    }

    /**
     * NISA枠ぎりぎり注文を取得する。
     * @return NISA枠ぎりぎり注文
     */
    public String getNisaBarelyBuyingType() {
        return nisaBarelyBuyingType;
    }

    /**
     * NISA枠ぎりぎり注文を設定する。
     * @param nisaBarelyBuyingType NISA枠ぎりぎり注文
     */
    public void setNisaBarelyBuyingType(final String nisaBarelyBuyingType) {
        this.nisaBarelyBuyingType = nisaBarelyBuyingType;
    }

    /**
     * 課税枠シフト注文を取得する。
     * @return 課税枠シフト注文
     */
    public String getTaxShiftType() {
        return taxShiftType;
    }

    /**
     * 課税枠シフト注文を設定する。
     * @param taxShiftType 課税枠シフト注文
     */
    public void setTaxShiftType(final String taxShiftType) {
        this.taxShiftType = taxShiftType;
    }

    /**
     * ボーナス設定有無を取得する。
     * @return ボーナス設定有無
     */
    public boolean isSettingBonusFlag() {
        return settingBonusFlag;
    }

    /**
     * ボーナス設定有無を設定する。
     * @param settingBonusFlag ボーナス設定有無
     */
    public void setSettingBonusFlag(final boolean settingBonusFlag) {
        this.settingBonusFlag = settingBonusFlag;
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
     * @param settingBonusAmount ボーナス設定金額
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
     * 設定金額概算手数料を取得する。
     * @return 設定金額概算手数料
     */
    public BigDecimal getEstimateFundOrder() {
        return estimateFundOrder;
    }

    /**
     * 設定金額概算手数料を設定する。
     * @param estimateFundOrder 設定金額概算手数料
     */
    public void setEstimateFundOrder(final BigDecimal estimateFundOrder) {
        this.estimateFundOrder = estimateFundOrder;
    }

    /**
     * ボーナス概算手数料を取得する。
     * @return ボーナス概算手数料
     */
    public BigDecimal getEstimateFundOrderBonus() {
        return estimateFundOrderBonus;
    }

    /**
     * ボーナス概算手数料を設定する。
     * @param estimateFundOrderBonus ボーナス概算手数料
     */
    public void setEstimateFundOrderBonus(final BigDecimal estimateFundOrderBonus) {
        this.estimateFundOrderBonus = estimateFundOrderBonus;
    }

    /**
     * 次の買付予定日を取得する。
     * @return 次の買付予定日
     */
    public String getPlanDate() {
        return planDate;
    }

    /**
     * 次の買付予定日を設定する。
     * @param planDate 次の買付予定日
     */
    public void setPlanDate(final String planDate) {
        this.planDate = planDate;
    }

    /**
     * ボーナス月次の買付予定日１を取得する。
     * @return ボーナス月次の買付予定日１
     */
    public String getBonusPlanDate1() {
        return bonusPlanDate1;
    }

    /**
     * ボーナス月次の買付予定日１を設定する。
     * @param bonusPlanDate1 ボーナス月次の買付予定日１
     */
    public void setBonusPlanDate1(final String bonusPlanDate1) {
        this.bonusPlanDate1 = bonusPlanDate1;
    }

    /**
     * ボーナス月次の買付予定日２を取得する。
     * @return ボーナス月次の買付予定日２
     */
    public String getBonusPlanDate2() {
        return bonusPlanDate2;
    }

    /**
     * ボーナス月次の買付予定日２を設定する。
     * @param bonusPlanDate2 ボーナス月次の買付予定日２
     */
    public void setBonusPlanDate2(final String bonusPlanDate2) {
        this.bonusPlanDate2 = bonusPlanDate2;
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
     * 投資信託協会コード（投資信託コード）を取得する。
     * @return 投資信託協会コード（投資信託コード）
     */
    public String getFundCode() {
        return fundCode;
    }

    /**
     * 投資信託協会コード（投資信託コード）を設定する。
     * @param fundCode 投資信託協会コード（投資信託コード）
     */
    public void setFundCode(final String fundCode) {
        this.fundCode = fundCode;
    }

    /**
     * 投資信託協会名（投資信託名）を設定する。
     * @param fundName 投資信託協会名（投資信託名）
     */
    public void setFundName(final String fundName) {
        this.fundName = fundName;
    }

    /**
     * 投資信託協会名（投資信託名）を取得する。
     * @return 投資信託協会名（投資信託名）
     */
    public String getFundName() {
        return fundName;
    }

    public String getNextReserveDate() {
        return nextReserveDate;
    }

    public void setNextReserveDate(String nextReserveDate) {
        this.nextReserveDate = nextReserveDate;
    }
}
