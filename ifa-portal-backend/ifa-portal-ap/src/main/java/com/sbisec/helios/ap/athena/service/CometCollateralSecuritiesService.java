package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq;
import com.sbisec.helios.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp;

/**
 * Comet API 余力サービス Service
 * <p>
 * 一覧
 * <p>
 * 余力サービス - 代用有価証券振替可能一覧取得API<br>
 * 
 * @author xin.li
 * @date: 03/09/2022
 * 
 */
public interface CometCollateralSecuritiesService {
    
    /**
     * 余力サービス - 代用有価証券振替可能一覧取得API.
     * 
     * @param ListPossibleCollateralSecuritiesTransfersReq Httpリクエスト
     * @return ListPossibleCollateralSecuritiesTransfersResp 代用有価証券振替可能一覧取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersReq
     * @see com.sbibits.horus.ap.athena.protocol.account.ListPossibleCollateralSecuritiesTransfersResp
     */
    public ListPossibleCollateralSecuritiesTransfersResp listPossibleCollateralSecuritiesTransfers(
            ListPossibleCollateralSecuritiesTransfersReq request) throws Exception;
    
}
