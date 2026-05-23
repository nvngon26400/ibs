package com.sbisec.helios.ap.fasthelp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.fasthelp.FastCcsOkHttpResponse;
import com.sbisec.helios.ap.fasthelp.protocol.fasthelp.GetFasthelpCcsCallsImportReq;
import com.sbisec.helios.ap.fasthelp.protocol.fasthelp.GetFasthelpCcsCallsImportRes;
import com.sbisec.helios.ap.fasthelp.service.AbstractBaseService;
import com.sbisec.helios.ap.fasthelp.service.FastHelp2CcsCallsImportService;
import com.sbisec.helios.ap.fasthelp.service.dto.fasthelp.FasthelpCcsCallsImportOut;

@Service
public class FastHelp2CcsCallsImportServiceImpl extends AbstractBaseService implements FastHelp2CcsCallsImportService {

    private static final Logger LOG = LoggerFactory.getLogger(FastHelp2CcsCallsImportServiceImpl.class);

    @Override
    public GetFasthelpCcsCallsImportRes getFasthelpCcsCallsImport(GetFasthelpCcsCallsImportReq req)
            throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("FastHelp2CcsCallsImportServiceImpl.getFasthelpCcsCallsImport : {}", hashCode());
        }

        // パラメータチェック
        checkParameter(req.getParameter());

        // post要求を送信
        FastCcsOkHttpResponse httpResp = post(getFasthelpUrl(req.getFasthelpApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Fasthelp response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetFasthelpCcsCallsImportRes resp = new GetFasthelpCcsCallsImportRes();
        try {
            // convert the string into entity bean and return it.
            FasthelpCcsCallsImportOut FasthelpApiOut = httpResp.getFastHelpCcsCallsResponseData();
            resp.setFasthelpCcsCallsImportOut(FasthelpApiOut);
        } catch (Exception e) {
            LOG.warn("Fasthelp response data deserialization exception:", e);
            throw e;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }

        // 結果を返す
        return resp;
    }

}
