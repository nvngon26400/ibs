package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA012Dto_positionDesignationAreaIndividualPositionInfoList {
    
    /** 個別建玉情報一覧.期限（個別）. */
    private String marginDueDate;
    
    /** 個別建玉情報一覧.国内新規約定日. */
    private String domesticTradeDate;
    
    /** 個別建玉情報一覧.現地新規約定日. */
    private String foreignTradeDate;
    
    /** 個別建玉情報一覧.建単価. */
    private String sinyoPreviousDayValue;
    
    /** 個別建玉情報一覧.数量. */
    private String quantity;
    
    /** 個別建玉情報一覧.注文数量. */
    private String orderCount;
    
}
