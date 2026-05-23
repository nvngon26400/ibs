package com.sbisec.helios.ap.fasthelp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.fasthelp.FastCcsOkHttpResponse;
import com.sbisec.helios.ap.fasthelp.protocol.ccs.GetCcsFastHelpInfoInsertDoReq;
import com.sbisec.helios.ap.fasthelp.protocol.ccs.GetCcsFastHelpInfoInsertDoRes;
import com.sbisec.helios.ap.fasthelp.service.AbstractBaseService;
import com.sbisec.helios.ap.fasthelp.service.Ccs2FastHelpInfoInsertService;
import com.sbisec.helios.ap.fasthelp.service.dto.ccs.CcsFastHelpInfoInsertDoOut;

@Service
public class Ccs2FastHelpInfoInsertServiceImpl extends AbstractBaseService implements Ccs2FastHelpInfoInsertService {

  private static final Logger LOG = LoggerFactory.getLogger(Ccs2FastHelpInfoInsertServiceImpl.class);

  @Override
  public GetCcsFastHelpInfoInsertDoRes getCcsFastHelpInfoInsertDo(GetCcsFastHelpInfoInsertDoReq req)
          throws Exception {

      long start = System.currentTimeMillis();

      if (LOG.isDebugEnabled()) {
          LOG.debug("Ccs2FastHelpInfoInsertServiceImpl.getCcsFastHelpInfoInsertDo : {}", hashCode());
      }

      // パラメータチェック
      checkParameter(req.getParameter());

      // post要求を送信
      FastCcsOkHttpResponse httpResp = post(getCcsUrl(req.getFasthelpApiUrl()), req);

      if (LOG.isDebugEnabled()) {
          LOG.debug("Fasthelp response data => {}", httpResp.getResponsData());
      }

      // 設定応答メッセージ
      GetCcsFastHelpInfoInsertDoRes resp = new GetCcsFastHelpInfoInsertDoRes();
      try {
          // convert the string into entity bean and return it.
          CcsFastHelpInfoInsertDoOut FasthelpApiOut = httpResp.getCcsFastHelpResponseData();
          resp.setCcsFastHelpInfoInsertDoOut(FasthelpApiOut);

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
