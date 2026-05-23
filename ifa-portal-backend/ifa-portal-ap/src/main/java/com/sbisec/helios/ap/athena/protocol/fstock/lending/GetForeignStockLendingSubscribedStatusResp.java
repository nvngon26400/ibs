package com.sbisec.helios.ap.athena.protocol.fstock.lending;

import lombok.Data;

/**
 * 外国株式貸株サービス - 外国株式貸株サービス加入判定API レスポンス.
 *
 * @author SCSK川崎
 * @date 03/26/2024
 */
@Data
public class GetForeignStockLendingSubscribedStatusResp {
    
    /** 加入済み。true：加入済み／false：加入なし */
    private Boolean subscribed;
    
}
