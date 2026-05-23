package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.CancelExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationReq;
import com.sbisec.helios.ap.athena.protocol.exchange.order.GetExchangeCancelledOrderInitializationRes;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaFxTradeOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaFxTradeOrderCancelConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaFxTradeOrderCancelConfirmA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaFxTradeOrderCancelConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0501-02_1
 * 画面名：為替取引注文取消確認
 * @author 鄒
 *
 * 2023/11/20 新規作成
 */
@Component(value = "cmpIfaFxTradeOrderCancelConfirmService")
public class IfaFxTradeOrderCancelConfirmServiceImpL implements IfaFxTradeOrderCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxTradeOrderCancelConfirmServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    @Autowired
    private IfaFxTradeOrderCancelConfirmDao dao;
    
    private static final char CHAR_ZERO = '0';
    
    //FCT001 対象顧客参照権限有無＝"0"（権限なし）
    private static final String FCT001_PRIV_FLAG_0 = "0";
    
    //FCT001 対象顧客参照権限有無＝"1"（権限なし）
    private static final String FCT001_PRIV_FLAG_1 = "1";
    
    private static final String INTERMEDIARY_VALUE_0 = "0";
    
    //国籍コード=99
    private static final String COUNTRYCD_99 = "99";
    
    //証券金銭種別_外貨
    private static final String PRODUCTCD_98 = "98";
    
    //為替
    private static final String EXCHANGE = "EXCHANGE";
    
    //為替取引注文
    private static final String ORDER = "ORDER";
    
    //検索用取引種別（為替取引)
    private static final String FX_TRADE_SEARCH_TRADE_CLASS_1 = "1";
    
    private static final String FX_TRADE_SEARCH_TRADE_CLASS_2 = "2";
    
    //取引種別（外貨）
    private static final String FOREIGN_TRADE_CLASS_1 = "1";
    
    private static final String FOREIGN_TRADE_CLASS_2 = "2";
    
    private static final String MSG_DISPLAY_TARGET_TRADE_1 = "為替取引";
    
    private static final String UN_ARRANGED_0 = "0";
    
    private static final String TRADE_CD_SELL = "2";
    
    private static final String TRADE_CD_BUY = "1";
    
    //区分.売買区分
    private static final String SELL_BUY_TYPE_BUY = "3";
    
    private static final String SELL_BUY_TYPE_SELL = "1";
    
    //区分.為替取引サービス種別　2（リテール）
    private static final String FX_TRADE_SERVICE_CLASS = "2";
    
    //区分.注文状況（一覧）　2(取消）
    private static final String FX_TRADE_ORDER_STATUS = "2";
    
    private static final String JPY = "JPY";
    
    //ハイフン
    private static final String HYPHEN = "-";
    
    //半角スペース
    private static final String SPACE = " ";
    
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    private static final String ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE = "errors.ext.orderExecution.unavailable";
    
    private static final String ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    private static final String ERRORS_CMN_ORDEREXECUTIONCANCEL_FAILED = "errors.cmn.orderExecutionCancel.failed";
    
    private static final String WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED = "warnings.cmn.postOrderExecutionCancel.completed";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA001DtoResponse
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse> initializeA001(
            IfaFxTradeOrderCancelConfirmA001DtoRequest dtoReq) throws Exception {
        
        DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse> dtoRes = new DataList<IfaFxTradeOrderCancelConfirmA001DtoResponse>();
        
        List<IfaFxTradeOrderCancelConfirmA001DtoResponse> resDtoList = new ArrayList<IfaFxTradeOrderCancelConfirmA001DtoResponse>();
        IfaFxTradeOrderCancelConfirmA001DtoResponse resA001Dto = new IfaFxTradeOrderCancelConfirmA001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeOrderCancelConfirmServiceImplL.initializeA001");
        }
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        //① 利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        //対象顧客参照権限有無＝"0"（権限なし）
        if (FCT001_PRIV_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        }
        //取引停止フラグ＝"1"（取引停止口座）
        if (FCT001_PRIV_FLAG_1.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        //② 取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCTCD_98);
        if (FX_TRADE_SEARCH_TRADE_CLASS_1.equals(dtoReq.getTradeCd())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_CLASS_1);
        } else if (FX_TRADE_SEARCH_TRADE_CLASS_2.equals(dtoReq.getTradeCd())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_CLASS_2);
        }
        inputFct003Dto.setCountryCd(COUNTRYCD_99);
        inputFct003Dto.setCurrencyCode(dtoReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        for (MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                                new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        
        //③ 通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api003Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api003Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api003Parameter.setServiceGroup(EXCHANGE);
        api003Parameter.setServiceType(ORDER);
        api003Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api003Parameter.setBuySellCode(dtoReq.getTradeCd());
        api003Request.setParameter(api003Parameter);
        CheckExchangeCurrencyServiceStatusRes api003Response = null;
        try {
            api003Response = exchangeService.checkExchangeCurrencyServiceStatusOrderCancel(api003Request);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        if (!api003Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        
        //④ 顧客共通情報.外貨建商品取引口座開設状況、顧客共通情報.外国株式取引口座開設状況」より、外貨建口座取引開設状況のチェックを行う。
        if (UN_ARRANGED_0.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())
                && UN_ARRANGED_0.equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        //⑤ 為替取引注文取消処理を行う。
        GetExchangeCancelledOrderInitializationReq api005Request = new GetExchangeCancelledOrderInitializationReq();
        GetExchangeCancelledOrderInitializationReq.Header api005Header = new GetExchangeCancelledOrderInitializationReq().new Header();
        GetExchangeCancelledOrderInitializationReq.Parameter api005Parameter = new GetExchangeCancelledOrderInitializationReq().new Parameter();
        api005Header.setToken(customerCommon.getButenCode() + HYPHEN
                + StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
        api005Parameter.setOrderNo(dtoReq.getOrderNumber());
        api005Parameter.setBusinessDate(dtoReq.getBusinessDay());
        if (TRADE_CD_SELL.equals(dtoReq.getTradeCd())) {
            // レスポンス.選択行_取引種別の売買　＝　”SELL”の場合、レスポンス.選択行_数量・受渡金額の数量の通貨コード＋　"JPY"　を設定する。
            api005Parameter.setCurrencyPair(dtoReq.getCurrencyCode() + JPY);
        } else if (TRADE_CD_BUY.equals(dtoReq.getTradeCd())) {
            // レスポンス.選択行_取引種別の売買　＝　”BUY”の場合、"JPY"＋　レスポンス.選択行_数量・受渡金額の数量の通貨コード　を設定する。　
            api005Parameter.setCurrencyPair(JPY + dtoReq.getCurrencyCode());
        }
        
        api005Parameter.setCycleNumber(Integer.valueOf(dtoReq.getCycleNumber()));
        api005Parameter.setOrderEventId(dtoReq.getOrderEventId());
        // #989
        api005Request.setHeader(api005Header);
        api005Request.setParameter(api005Parameter);
        
        try {
            GetExchangeCancelledOrderInitializationRes api005Response = exchangeService
                    .getExchangeCancelledOrderInitialization(api005Request);
            resA001Dto.setSelectedRowOrderTypeTrade(dtoReq.getTradeCd());
            resA001Dto.setSelectedRowForeignCurrencyCurrencyCode(dtoReq.getCurrencyCode());
            resA001Dto.setSelectedRowCurrency(dtoReq.getCurrency());
            resA001Dto.setSelectedRowOrderNumber(dtoReq.getOrderNumber());
            resA001Dto.setSelectedRowCycleNumber(dtoReq.getCycleNumber());
            resA001Dto.setSelectedRowOrderEventId(dtoReq.getOrderEventId());
            resA001Dto.setSelectedRowBusinessDay(dtoReq.getBusinessDay());
            resA001Dto.setOrderNumber(api005Response.getOrderNo());
            resA001Dto.setCurrencyCode(api005Response.getCurrencyCode());
            resA001Dto.setQuantity(api005Response.getOrderAmount());
            resA001Dto.setTradeKbn(api005Response.getBuySellCode());
            resA001Dto.setAccountType(api005Response.getSpecificAccountCode());
            resA001Dto.setCycleNumber(String.valueOf(api005Response.getCycleNumber()));
            
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        resDtoList.add(resA001Dto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：注文取消
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA002aDtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA002aDtoResponse
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse> orderCancellationA002a(
            IfaFxTradeOrderCancelConfirmA002aDtoRequest dtoReq) throws Exception {
        
        DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse> dtoRes = new DataList<IfaFxTradeOrderCancelConfirmA002aDtoResponse>();
        
        List<IfaFxTradeOrderCancelConfirmA002aDtoResponse> resDtoList = new ArrayList<IfaFxTradeOrderCancelConfirmA002aDtoResponse>();
        IfaFxTradeOrderCancelConfirmA002aDtoResponse resA002aDto = new IfaFxTradeOrderCancelConfirmA002aDtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeOrderCancelConfirmServiceImplL.orderCancellationA002a");
        }
        
        //① 利用者の口座に対する権限チェックを行う。
        
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        //対象顧客参照権限有無＝"0"（権限なし）
        if (FCT001_PRIV_FLAG_0.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXIST,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
            return dtoRes;
        }
        //取引停止フラグ＝"1"（取引停止口座）
        if (FCT001_PRIV_FLAG_1.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE));
            return dtoRes;
        }
        
        //② 取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCTCD_98);
        if (FX_TRADE_SEARCH_TRADE_CLASS_1.equals(dtoReq.getTradeCd())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_CLASS_1);
        } else if (FX_TRADE_SEARCH_TRADE_CLASS_2.equals(dtoReq.getTradeCd())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_CLASS_2);
        }
        inputFct003Dto.setCountryCd(COUNTRYCD_99);
        inputFct003Dto.setCurrencyCode(dtoReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        for (MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE,
                                new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        
        //③ 通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api003Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api003Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api003Parameter.setServiceGroup(EXCHANGE);
        api003Parameter.setServiceType(ORDER);
        api003Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api003Parameter.setBuySellCode(dtoReq.getTradeCd());
        api003Request.setParameter(api003Parameter);
        CheckExchangeCurrencyServiceStatusRes api003Response = null;
        try {
            api003Response = exchangeService.checkExchangeCurrencyServiceStatusOrderCancel(api003Request);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        if (!api003Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        
        //④ 顧客共通情報.外貨建商品取引口座開設状況、顧客共通情報.外国株式取引口座開設状況」より、外貨建口座取引開設状況のチェックを行う。
        if (UN_ARRANGED_0.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())
                && UN_ARRANGED_0.equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        //⑤ 注文取消前に注文内容をDBへ記録する。
        String ifaOrderNo = dao.selectIfaFxTradeOrderCancelConfirmSql004();
        String ifaOrderSubNo = "1";
        
        IfaFxTradeOrderCancelConfirmSql001RequestModel sql001Req = new IfaFxTradeOrderCancelConfirmSql001RequestModel();
        
        sql001Req.setIfaOrderNo(ifaOrderNo);
        sql001Req.setIfaOrderSubNo(ifaOrderSubNo);
        sql001Req.setButenCode(customerCommon.getButenCode());
        sql001Req.setAccountNumber(customerCommon.getAccountNumber());
        // 区分.売買区分でセットする
        if (TRADE_CD_BUY.equals(dtoReq.getTradeCd())) {
            sql001Req.setTradeCd(SELL_BUY_TYPE_BUY);
        } else if (TRADE_CD_SELL.equals(dtoReq.getTradeCd())) {
            sql001Req.setTradeCd(SELL_BUY_TYPE_SELL);
        }
        sql001Req.setCurrencyCode(dtoReq.getCurrencyCode());
        sql001Req.setCurrency(dtoReq.getCurrency());
        sql001Req.setFxOrderAmount(dtoReq.getQuantity());
        sql001Req.setFxTrade(SPACE);
        sql001Req.setAccountClassFxTrade(dtoReq.getTradingAccount());
        sql001Req.setFxTradeServiceClass(FX_TRADE_SERVICE_CLASS);
        sql001Req.setFxTradeOrderStatus(FX_TRADE_ORDER_STATUS);
        sql001Req.setFxTradeCycleNumber(dtoReq.getCycleNumber());
        sql001Req.setFxTradeOrderEventId(dtoReq.getOrderEventId());
        sql001Req.setOrderNumber(dtoReq.getOrderNumber());
        sql001Req.setBrokerCode(customerCommon.getBrokerCode());
        sql001Req.setBrokerChargeCode(customerCommon.getBrokerChargeCode());
        sql001Req.setCreator(userAccount.getMedUsers().getUserId());
        sql001Req.setUpdateUser(userAccount.getMedUsers().getUserId());
        try {
            //⑤ 注文取消前に注文内容をDBへ記録する。
            int sql001Res = dao.insertIfaFxTradeOrderCancelConfirmSql001(sql001Req);
            if (sql001Res != 1) {
                // DB登録NG：エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
                return dtoRes;
            }
            // DB登録OK：次の処理へ。
        } catch (SQLException e) {
            // DB登録NG：エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }
        resA002aDto.setIfaOrderNo(ifaOrderNo);
        resA002aDto.setIfaOrderSubNo(ifaOrderSubNo);
        
        resDtoList.add(resA002aDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002b
     * アクション名：注文取消
     * Dto リクエスト：IfaFxTradeOrderCancelConfirmA002DtoRequest
     * Dto レスポンス：IfaFxTradeOrderCancelConfirmA002DtoResponse
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse> orderCancellationA002b(
            IfaFxTradeOrderCancelConfirmA002bDtoRequest dtoReq) throws Exception {
        
        DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse> dtoRes = new DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse>();
        
        List<IfaFxTradeOrderCancelConfirmA002bDtoResponse> resDtoList = new ArrayList<IfaFxTradeOrderCancelConfirmA002bDtoResponse>();
        IfaFxTradeOrderCancelConfirmA002bDtoResponse resA002bDto = new IfaFxTradeOrderCancelConfirmA002bDtoResponse();
        
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        CancelExchangeOrderReq api004Request = new CancelExchangeOrderReq();
        CancelExchangeOrderReq.Header api004Header = new CancelExchangeOrderReq().new Header();
        CancelExchangeOrderReq.Parameter api004Parameter = new CancelExchangeOrderReq().new Parameter();
        api004Header.setToken(customerCommon.getButenCode() + HYPHEN
                + StringUtil.fillLeft(customerCommon.getAccountNumber(), CHAR_ZERO, 7));
        api004Header.setRequest_id("");
        api004Parameter.setOrderNo(dtoReq.getOrderNumber());
        api004Parameter.setBuySellCode(dtoReq.getTradeCd());
        api004Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api004Parameter.setBusinessDate(dtoReq.getBusinessDay());
        api004Parameter.setCycleNumber(Integer.valueOf(dtoReq.getCycleNumber()));
        api004Parameter.setOrderEventId(dtoReq.getOrderEventId());
        api004Request.setHeader(api004Header);
        api004Request.setParameter(api004Parameter);
        //⑥ 為替取引注文取消登録を行う。
        CancelExchangeOrderRes api004Response = new CancelExchangeOrderRes();
        IfaFxTradeOrderCancelConfirmSql002RequestModel sql002Req = new IfaFxTradeOrderCancelConfirmSql002RequestModel();
        DataList<IfaFxTradeOrderCancelConfirmA002bDtoResponse> dtoResApiErr = null;
        try {
            api004Response = exchangeService.cancelExchangeOrder(api004Request);
            sql002Req.setOrderCancelErrorFlag(false);
            sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            sql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            sql002Req.setDepositTypeFxTrade(api004Response.getDepositType());
            sql002Req.setOrderTypeDisplay(api004Response.getOrderType());
            sql002Req.setUpdateUser(userAccount.getMedUsers().getUserId());
        } catch (AthenaBusinessException e) {
            sql002Req.setOrderCancelErrorFlag(true);
            sql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
            sql002Req.setIfaOrderSubNo(dtoReq.getIfaOrderSubNo());
            sql002Req.setApiErrorCode(e.getErrorCode());
            sql002Req.setApiStatusCode(e.getStatusCode());
            sql002Req.setApiMsg(e.getMessage());
            sql002Req.setUpdateUser(userAccount.getMedUsers().getUserId());
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, "", null), e);
        }
        
        //⑦ 注文取消後に処理結果をDBへ記録する。
        ErrorLevel errorLevel = ErrorLevel.SUCCESS;
        String errorCode = "";
        String errorMessage = "";
        try {
            if (dao.updateIfaFxTradeOrderCancelConfirmSql002(sql002Req) != 1) {
                throw new Exception();
            }
        } catch (Exception e) {
            errorLevel = ErrorLevel.WARNING;
            errorCode = WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED;
            errorMessage = IfaCommonUtil.getMessage(WARNINGS_CMN_POSTORDEREXECUTIONCANCEL_COMPLETED);
        }
        
        if (dtoResApiErr != null) {
            return dtoResApiErr;
        }
        //⑧ 以下の表示を行う。
        resA002bDto.setTradeKbn(api004Response.getBuySellCode());
        resA002bDto.setCurrencyCode(api004Response.getCurrencyCode());
        resA002bDto.setQuantity(api004Response.getOrderAmount());
        resA002bDto.setAccountType(api004Response.getSpecificAccountCode());
        resA002bDto.setOrderTypeDisplay(api004Response.getOrderType());
        resA002bDto.setDepositType(api004Response.getDepositType());
        resA002bDto.setCurrency(dtoReq.getCurrency());
        
        resDtoList.add(resA002bDto);
        //DB更新エラー：API正常の場合、DB更新エラーを表示する。
        if (errorLevel != ErrorLevel.SUCCESS) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING, errorCode, errorMessage);
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        }
        
        return dtoRes;
    }
}
