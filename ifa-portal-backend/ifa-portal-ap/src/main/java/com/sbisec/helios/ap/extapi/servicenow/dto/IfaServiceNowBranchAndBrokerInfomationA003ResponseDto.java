package com.sbisec.helios.ap.extapi.servicenow.dto;

import java.util.List;

import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowBrokerDto;
import com.sbisec.helios.ap.extapi.servicenow.dto.common.IfaServiceNowDataList;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 仲介業社支店一覧取得 レスポンスDto
 *
 * @author SCSK
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class IfaServiceNowBranchAndBrokerInfomationA003ResponseDto extends IfaServiceNowDataList {
    
    /** 仲介業者支店リスト. */
    private List<IfaServiceNowBrokerDto> subBrokerList;
    
}
