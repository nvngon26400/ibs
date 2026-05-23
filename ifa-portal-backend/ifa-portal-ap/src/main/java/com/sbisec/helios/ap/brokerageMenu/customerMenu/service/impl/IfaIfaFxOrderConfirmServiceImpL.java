package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawal;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.ifa.order.CreateIfaExchangeOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct004;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaIfaFxOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaIfaFxOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderConfirmA001bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaIfaFxOrderConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0503-02_2
 * 画面名：【IFA】為替注文確認
 * @author 鄒
 *
 * 2023/11/9 新規作成
 */
@Component(value = "cmpIfaIfaFxOrderConfirmService")
public class IfaIfaFxOrderConfirmServiceImpL implements IfaIfaFxOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaIfaFxOrderConfirmServiceImpL.class);
    
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct004 fct004;
    
    //FCT001 対象顧客参照権限有無＝"0"（権限なし）
    private static final String FCT001_PRIV_FLAG_0 = "0";
    
    //FCT001 対象顧客参照権限有無＝"1"（権限なし）
    private static final String FCT001_PRIV_FLAG_1 = "1";
    
    private static final String INTERMEDIARY_VALUE_0 = "0";
    
    private static final String EXCHANGE_TRADE = "EXCHANGE_TRADE";
    
    private static final String UN_ARRANGED_0 = "0";
    
    private static final String ERRORS_EXT_ORDEREXECUTION_INSUFFICIENTPRIVILEGE = "errors.ext.orderExecution.insufficientPrivilege";
    
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";
    
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";
    
    private static final String ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    private static final String ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE = "errors.ext.orderExecution.unavailable";
    
    private static final String ERRORS_EXT_COUNTUNIT_OVERFLOW = "errors.ext.countUnit.overflow";
    
    private static final String ERRORS_CMN_ORDEREXECUTION_FAILED = "errors.cmn.orderExecution.failed";
    
    private static final String ERRORS_CMN_INFORMATION_OCCURS = "errors.cmn.information.occurs";
    
    private static final String ERRORS_FRS_PREORDEREXECUTION_FAILED = "errors.frs.preOrderExecution.failed";
    
    private static final String WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED = "warnings.frs.postOrderExecution.completed";
    
    private static final String MSG_DISPLAY_TARGET_TRADE_1 = "為替取引";
    
    //為替
    private static final String EXCHANGE = "EXCHANGE";
    
    //預り区分 総合
    private static final String GENERAL = "GENERAL";
    
    //IFAリアルタイム為替取引
    private static final String IFA_ORDER = "IFA_ORDER";
    
    private static final String TRADE_KBN_1 = "1";
    
    private static final String TRADE_TYPE_CASH_DEPOSIT = "CASH_DEPOSIT";
    
    //注文金額の上限超過
    private static final String EXCEEDING_ORDER_AMOUNT_LIMIT_1 = "true";
    
    // 区分.証券金銭種別 98：外貨
    private static final String PRODUCT_CD_FOREIGN_CURRENCY = "98";

    // 区分.国籍コード 99：該当なし
    private static final String COUNTRY_CD_OTHER = "99";

    // 区分.売買区分 3：買付
    private static final String TRADE_BUY = "3";

    // 区分.売買区分 1：売却
    private static final String TRADE_SELL = "1";

    // 区分.取引種別（外貨） 1：買付
    private static final String FOREIGN_TRADE_BUY = "1";

    // 区分.取引種別（外貨） 2：売却
    private static final String FOREIGN_TRADE_SELL = "2";

    @Autowired
    private IfaIfaFxOrderConfirmDao dao;
    
    /**
     * アクションID：A001a
     * アクション名：注文発注
     * Dto リクエスト：IfaIfaFxOrderConfirmA001aDtoRequest
     * Dto レスポンス：IfaIfaFxOrderConfirmA001aDtoResponse
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaIfaFxOrderConfirmA001aDtoResponse> orderPlacementA001a(
            IfaIfaFxOrderConfirmA001aDtoRequest dtoA001aReq) throws Exception {
        
        DataList<IfaIfaFxOrderConfirmA001aDtoResponse> dtoRes = new DataList<IfaIfaFxOrderConfirmA001aDtoResponse>();
        
        List<IfaIfaFxOrderConfirmA001aDtoResponse> resDtoList = new ArrayList<IfaIfaFxOrderConfirmA001aDtoResponse>();
        IfaIfaFxOrderConfirmA001aDtoResponse resA001aDto = new IfaIfaFxOrderConfirmA001aDtoResponse();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaIfaFxOrderConfirmServiceImplL.orderNewMarginA001");
        
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //① 利用者のIFAリアルタイム為替取引権限チェックを行う。
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        String operatorId = userAccount.getCcsUserId();
        ListOperatorScopesRes api001Response = null;
        try {
            api001Response = foreignAccountService.listOperatorScopes(butenCode, accountNumber, operatorId);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        //権限あり：次の処理へ。
        //権限なし：権限なしエラーを返す。
        if (!EXCHANGE_TRADE.equals(api001Response.getOperatorScopes().get(0))) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECUTION_INSUFFICIENTPRIVILEGE));
            return dtoRes;
        }
        
        //②利用者の口座に対する権限チェックを行う。
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
        
        //③顧客共通情報の「外貨建取引口座開設状況」「外国株式取引口座開設状況」より、外貨建口座取引開設状況をチェックを行う。
        if (UN_ARRANGED_0.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())
                && UN_ARRANGED_0.equals(customerCommon.getForeignStockTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        
        //④取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCT_CD_FOREIGN_CURRENCY);
        if (TRADE_BUY.equals(dtoA001aReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_BUY);
        }
        if (TRADE_SELL.equals(dtoA001aReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_SELL);
        }
        inputFct003Dto.setCountryCd(COUNTRY_CD_OTHER);
        inputFct003Dto.setCurrencyCode(dtoA001aReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        //変更管理 #975
        for (OutputFct003Dto.MediatePropriety mediatePropriety :outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(
                                ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        
        //⑤通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api002Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api002Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api002Parameter.setServiceGroup(EXCHANGE);
        api002Parameter.setServiceType(IFA_ORDER);
        api002Parameter.setCurrencyCode(dtoA001aReq.getCurrencyCode());
        api002Parameter.setBuySellCode(dtoA001aReq.getTradeKbn());
        api002Request.setParameter(api002Parameter);
        CheckExchangeCurrencyServiceStatusRes api002Response = null;
        try {
            api002Response = exchangeService.checkExchangeCurrencyServiceStatus(api002Request);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        if (!api002Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        //⑥通貨情報を取得し、注文金額≦ワーニングしきい値であることをチェックする。      
        GetExchangeTradeCurrencyReq api003Request = new GetExchangeTradeCurrencyReq();
        GetExchangeTradeCurrencyReq.Parameter api003Parameter = new GetExchangeTradeCurrencyReq().new Parameter();
        api003Parameter.setCurrencyCode(dtoA001aReq.getCurrencyCode());
        api003Request.setParameter(api003Parameter);
        GetExchangeTradeCurrencyRes api003Response = null;
        try {
            api003Response = exchangeService.getExchangeTradeCurrency(api003Request);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        if (new BigDecimal(dtoA001aReq.getQuantity())
                .compareTo(new BigDecimal(api003Response.getIfaWarningThreshold())) == 1) {
            if (!EXCEEDING_ORDER_AMOUNT_LIMIT_1.equals(dtoA001aReq.getExceedingOrderAmountLimit())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_INFORMATION_OCCURS));
                return dtoRes;
            }
        }
        if (TRADE_KBN_1.equals(dtoA001aReq.getTradeKbn())) {
            //為替グループ
            String exchangeGroup = "";
            //営業日情報
            String businessDate = "";
            //出金可能額
            String withdrawalPossibleAmount = "";
            //Athena余力未反映金額
            BigDecimal byingPowerMoneyAfterCalculate = new BigDecimal("0");
            
            BigDecimal maxOrderableQuantity = new BigDecimal("0");
            //⑦リクエスト.売買区分＝1（売却）の場合、為替グループを取得する。
            exchangeGroup = api003Response.getExchangeGroup();
            //⑧リクエスト.売買区分＝1（売却）の場合、営業日情報を取得する。
            GetIfaExchangeBusinessDateReq api005Request = new GetIfaExchangeBusinessDateReq();
            GetIfaExchangeBusinessDateReq.Parameter api005Parameter = new GetIfaExchangeBusinessDateReq().new Parameter();
            api005Parameter.setExchangeGroup(exchangeGroup);
            api005Request.setParameter(api005Parameter);
            GetIfaExchangeBusinessDateRes api005Response = null;
            try {
                api005Response = exchangeService.getIfaExchangeBusinessDate(api005Request);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            businessDate = api005Response.getBusinessDate();
            //⑨リクエスト.売買区分＝1（売却）の場合、出金可能額を取得する。
            MultiGetPossibleWithdrawalsReq api006Request = new MultiGetPossibleWithdrawalsReq();
            MultiGetPossibleWithdrawalsReq.Parameter api006Parameter = new MultiGetPossibleWithdrawalsReq().new Parameter();
            PossibleWithdrawalInput possibleWithdrawals = new PossibleWithdrawalInput();
            MultiGetPossibleWithdrawalsReq.Header api006Header = new MultiGetPossibleWithdrawalsReq().new Header();
            api006Header.setToken(RequestUtil.getToken(butenCode, accountNumber));
            possibleWithdrawals.setWithdrawalDate(api005Response.getDepositWithdrawalDate());
            possibleWithdrawals.setAccountKind(GENERAL);
            possibleWithdrawals.setCashingJournalType(EXCHANGE);
            possibleWithdrawals.setCurrencyCode(dtoA001aReq.getCurrencyCode());
            List<PossibleWithdrawalInput> possibleWithdrawalsList = new ArrayList<PossibleWithdrawalInput>();
            possibleWithdrawalsList.add(possibleWithdrawals);
            api006Parameter.setPossibleWithdrawals(possibleWithdrawalsList);
            api006Request.setHeader(api006Header);
            api006Request.setParameter(api006Parameter);
            MultiGetPossibleWithdrawalsRes api006ResponseList = null;
            try {
                api006ResponseList = foreignAccountService.multiGetPossibleWithdrawals(api006Request);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            PossibleWithdrawal api006Response = api006ResponseList.getPossibleWithdrawals().get(0);
            withdrawalPossibleAmount = api006Response.getWithdrawalPossibleAmount();
            //⑩リクエスト.売買区分＝1（売却）の場合、Athena余力未反映金額を取得する。
            InputFct004Dto inputFct004Dto = new InputFct004Dto();
            inputFct004Dto.setButenCode(customerCommon.getButenCode());
            inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct004Dto.setDepositType(GENERAL);
            inputFct004Dto.setOtcManageNumber("");
            inputFct004Dto.setTradeType(TRADE_TYPE_CASH_DEPOSIT);
            OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);
            byingPowerMoneyAfterCalculate = outputFct004Dto.getByingPowerMoneyAfterCalculate();
            //⑪リクエスト.売買区分＝1（売却）の場合、注文可能数量を計算する。
            //＜計算式＞
            //（API006.出金可能額　+　FCT004.計算後の余力金額）
            maxOrderableQuantity = new BigDecimal(withdrawalPossibleAmount).add(byingPowerMoneyAfterCalculate);
            //⑫リクエスト.売買区分＝1（売却）の場合、リクエスト.数量　≦　⑪で算出した注文可能数量であることをチェックする。
            if (new BigDecimal(dtoA001aReq.getQuantity()).compareTo(maxOrderableQuantity) == 1) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_EXT_COUNTUNIT_OVERFLOW,
                                new String[] { dtoA001aReq.getQuantity(),
                                        String.valueOf(api006Response.getWithdrawalPossibleAmount()),
                                        String.valueOf(outputFct004Dto.getOtcBuyingContractAmountToday()),
                                        String.valueOf(outputFct004Dto.getContractAmountTodayWithinForeignBond()),
                                        dtoA001aReq.getCurrencyCode() }));
                return dtoRes;
            }
        }
        String ifaOrderNo = dao.selectIfaIfaFxOrderConfirmSql003();
        
        //⑬発注前に注文内容を為替取引注文テーブルへ記録する。
        IfaIfaFxOrderConfirmSql001RequestModel insSql001Req = new IfaIfaFxOrderConfirmSql001RequestModel();
        BeanUtils.copyProperties(insSql001Req, dtoA001aReq);
        insSql001Req.setIfaOrderNo(ifaOrderNo);
        insSql001Req.setButenCode(customerCommon.getButenCode());
        insSql001Req.setAccountNumber(customerCommon.getAccountNumber());
        insSql001Req.setBrokerCode(customerCommon.getBrokerCode());
        insSql001Req.setBrokerChargeCode(customerCommon.getBrokerChargeCode());
        insSql001Req.setTradeCd(dtoA001aReq.getTradeKbn());
        insSql001Req.setUserId(userAccount.getUserId());
        insSql001Req.setFxOrderAmount(dtoA001aReq.getQuantity());
        int insSql001Res = 0;
        try {
            insSql001Res = dao.insertIfaIfaFxOrderConfirmSql001(insSql001Req);
            if (insSql001Res != 1) {
                //DB登録NG：DB登録エラーを返す。
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
                return dtoRes;
            }
        } catch (Exception e) {
            //DB登録NG：DB登録エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_FRS_PREORDEREXECUTION_FAILED));
            return dtoRes;
        }
        
        resA001aDto.setIfaOrderNo(ifaOrderNo);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        resDtoList.add(resA001aDto);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A001b
     * アクション名：注文発注
     * Dto リクエスト：IfaIfaFxOrderConfirmA001bDtoRequest
     * Dto レスポンス：IfaIfaFxOrderConfirmA001bDtoResponse
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @Override
    public DataList<IfaIfaFxOrderConfirmA001bDtoResponse> orderPlacementA001b(
            IfaIfaFxOrderConfirmA001bDtoRequest dtoA001bReq) throws Exception {
        
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        DataList<IfaIfaFxOrderConfirmA001bDtoResponse> dtoA001bRes = new DataList<IfaIfaFxOrderConfirmA001bDtoResponse>();
        
        List<IfaIfaFxOrderConfirmA001bDtoResponse> resA001bDtoList = new ArrayList<IfaIfaFxOrderConfirmA001bDtoResponse>();
        IfaIfaFxOrderConfirmA001bDtoResponse resA001bDto = new IfaIfaFxOrderConfirmA001bDtoResponse();
        //⑭注文発注を行う。
        IfaIfaFxOrderConfirmSql002RequestModel updSql002Req = new IfaIfaFxOrderConfirmSql002RequestModel();
        CreateIfaExchangeOrderReq api004Request = new CreateIfaExchangeOrderReq();
        
        CreateIfaExchangeOrderReq.Header api004Header = new CreateIfaExchangeOrderReq().new Header();
        CreateIfaExchangeOrderReq.Parameter api004Parameter = new CreateIfaExchangeOrderReq().new Parameter();
        api004Header.setToken(RequestUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()));
        api004Header.setOperator_id(userAccount.getMedUsers().getCcsUserId());
        api004Header.setRequest_id("");
        api004Parameter.setCurrencyCode(dtoA001bReq.getCurrencyCode());
        api004Parameter.setBuySellCode(dtoA001bReq.getTradeKbn());
        api004Parameter.setAccountKind(dtoA001bReq.getAccountClass());
        api004Parameter.setOrderAmount(dtoA001bReq.getQuantity());
        if (TRADE_KBN_1.equals(dtoA001bReq.getTradeKbn())) {
            api004Parameter.setSellMethod(dtoA001bReq.getSaleMethod());
        }
        
        api004Request.setHeader(api004Header);
        api004Request.setParameter(api004Parameter);
        
        CreateIfaExchangeOrderRes api004Response = new CreateIfaExchangeOrderRes();
        BeanUtils.copyProperties(updSql002Req, dtoA001bReq);
        DataList<IfaIfaFxOrderConfirmA001bDtoResponse> dtoA001bResApiErr = null;
        try {
            api004Response = exchangeService.createIfaExchangeOrder(api004Request);
            updSql002Req.setOrderPlacementErrorFlag(false);
            updSql002Req.setIfaOrderNo(dtoA001bReq.getIfaOrderNo());
            updSql002Req.setOrderNumber(api004Response.getOrderNo());
            updSql002Req.setTradeDateTime(api004Response.getExecutionDatetime());
            updSql002Req.setSettlementDate(api004Response.getValueDate());
            updSql002Req.setFxRate(api004Response.getExchangeRate());
            updSql002Req.setExchangeRateDateTime(api004Response.getExecutionDatetime());
            updSql002Req.setYenDeliveryAmount(api004Response.getNetAmount());
            updSql002Req.setOrderDate(api004Response.getOrderInputDatetime());
            updSql002Req.setOrderKindDisplay(api004Response.getOrderType());
            updSql002Req.setUserId(userAccount.getUserId());
        } catch (AthenaBusinessException e) {
            updSql002Req.setOrderPlacementErrorFlag(true);
            updSql002Req.setApiErrorCode(e.getErrorCode());
            updSql002Req.setApiStatusCode(String.valueOf(e.getStatusCode()));
            updSql002Req.setApiMsg(e.getMessage());
            updSql002Req.setUserId(userAccount.getUserId());
            dtoA001bResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        //⑮発注後に注文内容を為替取引注文テーブルへ記録する。
        int updSql002Res = dao.updateIfaIfaFxOrderConfirmSql002(updSql002Req);
        //⑯以下の表示を行う
        if (dtoA001bResApiErr != null) {
            //APIエラーあり：APIから返ってきたエラーを表示する。
            return dtoA001bResApiErr;
        } else {
            //APIエラーなし：【IFA】為替注文完了画面を表示する。
            //DB更新エラーの場合は、【IFA】為替注文完了画面を表示し、DB更新エラーを表示する。
            if (updSql002Res != 1) {
                dtoA001bRes = IfaCommonUtil.createDataList(resA001bDtoList, ErrorLevel.WARNING,
                        Integer.toString(ErrorLevel.WARNING.getId()),
                        IfaCommonUtil.getMessage(WARNINGS_FRS_POSTORDEREXECUTION_COMPLETED));
            } else {
                dtoA001bRes = IfaCommonUtil.createDataList(resA001bDtoList, ErrorLevel.SUCCESS, "0", "");
            }
        }
        
        resA001bDto.setTradeKbn(dtoA001bReq.getTradeKbn());
        resA001bDto.setOrderDate(api004Response.getOrderInputDatetime());
        resA001bDto.setOrderNumber(api004Response.getOrderNo());
        resA001bDto.setQuantity(api004Response.getOrderAmount());
        resA001bDto.setCurrencyCode(api004Response.getCurrencyCode());
        resA001bDto.setCurrency(api004Response.getCurrencyName());
        resA001bDto.setContractExchangeRate(api004Response.getExchangeRate());
        resA001bDto.setContractExchangeRateDateTime(api004Response.getRateDatetime());
        resA001bDto.setSelectedCurrencyInfo(dtoA001bReq.getSelectedCurrencyInfo());
        resA001bDto.setFxRateAdditionalType(dtoA001bReq.getFxRateAdditionalType());
        resA001bDto.setAdditionalPrice(dtoA001bReq.getAdditionalPrice());
        resA001bDto.setDeliveryAmount(api004Response.getNetAmount());
        resA001bDto.setTradeTiming(api004Response.getExchangeTradeType());
        resA001bDto.setSettlementDate(api004Response.getValueDate());
        resA001bDto.setOrderWarningexceedingThreshold(dtoA001bReq.getOrderWarningexceedingThreshold());
        resA001bDto.setWarningThreshold(dtoA001bReq.getWarningThreshold());
        resA001bDto.setAddLinkAttention(dtoA001bReq.getAddLinkAttention());
        resA001bDto.setDecimalLength(dtoA001bReq.getDecimalLength());
        
        resA001bDtoList.add(resA001bDto);
        return dtoA001bRes;
    }
}
