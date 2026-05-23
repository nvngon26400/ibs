package jp.co.sbisec.pcenter.dto.heracross;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RealQuoteSnapshot {
    
    @lombok.Getter(onMethod = @__(@JsonProperty("marketCode")))
    @lombok.Setter(onMethod = @__(@JsonProperty("marketCode")))
    private String marketCode;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("productCode")))
    @lombok.Setter(onMethod = @__(@JsonProperty("productCode")))
    private String productCode;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("curPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("curPrice")))
    private String curPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("priceChange")))
    @lombok.Setter(onMethod = @__(@JsonProperty("priceChange")))
    private String priceChange;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("highPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("highPrice")))
    private String highPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("lowPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("lowPrice")))
    private String lowPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("curPriceDate")))
    @lombok.Setter(onMethod = @__(@JsonProperty("curPriceDate")))
    private String curPriceDate;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("curPriceTime")))
    @lombok.Setter(onMethod = @__(@JsonProperty("curPriceTime")))
    private String curPriceTime;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("openPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("openPrice")))
    private String openPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("lastClosingPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("lastClosingPrice")))
    private String lastClosingPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("volume")))
    @lombok.Setter(onMethod = @__(@JsonProperty("volume")))
    private String volume;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("ytdHighPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("ytdHighPrice")))
    private String ytdHighPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("ytdLowPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("ytdLowPrice")))
    private String ytdLowPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("openPriceTime")))
    @lombok.Setter(onMethod = @__(@JsonProperty("openPriceTime")))
    private String openPriceTime;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("highPriceTime")))
    @lombok.Setter(onMethod = @__(@JsonProperty("highPriceTime")))
    private String highPriceTime;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("lowPriceTime")))
    @lombok.Setter(onMethod = @__(@JsonProperty("lowPriceTime")))
    private String lowPriceTime;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("volTime")))
    @lombok.Setter(onMethod = @__(@JsonProperty("volTime")))
    private String volTime;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice")))
    private String bidPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice")))
    private String askPrice;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize")))
    private String bidSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize")))
    private String askSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice1")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice1")))
    private String bidPrice1;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice2")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice2")))
    private String bidPrice2;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice3")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice3")))
    private String bidPrice3;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice4")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice4")))
    private String bidPrice4;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice5")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice5")))
    private String bidPrice5;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice6")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice6")))
    private String bidPrice6;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice7")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice7")))
    private String bidPrice7;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice8")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice8")))
    private String bidPrice8;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice9")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice9")))
    private String bidPrice9;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidPrice10")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidPrice10")))
    private String bidPrice10;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice1")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice1")))
    private String askPrice1;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice2")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice2")))
    private String askPrice2;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice3")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice3")))
    private String askPrice3;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice4")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice4")))
    private String askPrice4;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice5")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice5")))
    private String askPrice5;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice6")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice6")))
    private String askPrice6;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice7")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice7")))
    private String askPrice7;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice8")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice8")))
    private String askPrice8;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice9")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice9")))
    private String askPrice9;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askPrice10")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askPrice10")))
    private String askPrice10;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize1")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize1")))
    private String bidSize1;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize2")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize2")))
    private String bidSize2;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize3")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize3")))
    private String bidSize3;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize4")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize4")))
    private String bidSize4;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize5")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize5")))
    private String bidSize5;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize6")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize6")))
    private String bidSize6;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize7")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize7")))
    private String bidSize7;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize8")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize8")))
    private String bidSize8;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize9")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize9")))
    private String bidSize9;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("bidSize10")))
    @lombok.Setter(onMethod = @__(@JsonProperty("bidSize10")))
    private String bidSize10;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize1")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize1")))
    private String askSize1;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize2")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize2")))
    private String askSize2;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize3")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize3")))
    private String askSize3;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize4")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize4")))
    private String askSize4;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize5")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize5")))
    private String askSize5;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize6")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize6")))
    private String askSize6;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize7")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize7")))
    private String askSize7;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize8")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize8")))
    private String askSize8;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize9")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize9")))
    private String askSize9;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("askSize10")))
    @lombok.Setter(onMethod = @__(@JsonProperty("askSize10")))
    private String askSize10;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("marketCap")))
    @lombok.Setter(onMethod = @__(@JsonProperty("marketCap")))
    private String marketCap;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("curPriceTick")))
    @lombok.Setter(onMethod = @__(@JsonProperty("curPriceTick")))
    private String curPriceTick;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("turnoverAmount")))
    @lombok.Setter(onMethod = @__(@JsonProperty("turnoverAmount")))
    private String turnoverAmount;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("curPriceTone")))
    @lombok.Setter(onMethod = @__(@JsonProperty("curPriceTone")))
    private String curPriceTone;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("lastClosingPriceDate")))
    @lombok.Setter(onMethod = @__(@JsonProperty("lastClosingPriceDate")))
    private String lastClosingPriceDate;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("perChange")))
    @lombok.Setter(onMethod = @__(@JsonProperty("perChange")))
    private String perChange;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("mBidTone")))
    @lombok.Setter(onMethod = @__(@JsonProperty("mBidTone")))
    private String mBidTone;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("mAskTone")))
    @lombok.Setter(onMethod = @__(@JsonProperty("mAskTone")))
    private String mAskTone;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("vwap")))
    @lombok.Setter(onMethod = @__(@JsonProperty("vwap")))
    private String vwap;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("impAskSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("impAskSize")))
    private String impAskSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("impBidSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("impBidSize")))
    private String impBidSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("ytdHighPriceDate")))
    @lombok.Setter(onMethod = @__(@JsonProperty("ytdHighPriceDate")))
    private String ytdHighPriceDate;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("ytdLowPriceDate")))
    @lombok.Setter(onMethod = @__(@JsonProperty("ytdLowPriceDate")))
    private String ytdLowPriceDate;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("buyMarginNc")))
    @lombok.Setter(onMethod = @__(@JsonProperty("buyMarginNc")))
    private String buyMarginNc;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("sellMarginNc")))
    @lombok.Setter(onMethod = @__(@JsonProperty("sellMarginNc")))
    private String sellMarginNc;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("buyMargin")))
    @lombok.Setter(onMethod = @__(@JsonProperty("buyMargin")))
    private String buyMargin;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("sellMargin")))
    @lombok.Setter(onMethod = @__(@JsonProperty("sellMargin")))
    private String sellMargin;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("combineAskSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("combineAskSize")))
    private String combineAskSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("combineBidSize")))
    @lombok.Setter(onMethod = @__(@JsonProperty("combineBidSize")))
    private String combineBidSize;
    
    @lombok.Getter(onMethod = @__(@JsonProperty("orderUnit")))
    @lombok.Setter(onMethod = @__(@JsonProperty("orderUnit")))
    private String orderUnit;
    
    public RealQuoteSnapshot(String marketCode, String productCode, String curPrice,
            String priceChange, String highPrice, String lowPrice, String curPriceDate, String curPriceTime,
            String openPrice, String lastClosingPrice, String volume, String ytdHighPrice, String ytdLowPrice,
            String openPriceTime, String highPriceTime, String lowPriceTime, String volTime, String bidPrice,
            String askPrice, String bidSize, String askSize, String bidPrice1, String bidPrice2, String bidPrice3,
            String bidPrice4, String bidPrice5, String askPrice1, String askPrice2, String askPrice3, String askPrice4,
            String askPrice5, String bidSize1, String bidSize2, String bidSize3, String bidSize4, String bidSize5,
            String askSize1, String askSize2, String askSize3, String askSize4, String askSize5, String bidPrice6,
            String bidPrice7, String bidPrice8, String bidPrice9, String bidPrice10, String askPrice6, String askPrice7,
            String askPrice8, String askPrice9, String askPrice10, String bidSize6, String bidSize7, String bidSize8,
            String bidSize9, String bidSize10, String askSize6, String askSize7, String askSize8, String askSize9,
            String askSize10, String marketCap, String curPriceTick, String turnoverAmount, String curPriceTone,
            String lastClosingPriceDate, String perChange, String mBidTone, String mAskTone, String vwap,
            String impAskSize, String impBidSize, String ytdHighPriceDate, String ytdLowPriceDate, String buyMarginNc,
            String sellMarginNc, String buyMargin, String sellMargin, String combineAskSize, String combineBidSize,
            String orderUnit) {
        
        this.marketCode = marketCode;
        this.productCode = productCode;
        this.curPrice = curPrice;
        this.priceChange = priceChange;
        this.highPrice = highPrice;
        this.lowPrice = lowPrice;
        this.curPriceDate = curPriceDate;
        this.curPriceTime = curPriceTime;
        this.openPrice = openPrice;
        this.lastClosingPrice = lastClosingPrice;
        this.volume = volume;
        this.ytdHighPrice = ytdHighPrice;
        this.ytdLowPrice = ytdLowPrice;
        this.openPriceTime = openPriceTime;
        this.highPriceTime = highPriceTime;
        this.lowPriceTime = lowPriceTime;
        this.volTime = volTime;
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.bidSize = bidSize;
        this.askSize = askSize;
        this.bidPrice1 = bidPrice1;
        this.bidPrice2 = bidPrice2;
        this.bidPrice3 = bidPrice3;
        this.bidPrice4 = bidPrice4;
        this.bidPrice5 = bidPrice5;
        this.askPrice1 = askPrice1;
        this.askPrice2 = askPrice2;
        this.askPrice3 = askPrice3;
        this.askPrice4 = askPrice4;
        this.askPrice5 = askPrice5;
        this.bidSize1 = bidSize1;
        this.bidSize2 = bidSize2;
        this.bidSize3 = bidSize3;
        this.bidSize4 = bidSize4;
        this.bidSize5 = bidSize5;
        this.askSize1 = askSize1;
        this.askSize2 = askSize2;
        this.askSize3 = askSize3;
        this.askSize4 = askSize4;
        this.askSize5 = askSize5;
        this.bidPrice6 = bidPrice6;
        this.bidPrice7 = bidPrice7;
        this.bidPrice8 = bidPrice8;
        this.bidPrice9 = bidPrice9;
        this.bidPrice10 = bidPrice10;
        this.askPrice6 = askPrice6;
        this.askPrice7 = askPrice7;
        this.askPrice8 = askPrice8;
        this.askPrice9 = askPrice9;
        this.askPrice10 = askPrice10;
        this.bidSize6 = bidSize6;
        this.bidSize7 = bidSize7;
        this.bidSize8 = bidSize8;
        this.bidSize9 = bidSize9;
        this.bidSize10 = bidSize10;
        this.askSize6 = askSize6;
        this.askSize7 = askSize7;
        this.askSize8 = askSize8;
        this.askSize9 = askSize9;
        this.askSize10 = askSize10;
        this.marketCap = marketCap;
        this.curPriceTick = curPriceTick;
        this.turnoverAmount = turnoverAmount;
        this.curPriceTone = curPriceTone;
        this.lastClosingPriceDate = lastClosingPriceDate;
        this.perChange = perChange;
        this.mBidTone = mBidTone;
        this.mAskTone = mAskTone;
        this.vwap = vwap;
        this.impAskSize = impAskSize;
        this.impBidSize = impBidSize;
        this.ytdHighPriceDate = ytdHighPriceDate;
        this.ytdLowPriceDate = ytdLowPriceDate;
        this.buyMarginNc = buyMarginNc;
    }
    
    public RealQuoteSnapshot() {
        
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
    }
}
