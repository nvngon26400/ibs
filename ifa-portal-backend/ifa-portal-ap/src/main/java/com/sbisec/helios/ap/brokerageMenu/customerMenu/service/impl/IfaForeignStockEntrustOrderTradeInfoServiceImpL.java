package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.OrderHistory;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockMarginOrderDetailResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetForeignStockOrderDetailResp;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockEntrustOrderTradeInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockEntrustOrderTradeInfoA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignStockEntrustOrderTradeInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.service.CometCommonService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0202_0104-02
 * 画面名：外国株式委託注文約定情報
 *
 * @author SCSK 矢口
 * 
 *         2024/04/02 新規作成
 */
@Component(value = "cmpIfaForeignStockEntrustOrderTradeInfoService")
public class IfaForeignStockEntrustOrderTradeInfoServiceImpL implements IfaForeignStockEntrustOrderTradeInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignStockEntrustOrderTradeInfoServiceImpL.class);
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private ForeignStockService foreignStockService;

    @Autowired
    private CometCommonService cometCommonService;
    
    /** 権限チェックエラー */
    private static final String ERRORS_BUTEN_ACCOUNT_NOT_EXISTS = "errors.butenAccountNotExist";
    
    /** 取引種別:0(現物買) */
    private static final String TRADE_CLASS_TRADE_BUY = "0";
    
    /** 取引種別:1(現物売) */
    private static final String TRADE_CLASS_TRADE_SELL = "1";
    
    /** 取引種別:2(新規買) */
    private static final String TRADE_CLASS_MARGIN_TRADE_BUY = "2";
    
    /** 取引種別:3(新規売) */
    private static final String TRADE_CLASS_MARGIN_TRADE_SELL = "3";
    
    /** 取引種別:4(返済買) */
    private static final String TRADE_CLASS_MARGIN_REPAYMENT_BUY = "4";
    
    /** 取引種別:5(返済売) */
    private static final String TRADE_CLASS_MARGIN_REPAYMENT_SELL = "5";
    
    /** 取引種別:6(定期) */
    private static final String TRADE_CLASS_RESERVE_STOCK_ORDER = "6";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockEntrustOrderTradeInfoA001DtoRequest
     * Dto レスポンス：IfaForeignStockEntrustOrderTradeInfoA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse> initializeA001(
            IfaForeignStockEntrustOrderTradeInfoA001DtoRequest dtoReq) throws Exception {
        
        DataList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse> dtoRes = new DataList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse>();
        List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse> resDtoList = new ArrayList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockEntrustOrderTradeInfoServiceImpL.initializeA001");
        }
        
        
        
        /* ============================== */
        /* ①利用者の口座に対する権限チェックを行う  */
        /* ============================== */
        
        // 顧客共通情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        
        // FCT001の実行
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(customerCommon.getButenCode());
        inputFct001Dto.setAccountNumber(customerCommon.getAccountNumber());
        
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        
        /*
         * 権限あり：次の処理へ。
         * 権限なし：権限なしエラーを返す。
         */
        if (StringUtils.equals(outputFct001Dto.getTargetCustomerRefAuthFlag(),
                TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
            return IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOT_EXISTS,
                            new String[] { customerCommon.getButenCode(), customerCommon.getAccountNumber() }));
        }
        
        
        
        /* ============================== */
        /* ②委託注文約定情報を取得する。        */
        /* ============================== */
        
        // 部店コード
        String butenCode = customerCommon.getButenCode();
        // 口座番号(先頭"0"補完して7桁)
        String accountNumber = (String.format("%7s", customerCommon.getAccountNumber()).replace(" ", "0"));
        
        List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo> entrustOrderTradeinfoList =
                new ArrayList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo>();
        
        
        /*
         * 遷移元画面パラメータ.取引種別=0(現物買),1(現物売),6(定期)のいずれかの場合、
         * 外国株式現物注文詳細取得API(API001)を呼び出して委託注文約定情報を取得
         */
        if (StringUtils.equals(TRADE_CLASS_TRADE_BUY, dtoReq.getTradeCd())
                || StringUtils.equals(TRADE_CLASS_TRADE_SELL, dtoReq.getTradeCd())
                || StringUtils.equals(TRADE_CLASS_RESERVE_STOCK_ORDER, dtoReq.getTradeCd())) {
            
            // API001呼び出し
            GetForeignStockOrderDetailResp api001Res = new GetForeignStockOrderDetailResp();
            try {
                api001Res = foreignStockService.getForeignStockOrderDetail(butenCode, accountNumber,
                        dtoReq.getOrderNumber());
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaForeignStockEntrustOrderTradeInfoServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            
            // API001から取得した値を委託注文約定情報リストに格納
            entrustOrderTradeinfoList = getForeignStockOrderDetailResult(api001Res);
        }
        
        
        /*
         * 遷移元画面パラメータ.取引種別=2(新規買),3(新規売),4(返済買),5(返済売)のいずれかの場合、
         * 外国株式信用注文詳細取得API(API002)を呼び出して委託注文約定情報を取得()
         */
        if (StringUtils.equals(TRADE_CLASS_MARGIN_TRADE_BUY, dtoReq.getTradeCd())
                || StringUtils.equals(TRADE_CLASS_MARGIN_TRADE_SELL, dtoReq.getTradeCd())
                || StringUtils.equals(TRADE_CLASS_MARGIN_REPAYMENT_BUY, dtoReq.getTradeCd())
                || StringUtils.equals(TRADE_CLASS_MARGIN_REPAYMENT_SELL, dtoReq.getTradeCd())) {
            
            //API002呼び出し
            GetForeignStockMarginOrderDetailResp api002Res = new GetForeignStockMarginOrderDetailResp();
            try {
                api002Res = foreignStockService.getForeignStockMarginOrderDetail(butenCode, accountNumber,
                        dtoReq.getOrderSubNumber());
            } catch (Exception e) {
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{IfaForeignStockEntrustOrderTradeInfoServiceImplL.initializeA001}", e);
                }
                return cometCommonService.checkBussinessException(IfaCommonUtil.createDataList(new ArrayList<>(),
                        ErrorLevel.FATAL, ErrorLevel.FATAL.toString(), null), e);
            }
            
            // API002から取得した値を委託注文約定情報リストに格納
            entrustOrderTradeinfoList = getForeignStockMarginOrderDetailResult(api002Res);
        }
        
        
        
        /* ======================================== */
        /* ③委託注文約定情報リストを約定日時の降順にソートする。  */
        /* ======================================== */
        
        entrustOrderTradeinfoList.sort(Comparator
                .comparing(IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo::getTradeDateTime).reversed());
        
        // レスポンスに値をセット
        IfaForeignStockEntrustOrderTradeInfoA001DtoResponse resDto = new IfaForeignStockEntrustOrderTradeInfoA001DtoResponse();
        // 顧客名（漢字）
        resDto.setCustomerNameKanji(customerCommon.getCustomerNameKanji());
        // 顧客名（カナ）
        resDto.setCustomerNameKana(customerCommon.getCustomerNameKana());
        // 部店コード
        resDto.setButenCode(customerCommon.getButenCode());
        // 口座番号
        resDto.setAccountNumber(customerCommon.getAccountNumber());
        // 委託注文約定情報リスト
        resDto.setEntrustOrderTradeinfoList(entrustOrderTradeinfoList);
        
        // DataListの作成
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * API001から取得した値を委託注文約定情報リストにセットする
     *
     * @param api001Res API001レスポンス
     * @return 委託注文約定情報リスト
     */
    private List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo> getForeignStockOrderDetailResult(
            GetForeignStockOrderDetailResp api001Res) {
        
        List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo> result = new ArrayList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo>();
        
        // 注文履歴の値を格納
        for (OrderHistory r : api001Res.getOrderHistories()) {
            if (ObjectUtils.isEmpty(r.getExecutionDatetime()) || ObjectUtils.isEmpty(r.getExecutionQuantity()) || ObjectUtils.isEmpty(r.getExecutionPrice())) {
                continue;
            }
            IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo item = new IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo();
            
            // 約定日時
            item.setTradeDateTime(r.getExecutionDatetime());
            
            // 約定数量（数値(整数)）
            item.setTradeQuantity(r.getExecutionQuantity());
            
            // 約定単価（数値(小数)）
            item.setTradePrice(r.getExecutionPrice());
            
            // 約定単価通貨コード
            item.setTradePriceCurrencyCode(api001Res.getOrderDetail().getOrder().getTradeCurrencyCode());
            
            result.add(item);
        }
        return result;
    }
    
    /**
     * API002から取得した値を委託注文約定情報リストにセットする
     *
     * @param api002Res API002レスポンス
     * @return 委託注文約定情報リスト
     */
    private List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo> getForeignStockMarginOrderDetailResult(
            GetForeignStockMarginOrderDetailResp api002Res) {
        
        List<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo> result = new ArrayList<IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo>();
        
        // 注文履歴の値を格納
        for (OrderHistory r : api002Res.getOrderHistories()) {
            if (ObjectUtils.isEmpty(r.getExecutionDatetime()) || ObjectUtils.isEmpty(r.getExecutionQuantity()) || ObjectUtils.isEmpty(r.getExecutionPrice())) {
                continue;
            }
            IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo item = new IfaForeignStockEntrustOrderTradeInfoA001DtoResponseEntrustOrderTradeinfo();
            
            // 約定日時
            item.setTradeDateTime(r.getExecutionDatetime());
            
            // 約定数量（数値(整数)）
            item.setTradeQuantity(r.getExecutionQuantity());
            
            // 約定単価（数値(小数)）
            item.setTradePrice(r.getExecutionPrice());
            
            // 約定単価通貨コード
            item.setTradePriceCurrencyCode(
                    api002Res.getMarginOrderDetail().getMarginOrder().getOrder().getTradeCurrencyCode());
            
            result.add(item);
        }
        return result;
    }
}
