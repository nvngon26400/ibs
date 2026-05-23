package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class IfaForeignMarginTradeRepayOrderInputA012Api_positionDesignationAreaIndividualPositionInfoList {
    
    /** 個別建玉情報一覧.期限（個別）. */
    @NotEmpty(message = "期限（個別）")
    private String marginDueDate;
    
    /** 個別建玉情報一覧.国内新規約定日. */
    @NotEmpty(message = "国内新規約定日")
    private String domesticTradeDate;
    
    /** 個別建玉情報一覧.現地新規約定日. */
    @NotEmpty(message = "現地新規約定日")
    private String foreignTradeDate;
    
    /** 個別建玉情報一覧.建単価. */
    @NotEmpty(message = "建単価")
    private String sinyoPreviousDayValue;
    
    /** 個別建玉情報一覧.数量. */
    @NotEmpty(message = "数量")
    private String quantity;
    
    /** 個別建玉情報一覧.注文数量. */
    @NotEmpty(message = "注文数量")
    private String orderCount;
    
}
