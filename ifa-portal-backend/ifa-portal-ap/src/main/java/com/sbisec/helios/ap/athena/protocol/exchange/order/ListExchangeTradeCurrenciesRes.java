package com.sbisec.helios.ap.athena.protocol.exchange.order;

import java.util.List;

import lombok.Data;

@Data
public class ListExchangeTradeCurrenciesRes {
    
    /**レート情報*/
    private List<Currencies> currencies;
    
    @Data
    public static class Currencies {
        
        //通貨アイテム.通貨コード
        public String currencyCode;
        
        //通貨アイテム.通貨名
        public String currencyName;
        
        //通貨アイテム.小数部有効桁数
        public int decimalLength;
        
    }
    
}
