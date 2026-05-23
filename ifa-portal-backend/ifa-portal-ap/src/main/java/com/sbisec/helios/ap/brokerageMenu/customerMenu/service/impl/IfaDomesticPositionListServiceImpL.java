package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct020;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticPositionListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IntermediaryValue;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.PositionList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaDomesticPositionListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract0In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract0InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract0OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract0OutVec;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK 金志
 */
@Component(value = "cmpIfaDomesticPositionListService")
public class IfaDomesticPositionListServiceImpL implements IfaDomesticPositionListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaDomesticPositionListServiceImpL.class);
    
    @Autowired
    private IfaDomesticPositionListDao dao;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct020 fct020;
    
    /** 権限あり */
    private static final String AUTHORIZED = "1";
    
    /** 未開設 */
    private static final String SPACE = " ";
    
    /** 区分値.証券金銭種別_国内株式 */
    private static final String DOMESTICSTOCK = "01";
    
    /** "1" */
    private static final String ONE = "1";
    
    /** "50" */
    private static final String FIFTY = "50";
    
    /** ● */
    private static final String CIRCLE = "●";
    
    /** 日付最大値 */
    private static final String FINALDATE = "99991231";
    
    /** 無期限 */
    private static final String UNLIMITED = "無期限";
    
    /** "0" */
    private static final String ZERO = "0";
    
    /** "一般" */
    private static final String GENERAL = "一般";
    
    /** "特定" */
    private static final String SPECIFIC = "特定";
    
    /** パーセント空白 */
    private static final String BLANKPERCENT = "(--%)";
    
    /** 建玉残高明細MAX件数 */
    private static final int MAX_QUERY_MARGIN_CONTRACT0 = 50;
    
    enum TradeClass {
        
        // 信用返済買
        BUY("5"),
        // 信用返済売
        SELL("6"),
        // 現引
        GENBIKI("8"),
        // 現渡
        GENWATASHI("7");
        
        private String key;
        
        private TradeClass(String key) {
            
            this.key = key;
        }
    }
    
    enum MessageId {
        
        // errors.butenAccountNotExist
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.dms.domesticMarginAccount.notOpen
        ERRORS_NOTOPEN("errors.dms.domesticMarginAccount.notOpen");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticPositionListA001RequestDto
     * Dto レスポンス：IfaDomesticPositionListA001ResponseDto
     * model リクエスト：IfaDomesticPositionListSql001RequestModel
     * model レスポンス：IfaDomesticPositionListSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticPositionListA001ResponseDto> initializeA001(IfaDomesticPositionListA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaDomesticPositionListServiceImplL.initializeA001");
        }
        
        // ①利用者の口座に対する権限チェックを行う。（FCT001）
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // ・FCT001のリクエストパラメータをセット
        InputFct001Dto fct001Req = new InputFct001Dto();
        // Actionリクエスト.部店コードが設定されている場合
        if (dtoReq.getButenCode() != null && !dtoReq.getButenCode().isEmpty()) {
            // Actionリクエスト.部店コードをセット
            fct001Req.setButenCode(dtoReq.getButenCode());
            
            // else
        } else {
            // 顧客共通情報.部店コード(3桁)をセット
            fct001Req.setButenCode(cc.getButenCode());
        }
        // Actionリクエスト.口座番号が設定されている場合
        if (dtoReq.getAccountNumber() != null && !dtoReq.getAccountNumber().isEmpty()) {
            // Actionリクエスト.口座番号をセット
            fct001Req.setAccountNumber(dtoReq.getAccountNumber());
            
            // else
        } else {
            fct001Req.setAccountNumber(cc.getAccountNumber());
        }
        // ・FCT001を呼び出す（利用者顧客参照権限チェック）
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        
        DataList<IfaDomesticPositionListA001ResponseDto> dtoRes = new DataList<IfaDomesticPositionListA001ResponseDto>();
        List<IfaDomesticPositionListA001ResponseDto> resDtoList = new ArrayList<IfaDomesticPositionListA001ResponseDto>();
        IfaDomesticPositionListA001ResponseDto resDto = new IfaDomesticPositionListA001ResponseDto();
        // FCT001のレスポンスパラメータ.対象顧客参照権限有無 != '1'（権限あり）の場合
        if (!StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), AUTHORIZED)) {
            // エラー(errors.butenAccountNotExist)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        // リスポンス.取引停止フラグを設定
        resDto.setTradeSuspendFlag(fct001Res.getTradeSuspendFlag());
        
        // ②顧客共通情報の「信用口座区分(国内)」より、信用口座開設状況をチェックを行う。
        // 顧客共通情報.信用口座区分(国内) = "△"（未開設）の場合
        if (StringUtils.equals(cc.getDomesticMarginAccountType(), SPACE)) {
            // エラー(errors.dms.domesticMarginAccount.notOpen)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, IfaCommonUtil.getMessage(MessageId.ERRORS_NOTOPEN.key));
            return dtoRes;
        }
        
        // ③利用者の口座における取引コース媒介可否リストを取得する。（FCT003）
        // ・FCT003のリクエストパラメータに下記の値をセット
        InputFct003Dto fct003Req = new InputFct003Dto();
        // 部店コードに「顧客共通情報.部店コード」をセット
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号に「顧客共通情報.口座番号」をセット
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別に「"国内株式"」をセット
        fct003Req.setProductCd(DOMESTICSTOCK);
        // ・FCT003を呼び出す（取引コース媒介可否チェック）
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        // ・レスポンス.媒介可否リスト.媒介可否に下記の値をセット
        List<IntermediaryValue> intermediaryValueList = new ArrayList<>();
        for (int i = 0; i < fct003Res.getMediateProprietyList().size(); i++) {
            IntermediaryValue intermediaryValue = new IntermediaryValue();
            // FCT003.媒介可否リスト.取引種別 = "5" || FCT003.媒介可否リスト.取引種別 = "6" 
            // || FCT003.媒介可否リスト.取引種別 = "8" || FCT003.媒介可否リスト.取引種別 = "7" の場合
            String trade = fct003Res.getMediateProprietyList().get(i).getTradeClass();
            if (StringUtils.equals(trade, TradeClass.BUY.key) || StringUtils.equals(trade, TradeClass.SELL.key)
                    || StringUtils.equals(trade, TradeClass.GENBIKI.key)
                    || StringUtils.equals(trade, TradeClass.GENWATASHI.key)) {
                // レスポンス.媒介可否リスト.取引種別に「FCT003.媒介可否リスト.取引種別」を設定
                intermediaryValue.setTradeClass(fct003Res.getMediateProprietyList().get(i).getTradeClass());
                // レスポンス.媒介可否リスト.媒介可否に「FCT003.媒介可否リスト.媒介可否」を設定
                intermediaryValue
                        .setIntermediaryValue(fct003Res.getMediateProprietyList().get(i).getMediatePropriety());
                intermediaryValueList.add(intermediaryValue);
            }
        }
        resDto.setIntermediaryValueList(intermediaryValueList);
        
        // ④建玉一覧をAPIから取得する。(API001)
        // ・API001のリクエストパラメータに下記をセット
        QueryMarginContract0InData inData = new QueryMarginContract0InData();
        // 部店コードをセット
        // Actionリクエスト.部店コードが設定されている場合
        if (dtoReq.getButenCode() != null && !dtoReq.getButenCode().isEmpty()) {
            // Actionリクエスト.部店コードをセット
            inData.setButenCd(dtoReq.getButenCode());
            
            // else
        } else {
            // 顧客共通情報.部店コード(3桁)をセット
            inData.setButenCd(cc.getButenCode());
        }
        // 口座番号をセット
        // Actionリクエスト.口座番号が設定されている場合
        if (dtoReq.getAccountNumber() != null && !dtoReq.getAccountNumber().isEmpty()) {
            // Actionリクエスト.口座番号をセット
            //inData.setKozaNo(dtoReq.getAccountNumber());
            inData.setKozaNo(String.format("%7s", dtoReq.getAccountNumber()).replace(" ", "0"));
            
            // else
        } else {
            inData.setKozaNo(cc.getAccountNumber());
        }
        // ref_fromに「'1'」をセット
        inData.setRefFrom(ONE);
        // ref_toに「'50'」をセット
        inData.setRefTo(FIFTY);
        
        QueryMarginContract0In api001Req = new QueryMarginContract0In();
        api001Req.setIndata(inData);
        // ・API001を呼び出す（建玉残高明細）
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract0OutData> api001ResList = null;
        api001ResList = apiWrapper.queryMarginContract0(api001Req);
        for (QueryMarginContract0OutData api001Res : api001ResList) {
            apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        QueryMarginContract0OutData api001Res = new QueryMarginContract0OutData();
        // API001の結果をapi001ResTmpにセットする
        if (api001ResList != null) {
            // Beanコピー
            BeanUtils.copyProperties(api001Res, api001ResList.get(0));
            if (MAX_QUERY_MARGIN_CONTRACT0 < Integer.parseInt(api001ResList.get(0).getHitNumber())) {
                int count = 1;
                while (true) {
                    if (count == api001ResList.size()) {
                        break;
                    }
                    for (QueryMarginContract0OutVec tmp : api001ResList.get(count).getQueryMarginContract0Data()) {
                        api001Res.getQueryMarginContract0Data().add(tmp);
                    }
                    count++;
                }
                
            }
        }
        
        // レスポンス.検索結果件数を設定
        resDto.setSearchResultCount(api001Res.getHitNumber());
        
        // 建玉一覧リスト
        List<PositionList> positionList = new ArrayList<>();
        // 評価額合計（リアル）
        BigDecimal valuationTotalReal = BigDecimal.ZERO;
        // 評価損益合計（リアル)
        BigDecimal domesticPositionValuationTotalReal = BigDecimal.ZERO;
        
        // 取得した建玉一覧リストについて⑤～⑯の項目算出を行う。
        for (int index = 0; index < api001Res.getQueryMarginContract0Data().size(); index++) {
            // 建玉一覧リストのindex番のレコード
            QueryMarginContract0OutVec recode = new QueryMarginContract0OutVec();
            recode = api001Res.getQueryMarginContract0Data().get(index);
            
            //  ⑤API001で取得した建玉一覧の明細毎に銘柄名を取得する。
            IfaDomesticPositionListSql001RequestModel selSql001Req = new IfaDomesticPositionListSql001RequestModel();
            //  ・SQL001のリクエストパラメータ.銘柄コードに「API001.銘柄コード」をセット
            selSql001Req.setBrandCode(recode.getBrandCd());
            //  ・SQL001を呼び出す
            DataList<IfaDomesticPositionListSql001ResponseModel> selSql001Res = new DataList<IfaDomesticPositionListSql001ResponseModel>();
            selSql001Res = dao.selectIfaDomesticPositionListSql001(selSql001Req);
            //  ・建玉一覧リスト.銘柄コードにSQL001のレスポンスパラメータ.銘柄名を設定
            PositionList position = new PositionList();
            if (selSql001Res.getDataList().size() != 0) {
                position.setBrandName(selSql001Res.getDataList().get(0).getBrandName());
            }
            
            //  ⑥API001で取得した建玉一覧の明細毎に現在値を取得する。（FCT020）
            //  ・FCT020のリクエストパラメータに下記をセット
            InputFct020Dto fct020Req = new InputFct020Dto();
            //  銘柄コードに「API001.繰返部.銘柄コード」をセット
            fct020Req.setBrandCode(recode.getBrandCd());
            //  権利区分に「API001.繰返部.権利区分」をセット
            fct020Req.setRightType(recode.getStRightId());
            //  CT夜間バッチ終了フラグに「API001.CT夜間バッチ終了フラグ」をセット
            fct020Req.setCtNightBatchEndFlag(api001Res.getNightBatchEndFlg());
            //  ・FCT020を呼び出す（国内株式リアル時価取得）
            OutputFct020Dto fct020Res = new OutputFct020Dto();
            fct020Res = fct020.getData(fct020Req);
            
            //  FCT020.評価用現在値がNULLの場合
            if (fct020Res.getCurrentValueForEvaluation() == null
                    || fct020Res.getCurrentValueForEvaluation().isEmpty()) {
                // 建玉一覧リスト.現在値（リアル）に「"-"」をセット
                position.setCurrentPrice("-");
                
                // else
            } else {
                // 建玉一覧リスト.現在値（リアル）に「FCT020.評価用現在値」を設定
                position.setCurrentPrice(fct020Res.getCurrentValueForEvaluation());
            }
            
            //  ⑦Actionパラメータ.建玉一覧リスト.新規建日を編集する。
            //  if（API001.建玉件数=1の場合）｛
            if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                // 建玉一覧リスト.新規建日に「API001.新規約定日」をセット
                position.setConstructionDate(recode.getOpenTradeDate());
                
                // else if（API001.建玉件数>1の場合
            } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                // 建玉一覧リスト.新規建日に「null」をセット
                position.setConstructionDate(null);
            }
            
            //  ⑧Actionパラメータ.建玉一覧リスト.返済期限短縮、返済期限を編集する。
            //  ・Actionパラメータ.建玉一覧リスト.返済期限短縮
            //  API001.建玉件数=1　かつ　API001.期日短縮区分='1'(決済期日短縮あり)の場合
            if (Integer.parseInt(recode.getBargainNumber()) == 1 
                    && (StringUtils.equals(recode.getChangeLastTradeKbn(), ONE))) {
                // 建玉一覧リスト.返済期限短縮に「'●'」をセット
                position.setRepayPeriodShorter(CIRCLE);
            }
            //  ・Actionパラメータ.建玉一覧リスト.返済期限の設定
            //  API001.建玉件数=1の場合
            if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                // API001.決済期日が'99991231'の場合
                if (StringUtils.equals(recode.getLastTradeDate(), FINALDATE)) {
                    // 建玉一覧リスト.返済期限に「'無期限'」をセット
                    position.setLastTradeDate(UNLIMITED);
                    
                    // else
                } else {
                    // 建玉一覧リスト.返済期限に「API001.決済期日」をセット
                    position.setLastTradeDate(recode.getLastTradeDate());
                }
                
                // else if（API001.建玉件数>1の場合）
            } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                // 建玉一覧リスト.返済期限に「null」をセット
                position.setLastTradeDate(null);
            }
            
            //  ⑨Actionパラメータ.建玉一覧リスト.親株新規約定日を編集する。
            //  API001.建玉件数=1の場合
            if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                // 建玉一覧リスト.親株新規約定日に「API001.親株新規約定日」をセット
                position.setParentStockTradeDate(recode.getOrgNewTradeDate());
                
                // else if（API001.建玉件数>1の場合）
            } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                // 建玉一覧リスト.親株新規約定日に「null」をセット
                position.setParentStockTradeDate(null);
            }
            
            //  ⑩Actionパラメータ.建玉一覧リスト.特定・一般を編集する。
            //  API001.建玉件数=1の場合
            if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                // API001.特定建玉区分の値 = '0'の場合
                if (StringUtils.equals(recode.getTokuteiContractId(), ZERO)) {
                    // 建玉一覧リスト.特定・一般に「'一般'」をセット
                    position.setAccountType(GENERAL);
                    
                    // else if（API001.特定建玉区分の値 = '1'の場合）
                } else if (StringUtils.equals(recode.getTokuteiContractId(), ONE)) {
                    // 建玉一覧リスト.特定・一般に「'特定'」をセット
                    position.setAccountType(SPECIFIC);
                    
                    // else
                } else {
                    // 建玉一覧リスト.特定・一般に「'-'(半角ハイフン)」をセット
                    position.setAccountType(null);
                }
                
                // else if（API001.建玉件数>1の場合）
            } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                // 建玉一覧リスト.特定・一般に「'-'(半角ハイフン)」をセット
                position.setAccountType(null);
            }
            
            //  ⑪Actionパラメータ.建玉一覧リスト.担保を編集する。
            //  API001.新規建保証金率(5桁)=0の場合
            if (StringUtils.equals(recode.getBargainNeedSecRate(), ZERO)) {
                // 建玉一覧リスト.担保に「'(--%)' 」をセット
                position.setDomesticCollateral(BLANKPERCENT);
                
                // else
            } else {
                // 建玉一覧リスト.担保に「'('+上3桁(整数部)+'%)'」をセット　　　　※例）' 3300' ⇒'(33%)'
                String percent = recode.getBargainNeedSecRate().substring(0, 3);
                position.setDomesticCollateral("(" + percent.trim() + "%)");
            }
            
            //  ⑫Actionパラメータ.建玉一覧リスト.新規単価を編集する。
            //  API001.建玉件数=1の場合
            if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                // 建玉一覧リスト.新規単価に「API001.取得単価÷100」をセット
                BigDecimal unit = new BigDecimal(recode.getOpenPrice()).divide(BigDecimal.valueOf(100));
                position.setNewPrice(unit.toString());
                
                // else if（API001.建玉件数>1の場合）
            } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                // 建玉一覧リスト.新規単価に「'-'(半角ハイフン)」をセット
                position.setNewPrice(null);
            }
            
            //  ⑬Actionパラメータ.建玉一覧リスト.評価額計（リアル）を編集する。
            //  FCT020.評価用現在値がNULLの場合
            if (fct020Res.getCurrentValueForEvaluation() == null
                    || fct020Res.getCurrentValueForEvaluation().isEmpty()) {
                // 建玉一覧リスト.評価額計（リアル）に「"-"」をセット
                position.setRealtimeValueTotal(null);
                
                // else
            } else {
                // 建玉一覧リスト.評価額計（リアル）に「FCT020.評価用現在値 ｘ API001.残高数量合計」をセット
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation());
                BigDecimal contPositionTotal = new BigDecimal(recode.getContPositionTotal());
                BigDecimal total = currentValueForEvaluation.multiply(contPositionTotal);
                position.setRealtimeValueTotal(total.toString());
            }
            
            //  ⑭Actionパラメータ.建玉一覧リスト.評価損益（リアル）を編集する。
            //  FCT020.評価用現在値がNULLの場合
            if (fct020Res.getCurrentValueForEvaluation() == null
                    || fct020Res.getCurrentValueForEvaluation().isEmpty()) {
                // 建玉一覧リスト.評価損益（リアル）「"-"」をセット
                position.setDomesticPositionValuationTotalReal(null);
                
                // else if（API001.新規売買区分='0'(買建)の場合 ）
            } else if (StringUtils.equals(recode.getOpenTradeKbn(), ZERO)) {
                // 建玉一覧リスト.評価損益（リアル）に「FCT020.評価用現在値×API001.残高数量合計－API001.建代金－API001.諸経費」をセット
                // 建玉一覧リスト.評価損益（リアル）に「FCT020.評価用現在値×API001.残高数量合計－API001.建代金－API001.諸経費」をセット
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation());
                BigDecimal contPositionTotal = new BigDecimal(recode.getContPositionTotal());
                BigDecimal openAmount = new BigDecimal(recode.getOpenAmount());
                BigDecimal cost = new BigDecimal(recode.getCost());
                BigDecimal profit = currentValueForEvaluation.multiply(contPositionTotal);
                profit = profit.subtract(openAmount);
                profit = profit.subtract(cost);
                position.setDomesticPositionValuationTotalReal(profit.toString());
                
                // else if（API001.新規売買区分='1'(売建)の場合 ）
            } else if (StringUtils.equals(recode.getOpenTradeKbn(), ONE)) {
                // 建玉一覧リスト.評価損益（リアル）に「API001.建代金－FCT020.評価用現在値×API001.残高数量合計－API001.諸経費」をセット
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation());
                BigDecimal contPositionTotal = new BigDecimal(recode.getContPositionTotal());
                BigDecimal openAmount = new BigDecimal(recode.getOpenAmount());
                BigDecimal cost = new BigDecimal(recode.getCost());
                BigDecimal profit = openAmount.subtract(currentValueForEvaluation.multiply(contPositionTotal));
                profit = profit.subtract(cost);
                position.setDomesticPositionValuationTotalReal(profit.toString());
            }
            
            //  ⑮取引ボタンの表示制御を行う。
            List<QueryMarginContract0OutVec> listRecode = new ArrayList<QueryMarginContract0OutVec>();
            for (int l = 0; l < api001Res.getQueryMarginContract0Data().size(); l++) {
                QueryMarginContract0OutVec subRecode = new QueryMarginContract0OutVec();
                subRecode = api001Res.getQueryMarginContract0Data().get(l);
                if (index != l) {
                    // API001.建玉一覧リスト内で銘柄コード、新規売買区分、弁済期限が同じレコード取得
                    if (StringUtils.equals(recode.getBrandCd(), subRecode.getBrandCd())
                            && StringUtils.equals(recode.getOpenTradeKbn(), subRecode.getOpenTradeKbn())
                            && StringUtils.equals(recode.getPaymentLimit(), subRecode.getPaymentLimit())) {
                        listRecode.add(subRecode);
                    }
                }
            }
            int tmp = 0;
            if (listRecode.size() != 0) {
                for (QueryMarginContract0OutVec outVec : listRecode) {
                    if (StringUtils.equals(recode.getOpenMarket(), outVec.getOpenMarket())) {
                        tmp = 0;
                    } else {
                        tmp = 1;
                        break;
                    }
                }
            }
            // API001.建玉一覧リスト内で銘柄コード、新規売買区分、弁済期限が同じで新規市場が東証、PTSの何れかの場合
            if (tmp == 0) {
                // 建玉件数 ＝ 1 の場合
                if (Integer.parseInt(recode.getBargainNumber()) == 1) {
                    // 新規売買区分 = '0'：'買建'の場合
                    if (StringUtils.equals(recode.getOpenTradeKbn(), ZERO)) {
                        //　・建玉詳細ボタン表示区分に「0」をセット
                        position.setPositionDetailButtonDisplaylassification(ZERO);
                        //　・一括返済ボタン表示区分に「0」をセット
                        position.setMassRepayButtonDisplaylassification(ZERO);
                        //　・返済買ボタン表示区分に「0」をセット
                        position.setRepayBuyButtonDisplaylassification(ZERO);
                        //　・返済売ボタン表示区分に「1」をセット
                        position.setRepaySellButtonDisplaylassification(ONE);
                        //　・現引ボタン表示区分に「1」をセット
                        position.setReceiptButtonDisplaylassification(ONE);
                        //　・現渡ボタン表示区分に「0」をセット
                        position.setDeliveryButtonDisplaylassification(ZERO);
                        
                        // else if（新規売買区分＝　'1'：'売建'の場合）
                    } else if (StringUtils.equals(recode.getOpenTradeKbn(), ONE)) {
                        //　・建玉詳細ボタン表示区分に「0」をセット
                        position.setPositionDetailButtonDisplaylassification(ZERO);
                        //　・一括返済ボタン表示区分に「0」をセット
                        position.setMassRepayButtonDisplaylassification(ZERO);
                        //　・返済買ボタン表示区分に「1」をセット
                        position.setRepayBuyButtonDisplaylassification(ONE);
                        //　・返済売ボタン表示区分に「0」をセット
                        position.setRepaySellButtonDisplaylassification(ZERO);
                        //　・現引ボタン表示区分に「0」をセット
                        position.setReceiptButtonDisplaylassification(ZERO);
                        //　・現渡ボタン表示区分に「1」をセット
                        position.setDeliveryButtonDisplaylassification(ONE);
                    }
                    
                    // else if（建玉件数 ＞1の場合）
                } else if (Integer.parseInt(recode.getBargainNumber()) > 1) {
                    // ・建玉詳細ボタン表示区分に「1」をセット
                    position.setPositionDetailButtonDisplaylassification(ONE);
                    // ・一括返済ボタン表示区分に「1」をセット
                    position.setMassRepayButtonDisplaylassification(ONE);
                    // ・返済買ボタン表示区分に「0」をセット
                    position.setRepayBuyButtonDisplaylassification(ZERO);
                    // ・返済売ボタン表示区分に「0」をセット
                    position.setRepaySellButtonDisplaylassification(ZERO);
                    // ・現引ボタン表示区分に「0」をセット
                    position.setReceiptButtonDisplaylassification(ZERO);
                    // ・現渡ボタン表示区分に「0」をセット
                    position.setDeliveryButtonDisplaylassification(ZERO);
                }
                
                // else if（API001.建玉一覧リスト内で銘柄コード、新規売買区分、弁済期限が同じで新規市場が東証、PTSの両方が存在する場合）
            } else if (tmp == 1) {
                // ・建玉詳細ボタン表示区分に「1」をセット
                position.setPositionDetailButtonDisplaylassification(ONE);
                // ・一括返済ボタン表示区分に「1」をセット
                position.setMassRepayButtonDisplaylassification(ONE);
                // ・返済買ボタン表示区分に「0」をセット
                position.setRepayBuyButtonDisplaylassification(ZERO);
                // ・返済売ボタン表示区分に「0」をセット
                position.setRepaySellButtonDisplaylassification(ZERO);
                // ・現引ボタン表示区分に「0」をセット
                position.setReceiptButtonDisplaylassification(ZERO);
                // ・現渡ボタン表示区分に「0」をセット
                position.setDeliveryButtonDisplaylassification(ZERO);
            }
            
            //  ・活性非活性フラグ設定　
            //  「API001.残高数量合計」－「API001.返済注文済未出来数量」 > 0 の場合
            if ((Long.parseLong(recode.getContPositionTotal())
                    - Long.parseLong(recode.getUnactualQuantity())) > 0) {
                // 「’１’（活性）」をセット
                position.setActivationDeactivationFlag(ONE);
                
                // else if（「API001.残高数量合計」－「API001.返済注文済未出来数量」 ＝ 0 の場合）
            } else if ((Long.parseLong(recode.getContPositionTotal())
                    - Long.parseLong(recode.getUnactualQuantity())) == 0) {
                // 「’0’（非活性）」をセット
                position.setActivationDeactivationFlag(ZERO);
            }
            
            // レスポンス.建玉一覧.銘柄コード
            position.setBrandCode(recode.getBrandCd());
            // レスポンス.建玉一覧.売買区分
            position.setTradeKbn(recode.getOpenTradeKbn());
            // レスポンス.建玉一覧.弁済期限
            position.setPaymentDeadline(recode.getPaymentLimit());
            // レスポンス.建玉一覧.市場
            position.setMarket(recode.getOpenMarket());
            // レスポンス.建玉一覧.担保規制内容
            position.setCollateralRegulations(recode.getRegulateKbn());
            // レスポンス.建玉一覧.建玉件数
            position.setPositionCount(recode.getBargainNumber());
            // レスポンス.建玉一覧.建株数
            position.setContPositionTotal(recode.getContPositionTotal());
            // レスポンス.建玉一覧.注文中
            position.setUnactualQuantity(recode.getUnactualQuantity());
            // レスポンス.建玉一覧.評価単価（前日）
            position.setDayBeforeValuationPrice(recode.getMarketPrice());
            // レスポンス.建玉一覧.新規建玉指定番号
            position.setNewOpenInterestNumber(recode.getOpenContractNo());
            // レスポンス.建玉一覧.建玉金額計
            position.setTotalSmallPrice(recode.getOpenAmount());
            // レスポンス.建玉一覧.評価額計（前日）
            position.setPreviousDayValueTotal(recode.getMarketContractValue());
            // レスポンス.建玉一覧.諸経費計
            position.setCostSmallTotalYen(recode.getCost());
            // レスポンス.建玉一覧.評価損益（前日）
            position.setDomesticPositionValuationTotalPreviousDay(recode.getUnrealizedPl());
            // レスポンス.建玉一覧.現金拘束金
            position.setCashBond(recode.getCashHold());
            // レスポンス.建玉一覧.新規約定日
            position.setNewTradeDate(recode.getOpenTradeDate());
            positionList.add(position);
            
            // 評価額合計（リアル）
            if (position.getRealtimeValueTotal() != null) {
                valuationTotalReal = valuationTotalReal.add(new BigDecimal(position.getRealtimeValueTotal()));
            }
            // 評価損益合計（リアル）
            if (position.getDomesticPositionValuationTotalReal() != null) {
                domesticPositionValuationTotalReal = domesticPositionValuationTotalReal
                        .add(new BigDecimal(position.getDomesticPositionValuationTotalReal()));
            }
        }
        resDto.setPositionList(positionList);
        
        // ⑯建玉金額合計にAPI001.建代金合計を設定する。
        resDto.setTotalPrice(api001Res.getContractTotal());
        
        // ⑰評価額合計（前日）にAPI001.評価額合計を設定する。
        resDto.setValuationTotalPreviousDay(api001Res.getValueTotal());
        
        // ⑱評価額合計（リアル）に⑬で算出した評価額計（リアル）の全行合計値を設定する。
        resDto.setValuationTotalReal(valuationTotalReal.toString());
        
        // ⑲諸経費合計にAPI001.諸経費合計を設定する。
        resDto.setCostTotalYen15(api001Res.getCostTotal());
        
        // ⑳評価損益合計（前日）にAPI001.評価損益合計を設定する。
        resDto.setDomesticPositionValuationTotalPreviousDay(api001Res.getUnrealizedPlTotal());
        
        // ㉑評価損益合計（リアル)に⑮で算出した評価損益（リアル）の全行合計値を設定する。
        resDto.setDomesticPositionValuationTotalReal(domesticPositionValuationTotalReal.toString());
        
        // return レスポンス
        resDtoList.add(resDto);
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        return dtoRes;
    }
    
}
