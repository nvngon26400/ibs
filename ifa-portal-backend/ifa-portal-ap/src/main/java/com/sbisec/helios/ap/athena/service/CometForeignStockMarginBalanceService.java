package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp;

/**
 * 
 * Comet API 信用建余力 Service.
 * <p>
 * 一覧
 * <p>
 * 余力サービス - 外国株式信用建余力取得API <br>
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 * 
 */
public interface CometForeignStockMarginBalanceService {
    
    /**
     * 余力サービス - 外国株式信用建余力取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用建余力情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineResp
     */
    public GetMarginPowerHeadlineResp getMarginPowerHeadline(GetMarginPowerHeadlineReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式参考信用建余力取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式参考信用建余力情報
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginPowerReferenceReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.order.GetMarginPowerReferenceResp
     */
    public GetMarginPowerReferenceResp getMarginPowerReference(GetMarginPowerReferenceReq request) throws Exception;
    
    /**
     * 余力サービス - 外国株式信用建余力サマリ取得API
     * 
     * @param request Httpリクエスト
     * @return 外国株式信用建余力情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerSummaryResp
     */
    public GetMarginPowerSummaryResp getMarginPowerSummary(GetMarginPowerSummaryReq request) throws Exception;

}
