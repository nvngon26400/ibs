package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp;

/**
 * Comet API 外国株式銘柄 Service
 * <p>
 * 一覧
 * <p>
 * 外国株式銘柄サービス - 外国株式銘柄検索API<br>
 * 外国株式銘柄サービス - 外国株式銘柄マスタ取得API<br>
 * 
 * @author shuchen.xin
 * @date: 02/11/2022
 * 
 */
public interface CometForeignStockSecuritiesService {
    
    /**
     * 外国株式銘柄サービス - 外国株式銘柄マスタ取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式銘柄マスタ取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp
     */
    public GetForeignStockSecuritiesResp getForeignStockSecurities(GetForeignStockSecuritiesReq request)
            throws Exception;
    
    /**
     * 外国株式銘柄サービス - 外国株式銘柄検索API.
     * 
     * @param listForeignStockSecuritiesReq Httpリクエスト
     * @return 外国株式銘柄マスタ取得
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesReq
     * @see com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp
     */
    public ListForeignStockSecuritiesResp listForeignStockSecurities(
            ListForeignStockSecuritiesReq listForeignStockSecuritiesReq) throws Exception;
    
}
