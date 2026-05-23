package com.sbisec.helios.ap.safe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.safe.SafeOkHttpResponse;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundBasicRes;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailReq;
import com.sbisec.helios.ap.safe.protocol.fundProduct.GetFundDetailRes;
import com.sbisec.helios.ap.safe.service.SafeAbstractBaseService;
import com.sbisec.helios.ap.safe.service.SafeFundProductService;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundBasicInfoApiOut;
import com.sbisec.helios.ap.safe.service.fund.product.dto.FundDetailInfoApiOut;

@Service
public class SafeFundProductServiceImpl extends SafeAbstractBaseService implements SafeFundProductService {

    private static final Logger LOG = LoggerFactory.getLogger(SafeFundProductServiceImpl.class);

    @Override
    public GetFundBasicRes getBasicInfo(GetFundBasicReq req) throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundProductServiceImpl.getBasicInfo : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundBasicRes resp = new GetFundBasicRes();
        try {
            // convert the string into entity bean and return it.
            FundBasicInfoApiOut safeApiOut = httpResp.getResponseData(FundBasicInfoApiOut.class);
            resp.setFundBasicInfoApiOut(safeApiOut);

        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

    @Override
    public GetFundDetailRes getDetailInfo(GetFundDetailReq req) throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeFundProductServiceImpl.getDetailInfo : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = this.post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFundDetailRes resp = new GetFundDetailRes();
        try {
            // convert the string into entity bean and return it.
            FundDetailInfoApiOut safeApiOut = httpResp.getResponseData(FundDetailInfoApiOut.class);
            resp.setFundDetailInfoApiOut(safeApiOut);

        } catch (Exception e) {
            LOG.warn("Safe response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

}
