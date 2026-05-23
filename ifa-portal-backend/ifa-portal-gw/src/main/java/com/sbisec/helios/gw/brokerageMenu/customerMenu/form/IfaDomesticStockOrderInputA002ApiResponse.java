package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 国内株式注文入力A002レスポンスDTO
 *
 * @author SCSK
 * 
 */
@Data
public class IfaDomesticStockOrderInputA002ApiResponse {
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 売却可能数量（数値(整数)）. */
    private String acPosition;
    
    /** 営業日リスト. */
    private List<Date> businessDayList;
    
}
