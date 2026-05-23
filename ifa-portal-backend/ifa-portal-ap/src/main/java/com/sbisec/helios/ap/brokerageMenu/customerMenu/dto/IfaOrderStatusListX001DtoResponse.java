package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListX001DtoResponse {
    
    /** 取引停止フラグ. */
    private String tradeSuspendFlag;
    
    /** 媒介可否リスト. */
    private List<IfaOrderStatusListDtoResponseIntermediaryValue> intermediaryValueList;
    
    /** 国内株式注文リスト. */
    private List<IfaOrderStatusListDtoResponseDomesticStockOrder> domesticStockOrderList;
    
    /** 国内株式注文取得件数（算出）. */
    private String domesticStockOrderCount;
    
    /** 国内投信注文リスト. */
    private List<IfaOrderStatusListDtoResponseDomesticMutualFundOrderStatus> domesticMutualFundOrderStatusList;
    
    /** 国内投信注文取得件数（算出）. */
    private String domesticMutualFundOrderCount;
    
    /** 募集注文リスト. */
    private List<IfaOrderStatusListDtoResponseSubscriptOrderStatus> subscriptOrderStatusList;
    
    /** 募集注文取得件数（算出）. */
    private String subscriptOrderStatusCount;
    
    /** 国別リスト. */
    private List<IfaOrderStatusListDtoResponseForeignStockEntrustOrder> foreignStockEntrustOrder;
    
    /** 外株委託注文総件数（算出）. */
    private String fsEntrustOrderStatusTotalCount;
    
    /** 店頭取引注文リスト. */
    private List<IfaOrderStatusListDtoResponseFsStoreOrderStatus> fsStoreOrderStatusList;
    
    /** 店頭取引注文取得件数（算出）. */
    private String foreignStockCounterOrderCount;
    
    /** コールセンタ認証ID. */
    private String CcsAuthId;
    
}
