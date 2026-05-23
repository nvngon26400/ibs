package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Data
public class IfaSelfInspectBlotterSql005RequestModel {
    
    /** 表示年月. */
    private String assignMonth;
    
    /** 仲介業者コード. */
    private String brokerCode;
}
