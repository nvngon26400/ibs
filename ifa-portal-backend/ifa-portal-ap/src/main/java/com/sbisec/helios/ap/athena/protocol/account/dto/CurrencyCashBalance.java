package com.sbisec.helios.ap.athena.protocol.account.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 外貨金銭残高スケジュール(全通貨) Dto.
 * 
 * @author shuchen.xin
 * @date 01/05/2022
 */
public class CurrencyCashBalance implements Serializable {
    private static final long serialVersionUID = -1557279975038139907L;

    public CurrencyCashBalance() {
    }

    // 通貨コード
    private String currencyCode;
    // 外貨金銭残高スケジュールリスト
    private List<ForeignScheduleCashBalance> foreignScheduleCashBalances;

    /**
    * 通貨コード
    * 
    * @return
    */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * 通貨コード
     * 
     * @param currencyCode
     */
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 外貨金銭残高スケジュールリスト
     * 
     * @return
     */
    public List<ForeignScheduleCashBalance> getForeignScheduleCashBalances() {
        return foreignScheduleCashBalances;
    }

    /**
     * 外貨金銭残高スケジュールリスト
     * 
     * @param foreignScheduleCashBalances
     */
    public void setForeignScheduleCashBalances(List<ForeignScheduleCashBalance> foreignScheduleCashBalances) {
        this.foreignScheduleCashBalances = foreignScheduleCashBalances;
    }

}
