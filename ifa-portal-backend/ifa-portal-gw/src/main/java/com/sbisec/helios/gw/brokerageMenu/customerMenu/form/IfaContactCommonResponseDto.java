package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 接触履歴 A002Apiレスポンス

 * @author 趙韫慧
 *
 */
@Data
public class IfaContactCommonResponseDto {

    /** 枝番 */
    private String branch;

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
}
