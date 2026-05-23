package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceReq;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesResp;

/**
 * Comet API 余力サービス Service
 * <p>
 * 一覧
 * <p>
 * 余力サービス - 外貨建商品保有証券一覧取得API
 * 
 * @author shuchen.xin
 * @date: 02/14/2022
 */
public interface CometSecuritiesBalanceService {
    
    /**
     * 余力サービス - 外貨建商品保有証券一覧取得API.
     *
     * @param listSecuritiesBalancesReq Httpリクエスト
     * @return ListSecuritiesBalancesResp 外貨建商品保有証券一覧取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.ListSecuritiesBalancesReq
     * @see com.sbibits.horus.ap.athena.protocol.account.ListSecuritiesBalancesResp
     */
    public ListSecuritiesBalancesResp listSecuritiesBalances(ListSecuritiesBalancesReq listSecuritiesBalancesReq)
            throws Exception;

	/**
	 * 余力サービス - 外貨建商品保有証券取得API.
	 * 
	 * @param getSecuritiesBalanceReq Httpリクエスト
	 * @return GetSecuritiesBalanceResp 外貨建商品保有証券取得
	 * @throws Exception 異常
	 * 
	 * @see com.sbibits.horus.ap.athena.protocol.account.GetSecuritiesBalanceReq
	 * @see com.sbibits.horus.ap.athena.protocol.account.GetSecuritiesBalanceResp
	 */
	public GetSecuritiesBalanceResp getSecuritiesBalance(GetSecuritiesBalanceReq getSecuritiesBalanceReq)
			throws Exception;

}
