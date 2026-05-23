package com.sbisec.helios.ap.athena.protocol.fstock.order;

import java.util.List;

import com.sbisec.helios.ap.athena.protocol.fstock.dto.Order;

import lombok.Data;

/**
 * 外国株式現物注文登録 Response
 * 
 * @author SCSK 笹倉 秀行
 * @date 02/15/2024
 */
@Data
public class CreateForeignStockOrderResp {
    
    /** 国コード */
    private Order order;
    
    /** 銘柄コード */
    private List<String> warningStatuses;
}
