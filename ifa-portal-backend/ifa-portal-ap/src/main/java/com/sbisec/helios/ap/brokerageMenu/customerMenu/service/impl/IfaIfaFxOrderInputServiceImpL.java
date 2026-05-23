package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsReq;
import com.sbisec.helios.ap.athena.protocol.account.client_entry.cashing.MultiGetPossibleWithdrawalsRes;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawal;
import com.sbisec.helios.ap.athena.protocol.account.dto.PossibleWithdrawalInput;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.CheckExchangeCurrencyServiceStatusRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ConfirmIfaExchangeCreatedOrderRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetExchangeTradeCurrencyRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.GetIfaExchangeBusinessDateRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.IfaExchangeTradeRate;
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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaIfaFxOrderInputA008DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaIfaFxOrderInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 画面ID：SUB0202_0503-02_1
 * 画面名：【IFA】為替注文入力
 * 2023/09/07 新規作成
 *
 * @author 鄒
 */
@Component(value = "cmpIfaIfaFxOrderInputService")
public class IfaIfaFxOrderInputServiceImpL implements IfaIfaFxOrderInputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaIfaFxOrderInputServiceImpL.class);

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

    private static final String EXCHANGE_TRADE = "EXCHANGE_TRADE";

    //為替
    private static final String EXCHANGE = "EXCHANGE";

    //IFAリアルタイム為替取引
    private static final String IFA_ORDER = "IFA_ORDER";

    //預り区分 総合
    private static final String GENERAL = "GENERAL";

    //取引区分 保証金振替（預り金→保証金）
    private static final String CASH_DEPOSIT = "CASH_DEPOSIT";

    //FCT001 対象顧客参照権限有無＝"0"（権限なし）
    private static final String FCT001_PRIV_FLAG_0 = "0";

    //FCT001 対象顧客参照権限有無＝"1"（権限なし）
    private static final String FCT001_PRIV_FLAG_1 = "1";

    private static final String INTERMEDIARY_VALUE_0 = "0";

    private static final String UN_ARRANGED = "未開設";

    //売買区分(買付)
    private static final String TRADE_TYPE_BUY = "3";

    //売買区分(売却)
    private static final String TRADE_TYPE_SELL = "1";

    private static final String USD_100 = "100";

    //数量の指定方法（為替） 外貨で指定
    private static final String FX_QUANTITY_DESIGNATION_METHOD_1 = "1";

    //数量の指定方法（為替） 円で指定
    private static final String FX_QUANTITY_DESIGNATION_METHOD_2 = "2";

    //売却方法 全て売却
    private static final String SELL_METHOD_2 = "2";

    //「A008 ⑦注文金額≦ワーニングしきい値であることをチェックする。」メッセージ
    private static final String MSG_PREFIX = "注文金額が";

    //「A008 ⑦注文金額≦ワーニングしきい値であることをチェックする。」メッセージ
    private static final String MSG_SUFFIX = "（しきい値）を超えています。";

    //上乗せ区分(パーセント指定（上乗せ金額）)
    private static final String ADJUST_PERCENT = "ADJUST_PERCENT";

    private static final String CURRENCY_CODE_JPY = "JPY";

    //指定した数量を売却
    private static final String SELL_PART = "SELL_PART";

    private static final String ERRORS_EXT_ORDEREXECUTION_INSUFFICIENTPRIVILEGE = "errors.ext.orderExecution.insufficientPrivilege";

    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXIST = "errors.butenAccountNotExist";

    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.cmn.selectedAccount.outOfService";

    private static final String ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";

    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";

    private static final String ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE = "errors.ext.orderExecution.unavailable";

    private static final String ERRORS_EXT_COUNTUNIT_OVERFLOW = "errors.ext.countUnit.overflow";

    private static final String MSG_DISPLAY_TARGET_TRADE_1 = "為替取引";

    private static final String PRODUCT_CD = "98";

    private static final String COUNTRY_CD = "99";

    private static final String TRADE_BUY = "3";

    private static final String TRADE_SELL = "1";

    private static final String FOREIGN_TRADE_BUY = "1";

    private static final String FOREIGN_TRADE_SELL = "2";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaIfaFxOrderInputA001DtoResponse> orderNewMarginA001(IfaIfaFxOrderInputA001DtoRequest dtoReq)
            throws Exception {

        DataList<IfaIfaFxOrderInputA001DtoResponse> dtoRes = new DataList<IfaIfaFxOrderInputA001DtoResponse>();

        List<IfaIfaFxOrderInputA001DtoResponse> resDtoList = new ArrayList<IfaIfaFxOrderInputA001DtoResponse>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaIfaFxOrderInputServiceImplL.orderNewMarginA001");
        }

        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        //①利用者のIFAリアルタイム為替取引権限チェックを行う。
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        String operatorId = userAccount.getCcsUserId();

        ListOperatorScopesRes api001Response = new ListOperatorScopesRes();
        try {
            api001Response = foreignAccountService.listOperatorScopes(butenCode, accountNumber, operatorId);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
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
        if (UN_ARRANGED.equals(customerCommon.getForeignStockTradeAccountOpenStatus())
                && UN_ARRANGED.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        //④取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCT_CD);
        if (TRADE_BUY.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_BUY);
        }
        if (TRADE_SELL.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_SELL);
        }
        inputFct003Dto.setCountryCd(COUNTRY_CD);
        inputFct003Dto.setCurrencyCode(dtoReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        //変更管理 #974
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(
                                ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        //⑤通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api003Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api003Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api003Parameter.setServiceGroup(EXCHANGE);
        api003Parameter.setServiceType(IFA_ORDER);
        api003Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api003Parameter.setBuySellCode(dtoReq.getTradeKbn());
        api003Request.setParameter(api003Parameter);

        CheckExchangeCurrencyServiceStatusRes api003Response = new CheckExchangeCurrencyServiceStatusRes();
        try {
            api003Response = exchangeService.checkExchangeCurrencyServiceStatus(api003Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        if (!api003Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        //⑥通貨情報を取得する。
        GetExchangeTradeCurrencyReq api004Request = new GetExchangeTradeCurrencyReq();

        GetExchangeTradeCurrencyReq.Parameter api004Parameter = new GetExchangeTradeCurrencyReq().new Parameter();

        api004Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api004Request.setParameter(api004Parameter);
        GetExchangeTradeCurrencyRes api004Response = new GetExchangeTradeCurrencyRes();
        try {
            api004Response = exchangeService.getExchangeTradeCurrency(api004Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }
        api004Response.setExchangeGroup(api004Response.getExchangeGroup());
        //⑦取引下限、取引上限、取引単位を設定する。
        IfaIfaFxOrderInputA001DtoResponse a001DtoResponse = new IfaIfaFxOrderInputA001DtoResponse();
        //＜設定内容＞
        //取引下限
        //売買区分＝買付の場合API004.買下限（IFAリアルタイム為替）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setClosableQuantity(api004Response.getIfaBuyLimitMin());
        }
        //売買区分＝売却の場合API004.売下限（IFAリアルタイム為替）
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setClosableQuantity(api004Response.getIfaSellLimitMin());
        }
        //取引上限
        //売買区分＝買付の場合API004.買上限（IFAリアルタイム為替）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setTradeLimitMax(api004Response.getIfaBuyLimitMax());
        }
        //売買区分＝売却の場合API004.売上限（IFAリアルタイム為替）
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setTradeLimitMax(api004Response.getIfaSellLimitMax());
        }
        //取引単位
        //売買区分＝買付の場合API004.買単位（IFAリアルタイム為替）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setTradeUnit(api004Response.getIfaBuyUnit());
        }
        //売買区分＝売却の場合API004.売単位（IFAリアルタイム為替）
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setTradeUnit(api004Response.getIfaSellUnit());
        }

        //⑧レート情報を取得する。
        String currencyCode = dtoReq.getCurrencyCode();
        String buySellCode = dtoReq.getTradeKbn();
        //複数件取得の場合は、先頭要素を利用する。

        ListIfaExchangeTradeRatesRes api005ResponseList = new ListIfaExchangeTradeRatesRes();
        try {
            api005ResponseList = exchangeService.listIfaExchangeTradeRates(butenCode, accountNumber, currencyCode, buySellCode);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        IfaExchangeTradeRate api005Response = api005ResponseList.getIfaExchangeTradeRates().get(0);
        api005Response.setAdjustType(api005Response.getAdjustType());
        //⑩営業日情報を取得する。
        GetIfaExchangeBusinessDateReq api006Request = new GetIfaExchangeBusinessDateReq();
        GetIfaExchangeBusinessDateReq.Parameter api006Parameter = new GetIfaExchangeBusinessDateReq().new Parameter();
        api006Parameter.setExchangeGroup(api004Response.getExchangeGroup());
        api006Request.setParameter(api006Parameter);

        GetIfaExchangeBusinessDateRes api006Response = new GetIfaExchangeBusinessDateRes();
        try {
            api006Response = exchangeService.getIfaExchangeBusinessDate(api006Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        //⑪出金可能額を取得する。
        MultiGetPossibleWithdrawalsReq api007Request = new MultiGetPossibleWithdrawalsReq();
        MultiGetPossibleWithdrawalsReq.Parameter api007Parameter = new MultiGetPossibleWithdrawalsReq().new Parameter();
        PossibleWithdrawalInput possibleWithdrawals = new PossibleWithdrawalInput();
        MultiGetPossibleWithdrawalsReq.Header api007Header = new MultiGetPossibleWithdrawalsReq().new Header();
        api007Header.setToken(RequestUtil.getToken(butenCode, accountNumber));

        //リクエスト.売買区分　＝　3（買付）の場合、"JPY"               
        //リクエスト.売買区分　＝　1（売却）の場合、リクエスト.通貨コード 
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(CURRENCY_CODE_JPY);
        }
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(dtoReq.getCurrencyCode());
        }
        possibleWithdrawals.setWithdrawalDate(api006Response.getDepositWithdrawalDate());
        possibleWithdrawals.setAccountKind(GENERAL);
        possibleWithdrawals.setCashingJournalType(EXCHANGE);
        List<PossibleWithdrawalInput> possibleWithdrawalsList = new ArrayList<PossibleWithdrawalInput>();
        possibleWithdrawalsList.add(possibleWithdrawals);
        api007Parameter.setPossibleWithdrawals(possibleWithdrawalsList);
        api007Request.setHeader(api007Header);
        api007Request.setParameter(api007Parameter);
        MultiGetPossibleWithdrawalsRes api007ResponseList = new MultiGetPossibleWithdrawalsRes();
        try {
            api007ResponseList
                    = foreignAccountService.multiGetPossibleWithdrawals(api007Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        PossibleWithdrawal api007Response = api007ResponseList.getPossibleWithdrawals().get(0);

        //⑫Athena余力未反映金額を取得する。
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(customerCommon.getButenCode());
        inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct004Dto.setDepositType(GENERAL);
        inputFct004Dto.setOtcManageNumber("");
        inputFct004Dto.setTradeType(CASH_DEPOSIT);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);

        //⑬注文可能数量を計算する。
        //＜計算式＞
        //売買区分＝買付
        //API007.出金可能額×API005.レート情報.デノミ÷API005.レート情報.概算用レート（計算結果を小数点以下切り捨て）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse
                    .setMaxOrderableQuantity(String.valueOf(new BigDecimal(api007Response.getWithdrawalPossibleAmount())
                            .multiply(new BigDecimal(api005Response.getBasePrice()))
                            .divide(new BigDecimal(api005Response.getComputeExchangeRate()), 0, RoundingMode.FLOOR)));
        }
        //売買区分＝売却
        //API007.出金可能額 + FCT004.計算後の余力金額
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse
                    .setMaxOrderableQuantity(String.valueOf(new BigDecimal(api007Response.getWithdrawalPossibleAmount())
                            .add(outputFct004Dto.getByingPowerMoneyAfterCalculate())));
        }

        //⑭売買区分＝買付の場合のみ、概算買付可能金額を計算する。
        //＜計算式＞
        //注文可能数量÷API005.レート情報.デノミ×API005.レート情報.概算用レート（計算結果を小数点以下切り捨て）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a001DtoResponse.setApproximatePurchaseAmount(
                    String.valueOf(new BigDecimal(a001DtoResponse.getMaxOrderableQuantity())
                            .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR)
                            .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                            .setScale(0, RoundingMode.FLOOR)));
        }

        //⑮外貨スピンボタン増減幅を設定する。
        a001DtoResponse.setForeignButtonRange(USD_100);

        //⑯外貨指定最大値、円指定最小値、円指定最大値を設定する。
        //＜設定内容＞
        //外貨指定最大値
        //注文可能数量または取引上限のうち小さいほう
        if (new BigDecimal(a001DtoResponse.getMaxOrderableQuantity())
                .compareTo(new BigDecimal(a001DtoResponse.getTradeLimitMax())) < 0) {
            a001DtoResponse.setForeignDesignationMaxValue(a001DtoResponse.getMaxOrderableQuantity());
        } else {
            a001DtoResponse.setForeignDesignationMaxValue(a001DtoResponse.getTradeLimitMax());
        }
        //円指定最小値
        //取引下限×API005.概算用レート÷API005.デノミ（計算結果を小数点以下切り上げ）
        a001DtoResponse.setYenDesignationMinValue(String.valueOf(new BigDecimal(a001DtoResponse.getClosableQuantity())
                .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR)));
        //円指定最大値
        //注文可能数量または取引上限×API005.概算用レート÷API005.デノミ（計算結果を小数点以下切り上げ）のうち小さいほう
        BigDecimal yenDesignationMaxValueCalcValue = new BigDecimal(a001DtoResponse.getForeignDesignationMaxValue())
                .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR);
        a001DtoResponse.setYenDesignationMaxValue(String.valueOf(yenDesignationMaxValueCalcValue));

        //⑱画面の初期表示を行う。
        //通貨コード
        a001DtoResponse.setCurrencyCode(dtoReq.getCurrencyCode());
        //売買区分
        a001DtoResponse.setTradeKbn(dtoReq.getTradeKbn());
        //通貨名
        a001DtoResponse.setCurrency(api004Response.getCurrencyName());
        //小数部有効桁数
        a001DtoResponse.setDecimalLength(String.valueOf(api004Response.getDecimalLength()));
        //為替グループ
        a001DtoResponse.setExchangeGroup(api004Response.getExchangeGroup());
        //注文ワーニングしきい値
        a001DtoResponse.setIfaWarningThreshold(api004Response.getIfaWarningThreshold());
        //参考レート
        a001DtoResponse.setReferenceRate(api005Response.getReferenceExchangeRate());
        //概算用レート
        a001DtoResponse.setComputeExchangeRate(api005Response.getComputeExchangeRate());
        //デノミ
        a001DtoResponse.setDenominator(api005Response.getBasePrice());
        //スプレッド
        a001DtoResponse.setAdjustPrice(api005Response.getAdjustPrice());

        resDtoList.add(a001DtoResponse);

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");

        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaIfaFxOrderInputA001DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaIfaFxOrderInputA002DtoResponse> orderUpdateMarginA002(IfaIfaFxOrderInputA002DtoRequest dtoReq)
            throws Exception {

        DataList<IfaIfaFxOrderInputA002DtoResponse> dtoRes = new DataList<IfaIfaFxOrderInputA002DtoResponse>();

        List<IfaIfaFxOrderInputA002DtoResponse> resDtoList = new ArrayList<IfaIfaFxOrderInputA002DtoResponse>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaIfaFxOrderInputServiceImplL.orderUpdateMarginA002");
        }

        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        //①利用者のIFAリアルタイム為替取引権限チェックを行う。
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        String operatorId = userAccount.getCcsUserId();
        ListOperatorScopesRes api001Response = new ListOperatorScopesRes();
        try {
            api001Response = foreignAccountService.listOperatorScopes(butenCode, accountNumber, operatorId);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
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
        if (UN_ARRANGED.equals(customerCommon.getForeignStockTradeAccountOpenStatus())
                && UN_ARRANGED.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        //④取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCT_CD);
        if (TRADE_BUY.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_BUY);
        }
        if (TRADE_SELL.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_SELL);
        }
        inputFct003Dto.setCountryCd(COUNTRY_CD);
        inputFct003Dto.setCurrencyCode(dtoReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        //変更管理 #974
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(
                                ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        //⑤通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api003Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api003Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api003Parameter.setServiceGroup(EXCHANGE);
        api003Parameter.setServiceType(IFA_ORDER);
        api003Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api003Parameter.setBuySellCode(dtoReq.getTradeKbn());
        api003Request.setParameter(api003Parameter);
        CheckExchangeCurrencyServiceStatusRes api003Response = new CheckExchangeCurrencyServiceStatusRes();
        try {
            api003Response = exchangeService.checkExchangeCurrencyServiceStatus(api003Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        if (!api003Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        //⑥レート情報を取得する。  
        String currencyCode = dtoReq.getCurrencyCode();
        String buySellCode = dtoReq.getTradeKbn();
        //複数件取得の場合は、先頭要素を利用する。
        ListIfaExchangeTradeRatesRes api005ResponseList = new ListIfaExchangeTradeRatesRes();
        try {
            api005ResponseList = exchangeService.listIfaExchangeTradeRates(butenCode, accountNumber, currencyCode, buySellCode);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        IfaExchangeTradeRate api005Response = api005ResponseList.getIfaExchangeTradeRates().get(0);
        api005Response.setAdjustType(api005Response.getAdjustType());
        //⑧営業日情報を取得する。
        GetIfaExchangeBusinessDateReq api006Request = new GetIfaExchangeBusinessDateReq();
        GetIfaExchangeBusinessDateReq.Parameter api006Parameter = new GetIfaExchangeBusinessDateReq().new Parameter();
        api006Parameter.setExchangeGroup(dtoReq.getExchangeGroup());
        api006Request.setParameter(api006Parameter);

        GetIfaExchangeBusinessDateRes api006Response = new GetIfaExchangeBusinessDateRes();
        try {
            api006Response = exchangeService.getIfaExchangeBusinessDate(api006Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        //⑨出金可能額を取得する。
        MultiGetPossibleWithdrawalsReq api007Request = new MultiGetPossibleWithdrawalsReq();
        MultiGetPossibleWithdrawalsReq.Parameter api007Parameter = new MultiGetPossibleWithdrawalsReq().new Parameter();
        PossibleWithdrawalInput possibleWithdrawals = new PossibleWithdrawalInput();
        possibleWithdrawals.setWithdrawalDate(api006Response.getDepositWithdrawalDate());
        MultiGetPossibleWithdrawalsReq.Header api007Header = new MultiGetPossibleWithdrawalsReq().new Header();
        api007Header.setToken(RequestUtil.getToken(butenCode, accountNumber));
        //リクエスト.売買区分　＝　3（買付）の場合、"JPY"
        //リクエスト.売買区分　＝　1（売却）の場合、リクエスト.通貨コード
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(CURRENCY_CODE_JPY);
        }
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(dtoReq.getCurrencyCode());
        }
        possibleWithdrawals.setAccountKind(GENERAL);
        possibleWithdrawals.setCashingJournalType(EXCHANGE);
        List<PossibleWithdrawalInput> possibleWithdrawalsList = new ArrayList<PossibleWithdrawalInput>();
        possibleWithdrawalsList.add(possibleWithdrawals);
        api007Parameter.setPossibleWithdrawals(possibleWithdrawalsList);
        api007Request.setHeader(api007Header);
        api007Request.setParameter(api007Parameter);
        //複数件取得の場合は、先頭要素を利用する。
        MultiGetPossibleWithdrawalsRes api007ResponseList = new MultiGetPossibleWithdrawalsRes();
        try {
            api007ResponseList
                    = foreignAccountService.multiGetPossibleWithdrawals(api007Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        PossibleWithdrawal api007Response = api007ResponseList.getPossibleWithdrawals().get(0);

        //⑩Athena余力未反映金額を取得する。
        InputFct004Dto inputFct004Dto = new InputFct004Dto();
        inputFct004Dto.setButenCode(customerCommon.getButenCode());
        inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct004Dto.setDepositType(GENERAL);
        inputFct004Dto.setOtcManageNumber("");
        inputFct004Dto.setTradeType(CASH_DEPOSIT);
        OutputFct004Dto outputFct004Dto = fct004.doCheck(inputFct004Dto);

        //⑪注文可能数量を計算する。
        IfaIfaFxOrderInputA002DtoResponse a002DtoResponse = new IfaIfaFxOrderInputA002DtoResponse();
        //＜計算式＞
        //売買区分＝買付
        //API007.出金可能額×API005.レート情報.デノミ÷API005.レート情報.概算用レート（計算結果を小数点以下切り捨て）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a002DtoResponse
                    .setMaxOrderableQuantity(String.valueOf(new BigDecimal(api007Response.getWithdrawalPossibleAmount())
                            .multiply(new BigDecimal(api005Response.getBasePrice()))
                            .divide(new BigDecimal(api005Response.getComputeExchangeRate()), 0, RoundingMode.FLOOR)));
        }
        //売買区分＝売却
        //API007.出金可能額 + FCT004.計算後の余力金額
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            a002DtoResponse
                    .setMaxOrderableQuantity(String.valueOf(new BigDecimal(api007Response.getWithdrawalPossibleAmount())
                            .add(outputFct004Dto.getByingPowerMoneyAfterCalculate())));
        }
        //⑫売買区分＝買付の場合のみ、概算買付可能金額を計算する。
        //＜計算式＞
        //注文可能数量÷API005.レート情報.デノミ×API005.レート情報.概算用レート（計算結果を小数点以下切り捨て）
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            a002DtoResponse.setApproximatePurchaseAmount(
                    String.valueOf(new BigDecimal(a002DtoResponse.getMaxOrderableQuantity())
                            .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR)
                            .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                            .setScale(0, RoundingMode.FLOOR)));
        }
        //⑬外貨指定最大値、円指定最小値、円指定最大値を設定する。
        //＜設定内容＞
        //外貨指定最大値
        //注文可能数量または取引上限のうち小さいほう
        if (new BigDecimal(a002DtoResponse.getMaxOrderableQuantity())
                .compareTo(new BigDecimal(dtoReq.getTradeLimitMax())) < 0) {
            a002DtoResponse.setForeignDesignationMaxValue(a002DtoResponse.getMaxOrderableQuantity());
        } else {
            a002DtoResponse.setForeignDesignationMaxValue(dtoReq.getTradeLimitMax());
        }
        //円指定最小値
        //取引下限×API005.概算用レート÷API005.デノミ（計算結果を小数点以下切り上げ）
        a002DtoResponse.setYenDesignationMinValue(String.valueOf(new BigDecimal(dtoReq.getClosableQuantity())
                .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR)));
        //円指定最大値
        //注文可能数量または取引上限×API005.概算用レート÷API005.デノミ（計算結果を小数点以下切り上げ）のうち小さいほう
        BigDecimal yenDesignationMaxValueCalcValue = new BigDecimal(a002DtoResponse.getForeignDesignationMaxValue())
                .multiply(new BigDecimal(api005Response.getComputeExchangeRate()))
                .divide(new BigDecimal(api005Response.getBasePrice()), 0, RoundingMode.FLOOR);
        a002DtoResponse.setYenDesignationMaxValue(String.valueOf(yenDesignationMaxValueCalcValue));
        //⑭概算金額・数量算出
        //概算受渡金額、概算外貨数量を初期化する。
        //数量入力（外貨）に入力があり、かつ入力チェックエラーがない場合、
        //概算受渡金額を計算する。
        //＜計算式＞
        //数量入力（外貨）×画面.概算用レート÷画面.デノミ
        //（計算結果を小数点以下切り上げ）
        a002DtoResponse.setApproximateDeliveryAmount(String.valueOf(
                new BigDecimal(StringUtil.isNullOrEmpty(dtoReq.getForeignVolume()) ? "0" : dtoReq.getForeignVolume())
                        .multiply(new BigDecimal(dtoReq.getComputeExchangeRate()))
                        .divide(new BigDecimal(dtoReq.getDenominator()), 0, RoundingMode.FLOOR)));
        //数量入力（円）に入力があ、かつ入力チェックエラーがない場合、
        //概算外貨数量を計算する。
        //＜計算式＞
        //画面.数量入力（円）×画面.デノミ÷画面.概算用レート
        //（計算結果を小数点以下切り捨て）       
        a002DtoResponse.setApproximateForeignCount(String.valueOf(
                new BigDecimal(StringUtil.isNullOrEmpty(dtoReq.getDomesticVolume()) ? "0" : dtoReq.getDomesticVolume())
                        .multiply(new BigDecimal(dtoReq.getDenominator()))
                        .divide(new BigDecimal(dtoReq.getComputeExchangeRate()), 0, RoundingMode.FLOOR)));
        //⑮画面に0, RoundingMode.FLOORを更新する。
        //更新日時　（システム共通情報.システム日時を再設定）
        a002DtoResponse.setUpdateTime(DateUtil.format(DateUtil.SEPARATED_YYYYMMDD_HHMMSS));

        //参考レート
        a002DtoResponse.setReferenceRate(api005Response.getReferenceExchangeRate());
        //概算用レート
        a002DtoResponse.setComputeExchangeRate(api005Response.getComputeExchangeRate());
        //適用スプレッド_デノミ
        a002DtoResponse.setDenominator(api005Response.getBasePrice());
        //適用スプレッド、
        a002DtoResponse.setAdjustPrice(api005Response.getAdjustPrice());

        resDtoList.add(a002DtoResponse);

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");

        return dtoRes;
    }

    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaIfaFxOrderInputA008DtoRequest
     * Dto レスポンス：IfaIfaFxOrderInputA008DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaIfaFxOrderInputA008DtoResponse> orderConfirmationMarginA008(
            IfaIfaFxOrderInputA008DtoRequest dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaIfaFxOrderInputServiceImplL.orderConfirmationMarginA008");
        }

        DataList<IfaIfaFxOrderInputA008DtoResponse> dtoRes = new DataList<IfaIfaFxOrderInputA008DtoResponse>();

        List<IfaIfaFxOrderInputA008DtoResponse> resDtoList = new ArrayList<IfaIfaFxOrderInputA008DtoResponse>();

        IfaIfaFxOrderInputA008DtoResponse a008DtoResponse = new IfaIfaFxOrderInputA008DtoResponse();

        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        //①利用者のIFAリアルタイム為替取引権限チェックを行う。
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        String operatorId = userAccount.getCcsUserId();
        ListOperatorScopesRes api001Response = new ListOperatorScopesRes();
        try {
            api001Response = foreignAccountService.listOperatorScopes(butenCode, accountNumber, operatorId);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
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
        if (UN_ARRANGED.equals(customerCommon.getForeignStockTradeAccountOpenStatus())
                && UN_ARRANGED.equals(customerCommon.getForeignSecurityTradeAccountOpenStatus())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_CMN_FOREIGNSEACCOUNT_NOTOPEN));
            return dtoRes;
        }
        //④取引コースによる媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(customerCommon.getButenCode());
        inputFct003Dto.setAccountNumber(customerCommon.getAccountNumber());
        inputFct003Dto.setProductCd(PRODUCT_CD);
        if (TRADE_BUY.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_BUY);
        }
        if (TRADE_SELL.equals(dtoReq.getTradeKbn())) {
            inputFct003Dto.setTradeCd(FOREIGN_TRADE_SELL);
        }
        inputFct003Dto.setCountryCd(COUNTRY_CD);
        inputFct003Dto.setCurrencyCode(dtoReq.getCurrencyCode());
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        //可：次の処理へ。
        //不可：媒介不可エラーを返す。
        //変更管理 #974
        for (OutputFct003Dto.MediatePropriety mediatePropriety : outputFct003Dto.getMediateProprietyList()) {
            if (INTERMEDIARY_VALUE_0.equals(mediatePropriety.getMediatePropriety())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(
                                ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE, new String[] { MSG_DISPLAY_TARGET_TRADE_1 }));
                return dtoRes;
            }
        }
        //⑤通貨別サービスステータスチェックを行う。
        CheckExchangeCurrencyServiceStatusReq api003Request = new CheckExchangeCurrencyServiceStatusReq();
        CheckExchangeCurrencyServiceStatusReq.Parameter api003Parameter = new CheckExchangeCurrencyServiceStatusReq().new Parameter();
        api003Parameter.setServiceGroup(EXCHANGE);
        api003Parameter.setServiceType(IFA_ORDER);
        api003Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api003Parameter.setBuySellCode(dtoReq.getTradeKbn());
        api003Request.setParameter(api003Parameter);
        CheckExchangeCurrencyServiceStatusRes api003Response = new CheckExchangeCurrencyServiceStatusRes();
        try {
            api003Response = exchangeService.checkExchangeCurrencyServiceStatus(api003Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        if (!api003Response.isAvailable()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_EXT_ORDEREXECPTION_UNAVAILABLE));
            return dtoRes;
        }
        //⑥注文確認APIの入力パラメータ「為替注文金額」のセット内容となるActionパラメータ.注文金額を決定する。
        //為替注文金額(注文金額)
        BigDecimal orderAmount = new BigDecimal("0");
        //数量の指定方法＝外貨で指定、または、売却方法＝すべて売却の場合
        //数量の指定方法＝円で指定の場合
        if (FX_QUANTITY_DESIGNATION_METHOD_1.equals(dtoReq.getQuantityDesignationMethod())
                || SELL_METHOD_2.equals(dtoReq.getSaleMethod())) {
            //画面.数量入力（外貨）
            orderAmount = new BigDecimal(
                    StringUtil.isNullOrEmpty(dtoReq.getForeignVolume()) ? "0" : dtoReq.getForeignVolume());
            dtoReq.setOrderAmount(String.valueOf(orderAmount));
        } else if (FX_QUANTITY_DESIGNATION_METHOD_2.equals(dtoReq.getQuantityDesignationMethod())) {
            //画面.概算外貨数量
            orderAmount = new BigDecimal(StringUtil.isNullOrEmpty(dtoReq.getApproximateForeignCount()) ? "0" : dtoReq.getApproximateForeignCount());
            dtoReq.setOrderAmount(String.valueOf(orderAmount));
        }
        //⑦注文金額≦ワーニングしきい値であることをチェックする。
        if (orderAmount.compareTo(new BigDecimal(dtoReq.getWarningThreshold())) > 0) {
            a008DtoResponse.setOrderWarningexceedingThreshold(
                    MSG_PREFIX + dtoReq.getWarningThreshold() + dtoReq.getCurrencyCode() + MSG_SUFFIX);
        } else {
            a008DtoResponse.setOrderWarningexceedingThreshold("");
        }
        //⑧売買区分＝売却の場合、営業日情報を取得する。
        GetIfaExchangeBusinessDateReq api006Request = new GetIfaExchangeBusinessDateReq();
        GetIfaExchangeBusinessDateReq.Parameter api006Parameter = new GetIfaExchangeBusinessDateReq().new Parameter();
        api006Parameter.setExchangeGroup(dtoReq.getExchangeGroup());
        api006Request.setParameter(api006Parameter);
        GetIfaExchangeBusinessDateRes api006Response = new GetIfaExchangeBusinessDateRes();

        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            try {
                api006Response = exchangeService.getIfaExchangeBusinessDate(api006Request);
            }  catch (Exception e) {
                return cometCommonService.checkBussinessException(
                        IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                        e
                );
            }
        }
        //⑨売買区分＝売却の場合、出金可能額を取得する。
        MultiGetPossibleWithdrawalsReq api007Request = new MultiGetPossibleWithdrawalsReq();
        MultiGetPossibleWithdrawalsReq.Parameter api007Parameter = new MultiGetPossibleWithdrawalsReq().new Parameter();
        PossibleWithdrawalInput possibleWithdrawals = new PossibleWithdrawalInput();
        MultiGetPossibleWithdrawalsReq.Header api007Header = new MultiGetPossibleWithdrawalsReq().new Header();
        api007Header.setToken(RequestUtil.getToken(butenCode, accountNumber));
        //リクエスト.売買区分　＝　3（買付）の場合、"JPY"
        //リクエスト.売買区分　＝　1（売却）の場合、リクエスト.通貨コード
        if (TRADE_TYPE_BUY.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(CURRENCY_CODE_JPY);
        }
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            possibleWithdrawals.setCurrencyCode(dtoReq.getCurrencyCode());
        }
        possibleWithdrawals.setWithdrawalDate(api006Response.getDepositWithdrawalDate());
        possibleWithdrawals.setAccountKind(GENERAL);
        possibleWithdrawals.setCashingJournalType(EXCHANGE);
        List<PossibleWithdrawalInput> possibleWithdrawalsList = new ArrayList<PossibleWithdrawalInput>();
        possibleWithdrawalsList.add(possibleWithdrawals);
        api007Parameter.setPossibleWithdrawals(possibleWithdrawalsList);
        api007Request.setHeader(api007Header);
        api007Request.setParameter(api007Parameter);
        //複数件取得の場合は、先頭要素を利用する。
        MultiGetPossibleWithdrawalsRes api007ResponseList = new MultiGetPossibleWithdrawalsRes();
        PossibleWithdrawal api007Response = new PossibleWithdrawal();
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            try {
                api007ResponseList = foreignAccountService.multiGetPossibleWithdrawals(api007Request);
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(
                        IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                        e
                );
            }
            
            api007Response = api007ResponseList.getPossibleWithdrawals().get(0);
        }
        //⑩売買区分＝売却の場合、Athena余力未反映金額を取得する。
        OutputFct004Dto outputFct004Dto = new OutputFct004Dto();
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            InputFct004Dto inputFct004Dto = new InputFct004Dto();
            inputFct004Dto.setButenCode(customerCommon.getButenCode());
            inputFct004Dto.setAccountNumber(customerCommon.getAccountNumber());
            inputFct004Dto.setDepositType(GENERAL);
            inputFct004Dto.setOtcManageNumber("");
            inputFct004Dto.setTradeType(CASH_DEPOSIT);
            outputFct004Dto = fct004.doCheck(inputFct004Dto);
        }
        //⑪売買区分＝売却の場合、注文可能数量を計算する。
        BigDecimal maxOrderableQuantity = new BigDecimal("0");
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            maxOrderableQuantity = new BigDecimal(api007Response.getWithdrawalPossibleAmount())
                    .add(outputFct004Dto.getByingPowerMoneyAfterCalculate());
        }
        //⑫売買区分＝売却の場合、注文金額≦注文可能数量であることをチェックする。
        if (TRADE_TYPE_SELL.equals(dtoReq.getTradeKbn())) {
            if (orderAmount.compareTo(maxOrderableQuantity) > 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_EXT_COUNTUNIT_OVERFLOW,
                                new String[] { String.valueOf(orderAmount),
                                        String.valueOf(api007Response.getWithdrawalPossibleAmount()),
                                        String.valueOf(outputFct004Dto.getOtcBuyingContractAmountToday()),
                                        String.valueOf(outputFct004Dto.getContractAmountTodayWithinForeignBond()),
                                        String.valueOf(dtoReq.getCurrencyCode()) }));
                return dtoRes;
            }
        }

        //⑬注文確認を行う。
        ConfirmIfaExchangeCreatedOrderReq api008Request = new ConfirmIfaExchangeCreatedOrderReq();
        ConfirmIfaExchangeCreatedOrderReq.Header api008Header = new ConfirmIfaExchangeCreatedOrderReq().new Header();
        ConfirmIfaExchangeCreatedOrderReq.Parameter api008Parameter = new ConfirmIfaExchangeCreatedOrderReq().new Parameter();
        api008Header.setToken(RequestUtil.getToken(butenCode, accountNumber));
        api008Header.setOperator_id(userAccount.getCcsUserId());
        api008Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api008Parameter.setBuySellCode(dtoReq.getTradeKbn());
        api008Parameter.setAccountKind(GENERAL);
        api008Parameter.setOrderAmount(dtoReq.getOrderAmount());
        api008Parameter.setSellMethod(SELL_PART);
        api008Request.setHeader(api008Header);
        api008Request.setParameter(api008Parameter);
        ConfirmIfaExchangeCreatedOrderRes api008Response = new ConfirmIfaExchangeCreatedOrderRes();
        try {
            api008Response = exchangeService.confirmIfaExchangeCreatedOrder(api008Request);

        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }
        //⑭通貨情報を取得する。
        GetExchangeTradeCurrencyReq api004Request = new GetExchangeTradeCurrencyReq();
        GetExchangeTradeCurrencyReq.Parameter api004Parameter = new GetExchangeTradeCurrencyReq().new Parameter();
        api004Parameter.setCurrencyCode(dtoReq.getCurrencyCode());
        api004Request.setParameter(api004Parameter);
        GetExchangeTradeCurrencyRes api004Response = new GetExchangeTradeCurrencyRes();
        try {
            api004Response = exchangeService.getExchangeTradeCurrency(api004Request);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }
        api004Response.setExchangeGroup(api004Response.getExchangeGroup());

        //⑮注文確認画面表示用項目を取得する。
        String currencyCode = dtoReq.getCurrencyCode();
        String buySellCode = dtoReq.getTradeKbn();
        //複数件取得の場合は、先頭要素を利用する。
        ListIfaExchangeTradeRatesRes api005ResponseList = new ListIfaExchangeTradeRatesRes();
        IfaExchangeTradeRate api005Response = new IfaExchangeTradeRate();
        try {
            api005ResponseList = exchangeService.listIfaExchangeTradeRates(butenCode, accountNumber, currencyCode, buySellCode);
            api005Response = api005ResponseList.getIfaExchangeTradeRates().get(0);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, null, null),
                    e
            );
        }

        api005Response.setAdjustType(api005Response.getAdjustType());
        //API005.上乗せ区分が「パーセント指定（上乗せ金額）」の場合、レスポンス.上乗せ金額(パーセント)を算出する。
        if (ADJUST_PERCENT.equals(api005Response.getAdjustType())) {
            //上乗せ金額(パーセント)＝API005.上乗せ金額(パーセント指定)-100
            a008DtoResponse.setAdjustPercent(
                    String.valueOf(new BigDecimal(api005Response.getAdjustPercent()).subtract(new BigDecimal(100))));
        }

        //通貨コード
        a008DtoResponse.setCurrencyCode(api008Response.getCurrencyCode());
        //通貨名
        a008DtoResponse.setCurrency(api008Response.getCurrencyName());
        //売却方法
        a008DtoResponse.setSaleMethod(dtoReq.getSaleMethod());
        //売買区分
        //変更管理 #974
        a008DtoResponse.setTradeKbn(dtoReq.getTradeKbn());
        //為替注文金額
        a008DtoResponse.setOrderAmount(String.valueOf(api008Response.getOrderAmount()));
        //約定日時
        a008DtoResponse.setTradeDateTime(api008Response.getExecutionDatetime());
        //受渡日
        a008DtoResponse.setSettlementDate(api008Response.getValueDate());
        //為替レート
        a008DtoResponse.setFxRate(api008Response.getExchangeRate());
        //為替レート日時
        a008DtoResponse.setExchangeRateDateTime(api008Response.getRateDatetime());
        //受渡金額（円貨）
        a008DtoResponse.setYenDeliveryAmount(api008Response.getNetAmount());
        //受渡金額（円貨）
        a008DtoResponse.setWarningThreshold(String.valueOf(dtoReq.getWarningThreshold()));
        //適用スプレッド
        a008DtoResponse.setSelectedCurrencyInfo(api005Response.getAdjustPrice());
        //上乗せ区分
        a008DtoResponse.setFxRateAdditionalType(api005Response.getAdjustType());
        //上乗せ金額(金額)
        a008DtoResponse.setAdjustAmount(api005Response.getAdjustAmount());
        //数量の指定方法
        a008DtoResponse.setQuantityDesignationMethod(dtoReq.getQuantityDesignationMethod());
        //小数部有効桁数
        a008DtoResponse.setDecimalLength(String.valueOf(api004Response.getDecimalLength()));

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        resDtoList.add(a008DtoResponse);
        return dtoRes;
    }
}
