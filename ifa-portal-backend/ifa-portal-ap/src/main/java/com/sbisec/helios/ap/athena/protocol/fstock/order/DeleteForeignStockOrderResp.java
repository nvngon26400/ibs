package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;

import lombok.Data;

/**
 * 外国株式取引サービス - 外国株式現物注文取消初期情報取得API レスポンス.
 *
 * @author SCSK 宇田川達弥
 * @date 2024/04/01
 */
@Data
public class DeleteForeignStockOrderResp {
    
    /** 注文情報 */
    private Order order;
}
