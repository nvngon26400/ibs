package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaShortPositionDividendAdjustDetailA001ResponseDtoShortPositionDividendAdjustDetail {
    
    /** 銘柄コード. */
    private String brandCode;
    
    /** 銘柄名. */
    private String brandName;
    
    /** 予想配当. */
    private String expectedDividend;
    
    /** 株数. */
    private String stockQuantity;
    
    /** 拘束金. */
    private String restraintAmount;
    
    /** 権利付最終日. */
    private String rightLastDate;
}
