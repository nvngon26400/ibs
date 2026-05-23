package com.sbisec.helios.ap.athena.protocol.account;

import java.util.List;

import lombok.Data;
/**
 * 預り金一括取得API
 * @author SCSK
 *
 */
@Data
public class ListMultiGetCashDepositsResp {
    private List<CashDeposit> cashDeposits;
}
