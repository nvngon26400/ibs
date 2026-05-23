package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPowerDetailResp;

/**
 * @author SCSK 矢口
    2023/12/1 新規作成
 */
public interface CometGetMarginPowerDetail {
    
    /**
     * 余力サービス - 外国株式信用建余力詳細取得API

     * @param request Httpリクエスト
     * @return 外国株式信用建余力詳細
     * @throws Exception 異常
     */
    public GetMarginPowerDetailResp getMarginPowerDetail(GetMarginPowerDetailReq request) throws Exception;
}
