package com.sbisec.helios.ap.safe.service;

import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailRes;

public interface SafeFundProductService {

    /**
     * 銘柄詳細API 投信基本の取得 POST:/safe/fundProduct/fund/basic
     * 
     */
    public GetFundBasicRes getBasicInfo(GetFundBasicReq req) throws Exception;

    /**
     * 銘柄詳細API 投信詳細の取得 POST:/safe/fundProduct/fund/detail
     * 
     */
    public GetFundDetailRes getDetailInfo(GetFundDetailReq req) throws Exception;

}
