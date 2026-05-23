package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA009RequestDto {

    /** 自己点検項目リスト */
    private List<IfaSelfInspectItemManageSelfAssessmentItemDto> selfAssessmentItemList;

    /** 登録年月 */
    private String assignMonthList;

    /** 業者区分（全角半角） */
    private String brokerType;
    
    /** 業者区分リスト */
    private List<IfaSelfInspectItemManageBrokerTypeDto> brokerTypeList;

}
