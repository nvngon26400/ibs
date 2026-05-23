package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.protocol.account.GetSecuritiesBalanceResp;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDepositBalanceDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDepositBalanceDetailSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailCommonRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailCommonResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoDomesticStockDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignClaimDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignMmfDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoForeignStockDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDepositBalanceDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.AccountDetWebData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionDetWebInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountPositionDetWebOutData;

/**
 * 画面ID：SUB0202_010201-04
 * 画面名：預り残高詳細

 * @author 秋山
 */
@Component(value = "cmpIfaDepositBalanceDetailService")
public class IfaDepositBalanceDetailServiceImpL implements IfaDepositBalanceDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDepositBalanceDetailServiceImpL.class);
    
    // FCT001_出力パラメータ：対象顧客参照権限有無（なし）
    private static final String AUTH_ERROR_VALUE = "0";
    
    // FCT001_エラーメッセージ：権限なしエラー
    private static final String FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    // 商品名：国内株式
    private static final String DOMESTIC_STOCK_DEPOSIT = "国内株式";
    
    // 商品名：国内債券
    private static final String DOMESTIC_CLAIM_DEPOSIT = "国内債券";
    
    // 商品名：投資信託口数
    private static final String MUTUAL_FUND_LOT_DEPOSIT = "投資信託口数";
    
    // 商品名：投資信託金額
    private static final String MUTUAL_FUND_AMOUNT_DEPOSIT = "投資信託金額";
    
    // 商品名：外国債券
    private static final String FOREIGN_CLAIM_DEPOSIT = "外国債券";

    // 商品名：外国債券券（外貨建）
    private static final String FOREIGN_CLAIM_FOREIGN_CURRENCY_DEPOSIT = "外国債券（外貨建）";
    
    // 商品名：外国株式
    private static final String FOREIGN_STOCK_DEPOSIT = "外国株式";
    
    // 商品名：外貨建MMF
    private static final String FOREIGN_MMF_DEPOSIT = "外貨建MMF";
    
    // JrNisa契約区分
    private static final String JR_NISA_KBN = "1";
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private CometCommonService cometCommonService;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private IfaDepositBalanceDetailDao dao;
    
    enum HitokuteiAzukariKbnEnum {
        
        /** 特定預り */
        SPECIFIC_0("0"),
        /** NISA預り */
        NISA_4("4"),
        /** 特定（特例）預り */
        EXSPECIFIC_5("5"),
        /** 一般（特例）預り */
        EXGENERALLY_6("6"),
        /** JrNISA */
        JRNISA_7("7"),
        /** つみたてNISA預り */
        ACCUMULATIONNISA_8("8"),
        /** 総合NISA(成長投資枠) */
        TOTALNISA_H("H"),
        /** 総合NISA(つみたて投資枠) */
        TOTALACCUMULATIONNISA_I("I"),
        /** 継続管理勘定 */
        CONTINUATIONMANAGE_J("J");
        
        private String key;
        
        private HitokuteiAzukariKbnEnum(String key) {
            
            this.key = key;
        }
    }
    
    enum AzukariKbnEnum {
        
        /** 特定 */
        SPECIFIC("2"),
        /** 一般 */
        GENERAL("1"),
        /** 成長投資枠 */
        GROWTH_INVESTMENT("H"),
        /** NISA */
        NISA("4"),
        /** ジュニアNISA特定 */
        JR_SPECIFIC("6"),
        /** ジュニアNISA一般 */
        JR_GENERAL("5"),
        /** 継続管理勘定 */
        CONTINUOUS_MANAGEMENT("J"),
        /** ジュニアNISA */
        JR_NISA("7");
        
        private String key;
        
        private AzukariKbnEnum(String key) {
            
            this.key = key;
        }
    }

    enum InterestPaymentKbnEnum {

        /** 利払区分.割引債 */
        DISCOUNT("0 "),

        /** 利払区分.年1回 */
        ANNUAL("1 "),

        /** 利払区分.年2回 */
        SEMI_ANNUAL("2 "),

        /** 利払区分.年4回 */
        QUARTERLY("3 "),

        /** 利払区分.年12回(毎月) */
        MONTHLY("4 "),

        /** 利払区分.年6回(隔月) */
        BIMONTHLY("5 "),

        /** 利払区分.年3回(4ヶ月毎) */
        QUADRIMESTRALLY("6 ");

        private String key;

        private InterestPaymentKbnEnum(String key) {
            this.key = key;
        }
    }

    /** 利払日月末表示区分.月末 */
    private static String INTEREST_PAYMENT_DATE_GETSUMATSU_KBN_GETSUMATSU = "1 ";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDepositBalanceDetailA001DtoRequest
     * Dto レスポンス：IfaDepositBalanceDetailA001DtoResponse
     * model リクエスト：IfaDepositBalanceDetailSql002RequestModel
     * model レスポンス：IfaDepositBalanceDetailSql002ResponseModel
    
     * @param dtoReq リクエスト情報
     * @return IfaDepositBalanceDetailA001ResponseDto
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailA001ResponseDto> initializeA001(IfaDepositBalanceDetailA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaDepositBalanceDetailA001ResponseDto> resDto = new DataList<IfaDepositBalanceDetailA001ResponseDto>();
        
        IfaDepositBalanceDetailCommonRequestDto commonDtoReq = new IfaDepositBalanceDetailCommonRequestDto();
        
        BeanUtils.copyProperties(commonDtoReq, dtoReq);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDepositBalanceDetailServiceImplL.initializeA001");
        }
        
        DataList<IfaDepositBalanceDetailCommonResponseDto> commonDtoRes = commonAction(commonDtoReq);
        BeanUtils.copyProperties(resDto, commonDtoRes);
        
        return resDto;
    }
    
    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaDepositBalanceDetailA004DtoRequest
     * Dto レスポンス：IfaDepositBalanceDetailA004DtoResponse
    
     * @param dtoReq リクエスト情報
     * @return IfaDepositBalanceDetailA004ResponseDto レスポンス情報
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaDepositBalanceDetailA004ResponseDto> updateA004(IfaDepositBalanceDetailA004RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaDepositBalanceDetailA004ResponseDto> resDto = new DataList<IfaDepositBalanceDetailA004ResponseDto>();
        
        IfaDepositBalanceDetailCommonRequestDto commonDtoReq = new IfaDepositBalanceDetailCommonRequestDto();
        
        BeanUtils.copyProperties(commonDtoReq, dtoReq);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDepositBalanceDetailServiceImplL.updateA004");
        }
        
        DataList<IfaDepositBalanceDetailCommonResponseDto> commonDtoRes = commonAction(commonDtoReq);
        BeanUtils.copyProperties(resDto, commonDtoRes);
        
        return resDto;
    }
    
    /**
     * アクション名：A001,A004共通
    
     * @param commonDtoReq リクエスト
     * @return レスポンス
     * @exception Exception 処理で例外が発生した場合
     */
    public DataList<IfaDepositBalanceDetailCommonResponseDto> commonAction(
            IfaDepositBalanceDetailCommonRequestDto commonDtoReq) throws Exception {
        
        DataList<IfaDepositBalanceDetailCommonResponseDto> commonDtoRes = new DataList<IfaDepositBalanceDetailCommonResponseDto>();
        
        IfaDepositBalanceDetailCommonResponseDto commonResDto = new IfaDepositBalanceDetailCommonResponseDto();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        // FCT001実行
        commonDtoRes = executeFct001(butenCode, accountNumber);
        // 正常終了以外の場合、エラー内容を返却して処理を終了する。
        if (commonDtoRes.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return commonDtoRes;
        }
        
        /* Response項目設定 */
        // 部店
        commonResDto.setButenCode(butenCode);
        // 口座番号
        commonResDto.setAccountNumber(String.format("%7s", accountNumber).replace(" ", "0"));
        // 顧客名（漢字）
        commonResDto.setCustomerNameKanji(cc.getCustomerNameKanji());
        // 顧客名（カナ）
        commonResDto.setCustomerNameKana(cc.getCustomerNameKana());
        // 銘柄コード
        commonResDto.setBrandCode(commonDtoReq.getBrandCode());
        // 銘柄名
        commonResDto.setBrandName(commonDtoReq.getBrandName());
        
        // 国内株式預りリスト
        List<IfaDepositBalanceDetailResponseDtoDomesticStockDepositList> domesticStockDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoDomesticStockDepositList>();
        // 投資信託口数預りリスト
        List<IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList> mutualFundLotDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList>();
        // 投資信託金額預りリスト
        List<IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList> mutualFundAmountDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList>();
        // 国内債券預りリスト
        List<IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList> domesticClaimDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList>();
        // 外国債券預りリスト
        List<IfaDepositBalanceDetailResponseDtoForeignClaimDepositList> foreignClaimDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoForeignClaimDepositList>();
        // 外国債券（外貨建）預りリスト
        List<IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList> foreignClaimForeignCurrencyDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList>();
        // 外国株式預りリスト
        List<IfaDepositBalanceDetailResponseDtoForeignStockDepositList> foreignStockDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoForeignStockDepositList>();
        // 外貨建MMF預りリスト
        List<IfaDepositBalanceDetailResponseDtoForeignMmfDepositList> foreignMmfDepositList = new ArrayList<IfaDepositBalanceDetailResponseDtoForeignMmfDepositList>();
        
        QueryAccountPositionDetWebInData api001Req = new QueryAccountPositionDetWebInData();
        
        //A001リクエスト.商品名＝'国内株式'、'投資信託口数'、'投資信託金額'、'国内債券'、'外国債券'の場合
        if (StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_STOCK_DEPOSIT)
                || StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_CLAIM_DEPOSIT)
                || StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_LOT_DEPOSIT)
                || StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_AMOUNT_DEPOSIT)
                || StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_DEPOSIT)) {
            
            //API001のリクエストパラメータに下記をセット
            api001Req.setButenCd(butenCode);
            api001Req.setKozaNo(String.format("%7s", accountNumber).replace(" ", "0"));
            api001Req.setSecId(commonDtoReq.getSecurityType());
            api001Req.setDomesticFgnId(commonDtoReq.getKokunaiGaiKbn());
            api001Req.setSecType1(commonDtoReq.getSecurityClass1());
            api001Req.setSecType2(commonDtoReq.getSecurityClass2());
            api001Req.setCompanyCode(commonDtoReq.getCompanyCode());
            api001Req.setStRightId(commonDtoReq.getRightType());
            api001Req.setNewOldId(commonDtoReq.getNewOldType());
            if (StringUtils.equals(commonDtoReq.getTimes(), "")) {
                api001Req.setSerNo("9999");
            } else {
                api001Req.setSerNo(commonDtoReq.getTimes());
            }
            api001Req.setSubCode1(commonDtoReq.getIssue1());
            api001Req.setSubCode2(commonDtoReq.getIssue2());
            api001Req.setListCntryCd(commonDtoReq.getListedCountryCode());
            api001Req.setRequestType(" ");
            api001Req.setHitokuteiKbn(commonDtoReq.getNonSpecificDepositCategory());
            api001Req.setAccountGetKbn(commonDtoReq.getGetAccountCategory());
            
            //api001を呼び出す（預り残高一覧リクエスト（個別）（次期Web））
            List<QueryAccountPositionDetWebOutData> api001ResList = apiWrapper.queryAccountPositionDetWeb(api001Req);
            Boolean isFatal = false;
            for (QueryAccountPositionDetWebOutData api001Res : api001ResList) {
                if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                    isFatal = true;
                }
            }
            if (isFatal) {
                return apiErrorUtil.createDataList(new ArrayList<>(), null);
            }
            
            //商品口座預り編集
            String commodityAccountDeposit = this.commodityAccountDeposit(commonDtoReq, cc);
            commonResDto.setCommodityAccountDeposit(commodityAccountDeposit);
            
            String hitokuteiAzukariKbn = commonDtoReq.getNonSpecificDepositCategory();
            //APIの返りがある場合
            if (api001ResList != null && api001ResList.size() > 0) {
                for (QueryAccountPositionDetWebOutData api001ListRes : api001ResList) {
                    if (api001ListRes.getAccountDetWebData() != null) {
                        for (AccountDetWebData api001OutSub : api001ListRes.getAccountDetWebData()) {
                            //⑨A001リクエスト.商品名＝'国内株式'の場合
                            if (StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_STOCK_DEPOSIT)) {
                                //国内株式の優先市場を取得する。
                                IfaDepositBalanceDetailSql002RequestModel selSql002Req = new IfaDepositBalanceDetailSql002RequestModel();
                                selSql002Req.setIpmProductCode(commonDtoReq.getBrandCode());
                                DataList<IfaDepositBalanceDetailSql002ResponseModel> selSql002Res = dao
                                        .selectIfaDepositBalanceDetailSql002(selSql002Req);
                                
                                // FCT020.評価用現在値
                                BigDecimal currentPrice = null;
                                if (selSql002Res.getDataList().size() > 0) {
                                    // FCT020入力パラメータ設定
                                    InputFct020Dto inputFct020Dto = new InputFct020Dto();
                                    inputFct020Dto.setBrandCode(commonDtoReq.getBrandCode());
                                    inputFct020Dto.setMarketCode(selSql002Res.get(0).getIpmSeInvestmentsCode());
                                    OutputFct020Dto fct020Dto = fct020.getData(inputFct020Dto);

                                    // 現在値
                                    // FCT020.評価用現在値
                                    // ※小数点第3位以下切捨て
                                    BigDecimal culCurrentPriceValue = new BigDecimal(fct020Dto.getCurrentValueForEvaluation());
                                    currentPrice = this.calcWkCurrentPrice(culCurrentPriceValue);
                                }

                                //明細項目編集_国内株式
                                IfaDepositBalanceDetailResponseDtoDomesticStockDepositList detail = this
                                        .createDomesticStockDeposit(api001OutSub, hitokuteiAzukariKbn, currentPrice);
                                
                                domesticStockDepositList.add(detail);

                                //⑨A001リクエスト.商品名＝'投資信託口数'の場合
                            } else if (StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_LOT_DEPOSIT)) {
                                //投信基準価格単位を取得する。
                                IfaDepositBalanceDetailSql001RequestModel selSql001Req = new IfaDepositBalanceDetailSql001RequestModel();
                                selSql001Req.setBrandCode(String.format("%-8s",
                                        api001OutSub.getSerNo() + " " + api001OutSub.getSubCode2()));
                                DataList<IfaDepositBalanceDetailSql001ResponseModel> selSql001Res = dao
                                        .selectIfaDepositBalanceDetailSql001(selSql001Req);
                                
                                // 基準価額単位
                                String basePriceUnit = (selSql001Res.size() > 0) ? selSql001Res.get(0).getBasePriceUnit() : "0";
                                // 協会コード
                                String kyoukaiCd = (selSql001Res.size() > 0) ? selSql001Res.get(0).getKyoukaiCd() : null;

                                // 投信基準価格
                                BigDecimal basePrice = (selSql001Res.size() > 0) ? getMutualFundBasePrice(kyoukaiCd) : BigDecimal.ZERO;

                                //明細項目編集_投資信託口数
                                IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList detail = this
                                        .createMutualFundLotDeposit(api001OutSub, basePrice, basePriceUnit,
                                                hitokuteiAzukariKbn);
                                
                                mutualFundLotDepositList.add(detail);
                                
                                //⑨A001リクエスト.商品名＝'投資信託金額'の場合    
                            } else if (StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_AMOUNT_DEPOSIT)) {
                                //投信基準価格単位を取得する。
                                IfaDepositBalanceDetailSql001RequestModel selSql001Req = new IfaDepositBalanceDetailSql001RequestModel();
                                selSql001Req.setBrandCode(String.format("%-8s",
                                        api001OutSub.getSerNo() + " " + api001OutSub.getSubCode2()));
                                DataList<IfaDepositBalanceDetailSql001ResponseModel> selSql001Res = dao
                                        .selectIfaDepositBalanceDetailSql001(selSql001Req);
                                
                                // 基準価額単位
                                String basePriceUnit = (selSql001Res.size() > 0) ? selSql001Res.get(0).getBasePriceUnit() : "0";

                                // 協会コード
                                String kyoukaiCd = (selSql001Res.size() > 0) ? selSql001Res.get(0).getKyoukaiCd() : null;

                                // 投信基準価格
                                BigDecimal basePrice = (selSql001Res.size() > 0) ? getMutualFundBasePrice(kyoukaiCd) : BigDecimal.ZERO;

                                // SQL001件数が1件以上かフラグ
                                Boolean isSql001ExistsData = (selSql001Res.size() > 0) ? true : false;

                                //明細項目編集_投資信託金額
                                IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList detail = this
                                        .createMutualFundAmountDeposit(api001OutSub, basePrice, basePriceUnit,
                                                hitokuteiAzukariKbn, isSql001ExistsData);
                                
                                mutualFundAmountDepositList.add(detail);
                                
                                //⑨A001リクエスト.商品名＝'国内債券'の場合    
                            } else if (StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_CLAIM_DEPOSIT)) {
                                //明細項目編集_国内債券
                                IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList detail = this
                                        .createDomesticClaimDeposit(api001OutSub, hitokuteiAzukariKbn);
                                
                                domesticClaimDepositList.add(detail);
                                
                                //⑨A001リクエスト.商品名＝'外国債券'の場
                            } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_DEPOSIT)) {
                                //明細項目編集_外国債券
                                IfaDepositBalanceDetailResponseDtoForeignClaimDepositList detail = this
                                        .createForeignClaimDeposit(api001OutSub, hitokuteiAzukariKbn);
                                
                                foreignClaimDepositList.add(detail);
                                
                            }
                        }
                    }
                }
                
            } else {
                //API001のレスポンスが0件の場合
                return commonDtoRes;
            }
        } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_FOREIGN_CURRENCY_DEPOSIT)
                ||  StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_STOCK_DEPOSIT)
                || StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_MMF_DEPOSIT)) {
            //API003入力パラメータの設定を行う
            GetSecuritiesBalanceResp api003Res = new GetSecuritiesBalanceResp();
            try {
                api003Res = foreignAccountService.getSecuritiesBalance(butenCode, accountNumber, null,
                        commonDtoReq.getSecurityClass(), commonDtoReq.getCountryCode(), commonDtoReq.getCurrencyCode(),
                        commonDtoReq.getDepositType(), commonDtoReq.getBrandCode());
            } catch (Exception e) {
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            
            //商品口座預り編集
            String commodityAccountDeposit = this.commodityAccountDeposit(commonDtoReq, cc);
            commonResDto.setCommodityAccountDeposit(commodityAccountDeposit);
            
            if (api003Res != null) {
                if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_FOREIGN_CURRENCY_DEPOSIT)) {
                    // SQL003 外国債券（外貨建）の銘柄情報を取得する。
                    IfaDepositBalanceDetailSql003RequestModel selSql003Req = new IfaDepositBalanceDetailSql003RequestModel();
                    selSql003Req.setBrandCode(commonDtoReq.getBrandCode());

                    DataList<IfaDepositBalanceDetailSql003ResponseModel> selSql003Res
                            = dao.selectIfaDepositBalanceDetailSql003(selSql003Req);
                    
                    //明細項目編集_外国債券（外貨建）
                    IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList detail
                            = this.createForeignClaimForeignCurrencyDepositList(api003Res, selSql003Res);
                    
                    foreignClaimForeignCurrencyDepositList.add(detail);
                    
                }
                else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_STOCK_DEPOSIT)) {
                    //明細項目編集_外外国株式
                    IfaDepositBalanceDetailResponseDtoForeignStockDepositList detail = this
                            .createForeignStockDepositList(api003Res);
                    
                    foreignStockDepositList.add(detail);
                    
                } else {
                    //明細項目編集_外貨建MMF
                    IfaDepositBalanceDetailResponseDtoForeignMmfDepositList detail = this
                            .createForeignMmfDeposit(api003Res);
                    
                    foreignMmfDepositList.add(detail);
                }
            }
        }
        
        // 国内株式預りリスト設定
        commonResDto.setDomesticStockDepositList(domesticStockDepositList);
        // 投資信託口数預りリスト設定
        commonResDto.setMutualFundLotDepositList(mutualFundLotDepositList);
        // 投資信託金額預りリスト設定
        commonResDto.setMutualFundAmountDepositList(mutualFundAmountDepositList);
        // 国内債券預りリスト設定
        commonResDto.setDomesticClaimDepositList(domesticClaimDepositList);
        // 外国債券預りリスト設定
        commonResDto.setForeignClaimDepositList(foreignClaimDepositList);
        // 外国債券（外貨建）預りリスト設定
        commonResDto.setForeignClaimForeignCurrencyDepositList(foreignClaimForeignCurrencyDepositList);
        // 外国株式預りリスト設定
        commonResDto.setForeignStockDepositList(foreignStockDepositList);
        // 外貨建MMF預りリスト設定
        commonResDto.setForeignMmfDepositList(foreignMmfDepositList);
        
        // レスポンスDTOを作成
        List<IfaDepositBalanceDetailCommonResponseDto> commonResDtoList = new ArrayList<IfaDepositBalanceDetailCommonResponseDto>();
        
        commonResDtoList.add(commonResDto);
        commonDtoRes = apiErrorUtil.createDataList(commonResDtoList, null);
        
        return commonDtoRes;
    }
    
    /**
    * 共通：FCT001実行処理
    *
    * @param butenCode 顧客共通情報の部店コード
    * @param accountNumber 顧客共通情報の口座番号
    * @return データリスト. エラー有の場合そのエラー内容
    */
    private <E> DataList<E> executeFct001(String butenCode, String accountNumber) {
        
        List<E> list = new ArrayList<E>();
        
        // FCT001：利用者の口座に対する権限チェックを行う
        InputFct001Dto reqFct001 = new InputFct001Dto();
        reqFct001.setButenCode(butenCode);
        reqFct001.setAccountNumber(accountNumber);
        OutputFct001Dto resFct001 = fct001.doCheck(reqFct001);
        
        if (StringUtils.equals(resFct001.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            // 対象顧客参照権限：なしの場合
            return IfaCommonUtil.createDataList(list, ErrorLevel.FATAL, FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(FCT001_ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
        }
        return new DataList<E>();
    }
    
    /** 
     * 国内株式 預り明細情報の作成
    
     * @param api001ResSub API001レスポンス.明細部
     * @param hitokuteiAzukariKbn API001リクエスト.非特定預り区分
     * @param currentPrice FCT020.評価用現在値
     * @return 国内株式 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoDomesticStockDepositList createDomesticStockDeposit(
            AccountDetWebData api001ListRes, String hitokuteiAzukariKbn, BigDecimal currentPrice) {
        
        IfaDepositBalanceDetailResponseDtoDomesticStockDepositList detail = new IfaDepositBalanceDetailResponseDtoDomesticStockDepositList();
        
        //保有株数
        detail.setHoldingStock(api001ListRes.getAcPositiont1());
        //取得単価/参考単価
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            BigDecimal getPriceReferencePriceValue = new BigDecimal(api001ListRes.getAveragePrice());
            detail.setGetPriceReferencePrice(
                    getPriceReferencePriceValue.setScale(2, RoundingMode.DOWN).toPlainString());
        } else {
            BigDecimal getPriceReferencePriceValue = new BigDecimal(api001ListRes.getAcqPrice());
            detail.setGetPriceReferencePrice(
                    getPriceReferencePriceValue.setScale(2, RoundingMode.DOWN).toPlainString());
        }
        //現在値
        if (currentPrice != null) {
            detail.setCurrentPrice(currentPrice.toPlainString());
        } else {
            detail.setCurrentPrice(null);
        }

        //取得金額/参考金額
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            BigDecimal averagePrice = new BigDecimal(api001ListRes.getAveragePrice());
            BigDecimal acPositiont1 = new BigDecimal(api001ListRes.getAcPositiont1());
            BigDecimal acquireAmountReferenceAmountValue = averagePrice.multiply(acPositiont1);
            detail.setAcquireAmountReferenceAmount(
                    acquireAmountReferenceAmountValue.setScale(3, RoundingMode.DOWN).toPlainString());
        } else {
            BigDecimal acqPrice = new BigDecimal(api001ListRes.getAcqPrice());
            BigDecimal acPositiont1 = new BigDecimal(api001ListRes.getAcPositiont1());
            BigDecimal acquireAmountReferenceAmountValue = acqPrice.multiply(acPositiont1);
            detail.setAcquireAmountReferenceAmount(
                    acquireAmountReferenceAmountValue.setScale(3, RoundingMode.DOWN).toPlainString());
        }

        if (currentPrice != null) {
            //評価額
            // 明細部.約定基準残高（T+1）
            BigDecimal acPositiont1 = new BigDecimal(api001ListRes.getAcPositiont1());
            BigDecimal valuationValue = currentPrice.multiply(acPositiont1);
            detail.setValuation(valuationValue.setScale(3, RoundingMode.DOWN).toPlainString());

            //評価損益
            String acqOrAveragePrice = "";
            if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                acqOrAveragePrice = api001ListRes.getAveragePrice(); // API001.明細部.移動平均単価
            } else {
                acqOrAveragePrice = api001ListRes.getAcqPrice(); // API001.明細部.取得単価
            }

            if (StringUtils.equals(acqOrAveragePrice, "")  // API001.明細部.取得単価・移動平均単価がスペースの場合（ApiWrapperでトリムされるため空文字と比較）
                || StringUtils.equals(api001ListRes.getAcPositiont1(), "")  // API001.明細部.約定基準残高（T＋1）がスペースの場合（ApiWrapperでトリムされるため空文字と比較）
                || StringUtils.equals(api001ListRes.getStRightId(), "1")) { // API001.明細部.権利区分=’1’(:ｺｰﾎﾟﾚｰﾄｱｸｼｮﾝに伴う売買停止期間中)の場合
                detail.setValuationProfitAndLoss("-");
            } else {
                BigDecimal price = new BigDecimal(acqOrAveragePrice); // API001.明細部.取得単価・移動平均単価
                BigDecimal valuationProfitAndLossValueA = currentPrice.multiply(acPositiont1); // FCT020.評価用現在値×API001.明細部.約定基準残高（T+1）
                BigDecimal valuationProfitAndLossValueB = price.multiply(acPositiont1); // API001.明細部.取得単価・移動平均単価×API001.明細部.約定基準残高（T+1）
                BigDecimal valuationProfitAndLoss = valuationProfitAndLossValueA.subtract(valuationProfitAndLossValueB); // FCT020.評価用現在値×API001.明細部.約定基準残高（T+1）-API001.明細部.取得単価 ×API001.明細部.約定基準残高（T＋1）
                if (valuationProfitAndLossValueB.compareTo(BigDecimal.ZERO) < 0) { // API001.明細部.取得単価・移動平均単価 ×API001.明細部.約定基準残高（T＋1）がマイナスの場合
                    detail.setValuationProfitAndLoss("-");
                } else {
                    detail.setValuationProfitAndLoss(valuationProfitAndLoss.setScale(2, RoundingMode.DOWN).toPlainString());
                }
            }
        }

        //預り年月日
        detail.setDepositDate(api001ListRes.getDepositDate());
        //保護／代用区分
        if (StringUtils.equals(api001ListRes.getDepositCode3(), "1")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("信用代用");
        } else if (StringUtils.equals(api001ListRes.getDepositCode3(), "0")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else {
            detail.setProtectionSubstitutionClass("-");
        }
        //入庫理由
        if (StringUtils.equals(api001ListRes.getDpRoute2(), "0")) {
            detail.setStorageReason("買付");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "1")) {
            detail.setStorageReason("預り");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "2")) {
            detail.setStorageReason("振替");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "3")) {
            detail.setStorageReason("扱店変更");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "4")) {
            detail.setStorageReason("募集");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "5")) {
            detail.setStorageReason("現引");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "6")) {
            detail.setStorageReason("一部売却残");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "7")) {
            detail.setStorageReason("売却取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "9")) {
            detail.setStorageReason("出庫取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "B")) {
            detail.setStorageReason("特別償還");
        } else if (api001ListRes.getDpRoute2() == null || StringUtils.equals(api001ListRes.getDpRoute2(), "")
                || StringUtils.equals(api001ListRes.getDpRoute2(), "<BR>")) {
            detail.setStorageReason("-");
        } else {
            detail.setStorageReason(api001ListRes.getDpRoute2());
        }
        
        return detail;
    }
    
    /** 
     * 投資信託口数 預り明細情報の作成
    
     * @param api001ResSub API001レスポンス.明細部
     * @param basePrice SQL004レスポンス.基準価格
     * @param basePriceUnit SQL001レスポンス.基準価格単位
     * @param hitokuteiAzukariKbn API001リクエスト.非特定預り区分
     * @return 投資信託口数 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList createMutualFundLotDeposit(
            AccountDetWebData api001ListRes, BigDecimal basePrice, String basePriceUnit, String hitokuteiAzukariKbn) {
        
        IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList detail = new IfaDepositBalanceDetailResponseDtoMutualFundLotDepositList();
        
        BigDecimal acPositiont1 = new BigDecimal(api001ListRes.getAcPositiont1());
        BigDecimal quantityModValue = new BigDecimal(api001ListRes.getQuantityModValue());
        BigDecimal averagePrice = new BigDecimal(api001ListRes.getAveragePrice());
        BigDecimal acqPrice = new BigDecimal(api001ListRes.getAcqPrice());
        BigDecimal priceModValue = new BigDecimal(api001ListRes.getPriceModValue());
        BigDecimal position = new BigDecimal(api001ListRes.getPosition());
        BigDecimal basePriceUnitValue = new BigDecimal(basePriceUnit);
        
        //保有口数
        String subCode2 = api001ListRes.getSubCode2();
        if (StringUtils.equals(api001ListRes.getSecId(), "S")
                && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")
                && !StringUtils.equals(subCode2.substring(1, 1), "ﾜ")) {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 10, RoundingMode.DOWN);
            unitVolume = unitVolume.divide(new BigDecimal("1000"));
            detail.setUnitVolume(unitVolume.setScale(0, RoundingMode.DOWN).toPlainString());
        } else if (api001ListRes.getSecId().equals("S") && api001ListRes.getDomesticFgnId().equals("1")
                && ((api001ListRes.getSecType1().equals("0") && api001ListRes.getSecType2().equals("0"))
                        || (api001ListRes.getSecType1().equals("5") && api001ListRes.getSecType2().equals("1"))
                        || (api001ListRes.getSecType1().equals("5") && api001ListRes.getSecType2().equals("2")))) {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 10, RoundingMode.DOWN);
            unitVolume = unitVolume.divide(new BigDecimal("1000"));
            detail.setUnitVolume(unitVolume.setScale(0, RoundingMode.DOWN).toPlainString());
        } else {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 0, RoundingMode.DOWN);
            detail.setUnitVolume(unitVolume.toPlainString());
        }
        
        //取得単価/参考単価
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            detail.setGetPriceReferencePrice(averagePrice.setScale(2, RoundingMode.DOWN).toPlainString());
        } else {
            if (StringUtils.equals(api001ListRes.getSecId(), "S")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                BigDecimal getPriceReferencePrice = acqPrice.multiply(new BigDecimal("100"));
                getPriceReferencePrice = getPriceReferencePrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "S")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "1")
                    && !(StringUtils.equals(api001ListRes.getSecType1(), "2")
                            && StringUtils.equals(api001ListRes.getSecType2(), "1"))
                    && !(StringUtils.equals(api001ListRes.getSecType1(), "3")
                            && StringUtils.equals(api001ListRes.getSecType2(), "0"))) {
                BigDecimal getPriceReferencePrice = acqPrice.multiply(new BigDecimal("100"));
                getPriceReferencePrice = getPriceReferencePrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "T")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                detail.setGetPriceReferencePrice(api001ListRes.getAcqPrice());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "Y")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                detail.setGetPriceReferencePrice(api001ListRes.getAcqPrice());
            } else {
                BigDecimal getPriceReferencePrice = acqPrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            }
        }
        
        //現在値
        detail.setCurrentPrice(basePrice.toString());
        
        //取得金額/参考金額
        if (basePriceUnitValue.compareTo(BigDecimal.ZERO) != 0 ) {
            if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                BigDecimal unitVolume = new BigDecimal(detail.getUnitVolume());
                BigDecimal acquireAmountReferenceAmount = averagePrice.multiply(unitVolume);
                acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(basePriceUnitValue, 3, RoundingMode.DOWN);
                detail.setAcquireAmountReferenceAmount(acquireAmountReferenceAmount.toPlainString());

            } else {
                BigDecimal acquireAmountReferenceAmount = acqPrice.multiply(position);
                acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(basePriceUnitValue, 3, RoundingMode.DOWN);
                detail.setAcquireAmountReferenceAmount(acquireAmountReferenceAmount.toPlainString());

            }
        } else {
            detail.setAcquireAmountReferenceAmount("-");
        }
        
        //評価額
        // SQL004.基準価額 × API001.残高数量 ÷ SQL001.基準価額単位
        // ※計算結果の小数点以下切り捨て
        //
        //※計算結果の小数点以下切捨て
        //※SQL001が0件、またはSQL001.基準価額単位がゼロ、またはSQL004が0件、またはSQL011.基準価額がスペース、またはSQL004.基準価額がゼロ以下の場合、'-'(半角ハイフン)を設定      
        //SQL004.基準価額は、SQL004が0件、またはSQL004.基準価額がスペースの場合、0として受け渡される
        if (basePriceUnitValue.compareTo(BigDecimal.ZERO) != 0 && basePrice.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valuation = basePrice.multiply(position);
            valuation = valuation.divide(basePriceUnitValue, 0, RoundingMode.DOWN);
            detail.setValuation(valuation.toPlainString());
        } else {
            detail.setValuation("-");
        }
        
        //評価損益
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            if (StringUtils.equals(detail.getValuation(), "-")
                    || new BigDecimal(detail.getValuation()).compareTo(BigDecimal.ZERO) < 0) {
                detail.setValuationProfitAndLoss("-");
            } else {
                BigDecimal valuation = new BigDecimal(detail.getValuation());
                BigDecimal unitVolume = new BigDecimal(detail.getUnitVolume());
                BigDecimal valuationProfitAndLoss = averagePrice.multiply(unitVolume);
                valuationProfitAndLoss = valuationProfitAndLoss.divide(basePriceUnitValue, 10, RoundingMode.DOWN);
                valuationProfitAndLoss = valuation.subtract(valuationProfitAndLoss);
                detail.setValuationProfitAndLoss(valuationProfitAndLoss.setScale(0, RoundingMode.DOWN).toPlainString());
            }
        } else {
            if (detail.getValuation().equals("-")
                    || new BigDecimal(detail.getValuation()).compareTo(BigDecimal.ZERO) < 0) {
                detail.setValuationProfitAndLoss("-");
            } else {
                BigDecimal valuation = new BigDecimal(detail.getValuation());
                BigDecimal valuationProfitAndLoss = acqPrice.multiply(position);
                valuationProfitAndLoss = valuationProfitAndLoss.divide(basePriceUnitValue, 10, RoundingMode.DOWN);
                valuationProfitAndLoss = valuation.subtract(valuationProfitAndLoss);
                detail.setValuationProfitAndLoss(valuationProfitAndLoss.setScale(0, RoundingMode.DOWN).toPlainString());
            }
        }
        
        //保護／代用区分
        if (StringUtils.equals(api001ListRes.getDepositCode3(), "1")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("信用代用");
        } else if (StringUtils.equals(api001ListRes.getDepositCode3(), "0")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else {
            detail.setProtectionSubstitutionClass("-");
        }
        
        //入庫理由
        if (StringUtils.equals(api001ListRes.getDpRoute2(), "0")) {
            detail.setStorageReason("買付");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "1")) {
            detail.setStorageReason("預り");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "2")) {
            detail.setStorageReason("振替");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "3")) {
            detail.setStorageReason("扱店変更");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "4")) {
            detail.setStorageReason("募集");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "5")) {
            detail.setStorageReason("現引");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "6")) {
            detail.setStorageReason("一部売却残");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "7")) {
            detail.setStorageReason("売却取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "9")) {
            detail.setStorageReason("出庫取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "B")) {
            detail.setStorageReason("特別償還");
        } else if (api001ListRes.getDpRoute2() == null || StringUtils.equals(api001ListRes.getDpRoute2(), "")
                || StringUtils.equals(api001ListRes.getDpRoute2(), "<BR>")) {
            detail.setStorageReason("-");
        } else {
            detail.setStorageReason(api001ListRes.getDpRoute2());
        }
        
        return detail;
    }
    
    /** 
     * 投資信託金額 預り明細情報の作成
    
     * @param api001ResSub API001レスポンス.明細部
     * @param basePrice SQL004レスポンス.基準価格
     * @param basePriceUnit SQL001レスポンス.基準価格単位
     * @param hitokuteiAzukariKbn API001リクエスト.非特定預り区分
     * @param isSql001ExistsData SQL001件数が1件以上かどうかフラグ
     * @return 投資信託金額 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList createMutualFundAmountDeposit(
            AccountDetWebData api001ListRes, BigDecimal basePrice, String basePriceUnit, String hitokuteiAzukariKbn, Boolean isSql001ExistsData) {
        
        IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList detail = new IfaDepositBalanceDetailResponseDtoMutualFundAmountDepositList();
        
        BigDecimal acPositiont1 = new BigDecimal(api001ListRes.getAcPositiont1());
        BigDecimal quantityModValue = new BigDecimal(api001ListRes.getQuantityModValue());
        BigDecimal averagePrice = new BigDecimal(api001ListRes.getAveragePrice());
        BigDecimal acqPrice = new BigDecimal(api001ListRes.getAcqPrice());
        BigDecimal priceModValue = new BigDecimal(api001ListRes.getPriceModValue());
        BigDecimal position = new BigDecimal(api001ListRes.getPosition());
        BigDecimal basePriceUnitValue = new BigDecimal(basePriceUnit);
        
        //保有口数
        String subCode2 = api001ListRes.getSubCode2();
        if (StringUtils.equals(api001ListRes.getSecId(), "S")
                && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")
                && !StringUtils.equals(subCode2.substring(1, 1), "ﾜ")) {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 10, RoundingMode.DOWN);
            unitVolume = unitVolume.divide(new BigDecimal("1000"));
            detail.setUnitVolume(unitVolume.setScale(0, RoundingMode.DOWN).toPlainString());
        } else if (StringUtils.equals(api001ListRes.getSecId(), "S")
                && StringUtils.equals(api001ListRes.getDomesticFgnId(), "1")
                && ((StringUtils.equals(api001ListRes.getSecType1(), "0")
                        && StringUtils.equals(api001ListRes.getSecType2(), "0"))
                        || (StringUtils.equals(api001ListRes.getSecType1(), "5")
                                && StringUtils.equals(api001ListRes.getSecType2(), "1"))
                        || (StringUtils.equals(api001ListRes.getSecType1(), "5")
                                && StringUtils.equals(api001ListRes.getSecType2(), "2")))) {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 10, RoundingMode.DOWN);
            unitVolume = unitVolume.divide(new BigDecimal("1000"));
            detail.setUnitVolume(unitVolume.setScale(0, RoundingMode.DOWN).toPlainString());
        } else {
            BigDecimal unitVolume = acPositiont1.divide(quantityModValue, 0, RoundingMode.DOWN);
            detail.setUnitVolume(unitVolume.toPlainString());
        }
        
        //取得単価/参考単価
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            detail.setGetPriceReferencePrice(averagePrice.setScale(2, RoundingMode.DOWN).toPlainString());
        } else {
            if (StringUtils.equals(api001ListRes.getSecId(), "S")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                BigDecimal getPriceReferencePrice = acqPrice.multiply(new BigDecimal("100"));
                getPriceReferencePrice = getPriceReferencePrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "S")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "1")
                    && !(StringUtils.equals(api001ListRes.getSecType1(), "2")
                            && StringUtils.equals(api001ListRes.getSecType2(), "1"))
                    && !(StringUtils.equals(api001ListRes.getSecType1(), "3")
                            && StringUtils.equals(api001ListRes.getSecType2(), "0"))) {
                BigDecimal getPriceReferencePrice = acqPrice.multiply(new BigDecimal("100"));
                getPriceReferencePrice = getPriceReferencePrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "T")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                detail.setGetPriceReferencePrice(api001ListRes.getAcqPrice());
            } else if (StringUtils.equals(api001ListRes.getSecId(), "Y")
                    && StringUtils.equals(api001ListRes.getDomesticFgnId(), "0")) {
                detail.setGetPriceReferencePrice(api001ListRes.getAcqPrice());
            } else {
                BigDecimal getPriceReferencePrice = acqPrice.divide(priceModValue, 2, RoundingMode.DOWN);
                detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
            }
        }
        
        //現在値
        detail.setCurrentPrice(basePrice.toString());
        
        //基準価格単位
        String depositDetailPriceUnitText = "(-)";
        if (isSql001ExistsData) {
            depositDetailPriceUnitText = "(" + basePriceUnit + "口当たり" + ")";
        }
        
        detail.setDepositDetailPriceUnit(depositDetailPriceUnitText);

        //取得金額/参考金額
        if (basePriceUnitValue.compareTo(BigDecimal.ZERO) != 0 ) {
            if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                    || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                BigDecimal unitVolume = new BigDecimal(detail.getUnitVolume());
                BigDecimal acquireAmountReferenceAmount = averagePrice.multiply(unitVolume);
                acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(basePriceUnitValue, 3, RoundingMode.DOWN);
                detail.setAcquireAmountReferenceAmount(acquireAmountReferenceAmount.toPlainString());

            } else {
                BigDecimal acquireAmountReferenceAmount = acqPrice.multiply(position);
                acquireAmountReferenceAmount = acquireAmountReferenceAmount.divide(basePriceUnitValue, 3, RoundingMode.DOWN);
                detail.setAcquireAmountReferenceAmount(acquireAmountReferenceAmount.toPlainString());

            }
        } else {
            detail.setAcquireAmountReferenceAmount("-");
        }
        
        //評価額
        // SQL004.基準価額 × API001.残高数量 ÷ SQL001.基準価額単位
        // ※計算結果の小数点以下切り捨て
        //
        //※計算結果の小数点以下切捨て
        //※SQL001が0件、またはSQL001.基準価額単位がゼロ、またはSQL004が0件、またはSQL011.基準価額がスペース、またはSQL004.基準価額がゼロ以下の場合、'-'(半角ハイフン)を設定      
        //SQL004.基準価額は、SQL004が0件、またはSQL004.基準価額がスペースの場合、0として受け渡される
        if (basePriceUnitValue.compareTo(BigDecimal.ZERO) != 0 && basePrice.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal valuation = basePrice.multiply(position);
            valuation = valuation.divide(basePriceUnitValue, 0, RoundingMode.DOWN);
            detail.setValuation(valuation.toPlainString());
        } else {
            detail.setValuation("-");
        }
        
        //評価損益
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.NISA_4.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.JRNISA_7.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
            if (StringUtils.equals(detail.getValuation(), "-")
                    || new BigDecimal(detail.getValuation()).compareTo(BigDecimal.ZERO) < 0) {
                detail.setValuationProfitAndLoss("-");
            } else {
                BigDecimal valuation = new BigDecimal(detail.getValuation());
                BigDecimal unitVolume = new BigDecimal(detail.getUnitVolume());
                BigDecimal valuationProfitAndLoss = averagePrice.multiply(unitVolume);
                valuationProfitAndLoss = valuationProfitAndLoss.divide(basePriceUnitValue, 10, RoundingMode.DOWN);
                valuationProfitAndLoss = valuation.subtract(valuationProfitAndLoss);
                detail.setValuationProfitAndLoss(valuationProfitAndLoss.setScale(0, RoundingMode.DOWN).toPlainString());
            }
        } else {
            if (StringUtils.equals(detail.getValuation(), "-")
                    || new BigDecimal(detail.getValuation()).compareTo(BigDecimal.ZERO) < 0) {
                detail.setValuationProfitAndLoss("-");
            } else {
                BigDecimal valuation = new BigDecimal(detail.getValuation());
                BigDecimal valuationProfitAndLoss = acqPrice.multiply(position);
                valuationProfitAndLoss = valuationProfitAndLoss.divide(basePriceUnitValue, 10, RoundingMode.DOWN);
                valuationProfitAndLoss = valuation.subtract(valuationProfitAndLoss);
                detail.setValuationProfitAndLoss(valuationProfitAndLoss.setScale(0, RoundingMode.DOWN).toPlainString());
            }
        }
        
        //保護／代用区分
        if (StringUtils.equals(api001ListRes.getDepositCode3(), "1")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("信用代用");
        } else if (StringUtils.equals(api001ListRes.getDepositCode3(), "0")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else {
            detail.setProtectionSubstitutionClass("-");
        }
        
        return detail;
    }
    
    /** 
     * 国内債券 預り明細情報の作成
    
     * @param api001ResSub API001レスポンス.明細部
     * @param hitokuteiAzukariKbn API001リクエスト.非特定預り区分
     * @return 国内債券 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList createDomesticClaimDeposit(
            AccountDetWebData api001ListRes, String hitokuteiAzukariKbn) {
        
        IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList detail = new IfaDepositBalanceDetailResponseDtoDomesticClaimDepositList();
        
        BigDecimal quantityModValue = new BigDecimal(api001ListRes.getQuantityModValue());
        BigDecimal averagePrice = new BigDecimal(api001ListRes.getAveragePrice());
        BigDecimal acqPrice = new BigDecimal(api001ListRes.getAcqPrice());
        BigDecimal priceModValue = new BigDecimal(api001ListRes.getPriceModValue());
        BigDecimal rateModValue = new BigDecimal(api001ListRes.getRateModValue());
        BigDecimal position = new BigDecimal(api001ListRes.getPosition());
        BigDecimal standardRate = new BigDecimal(api001ListRes.getStandardRate());
        
        //保有額面
        if (StringUtils.equals(api001ListRes.getQuantityModValue(), "0")
                || api001ListRes.getQuantityModValue() == null) {
            detail.setHoldingFaceValue("-");
        } else {
            BigDecimal holdingFaceValue = position.divide(quantityModValue, 0, RoundingMode.DOWN);
            detail.setHoldingFaceValue(holdingFaceValue.toPlainString());
        }
        
        //利率
        detail.setCompoundInterest(api001ListRes.getRateInterest());
        
        //償還日
        detail.setRedemptionDate(api001ListRes.getRedemptionDate());
        
        //利払日
        String lineSeparator = System.lineSeparator();
        //①API001.明細部.利払区分＝0(割引債)の場合、「-」
        if (StringUtils.equals(api001ListRes.getCouponPayId(), "0")) {
            detail.setInterestPaymentDate("-");
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "1")
                && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
            detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-" + "末日");
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "1")) {
            detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                    + api001ListRes.getCouponPayDate1().substring(2, 4));
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "2")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                api001ListRes.getCouponPayDate2().substring(2, 4))
                && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-" + "末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4));
            } else if (!StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate2().substring(2, 4));
            }
        } else if (api001ListRes.getCouponPayId().equals("3")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                api001ListRes.getCouponPayDate2().substring(2, 4))
                && StringUtils.equals(api001ListRes.getCouponPayDate3().substring(2, 4),
                        api001ListRes.getCouponPayDate4().substring(2, 4))
                && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                        api001ListRes.getCouponPayDate3().substring(2, 4))
                && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-" + "末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate3().substring(2, 4),
                            api001ListRes.getCouponPayDate4().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                            api001ListRes.getCouponPayDate3().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4));
            } else {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate2().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate3().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate4().substring(2, 4));
            }
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "4")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate("毎月末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate4().substring(2, 4), "M ")
                    || StringUtils.equals(api001ListRes.getCouponPayDate4().substring(2, 4), " M")) {
                detail.setInterestPaymentDate("毎月" + api001ListRes.getCouponPayDate1().substring(2, 4).trim() + "日");
            }
            detail.setInterestPaymentDate("毎月" + api001ListRes.getCouponPayDate1().substring(2, 4) + "日");
        }
            
        //参考為替
        detail.setExchangeRate("-");
        
        //取得単価/参考単価
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
            || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
            BigDecimal getPriceReferencePrice = averagePrice.multiply(new BigDecimal("100"));
            detail.setGetPriceReferencePrice(getPriceReferencePrice.setScale(2, RoundingMode.DOWN).toPlainString());
        } else {
            BigDecimal getPriceReferencePrice = acqPrice.multiply(new BigDecimal("100"));
            getPriceReferencePrice = getPriceReferencePrice.divide(priceModValue, 2, RoundingMode.DOWN);
            detail.setGetPriceReferencePrice(getPriceReferencePrice.toPlainString());
        }
        
        //約定為替
        detail.setContractExchange("-");
        
        //約定金額
        BigDecimal holdingFaceValue = new BigDecimal(detail.getHoldingFaceValue());
        BigDecimal getPriceReferencePrice = new BigDecimal(detail.getGetPriceReferencePrice());
        BigDecimal contractAmount = holdingFaceValue.multiply(getPriceReferencePrice);
        contractAmount = contractAmount.divide(new BigDecimal("100"));
        detail.setContractAmount(contractAmount.setScale(0, RoundingMode.DOWN).toPlainString());
        
        //預り年月日
        detail.setDepositDate(api001ListRes.getDepositDate());
        
        //評価額
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
        || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
            if (StringUtils.equals(api001ListRes.getPriceModValue(), "0") || api001ListRes.getPriceModValue() == null
                    || StringUtils.equals(api001ListRes.getQuantityModValue(), "0")
                    || api001ListRes.getQuantityModValue() == null
                    || StringUtils.equals(api001ListRes.getAveragePrice(), "0")) {
                detail.setValuation("-");
            } else {
                BigDecimal valuation = position.divide(quantityModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(averagePrice);
                valuation = valuation.multiply(standardRate);
                valuation = valuation.divide(rateModValue, 0, RoundingMode.DOWN);
                detail.setValuation(valuation.toPlainString());
            }
        } else {
            if (StringUtils.equals(api001ListRes.getPriceModValue(), "0") || api001ListRes.getPriceModValue() == null
                    || StringUtils.equals(api001ListRes.getQuantityModValue(), "0")
                    || api001ListRes.getQuantityModValue() == null
                    || StringUtils.equals(api001ListRes.getAveragePrice(), "0")) {
                detail.setValuation("-");
            } else {
                BigDecimal valuation = position.divide(quantityModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(acqPrice);
                valuation = valuation.divide(priceModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(standardRate);
                valuation = valuation.divide(rateModValue, 0, RoundingMode.DOWN);
                detail.setValuation(valuation.toPlainString());
            }
        }
        
        //保護／代用区分
        if (StringUtils.equals(api001ListRes.getDepositCode3(), "1")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("信用代用");
        } else if (StringUtils.equals(api001ListRes.getDepositCode3(), "0")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else {
            detail.setProtectionSubstitutionClass("-");
        }
        
        //入庫理由
        if (StringUtils.equals(api001ListRes.getDpRoute2(), "0")) {
            detail.setStorageReason("買付");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "1")) {
            detail.setStorageReason("預り");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "2")) {
            detail.setStorageReason("振替");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "3")) {
            detail.setStorageReason("扱店変更");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "4")) {
            detail.setStorageReason("募集");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "5")) {
            detail.setStorageReason("現引");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "6")) {
            detail.setStorageReason("一部売却残");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "7")) {
            detail.setStorageReason("売却取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "9")) {
            detail.setStorageReason("出庫取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "B")) {
            detail.setStorageReason("特別償還");
        } else if (api001ListRes.getDpRoute2() == null || StringUtils.equals(api001ListRes.getDpRoute2(), "")
                || StringUtils.equals(api001ListRes.getDpRoute2(), "<BR>")) {
            detail.setStorageReason("-");
        } else {
            detail.setStorageReason(api001ListRes.getDpRoute2());
        }
        
        return detail;
    }
    
    /** 
     * 外国債券 預り明細情報の作成
    
     * @param api001ResSub API001レスポンス.明細部
     * @param hitokuteiAzukariKbn API001リクエスト.非特定預り区分
     * @return 外国債券 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoForeignClaimDepositList createForeignClaimDeposit(
            AccountDetWebData api001ListRes, String hitokuteiAzukariKbn) {
        
        IfaDepositBalanceDetailResponseDtoForeignClaimDepositList detail = new IfaDepositBalanceDetailResponseDtoForeignClaimDepositList();
        
        BigDecimal quantityModValue = new BigDecimal(api001ListRes.getQuantityModValue());
        BigDecimal averagePrice = new BigDecimal(api001ListRes.getAveragePrice());
        BigDecimal priceModValue = new BigDecimal(api001ListRes.getPriceModValue());
        BigDecimal rateModValue = new BigDecimal(api001ListRes.getRateModValue());
        BigDecimal position = new BigDecimal(api001ListRes.getPosition());
        BigDecimal standardRate = new BigDecimal(api001ListRes.getStandardRate());
        BigDecimal rate = new BigDecimal(api001ListRes.getRate());
        BigDecimal acqPrice = new BigDecimal(api001ListRes.getAcqPrice());

        //保有額面
        if (StringUtils.equals(api001ListRes.getQuantityModValue(), "0")
                || api001ListRes.getQuantityModValue() == null) {
            detail.setHoldingFaceValue("-");
        } else {
            BigDecimal holdingFaceValue = position.divide(quantityModValue, 0, RoundingMode.DOWN);
            detail.setHoldingFaceValue(holdingFaceValue.toPlainString() + " " + api001ListRes.getCcyCode());

        }
        
        //利率
        detail.setCompoundInterest(api001ListRes.getRateInterest());
        
        //償還日
        detail.setRedemptionDate(api001ListRes.getRedemptionDate());
        
        //利払日
        String lineSeparator = System.lineSeparator();
        //①API001.明細部.利払区分＝0(割引債)の場合、「-」
        if (StringUtils.equals(api001ListRes.getCouponPayId(), "0")) {
            detail.setInterestPaymentDate("-");
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "1")
                && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
            detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-" + "末日");
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "1")) {
            detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                    + api001ListRes.getCouponPayDate1().substring(2, 4));
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "2")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-" + "末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4));
            } else if (!StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate2().substring(2, 4));
            }
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "3")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate3().substring(2, 4),
                            api001ListRes.getCouponPayDate4().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                            api001ListRes.getCouponPayDate3().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-" + "末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                    api001ListRes.getCouponPayDate2().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate3().substring(2, 4),
                            api001ListRes.getCouponPayDate4().substring(2, 4))
                    && StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4),
                            api001ListRes.getCouponPayDate3().substring(2, 4))) {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "/"
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4));
            } else {
                detail.setInterestPaymentDate(api001ListRes.getCouponPayDate1().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate1().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate2().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate2().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate3().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate3().substring(2, 4) + lineSeparator
                        + api001ListRes.getCouponPayDate4().substring(0, 2) + "-"
                        + api001ListRes.getCouponPayDate4().substring(2, 4));
            }
        } else if (StringUtils.equals(api001ListRes.getCouponPayId(), "4")) {
            if (StringUtils.equals(api001ListRes.getCouponPayDate1().substring(2, 4), "99")) {
                detail.setInterestPaymentDate("毎月末日");
            } else if (StringUtils.equals(api001ListRes.getCouponPayDate4().substring(2, 4), "M ")
                    || StringUtils.equals(api001ListRes.getCouponPayDate4().substring(2, 4), " M")) {
                detail.setInterestPaymentDate("毎月" + api001ListRes.getCouponPayDate1().substring(2, 4).trim() + "日");
            }
            detail.setInterestPaymentDate("毎月" + api001ListRes.getCouponPayDate1().substring(2, 4) + "日");
        }
        
        //参考為替
        if (StringUtils.equals(api001ListRes.getCcyCode(), "JPY")) {
            detail.setExchangeRate("-");
        } else if (StringUtils.equals(api001ListRes.getCcyCode(), "INR")) {
            BigDecimal exchangeRate = standardRate.divide(rateModValue, 4, RoundingMode.DOWN);
            detail.setExchangeRate(
                    exchangeRate.toPlainString() + "円/" + api001ListRes.getCcyCode());
        } else if (StringUtils.equals(api001ListRes.getCcyCode(), "IDR")) {
            BigDecimal exchangeRate = standardRate.divide(rateModValue, 6, RoundingMode.DOWN);
            detail.setExchangeRate(
                    exchangeRate.toPlainString() + "円/" + api001ListRes.getCcyCode());
        } else if (!StringUtils.equals(api001ListRes.getCcyCode(), "JPY")
                || !StringUtils.equals(api001ListRes.getCcyCode(), "INR")
                || !StringUtils.equals(api001ListRes.getCcyCode(), "IDR")) {
            BigDecimal exchangeRate = standardRate.divide(rateModValue, 2, RoundingMode.DOWN);
            detail.setExchangeRate(
                    exchangeRate.toPlainString() + "円/" + api001ListRes.getCcyCode());
        }
        
        //買付単価
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
        || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
            BigDecimal unitPrice = averagePrice.multiply(new BigDecimal("100"));
            detail.setUnitPrice(
                    unitPrice.setScale(2, RoundingMode.DOWN).toPlainString() + " " + api001ListRes.getCcyCode());
        } else {
            BigDecimal unitPrice = acqPrice.multiply(new BigDecimal("100"));
            unitPrice = unitPrice.divide(priceModValue, 2, RoundingMode.DOWN);
            detail.setUnitPrice(
                    unitPrice.toPlainString() + " " + api001ListRes.getCcyCode());
        }
        
        //約定為替
        if (StringUtils.equals(api001ListRes.getCcyCode(), "JPY")) {
            detail.setContractExchange("-");
        } else if (StringUtils.equals(api001ListRes.getCcyCode(), "INR")) {
            BigDecimal contractExchange = rate.divide(rateModValue, 4, RoundingMode.DOWN);
            detail.setContractExchange(contractExchange.toPlainString() + "円/"
                    + api001ListRes.getCcyCode());
        } else if (StringUtils.equals(api001ListRes.getCcyCode(), "IDR")) {
            BigDecimal contractExchange = rate.divide(rateModValue, 6, RoundingMode.DOWN);
            detail.setContractExchange(contractExchange.toPlainString() + "円/"
                    + api001ListRes.getCcyCode());
        } else if (!StringUtils.equals(api001ListRes.getCcyCode(), "JPY")
                || !StringUtils.equals(api001ListRes.getCcyCode(), "INR")
                || !StringUtils.equals(api001ListRes.getCcyCode(), "IDR")) {
            BigDecimal contractExchange = rate.divide(rateModValue, 2, RoundingMode.DOWN);
            detail.setContractExchange(contractExchange.toPlainString() + "円/"
                    + api001ListRes.getCcyCode());
        }
        
        //預り年月
        detail.setDepositDate(api001ListRes.getDepositDate());

        //約定金額
        //取得単価(※2)を求める 
        //API001.明細部.取得単価×100÷API001.明細部.単価修正値
        BigDecimal acqPriceTmpValue = acqPrice.multiply(new BigDecimal("100"));
        acqPriceTmpValue = acqPriceTmpValue.divide(priceModValue, 3, RoundingMode.DOWN);
        
        //取得為替(※3)を求める
        //API001.明細部.取得レート÷API001.明細部.為替修正値
        BigDecimal acqRateTmpValue = new BigDecimal("0");
        if (StringUtils.equals(api001ListRes.getCcyCode(), "INR")) {
            acqRateTmpValue = rate.divide(rateModValue, 4, RoundingMode.DOWN);
        } else if (StringUtils.equals(api001ListRes.getCcyCode(), "IDR")) {
            acqRateTmpValue = rate.divide(rateModValue, 6, RoundingMode.DOWN);
        } else {
            acqRateTmpValue = rate.divide(rateModValue, 3, RoundingMode.DOWN);
        }
        
        //保有額面(※1)を求める
        //API001.明細部.残高数量÷API001.明細部.数量修正値
        BigDecimal holdingFaceValue = position.divide(quantityModValue, 0, RoundingMode.DOWN);
        
        //約定金額を求める 保有額面(※1)×取得単価(※2)×取得為替(※3)÷100
        BigDecimal contractAmount = holdingFaceValue.multiply(acqPriceTmpValue);
        contractAmount = contractAmount.multiply(acqRateTmpValue);
        contractAmount = contractAmount.divide(new BigDecimal("100"));
        detail.setContractAmount(contractAmount.setScale(0, RoundingMode.DOWN).toPlainString() + " 円");
        
        //評価額(円)
        if (StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
        || StringUtils.equals(hitokuteiAzukariKbn, HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
            if (StringUtils.equals(api001ListRes.getQuantityModValue(), "0")
                    || api001ListRes.getQuantityModValue() == null
                    || StringUtils.equals(api001ListRes.getAveragePrice(), "0")
                    || api001ListRes.getAveragePrice() == null
                    || StringUtils.equals(api001ListRes.getStandardRate(), "0")
                    || api001ListRes.getStandardRate() == null) {
                detail.setValuationJpAmount("-");
            } else {
                BigDecimal valuation = position.divide(quantityModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(averagePrice);
                valuation = valuation.multiply(standardRate);
                valuation = valuation.divide(rateModValue, 0, RoundingMode.DOWN);
                detail.setValuationJpAmount(valuation.toPlainString());
            }
        } else {
            if (StringUtils.equals(api001ListRes.getPriceModValue(), "0") || api001ListRes.getPriceModValue() == null
                    || StringUtils.equals(api001ListRes.getAcqPrice(), "0") || api001ListRes.getAcqPrice() == null
                    || StringUtils.equals(api001ListRes.getStandardRate(), "0")
                    || api001ListRes.getStandardRate() == null) {
                detail.setValuationJpAmount("-");
            } else {
                BigDecimal valuation = position.divide(quantityModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(acqPrice);
                valuation = valuation.divide(priceModValue, 20, RoundingMode.DOWN);
                valuation = valuation.multiply(standardRate);
                valuation = valuation.divide(rateModValue, 0, RoundingMode.DOWN);
                detail.setValuationJpAmount(valuation.toPlainString());
            }
        }
        
        //保護／代用区分
        if (StringUtils.equals(api001ListRes.getDepositCode3(), "1")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("信用代用");
        } else if (StringUtils.equals(api001ListRes.getDepositCode3(), "0")
                && StringUtils.equals(api001ListRes.getDepositCode4(), "0")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else {
            detail.setProtectionSubstitutionClass("-");
        }
        
        //入庫理由
        if (StringUtils.equals(api001ListRes.getDpRoute2(), "0")) {
            detail.setStorageReason("買付");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "1")) {
            detail.setStorageReason("預り");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "2")) {
            detail.setStorageReason("振替");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "3")) {
            detail.setStorageReason("扱店変更");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "4")) {
            detail.setStorageReason("募集");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "5")) {
            detail.setStorageReason("現引");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "6")) {
            detail.setStorageReason("一部売却残");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "7")) {
            detail.setStorageReason("売却取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "9")) {
            detail.setStorageReason("出庫取消");
        } else if (StringUtils.equals(api001ListRes.getDpRoute2(), "B")) {
            detail.setStorageReason("特別償還");
        } else if (api001ListRes.getDpRoute2() == null || StringUtils.equals(api001ListRes.getDpRoute2(), "")
                || StringUtils.equals(api001ListRes.getDpRoute2(), "<BR>")) {
            detail.setStorageReason("-");
        } else {
            detail.setStorageReason(api001ListRes.getDpRoute2());
        }
        
        //通貨コード
        detail.setCurrencyCode(api001ListRes.getCcyCode());
        
        return detail;
    }
    
    /** 
     * 外国債券（外貨建） 預り明細情報の作成
    
     * @param api003Res API003レスポンス
     * @return 外国債券（外貨建） 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList createForeignClaimForeignCurrencyDepositList(
            GetSecuritiesBalanceResp api003Res,
            DataList<IfaDepositBalanceDetailSql003ResponseModel> sql003Res
    ) {
        
        IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList detail = new IfaDepositBalanceDetailResponseDtoForeignClaimForeignCurrencyDepositList();
        
        // 通貨コード
        String currencyCode = api003Res.getCurrencyCode();

        // 保有額面
        detail.setHoldingFaceValue(api003Res.getSecuritiesQuantity() + " " + currencyCode);

        if (sql003Res.size() == 1) {
            // 利率
            detail.setCompoundInterest(sql003Res.get(0).getCompoundInterest());

            // 償還日
            detail.setRedemptionDate(sql003Res.get(0).getRedemptionDate());

            // 利払日
            String interestPaymentDate = ForeignClaimForeignInterestPaymentDateFormatter
                    .formatForeignClaimForeignInterestPaymentDate(sql003Res.get(0));
            detail.setInterestPaymentDate(interestPaymentDate);

        } else {
            // 利率
            detail.setCompoundInterest("-");

            // 償還日
            detail.setRedemptionDate("-");

            // 利払日
            detail.setInterestPaymentDate("-");

        }

        // 参考為替
        if (!ObjectUtils.isEmpty(api003Res.getEvaluationProfitLoss()) && api003Res.getEvaluationProfitLoss().getEvaluationExchangeRate() != null && !StringUtils.equals(api003Res.getEvaluationProfitLoss().getEvaluationExchangeRate(), "")) {
            BigDecimal exchangeRate = new BigDecimal(api003Res.getEvaluationProfitLoss().getEvaluationExchangeRate());
            if (StringUtils.equals(currencyCode, "INR")) {
                detail.setExchangeRate(exchangeRate.setScale(4, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            } else if (StringUtils.equals(currencyCode, "IDR")) {
                detail.setExchangeRate(exchangeRate.setScale(6, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            } else {
                detail.setExchangeRate(exchangeRate.setScale(2, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            }
        } else {
            detail.setExchangeRate("円/" + currencyCode);
        }

        // 買付単価
        detail.setUnitPrice(api003Res.getFrnAcquisitionPrice() + " " + currencyCode);

        // 約定為替
        if (api003Res.getAcquisitionExchangeRate() != null && !StringUtils.equals(api003Res.getAcquisitionExchangeRate(), "")) {
            BigDecimal contractExchange = new BigDecimal(api003Res.getAcquisitionExchangeRate());
            if (StringUtils.equals(currencyCode, "INR")) {
                detail.setContractExchange(contractExchange.setScale(4, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            } else if (StringUtils.equals(currencyCode, "IDR")) {
                detail.setContractExchange(contractExchange.setScale(6, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            } else {
                detail.setContractExchange(contractExchange.setScale(2, RoundingMode.DOWN).toPlainString() + "円/" + currencyCode);
            }
        } else {
            detail.setContractExchange("円/" + currencyCode);
        }


        // 約定金額
        detail.setContractAmount(api003Res.getFrnAcquisitionAmount() + " " + currencyCode);

        // 評価額（円）
        if (!ObjectUtils.isEmpty(api003Res.getEvaluationProfitLoss())) {
            detail.setValuationJpAmount(api003Res.getEvaluationProfitLoss().getEvaluationAmount());
        }

        //保護／代用区分
        if (StringUtils.equals(api003Res.getDepositType(), "PROTECTION_KEEPING")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else if (StringUtils.equals(api003Res.getDepositType(), "COLLATERAL_SECURITIES")) {
            detail.setProtectionSubstitutionClass("代用預り");
        } else if (StringUtils.equals(api003Res.getDepositType(), "MARGIN_CASH")) {
            detail.setProtectionSubstitutionClass("信用保証金");
        }

        return detail;
    }

    /** 
     * 外国株式 預り明細情報の作成
    
     * @param api003Res API003レスポンス
     * @return 外国株式 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoForeignStockDepositList createForeignStockDepositList(
            GetSecuritiesBalanceResp api003Res) {
        
        IfaDepositBalanceDetailResponseDtoForeignStockDepositList detail = new IfaDepositBalanceDetailResponseDtoForeignStockDepositList();
        
        //保有株数
        detail.setHoldingStock(api003Res.getSecuritiesQuantity());
        
        //取得単価
        detail.setOpenPrice(api003Res.getFrnAcquisitionPrice() + " " + api003Res.getCurrencyCode());
        
        //現在値
        if (api003Res.getStockPrice() != null) {
            detail.setCurrentPrice(api003Res.getStockPrice().getLastToPrevClose() + " " + api003Res.getCurrencyCode());
        }
        
        //取得金額
        detail.setGetAmount(api003Res.getFrnAcquisitionAmount() + " " + api003Res.getCurrencyCode());
        
        //評価額
        detail.setValuation(
                api003Res.getEvaluationProfitLoss().getFrnEvaluationAmount() + " " + api003Res.getCurrencyCode());
        
        //外貨建評価損益
        detail.setForeignProfitAndLoss(
                api003Res.getEvaluationProfitLoss().getFrnEvaluationProfitLoss() + " " + api003Res.getCurrencyCode());
        
        //評価為替レート
        detail.setFxValuationRate(
                api003Res.getEvaluationProfitLoss().getEvaluationExchangeRate() + "円/" + api003Res.getCurrencyCode());
        
        //評価額（円貨）
        detail.setValuationJpAmount(api003Res.getEvaluationProfitLoss().getEvaluationAmount());
        
        //評価損益（円貨）
        detail.setYenProfitLoss(api003Res.getEvaluationProfitLoss().getEvaluationProfitLoss());
        
        //保護／代用区分
        if (StringUtils.equals(api003Res.getDepositType(), "PROTECTION_KEEPING")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else if (StringUtils.equals(api003Res.getDepositType(), "COLLATERAL_SECURITIES")) {
            detail.setProtectionSubstitutionClass("代用預り");
        } else if (StringUtils.equals(api003Res.getDepositType(), "MARGIN_CASH")) {
            detail.setProtectionSubstitutionClass("信用保証金");
        }
        
        //通貨コード
        detail.setCurrencyCode(api003Res.getCurrencyCode());
        
        return detail;
    }
    
    /** 
     * 外国建MMF 預り明細情報の作成
    
     * @param api003Res API003レスポンス
     * @return 外国建MMF 預り明細情報
     * */
    private IfaDepositBalanceDetailResponseDtoForeignMmfDepositList createForeignMmfDeposit(
            GetSecuritiesBalanceResp api003Res) {
        
        IfaDepositBalanceDetailResponseDtoForeignMmfDepositList detail = new IfaDepositBalanceDetailResponseDtoForeignMmfDepositList();
        //保有口数
        detail.setUnitVolume(api003Res.getSecuritiesQuantity());
        
        //評価額（外貨）
        detail.setForeignValuation(
                api003Res.getEvaluationProfitLoss().getFrnEvaluationAmount() + " " + api003Res.getCurrencyCode());
        
        //評価為替レート
        detail.setFxValuationRate(
                api003Res.getEvaluationProfitLoss().getEvaluationExchangeRate() + "円/" + api003Res.getCurrencyCode());
        
        //評価額（円貨）
        detail.setValuation(api003Res.getEvaluationProfitLoss().getEvaluationAmount());
        
        //評価損益（円貨）
        detail.setYenProfitLoss(api003Res.getEvaluationProfitLoss().getEvaluationProfitLoss());
        
        //保護／代用区分
        if (api003Res.getDepositType().equals("PROTECTION_KEEPING")) {
            detail.setProtectionSubstitutionClass("保護預り");
        } else if (api003Res.getDepositType().equals("COLLATERAL_SECURITIES")) {
            detail.setProtectionSubstitutionClass("代用預り");
        } else if (api003Res.getDepositType().equals("MARGIN_CASH")) {
            detail.setProtectionSubstitutionClass("信用保証金");
        }
        
        //通貨コード
        detail.setCurrencyCode(api003Res.getCurrencyCode());
        
        return detail;
    }
    
    /**
     * 商品口座預り設定
    
     * @param commonDtoReq リクエスト
     * @param cc 顧客共通情報
     * @return commodityAccountDeposit 商品口座預り
     */
    private String commodityAccountDeposit(IfaDepositBalanceDetailCommonRequestDto commonDtoReq, CustomerCommon cc) {
        
        String commodityAccountDeposit = new String();
        //商品名が「国内株式」の場合
        if (StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_STOCK_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "株式（総合口座－特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "株式（総合口座－旧NISA預り）";
                    //リクエスト.非特定預り区分が「5(特定(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
                    commodityAccountDeposit = "株式（ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「6(一般(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)) {
                    commodityAccountDeposit = "株式（ジュニアNISA口座－一般預り）";
                    //リクエスト.非特定預り区分が「7(ジュニアNISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.JRNISA_7.key)) {
                    commodityAccountDeposit = "株式（ジュニアNISA口座－NISA預り）";
                    //リクエスト.非特定預り区分が「H(総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "株式（総合口座－NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「J'(継続管理勘定))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "株式（ジュニアNISA口座－NISA預り（継続管理勘定））";
                    //上記以外
                } else {
                    commodityAccountDeposit = "株式（総合口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "株式（特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "株式（旧NISA預り）";
                    //リクエスト.非特定預り区分が「H((総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "株式（NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「0」「4」「5」「6」「7」「H」「J」以外の場合
                } else if (!StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.NISA_4.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.JRNISA_7.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "株式（一般預り）";
                }
            }
            //商品名が「投資信託口数」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_LOT_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－旧NISA預り）";
                    //リクエスト.非特定預り区分が「5(特定(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
                    commodityAccountDeposit = "投資信託（口数/ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「6(一般(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)) {
                    commodityAccountDeposit = "投資信託（口数/ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「7(ジュニアNISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.JRNISA_7.key)) {
                    commodityAccountDeposit = "投資信託（口数/ジュニアNISA口座－NISA預り）";
                    //リクエスト.非特定預り区分が「8(つみたてNISA預り)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－旧つみたてNISA預り）";
                    //リクエスト.非特定預り区分が「H(総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「I(総合NISA(つみたて投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－NISA預り（つみたて投資枠））";
                    //リクエスト.非特定預り区分が「J'(継続管理勘定))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "投資信託（口数/ジュニアNISA口座－NISA預り（継続管理勘定））";
                    //上記以外
                } else {
                    commodityAccountDeposit = "投資信託（口数/総合口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "投資信託（口数/特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "投資信託（口数/旧NISA預り）";
                    //リクエスト.非特定預り区分が「8(つみたてNISA預り)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)) {
                    commodityAccountDeposit = "投資信託（口数/旧つみたてNISA預り）";
                    //リクエスト.非特定預り区分が「H(総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "投資信託（口数/NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「I(総合NISA(つみたて投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)) {
                    commodityAccountDeposit = "投資信託（口数/NISA預り（つみたて投資枠））";
                    //リクエスト.非特定預り区分が「0」「4」「5」「6」「7」「8」「H」「I」「J」以外の場合
                } else if (!StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.NISA_4.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.JRNISA_7.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "投資信託（口数/総合口座－一般預り）";
                }
            }
            //商品名が「投資信託金額」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), MUTUAL_FUND_AMOUNT_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "投資信託（金額/総合口座－特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "投資信託（金額/総合口座－旧NISA預り）";
                    //リクエスト.非特定預り区分が「5(特定(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
                    commodityAccountDeposit = "投資信託（金額/ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「6(一般(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)) {
                    commodityAccountDeposit = "投資信託（金額/ジュニアNISA口座－一般預り）";
                    //リクエスト.非特定預り区分が「7(ジュニアNISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.JRNISA_7.key)) {
                    commodityAccountDeposit = "投資信託（金額/ジュニアNISA口座－NISA預り）";
                    //リクエスト.非特定預り区分が「8(つみたてNISA預り)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)) {
                    commodityAccountDeposit = "投資信託（金額/総合口座－旧つみたてNISA預り）";
                    //リクエスト.非特定預り区分が「H(総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "投資信託（金額/総合口座－NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「I(総合NISA(つみたて投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)) {
                    commodityAccountDeposit = "投資信託（金額/総合口座－NISA預り（つみたて投資枠））";
                    //リクエスト.非特定預り区分が「J'(継続管理勘定))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "投資信託（金額/ジュニアNISA口座－NISA預り（継続管理勘定））";
                    //上記以外
                } else {
                    commodityAccountDeposit = "投資信託（金額/総合口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "投資信託（金額/特定預り）";
                    //リクエスト.非特定預り区分が「4（NISA預り）」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.NISA_4.key)) {
                    commodityAccountDeposit = "投資信託（金額/旧NISA預り）";
                    //リクエスト.非特定預り区分が「8(つみたてNISA預り)」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)) {
                    commodityAccountDeposit = "投資信託（金額/旧つみたてNISA預り）";
                    //リクエスト.非特定預り区分が「H(総合NISA(成長投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALNISA_H.key)) {
                    commodityAccountDeposit = "投資信託（金額/NISA預り（成長投資枠））";
                    //リクエスト.非特定預り区分が「I(総合NISA(つみたて投資枠))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)) {
                    commodityAccountDeposit = "投資信託（金額/NISA預り（つみたて投資枠））";
                    //リクエスト.非特定預り区分が「0」「4」「5」「6」「7」「8」「H」「I」「J」以外の場合
                } else if (!StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.NISA_4.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.JRNISA_7.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.ACCUMULATIONNISA_8.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.TOTALNISA_H.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.TOTALACCUMULATIONNISA_I.key)
                        && !StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                                HitokuteiAzukariKbnEnum.CONTINUATIONMANAGE_J.key)) {
                    commodityAccountDeposit = "投資信託（金額/一般預り）";
                }
            }
            //商品名が「国内債券」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), DOMESTIC_CLAIM_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "国内債券（総合口座－特定預り）";
                    //リクエスト.非特定預り区分が「5(特定(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
                    commodityAccountDeposit = "国内債券（ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「6(一般(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)) {
                    commodityAccountDeposit = "国内債券（ジュニアNISA口座－一般預り）";
                    //上記以外
                } else {
                    commodityAccountDeposit = "国内債券（総合口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "国内債券（特定預り）";
                    //上記以外の場合
                } else {
                    commodityAccountDeposit = "国内債券（一般預り）";
                }
            }
            //商品名が「外国債券」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "外国債券（総合口座－特定預り）";
                    //リクエスト.非特定預り区分が「5(特定(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXSPECIFIC_5.key)) {
                    commodityAccountDeposit = "外国債券（ジュニアNISA口座－特定預り）";
                    //リクエスト.非特定預り区分が「6(一般(特例))」の場合
                } else if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.EXGENERALLY_6.key)) {
                    commodityAccountDeposit = "外国債券（ジュニアNISA口座－一般預り）";
                    //上記以外
                } else {
                    commodityAccountDeposit = "外国債券（総合口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.非特定預り区分が「0（特定預り）」の場合
                if (StringUtils.equals(commonDtoReq.getNonSpecificDepositCategory(),
                        HitokuteiAzukariKbnEnum.SPECIFIC_0.key)) {
                    commodityAccountDeposit = "外国債券（特定預り）";
                    //上記以外の場合
                } else {
                    commodityAccountDeposit = "外国債券（一般預り）";
                }
            }
            //商品名が「外国債券（外貨建）」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_CLAIM_FOREIGN_CURRENCY_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外国債券（総合口座－特定預り）";
                //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外国債券（総合口座－一般預り）";
                //リクエスト.預り区分が「JR_SPECIFIC(ジュニアNISA特定)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_SPECIFIC.key)) {
                    commodityAccountDeposit = "外国債券（ジュニアNISA口座－特定預り）";
                //リクエスト.預り区分が「JR_GENERAL(ジュニアNISA一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_GENERAL.key)) {
                    commodityAccountDeposit = "外国債券（ジュニアNISA口座－一般預り）";
                }
            //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外国債券（特定預り）";
                //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外国債券（一般預り）";
                }
            }
            //商品名が「外国株式」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_STOCK_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外国株式（総合口座－特定預り）";
                    //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外国株式（総合口座－一般預り）";
                    //リクエスト.預り区分が「GROWTH_INVESTMENT(成長投資枠)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GROWTH_INVESTMENT.key)) {
                    commodityAccountDeposit = "外国株式（総合口座－NISA預り（成長投資枠））";
                    //リクエスト.預り区分が「NISA(NISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.NISA.key)) {
                    commodityAccountDeposit = "外国株式（総合口座－旧NISA預り）";
                    //リクエスト.預り区分が「JR_SPECIFIC(ジュニアNISA特定)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_SPECIFIC.key)) {
                    commodityAccountDeposit = "外国株式（ジュニアNISA口座－特定預り）";
                    //リクエスト.預り区分が「JR_GENERAL(ジュニアNISA一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_GENERAL.key)) {
                    commodityAccountDeposit = "外国株式（ジュニアNISA口座－一般預り）";
                    //リクエスト.預り区分が「CONTINUOUS_MANAGEMENT(継続管理勘定)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(),
                        AzukariKbnEnum.CONTINUOUS_MANAGEMENT.key)) {
                    commodityAccountDeposit = "外国株式（ジュニアNISA口座－NISA預り（継続管理勘定））";
                    //リクエスト.預り区分が「JR_NISA(ジュニアNISA NISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_NISA.key)) {
                    commodityAccountDeposit = "外国株式（ジュニアNISA口座－NISA預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外国株式（特定預り）";
                    //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外国株式（一般預り）";
                    //リクエスト.預り区分が「GROWTH_INVESTMENT(成長投資枠)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GROWTH_INVESTMENT.key)) {
                    commodityAccountDeposit = "外国株式（NISA預り（成長投資枠））";
                    //リクエスト.預り区分が「NISA(NISA)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.NISA.key)) {
                    commodityAccountDeposit = "外国株式（旧NISA預り）";
                }
            }
            //商品名が「外貨建MMF」の場合
        } else if (StringUtils.equals(commonDtoReq.getProductName(), FOREIGN_MMF_DEPOSIT)) {
            //顧客共通情報.ジュニアNISA契約区分が「1」の場合
            if (StringUtils.equals(cc.getJrIsaContractType(), JR_NISA_KBN)) {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外貨建MMF（総合口座－特定預り）";
                    //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外貨建MMF（総合口座－一般預り）";
                    //リクエスト.預り区分が「JR_SPECIFIC(ジュニアNISA特定)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_SPECIFIC.key)) {
                    commodityAccountDeposit = "外貨建MMF（ジュニアNISA口座－特定預り）";
                    //リクエスト.預り区分が「JR_GENERAL(ジュニアNISA一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.JR_GENERAL.key)) {
                    commodityAccountDeposit = "外貨建MMF（ジュニアNISA口座－一般預り）";
                }
                //顧客共通情報.ジュニアNISA契約区分が「1」以外の場合
            } else {
                //リクエスト.預り区分が「SPECIFIC(特定)」の場合
                if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.SPECIFIC.key)) {
                    commodityAccountDeposit = "外貨建MMF（特定預り）";
                    //リクエスト.預り区分が「GENERAL(一般)」の場合
                } else if (StringUtils.equals(commonDtoReq.getDepositType(), AzukariKbnEnum.GENERAL.key)) {
                    commodityAccountDeposit = "外貨建MMF（一般預り）";
                }
            }
        }
        return commodityAccountDeposit;
    }
    
    /**
     * 国内株式における現在値の算出
    
     * @param currentPriceValue 現在値
     * @return currentPrice 現在値     
     */
    private BigDecimal calcWkCurrentPrice(BigDecimal currentPriceValue) {
        
        BigDecimal currentPrice = currentPriceValue.setScale(2, RoundingMode.DOWN);
        return currentPrice;
    }

    private static class ForeignClaimForeignInterestPaymentDateFormatter {

        static String formatForeignClaimForeignInterestPaymentDate(
                IfaDepositBalanceDetailSql003ResponseModel sql003Res
        ) {

            // SQL003.利払区分 = 割引債
            if (StringUtils.equals(
                    sql003Res.getInterestPaymentKbn(),
                    InterestPaymentKbnEnum.DISCOUNT.key
            )) {
                return "-";
            }

            // =====================================
            // SQL003.利払区分 = 年1/2/3/4回
            // =====================================
            if (
                    StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.ANNUAL.key)
                    || StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.SEMI_ANNUAL.key)
                    || StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUADRIMESTRALLY.key)
                    || StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUARTERLY.key)
            ) {
                // SQL003.月末表示区分 = 月末
                if (StringUtils.equals(
                        sql003Res.getInterestPaymentDateGetsumatsuKbn(),
                        INTEREST_PAYMENT_DATE_GETSUMATSU_KBN_GETSUMATSU
                )) {
                    // 対象月一覧
                    List<String> interestPaymentMonths
                            = ForeignClaimForeignInterestPaymentDateFormatter.getInterestPaymentMonths(sql003Res);

                    // 結果を返却
                    return String.format(
                            "%s-末日",
                            StringUtils.join(interestPaymentMonths, "/")
                    );
                }

                // SQL003.月末表示区分 != 月末
                // SQL003.利払日が全て一致
                if (ForeignClaimForeignInterestPaymentDateFormatter.isAllEqualInterestPaymentDays(sql003Res)) {
                    // 対象月一覧
                    List<String> interestPaymentMonths
                            = ForeignClaimForeignInterestPaymentDateFormatter.getInterestPaymentMonths(sql003Res);

                    // 結果を返却
                    return String.format(
                            "%s-%s",
                            StringUtils.join(interestPaymentMonths, "/"),
                            sql003Res.getInterestPaymentDay1()
                    );
                }

                // SQL003.月末表示区分 != 月末
                // SQL003.利払日が一致しないものが存在する
                List<String> interestPaymentDates
                        = ForeignClaimForeignInterestPaymentDateFormatter.getInterestPaymentDates(sql003Res);

                // 結果を返却
                return StringUtils.join(interestPaymentDates, "\n");

            }

            // =====================================
            // SQL003.利払区分 = 年6 / 12回
            // =====================================
            if (
                    StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.BIMONTHLY.key)
                    || StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.MONTHLY.key)
            ) {
                // 利払日の月部分
                String interestPaymentMonthStr = "";

                // 利払日が隔月の場合の月部分
                if (StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.BIMONTHLY.key)) {
                    int interestPaymentMonthInt = NumberUtils.toInt(sql003Res.getInterestPaymentMonth1());
                    if ((interestPaymentMonthInt % 2) == 0) {
                        interestPaymentMonthStr =  "偶数月";
                    } else {
                        interestPaymentMonthStr =  "奇数月";
                    }

                } else { // 利払日が毎月の場合の月部分
                    interestPaymentMonthStr = "毎月";
                }

                // 利払日の日部分
                String interestPaymentDay = "";
                if (StringUtils.equals(sql003Res.getInterestPaymentDateGetsumatsuKbn(),INTEREST_PAYMENT_DATE_GETSUMATSU_KBN_GETSUMATSU)) {
                    interestPaymentDay = "末日";

                } else {
                    interestPaymentDay = String.format(
                            "%s日",
                            StringUtils.strip(sql003Res.getInterestPaymentDay1(), " ")
                    );
                }

                return String.format("%s%s", interestPaymentMonthStr, interestPaymentDay);

            }

            // =====================================
            // その他の場合
            // =====================================

            return String.format(
                    "%s-%s",
                    sql003Res.getInterestPaymentMonth1(),
                    sql003Res.getInterestPaymentDay1()
            );

        }

        private static List<String> getInterestPaymentMonths(
                IfaDepositBalanceDetailSql003ResponseModel sql003Res
        ) {
            // SQL003.利払区分 = 年1回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.ANNUAL.key)
            ) {
                return Arrays.asList(
                        sql003Res.getInterestPaymentMonth1()
                );
            }

            // SQL003.利払区分 = 年2回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.SEMI_ANNUAL.key)
            ) {
                return Arrays.asList(
                        sql003Res.getInterestPaymentMonth1(),
                        sql003Res.getInterestPaymentMonth2()
                );
            }

            // SQL003.利払区分 = 年3回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUADRIMESTRALLY.key)
            ) {
                return Arrays.asList(
                        sql003Res.getInterestPaymentMonth1(),
                        sql003Res.getInterestPaymentMonth2(),
                        sql003Res.getInterestPaymentMonth3()
                );
            }

            // SQL003.利払区分 = 年4回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUARTERLY.key)
            ) {
                return Arrays.asList(
                        sql003Res.getInterestPaymentMonth1(),
                        sql003Res.getInterestPaymentMonth2(),
                        sql003Res.getInterestPaymentMonth3(),
                        sql003Res.getInterestPaymentMonth4()
                );
            }

            // 条件を満たさない場合(業務的に実行される可能性なし)
            return Arrays.asList();

        }

        private static List<String> getInterestPaymentDates(
                IfaDepositBalanceDetailSql003ResponseModel sql003Res
        ) {
            // SQL003.利払区分 = 年1回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.ANNUAL.key)
            ) {
                return Arrays.asList(
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth1(), sql003Res.getInterestPaymentDay1())
                );
            }

            // SQL003.利払区分 = 年2回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.SEMI_ANNUAL.key)
            ) {
                return Arrays.asList(
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth1(), sql003Res.getInterestPaymentDay1()),
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth2(), sql003Res.getInterestPaymentDay2())
                );
            }

            // SQL003.利払区分 = 年3回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUADRIMESTRALLY.key)
            ) {
                return Arrays.asList(
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth1(), sql003Res.getInterestPaymentDay1()),
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth2(), sql003Res.getInterestPaymentDay2()),
                        String.format("%s-%s", sql003Res.getInterestPaymentMonth3(), sql003Res.getInterestPaymentDay3())
                );
            }

            // SQL003.利払区分 = 年4回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUARTERLY.key)
            ) {
                return Arrays.asList(
                    String.format("%s-%s", sql003Res.getInterestPaymentMonth1(), sql003Res.getInterestPaymentDay1()),
                    String.format("%s-%s", sql003Res.getInterestPaymentMonth2(), sql003Res.getInterestPaymentDay2()),
                    String.format("%s-%s", sql003Res.getInterestPaymentMonth3(), sql003Res.getInterestPaymentDay3()),
                    String.format("%s-%s", sql003Res.getInterestPaymentMonth4(), sql003Res.getInterestPaymentDay4())
                );
            }

            // 条件を満たさない場合(業務的に実行される可能性なし)
            return Arrays.asList();

        }

        private static boolean isAllEqualInterestPaymentDays(
                IfaDepositBalanceDetailSql003ResponseModel sql003Res
        ) {
            // SQL003.利払区分 = 年1回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.ANNUAL.key)
            ) {
                return true;
            }

            // SQL003.利払区分 = 年2回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.SEMI_ANNUAL.key)
            ) {
                return StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay2());
            }

            // SQL003.利払区分 = 年3回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUADRIMESTRALLY.key)
            ) {
                return (
                        StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay2())
                        && StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay3())
                );
            }

            // SQL003.利払区分 = 年4回の場合
            if (
                StringUtils.equals(sql003Res.getInterestPaymentKbn(), InterestPaymentKbnEnum.QUARTERLY.key)
            ) {
                return (
                        StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay2())
                        && StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay3())
                        && StringUtils.equals(sql003Res.getInterestPaymentDay1(), sql003Res.getInterestPaymentDay4())
                );
            }

            // 条件を満たさない場合(業務的に実行される可能性なし)
            return false;

        }

    }

    /**
     * 投信信託における投信基準価格の取得
    
     * @param kyoukaiCd 協会コード
     * @return basePrice 投信基準価格   
     * @exception Exception 処理で例外が発生した場合
     */
    private BigDecimal getMutualFundBasePrice(String kyoukaiCd) throws Exception{
        
        //基準価額
        IfaDepositBalanceDetailSql004RequestModel selSql004Req = new IfaDepositBalanceDetailSql004RequestModel();
        selSql004Req.setKyoukaiCd(kyoukaiCd);
        DataList<IfaDepositBalanceDetailSql004ResponseModel> selSql004Res = dao
                .selectIfaDepositBalanceDetailSql004(selSql004Req);

        BigDecimal basePrice = (selSql004Res.size() > 0 
                && selSql004Res.get(0) != null 
                && !StringUtil.isNullOrEmpty(selSql004Res.get(0).getBasePrice()) 
                && !selSql004Res.get(0).getBasePrice().trim().isEmpty())
                ? new BigDecimal(selSql004Res.get(0).getBasePrice())
                : BigDecimal.ZERO;
        
        return basePrice; 
    }
}
