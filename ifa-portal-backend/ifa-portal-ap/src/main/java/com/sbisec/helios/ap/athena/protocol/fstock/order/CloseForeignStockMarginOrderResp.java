package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.ArrayList;
import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrder;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式信用返済注文登録API Response.
 * 
 * @author yunhui.zhao
 * @date: 03/09/2022
 */
@Data
public class CloseForeignStockMarginOrderResp {
    
    // 信用注文情報
    private MarginOrder marginOrder;
    
    // 見積価格
    private String estimatePrice;
    
    // 信用建余力（注文後）
    private String marginBuyingPower;
    
    // 概算諸経費合計
    private String estimateTotalExpenses;
    
    // 委託保証金率（注文後預託率）
    private String depositRate;
    
    /**注文ワーニングステータス*/
    List<String> warningStatuses = new ArrayList<String>();
    
}
