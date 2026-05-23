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
public class IfaSelfInspectItemManageRegisterDateDto {

    /** 登録年月(YYYYMM) */
    private String dateId;

    /** 登録年月(YYYY年MM月) */
    private String dateName;

}
