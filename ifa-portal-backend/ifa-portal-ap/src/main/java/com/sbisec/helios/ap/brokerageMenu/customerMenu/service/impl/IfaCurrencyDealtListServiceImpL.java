package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.enums.BuySell;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ExchangeTradeRates;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.ExchangeTradeCurrency;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCurrencyDealtListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCurrencyDealtListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoResponseCurrency;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCurrencyDealtListA001DtoResponseCurrencyDisporder;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCurrencyDealtListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0502-01 画面名：取扱通貨一覧
 * 
 *
 * @author 池亀緑
 *
 *         2023/08/23 新規作成
 */
@Component(value = "cmpIfaCurrencyDealtListService")
public class IfaCurrencyDealtListServiceImpL implements IfaCurrencyDealtListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCurrencyDealtListServiceImpL.class);
    
    @Autowired
    private IfaCurrencyDealtListDao dao;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private CodeListService codeListService;
    
    /** 権限チェックエラー */
    public static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    public static final String ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    /** 外貨建口座未開設エラー */
    public static final String ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    /** 媒介不可エラー */
    public static final String ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 権限チェックエラー値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG = "0";
    
    /** 取引停止口座エラー値 「取引停止口座」 */
    public static final String TRADE_SUSPEND_FLAG = "1";
    
    /** 外貨建口座未開設エラー値 「外貨建商品取引口座開設状況：未開設」 */
    public static final String FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS = "0";
    
    /** 外貨建口座未開設エラー値 「外国株式取引口座開設状況：未開設」 */
    public static final String FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS = "0";
    
    /** 媒介不可エラー値 「媒介可取引有無なし」 */
    public static final String MEDIATE_ABLETRADE_FLAG = "0";
    
    /** Fct003設定値 証券金銭種別：外貨 */
    public static final String SECURITY_MONEY_CLASS = "98";
    
    /**
     * アクションID：A001 アクション名：初期化 Dto リクエスト：IfaCurrencyDealtListA001DtoRequest Dto
     * レスポンス：IfaCurrencyDealtListA001DtoResponse model
     * リクエスト：IfaCurrencyDealtListSql001RequestModel model
     * レスポンス：IfaCurrencyDealtListSql001ResponseModel
     *
     * @param dtoReq {@code IfaCurrencyDealtListA001DtoRequest }
     * @return {@code DataList <IfaCurrencyDealtListA001DtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCurrencyDealtListA001DtoResponse> orderNewMarginA001(IfaCurrencyDealtListA001DtoRequest dtoReq)
            throws Exception {
        
        // レスポンスデータ
        DataList<IfaCurrencyDealtListA001DtoResponse> dtoRes = new DataList<IfaCurrencyDealtListA001DtoResponse>();
        List<IfaCurrencyDealtListA001DtoResponse> resMainList = new ArrayList<>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCurrencyDealtListServiceImplL.orderNewMarginA001");
        }
        // 顧客共通情報
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // ユーザ共通情報
        UserAccount ua = IfaCommonUtil.getUserAccount();
        
        // Fct001
        InputFct001Dto inpFct001Dto = new InputFct001Dto();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        inpFct001Dto.setButenCode(butenCode);
        inpFct001Dto.setAccountNumber(accountNumber);
        
        OutputFct001Dto outFct001Dto = fct001.doCheck(inpFct001Dto);
        
        // Fct001　利用者の口座に対する権限チェックを行う
        // 対象顧客参照権限有無＝権限なしの場合：権限なしエラーを返す
        if (TARGET_CUSTOMER_REF_AUTH_FLAG.equals(outFct001Dto.getTargetCustomerRefAuthFlag())) {
            // 業務エラーメッセージの取得
            String message = IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    new String[] { butenCode, accountNumber });
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
            
            return dtoRes;
        }
        // 取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (TRADE_SUSPEND_FLAG.equals(outFct001Dto.getTradeSuspendFlag())) {
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_SELECTACCOUNT_OUTOFSERVICE, new String[] {});
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
            
            return dtoRes;
        }
        
        String foreignSecurityTradeAccountOpenStatus = cc.getForeignSecurityTradeAccountOpenStatus();
        String foreignStockTradeAccountOpenStatus = cc.getForeignStockTradeAccountOpenStatus();
        //　利用者の口座に対する権限チェックを行う
        // 顧客共通情報の「外貨建商品取引口座開設状況」「外国株式取引口座開設状況」が共に未開設の場合：外貨建口座未開設エラーを返す
        if (FOREIGN_SECURITY_TRADE_ACCOUNT_OPEN_STATUS.equals(foreignSecurityTradeAccountOpenStatus)
                && FOREIGN_STOCK_TRADE_ACCOUNT_OPEN_STATUS.equals(foreignStockTradeAccountOpenStatus)) {
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGN_SECURITIES_ACCOUNT_NOTOPEN, new String[] {});
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
            
            return dtoRes;
        }
        
        // Fct003
        InputFct003Dto inpFct003Dto = new InputFct003Dto();
        inpFct003Dto.setButenCode(butenCode);
        inpFct003Dto.setAccountNumber(accountNumber);
        inpFct003Dto.setProductCd(SECURITY_MONEY_CLASS);
        
        OutputFct003Dto outFct003Dto = fct003.doCheck(inpFct003Dto);
        
        //　Fct003　取引コースによる媒介可否チェックを行う
        //　取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す
        if (MEDIATE_ABLETRADE_FLAG.equals(outFct003Dto.getMediateAbleTradeFlag())) {
            // 区分.対象取引（メッセージ表示用）（区分値：1（為替取引） ＠表示パターン：1
            String codeList = codeListService.getValue("MSG_DISPLAY_TARGET_TRADE", "1");
            String message = IfaCommonUtil.getMessage(ERRORS_CMN_SELECTED_ACCOUNT_COURSE_UNAVAILABLE,
                    new String[] { codeList });
            dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.FATAL, ErrorLevel.FATAL.name(), message);
            
            return dtoRes;
        }
        
        //　API001　通貨情報を取得する
        ListExchangeTradeCurrenciesReq api001Req = new ListExchangeTradeCurrenciesReq();
        ListExchangeTradeCurrenciesRes api001Res = exchangeService.listExchangeTradeCurrencies(api001Req);
        List<ExchangeTradeCurrency> api001ResList = api001Res.getCurrencies();
        
        //　API002　レート情報を取得する
        ListExchangeTradeRatesReq listExchangeTradeRatesReq = new ListExchangeTradeRatesReq();
        listExchangeTradeRatesReq.getHeader().setToken(RequestUtil.getToken(butenCode, accountNumber));
        listExchangeTradeRatesReq.getHeader().setOperator_id(ua.getCcsUserId());
        ListExchangeTradeRatesRes api002Res = exchangeService.listExchangeTradeRates(listExchangeTradeRatesReq);
        List<ExchangeTradeRates> api002ResList = api002Res.getExchangeTradeRates();
        
        Map<String, List<ExchangeTradeRates>> map = api002ResList.stream()
                .collect(Collectors.groupingBy(api -> api.getCurrencyCode().getId()));
        List<IfaCurrencyDealtListA001DtoResponseCurrencyDisporder> currencyList = new ArrayList<>();
        
        for (ExchangeTradeCurrency apiRes001 :api001ResList ) {
            if (map.keySet().contains(apiRes001.getCurrencyCode())) {
                String currencyCode = apiRes001.getCurrencyCode();
                List<ExchangeTradeRates> api002resList = map.get(currencyCode);
                
                IfaCurrencyDealtListA001DtoResponseCurrencyDisporder currencyDisporder = new IfaCurrencyDealtListA001DtoResponseCurrencyDisporder();
                String buyPrice = api002resList.stream().filter(a -> BuySell.BUY.getId().equals(a.getBuySellCode().getId()))
                        .map(a -> a.getReferenceExchangeRate()).findFirst().orElse(null);
                String sellPrice = api002resList.stream().filter(a -> BuySell.SELL.getId().equals(a.getBuySellCode().getId()))
                        .map(a -> a.getReferenceExchangeRate()).findFirst().orElse(null);
                
                currencyDisporder.setCurrencyCode(currencyCode);
                currencyDisporder.setCurrencyCode(apiRes001.getCurrencyCode());
                currencyDisporder.setCurrencyName(apiRes001.getCurrencyName());
                currencyDisporder.setFxTrade(apiRes001.getExchangeTradeType());
                currencyDisporder.setDeadlines1(apiRes001.getCloseTime1());
                currencyDisporder.setDeadlines2(apiRes001.getCloseTime2());
                currencyDisporder.setDecimalLength(apiRes001.getDecimalLength());
                currencyDisporder.setPurchaseReferenceRate(buyPrice);
                currencyDisporder.setReferenceRateForSale(sellPrice);
                
                currencyList.add(currencyDisporder);
            }        
        }     
        // SQL001　コード１を取得する。
        IfaCurrencyDealtListSql001RequestModel sql001Req = new IfaCurrencyDealtListSql001RequestModel();
        DataList<IfaCurrencyDealtListSql001ResponseModel> sql001Res = new DataList<IfaCurrencyDealtListSql001ResponseModel>();
        for (IfaCurrencyDealtListA001DtoResponseCurrencyDisporder currencyCode : currencyList) {
            sql001Req.setCurrencyCode(currencyCode.getCurrencyCode());
            sql001Res = dao.selectIfaCurrencyDealtListSql001(sql001Req);
            currencyCode.setDisporder(sql001Res.getDataList().get(0).getDisporder());
        }
        // 通貨リスト.コード1で昇順にソート
        currencyList.sort(Comparator.comparing(IfaCurrencyDealtListA001DtoResponseCurrencyDisporder::getDisporder));
        
        //ソート用通貨リストからレスポンス用通貨リストへ値をセット
        IfaCurrencyDealtListA001DtoResponse resMain = new IfaCurrencyDealtListA001DtoResponse();
        List<IfaCurrencyDealtListA001DtoResponseCurrency> resList = new ArrayList<IfaCurrencyDealtListA001DtoResponseCurrency>();
        for (IfaCurrencyDealtListA001DtoResponseCurrencyDisporder currencySort : currencyList) {
            IfaCurrencyDealtListA001DtoResponseCurrency res = new IfaCurrencyDealtListA001DtoResponseCurrency();
            // 通貨コード
            res.setCurrencyCode(currencySort.getCurrencyCode());
            // 通貨リスト.通貨名
            res.setCurrencyName(currencySort.getCurrencyName());
            // 通貨リスト.為替取引
            res.setFxTrade(currencySort.getFxTrade());
            // 通貨リスト.締時間1
            res.setDeadlines1(currencySort.getDeadlines1());
            // 通貨リスト.締時間2
            res.setDeadlines2(currencySort.getDeadlines2());
            // 通貨リスト.小数部有効桁数
            res.setDecimalLength(currencySort.getDecimalLength());
            // 通貨リスト.買付参考レート
            res.setPurchaseReferenceRate(currencySort.getPurchaseReferenceRate());
            // 通貨リスト.売却参考レート
            res.setReferenceRateForSale(currencySort.getReferenceRateForSale());
            
            resList.add(res);
        }
        
        resMain.setCurrencyList(resList);
        resMainList.add(resMain);
        dtoRes = IfaCommonUtil.createDataList(resMainList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
}

