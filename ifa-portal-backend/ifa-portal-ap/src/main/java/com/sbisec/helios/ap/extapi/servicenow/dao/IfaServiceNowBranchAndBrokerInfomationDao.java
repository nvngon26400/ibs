package com.sbisec.helios.ap.extapi.servicenow.dao;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA001RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA002RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA003RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.IfaServiceNowBranchAndBrokerInfomationA004RequestDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBranchDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowEmployeeDto;

/**
 * IfaServiceNowBranchAndBrokerInfomationDao
 *
 * @author SCSK
 */
public interface IfaServiceNowBranchAndBrokerInfomationDao {
    
    /**
     * 本店・支店情報取得
     */
    public List<IfaServiceNowBranchDto> selectA001Branch(IfaServiceNowBranchAndBrokerInfomationA001RequestDto req)
            throws Exception;
    
    /**
     * SBI証券本支店の仲介業者支店を取得
     */
    public List<IfaServiceNowBrokerDto> selectA002Broker(IfaServiceNowBranchAndBrokerInfomationA002RequestDto req)
            throws Exception;
    
    /**
     * 仲介業社支店一覧取得
     */
    public List<IfaServiceNowBrokerDto> selectA003Broker(IfaServiceNowBranchAndBrokerInfomationA003RequestDto req)
            throws Exception;
    
    /**
     * 営業員一覧を取得
     */
    public List<IfaServiceNowEmployeeDto> selectA004Employee(IfaServiceNowBranchAndBrokerInfomationA004RequestDto req)
            throws Exception;
    
}
