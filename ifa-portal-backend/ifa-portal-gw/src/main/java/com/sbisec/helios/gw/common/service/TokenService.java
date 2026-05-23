package com.sbisec.helios.gw.common.service;

import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.service.Service;

/**
 * トークチェックサービスのインターフェイス
 */
public interface TokenService extends Service {
    
    public FrameworkSessionInfo createSessionInfo(String frameworkSessionId, UserAccount userAccount) throws Exception;
    
    public void updateSessionInfo(FrameworkSessionInfo sessionInfo, UserAccount userAccount) throws Exception;
    
    public void setSessionInfoToCache(FrameworkSessionInfo frameworkSessionInfo) throws Exception;
    
    public FrameworkSessionInfo getSessionInfo(String frameworkSessionId) throws Exception;
    
    public Boolean verifyAuthenticationToken(String frameworkSessionId, String targetToken) throws Exception;
    
    public UserAccount getUserAccountObjectFromJwt(FrameworkSessionInfo sessionInfo);
    
    public void evictSessionInfoFromCache(String frameworkSessionId);
}
