package com.sbisec.helios.ap.safe.service.common.dto;


/**
 * 口座情報DTO
 */
public class AccountDto {

    /**
     * 部店
     */
    private String butenCode;
    /**
     * 口座番号
     */
    private String accountNumber;

    public String getButenCode() {
        return butenCode;
    }
    public void setButenCode(final String butenCode) {
        this.butenCode = butenCode;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(final String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
