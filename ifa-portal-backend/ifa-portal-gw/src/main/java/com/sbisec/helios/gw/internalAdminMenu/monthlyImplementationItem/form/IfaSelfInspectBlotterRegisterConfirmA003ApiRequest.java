package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@Data
public class IfaSelfInspectBlotterRegisterConfirmA003ApiRequest {
    
    /** 登録年月. */
    @NotEmpty(message = "登録年月")
    private String registerDate;
    
    /** 自己点検リスト. */
    @NotEmpty(message = "自己点検リスト")
    private List<IfaSelfInspectBlotterRegisterConfirmSelfAssessmentRequest> selfAssessmentList;
    
}
