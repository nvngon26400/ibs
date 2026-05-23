package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct017;
import com.sbisec.helios.ap.bizcommon.component.Fct022;
import com.sbisec.helios.ap.bizcommon.component.Fct025;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct017Dto.InquiryMutualFundBrand;
import com.sbisec.helios.ap.bizcommon.model.InputFct022Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct022Dto.BrandForInput;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct025Dto.Brand;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto.MediatePropriety;
import com.sbisec.helios.ap.bizcommon.model.OutputFct017Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct022Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct025Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticMutualFundBuyAbleListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundBuyAbleListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticMutualFundBuyAbleListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkReq;
import com.sbisec.helios.ap.safe.protocol.fundTrade.GetFundFundDocReadHistoryBulkRes;
import com.sbisec.helios.ap.safe.service.SafeCommonService;
import com.sbisec.helios.ap.safe.service.SafeFundTradeService;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulk;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkApiIn;
import com.sbisec.helios.ap.safe.service.fund.trade.dto.FundDocReadHistoryBulkItem;
import com.sbisec.helios.ap.safe.utils.SafeApiUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryFund;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryFundOutData;


/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入可能一覧
 * アクションID：A001
 * アクション名：初期化
 * アクションID：A002
 * アクション名：購入（直接入力）
　*　@author SCSK浦田 
 * 2023/10/27 新規作成
 */
@Component(value = "cmpIfaDomesticMutualFundBuyAbleListService")
public class IfaDomesticMutualFundBuyAbleListServiceImpl implements IfaDomesticMutualFundBuyAbleListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticMutualFundBuyAbleListServiceImpl.class);
    
    /** 正常終了のリターンコード  */
    private static final String RETURN_CODE_SUCCESS = "0";
    
    /** 正常終了のメッセージ  */
    private static final String RETURN_MESSEAGE_SUCCESS = "";
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** API001実行エラー  */
    private static final String ERRORS_API001_NRI_QUERY_FUND = "errors.nriQueryFund";
    
    /**  通貨選択型 強制注文対象エラー */
    private static final String ERRORS_CURRENCY_SELECTION_TYPE = "errors.fnd.selectedBrand.currencySelectionType";
    
    /**  複雑投信 強制注文対象エラー */
    private static final String ERRORS_CURRENCY_COMPLEX_TYPE = "errors.fnd.selectedBrand.complexType";
    
    /** マスタ取得エラー */
    private static final String ERR_NO_INFO = "errors.fnd.selectedBrand.noInformation";
    
    /** 投信目論見書交付チェックエラー */
    private static final String NO_ISSUANCE_RECORD = "errors.fnd.selectedBrand.noIssuanceRecord";
    
    /** 投信銘柄名が取得エラー */
    private static final String ERRORS_CMN_GETFUNDNAME_FAILED = "errors.cmn.getFundName.failed";
    
    /** API001実行結果 正常終了コード  */
    private static final String API001_SUCCESS_CD = "     ";
    
    /** 対象顧客参照なし */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 証券金銭種別- 国内投信 */
    private static final String DOMESTIOC_MUTUAL_FUND = "06";
    
    /** 顧客共通情報.ジュニアISA契約区分：契約*/
    private static final String JR_NISA_CONTRACT_KBN = "1";
    
    /** 通常口座およびJrNISA口座の第一口座 */
    private static final String REGULAR_AND_JR_NISA_FIRST_ACCOUNT = " ";
    
    /** JrNISA口座(第一、第二口座両方) */
    private static final String JR_NISA_FIRST_AND_SECOND_ACCOUNT = "2";
    
    /** NRI_QueryFund 委託会社コード (スペース埋めで設定)*/
    private static final String QUERY_FUND_ASSET_MANEGEMENT_ID = "     ";
    
    /** 仲介業者扱可否:不可 */
    private static final String IS_BROKER_SERVICE_IMPOSSIBLE = "0";
    
    /** 数値のフォーマット指定 */
    private static final String STRING_NUMBER_FORMAT_00000 = "%05d";
    
    /** レスポンスデータ設定用 半角スペース */
    private static final String RESPONSE_SET_EMPTY = "";
    
    /** レスポンスデータ設定用 半角スペース */
    private static final String RESPONSE_SET_HALF_WIDTH_SPACE = " ";
    
    /** レスポンスデータ設定用 半角ハイフン */
    private static final String RESPONSE_SET_HALF_WIDTH_HYPHEN = "-";

   /** レスポンスデータ設定用 半角ピリオド */
    private static final String RESPONSE_SET_HALF_WIDTH_PERIOD = ".";
    
    /** NRI_QueryFund 注文停止フラグ：注文受付停止 */
    private static final String ORDER_STOP_FLAG_STOP = "1";
    
    /** 買付手数料(税込)） なし */
    private static final String SET_BUY_COMM_NON = "なし";
    
    /** 買付手数料(税込)） 口数による */
    private static final String SET_BUY_COMM_UNIT = "口数による";
    
    /** 買付手数料(税込)） 金額による */
    private static final String SET_BUY_COMM_AMOUNT = "金額による";
    
    /** 優遇適用率 適用外 */
    private static final String PREFERENTIAL_APPLICATION_RATE_NOT_APPLICAVLE = "適用外";
    
    /** 優遇適用率 999.99% */
    private static final String PREFERENTIAL_APPLICATION_RATE_MAX_PERCENT = "999.99%";
    
    /** 受入状況:未受入 (区分値ではなく、設計書の編集内容欄 設定値) */
    private static final String NOT_ACCEPTED = "0";
    
    /** 受入状況:受入済 (区分値ではなく、設計書の編集内容欄 設定値) */
    private static final String ACCEPTED = "1";
    
    /** 受入状況:受入不要 (区分値ではなく、設計書の編集内容欄 設定値) */
    private static final String ACCEPTANCE_EXCLUDE = "2";
    
    /** 受入状況:銘柄情報がなく表示不可 (区分値ではなく、設計書の編集内容欄 設定値) */
    private static final String DISPLAY_UNABLE = "3";
    
    /** 顧客共通情報 取引停止フラグ:取引停止口座 */
    private static final String SUSPENDED_ACCOUNT = "1";
    
    /** 媒介可否:媒介不可 (FCT003) */
    private static final String MEDIATE_PROPRIETY_UNABLE = "0";
    
    /** 媒介可否:媒介可 (FCT003) */
    private static final String MEDIATE_PROPRIETY_ABLE = "1";
    
    /** 消費税 */
    private static final BigDecimal CONSUMPTION_TAX = new BigDecimal("1.1");
    
    /** 優遇率計算 パーセント */
    private static final BigDecimal CALCULATION_PERCENT = new BigDecimal("100");
    
    /** 取引種別 国内投信 */
    private enum DomestincMutualFundTradeClass {
        
        // 購入(一般)
        BUY_GENERAL("0"),
        // 購入(累投)
        CUMULATIVE_PITCH("1"),
        // 購入（積立）
    	BUY_ACCUMULATE("2");
        
        private final String value;
        
        DomestincMutualFundTradeClass(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** E*TRADE扱い可否 */
    private enum EtradeService {
        
        // 売買不可
        NOT_AVAILABLE_FOR_SALE("0"),
        // 売のみ可
        SALE_ONLY("2");
        
        private final String value;
        
        EtradeService(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** FCT017：確認書受入要否 */
    private enum AcceptanceNecessiy {
        
        // 不要
        UNNECESSARY("0"),
        // 必要
        NECESSITY("1");
        
        private final String value;
        
        AcceptanceNecessiy(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** FCT017：投信銘柄種別 */
    private enum MutualFundBrand {
        
        // 通貨選択型
        CURRENCY_SELECT("1"),
        // 複雑投信
        COMPLEX_MUTUAL_FUND("2");
        
        private final String value;
        
        MutualFundBrand(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 目論見書状態 */
    private enum ProspectusStatusTrade {
        
        // 取引停止
        SUSPENSION("1"),
        // 発送済
        SHIPPED("2"),
        // 発送中
        SHIPPING("3");
        
        private final String value;
        
        ProspectusStatusTrade(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 目論見書閲覧区分 */
    private enum ProspecFundsProspectus {
    	
        // 閲覧済・郵送完了
    	PROSPECTUS("0");

        private final String value;
        
        ProspecFundsProspectus(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 買付可能ステータス */
    private enum TradeStatus {
        
        // 買付可能（電子閲覧済）
        AVAILABLE_FOR_PURCHASE("1"),
        // 発送処理中（郵送請求中）
        SHIPPING_IN_PROGLESS("2"),
        // 買付可能（郵送請求または即時交付済）
        AVAILABLE_FOR_PURCHASE_SHIPPED("3");
        
        private final String value;
        
        TradeStatus(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** ファンドタイプ */
    private enum FundType {
        
        // 一般
        GENERAL("1"),
        // 累投
        CUMULATIVE_PITCH("2"),
        // 積立
        ACCUMULATE("3"),
        // 累投／積立
        CUMULATIVE_PITCH_AND_ACCUMULATE("4");
        
        private final String value;
        
        FundType(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 確認書受入状況（通貨型、複雑） */
    private enum ConfrimDocAcceptanceStatus {
        
        // 受入不要
        ACCEPTANCE_EXCLUDE("1"),
        // WEB閲覧
        WEB_VIEW("2"),
        // 受入済
        ACCEPTED("3"),
        // 確認書受入確認
        COMFRIMATION_OF_ACCEPTANCE("4"),
        // -(ハイフン)
        HYPEHN("5");
        
        private final String value;
        
        ConfrimDocAcceptanceStatus(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 購入可否 */
    private enum BuyPropriety {
        
        // 可能
        ABLE("1"),
        // 不可
        UNABLE("2"),
        // 強制注文対象
        COERCITON_ORDER_TARGET("3"),
        // 不可（媒介不可、取扱不可）
        UNABLE_HANDLING_AND_MEDIATE_PROPRIETY("4");
        
        private final String value;
        
        BuyPropriety(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    /** 積立可否 */
    private enum AccumulatePropriety {
        
        // 可能
        ABLE("1"),
        // 不可
        UNABLE("2"),
        // 強制注文対象
        COERCITON_ORDER_TARGET("3"),
    	
        // 買付不可
    	TRADE_UNABLE("4");
        
        private final String value;
        
        AccumulatePropriety(final String value) {
            
            this.value = value;
        }
        
        private String getValue() {
            
            return value;
        }
    }
    
    @Autowired
    Fct001 fct001;
    
    @Autowired
    Fct003 fct003;
    
    @Autowired
    Fct017 fct017;
    
    @Autowired
    Fct022 fct022;
    
    @Autowired
    Fct025 fct025;
    
    @Autowired
    ApiWrapper apiwrapper;
    
	@Autowired
    private SafeFundTradeService safeFundTradeService;
	   
    @Autowired
    private IfaDomesticMutualFundBuyAbleListDao dao;
    
    @Autowired
    private SafeCommonService safeCommonService;
    
    /**
    
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto> initializeA001(
            IfaDomesticMutualFundBuyAbleListA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto>();
        List<IfaDomesticMutualFundBuyAbleListA001ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundBuyAbleListA001ResponseDto>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundBuyAbleListServiceImplL.initializeA001");
        }
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String kouzaNumber = customerCommon.getAccountNumber();
        
        // ➀利用者の口座に対する権限チェックを行う。
        // 権限あり：次の処理へ。
        // 上記以外：権限なしエラーを返す。
        InputFct001Dto inFct001Dto = new InputFct001Dto();
        inFct001Dto.setButenCode(butenCode);
        inFct001Dto.setAccountNumber(kouzaNumber);
        
        OutputFct001Dto outFct001Dto = fct001.doCheck(inFct001Dto);
        if (StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS, new String[] { butenCode, kouzaNumber }));
            return dtoRes;
        }
        
        // ②取引コース媒介可否チェックの結果を取得する。
        // FCT003の呼び出し
        InputFct003Dto inFct003Dto = new InputFct003Dto();
        inFct003Dto.setButenCode(butenCode);
        inFct003Dto.setAccountNumber(kouzaNumber);
        inFct003Dto.setProductCd(DOMESTIOC_MUTUAL_FUND);
        
        final OutputFct003Dto outFct003Dto = fct003.doCheck(inFct003Dto);
        
        // ③買付可能一覧を取得する。
        // header情報はApiWrapperでやる想定
        // 検索する範囲は繰り返し構文で適宜設定
        QueryFundIn fundIn = new QueryFundIn();
        QueryFundInData fundInData = new QueryFundInData();
        fundInData.setButenCd(butenCode);
        fundInData.setKozaNo(kouzaNumber);
        fundInData.setAssetManagementId(QUERY_FUND_ASSET_MANEGEMENT_ID);
        if (!StringUtils.equals(customerCommon.getJrIsaContractType(), JR_NISA_CONTRACT_KBN)) {
            fundInData.setAccountGetKbn(REGULAR_AND_JR_NISA_FIRST_ACCOUNT);
        } else {
            fundInData.setAccountGetKbn(JR_NISA_FIRST_AND_SECOND_ACCOUNT);
        }
        boolean isCallStop = false;
        int searchCount = 1;
        String searchNumFrom;
        String searchNumTo;
        
        QueryFundOutData fundOutData = new QueryFundOutData();
        List<QueryFund> fundList = new ArrayList<QueryFund>();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 正常：次の処理へ。
        // エラー：APIエラーを返す。
        // API呼出初期設定
        do {
            // 検索番号の設定
            // 設定値は基本設計書「API詳細シート」を参照            
            searchNumFrom = String.format(STRING_NUMBER_FORMAT_00000, (searchCount - 1) * 100 + 1);
            searchNumTo = String.format(STRING_NUMBER_FORMAT_00000, searchCount * 100);
            fundInData.setRefFrom(searchNumFrom);
            fundInData.setRefTo(searchNumTo);
            
            fundIn.setIndata(fundInData);
            fundOutData = apiwrapper.queryFund(fundIn);
            // APIエラー
            if (apiErrorUtil.isError(fundOutData.getShubetu(), fundOutData.getCode(), fundOutData.getMessage())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SYSTEM_ERROR, ERRORS_API001_NRI_QUERY_FUND,
                        IfaCommonUtil.getMessage(ERRORS_API001_NRI_QUERY_FUND,
                                new String[] { fundOutData.getCode(), fundOutData.getMessage() }));
                return dtoRes;
            }
            
            // 実行時の検索結果をリストに格納する
            for (QueryFund fundResult : fundOutData.getQueryFund()) {
                fundList.add(fundResult);
            }
            // 検索結果を全て取得したかチェック
            if (Integer.parseInt(searchNumTo) > Integer.parseInt(fundOutData.getHitNumber())) {
                isCallStop = true;
            }
            searchCount++;
        } while (!isCallStop);
        
        // ④各銘柄の基準価額、前日比、販売手数料、優遇適用率および注文締切時間を取得する。
        // FCT022 リクエストデータ設定
        InputFct022Dto inFct022Dto = new InputFct022Dto();
        List<BrandForInput> brandForInputList = new ArrayList<BrandForInput>();
        for (QueryFund data : fundList) {
            // 回数(4桁)＋号(3桁)
            BrandForInput brandForInput = new BrandForInput();
            brandForInput.setNriCd(data.getFundCodeSerNo() + data.getFundCodeSub());
            brandForInputList.add(brandForInput);
        }
        inFct022Dto.setBrandList(brandForInputList);
        inFct022Dto.setDealerNumber(customerCommon.getDealerNumber());
        
        final OutputFct022Dto outFct022Dto = fct022.getData(inFct022Dto);
        
        // ⑤通貨選択確認書および複雑投信確認書の受入状況を取得する。
        // FCT017 リクエストデータ設定
        InputFct017Dto inFct017Dto = new InputFct017Dto();
        List<InquiryMutualFundBrand> inquiryMutualfundBrandList = new ArrayList<InquiryMutualFundBrand>();
        for (QueryFund data : fundList) {
            // 回数(4桁)＋号(3桁)
            InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
            inquiryMutualFundBrand.setNriCd(data.getFundCodeSerNo() + data.getFundCodeSub());
            inquiryMutualfundBrandList.add(inquiryMutualFundBrand);
        }
        inFct017Dto.setButenCode(butenCode);
        inFct017Dto.setAccountNumber(kouzaNumber);
        inFct017Dto.setInquiryMutualFundBrandList(inquiryMutualfundBrandList);
        
        final OutputFct017Dto outFct017Dto = fct017.getData(inFct017Dto);
        
        /* Indigo銘柄設定を取得し、買付可能一覧から取引不可銘柄を削除する。
          (1)FCT025の応答から、以下のいずれかに合致する銘柄を買付可能一覧からの削除対象とする。
            ■FCT025で削除対象とする銘柄の条件
                銘柄リスト.E*TRADE扱い可否=0:売買不可    または    2:売のみ可
                銘柄リスト.仲介業者扱可否=0:不可
        */
        // FCT025 リクエストデータの設定
        InputFct025Dto inFct025Dto = new InputFct025Dto();
        List<Brand> brandList = new ArrayList<Brand>();
        for (QueryFund data : fundList) {
            // 回数(4桁)＋号(3桁)
            Brand brand = new Brand();
            brand.setNriCd(data.getFundCodeSerNo() + data.getFundCodeSub());
            brandList.add(brand);
        }
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        inFct025Dto.setBrokerCode(userAccount.getBrokerCode());
        inFct025Dto.setBrandList(brandList);
        
        // ユーザ共通情報.仲介業者コード;
        // リクエスト.ファンドコード（回数）+リクエスト.検索結果.ファンドコード（号）
        OutputFct025Dto outFct025Dto = fct025.doCheck(inFct025Dto);

        // リスト.購入積立不可対象
        List<String> restrictedFundCdList = new ArrayList<String>();
        // ■FCT025で購入積立不可対象とする銘柄の条件
        if (!outFct025Dto.getBrandList().isEmpty()) {
            for (OutputFct025Dto.Brand resBrand : outFct025Dto.getBrandList()) {
                // 銘柄リスト.E*TRADE扱い可否=0:売買不可    または    2:売のみ可
                if (StringUtils.equals(resBrand.getIsEtradeService(), EtradeService.NOT_AVAILABLE_FOR_SALE.getValue())
                        || StringUtils.equals(resBrand.getIsEtradeService(), EtradeService.SALE_ONLY.getValue())) {
                    restrictedFundCdList.add(resBrand.getNriCd());
                }
                // 銘柄リスト.仲介業者扱可否=0:不可
                if (StringUtils.equals(resBrand.getIsBrokerService(), IS_BROKER_SERVICE_IMPOSSIBLE)) {
                    restrictedFundCdList.add(resBrand.getNriCd());
                }
            }
        }
        
        IfaDomesticMutualFundBuyAbleListA001ResponseDto dto = new IfaDomesticMutualFundBuyAbleListA001ResponseDto();
        
        // 可否リストを取得: ableList[0]:媒介可否,ableList[1]:購入可否,ableList[2]:積立可否
        List<String> ableList = getMediateProprietyFromOutputFct003Dto(outFct003Dto);
        // 媒介可否
        dto.setIntermediaryValue(ableList.get(0));
        // 購入可否
        dto.setBuyAbleValue(ableList.get(1));
        // 積立可否
        dto.setAccumulateAbleValue(ableList.get(2));
        // APIエラーコード
        dto.setApiErrorCode(fundOutData.getCode());
        // APIエラーメッセージ
        dto.setApiErrorMsg(fundOutData.getMessage());
        // 乗換優遇限度額.総合口座（今月残）
        dto.setTransfersPreferentialLimitAmountThisMonth(fundOutData.getTreatmentPrice1());
        // 乗換優遇限度額.総合口座（来月残）
        dto.setTransfersPreferentialLimitAmountNextMonth(fundOutData.getTreatmentPrice2());
        // 乗換優遇限度額.ジュニアNISA口座（今月残）
        dto.setSwitchingFavorableTreatmentLimitJuniorNisaAccountThisMonth(fundOutData.getJrnisaTreatmentPrice1());
        // 乗換優遇限度額.ジュニアNISA口座（来月残）
        dto.setSwitchingFavorableTreatmentLimitJuniorNisaAccountNextMonth(fundOutData.getJrnisaTreatmentPrice2());
        // 検索結果総数
        dto.setHitNumber(fundOutData.getHitNumber());
        // 明細部の設定
        dto.setDetailList(createA001ResponseDataDetail(fundList, outFct022Dto, outFct017Dto, outFct003Dto,
                outFct001Dto.getTradeSuspendFlag(), restrictedFundCdList));

        resDtoList.add(dto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
    
    /**
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA002RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA002ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto> purchaseDirectInputA002(
            IfaDomesticMutualFundBuyAbleListA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto>();
        List<IfaDomesticMutualFundBuyAbleListA002ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundBuyAbleListA002ResponseDto>();
        
        /*
        ②通貨選択型投信    または    複雑型投信チェックを行う。
                    銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
                    銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：強制注文対象エラーを返す。
                    銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：強制注文対象エラーを返す。
        
        */
        // ⑤通貨選択確認書および複雑投信確認書の受入状況を取得する。
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // FCT017 リクエストデータ設定
        InputFct017Dto inFct017Dto = new InputFct017Dto();
        InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
        List<InquiryMutualFundBrand> inquiryMutualfundBrandList = new ArrayList<InquiryMutualFundBrand>();
        // 回数(4桁)＋号(3桁)
        inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        inquiryMutualfundBrandList.add(inquiryMutualFundBrand);
        
        inFct017Dto.setButenCode(customerCommon.getButenCode());
        inFct017Dto.setAccountNumber(customerCommon.getAccountNumber());
        inFct017Dto.setInquiryMutualFundBrandList(inquiryMutualfundBrandList);
        
        OutputFct017Dto outFct017Dto = fct017.getData(inFct017Dto);
        
        for (OutputFct017Dto.Brand dto : outFct017Dto.getBrandList()) {
            if (StringUtils.equals(dto.getAcceptanceNecessity(), AcceptanceNecessiy.UNNECESSARY.getValue())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                        RETURN_MESSEAGE_SUCCESS);
            } else if (StringUtils.equals(dto.getMutualFundBrandClass(), MutualFundBrand.CURRENCY_SELECT.getValue())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CURRENCY_SELECTION_TYPE,
                        IfaCommonUtil.getMessage(ERRORS_CURRENCY_SELECTION_TYPE, new String[] {}));
                return dtoRes;
            } else if (StringUtils.equals(dto.getMutualFundBrandClass(),
                    MutualFundBrand.COMPLEX_MUTUAL_FUND.getValue())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CURRENCY_COMPLEX_TYPE,
                        IfaCommonUtil.getMessage(ERRORS_CURRENCY_COMPLEX_TYPE, new String[] {}));
                return dtoRes;
            }
        }
        
        
        // SQL002:協会コード と ファンドタイプ の取得
        var sql002resList = executeSql002(Optional.ofNullable(dtoReq.getFundCodeTimes()).orElse(""), Optional.ofNullable(dtoReq.getFundCodeIssues()).orElse(""));
        
        if (sql002resList.size() != 1) {
        	 // 取得エラーを返却する
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERR_NO_INFO,
                    IfaCommonUtil.getMessage(ERR_NO_INFO, new String[] {}));
        }
        
        IfaDomesticMutualFundBuyAbleListA002ResponseDto dto = new IfaDomesticMutualFundBuyAbleListA002ResponseDto();
        
        // 投信閲覧履歴一括照会の取得
        Boolean continueFlg = false;
   	 	GetFundFundDocReadHistoryBulkReq getFundDocReadHistoryBulkReq = new GetFundFundDocReadHistoryBulkReq();
        getFundDocReadHistoryBulkReq.getHeader().setToken(SafeApiUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()));  // トークン
        FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
        fundDocReadHistoryBulkApiIn.setDataOutputKbn("1");  // データ出力区分,1:データ出力区分
        
        List<FundDocReadHistoryBulkItem> fundDocReadHistoryBulkList = new ArrayList<>();
        FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
        fundDocReadHistoryBulkItem.setFundCode(sql002resList.get(0).getMFCode());  // 協会コード
        
        fundDocReadHistoryBulkItem.setFundType(" ");  // ファンドタイプ,△:未入力、口数/金額の一括取得
        fundDocReadHistoryBulkList.add(fundDocReadHistoryBulkItem);
        fundDocReadHistoryBulkApiIn.setFunds(fundDocReadHistoryBulkList);

        getFundDocReadHistoryBulkReq.setParameter(fundDocReadHistoryBulkApiIn);
        
        GetFundFundDocReadHistoryBulkRes getFundDocReadHistoryBulkres = new GetFundFundDocReadHistoryBulkRes();
        
        try {
            
             getFundDocReadHistoryBulkres = safeFundTradeService.getBulkFundDocReadHistory(getFundDocReadHistoryBulkReq); 
        	
        } catch (Exception e) {
        	// 取得エラーを返却する
        	return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }

        if(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds() != null 
       		 && !CollectionUtils.isEmpty(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) ) {
            for (FundDocReadHistoryBulk fund :  getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) {
             	// ファンドタイプが汎用累投かつ 目論見書閲覧区分が0(閲覧済・郵送完了)が存在の場合、次の処理へ
             	if(StringUtils.equals(fund.getFundType(),sql002resList.get(0).getMFType()) 
             			&& StringUtils.equals(fund.getProspectus(),ProspecFundsProspectus.PROSPECTUS.getValue())) {
             		continueFlg = true;
             		break;
                 }	
             } 
        }
        
        // 目論見書チェック区分の設定
        if(!continueFlg) {
            // 目論見書チェック区分
       	 // 画面.目論見書チェック区分に"△"（半角スペース1バイト）を設定し、次の処理へ
            dto.setProspectusFlag(" ");
        }else{
            // 目論見書チェック区分
            dto.setProspectusFlag("1");
        }
        // 協会コード
        dto.setKyoukaiCd(sql002resList.get(0).getMFCode());
        // ファンドタイプ
        dto.setFundType(sql002resList.get(0).getMFType());
        resDtoList.add(dto);

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                RETURN_MESSEAGE_SUCCESS);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticMutualFundBuyAbleListServiceImplL.buyDirectInputA002");
        }
        
        return dtoRes;
    }
    
    /**
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA004RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA004ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto> accumulateDirectInputA004(
    		IfaDomesticMutualFundBuyAbleListA004RequestDto dtoReq) throws Exception {   
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("cmpIfaDomesticMutualFundBuyAbleListService.accumulateDirectInputA004");
        } 
        
        DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto>();
        List<IfaDomesticMutualFundBuyAbleListA004ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundBuyAbleListA004ResponseDto>();   
        
        /*
        ②通貨選択型投信    または    複雑型投信チェックを行う。
                    銘柄リスト.NRIコード.書類コード.受入要否=不要：次の処理へ。
                    銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型：強制注文対象エラーを返す。
                    銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信：強制注文対象エラーを返す。
        
        */
        // ⑤通貨選択確認書および複雑投信確認書の受入状況を取得する。
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // FCT017 リクエストデータ設定
        InputFct017Dto inFct017Dto = new InputFct017Dto();
        InquiryMutualFundBrand inquiryMutualFundBrand = new InquiryMutualFundBrand();
        List<InquiryMutualFundBrand> inquiryMutualfundBrandList = new ArrayList<InquiryMutualFundBrand>();
        // 回数(4桁)＋号(3桁)
        inquiryMutualFundBrand.setNriCd(dtoReq.getFundCodeTimes() + dtoReq.getFundCodeIssues());
        inquiryMutualfundBrandList.add(inquiryMutualFundBrand);
        
        inFct017Dto.setButenCode(customerCommon.getButenCode());
        inFct017Dto.setAccountNumber(customerCommon.getAccountNumber());
        inFct017Dto.setInquiryMutualFundBrandList(inquiryMutualfundBrandList);
        
        OutputFct017Dto outFct017Dto = fct017.getData(inFct017Dto);
        
        // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
        if (!ObjectUtils.isEmpty(outFct017Dto.getBrandList())) {
            for (OutputFct017Dto.Brand brand : outFct017Dto.getBrandList()) {
                // if(銘柄リスト.NRIコード.書類コード.受入要否=不要){
                if (StringUtils.equals(brand.getAcceptanceNecessity(),AcceptanceNecessiy.UNNECESSARY.getValue())) {
                    continue;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=通貨選択型 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), MutualFundBrand.CURRENCY_SELECT.getValue())) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.currencySelectionType)を返す
                	 dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CURRENCY_SELECTION_TYPE,
                             IfaCommonUtil.getMessage(ERRORS_CURRENCY_SELECTION_TYPE, new String[] {}));
                     return dtoRes;
                }
                // if(銘柄リスト.NRIコード.書類コード.投信銘柄種別=複雑投信 の場合){
                if (StringUtils.equals(brand.getMutualFundBrandClass(), MutualFundBrand.COMPLEX_MUTUAL_FUND.getValue())) {
                    // 強制注文対象エラー(errors.fnd.selectedBrand.complexType)を返す
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CURRENCY_COMPLEX_TYPE,
                            IfaCommonUtil.getMessage(ERRORS_CURRENCY_COMPLEX_TYPE, new String[] {}));
                    return dtoRes;
                }
            }
        }
        
        // SQL001:協会コードの取得
        var sql001resList = executeSql001(Optional.ofNullable(dtoReq.getFundCodeTimes()).orElse(""), Optional.ofNullable(dtoReq.getFundCodeIssues()).orElse(""));
        
        if (sql001resList.size() != 1) {
        	 // 取得エラーを返却する
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERR_NO_INFO,
                    IfaCommonUtil.getMessage(ERR_NO_INFO, new String[] {}));
        }
        
        /*
        ③	投信閲覧履歴一括照会APIを呼出し、目論見書閲覧ステータスを取得する。
				ファンド一覧.目論見書閲覧区分=0(閲覧済・郵送完了)：次の処理へ。
				ファンド一覧.目論見書閲覧区分≠0：投信目論見書交付チェックエラーを返す。
        
        */
        // 投信閲覧履歴一括照会の取得
        Boolean continueFlg = false;
   	 	GetFundFundDocReadHistoryBulkReq getFundDocReadHistoryBulkReq = new GetFundFundDocReadHistoryBulkReq();
        getFundDocReadHistoryBulkReq.getHeader().setToken(SafeApiUtil.getToken(customerCommon.getButenCode(), customerCommon.getAccountNumber()));  // トークン
        FundDocReadHistoryBulkApiIn fundDocReadHistoryBulkApiIn = new FundDocReadHistoryBulkApiIn();
        fundDocReadHistoryBulkApiIn.setDataOutputKbn("1");  // データ出力区分,1:データ出力区分
        
        List<FundDocReadHistoryBulkItem> fundDocReadHistoryBulkList = new ArrayList<>();
        FundDocReadHistoryBulkItem fundDocReadHistoryBulkItem = new FundDocReadHistoryBulkItem();
        fundDocReadHistoryBulkItem.setFundCode(sql001resList.get(0));  // 協会コード
        
        fundDocReadHistoryBulkItem.setFundType(" ");  // ファンドタイプ,△:未入力、口数/金額の一括取得
        fundDocReadHistoryBulkList.add(fundDocReadHistoryBulkItem);
        fundDocReadHistoryBulkApiIn.setFunds(fundDocReadHistoryBulkList);
        
        getFundDocReadHistoryBulkReq.setParameter(fundDocReadHistoryBulkApiIn);
        
        GetFundFundDocReadHistoryBulkRes getFundDocReadHistoryBulkres = new GetFundFundDocReadHistoryBulkRes();
        try {

             getFundDocReadHistoryBulkres = safeFundTradeService.getBulkFundDocReadHistory(getFundDocReadHistoryBulkReq);

        } catch (Exception e) {
        	// 取得エラーを返却する
        	return safeCommonService.checkSafeBussinessException(resDtoList, e);
        }
       
        if(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds() != null 
       		 && !CollectionUtils.isEmpty(getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) ) {
            for (FundDocReadHistoryBulk fund :  getFundDocReadHistoryBulkres.getFundDocReadHistoryBulkApiOut().getFunds()) {
             	// ファンドタイプが汎用累投かつ 目論見書閲覧区分が0(閲覧済・郵送完了)が存在の場合、次の処理へ
             	if(StringUtils.equals(fund.getFundType(),FundType.CUMULATIVE_PITCH.getValue()) 
             			&& StringUtils.equals(fund.getProspectus(),ProspecFundsProspectus.PROSPECTUS.getValue())) {
             		continueFlg = true;
             		break;
                 }	
             } 
        }
       
        
        // 上記以外：投信目論見書交付チェックエラーを返す。
        if(!continueFlg) {
         // 取得エラーを返却する
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, NO_ISSUANCE_RECORD,
                    IfaCommonUtil.getMessage(NO_ISSUANCE_RECORD, new String[] {}));
        }
   	
        IfaDomesticMutualFundBuyAbleListA004ResponseDto dto = new IfaDomesticMutualFundBuyAbleListA004ResponseDto();
        // 協会コード
        dto.setKyoukaiCd(sql001resList.get(0));
        resDtoList.add(dto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
      
        return dtoRes;
    }
    
    
    /**
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA007RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA007ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto> directInputSelectMFNameA007(
    		IfaDomesticMutualFundBuyAbleListA007RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("cmpIfaDomesticMutualFundBuyAbleListService.accumulateDirectInputA007");
        }
        
        DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto> dtoRes = new DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto>();
        List<IfaDomesticMutualFundBuyAbleListA007ResponseDto> resDtoList = new ArrayList<IfaDomesticMutualFundBuyAbleListA007ResponseDto>();
        
        // SQL002:銘柄名の取得
        var sql002resList = executeSql002(Optional.ofNullable(dtoReq.getFundCodeTimes()).orElse(""), Optional.ofNullable(dtoReq.getFundCodeIssues()).orElse(""));
        
        if (sql002resList.size() != 1) {
        	 // 取得エラーを返却する
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_CMN_GETFUNDNAME_FAILED,
                    IfaCommonUtil.getMessage(ERRORS_CMN_GETFUNDNAME_FAILED, new String[] {}));
        }
        
        IfaDomesticMutualFundBuyAbleListA007ResponseDto dto = new IfaDomesticMutualFundBuyAbleListA007ResponseDto();
        // ファンド正式名
        dto.setKyoukaiName(sql002resList.get(0).getMFName());

        resDtoList.add(dto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, RETURN_CODE_SUCCESS,
                RETURN_MESSEAGE_SUCCESS);   
        
        return dtoRes;
    }
    
    
    /**
     * A001のレスポンスデータの明細リストを作成する
     *
     * @param queryFundList NRI_QueryFundから取得した買付可能一覧の明細
     * @param outFct022Dto FCT022 国内投信基準価格価格・手数料取得 実行結果
     * @param outFct017Dto FCT017 確認書受入および強制注文が必要な国内投信銘柄情報取得 実行結果
     * @param outFct003Dto FCT003 取引コース媒介可否チェック 実行結果
     * @param tradeSuspendFlag FCT001 利用者顧客参照権限チェック 取引停止フラグ
     * @param restrictedFundList 購入積立不可対象
     * @return 購入可能一覧の明細
    
     */
    private List<IfaDomesticMutualFundBuyAbleListDetail> createA001ResponseDataDetail(List<QueryFund> queryFundList,
            OutputFct022Dto outFct022Dto, OutputFct017Dto outFct017Dto, OutputFct003Dto outFct003Dto,
            String tradeSuspendFlag, List<String> restrictedFundCdList) {
        
        List<IfaDomesticMutualFundBuyAbleListDetail> detailList = new ArrayList<IfaDomesticMutualFundBuyAbleListDetail>();
        
        for (QueryFund targetFund : queryFundList) {
            StringBuilder brandCodeBrandName = new StringBuilder();
            // コード
            // 検索結果.ファンドコード（回数）≠値なし　かつ　検索結果.ファンドコード（号）≠値なし　の場合
            if (!targetFund.getFundCodeSerNo().isEmpty() && !targetFund.getFundCodeSub().isEmpty()) {
                brandCodeBrandName.append(targetFund.getFundCodeSerNo());
                brandCodeBrandName.append(RESPONSE_SET_HALF_WIDTH_PERIOD);
                brandCodeBrandName.append(targetFund.getFundCodeSub());
            } else {
                brandCodeBrandName.append(targetFund.getFundCodeSerNo());
                brandCodeBrandName.append(targetFund.getFundCodeSub());
            }
            brandCodeBrandName.append(RESPONSE_SET_HALF_WIDTH_SPACE);
            // 委託先会社名称
            if (StringUtils.isEmpty(targetFund.getItakuKasyaName())) {
                brandCodeBrandName.append(RESPONSE_SET_EMPTY);
            } else {
                brandCodeBrandName.append(targetFund.getItakuKasyaName());
            }
            brandCodeBrandName.append(RESPONSE_SET_HALF_WIDTH_SPACE);
            // 銘柄名
            if (targetFund.getSecName().isEmpty()) {
                brandCodeBrandName.append(RESPONSE_SET_EMPTY);
            } else {
                brandCodeBrandName.append(targetFund.getSecName());
            }
            
            IfaDomesticMutualFundBuyAbleListDetail detail = new IfaDomesticMutualFundBuyAbleListDetail();
            // 明細.コード銘柄名
            detail.setBrandCodeBrandName(brandCodeBrandName.toString());
            // 明細.ファンドタイプ
            detail.setFundType(targetFund.getFundType());
            // 明細.目論見書状態
            if (StringUtils.equals(targetFund.getStopOrderFlag(), ORDER_STOP_FLAG_STOP)) {
                detail.setProspectus(ProspectusStatusTrade.SUSPENSION.getValue());
            } else {
                if (StringUtils.equals(targetFund.getTradeStatus(), TradeStatus.AVAILABLE_FOR_PURCHASE.getValue())) {
                    detail.setProspectus(ProspectusStatusTrade.SHIPPED.getValue());
                } else if (StringUtils.equals(targetFund.getTradeStatus(),
                        TradeStatus.SHIPPING_IN_PROGLESS.getValue())) {
                    detail.setProspectus(ProspectusStatusTrade.SHIPPING.getValue());
                }
            }
            // 明細.目論見書発送日
            detail.setProspectusShippingDate(targetFund.getProspectusSendDate());
            // 明細.買付可能日
            detail.setBuyAbledate(targetFund.getBuyDate());
            // 明細.マーケット発注日　marketOrderDate
            detail.setMarketOrderDate(targetFund.getMrtOrderDate());
            // FCT022のリストから一致する明細のデータを取得する
            for (OutputFct022Dto.Brand resFct022 : outFct022Dto.getBrandList()) {
                if (StringUtils.equals(resFct022.getNriCd(),
                        targetFund.getFundCodeSerNo() + targetFund.getFundCodeSub() + " ")) {
                    // 明細.基準価額
                    detail.setPrice(resFct022.getBasePrice().toString());
                    // 明細.前日比
                    detail.setDiff(resFct022.getDiff().toString());
                    // 明細.買付手数料（税込）（数値(小数)）
                    if (resFct022.getSalesCommRate1().compareTo(BigDecimal.ZERO) == 0
                            && resFct022.getSalesCommRate2().compareTo(BigDecimal.ZERO) == 0) {
                        detail.setBuyComm(SET_BUY_COMM_NON);
                    } else if (resFct022.getSalesCommRate1().compareTo(BigDecimal.ZERO) != 0
                            && resFct022.getSalesCommRate2().compareTo(BigDecimal.ZERO) == 0) {
                        detail.setBuyComm(resFct022.getSalesCommRate1().multiply(CONSUMPTION_TAX)
                                .setScale(4, RoundingMode.DOWN).toString());
                    } else {
                        if (StringUtils.equals(detail.getFundType(), FundType.GENERAL.getValue())) {
                            // ファンドタイプ=一般の場合
                            detail.setBuyComm(SET_BUY_COMM_UNIT);
                        } else if (StringUtils.equals(detail.getFundType(), FundType.CUMULATIVE_PITCH.getValue())
                                || StringUtils.equals(detail.getFundType(), FundType.ACCUMULATE.getValue())
                                || StringUtils.equals(detail.getFundType(),
                                        FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.getValue())) {
                            // ファンドタイプ一般ではない他の場合
                            detail.setBuyComm(SET_BUY_COMM_AMOUNT);
                        }
                    }
                    // 明細.優遇適用率（数値(小数)） preferentialApplicationRate
                    BigDecimal numerator = resFct022.getTransfersPreferentialRateNumerator();
                    BigDecimal denominator = resFct022.getTransfersPreferentialRateDenominator();
                    BigDecimal preferentialRate = new BigDecimal("0.0");
                    if (numerator.compareTo(BigDecimal.ZERO) == 0 || numerator.compareTo(BigDecimal.ZERO) < 0) {
                        // 乗換優遇率・分子 <= 0 の場合
                        detail.setPreferentialApplicationRate(PREFERENTIAL_APPLICATION_RATE_NOT_APPLICAVLE);
                    } else if (denominator.compareTo(BigDecimal.ZERO) == 0
                            || denominator.compareTo(BigDecimal.ZERO) < 0) {
                        // 乗換優遇率・分母 <= 0 の場合
                        detail.setPreferentialApplicationRate(PREFERENTIAL_APPLICATION_RATE_MAX_PERCENT);
                    } else {
                        // 乗換優遇率・分子/乗換優遇率・分母×100　※小数点以下3桁目を切り捨て
                        preferentialRate = numerator.divide(denominator, 4, RoundingMode.DOWN)
                                .multiply(CALCULATION_PERCENT);
                        detail.setPreferentialApplicationRate(
                                preferentialRate.setScale(2, RoundingMode.DOWN).toString());
                    }
                    // 明細.注文締切時間 deadlines
                    detail.setDeadlines(resFct022.getOrderDeadlineTime());
                }
            }
            // 明細.通貨選択／複雑投信確認書受入状況
            // FCT017のリストから一致する明細のデータを取得する
            // 取得できなかった場合は受入状況のステータスは非表示が望ましいと判断し、デフォルトで"3"を設定する。
            String currencySelectAcceptanceStatus = "3";
            String complexMutualFundAcceptanceStatus = "3";
            for (OutputFct017Dto.Brand resFct017 : outFct017Dto.getBrandList()) {
                if (StringUtils.equals(resFct017.getNriCd(),
                        targetFund.getFundCodeSerNo() + targetFund.getFundCodeSub())) {
                    currencySelectAcceptanceStatus = getCurrencySelectAcceptanceStatus(targetFund, resFct017);
                    complexMutualFundAcceptanceStatus = getComplexMutualFundAcceptanceStatus(targetFund, resFct017);
                }
            }
            // 明細.通貨選択確認書受入状況
            detail.setCurrencySelectConfirmDocument(
                    getAcceptanceStatusFromTradeStatus(currencySelectAcceptanceStatus, targetFund.getTradeStatus()));
            // 明細.複雑投信確認書受入状況
            detail.setComplexMutualFundConfirmDocument(
                    getAcceptanceStatusFromTradeStatus(complexMutualFundAcceptanceStatus, targetFund.getTradeStatus()));
            // 明細.購入可否
            if (StringUtils.equals(tradeSuspendFlag, SUSPENDED_ACCOUNT)) {
                detail.setCoercionOrderTarget(BuyPropriety.UNABLE_HANDLING_AND_MEDIATE_PROPRIETY.getValue());
            } else if (isContainTargetFundCd(targetFund, restrictedFundCdList)) {
                // 購入積立不可対象
                detail.setCoercionOrderTarget(BuyPropriety.UNABLE.getValue());
            } else {
                // 通貨確認書受入状況が2よりも小さい もしくは 複雑投信受入状況が2よりも小さい
                if (currencySelectAcceptanceStatus.compareTo("2") < 0
                        || complexMutualFundAcceptanceStatus.compareTo("2") < 0) {
                    detail.setCoercionOrderTarget(BuyPropriety.COERCITON_ORDER_TARGET.getValue());
                } else {
                    // 媒介可否の値をチェック
                    if (StringUtils.equals(getMediateProprietyFromFundType(targetFund.getFundType(), outFct003Dto),
                            MEDIATE_PROPRIETY_ABLE)) {
                        // 媒介可否 = 可
                        if (!StringUtils.equals(targetFund.getStopOrderFlag(), ORDER_STOP_FLAG_STOP) && (StringUtils
                                .equals(targetFund.getTradeStatus(), TradeStatus.AVAILABLE_FOR_PURCHASE.getValue())
                                || StringUtils.equals(targetFund.getTradeStatus(),
                                        TradeStatus.AVAILABLE_FOR_PURCHASE_SHIPPED.getValue()))) {
                            detail.setCoercionOrderTarget(BuyPropriety.ABLE.getValue());
                        } else {
                            detail.setCoercionOrderTarget(BuyPropriety.UNABLE.getValue());
                        }
                    } else {
                        detail.setCoercionOrderTarget(BuyPropriety.UNABLE_HANDLING_AND_MEDIATE_PROPRIETY.getValue());
                    }
                }
            }
            
            // 明細.積立可否
            if (StringUtils.equals(tradeSuspendFlag, SUSPENDED_ACCOUNT)) {
                detail.setAccumulateType(AccumulatePropriety.UNABLE.getValue());
            } else if (isContainTargetFundCd(targetFund, restrictedFundCdList)) {
                // 購入積立不可対象
                detail.setAccumulateType(AccumulatePropriety.TRADE_UNABLE.getValue());
            } else {
                // 通貨確認書受入状況が2よりも小さい もしくは 複雑投信受入状況が2よりも小さい
                if (currencySelectAcceptanceStatus.compareTo("2") < 0
                        || complexMutualFundAcceptanceStatus.compareTo("2") < 0) {
                    detail.setAccumulateType(AccumulatePropriety.COERCITON_ORDER_TARGET.getValue());
                }else {
                    // 媒介可否の値をチェック
                    if (StringUtils.equals(getMediateProprietyFromFundTypeForAccumulate(targetFund.getFundType(), outFct003Dto),
                            MEDIATE_PROPRIETY_ABLE)) {
						if (StringUtils.equals(targetFund.getTradeStatus(),
								TradeStatus.AVAILABLE_FOR_PURCHASE.getValue())
								|| StringUtils.equals(targetFund.getTradeStatus(),
										TradeStatus.AVAILABLE_FOR_PURCHASE_SHIPPED.getValue())) {
							// 媒介可否 = 可
							// API001.検索結果.買付可能ステータス=1 または 3
							detail.setAccumulateType(AccumulatePropriety.ABLE.getValue());
						} else {
							// 上記以外 4:不可
							detail.setAccumulateType(AccumulatePropriety.TRADE_UNABLE.getValue());
						}
                    } else {
                        detail.setAccumulateType(AccumulatePropriety.UNABLE.getValue());
                    }
                }
            }

            // 協会コード
            detail.setKyoukaiCd(Optional.ofNullable(targetFund.getKyokaiCode()).orElse(""));
            
            // ファンドコード（回数）
            detail.setFundCodeTimes(Optional.ofNullable(targetFund.getFundCodeSerNo()).orElse(""));
            
            // ファンドコード（号）
            detail.setFundCodeIssues(Optional.ofNullable(targetFund.getFundCodeSub()).orElse(""));
            
            detailList.add(detail);
        }
        
        return detailList;
    }


    /**
     * ファンドのファンドコードが制限されているかチェックする
     * @param fund ファンド
     * @param restrictedFundCdList リスト.購入積立不可対象
     * @return チェック結果
     */
    private Boolean isContainTargetFundCd(QueryFund fund, List<String> restrictedFundCdList) {
        // 検索結果.ファンドコード（回数）+ 検索結果.ファンドコード（号）+ "△" = FCT025.銘柄リスト.NRIコード
        for (String restrictedFundCd : restrictedFundCdList) {
            if (StringUtils.equals(fund.getFundCodeSerNo() + fund.getFundCodeSub() + " ", restrictedFundCd)) {
                // ファンドコードが一致した場合は購入積立不可対象
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    /**
     * 通貨選択確認書の受入状況ステータスを取得する
     *
     * @param targetFund NRI_QueryFundから取得した買付可能一覧の明細
     * @param resFct017Brand NRI_QueryFundの明細に紐づくFCT017の銘柄
     * @return 受入状況のステータス
    
     */
    private String getCurrencySelectAcceptanceStatus(QueryFund targetFund, OutputFct017Dto.Brand resFct017Brand) {
        
        // 取得できなかった場合は受入状況のステータスは非表示が望ましいと判断し、デフォルトで"3"を設定する。
        String acceptanceStatus = "3";
        // 通貨選択確認書受入状況
        if (StringUtils.equals(resFct017Brand.getAcceptanceNecessity(), AcceptanceNecessiy.NECESSITY.getValue())) {
            if (StringUtils.equals(resFct017Brand.getMutualFundBrandClass(),
                    MutualFundBrand.CURRENCY_SELECT.getValue())) {
                acceptanceStatus = resFct017Brand.getAcceptanceStatus();
            } else if (!StringUtils.equals(resFct017Brand.getMutualFundBrandClass(),
                    MutualFundBrand.CURRENCY_SELECT.getValue())) {
                acceptanceStatus = ACCEPTANCE_EXCLUDE;
            }
        } else if (StringUtils.equals(resFct017Brand.getAcceptanceNecessity(),
                AcceptanceNecessiy.UNNECESSARY.getValue())) {
            acceptanceStatus = ACCEPTANCE_EXCLUDE;
        }
        if (acceptanceStatus.isEmpty()) {
            acceptanceStatus = DISPLAY_UNABLE;
        }
        return acceptanceStatus;
    }
    
    /**
     * 複雑投信確認書の受入状況ステータスを取得する
     *
     * @param QueryFund NRI_QueryFundから取得した買付可能一覧の明細
     * @param resFct017Brand NRI_QueryFundの明細に紐づくFCT017の銘柄
     * @return 受入状況のステータス
    
     */
    private String getComplexMutualFundAcceptanceStatus(QueryFund targetFund, OutputFct017Dto.Brand resFct017Brand) {
        
        // 取得できなかった場合は受入状況のステータスは非表示が望ましいと判断し、デフォルトで"3"を設定する。
        String acceptanceStatus = "3";
        // 複雑投信確認書受入状況
        if (StringUtils.equals(resFct017Brand.getAcceptanceNecessity(), AcceptanceNecessiy.NECESSITY.getValue())) {
            if (StringUtils.equals(resFct017Brand.getMutualFundBrandClass(),
                    MutualFundBrand.COMPLEX_MUTUAL_FUND.getValue())) {
                acceptanceStatus = resFct017Brand.getAcceptanceStatus();
            } else if (!StringUtils.equals(resFct017Brand.getMutualFundBrandClass(),
                    MutualFundBrand.COMPLEX_MUTUAL_FUND.getValue())) {
                acceptanceStatus = ACCEPTANCE_EXCLUDE;
            }
        } else if (StringUtils.equals(resFct017Brand.getAcceptanceNecessity(),
                AcceptanceNecessiy.UNNECESSARY.getValue())) {
            acceptanceStatus = ACCEPTANCE_EXCLUDE;
        }
        if (acceptanceStatus.isEmpty()) {
            acceptanceStatus = DISPLAY_UNABLE;
        }
        return acceptanceStatus;
    }
    
    /**
     * ファンドタイプとFCT003の媒介可否リストをもとに媒介可否を取得する
     *
     * @param fundType 買付可能な明細のファンドタイプ
     * @param outFct003Dto 取引コースの媒介可否リスト
     * @return 媒介可否("0":媒介不可、"1":媒介可)
    
     */
    private String getMediateProprietyFromFundType(String fundType, OutputFct003Dto outFct003Dto) {
        
        String mediatePropriety = MEDIATE_PROPRIETY_UNABLE;
        String targetTradeClass = null;
        
        // 取得対象の媒介可否リストを設定する
        if (StringUtils.equals(fundType, FundType.GENERAL.getValue())) {
            // 購入（一般）
            targetTradeClass = DomestincMutualFundTradeClass.BUY_GENERAL.getValue();
        } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.getValue())) {
            targetTradeClass = DomestincMutualFundTradeClass.CUMULATIVE_PITCH.getValue();
        } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.getValue())) {
            targetTradeClass = DomestincMutualFundTradeClass.CUMULATIVE_PITCH.getValue();
            ;
        } else if (StringUtils.equals(fundType, FundType.ACCUMULATE.getValue())) {
            // 3の場合FCT003の値に関係なく媒介不可を返す
            return mediatePropriety;
        }
        
        for (MediatePropriety list : outFct003Dto.getMediateProprietyList()) {
            
            if (StringUtils.equals(targetTradeClass, list.getTradeClass())) {
                mediatePropriety = list.getMediatePropriety();
            }
        }
        
        return mediatePropriety;
    }
    
    /**
     * ファンドタイプとFCT003の媒介可否リストをもとに媒介可否を取得する
     *
     * @param fundType 買付可能な明細のファンドタイプ
     * @param outFct003Dto 取引コースの媒介可否リスト
     * @return 積立媒介可否("0":媒介不可、"1":媒介可)
    
     */
    private String getMediateProprietyFromFundTypeForAccumulate(String fundType, OutputFct003Dto outFct003Dto) {
        
        String mediatePropriety = MEDIATE_PROPRIETY_UNABLE;
        String targetTradeClass = null;
        
        // 取得対象の媒介可否リストを設定する
        if (StringUtils.equals(fundType, FundType.GENERAL.getValue())) {
        	// 1の場合FCT003の値に関係なく媒介不可を返す
        	return mediatePropriety;
        } else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH.getValue())) {
        	// 2の場合FCT003の値に関係なく媒介不可を返す
        	return mediatePropriety;
        } else if (StringUtils.equals(fundType, FundType.ACCUMULATE.getValue())) {
            // 購入（積立）
        	targetTradeClass = DomestincMutualFundTradeClass.BUY_ACCUMULATE.getValue();
        }else if (StringUtils.equals(fundType, FundType.CUMULATIVE_PITCH_AND_ACCUMULATE.getValue())) {
        	// 購入（積立）
            targetTradeClass = DomestincMutualFundTradeClass.BUY_ACCUMULATE.getValue();
        } 
        
        for (MediatePropriety list : outFct003Dto.getMediateProprietyList()) {   
            if (StringUtils.equals(targetTradeClass, list.getTradeClass())) {
                mediatePropriety = list.getMediatePropriety();
                break;
            }
        }
        
        return mediatePropriety;
    }
    
    
    /**
     * 通貨選択／複雑投信の受入状況と買付可能ステータスをもとに確認書受入状況のコード値を設定する
     *
     * @param fundType 買付可能な明細のファンドタイプ
     * @param outFct003Dto 取引コースの媒介可否リスト
     * @return 媒介可否("0":媒介不可、"1":媒介可)
    
     */
    private String getAcceptanceStatusFromTradeStatus(String acceptanceStatus, String tradeStatus) {
        
        // 通貨選択／複雑投信確認書受入状況
        if (StringUtils.equals(acceptanceStatus, ACCEPTANCE_EXCLUDE)) {
            return ConfrimDocAcceptanceStatus.ACCEPTANCE_EXCLUDE.getValue();
        } else if (
            (
                StringUtils.equals(acceptanceStatus, NOT_ACCEPTED) ||
                StringUtils.equals(acceptanceStatus, ACCEPTED)
            )
            && StringUtils.equals(tradeStatus, TradeStatus.AVAILABLE_FOR_PURCHASE.getValue())
        ) {
            return ConfrimDocAcceptanceStatus.WEB_VIEW.getValue();
        } else if (StringUtils.equals(acceptanceStatus, ACCEPTED)) {
            return ConfrimDocAcceptanceStatus.ACCEPTED.getValue();
        } else if (StringUtils.equals(acceptanceStatus, NOT_ACCEPTED)) {
            return ConfrimDocAcceptanceStatus.COMFRIMATION_OF_ACCEPTANCE.getValue();
        } else {
            return ConfrimDocAcceptanceStatus.HYPEHN.getValue();
        }
    }
    
    /**
     * FCT003の媒介可否リストをもとに媒介可否を取得する
     *
     * @param fundType 買付可能な明細のファンドタイプ
     * @param outFct003Dto 取引コースの媒介可否リスト
     * @return 可否リスト:    list[0]:媒介可否("0":媒介不可、"1":媒介可)
     * 				       list[1]:購入可否("0":購入不可、"1":購入可)
     * 				       list[2]:積立可否("0":積立不可、"1":積立可)
     * 
     */
    private List<String> getMediateProprietyFromOutputFct003Dto(OutputFct003Dto outFct003Dto) {
        
        String mediatePropriety = MEDIATE_PROPRIETY_UNABLE; //媒介可否
        String buyGeneralMediatePropriety = "";
        String cumulativePitchMediatePropriety = "";
        String buyAccumulateMediatePropriety = "";
        List<String> ableList = Arrays.asList(mediatePropriety, mediatePropriety, mediatePropriety); // 可否リスト

        
        for (MediatePropriety list : outFct003Dto.getMediateProprietyList()) {
            if (StringUtils.equals(list.getTradeClass(), DomestincMutualFundTradeClass.BUY_GENERAL.getValue())) {
            	// 購入(一般)
                buyGeneralMediatePropriety = list.getMediatePropriety();
                continue;
            } else if (StringUtils.equals(list.getTradeClass(),
                    DomestincMutualFundTradeClass.CUMULATIVE_PITCH.getValue())) {
            	// 購入(累投)
                cumulativePitchMediatePropriety = list.getMediatePropriety();
                continue;
            } else if (StringUtils.equals(list.getTradeClass(),
                    DomestincMutualFundTradeClass.BUY_ACCUMULATE.getValue())) {
            	// 購入（積立）
            	buyAccumulateMediatePropriety = list.getMediatePropriety();
                continue;
            }
        }
        
        if (StringUtils.equals(buyGeneralMediatePropriety, MEDIATE_PROPRIETY_ABLE)
                || StringUtils.equals(cumulativePitchMediatePropriety, MEDIATE_PROPRIETY_ABLE)) {
        	// 購入（一般）もしくは取引種別=購入（累投）の場合、購入可
        	ableList.set(1, MEDIATE_PROPRIETY_ABLE);
        }
        if (StringUtils.equals(buyAccumulateMediatePropriety, MEDIATE_PROPRIETY_ABLE)) {
        	// 購入（積立）の場合
        	ableList.set(2, MEDIATE_PROPRIETY_ABLE);
        }
        
        
        /*
         * 　取引種別=購入（一般）
　			取引種別=購入（累投）
　			取引種別=購入（積立）
         * いずれかの媒介可否が可　の場合、　「媒介」可をセットする
         */
        if(StringUtils.equals(ableList.get(1), MEDIATE_PROPRIETY_ABLE) || StringUtils.equals(ableList.get(2), MEDIATE_PROPRIETY_ABLE)) {
        	ableList.set(0, MEDIATE_PROPRIETY_ABLE);
        }
        
        // 可否リスト
        return ableList;
    }
    
    /**
     * SQL001の処理
     *
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     * @return データありの場合レスポンス
     * @exception Exception 実行時例外
     */
    private List<String> executeSql001(String fundCodeTimes, String fundCodeIssues) throws Exception {
    	List<String> kyoukaiCdList = new ArrayList<String>();
    	
    	DataList<IfaDomesticMutualFundBuyAbleListSql001ResponseModel> sql0001Res = dao
                .selectIfaDomesticMutualFundBuyAbleListSql001(new IfaDomesticMutualFundBuyAbleListSql001RequestModel(fundCodeTimes, fundCodeIssues));
        
    	if (sql0001Res != null && !CollectionUtils.isEmpty(sql0001Res.getDataList())) {
    		kyoukaiCdList = sql0001Res.getDataList().stream().map(d -> d.getMFCode()).collect(Collectors.toList());
        }
                
        return kyoukaiCdList;
    }
    
    /**
     * SQL002の処理
     *
     * @param fundCodeTimes ファンドコード（回数）
     * @param fundCodeIssues ファンドコード（号）
     * @return データありの場合レスポンス
     * @exception Exception 実行時例外
     */
    private DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> executeSql002(String fundCodeTimes, String fundCodeIssues) throws Exception {
    	DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> fundInfoList = new DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel>();
    	
    	DataList<IfaDomesticMutualFundBuyAbleListSql002ResponseModel> sql0002Res = dao
                .selectIfaDomesticMutualFundBuyAbleListSql002(new IfaDomesticMutualFundBuyAbleListSql002RequestModel(fundCodeTimes, fundCodeIssues));
        
    	if (sql0002Res != null && !CollectionUtils.isEmpty(sql0002Res.getDataList())) {
    		fundInfoList = sql0002Res;
        }
                
        return fundInfoList;
    }

}