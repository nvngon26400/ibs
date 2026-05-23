package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 投信詳細情報 SQL006応答
 *
 * @author SCSK
 *
 */
@Data
public class IfaMutualFundDetailInfoSql006ResponseModel {
    
    /** 更新日(反映開始日時). */
    private String fbmStartTime;
    
    /** 編集文章. */
    private String fbmBody;
    
}
