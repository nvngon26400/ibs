package com.sbisec.helios.ap.pom.service.impl;

import com.sbisec.helios.ap.pom.protocol.ReferPointReq;
import com.sbisec.helios.ap.pom.protocol.ReferPointRes;
import com.sbisec.helios.ap.pom.service.AbstractBaseService;
import com.sbisec.helios.ap.pom.service.ReferPointService;
import com.sbisec.helios.ap.pom.utils.PomUrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * ポイント残高照会
 * 
 * @author SCSK
 *
 */
@Service
public class ReferPointServiceImpl extends AbstractBaseService implements ReferPointService {
    
    private static final Logger LOG = LoggerFactory.getLogger(ReferPointServiceImpl.class);
    
    /**
     * ポイント残高照会
     * 
     * @param req
     * @return
     * @throws Exception API例外
     * @see com.sbisec.helios.ap.pom.service.ReferPointService#getReferPoint(com.sbisec.helios.ap.pom.protocol.ReferPointReq)
     */
    @Override
    public ReferPointRes getReferPoint(ReferPointReq req) throws Exception {
        
        // APIのURLを設定する。
        String url = this.getUrl(PomUrl.REFER_POINT.getUrl());
        
        // POSTを送信する。
        var httpResp = this.post(url, req);
        
        // RESPONSEをインスタンスする。
        try {
            return httpResp.getResponseData(ReferPointRes.class);
        } catch (Exception e) {
            LOG.warn("response data deserialization exception:", e);
            throw e;
        }
        
    }
    
}
