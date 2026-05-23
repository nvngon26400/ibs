package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import javax.validation.constraints.NotNull;

/**
 * DTOクラス 取得対象積立設定情報
 */
public class ReserveSettingDataForBulkUpdate {

    /** 協会コード */
    private String fundCode;

    /** 決済方法 */
    @NotNull
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

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
}
