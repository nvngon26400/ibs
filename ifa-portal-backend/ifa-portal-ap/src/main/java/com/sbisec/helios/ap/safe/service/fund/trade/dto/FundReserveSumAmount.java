package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import java.math.BigDecimal;

/**
 * DTOクラス 非隔月・隔月-1カ月あたりの積立金額（概算）
 */
public class FundReserveSumAmount {

    /** 積立金額 */
    private BigDecimal settingAmount;

    /** 現金決済積立金額 */
    private BigDecimal cashSettingAmount;

    /** クレカ決済積立金額 */
    private BigDecimal creditCardSettingAmount;

    /** 特定／一般積立金額 */
    private BigDecimal normalSettingAmount;

    /** NISA（成長投資枠）積立金額 */
    private BigDecimal nisaGrowthSettingAmount;

    /** NISA（つみたて投資枠）積立金額 */
    private BigDecimal nisaReserveSettingAmount;

    /**
     * 積立金額を取得する。
     * @return 積立金額
     */
    public BigDecimal getSettingAmount() {
        return settingAmount;
    }

    /**
     * 積立金額を設定する。
     * @param settingAmount 積立金額
     */
    public void setSettingAmount(BigDecimal settingAmount) {
        this.settingAmount = settingAmount;
    }

    /**
     * 現金決済積立金額を取得する。
     * @return 現金決済積立金額
     */
    public BigDecimal getCashSettingAmount() {
        return cashSettingAmount;
    }

    /**
     * 現金決済積立金額を設定する。
     * @param cashSettingAmount 現金決済積立金額
     */
    public void setCashSettingAmount(BigDecimal cashSettingAmount) {
        this.cashSettingAmount = cashSettingAmount;
    }

    /**
     * クレカ決済積立金額を取得する。
     * @return クレカ決済積立金額
     */
    public BigDecimal getCreditCardSettingAmount() {
        return creditCardSettingAmount;
    }

    /**
     * クレカ決済積立金額を設定する。
     * @param creditCardSettingAmount クレカ決済積立金額
     */
    public void setCreditCardSettingAmount(BigDecimal creditCardSettingAmount) {
        this.creditCardSettingAmount = creditCardSettingAmount;
    }

    /**
     * 特定／一般積立金額を取得する。
     * @return 特定／一般積立金額
     */
    public BigDecimal getNormalSettingAmount() {
        return normalSettingAmount;
    }

    /**
     * 特定／一般積立金額を設定する。
     * @param normalSettingAmount 特定／一般積立金額
     */
    public void setNormalSettingAmount(BigDecimal normalSettingAmount) {
        this.normalSettingAmount = normalSettingAmount;
    }

    /**
     * NISA（成長投資枠）積立金額を取得する。
     * @return NISA（成長投資枠）積立金額
     */
    public BigDecimal getNisaGrowthSettingAmount() {
        return nisaGrowthSettingAmount;
    }

    /**
     * NISA（成長投資枠）積立金額を設定する。
     * @param nisaGrowthSettingAmount NISA（成長投資枠）積立金額
     */
    public void setNisaGrowthSettingAmount(BigDecimal nisaGrowthSettingAmount) {
        this.nisaGrowthSettingAmount = nisaGrowthSettingAmount;
    }

    /**
     * NISA（つみたて投資枠）積立金額を取得する。
     * @return NISA（つみたて投資枠）積立金額
     */
    public BigDecimal getNisaReserveSettingAmount() {
        return nisaReserveSettingAmount;
    }

    /**
     * NISA（つみたて投資枠）積立金額を設定する。
     * @param nisaReserveSettingAmount NISA（つみたて投資枠）積立金額
     */
    public void setNisaReserveSettingAmount(BigDecimal nisaReserveSettingAmount) {
        this.nisaReserveSettingAmount = nisaReserveSettingAmount;
    }
}
