package com.sbisec.helios.ap.athena.ifa;

import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;

/**
 * IFA Athena 情報サービス
 * 
 * @author shuchen.xin
 * @date 02/28/2022
 */
public interface ForeignInformationService extends com.sbibits.earth.service.Service {
    
    /**
     * マーケット価格取得用チケット登録
     * 
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNo     「必須」 口座番号(最大7桁)
     * @param ip            「必須」 IPv4 形式
     * @param xAppUserAgent 「必須」 App User Agent
     * @param countryCode   「必須」国コード<br>
     *                      <li>米株：US</li>
     *                      <li>日本株：JP</li>
     * @return マーケット価格取得用チケット登録結果 CreateMarketPriceTicketResp
     * @throws Exception 異常
     */
    public CreateMarketPriceTicketResp createMarketPriceTicket(String butenCode, String accountNo, String ip,
            String xAppUserAgent, String countryCode) throws Exception;
    
    /**
     * マーケット価格取得
     * 
     * @param hashToken   「必須」 部店コード(3桁)
     * @param countryCode 「非必須」 enums国コードCountryCode<br>
     *                    <li>米株：US</li>
     *                    <li>日本株：JP</li>
     * @param rics        「必須」 RICリスト
     * @return マーケット価格情報 ListMarketPricesResp
     * @throws Exception 異常
     */
    public ListMarketPricesResp listMarketPrices(String hashToken, String countryCode, String[] rics) throws Exception;
    
    /**
     * マーケット価格ハッシュ取得
     * 
     * @param butenCode     「必須」 部店コード(3桁)
     * @param accountNo     「必須」 口座番号(最大7桁)
     * @param ticket        「必須」 チケット
     * @param countryCode 「非必須」 enums国コードCountryCode<br>
     *                    <li>米株：US</li>
     *                    <li>日本株：JP</li>
     * @return マーケット価格ハッシュ情報 GetMarketPriceHashResp
     * @throws Exception 異常
     */
    public GetMarketPriceHashResp getMarketPriceHash(String butenCode, String accountNo, String ticket,
            String countryCode) throws Exception;
}
