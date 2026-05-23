package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Data
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto {
    
    /** 登録年月. */
    private String registerDate;
    
    /** 自己点検リスト. */
    private List<IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessment> selfAssessmentList;
    
}
