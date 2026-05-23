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
public class IfaSelfInspectItemManageSql003ResponseModel {

    /** 自己点検項目ID */
    private String selfCheckItemId;

    /** 登録年月 */
    private String registerDate;

    /** 自己点検項目名 */
    private String selfAssessmentItemName;

    /** 表示フラグ */
    private String displayFlag;

    /** ソート番号 */
    private String sortNumber;

    /** 確認初期値 */
    private String confirmationInit;

    /** 登録日時 */
    private String createDate;

    /** 登録者 */
    private String createBy;

    /** 更新日時 */
    private String updateDate;

    /** 更新者 */
    private String updateBy;

    /** 業者区分 */
    private String brokerType;

    /** 回答 */
    private String answer;

    /** 理由必須フラグ */
    private String reasonRequiredFlag;

    /** 削除可能フラグ */
    private String deletableFlag;

    /** 変更フラグ */
    private String changedFlag;

}
