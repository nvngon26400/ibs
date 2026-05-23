package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 積立設定入力受付機能IN Dto
 */
public class FundReserveSettingInputReceptApiIn extends FundTradeDtoIn {

    /**
     * コンストラクタ
     */
    public FundReserveSettingInputReceptApiIn() {
        super("fund.reserve.setting.input.recept");
    }

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

    /** 投資信託協会コード（投資信託コード） */
    private String fundCode;

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

    /** 受付経路区分 */
    private String routeType;

    /** オペレーターID */
    private String operatorId;

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
     * 受付経路区分を取得する。
     * @return 受付経路区分
     */
    public String getRouteType() {
        return routeType;
    }

    /**
     * 受付経路区分を設定する。
     * @param routeType 受付経路区分
     */
    public void setRouteType(final String routeType) {
        this.routeType = routeType;
    }

    /**
     * オペレーターIDを取得する。
     * @return オペレーターID
     */
    public String getOperatorId() {
        return operatorId;
    }

    /**
     * オペレーターIDを設定する。
     * @param operatorId オペレーターID
     */
    public void setOperatorId(final String operatorId) {
        this.operatorId = operatorId;
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

}
