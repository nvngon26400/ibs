package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignInformationService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.MarginOrderInput;
import com.sbisec.helios.ap.athena.protocol.fstock.order.CreateForeignStockMarginOrderResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockCreatedMarginOrderInitializationResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockAttentionSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.protocol.information.CreateMarketPriceTicketResp;
import com.sbisec.helios.ap.athena.protocol.information.GetMarketPriceHashResp;
import com.sbisec.helios.ap.athena.protocol.information.ListMarketPricesResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct018;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct018Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignMarginTradeNewOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeNewOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignMarginTradeNewOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.AlertContentsConfirm;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.ConfirmItem;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeNewOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.OrderInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.PriceBaseInfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.model.ApiStatusModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeNewOrderConfirmService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.AmericaStockUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0303-01_2
 * 画面名：米株信用取引新規注文確認
 *
 * @author SCSK 金志
 *
 */
@Component(value = "cmpIfaForeignMarginTradeNewOrderConfirmService")
public class IfaForeignMarginTradeNewOrderConfirmServiceImpL implements IfaForeignMarginTradeNewOrderConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginTradeNewOrderConfirmServiceImpL.class);
    
    @Autowired
    private IfaForeignMarginTradeNewOrderConfirmDao dao;
    
    @Autowired
    private ForeignInformationService foreignInformationService;
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct018 fct018;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private CodeListService codelistservice;

    @Autowired
    private CometCommonService cometCommonService;
    
    /** 米国 */
    private static final String US = "US";
    
    /** 権限あり */
    private static final String AUTHORIZED = "1";
    
    /** 取引停止口座 */
    private static final String STOPTRADEACCOUNT = "1";
    
    /** あり */
    private static final String EXIST = "1";
    
    /** 未開設 */
    private static final String NOESTABLISHMENT = "0";
    
    /** 半角スペース */
    private static final String HALFWIDTH_SPACE = " ";
    
    /** NG */
    private static final String NG = "NG";
    
    /** SBI証券本店 */
    private static final String SBI_MAIN = "1";
    
    /** SBI証券支店 */
    private static final String SBI_BRANCH = "2";
    
    /** 注文数量 */
    private static final String ORDER_COUNT = "注文数量";
    
    /** 取引単位 */
    private static final String TRADE_UNIT = "取引単位";
    
    /** 注文単価（指値） */
    private static final String ORDER_PRICE = "注文単価（指値）";
    
    /** 注文単価（逆指値）*/
    private static final String REVERSE_ORDER_PRICE = "注文単価（逆指値）";
    
    /** 参照価格（逆指値） */
    private static final String REFERENCE_PRICE = "参照価格（逆指値）";
    
    /** 外国株式 */
    private static final String FOREIGN_STOCKS = "15";
    
    /** 信用新規 */
    private static final String MARGIN_OPEN = "MARGIN_OPEN";
    
    /** 期間指定 */
    private static final String CARRY_OVER_ORDER = "1";
    
    /** 当日注文 */
    private static final String TODAY_ORDER = "0";
    
    /** 取引制限チェック.通貨コード */
    private static final String CURRENCY_CODE = "999";
    
    /** 未確認 */
    private static final String NOT_CONFIRM = "0";
    
    /** 電話 */
    private static final String PHONE = "PHONE";
    
    /** 最小値 */
    private static final String MIN_VALUE = "0";
    
    /** 最大値 */
    private static final String MAX_VALUE = "999999999.99";
    
    /** 信用新規買 */
    private static final String NEW_PURCHASE_KUBUN = "2";
    
    /** 信用新規売 */
    private static final String NEW_SALES_KUBUN = "3";
    
    /** 対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** IPアドレス */
    private static final String IP_ADDRESS = "127.0.0.1";
    
    /** X-App-User-Agent */
    private static final String USER_AGENT = "Edge";
    
    /** 9営業日 */
    private static final int MAX_ORDER_TERM_LIST = 9;
    
    /** 取引上限数量の上限なし */
    private static final BigDecimal LIMIT_MAX = new BigDecimal("9999999999");
    
    /** 信用新規買 */
    private static final String BUY = "BUY";
    
    /** 信用新規売 */
    private static final String SELL = "SELL";
    
    // 価格条件
    private enum PriceConditons {
        
        // 指値
        LIMIT("1"),
        // 成行
        MARKET("2"),
        // 逆指値/指値
        STOP_PRICE_LIMIT("3"),
        // 逆指値/成行
        STOP_PRICE_MARKET("4");
        
        private final String value;
        
        PriceConditons(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    enum MessageId {
        
        // errors.butenAccountNotExist
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.foreignStockAccountCheck
        ERRORS_FOREIGNSTOCKACCOUNTCHECK("errors.foreignStockAccountCheck"),
        // errors.frs.foreignStockAccount.notOpen#1
        ERRORS_NOTOPEN("errors.frs.foreignStockAccount.notOpen#1"),
        // errors.frs.serviceHours.outOfRange
        ERRORS_OUTOFRANGE("errors.frs.serviceHours.outOfRange"),
        // errors.frs.orderExecution.insufficientPrivilege
        ERRORS_INSUFFICIENTPRIVILEGE("errors.frs.orderExecution.insufficientPrivilege.MarginTrading"),
        // errors.frs.orderValue.outOfRange
        ERRORS_ORDERVALUEOUTOFRANGE("errors.frs.orderValue.outOfRange"),
        // errors.frs.orderingCondition.inconsistent
        ERRORS_INCONSISTENT("errors.frs.orderingCondition.inconsistent"),
        // errors.frs.orderTerms.outOfRange
        ERRORS_TERMS_OUTOFRANGE("errors.frs.orderTerms.outOfRange"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICE_ERROR_CHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATIONCHECK("errors.informationCheck"),
        // errors.cmn.information.occurs
        ERRORS_INFORMATION_OCCURS("errors.cmn.information.occurs"),
        // errors.frs.preOrderExecution.failed
        ERRORS_PREORDEREXECUTION_FAILED("errors.frs.preOrderExecution.failed"),
        // warnings.frs.postOrderExecution.completed
        WARNINGS_POSTORDEREXECUTION_COMPLETED("warnings.frs.postOrderExecution.completed"),
        // errors.cmn.orderExecution.failed
        ERRORS_CMN_ORDER_EXECUTION_FAILD("errors.cmn.orderExecution.failed");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA004RequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 更新の際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto> updateA004(
            IfaForeignMarginTradeNewOrderConfirmA004RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderConfirmServiceImplL.updateA004");
        }
        
        // 初期化
        DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto> dtoRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto>();
        List<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderConfirmA004ResponseDto>();
        IfaForeignMarginTradeNewOrderConfirmA004ResponseDto resDto = new IfaForeignMarginTradeNewOrderConfirmA004ResponseDto();
        
        // ⓵利用者の口座に対する権限チェックを行う
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 顧客共通情報.部店コードをセット
        fct001Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無==1(権限あり)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), AUTHORIZED)) {
            // 顧客情報を格納し次の処理へ
        } else {
            // 利用者の口座に対する権限チェックを行う。
            // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // FCT001.取引停止フラグ==1（取引停止口座）の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOPTRADEACCOUNT)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            return dtoRes;
        }
        
        // ⓶口座の契約締結前交付書面コードのチェック　および共同募集顧客(共募顧客)のチェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 顧客共通情報.部店コードをセット
        fct003Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別をセット
        fct003Req.setProductCd(FOREIGN_STOCKS);
        // リクエストパラメータ.取引種別をセット
        fct003Req.setTradeCd(dtoReq.getTradeCd());
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規買"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）
        // または
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規売"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）が存在しない場合
        int num = 0;
        for (MediatePropriety loop : fct003Res.getMediateProprietyList()) {
            if ((StringUtils.equals(loop.getTradeClass(), NEW_PURCHASE_KUBUN)
                    && StringUtils.equals(loop.getMediatePropriety(), EXIST))
                    || (StringUtils.equals(loop.getTradeClass(), NEW_SALES_KUBUN)
                            && StringUtils.equals(loop.getMediatePropriety(), EXIST))) {
                // 存在した場合プラス
                num++;
            }
        }
        if (num == 0) {
            // レスポンスに媒介不可エラーを設定する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_UNAVAILABLE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }
        
        // ③顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        // 顧客共通情報.外国株式取引口座開設状況==0(未開設)の場合
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(), NOESTABLISHMENT)) {
            // レスポンスに外貨建口座未開設エラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_FOREIGNSTOCKACCOUNTCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_FOREIGNSTOCKACCOUNTCHECK.key));
            return dtoRes;
        }
        
        // ④顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        // 顧客共通情報.信用口座区分（外国）="△"の場合
        if (StringUtils.equals(cc.getForeignMarginAccountType(), HALFWIDTH_SPACE)) {
            // 外国信用口座未開設エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTOPEN.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTOPEN.key));
            return dtoRes;
        }
        
        // API001を呼びだす。
        GetForeignStockSecuritiesResp api001Res = new GetForeignStockSecuritiesResp();
        try {
            api001Res = foreignStockService.getForeignStockSecurities(US, dtoReq.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // API006を呼び出す。
        CreateMarketPriceTicketResp api006Res = new CreateMarketPriceTicketResp();
        try {
            api006Res = foreignInformationService.createMarketPriceTicket(cc.getButenCode(), cc.getAccountNumber(),
                    IP_ADDRESS, USER_AGENT, US);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // API007を呼び出す。
        GetMarketPriceHashResp api007Res = new GetMarketPriceHashResp();
        try {
            api007Res = foreignInformationService.getMarketPriceHash(cc.getButenCode(), cc.getAccountNumber(),
                    api006Res.getPriceTicket(), US);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // ⑤株価を取得する。
        // Header.hash_tokenをセット
        // パラメータ.countryCode（"US"（米国））をセット
        // パラメータ.ricsをセット
        // API003を呼び出す。
        ListMarketPricesResp api003Res = new ListMarketPricesResp();
        try {
            api003Res = foreignInformationService.listMarketPrices(api007Res.getHashValue(), US,
                    new String[] { api001Res.getSecurities().getRic() });
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // ⑥処理結果をコントローラーに返却する
        if (api003Res.getMarketPrices().size() != 0) {
            for (int i = 0; i < api003Res.getMarketPrices().size(); i++) {
                PriceBaseInfo dto = new PriceBaseInfo();
                // 現在値（数値(小数)）
                dto.setCurrentPrice(api003Res.getMarketPrices().get(i).getPrice().getLast());
                // 現在値日時
                dto.setDiffDateTime(api003Res.getMarketPrices().get(i).getPrice().getLastDatetime());
                // ティック矢印(アップorダウン)
                dto.setTickArrow(api003Res.getMarketPrices().get(i).getPrice().getTickArrow());
                // 前日比（数値(小数)）
                dto.setDiff(api003Res.getMarketPrices().get(i).getPrice().getChange());
                // 前日比(%)
                dto.setRatio(api003Res.getMarketPrices().get(i).getPrice().getChangePercent());
                // 始値（数値(小数)）
                dto.setStart(api003Res.getMarketPrices().get(i).getPrice().getOpen());
                // 高値（数値(小数)）
                dto.setHigh(api003Res.getMarketPrices().get(i).getPrice().getHigh());
                // 安値（数値(小数)）
                dto.setLow(api003Res.getMarketPrices().get(i).getPrice().getLow());
                // 出来高（数値(整数)）
                dto.setVolume(api003Res.getMarketPrices().get(i).getPrice().getVolume());
                // 前日終値（数値(小数)）
                dto.setLast(api003Res.getMarketPrices().get(i).getPrice().getPrevClose());
                // 前日終値日付
                dto.setLastDate(api003Res.getMarketPrices().get(i).getPrice().getPrevCloseDate());
                
                resDto.setPriceBaseInfo(dto);
            }
        }
        resDtoList.add(resDto);
        
        // レスポンスをコントローラーに返却する。
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A010a
     * アクション名：注文発注a
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA010aRequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA010aResponseDto
     * model リクエスト：IfaForeignMarginTradeNewOrderConfirmSql001RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注aの際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto> orderPlacementA010a(
            IfaForeignMarginTradeNewOrderConfirmA010aRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderConfirmServiceImplL.orderPlacementA010a");
        }
        
        DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto> dtoRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto>();
        List<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderConfirmA010aResponseDto>();
        
        // ⓵委託注文サービス時間のチェックを行う。
        InputFct018Dto fct018Req = new InputFct018Dto();
        // 国コード（"US"）をセット
        fct018Req.setCountryCode(US);
        // 共通関数FCT018を呼び出す。
        OutputFct018Dto fct018Res = new OutputFct018Dto();
        fct018Res = fct018.doCheck(fct018Req);
        // FCT018.処理結果==”NG"
        if (StringUtils.equals(fct018Res.getProcessResult(), NG)) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFRANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFRANGE.key));
            return dtoRes;
        }
        
        // ⓶利用者の権限チェックを行う。
        // ユーザ共通情報.権限コード==1(SBI証券本店)　または　ユーザ共通情報.権限コード==2(SBI証券支店)の場合
        UserAccount ua = IfaCommonUtil.getUserAccount();
        if (StringUtils.equals(ua.getPrivId(), SBI_MAIN) || StringUtils.equals(ua.getPrivId(), SBI_BRANCH)) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_INSUFFICIENTPRIVILEGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INSUFFICIENTPRIVILEGE.key));
            return dtoRes;
        }
        
        // ③利用者の口座に対する権限チェックを行う
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        InputFct001Dto fct001Req = new InputFct001Dto();
        // 顧客共通情報.部店コードをセット
        fct001Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無==1(権限あり)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), AUTHORIZED)) {
            // 顧客情報を格納し次の処理へ
        } else {
            // 利用者の口座に対する権限チェックを行う。
            // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // FCT001.取引停止フラグ==1（取引停止口座）の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), STOPTRADEACCOUNT)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUTOFSERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUTOFSERVICE.key));
            return dtoRes;
        }
        
        // ④口座の契約締結前交付書面コードのチェック　および共同募集顧客(共募顧客)のチェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 顧客共通情報.部店コードをセット
        fct003Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別をセット
        fct003Req.setProductCd(FOREIGN_STOCKS);
        // リクエストパラメータ.取引種別をセット
        fct003Req.setTradeCd(dtoReq.getTradeCd());
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規買"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）
        // または
        // FCT003.レスポンス.媒介可否リスト.取引種別が「"信用新規売"」かつ　レスポンス.媒介可否リスト.媒介可否＝"1"（媒介可）が存在しない場合
        int num = 0;
        for (MediatePropriety loop : fct003Res.getMediateProprietyList()) {
            if ((StringUtils.equals(loop.getTradeClass(), NEW_PURCHASE_KUBUN)
                    && StringUtils.equals(loop.getMediatePropriety(), EXIST))
                    || (StringUtils.equals(loop.getTradeClass(), NEW_SALES_KUBUN)
                            && StringUtils.equals(loop.getMediatePropriety(), EXIST))) {
                // 存在した場合プラス
                num++;
            }
        }
        if (num == 0) {
            // レスポンスに媒介不可エラーを設定する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_UNAVAILABLE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_UNAVAILABLE.key, new String[] { codeName }));
            return dtoRes;
        }
        
        // ⑤顧客共通情報.外国株式取引口座開設状況より、外貨建口座取引開設状況のチェックを行う。
        // 顧客共通情報.外国株式取引口座開設状況==0(未開設)の場合
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(), NOESTABLISHMENT)) {
            // レスポンスに外貨建口座未開設エラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_FOREIGNSTOCKACCOUNTCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_FOREIGNSTOCKACCOUNTCHECK.key));
            return dtoRes;
        }
        
        // ⑥顧客共通情報.信用口座区分（外国）より、外国信用口座開設状況のチェックを行う。
        // 顧客共通情報.信用口座区分（外国）="△"の場合
        if (StringUtils.equals(cc.getForeignMarginAccountType(), HALFWIDTH_SPACE)) {
            // 外国信用口座未開設エラーを返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTOPEN.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTOPEN.key));
            return dtoRes;
        }
        
        // ⑦数量をチェックする。
        // 銘柄情報を取得する。
        // パラメータ.countryCode"US"（米国）をセット
        // パラメータ.securitiesCodeをセット
        // API001を呼びだす。
        GetForeignStockSecuritiesResp api001Res = new GetForeignStockSecuritiesResp();
        try {
            api001Res = foreignStockService.getForeignStockSecurities(US, dtoReq.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        BigDecimal limitMax = new BigDecimal(api001Res.getTradeLimitMax());
        if (limitMax.compareTo(BigDecimal.ZERO) == 0) {
            limitMax = LIMIT_MAX;
        }
        // リクエストパラメータ.注文数量 ＞ API001.取引上限数量の場合　かつAPI001.取引上限数量 != 0の場合
        if (new BigDecimal(dtoReq.getForeignQuantity()).compareTo(limitMax) > 0) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                            new String[] { ORDER_COUNT, api001Res.getTradeLimitMin(), limitMax.toString() }));
            return dtoRes;
        }
        // リクエストパラメータ.注文数量 ＜ API001.取引下限数量の場合
        if (new BigDecimal(dtoReq.getForeignQuantity()).compareTo(new BigDecimal(api001Res.getTradeLimitMin())) < 0) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                            new String[] { ORDER_COUNT, api001Res.getTradeLimitMin(), limitMax.toString() }));
            return dtoRes;
        }
        
        // 数量が、取引単位で割り切れることをチェックする。
        // リクエストパラメータ.注文数量 % API001.取引単位 != 0の場合
        if (new BigDecimal(dtoReq.getForeignQuantity())
                .remainder(new BigDecimal(api001Res.getTradeUnit())) != BigDecimal.ZERO) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INCONSISTENT.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INCONSISTENT.key,
                            new String[] { ORDER_COUNT, TRADE_UNIT, api001Res.getTradeUnit() }));
            return dtoRes;
        }
        
        // レスポンス.銘柄種別をセット
        IfaForeignMarginTradeNewOrderConfirmA010aResponseDto resDto = new IfaForeignMarginTradeNewOrderConfirmA010aResponseDto();
        resDto.setBrandClass(api001Res.getSecuritiesType());
        
        if (StringUtils.equals(dtoReq.getOrderPriceKindList(), PriceConditons.LIMIT.getValue())) {
            // ⑧注文単価（指値）が、0以上　999999999.＊＊以下の範囲にあることをチェックする。
            // 注文単価（指値） < 0または    注文単価（指値） ＞ 999999999.＊＊の場合
            if (new BigDecimal(dtoReq.getHiddenOrderPrice()).compareTo(BigDecimal.ZERO) < 0
                    || new BigDecimal(dtoReq.getHiddenOrderPrice()).compareTo(new BigDecimal(MAX_VALUE)) > 0) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                                new String[] { ORDER_PRICE, MIN_VALUE, MAX_VALUE }));
                return dtoRes;
            }
        } else if (!StringUtils.equals(dtoReq.getOrderPriceKindList(), PriceConditons.MARKET.getValue())) {
            if (!dtoReq.getHiddenOrderPrice().isEmpty()) {
                // ⑩注文単価（逆指値）が、0以上　999999999.＊＊以下の範囲にあることをチェックする。
                // if(注文単価（逆指値） < 0または　注文単価（逆指値） ＞ 999999999.＊＊の場合){
                if (new BigDecimal(dtoReq.getHiddenOrderPrice()).compareTo(BigDecimal.ZERO) < 0
                        || new BigDecimal(dtoReq.getHiddenOrderPrice()).compareTo(new BigDecimal(MAX_VALUE)) > 0) {
                    // レスポンスにエラーを設定する
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                                    new String[] { REVERSE_ORDER_PRICE, MIN_VALUE, MAX_VALUE }));
                    return dtoRes;
                }
            }
            // ⑨発火条件価格（逆指値）が、0以上　999999999.＊＊以下の範囲にあることをチェックする。
            // if発火条件価格（逆指値） < 0または　発火条件価格（逆指値） ＞ 999999999.＊＊の場合
            if (new BigDecimal(dtoReq.getStopOrderPrice2()).compareTo(BigDecimal.ZERO) < 0
                    || new BigDecimal(dtoReq.getStopOrderPrice2()).compareTo(new BigDecimal(MAX_VALUE)) > 0) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERVALUEOUTOFRANGE.key,
                                new String[] { REFERENCE_PRICE, MIN_VALUE, MAX_VALUE }));
                return dtoRes;
            }
            
        }
        
        // ⑪期間条件が「期間指定」の場合、日付が9営業日後以内であることをチェックする。
        // Header.tokenをセット
        // パラメータ.countryCode("US"（米国）)をセット
        // securitiesCodeをセット
        // stockTradeTypeをセット   
        // buySellCodeをセット
        String buySellCode = null;
        if (StringUtils.equals(dtoReq.getTradeCd(), "2")) {
            buySellCode = BUY;
        } else if (StringUtils.equals(dtoReq.getTradeCd(), "3")) {
            buySellCode = SELL;
        }
        // specificAccountCodeをセット
        
        // API005を呼び出す。
        GetForeignStockCreatedMarginOrderInitializationResp api005Res = new GetForeignStockCreatedMarginOrderInitializationResp();
        try {
            api005Res = foreignStockService.getForeignStockCreatedMarginOrderInitialization(cc.getButenCode(),
                    cc.getAccountNumber(), US, dtoReq.getBrandCode(), MARGIN_OPEN, buySellCode,
                    dtoReq.getDepositType());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // iリクエストパラメータ.期間条件=="1" （"CARRY_OVER_ORDER"（期間指定））の場合
        if (StringUtils.equals(dtoReq.getPeriodDate(), CARRY_OVER_ORDER)) {
            if (null == api005Res.getOrderTerms() || api005Res.getOrderTerms().size() == 0) {
                // レスポンスに注文可能期間エラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_TERMS_OUTOFRANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_TERMS_OUTOFRANGE.key));
                return dtoRes;
            }
            int orderTermSize = api005Res.getOrderTerms().size() > MAX_ORDER_TERM_LIST ? MAX_ORDER_TERM_LIST
                    : api005Res.getOrderTerms().size();
            List<String> orderTermList = api005Res.getOrderTerms().subList(0, orderTermSize);
            // リクエストパラメータ.期間 ＞ API005.有効期間一覧の場合（9営業日後以降の場合）
            if (!orderTermList.contains(DateFormatUtil.dateFormatToHyphen(dtoReq.getPeriod()))) {
                // レスポンスに注文可能期間エラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_TERMS_OUTOFRANGE.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_TERMS_OUTOFRANGE.key));
                return dtoRes;
            }
        }
        
        // レスポンス.有効期間一覧をセット
        resDto.setValidityPeriodList(api005Res.getOrderTerms());
        
        // ⑫口座の取引制限チェックを行う。
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 顧客共通情報.部店コードをセット
        fct021Req.setButenCode(cc.getButenCode());
        // 顧客共通情報.口座番号をセット
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(外国株式(15))をセット
        fct021Req.setProductCd(FOREIGN_STOCKS);
        // リクエストパラメータ.取引種別をセット
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        // 国籍コード("US")をセット
        fct021Req.setCountryCd(US);
        // 通貨コード("999")をセット
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        
        // FCT021を呼び出す。
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // FCT021.注意情報エラー有無=="1"の場合
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), EXIST)) {
            // レスポンスに注意情報エラーを設定する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "5", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICE_ERROR_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICE_ERROR_CHECK.key, new String[] { codeName }));
            return dtoRes;
        }
        // FCT021.お知らせエラー有無=="1"の場合
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), EXIST)) {
            // レスポンスにお知らせエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATIONCHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATIONCHECK.key));
            return dtoRes;
        }
        // FCT021.注意情報アラート有無=="1"の場合
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), EXIST)) {
            // リクエストパラメータ.アラート内容確認.注意情報アラート確認==0(未確認)　または　非表示
            if (StringUtils.equals(dtoReq.getNoteInfoCheckbox(), NOT_CONFIRM)
                    || dtoReq.getNoteInfoCheckbox().isEmpty()) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
        }
        // FCT021.お知らせアラート有無=="1"の場合
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), EXIST)) {
            // リクエストパラメータ.アラート内容確認.お知らせアラート確認==0(未確認)　または　非表示
            if (StringUtils.equals(dtoReq.getNoteLimitCheck(), NOT_CONFIRM) || dtoReq.getNoteLimitCheck().isEmpty()) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
        }
        
        // ⑬注意銘柄を取得する。
        // パラメータ.countryCode("US"（米国）)をセット
        // パラメータ.securitiesCodeをセット
        // API002を呼び出す。
        GetForeignStockAttentionSecuritiesResp api002Res = new GetForeignStockAttentionSecuritiesResp();
        try {
            api002Res = foreignStockService.getForeignStockAttentionSecurities(US, dtoReq.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        // API002.注意銘柄==true　の場合
        if (api002Res.getAttentionSecurities()) {
            // リクエストパラメータ.アラート内容確認.取引注意情報（銘柄）確認==OFF　または　非表示の場合
            if (StringUtils.equals(dtoReq.getTradingCautionInformation(), NOT_CONFIRM)
                    || dtoReq.getTradingCautionInformation().isEmpty()) {
                // レスポンスにエラーを設定する
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        MessageId.ERRORS_INFORMATION_OCCURS.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_OCCURS.key));
                return dtoRes;
            }
        }
        
        // レスポンス.注意銘柄セット
        if (api002Res.getAttentionSecurities()) {
            resDto.setTradeLimitTitle("true");
        } else {
            resDto.setTradeLimitTitle("false");
        }
        
        // 注文番号に設定する連番を取得する。（SQL003）
        // ⑭発注前に注文内容をDBに登録する。
        IfaForeignMarginTradeNewOrderConfirmSql001RequestModel insSql001Req = new IfaForeignMarginTradeNewOrderConfirmSql001RequestModel();
        // 受付注文番号
        insSql001Req.setOrderNumber(dtoReq.getOrderNumber());
        // 受付注文サブ番号
        insSql001Req.setOrderSubNumber(dtoReq.getOrderSubNumber());
        // 部店コード
        insSql001Req.setButenCode(cc.getButenCode());
        // 口座番号
        insSql001Req.setAccountNumber(cc.getAccountNumber());
        // ティッカーコード
        insSql001Req.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名
        insSql001Req.setBrandName(dtoReq.getBrandName());
        // 市場コード
        insSql001Req.setMarketCode(dtoReq.getMarketCode());
        // 売買区分
        insSql001Req.setTradeKbn(dtoReq.getTradeKbn());
        // 注文数量
        if (!dtoReq.getForeignQuantity().isEmpty()) {
            insSql001Req.setForeignQuantity(Long.parseLong(dtoReq.getForeignQuantity()));
        }
        // 価格条件区分
        insSql001Req.setOrderPriceKindList(dtoReq.getOrderPriceKindList());
        // 指値
        if (!dtoReq.getHiddenOrderPrice().isEmpty()) {
            insSql001Req.setHiddenOrderPrice(dtoReq.getHiddenOrderPrice());
        }
        // 発火条件価格
        if (!dtoReq.getStopOrderPrice2().isEmpty()) {
            insSql001Req.setStopOrderPrice2(dtoReq.getStopOrderPrice2());
        }
        // 通貨コード
        insSql001Req.setStopOrderPriceText2(dtoReq.getTradeCurrency());
        // 決済区分
        insSql001Req.setKessaiHoho(dtoReq.getSettlementType());
        // 預り区分
        insSql001Req.setDepositType(dtoReq.getDepositType());
        // 勧誘区分
        insSql001Req.setKanyuKbn(dtoReq.getKanyuKbn());
        // 受注方法区分
        insSql001Req.setOrderClassification(dtoReq.getReceiveOrderTypeName());
        // 重要事項説明区分
        insSql001Req.setImportantMatterType(dtoReq.getImportantMatterType());
        // 英文開示銘柄明区分
        insSql001Req.setEngPubText(dtoReq.getEngPubText());
        // 取引前確認区分
        insSql001Req.setCheckInsider(dtoReq.getCheckInsider());
        
        // 注文日
        String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
        SimpleDateFormat sdFormat3 = new SimpleDateFormat(pattern);
        Date orderDate = sdFormat3.parse(dtoReq.getOrderDate());
        insSql001Req.setOrderDateTime(orderDate);
        // 国内約定日
        insSql001Req.setTradeDate(dtoReq.getDomesticTradeDate().substring(0, 4)
                + dtoReq.getDomesticTradeDate().substring(5, 7) + dtoReq.getDomesticTradeDate().substring(8, 10));
        // 注文期限日
        // 期間条件が当日注文の場合は、A010.現地約定日 YYYYMMDD形式
        if (StringUtils.equals(dtoReq.getPeriodDate(), TODAY_ORDER)) {
            insSql001Req.setPeriod(dtoReq.getLocalTradeDate().substring(0, 4)
                    + dtoReq.getLocalTradeDate().substring(5, 7) + dtoReq.getLocalTradeDate().substring(8, 10));
        } else {
            SimpleDateFormat sdFormat4 = new SimpleDateFormat("yyyy-MM-dd");
            Date period = sdFormat4.parse(dtoReq.getPeriod());
            SimpleDateFormat sdFormat5 = new SimpleDateFormat("yyyyMMdd");
            insSql001Req.setPeriod(sdFormat5.format(period));
        }
        // 仲介業者コード
        insSql001Req.setBrokerCode(cc.getBrokerCode());
        // 仲介業者営業員コード
        insSql001Req.setBrokerChargeCode(cc.getBrokerChargeCode());
        // 作成者
        // 更新者
        insSql001Req.setUserId(ua.getUserId());
        
        try {
            dao.insertIfaForeignMarginTradeNewOrderConfirmSql001(insSql001Req);
        } catch (
        
        Exception e) {
            // レスポンスにエラーを設定する
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_PREORDEREXECUTION_FAILED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_PREORDEREXECUTION_FAILED.key));
            return dtoRes;
        }
        
        // レスポンス.IFA注文番号をセット
        resDto.setIfaOrderNo(insSql001Req.getIfaOrderNo());
        
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A010b
     * アクション名：注文発注b
     * Dto リクエスト：IfaForeignMarginTradeNewOrderConfirmA010bRequestDto
     * Dto レスポンス：IfaForeignMarginTradeNewOrderConfirmA010bResponseDto
     * model リクエスト：IfaForeignMarginTradeNewOrderConfirmSql002RequestModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 注文発注bの際、例外が発生した場合
     */
    public DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> orderPlacementA010b(
            IfaForeignMarginTradeNewOrderConfirmA010bRequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginTradeNewOrderConfirmServiceImplL.orderPlacementA010b");
        }
        
        // ⑮注文発注を行う。
        // 外国株式現物注文登録APIにて注文発注を行い、結果を取得する。
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // Header.tokenをセット
        // Header.request_idをセット
        // Header.channelをセット
        // パラメータ.MarginOrderInput.countryCodeをセット
        MarginOrderInput marginOrderInput = new MarginOrderInput();
        marginOrderInput.setCountryCode(US);
        // パラメータ.MarginOrderInput.marketCodeをセット
        marginOrderInput.setMarketCode(dtoReq.getMarketCode());
        // パラメータ.MarginOrderInput.securitiesCodeをセット
        marginOrderInput.setSecuritiesCode(dtoReq.getBrandCode());
        // パラメータ.MarginOrderInput.buySellCodeをセット
        String buySellCode = null;
        if (StringUtils.equals(dtoReq.getTradeCd(), "2")) {
            buySellCode = BUY;
        } else if (StringUtils.equals(dtoReq.getTradeCd(), "3")) {
            buySellCode = SELL;
        }
        marginOrderInput.setBuySellCode(buySellCode);
        // パラメータ.MarginOrderInput.orderQuantityをセット
        marginOrderInput.setOrderQuantity(dtoReq.getForeignQuantity());
        // パラメータ.MarginOrderInput.orderPriceKindCodeをセット
        marginOrderInput.setOrderPriceKindCode(dtoReq.getOrderPriceKindList());
        // パラメータ.MarginOrderInput.orderPriceをセット
        marginOrderInput.setOrderPrice(dtoReq.getHiddenOrderPrice());
        // パラメータ.MarginOrderInput.stopPriceをセット
        marginOrderInput.setStopPrice(dtoReq.getStopOrderPrice2());
        // パラメータ.MarginOrderInput.orderLimitCodeをセット
        marginOrderInput.setOrderLimitCode(dtoReq.getPeriodDate());
        // パラメータ.MarginOrderInput.orderTermをセット
        marginOrderInput.setOrderTerm(dtoReq.getPeriod());
        // パラメータ.MarginOrderInput.specificAccountCodeをセット
        marginOrderInput.setSpecificAccountCode(dtoReq.getDepositType());
        // パラメータ.MarginOrderInput.settlementMethodCodeをセット
        marginOrderInput.setSettlementMethodCode(dtoReq.getSettlementType());
        // パラメータ.MarginOrderInput.marginCloseLimitTypeをセット
        marginOrderInput.setMarginCloseLimitType(dtoReq.getMarginDueDate());
        // API004を呼び出す。
        CreateForeignStockMarginOrderResp api004Res = null;
        ApiStatusModel apiStatusModel = null;
        
        DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> dtoRes = new DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto>();
        List<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> resDtoList = new ArrayList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto>();
        DataList<IfaForeignMarginTradeNewOrderConfirmA010bResponseDto> dtoResApiErr = null;
        try {
            api004Res = foreignStockService.createForeignStockMarginOrder(cc.getButenCode(), cc.getAccountNumber(),
                    null, "", PHONE, marginOrderInput);
            apiStatusModel = null;
        } catch (AthenaBusinessException e) {
            // API業務異常時、APIのステータスを取得する
            apiStatusModel = AmericaStockUtil.getApiStatusInfo(e);
            // エラーメッセージを取得する
            api004Res = null;
            dtoResApiErr = cometCommonService.checkBussinessException(
                    IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, "", null), e);
        }
        
        // ⑯発注後に注文結果をDBに反映する。
        // 注文データをDBに登録する。
        IfaForeignMarginTradeNewOrderConfirmSql002RequestModel updSql002Req = new IfaForeignMarginTradeNewOrderConfirmSql002RequestModel();
        if (apiStatusModel == null) {
            // 受付注文番号
            updSql002Req.setOrderNumber(api004Res.getMarginOrder().getOrder().getOrderNo());
            // 受付注文サブ番号
            updSql002Req.setOrderSubNumber(api004Res.getMarginOrder().getOrder().getOrderSubNo());
            // 注文日
            // 注文時間
            String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
            SimpleDateFormat sdFormat = new SimpleDateFormat(pattern);
            Date orderDate = sdFormat.parse(api004Res.getMarginOrder().getOrder().getOrderInputDatetime());
            updSql002Req.setOrderDateTime(orderDate);
            // 注文期限日
            // 期間条件が当日注文の場合は、API.注文情報.現地約定 YYYYMMDD形式
            if (StringUtils.equals(api004Res.getMarginOrder().getOrder().getOrderLimitCode(), TODAY_ORDER)) {
                String orderTerm = api004Res.getMarginOrder().getOrder().getFrnTradeDate();
                if (orderTerm != null) {
                    updSql002Req.setPeriod(orderTerm.substring(0, 4)
                        + orderTerm.substring(5, 7) + orderTerm.substring(8, 10));
                }
            } else {
                SimpleDateFormat sdFormat4 = new SimpleDateFormat("yyyy-MM-dd");
                Date period = sdFormat4.parse(api004Res.getMarginOrder().getOrder().getOrderTerm());
                SimpleDateFormat sdFormat5 = new SimpleDateFormat("yyyyMMdd");
                updSql002Req.setPeriod(sdFormat5.format(period));
            }
        }
        
        // APIステータスコード
        updSql002Req.setResponseCode(200);
        // 更新者
        UserAccount ua = IfaCommonUtil.getUserAccount();
        updSql002Req.setUserId(ua.getUserId());
        // IFA注文番号
        updSql002Req.setIfaOrderNo(dtoReq.getIfaOrderNo());
        // IFA注文サブ番号
        updSql002Req.setIfaOrderSubNo("1");
        
        int sql002Res = 0;
        try {
            // 注文発注に成功
            if (apiStatusModel == null) {
                // SQL002（正常）を発行
                sql002Res = dao.updateIfaForeignMarginTradeNewOrderConfirmSql002(updSql002Req);
            } else {
                // APIステータスコード
                updSql002Req.setResponseCode(apiStatusModel.getApiStatusCode());
                // エラーコード
                updSql002Req.setErrorCode(apiStatusModel.getApiErrorCode());
                // API.エラーメッセージ
                updSql002Req.setErrMessage(apiStatusModel.getApiMessage());
                // SQL002（エラー）を発行
                sql002Res = dao.updateIfaForeignMarginTradeNewOrderConfirmSql002(updSql002Req);
            }
            if (sql002Res == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                        MessageId.WARNINGS_POSTORDEREXECUTION_COMPLETED.key,
                        IfaCommonUtil.getMessage(MessageId.WARNINGS_POSTORDEREXECUTION_COMPLETED.key));
            }
        } catch (Exception e) {
            // レスポンスにエラーを設定する
            // DB更新NG：注文結果DB登録失敗ワーニング情報を格納し、正常を返す。
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    MessageId.WARNINGS_POSTORDEREXECUTION_COMPLETED.key,
                    IfaCommonUtil.getMessage(MessageId.WARNINGS_POSTORDEREXECUTION_COMPLETED.key));
        }
        
        // ⑰注文発注結果がエラー(⑯のDB更新結果には影響されない)
        // 注文発注結果==エラーの場合
        if (dtoResApiErr != null) {
            // レスポンスにエラーを設定する
            return dtoResApiErr;
        }
        
        // 注文情報
        OrderInfo orderInfo = new OrderInfo();
        // 注文番号（数字）
        orderInfo.setOrderNumber(api004Res.getMarginOrder().getOrder().getOrderNo());
        // 注文Sub番号（数字）
        orderInfo.setOrderSubNumber(api004Res.getMarginOrder().getOrder().getOrderSubNo());
        // 銘柄情報（全角半角）
        orderInfo.setBrandInformation(api004Res.getMarginOrder().getOrder().getSecurities());
        // 取引通貨
        orderInfo.setStopOrderPriceText2(api004Res.getMarginOrder().getOrder().getTradeCurrencyCode());
        // 市場情報（全角半角）
        orderInfo.setMarketInformation(api004Res.getMarginOrder().getOrder().getMarket());
        // 売買区分（全角半角）
        orderInfo.setTradeKbn(api004Res.getMarginOrder().getOrder().getBuySellCode());
        // 自動買付区分
        orderInfo.setAutoBuyClassification(api004Res.getMarginOrder().getOrder().getAutoStockOrderType());
        // 注文数量
        orderInfo.setForeignQuantity(api004Res.getMarginOrder().getOrder().getOrderQuantity());
        // 価格条件
        orderInfo.setOrderPriceKindList(api004Res.getMarginOrder().getOrder().getOrderPriceKindCode());
        // 注文単価（数値(小数)）
        orderInfo.setHiddenOrderPrice(api004Res.getMarginOrder().getOrder().getOrderPrice());
        // 発火条件価格
        orderInfo.setStopOrderPrice2(api004Res.getMarginOrder().getOrder().getStopPrice());
        // トレールストップ幅
        orderInfo.setTrailStopWidth(api004Res.getMarginOrder().getOrder().getTrailingStopAmount());
        // 成行基準価格（全角半角）
        orderInfo.setExecuteBasePrice(api004Res.getMarginOrder().getOrder().getNoLimitPrice());
        // 期間条件
        orderInfo.setPeriodDate(api004Res.getMarginOrder().getOrder().getOrderLimitCode());
        // 期間
        orderInfo.setPeriod(api004Res.getMarginOrder().getOrder().getOrderTerm());
        // 預り区分（全角半角）
        orderInfo.setDepositType(api004Res.getMarginOrder().getOrder().getSpecificAccountCode());
        // 決済方法（全角半角）
        orderInfo.setKessaiHoho(api004Res.getMarginOrder().getOrder().getSettlementMethodCode());
        // 決済通貨
        orderInfo.setSettlementCurrency(api004Res.getMarginOrder().getOrder().getSettlementCurrencyCode());
        // 為替レート（数値(小数)）
        orderInfo.setFxRate(api004Res.getMarginOrder().getOrder().getExchangeRate());
        // 平均約定単価（数値(小数)）
        orderInfo.setAverageTradePrice(api004Res.getMarginOrder().getOrder().getExecutionAveragePrice());
        // 未約定数量（数値(整数)）
        orderInfo.setUnTradeQuantity(api004Res.getMarginOrder().getOrder().getUnexecutedQuantity());
        // 約定数量（数値(整数)）
        orderInfo.setTradeQuantity(api004Res.getMarginOrder().getOrder().getExecutionQuantity());
        // 約定金額（外貨）（数値(小数)）
        orderInfo.setContractAmountForeign(api004Res.getMarginOrder().getOrder().getFrnGrossAmount());
        // 約定金額（円貨）
        orderInfo.setApproximatePositionAmount(api004Res.getMarginOrder().getOrder().getGrossAmount());
        // 受渡金額（外貨）（数値(小数)）
        orderInfo.setForeignDeliveryAmount(api004Res.getMarginOrder().getOrder().getFrnNetAmount());
        // 受渡金額（円貨）（数値(整数)）
        orderInfo.setYenDeliveryAmount(api004Res.getMarginOrder().getOrder().getNetAmount());
        // 受渡金額（約定分）
        orderInfo.setDeliveryPriceTrade(api004Res.getMarginOrder().getOrder().getExecutionNetAmount());
        // 国内手数料（外貨）
        orderInfo.setForeignApproximateFeeForeign(api004Res.getMarginOrder().getOrder().getFrnCommissionAmount());
        // 国内手数料（円貨）
        orderInfo.setForeignApproximateFee(api004Res.getMarginOrder().getOrder().getCommissionAmount());
        // 国内消費税（外貨）
        orderInfo.setForeignApproximateConsumptionTaxForeign(
                api004Res.getMarginOrder().getOrder().getFrnCommissionCtax());
        // 国内消費税（円貨）
        orderInfo.setForeignApproximateConsumptionTax(api004Res.getMarginOrder().getOrder().getCommissionCtax());
        // 現地手数料等（外貨）
        orderInfo.setLocalCommEtcForeign(api004Res.getMarginOrder().getOrder().getFrnLocalCharge());
        // 現地手数料等（円貨）
        orderInfo.setLocalCommEtc(api004Res.getMarginOrder().getOrder().getLocalCharge());
        // 現地精算金額（外貨）
        orderInfo.setLocalAdjustmentAmountForeign(api004Res.getMarginOrder().getOrder().getFrnLocalNetAmount());
        // 現地精算金額（円貨）
        orderInfo.setLocalAdjustmentAmount(api004Res.getMarginOrder().getOrder().getLocalNetAmount());
        // NISA枠拘束金額
        orderInfo.setNisaLimitRestraintAmount(api004Res.getMarginOrder().getOrder().getNisaFixedAmount());
        // 概算譲渡益税（数値(整数)）
        orderInfo.setApproximateCapitalGainsTax(api004Res.getMarginOrder().getOrder().getSpecificTax());
        // 注文状況（全角半角）
        orderInfo.setOrderStatus(api004Res.getMarginOrder().getOrder().getOrderStatus());
        // 約定状況
        orderInfo.setTradeStatus(api004Res.getMarginOrder().getOrder().getExecutionStatus());
        // 発火状況
        orderInfo.setIgnitionStatus(api004Res.getMarginOrder().getOrder().getWorkingStatus());
        // 国内約定日
        orderInfo.setTradeDate(api004Res.getMarginOrder().getOrder().getTradeDate());
        // 国内受渡日
        orderInfo.setDomesticSettlementDate(api004Res.getMarginOrder().getOrder().getValueDate());
        // 現地約定日
        orderInfo.setContractDate(api004Res.getMarginOrder().getOrder().getFrnTradeDate());
        // 現地受渡日
        orderInfo.setLocalDeliveryDate(api004Res.getMarginOrder().getOrder().getFrnValueDate());
        // 注文日時
        orderInfo.setOrderDate(api004Res.getMarginOrder().getOrder().getOrderInputDatetime());
        // 約定日時
        orderInfo.setTradeDateTime(api004Res.getMarginOrder().getOrder().getExecutionDatetime());
        // 失効日時
        orderInfo.setRevocationdateTime(api004Res.getMarginOrder().getOrder().getExpiredDatetime());
        // 株取引区分
        orderInfo.setStockTradeClassification(api004Res.getMarginOrder().getOrder().getStockTradeType());
        // レスポンスDTO
        IfaForeignMarginTradeNewOrderConfirmA010bResponseDto resDto = new IfaForeignMarginTradeNewOrderConfirmA010bResponseDto();
        resDto.setOrderInfo(orderInfo);
        
        // 返済建玉指定方法（全角半角）
        resDto.setRepayPositionDesignateMethod(api004Res.getMarginOrder().getClosePositionKind());
        // 返済選択順序（全角半角）
        resDto.setRepaySelectOrder(api004Res.getMarginOrder().getCloseSelectionSort());
        // 信用期日
        resDto.setMarginDueDate(api004Res.getMarginOrder().getMarginCloseLimitType());
        // 決済損益（数値(整数)）
        resDto.setSettlement(api004Res.getMarginOrder().getClosedProfitLoss());
        // 保証金不足金額
        resDto.setDepositDeficientAmount(api004Res.getMarginOrder().getMarginDeficitAmount());
        // 振替預り金額
        resDto.setCurrency(api004Res.getMarginOrder().getTransferDepositAmount());
        // 振替有価証券評価額
        resDto.setAmericaStockTransferAmount(api004Res.getMarginOrder().getTransferEvaluationAmount());
        // 銘柄種別（全角半角）
        resDto.setBrandClass(dtoReq.getBrandClass());
        // 注意銘柄
        resDto.setTradeLimitTitle(dtoReq.getTradeLimitTitle());
        // 有効期間一覧
        resDto.setValidityPeriodList(dtoReq.getValidityPeriodList());
        // 勧誘区分（全角半角）
        resDto.setKanyuKbn(dtoReq.getKanyuKbn());
        // 受注方法
        resDto.setOrderMethod(dtoReq.getReceiveOrderTypeName());
        // 重要事項の説明
        resDto.setImportantMatterType(dtoReq.getImportantMatterType());
        // 英文開示銘柄
        resDto.setEngPubText(dtoReq.getEngPubText());
        // 確認項目
        ConfirmItem confirmItem = new ConfirmItem();
        // 確認項目.インサイダー確認
        confirmItem.setInsiderConfirmCheckBox(dtoReq.getCheckInsider());
        resDto.setConfirmItem(confirmItem);
        // アラート内容確認
        AlertContentsConfirm alertContentsConfirm = new AlertContentsConfirm();
        //  アラート内容確認.取引注意情報（銘柄）確認
        alertContentsConfirm.setTradingCautionInformation(dtoReq.getTradingCautionInformation());
        // アラート内容確認.注意情報アラート確認
        alertContentsConfirm.setNoteInfoCheckbox(dtoReq.getNoteInfoCheckbox());
        // アラート内容確認.お知らせアラート確認
        alertContentsConfirm.setNoteLimitCheck(dtoReq.getNoteLimitCheck());
        // アラート内容確認.増し担保規制確認
        alertContentsConfirm
                .setAdditionalCollateralRegulationsConfirm(dtoReq.getAdditionalCollateralRegulationsConfirm());
        // アラート内容確認.逆指値注文即時発火
        alertContentsConfirm.setMethodCheck(dtoReq.getMethodCheck());
        // アラート内容確認.翌営業日向け注文
        alertContentsConfirm.setNextDayCheck(dtoReq.getNextDayCheck());
        resDto.setAlertContentsConfirm(alertContentsConfirm);
        // 注意情報アラート（全角半角）
        resDto.setNoticeInfoAlert(dtoReq.getNoticeInfoAlert());
        // お知らせアラート（全角半角）
        resDto.setNoticeAlert(dtoReq.getNoticeAlert());
        // 増し担保規制銘柄取引メッセージ
        resDto.setAdditionalCollateralRegulationConfirmMsg(dtoReq.getAdditionalCollateralRegulationConfirmMsg());
        // 逆指値注文即時発火メッセージ
        resDto.setStopOrderInstantMessage(dtoReq.getStopOrderInstantMessage());
        // 現地約定日超過メッセージ
        resDto.setNextBusinessDayMessage(dtoReq.getNextBusinessDayMessage());
        // 取引注意情報（銘柄）メッセージ（全角半角）
        resDto.setTradeNoticeInfoBrandMsg(dtoReq.getTradeNoticeInfoBrandMsg());
        // 銘柄コード（半角英数字）
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名（全角半角）
        resDto.setBrandName(dtoReq.getBrandName());
        // 市場略名
        resDto.setListedMarket(dtoReq.getListedMarket());
        // 取引種別（全角半角）
        resDto.setTradeCd(dtoReq.getTradeCd());
        // 本日の注意銘柄URL
        resDto.setTradeLimit(dtoReq.getTradeLimit());
        
        // レスポンスをコントローラーに返却する。
        resDtoList.add(resDto);
        if (dtoRes.getErrorLevel() != ErrorLevel.WARNING.getId()) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        }
        
        return dtoRes;
    }
    
}
