package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.ForeignCashBalance;

/**
 * 外貨金銭残高スケジュール Response
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/27/2021
 */
public class ListForeignScheduleCashBalancesResp {
    public ListForeignScheduleCashBalancesResp() {
    }

    // 外貨金銭残高スケジュール
    private List<ForeignCashBalance> foreignCashBalances;

    /**
     * @return 外貨金銭残高スケジュール
     */
    public List<ForeignCashBalance> getForeignCashBalances() {
        return foreignCashBalances;
    }

    /**
     * @param foreignCashBalances the foreignCashBalances to set
     */
    public void setForeignCashBalances(List<ForeignCashBalance> foreignCashBalances) {
        this.foreignCashBalances = foreignCashBalances;
    }
}
