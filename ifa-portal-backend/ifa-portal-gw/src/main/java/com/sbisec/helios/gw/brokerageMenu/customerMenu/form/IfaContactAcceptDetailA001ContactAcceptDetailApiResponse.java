package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 接触履歴受付詳細 A001レスポンスDto
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactAcceptDetailA001ContactAcceptDetailApiResponse {

    /** 受付シート */
    private String acceptSheetNo;

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
}
