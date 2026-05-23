package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 外貨金銭残高スケジュール Dto.
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
public class ForeignCashBalance implements Serializable {
    private static final long serialVersionUID = 6129141965796956741L;

    public ForeignCashBalance() {
    }

    // 口座分類
    private String accountKind;
    // 外貨金銭残高スケジュール(全通貨)
    private List<CurrencyCashBalance> currencyCashBalances;

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
     * 外貨金銭残高スケジュール(全通貨)
     * 
     * @return
     */
    public List<CurrencyCashBalance> getCurrencyCashBalances() {
        return currencyCashBalances;
    }

    /**
     * 外貨金銭残高スケジュール(全通貨)
     * 
     * @param currencyCashBalances
     */
    public void setCurrencyCashBalances(List<CurrencyCashBalance> currencyCashBalances) {
        this.currencyCashBalances = currencyCashBalances;
    }
}
