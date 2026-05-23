package com.sbisec.helios.ap.common.service;

import com.sbisec.helios.ap.service.Service;

public interface IfaAliveMonitorService extends Service {
    
    public long watch(String dummy) throws Exception;
}
