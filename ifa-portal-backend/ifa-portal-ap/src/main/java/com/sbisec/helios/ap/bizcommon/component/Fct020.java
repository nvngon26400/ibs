package com.sbisec.helios.ap.bizcommon.component;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.extapi.ApiError;
import com.sbisec.helios.ap.bizcommon.dao.Fct020Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct020Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct020Dto;
import com.sbisec.helios.ap.common.util.HeracrossApiWrapper;

import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshot;
import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;

/**
 * 共通関数：FCT020 国内株リアル時価取得
 *
 * @author SCSK
 */

@Component
public class Fct020 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct020.class);
    
    @Autowired
    private HeracrossApiWrapper heracrossApiWrapper;
    
    @Autowired
    private Fct020Dao dao;
    
    /** クロージングランONタイム　*/
    private static final LocalTime CLOSING_RUN_ON_TIME = LocalTime.parse("08:30:00");
    
    /** クロージングランOFFタイム　*/
    private static final LocalTime CLOSING_RUN_OFF_TIME = LocalTime.parse("21:00:00");
    
    /** 市場コード:東証　*/
    private static final String MARKET_CODE_TOUSYOU = "0";
    
    /** 市場コード:名証　*/
    private static final String MARKET_CODE_MEISYOU = "2";
    
    /** 市場コード:福証*/
    private static final String MARKET_CODE_FUKUSYOU = "6";
    
    /** 市場コード:札証　*/
    private static final String MARKET_CODE_SATUSYOU = "8";
    
    /** 優先市場コード:東証　*/
    private static final String PREFERRED_BRAND_CODE_TOUSYOU = "T";
    
    /** 優先市場コード:名証　*/
    private static final String PREFERRED_BRAND_CODE_MEISYOU = "M";
    
    /** 優先市場コード:福証　*/
    private static final String PREFERRED_BRAND_CODE_FUKUSYOU = "FK";
    
    /** 優先市場コード:札証　*/
    private static final String PREFERRED_BRAND_CODE_SATUSYOU = "S";
    
    /** 権利区分:売買停止銘柄 */
    private static final String RIGHT_TYPE_BUYSELL_STOP_BRAND = "1";
    
    /** 権利区分:通常銘柄 */
    private static final String RIGHT_TYPE_NORMAL = " ";
    
    /** 権利区分:分割(大引け) */
    private static final String RIGHT_TYPE_DIVISINO_CLOSED = "2";
    
    /** 権利区分:分割(夜間) */
    private static final String RIGHT_TYPE_DIVISINO_NIGHT = "3";
    
    /** 権利区分:併合(大引け) */
    private static final String RIGHT_TYPE_MERGING_CLOSED = "4";
    
    /** 権利区分:併合(夜間) */
    private static final String RIGHT_TYPE_MERGING_NIGHT = "5";
    
    /** 適用値区分:基準値 */
    private static final String APPLY_PRICE_TYPE_BASE = "1";
    
    /** 適用値区分:現在値 または 基準値  */
    private static final String APPLY_PRICE_TYPE_CURRENT_OR_BASE = "2";
    
    /** クロージングラン完了フラグ: 完了*/
    private static final String CLOSING_RUN_FINISH_FLG_FINISH = "1";
    
    /** クロージングラン完了フラグ: 未完了*/
    private static final String CLOSING_RUN_FINISH_FLG_IMPERFECT = "0";
    
    /** CT夜間バッチ終了フラグ:夜間バッチ終了 */
    private static final String CT_NIGHT_BATCHEND_FLAG_END = "1";
    
    /** CT夜間バッチ終了フラグ:夜間バッチ前 */
    private static final String CT_NIGHT_BATCHEND_FLAG_BEFORE = "0";
    
    /**
     * 国内株リアル時価取得
     *
     * @param input リクエストDto
     * @return レスポンスDto
     */
    public OutputFct020Dto getData(InputFct020Dto input) {
        
        //　デバッグ　ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct020.getData");
        }
        
        //リクエストの必須パラメタチェック
        if (input.getBrandCode() == null) {
            return new OutputFct020Dto();
        }
        
        OutputFct020Dto resDto = new OutputFct020Dto();
        
        // レスポンス.銘柄コードに、リクエスト.銘柄コードを設定する。
        resDto.setBrandCode(input.getBrandCode());
        
        // ①  選択市場コードを取得する。
        String selectMarketCode = input.getMarketCode();
        if (selectMarketCode != null && (StringUtils.equals(Fct020.MARKET_CODE_TOUSYOU, selectMarketCode)
                || StringUtils.equals(Fct020.MARKET_CODE_MEISYOU, selectMarketCode)
                || StringUtils.equals(Fct020.MARKET_CODE_FUKUSYOU, selectMarketCode)
                || StringUtils.equals(Fct020.MARKET_CODE_SATUSYOU, selectMarketCode))) {
            // リクエスト.市場コード ≠ null の場合、
            
            // 選択市場コード = リクエスト.市場コード
            selectMarketCode = input.getMarketCode();
            
            // レスポンス.市場コード = リクエスト.市場コード
            resDto.setMarketCode(selectMarketCode);
            // 次の処理へ。
        } else {
            //　上記以外の場合
            
            // DBから 主要取引所コード、SBI主要取引所コード を取得する。
            Fct020Sql001RequestModel sql001Req = new Fct020Sql001RequestModel();
            sql001Req.setBrandCode(input.getBrandCode());
            
            List<Fct020Sql001ResponseModel> sql001ResList = dao.getPreExCode(sql001Req);
            
            String preferredBrandCode = null;
            if (sql001ResList == null || sql001ResList.size() != 1) {
                // SQL001の取得件数が1件以外の場合、
                // 優先市場コード = null
                preferredBrandCode = null;
            } else {
                // SQL001の取得件数が1件の場合、以下の処理を行う。
                Fct020Sql001ResponseModel sql001Res = sql001ResList.get(0);
                
                if (sql001Res.getPreExCodeSbi() != null) {
                    // SQL001.SBI主要取引所コード ≠ nullの場合
                    // 優先市場コード = SQL001.SBI主要取引所コード
                    preferredBrandCode = sql001Res.getPreExCodeSbi();
                } else {
                    // SQL001.SBI主要取引所コード = nullの場合
                    // 優先市場コード = SQL001.主要取引所コード
                    preferredBrandCode = sql001Res.getPreExCode();
                }
            }
            
            LOGGER.info("DEBUG: preferredBrandCode: [" + preferredBrandCode + "]");
            
            // 優先市場コードに以下の変換処理を行い、変換値を 選択市場コード と レスポンス.市場コード に設定する。
            if (StringUtils.equals(Fct020.PREFERRED_BRAND_CODE_TOUSYOU, preferredBrandCode)) {
                // 優先市場コード = 'T':東証 の場合、'0':東証
                selectMarketCode = "0";
            } else if (StringUtils.equals(Fct020.PREFERRED_BRAND_CODE_MEISYOU, preferredBrandCode)) {
                // 優先市場コード = 'M':名証 の場合、'2':名証
                selectMarketCode = "2";
            } else if (StringUtils.equals(Fct020.PREFERRED_BRAND_CODE_FUKUSYOU, preferredBrandCode)) {
                // 優先市場コード = 'FK':福証 の場合、'6':福証
                selectMarketCode = "6";
            } else if (StringUtils.equals(Fct020.PREFERRED_BRAND_CODE_SATUSYOU, preferredBrandCode)) {
                // 優先市場コード = 'S':札証 の場合、'8':札証
                selectMarketCode = "8";
            } else {
                // 上記以外の場合、 null 
                selectMarketCode = null;
            }
            resDto.setMarketCode(selectMarketCode);
        }
        
        // ②  選択市場コードチェックする。
        if (selectMarketCode == null) {
            // 選択市場コード ≠ null である場合、
            // 次の処理へ。
            // 上記以外の場合、
            // レスポンス.評価用現在値 = null、  レスポンスを返す。
            
            resDto.setCurrentValueForEvaluation(null);
            return resDto;
        }
        
        // ③  現在値 を取得する。
        String currentPrice = null;
        try {
            String[] inputStringArray = { selectMarketCode, input.getBrandCode().trim() };
            List<String[]> inputStringList = new ArrayList<String[]>();
            inputStringList.add(inputStringArray);
            String inputString = heracrossApiWrapper.convertInputstring(inputStringList);
            
            RealQuoteSnapshotOut outSymbol = heracrossApiWrapper.getRealQuoteSnapshot(inputString);
            if (outSymbol != null && (outSymbol.getFeedData() != null && outSymbol.getFeedData().size() > 0)) {
                RealQuoteSnapshot apiOut = outSymbol.getFeedData().get(0);
                currentPrice = (apiOut == null) ? null : apiOut.getCurPrice();
                if (currentPrice == null || new BigDecimal(currentPrice).compareTo(BigDecimal.ZERO) == 0) {
                    // API001.現在値 = null または API001.現在値 = 0 の場合、
                    // 現在値 = null
                    currentPrice = null;
                    
                    // 上記以外の場合、
                    // 現在値 = API001.現在値
                }
            }
        } catch (ApiError e) {
            LOGGER.error("API Exception occured.");
            LOGGER.info("API Exception occured.", e);
            // 現在値 = null
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            // 現在値 = null
        }
        
        LOGGER.info("DEBUG: currentPrice: [" + currentPrice + "]");
        
        // ④  基準値を取得する。
        Fct020Sql002RequestModel sql002Req = new Fct020Sql002RequestModel();
        sql002Req.setBrandCode(input.getBrandCode());
        sql002Req.setSelectMarketCode(selectMarketCode);
        
        List<Fct020Sql002ResponseModel> sql002ResList = dao.getBasePrice(sql002Req);
        BigDecimal basePrice = null;
        if (sql002ResList != null && sql002ResList.size() == 1) {
            Fct020Sql002ResponseModel sql002Res = sql002ResList.get(0);
            if (sql002Res != null && sql002Res.getBasePrice().doubleValue() != 0) {
                // SQL002の取得件数が1件 かつ SQL002.翌日基準値 ≠ null かつ SQL002.翌日基準値 ≠ 0の場合、
                // 基準値 = SQL002.翌日基準値
                basePrice = sql002Res.getBasePrice();
            } else {
                // 上記以外の場合、
                // 基準値 = null
                basePrice = null;
            }
        } else {
            // 上記以外の場合、
            // 基準値 = null
            basePrice = null;
        }
        
        LOGGER.info("DEBUG: BasePrice: [" + basePrice + "]");
        
        // ⑤  クロージングラン完了フラグを算出する。
        // クロージングランON時刻 = 08:30, クロージングランOFF時刻 = 21:00 を設定する。
        // 現在時刻を取得する。
        LocalTime nowTime = LocalTime.now();
        String closingRunFinishFlg = null;
        if (nowTime.compareTo(Fct020.CLOSING_RUN_ON_TIME) >= 0 && nowTime.compareTo(Fct020.CLOSING_RUN_OFF_TIME) < 0) {
            // 現在時刻がクロージングランON時刻 ~ クロージングランOFF時刻 の間の場合
            // （クロージングランON時刻ちょうどを含む、クロージングランOFF時刻ちょうどを含まない）"
            // クロージングラン完了フラグ = '1':完了
            closingRunFinishFlg = "1";
        } else {
            // 現在時刻がクロージングランON時刻 ~ クロージングランOFF時刻 の間以外の場合
            // （クロージングランON時刻ちょうどを含まない、クロージングランOFF時刻ちょうどを含む）"
            // クロージングラン完了フラグ = '0':未完了
            closingRunFinishFlg = "0";
        }

        LOGGER.info("DEBUG: closingRunFinishFlg: [" + closingRunFinishFlg + "]");
        
        // ⑥  適用値区分 を算出する。
        String applyPriceType = null;
        if (input.getRightType() == null || input.getCtNightBatchEndFlag() == null) {
            //  リクエスト.権利区分 = null または リクエスト.CT夜間バッチ終了フラグ = null の場合
            //    適用値区分 = '2':現在値 または 基準値
            applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
        } else if (StringUtils.equals(Fct020.RIGHT_TYPE_BUYSELL_STOP_BRAND, input.getRightType())) {
            // リクエスト.権利区分 = '1':売買停止銘柄 の場合
            // レスポンス.評価用現在値 = null、  レスポンスを返す。
            resDto.setCurrentValueForEvaluation(null);
            return resDto;
        } else {
            // 適用値区分 を 「別紙_適用値区分算出表」シートの条件をもとに算出し、設定する。
            applyPriceType = this.getApplyPriceType(input.getRightType(), closingRunFinishFlg,
                    input.getCtNightBatchEndFlag());
        }
        
        LOGGER.info("DEBUG: applyPriceType: [" + applyPriceType + "]");
        
        // ⑦  評価用現在値 を設定する。
        if (StringUtils.equals(Fct020.APPLY_PRICE_TYPE_BASE, applyPriceType)) {
            // 適用値区分 = '1':基準値 の場合
            // レスポンス.評価用現在値 = 基準値、  レスポンスを返す。
            resDto.setCurrentValueForEvaluation((basePrice != null) ? basePrice.toString() : null);
        } else if (StringUtils.equals(Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE, applyPriceType)) {
            // 適用値区分 = '2':現在値 または 基準値 の場合
            
            if (currentPrice != null) {
                // 現在値 ≠ null の場合
                // レスポンス.評価用現在値 = 現在値、  レスポンスを返す。
                resDto.setCurrentValueForEvaluation(currentPrice);
            } else {
                // 上記以外の場合、
                // レスポンス.評価用現在値 = 基準値、  レスポンスを返す。
                resDto.setCurrentValueForEvaluation((basePrice != null) ? basePrice.toString() : null);
            }
        } else {
            // 上記以外の場合、
            // レスポンス.評価用現在値 = null、  レスポンスを返す。
            resDto.setCurrentValueForEvaluation(null);
        }
        return resDto;
    }
    
    /**
     * 適用値区分の算出
     *
     * @param rightType 権利区分
     * @param closingRunFinishFlg クロージングラン完了フラグ
     * @param ctNightBatchEndFlag CT夜間バッチ終了フラグ
     * @return 適用値区分
     */
    private String getApplyPriceType(String rightType, String closingRunFinishFlg, String ctNightBatchEndFlag) {
    	
    	LOGGER.info("DEBUG: rightType: [" + rightType + "]");
    	LOGGER.info("DEBUG: closingRunFinishFlg: [" + closingRunFinishFlg + "]");
    	LOGGER.info("DEBUG: ctNightBatchEndFlag: [" + ctNightBatchEndFlag + "]");
        
        String applyPriceType = null;
        
        // リクエスト.権利区分
        if (StringUtils.equals(Fct020.RIGHT_TYPE_NORMAL, rightType)) {
            // △:通常銘柄
            
            // クロージングラン完了フラグ
            if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_FINISH, closingRunFinishFlg)) {
                // '1':完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else {
                // '0':未完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            }
        } else if (StringUtils.equals(Fct020.RIGHT_TYPE_DIVISINO_CLOSED, rightType)) {
            // '2':分割(大引け)
            
            // クロージングラン完了フラグ
            if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_FINISH, closingRunFinishFlg)) {
                // '1':完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_IMPERFECT, closingRunFinishFlg)) {
                // '0':未完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals("1", ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else {
                // 上記以外
                applyPriceType = null;
            }
        } else if (StringUtils.equals(Fct020.RIGHT_TYPE_DIVISINO_NIGHT, rightType)) {
            // '3':分割(夜間)
            
            // クロージングラン完了フラグ
            if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_FINISH, closingRunFinishFlg)) {
                // '1':完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_IMPERFECT, closingRunFinishFlg)) {
                // '0':未完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals("1", ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else {
                // 上記以外
                applyPriceType = null;
            }
        } else if (StringUtils.equals(Fct020.RIGHT_TYPE_MERGING_CLOSED, rightType)) {
            // '4':併合(大引け)
            
            // クロージングラン完了フラグ
            if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_FINISH, closingRunFinishFlg)) {
                // '1':完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_IMPERFECT, closingRunFinishFlg)) {
                // '0':未完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else {
                // 上記以外
                applyPriceType = null;
            }
        } else if (StringUtils.equals(Fct020.RIGHT_TYPE_MERGING_NIGHT, rightType)) {
            // 5':併合(夜間)
            
            // クロージングラン完了フラグ
            if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_FINISH, closingRunFinishFlg)) {
                // '1':完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else if (StringUtils.equals(Fct020.CLOSING_RUN_FINISH_FLG_IMPERFECT, closingRunFinishFlg)) {
                // '0':未完了
                
                // リクエスト.CT夜間バッチ終了フラグ
                if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_END, ctNightBatchEndFlag)) {
                    // '1':夜間バッチ終了
                    
                    // 適用値区分の設定値  '1':基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_BASE;
                } else if (StringUtils.equals(Fct020.CT_NIGHT_BATCHEND_FLAG_BEFORE, ctNightBatchEndFlag)) {
                    // '0':夜間バッチ前
                    
                    // 適用値区分の設定値  '2':現在値 または 基準値
                    applyPriceType = Fct020.APPLY_PRICE_TYPE_CURRENT_OR_BASE;
                } else {
                    // 上記以外
                    applyPriceType = null;
                }
            } else {
                // 上記以外
                applyPriceType = null;
            }
        } else {
            // 上記以外
            applyPriceType = null;
        }
        
        LOGGER.info("DEBUG: applyPriceType: [" + applyPriceType + "]");
        
        return applyPriceType;
    }
}
