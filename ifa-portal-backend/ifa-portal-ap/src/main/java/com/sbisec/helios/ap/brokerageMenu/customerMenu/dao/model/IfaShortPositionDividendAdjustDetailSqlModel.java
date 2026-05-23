package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 売建配当調整金明細SQL001リクエスト.
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaShortPositionDividendAdjustDetailSqlModel {
    
    /** 銘柄コード. */
    private String brandCd;
    
    /** 銘柄名. */
    private String brandName;
    
    /** 予想配当. */
    private String unitAmount;
    
    /** 株数. */
    private String quantity;
    
    /** 拘束金. */
    private String lockAmount;
    
    /** 拘束解除日. */
    private String lostRightDate;
}
