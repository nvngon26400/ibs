package com.sbisec.helios.ap.athena.protocol.fstock.order;

import com.sbisec.helios.ap.athena.protocol.BaseRequest;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderInput;

import lombok.Data;

/**
 * 外国株式現物注文登録 Request
 * 
 * @author SCSK 笹倉 秀行
 * @date 02/15/2024
 */
@Data
public class CreateForeignStockOrderReq implements BaseRequest {
    
    private Header header = new Header();
    
    private Parameter parameter = new Parameter();
    
    @Data
    public class Header {
        
        // {部店}3桁 + "-" + {口座}7桁
        private String token;
        
        // 取引パスワード
        private String trade_password;
        
        // Request Id
        private String request_id;
        
        // チャネル
        private String channel;
    }
    
    @Data
    public class Parameter {
        
        /** 国コード */
        private String countryCode;
        
        /** 市場コード */
        private String marketCode;
        
        /** 銘柄コード */
        private String securitiesCode;
        
        /** 売買区分 */
        private String buySellCode;
        
        /** 注文数量 */
        private String orderQuantity;
        
        /** 価格条件 */
        private String orderPriceKindCode;
        
        /** 注文単価 */
        private String orderPrice;
        
        /** 発火条件価格 */
        private String stopPrice;
        
        /** トレールストップ幅 */
        private String trailingStopAmount;
        
        /** 成行基準価格 */
        private String noLimitPrice;
        
        /** 期間条件 */
        private String orderLimitCode;
        
        /** 期間 "yyyy-MM-dd"形式 */
        private String orderTerm;
        
        /** 預り区分 */
        private String specificAccountCode;
        
        /** 決済方法 */
        private String settlementMethodCode;
    }
}
