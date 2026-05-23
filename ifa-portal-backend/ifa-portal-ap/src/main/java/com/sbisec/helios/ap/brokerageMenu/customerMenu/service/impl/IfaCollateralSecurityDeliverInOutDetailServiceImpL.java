package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCollateralSecurityDeliverInOutDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCollateralSecurityDeliverInOutDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecRecDelDetail;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecRecDelInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecRecDelOutData;
//import jp.co.sbisec.pcenter.dto.yanap.QueryAccountSubSecRecDelOutVec;

/**
 * 画面ID：SUB0202_010204-02
 * 画面名：代用有価証券入出庫-個別詳細
 * アクションID：A001
 * アクション名：初期化
 *
 * @author SCSK
 */
@Component(value = "ifaCollateralSecurityDeliverInOutDetailService")
public class IfaCollateralSecurityDeliverInOutDetailServiceImpL
        implements IfaCollateralSecurityDeliverInOutDetailService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaCollateralSecurityDeliverInOutDetailServiceImpL.class);
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private Fct001 fct001;
    
    /** 数値（0～9、.）のみの文字列判定 */
    private static final Pattern NUMBER_PATTERN = Pattern.compile("[0-9.]+");
    
    /** 新旧区分：新株 */
    private static final String NEW_OLD_ID_NEW = "1";
    
    /** 検索番号 FORM 一回目 */
    private static final String SEARCH_REF_FROM_FIRST = "00001";
    
    /** 検索番号 TO   一回目 */
    private static final String SEARCH_REF_TO_FIRST = "01000";
    
    /** 権限チェックエラー  */
    private static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    private static final String AUTH_ERROR_VALUE = "0";
    
    /** 国内信用口座開設状況のチェックエラー */
    private static final String ERRORS_DMS_NOT_OPEN = "errors.dms.domesticMarginAccount.notOpen";
    
    /** 商品タイプ 判定用定数　*/
    private static enum SecurityType {
        
        /** 国内株式 */
        DOMESTIC_STOCK("K0", "国内株式"),
        /** 国内投信 */
        DOMESTIC_MUTUAL("T0", "投資信託"),;
        
        /** 自動注文種別 判定用定数値 */
        private final String value;
        
        /** 日本語値　*/
        private final String jpName;
        
        /** 定数値の設定 */
        private SecurityType(final String value, final String jpName) {
            
            this.value = value;
            this.jpName = jpName;
        }
        
        /**
         * 定数値の取得
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
        
        /**
         * 日本語値の取得
         *
         * @return 日本語値
         */
        public String getJpName() {
            
            return this.jpName;
        }
    }
    
    /** 商品区分 判定用定数　*/
    private static enum SecurityId {
        
        /** 投信,投信一般 */
        INVESTMENT_TRUST_GENERAL_MOUTH("T"),
        /** 投信累投口 */
        INVESTMENT_TRUST_ACCUMULATION("Y"),;
        
        /** 自動注文種別 判定用定数値 */
        private final String value;
        
        /** 定数値の設定 */
        private SecurityId(final String value) {
            
            this.value = value;
        }
        
        /**
         * 定数値の取得
         *
         * @return 文字列値
         */
        public String getStringValue() {
            
            return this.value;
        }
    }
    
    /**
     * 初期化
     * Dto リクエスト：IfaCollateralSecurityDeliverInOutDetailA001RequestDto
     * Dto レスポンス：IfaCollateralSecurityDeliverInOutDetailA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto> initializeA001(
            IfaCollateralSecurityDeliverInOutDetailA001RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCollateralSecurityDeliverInOutDetailServiceImplL.initializeA001");
        }
        
        DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto> dtoRes =
                new DataList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto>();
        List<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto> resDtoList =
                new ArrayList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto>();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //①利用者の口座に対する権限チェックを行う。
        //  権限あり（対象顧客参照権限有無＝"1"）：次の処理へ。
        //  権限なし（対象顧客参照権限有無＝"0"：権限なしエラーを返す。
        InputFct001Dto fct001InData = new InputFct001Dto();
        fct001InData.setButenCode(butenCode);
        fct001InData.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(fct001InData);
        if (fct001Dto == null || StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(),
                IfaCollateralSecurityDeliverInOutDetailServiceImpL.AUTH_ERROR_VALUE)) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaCollateralSecurityDeliverInOutDetailServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(
                            IfaCollateralSecurityDeliverInOutDetailServiceImpL.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoRes;
        }
        
        String domesticMarginAccountType = cc.getDomesticMarginAccountType();
        
        //②顧客共通情報の「信用口座区分(国内)」より、信用口座開設状況をチェックを行う。
        //  "１"（信用口座）→「開設済」：次の処理へ。
        //  "△"（現物口座）→「未開設」：信用口座未開設エラーを返す。
        if (StringUtils.equals(domesticMarginAccountType, "1") == false) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaCollateralSecurityDeliverInOutDetailServiceImpL.ERRORS_DMS_NOT_OPEN,
                    IfaCommonUtil.getMessage(IfaCollateralSecurityDeliverInOutDetailServiceImpL.ERRORS_DMS_NOT_OPEN));
            return dtoRes;
        }
        
        IfaCollateralSecurityDeliverInOutDetailA001ResponseDto resDto = 
                new IfaCollateralSecurityDeliverInOutDetailA001ResponseDto();
        // 表示基準日（受渡日）の設定
        resDto.setDisplayBaseDate(dtoReq.getDisplayBaseDate());
        // 受渡日の設定
        resDto.setSettlementDate(dtoReq.getSettlementDate());
        // 入出庫区分の設定
        resDto.setDeliverInOutClassification(dtoReq.getDeliverInOutClassification());
        
        //③代用有価証券評価額合計欄の情報を取得する。（API001）
        //  検索番号指定ＦＲＯＭ～検索番号指定ＴＯの設定について
        //  '1回目の呼び出しでOUT.検索結果件数>1000の場合、2回目以降は 検索結果件数分をすべて取得するまで設定を変えて実行
        //  例）１回目 From:00001、To:01000
        //            2回目 From:01001、To:02000
        //            3回目 From02001、To:03000
        //            ～
        String searchFrom = IfaCollateralSecurityDeliverInOutDetailServiceImpL.SEARCH_REF_FROM_FIRST;
        String searchTo = IfaCollateralSecurityDeliverInOutDetailServiceImpL.SEARCH_REF_TO_FIRST;
        
        QueryAccountSubSecRecDelInData api001InDto = new QueryAccountSubSecRecDelInData();
        api001InDto.setOfficeCode(butenCode.substring(0, 3));
        api001InDto.setAccountNo(String.format("%7s", accountNumber).replace(" ", "0"));
        api001InDto.setSettleDate(dtoReq.getSettlementDate().replace("/", ""));
        api001InDto.setRefFrom(searchFrom);
        api001InDto.setRefTo(searchTo);
        api001InDto.setApExKbn(dtoReq.getDeliverInOutClassification());
        QueryAccountSubSecRecDelOutData api001ResDto = apiwrapper.queryAccountSubSecRecDel(api001InDto);
        apiErrorUtil.checkApiResponse(api001ResDto.getShubetu(), api001ResDto.getCode(), api001ResDto.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        //・代用有価証券評価額合計の編集
        //  API001.代用評価金額合計＝データなし又はフォーマット不正の場合、'-'を設定する。
        //  API001.代用評価金額合計が上記以外の場合、API001.代用評価金額合計を設定する
        if (IfaCollateralSecurityDeliverInOutDetailServiceImpL.NUMBER_PATTERN
                .matcher(api001ResDto.getTotalValue().trim()).matches() == false
                || StringUtils.equals(api001ResDto.getTotalValue().trim(), "0")) {
            
            // nullを返却することで、画面共通部品により表示上には'-'と表示される。
            resDto.setCollateralValuationTotal(null);
        } else {
            resDto.setCollateralValuationTotal(api001ResDto.getTotalValue());
        }
        
        List<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto.Detail> detailList =
                new ArrayList<IfaCollateralSecurityDeliverInOutDetailA001ResponseDto.Detail>();
        
        for (QueryAccountSubSecRecDelDetail substitute : api001ResDto.getSubstitute()) {
            IfaCollateralSecurityDeliverInOutDetailA001ResponseDto.Detail detail =
                    new IfaCollateralSecurityDeliverInOutDetailA001ResponseDto.Detail();
            
            //・代用有価証券明細部リスト.商品分類の編集
            //  API001.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合、”国内株式”を設定
            //  API001.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信  ※一般口投信・汎用累投の場合、
            //                  ”投資信託”を設定
            String secType = substitute.getSecTypeName();
            if (StringUtils.equals(secType, SecurityType.DOMESTIC_STOCK.getStringValue())) {
                detail.setSecurityClass(SecurityType.DOMESTIC_STOCK.getJpName());
            } else if (StringUtils.equals(secType, SecurityType.DOMESTIC_MUTUAL.getStringValue())) {
                detail.setSecurityClass(SecurityType.DOMESTIC_MUTUAL.getJpName());
            } else {
                // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                detail.setSecurityClass(null);
            }
            
            //
            //・代用有価証券明細部リスト.銘柄コード編集
            //  API001.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合、
            //  「API001.会社コードの頭4桁＋API001.新旧区分」を設定
            //  API001.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信  ※一般口投信・汎用累投  かつ、
            //  API001.商品区分='T'(投信,投信一般口)or'Y'(投信累投口)の場合：
            //                 「API001.回数+"".""+API001.号1+API001.号2"" 」を設定
            //
            if (StringUtils.equals(secType, SecurityType.DOMESTIC_STOCK.getStringValue())) {
                detail.setBrandCode(substitute.getCompanyCode().substring(0, 4) + substitute.getNewOldId());
            } else if (StringUtils.equals(secType, SecurityType.DOMESTIC_MUTUAL.getStringValue()) && (StringUtils
                    .equals(substitute.getSecId(), SecurityId.INVESTMENT_TRUST_GENERAL_MOUTH.getStringValue())
                    || StringUtils.equals(substitute.getSecId(),
                            SecurityId.INVESTMENT_TRUST_ACCUMULATION.getStringValue()))) {
                detail.setBrandCode(
                        substitute.getSerNo() + "." + substitute.getSubCode1() + substitute.getSubCode2());
            }
            
            //・代用有価証券明細部リスト.銘柄名編集
            //  ①API001.商品タイプ（商品区分＋国内外国区分）＝  Ｋ０：国内株式の場合、
            //  1).API001.銘柄名（漢字）が全スペース以外かつ新旧区分が""1""(新株)の場合
            //        「API001.銘柄名（漢字）+""－新株""」を設定
            //  2).API001.銘柄名（漢字）が全スペース以外かつ新旧区分が""1""(新株)以外の場合
            //        「API001.銘柄名（漢字）」を設定
            //  3).API001.銘柄名（漢字）が全スペースの場合「-」を設定
            //  ②API001.商品タイプ（商品区分＋国内外国区分）＝  Ｔ０：国内投信  ※一般口投信・汎用累投  の場合、
            //  1).API001.銘柄名（漢字）が全スペース以外の場合
            //        「API001.銘柄名（漢字）」を設定
            //  2).API001.銘柄名（漢字）が全スペースの場合「-」を設定
            //
            if (StringUtils.equals(secType, SecurityType.DOMESTIC_STOCK.getStringValue())) {
                if (substitute.getSecName().trim().length() != 0) {
                    if (StringUtils.equals(substitute.getNewOldId(),
                            IfaCollateralSecurityDeliverInOutDetailServiceImpL.NEW_OLD_ID_NEW)) {
                        detail.setBrandName(substitute.getSecName() + "－新株");
                    } else {
                        detail.setBrandName(substitute.getSecName());
                    }
                } else {
                    // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                    detail.setBrandName(null);
                }
            } else if (StringUtils.equals(secType, SecurityType.DOMESTIC_MUTUAL.getStringValue())) {
                if (substitute.getSecName().trim().length() != 0) {
                    detail.setBrandName(substitute.getSecName());
                } else {
                    // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                    detail.setBrandName(null);
                }
            }
            
            //・代用有価証券明細部リスト.預り区分の編集
            //  「①特定口座区分  ＋  ②口数金額」を設定する
            //
            //  ①特定口座区分の編集
            //        1).API001.非特定預り区分＝  ""0""'(特定口座における特定預り)の場合、  ”特定”を設定
            //        2).API001.非特定預り区分＝  ""0""'(特定口座における特定預り)以外の場合、  ”一般”を設定
            String specificAccountType = (StringUtils.equals(substitute.getHitokuteiKbn(), "0")) ? "特定" : "一般";
            //  ②口数金額の編集
            //        1).API001.商品区分='T'(投信,投信一般口)の場合  ”(口数)”を設定
            //        2).API001.商品区分='Y'(投信累投口)の場合”(金額)”を設定
            //
            String mutualFundUnitAmount = "";
            if (StringUtils.equals(substitute.getSecId(),
                    SecurityId.INVESTMENT_TRUST_GENERAL_MOUTH.getStringValue())) {
                mutualFundUnitAmount = "(口数)";
            } else if (StringUtils.equals(substitute.getSecId(),
                    SecurityId.INVESTMENT_TRUST_ACCUMULATION.getStringValue())) {
                mutualFundUnitAmount = "(金額)";
            }
            detail.setDepositType(specificAccountType + mutualFundUnitAmount);
            
            //・代用有価証券明細部リスト.残高数量の編集
            //  API001.残高数量＝  データなし又はフォーマット不正の場合”-”を設定
            //  API001.残高数量＝  上記以外の場合、
            //                  API001.残高数量を設定
            //
            String quantity = substitute.getQuantity().trim();
            if (quantity.length() == 0 || IfaCollateralSecurityDeliverInOutDetailServiceImpL.NUMBER_PATTERN
                    .matcher(quantity).matches() == false) {
                // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                detail.setContPosition(null);
            } else {
                detail.setContPosition(substitute.getQuantity());
            }
            
            //・代用有価証券明細部リスト.評価単価の編集
            //  API001.評価単価＝  データなし又はフォーマット不正の場合”-”を設定
            //  API001.評価単価＝  上記以外の場合、
            //                  API001.評価単価を設定
            //
            String marketPrice = substitute.getMarketPrice().trim();
            if (marketPrice.length() == 0 || IfaCollateralSecurityDeliverInOutDetailServiceImpL.NUMBER_PATTERN
                    .matcher(marketPrice).matches() == false) {
                
                // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                detail.setValuationPrice(null);
            } else {
                detail.setValuationPrice(substitute.getMarketPrice());
            }
            
            //・代用有価証券明細部リスト.代用掛目の編集
            //  API001.代用掛目＝  全スペースの場合、未設定
            //  API001.代用掛目＝  上記以外の場合、
            //            API001.代用掛目を設定
            //
            if (substitute.getCollateralRate().trim().length() != 0) {
                detail.setCollateralAssessment(substitute.getCollateralRate());
            }
            
            //・代用有価証券明細部リスト.代用評価金額の編集
            //  API001.代用評価金額＝  データなし又はフォーマット不正の場合”-”を設定
            //  API001.代用評価金額＝  上記以外の場合、
            //          API001.代用評価金額を設定
            String collateralValue = substitute.getCollateralValue().trim();
            if (collateralValue.length() == 0 || IfaCollateralSecurityDeliverInOutDetailServiceImpL.NUMBER_PATTERN
                    .matcher(collateralValue).matches() == false) {
                
                // nullを返却することで、画面共通部品により表示上には'-'と表示される。
                detail.setCollateralValuation(null);
            } else {
                detail.setCollateralValuation(substitute.getCollateralValue());
            }
            detailList.add(detail);
            
        }
        
        resDto.setDetailList(detailList);
        resDtoList.add(resDto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
}
