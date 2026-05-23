package com.sbisec.helios.ap.sss.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sbisec.helios.ap.sss.SssOkHttpResponse;
import com.sbisec.helios.ap.sss.protocol.ConnectSssReq;
import com.sbisec.helios.ap.sss.protocol.ConnectSssResp;
import com.sbisec.helios.ap.sss.service.SssConnectionService;

@Service
public class SssConnectionServiceImpl extends SssConnectionBaseService implements SssConnectionService{

	private static final Logger LOG = LoggerFactory.getLogger(SssConnectionServiceImpl.class);
	private static final String GET_SSS_FOR_IFA = "/allain-server-gb/app/sso-token";
    private static final String GET_SSS_NEW_FOR_IFA = "/allain-server/app/sso-token";

	@Override
	public ConnectSssResp getSssCertificationInfo(String userId, String apiKey, String sssType) throws Exception {
		long start = System.currentTimeMillis();
	
		if (LOG.isDebugEnabled()) {
			LOG.debug("SssConnectServiceImpl.getSssCertificationInfo : {}", hashCode());
		}
		
		ConnectSssReq request = new ConnectSssReq();
		request.getParameter().setUserId(userId);
		request.getParameter().setApiKey(apiKey);
		
	
		String url = "";
		if ("1".equals(sssType)) {
		    url = getUrl(GET_SSS_NEW_FOR_IFA, sssType);
		} else {
		    url = getUrl(GET_SSS_FOR_IFA, sssType);
		}
	
		SssOkHttpResponse httpResp = null;
		// post要求を送信
		httpResp = super.post(url, request);
	
		if (LOG.isDebugEnabled()) {
			LOG.debug("Get sss response data : success");
		}
		// 設定応答メッセージ
		ConnectSssResp resp = null;
		try {
			// convert the string into entity bean and return it.
			resp = httpResp.getResponseData(ConnectSssResp.class);
		} catch (Exception e) {
			LOG.warn("Sss response data deserialization exception");
			throw e;
		}

		if (LOG.isDebugEnabled()) {
			LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
		}

		return resp;
	}
}
