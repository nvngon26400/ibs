package com.sbisec.helios.ap.athena.service;

import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketReq;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashReq;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesReq;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;

/**
 * Comet API マーケット価格情報サービス Service.
 *       <p>
 *       一覧
 *       <p>
 *       マーケット価格情報サービス - マーケット価格取得API.<br>
 *       マーケット価格情報サービス - マーケット価格取得用チケット登録API.<br>
 *       マーケット価格情報サービス - マーケット価格ハッシュ取得API.
 * 
 * @author yunhui.zhao
 * @date 02/11/2022
 */
public interface CometInformationService {
    
    /**
     * マーケット価格情報サービス - マーケット価格取得API.
     * 
     * @param ListMarketPricesReq リクエスト
     * @return マーケット価格情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesReq
     * @see com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp
     */
    public ListMarketPricesResp listMarketPrices(ListMarketPricesReq listMarketPricesReq) throws Exception;
    
    /**
     * マーケット価格情報サービス - マーケット価格取得用チケット登録API.
     * 
     * @param createMarketPriceTicketReq リクエスト
     * @return マーケット価格取得用チケット情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketReq
     * @see com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp
     */
    public CreateMarketPriceTicketResp createMarketPriceTicket(CreateMarketPriceTicketReq createMarketPriceTicketReq)
            throws Exception;
    
    /**
     * マーケット価格情報サービス - マーケット価格ハッシュ取得API.
     * 
     * @param getMarketPriceHashReq リクエスト
     * @return マーケット価格ハッシュ情報
     * @throws Exception 異常
     * 
     * @see com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashReq
     * @see com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp
     */
    public GetMarketPriceHashResp getMarketPriceHash(GetMarketPriceHashReq getMarketPriceHashReq) throws Exception;
}
