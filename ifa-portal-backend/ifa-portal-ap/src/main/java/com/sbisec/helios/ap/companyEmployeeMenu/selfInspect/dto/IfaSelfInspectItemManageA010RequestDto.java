package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto;

import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageA010RequestDto {

    /** 自己点検項目ID（数字） */
    private String selfCheckItemId;

    /** 業者区分（全角半角） */
    private String brokerType;

}
