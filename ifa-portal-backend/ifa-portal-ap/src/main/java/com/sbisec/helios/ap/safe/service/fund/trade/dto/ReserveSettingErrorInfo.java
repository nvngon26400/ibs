package com.sbisec.helios.ap.safe.service.fund.trade.dto;

import com.sbisec.helios.ap.safe.common.exception.dto.ErrorInfo;

/**
 * DTOクラス 積立設定 エラー情報
 */
public class ReserveSettingErrorInfo {

    /** 協会コード */
    private String fundCode;

    /** ファンド名 */
    private String fundName;

    /** 決済方法 */
    private String paymentMethod;

    /** 預り区分 */
    private String accountType;

    /** エラー情報 */
    private ErrorInfo errorInfo;

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
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

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
