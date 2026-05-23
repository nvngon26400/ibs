package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0506_02-01
 * 画面名：自己点検項目管理
 * 2024/06/19 新規作成
 *
 * @author SCSK 江口
 */
@Data
public class IfaSelfInspectItemManageSql002ResponseModel {

    /** 業者区分 */
    private String brokerType;

    /** 区分名称 */
    private String classificationName;

}
