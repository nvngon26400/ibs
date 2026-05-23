package com.sbisec.helios.ap.common.dao.impl;

import com.sbisec.helios.ap.common.dao.IfaAliveMonitorDao;
import com.sbisec.helios.ap.common.dao.mapper.IfaAliveMonitorMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IfaAliveMonitorDaoImpl implements IfaAliveMonitorDao {
    
    @Autowired
    private IfaAliveMonitorMapper mapper;
    
    @Override
    public void watch() throws Exception {
        
        mapper.watch();
    }
}
