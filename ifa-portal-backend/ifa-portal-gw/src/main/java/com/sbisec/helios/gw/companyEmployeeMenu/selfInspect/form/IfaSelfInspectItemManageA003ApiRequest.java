package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA003ApiRequest {

    /** 登録年月 */
    @DateTimeFormat(pattern = "yyyyMM")
    @JsonFormat(pattern = "yyyyMM")
    @NotEmpty(message = "登録年月")
    @Size(min = 6, max = 6, message = "登録年月")
    private String assignMonthList;

    /** 業者区分（全角半角） */
    @NotEmpty(message = "業者区分")
    @Size(max = 20, message = "業者区分")
    private String brokerType;

}
