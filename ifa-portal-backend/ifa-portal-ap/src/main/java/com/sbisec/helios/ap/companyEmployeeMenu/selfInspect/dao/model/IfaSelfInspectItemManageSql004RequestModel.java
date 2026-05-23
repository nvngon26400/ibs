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
public class IfaSelfInspectItemManageSql004RequestModel {

    /** 自己点検項目ID（数字） */
    private String selfCheckItemId;

    /** 自己点検項目名 */
    private String selfAssessmentItemName;

    /** 表示フラグ */
    private String displayFlag;

    /** ソート番号 */
    private String sortNumber;

    /** 確認初期値 */
    private String confirmationInit;

    /** 登録者/更新者 */
    private String userId;

    /** 登録年月 */
    private String registerDate;

    /** 業者区分（全角半角） */
    private String brokerType;

    /** 回答（半角英数字） */
    private String answer;

    /** 理由必須フラグ */
    private String reasonRequiredFlag;

}
