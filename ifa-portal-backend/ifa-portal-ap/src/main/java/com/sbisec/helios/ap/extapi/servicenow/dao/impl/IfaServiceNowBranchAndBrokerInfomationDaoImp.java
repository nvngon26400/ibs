package com.sbisec.helios.ap.extapi.servicenow.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowBranchAndBrokerInfomationDao;
import com.sbisec.helios.ap.extapi.servicenow.dao.mapper.IfaServiceNowBranchAndBrokerInfomationMapper;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBranchDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowEmployeeDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * IfaServiceNowBranchAndBrokerInfomationDaoImp
 *
 * @author SCSK
 */
@Component
public class IfaServiceNowBranchAndBrokerInfomationDaoImp extends RowSelectableDao implements IfaServiceNowBranchAndBrokerInfomationDao {
    
    @Autowired
    private IfaServiceNowBranchAndBrokerInfomationMapper mapper;
    
    @Override
    public List<IfaServiceNowBranchDto> selectA001Branch(
            IfaServiceNowBranchAndBrokerInfomationA001RequestDto req) throws Exception {
        
        List<IfaServiceNowBranchDto> res = mapper.selectA001Branch(req);
        return res;
    }
    
    @Override
    public List<IfaServiceNowBrokerDto> selectA002Broker(
            IfaServiceNowBranchAndBrokerInfomationA002RequestDto req) throws Exception {
        
        List<IfaServiceNowBrokerDto> res = mapper.selectA002Broker(req);
        return res;
    }
    
    @Override
    public List<IfaServiceNowBrokerDto> selectA003Broker(
            IfaServiceNowBranchAndBrokerInfomationA003RequestDto req) throws Exception {
        
        List<IfaServiceNowBrokerDto> res = mapper.selectA003Broker(req);
        return res;
    }
    
    @Override
    public List<IfaServiceNowEmployeeDto> selectA004Employee(
            IfaServiceNowBranchAndBrokerInfomationA004RequestDto req) throws Exception {

        List<IfaServiceNowEmployeeDto> res = mapper.selectA004Employee(req);
        
        return res;
    }
    
}
