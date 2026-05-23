package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.account.dto.CashBalances;
import com.sbisec.helios.ap.athena.protocol.common.NisaLimit;

/**
 * 円貨金銭残高スケジュール Response
 *
 * @author shuchen.xin
 * @version 1.0
 * @date 5/25/2021
 */
public class ListScheduleCashBalancesResp {
    public ListScheduleCashBalancesResp() {
    }

    // 預り金自動スイープ
    private Boolean autoSweep;
    // 精算予定一覧表（日本）
    private List<CashBalances> cashBalances;
    // NISA投資可能枠
    private List<NisaLimit> nisaLimits;

    /**
     * @return 預り金自動スイープ
     */
    public Boolean getAutoSweep() {
        return autoSweep;
    }

    public void setAutoSweep(Boolean autoSweep) {
        this.autoSweep = autoSweep;
    }

    /**
     * @return 精算予定一覧表（日本）
     */
    public List<CashBalances> getCashBalances() {
        return cashBalances;
    }

    public void setCashBalances(List<CashBalances> cashBalances) {
        this.cashBalances = cashBalances;
    }

    /**
     * @return NISA投資可能枠
     */
    public List<NisaLimit> getNisaLimits() {
        return nisaLimits;
    }

    public void setNisaLimits(List<NisaLimit> nisaLimits) {
        this.nisaLimits = nisaLimits;
    }

}
