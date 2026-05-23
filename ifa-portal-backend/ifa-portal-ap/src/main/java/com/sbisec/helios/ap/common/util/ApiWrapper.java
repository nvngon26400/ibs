package com.sbisec.helios.ap.common.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.extapi.ApiIOException;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.exception.ApiError;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.Config;
import jp.co.sbisec.pcenter.api.HttpApiWrapper;
import jp.co.sbisec.pcenter.dto.DtoIn;
import jp.co.sbisec.pcenter.dto.yanap.*;

@Component
public class ApiWrapper {
    
    @Autowired
    private CodeListService codelistservice;
    
    private static ObjectMapper jacksonObjectMapper = new ObjectMapper();
    
    /* For API CONFIG */
    private static final String CONF_FILE = "pcenter.yml";
    
    /** 検索番号int->String変換用 */
    private static final DecimalFormat dcf = new DecimalFormat("00000");
    
    /** 口座番号int->String変換用 */
    private static final DecimalFormat dcfAcc = new DecimalFormat("0000000");
    
    private static final Logger logger = LoggerFactory.getLogger(ApiWrapper.class);
    
    private static final long SUCCESS_CODE = 0L;

    /** APIレスポンス 種別：正常 */
    private static final String API_RESPONSE_SHUBETU_SUCCESS = String.format("%5s", StringUtil.EMPTY_STRING);   
    /** APIレスポンス 種別：DB */
    private static final String API_RESPONSE_SHUBETU_DB = String.format("%-5s", "ORA");    
    /** APIレスポンス 種別：MIDDLEWARE */
    private static final String API_RESPONSE_SHUBETU_MW = String.format("%-5s", "MDL");
    /** 異常終了判定用 WARNIG*/
    private static final String CODE_WARN = "W";
    /** 異常終了判定用 SYSTEMERROR*/
    private static final String CODE_FAIL = "E";
     
    //private static final String SUCCESS_CODE_OUTDATA = " ";
    
    /* API CONFIG Load */
    static {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
          final URL url = classLoader.getResource(CONF_FILE);
          try {
              if (url != null) {
                  Config.init(url.getPath());
              } else {
                  throw new Exception("ec gateway config file not found. [" + CONF_FILE + "]");
              }
          } catch (Exception e) {
              throw new RuntimeException(e);
          }

        jacksonObjectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      }

    /**
     * NRI API結果を検証する．
     * 
     * @param code 結果コード
     * @param message メッセージ
     * @throws Exception 異常
     */
    private boolean checkApplicationValidationResult(String shubetu, String code, String message) throws Exception {
        
        if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_SUCCESS)) {
            // 正常終了
            return true;
        
        } else if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_DB) || 
                   StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_MW) || 
                   code.startsWith(CODE_FAIL)) {            
            // エラー
            logger.info("ApiError at API call. code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            return false;
        
        } else if (code.startsWith(CODE_WARN)) {
            // 警告
            logger.info("ApiWarning at API call. code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            return true;
            
        } else  {
            // 想定外ケースの為例外をスロー
            logger.info("Exception at API call. shubetu:[" + shubetu +"], code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            throw new ApiError(code, message);
        }
        
    }
    
    /**
     * NRI API結果を検証する．
     * @param outdata NRI-APIから取得したoutdata
     * @throws Exception 異常
     */
    private Boolean checkApplicationValidationResult(Object outdata) throws Exception {
        
        String shubetu;
        String code;
        String message;
        
        if (outdata == null) {
            throw new Exception(IfaCommonUtil.getMessage("errors.serverError"));
        }
        
        Class<?> clazz = outdata.getClass();
        try {
            Field field = clazz.getDeclaredField("shubetu"); // 指定されたフィールドを取得
            field.setAccessible(true); // プライベートフィールドへのアクセスを可能にする
            shubetu = (String) field.get(outdata); // フィールドの値を取得して返す
            
            field = clazz.getDeclaredField("code"); // 指定されたフィールドを取得
            field.setAccessible(true); // プライベートフィールドへのアクセスを可能にする
            code = (String) field.get(outdata); // フィールドの値を取得して返す
            
            field = clazz.getDeclaredField("message"); // 指定されたフィールドを取得
            field.setAccessible(true); // プライベートフィールドへのアクセスを可能にする
            message = (String) field.get(outdata); // フィールドの値を取得して返す
            
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            throw e; // フィールドが見つからない場合やアクセスできない場合は null を返す
        }

        if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_SUCCESS)) {
            // 正常終了
            logger.info("Api call is success.");
            return true;       
        } else if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_DB) || 
                   StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_MW) || 
                   code.startsWith(CODE_FAIL)) {            
            // 異常終了(種別がORA,MDLはエラーログ出力,CODEがEXXXXXは、エラーログ出力しない） 
            if (StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_DB) || StringUtils.equals(shubetu, API_RESPONSE_SHUBETU_MW) ) {
                logger.error("ApiError at NRI-API call. code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            } else {
                logger.info("ApiError at NRI-API call. code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            }
            return false;        
        } else if (code.startsWith(CODE_WARN)) {
            // 警告　
            logger.info("ApiWarning at NRI-API call. code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            return true;            
        } else  {
            // 例外をスロー
            logger.info("Exception at NRI-API call. shubetu:[" + shubetu +"], code:[" + StringUtil.trim(code) + "], message:[" + StringUtil.trim(message) + "]");
            throw new ApiError(code, message);
        }        
    }
    
    /**
     * ログ出力を共通化するための HttpApiWrapper のラッパー
     * @param <T>
     * @param dtoIn
     * @param valueType
     * @return
     * @throws Exception
     */
    private <T> T callHttpApiWrraper(DtoIn dtoIn, Class<T> valueType) throws Exception {
        final HttpApiWrapper wrapper = jp.co.sbisec.pcenter.api.HttpApiWrapper.get();

        String injson = jacksonObjectMapper.writeValueAsString(dtoIn);
        logger.info("API input : {}", injson);

        logger.info("API url : {}", dtoIn.getApi());
        T nrioutput = wrapper.call(dtoIn, valueType);

        String outjson = jacksonObjectMapper.writeValueAsString(nrioutput);
        logger.info("API output : {}", outjson);

        return nrioutput;
    }
    
    private static final int MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA = 100;
    
    /**
     * NRI 預り残高一覧リクエスト（サマリー）(次期Web）
     * NRI_QueryAccountPositionSumWeb
     * 預り残高サマリー全件一覧リクエスト. API預り残高一覧リクエスト(サマリー)．
     * (NRI_QueryAccountPositionSumWeb)をコールして全件をリストで返す。<br>
     * 現物・投信 一括表示 （ヘッダー＋レコード）＊ N回APIコール
     * ※API一回MAX 100件を取得する
     *
     * @param input 検索条件（部店や口座番号や商品種類など）
     * @return 預り残高一覧
     * @throws Exception 異常
     */
    public List<QueryAccountPositionSumWebOutData> queryAccountPositionSumWeb(QueryAccountPositionSumWebInData input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountPositionSumWeb : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 預り残高一覧
        List<QueryAccountPositionSumWebOutData> results = new ArrayList<QueryAccountPositionSumWebOutData>();
        
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
        
        QueryAccountPositionSumWebOut nrioutput = null;
        while (true) {
            // n件 ～ n+100件
            input.setRefFrom(dcf.format(refFrom));
            input.setRefTo(dcf.format(refTo));
            int kozaNo = Integer.parseInt(input.getKozaNo());
            input.setKozaNo(dcfAcc.format(kozaNo));
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getButenCd());
                // api inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountPositionSumWebIn(header, input);
                // NRI_QueryAccountPositionSumWebを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAccountPositionSumWebOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            //sthis.checkApplicationValidationResult(nrioutput.getOutdata());

            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                if (nrioutput.getOutdata().getAccountSumWebData() != null) {
                    for (AccountSumWebData outDataUnit : nrioutput.getOutdata().getAccountSumWebData()) {
                        String[] excludeList = { "acqPrincipal", "acquireRate", "collateralRate", "couponPayDate1",
                                "couponPayDate2", "couponPayDate3", "couponPayDate4", "isaAcquisitionYear",
                                "redemptionDate", "secName", "secTypeName", "secId",
                                "domesticFgnId","secType1", "secType2", "companyCode", "stRightId", "newOldId",
                                "serNo", "subCode1", "subCode2", "listCntryCd" };
                        TrimUtil.trimStringFieldsConvrertZero(outDataUnit, excludeList);
                        String[] excludeList2 = { "acquireRate", "collateralRate", "standardRate", "secId",
                                "domesticFgnId","secType1", "secType2", "companyCode", "stRightId", "newOldId",
                                "serNo", "subCode1", "subCode2", "listCntryCd" };
                        TrimUtil.trimStringFields(outDataUnit, excludeList2);
                    }
                }
                // Api 戻り値を処理
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
                refTo += MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    private static final int MAX_QUERY_ACCOUNT_POSITION_DET_WEB_OUT_DATA = 50;

    /**
     * 預り残高個別ー全件一覧リクエスト. API預り残高一覧リクエスト(個別)(次期Web)．
     * (NRI_QueryAccountPositionDetWeb)をコールして全件をリストで返す。<br>
     * 現物・投信 一括表示 （ヘッダー＋レコード）＊ N回APIコール
     * ※API一回MAX 50件を取得する
     *
     * @param input 検索条件（部店や口座番号や商品種類など）
     * @return 預り残高一覧
     * @throws Exception 異常
     */
    public List<QueryAccountPositionDetWebOutData> queryAccountPositionDetWeb(QueryAccountPositionDetWebInData input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountPositionDetWeb : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 預り残高一覧
        List<QueryAccountPositionDetWebOutData> results = new ArrayList<QueryAccountPositionDetWebOutData>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_ACCOUNT_POSITION_DET_WEB_OUT_DATA;
        
        QueryAccountPositionDetWebOut nrioutput = null;
        while (true) {
            // n件 ～ n+50件
            input.setRefFrom(dcf.format(refFrom));
            input.setRefTo(dcf.format(refTo));
            int kozaNo = Integer.parseInt(input.getKozaNo());
            input.setKozaNo(dcfAcc.format(kozaNo));
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = 
                        new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());
                // api inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountPositionDetWebIn(header, input);
                // NRI_QueryAccountPositionSumWebを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAccountPositionDetWebOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // Api 戻る結果利用ください
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                for (AccountDetWebData detail : nrioutput.getOutdata().getAccountDetWebData()) {
                    String[] excludeList = { "quantityUnit", "secName", "depositNo", "rate", "standardRate",
                            "depositDate", "buyTradeDate", "dpRoute1", "dpRoute2", "collateralRate", "acqPrincipal",
                            "rateInterest", "redemptionDate", "coupon_pay_date1", "couponPayDate2", "couponPayDate3",
                            "couponPayDate4", "bkyakKanoYmd", "isaAcquisitionYear" };
                    TrimUtil.trimStringFieldsConvrertZero(detail, excludeList);
                    TrimUtil.trimStringFields(detail);
                }
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_ACCOUNT_POSITION_DET_WEB_OUT_DATA;
                refTo += MAX_QUERY_ACCOUNT_POSITION_DET_WEB_OUT_DATA;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }

    /**
     * 預り残高サマリー1件リクエスト. API預り残高一覧リクエスト(サマリー)(次期Web)．
     * (NRI_QueryAccountPositionSumWeb)をコールして条件を満たすもののうち最初に見つけたもの1件を返す。<br>
     *
     * @param input 検索条件（部店や口座番号や商品種類など）
     * @param predicate 取得する預かり残高の条件
     * @return 預り残高一覧
     * @throws Exception 異常
     */
    public QueryAccountPositionSumWebOutData queryAccountPositionSumWebWithCondition(
            QueryAccountPositionSumWebInData input,
            Predicate<AccountSumWebData> predicate
    ) throws Exception {

        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountPositionSumWebWithCondition : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
        
        AccountSumWebData result = null;
        QueryAccountPositionSumWebOutData outData = new QueryAccountPositionSumWebOutData();
        // inputを内部用にコピー
        QueryAccountPositionSumWebInData nriInput = new QueryAccountPositionSumWebInData();
        BeanUtils.copyProperties(nriInput, input);

        // 口座番号が6桁の場合、7桁にパディング
        nriInput.setKozaNo(dcfAcc.format(Integer.parseInt(input.getKozaNo())));

        // Request Headerに「部店」を設定する
        jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(
                nriInput.getButenCd()
        );
        
        while (true) {
            // n件 ～ n+100件
            nriInput.setRefFrom(dcf.format(refFrom));
            nriInput.setRefTo(dcf.format(refTo));

            // API呼び出しのレスポンス
            QueryAccountPositionSumWebOut nrioutput = null;
            try {
                // api inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountPositionSumWebIn(header, nriInput);

                // ECGateway呼び出し

                // NRI_QueryAccountPositionSumWebを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAccountPositionSumWebOut.class);

            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;

            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値から条件を満たすものを線形探索
            if (nrioutput.getOutdata() != null) {
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                for(AccountSumWebData detail :nrioutput.getOutdata().getAccountSumWebData()) {
                    TrimUtil.trimStringFields(detail);
                }
                BeanUtils.copyProperties(outData, nrioutput.getOutdata());
                result = nrioutput.getOutdata().getAccountSumWebData().stream()
                    .filter(predicate)
                    .findFirst()
                    .orElse(null);
            }

            // ループ終了判定1 条件を満たすものを見つけた場合
            if (result != null) {
                outData.getAccountSumWebData().clear();
                outData.getAccountSumWebData().add(result);
                break;
            }

            // ループ終了判定2 全件検索が完了した場合
            int hitNumber = Integer.parseInt(nrioutput.getOutdata().getHitNumber());
            if (hitNumber <= refTo) {
                outData.getAccountSumWebData().clear();
                break;
            }

            // ループ継続
            refFrom += MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
            refTo += MAX_QUERY_ACCOUNT_POSITION_SUM_WEB_OUT_DATA;
        }

        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return outData;
    }
 
    /**
     * NRI 買付余力照会
     * NRI_QueryAccountBalance
     *
     * @param input 検索条件（部店と口座番号）
     * @return 買付余力照会
     * @throws Exception 異常
     */
    public QueryAccountBalanceOutData queryAccountBalance(QueryAccountBalanceIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountBalance : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        QueryAccountBalanceOut nrioutput = null;
        int kozaNo = Integer.parseInt(input.getIndata().getKozaNo());
        input.getIndata().setKozaNo(dcfAcc.format(kozaNo));

        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // api inputを作成する
            DtoIn dtoIn = new NriQueryAccountBalanceIn(header, input.getIndata());
            // NRI_QueryAccountBalanceを呼び出す

            nrioutput = callHttpApiWrraper(dtoIn, QueryAccountBalanceOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻り値を処理
            nrioutput.getOutdata().setIsaThisYearKbn(codelistservice.convertExtKeyToKey("NISA_TYPE", "EC-GW", nrioutput.getOutdata().getIsaThisYearKbn()));
            nrioutput.getOutdata().setIsaNextYearKbn(codelistservice.convertExtKeyToKey("NISA_TYPE", "EC-GW", nrioutput.getOutdata().getIsaNextYearKbn()));
            //Trim
            TrimUtil.trimStringFields(nrioutput.getOutdata(),"autoSweepKbnJrnisa",
                    "isaBuyLimitStop","isaBuyLimitStopJrnisa","isaBuyLimitStopNext","isaBuyLimitStopNextJrnisa",
                    "isaBuyYear","isaBuyYearNext","isaBuyYearJrnisa","isaBuyYearNextJrnisa");
            //Trim"autoSweepKbnJrnisa","isaBuyLimitStop","isaBuyLimitStopJrnisa","isaBuyLimitStopNext","isaBuyLimitStopNextJrnisa"
            if (nrioutput.getOutdata().getT0() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT0(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT1() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT1(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT2() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT2(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT3() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT3(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT4() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT4(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT5() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT5(),"ｓettlementDateT");
            }
            if (nrioutput.getOutdata().getT0Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT0Jr(),"ｓettlementDateTJrnisa");
            }
            if (nrioutput.getOutdata().getT1Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT1Jr(),"settlementDateTJrnisa");
            }
            if (nrioutput.getOutdata().getT2Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT2Jr(),"settlementDateTJrnisa");
            }
            if (nrioutput.getOutdata().getT3Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT3Jr(),"settlementDateTJrnisa");
            }
            if (nrioutput.getOutdata().getT4Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT4Jr(),"settlementDateTJrnisa");
            }
            if (nrioutput.getOutdata().getT5Jr() != null) {
                TrimUtil.trimStringFieldsConvrertZero(nrioutput.getOutdata().getT5Jr(),"settlementDateTJrnisa");
            }
            
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 信用口座自動振替設定情報を取得する。
     *
     * @param input 検索条件（部店と口座番号）
     * @return 信用口座自動振替設定情報
     * @throws Exception 異常
     */
 /*   public GetMarginAccountAutoTransferSettingOutData GetMarginAccountAutoTransferSetting(
            GetMarginAccountAutoTransferSettingInData input) {
        
        return new GetMarginAccountAutoTransferSettingOutData();
    }
 */  
    /**
     * NRI 信用建玉余力リクエスト（簡易版）
     * NRI_QueryMgEstCapabilityWeb
     * 信用建玉一覧-詳細：維持率情報を取得。<br>
     * 顧客一覧・信用情報を取得
     *
     * @param input 検索条件（部店と口座番号）
     * @return 信用建玉余力
     * @throws Exception 異常
     */
    public QueryMgEstCapabilityWebHtsOutData queryMgEstCapabilityWebHts(QueryMgEstCapabilityWebIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMgEstCapabilityWebHts : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryMgEstCapabilityWebHtsOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMgEstCapabilityWebHtsIn(header, input.getIndata());
            // NRI_QueryMgEstCapabilityWebを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryMgEstCapabilityWebHtsOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for(QueryMgEstCapabilityWebReceiptT detail :nrioutput.getOutdata().getReceiptT()) {
                TrimUtil.trimStringFields(detail);
            }
            for(QueryMgEstCapabilityWebSettlementDateT detail :nrioutput.getOutdata().getSettlementDateT()) {
                TrimUtil.trimStringFields(detail);
            }
            for(QueryMgEstCapabilityWebStatusDivisionT detail :nrioutput.getOutdata().getStatusDivisionT()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 当日注文一覧を取得する．
     * ※API一回MAX 100件を取得する
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 当日注文一覧
     * @throws Exception 異常
     */
    //  public static final int MAX_QUERY_STOCK_UNITE_ORDER = 100;
    //  public List<QueryStockUniteOrderOutData> queryStockUniteOrder(QueryStockUniteOrderIn input) throws Exception {
    //      long start = System.currentTimeMillis();
    //      logger.info("ApiWrapper.queryStockUniteOrder : {}", hashCode());
    //
    //      // 当日注文一覧
    //      List<QueryStockUniteOrderOutData> results = new ArrayList<QueryStockUniteOrderOutData>();
    //
    //      // 件数初期化
    //      int refFrom = 1;
    //      int refTo = MAX_QUERY_STOCK_UNITE_ORDER;
    //
    //      QueryStockUniteOrderOut nrioutput = null;
    //      while (true) {
    //          // n件 ～ n+100件
    //          input.getIndata().setRefFrom(dcf.format(refFrom));// 取得開始位置
    //          input.getIndata().setRefTo(dcf.format(refTo));// 取得終了位置
    //
    //          try {
    //              // ECGateway呼び出し
    //              final HttpApiWrapper wrapper = HttpApiWrapper.get();
    //
    //              // Request Headerに「部店」を設定する
    //              RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
    //              // api inputを作成する
    //              DtoIn dtoIn = new NriQueryStockUniteOrderIn(header, input.getIndata());
    //              // NRI_QueryStockUniteOrderを呼び出す
    //              nrioutput = wrapper.call(dtoIn, QueryStockUniteOrderOut.class);
    //          } catch (IOException e) {
    //              ApiIOException throwE = new ApiIOException(e);
    //              logger.warn(throwE.getMessage());
    //              throw throwE;
    //          } catch (Exception e) {
    //              // API接続異常処理
    //              throw new ApiConnectionException(e);
    //          }
    //
    //          // Check validation result.
    //          this.checkApplicationValidationResult(nrioutput.getOutdata());
    //
    //          // ECGatewayの戻り値がnullかである判断
    //          if (nrioutput.getOutdata() != null) {
    //              // Api 戻る結果利用ください
    //              results.add(nrioutput.getOutdata());
    //              if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
    //                  break;
    //              }
    //              refFrom += MAX_QUERY_STOCK_UNITE_ORDER;
    //              refTo += MAX_QUERY_STOCK_UNITE_ORDER;
    //          }
    //      }
    //      logger.info("cost -> {}", (System.currentTimeMillis() - start));
    //      return results;
    //  }
    
    /**
     * NRI 現引・現渡注文約定明細一覧リクエスト
     * NRI_QueryMgRDOrderExecution
     * @param input
     * @return
     * @throws Exception
     */
    private static final int MAX_QUERY_MG_RD_ORDER_EXECUTION = 100;
    
    public QueryMgRDOrderExecutionOutData queryMgRDOrderExecution(QueryMgRDOrderExecutionIn input, int totalCount)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMgRDOrderExecution : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 当日約定一覧
        QueryMgRDOrderExecutionOutData results = new QueryMgRDOrderExecutionOutData();
        
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MG_RD_ORDER_EXECUTION;
        
        QueryMgRDOrderExecutionOut nrioutput = null;

        
        while (true) {
            // n件 ～ n+100件
            input.getIndata().setRefFrom(dcf.format(refFrom));// 取得開始位置
            input.getIndata().setRefTo(dcf.format(refTo));// 取得終了位置
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMgRDOrderExecutionIn(header, input.getIndata());
                // NRI_QueryMgRDOrderExecutionを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryMgRDOrderExecutionOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // 区分値変換 約定部.非特定預り売買区分
                
                int outdataSize = nrioutput.getOutdata().getQueryMgrdOrderData().size();
                for (int i = 0; i < outdataSize; i++) {
                    String hitokuteiTradeKbn = codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                            nrioutput.getOutdata().getQueryMgrdOrderData().get(i).getHitokuteiTradeKbn());
                    nrioutput.getOutdata().getQueryMgrdOrderData().get(i).setHitokuteiTradeKbn(hitokuteiTradeKbn);
                    TrimUtil.trimStringFields(nrioutput.getOutdata().getQueryMgrdOrderData().get(i));
                }
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                
                // Api 戻る結果利用ください
                if (null == results.getQueryMgrdOrderData()) {
                    BeanUtils.copyProperties(results, nrioutput.getOutdata());
                } else {
                    results.getQueryMgrdOrderData().addAll(nrioutput.getOutdata().getQueryMgrdOrderData());
                }
                
                if (MAX_QUERY_MG_RD_ORDER_EXECUTION == Integer.parseInt(nrioutput.getOutdata().getTotalNumber())) {
                    refFrom += MAX_QUERY_MG_RD_ORDER_EXECUTION;
                    refTo += MAX_QUERY_MG_RD_ORDER_EXECUTION;
                } else {
                    break;
                }
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
 
    /**
     * NRI 信用建玉一覧リクエスト
     * NRI_QueryMarginContract
     * オフセット指定あり．
     * ※API一回MAX 50件を取得する
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 信用建玉一覧
     * @throws Exception 異常
     */
    private static final int MAX_QUERY_MARGIN_CONTRACT = 50;
    
    public List<QueryMarginContractOutData> queryMarginContract(QueryMarginContractIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMarginContract : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 信用建玉一覧
        List<QueryMarginContractOutData> results = new ArrayList<QueryMarginContractOutData>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MARGIN_CONTRACT;
        
        QueryMarginContractOut nrioutput = null;
        while (true) {
            // n件 ～ n+50件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する               
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMarginContractIn(header, input.getIndata());
                // NRI_QueryMarginContractを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryMarginContractOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // API 戻り値を処理
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                for(QueryMarginContractOutVec detail :nrioutput.getOutdata().getQueryMarginContractData()) {
                    TrimUtil.trimStringFields(detail, "ippanMgPaymentKbn", "ippanMgPaymentLimit");
                    //外部コード⇒内部コード変換：　新規市場
                    String openMarket = codelistservice.convertExtKeyToKey("NEW_MARKET", "EC-GW", detail.getOpenMarket());
                    detail.setOpenMarket(openMarket);
                }
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MARGIN_CONTRACT;
                refTo += MAX_QUERY_MARGIN_CONTRACT;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    private static final int MAX_QUERY_MARGIN_CONTRACT0 = 50;
    
    /**
     * NRI 建玉残高一覧サマリー。オフセット指定あり．
     * NRI_QueryMarginContract0
     * ※API一回MAX 50件を取得する
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 建玉残高明細
     * @throws Exception 異常
     */
    public List<QueryMarginContract0OutData> queryMarginContract0(QueryMarginContract0In input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMarginContract0 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 信用建玉一覧
        List<QueryMarginContract0OutData> results = new ArrayList<QueryMarginContract0OutData>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MARGIN_CONTRACT0;
        
        QueryMarginContract0Out nrioutput = null;
        while (true) {
            // n件 ～ n+50件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMarginContract0In(header, input.getIndata());
                
                // NRI_QueryMarginContract0を呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryMarginContract0Out.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (!ObjectUtils.isEmpty(nrioutput.getOutdata())) {
                //取得単価などTrim
                TrimUtil.trimStringFields(nrioutput.getOutdata(), "etNeedCashRate");
                //外部→内部コード変換
                if (!ObjectUtils.isEmpty(nrioutput.getOutdata().getQueryMarginContract0Data())) {
                    for (QueryMarginContract0OutVec result : nrioutput.getOutdata().getQueryMarginContract0Data()) {
                        //新規建売買区分
                        String openTradeKbn;
                        openTradeKbn = codelistservice.convertExtKeyToKey("NEW_CREDIT_SELL_BUY_TYPE", "EC-GW",
                                result.getOpenTradeKbn());
                        result.setOpenTradeKbn(openTradeKbn);
                        //新規市場
                        String openMarket;
                        openMarket = codelistservice.convertExtKeyToKey("NEW_MARKET", "EC-GW", result.getOpenMarket());
                        result.setOpenMarket(openMarket);
                        //弁済期限
                        String paymentLimit;
                        paymentLimit = codelistservice.convertExtKeyToKey("PAYMENT_DEADLINE", "EC-GW", result.getPaymentLimit());
                        result.setPaymentLimit(paymentLimit);
                        
                        //取得単価などTrim
                        String[] excludeList = { "bargainNeedSecRate", "bargainNumber", "ippanMgPaymentLimit",
                                "lastTradeDate", "openContractNo", "openMarket", "openTradeDate", "orgNewTradeDate" };
                        TrimUtil.trimStringFieldsConvrertZero(result, excludeList);
                        TrimUtil.trimStringFields(result, "bargainNeedSecRate");
                    }
                }
                // Api 戻る結果利用ください
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MARGIN_CONTRACT0;
                refTo += MAX_QUERY_MARGIN_CONTRACT0;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    private static final int MAX_QUERY_MARGIN_CONTRACT1 = 50;
    
    /**
     * NRI 建玉残高明細
     * NRI_QueryMarginContract1
     * 。オフセット指定あり．
     * ※API一回MAX 50件を取得する
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 建玉残高明細
     * @throws Exception 異常
     */
    public List<QueryMarginContract1OutData> queryMarginContract1List(QueryMarginContract1In input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMarginContract1List : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        // 信用建玉一覧
        List<QueryMarginContract1OutData> results = new ArrayList<QueryMarginContract1OutData>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MARGIN_CONTRACT1;
        //外部コード変換
        //新規売買区分
        input.getIndata().setOpenTradeKbn(codelistservice.convertKeyToExtKey("NEW_CREDIT_SELL_BUY_TYPE", "EC-GW", input.getIndata().getOpenTradeKbn()));
        //弁済期限
        input.getIndata().setPaymentLimit(codelistservice.convertKeyToExtKey("PAYMENT_DEADLINE", "EC-GW", input.getIndata().getPaymentLimit()));
        
        QueryMarginContract1Out nrioutput = null;
        while (true) {
            // n件 ～ n+50件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する                
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMarginContract1In(header, input.getIndata());
                // NRI_QueryMarginContract1を呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryMarginContract1Out.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                if(nrioutput.getOutdata().getQueryMarginContract1Data() != null) {
                    for (QueryMarginContract1OoutVec result : nrioutput.getOutdata().getQueryMarginContract1Data()) {
                        //　外部→内部コード変換
                        //新規市場
                        String bargainMarket;
                        bargainMarket = codelistservice.convertExtKeyToKey("NEW_MARKET", "EC-GW", result.getBargainMarket());
                        result.setBargainMarket(bargainMarket);
                    }
                }
                // API 戻り値を処理
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                for(QueryMarginContract1OoutVec detail :nrioutput.getOutdata().getQueryMarginContract1Data()) {
                    TrimUtil.trimStringFields(detail);
                }
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MARGIN_CONTRACT1;
                refTo += MAX_QUERY_MARGIN_CONTRACT1;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    /**
     * NRI 建玉残高明細
     * NRI_QueryMarginContract1
     * 。オフセット指定あり．
     * ※API一回MAX 50件を取得する
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 建玉残高明細
     * @throws Exception 異常
     */
    public List<QueryMarginContract1OutData> queryMarginContract1List2(QueryMarginContract1In input) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMarginContract1List : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 信用建玉一覧
        List<QueryMarginContract1OutData> results = new ArrayList<QueryMarginContract1OutData>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MARGIN_CONTRACT1;
        //外部コード変換
        //新規市場
        input.getIndata().setOpenMarket(
                codelistservice.convertKeyToExtKey("NEW_MARKET", "EC-GW", input.getIndata().getOpenMarket()));
        //弁済期限
        input.getIndata().setPaymentLimit(
                codelistservice.convertKeyToExtKey("PAYMENT_DEADLINE", "EC-GW", input.getIndata().getPaymentLimit()));
        
        while (true) {
            // n件 ～ n+50件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            
            QueryMarginContract1Out nrioutput = null;
            try {
                nrioutput = callHttpApiWrraper(
                        new NriQueryMarginContract1In(new RequestHeader(input.getIndata().getButenCd()),
                                input.getIndata()),
                        QueryMarginContract1Out.class);
            } catch (IOException e) {
                logger.warn("Exception occured.", e);
                throw new ApiIOException(e);
            } catch (Exception e) {
                logger.error("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            checkApplicationValidationResult(nrioutput.getOutdata());
            
            if (nrioutput.getOutdata() != null) {
                TrimUtil.trimStringFields(nrioutput.getOutdata(), "ippanMgPaymentKbn", "ippanMgPaymentLimit");
                for (QueryMarginContract1OoutVec detail : nrioutput.getOutdata().getQueryMarginContract1Data()) {
                    TrimUtil.trimStringFields(detail);
                }
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MARGIN_CONTRACT1;
                refTo += MAX_QUERY_MARGIN_CONTRACT1;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    /**
     * NRI 建玉残高明細.
     * NRI_QueryMarginContract1
     * オフセット指定あり.
     * ※API呼出1回MAX50件を取得する
     * <strong>コード値変換・Trim無し</strong>.
     *
     * @param input 検索条件（部店や口座番号や銘柄コードなど）
     * @return 建玉残高明細
     * @throws Exception 異常
     */
    public List<QueryMarginContract1OutData> callQueryMarginContract1List(QueryMarginContract1In input)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.callQueryMarginContract1List : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 信用建玉一覧
        final List<QueryMarginContract1OutData> results = new ArrayList<>();
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MARGIN_CONTRACT1;
        
        while (true) {
            // n件 ～ n+50件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            
            QueryMarginContract1Out nrioutput = null;
            try {
                nrioutput = callHttpApiWrraper(
                        new NriQueryMarginContract1In(new RequestHeader(input.getIndata().getButenCd()),
                                input.getIndata()),
                        QueryMarginContract1Out.class);
            } catch (IOException e) {
                logger.warn("Exception occured.", e);
                throw new ApiIOException(e);
            } catch (Exception e) {
                logger.error("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            checkApplicationValidationResult(nrioutput.getOutdata());
            
            if (nrioutput.getOutdata() != null) {
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MARGIN_CONTRACT1;
                refTo += MAX_QUERY_MARGIN_CONTRACT1;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    public List<QueryExecutionSummary1OutData> queryExecutionSummary1List(QueryExecutionSummary1In input) throws Exception {
        return new ArrayList<QueryExecutionSummary1OutData>();
    }
    /**
     * NRI 約定一覧リクエスト（サマリー）
     * NRI_QueryExecutionSummary1
     * @param input
     * @return
     * @throws Exception
     */
    private static final int MAX_QUERY_EXECUTION_SUMMARY1 = 100;
    
    public QueryExecutionSummary1OutData queryExecutionSummary1(QueryExecutionSummary1In input, int totalCount) throws Exception {
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryExecutionSummary1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 当日約定一覧
        QueryExecutionSummary1OutData results = new QueryExecutionSummary1OutData();
        QueryExecutionSummary1Out nrioutput = null;
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_EXECUTION_SUMMARY1;
        
        while (true) {
            // n件 ～ n+100件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryExecutionSummary1In(header, input.getIndata());
                // NRI_QueryExecutionSummary1を呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryExecutionSummary1Out.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // API 戻り値を処理
                if (nrioutput.getOutdata().getExecSumData() != null) {
                    // 外部コード → 内部コード
                    for (int i = 0; i < nrioutput.getOutdata().getExecSumData().size(); i++) {
                        // 約定部.取引区分
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getExecSumData().get(i).getTradeId())) {
                            if (!StringUtils.equals(nrioutput.getOutdata().getExecSumData().get(i).getTradeId(), "7")
                                    || !StringUtils.equals(nrioutput.getOutdata().getExecSumData().get(i).getTradeId(), "8")) {
                                String tradeIdNri = codelistservice.convertExtKeyToKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW", 
                                        nrioutput.getOutdata().getExecSumData().get(i).getTradeId());
                                nrioutput.getOutdata().getExecSumData().get(i).setTradeId(tradeIdNri);
                            }
                        }
                        // 約定部.非特定預り売買区分
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getExecSumData().get(i).getHitokuteiTradeKbn())) {
                            String hitokuteiTradeKbnNri = codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getExecSumData().get(i).getHitokuteiTradeKbn());
                            nrioutput.getOutdata().getExecSumData().get(i).setHitokuteiTradeKbn(hitokuteiTradeKbnNri);
                        }
                        // 約定部.代用適格区分
                        if (null != nrioutput.getOutdata().getExecSumData().get(i).getCollateralId()) {
                            String collateralIdNri = codelistservice.convertExtKeyToKey("COLLATERAL_ELIGIBLE_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getExecSumData().get(i).getCollateralId());
                            nrioutput.getOutdata().getExecSumData().get(i).setCollateralId(collateralIdNri);
                        }
                        TrimUtil.trimStringFields(nrioutput.getOutdata().getExecSumData().get(i));
                    }
                    TrimUtil.trimStringFields(nrioutput.getOutdata());

                }
                // 既存の現物信用約定一覧の後ろに結合する。
                if (null == results.getExecSumData()) {
                    BeanUtils.copyProperties(results, nrioutput.getOutdata());
                } else {
                    results.getExecSumData().addAll(nrioutput.getOutdata().getExecSumData());
                }
                
                if (MAX_QUERY_EXECUTION_SUMMARY1 == Integer.parseInt(nrioutput.getOutdata().getTotalNumber())) {
                    refFrom += MAX_QUERY_EXECUTION_SUMMARY1;
                    refTo += MAX_QUERY_EXECUTION_SUMMARY1;
                } else {
                    break;
                }
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    public QueryExecutionSummary1OutData queryExecutionSummary1(QueryExecutionSummary1In input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryExecutionSummary1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 当日約定一覧
        QueryExecutionSummary1OutData results = new QueryExecutionSummary1OutData();
        QueryExecutionSummary1Out nrioutput = null;
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_EXECUTION_SUMMARY1;
        
        while (true) {
            // n件 ～ n+100件
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryExecutionSummary1In(header, input.getIndata());
                // NRI_QueryExecutionSummary1を呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryExecutionSummary1Out.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // API 戻り値を処理
                if (nrioutput.getOutdata().getExecSumData() != null) {
                    // 外部コード → 内部コード
                    for (int i = 0; i < nrioutput.getOutdata().getExecSumData().size(); i++) {
                        // 約定部.取引区分
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getExecSumData().get(i).getTradeId())) {
                            if (!StringUtils.equals(nrioutput.getOutdata().getExecSumData().get(i).getTradeId(), "7")
                                    || !StringUtils.equals(nrioutput.getOutdata().getExecSumData().get(i).getTradeId(), "8")) {
                                String tradeIdNri = codelistservice.convertExtKeyToKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW", 
                                        nrioutput.getOutdata().getExecSumData().get(i).getTradeId());
                                nrioutput.getOutdata().getExecSumData().get(i).setTradeId(tradeIdNri);
                            }
                        }
                        // 約定部.非特定預り売買区分
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getExecSumData().get(i).getHitokuteiTradeKbn())) {
                            String hitokuteiTradeKbnNri = codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getExecSumData().get(i).getHitokuteiTradeKbn());
                            nrioutput.getOutdata().getExecSumData().get(i).setHitokuteiTradeKbn(hitokuteiTradeKbnNri);
                        }
                        // 約定部.代用適格区分
                        if (null != nrioutput.getOutdata().getExecSumData().get(i).getCollateralId()) {
                            String collateralIdNri = codelistservice.convertExtKeyToKey("COLLATERAL_ELIGIBLE_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getExecSumData().get(i).getCollateralId());
                            nrioutput.getOutdata().getExecSumData().get(i).setCollateralId(collateralIdNri);
                        }
                        TrimUtil.trimStringFields(nrioutput.getOutdata().getExecSumData().get(i));
                    }
                    TrimUtil.trimStringFields(nrioutput.getOutdata());
                }
                // 既存の現物信用約定一覧の後ろに結合する。
                if (null == results.getExecSumData()) {
                    BeanUtils.copyProperties(results, nrioutput.getOutdata());
                } else {
                    results.getExecSumData().addAll(nrioutput.getOutdata().getExecSumData());
                }
                
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_EXECUTION_SUMMARY1;
                refTo += MAX_QUERY_EXECUTION_SUMMARY1;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    /**
     * NRI 現引・現渡注文一覧リクエスト（次期Web）
     * NRI_QueryMgRDOrderWeb
     * @param input
     * @return
     * @throws Exception
     */
    private static final int MAX_QUERY_MG_RD_ORDER_WEB = 100;
    
    public QueryMgRDOrderWebOutData queryMgRDOrderWeb(QueryMgRDOrderWebIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMgRDOrderWeb : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 当日約定一覧
        QueryMgRDOrderWebOutData results = new QueryMgRDOrderWebOutData();
        
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_MG_RD_ORDER_WEB;
        
        QueryMgRDOrderWebOut nrioutput = null;

        
        while (true) {
            // n件 ～ n+100件
            input.getIndata().setRefFrom(dcf.format(refFrom)); // 取得開始位置
            input.getIndata().setRefTo(dcf.format(refTo)); // 取得終了位置
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMgRDOrderWebIn(header, input.getIndata());
                // NRI_QueryMgRDOrderWebを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryMgRDOrderWebOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // API 戻り値を処理
                if (nrioutput.getOutdata().getQueryMgrdOrderWebData() != null) {
                    // 外部コード → 内部コード
                    for (int i = 0; i < nrioutput.getOutdata().getQueryMgrdOrderWebData().size(); i++) {
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getSettlementId())) {
                            String settlementIdNri = codelistservice.convertExtKeyToKey("RECEIPT_DELIVERY_TRADE_CLASS", "EC-GW",
                                    nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getSettlementId());
                            nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setSettlementId(settlementIdNri);
                        }
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getHitokuteiTradeKbn())) {
                            String hitokuteiTradeKbnNri = codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getHitokuteiTradeKbn());
                            nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setHitokuteiTradeKbn(hitokuteiTradeKbnNri);
                        }
                        if (!StringUtil.isNullOrEmpty(nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getForceKbn())) {
                            String forceKbnNri = codelistservice.convertExtKeyToKey("COERCION_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).getForceKbn());
                            nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setForceKbn(forceKbnNri);
                        }
                        TrimUtil.trimStringFields( nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i));
                    }
                    TrimUtil.trimStringFields( nrioutput.getOutdata());
                }
                // 既存の現引現渡約定一覧の後ろに結合する
                if (null == results.getQueryMgrdOrderWebData()) {
                    BeanUtils.copyProperties(results, nrioutput.getOutdata());
                } else {
                    results.getQueryMgrdOrderWebData().addAll(nrioutput.getOutdata().getQueryMgrdOrderWebData()); 
                }
                
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_MG_RD_ORDER_WEB;
                refTo += MAX_QUERY_MG_RD_ORDER_WEB;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    /**
     * NRI その他商品の注文・出来・取消．
     * NRI_AdditionalPlaceOrder
     * 
     * @param input 検索条件（部店と口座番号と約定予定日と受渡予定日と精算金額と数量など）
     * @return 余力拘束結果
     * @throws Exception 異常
     */
      public AdditionalPlaceOrderOutData additionalPlaceOrder(AdditionalPlaceOrderIn input) throws Exception {
          long start = System.currentTimeMillis();
          logger.info("ApiWrapper.additionalPlaceOrder : {}", hashCode());
          logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
          
          AdditionalPlaceOrderOut nrioutput = null;
          int kozaNo = Integer.parseInt(input.getIndata().getKozaNo());
          input.getIndata().setKozaNo(dcfAcc.format(kozaNo));
          try {
              // ECGateway呼び出し
    
              // Request Headerに「部店」を設定する
              RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
              // API inputを作成する
              DtoIn dtoIn = new NriAdditionalPlaceOrderIn(header, input.getIndata());
              // NRI_AdditionalPlaceOrderを呼び出す
              nrioutput = callHttpApiWrraper(dtoIn, AdditionalPlaceOrderOut.class);
          } catch (IOException e) {
              ApiIOException throwE = new ApiIOException(e);
              logger.warn(throwE.getMessage());
              throw throwE;
          } catch (Exception e) {
              // API接続異常処理
              logger.error("Exception occured.");
              logger.info("Exception occured.", e);
              throw new ApiConnectionException(e);
          }
    
          // Check validation result.
          this.checkApplicationValidationResult(nrioutput.getOutdata());
          logger.info("cost -> {}", (System.currentTimeMillis() - start));
          // ECGatewayの戻り値がnullかである判断
          if (nrioutput.getOutdata() != null) {
              TrimUtil.trimStringFields( nrioutput.getOutdata());
            // API 戻り値を処理
              return nrioutput.getOutdata();
          } else {
              return null;
          }
      }
      /**
       * 株式基準価格リクエスト
       * NRI_QueryStockBasePrice 
       * @param input  入力データ
       * @return 出力データ
       * @throws Exception 異常

      */
      
    public QueryStockBasePriceOutData queryStockBasePrice(QueryStockBasePriceIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryStockBasePrice : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setMarketId(codelistservice.convertKeyToExtKey("SELECT_MARKET", "EC-GW2", input.getIndata().getMarketId()));
        QueryStockBasePriceOut nrioutput = null;
        try {
            // ECGateway呼び出し
            // Request Headerを生成するに「部店」を設定する
            RequestHeader header = new RequestHeader(IfaCommonUtil.getCustomerCommon().getButenCode());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryStockBasePriceIn(header,input.getIndata());
            // NRI_QueryStockBasePriceを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryStockBasePriceOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("output.queryBasePrice : {}", ReflectionToStringBuilder.toString(nrioutput.getOutdata().getQueryBasePrice(),ToStringStyle.MULTI_LINE_STYLE));
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            if (nrioutput.getOutdata().getQueryBasePrice()!= null) {
                nrioutput.getOutdata().getQueryBasePrice().setBStopLow(nrioutput.getOutdata().getQueryBasePrice().getBStopLow().trim());
                nrioutput.getOutdata().getQueryBasePrice().setBStopHigh(nrioutput.getOutdata().getQueryBasePrice().getBStopHigh().trim());
                nrioutput.getOutdata().getQueryBasePrice().setSStopLow(nrioutput.getOutdata().getQueryBasePrice().getSStopLow().trim());
                nrioutput.getOutdata().getQueryBasePrice().setSStopHigh(nrioutput.getOutdata().getQueryBasePrice().getSStopHigh().trim());
                nrioutput.getOutdata().getQueryBasePrice().setBasePrice(nrioutput.getOutdata().getQueryBasePrice().getBasePrice().trim());
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 信用建玉余力リクエスト:NRI_QueryMgEstimateCapability
     * 信用建玉余力情報を取得する
     *
     * @param input 検索条件
     * <ul>
     * <li>部店コード</li>
     * <li>口座番号</li>
     * <li>余力項目セット区分</li>
     * <li>追証項目セット区分</li>
     * </ul>
     * @return 信用建玉余力
     * @throws Exception 異常
     */
    public QueryMgEstimateCapabilityOutData queryMgEstimateCapability(QueryMgEstimateCapabilityIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMgEstimateCapability : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryMgEstimateCapabilityOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMgEstimateCapabilityIn(header,input.getIndata());
            // NRI_QueryMgEstimateCapabilityを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryMgEstimateCapabilityOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        QueryMgEstimateCapabilityOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(outData, "creditAccountKbn", "deficitStatus", "autoSweepKbn");
            for(DateT dateT :outData.getDateT()) {
                TrimUtil.trimStringFields(dateT, "dateTM", "deficitDate");
            }
            for(SettlementDateT settlementDateT :outData.getSettlementDateT()) {
                TrimUtil.trimStringFields(settlementDateT, "fixedSettlementFlg");
            }
            return outData;
        } else {
            return null;
        }
    }
    
    /**
     * NRI API結果を検証する．
     * 
     * @param code 結果コード
     * @param message メッセージ
     * @throws Exception 異常
     */
    private void checkApplicationValidationResult(long code, String message) throws Exception {
        
        String errorMessage = message.trim();
        if (SUCCESS_CODE != code) {
            logger.info("ApiError at API call. code:[" + code + "], message:[" + message + "]");
            String errorCode = String.valueOf(code);
            throw new ApiError(errorCode, errorMessage);
        }
    }
    

       
    /**
     * 信用（新規）注文確認
     * NRI_EstimateMarginOrder
     * 
     * @param input
     * @return 信用（新規）注文確認
     * @throws Exception 異常
     */
    public EstimateMarginOrderOutData estimateMarginOrder(EstimateMarginOrderIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateMarginOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getIndata().getComId()));
        input.getIndata().setTradeKbn(codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getIndata().getTradeKbn()));

        EstimateMarginOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateMarginOrderIn(header,input.getIndata());
            // NRI_EstimateMarginOrderを呼び出す
            //            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn,ToStringStyle.MULTI_LINE_STYLE));
            nrioutput = callHttpApiWrraper(dtoIn, EstimateMarginOrderOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 国内株式・信用訂正注文確認．
     *
     * @param input 検索条件
     * @return 検索結果
     * @throws Exception 異常
     */
    public EstimateStockModifyOrderOutData estimateStockModifyOrder1(EstimateStockModifyOrderIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockModifyOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 外部コード変換
        input.getIndata()
                .setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        EstimateStockModifyOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            final jp.co.sbisec.pcenter.api.HttpApiWrapper wrapper = jp.co.sbisec.pcenter.api.HttpApiWrapper.get();
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockModifyOrderIn(header, input.getIndata());
            // NRI_EstimateStockModifyOrderを呼び出す
            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn, ToStringStyle.MULTI_LINE_STYLE));
            nrioutput = wrapper.call(dtoIn, EstimateStockModifyOrderOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("output : {}",
                ReflectionToStringBuilder.toString(nrioutput.getOutdata(), ToStringStyle.MULTI_LINE_STYLE));
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 自動注文(国内株式)訂正注文確認
     *
     * @param input 検索条件
     * @return 検索結果
     * @throws Exception 異常
     */
    public EstimateStockModifyOrderAutoOutData estimateStockModifyOrderAuto1(EstimateStockModifyOrderAutoIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockModifyOrderAuto : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 外部コード変換
        input.getIndata()
                .setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        EstimateStockModifyOrderAutoOut nrioutput = null;
        try {
            // ECGateway呼び出し
            final jp.co.sbisec.pcenter.api.HttpApiWrapper wrapper = jp.co.sbisec.pcenter.api.HttpApiWrapper.get();
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockModifyOrderAutoIn(header, input.getIndata());
            // NRI_EstimateStockModifyOrderAutoを呼び出す
            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn, ToStringStyle.MULTI_LINE_STYLE));
            nrioutput = wrapper.call(dtoIn, EstimateStockModifyOrderAutoOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("output : {}",
                ReflectionToStringBuilder.toString(nrioutput.getOutdata(), ToStringStyle.MULTI_LINE_STYLE));
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 株式統合注文一覧照会(ポイント)
     * NRI_QueryStockUniteOrderPoint
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryStockUniteOrderPointOutData queryStockUniteOrderPoint(QueryStockUniteOrderPointIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryStockUniteOrderPoint : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        QueryStockUniteOrderPointOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryStockUniteOrderPointIn(header, input.getIndata());
            // NRI_QueryStockUniteOrderNeoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryStockUniteOrderPointOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.//shubetu/code/messegeは、CommonOrderDataに格納
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            if (nrioutput.getOutdata().getReqOrderDataExe() != null) {
                for (int i = 0; i < nrioutput.getOutdata().getReqOrderDataExe().size(); i++) {
                    nrioutput.getOutdata().getReqOrderDataExe().get(i).setComId(codelistservice.convertExtKeyToKey(
                            "COMM_TYPE", "EC-GW", nrioutput.getOutdata().getReqOrderDataExe().get(i).getComId()));
                    nrioutput.getOutdata().getReqOrderDataExe().get(i)
                            .setForceKbn(codelistservice.convertExtKeyToKey("COERCION_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getReqOrderDataExe().get(i).getForceKbn()));
                    nrioutput.getOutdata().getReqOrderDataExe().get(i)
                            .setHitokuteiTradeKbn(codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getReqOrderDataExe().get(i).getHitokuteiTradeKbn()));
                    nrioutput.getOutdata().getReqOrderDataExe().get(i)
                            .setMarketId(codelistservice.convertExtKeyToKey("SELECT_MARKET", "EC-GW",
                                    nrioutput.getOutdata().getReqOrderDataExe().get(i).getMarketId()));
                    nrioutput.getOutdata().getReqOrderDataExe().get(i)
                            .setSasinariId(codelistservice.convertExtKeyToKey("LIMIT_MARKET_TYPE", "EC-GW",
                                    nrioutput.getOutdata().getReqOrderDataExe().get(i).getSasinariId()));
                    TrimUtil.trimStringFields(nrioutput.getOutdata().getReqOrderDataExe().get(i), "ippanMgPaymentKbn", "ippanMgPaymentLimit");
                }
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 株式統合注文一覧照会（NEO） ※注文状況一覧用
     * NRI_QueryStockUniteOrderNeo
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryStockUniteOrderPointOutData callQueryStockUniteOrderPointForOrderStatusList(QueryStockUniteOrderPointIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryStockUniteOrderPointForOrderStatusList : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        try {
            QueryStockUniteOrderPointOut nrioutput = callHttpApiWrraper(
                    new NriQueryStockUniteOrderPointIn(new RequestHeader(input.getIndata().getButenCd()),
                            input.getIndata()),
                    QueryStockUniteOrderPointOut.class);
            checkApplicationValidationResult(nrioutput.getOutdata());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            return nrioutput.getOutdata();
        } catch (ApiError e) {
            throw e;
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
    }

    /**
     * 国内株式・信用注文取消
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public StockCancelOrderOutData stockCancelOrder(StockCancelOrderIn input) throws Exception {
        
        StockCancelOrderOut nriOutput = null;

        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.stockCancelOrder : {}", hashCode());
        
        try {
            // ECGatewayの呼び出し

            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());

            // API Inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriStockCancelOrderIn(header, input.getIndata());
            logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));

            // NRI_StockCancelOrderを呼び出す
            nriOutput = callHttpApiWrraper(dtoIn, StockCancelOrderOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;

        } catch (Exception e) {
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result
        this.checkApplicationValidationResult(nriOutput.getHeader().getCode(), nriOutput.getHeader().getMessage());
        
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        
        return nriOutput.getOutdata();
    }
    
    /**
     * 投信（一般口、汎用累投）注文一覧リクエスト（口数売却拡張版）
     * NRI_QueryFundOrderWebExt
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryFundOrderWebExtOutData queryFundOrderWebExt(QueryFundOrderWebExtIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryFundOrderWebExtOut : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        QueryFundOrderWebExtOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryFundOrderWebExtIn(header,input.getIndata());
            // NRI_QueryFundOrderWebExtを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryFundOrderWebExtOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            //throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            //throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            for (int i = 0; i < nrioutput.getOutdata().getQueryFundOrderWebData().size(); i++) { 
                nrioutput.getOutdata().getQueryFundOrderWebData().get(i).setIsaKbn(codelistservice.convertExtKeyToKey("MUTUAL_FUND_DEPOSIT_TYPE", "EC-GW", nrioutput.getOutdata().
                        getQueryFundOrderWebData().get(i).getIsaKbn()));
                nrioutput.getOutdata().getQueryFundOrderWebData().get(i).setPointType(codelistservice.convertExtKeyToKey("POINT_TYPE", "EC-GW", nrioutput.getOutdata().
                        getQueryFundOrderWebData().get(i).getPointType()));
                nrioutput.getOutdata().getQueryFundOrderWebData().get(i).setReinvest(codelistservice.convertExtKeyToKey("DISTRIBUTION_RECEIVE_METHOD", "EC-GW", nrioutput.getOutdata().
                        getQueryFundOrderWebData().get(i).getReinvest()));
            }
            
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 国内株式注文確認．
     * NRI_EstimateStockOrder
     * 
     * @param input
     * @return 国内株式注文確認
     * @throws Exception 異常
     */
    public EstimateStockOrderOutData estimateStockOrder(EstimateStockOrderIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockOrderOut : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getIndata().getComId()));
        input.getIndata().setTradeKbn(codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getIndata().getTradeKbn()));
        EstimateStockOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockOrderIn(header,input.getIndata());
            // NRI_EstimateStockOrderNeoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateStockOrderOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            return nrioutput.getOutdata();
        } else {
            return null;
        }

    }
    
    /**
     * 自動注文(国内株式現物・信用)新規注文確認．
     * NRI_EstimateStockOrderAuto
     * 
     * @param input
     * @return 自動注文(国内株式現物・信用)新規注文確認
     * @throws Exception 異常
     */
    public EstimateStockOrderAutoOutData estimateStockOrderAuto(EstimateStockOrderAutoIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockOrderAuto : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getIndata().getComId()));
        input.getIndata().setTradeKbn(codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getIndata().getTradeKbn()));
        EstimateStockOrderAutoOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockOrderAutoIn(header,input.getIndata());
            // NRI_EstimateStockOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateStockOrderAutoOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 国内株式・信用訂正注文確認
     * NRI_EstimateStockModifyOrder
     *
     * @param input
     * @return 国内株式注文確認(NEO)
     * @throws Exception システムエラー
     */
    public EstimateStockModifyOrderOutData estimateStockModifyOrder(EstimateStockModifyOrderIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockOrderNeoOut : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        EstimateStockModifyOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockModifyOrderIn(header,input.getIndata());
            // NRI_EstimateStockModifyOrderを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateStockModifyOrderOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 自動注文(国内株式)訂正注文確認
     * NRI_EstimateStockModifyOrderAuto
     * 
     * @param input
     * @return自動注文(国内株式)訂正注文確認
     * @throws Exception システムエラー
     */
    public EstimateStockModifyOrderAutoOutData estimateStockModifyOrderAuto(EstimateStockModifyOrderAutoIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockOrderNeoOut : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        EstimateStockModifyOrderAutoOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateStockModifyOrderAutoIn(header,input.getIndata());
            // NRI_EstimateStockModifyOrderを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateStockModifyOrderAutoOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 約定一覧リクエスト
     *　NRI_QueryExecutionSummary1
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 約定一覧取得時に例外が発生した場合
     */
//    public QueryExecutionSummary1OutData queryExecutionSummary1(QueryExecutionSummary1In input) throws Exception {
//        
//        long start = System.currentTimeMillis();
//        logger.info("ApiWrapper.queryExecutionSummary1 : {}", hashCode());
//        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
//        QueryExecutionSummary1Out nrioutput = null;
//        try {
//            // ECGateway呼び出し
//            final jp.co.sbisec.pcenter.api.HttpApiWrapper wrapper = jp.co.sbisec.pcenter.api.HttpApiWrapper.get();
//            
//            // Request Headerに「部店」を設定する
//            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
//            // API inputを作成する
//            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryExecutionSummary1In(header,input.getIndata());
//            // NRI_QueryExecutionSummary1を呼び出す
//            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn,ToStringStyle.MULTI_LINE_STYLE));
//            nrioutput = wrapper.call(dtoIn, QueryExecutionSummary1Out.class);
//        } catch (IOException e) {
//            ApiIOException throwE = new ApiIOException(e);
//            logger.warn(throwE.getMessage());
//            throw throwE;
//        } catch (Exception e) {
//            // API接続異常処理
//            logger.error("Exception occured.");
//            logger.info("Exception occured.", e);
//            throw new ApiConnectionException(e);
//        }
//        // Check validation result.
//        this.checkApplicationValidationResult(nrioutput.getOutdata());
//        logger.info("output : {}", ReflectionToStringBuilder.toString(nrioutput.getOutdata(),ToStringStyle.MULTI_LINE_STYLE));
//        logger.info("cost -> {}", (System.currentTimeMillis() - start));
//        // ECGatewayの戻り値がnullかである判断
//        if (nrioutput.getOutdata() != null) {
//            // API 戻り値を処理
//            return nrioutput.getOutdata();
//        } else {
//            return null;
//        }
//    }
    
    /**
    * NRI 未精算明細リクエスト
    * NRI_QueryStatementAccount
    *
    * @param input 入力データ
    * @return 出力データ
    * @throws Exception 未精算明細取得時に例外が発生した場合
    */
    private static final int MAX_QUERY_STATEMENT_ACCOUNT = 100;
    
    public QueryStatementAccountOutData queryStatementAccount(QueryStatementAccountIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryStatementAccount : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        int refFrom = 1;
        int refTo = MAX_QUERY_STATEMENT_ACCOUNT;
        // 未精算明細リスト
        QueryStatementAccountOutData results = new QueryStatementAccountOutData();
        QueryStatementAccountOut nrioutput = null;
        while (true) {
            input.getIndata().setRefFrom(dcf.format(refFrom));
            input.getIndata().setRefTo(dcf.format(refTo));
            try {   
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryStatementAccountIn(header, input.getIndata());
                // NRI_QueryStatementAccountを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryStatementAccountOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for( QueryStatementAccountDetail detail :nrioutput.getOutdata().getReceiptData()) {
                TrimUtil.trimStringFields(detail);
            }
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            // ECGatewayの戻り値がnullかである判断
            if (null == results.getReceiptData()) {
                results = nrioutput.getOutdata();
            } else {
                results.getReceiptData().addAll(nrioutput.getOutdata().getReceiptData());
            }
            
            if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                break;
            }
            refFrom += MAX_QUERY_STATEMENT_ACCOUNT;
            refTo += MAX_QUERY_STATEMENT_ACCOUNT;
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
    
    /**
    * NRI 現引・現渡注文一覧リクエスト（次期Web）
    * NRI_QueryMgRDOrderWeb
    *
    * @param input 入力データ
    * @return 出力データ
    * @throws Exception 現引・現渡注文一覧取得時に例外が発生した場合
    */
//    public QueryMgRDOrderWebOutData queryMgRDOrderWeb(QueryMgRDOrderWebIn input) throws Exception {
//        
//        long start = System.currentTimeMillis();
//        logger.info("ApiWrapper.queryMgRDOrderWeb : {}", hashCode());
//        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
//        QueryMgRDOrderWebOut nrioutput = null;
//        try {
//            // ECGateway呼び出し
//            final jp.co.sbisec.pcenter.api.HttpApiWrapper wrapper = jp.co.sbisec.pcenter.api.HttpApiWrapper.get();
//            
//            // Request Headerに「部店」を設定する
//            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
//            // API inputを作成する
//            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMgRDOrderWebIn(header,input.getIndata());
//            // NRI_QueryMgRDOrderWebを呼び出す
//            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn,ToStringStyle.MULTI_LINE_STYLE));
//            nrioutput = wrapper.call(dtoIn, QueryMgRDOrderWebOut.class);
//        } catch (IOException e) {
//            ApiIOException throwE = new ApiIOException(e);
//            logger.warn(throwE.getMessage());
//            throw throwE;
//        } catch (Exception e) {
//            // API接続異常処理
//            logger.error("Exception occured.");
//            logger.info("Exception occured.", e);
//            throw new ApiConnectionException(e);
//        }
//        // Check validation result.
//        this.checkApplicationValidationResult(nrioutput.getOutdata());
//        logger.info("output : {}", ReflectionToStringBuilder.toString(nrioutput.getOutdata(),ToStringStyle.MULTI_LINE_STYLE));
//        logger.info("cost -> {}", (System.currentTimeMillis() - start));    
//        // ECGatewayの戻り値がnullかである判断
//        if (nrioutput.getOutdata() != null) {
//          // API 戻り値を処理
//            for (int i = 0; i < nrioutput.getOutdata().getQueryMgrdOrderWebData().size(); i++) { 
//                nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setForceKbn(codelistservice.convertExtKeyToKey("COERCION_TYPE", "EC-GW", nrioutput.getOutdata().
//                        getQueryMgrdOrderWebData().get(i).getForceKbn()));
//                nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setHitokuteiTradeKbn(codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW", nrioutput.getOutdata().
//                        getQueryMgrdOrderWebData().get(i).getHitokuteiTradeKbn()));
//                nrioutput.getOutdata().getQueryMgrdOrderWebData().get(i).setSettlementId(codelistservice.convertExtKeyToKey("RECEIPT_DELIVERY_TRADE_CLASS", "EC-GW", nrioutput.getOutdata().
//                        getQueryMgrdOrderWebData().get(i).getSettlementId()));
//            }
//            return nrioutput.getOutdata();
//        } else {
//            return null;
//        }
//        
//    }

    /**
     * 建玉残高明細
     * NRI_QueryMarginContract1
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 例外が発生した場合
     */
    public QueryMarginContract1OutData callQueryMarginContract1(QueryMarginContract1In input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.callQueryMarginContract1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        try {
            QueryMarginContract1Out nrioutput = callHttpApiWrraper(
                    new NriQueryMarginContract1In(new RequestHeader(input.getIndata().getButenCd()), input.getIndata()),
                    QueryMarginContract1Out.class);
            checkApplicationValidationResult(nrioutput.getOutdata());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            return nrioutput.getOutdata();
        } catch (ApiError e) {
            throw e;
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
    }
    
    /**
     * NRI 代用有価証券入出庫内訳
     * NRI_QueryAccountSubSecRecDel
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 代用有価証券入出庫内訳取得時に例外が発生した場合
     */
    public QueryAccountSubSecRecDelOutData queryAccountSubSecRecDel(QueryAccountSubSecRecDelInData input)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountSubSecRecDel : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryAccountSubSecRecDelOut nrioutput = null;
        QueryAccountSubSecRecDelOutData nrioutputData = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getOfficeCode());
            
            String searchFrom;
            String searchTo;
            int searchCnt = 0;
            do {
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountSubSecRecDelIn(header, input);
                // NRI_QueryAccountSubSecRecDelを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAccountSubSecRecDelOut.class);
                
                // Check validation result.
                this.checkApplicationValidationResult(nrioutput.getOutdata());
                logger.info("cost -> {}", (System.currentTimeMillis() - start));
                
                // ECGatewayの戻り値がnullかである判断
                if (nrioutput.getOutdata() != null) {
                    // API 戻り値を処理
                    if (nrioutputData == null) {
                        nrioutputData = nrioutput.getOutdata();
                    } else {
                        QueryAccountSubSecRecDelOutData workNrioutputData = nrioutput.getOutdata();
                        List<QueryAccountSubSecRecDelDetail> vecList = nrioutputData.getSubstitute();
                        vecList.addAll(workNrioutputData.getSubstitute());
                        
                        nrioutputData.setSubstitute(vecList);
                    }

                    if (Integer.parseInt(input.getRefTo()) >= Integer.parseInt(nrioutputData.getHitNumber())) {
                        break;
                    }

                    searchCnt++;
                    searchFrom = String.format("%05d", (searchCnt * 1000) + 1);
                    searchTo = String.format("%05d", (searchCnt + 1) * 1000);
                    input.setRefFrom(searchFrom);
                    input.setRefTo(searchTo);
                }
            } while (nrioutputData != null && nrioutputData.getSubstitute().size() != 0);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // 商品タイプ（商品区分＋国内外国区分）の外部コード→内部コード変換
        if(!ObjectUtils.isEmpty(nrioutputData.getSubstitute())) {
            for(QueryAccountSubSecRecDelDetail outVec : nrioutputData.getSubstitute()) {
                outVec.setSecTypeName(codelistservice.convertExtKeyToKey("SEC_TYPE_NAME", "EC-GW",
                        outVec.getSecTypeName()));
            }
        }
        
        return nrioutputData;
    }
    
    /**
     * NRI 買付可能一覧
     * NRI_QueryFund
     *
     * @param input 入力データ
     * @return 出力データ
     */
    public QueryFundOutData queryFund(QueryFundIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryFund : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryFundOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryFundIn(header, input.getIndata());
            // NRI_QueryFundを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryFundOut.class);
        } catch (IOException e) {
            
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 代用有価証券評価額合計
     * NRI_QueryAccountSubSecValue
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 売却制限明細一覧取得時に例外が発生した場合
     */
    public QueryAccountSubSecValueOutData queryAccountSubSecValue(QueryAccountSubSecValueInData input)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountSubSecValue : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryAccountSubSecValueOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getOfficeCode());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountSubSecValueIn(header, input);
            // NRI_QueryAccountSubSecValueを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryAccountSubSecValueOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
        
    }
    
    /**
     * NRI 代用有価証券受渡日別サマリ
     * NRI_QueryAccountSubSecSum
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 
     */
    public QueryAccountSubSecSumOutData queryAccountSubSecSum(QueryAccountSubSecSumInData input) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAccountSubSecSum : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryAccountSubSecSumOut nrioutput = null;
        QueryAccountSubSecSumOutData nrioutputData = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getOfficeCode());
            
            String searchFrom;
            String searchTo;
            int searchCnt = 0;
            do {
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAccountSubSecSumIn(header, input);
                // NRI_QueryAccountSubSecSumを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAccountSubSecSumOut.class);
                
                // Check validation result.
                this.checkApplicationValidationResult(nrioutput.getHeader().getCode(),
                        nrioutput.getHeader().getMessage());
                logger.info("cost -> {}", (System.currentTimeMillis() - start));
                
                // ECGatewayの戻り値がnullかである判断
                if (nrioutput.getOutdata() != null) {
                    // Api 戻る結果利用ください
                    if (nrioutputData == null) {
                        nrioutputData = nrioutput.getOutdata();
                    } else {
                        QueryAccountSubSecSumOutData workNrioutputData = nrioutput.getOutdata();
                        List<QueryAccountSubSecSumOutVec> vecList = nrioutputData.getSubstituteT();
                        vecList.addAll(workNrioutputData.getSubstituteT());
                        
                        nrioutputData.setSubstituteT(vecList);
                    }
                    
                    searchCnt++;
                    searchFrom = String.format("%05d", (searchCnt * 1000) + 1);
                    searchTo = String.format("%05d", (searchCnt + 1) * 1000);
                    input.setRefFrom(searchFrom);
                    input.setRefTo(searchTo);
                }
            } while (nrioutput != null && nrioutput.getOutdata().getSubstituteT().size() != 0);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        TrimUtil.trimStringFields(nrioutputData);
        for(QueryAccountSubSecSumOutVec detail :nrioutputData.getSubstituteT()) {
            detail.setSecTypeName(
                    codelistservice.convertExtKeyToKey("SEC_TYPE_NAME", "EC-GW", detail.getSecTypeName()));
            TrimUtil.trimStringFields(detail);
        }
        return nrioutputData;
    }
    
    /**
     * NRI 売却制限明細一覧
     * NRI_QuerySaleLimit
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 売却制限明細一覧取得時に例外が発生した場合
     */
    public QuerySaleLimitOutData querySaleLimit(QuerySaleLimitIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.querySaleLimit : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QuerySaleLimitOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQuerySaleLimitIn(header,input.getIndata());
            // NRI_QueryMgEstimateCapabilityを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QuerySaleLimitOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for(QuerySaleLimitDetail detail :nrioutput.getOutdata().getQuerySaleLimitDetail()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 約定一覧リクエスト
     * NRI_QueryExecutionDetail1
     *
     * @param input 入力データ
     * @return 出力データ
     */
    public QueryExecutionDetail1OutData queryExecutionDetail1(QueryExecutionDetail1InData input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryExecutionDetail1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryExecutionDetail1Out nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getButenCd());
            //　内部→外部コード変換
            //取引区分
            String tradeId;
            tradeId = codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW", input.getTradeId());
            input.setTradeId(tradeId);
            //非特定預り売買区分
            String hitokuteiTradeKbn;
            hitokuteiTradeKbn = codelistservice.convertKeyToExtKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                    input.getHitokuteiTradeKbn());
            input.setHitokuteiTradeKbn(hitokuteiTradeKbn);
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryExecutionDetail1In(header, input);
            // NRI_QueryExecutionDetail1を呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryExecutionDetail1Out.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            //外部→内部コード変換
            for (int i = 0; i < nrioutput.getOutdata().getExecDetailData().size(); i++) {
                //約定一覧.非特定預り売買区分
                String hitokuteiTradeKbn;
                hitokuteiTradeKbn = codelistservice.convertExtKeyToKey("DOMESTIC_DEPOSIT_TYPE", "EC-GW",
                        nrioutput.getOutdata().getExecDetailData().get(i).getHitokuteiTradeKbn());
                nrioutput.getOutdata().getExecDetailData().get(i).setHitokuteiTradeKbn(hitokuteiTradeKbn);
                //約定一覧.取消ステータス
                String cxlStatus;
                cxlStatus = codelistservice.convertExtKeyToKey("CANCEL_STATUS", "EC-GW", nrioutput.getOutdata().getExecDetailData().get(i).getCxlStatus());
                nrioutput.getOutdata().getExecDetailData().get(i).setCxlStatus(cxlStatus);
                
                TrimUtil.trimStringFields(nrioutput.getOutdata().getExecDetailData().get(i));
            }
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 注文建玉明細
     * NRI_QueryMarginContract2
     *
     * @param input 入力データ
     * @return 出力データ
     * @exception
     */
    public QueryMarginContract2OutData queryMarginContract2(QueryMarginContract2InData input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryMarginContract2 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        QueryMarginContract2Out nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryMarginContract2In(header, input);
            // NRI_QueryMarginContract2を呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryMarginContract2Out.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // 外部→内部コード変換
              for (int i = 0; i < nrioutput.getOutdata().getQueryMarginContract2Data().size(); i++) {
                // 建玉明細.建市場
                String bargainMarket;
                bargainMarket = codelistservice.convertExtKeyToKey("NEW_MARKET", "EC-GW", nrioutput.getOutdata().getQueryMarginContract2Data().get(i).getBargainMarket());
                nrioutput.getOutdata().getQueryMarginContract2Data().get(i).setBargainMarket(bargainMarket);
            }
            // API 戻る結果利用ください
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for(QueryMarginContract2Detail detail :nrioutput.getOutdata().getQueryMarginContract2Data()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 再投資停止情報参照・登録
     *
     * @param input 入力データ
     * @return 再投資停止情報
     * @throws Exception 再投資停止情報参照・登録時に例外が発生した場合
     */
    public ReinvestEntryOutData reinvestEntry(ReinvestEntryInData input) throws Exception {

        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.reinvestEntry : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));

        ReinvestEntryOut nrioutput = null;
        try {
            // ECGateway呼び出し

            // Request Headerに「部店」を設定する
            jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());

            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriReinvestEntryIn(header, input);

            // NRI_QueryMgEstimateCapabilityを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, ReinvestEntryOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;

        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));

        // ECGatewayの戻り値がnullでなければ、戻り値にセット
        ReinvestEntryOutData result = null;
        if (nrioutput.getOutdata() != null) {
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for(SimReinvestEntryDataO detail :nrioutput.getOutdata().getSimReinvestEntryDataO()) {
                TrimUtil.trimStringFields(detail);
            }
            result =  nrioutput.getOutdata();
        }
        
        return result;
    }
 
    /**
     * NRI 信用新規注文確認リクエスト:NRI_MarginPlaceOrder
     * 信用(新規)注文
     * 
     * @param input 検索条件
     * <ul>
     * <li></li>
     * <li></li>
     * <li></li>
     * <li></li>
     * </ul>
     * @return 
     * @throws Exception 異常
    */
    public MarginPlaceOrderOutData marginPlaceOrder(MarginPlaceOrderIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.marginPlaceOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getIndata().getComId()));
        input.getIndata().setTradeKbn(codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getIndata().getTradeKbn()));
        MarginPlaceOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriMarginPlaceOrderIn(header,input.getIndata());
            // NRI_StockPlaceOrderAutoを呼び出す
            //            logger.info("API input : {}", ReflectionToStringBuilder.toString(dtoIn,ToStringStyle.MULTI_LINE_STYLE));
            nrioutput = callHttpApiWrraper(dtoIn, MarginPlaceOrderOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrder
     *
     * @param input 信用一括返済注文確認情報
     * @return 信用一括返済注文確認結果情報
     * @throws Exception 異常
     */
    public EstimateMarginLumpOrderOutData estimateMarginLumpOrder(EstimateMarginLumpOrderIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateMarginLumpOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        try {
            DtoIn dtoIn = new NriEstimateMarginLumpOrderIn(new RequestHeader(input.getIndata().getButenCd()),
                    input.getIndata());
            EstimateMarginLumpOrderOut output = callHttpApiWrraper(dtoIn, EstimateMarginLumpOrderOut.class);
            checkApplicationValidationResult(output.getHeader().getCode(), output.getHeader().getMessage());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            if (output.getOutdata() == null) {
                return null;
            }
            TrimUtil.trimStringFields(output.getOutdata());
            for(EstimateMarginLumpOrderOutVec detail :output.getOutdata().getCodeT()) {
                TrimUtil.trimStringFields(detail);
            }
            return output.getOutdata();
        } catch (ApiError e) {
            throw e;
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
    }
    
    /**
     * 返済順序指定信用一括返済注文確認．
     * NRI_EstimateMarginLumpOrderSort
     *
     * @param input 返済順序指定信用一括返済注文確認情報
     * @return 返済順序指定信用一括返済注文確認結果情報
     * @throws Exception 異常
     */
    public EstimateMarginLumpOrderSortOutData estimateMarginLumpOrderSort(EstimateMarginLumpOrderSortIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateStockOrderNeoOut : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        try {
            DtoIn dtoIn = new NriEstimateMarginLumpOrderSortIn(new RequestHeader(input.getIndata().getButenCd()),
                    input.getIndata());
            EstimateMarginLumpOrderSortOut output = callHttpApiWrraper(dtoIn,
                    EstimateMarginLumpOrderSortOut.class);
            checkApplicationValidationResult(output.getHeader().getCode(), output.getHeader().getMessage());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            if (output.getOutdata() == null) {
                return null;
            }
            TrimUtil.trimStringFields(output.getOutdata());
            return output.getOutdata();
        } catch (ApiError e) {
            throw e;
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
    }
    
    /**
     * NRI 自動注文(国内株式現物・信用)新規注文受付
     * NRI_StockPlaceOrderAuto
     * 
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常
    */
    public StockPlaceOrderAutoOutData stockPlaceOrderAuto(StockPlaceOrderAutoIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.stockPlaceOrderAuto : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        //外部コード変換
        input.getIndata().setComId(codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getIndata().getComId()));
        input.getIndata().setTradeKbn(codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getIndata().getTradeKbn()));
        StockPlaceOrderAutoOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriStockPlaceOrderAutoIn(header,input.getIndata());
            // NRI_StockPlaceOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, StockPlaceOrderAutoOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * 投信（一般口、汎用累投）注文一覧リクエスト（口数売却拡張版）_変換なし
     * NRI_QueryFundOrderWebExt
     *
     * @param input 入力データ
     * @return 出力データ
     * @throws Exception 異常
     */
    public QueryFundOrderWebExtOutData queryFundOrderWebExtNoConvert(QueryFundOrderWebExtIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryFundOrderWebExtNoConvert : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        QueryFundOrderWebExtOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryFundOrderWebExtIn(header, input.getIndata());
            // NRI_QueryFundOrderWebExtを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryFundOrderWebExtOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            //throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            //throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for (QueryFundOrderWebExtDetail detail : nrioutput.getOutdata().getQueryFundOrderWebData()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    private static final int MAX_FUND_ORDER_WEB_EXT = 100;
    
    /**
     * 投信（一般口、汎用累投）注文一覧リクエスト（口数売却拡張版）:NRI_QueryFundOrderWebExt
     *
     * @param input 検索条件
     * @return 投信注文一覧リクエスト情報リスト
     * @throws Exception 投信注文一覧リクエスト情報取得処理で例外が発生した場合    
     */
    public List<QueryFundOrderWebExtOutData> queryFundOrderWebList(QueryFundOrderWebExtInData input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryFundOrderWebList : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        List<QueryFundOrderWebExtOutData> results = new ArrayList<QueryFundOrderWebExtOutData>();
        
        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_FUND_ORDER_WEB_EXT;
        
        QueryFundOrderWebExtOut nrioutput = null;
        while (true) {
            // n件 ～ n+100件
            input.setRefFrom(dcf.format(refFrom));
            input.setRefTo(dcf.format(refTo));
        
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());
                // api inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryFundOrderWebExtIn(header, input);
                // NRI_QueryFundOrderWebExtを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryFundOrderWebExtOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                logger.error("Exception occured.");
                logger.info("Exception occured.", e);
                throw new ApiConnectionException(e);
            }

            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // Api 戻る結果利用ください
                TrimUtil.trimStringFields(nrioutput.getOutdata());
                for(QueryFundOrderWebExtDetail detail :nrioutput.getOutdata().getQueryFundOrderWebData()) {
                    TrimUtil.trimStringFields(detail);
                }
                results.add(nrioutput.getOutdata());
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_FUND_ORDER_WEB_EXT;
                refTo += MAX_FUND_ORDER_WEB_EXT;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
 
    
    /**
     * 投信（一般口）取消:NRI_FundCancelOrder1
     *
     * @param input 検索条件
     * @return 投信注文一覧リクエスト情報
     * @throws Exception 投信注文一覧リクエスト情報取得処理で例外が発生した場合    
     */
    public FundCancelOrder1OutData fundCancelOrder1(FundCancelOrder1InData input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.fundCancelOrder1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        FundCancelOrder1Out nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriFundCancelOrder1In(header, input);
            // NRI_QueryFundOrderWebExtを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, FundCancelOrder1Out.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 信用一括返済注文:NRI_MarginLumpPlaceOrder
     *
     * @param input 信用一括返済注文リクエスト
     * @return 信用一括返済注文レスポンス
     * @throws Exception 例外が発生した場合
    */
    public MarginLumpPlaceOrderOutData marginLumpPlaceOrder(MarginLumpPlaceOrderIn input) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.marginLumpPlaceOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        MarginLumpPlaceOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            final RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            final jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriMarginLumpPlaceOrderIn(header, input.getIndata());
            // NRI_StockLumpPlaceOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, MarginLumpPlaceOrderOut.class);
        } catch (IOException e) {
            final ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            for(MarginLumpPlaceOrderOutVec detail :nrioutput.getOutdata().getCodeT()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 返済順序指定信用一括返済注文:NRI_MarginLumpPlaceOrderSort
     *
     * @param input 返済順序指定信用一括返済注文リクエスト
     * @return 返済順序指定信用一括返済注文レスポンス
     * @throws Exception 例外が発生した場合
    */
    public MarginLumpPlaceOrderSortOutData marginLumpPlaceOrderSort(MarginLumpPlaceOrderSortIn input) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.marginLumpPlaceOrderSort : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        MarginLumpPlaceOrderSortOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            final RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            final jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriMarginLumpPlaceOrderSortIn(header, input.getIndata());
            // NRI_StockLumpPlaceOrderSortAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, MarginLumpPlaceOrderSortOut.class);
        } catch (IOException e) {
            final ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 信用建玉余力リクエスト:NRI_EstimateMgRcptDliverOrder
     * 信用建玉余力情報を取得する
     *
     * @param input 検索条件
     * @return 信用建玉余力
     * @throws Exception 異常
     */
    public EstimateMgRcptDliverOrderOutData estimateMgRcptDliverOrder(EstimateMgRcptDliverOrderIn input)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateMgRcptDliverOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        EstimateMgRcptDliverOrderOut out = null;
        try {
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            out = callHttpApiWrraper(new NriEstimateMgRcptDliverOrderIn(header, input.getIndata()),
                    EstimateMgRcptDliverOrderOut.class);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        checkApplicationValidationResult(out.getHeader().getCode(), out.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        if (out.getOutdata() == null) {
            return null;
        }
        EstimateMgRcptDliverOrderOutData outData = out.getOutdata();
        TrimUtil.trimStringFields(outData);
        return outData;
    }
    
    /**
     * NRI 信用現引現渡申込み
     * NRI_MarginlReceiptDeliverOrder
     *
     * @param input 信用現引現渡申込情報
     * @return 信用現引現渡申込結果情報
     * @throws Exception 異常
     */
    public MarginlReceiptDeliverOrderOutData marginlReceiptDeliverOrder(MarginlReceiptDeliverOrderIn input)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.marginlReceiptDeliverOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        try {
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            MarginlReceiptDeliverOrderOut out = callHttpApiWrraper(
                    new NriMarginlReceiptDeliverOrderIn(header, input.getIndata()),
                    MarginlReceiptDeliverOrderOut.class);
            checkApplicationValidationResult(out.getHeader().getCode(), out.getHeader().getMessage());
            logger.info("cost -> {}", (System.currentTimeMillis() - start));
            MarginlReceiptDeliverOrderOutData outData = out.getOutdata();
            TrimUtil.trimStringFields(outData);
            return outData;
        } catch (ApiError e) {
            throw e;
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
    }
    
    /**
     * NRI 信用現引現渡取消
     * NRI_MgRcptDeliverCancelOrder
     *
     * @param input 信用現引現渡取消リクエスト
     * @return 信用現引現渡取消
     * @throws Exception 異常
     */
    public MgRcptDeliverCancelOrderOutData mgRcptDeliverCancelOrder(MgRcptDeliverCancelOrderIn input) throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.info("ApiWrapper.mgRcptDeliverCancelOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        MgRcptDeliverCancelOrderOut out = null;
        try {
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            out = callHttpApiWrraper(new NriMgRcptDeliverCancelOrderIn(header, input.getIndata()),
                    MgRcptDeliverCancelOrderOut.class);
        } catch (IOException e) {
            logger.warn("Exception occured.", e);
            throw new ApiIOException(e);
        } catch (Exception e) {
            logger.error("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        checkApplicationValidationResult(out.getHeader().getCode(), out.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        if (out.getOutdata() == null) {
            return null;
        }
        MgRcptDeliverCancelOrderOutData outData = out.getOutdata();
        TrimUtil.trimStringFields(outData);
        return outData;
    }
    
    /**
     * 国内株式注文．
     * 
     * @param input
     * @return 国内株式注文
     * @throws Exception 異常
     */
    public StockPlaceOrderOutData stockPlaceOrder(StockPlaceOrderInData input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.stockPlaceOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        StockPlaceOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(
                    input.getButenCd());
            //　内部→外部コード変換
            //売買区分
            String tradeKbn;
            tradeKbn = codelistservice.convertKeyToExtKey("DOMESTIC_STOCK_TRADE_CLASS", "EC-GW2", input.getTradeKbn());
            input.setTradeKbn(tradeKbn);
            //手数料区分
            String comId;
            comId = codelistservice.convertKeyToExtKey("PRE_CONTRACT_DOC_CODE", "EC-GW", input.getComId());
            input.setComId(comId);
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriStockPlaceOrderIn(header, input);
            // NRI_StockPlaceOrderを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, StockPlaceOrderOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * 投信（一般口）注文:NRI_FundPlaceOrder1
     *
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常   
     */
    public FundPlaceOrder1OutData fundPlaceOrder1(FundPlaceOrder1InData input) 
            throws Exception{
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.fundPlaceOrder1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input,ToStringStyle.MULTI_LINE_STYLE));
        
        FundPlaceOrder1Out nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriFundPlaceOrder1In(header, input);
            // NRI_QueryFundOrderWebExtを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, FundPlaceOrder1Out.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
        return null;
        }
    }

    public FundPlaceOrder2ExtOutData fundPlaceOrder2Ext(FundPlaceOrder2ExtInData input) 
            throws Exception{
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.fundPlaceOrder2Ext : {}", hashCode());
        
        FundPlaceOrder2ExtOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            jp.co.sbisec.pcenter.dto.yanap.RequestHeader header = new jp.co.sbisec.pcenter.dto.yanap.RequestHeader(input.getButenCd());
            //　内部→外部コード変換
            // ポイント種別
            String pointType;
            pointType = codelistservice.convertKeyToExtKey("POINT_TYPE", "EC-GW", input.getPointType());
            input.setPointType(pointType);
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriFundPlaceOrder2ExtIn(header, input);
            // NRI_QueryFundOrderWebExtを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, FundPlaceOrder2ExtOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻る結果利用ください
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
        return null;
        }
    }
    /**
     * NRI 投信銘柄別受渡日算出
     * NRI_QueryFundSettlementDate
     *
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常
    */
    public QueryFundSettlementDateOutData queryFundSettlementDate(QueryFundSettlementDateIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryFundSettlementDate : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        QueryFundSettlementDateOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryFundSettlementDateIn(header, input.getIndata());
            // NRI_QueryFundSettlementDateを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryFundSettlementDateOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI NISA買付可能最大額リクエスト(汎用累投)
     * NRI_QueryIsaFundMaxBuyAmount
     *
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常
    */
    public QueryIsaFundMaxBuyAmountOutData queryIsaFundMaxBuyAmount(QueryIsaFundMaxBuyAmountIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryIsaFundMaxBuyAmount : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        QueryIsaFundMaxBuyAmountOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryIsaFundMaxBuyAmountIn(header, input.getIndata());
            // NRI_QueryIsaFundMaxBuyAmountを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryIsaFundMaxBuyAmountOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 投信（一般口）注文確認
     * NRI_EstimateFundOrder1
     *
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常
    */
    public EstimateFundOrder1OutData estimateFundOrder1(EstimateFundOrder1In input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateFundOrder1 : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        EstimateFundOrder1Out nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateFundOrder1In(header, input.getIndata());
            // NRI_EstimateFundOrder1を呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateFundOrder1Out.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI 投信（汎用累投）注文確認（口数売却拡張版）
     * NRI_EstimateFundOrder2Ext
     *
     * @param input 検索条件
     * @return 出力データ
     * @throws Exception 異常
    */
    public EstimateFundOrder2ExtOutData estimateFundOrder2Ext(EstimateFundOrder2ExtIn input) throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateFundOrder2Ext : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        EstimateFundOrder2ExtOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            //　内部→外部コード変換
            // ポイント種別
            String pointType;
            pointType = codelistservice.convertKeyToExtKey("POINT_TYPE", "EC-GW", input.getIndata().getPointType());
            input.getIndata().setPointType(pointType);
            // API inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateFundOrder2ExtIn(header, input.getIndata());
            // NRI_EstimateFundOrder2Extを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateFundOrder2ExtOut.class);
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getHeader().getCode(), nrioutput.getHeader().getMessage());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // API 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata());
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * NRI 国内株式・信用訂正注文
     * NRI_StockModifyOrder
     *
     * @param input 国内株式・信用訂正注文情報
     * @return 国内株式・信用訂正注文結果
     * @throws Exception 異常
     */
    public StockModifyOrderOutData stockModifyOrder(StockModifyOrderIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.stockModifyOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 外部コード変換
        input.getIndata()
                .setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        StockModifyOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriStockModifyOrderIn(header, input.getIndata());
            // NRI_StockModifyOrderを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, StockModifyOrderOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        StockModifyOrderOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(outData, "modLimitFlg", "comIdR");
            return outData;
        } else {
            return null;
        }
    }

    /**
     * NRI 自動注文(国内株式)訂正注文受付
     * NRI_StockModifyOrderAuto
     *
     * @param input 自動注文(国内株式)訂正注文情報
     * @return 自動注文(国内株式)訂正注文結果
     * @throws Exception 異常
     */
    public StockModifyOrderAutoOutData stockModifyOrderAuto(StockModifyOrderAutoIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.stockModifyOrderAuto : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // 外部コード変換
        input.getIndata()
                .setComId(codelistservice.convertKeyToExtKey("COMM_TYPE", "EC-GW", input.getIndata().getComId()));
        StockModifyOrderAutoOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriStockModifyOrderAutoIn(header, input.getIndata());
            // NRI_StockModifyOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, StockModifyOrderAutoOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        StockModifyOrderAutoOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(outData, "modLimitFlg", "comIdR");
            return outData;
        } else {
            return null;
        }
    }

    /**
     * NRI リアル維持率リクエスト
     * NRI_QueryRealCapability
     *
     * @param input indata
     * @return outdata
     * @throws Exception 異常
     */
    public QueryRealCapabilityOutData queryRealCapability(QueryRealCapabilityIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryRealCapability : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        QueryRealCapabilityOut nrioutput = null;
        try {
            // ECGateway呼び出し
            
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getOfficeCode());
            
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryRealCapabilityIn(header, input.getIndata());
            // NRI_StockModifyOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, QueryRealCapabilityOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata(), "autoSweepKbn");
            for (QueryRealCapabilityOutDetailT detail : nrioutput.getOutdata().getOutDetailT()) {
                TrimUtil.trimStringFields(detail);
            }
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }

    /**
     * NRI 出金確認 NRI_EstimateCashPaymentOrder
     *
     * @param input indata
     * @return outdata
     * @throws Exception 異常
     */
    public EstimateCashPaymentOrderOutData estimateCashPaymentOrder(EstimateCashPaymentOrderIn input) throws Exception {
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateCashPaymentOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        EstimateCashPaymentOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し

            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());

            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateCashPaymentOrderIn(header, input.getIndata());
            // NRI_StockModifyOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateCashPaymentOrderOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        EstimateCashPaymentOrderOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(outData.getPayEstimateData(), "pay_amount");
            return outData;
        } else {
            return null;
        }
    }

    /**
     * NRI 出金受付 NRI_CashPaymentOrder
     *
     * @param input indata
     * @return outdata
     * @throws Exception 異常
     */
    public CashPaymentOrderOutData cashPaymentOrder(CashPaymentOrderIn input) throws Exception {
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.cashPaymentOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        CashPaymentOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し

            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());

            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriCashPaymentOrderIn(header, input.getIndata());
            // NRI_StockModifyOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, CashPaymentOrderOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        CashPaymentOrderOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(outData.getPayOrderData(), "pay_amount");
            return outData;
        } else {
            return null;
        }
    }

    /**
     * NRI 出金取消 NRI_CashPaymentCancelOrder
     *
     * @param input indata
     * @return outdata
     * @throws Exception 異常
     */
    public CashPaymentCancelOrderOutData cashPaymentCancelOrder(CashPaymentCancelOrderIn input) throws Exception {
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.cashPaymentOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        CashPaymentCancelOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し

            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());

            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriCashPaymentCancelOrderIn(header, input.getIndata());
            // NRI_StockModifyOrderAutoを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, CashPaymentCancelOrderOut.class);

        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }

        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        CashPaymentCancelOrderOutData outData = nrioutput.getOutdata();
        if (outData != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(outData.getPayCxlData(), "pay_amount");
            return outData;
        } else {
            return null;
        }
    }
    
    /**
     * NRI その他商品の注文確認
     * NRI_Estimateadditionalorder
     *
     * @param input indata
     * @return outdata
     * @throws Exception 異常
     */
    public EstimateAdditionalOrderOutData estimateadditionalorder(EstimateAdditionalOrderIn input)
            throws Exception {
        
        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.estimateadditionalorder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        EstimateAdditionalOrderOut nrioutput = null;
        try {
            // ECGateway呼び出し
            // Request Headerに「部店」を設定する
            RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
            // api inputを作成する
            jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriEstimateAdditionalOrderIn(header, input.getIndata());
            
            // NRI_Estimateadditionalorderを呼び出す
            nrioutput = callHttpApiWrraper(dtoIn, EstimateAdditionalOrderOut.class);
            
        } catch (IOException e) {
            ApiIOException throwE = new ApiIOException(e);
            logger.warn(throwE.getMessage());
            throw throwE;
        } catch (Exception e) {
            // API接続異常処理
            logger.error("Exception occured.");
            logger.info("Exception occured.", e);
            throw new ApiConnectionException(e);
        }
        
        // Check validation result.
        this.checkApplicationValidationResult(nrioutput.getOutdata());
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        // ECGatewayの戻り値がnullかである判断
        if (nrioutput.getOutdata() != null) {
            // Api 戻り値を処理
            TrimUtil.trimStringFields(nrioutput.getOutdata(), "autoSweepKbn");
            return nrioutput.getOutdata();
        } else {
            return null;
        }
    }
    
    /**
     * NRI その他余力拘束状況リクエスト
     * NRI_QueryAdditionalOrder
     * @param input
     * @return
     * @throws Exception
     */
    private static final int MAX_QUERY_ADDITIONAL_ORDER = 100;
    
    public QueryAdditionalOrderOutData queryAdditionalOrder(QueryAdditionalOrderIn input) throws Exception {

        long start = System.currentTimeMillis();
        logger.info("ApiWrapper.queryAdditionalOrder : {}", hashCode());
        logger.info("input : {}", ReflectionToStringBuilder.toString(input, ToStringStyle.MULTI_LINE_STYLE));
        // その他余力拘束状況リクエスト
        QueryAdditionalOrderOutData results = new QueryAdditionalOrderOutData();

        // 件数初期化
        int refFrom = 1;
        int refTo = MAX_QUERY_ADDITIONAL_ORDER;
        
        QueryAdditionalOrderOut nrioutput = null;

        while (true) {
            // n件 ～ n+100件
            input.getIndata().setRefFrom(dcf.format(refFrom)); // 取得開始位置
            input.getIndata().setRefTo(dcf.format(refTo)); // 取得終了位置
            
            try {
                // ECGateway呼び出し
                
                // Request Headerに「部店」を設定する
                RequestHeader header = new RequestHeader(input.getIndata().getButenCd());
                // API inputを作成する
                jp.co.sbisec.pcenter.dto.DtoIn dtoIn = new NriQueryAdditionalOrderIn(header, input.getIndata());
                // NRI_QueryAdditionalOrderを呼び出す
                nrioutput = callHttpApiWrraper(dtoIn, QueryAdditionalOrderOut.class);
            } catch (IOException e) {
                ApiIOException throwE = new ApiIOException(e);
                logger.warn(throwE.getMessage());
                throw throwE;
            } catch (Exception e) {
                // API接続異常処理
                throw new ApiConnectionException(e);
            }
            
            // Check validation result.
            this.checkApplicationValidationResult(nrioutput.getOutdata());
            
            // ECGatewayの戻り値がnullかである判断
            if (nrioutput.getOutdata() != null) {
                // API 戻り値を処理
                if (nrioutput.getOutdata().getQueryAdditionalOrderData() != null) {
                    TrimUtil.trimStringFields( nrioutput.getOutdata());
                }
                // その他余力拘束状況リクエストの後ろに結合する
                if (null == results.getQueryAdditionalOrderData()) {
                    BeanUtils.copyProperties(results, nrioutput.getOutdata());
                } else {
                    results.getQueryAdditionalOrderData().addAll(nrioutput.getOutdata().getQueryAdditionalOrderData()); 
                }
                
                if (refTo >= Integer.parseInt(nrioutput.getOutdata().getHitNumber())) {
                    break;
                }
                refFrom += MAX_QUERY_ADDITIONAL_ORDER;
                refTo += MAX_QUERY_ADDITIONAL_ORDER;
            }
        }
        logger.info("cost -> {}", (System.currentTimeMillis() - start));
        return results;
    }
}
