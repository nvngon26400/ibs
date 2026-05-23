package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMultiGetCashDepositsResp;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListScheduleCashBalancesResp;
import com.sbisec.helios.ap.athena.protocol.account.MultiGetCashDepositsReq;

/**
 * Comet API 余力サービス Service
 * <p>
 * 一覧
 * <p>
 * 余力サービス - 円貨金銭残高スケジュール取得API<br>
 * 余力サービス - 外貨金銭残高スケジュール取得API<br>
 * 
 * @author shuchen.xin
 * @date: 02/14/2022
 * 
 */
public interface CometCashBalanceService {

    /**
     * 余力サービス - 円貨金銭残高スケジュール取得API.
     * 
     * @param ListScheduleCashBalancesReq Httpリクエスト
     * @return ListScheduleCashBalancesResp 円貨金銭残高取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.ListScheduleCashBalancesReq
     * @see com.sbibits.horus.ap.athena.protocol.account.ListScheduleCashBalancesResp
     */
    public ListScheduleCashBalancesResp listScheduleCashBalances(ListScheduleCashBalancesReq request)
            throws Exception;

    /**
     * 余力サービス - 外貨金銭残高スケジュール取得API.
     * 
     * @param ListForeignScheduleCashBalancesReq Httpリクエスト
     * @return ListForeignScheduleCashBalancesResp 外貨金銭残高取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq
     * @see com.sbibits.horus.ap.athena.protocol.account.ListForeignScheduleCashBalancesResp
     */
    public ListForeignScheduleCashBalancesResp listForeignScheduleCashBalances(
            ListForeignScheduleCashBalancesReq request) throws Exception;

    /**
     * 預り金一括取得API 
     * @param multiGetCashDepositsReq Httpリクエスト
     * @return
     */
    public ListMultiGetCashDepositsResp multiGetCashDeposits(MultiGetCashDepositsReq request)  throws Exception;
}

