package com.sbisec.helios.common.util;

//import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.heracross.RealQuoteKey;
import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;
import jp.co.sbisec.pcenter.dto.heracross.RealStockSnapshotstub;

@Component
public class NriApiWrapper {
    
    private static final Logger logger = LoggerFactory.getLogger(NriApiWrapper.class);
    /**
     * 銘柄の株価を取得します。<br>
     *
     * @param symbols List<StockKey> 入力銘柄情報
     * @return StockSnapshotOut 株価情報
     * @throws Exception
     */
    public RealQuoteSnapshotOut getRealQuoteSnapshot(List<RealQuoteKey> symbols) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("NriApiWrapper.stockSnapshot : {}", hashCode());
        
        RealQuoteSnapshotOut stockSnapshotOut = null;
       //TODO 外部結合向けにスタブデータ返却
        RealStockSnapshotstub stub = new RealStockSnapshotstub();
        stockSnapshotOut = stub.getDtoApi001Response();
        
/*        
        if (symbols == null) {
            return stockSnapshotOut;
        }
        
        for (RealQuoteKey stockKey : symbols) {
            if (stockKey != null && stockKey.getMarket() == null) {
                stockKey.setMarket(StringUtil.EMPTY_STRING);
            }
        }
        
        try {
            // ECGateway呼び出し
            final HttpApiWrapper wrapper = HttpApiWrapper.get();

            RealQuoteSnapshotIn stockSnapshotIn = new RealQuoteSnapshotIn(symbols);
            logger.info("API input : {}", ReflectionToStringBuilder.toString(stockSnapshotIn));
            stockSnapshotOut = wrapper.callAsGet(stockSnapshotIn, RealQuoteSnapshotOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Heracross Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        logger.info("output : {}", ReflectionToStringBuilder.toString(stockSnapshotOut.getFeedData(),ToStringStyle.MULTI_LINE_STYLE));
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
*/        
        return stockSnapshotOut;
    }

}


