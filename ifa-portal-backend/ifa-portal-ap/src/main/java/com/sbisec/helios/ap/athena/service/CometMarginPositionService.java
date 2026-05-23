package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
//import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
//import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;

/**
 * 
 * Comet API 外国株式信用建玉 Service.
 * <p>
 * 一覧
 * <p>
 * 余力サービス - 外国株式信用建玉明細取得API <br>
 * 余力サービス - 外国株式信用建玉明細一覧取得API <br>
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 * 
 */
public interface CometMarginPositionService {
    
    /**
     * 余力サービス - 外国株式信用建玉明細取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用建玉明細情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginPositionReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginPositionResp
     */
    public GetMarginPositionResp getMarginPosition(GetMarginPositionReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉明細一覧取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用建玉明細一覧情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionResp
     */
    public ListMarginPositionsResp listMarginPositions(ListMarginPositionsReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 
     * @param request リクエスト
     * @return 外国株式信用建玉サマリ情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryResp
     */
    public GetMarginPositionSummaryResp getMarginPositionSummary(GetMarginPositionSummaryReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 返済注文確認用.
     * 
     * @param request リクエスト
     * @return 外国株式信用建玉サマリ情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryResp
     */
    public GetMarginPositionSummaryResp getMarginPositionSummaryForRepayOrderConfirm(
            GetMarginPositionSummaryReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建玉サマリ取得API.
     * 売買区分 区分値変換：区分.売買区分（建玉）
     * 
     * @param request リクエスト
     * @return 外国株式信用建玉サマリ情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryReq
     * @see com.sbibits.horus.ap.athena.protocol.account.GetMarginPositionSummaryResp
     */
    public GetMarginPositionSummaryResp getMarginPositionSummaryForPositionDetails(GetMarginPositionSummaryReq request)
            throws Exception;
}
