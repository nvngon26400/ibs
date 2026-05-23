package com.sbisec.helios.ap.ccsApi.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.ccsApi.CcsApiOkHttpResponse;
import com.sbisec.helios.ap.ccsApi.protocol.ShowSubContractHistoryIFAReq;
import com.sbisec.helios.ap.ccsApi.protocol.ShowSubContractHistoryIFARes;
import com.sbisec.helios.ap.ccsApi.protocol.ShowDealingDetailIFAReq;
import com.sbisec.helios.ap.ccsApi.protocol.ShowDealingDetailIFARes;
import com.sbisec.helios.ap.ccsApi.service.CcsApiBaseService;
import com.sbisec.helios.ap.ccsApi.service.CcsApiService;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowSubContractHistoryIFAOut;
import com.sbisec.helios.ap.ccsApi.service.dto.ShowDealingDetailIFAOut;

@Service
public class CcsApiServiceImpl extends CcsApiBaseService implements CcsApiService {
    private static final Logger LOG = LoggerFactory.getLogger(CcsApiServiceImpl.class);

    @Override
    public ShowSubContractHistoryIFARes calShowSubContractHistoryIFA(ShowSubContractHistoryIFAReq x_req) throws Exception {

        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("CcsApiServiceImpl.calShowSubContractHistoryIFA : {}", hashCode());
        }
        // パラメータチェック
        checkParameter(x_req.getParameter());
        // post要求を送信
        CcsApiOkHttpResponse p_httpRes = post(getCcsUrl(x_req.getShowSubContractHistoryIFAUrl()), x_req);
        if (LOG.isDebugEnabled()) {
            LOG.debug("CCS Api response data => {}", p_httpRes.getResData());
        }
        // 設定応答メッセージ
        ShowSubContractHistoryIFARes p_res = new ShowSubContractHistoryIFARes();
        try {
            // convert the string into entity bean and return it.
            ShowSubContractHistoryIFAOut p_out = p_httpRes.getShowSubContractHistoryIFAResponseData();
            p_res.setCcsApiOut(p_out);
        } catch (Exception e) {
            LOG.warn("CCS Api response data deserialization exception:", e);
            throw e;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        // 結果を返す
        return p_res;
    }

    @Override
    public ShowDealingDetailIFARes calShowDealingDetailIFA(ShowDealingDetailIFAReq x_req) throws Exception {

        long start = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("CcsApiServiceImpl.calShowDealingDetailIFA : {}", hashCode());
        }
        // パラメータチェック
        checkParameter(x_req.getParameter());
        // post要求を送信
        CcsApiOkHttpResponse p_httpRes = post(getCcsUrl(x_req.getShowDealingDetailIFAUrl()), x_req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("CCS Api response data => {}", p_httpRes.getResData());
        }
        // 設定応答メッセージ
        ShowDealingDetailIFARes p_res = new ShowDealingDetailIFARes();
        try {
            // convert the string into entity bean and return it.
            ShowDealingDetailIFAOut p_out = p_httpRes.getShowDealingDetailIFAResponseData();
            p_res.setCcsApiOut(p_out);
        } catch (Exception e) {
            LOG.warn("CCS Api response data deserialization exception:", e);
            throw e;
        }
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        // 結果を返す
        return p_res;
    }

}
