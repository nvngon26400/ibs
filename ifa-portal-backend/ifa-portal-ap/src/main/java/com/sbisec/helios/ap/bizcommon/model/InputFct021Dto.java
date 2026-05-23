package com.sbisec.helios.ap.bizcommon.model;

import lombok.Data;

/**
 * 共通関数DTO：FCT021
 *
 * @author SCSK
 */

@Data
public class InputFct021Dto {
    
    //部店コード
    private String butenCode;
    
    //口座番号
    private String accountNumber;
    
    //証券金銭種別
    private String productCd;
    
    //取引種別
    private String tradeCd;
    
    //国籍コード
    private String countryCd;
    
    //通貨コード
    private String currencyCode;
    
    //選択市場
    private String tradeRestrictChkMarket;
    
    //弁済期限
    private String paymentLimit;
    
}
