
package com.sbisec.helios.ap.sss.service;

import com.sbisec.helios.ap.sss.protocol.ConnectSssResp;

public interface SssConnectionService  {
	
	public ConnectSssResp getSssCertificationInfo(String userId, String apiKey, String sssType) throws Exception;
}
