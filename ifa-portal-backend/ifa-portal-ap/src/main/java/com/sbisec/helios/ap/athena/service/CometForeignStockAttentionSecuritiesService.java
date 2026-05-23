package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;

/**
 * 外国株式銘柄サービス
 * <p>
 * 一覧
 * <p>
 * 外国株式銘柄サービス - 外国株式本日注意銘柄取得API <br>
 * 
 * @author shuchen.xin
 * @date 02/16/2022
 */
public interface CometForeignStockAttentionSecuritiesService {
    
    /**
     * 外国株式銘柄サービス - 外国株式本日注意銘柄取得API.
     * 
     * @param request Httpリクエスト
     * @return 外国株式本日注意銘柄取得
     * @throws Exception 異常
     * 
     * @see com.sbibits.horus.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesReq
     * @see com.sbibits.horus.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp
     */
    public GetForeignStockAttentionSecuritiesResp getForeignStockAttentionSecurities(
            GetForeignStockAttentionSecuritiesReq request) throws Exception;
    
}
