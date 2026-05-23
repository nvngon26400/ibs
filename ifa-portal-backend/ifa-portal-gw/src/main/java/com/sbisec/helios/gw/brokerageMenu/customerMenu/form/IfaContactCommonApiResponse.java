package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 接触履歴 A001 A002Apiレスポンス
 * 
 * @author 趙韫慧
 * 
 */
@Data
public class IfaContactCommonApiResponse {

    /** 大分類 */
    private String bigClass;

    /** 中分類 */
    private String midClass;

    /** 記録日時 */
    private String recordDate;

    /** ステータス */
    private String status;

    /** 内容 */
    private String contents;

    /** 詳細 */
    private String details;

    /** 担当者名 */
    private String chargeName;

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 作成者 */
    private String createUser;

    /** 参照元履歴区分 */
    private String referenceKbn;

    /** 回答IFA問合せNO */
    private String ansIfaToiawaseNo;

    /** 枝番 */
    private String edaban;

    /** 詳細区分 */
    private String shousaiKbn;
}
