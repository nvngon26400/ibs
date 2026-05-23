package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
@Data
public class IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ApiRequest {
    
    /** 登録年月. */
    @NotEmpty(message = "登録年月")
    private String registerDate;
    
    /** 自己点検リスト. */
    @NotEmpty(message = "自己点検リスト")
    private List<IfaSelfInspectBlotterReplyConfirmReplyReasonInputSelfAssessmentRequest> selfAssessmentList;
}
