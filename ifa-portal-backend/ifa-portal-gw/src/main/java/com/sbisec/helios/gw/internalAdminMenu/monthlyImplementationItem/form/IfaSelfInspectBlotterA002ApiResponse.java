package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Data
public class IfaSelfInspectBlotterA002ApiResponse {
    
    /** 確認日. */
    private String confirmationDate;
    
    /** 登録年月リスト. */
    private List<IfaSelfInspectBlotterRegisterDate> registerDateList;
    
    /** 自己点検リスト. */
    private List<IfaSelfInspectBlotterSelfAssessmentResponse> selfAssessmentList;
    
}
