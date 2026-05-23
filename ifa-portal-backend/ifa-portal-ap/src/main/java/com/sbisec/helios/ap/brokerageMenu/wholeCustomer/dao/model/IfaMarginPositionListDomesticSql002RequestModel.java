package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticSql002RequestModel {
    
    /** 銘柄コード. */
    private String brandCode;
    
    /** 銘柄コード. */
    //A002b内のSQL002実行時に使用
    private String reqBrandCode;
    
    /** SQL処理タイミング. */
    private String sqlSyori;
  
}
