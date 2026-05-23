package com.sbisec.helios.ap.brokerageMenu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ibm.icu.math.BigDecimal;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ExchangeService;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListExchangeTradeCurrenciesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListIfaExchangeTradeRatesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.IfaExchangeTradeRate;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesReq;
import com.sbisec.helios.ap.athena.protocol.exchange.master.ListOperatorScopesRes;
import com.sbisec.helios.ap.athena.protocol.exchange.master.dto.ExchangeTradeCurrency; 
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.service.IfaIfaCurrencyDealtListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListA001RequestDto;
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListA001ResponseDto;
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListElements;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0503-01 画面名：【IFA】取扱通貨一覧 アクションID：A001 アクション名：初期化

 * @author 松尾
 *
 * 　　　　2023/08/21 新規作成
 */
@Component(value = "cmpIfaIfaCurrencyDealtListService")
public class IfaIfaCurrencyDealtListServiceImpl implements IfaIfaCurrencyDealtListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaIfaCurrencyDealtListServiceImpl.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private CodeListService codelistservice;
    
    @Autowired
    private ExchangeService exchangeService;
    
    @Autowired
    private ForeignAccountService foreignAccountService;

    @Autowired
    private CometCommonService cometCommonService;
    
    /** 為替取引権限チェックエラー   */
    public static final String ERRORS_INSUFFICIENT_PRIVILEGE = "errors.ext.orderExecution.insufficientPrivilege";
    
    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 取引停止口座エラー */
    public static final String ERRORS_CORCE_OUT_OF_SERVICE = "errors.frs.selectedAccount.outOfService";
    
    /** 取引不可エラー */
    public static final String ERRORS_NOT_USE_SERVICE = "errors.frs.selectedAccount.outOfService";
    
    /** 国内信用口座開設状況のチェックエラー */
    public static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";

    /** CCSIDが未登録のためご利用できません。 */
    private static final String ERRORS_CMN_CCSID_UNREGISTERED = "errors.cmn.ccsid.unregistered";
    
    /** 注意情報エラー(仮置き) */
    public static final String ERRORS_WARN_ERROR = "errors.warningCheck.confirm";
    
    /** お知らせエラー */
    public static final String ERRORS_INFO_ERROR = "errors.informationCheck";
    
    /** アラートチェックエラー(仮置き) */
    public static final String ERRORS_ALERT_CHECK_ERROR = "errors.alertCheck";
    
    /** 注文発注前DB登録エラー */
    public static final String ERRORS_PRE_ORDER_EXECUTION_ERROR = "errors.frs.preOrderExecution.failed";
    
    /** 信用新規注文エラー(仮置き)   */
    public static final String ERRORS_MARGIN_ERROR = "errors.api.domesticMargin.confirm";
    
    /** 注文発注後DB登録エラー(仮置き) */
    public static final String ERRORS_POST_ORDER_EXECUTION_ERROR = "warnings.frs.postOrderExecution.completed";
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 権限チェック有効値 */
    public static final String AUTH_VALUE = "1";
    
    /** 注文種別：通常/逆指値 */
    public static final String ORDER_KIND_NORMAL = "0";
    
    /** 注文種別：IFD */
    public static final String ORDER_KIND_IFD = "2";
    
    /** 取引停止口座エラー値 */
    public static final String ACCOUNT_ERROR_VALUE = "1";
    
    /** 媒介不可エラー */
    public static final String MEDIATE_ERROR = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 媒介可取引有無エラー値 */
    public static final String MEDIATE_ERROR_VALUE = "0";
    
    /** オペレータ権限有 */
    public static final String EXCHANGE_TRADE = "EXCHANGE_TRADE";
    
    /** 外貨建商品取引口座開設状況 未開設値 */
    public static final String FOREIGN_SECURITY_TRADE_ACCOUNT_NOT_OPEN = "0";
    
    /** 外国株式取引口座開設状況 未開設値 */
    public static final String FOREIGN_STOCK_TRADE_ACCOUNT_NOT_OPEN = "0";
    
    /** 正常終了値 */
    public static final String RETURN_CODE_SUCCESS = "0";
    
    /** 正常終了メッセージ */
    public static final String RETURN_MESSEAGE_SUCCESS = "";
    
    /** 外貨建口座未開設エラー */
    public static final String ERRORS_FOREIGN_ACCOUNT_NOT_OPEN = "errors.cmn.foreignSecuritiesAccount.notOpen";
    
    /** 売買区分 買付 */
    public static final String BUY = "BUY";
    
    /** 売買区分 売却 */
    public static final String SELL = "SELL";
    
    /** 証券金銭種別　外貨*/
    private static final String FOREIGN_CURRENCY = "98";
    
    /**
    
     * Dto リクエスト：IfaIfaCurrencyDealtListA001DtoRequest Dto
     * レスポンス：IfaIfaCurrencyDealtListA001DtoResponse model 
     * リクエスト：RequestModel
     * レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     */
    public DataList<IfaIfaCurrencyDealtListA001ResponseDto> initializeA001(IfaIfaCurrencyDealtListA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaIfaCurrencyDealtListA001ResponseDto> dtoRes = new DataList<IfaIfaCurrencyDealtListA001ResponseDto>();
        List<IfaIfaCurrencyDealtListA001ResponseDto> resDto = new ArrayList<IfaIfaCurrencyDealtListA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaIfaCurrencyDealtListServiceImplL.initializeA001");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        String operatorId = userAccount.getCcsUserId();

        // ユーザ共通情報 .CCSログイン用IDが未設定(Null または空文字）の場合：取引不可エラーを返す
        if (StringUtils.isEmpty(userAccount.getCcsUserId())) {
            DataList<IfaIfaCurrencyDealtListA001ResponseDto> response = IfaCommonUtil.createDataList(
                    List.of(),
                    ErrorLevel.FATAL,
                    ERRORS_CMN_CCSID_UNREGISTERED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_CCSID_UNREGISTERED)
            );

            return response;
        }
        
        // ①利用者のIFAリアルタイム為替取引権限チェックを行う。
        ListOperatorScopesRes ifaRealtimeFxtradeAuthority = null;
        try {            
            ifaRealtimeFxtradeAuthority = foreignAccountService.listOperatorScopes(butenCode, accountNumber, operatorId);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // 権限あり：次の処理へ。
        // 権限なし：権限なしエラーを返す。
        if (ifaRealtimeFxtradeAuthority.getOperatorScopes() == null || ifaRealtimeFxtradeAuthority.getOperatorScopes().isEmpty() || !(StringUtils.equals(EXCHANGE_TRADE, ifaRealtimeFxtradeAuthority.getOperatorScopes().get(0)))) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_INSUFFICIENT_PRIVILEGE,
                    IfaCommonUtil.getMessage(ERRORS_INSUFFICIENT_PRIVILEGE));
            return dtoRes;
        }
        
        // ②利用者の口座に対する権限チェックを行う。
        // 本来は顧客情報.部店コードと顧客情報.口座番号を利用して取得する
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(cc.getButenCode());
        inputFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto fct001Dto = fct001.doCheck(inputFct001Dto);
        // ■対象顧客参照権限有無＝権限ありの場合
        // ■取引停止フラグ＝取引停止口座ではないの場合：次の処理へ。
        if (StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_VALUE)) {
            // ■取引停止フラグ＝取引停止口座の場合：取引停止口座エラーを返す。
            if (StringUtils.equals(fct001Dto.getTradeSuspendFlag(), ACCOUNT_ERROR_VALUE)) {
                dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CORCE_OUT_OF_SERVICE,
                        IfaCommonUtil.getMessage(ERRORS_CORCE_OUT_OF_SERVICE));
                return dtoRes;
            }
            // ■対象顧客参照権限有無＝権限なし：権限なしエラーを返す。
        } else {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS, IfaCommonUtil
                    .getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // ③顧客共通情報の「外貨建取引口座開設状況」「外国株式取引口座開設状況」より、外貨建口座取引開設状況をチェックを行う。
        // どちらか一方でも「開設済」：次の処理へ。
        // どちらも「未開設」：外貨建口座未開設エラーを返す。
        if (StringUtils.equals(cc.getForeignSecurityTradeAccountOpenStatus(), FOREIGN_SECURITY_TRADE_ACCOUNT_NOT_OPEN)
                && StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                        FOREIGN_STOCK_TRADE_ACCOUNT_NOT_OPEN)) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_FOREIGN_ACCOUNT_NOT_OPEN,
                    IfaCommonUtil.getMessage(ERRORS_FOREIGN_ACCOUNT_NOT_OPEN));
            return dtoRes;
        }
        
        // ④取引コースによる媒介可否チェックを行う。
        // 媒介可取引有無が1（あり）：次の処理へ。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setButenCode(cc.getButenCode());
        inputFct003Dto.setAccountNumber(cc.getAccountNumber());
        inputFct003Dto.setProductCd(FOREIGN_CURRENCY);
        OutputFct003Dto fct003Dto = fct003.doCheck(inputFct003Dto);
        // 媒介可取引有無が0（なし）：媒介不可エラーを返す。
        if (StringUtils.equals(fct003Dto.getMediateAbleTradeFlag(), MEDIATE_ERROR_VALUE)) {
            String codeName = codelistservice.getValue("MSG_DISPLAY_TARGET_TRADE", "1");
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, MEDIATE_ERROR,
                    IfaCommonUtil.getMessage(MEDIATE_ERROR, new String[] { codeName }));
            return dtoRes;
        }
        
        //⑧レート情報を取得する。
        ListExchangeTradeCurrenciesReq listIfaExchangeTradeRatesIn = new ListExchangeTradeCurrenciesReq();
        ListExchangeTradeCurrenciesReq.Parameter listIfaExchangeTradeRatesParameter = new ListExchangeTradeCurrenciesReq().new Parameter();
        listIfaExchangeTradeRatesIn.setParameter(listIfaExchangeTradeRatesParameter);
        
        // ⑤通貨情報を取得する。
        ListExchangeTradeCurrenciesRes currencyInfo = null;
        try {
            currencyInfo = exchangeService.listExchangeTradeCurrencies(listIfaExchangeTradeRatesIn);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // ⑥レート情報を取得する。
        String currencyCode = null;
        String buySellCode = null;
        ListIfaExchangeTradeRatesRes ratesInfo01 = null;
        try {            
            ratesInfo01 = exchangeService.listIfaExchangeTradeRates(butenCode, accountNumber, currencyCode, buySellCode);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        // ⑦"通貨リストを編集する。
        // API003.通貨アイテムを軸に、API003.通貨コード＝API004.レート情報.通貨コードで紐づけ、API003にもAPI004にも存在する通貨のみをセットする。
        // セット項目は画面遷移パラメータ参照。（ソート順はAPI003の通貨順に従う）"
        
        List<IfaIfaCurrencyDealtListElements> resList = new ArrayList<IfaIfaCurrencyDealtListElements>();
        HashMap<String, Integer> listMap = new HashMap<String, Integer>();
        
        for (ExchangeTradeCurrency api003rec : currencyInfo.getCurrencies()) {
            for (IfaExchangeTradeRate api004rec : ratesInfo01.getIfaExchangeTradeRates()) {
                if (StringUtils.equals(api003rec.getCurrencyCode(), api004rec.getCurrencyCode())) {
                    // Outputのデータを作る
                    if (listMap.containsKey(api004rec.getCurrencyCode())) {
                        // BUYの場合
                        if (StringUtils.equals(api004rec.getBuySellCode(), BUY)) {
                            // 通貨リスト.買付参考レート
                            resList.get(listMap.get(api004rec.getCurrencyCode())).setPurchaseReferenceRate(api004rec.getReferenceExchangeRate());
                        // SELLの場合
                        } else {
                            // 通貨リスト.売却参考レート
                            resList.get(listMap.get(api004rec.getCurrencyCode())).setReferenceRateForSale(api004rec.getReferenceExchangeRate());
                        }
                    } else {
                        IfaIfaCurrencyDealtListElements res = new IfaIfaCurrencyDealtListElements();
                        // 通貨コード
                        res.setCurrencyCode(api003rec.getCurrencyCode());
                        // 通貨リスト.通貨名
                        res.setCurrencyName(api003rec.getCurrencyName());
                        // 通貨リスト.小数部有効桁数
                        res.setDecimalLength(api003rec.getDecimalLength());
                        // BUYの場合
                        if (StringUtils.equals(api004rec.getBuySellCode(), BUY)) {
                            // 通貨リスト.買付参考レート
                            res.setPurchaseReferenceRate(api004rec.getReferenceExchangeRate());
                        // SELLの場合
                        } else if (StringUtils.equals(api004rec.getBuySellCode(), SELL)) {
                            // 通貨リスト.売却参考レート
                            res.setReferenceRateForSale(api004rec.getReferenceExchangeRate());
                        }
                        
                        listMap.put(api004rec.getCurrencyCode(), resList.size());
                        
                        // リストに詰める
                        resList.add(res);
                    }
                }
            }
        }
        IfaIfaCurrencyDealtListA001ResponseDto dto = new IfaIfaCurrencyDealtListA001ResponseDto();
        dto.setCurrencyList(resList);
        resDto.add(dto);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS, RETURN_MESSEAGE_SUCCESS);
        return dtoRes;
    }
    
}
