package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 精算予定一覧表（日本） Dto
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
public class CashBalances implements Serializable {
    private static final long serialVersionUID = 1198417499342449237L;

    public CashBalances() {
    }

    // 口座分類
    private String accountKind;
    // 精算予定一覧表（日本）
    private List<ScheduleCashBalance> scheduleCashBalances;

    /**
     * 口座分類
     * 
     * @return
     */
    public String getAccountKind() {
        return accountKind;
        }

    /**
     * 口座分類
     * 
     * @param accountKind
     */
    public void setAccountKind(String accountKind) {
        this.accountKind = accountKind;
    }

    /**
     * 精算予定一覧表（日本）
     * 
     * @return
     */
    public List<ScheduleCashBalance> getScheduleCashBalances() {
        return scheduleCashBalances;
    }

    /**
     * 精算予定一覧表（日本）
     * 
     * @param scheduleCashBalances
     */
    public void setScheduleCashBalances(List<ScheduleCashBalance> scheduleCashBalances) {
        this.scheduleCashBalances = scheduleCashBalances;
    }
}
