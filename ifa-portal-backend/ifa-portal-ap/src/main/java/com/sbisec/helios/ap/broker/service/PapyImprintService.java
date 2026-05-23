package com.sbisec.helios.ap.broker.service;

import com.sbisec.helios.ap.broker.protocol.GetImageForIFAResp;

public interface PapyImprintService {
    
    public GetImageForIFAResp getImprintImage(String ifaIdentifier, String butenCode, String accountNumber)
            throws Exception;
}
