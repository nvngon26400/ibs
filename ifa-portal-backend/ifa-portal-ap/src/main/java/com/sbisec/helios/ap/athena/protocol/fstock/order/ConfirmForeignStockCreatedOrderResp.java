package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.common.PriceData;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文確認API レスポンス.
 *
 * @author SCSK 宇田川達弥
 * @date 2024/02/16
 */
@Data
public class ConfirmForeignStockCreatedOrderResp {
    
    /** 注文情報 */
    private Order order;
    
    /** 見積価格 */
    private String estimatePrice;
    
    /** 買付余力（注文後） */
    private String buyPossibleAmount;
    
    /** NISA投資可能枠（注文後） */
    private String nisaBuyLimitAmount;
    
    /** 売却可能数（注文後） */
    private String sellPossibleQuantity;
    
    /** 注文ワーニングステータス */
    private List<String> warningStatuses;
    
    /** 価格情報 */
    private PriceData stockPrice;
}
