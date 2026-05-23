package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.Position;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.utils.RequestUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoResponseIntermediaryValue;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA001DtoResponsePositionList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignPositionListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignPositionListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignMarginAccountType;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_010203-01
 * 画面名：米株建玉一覧
 *
 * @author SCSK
 *
 *     2023/11/01 新規作成
 */
@Component(value = "IfaForeignPositionListService")
public class IfaForeignPositionListServiceImpl implements IfaForeignPositionListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignPositionListServiceImpl.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private ForeignAccountService foreignAccountService;
    
    @Autowired
    private ForeignStockService foreignStockService;

    @Autowired
    private CometCommonService cometCommonService;
    
    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 信用口座未開設エラー */
    public static final String ERRORS_FOREIGN_MARGIN_ACCOUNT_NOT_OPEN = "errors.frs.foreignStockAccount.notOpen#1";
    
    /** 銘柄上場ステータスエラー */
    public static final String ERRORS_DELISTING = "errors.frs.listedSecuritiesStatus.delisting";
    
    /** 区分値.証券金銭種別_外国株式 */
    private static final String FOREIGNSTOCK = "15";
    
    /** 区分値.国籍コード_アメリカ合衆国(米国) */
    private static final String US = "US";
    
    /** 区分値.取引種別（外国株式）_信用返済買 */
    private static final String BUY = "4";
    
    /** 区分値.取引種別（外国株式）_信用返済売 */
    private static final String SELL = "5";
    
    /** 日付値「9999-12-31」 */
    private static final String INVALID_DATE = "9999-12-31";
    
    /** 日付値「9999-12-31」時の設定値 */
    private static final String DATE_HYPHEN = "(----/--/--)";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignPositionListA001DtoRequest
     * Dto レスポンス：IfaForeignPositionListA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignPositionListA001DtoResponse> initializeA001(IfaForeignPositionListA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaForeignPositionListA001DtoResponse> dtoResList = new DataList<IfaForeignPositionListA001DtoResponse>();
        List<IfaForeignPositionListA001DtoResponse> resDtoList = new ArrayList<IfaForeignPositionListA001DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignPositionListServiceImpl.initializeA001");
        }
        
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        String butenCode = cc.getButenCode();
        String accountNumber = cc.getAccountNumber();
        
        //①利用者の口座に対する権限チェックを行う。
        //本来は顧客情報.部店コードと顧客情報.口座番号を利用して取得する
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCode);
        inputFct001Dto.setAccountNumber(accountNumber);
        OutputFct001Dto fct001Dto = fct001.doCheck(inputFct001Dto);
        IfaForeignPositionListA001DtoResponse resDto = new IfaForeignPositionListA001DtoResponse();
        resDto.setTradeSuspendFlag(fct001Dto.getTradeSuspendFlag());
        //FCT001.対象顧客参照権限有無 = '1':権限あり の場合、：次の処理へ。
        //FCT001.対象顧客参照権限有無 ≠ '1':権限あり の場合：権限なしエラーを返す。
        if (StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            dtoResList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignPositionListServiceImpl.ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(IfaForeignPositionListServiceImpl.ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { butenCode, accountNumber }));
            return dtoResList;
        }
        
        //②顧客共通情報の「信用口座区分(外国)」より、信用口座開設状況をチェックを行う。
        //「開設済」：次の処理へ。
        //「未開設」：信用口座未開設エラーを返す。
        if (StringUtils.equals(cc.getForeignMarginAccountType(), ForeignMarginAccountType.SPOT.getId())) {
            dtoResList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignPositionListServiceImpl.ERRORS_FOREIGN_MARGIN_ACCOUNT_NOT_OPEN,
                    IfaCommonUtil.getMessage(IfaForeignPositionListServiceImpl.ERRORS_FOREIGN_MARGIN_ACCOUNT_NOT_OPEN));
            return dtoResList;
        }
        
        // ③利用者の口座における取引コース媒介可否リストを取得する。（FCT003）
        // ・FCT003のリクエストパラメータに下記の値をセット
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コードに「顧客共通情報.部店コード」をセット
        fct003Req.setButenCode(butenCode);
        // 口座番号に「顧客共通情報.口座番号」をセット
        fct003Req.setAccountNumber(accountNumber);
        // 証券金銭種別に「"外国株式"」をセット
        fct003Req.setProductCd(FOREIGNSTOCK);
        // 取引種別に「"信用返済買"」をセット
        fct003Req.setTradeCd(BUY);
        // 国籍コードに「"US"」をセット
        fct003Req.setCountryCd(US);
        // 通貨コードに「"999"」をセット
        fct003Req.setCurrencyCode("999");
        // ・FCT003を呼び出す（取引コース媒介可否チェック）
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // ・レスポンス.媒介可否リスト.媒介可否に下記の値をセット
        List<IfaForeignPositionListA001DtoResponseIntermediaryValue> intermediaryValueList = new ArrayList<>();
        IfaForeignPositionListA001DtoResponseIntermediaryValue intermediaryValueBuy = new IfaForeignPositionListA001DtoResponseIntermediaryValue();
        // レスポンス.媒介可否リスト.取引種別に「FCT003.媒介可否リスト.取引種別」を設定
        intermediaryValueBuy.setTradeClass(fct003Res.getMediateProprietyList().get(0).getTradeClass());
        // レスポンス.媒介可否リスト.媒介可否に「FCT003.媒介可否リスト.媒介可否」を設定
        intermediaryValueBuy.setIntermediaryValue(fct003Res.getMediateProprietyList().get(0).getMediatePropriety());
        intermediaryValueList.add(intermediaryValueBuy);
        resDto.setIntermediaryValueList(intermediaryValueList);
        
        // ・FCT003のリクエストパラメータに下記の値をセット
        // 取引種別に「"信用返済売"」をセット
        fct003Req.setTradeCd(SELL);
        // ・FCT003を呼び出す（取引コース媒介可否チェック）
        fct003Res = fct003.doCheck(fct003Req);
        // ・レスポンス.媒介可否リスト.媒介可否に下記の値をセット
        IfaForeignPositionListA001DtoResponseIntermediaryValue intermediaryValueSell = new IfaForeignPositionListA001DtoResponseIntermediaryValue();
        // レスポンス.媒介可否リスト.取引種別に「FCT003.媒介可否リスト.取引種別」を設定
        intermediaryValueSell.setTradeClass(fct003Res.getMediateProprietyList().get(0).getTradeClass());
        // レスポンス.媒介可否リスト.媒介可否に「FCT003.媒介可否リスト.媒介可否」を設定
        intermediaryValueSell.setIntermediaryValue(fct003Res.getMediateProprietyList().get(0).getMediatePropriety());
        intermediaryValueList.add(intermediaryValueSell);
        resDto.setIntermediaryValueList(intermediaryValueList);
        
        //④ 建玉一覧をAPIから全件取得する。
        String countryCode = US;
        ListMarginPositionsResp api001Res = new ListMarginPositionsResp();
        try {            
            api001Res = foreignAccountService.listMarginPositions(butenCode, accountNumber,
                    countryCode, null, null, null, null, null, null, null, null);
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        List<IfaForeignPositionListA001DtoResponsePositionList> positionListList = new ArrayList<IfaForeignPositionListA001DtoResponsePositionList>();
        
        //⑤ 建玉一覧が1件以上ある場合、次の処理を行う。
        if (api001Res != null) {            
            if (api001Res.getPositions() != null) {
                if (api001Res.getPositions().size() > 0) {
                    IfaForeignPositionListA001DtoResponsePositionList positionList = null;
                    
                    for (Position position : api001Res.getPositions()) {
                        positionList = new IfaForeignPositionListA001DtoResponsePositionList();
                        
                        // 建玉一覧リスト.国コード の設定
                        positionList.setCountryCode(position.getCountryCode());
                        // 建玉一覧リスト.銘柄コード の設定
                        positionList.setBrandCode(position.getSecurities().getSecuritiesCode());
                        // 建玉一覧リスト.銘柄名 の設定
                        positionList.setBrandName(position.getSecurities().getSecuritiesName());
                        // 建玉一覧リスト.市場略名 の設定
                        positionList.setMarketAbbreviatedName(position.getMarket().getMarketShortName());
                        // 建玉一覧リスト.売買区分 の設定
                        positionList.setTradeKbn(position.getBuySellCode());
                        // 建玉一覧リスト.信用期日 の設定
                        positionList.setMarginDueDate(position.getMarginCloseLimitType());
                        
                        // ⑥ 建玉一覧リスト.建日を編集する。
                        // API001.国内新規約定日（YYYY-MM-DD)をYYYY/MM/DDに変換
                        positionList.setOpenTradeDate(position.getTradeDate().replace("-", "/"));
                        // 建玉一覧リスト.預り区分 の設定
                        positionList.setDepositType(position.getSpecificAccountCode());
                        
                        // ⑦   建玉一覧リスト.返済期限を編集する。
                        // API001.現地決済期日が '9999-12-31' の場合、'(----/--/--)'
                        // 上記以外の場合、'(' +　API001.現地決済期日（YYYY-MM-DD）をYYYY/MM/DDに形式変換+ ')'
                        positionList.setLastTradeDate(DATE_HYPHEN);
                        if (position.getFrnCloseLimitDate() != null
                                && !INVALID_DATE.equals(position.getFrnCloseLimitDate())) {
                            positionList.setLastTradeDate('(' + position.getFrnCloseLimitDate().replace("-", "/") + ')');
                        }
                        
                        // 建玉一覧リスト.建玉必要保証金率 の設定
                        positionList.setPositionNecessaryDepositRate(position.getPositionMarginRate());
                        // 建玉一覧リスト.増担保規制建玉フラグ の設定
                        positionList
                        .setAdditionalSecurityRegulationPositionFlag(position.getConvNeedAdditionalCollateral());
                        // 建玉一覧リスト.建玉残数量 の設定
                        positionList.setPositionRemainingQuantity(position.getQuantity());
                        
                        // ⑧   建玉一覧リスト.注文中を編集する。
                        // 建玉一覧リスト.注文中（数値(整数)） の設定
                        positionList.setUnactualQuantity(position.getClosingQuantity());
                        // 建玉一覧リスト.新規建単価（外貨） の設定
                        positionList.setNewPositionPriceForeign(position.getFrnPositionPrice());
                        // 建玉一覧リスト.現在値or前日終値 の設定
                        if (position.getPriceData() != null) {
                            positionList.setCurrentPriceOrPreviousDayEndPrice(position.getPriceData().getLastToPrevClose());
                        }
                        // 建玉一覧リスト.新規建代金（外貨） の設定
                        positionList.setForeignNewPositionAmount(position.getFrnPositionAmount());
                        // 建玉一覧リスト.諸経費合計額（外貨） の設定
                        positionList.setExpensesTotalAmountForeign(position.getFrnTotalExpenses());
                        if (position.getEvaluationProfitLoss() != null) {
                            // 建玉一覧リスト.評価損益（外貨） の設定
                            positionList.setCustomerListProfitAndLossForeign(
                                    position.getEvaluationProfitLoss().getFrnEvaluationProfitLoss());
                            // 建玉一覧リスト.評価割合 の設定
                            positionList.setValuationRate(position.getEvaluationProfitLoss().getFrnEvaluationProfitLossPercent());
                        }

                        // ⑨  明細表示およびボタン制御について           
                        // 建玉一覧リスト.返済売ボタン
                        if (StringUtils.equals(position.getBuySellCode(), "1")) {
                            positionList.setRepaySellButton("0");
                        } else {
                            positionList.setRepaySellButton("1");
                        }
                        
                        // 建玉一覧リスト.返済買ボタン
                        if (StringUtils.equals(position.getBuySellCode(), "1")) {
                            positionList.setRepayBuyButton("1");
                        } else {
                            positionList.setRepayBuyButton("0");
                        }
                        
                        // 建玉一覧リスト.取引ボタン非活性
                        Long positionRemainingQuantityValue = (positionList.getPositionRemainingQuantity() == null) ? 0
                                : Long.parseLong(positionList.getPositionRemainingQuantity());
                        Long closingQuantityValue = (position.getClosingQuantity() == null) ? 0
                                : Long.parseLong(position.getClosingQuantity());
                        
                        // API001.建玉残数量　-　API001.決済注文中数量　＝　0（返済可能数量がないとき）の場合   
                        // ・建玉一覧リスト.取引ボタン非活性に’１’（非活性）を設定  
                        if ((positionRemainingQuantityValue - closingQuantityValue) == 0) {
                            positionList.setTradeButtonDeactivation("1");
                        } else {
                            positionList.setTradeButtonDeactivation("0");
                        }
                        
                        // 建玉一覧リスト.国内約定日
                        positionList.setDomesticTradeDate(position.getTradeDate());
                        
                        // 建玉一覧リスト.現地約定日
                        positionList.setLocalTradeDate(position.getFrnTradeDate());
                        
                        // 建玉一覧リスト.新規建単価（円貨）
                        positionList.setJpyAmountNewPositionPrice(position.getPositionPrice());
                        
                        positionListList.add(positionList);
                    }
                }
            }
        }
        resDto.setPositionListList(positionListList);
        
        resDtoList.add(resDto);
        dtoResList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoResList;
    }
    
    /**
     * アクションID：A002
     * アクション名：建玉返済
     * Dto リクエスト：IfaForeignPositionListA002DtoRequest
     * Dto レスポンス：IfaForeignPositionListA002DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<Object> positionRepaymentA002(IfaForeignPositionListA002DtoRequest dtoReq) throws Exception {
        
        DataList<Object> dtoResList = new DataList<Object>();
        List<Object> resDtoList = new ArrayList<Object>();
        
        //① 外国株式銘柄マスタ取得APIを利用して、銘柄情報を取得する。
        GetForeignStockSecuritiesResp api002res = new GetForeignStockSecuritiesResp();
        try {            
            api002res = foreignStockService.getForeignStockSecurities(dtoReq.getCountryCode(), dtoReq.getBrandCode());
        } catch (Exception e) {
            return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                    ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
        }
        
        //・銘柄上場ステータス　＝　DELISTING（上場廃止）の場合、エラーを返す
        //・銘柄上場ステータス　≠　DELISTING（上場廃止）の場合、次の処理へ
        if (StringUtils.equals(api002res.getListedSecuritiesStatus(), "DELISTING")) {
            dtoResList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    IfaForeignPositionListServiceImpl.ERRORS_DELISTING,
                    IfaCommonUtil.getMessage(IfaForeignPositionListServiceImpl.ERRORS_DELISTING));
            return dtoResList;
        } else {
            dtoResList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        }
        return dtoResList;
    }
}
