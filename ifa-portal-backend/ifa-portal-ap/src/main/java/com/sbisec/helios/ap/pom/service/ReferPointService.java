package com.sbisec.helios.ap.pom.service;

import javax.validation.constraints.NotNull;

import com.sbisec.helios.ap.pom.protocol.ReferPointReq;
import com.sbisec.helios.ap.pom.protocol.ReferPointRes;

/**
 * ポイント残高照会
 * 
 * @author SCSK
 *
 */
public interface ReferPointService {
    
    @NotNull
    public ReferPointRes getReferPoint(ReferPointReq req) throws Exception;
}
