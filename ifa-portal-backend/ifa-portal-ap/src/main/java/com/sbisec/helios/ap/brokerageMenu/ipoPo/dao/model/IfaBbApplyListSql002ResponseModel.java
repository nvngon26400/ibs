package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model;

import lombok.Data;

/**
*
* @author BASE李
*
*/
@Data
public class IfaBbApplyListSql002ResponseModel {

    /** IFAのブックビルディング申込期間（開始）. */
    private String presentationFrom;

    /** IFAのブックビルディング申込期間（終了）. */
    private String presentationTo;

    /** 緊急入力停止. */
    private String urgentStop;

}
