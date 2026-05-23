package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.util.OrderStatusUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticTradeStatusListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MarginTradeKbn;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1ExecSumData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderWebDetail;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderWebIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderWebOutData;

/**
 * 画面ID：SUB0202_0105-01
 * 画面名：国内株当日約定状況（一覧）
 * @author <author-name>
 *
 * 2023/09/20 新規作成
 */
@Component(value = "cmpIfaDomesticTradeStatusListService")
public class IfaDomesticTradeStatusListServiceImpL implements IfaDomesticTradeStatusListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticTradeStatusListServiceImpL.class);
    
    /**
     * API呼び出しクラス
     */
    @Autowired
    private ApiWrapper apiwrapper;
    
    /**
     * 共通関数Function001クラス
     */
    @Autowired
    private Fct001 fct001;
    
    /**
     * 区分値取得クラス
     */
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private OrderStatusUtil orderStatusUtil;
    
    /** 検索番号フォーマット */
    private static final String SEARCH_FORMAT = "00%d";
    
    /** 現物信用 検索番号 FROM指定 */
    private static final String SPOT_MARGIN_SEARCH_FROM_DEFAULT = String.format("%05d", 1);
    
    /**　現物信用 検索番号 TO指定 */
    private static final String SPOT_MARGIN_SEARCH_TO_DEFAULT = String.format("%05d", 100);
    
    /** 検索番号 変更 FROM指定 */
    private static final int SEARCH_FROM_DEFAULT = 1;
    
    /** 検索番号　変更 TO指定 */
    private static final int SEARCH_TO_DEFAULT = 100;
    
    /** 検索結果件数 0件 */
    private static final int SEARCH_RESULT_0_ITEMS = 0;
    
    /** APIリクエスト日付（8桁固定） */
    private static final String API_REQUEST_DATE = String.format("%8s", "");
    
    /** API001.商品区分:'S'（株式） */
    private static final String A001_SEC_ID = "S";
    
    /** API001.取引種別:'A'（全て） */
    private static final String A001_TRADE_TYPE = "A";
    
    /** API001.約定部.取引区分:"7"(上場CW買い) */
    private static final String LISTED_CW_BUY = "7";
    
    /** API001.約定部.取引区分:"8"(上場CW売り) */
    private static final String LISTED_CW_SELL = "8";
    
    /** 約定部.一般信用売弁済期限年月日区分:"Y"(年単位) */
    private static final String SELL_SETTLEMENT_DEADLINE_YEAR = "Y";
    
    /** 約定部.一般信用売弁済期限年月日区分:"M"(月単位) */
    private static final String SELL_SETTLEMENT_DEADLINE_MONTH = "M";
    
    /** 約定部.一般信用売弁済期限年月日区分:"D"(日単位) */
    private static final String SELL_SETTLEMENT_DEADLINE_DAY = "D";
    
    /** API002.商品区分:'G'（現引現渡） */
    private static final String A002_SEC_ID = "G";
    
    /** API002.取引種別:' '（当日確定分） */
    private static final String A002_REQUEST_TYPE = " ";
    
    /** 現引現渡 検索番号 FROM指定 */
    private static final String RECEIPT_DELIVERY_SEARCH_FROM_DEFAULT = String.format("%05d", 1);
    
    /**　現引現渡 検索番号 TO指定 */
    private static final String RECEIPT_DELIVERY_SEARCH_TO_DEFAULT = String.format("%05d", 100);
    
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 区分定義.ドメインID_預り区分（国内） */
    private static final String DOMESTIC_DEPOSIT_TYPE = "DOMESTIC_DEPOSIT_TYPE";
    
    /** 区分定義.ドメインID_強制区分 */
    private static final String COERCION_TYPE = "COERCION_TYPE";
    
    /** 区分定義.ドメインID_取引種別（国内株式） */
    private static final String DOMESTIC_STOCK_TRADE_CLASS = "DOMESTIC_STOCK_TRADE_CLASS";
    
    /** 区分定義.ドメインID_取引種別（現引現渡） */
    private static final String RECEIPT_DELIVERY_TRADE_CLASS = "RECEIPT_DELIVERY_TRADE_CLASS";
    
    /** 区分定義.ドメインID_代用適格区分 */
    private static final String COLLATERAL_ELIGIBLE_TYPE = "COLLATERAL_ELIGIBLE_TYPE";
    
    /** APIタイプ:EC-GW */
    private static final String API_TYPE_EC_GW = "EC-GW";
    
    /** コード値保持Map */
    private Map<String, String> tempMap;
    
    /** ジュニアISA契約区分 = "1":契約 */
    private static final String JR_ISA_CONTRACT_TYPE_CONTRACT = "1";
    
    /** API001:取得口座区分 通常口座およびJrNISA口座の第一口座 */
    private static final String ACCOUNT_GET_KBN_FIRST = " ";
    
    /** API001:取得口座区分 JrNISA口座(第一、第二口座両方) */
    private static final String ACCOUNT_GET_KBN_JRNISA = "2";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticTradeStatusListA001DtoRequest
     * Dto レスポンス：IfaDomesticTradeStatusListA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticTradeStatusListA001DtoResponse> initializeA001(
            IfaDomesticTradeStatusListA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaDomesticTradeStatusListServiceImplL.initializeA001");
        
        // 初期化
        String errorMessage = StringUtil.EMPTY_STRING;
        String errorMessageId = StringUtil.EMPTY_STRING;
        tempMap = new HashMap<String, String>();
        
        DataList<IfaDomesticTradeStatusListA001DtoResponse> dtoRes = new DataList<IfaDomesticTradeStatusListA001DtoResponse>();
        List<IfaDomesticTradeStatusListA001DtoResponse> resDtoList = new ArrayList<IfaDomesticTradeStatusListA001DtoResponse>();
        IfaDomesticTradeStatusListA001DtoResponse resDto = new IfaDomesticTradeStatusListA001DtoResponse();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // FCT001：利用者顧客参照権限チェック
        if (!callFCT001(cc.getButenCode(), cc.getAccountNumber())) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                    new String[] { cc.getButenCode(), cc.getAccountNumber() });
            errorMessageId = ERRORS_BUTENACCOUNTNOTEXIST;
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, errorMessageId, errorMessage);
        }
        
        //現物信用約定一覧
        List<IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList> spotMarginTradeList = new ArrayList<IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList>();
        //API001:現物信用約定一覧取得
        getSpotMarginTradeList(cc, spotMarginTradeList, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        //現物信用約定一覧をセット
        if (SEARCH_RESULT_0_ITEMS != spotMarginTradeList.size()) {
            resDto.setSpotMarginTradeList(spotMarginTradeList);
        }
        //現物信用約定取得件数をセット
        resDto.setSpotMarginTradeCount(Integer.toString(spotMarginTradeList.size()));
        
        //現引現渡約定一覧
        List<IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList> receiptDeliveryTradeList = new ArrayList<IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList>();
        //API002:現引現渡約定一覧取得
        getReceiptDeliveryTradeList(cc, receiptDeliveryTradeList, apiErrorUtil);
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        //現引現渡約定一覧をセット
        if (SEARCH_RESULT_0_ITEMS != receiptDeliveryTradeList.size()) {
            resDto.setReceiptDeliveryTradeList(receiptDeliveryTradeList);
        }
        //現引現渡約定取得件数をセット
        resDto.setReceiptDeliveryTradeCount(Integer.toString(receiptDeliveryTradeList.size()));
        
        resDtoList.add(resDto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
    /** 
     * API001:現物信用約定一覧を取得する
     * @param cc
     * @param spotMarginTradeList
     * @param apiErrorUtil　エラーハンドリング
     * @throws Exception 
     */
    private void getSpotMarginTradeList(CustomerCommon cc,
            List<IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList> spotMarginTradeList, ApiErrorUtil apiErrorUtil) throws Exception {
        
        //現物信用約定一覧を取得する以下の処理を行う。
        //API001呼出し
        QueryExecutionSummary1InData api001InDto = new QueryExecutionSummary1InData();
        QueryExecutionSummary1In inputA001 = new QueryExecutionSummary1In();
        inputA001.setIndata(setQueryExecutionSummary1InData(cc, api001InDto, SPOT_MARGIN_SEARCH_FROM_DEFAULT,
                SPOT_MARGIN_SEARCH_TO_DEFAULT));
        
        //API001取得結果
        QueryExecutionSummary1OutData api001ResDto = apiwrapper.queryExecutionSummary1(inputA001);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return;
        }
        
        //1件ずつ約定部をセット
        setSpotMarginTradeList(api001ResDto.getExecSumData(), spotMarginTradeList);
        
        //        //API001.検索結果編集後件数
        //        int totalNumberA001 = Integer.parseInt(api001ResDto.getTotalNumber());
        //        int searchCntA001 = 0;
        //        String searchFromA001 = SPOT_MARGIN_SEARCH_FROM_DEFAULT;
        //        String searchToA001 = SPOT_MARGIN_SEARCH_TO_DEFAULT;
        //        
        //        //繰り返し処理開始
        //        while (Integer.parseInt(searchFromA001) <= totalNumberA001) {
        //            
        //            //1件ずつ約定部をセット
        //            setSpotMarginTradeList(api001ResDto.getExecSumData(), spotMarginTradeList);
        //            
        //            //2回目以降の検索で1回目取得できなかったデータを取得できるように検索番号を変更
        //            searchCntA001++;
        //            searchFromA001 = String.format(SEARCH_FORMAT, (searchCntA001 * SEARCH_TO_DEFAULT) + SEARCH_FROM_DEFAULT);
        //            searchToA001 = String.format(SEARCH_FORMAT, (searchCntA001 + SEARCH_FROM_DEFAULT) * SEARCH_TO_DEFAULT);
        //            
        //            //次のデータを取得する。
        //            api001InDto = new QueryExecutionSummary1InData();
        //            inputA001.setIndata(setQueryExecutionSummary1InData(cc, api001InDto, searchFromA001, searchToA001));
        //            api001ResDto = apiwrapper.queryExecutionSummary1(inputA001);
        //            
        //            //検索結果編集後件数を更新
        //            totalNumberA001 = Integer.parseInt(api001ResDto.getTotalNumber());
        //        }
    }
    
    /** 
     * API002:現引現渡約定一覧を取得する
     * @param cc
     * @param receiptDeliveryTradeList
     * @param apiErrorUtil エラーハンドリング
     * @throws Exception 
     */
    private void getReceiptDeliveryTradeList(CustomerCommon cc,
            List<IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList> receiptDeliveryTradeList, ApiErrorUtil apiErrorUtil)
            throws Exception {
        
        //現引現渡約定一覧を取得する以下の処理を行う。
        //API002呼出し
        QueryMgRDOrderWebInData api002InDto = new QueryMgRDOrderWebInData();
        QueryMgRDOrderWebIn inputA002 = new QueryMgRDOrderWebIn();
        inputA002.setIndata(setQueryMgRDOrderWebInData(cc, api002InDto, RECEIPT_DELIVERY_SEARCH_FROM_DEFAULT,
                RECEIPT_DELIVERY_SEARCH_TO_DEFAULT));
        
        //API002取得結果
        QueryMgRDOrderWebOutData api002ResDto = apiwrapper.queryMgRDOrderWeb(inputA002);
        apiErrorUtil.checkApiResponse(api002ResDto.getShubetu(), api002ResDto.getCode(), api002ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return;
        }
        
        //1件ずつ約定部をセット
        setReceiptDeliveryTradeList(api002ResDto.getQueryMgrdOrderWebData(), receiptDeliveryTradeList);
        
        //        //API002.検索結果編集後件数
        //        int totalNumberA002 = Integer.parseInt(api002ResDto.getTotalNumber());
        //        int searchCntA002 = 0;
        //        String searchFromA002 = SPOT_MARGIN_SEARCH_FROM_DEFAULT;
        //        String searchToA002 = SPOT_MARGIN_SEARCH_TO_DEFAULT;
        
        //        //繰り返し処理開始
        //        while (Integer.parseInt(searchFromA002) <= totalNumberA002) {
        
        //            //1件ずつ約定部をセット
        //            setReceiptDeliveryTradeList(api002ResDto.getQueryMgrdOrderWebData(), receiptDeliveryTradeList);
        
        //            //2回目以降の検索で1回目取得できなかったデータを取得できるように検索番号を変更
        //            searchCntA002++;
        //            searchFromA002 = String.format(SEARCH_FORMAT, (searchCntA002 * SEARCH_TO_DEFAULT) + SEARCH_FROM_DEFAULT);
        //            searchToA002 = String.format(SEARCH_FORMAT, (searchCntA002 + SEARCH_FROM_DEFAULT) * SEARCH_TO_DEFAULT);
        
        //            //次のデータを取得する。
        //            api002InDto = new QueryMgRDOrderWebInData();
        //            inputA002.setIndata(setQueryMgRDOrderWebInData(cc, api002InDto, searchFromA002, searchToA002));
        //            api002ResDto = apiwrapper.queryMgRDOrderWeb(inputA002);
        
        //検索結果編集後件数を更新
        //            totalNumberA002 = Integer.parseInt(api002ResDto.getTotalNumber());
        //        }
    }
    
    /**
     * API001の検索条件をセット
     * @param cc 
     * @param api001InDto
     * @param searchFrom
     * @param searchTo
     * @return api001InDto
     */
    private QueryExecutionSummary1InData setQueryExecutionSummary1InData(CustomerCommon cc,
            QueryExecutionSummary1InData api001InDto, String searchFrom, String searchTo) {
        
        api001InDto.setButenCd(cc.getButenCode());
        api001InDto.setKozaNo(StringUtil.fillLeft(cc.getAccountNumber(), '0', 7));
        api001InDto.setSecId(A001_SEC_ID);
        api001InDto.setTradeType(A001_TRADE_TYPE);
        api001InDto.setRequestDate(API_REQUEST_DATE);
        api001InDto.setRefFrom(searchFrom);
        api001InDto.setRefTo(searchTo);
        
        if (StringUtils.equals(JR_ISA_CONTRACT_TYPE_CONTRACT, cc.getJrIsaContractType())) {
            // 顧客共通情報.ジュニアISA契約区分 = "1"
            api001InDto.setAccountGetKbn(ACCOUNT_GET_KBN_JRNISA);
        } else {
            // 顧客共通情報.ジュニアISA契約区分 ≠ "1"
            api001InDto.setAccountGetKbn(ACCOUNT_GET_KBN_FIRST);
        }
        
        return api001InDto;
    }
    
    /**
     * API002の検索条件をセット
     * @param cc 
     * @param api002InDto
     * @param searchFrom
     * @param searchTo
     * @return api002InDto
     */
    private QueryMgRDOrderWebInData setQueryMgRDOrderWebInData(CustomerCommon cc, QueryMgRDOrderWebInData api002InDto,
            String searchFrom, String searchTo) {
        
        api002InDto.setButenCd(cc.getButenCode());
        api002InDto.setKozaNo(StringUtil.fillLeft(cc.getAccountNumber(), '0', 7));
        api002InDto.setSecId(A002_SEC_ID);
        api002InDto.setRequestType(A002_REQUEST_TYPE);
        api002InDto.setRefFrom(searchFrom);
        api002InDto.setRefTo(searchTo);
        
        return api002InDto;
    }
    
    /**
     * FCT001チェック
     * @param butenCode 部店コード
     * @param accountNumber 口座番号
     * @return API結果
     */
    private boolean callFCT001(String butenCode, String accountNumber) {
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(accountNumber);
        input.setButenCode(butenCode);
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(), Fct001.TARGET_CUSTOMER_REF_AUTH_FLAG_0)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 現物信用約定リストから1件ずつ約定部をセット
     * @param execSumData
     * @param spotMarginTradeList
     */
    private void setSpotMarginTradeList(List<QueryExecutionSummary1ExecSumData> execSumData,
            List<IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList> spotMarginTradeList) {
        
        for (QueryExecutionSummary1ExecSumData sumData : execSumData) {
            
            //取引区分
            //上場CW(API001.約定部.取引区分が "7":上場CW買い または "8":上場CW売り)のレコードを除外
            if (StringUtils.equals(sumData.getTradeId(), LISTED_CW_BUY)
                    || StringUtils.equals(sumData.getTradeId(), LISTED_CW_SELL)) {
                
                continue;
            }
            
            IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList spotMarginTrade = new IfaDomesticTradeStatusListA001ResponseDto_SpotMarginTradeList();
            
            //信用取引区分をセット
            spotMarginTrade.setMarginTradeClassification(orderStatusUtil.getMarginTradeType(sumData.getPaymentLimit(),
                    sumData.getIppanMgPaymentKbn(), sumData.getIppanMgPaymentLimit()));
            //銘柄コードをセット
            spotMarginTrade.setBrandCode(sumData.getSecCode());
            //銘柄名をセット
            spotMarginTrade.setBrandName(sumData.getSecName());
            //取引区分をセット
            spotMarginTrade.setTradeClassification(sumData.getTradeId());
            //預かり区分をセット
            spotMarginTrade.setDepositType(sumData.getHitokuteiTradeKbn());
            //数量をセット
            spotMarginTrade.setQuantity(sumData.getQuantityTotal());
            //平均単価をセット
            spotMarginTrade.setAveragePrice(sumData.getAveragePrice());
            //課税額/譲渡益税をセット
            spotMarginTrade.setTaxAmountCapitalGain(sumData.getTaxTotal());
            //手数料/諸経費をセット
            spotMarginTrade.setCommCost(sumData.getComTotal());
            //精算金額をセット
            spotMarginTrade.setSettlementAmount(sumData.getNetAmountTotal());
            //代用適格区分をセット
            spotMarginTrade.setCollateralEligibleType(sumData.getCollateralId());
            //日計りをセット
            spotMarginTrade.setDayTrade(sumData.getDaytradeTotal());
            //約定日をセット
            spotMarginTrade.setTradeDate(sumData.getTradeDate());
            //受渡日をセット
            spotMarginTrade.setSettlementDate(sumData.getSettlementDate());
            //弁済期限をセット
            spotMarginTrade.setPaymentDeadline(sumData.getPaymentLimit());
            
            //リストにセット
            spotMarginTradeList.add(spotMarginTrade);
        }
    }
    
    /**
     * 現引現渡約定リストから1件ずつ約定部をセット
     * @param queryMgrdOrderData
     * @param receiptDeliveryTradeList
     */
    private void setReceiptDeliveryTradeList(List<QueryMgRDOrderWebDetail> queryMgrdOrderData,
            List<IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList> receiptDeliveryTradeList) {
        
        for (QueryMgRDOrderWebDetail orderData : queryMgrdOrderData) {
            
            IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList receiptDeliveryTrade = new IfaDomesticTradeStatusListA001ResponseDto_ReceiptDeliveryTradeList();
            
            //信用取引区分をセット
            receiptDeliveryTrade.setMarginTradeClassification(orderStatusUtil.getMarginTradeType(
                    orderData.getPaymentLimit(),
                    orderData.getIppanMgPaymentKbn(), orderData.getIppanMgPaymentLimit()));
            //EC受注番号をセット
            receiptDeliveryTrade.setEcOrderNo(orderData.getOrdeNo());
            //銘柄コードをセット
            receiptDeliveryTrade.setBrandCode(orderData.getSecCode());
            //銘柄名をセット
            receiptDeliveryTrade.setBrandName(orderData.getSecName());
            //取引種別をセット
            receiptDeliveryTrade.setTradeCd(orderData.getSettlementId());
            //預かり区分をセット
            receiptDeliveryTrade.setDepositType(orderData.getHitokuteiTradeKbn());
            //数量をセット
            receiptDeliveryTrade.setQuantity(orderData.getQuantity());
            //諸経費をセット
            receiptDeliveryTrade.setCost(orderData.getCost());
            //精算金額をセット
            receiptDeliveryTrade.setSettlementAmount(orderData.getNetAmount());
            //強制区分をセット
            receiptDeliveryTrade.setCoercionType(orderData.getForceKbn());
            //新規建日をセット
            receiptDeliveryTrade.setConstructionDate(orderData.getOpenDate());
            //新規単価をセット
            receiptDeliveryTrade.setNewPrice(orderData.getOpenPrice());
            //約定日をセット
            receiptDeliveryTrade.setTradeDate(orderData.getTradeDate());
            //受注日時をセット
            receiptDeliveryTrade.setOrderDayTime(orderData.getInputDate());
            
            //リストにセット
            receiptDeliveryTradeList.add(receiptDeliveryTrade);
        }
    }
    
    /** 
     * 約定一覧から信用取引区分を算出
     * @param paymentLimit
     * @param ippanMgPaymentKbn
     * @param ippanMgPaymentLimit
     * @return 信用取引区分
     */
    private String getMarginTradeClassification(String paymentLimit, String ippanMgPaymentKbn,
            String ippanMgPaymentLimit) {
        
        MarginTradeKbn marginTradeKbn = MarginTradeKbn.valueOfKbn(paymentLimit, ippanMgPaymentKbn);
        String marginTradeClassification = null;
        
        if (StringUtils.equals(marginTradeKbn.getPaymentKbn(), SELL_SETTLEMENT_DEADLINE_YEAR)
                || StringUtils.equals(marginTradeKbn.getPaymentKbn(), SELL_SETTLEMENT_DEADLINE_MONTH)
                || StringUtils.equals(marginTradeKbn.getPaymentKbn(), SELL_SETTLEMENT_DEADLINE_DAY)) {
            
            marginTradeClassification = String.format(marginTradeKbn.getMarginTradeKbn(),
                    Integer.parseInt(ippanMgPaymentLimit));
        } else {
            
            marginTradeClassification = marginTradeKbn.getMarginTradeKbn();
        }
        
        return marginTradeClassification;
    }
}
