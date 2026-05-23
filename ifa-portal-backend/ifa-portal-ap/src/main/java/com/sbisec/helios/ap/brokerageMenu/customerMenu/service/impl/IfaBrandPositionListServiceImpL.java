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
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.BrandPositionDetail;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IntermediaryValue;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.Pts;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.TokyoSecurity;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaBrandPositionListService;
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

import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OoutVec;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract1OutData;

import io.netty.util.internal.StringUtil;

/**
 * 画面ID：SUB0202_010202-03
 * 画面名：銘柄別建玉一覧
 *
 * @author SCSK
 */
@Component(value = "cmpIfaBrandPositionListService")
public class IfaBrandPositionListServiceImpL implements IfaBrandPositionListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandPositionListServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private Fct020 fct020;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
    /** 未開設 */
    private static final String ACCOUNT_NOTOPEN = "0";
    
    /** 権限あり */
    private static final String AUTHORIZED = "1";
    
    /** 証券金銭種別.区分値_国内株式 */
    private static final String DOMESTICSTOCK = "01";
    
    /** ALL */
    private static final String ALL = "ALL";
    
    /** ’1’ */
    private static final String ONE = "1";
    
    /** ’50’ */
    private static final String FIFTY = "50";
    
    /** 新規市場.区分値_TKY */
    private static final String TKY = "0";
    
    /** 新規市場.区分値_PTS */
    private static final String PTS = "7";
    
    /** 日付最大値 */
    private static final String FINALDATE = "99991231";
    
    /** 無期限 */
    private static final String UNLIMITED = "無期限";
    
    /** 日付空白*/
    private static final String BLANKDATE = "----/--/--";
    
    /** 買建 */
    private static final String PURCHASE = "0";
    
    /** 売建 */
    private static final String SALE = "1";
    
    /** パーセント空白 */
    private static final String BLANKPERCENT = "(--%)";
    
    /** 固定値”東証” */
    private static final String TOKYO_SECURITY = "東証";
    
    /** 固定値"PTS" */
    private static final String FIXPTS = "PTS";
    
    /** 建玉残高明細MAX件数 */
    private static final int MAX_QUERY_MARGIN_CONTRACT1 = 50;
    
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
        
        // errors.butenAccountNotExis
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        // errors.dms.domesticMarginAccount.notOpen
        ERRORS_DOMESTICMARGINACCOUNT_NOTOPEN("errors.dms.domesticMarginAccount.notOpen"),
        // errors.cmn.information.notfound
        ERRORS_INFORMATION_NOTFOUND("errors.cmn.information.notfound");
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrandPositionListA001RequestDto
     * Dto レスポンス：IfaBrandPositionListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaBrandPositionListA001ResponseDto> initializeA001(IfaBrandPositionListA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaBrandPositionListA001ResponseDto> dtoRes = new DataList<IfaBrandPositionListA001ResponseDto>();
        List<IfaBrandPositionListA001ResponseDto> resDtoList = new ArrayList<IfaBrandPositionListA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBrandPositionListServiceImplL.initializeA001");
        }
        
        // 権限チェックを行う
        InputFct001Dto fct001Req = new InputFct001Dto();
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        // "リクエストパラメーター(
        // 部店コード,
        fct001Req.setButenCode(cc.getButenCode());
        // 口座番号
        fct001Req.setAccountNumber(cc.getAccountNumber());
        // )へセット"
        // FCT001を呼び出す(利用者顧客参照権限チェック)
        OutputFct001Dto fct001Res = new OutputFct001Dto();
        fct001Res = fct001.doCheck(fct001Req);
        // FCT001.対象顧客参照権限有無 ≠ '1':権限あり の場合
        if (!StringUtils.equals(fct001Res.getTargetCustomerRefAuthFlag(), AUTHORIZED)) {
            // 権限なしエラー(errors.butenAccountNotExis)を返す
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return dtoRes;
        }
        
        // 信用口座開設状況をチェックを行う
        // 顧客共通情報.信用口座区分(国内)が未開設（信用口座区分(国内)≠信用口座）の場合
        if (StringUtils.equals(cc.getDomesticMarginAccountType(), ACCOUNT_NOTOPEN)) {
            // "信用口座未開設エラー(errors.dms.domesticMarginAccount.notOpen)を返す。"
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    MessageId.ERRORS_DOMESTICMARGINACCOUNT_NOTOPEN.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DOMESTICMARGINACCOUNT_NOTOPEN.key));
            return dtoRes;
        }
        
        IfaBrandPositionListA001ResponseDto resDto = new IfaBrandPositionListA001ResponseDto();
        // 取引停止フラグセット
        resDto.setTradeSuspendFlag(fct001Res.getTradeSuspendFlag());
        
        // 利用者の口座における媒介可否リストを取得する。
        InputFct003Dto fct003Req = new InputFct003Dto();
        // "リクエストパラメーター(
        // 部店コード,
        fct003Req.setButenCode(cc.getButenCode());
        // 口座番号,
        fct003Req.setAccountNumber(cc.getAccountNumber());
        // 証券金銭種別,
        fct003Req.setProductCd(DOMESTICSTOCK);
        // )をセット"
        // FCT003(取引コース媒介可否取得)を呼び出す
        OutputFct003Dto fct003Res = new OutputFct003Dto();
        fct003Res = fct003.doCheck(fct003Req);
        
        // FCT003.媒介可否リストより、FCT003.媒介可否リスト.取引種別が"信用返済買”、”信用返済売”、”現引”、”現渡"のいずれでもない要素を除外する
        List<IntermediaryValue> intermediaryValueList = new ArrayList<>();
        for (int i = 0; i < fct003Res.getMediateProprietyList().size(); i++) {
            IntermediaryValue intermediaryValue = new IntermediaryValue();
            // FCT003.媒介可否リスト.取引種別 = "5" || FCT003.媒介可否リスト.取引種別 = "6" 
            // || FCT003.媒介可否リスト.取引種別 = "8" || FCT003.媒介可否リスト.取引種別 = "7" の場合
            String trade = fct003Res.getMediateProprietyList().get(i).getTradeClass();
            if (StringUtils.equals(trade, TradeClass.BUY.key) || StringUtils.equals(trade, TradeClass.SELL.key)
                    || StringUtils.equals(trade, TradeClass.GENBIKI.key)
                    || StringUtils.equals(trade, TradeClass.GENWATASHI.key)) {
                // レスポンス.媒介可否リスト.媒介可否に「FCT003.媒介可否リスト.媒介可否」を設定
                intermediaryValue
                        .setIntermediaryValue(fct003Res.getMediateProprietyList().get(i).getMediatePropriety());
                
                // レスポンス.媒介可否リスト.取引種別に「FCT003.媒介可否リスト.取引種別」を設定
                intermediaryValue.setTradeClass(fct003Res.getMediateProprietyList().get(i).getTradeClass());
                
                intermediaryValueList.add(intermediaryValue);
            }
        }
        resDto.setIntermediaryValueList(intermediaryValueList);
        
        // "リクエストパラメーター(
        QueryMarginContract1InData api001ReqData = new QueryMarginContract1InData();
        // 部店コード,
        api001ReqData.setButenCd(cc.getButenCode());
        // 口座番号,
        api001ReqData.setKozaNo(String.format("%7s", cc.getAccountNumber()).replace(" ", "0"));
        // 銘柄コード,
        api001ReqData.setBrandCd(dtoReq.getBrandCode());
        // 新規売買区分,
        api001ReqData.setOpenTradeKbn(dtoReq.getOpenTradeKbn());
        // 新規市場,
        api001ReqData.setOpenMarket(ALL);
        // 弁済期限,
        api001ReqData.setPaymentLimit(dtoReq.getPaymentDeadline());
        // リクエストタイプ,
        api001ReqData.setRequestType(dtoReq.getSortOrder());
        // 検索番号指定ＦＲＯＭ,
        api001ReqData.setRefFrom(ONE);
        // 検索番号指定ＴＯ,
        api001ReqData.setRefTo(FIFTY);
        // )をセット"
        QueryMarginContract1In api001Req = new QueryMarginContract1In();
        api001Req.setIndata(api001ReqData);
        
        // API001.(建玉残高明細)を呼び出す
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        List<QueryMarginContract1OutData> api001ResList = null;
        api001ResList = apiWrapper.queryMarginContract1List(api001Req);
        for (QueryMarginContract1OutData api001Res : api001ResList) {
            apiErrorUtil.checkApiResponse(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage());
        }
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        QueryMarginContract1OutData api001Res = new QueryMarginContract1OutData();
        // API001の結果をapi001ResTmpにセットする
        if (api001ResList != null) {
            // Beanコピー
            BeanUtils.copyProperties(api001Res, api001ResList.get(0));
            if (MAX_QUERY_MARGIN_CONTRACT1 < Integer.parseInt(api001ResList.get(0).getHitNumber())) {
                int count = 1;
                while (true) {
                    if (count == api001ResList.size()) {
                        break;
                    }
                    for (QueryMarginContract1OoutVec tmp : api001ResList.get(count).getQueryMarginContract1Data()) {
                        api001Res.getQueryMarginContract1Data().add(tmp);
                    }
                    count++;
                }
                
            }
        }
        
        // 東証リスト
        List<TokyoSecurity> tokyoSecurityList = new ArrayList<>();
        // PTSリスト
        List<Pts> ptsList = new ArrayList<>();
        // 銘柄建玉明細リスト
        List<BrandPositionDetail> brandPositionDetailList = new ArrayList<>();
        
        // 銘柄建玉明細.評価額（リアル）の合計
        BigDecimal sumValuationRealTo = BigDecimal.ZERO;
        BigDecimal sumValuationRealPts = BigDecimal.ZERO;
        // 銘柄建玉明細.評価損益（リアル）の合計
        BigDecimal sumDomesticPositionValuationTotalRealTo = BigDecimal.ZERO;
        BigDecimal sumDomesticPositionValuationTotalRealPts = BigDecimal.ZERO;
        // FCT020レスポンス
        OutputFct020Dto fct020Res = new OutputFct020Dto();
        
        // 建市場存在チェックを行う(繰り返し分チェック)
        for (int index = 0; index < api001Res.getQueryMarginContract1Data().size(); index++) {
            QueryMarginContract1OoutVec apiOut = api001Res.getQueryMarginContract1Data().get(index);
            
            // 銘柄建玉明細
            BrandPositionDetail brandPositionDetail = new BrandPositionDetail();
            
            // API001[index].建市場 が 'TKY'の場合
            if (StringUtils.equals(apiOut.getBargainMarket(), TKY)) {
                // 建市場_東証フラグ に '1'を設定する
                resDto.setTokyoSecurityFlag(ONE);
                
                // else if (API001[index].建市場 が 'PTS'の場合)
            } else if (StringUtils.equals(apiOut.getBargainMarket(), PTS)) {
                // 建市場_PTSフラグ に'1'を設定する
                resDto.setPtsFlag(ONE);
            }
            
            // 銘柄建玉明細.期日短縮区分の編集　※クライアント側で実施
            // 
            // 　銘柄建玉明細リスト.返済期限の編集を行う
            // ※API001.期日短縮区分が'1'の場合、赤色表示　※クライアント側で実施"
            // API001[index].決済期日 が '99991231'の場合
            if (StringUtils.equals(apiOut.getLastTradeDate(), FINALDATE)) {
                // 銘柄建玉明細リスト.返済期限に '無制限'を設定
                brandPositionDetail.setLastTradeDate(UNLIMITED);
                
                // else if (API001[index].決済期日 が スペースの場合) 
            } else if (apiOut.getLastTradeDate().isBlank()) {
                // 　銘柄建玉明細リスト.返済期限に ----/--/--を設定
                brandPositionDetail.setLastTradeDate(BLANKDATE);
            } else {
                // 銘柄建玉明細リスト.返済期限にAPI001[index].決済期日を設定"
                brandPositionDetail.setLastTradeDate(apiOut.getLastTradeDate());
            }
            
            // 銘柄建玉明細リスト.親株新規約定日の編集を行う
            // API001[index].親株新規約定日 が スペースの場合
            if (apiOut.getOrgNewTradeDate().isBlank()) {
                //　銘柄建玉明細リスト.親株新規約定日に----/--/--を設定
                brandPositionDetail.setParentStockTradeDate(BLANKDATE);
            } else {
                // 銘柄建玉明細リスト.親株新規約定日にAPI001[index].親株新規約定日を設定"
                brandPositionDetail.setParentStockTradeDate(apiOut.getOrgNewTradeDate());
            }
            
            // 銘柄建玉明細リスト.担保の編集を行う
            // API001[index].新規建保証金率(5桁) が 0の場合
            if (Integer.parseInt(apiOut.getBargainNeedSecRate().trim()) == 0) {
                // 銘柄建玉明細リスト.担保に'(--%)' を設定
                brandPositionDetail.setSecurity(BLANKPERCENT);
            } else {
                // 銘柄建玉明細リスト.担保に'('+上3桁(整数部)+'%)'を設定
                apiOut.setBargainNeedSecRate(String.format("%5s", apiOut.getBargainNeedSecRate()));
                String percent = apiOut.getBargainNeedSecRate().substring(0, 3);
                brandPositionDetail.setSecurity("(" + percent.trim() + "%)");
            }
            
            // 銘柄建玉明細リスト.新規単価の編集を行う
            // 銘柄建玉明細リスト.新規単価にAPI001[index].取得単価÷100を設定"
            BigDecimal openPrice = new BigDecimal(apiOut.getOpenPrice().trim());
            brandPositionDetail.setNewPrice(openPrice.divide(new BigDecimal("100")).toString());
            
            // ・国内株式リアル時価取得
            // （API001で取得した１件目のデータに対してのみ取得する。）"
            if (index == 0) {
                InputFct020Dto fct020Req = new InputFct020Dto();
                fct020Req.setBrandCode(dtoReq.getBrandCode());
                fct020Req.setRightType(apiOut.getStRightId());
                fct020Req.setCtNightBatchEndFlag(api001Res.getNightBatchEndFlg());
                // FCT020（国内株リアル時価取得）を呼び出す
                fct020Res = fct020.getData(fct020Req);
            }
            
            // 銘柄建玉明細リスト.評価額（リアル）の編集を行う
            // FCT020.評価用現在値の値がNullの場合
            if (StringUtil.isNullOrEmpty(fct020Res.getCurrentValueForEvaluation())) {
                // 銘柄建玉明細.評価額（リアル）へ"-"を設定
                brandPositionDetail.setValuationReal(null);
            } else {
                // 銘柄建玉明細.評価額（リアル）へFCT020.評価用現在値　ｘ API001[index].残高数量を設定"
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation().trim());
                BigDecimal contPosition = new BigDecimal(apiOut.getContPosition().trim());
                BigDecimal multi = currentValueForEvaluation.multiply(contPosition);
                // 銘柄建玉明細リスト.評価額（リアル）
                brandPositionDetail.setValuationReal(multi.toString());
                // 評価額（リアル）の合計額計算
                if (StringUtils.equals(api001Res.getQueryMarginContract1Data().get(index).getBargainMarket(), TKY)) {
                    sumValuationRealTo = sumValuationRealTo.add(multi);
                } else {
                    sumValuationRealPts = sumValuationRealPts.add(multi);
                }
            }
            
            // 銘柄建玉明細リスト.評価損益（リアル）の編集を行う
            // FCT020.評価用現在値の値がNullの場合
            if (StringUtil.isNullOrEmpty(fct020Res.getCurrentValueForEvaluation())) {
                // 銘柄建玉明細リスト.評価損益（リアル）へ"-"を設定
                brandPositionDetail.setDomesticPositionValuationTotalReal(null);
                
                // else if (リクエスト.新規売買区分 が '0'(買建)の場合)
            } else if (StringUtils.equals(dtoReq.getOpenTradeKbn(), PURCHASE)) {
                // 銘柄建玉明細リスト.評価損益（リアル）へ
                // FCT020.評価用現在値 × API001[index].残高数量 
                // － API001[index].建玉金額 
                // － API001[index].諸経費を設定"
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation());
                BigDecimal contPosition = new BigDecimal(apiOut.getContPosition().trim());
                BigDecimal openAmount = new BigDecimal(apiOut.getOpenAmount().trim());
                BigDecimal cost = new BigDecimal(apiOut.getCost().trim());
                // 計算処理
                BigDecimal totalReal = currentValueForEvaluation.multiply(contPosition).subtract(openAmount)
                        .subtract(cost);
                brandPositionDetail.setDomesticPositionValuationTotalReal(totalReal.toString());
                // 評価損益（リアル）の合計額計算
                if (StringUtils.equals(api001Res.getQueryMarginContract1Data().get(index).getBargainMarket(), TKY)) {
                    sumDomesticPositionValuationTotalRealTo = sumDomesticPositionValuationTotalRealTo.add(totalReal);
                } else {
                    sumDomesticPositionValuationTotalRealPts = sumDomesticPositionValuationTotalRealPts.add(totalReal);
                }
               
                // else if (リクエスト.新規売買区分='1'(売建)の場合)
            } else if (StringUtils.equals(dtoReq.getOpenTradeKbn(), SALE)) {
                // 銘柄建玉明細リスト.評価損益（リアル）へ
                // API001[index].建玉金額 － FCT020.評価用現在値
                // × API001[index].残高数量 
                // － API001[index].諸経費を設定"
                BigDecimal openAmount = new BigDecimal(apiOut.getOpenAmount().trim());
                BigDecimal currentValueForEvaluation = new BigDecimal(fct020Res.getCurrentValueForEvaluation());
                BigDecimal contPosition = new BigDecimal(apiOut.getContPosition().trim());
                BigDecimal cost = new BigDecimal(apiOut.getCost().trim());
                // 計算処理
                BigDecimal totalReal = openAmount.subtract(currentValueForEvaluation.multiply(contPosition))
                        .subtract(cost);
                brandPositionDetail.setDomesticPositionValuationTotalReal(totalReal.toString());
                // 評価損益（リアル）の合計額計算
                if (StringUtils.equals(api001Res.getQueryMarginContract1Data().get(index).getBargainMarket(), TKY)) {
                    sumDomesticPositionValuationTotalRealTo = sumDomesticPositionValuationTotalRealTo.add(totalReal);
                } else {
                    sumDomesticPositionValuationTotalRealPts = sumDomesticPositionValuationTotalRealPts.add(totalReal);
                }
            }
            
            // 取引ボタン（「返売」「現引」「返買」「現渡」）の表示制御
            // 注文可能数量 ＝　API001[index].残高数量　－　API001[index].返済注文済未出来数量
            Long quantity = Long.parseLong(apiOut.getContPosition().trim())
                    - Long.parseLong(apiOut.getUnactualQuantity().trim());
            brandPositionDetail.setMaxOrderableQuantity(quantity.toString());
            // ※表示制御はクライアント側で判定実施する

            TokyoSecurity tokyoSecurity = new TokyoSecurity();
            
            // 東証リスト.市場
            tokyoSecurity.setMarket(TOKYO_SECURITY);
            // 東証リスト.建玉金額合計
            tokyoSecurity.setTotalPrice(api001Res.getContractTotal());
            // 東証リスト.評価額合計（前日）
            tokyoSecurity.setValuationTotalPreviousDay(api001Res.getValueTotal());
            // 東証リスト.諸費用合計
            tokyoSecurity.setExpensesTotal(api001Res.getCostTotal());
            // 東証リスト.評価損益合計（前日）
            tokyoSecurity.setDomesticPositionValuationTotalPreviousDay(api001Res.getUnrealizedPlTotal());
            // 東証リスト
            tokyoSecurityList.add(tokyoSecurity);
            
            Pts pts = new Pts();
            // PTSリスト.市場
            pts.setMarket(FIXPTS);
            // PTSリスト.建玉金額合計
            pts.setTotalPrice(api001Res.getContractTotalPts());
            // PTSリスト.評価額合計（前日）
            pts.setValuationTotalPreviousDay(api001Res.getValueTotalPts());
            // PTSリスト.諸費用合計
            pts.setExpensesTotal(api001Res.getCostTotalPts());
            // PTSリスト.評価損益合計（前日）
            pts.setDomesticPositionValuationTotalPreviousDay(api001Res.getUnrealizedPlTotalPts());
            // PTSリスト
            ptsList.add(pts);
            
            // 銘柄建玉明細リスト.市場
            brandPositionDetail.setMarket(apiOut.getBargainMarket());
            // 銘柄建玉明細リスト.新規建日
            brandPositionDetail.setConstructionDate(apiOut.getOpenTradeDate());
            // 期日短縮区分
            brandPositionDetail.setDueDateShortenClassification(apiOut.getChangeLastTradeKbn());
            // 銘柄建玉明細リスト.特定・一般(担保)
            brandPositionDetail.setAccountType(apiOut.getTokuteiContractId());
            // 銘柄建玉明細リスト.担保規制内容
            brandPositionDetail.setCollateralRegulations(apiOut.getRegulateKbn());
            // 銘柄建玉明細リスト.建株数
            brandPositionDetail.setContPositionTotal(apiOut.getContPosition());
            // 銘柄建玉明細リスト.注文中
            brandPositionDetail.setUnactualQuantity(apiOut.getUnactualQuantity());
            // 銘柄建玉明細リスト.評価単価（前日）
            brandPositionDetail.setDayBeforeValuationPrice(apiOut.getMarketPrice());
            // 銘柄建玉明細リスト.現在値（リアル）
            brandPositionDetail.setLatestPrice(fct020Res.getCurrentValueForEvaluation());
            // 銘柄建玉明細リスト.建玉金額
            brandPositionDetail.setOpenInterestAmount(apiOut.getOpenAmount());
            // 銘柄建玉明細リスト.評価額（前日）
            brandPositionDetail.setValuationPreviousDay(apiOut.getMarketContractValue());
            // 銘柄建玉明細リスト.諸費用
            brandPositionDetail.setCharge(apiOut.getCost());
            // 銘柄建玉明細リスト.評価損益（前日）
            brandPositionDetail.setDomesticPositionValuationTotalPreviousDay(apiOut.getUnrealizedPl());
            
            // 銘柄建玉明細リスト.現金拘束金
            brandPositionDetail.setCashBond(apiOut.getCashHold());
            // 銘柄建玉明細リスト.新規建玉指定番号
            brandPositionDetail.setNewOpenInterestNumber(apiOut.getOpenContractNo());
            // 銘柄建玉明細リスト.親株新規約定日２
            brandPositionDetail.setHiddenItemParentStockTradeDate(apiOut.getOrgNewTradeDate());
            // 銘柄建玉明細リスト
            brandPositionDetailList.add(brandPositionDetail);
        }
        
        // ⑥銘柄別建玉一覧合計部情報を算出する
        for (int index = 0; index < api001Res.getQueryMarginContract1Data().size(); index++) {
            // 算出値を設定する
            // API001[index].建市場 が'TKY'の場合
            if (StringUtils.equals(api001Res.getQueryMarginContract1Data().get(index).getBargainMarket(), TKY)) {
                // 東証.評価額合計（リアル）へ銘柄別建玉一覧.評価額（リアル）の合計値を設定
                tokyoSecurityList.get(index).setValuationTotalReal(sumValuationRealTo.toString());
                // 東証.評価損益合計（リアル）へ銘柄別建玉一覧.評価損益（リアル）の合計値を設定
                tokyoSecurityList.get(index)
                        .setDomesticPositionValuationTotalReal(sumDomesticPositionValuationTotalRealTo.toString());
                
                // else if (API001[index].建市場 が 'PTS'の場合)
            } else if (StringUtils.equals(api001Res.getQueryMarginContract1Data().get(index).getBargainMarket(), PTS)) {
                // PTS.評価額合計（リアル）へ柄別建玉一覧.評価額（リアル）の合計値を設定
                ptsList.get(index).setValuationTotalReal(sumValuationRealPts.toString());
                // PTS.評価損益合計（リアル）へ銘柄別建玉一覧.評価損益（リアル）の合計値を設定
                ptsList.get(index)
                        .setDomesticPositionValuationTotalReal(sumDomesticPositionValuationTotalRealPts.toString());
            }
        }
        
        // レスポンスに値セット
        resDto.setTokyoSecurityList(tokyoSecurityList);
        resDto.setPtsList(ptsList);
        resDto.setBrandPositionDetailList(brandPositionDetailList);
        
        // 銘柄コード
        resDto.setBrandCode(dtoReq.getBrandCode());
        // 銘柄名
        resDto.setBrandName(dtoReq.getBrandName());
        // 建玉区分（新規売買区分）
        resDto.setNewCreditOrderType(dtoReq.getOpenTradeKbn());
        // 建玉区分（弁済期限）
        resDto.setSpecificPositionTypePaymentDeadline(dtoReq.getPaymentDeadline());
        // 新規売買区分
        resDto.setOpenTradeKbn(dtoReq.getOpenTradeKbn());
        // 弁済期限
        resDto.setPaymentDeadline(dtoReq.getPaymentDeadline());
        // 検索結果件数
        resDto.setSearchResultCount(api001Res.getHitNumber());
        resDtoList.add(resDto);
        
        // レスポンスをコントローラーに返却する。
        dtoRes = apiErrorUtil.createDataList(resDtoList, null);
        
        return dtoRes;
    }
    
}
