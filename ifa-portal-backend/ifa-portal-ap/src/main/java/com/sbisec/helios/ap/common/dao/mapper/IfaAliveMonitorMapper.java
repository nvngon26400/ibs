package com.sbisec.helios.ap.common.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IfaAliveMonitorMapper {
    
    public void watch() throws Exception;
    
}
