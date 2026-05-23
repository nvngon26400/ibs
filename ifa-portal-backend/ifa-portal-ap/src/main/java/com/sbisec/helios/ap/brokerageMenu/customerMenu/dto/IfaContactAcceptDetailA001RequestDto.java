package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 接触履歴受付詳細 A001リクエストDto
 * 
 * @author 趙韫慧
 *
 */
@Data
public class IfaContactAcceptDetailA001RequestDto {

    /** 参照元履歴区分 */
    private String referenceKbn;

    /** 記録日 */
    private String recordDateYmd;

    /** 大分類 */
    private String bigClass;

    /** 記録日時 */
    private String recordDate;

    /** 内容 */
    private String contents;

    /** 作成者 */
    private String createUser;

    /** 枝番 */
    private String edaban;

    /** 詳細区分 */
    private String shousaiKbn;
}
