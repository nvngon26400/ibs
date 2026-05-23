package com.sbisec.helios.ap.safe.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.safe.SafeOkHttpResponse;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingReq;
import com.sbisec.helios.ap.safe.protocol.account.GetAccountPointGetReserveBuySettingRes;
import com.sbisec.helios.ap.safe.service.SafeAbstractBaseService;
import com.sbisec.helios.ap.safe.service.SafeAccountService;
import com.sbisec.helios.ap.safe.service.account.dto.ReserveBuySettingGetApiOut;

@Service
public class SafeAccountServiceImpl extends SafeAbstractBaseService implements SafeAccountService {

    private static final Logger LOG = LoggerFactory.getLogger(SafeAccountServiceImpl.class);

    @Override
    public GetAccountPointGetReserveBuySettingRes getReserveBuySetting(GetAccountPointGetReserveBuySettingReq req)
            throws Exception {

        long start = System.currentTimeMillis();

        if (LOG.isDebugEnabled()) {
            LOG.debug("SafeAccountServiceImpl.getReserveBuySetting : {}", hashCode());
        }

        // パラメータチェック
        checkHeaderAndParameter(req.getHeader().getToken(), req.getParameter());

        // post要求を送信
        SafeOkHttpResponse httpResp = post(getUrl(req.getSafeApiUrl()), req);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Safe response data => {}", httpResp.getResponsData());
        }

        // 設定応答メッセージ
        GetAccountPointGetReserveBuySettingRes resp = new GetAccountPointGetReserveBuySettingRes();
        try {
            // convert the string into entity bean and return it.
            ReserveBuySettingGetApiOut safeApiOut = httpResp.getResponseData(ReserveBuySettingGetApiOut.class);
            resp.setReserveBuySettingGetApiOut(safeApiOut);

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
