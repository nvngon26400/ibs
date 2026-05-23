package com.sbisec.helios.ap.testtool2.service;

import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.testtool2.service.dto.ApiTestServiceRequest;

/**
 * API テスト用サービス
 *
 */
public interface ApiTestDriverService extends Service {
    
    public Object doApiTest(ApiTestServiceRequest request) throws Exception;
}
