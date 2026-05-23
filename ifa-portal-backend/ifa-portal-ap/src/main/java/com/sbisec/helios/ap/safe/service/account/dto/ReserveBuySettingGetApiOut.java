package com.sbisec.helios.ap.safe.service.account.dto;

import java.math.BigDecimal;

/**
 * 積立買付利用設定取得OutDto
 */
public class ReserveBuySettingGetApiOut extends AccountDtoOut {

    /** ポイント利用設定 */
    private String pointUseType;
    /** ポイント利用上限 */
    private BigDecimal pointUseUpperLimit;
    /** 当月ポイント利用合計 */
    private BigDecimal currentMonthUsedPointTotal;

    /**
     * ポイント利用設定を取得する。
     * @return pointUseType
     */
    public String getPointUseType() {
        return pointUseType;
    }

    /**
     * ポイント利用設定を設定する。
     * @param pointUseType ポイント利用設定
     */
    public void setPointUseType(final String pointUseType) {
        this.pointUseType = pointUseType;
    }

    /**
     * ポイント利用上限を取得する。
     * @return pointUseUpperLimit
     */
    public BigDecimal getPointUseUpperLimit() {
        return pointUseUpperLimit;
    }

    /**
     * ポイント利用上限を設定する。
     * @param pointUseUpperLimit ポイント利用上限
     */
    public void setPointUseUpperLimit(final BigDecimal pointUseUpperLimit) {
        this.pointUseUpperLimit = pointUseUpperLimit;
    }

    /**
     * 当月ポイント利用合計を取得する。
     * @return currentMonthUsedPointTotal
     */
    public BigDecimal getCurrentMonthUsedPointTotal() {
        return currentMonthUsedPointTotal;
    }

    /**
     * 当月ポイント利用合計を設定する。
     * @param currentMonthUsedPointTotal 当月ポイント利用合計
     */
    public void setCurrentMonthUsedPointTotal(final BigDecimal currentMonthUsedPointTotal) {
        this.currentMonthUsedPointTotal = currentMonthUsedPointTotal;
    }
}
