package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignMarginScheduleCashBalancesResp;

/**
 * @author SCSK 矢口
    2023/12/1 新規作成
 */
public interface CometListForeignMarginScheduleCashBalances {
    
    /**
     * 余力サービス - 外貨信用保証金残高スケジュール取得API

     * @param request Httpリクエスト
     * @return 外貨信用保証金残高スケジュール情報
     * @throws Exception 異常
     */
    public ListForeignMarginScheduleCashBalancesResp listForeignMarginScheduleCashBalances(
            ListForeignMarginScheduleCashBalancesReq request) throws Exception;
}
