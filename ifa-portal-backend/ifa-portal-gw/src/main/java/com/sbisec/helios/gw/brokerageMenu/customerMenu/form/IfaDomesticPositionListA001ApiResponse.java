package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * _国内建玉一覧A001リスポンス
 *
 * @author SCSK 金志
 * 
 */
@Data
public class IfaDomesticPositionListA001ApiResponse {
    
    /** 検索結果件数（数値(整数)）. */
    private String searchResultCount;
    
    /** 建玉金額合計（数値(整数)）. */
    private String totalPrice;
    
    /** 評価額合計（前日）. */
    private String valuationTotalPreviousDay;
    
    /** 評価額合計（リアル）. */
    private String valuationTotalReal;
    
    /** 諸経費合計（数値(整数)）. */
    private String costTotalYen15;
    
    /** 評価損益合計（前日）. */
    private String domesticPositionValuationTotalPreviousDay;
    
    /** 評価損益合計（リアル）. */
    private String domesticPositionValuationTotalReal;
    
    /** 建玉一覧リスト. */
    private List<PositionList> positionList;
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 媒介可否リスト. */
    private List<IntermediaryValue> intermediaryValueList;
    
}
