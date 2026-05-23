package com.sbisec.helios.gw.internalAdminMenu.monthlyImplementationItem.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 * 2024/07/31 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectBlotterA004ApiRequest {
    
    /** 登録年月 */
    @NotEmpty(message = "登録年月")
    private String registerDate;
    
    /** 自己点検リスト */
    @NotEmpty(message = "自己点検リスト")
    private List<IfaSelfInspectBlotterSelfAssessmentRequest> selfAssessmentList;
    
}
