package com.sbisec.helios.ap.testtool.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.GetAccountProfileReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryReq;
import com.sbisec.helios.ap.athena.protocol.account.ListForeignScheduleCashBalancesReq;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq;
import com.sbisec.helios.ap.athena.protocol.account.accounts.GetJrNisaAccountStatusReq;
//import com.sbisec.helios.ap.athena.protocol.account.ListSecuritiesBalancesRes;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.common.Page;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
//import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ConfirmExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CreateExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.ListExchangeOrdersReq;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ClosedPositionInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInputForConfirmForeignStockClosedMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CloseForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ConfirmForeignStockClosedMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.DeleteForeignStockMarginOrderReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockDeletedMarginOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerHeadlineReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPowerReferenceReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesReq;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketReq;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashReq;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesReq;
import com.sbisec.helios.ap.athena.service.CometForeignStockMarginOrderService;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.component.Fct024;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.component.Fct028;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct031;
import com.sbisec.helios.ap.bizcommon.component.Fct032;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.component.Fct034;
import com.sbisec.helios.ap.bizcommon.component.Fct035;
import com.sbisec.helios.ap.bizcommon.component.Fct037;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.component.Fct039;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.HeracrossApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.testtool.service.FunctionTestService;
import com.sbisec.helios.common.util.NriApiWrapper;

import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;
import jp.co.sbisec.pcenter.dto.yanap.EstimateMarginOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.MarginPlaceOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionDetail1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract0In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract2InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderExecutionIn;
import jp.co.sbisec.pcenter.dto.yanap.StockPlaceOrderAutoIn;

/**
 * 共通関数スタブテスト用サービス
 * 
 * @author base 熊
 */

@Component("functionTestService")

public class FunctionTestServiceImpl implements FunctionTestService {
    
    /* ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionTestServiceImpl.class);
    
    @Autowired
    private ApplicationContext appContext;
    
    @Autowired
    private Fct027 fct027;
    
    /**
     * API呼び出しクラス(Athena)
     */
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private NriApiWrapper nriapiwrapper;
    
    @Autowired
    private CometForeignStockMarginOrderService cForeignStockMarginOrderService;
    
    @Autowired
    private HeracrossApiWrapper nriapiwrapper2;

    @Override
    public OutputFct001Dto doFct001(InputFct001Dto input) throws Exception {
        Fct001 stub = appContext.getBean(Fct001.class);
        return stub.doCheck(input);
    }
    
    @Override
    public OutputFct004Dto doFct004(InputFct004Dto input) throws Exception {
        
        Fct004 stub = appContext.getBean(Fct004.class);
        return stub.doCheck(input);
    }

    public OutputFct004Dto doFct004_2(InputFct004Dto input) {
        
        //APIを順次呼び出し
        
        CheckExchangeCurrencyServiceStatusReq req1 = new CheckExchangeCurrencyServiceStatusReq();
        ConfirmExchangeCreatedOrderReq req2 = new ConfirmExchangeCreatedOrderReq();
        CreateExchangeOrderReq req3 = new CreateExchangeOrderReq();
        GetExchangeBusinessDateReq req4 = new GetExchangeBusinessDateReq();
        GetExchangeTradeCurrencyReq req5 = new GetExchangeTradeCurrencyReq();
        GetJrNisaAccountStatusReq req6 = new GetJrNisaAccountStatusReq();
        ListExchangeOrdersReq req7 = new ListExchangeOrdersReq();
        ListExchangeTradeRatesReq req8 = new ListExchangeTradeRatesReq();
        MultiGetPossibleWithdrawalsReq req9 = new MultiGetPossibleWithdrawalsReq();
        GetAccountProfileReq req10 = new GetAccountProfileReq();
        
        GetExchangeCancelledOrderInitializationReq req11 = new GetExchangeCancelledOrderInitializationReq();
        CancelExchangeOrderReq req12 = new CancelExchangeOrderReq();
        ConfirmIfaExchangeCreatedOrderReq req13 = new ConfirmIfaExchangeCreatedOrderReq();
        CreateIfaExchangeOrderReq req14 = new CreateIfaExchangeOrderReq();
        GetIfaExchangeBusinessDateReq req15 = new GetIfaExchangeBusinessDateReq();
        //GetMarginPowerDetailReq req16 = new GetMarginPowerDetailReq();
        ListExchangeTradeCurrenciesReq req17 = new ListExchangeTradeCurrenciesReq();
        //ListForeignMarginScheduleCashBalancesReq req18 = new ListForeignMarginScheduleCashBalancesReq();
        ListIfaExchangeTradeRatesReq req19 = new ListIfaExchangeTradeRatesReq();
        ListOperatorScopesReq req20 = new ListOperatorScopesReq();
        
        GetForeignStockSecuritiesReq req21 = new GetForeignStockSecuritiesReq();
        GetForeignStockAttentionSecuritiesReq req23 = new GetForeignStockAttentionSecuritiesReq();
        ListForeignScheduleCashBalancesReq req24 = new ListForeignScheduleCashBalancesReq();
        
        ListMarketPricesReq req25 = new ListMarketPricesReq();
        QueryMarginContract1In req26 = new QueryMarginContract1In();
        //String[] 
        
        QueryMgEstimateCapabilityIn req28 = new QueryMgEstimateCapabilityIn();
        GetForeignStockCreatedMarginOrderInitializationReq req29 = new GetForeignStockCreatedMarginOrderInitializationReq();
        GetMarginPowerHeadlineReq req30 = new GetMarginPowerHeadlineReq();
        QueryAccountPositionSumWebInData req31 = new QueryAccountPositionSumWebInData();
        
        CreateMarketPriceTicketReq req33 = new CreateMarketPriceTicketReq();
        GetMarginPositionSummaryReq req34 = new GetMarginPositionSummaryReq();
        GetMarketPriceHashReq req35 = new GetMarketPriceHashReq();
        ListMarginPositionsReq req38 = new ListMarginPositionsReq();
        ListShortableStocksReq req39 = new ListShortableStocksReq();
        EstimateStockOrderAutoIn req40 = new EstimateStockOrderAutoIn();
        QueryExecutionSummary1In req41 = new QueryExecutionSummary1In();
        StockPlaceOrderAutoIn req44 = new StockPlaceOrderAutoIn();
        GetMarginPowerReferenceReq req47 = new GetMarginPowerReferenceReq();
        GetMarginPositionReq req48 = new GetMarginPositionReq();
        //ConfirmForeignStockCreatedMarginOrderReq req49 = new ConfirmForeignStockCreatedMarginOrderReq();
        MarginOrderInput req49 = new MarginOrderInput();
        MarginOrderInput req50 = new MarginOrderInput();
        //CreateForeignStockMarginOrderReq req50 = new CreateForeignStockMarginOrderReq();
        GetForeignStockDeletedMarginOrderInitializationReq req51 = new GetForeignStockDeletedMarginOrderInitializationReq();
        DeleteForeignStockMarginOrderReq req52 = new DeleteForeignStockMarginOrderReq();
        ConfirmForeignStockClosedMarginOrderReq req53 = new ConfirmForeignStockClosedMarginOrderReq();
        CloseForeignStockMarginOrderReq req54 = new CloseForeignStockMarginOrderReq();
        //ListForeignStockOrdersReq req55 = new ListForeignStockOrdersReq();
        QueryMarginContract0In req56 = new QueryMarginContract0In();
        //NRIQueryMgRDOrderWebIn req57 = new NRIQueryMgRDOrderWebIn();
        QueryMgRDOrderExecutionIn req58 = new QueryMgRDOrderExecutionIn();
        MarginPlaceOrderIn req59 = new MarginPlaceOrderIn();
        EstimateMarginOrderIn req60 = new EstimateMarginOrderIn();
        GetMarginAccountAutoTransferSettingReq req63 = new GetMarginAccountAutoTransferSettingReq();
        
        QueryMarginContract2InData req67 = new QueryMarginContract2InData();
        QueryExecutionDetail1InData req69 = new QueryExecutionDetail1InData();
        Page page = new Page();
        String[] strarray = { "" };
        
        List<ClosedPositionInput> pos = new ArrayList<ClosedPositionInput>();
        MarginOrderInputForConfirmForeignStockClosedMarginOrder reqxx = new MarginOrderInputForConfirmForeignStockClosedMarginOrder();
        MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder reqxxx = new MarginTradeRepayOrderConfirmForCloseForeignStockMarginOrder();
        
        CreateMarginAccountAutoTransferSettingReq reqx1 = new CreateMarginAccountAutoTransferSettingReq();
        
        try {
            
            String[] beforeInputstringAray = { "0", "1111" };
            List<String[]> beforeInputstringArayList = new ArrayList<String[]>();
            beforeInputstringArayList.add(beforeInputstringAray);
            String inputstring = nriapiwrapper2.convertInputstring(beforeInputstringArayList);
            
            RealQuoteSnapshotOut out = nriapiwrapper2.getRealQuoteSnapshot(inputstring);
            /*        	apiwrapper.queryStockUniteOrderNeo(req22);
                    	RealQuoteKey apiIn = new RealQuoteKey();
            List<RealQuoteKey> symbols = new ArrayList<RealQuoteKey>();
            symbols.add(apiIn);
            out = nriapiwrapper.getRealQuoteSnapshot(symbols);*/
            exchangeService.checkExchangeCurrencyServiceStatus(req1);
            exchangeService.confirmExchangeCreatedOrder(req2);
            exchangeService.createExchangeOrder(req3);
            exchangeService.getExchangeBusinessDate(req4);
            exchangeService.getExchangeTradeCurrency(req5);
            foreignAccountService.getJrNisaAccountStatus(req6);
            exchangeService.listExchangeOrders(req7);
            exchangeService.listExchangeTradeRates(req8);
            foreignAccountService.multiGetPossibleWithdrawals(req9);
            foreignAccountService.getAccountProfile("", "", "");
            exchangeService.getExchangeCancelledOrderInitialization(req11);
            exchangeService.cancelExchangeOrder(req12);
            exchangeService.confirmIfaExchangeCreatedOrder(req13);
            exchangeService.createIfaExchangeOrder(req14);
            exchangeService.getIfaExchangeBusinessDate(req15);
            exchangeService.listExchangeTradeCurrencies(req17);
            //exchangeService.listIfaExchangeTradeRates(req19);
            //foreignAccountService.listOperatorScopes(req20);
            foreignStockService.getForeignStockSecurities("", "");//21
            //apiwrapper.queryStockUniteOrderNeo(req22);
            
            foreignStockService.createMarginAccountAutoTransferSetting("1", "1", "1", null, null, null, null, "1");
            foreignStockService.getForeignStockAttentionSecurities("", "");//23
            foreignAccountService.listForeignScheduleCashBalances(req24); //空？
            
            foreignInformationService.listMarketPrices("", "", strarray);//25
            //NRI        	apiwrapper.queryMarginContract1(req26);
            //        	RealQuoteKey apiIn = new RealQuoteKey();
            //            List<RealQuoteKey> symbols = new ArrayList<RealQuoteKey>();
            //            symbols.add(apiIn);
            //heracross            com.sbisec.helios.ap.extapi.dto.stock.RealQuoteSnapshotOut out = nriapiwrapper.getRealQuoteSnapshot(symbols);
            //apiwrapper.queryMgEstimateCapability(req28);
            foreignStockService.getForeignStockCreatedMarginOrderInitialization("", "", "", "", "", "", "");
            foreignAccountService.getMarginPowerHeadline("", "", "");
            
            //NRI         	apiwrapper.queryAccountPositionSumWeb(req31);
            
            foreignInformationService.createMarketPriceTicket("", "", "", "", "");
            foreignAccountService.getMarginPositionSummary(req34);
            foreignInformationService.getMarketPriceHash("", "", "", "");
            //foreignAccountService.listMarginPositions(req38);
            
            foreignStockService.listShortableStocks("", "", "", page);
            /*        	apiwrapper.estimateStockOrderAuto(req40);
                    	apiwrapper.queryExecutionSummary1(req41);
                    	apiwrapper.stockPlaceOrderAuto(req44); //信用新規注文確認（まーじまち）
            */ //45
                //46
            foreignAccountService.getMarginPowerReference("", "", "");//米株信用取引新規注文入力//47
            foreignAccountService.getMarginPosition("", "", "", "", "", "", "", "", "", "", "");//48
            foreignStockService.confirmForeignStockCreatedMarginOrder("", "", "", "", "", req49);
            foreignStockService.createForeignStockMarginOrder("", "", "", "", "", req50);
            cForeignStockMarginOrderService.getForeignStockDeletedMarginOrderInitialization(req51);//米株信用取引注文取消確認
            cForeignStockMarginOrderService.deleteForeignStockMarginOrder(req52); //米株信用取引注文取消確認
            foreignStockService.confirmForeignStockClosedMarginOrder("", "", "", "", "", reqxx, pos);//53
            foreignStockService.closeForeignStockMarginOrder("", "", "", "", "", reqxxx, pos);//54
            /*        	apiwrapper.queryMarginContract0(req56);
                    	apiwrapper.queryMgRDOrderWeb(req57);
                    	apiwrapper.queryMgRDOrderExecution(req58);
                    	apiwrapper.marginPlaceOrder(req59); // 信用新規注文確認（まーじまち）
                    	apiwrapper.estimateMarginOrder(req60);
                    	//foreignAccountService.getMarginAccountAutoTransferSetting(req63); //　米株信用　自動振替設定（まーじまち）
                    	apiwrapper.queryMarginContract2(req67);
                    	apiwrapper.queryExecutionDetail1(req69);
            */
            
        } catch (Exception e) {
            // TODO: handle exception
            LOGGER.debug(e.getMessage());
            
        }
        return new OutputFct004Dto();
    }
    
    @Override
    public OutputFct020Dto doFct020(InputFct020Dto input) {
        
        Fct020 stub = appContext.getBean(Fct020.class);
        return stub.getData(input);
    }
    
    @Override
    public OutputFct024Dto doFct024(InputFct024Dto input) {
        
        Fct024 stub = appContext.getBean(Fct024.class);
        return stub.getData(input);
    }

    @Override
    public OutputFct027Dto doFct027(InputFct027Dto input) {
        
        Fct027 stub = appContext.getBean(Fct027.class);
        
        return stub.getData(input);
    }

    @Override
    public OutputFct028Dto doFct028(InputFct028Dto input) throws Exception{
        
        Fct028 stub = appContext.getBean(Fct028.class);
        
        return stub.doCheck(input);
    }
    
    @Override
    public OutputFct030Dto doFct030(InputFct030Dto input) throws Exception{
        
        Fct030 stub = appContext.getBean(Fct030.class);
        
        return stub.getData(input);
    }

    @Override
    public OutputFct033Dto doFct033(InputFct033Dto input) throws Exception {
        
        UserAccount user = IfaCommonUtil.getUserAccount();
        
        CustomerCommon common = IfaCommonUtil.getCustomerCommon();
        
        Fct033 stub = appContext.getBean(Fct033.class);
        return stub.doCheck(input);
    }
    
    @Override
    public OutputFct034Dto doFct034(InputFct034Dto input) throws Exception{
        
        UserAccount user = IfaCommonUtil.getUserAccount();
        
        CustomerCommon common = IfaCommonUtil.getCustomerCommon();
        
        Fct034 stub = appContext.getBean(Fct034.class);
        return stub.doCheck(input);
    }
    
    @Override
    public OutputFct035Dto doFct035(InputFct035Dto input) throws Exception{
        
        Fct035 stub = appContext.getBean(Fct035.class);
        return stub.doCheck(input);
        
    }
    
    @Override
    public OutputFct032Dto doFct032(InputFct032Dto input) {
        
        Fct032 stub = appContext.getBean(Fct032.class);
        return stub.getData(input);
    }
    
    @Override
    public OutputFct031Dto doFct031(InputFct031Dto input) throws Exception {
        
        Fct031 stub = appContext.getBean(Fct031.class);
        return stub.getData(input);
    }
    
    @Override
    public OutputFct037Dto doFct037(InputFct037Dto input) {
        
        Fct037 stub = appContext.getBean(Fct037.class);
        return stub.getData(input);
    }
    
    @Override
    public OutputFct038Dto doFct038(InputFct038Dto input) {
        
        Fct038 stub = appContext.getBean(Fct038.class);
        return stub.getData(input);
    }
    
    @Override
    public OutputFct039Dto doFct039(InputFct039Dto input) throws Exception {
        
        Fct039 stub = appContext.getBean(Fct039.class);
        return stub.getData(input);
    }

    @Override
    public OutputFct006Dto doFct006(InputFct006Dto input) {
        
        Fct006 stub = appContext.getBean(Fct006.class);
        return stub.doCheck(input);
    }
    
}
