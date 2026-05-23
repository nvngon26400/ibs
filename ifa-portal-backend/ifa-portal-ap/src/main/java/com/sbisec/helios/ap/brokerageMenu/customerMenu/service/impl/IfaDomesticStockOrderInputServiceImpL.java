package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct006;
import com.sbisec.helios.ap.bizcommon.component.Fct008;
import com.sbisec.helios.ap.bizcommon.component.Fct021;
import com.sbisec.helios.ap.bizcommon.component.Fct027;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct008Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct021Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct027Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputA016ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputApiResponseBuyingPower;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticStockOrderInputApiResponseComplianceRankCheck;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticStockOrderInputService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.AccountSumWebData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderAutoOutData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderIn;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderInData;
import jp.co.sbisec.pcenter.dto.yanap.EstimateStockOrderOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountBalanceOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionSumWebOutData;

/**
 * 画面ID：SUB0202_0208-01_1
 * 画面名：国内株式注文入力
 *
 * @author SCSK
 */
@Component(value = "cmpIfaDomesticStockOrderInputService")
public class IfaDomesticStockOrderInputServiceImpL implements IfaDomesticStockOrderInputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticStockOrderInputServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct008 fct008;
    
    @Autowired
    private Fct021 fct021;
    
    @Autowired
    private Fct006 fct006;
    
    @Autowired
    private Fct027 fct027;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private CodeListService codelistservice;
    
    /** 権限なし */
    private static final String NO_AUTHORITY = "0";
    
    /** 取引停止口座 */
    private static final String SUSPENSION_ACCOUNT = "1";
    
    /** 国内株式.区分値 */
    private static final String DOMESTIC_STOCK = "01";
    
    /** あり */
    private static final String YES = "1";
    
    /** なし */
    private static final String NON = "0";
    
    /** 現物買付.区分値 */
    private static final String STOCK_PURCHASE = "1";
    
    /** 現物売却.区分値 */
    private static final String STOCK_SELL = "2";
    
    /** API004.国内株式*/
    private static final String DOMESTIC_STOCK_API004 = "K0";
    
    /** △半角スペース */
    private static final String SPACE = " ";
    
    /** 契約 */
    private static final String CONTRACT = "1";
    
    /** 第一、第二口座両方 */
    private static final String ACCOUNT_BOTH = "2";
    
    /** API004検索結果レコード数 */
    private static final int MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA = 100;
    
    /** 国籍コード */
    private static final String NATIONALITY_CODE = "99";
    
    /** 通貨コード */
    private static final String CURRENCY_CODE = "999";
    
    /** 対象取引（メッセージ表示用） */
    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    
    /** 国内.区分値 */
    private static final String DOMESTIC = "0";
    
    /** 株式.区分値 */
    private static final String STOCK = "1 ";
    
    /** 買付注文.区分値 */
    private static final String PURCHASE_ORDER = "1";
    
    /** 規制銘柄 */
    private static final String REGULATION_BRAND = "1";
    
    /** 当日中.区分値 */
    private static final String TODAY = "0";
    
    enum JudgementResult {
        
        // ノーマル
        NORMAL("0"),
        // アラート
        ALERT("1"),
        // エラー
        ERROR("2");
        
        private String key;
        
        private JudgementResult(String key) {
            
            this.key = key;
        }
    }
    
    enum OrderKind {
        
        // 通常/逆指値
        NORMALPRICE_LIMITREVERSE("1"),
        // OCO
        OCO("2"),
        // IFD
        IFD("3"),
        // IFDOCO
        IFDOCO("4");
        
        private String key;
        
        private OrderKind(String key) {
            
            this.key = key;
        }
    }
    
    enum SasinariHouhou {
        
        // 指値
        LIMIT("1"),
        // 成行
        MARKETORDER("2"),
        // 逆指値
        REVERCE_LIMIT("3");
        
        private String key;
        
        private SasinariHouhou(String key) {
            
            this.key = key;
        }
    }
    
    enum MessageId {
        
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.cmn.selectedAccount.outOfService
        ERRORS_OUT_OF_SERVICE("errors.cmn.selectedAccount.outOfService"),
        // errors.cmn.selectedAccountCourse.unavailable
        ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        // errors.cmn.noticeErrorCheck
        ERRORS_NOTICE_ERROR_CHECK("errors.cmn.noticeErrorCheck"),
        // errors.informationCheck
        ERRORS_INFORMATION_CHECK("errors.informationCheck"),
        // warnings.cmn.noticeWarningCheck
        WARNINGS_NOTICE_WARNING_CHECK("warnings.cmn.noticeWarningCheck"),
        // warnings.cmm.informationCheck
        WARNINGS_CMM_INFORMATION_CHECK("warnings.cmm.informationCheck"),
        // warnings.dms.informationCheck
        WARNINGS_DMS_INFORMATION_CHECK("warnings.dms.informationCheck"),
        // warning.dms.insider.exist
        WARNINGS_INSIDER_EXIST("warning.dms.insider.exist"),
        // errors.cmn.orderExecution.failed
        ERRORS_ORDER_EXECUTION_FAILED("errors.cmn.orderExecution.failed"),
        // errors.cmn.ccsid.unregistered
        ERRORS_CCSIDUNREGISTERED("errors.cmn.ccsid.unregistered");

        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticStockOrderInputA001RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA001ResponseDto> initializeA001(
            IfaDomesticStockOrderInputA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaDomesticStockOrderInputA001ResponseDto> dtoRes = new DataList<IfaDomesticStockOrderInputA001ResponseDto>();
        List<IfaDomesticStockOrderInputA001ResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderInputA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderInputServiceImplL.initializeA001");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ②取引コース媒介可否チェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(国内株式（"01"）)をセットする
        fct003Req.setProductCd(DOMESTIC_STOCK);
        // 取引種別(リクエスト.取引種別)をセットする
        if (dtoReq.getTradeCd() == null || dtoReq.getTradeCd().trim().isEmpty()) {
            fct003Req.setTradeCd(STOCK_PURCHASE);
        } else {
            fct003Req.setTradeCd(dtoReq.getTradeCd());
        }
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // FCT003.媒介可否リスト.媒介可否 == "0"(なし)の場合
        if (fct003Res.getMediateProprietyList() != null || fct003Res.getMediateProprietyList().size() != 0) {
            for (int i = 0; i < fct003Res.getMediateProprietyList().size(); i++) {
                if (StringUtils.equals(fct003Res.getMediateProprietyList().get(i).getMediatePropriety(), NON)) {
                    // レスポンスに取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
                    String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1");
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key,
                                    new String[] { codeName }));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
            }
        }
        
        IfaDomesticStockOrderInputA001ResponseDto resDto = new IfaDomesticStockOrderInputA001ResponseDto();
        // ③買付余力を取得する
        // リクエスト.取引種別 == 未指定(nullか空) || リクエスト.取引種別 == 現物買付（"1"）の場合
        if (dtoReq.getTradeCd() == null || dtoReq.getTradeCd().trim().isEmpty()
                || StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
            // レスポンス.取引種別に現物買付(区分.取引種別(国内株式)="1")をセットする
            resDto.setTradeCd(STOCK_PURCHASE);
        } else {
            resDto.setTradeCd(dtoReq.getTradeCd());
        }
        
        QueryAccountBalanceIn api003Req = new QueryAccountBalanceIn();
        // 部店コード(顧客共通情報.部店コード)をセットする
        // 口座番号(顧客共通情報.口座番号)をセットする
        QueryAccountBalanceInData api003ReqData = new QueryAccountBalanceInData(cc.getButenCode(),
                cc.getAccountNumber());
        api003Req.setIndata(api003ReqData);
        
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        IfaDomesticStockOrderInputApiResponseBuyingPower buyingPower = new IfaDomesticStockOrderInputApiResponseBuyingPower();

        // API003(NRI_QueryAccountBalance 買付余力照会)を呼び出し、買付余力を取得する
        api003Res = apiWrapper.queryAccountBalance(api003Req);
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // 買付余力.受渡日(T+2)
        buyingPower.setDeliveryDateT2(api003Res.getT2().getSettlementDateT());
        // 買付余力.受渡日(T+3)
        buyingPower.setDeliveryDateT3(api003Res.getT3().getSettlementDateT());
        // 買付余力.総合口座（T+2）
        buyingPower.setWholeAccountT2(api003Res.getT2().getBuyingPowerTotal());
        // 買付余力.総合口座（T+3）
        buyingPower.setWholeAccountT3(api003Res.getT3().getBuyingPowerTotal());
        // 買付余力.JrNISA口座（T+2）
        buyingPower.setJrNisaAccountStatusT2(api003Res.getT2Jr().getBuyingPowerTotalJrnisa());
        // 買付余力.JrNISA口座（T+3）
        buyingPower.setJrNisaAccountStatusT3(api003Res.getT3Jr().getBuyingPowerTotalJrnisa());
        // 買付余力.NISA買付可能枠
        buyingPower.setNisaBuy(api003Res.getIsaSeityoBuyLimit());
        
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 預り区分
        resDto.setDepositType(dtoReq.getDepositType());
        // 買付余力
        resDto.setBuyingPower(buyingPower);
        resDtoList.add(resDto);
        
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：銘柄選択、市場選択
     * Dto リクエスト：IfaDomesticStockOrderInputA002RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA002ResponseDto> brandSelectionMarketSelectionA002(
            IfaDomesticStockOrderInputA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderInputServiceImplL.brandSelectionMarketSelectionA002");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // 売却可能数量計算変数
        Long acPositionTmp = 0L;
        // ①取引種別=現物売却の場合、売却可能数量を取得する
        // リクエスト.取引種別 == 現物売却（"2"）の場合
        if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
            QueryAccountPositionSumWebInData api004Req = new QueryAccountPositionSumWebInData();
            // 部店コード(顧客共通情報.部店コード)をセットする
            api004Req.setButenCd(cc.getButenCode());
            // 口座番号(顧客共通情報.口座番号)をセットする
            api004Req.setKozaNo(cc.getAccountNumber());
            // 商品タイプ("K0"(国内株式))をセットする
            api004Req.setSecType(DOMESTIC_STOCK_API004);
            // リクエスト区分("△"(半角スペース):すべての預り)をセットする
            api004Req.setRequestType(SPACE);
            // if(顧客共通情報.ジュニアNISA契約区分 != "1"(契約)) {
            if (!StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
                // 取得口座区分 = "△"(半角スペース):通常口座およびJrNISA口座の第一口座
                api004Req.setAccountGetKbn(SPACE);
                
                // 顧客共通情報.ジュニアNISA契約区分 == "1"(契約)
            } else if (StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
                // 取得口座区分 = "2"：第一、第二口座両方
                api004Req.setAccountGetKbn(ACCOUNT_BOTH);
            }
            // API004(NRI_QueryAccountPositionSumWeb 預り残高一覧リクエスト(サマリー)(次期Web))を呼び出す
            List<QueryAccountPositionSumWebOutData> api004ResList = new ArrayList<QueryAccountPositionSumWebOutData>();
            api004ResList = apiWrapper.queryAccountPositionSumWeb(api004Req);
            for (QueryAccountPositionSumWebOutData api004Res : api004ResList) {
                apiErrorUtil.checkApiResponse(api004Res.getShubetu(), api004Res.getCode(), api004Res.getMessage());
            }
            if (apiErrorUtil.isFatal()) {
                return apiErrorUtil.createDataList(new ArrayList<>(), null);
            }
            
            QueryAccountPositionSumWebOutData api004Res = new QueryAccountPositionSumWebOutData();
            if (api004ResList.size() != 0) {
                // Beanコピー
                BeanUtils.copyProperties(api004Res, api004ResList.get(0));
                if (MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA < Integer
                        .parseInt(api004ResList.get(0).getHitNumber().trim())) {
                    int count = 1;
                    while (true) {
                        if (count == api004ResList.size()) {
                            break;
                        }
                        for (AccountSumWebData tmp : api004ResList.get(count).getAccountSumWebData()) {
                            api004Res.getAccountSumWebData().add(tmp);
                        }
                        count++;
                    }
                    
                }
            }
            
            // 取得したAPI004.明細の中から、以下の条件を満たすレコード１つを取得
            // ■API004.明細.会社コード(先頭4桁) + API004.明細.新旧区分 == リクエスト.銘柄コード
            // ■API004.明細.非特定預り区分 == リクエスト.預り区分
            for (AccountSumWebData accountSum : api004Res.getAccountSumWebData()) {
                String api004BrandCode = accountSum.getCompanyCode().substring(0, 4) + accountSum.getNewOldId();
                if (StringUtils.equals(api004BrandCode, dtoReq.getBrandCode())
                        && StringUtils.equals(accountSum.getHitokuteiKbn(), dtoReq.getDepositType())) {
                    // レスポンス.売却可能数量に(API004.明細.残高数量-API004.明細.売却発注済数量)をセットする
                    acPositionTmp += Long.parseLong(accountSum.getPosition().trim())
                            - Long.parseLong(accountSum.getOrderedQuantity().trim());
                }
            }
        }
        
        // ②営業日リストを取得する。
        InputFct008Dto fct008Req = new InputFct008Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct008Req.setBrandCode(dtoReq.getBrandCode());
        // 期間対象市場(リクエスト.市場)をセットする
        fct008Req.setPeriodTargetMarket(dtoReq.getMarket());
        // 共通関数FCT008を呼び出す
        OutputFct008Dto fct008Res = new OutputFct008Dto();
        fct008Res = fct008.getData(fct008Req);
        
        // レスポンス設定
        IfaDomesticStockOrderInputA002ResponseDto resDto = new IfaDomesticStockOrderInputA002ResponseDto();
        // 取引種別
        resDto.setTradeCd(dtoReq.getTradeCd());
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 預り区分
        resDto.setDepositType(dtoReq.getDepositType());
        // 売却可能数量
        resDto.setAcPosition(acPositionTmp.toString());
        // 営業日リスト
        List<String> businessDayList = new ArrayList<String>();
        for (Date day : fct008Res.getBussiessDaylist()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            businessDayList.add(formatter.format(day));
        }
        resDto.setBusinessDayList(businessDayList);
        
        List<IfaDomesticStockOrderInputA002ResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderInputA002ResponseDto>();
        resDtoList.add(resDto);
        
        // レスポンスをコントローラーに返却する。
        DataList<IfaDomesticStockOrderInputA002ResponseDto> dtoRes = new DataList<IfaDomesticStockOrderInputA002ResponseDto>();
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：更新
     * Dto リクエスト：IfaDomesticStockOrderInputA005RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA005ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA005ResponseDto> updateA005(
            IfaDomesticStockOrderInputA005RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderInputServiceImplL.updateA005");
        }
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // ①買付余力を取得する
        QueryAccountBalanceIn api003Req = new QueryAccountBalanceIn();
        // 部店コード(顧客共通情報.部店コード)をセットする
        // 口座番号(顧客共通情報.口座番号)をセットする
        QueryAccountBalanceInData api003ReqData = new QueryAccountBalanceInData(cc.getButenCode(),
                cc.getAccountNumber());
        api003Req.setIndata(api003ReqData);
        
        QueryAccountBalanceOutData api003Res = new QueryAccountBalanceOutData();
        IfaDomesticStockOrderInputApiResponseBuyingPower buyingPower = new IfaDomesticStockOrderInputApiResponseBuyingPower();
        // API003(NRI_QueryAccountBalance 買付余力照会)を呼び出し、買付余力を取得する
        api003Res = apiWrapper.queryAccountBalance(api003Req);
        // エラーハンドリング
        apiErrorUtil.checkApiResponse(api003Res.getShubetu(), api003Res.getCode(), api003Res.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        // 買付余力.受渡日(T+2)
        buyingPower.setDeliveryDateT2(api003Res.getT2().getSettlementDateT());
        // 買付余力.受渡日(T+3)
        buyingPower.setDeliveryDateT3(api003Res.getT3().getSettlementDateT());
        // 買付余力.総合口座（T+2）
        buyingPower.setWholeAccountT2(api003Res.getT2().getBuyingPowerTotal());
        // 買付余力.総合口座（T+3）
        buyingPower.setWholeAccountT3(api003Res.getT3().getBuyingPowerTotal());
        // 買付余力.JrNISA口座（T+2）
        buyingPower.setJrNisaAccountStatusT2(api003Res.getT2Jr().getBuyingPowerTotalJrnisa());
        // 買付余力.JrNISA口座（T+3）
        buyingPower.setJrNisaAccountStatusT3(api003Res.getT3Jr().getBuyingPowerTotalJrnisa());
        // 買付余力.NISA買付可能枠
        buyingPower.setNisaBuy(api003Res.getIsaSeityoBuyLimit());
        
        // 売却可能数量計算変数
        Long acPositionTmp = 0L;
        // ②売却可能数量を取得する
        QueryAccountPositionSumWebInData api004Req = new QueryAccountPositionSumWebInData();
        // 部店コード(顧客共通情報.部店コード)をセットする
        api004Req.setButenCd(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        api004Req.setKozaNo(cc.getAccountNumber());
        // 商品タイプ("K0"(国内株式))をセットする
        api004Req.setSecType(DOMESTIC_STOCK_API004);
        // リクエスト区分("△"(半角スペース):すべての預り)をセットする
        api004Req.setRequestType(SPACE);
        // if(顧客共通情報.ジュニアNISA契約区分 != "1"(契約)) {
        if (!StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
            // 取得口座区分 = "△"(半角スペース):通常口座およびJrNISA口座の第一口座
            api004Req.setAccountGetKbn(SPACE);
            
            // 顧客共通情報.ジュニアNISA契約区分 == "1"(契約)
        } else if (StringUtils.equals(cc.getJrIsaContractType(), CONTRACT)) {
            // 取得口座区分 = "2"：第一、第二口座両方
            api004Req.setAccountGetKbn(ACCOUNT_BOTH);
        }
        // API004(NRI_QueryAccountPositionSumWeb 預り残高一覧リクエスト(サマリー)(次期Web))を呼び出す
        List<QueryAccountPositionSumWebOutData> api004ResList = new ArrayList<QueryAccountPositionSumWebOutData>();
        api004ResList = apiWrapper.queryAccountPositionSumWeb(api004Req);
        // エラーハンドリング
        for (QueryAccountPositionSumWebOutData api004Res : api004ResList) {
            apiErrorUtil.checkApiResponse(api004Res.getShubetu(), api004Res.getCode(), api004Res.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        QueryAccountPositionSumWebOutData api004Res = new QueryAccountPositionSumWebOutData();
        if (api004ResList.size() != 0) {
            // Beanコピー
            BeanUtils.copyProperties(api004Res, api004ResList.get(0));
            if (MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA < Integer
                    .parseInt(api004ResList.get(0).getHitNumber().trim())) {
                int count = 1;
                while (true) {
                    if (count == api004ResList.size()) {
                        break;
                    }
                    for (AccountSumWebData tmp : api004ResList.get(count).getAccountSumWebData()) {
                        api004Res.getAccountSumWebData().add(tmp);
                    }
                    count++;
                }
                
            }
        }
        
        // 取得したAPI004.明細の中から、以下の条件を満たすレコード１つを取得
        // ■API004.明細.会社コード(先頭4桁) + API004.明細.新旧区分 == リクエスト.銘柄コード
        // ■API004.明細.非特定預り区分 == リクエスト.預り区分
        for (AccountSumWebData accountSum : api004Res.getAccountSumWebData()) {
            String api004BrandCode = accountSum.getCompanyCode().substring(0, 4) + accountSum.getNewOldId();
            if (StringUtils.equals(api004BrandCode, dtoReq.getBrandCode())
                    && StringUtils.equals(accountSum.getHitokuteiKbn(), dtoReq.getDepositType())) {
                // レスポンス.売却可能数量に(API004.明細.残高数量-API004.明細.売却発注済数量)をセットする
                acPositionTmp += Long.parseLong(accountSum.getPosition().trim())
                        - Long.parseLong(accountSum.getOrderedQuantity().trim());
            }
        }
        
        // ②営業日リストを取得する。
        InputFct008Dto fct008Req = new InputFct008Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct008Req.setBrandCode(dtoReq.getBrandCode());
        // 期間対象市場(リクエスト.市場)をセットする
        fct008Req.setPeriodTargetMarket(dtoReq.getMarket());
        // 共通関数FCT008を呼び出す
        OutputFct008Dto fct008Res = new OutputFct008Dto();
        fct008Res = fct008.getData(fct008Req);
        
        // レスポンス設定
        IfaDomesticStockOrderInputA005ResponseDto resDto = new IfaDomesticStockOrderInputA005ResponseDto();
        // 取引種別
        resDto.setTradeCd(dtoReq.getTradeCd());
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 預り区分
        resDto.setDepositType(dtoReq.getDepositType());
        // 売却可能数量
        resDto.setAcPosition(acPositionTmp.toString());
        // 買付余力
        resDto.setBuyingPower(buyingPower);
        // 営業日リスト
        List<String> businessDayList = new ArrayList<String>();
        for (Date day : fct008Res.getBussiessDaylist()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            businessDayList.add(formatter.format(day));
        }
        resDto.setBusinessDayList(businessDayList);
        
        List<IfaDomesticStockOrderInputA005ResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderInputA005ResponseDto>();
        resDtoList.add(resDto);
        
        // レスポンスをコントローラーに返却する。
        DataList<IfaDomesticStockOrderInputA005ResponseDto> dtoRes = new DataList<IfaDomesticStockOrderInputA005ResponseDto>();
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
    /**
     * アクションID：A016
     * アクション名：注文確認
     * Dto リクエスト：IfaDomesticStockOrderInputA016RequestDto
     * Dto レスポンス：IfaDomesticStockOrderInputA016ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return リスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticStockOrderInputA016ResponseDto> orderConfirmA016(
            IfaDomesticStockOrderInputA016RequestDto dtoReq) throws Exception {
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        DataList<IfaDomesticStockOrderInputA016ResponseDto> dtoRes = new DataList<IfaDomesticStockOrderInputA016ResponseDto>();
        List<IfaDomesticStockOrderInputA016ResponseDto> resDtoList = new ArrayList<IfaDomesticStockOrderInputA016ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticStockOrderInputServiceImplL.orderConfirmA016");
        }
        
        // ①利用者の口座に対する権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // 共通関数FCT001を呼び出す
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 == "0"(権限なし)の場合
        if (StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), NO_AUTHORITY)) {
            // レスポンスに権限なしエラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT001.取引停止フラグ == "1"(取引停止口座)の場合
        if (StringUtils.equals(fct001Res.getTradeSuspendFlag(), SUSPENSION_ACCOUNT)) {
            // レスポンスに取引停止口座エラー(errors.cmn.selectedAccount.outOfService)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_OUT_OF_SERVICE.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_OUT_OF_SERVICE.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        
        // ②取引コース媒介可否チェックを行う
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(国内株式（"01"）)をセットする
        fct003Req.setProductCd(DOMESTIC_STOCK);
        // 取引種別(リクエスト.取引種別)をセットする
        if (dtoReq.getTradeCd() == null || dtoReq.getTradeCd().trim().isEmpty()) {
            fct003Req.setTradeCd(STOCK_PURCHASE);
        } else {
            fct003Req.setTradeCd(dtoReq.getTradeCd());
        }
        // 共通関数FCT003を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // FCT003.媒介可否リスト.媒介可否 == "0"(なし)の場合
        if (fct003Res.getMediateProprietyList() != null || fct003Res.getMediateProprietyList().size() != 0) {
            for (int i = 0; i < fct003Res.getMediateProprietyList().size(); i++) {
                if (StringUtils.equals(fct003Res.getMediateProprietyList().get(i).getMediatePropriety(), NON)) {
                    // レスポンスに取引不可エラー(errors.cmn.selectedAccountCourse.unavailable)を返す
                    String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1");
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key,
                            IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED_ACCOUNT_COURSE_UNAVAILABLE.key,
                                    new String[] { codeName }));
                    // レスポンスを呼出元に返却する
                    return dtoRes;
                }
            }
        }
        
        // ユーザ共通情報.CCSログイン用IDのチェックを行う。
        if (StringUtil.isNullOrEmpty(IfaCommonUtil.getUserAccount().getCcsUserId())) {
            // 未設定(Null または空文字）の場合：取引不可エラーを返す。
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_CCSIDUNREGISTERED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CCSIDUNREGISTERED.key));
        }

        // ③口座の取引制限チェックを行う
        InputFct021Dto fct021Req = new InputFct021Dto();
        // 部店コード(顧客共通情報.部店コード)をセットする
        fct021Req.setButenCode(cc.getButenCode());
        // 口座番号(顧客共通情報.口座番号)をセットする
        fct021Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別(国内株式（"01"）)をセットする
        fct021Req.setProductCd(DOMESTIC_STOCK);
        // 取引種別(リクエスト.取引種別)をセットする
        fct021Req.setTradeCd(dtoReq.getTradeCd());
        // 国籍コード(99)をセットする
        fct021Req.setCountryCd(NATIONALITY_CODE);
        // 通貨コード(999)をセットする
        fct021Req.setCurrencyCode(CURRENCY_CODE);
        // 選択市場(リクエスト.市場)をセットする
        fct021Req.setTradeRestrictChkMarket(dtoReq.getMarket());
        // 共通関数FCT021を呼び出す
        OutputFct021Dto fct021Res = new OutputFct021Dto();
        fct021Res = fct021.doCheck(fct021Req);
        // FCT021.注意情報エラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteInfoErrFlag(), YES)) {
            // レスポンスにエラー(errors.cmn.noticeErrorCheck)を返す
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1");
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_NOTICE_ERROR_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_NOTICE_ERROR_CHECK.key, new String[] { codeName }));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.お知らせエラー有無=="1"(あり)の場合
        if (StringUtils.equals(fct021Res.getNoteLimitErrFlag(), YES)) {
            // レスポンスにエラー(errors.informationCheck)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, MessageId.ERRORS_INFORMATION_CHECK.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_INFORMATION_CHECK.key));
            // レスポンスを呼出元に返却する
            return dtoRes;
        }
        // FCT021.注意情報アラート有無=="1"(あり)の場合
        IfaDomesticStockOrderInputA016ResponseDto resDto = new IfaDomesticStockOrderInputA016ResponseDto();
        if (StringUtils.equals(fct021Res.getNoteInfoAlertFlag(), YES)) {
            // 注意情報アラート(warnings.cmn.noticeWarningCheck)を格納する
            String codeName = codelistservice.getValue(MSG_DISPLAY_TARGET_TRADE, "2", "1");
            resDto.setNoticeInfoAlert(
                    IfaCommonUtil.getMessage(MessageId.WARNINGS_NOTICE_WARNING_CHECK.key, new String[] { codeName }));
        }
        // if(FCT021.お知らせアラート有無=="1"(あり)の場合){
        if (StringUtils.equals(fct021Res.getNoteLimitAlertFlag(), YES)) {
            // お知らせアラート(warnings.cmm.informationCheck)を格納する
            resDto.setNoticeAlert(IfaCommonUtil.getMessage(MessageId.WARNINGS_CMM_INFORMATION_CHECK.key));
        }
        
        // ④現物買付の場合、コンプラランクチェックを行う。
        IfaDomesticStockOrderInputApiResponseComplianceRankCheck complianceRankCheck = new IfaDomesticStockOrderInputApiResponseComplianceRankCheck();
        OutputFct006Dto fct006Res = new OutputFct006Dto();
        if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
            InputFct006Dto fct006Req = new InputFct006Dto();
            // 部店コード(顧客共通情報.部店コード)をセットする
            fct006Req.setButenCode(cc.getButenCode());
            // 口座番号(顧客共通情報.口座番号)をセットする
            fct006Req.setAccountNumber(cc.getAccountNumber());
            // 国内外国区分("0":国内)をセットする
            fct006Req.setBrDomesticFgnInd(DOMESTIC);
            // 商品区分("1△":株式)をセットする
            fct006Req.setBrBrandInd(STOCK);
            // 銘柄コード1(リクエスト.銘柄コード)をセットする
            fct006Req.setBrandCode1(dtoReq.getBrandCode());
            // 勧誘区分(リクエスト.勧誘区分)をセットする
            fct006Req.setInvitationType(dtoReq.getKanyuKbn());
            // 受注方法(パラメータ.受注方法)をセットする
            fct006Req.setOrderMethod(dtoReq.getReceiveOrderType());
            // コンプラチェック種類("1":買付注文)をセットする
            fct006Req.setComplaCheckKind(PURCHASE_ORDER);
            // 共通関数FCT006を呼び出す
            fct006Res = fct006.doCheck(fct006Req);
            // FCT006.判定結果==0(ノーマル)の場合
            if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.NORMAL.key)) {
                // ⑤の処理へ進む
                
                // FCT006.判定結果==1(アラート)の場合
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ALERT.key)) {
                // FCT006.メッセージID、FCT006.チェックボックス文言を格納し、⑤の処理へ進む
                // コンプラランクチェック.メッセージ
                complianceRankCheck.setMessage(IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                // コンプラランクチェック.チェックボックス文言
                complianceRankCheck.setChkBoxLabel(fct006Res.getChkBoxLabel());
                
                // FCT006.判定結果==2(エラー)の場合
            } else if (StringUtils.equals(fct006Res.getJudgementResult(), JudgementResult.ERROR.key)) {
                // レスポンスにエラー(FCT006.メッセージID)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getMessageId(),
                        IfaCommonUtil.getMessage(fct006Res.getMessageId()));
                return dtoRes;
            } else {
                // レスポンスにエラー(FCT006.メッセージID)を返す
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, fct006Res.getReturnCode(),
                        fct006Res.getErrMessage());
                return dtoRes;
            }
            // コンプラランクチェック.コンプラチェック用資金性格
            complianceRankCheck.setComplianceRankCheck(fct006Res.getComplaCheckFundCharacter());
        }
        // コンプラランクチェック
        resDto.setComplianceRankCheck(complianceRankCheck);
        
        // ⑤銘柄の取引注意情報を取得する
        InputFct027Dto fct027Req = new InputFct027Dto();
        // 銘柄コード(リクエスト.銘柄コード)をセットする
        fct027Req.setBrandCode(dtoReq.getBrandCode());
        // 共通関数FCT027を呼び出す
        OutputFct027Dto fct027Res = new OutputFct027Dto();
        fct027Res = fct027.getData(fct027Req);
        // if(FCT027.規制銘柄区分==1(規制銘柄)の場合){
        if (StringUtils.equals(fct027Res.getRegKbn(), REGULATION_BRAND)) {
            // 取引注意情報(銘柄)メッセージ(warnings.dms.informationCheck)を格納する
            resDto.setTradeNoticeInfoBrandMsg(IfaCommonUtil.getMessage(MessageId.WARNINGS_DMS_INFORMATION_CHECK.key));
        }
        
        UserAccount ua = IfaCommonUtil.getUserAccount();
        // ⑥注文種別に応じて、注文確認を行う
        // if(リクエスト.注文種別==通常/逆指値 || リクエスト.注文種別==OCO注文の場合){
        if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)
                || StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
            EstimateStockOrderInData api001ReqData = new EstimateStockOrderInData();
            // 部店コード(顧客共通情報.部店コード)をセットする
            api001ReqData.setButenCd(cc.getButenCode());
            // 口座番号(顧客共通情報.口座番号)をセットする
            api001ReqData.setKozaNo(cc.getAccountNumber());
            // アカウントID(ALL"0")
            api001ReqData.setAccountId("00000000000");
            // アカウント毎の連番(ALL"0")
            api001ReqData.setNumber("0000000");
            // オリジン("0")
            api001ReqData.setOrigin("0");
            // 銘柄コード(リクエスト.銘柄コード)
            api001ReqData.setBrandCd(dtoReq.getBrandCode());
            // 売買区分(リクエスト.取引種別)
            api001ReqData.setTradeKbn(dtoReq.getTradeCd());
            // 注文株数(リクエスト.数量)
            api001ReqData.setQuantity(dtoReq.getQuantity());
            // 注文種別 == 通常/逆指値の場合
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // 指成区分(リクエスト.通常/逆指値.執行条件)
                api001ReqData.setSasinariKbn(dtoReq.getNormalPriceLimitReverseSasinariJyouken());
                
                // 注文種別=OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // 指成区分(リクエスト.OCO1.執行条件)
                api001ReqData.setSasinariKbn(dtoReq.getOco1SasinariJyouken());
            }
            // 注文種別 == 通常/逆指値の場合
            api001ReqData.setPrice("0000000000");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // 通常/逆指値.執行方法 = 指値の場合
                if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(), SasinariHouhou.LIMIT.key)) {
                    // 指値(通常/逆指値.注文単価（指値）)
                    api001ReqData.setPrice(dtoReq.getNormalPriceLimitReversePrice());
                    
                    // 通常/逆指値.執行方法 == 逆指値の場合
                } else if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(),
                        SasinariHouhou.REVERCE_LIMIT.key)) {
                    // 通常/逆指値.執行方法（逆指値） == 指値の場合
                    if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseGyakusasiHouhou(),
                            SasinariHouhou.LIMIT.key)) {
                        // 指値(通常/逆指値.注文単価（逆指値/指値）)
                        api001ReqData.setPrice(dtoReq.getNormalPriceLimitReversePrice());
                    }
                }
                
                // 注文種別 == OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // 指値(OCO1.注文単価)
                api001ReqData.setPrice(dtoReq.getOco1Price());
            }
            // 取引種別 == 現物買付の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
                // "1"：保護預り
                api001ReqData.setUkewHoho("1");
                
                // 取引種別 == 現物売却の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
                // "1"：預り
                api001ReqData.setUkewHoho("1");
            }
            // 市場(リクエスト.市場)
            api001ReqData.setMarket(dtoReq.getMarket());
            // 取引種別 == 現物買付の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
                // 譲渡益税区分("△")
                api001ReqData.setJoZeiKbn(" ");
                
                // 取引種別 == 現物売却の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
                // 譲渡益税区分("2"：申告分離)
                api001ReqData.setJoZeiKbn("2");
            }
            // 非特定預り売買区分(リクエスト.預り区分)
            api001ReqData.setHitokuteiTradeKbn(dtoReq.getDepositType());
            // 弁済期限("△")
            api001ReqData.setPaymentLimit(" ");
            // if(期間.期間条件 == 当日中の場合){
            if (StringUtils.equals(dtoReq.getPeriodTerms(), TODAY)) {
                // 有効期限("△△△△△△△△")
                api001ReqData.setLimit("        ");
            } else {
                // 有効期限(期間.日付　YYYYMMDD形式)
                api001ReqData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLimit()));
            }
            // 摘要(ALL"△")
            api001ReqData.setSummary("                              ");
            // 決済方法区分(ALL"△")
            api001ReqData.setPaymentKbn(" ");
            // 決済方法(ALL"△")
            api001ReqData.setPaymentMethod("          ");
            // 振込先銀行区分(ALL"△")
            api001ReqData.setBankKbn(" ");
            // 振込先銀行名(ALL"△")
            api001ReqData.setBankName("                    ");
            // 受付経路区分("0")
            api001ReqData.setCallcenterKbn("0");
            // ユーザーＩＤ(ユーザ共通情報.CCSログイン用ID)
            api001ReqData.setUserId(ua.getMedUsers().getCcsUserId());
            // ベティング区分("△")
            api001ReqData.setVettingKbn(" ");
            // 与信チェック用時価(ALL"△")
            api001ReqData.setCheckPrice("          ");
            // 手数料区分(顧客共通情報.契約締結前交付書面コード)
            api001ReqData.setComId(cc.getCustomerAttribute());
            // 余力チェック区分("△")
            api001ReqData.setCheckId(" ");
            // 注文種別=通常/逆指値の場合
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // 通常/逆指値.執行方法 == 指値 || 通常/逆指値.執行方法 == 成行の場合
                if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(), SasinariHouhou.LIMIT.key)
                        || StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(),
                                SasinariHouhou.MARKETORDER.key)) {
                    // RBE注文種別(ALL"△")
                    api001ReqData.setRbeOrderKind("   ");
                    
                    // 通常/逆指値.執行方法 == 逆指値の場合
                } else if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(),
                        SasinariHouhou.REVERCE_LIMIT.key)) {
                    // RBE注文種別("SLO")
                    api001ReqData.setRbeOrderKind("SLO");
                }
                
                // 注文種別 == OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // RBE注文種別("OCO")
                api001ReqData.setRbeOrderKind("OCO");
            }
            // 注文種別=通常/逆指値の場合
            api001ReqData.setTriggerZone(" ");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // 通常/逆指値.執行方法=逆指値の場合
                if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(),
                        SasinariHouhou.REVERCE_LIMIT.key)) {
                    // 取引種別 == 現物買付の場合
                    if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
                        // トリガ発動ゾーン("0")
                        api001ReqData.setTriggerZone("0");
                        
                        // 取引種別 == 現物売却の場合
                    } else if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
                        // トリガ発動ゾーン("1")
                        api001ReqData.setTriggerZone("1");
                    }
                }
                
                // 注文種別=OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // 取引種別 == 現物買付の場合
                if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
                    // トリガ発動ゾーン("0")
                    api001ReqData.setTriggerZone("0");
                    
                    // 取引種別 == 現物売却の場合
                } else if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
                    // トリガ発動ゾーン("1")
                    api001ReqData.setTriggerZone("1");
                }
            }
            // 注文種別 == 通常/逆指値の場合
            api001ReqData.setTriggerPrice("0000000000");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // 通常/逆指値.執行方法 == 逆指値の場合
                if (StringUtils.equals(dtoReq.getNormalPriceLimitReverseSasinariHouhou(),
                        SasinariHouhou.REVERCE_LIMIT.key)) {
                    // トリガ値段(通常/逆指値.発火条件価格（逆指値）)
                    api001ReqData.setTriggerPrice(dtoReq.getNormalPriceLimitReverseTriggerPrice());
                }
                
                // 注文種別 == OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // トリガ値段(OCO2.発火条件価格（逆指値）)
                api001ReqData.setTriggerPrice(dtoReq.getOco2TriggerPrice());
            }
            //注文種別 == 通常/逆指値の場合
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.NORMALPRICE_LIMITREVERSE.key)) {
                // OCO指成区分("△")
                api001ReqData.setOcoSasinariKbn(" ");
                // OCO指値(ALL"△")
                api001ReqData.setOcoPrice("          ");
                
                // 注文種別 == OCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.OCO.key)) {
                // OCO指成区分(OCO2.執行条件（逆指値）)
                api001ReqData.setOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
                // OCO指値(OCO2.注文単価)
                api001ReqData.setOcoPrice(dtoReq.getOco2Price());
            }
            // IPアドレス("ifap")
            api001ReqData.setIpAddress("ifap");
            // if(市場=A:当社優先市場／SORの場合){
            if (StringUtils.equals(api001ReqData.getMarket(), "A")) {
                // SOR受注時板乗せ市場区分("tse")
                api001ReqData.setSorLastMarket("tse");
            } else {
                // SOR受注時板乗せ市場区分("△△△")
                api001ReqData.setSorLastMarket("   ");
            }
            // 引継ぎID("△")
            api001ReqData.setTransId(" ");
            // 媒介("1")
            api001ReqData.setIntermediary("1");
            EstimateStockOrderIn api001Req = new EstimateStockOrderIn();
            api001Req.setIndata(api001ReqData);
            // API001('NRI_EstimateStockOrder 国内株式注文確認)を呼び出す
            EstimateStockOrderOutData api001Res = new EstimateStockOrderOutData();
            api001Res = apiWrapper.estimateStockOrder(api001Req);
            // エラーハンドリング
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), MessageId.ERRORS_ORDER_EXECUTION_FAILED.key);
            }
            
            // if(API.001内部エラー区分=="1"(内部取引に該当する)の場合){
            if (StringUtils.equals(api001Res.getInsiderErrKbn(), "1")) {
                // 内部者確認メッセージ(warning.dms.insider.exist)を格納し、⑦の処理へ
                resDto.setInsiderConfirmMsg(IfaCommonUtil.getMessage(MessageId.WARNINGS_INSIDER_EXIST.key));
            }
            
            // 種別
            resDto.setShubetu(api001Res.getShubetu());
            // エラーコード
            resDto.setCode(api001Res.getCode());
            // エラーメッセージ
            resDto.setErrMessage(api001Res.getMessage());
            // ジュニアNISA振替金額
            resDto.setJrnisaTransferAmount(api001Res.getJrnisaTransferAmount());
            // 見積単価
            resDto.setQuoteUnitPrice(api001Res.getEstimatePrice());
            // 約定金額
            resDto.setContractAmount(api001Res.getAmount());
            // 手数料/諸費用
            resDto.setCharge(api001Res.getCommission());
            // 消費税
            resDto.setConsumptionTax(api001Res.getConsumptionTax());
            // 讓渡益税
            resDto.setYieldTax(api001Res.getCapitalGainTax());
            // 精算金額
            resDto.setSettlementAmount(api001Res.getNetAmount());
            // 投資可能枠
            resDto.setInvestableQuote(api001Res.getIsaBuyLimit());
            // 約定予定日
            resDto.setContractDate(api001Res.getTradeDate());
            // 受渡予定日
            resDto.setDeliveryDate(api001Res.getSettlementDate());
            // 受注日時
            resDto.setOrderDayTime(api001Res.getAcceptDate() + " " + api001Res.getAcceptTime());
            // 注文入力市場
            resDto.setOrderedMarket(api001Res.getOrderedMarket());
            
            // リクエスト.注文種別 == IFD ||リクエスト.注文種別 == IFDOCOの場合
        } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFD.key)
                || StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
            EstimateStockOrderAutoInData api002ReqData = new EstimateStockOrderAutoInData();
            // 部店コード(顧客共通情報.部店コード)をセットする
            api002ReqData.setButenCd(cc.getButenCode());
            // 口座番号(顧客共通情報.口座番号)をセットする
            api002ReqData.setKozaNo(cc.getAccountNumber());
            // アカウントID("":現在未使用、設定内容は任意)
            api002ReqData.setAccountId("");
            // アカウント毎の連番("":現在未使用、設定内容は任意)
            api002ReqData.setNumber("");
            // オリジン("":現在未使用、設定内容は任意)
            api002ReqData.setOrigin("");
            // 銘柄コード(リクエスト.銘柄コード)
            api002ReqData.setBrandCd(dtoReq.getBrandCode());
            // 売買区分(リクエスト.取引種別)
            api002ReqData.setTradeKbn(dtoReq.getTradeCd());
            // 注文株数(リクエスト.数量)
            api002ReqData.setQuantity(dtoReq.getQuantity());
            // 指成区分(リクエスト.IFD1.執行条件)
            api002ReqData.setSasinariKbn(dtoReq.getIfd1SasinariJyouken());
            // IFD1.執行方法=指値の場合
            api002ReqData.setPrice("0000000000");
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.LIMIT.key)) {
                // 指値(IFD1.注文単価（指値）)
                api002ReqData.setPrice(dtoReq.getIfd1Price());
                
                // IFD1.執行方法 == 逆指値の場合
            } else if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                // IFD1.執行方法（逆指値） == 指値の場合
                if (StringUtils.equals(dtoReq.getIfd1GyakusasiHouhou(), SasinariHouhou.LIMIT.key)) {
                    // 指値(IFD1.注文単価（逆指値/指値）
                    api002ReqData.setPrice(dtoReq.getIfd1Price());
                }
            }
            // 受渡方法("1")
            api002ReqData.setUkewHoho("1");
            // 市場(リクエスト.市場)
            api002ReqData.setMarket(dtoReq.getMarket());
            // 取引種別 == 現物買付の場合
            if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_PURCHASE)) {
                // 譲渡益税区分("△")
                api002ReqData.setJoZeiKbn(" ");
                
                // 取引種別 == 現物売却の場合
            } else if (StringUtils.equals(dtoReq.getTradeCd(), STOCK_SELL)) {
                // 譲渡益税区分("2")
                api002ReqData.setJoZeiKbn("2");
            }
            // 非特定預り売買区分(リクエスト.預り区分)
            api002ReqData.setHitokuteiTradeKbn(dtoReq.getDepositType());
            // 弁済期限("△")
            api002ReqData.setPaymentLimit(" ");
            // 期間.期間条件 == 当日中の場合
            if (StringUtils.equals(dtoReq.getPeriodTerms(), TODAY)) {
                // 有効期限("△△△△△△△△")
                api002ReqData.setLimit("        ");
            } else {
                // 有効期限(期間.日付　YYYYMMDD形式)
                api002ReqData.setLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getLimit()));
            }
            // 摘要(ALL"△")
            api002ReqData.setSummary("                              ");
            // 決済方法区分("△")
            api002ReqData.setPaymentKbn(" ");
            // 決済方法(ALL"△")
            api002ReqData.setPaymentMethod("          ");
            // 振込先銀行区分("△")
            api002ReqData.setBankKbn(" ");
            // 振込先銀行名(ALL"△")
            api002ReqData.setBankName("                    ");
            // 受付経路区分("0")
            api002ReqData.setCallcenterKbn("0");
            // ユーザーＩＤ(ユーザ共通情報.CCSログイン用ID)
            api002ReqData.setUserId(ua.getMedUsers().getCcsUserId());
            // ベティング区分("△")
            api002ReqData.setVettingKbn(" ");
            // 与信チェック用時価(ALL"△")
            api002ReqData.setCheckPrice("          ");
            // 手数料区分(顧客共通情報.契約締結前交付書面コード)
            api002ReqData.setComId(cc.getCustomerAttribute());
            // 余力チェック区分("△")
            api002ReqData.setCheckId(" ");
            // if(IFD1.執行方法 == 指値 || IFD1.執行方法 == 成行の場合){
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.LIMIT.key)
                    || StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.MARKETORDER.key)) {
                // RBE注文種別(ALL"△")
                api002ReqData.setRbeOrderKind("   ");
                
                // IFD1.執行方法 == 逆指値の場合
            } else if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                // RBE注文種別("SLO")
                api002ReqData.setRbeOrderKind("SLO");
            }
            // IFD1.執行方法 == 逆指値　の場合
            api002ReqData.setTriggerZone(" ");
            api002ReqData.setTriggerPrice("0000000000");
            if (StringUtils.equals(dtoReq.getIfd1SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                // トリガ発動ゾーン("0")
                api002ReqData.setTriggerZone("0");
                // トリガ値段(IFD1.発火条件価格（逆指値）)
                api002ReqData.setTriggerPrice(dtoReq.getIfd1TriggerPrice());
            }
            // OCO指成区分("△")
            api002ReqData.setOcoSasinariKbn(" ");
            // OCO指値(ALL"△")
            api002ReqData.setOcoPrice("          ");
            // 自動注文種別("IF△△")
            api002ReqData.setAutoOrderKind("IF  ");
            // 注文種別 == IFDの場合
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFD.key)) {
                // DONE指成区分(IFD2.執行条件)
                api002ReqData.setDoneSasinariKbn(dtoReq.getIfd2SasinariJyouken());
                
                // 注文種別 == IFDOCO
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
                // DONE指成区分(OCO1.執行条件)
                api002ReqData.setDoneSasinariKbn(dtoReq.getOco1SasinariJyouken());
            }
            // 注文種別=IFDの場合
            api002ReqData.setDonePrice("0000000000");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFD.key)) {
                //IFD2.執行方法 == 指値の場合
                if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.LIMIT.key)) {
                    // DONE指値(IFD2.注文単価)
                    api002ReqData.setDonePrice(dtoReq.getIfd2Price());
                    
                    // IFD2.執行方法 == 逆指値の場合
                } else if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                    // IFD2.執行方法（逆指値）== 指値の場合
                    if (StringUtils.equals(dtoReq.getIfd2GyakusasiHouhou(), SasinariHouhou.LIMIT.key)) {
                        // DONE指値(IFD2.注文単価)
                        api002ReqData.setDonePrice(dtoReq.getIfd2Price());
                    }
                }
                
                // 注文種別=IFDOCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
                // if(OCO1執行方法 == 指値の場合){
                if (StringUtils.equals(dtoReq.getOco1SasinariHouhou(), SasinariHouhou.LIMIT.key)) {
                    // DONE指値(OCO1.注文単価)
                    api002ReqData.setDonePrice(dtoReq.getOco1Price());
                }
            }
            // IFD2.期間.期間条件=当日中の場合
            if (StringUtils.equals(dtoReq.getIfd2PeriodTerms(), TODAY)) {
                // DONE有効期限("△△△△△△△△")
                api002ReqData.setDoneLimit("        ");
            } else {
                // DONE有効期限(IFD2.期間.日付　YYYYMMDD形式)
                api002ReqData.setDoneLimit(DateFormatUtil.dateFormatToYmdNoSign(dtoReq.getIfd2Limit()));
            }
            // 注文種別=IFDの場合
            api002ReqData.setDoneRbeOrderKind("   ");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFD.key)) {
                // if(IFD2.執行方法 == 指値 || IFD2.執行方法 == 成行の場合){
                if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.LIMIT.key)
                        || StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.MARKETORDER.key)) {
                    // DONERBE注文種別(ALL"△")
                    api002ReqData.setDoneRbeOrderKind("   ");
                    
                    // IFD2.執行方法 == 逆指値の場合
                } else if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                    // DONERBE注文種別("SLO")
                    api002ReqData.setDoneRbeOrderKind("SLO");
                }
                
                // 注文種別 == IFDOCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
                // DONERBE注文種別("OCO")
                api002ReqData.setDoneRbeOrderKind("OCO");
            }
            // 注文種別 == IFDの場合
            api002ReqData.setDoneTriggerZone(" ");
            api002ReqData.setDoneTriggerPrice("0000000000");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFD.key)) {
                // IFD2.執行方法 == 逆指値の場合
                if (StringUtils.equals(dtoReq.getIfd2SasinariHouhou(), SasinariHouhou.REVERCE_LIMIT.key)) {
                    // DONEトリガ発動ゾーン("1")
                    api002ReqData.setDoneTriggerZone("1");
                    // DONEトリガ値段(IFD2.発火条件価格（逆指値）)
                    api002ReqData.setDoneTriggerPrice(dtoReq.getIfd2TriggerPrice());
                }
                
                // 注文種別 == IFDOCOの場合
            } else if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
                // DONEトリガ発動ゾーン("1")
                api002ReqData.setDoneTriggerZone("1");
                // DONEトリガ値段(OCO2.発火条件価格（逆指値）)
                api002ReqData.setDoneTriggerPrice(dtoReq.getOco2TriggerPrice());
            }
            // 注文種別 == IFDOCOの場合
            api002ReqData.setDoneOcoSasinariKbn(" ");
            api002ReqData.setDoneOcoPrice("0000000000");
            if (StringUtils.equals(dtoReq.getOrderKind(), OrderKind.IFDOCO.key)) {
                // DONEOCO指成区分(OCO2.執行条件)
                api002ReqData.setDoneOcoSasinariKbn(dtoReq.getOco2GyakusasiJyouken());
                // DONEOCO指値(OCO2.注文単価)
                api002ReqData.setDoneOcoPrice(dtoReq.getOco2Price());
            }
            // IPアドレス("ifap")
            api002ReqData.setIpAddress("ifap");
            // 引継ぎID("△")
            api002ReqData.setTransId(" ");
            // 媒介("1")
            api002ReqData.setIntermediary("1");
            // 新規約定日(ALL"△")
            api002ReqData.setOpenTradeDate("        ");
            // 新規単価(ALL"△")
            api002ReqData.setOpenPrice("            ");
            // 一般信用売弁済期限年月日区分("△")
            api002ReqData.setIppanMgPaymentKbn(" ");
            // 一般信用売弁済期限年月日数(ALL"△")
            api002ReqData.setIppanMgPaymentLimit("  ");
            EstimateStockOrderAutoIn api002Req = new EstimateStockOrderAutoIn();
            api002Req.setIndata(api002ReqData);
            // API002(NRI_EstimateStockOrderAuto 自動注文(国内株式現物・信用)新規注文確認)を呼び出す
            EstimateStockOrderAutoOutData api002Res = new EstimateStockOrderAutoOutData();
            api002Res = apiWrapper.estimateStockOrderAuto(api002Req);
            // エラーハンドリング
            if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                return apiErrorUtil.createDataList(new ArrayList<>(), MessageId.ERRORS_ORDER_EXECUTION_FAILED.key);
            }
            
            // if(API002.取引承認概要区分=="1"||API002.取引承認概要区分=="4"||API002.取引承認概要区分=="5"の場合){
            if (StringUtils.equals(api002Res.getInsiderErrKbn(), "1")
                    || StringUtils.equals(api002Res.getInsiderErrKbn(), "4")
                    || StringUtils.equals(api002Res.getInsiderErrKbn(), "5")) {
                // 内部者確認メッセージ(warning.dms.insider.exist)を格納
                resDto.setInsiderConfirmMsg(IfaCommonUtil.getMessage(MessageId.WARNINGS_INSIDER_EXIST.key));
            }
            
            // 種別
            resDto.setShubetu(api002Res.getShubetu());
            // エラーコード
            resDto.setCode(api002Res.getCode());
            // エラーメッセージ
            resDto.setErrMessage(api002Res.getMessage());
            // ジュニアNISA振替金額
            resDto.setJrnisaTransferAmount(api002Res.getJrnisaTransferAmount());
            // 見積単価
            resDto.setQuoteUnitPrice(api002Res.getEstimatePrice());
            // 約定金額
            resDto.setContractAmount(api002Res.getAmount());
            // 手数料/諸費用
            resDto.setCharge(api002Res.getCommission());
            // 消費税
            resDto.setConsumptionTax(api002Res.getConsumptionTax());
            // 讓渡益税
            resDto.setYieldTax(api002Res.getCapitalGainTax());
            // 精算金額
            resDto.setSettlementAmount(api002Res.getNetAmount());
            // 投資可能枠
            resDto.setInvestableQuote(api002Res.getIsaBuyLimit());
            // 約定予定日
            resDto.setContractDate(api002Res.getTradeDate());
            // 受渡予定日
            resDto.setDeliveryDate(api002Res.getSettlementDate());
            // 受注日時
            resDto.setOrderDayTime(api002Res.getAcceptDate() + " " + api002Res.getAcceptTime());
            // 注文入力市場
            resDto.setOrderedMarket(api002Res.getOrderedMarket());
        }
        
        // 銘柄名
        resDto.setBrandName(fct027Res.getBrandName());
        // リクエスト内容
        resDto.setRequestContents(dtoReq);
        resDtoList.add(resDto);
        
        if (dtoRes.getErrorLevel() == ErrorLevel.WARNING.getId()) {
            if (resDtoList != null) {
                dtoRes.setDataList(resDtoList);
            }
            dtoRes.setTotalSize(dtoRes.getDataList().size());
            dtoRes.setMaxRownum(dtoRes.getDataList().size());
            dtoRes.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
            return dtoRes;
        }
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
}
