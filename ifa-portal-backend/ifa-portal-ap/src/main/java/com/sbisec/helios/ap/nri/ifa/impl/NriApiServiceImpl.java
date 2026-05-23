package com.sbisec.helios.ap.nri.ifa.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.AccountUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.TrimUtil;
import com.sbisec.helios.ap.nri.ifa.NriApiService;

import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginLumpOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortIn;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortInData;
import jp.co.sbisec.pcenter.dto.yanap.MarginLumpPlaceOrderSortOutData;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.MgRcptDeliverCancelOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointExe;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOrd;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockUniteOrderPointOutData;
import jp.co.sbisec.pcenter.dto.yanap.RequestHeader;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.StockCancelOrderOutData;

/**
 * NRI API 呼び出しサービスクラス
 * 2024/05/07 新規作成
 *
 * @author SCSK 笹倉 秀行
 *
 */
@Component
public class NriApiServiceImpl implements NriApiService {
    
    /** EC-GW */
    private static final String EC_GW = "EC-GW";
    
    /** EC-GW2 */
    private static final String EC_GW2 = "EC-GW2";
    
    /** 区分.新規建売買区分 */
    private static final String OPEN_TRADE_KBN = "NEW_CREDIT_SELL_BUY_TYPE";
    
    /** 区分.新規市場 */
    private static final String NEW_MARKET = "NEW_MARKET";
    
    /** 区分.弁済期限 */
    private static final String PAYMENT_DEADLINE = "PAYMENT_DEADLINE";
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 区分.取引種別（国内株式） */
    private static final String DOMESTIC_STOCK_TRADE_CLASS = "DOMESTIC_STOCK_TRADE_CLASS";
    
    /** 新規市場:全市場 */
    private static final String NEW_MARKET_ALL = "ALL";
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private CodeListService codeListService;
    
    /**
     * 信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrder
     *
     * @param input 信用一括返済注文確認情報
     * @return 信用一括返済注文確認結果情報
     * @throws Exception 異常
     */
    public EstimateMarginLumpOrderOutData estimateMarginLumpOrder(EstimateMarginLumpOrderInData input)
            throws Exception {
        
        input.setTradeKbn(codeListService.convertKeyToExtKey(DOMESTIC_STOCK_TRADE_CLASS, EC_GW2, input.getTradeKbn()));
        input.setComId(codeListService.convertKeyToExtKey(PRE_CONTRACT_DOC_CODE, EC_GW, input.getComId()));
        
        EstimateMarginLumpOrderIn api001Req = new EstimateMarginLumpOrderIn();
        api001Req.setIndata(input);
        EstimateMarginLumpOrderOutData output = apiWrapper.estimateMarginLumpOrder(api001Req);
        TrimUtil.trimStringFields(output);
        return output;
    }
    
    /**
     * 返済順序指定信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrderSort
     *
     * @param input 返済順序指定信用一括返済注文確認情報
     * @return 返済順序指定信用一括返済注文確認結果情報
     * @throws Exception 異常
     */
    public EstimateMarginLumpOrderSortOutData estimateMarginLumpOrderSort(EstimateMarginLumpOrderSortInData input)
            throws Exception {
        
        input.setTradeKbn(codeListService.convertKeyToExtKey(DOMESTIC_STOCK_TRADE_CLASS, EC_GW2, input.getTradeKbn()));
        input.setComId(codeListService.convertKeyToExtKey(PRE_CONTRACT_DOC_CODE, EC_GW, input.getComId()));
        
        EstimateMarginLumpOrderSortIn api002Req = new EstimateMarginLumpOrderSortIn();
        api002Req.setIndata(input);
        EstimateMarginLumpOrderSortOutData output = apiWrapper.estimateMarginLumpOrderSort(api002Req);
        TrimUtil.trimStringFields(output);
        return output;
    }
    
    /**
     * 建玉残高明細
     * NRI_QueryMarginContract1
     * 検索結果件数が50件を超える場合に、検索結果件数に達するまで、検索番号指定FROM/検索番号指定TOを50件ずつオフセットして繰り返します.
     *
     * @param buttenCode 部店コード
     * @param accountNumber 口座番号
     * @param brandCode 銘柄コード
     * @param openTradeKbn 新規売買区分
     * @param openMarket 新規市場
     * @param paymentLimit 弁済期限
     * @param requestType リクエストタイプ
     * @return 建玉残高明細レスポンス
     * @throws Exception 異常
     */
    @Override
    public List<QueryMarginContract1OutData> queryMarginContract1List(String buttenCode, String accountNumber,
            String brandCode, String openTradeKbn, String openMarket, String paymentLimit, String requestType)
            throws Exception {
        
        // 建玉残高明細リクエストの共通項目を設定する
        final QueryMarginContract1InData inData = new QueryMarginContract1InData();
        inData.setButenCd(buttenCode);
        inData.setKozaNo(AccountUtil.formatToApi(accountNumber));
        inData.setBrandCd(brandCode);
        inData.setOpenTradeKbn(codeListService.convertKeyToExtKey(OPEN_TRADE_KBN, EC_GW, openTradeKbn));
        inData.setOpenMarket(codeListService.convertKeyToExtKey(NEW_MARKET, EC_GW, openMarket));
        if (NEW_MARKET_ALL.equals(openMarket)) {
            inData.setOpenMarket(openMarket);
        }
        inData.setPaymentLimit(codeListService.convertKeyToExtKey(PAYMENT_DEADLINE, EC_GW, paymentLimit));
        inData.setRequestType(requestType);
        final QueryMarginContract1In input = new QueryMarginContract1In();
        input.setIndata(inData);
        
        // APIを呼び出す
        final List<QueryMarginContract1OutData> output = apiWrapper.callQueryMarginContract1List(input);
        
        for (QueryMarginContract1OutData outdata : output) {
            // APIの戻り値をTrimする
            TrimUtil.trimStringFields(outdata, "ippanMgPaymentKbn", "ippanMgPaymentLimit");
            
            for (QueryMarginContract1OoutVec vec : outdata.getQueryMarginContract1Data()) {
                //　繰り返し部の外部→内部コード変換とTrimを行う
                vec.setBargainMarket(codeListService.convertExtKeyToKey(NEW_MARKET, EC_GW, vec.getBargainMarket()));
                TrimUtil.trimStringFields(vec);
            }
        }
        
        return output;
    }
    
    /**
     * 信用一括返済注文
     * NRI_MarginLumpPlaceOrder
     *
     * @param input 信用一括返済注文情報
     * @return 信用一括返済注文結果情報
     * @throws Exception 異常
    */
    @Override
    public MarginLumpPlaceOrderOutData marginLumpPlaceOrder(MarginLumpPlaceOrderInData input) throws Exception {
        
        // 内部→外部コード変換を行う
        input.setTradeKbn(codeListService.convertKeyToExtKey(DOMESTIC_STOCK_TRADE_CLASS, EC_GW2, input.getTradeKbn()));
        input.setComId(codeListService.convertKeyToExtKey(PRE_CONTRACT_DOC_CODE, EC_GW, input.getComId()));
        
        // APIを呼び出す
        final MarginLumpPlaceOrderIn apiReq = new MarginLumpPlaceOrderIn();
        apiReq.setIndata(input);
        final MarginLumpPlaceOrderOutData output = apiWrapper.marginLumpPlaceOrder(apiReq);
        
        // APIレスポンスのTrimを行い、返却する
        TrimUtil.trimStringFields(output);
        return output;
    }
    
    /**
     * 返済順序指定信用一括返済注文
     * NRI_MarginLumpPlaceOrderSort
     *
     * @param input 返済順序指定信用一括返済注文情報
     * @return 返済順序指定信用一括返済注文結果情報
     * @throws Exception 異常
    */
    @Override
    public MarginLumpPlaceOrderSortOutData marginLumpPlaceOrderSort(MarginLumpPlaceOrderSortInData input)
            throws Exception {
        
        // 内部→外部コード変換を行う
        input.setTradeKbn(codeListService.convertKeyToExtKey(DOMESTIC_STOCK_TRADE_CLASS, EC_GW2, input.getTradeKbn()));
        input.setComId(codeListService.convertKeyToExtKey(PRE_CONTRACT_DOC_CODE, EC_GW, input.getComId()));
        
        // APIを呼び出す
        final MarginLumpPlaceOrderSortIn apiReq = new MarginLumpPlaceOrderSortIn();
        apiReq.setIndata(input);
        final MarginLumpPlaceOrderSortOutData output = apiWrapper.marginLumpPlaceOrderSort(apiReq);
        
        // APIレスポンスのTrimを行い、返却する
        TrimUtil.trimStringFields(output);
        return output;
    }

    /**
     * 株式統合注文一覧照会(ポイント)
     * NRI_QueryStockUniteOrderPoint
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    @Override
    public QueryStockUniteOrderPointOutData queryStockUniteOrderPoint(QueryStockUniteOrderPointInData input)
            throws Exception {
        
        // 部店コードを設定したリクエストヘッダを作成する
        final RequestHeader header = new RequestHeader();
        header.setButenCd(input.getButenCd());
        
        // APIリクエストを作成する
        final QueryStockUniteOrderPointIn apiReq = new QueryStockUniteOrderPointIn();
        apiReq.setHeader(header);
        apiReq.setIndata(input);
        
        // APIを呼び出す
        final QueryStockUniteOrderPointOutData output = apiWrapper.queryStockUniteOrderPoint(apiReq);
        
        // API呼出結果をTrimして返却する
        TrimUtil.trimStringFields(output);
        return output;
    }
    

    /**
     * 株式統合注文一覧照会(ポイント) 注文状況一覧用
     * NRI_QueryStockUniteOrderPoint
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    @Override
    public QueryStockUniteOrderPointOutData queryStockUniteOrderPointForOrderStatusList(QueryStockUniteOrderPointInData input, Boolean isOrderStatusList)
            throws Exception {
        
        final QueryStockUniteOrderPointOutData output = apiWrapper.callQueryStockUniteOrderPointForOrderStatusList(
                new QueryStockUniteOrderPointIn(new RequestHeader(input.getButenCd()), input));
        
        TrimUtil.trimStringFields(output);
        for (QueryStockUniteOrderPointOrd reqOrderData : output.getReqOrderDataExe()) {
            TrimUtil.trimStringFields(reqOrderData, "triggerZone", "doneTriggerZone", "ippanMgPaymentKbn", "ippanMgPaymentLimit");
            for (QueryStockUniteOrderPointExe exeOrderData : reqOrderData.getExeOrderData()) {
                TrimUtil.trimStringFields(exeOrderData);
            }
            reqOrderData.setComId(codeListService.convertExtKeyToKey("COMM_TYPE", EC_GW, reqOrderData.getComId()));
            // 注文状況一覧の遷移の場合のみcomIdRを区分値変換する
            if (isOrderStatusList) {
                reqOrderData.setComIdR(codeListService.convertExtKeyToKey("COMM_TYPE", EC_GW, reqOrderData.getComIdR()));
            }
            reqOrderData.setForceKbn(
                    codeListService.convertExtKeyToKey("COERCION_TYPE", EC_GW, reqOrderData.getForceKbn()));
            reqOrderData.setHitokuteiTradeKbn(codeListService.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", EC_GW,
                    reqOrderData.getHitokuteiTradeKbn()));
            reqOrderData.setMarketId(
                    codeListService.convertExtKeyToKey("SELECT_MARKET", EC_GW, reqOrderData.getMarketId()));
            reqOrderData.setSasinariId(
                    codeListService.convertExtKeyToKey("LIMIT_MARKET_TYPE", EC_GW, reqOrderData.getSasinariId()));
        }
        return output;
    }
    
    /**
     * 国内株式・信用注文取消
     * NRI_StockCancelOrder
     *
     * @param input 国内株式・信用注文情報
     * @return 国内株式・信用注文取消結果情報
     * @throws Exception 異常
     */
    @Override
    public StockCancelOrderOutData stockCancelOrder(StockCancelOrderInData input) throws Exception {
        
        final StockCancelOrderOutData output = apiWrapper
                .stockCancelOrder(new StockCancelOrderIn(new RequestHeader(input.getButenCd()), input));
        TrimUtil.trimStringFields(output);
        return output;
    }
    
    /**
     * 信用現引現渡取消
     * NRI_MgRcptDeliverCancelOrder
     *
     * @param input 信用現引現渡取消リクエスト
     * @return 信用現引現渡取消
     * @throws Exception 異常
     */
    @Override
    public MgRcptDeliverCancelOrderOutData mgRcptDeliverCancelOrder(MgRcptDeliverCancelOrderInData input)
            throws Exception {
        
        // APIリクエストを作成する
        final MgRcptDeliverCancelOrderIn apiReq = new MgRcptDeliverCancelOrderIn();
        apiReq.setIndata(input);
        
        // APIを呼び出す
        final MgRcptDeliverCancelOrderOutData output = apiWrapper.mgRcptDeliverCancelOrder(apiReq);
        
        // API呼出結果をTrimして返却する
        TrimUtil.trimStringFields(output);
        return output;
    }
}
