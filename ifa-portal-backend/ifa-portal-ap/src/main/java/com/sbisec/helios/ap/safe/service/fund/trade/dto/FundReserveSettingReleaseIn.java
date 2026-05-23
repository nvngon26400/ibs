package com.sbisec.helios.ap.safe.service.fund.trade.dto;

/**
 * DTOクラス 積立設定解除機能in
 */
public class FundReserveSettingReleaseIn {

    /** 決済方法 */
    private String paymentMethod;

    /** 投資信託協会コード（投資信託コード） */
    private String fundCode;

    /** 預り区分 */
    private String accountType;

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
