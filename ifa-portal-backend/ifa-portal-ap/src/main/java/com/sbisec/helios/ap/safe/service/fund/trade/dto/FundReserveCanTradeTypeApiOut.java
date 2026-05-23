package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import com.sbisec.helios.ap.safe.service.common.dto.DtoOut;

/**
 * DTOクラス 設定可能な預かり区分の取得（現金とクレカ）
 *
 */
public class FundReserveCanTradeTypeApiOut extends DtoOut {

    /** ジュニアNISA口座開設有無 */
    private boolean openedJnisa;

    /** NISA口座開設有無 */
    private boolean openedNisa3;

    /** 払出制限中 */
    private boolean duringPayoutLimit;

    /** クレジットカード会社 */
    private String creditCardCompany;

    /** 特定／一般 積立設定状態（現金）*/
    private String cashNormalStatus;

    /** ジュニア特定／一般 積立設定状態（現金）*/
    private String cashJnisaNormalStatus;

    /** NISA（成長投資枠） 積立設定状態（現金）*/
    private String cashNisaGrowthStatus;

    /** NISA（つみたて投資枠） 積立設定状態（現金）*/
    private String cashNisaReserveStatus;

    /** 特定／一般 積立設定状態（クレカ）*/
    private String creditNormalStatus;

    /** NISA（成長投資枠） 積立設定状態（クレカ）*/
    private String creditNisaGrowthStatus;

    /** NISA（つみたて投資枠） 積立設定状態（クレカ）*/
    private String creditNisaReserveStatus;

    /** 警告コード */
    private String warningCode;

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
    public void setOpenedJnisa(final boolean openedJnisa) {
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
    public void setOpenedNisa3(final boolean openedNisa3) {
        this.openedNisa3 = openedNisa3;
    }

    /**
     * 払出制限中を取得する。
     * @return 払出制限中
     */
    public boolean isDuringPayoutLimit() {
        return duringPayoutLimit;
    }

    /**
     * 払出制限中を設定する。
     * @param duringPayoutLimit 払出制限中
     */
    public void setDuringPayoutLimit(final boolean duringPayoutLimit) {
        this.duringPayoutLimit = duringPayoutLimit;
    }

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
    public void setCreditCardCompany(final String creditCardCompany) {
        this.creditCardCompany = creditCardCompany;
    }

    /**
     * 特定／一般 積立設定状態（現金）を取得する。
     * @return 特定／一般 積立設定状態（現金）
     */
    public String getCashNormalStatus() {
        return cashNormalStatus;
    }

    /**
     * 特定／一般 積立設定状態（現金）を設定する。
     * @param cashNormalStatus 特定／一般 積立設定状態（現金）
     */
    public void setCashNormalStatus(final String cashNormalStatus) {
        this.cashNormalStatus = cashNormalStatus;
    }

    /**
     * ジュニア特定／一般 積立設定状態（現金）を取得する。
     * @return ジュニア特定／一般 積立設定状態（現金）
     */
    public String getCashJnisaNormalStatus() {
        return cashJnisaNormalStatus;
    }

    /**
     * ジュニア特定／一般 積立設定状態（現金）を設定する。
     * @param cashJnisaNormalStatus ジュニア特定／一般 積立設定状態（現金）
     */
    public void setCashJnisaNormalStatus(final String cashJnisaNormalStatus) {
        this.cashJnisaNormalStatus = cashJnisaNormalStatus;
    }

    /**
     * NISA（成長投資枠） 積立設定状態（現金）を取得する。
     * @return NISA（成長投資枠） 積立設定状態（現金）
     */
    public String getCashNisaGrowthStatus() {
        return cashNisaGrowthStatus;
    }

    /**
     * NISA（成長投資枠） 積立設定状態（現金）を設定する。
     * @param cashNisaGrowthStatus NISA（成長投資枠） 積立設定状態（現金）
     */
    public void setCashNisaGrowthStatus(final String cashNisaGrowthStatus) {
        this.cashNisaGrowthStatus = cashNisaGrowthStatus;
    }

    /**
     * NISA（つみたて投資枠） 積立設定状態（現金）を取得する。
     * @return NISA（つみたて投資枠） 積立設定状態（現金）
     */
    public String getCashNisaReserveStatus() {
        return cashNisaReserveStatus;
    }

    /**
     * NISA（つみたて投資枠） 積立設定状態（現金）を設定する。
     * @param cashNisaReserveStatus NISA（つみたて投資枠） 積立設定状態（現金）
     */
    public void setCashNisaReserveStatus(final String cashNisaReserveStatus) {
        this.cashNisaReserveStatus = cashNisaReserveStatus;
    }

    /**
     * 特定／一般 積立設定状態（クレカ）を取得する。
     * @return 特定／一般 積立設定状態（クレカ）
     */
    public String getCreditNormalStatus() {
        return creditNormalStatus;
    }

    /**
     * 特定／一般 積立設定状態（クレカ）を設定する。
     * @param creditNormalStatus 特定／一般 積立設定状態（クレカ）
     */
    public void setCreditNormalStatus(final String creditNormalStatus) {
        this.creditNormalStatus = creditNormalStatus;
    }

    /**
     * NISA（成長投資枠） 積立設定状態（クレカ）を取得する。
     * @return NISA（成長投資枠） 積立設定状態（クレカ）
     */
    public String getCreditNisaGrowthStatus() {
        return creditNisaGrowthStatus;
    }

    /**
     * NISA（成長投資枠） 積立設定状態（クレカ）を設定する。
     * @param creditNisaGrowthStatus NISA（成長投資枠） 積立設定状態（クレカ）
     */
    public void setCreditNisaGrowthStatus(final String creditNisaGrowthStatus) {
        this.creditNisaGrowthStatus = creditNisaGrowthStatus;
    }

    /**
     * NISA（つみたて投資枠） 積立設定状態（クレカ）を取得する。
     * @return NISA（つみたて投資枠） 積立設定状態（クレカ）
     */
    public String getCreditNisaReserveStatus() {
        return creditNisaReserveStatus;
    }

    /**
     * NISA（つみたて投資枠） 積立設定状態（クレカ）を設定する。
     * @param creditNisaReserveStatus NISA（つみたて投資枠） 積立設定状態（クレカ）
     */
    public void setCreditNisaReserveStatus(final String creditNisaReserveStatus) {
        this.creditNisaReserveStatus = creditNisaReserveStatus;
    }

    /**
     * 警告コードを取得する。
     * @return 警告コード
     */
    public String getWarningCode() {
        return warningCode;
    }

    /**
     * 警告コードを設定する。
     * @param warningCode 警告コード
     */
    public void setWarningCode(final String warningCode) {
        this.warningCode = warningCode;
    }
}
