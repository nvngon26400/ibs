package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailCommonRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailCommonResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.SettlementPositionListModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.TradeListModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaMatchedTradeDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionDetail1Detail;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionDetail1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionDetail1OutData;
//import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract2Data;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract2Detail;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract2InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMarginContract2OutData;
//import jp.co.sbisec.pcenter.dto.yanap.TradeList;

/**
 * 画面ID：SUB0202_0105-02
 * 画面名：出来明細

 * @author 松尾
 *
 * 　　　　2023/09/14 新規作成
 */
@Component(value = "cmpIfaMatchedTradeDetailService")
public class IfaMatchedTradeDetailServiceImpl implements IfaMatchedTradeDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMatchedTradeDetailServiceImpl.class);
    
    @Autowired
    private ApiWrapper apiwrapper;
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /** 権限チェックエラー   */
    public static final String ERRORS_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 権限チェックエラー値 */
    public static final String AUTH_ERROR_VALUE = "0";
    
    /** 通常口座およびJrNISA口座の第一口座 */
    public static final String JR_NISA_FIRST_ACCOUNT = " ";
    
    /** JrNISA口座(第一、第二口座両方) */
    public static final String JR_NISA_ACCOUNT = "2";
    
    /** API001 取得件数が0件の場合のメッセージ   */
    public static final String INFORMATION_NOTFOUND = "errors.cmn.information.notfound";
    
    /** 正常終了値 */
    public static final String RETURN_CODE_SUCCESS = "0";
    
    /** 正常終了メッセージ */
    public static final String RETURN_MESSEAGE_SUCCESS = "";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMatchedTradeDetailA001DtoRequest
     * Dto レスポンス：IfaMatchedTradeDetailA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     * @param a001DtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMatchedTradeDetailA001ResponseDto> initializeA001(IfaMatchedTradeDetailA001RequestDto a001DtoReq)
            throws Exception {
        
        DataList<IfaMatchedTradeDetailA001ResponseDto> a001DtoRes = new DataList<IfaMatchedTradeDetailA001ResponseDto>();
        
        IfaMatchedTradeDetailCommonRequestDto commonDtoReq = new IfaMatchedTradeDetailCommonRequestDto();
        
        BeanUtils.copyProperties(commonDtoReq, a001DtoReq);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMatchedTradeDetailServiceImpl.initializeA001");
        }
        
        DataList<IfaMatchedTradeDetailCommonResponseDto> commonDtoRes = commonAction(commonDtoReq);
        BeanUtils.copyProperties(a001DtoRes, commonDtoRes);
        return a001DtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：更新
     * Dto リクエスト：IfaMatchedTradeDetailA003DtoRequest
     * Dto レスポンス：IfaMatchedTradeDetailA003DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     * @param a003DtoReq リクエスト
     * @return レスポンス
     * @exception Exception 更新処理で例外が発生した場合
     */
    public DataList<IfaMatchedTradeDetailA003ResponseDto> updateA003(IfaMatchedTradeDetailA003RequestDto a003DtoReq)
            throws Exception {
        
        DataList<IfaMatchedTradeDetailA003ResponseDto> a003DtoRes = new DataList<IfaMatchedTradeDetailA003ResponseDto>();
        
        IfaMatchedTradeDetailCommonRequestDto commonDtoReq = new IfaMatchedTradeDetailCommonRequestDto();
        
        BeanUtils.copyProperties(commonDtoReq, a003DtoReq);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMatchedTradeDetailServiceImpl.updateA003");
        }
        
        DataList<IfaMatchedTradeDetailCommonResponseDto> commonDtoRes = commonAction(commonDtoReq);
        
        BeanUtils.copyProperties(a003DtoRes, commonDtoRes);
        return a003DtoRes;
    }
    
    /**
     * A001 A003 共通処理Action

     * @param commonDtoReq リクエスト
     * @return レスポンス
     * @throws Exception 処理で例外が発生した場合
     */
    private DataList<IfaMatchedTradeDetailCommonResponseDto> commonAction(
            IfaMatchedTradeDetailCommonRequestDto commonDtoReq) throws Exception {
        
        DataList<IfaMatchedTradeDetailCommonResponseDto> commonDtoRes = new DataList<IfaMatchedTradeDetailCommonResponseDto>();
        List<IfaMatchedTradeDetailCommonResponseDto> commonResDto = new ArrayList<IfaMatchedTradeDetailCommonResponseDto>();
        
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        // 顧客情報の取得
        // 現在は仮項目設定しかしていないのでそれを用いて処理する
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //①利用者の口座に対する権限チェックを行う。
        //本来は顧客情報.部店コードと顧客情報.口座番号を利用して取得する
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(cc.getButenCode());
        inputFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto fct001Dto = fct001.doCheck(inputFct001Dto);
        //FCT001.対象顧客参照権限有無 = '1':権限あり の場合、：次の処理へ。
        //FCT001.対象顧客参照権限有無 ≠ '1':権限あり の場合：権限なしエラーを返す。
        if (StringUtils.equals(fct001Dto.getTargetCustomerRefAuthFlag(), AUTH_ERROR_VALUE)) {
            commonDtoRes = IfaCommonUtil.createDataList(commonResDto, ErrorLevel.FATAL, ERRORS_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_ACCOUNT_NOT_EXISTS,
                            new String[] { cc.getButenCode(), cc.getAccountNumber() }));
            return commonDtoRes;
        }
        
        //②約定一覧を取得する以下の処理を行う。
        //APIから約定一覧を取得する。
        QueryExecutionDetail1InData queryExecutionDetail1InData = new QueryExecutionDetail1InData();
        queryExecutionDetail1InData.setButenCd(cc.getButenCode());
        queryExecutionDetail1InData.setKozaNo(String.format("%7s", cc.getAccountNumber()).replace(" ", "0"));
        queryExecutionDetail1InData.setSecId("S");
        queryExecutionDetail1InData.setRequestDate(commonDtoReq.getTradeDate());
        queryExecutionDetail1InData.setSettlementDate(commonDtoReq.getDeliveryDate());
        queryExecutionDetail1InData.setMarketMakerCode(" ");
        queryExecutionDetail1InData.setSecCode(commonDtoReq.getBrandCode());
        queryExecutionDetail1InData.setTradeId(commonDtoReq.getTradeClassification());
        queryExecutionDetail1InData.setPaymentLimit(commonDtoReq.getPaymentDeadline());
        queryExecutionDetail1InData.setHitokuteiTradeKbn(commonDtoReq.getNotSpecificDepositTradeType());
        String accountGetKbn = "";
        if (!(StringUtils.equals(cc.getJrIsaContractType(), "1"))) {
            accountGetKbn = JR_NISA_FIRST_ACCOUNT;
        } else {
            accountGetKbn = JR_NISA_ACCOUNT;
        }
        queryExecutionDetail1InData.setAccountGetKbn(accountGetKbn);
        QueryExecutionDetail1OutData outData = apiwrapper.queryExecutionDetail1(queryExecutionDetail1InData);
        apiErrorUtil.checkApiResponse(outData.getShubetu(), outData.getCode(), outData.getMessage());
        if (apiErrorUtil.isFatal()) {
            return apiErrorUtil.createDataList(new ArrayList<>(), null);
        }
        
        //取得件数が0件の場合：メッセージを表示して、処理終了。
        //上記以外の場合：次の処理へ。
        if (outData.getExecDetailData().size() == 0) {
            commonDtoRes = IfaCommonUtil.createDataList(commonResDto, ErrorLevel.FATAL, INFORMATION_NOTFOUND,
                    IfaCommonUtil.getMessage(INFORMATION_NOTFOUND));
            return commonDtoRes;
        }
        
        //市場（算出）を算出する。
        //算出の仕様は別紙.明細算出方法の「市場」を参照。　　
        String market = "";
        List<TradeListModel> tradeList = new ArrayList<TradeListModel>();
        for (QueryExecutionDetail1Detail record : outData.getExecDetailData()) {
            //市場　= "-"
            market = "-";
            if (StringUtils.equals(record.getOddStockOrderFlg(), "0")) {
                
                if (StringUtils.equals(record.getMarket(), "0")) {
                    if (StringUtils.equals(record.getProductType(), "T")) {
                        //市場 = "東証(外)"
                        market = "東証(外)";
                    } else {
                        //市場 = "東証"
                        market = "東証";
                    }
                } else if (StringUtils.equals(record.getMarket(), "1")) {
                    //市場 = "大証"
                    market = "大証";
                } else if (StringUtils.equals(record.getMarket(), "2")) {
                    //市場 = "名証"
                    market = "名証";
                } else if (StringUtils.equals(record.getMarket(), "5")) {
                    //市場 = "ＨＣ"
                    market = "ＨＣ";
                } else if (StringUtils.equals(record.getMarket(), "6")) {
                    //市場 = "福証"
                    market = "福証";
                } else if (StringUtils.equals(record.getMarket(), "7")) {
                    if (StringUtils.equals(record.getSorLinkKbn(), " ")
                            || (StringUtils.equals(record.getSorLinkKbn(), "1"))) {
                        if (StringUtils.equals(record.getProductType(), "D")) {
                            //市場 = "PTS(J)"
                            market = "PTS(J)";
                        } else if (StringUtils.equals(record.getProductType(), "X")) {
                            //市場 = "PTS(X)"
                            market = "PTS(X)";
                        } else if (StringUtils.equals(record.getProductType(), "O")) {
                            //市場 = "PTS(O)"
                            market = "PTS(O)";
                        }
                    } else {
                        //市場 = "PTS"
                        market = "PTS";
                    }
                } else if (StringUtils.equals(record.getMarket(), "8")) {
                    //市場 = "札幌 "
                    market = "札幌 ";
                } else if (StringUtils.equals(record.getMarket(), "9")) {
                    //市場 = "ＪＱ"
                    market = "ＪＱ";
                }
            } else if (StringUtils.equals(record.getOddStockOrderFlg(), "1")) {
                if (StringUtils.equals(record.getTradePartnerCode(), " ")) {
                    if (StringUtils.equals(record.getBrokerCode(), "G02")) {
                        //市場 = "取次"
                        market = "取次";
                    }
                } else if (StringUtils.equals(record.getTradePartnerCode(), "1")) {
                    if (StringUtils.equals(record.getBrokerCode(), " ")) {
                        //市場 = "自己"
                        market = "自己";
                    }
                }
            }
            TradeListModel res = new TradeListModel();
            res.setTradeTime(record.getTradeTime());
            res.setTradeQuantity(record.getQuantity());
            res.setTradePrice(record.getPrice());
            res.setContractAmount(record.getAmount());
            res.setDepositType(record.getHitokuteiTradeKbn());
            res.setEcOrderNo(record.getOrderNo());
            res.setCancelStatus(record.getCxlStatus());
            res.setMarket(market);
            tradeList.add(res);
        }
        
        ArrayList<String> ecOrderNoList = new ArrayList<String>();
        List<SettlementPositionListModel> settlementPositionList = new ArrayList<SettlementPositionListModel>();
        //③,④リクエスト.取引区分が"5":返済買 または "6":返済売 の場合、
        if (StringUtils.equals(commonDtoReq.getTradeClassification(), "5")
                || StringUtils.equals(commonDtoReq.getTradeClassification(), "6")) {
            //API001.約定一覧.EC受注番号より重複を除いた、EC受注番号（重複なし）を求める。　（ソート順は変更しない）
            for (QueryExecutionDetail1Detail rec : outData.getExecDetailData()) {
                if (ecOrderNoList.contains(rec.getOrderNo())) {
                    continue;
                } else {
                    ecOrderNoList.add(rec.getOrderNo());
                }
            }
            
            //EC受注番号（重複なし）分だけ決済建玉一覧を取得する以下の処理を繰り返す。"
            for (String rec2 : ecOrderNoList) {
                QueryMarginContract2InData queryMarginContract2InData = new QueryMarginContract2InData();
                queryMarginContract2InData.setButenCd(cc.getButenCode());
                queryMarginContract2InData.setKozaNo(String.format("%7s", cc.getAccountNumber()).replace(" ", "0"));
                queryMarginContract2InData.setOrderNum(String.format("%6s", rec2).replace(" ", "0"));
                QueryMarginContract2OutData outData2 = apiwrapper.queryMarginContract2(queryMarginContract2InData);
                apiErrorUtil.checkApiResponse(outData2.getShubetu(), outData2.getCode(), outData2.getMessage());
                if (apiErrorUtil.isFatal()) {
                    return apiErrorUtil.createDataList(new ArrayList<>(), null);
                }
                
                //取得件数が0件の場合：メッセージを表示して、処理終了。
                if (outData2.getQueryMarginContract2Data().size() == 0) {
                    commonDtoRes = IfaCommonUtil.createDataList(commonResDto, ErrorLevel.FATAL, INFORMATION_NOTFOUND,
                            IfaCommonUtil.getMessage(INFORMATION_NOTFOUND));
                    return commonDtoRes;
                }
                
                for (QueryMarginContract2Detail queryMarginContract2Data : outData2.getQueryMarginContract2Data()) {
                    
                    //平均約定単価（算出）を算出する。
                    BigDecimal amount = new BigDecimal(queryMarginContract2Data.getAmount());
                    BigDecimal openPrice = new BigDecimal(queryMarginContract2Data.getOpenPrice());
                    BigDecimal actualQuantity = new BigDecimal(queryMarginContract2Data.getActualQuantity());
                    BigDecimal cost = new BigDecimal(queryMarginContract2Data.getCost());
                    BigDecimal oneHundred = new BigDecimal("100");
                    BigDecimal averageTradePrice = null;
                    BigDecimal settlementLossProfit = null;
                    BigDecimal builtPrice = new BigDecimal(queryMarginContract2Data.getOpenPrice()).divide(oneHundred);
                    //●API002.建玉明細.約定数量 = 0 の場合
                    if (StringUtils.equals(queryMarginContract2Data.getActualQuantity(), "0")) {
                        //平均約定単価（算出）= 0 
                        averageTradePrice = BigDecimal.ZERO;
                        
                        //●API002.建玉明細.約定数量 ≠ 0 の場合    
                    } else if (!(StringUtils.equals(queryMarginContract2Data.getActualQuantity(), "0"))) {
                        //平均約定単価（算出）= API002.建玉明細.約定金額 / API002.建玉明細.約定数量
                        averageTradePrice = amount.divide(actualQuantity, 0, RoundingMode.FLOOR);
                    }
                    
                    //決済損益（算出）を算出する。
                    //●API002.新規売買区分 = "0":買建 の場合、              
                    if (StringUtils.equals(outData2.getOpenTradeKbn(), "0")) {
                        //決済損益（算出）= API002.建玉明細.約定金額 -（API002.建玉明細.取得単価 / 100 * API002.建玉明細.約定数量）- API002.建玉明細.諸経費合計
                        settlementLossProfit = amount.subtract(openPrice.divide(oneHundred).multiply(actualQuantity))
                                .subtract(cost);
                        
                        //●API002.新規売買区分 = "1":売建 の場合、    
                    } else if (StringUtils.equals(outData2.getOpenTradeKbn(), "1")) {
                        //決済損益（算出）=（API002.建玉明細.取得単価 / 100 * API002.建玉明細.約定数量）- API002.建玉明細.約定金額 - API002.建玉明細.諸経費合計
                        settlementLossProfit = (openPrice.divide(oneHundred).multiply(actualQuantity)).subtract(amount)
                                .subtract(cost);
                    }
                    
                    //取得した決済建玉一覧を既存の決済建玉一覧の後ろに結合する。 
                    SettlementPositionListModel res = new SettlementPositionListModel();
                    res.setOpenTradeDate(queryMarginContract2Data.getOpenTradeDate());
                    res.setLastTradeDate(queryMarginContract2Data.getLastTradeDate());
                    res.setBargainMarket(queryMarginContract2Data.getBargainMarket());
                    res.setOrgNewTradeDate(queryMarginContract2Data.getOrgNewTradeDate());
                    res.setBuiltPrice(builtPrice.toString());
                    res.setOrderQuantity(queryMarginContract2Data.getQuantity());
                    res.setActualQuantity(queryMarginContract2Data.getActualQuantity());
                    res.setCostTotal(queryMarginContract2Data.getCost());
                    res.setAverageTradePrice(averageTradePrice.toString());
                    res.setSettlementLossProfit(settlementLossProfit.toString());
                    settlementPositionList.add(res);
                }
            }
        }
        // システム日付取得
        String systemDate = ifaDateUtil.format(IfaDateUtil.SEPARATED_YYYYMMDD_HHMMSS);
        
        IfaMatchedTradeDetailCommonResponseDto dto = new IfaMatchedTradeDetailCommonResponseDto();
        dto.setTradeDate(commonDtoReq.getTradeDate());
        dto.setDeliveryDate(commonDtoReq.getDeliveryDate());
        dto.setBrandCode(commonDtoReq.getBrandCode());
        dto.setBrandName(commonDtoReq.getBrandName());
        dto.setTradeClassification(commonDtoReq.getTradeClassification());
        dto.setPaymentDeadline(commonDtoReq.getPaymentDeadline());
        dto.setMarginTradeTypeText(commonDtoReq.getMarginTradeTypeText());
        dto.setNotSpecificDepositTradeType(commonDtoReq.getNotSpecificDepositTradeType());
        dto.setUpdateTime(systemDate);
        dto.setSettlementPositionList(settlementPositionList);
        dto.setTradeList(tradeList);
        commonResDto.add(dto);
        commonDtoRes = apiErrorUtil.createDataList(commonResDto, null);
        return commonDtoRes;
    }
}
