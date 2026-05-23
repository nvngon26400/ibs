package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * ApiOutクラス 投資信託 積立設定サマリを取得する
 *
 */
public class ReserveSettingSummaryApiOut extends FundTradeDtoOut {

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** ジュニアNISA口座開設有無 */
    private boolean openedJnisa;

    /** NISA口座開設有無 */
    private boolean openedNisa3;

    /** 積立設定中件数（クレカと現金） */
    private int settingCount;

    /** 設定中 特定／一般 有無 */
    private boolean normalSetting;

    /** 設定中 ジュニア特定／一般 有無 */
    private boolean jnisaNormalSetting;

    /** 設定中 NISA（成長投資枠） 有無 */
    private boolean nisaGrowthSetting;

    /** 設定中 NISA（つみたて投資枠） 有無 */
    private boolean nisaReserveSetting;

    /** 1カ月あたりの積立金額（概算）*/
    private FundReserveSumAmount oneMonthSumAmount;

    /** 1年あたりの積立金額（概算）*/
    private FundReserveSumAmount oneYearSumAmount;

    /** ボーナス月設定の合計金額 */
    private BigDecimal bonusSumAmount;

    /** 特定／一般ボーナス月設定の合計金額 */
    private BigDecimal bonusSumAmountNormal;

    /** NISA（成長投資枠）ボーナス月設定の合計金額 */
    private BigDecimal bonusSumAmountNisaGrowth;

    /** NISA（つみたて投資枠）ボーナス月設定の合計金額 */
    private BigDecimal bonusSumAmountNisaReserve;

    /** 次回発注予定 */
    private NextOrderPlan nextOrderPlan;

    /** 1ヵ月クレカの積立設定上限金額 */
    private BigDecimal oneMonthLimitCreditCardAmount;

    /** 1年あたりのNISA（つみたて投資枠）設定金額上限 */
    private BigDecimal oneYearLimitNisaReserveAmount;

    /**
     * クレジットカード会社を取得する。
     * @return クレジットカード会社
     */
    public String getCreditCardCompany() {
        return creditCardCompany;
    }

    /**
     * クレジットカード会社を設定する。
     * @param creditCardCompany クレジットカード会社
     */
    public void setCreditCardCompany(String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    /**
     * 積立設定中件数（クレカと現金）を取得する。
     * @return 積立設定中件数（クレカと現金）
     */
    public int getSettingCount() {
        return settingCount;
    }

    /**
     * 積立設定中件数（クレカと現金）を設定する。
     * @param settingCount 積立設定中件数（クレカと現金）
     */
    public void setSettingCount(int settingCount) {
        this.settingCount = settingCount;
    }

    /**
     * ジュニアNISA口座開設有無を取得する。
     * @return ジュニアNISA口座開設有無
     */
    public boolean isOpenedJnisa() {
        return openedJnisa;
    }

    /**
     * ジュニアNISA口座開設有無を設定する。
     * @param openedJnisa ジュニアNISA口座開設有無
     */
    public void setOpenedJnisa(boolean openedJnisa) {
        this.openedJnisa = openedJnisa;
    }

    /**
     * NISA口座開設有無を取得する。
     * @return NISA口座開設有無
     */
    public boolean isOpenedNisa3() {
        return openedNisa3;
    }

    /**
     * NISA口座開設有無を設定する。
     * @param openedNisa3 NISA口座開設有無
     */
    public void setOpenedNisa3(boolean openedNisa3) {
        this.openedNisa3 = openedNisa3;
    }

    /**
     * 1カ月あたりの積立金額（概算）を取得する。
     * @return 1カ月あたりの積立金額（概算）
     */
    public FundReserveSumAmount getOneMonthSumAmount() {
        return oneMonthSumAmount;
    }

    /**
     * 1カ月あたりの積立金額（概算）を設定する。
     * @param oneMonthSumAmount 1カ月あたりの積立金額（概算）
     */
    public void setOneMonthSumAmount(FundReserveSumAmount oneMonthSumAmount) {
        this.oneMonthSumAmount = oneMonthSumAmount;
    }

    /**
     * 1年あたりの積立金額（概算）を取得する。
     * @return 1年あたりの積立金額（概算）
     */
    public FundReserveSumAmount getOneYearSumAmount() {
        return oneYearSumAmount;
    }

    /**
     * 1年あたりの積立金額（概算）を設定する。
     * @param oneYearSumAmount 1年あたりの積立金額（概算）
     */
    public void setOneYearSumAmount(FundReserveSumAmount oneYearSumAmount) {
        this.oneYearSumAmount = oneYearSumAmount;
    }

    /**
     * ボーナス月設定の合計金額を取得する。
     * @return ボーナス月設定の合計金額
     */
    public BigDecimal getBonusSumAmount() {
        return bonusSumAmount;
    }

    /**
     * ボーナス月設定の合計金額を設定する。
     * @param bonusSumAmount ボーナス月設定の合計金額
     */
    public void setBonusSumAmount(BigDecimal bonusSumAmount) {
        this.bonusSumAmount = bonusSumAmount;
    }

    /**
     * 特定／一般ボーナス月設定の合計金額を取得する。
     * @return 特定／一般ボーナス月設定の合計金額
     */
    public BigDecimal getBonusSumAmountNormal() {
        return bonusSumAmountNormal;
    }

    /**
     * 特定／一般ボーナス月設定の合計金額を設定する。
     * @param bonusSumAmountNormal 特定／一般ボーナス月設定の合計金額
     */
    public void setBonusSumAmountNormal(BigDecimal bonusSumAmountNormal) {
        this.bonusSumAmountNormal = bonusSumAmountNormal;
    }

    /**
     * NISA（成長投資枠）ボーナス月設定の合計金額を取得する。
     * @return NISA（成長投資枠）ボーナス月設定の合計金額
     */
    public BigDecimal getBonusSumAmountNisaGrowth() {
        return bonusSumAmountNisaGrowth;
    }

    /**
     * NISA（成長投資枠）ボーナス月設定の合計金額を設定する。
     * @param bonusSumAmountNisaGrowth NISA（成長投資枠）ボーナス月設定の合計金額
     */
    public void setBonusSumAmountNisaGrowth(BigDecimal bonusSumAmountNisaGrowth) {
        this.bonusSumAmountNisaGrowth = bonusSumAmountNisaGrowth;
    }

    /**
     * NISA（つみたて投資枠）ボーナス月設定の合計金額を取得する。
     * @return NISA（つみたて投資枠）ボーナス月設定の合計金額
     */
    public BigDecimal getBonusSumAmountNisaReserve() {
        return bonusSumAmountNisaReserve;
    }

    /**
     * NISA（つみたて投資枠）ボーナス月設定の合計金額を設定する。
     * @param bonusSumAmountNisaReserve NISA（つみたて投資枠）ボーナス月設定の合計金額
     */
    public void setBonusSumAmountNisaReserve(BigDecimal bonusSumAmountNisaReserve) {
        this.bonusSumAmountNisaReserve = bonusSumAmountNisaReserve;
    }

    /**
     * 次回発注予定を取得する。
     * @return 次回発注予定
     */
    public NextOrderPlan getNextOrderPlan() {
        return nextOrderPlan;
    }

    /**
     * 次回発注予定を設定する。
     * @param nextOrderPlan 次回発注予定
     */
    public void setNextOrderPlan(NextOrderPlan nextOrderPlan) {
        this.nextOrderPlan = nextOrderPlan;
    }

    /**
     * 1ヵ月クレカの積立設定上限金額を取得する。
     * @return 1ヵ月クレカの積立設定上限金額
     */
    public BigDecimal getOneMonthLimitCreditCardAmount() {
        return oneMonthLimitCreditCardAmount;
    }

    /**
     * 1ヵ月クレカの積立設定上限金額を設定する。
     * @param oneMonthLimitCreditCardAmount 1ヵ月クレカの積立設定上限金額
     */
    public void setOneMonthLimitCreditCardAmount(BigDecimal oneMonthLimitCreditCardAmount) {
        this.oneMonthLimitCreditCardAmount = oneMonthLimitCreditCardAmount;
    }

    /**
     * 1年あたりのNISA（つみたて投資枠）設定金額上限を取得する。
     * @return 1年あたりのNISA（つみたて投資枠）設定金額上限
     */
    public BigDecimal getOneYearLimitNisaReserveAmount() {
        return oneYearLimitNisaReserveAmount;
    }

    /**
     * 1年あたりのNISA（つみたて投資枠）設定金額上限を設定する。
     * @param limitTnisaAmount 1年あたりのNISA（つみたて投資枠）設定金額上限
     */
    public void setOneYearLimitNisaReserveAmount(BigDecimal oneYearLimitNisaReserveAmount) {
        this.oneYearLimitNisaReserveAmount = oneYearLimitNisaReserveAmount;
    }

    /**
     * 設定中 特定／一般 有無を取得する。
     * @return 設定中 特定／一般 有無
     */
    public boolean isNormalSetting() {
        return normalSetting;
    }

    /**
     * 設定中 特定／一般 有無を設定する。
     * @param normalSetting 設定中 特定／一般 有無
     */
    public void setNormalSetting(boolean normalSetting) {
        this.normalSetting = normalSetting;
    }

    /**
     * 設定中 ジュニア特定／一般 有無を取得する。
     * @return 設定中 ジュニア特定／一般 有無
     */
    public boolean isJnisaNormalSetting() {
        return jnisaNormalSetting;
    }

    /**
     * 設定中 ジュニア特定／一般 有無を設定する。
     * @param jnisaNormalSetting 設定中 ジュニア特定／一般 有無
     */
    public void setJnisaNormalSetting(boolean jnisaNormalSetting) {
        this.jnisaNormalSetting = jnisaNormalSetting;
    }

    /**
     * 設定中 NISA（成長投資枠） 有無を取得する。
     * @return 設定中 NISA（成長投資枠） 有無
     */
    public boolean isNisaGrowthSetting() {
        return nisaGrowthSetting;
    }

    /**
     * 設定中 NISA（成長投資枠） 有無を設定する。
     * @param nisaGrowthSetting 設定中 NISA（成長投資枠） 有無
     */
    public void setNisaGrowthSetting(boolean nisaGrowthSetting) {
        this.nisaGrowthSetting = nisaGrowthSetting;
    }

    /**
     * 設定中 NISA（つみたて投資枠） 有無を取得する。
     * @return 設定中 NISA（つみたて投資枠） 有無
     */
    public boolean isNisaReserveSetting() {
        return nisaReserveSetting;
    }

    /**
     * 設定中 NISA（つみたて投資枠） 有無を設定する。
     * @param nisaReserveSetting 設定中 NISA（つみたて投資枠） 有無
     */
    public void setNisaReserveSetting(boolean nisaReserveSetting) {
        this.nisaReserveSetting = nisaReserveSetting;
    }
}
