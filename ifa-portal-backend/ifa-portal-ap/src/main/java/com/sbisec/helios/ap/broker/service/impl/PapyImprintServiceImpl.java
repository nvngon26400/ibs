package com.sbisec.helios.ap.broker.service.impl;

import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.broker.PapyOkHttpResponse;
import com.sbisec.helios.ap.broker.protocol.GetImageForIFAReq;
import com.sbisec.helios.ap.broker.protocol.GetImageForIFAResp;
import com.sbisec.helios.ap.broker.service.PapyImprintService;
import com.sbisec.helios.ap.broker.util.PapyConnectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PapyImprintServiceImpl extends PapyConnectionBaseService implements PapyImprintService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ImprintInquiryServiceImpl.class);
    
    private static final String GET_IMAGE_FOR_IFA = "/sec/web/api/ifa/getImageForIFA";
    
    @Override
    public GetImageForIFAResp getImprintImage(String ifaIdentifier, String butenCode, String accountNumber)
            throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("PapyImprintServiceImpl.getImprintImage : {}", hashCode());
        }
        
        // ifaIdentifier non empty check
        if (StringUtil.isNullOrEmpty(ifaIdentifier)) {
            LOG.error("ifaIdentifier is null or empty!");
            throw new PapyConnectionException("ifaIdentifier is null or empty!");
        }
        // butenCode non empty check
        if (StringUtil.isNullOrEmpty(butenCode)) {
            LOG.error("butenCode is null or empty!");
            throw new PapyConnectionException("butenCode is null or empty!");
        }
        // accountNumber non empty check
        if (StringUtil.isNullOrEmpty(accountNumber)) {
            LOG.error("accountNumber is null or empty!");
            throw new PapyConnectionException("accountNumber is null or empty!");
        }
        GetImageForIFAReq request = new GetImageForIFAReq();
        request.getParameter().setIfaIdentifier(ifaIdentifier); // IFA識別子
        request.getParameter().setDepartId(butenCode); // 部店
        request.getParameter().setAccountNo(accountNumber); // 口座
        
        String url = getUrl(GET_IMAGE_FOR_IFA);
        
        PapyOkHttpResponse httpResp = null;
        // post要求を送信
        httpResp = super.post(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Get papy response data : success");
        }
        // 設定応答メッセージ
        GetImageForIFAResp resp = null;
        try {
            // convert the string into entity bean and return it.
            resp = httpResp.getResponseData(GetImageForIFAResp.class);
        } catch (Exception e) {
            LOG.warn("Papy response data deserialization exception");
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        return resp;
    }
}
