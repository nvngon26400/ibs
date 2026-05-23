package com.sbisec.helios.ap.extapi.servicenow.service.impl;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.common.util.ServiceNowUtil;
import com.sbisec.helios.ap.extapi.servicenow.dao.IfaServiceNowBranchAndBrokerInfomationDao;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004ResponseDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBranchDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowEmployeeDto;
import com.sbisec.helios.ap.extapi.servicenow.service.IfaServiceNowBranchAndBrokerInfomationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * cmpIfaServiceNowBranchAndBrokerInfomationService サービス
 *
 * @author SCSK
 */
@Component(value = "cmpIfaServiceNowBranchAndBrokerInfomationService")
public class IfaServiceNowBranchAndBrokerInfomationServiceImp implements IfaServiceNowBranchAndBrokerInfomationService {
    
    @Autowired
    private IfaServiceNowBranchAndBrokerInfomationDao dao;
    
    /**
     * 本店・支店情報取得
     */
    @Override
    public IfaServiceNowBranchAndBrokerInfomationA001ResponseDto invokeA001(
            IfaServiceNowBranchAndBrokerInfomationA001RequestDto dtoReq) throws Exception {
        
        List<IfaServiceNowBranchDto> res = dao.selectA001Branch(dtoReq);
        
        IfaServiceNowBranchAndBrokerInfomationA001ResponseDto dtoRes = new IfaServiceNowBranchAndBrokerInfomationA001ResponseDto();
        dtoRes.setBranchList(res);
        
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
    /**
     * SBI証券本支店の仲介業者支店を取得
     */
    @Override
    public IfaServiceNowBranchAndBrokerInfomationA002ResponseDto invokeA002(
            IfaServiceNowBranchAndBrokerInfomationA002RequestDto dtoReq) throws Exception {
        
        List<IfaServiceNowBrokerDto> res = dao.selectA002Broker(dtoReq);
        
        IfaServiceNowBranchAndBrokerInfomationA002ResponseDto dtoRes = new IfaServiceNowBranchAndBrokerInfomationA002ResponseDto();
        dtoRes.setBrokerList(res);
        
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
    /**
     * 仲介業社支店一覧取得
     */
    @Override
    public IfaServiceNowBranchAndBrokerInfomationA003ResponseDto invokeA003(
            IfaServiceNowBranchAndBrokerInfomationA003RequestDto dtoReq) throws Exception {
        
        List<IfaServiceNowBrokerDto> res = dao.selectA003Broker(dtoReq);
        
        IfaServiceNowBranchAndBrokerInfomationA003ResponseDto dtoRes = new IfaServiceNowBranchAndBrokerInfomationA003ResponseDto();
        dtoRes.setSubBrokerList(res);
        
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
    /**
     * 営業員一覧を取得
     */
    @Override
    public IfaServiceNowBranchAndBrokerInfomationA004ResponseDto invokeA004(
            IfaServiceNowBranchAndBrokerInfomationA004RequestDto dtoReq) throws Exception {
        
        List<IfaServiceNowEmployeeDto> res = dao.selectA004Employee(dtoReq);
        
        IfaServiceNowBranchAndBrokerInfomationA004ResponseDto dtoRes = new IfaServiceNowBranchAndBrokerInfomationA004ResponseDto();
        dtoRes.setEmployeeList(res);
        
        ServiceNowUtil.createDataListServiceNow(dtoRes, 0);
        
        return dtoRes;
    }
    
}
