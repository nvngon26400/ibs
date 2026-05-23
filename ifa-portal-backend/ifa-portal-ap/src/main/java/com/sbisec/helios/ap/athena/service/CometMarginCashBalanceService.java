package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisReq;
import com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp;

/**
 * Comet API 余力サービス Service
 * <p>
 * 一覧
 * <p>
 * 余力サービス - リアルタイム委託保証金率取得API<br>
 * 
 * @author xin.li
 * @date: 08/12/2022
 * 
 */
public interface CometMarginCashBalanceService {
    
    /**
     * 余力サービス - リアルタイム委託保証金率取得API.
     * 
     * @param ListDepositRateBasisReq Httpリクエスト
     * @return ListDepositRateBasisResp リアルタイム委託保証金率取得
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisReq
     * @see com.sbisec.helios.ap.athena.protocol.account.ListDepositRateBasisResp
     */
    public ListDepositRateBasisResp listDepositRateBasis(ListDepositRateBasisReq request) throws Exception;
    
}
