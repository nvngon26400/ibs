package com.sbisec.helios.ap.athena.service.impl;

import java.math.BigDecimal;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.OkHttpResponse;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryReq;
import com.sbisec.helios.ap.athena.protocol.account.GetMarginPositionSummaryResp;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsReq;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
//import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionReq;
//import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginPositionResp;
import com.sbisec.helios.ap.athena.service.AbstractBaseService;
import com.sbisec.helios.ap.athena.service.CometMarginPositionService;
import com.sbisec.helios.ap.athena.utils.AthenaException;
import com.sbisec.helios.ap.athena.utils.CometApiUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

/**
 * 余力サービス - 外国株式信用建玉 Service implements.
 * 
 * @author mengzhe.li
 * @date 03/09/2022
 */
@Service
public class CometMarginPositionServiceImpl extends AbstractBaseService implements CometMarginPositionService {
    
    private static final Logger LOG = LoggerFactory.getLogger(CometMarginPositionServiceImpl.class);
    
    @Autowired
    private CodeListService codeListService;
    
    /** APIタイプ：Athena */
    private static final String ATHENA = "Athena";
    
    /** 区分.売買区分 */
    private static final String SELL_BUY_TYPE = "SELL_BUY_TYPE";
    
    /** 区分.預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";
    
    /** 区分.信用期日 */
    private static final String MARGIN_DUE_DATE = "MARGIN_DUE_DATE";
    
    /** 区分.売買区分（建玉） */
    private static final String POSITION_SELL_BUY_TYPE = "POSITION_SELL_BUY_TYPE";
    
    @Override
    public GetMarginPositionResp getMarginPosition(GetMarginPositionReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometMarginPositionServiceImpl.getMarginPosition : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // Headerを設定する
            GetMarginPositionReq.Header header = request.getHeader();
            // parameterを設定する
            GetMarginPositionReq.Parameter parameter = request.getParameter();
            
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(header.getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(header.getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            // 必須入力チェック「国コード」
            String countryCode = parameter.getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            //            if (null == CountryCode.getById(countryCode)) {
            //                warnMsg = "CountryCode " + countryCode + " is not exists!";
            //                break;
            //            }
            // 必須入力チェック「銘柄コード」
            if (StringUtil.isNullOrEmpty(parameter.getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            // 必須入力チェック「売買区分」
            String buySellCode = parameter.getBuySellCode();
            if (StringUtil.isNullOrEmpty(buySellCode)) {
                warnMsg = "BuySellCode is null or empty!";
                break;
            }
            //            if (null == BuySell.getById(buySellCode)) {
            //                warnMsg = "BuySellCode " + buySellCode + " is not exists!";
            //                break;
            //            }
            // 必須入力チェック「信用期日」
            String marginCloseLimitType = parameter.getMarginCloseLimitType();
            if (StringUtil.isNullOrEmpty(marginCloseLimitType)) {
                warnMsg = "MarginCloseLimitType is null or empty!";
                break;
            }
            //            if (null == MarginCloseLimitType.getById(marginCloseLimitType)) {
            //                warnMsg = "MarginCloseLimitType " + marginCloseLimitType + " is not exists!";
            //                break;
            //            }
            // 必須入力チェック「預り区分」
            String specificAccountCode = parameter.getSpecificAccountCode();
            if (StringUtil.isNullOrEmpty(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            //            if (null == SpecificAccount.getById(specificAccountCode)) {
            //                warnMsg = "SpecificAccountCode " + specificAccountCode + " is not exists!";
            //                break;
            //            }
            // 必須入力チェック「国内約定日」
            String tradeDate = parameter.getTradeDate();
            if (StringUtil.isNullOrEmpty(tradeDate)) {
                warnMsg = "TradeDate is null or empty!";
                break;
            }
            // フォーマットチェック「国内約定日 」
            if (!StringUtil.isNullOrEmpty(tradeDate) && !DateUtil.isParsable(tradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "TradeDate " + tradeDate + " format is illegal!";
                break;
            }
            // 必須入力チェック「現地約定日」
            String frnTradeDate = parameter.getFrnTradeDate();
            if (StringUtil.isNullOrEmpty(frnTradeDate)) {
                warnMsg = "FrnTradeDate is null or empty!";
                break;
            }
            // フォーマットチェック「現地約定日 」
            if (!StringUtil.isNullOrEmpty(frnTradeDate)
                    && !DateUtil.isParsable(frnTradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "FrnTradeDate " + frnTradeDate + " format is illegal!";
                break;
            }
            // 必須入力チェック「新規建単価（外貨）」
            String frnpositionprice = parameter.getFrnPositionPrice();
            if (StringUtil.isNullOrEmpty(frnpositionprice)) {
                warnMsg = "FrnPositionPrice is null or empty!";
                break;
            }
            // サイズ範囲:0-999999999999.9999 新規建単価（外貨）
            if (!StringUtil.isNullOrEmpty(frnpositionprice) && !checkRange(new BigDecimal(frnpositionprice),
                    FRNPOSITIONPRICE_MIN_VALUE, FRNPOSITIONPRICE_MAX_VALUE)) {
                warnMsg = "FrnPositionPrice " + frnpositionprice + " is out of this range!";
                break;
            }
            // 必須入力チェック「新規建単価（円貨）」
            String positionPrice = parameter.getPositionPrice();
            if (StringUtil.isNullOrEmpty(positionPrice)) {
                warnMsg = "PositionPrice is null or empty!";
                break;
            }
            // サイズ範囲:0-999999999999.9999 新規建単価（円貨）
            if (!StringUtil.isNullOrEmpty(positionPrice) && !checkRange(new BigDecimal(positionPrice),
                    FRNPOSITIONPRICE_MIN_VALUE, FRNPOSITIONPRICE_MAX_VALUE)) {
                warnMsg = "PositionPrice " + positionPrice + " is out of this range!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        /* 内部コード→外部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getBuySellCode())) {
            String buySellCodeAthena = codeListService.convertKeyToExtKey(POSITION_SELL_BUY_TYPE, ATHENA,
                    request.getParameter().getBuySellCode());
            request.getParameter().setBuySellCode(buySellCodeAthena);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(request.getParameter().getMarginCloseLimitType())) {
            String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    request.getParameter().getMarginCloseLimitType());
            request.getParameter().setMarginCloseLimitType(marginCloseLimitTypeAthena);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(request.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    request.getParameter().getSpecificAccountCode());
            request.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_balance_positions_getDetail());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        GetMarginPositionResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(GetMarginPositionResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード→内部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(POSITION_SELL_BUY_TYPE, ATHENA,
                    resp.getBuySellCode());
            resp.setBuySellCode(convBuySellCode);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getMarginCloseLimitType());
            resp.setMarginCloseLimitType(convMarginCloseLimitType);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getSpecificAccountCode())) {
            String convSpecificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    resp.getSpecificAccountCode());
            resp.setSpecificAccountCode(convSpecificAccountCode);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public ListMarginPositionsResp listMarginPositions(ListMarginPositionsReq request) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometMarginPositionServiceImpl.listMarginPositions : {}", hashCode());
        }
        // パラメータチェック
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            if (request == null) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 必須入力チェック「token」
            if (StringUtil.isNullOrEmpty(request.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            // tokenの正確性チェックを行う。
            if (!checkToken(request.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 必須入力チェック「国コード」
            String countryCode = request.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            //            // 存在チェック「国コード」
            //            if (null == CountryCode.getById(countryCode)) {
            //                warnMsg = "CountryCode " + countryCode + " is not exists!";
            //                break;
            //            }
            //            
            //            // 「売買区分」：「非必須」存在チェック
            //            String buySellCode = request.getParameter().getBuySellCode();
            //            if (!StringUtil.isNullOrEmpty(buySellCode) && null == BuySell.getById(buySellCode)) {
            //                warnMsg = "BuySellCode " + buySellCode + " is not exists!";
            //                break;
            //            }
            //            
            //            // 「預り区分」：「非必須」存在チェック
            //            String specificAccountCode = request.getParameter().getSpecificAccountCode();
            //            if (!StringUtil.isNullOrEmpty(specificAccountCode)
            //                    && null == SpecificAccount.getById(specificAccountCode)) {
            //                warnMsg = "SpecificAccountCode " + specificAccountCode + " is not exists!";
            //                break;
            //            }
            //            
            //            // 「信用期日」：「非必須」存在チェック
            //            String marginCloseLimitType = request.getParameter().getMarginCloseLimitType();
            //            if (!StringUtil.isNullOrEmpty(marginCloseLimitType)
            //                    && null == MarginCloseLimitType.getById(marginCloseLimitType)) {
            //                warnMsg = "MarginCloseLimitType " + marginCloseLimitType + " is not exists!";
            //                break;
            //            }
            
            // 「国内約定日」： 「非必須」フォーマットチェック
            String tradeDate = request.getParameter().getTradeDate();
            if (!StringUtil.isNullOrEmpty(tradeDate) && !DateUtil.isParsable(tradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "TradeDate " + tradeDate + " format is illegal!";
                break;
            }
            
            // 「現地約定日」： 「非必須」フォーマットチェック
            String frnTradeDate = request.getParameter().getFrnTradeDate();
            if (!StringUtil.isNullOrEmpty(frnTradeDate)
                    && !DateUtil.isParsable(frnTradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "FrnTradeDate " + frnTradeDate + " format is illegal!";
                break;
            }
            
            // 新規建単価（外貨）： 「非必須」サイズ範囲:0-999999999999.9999 新規建単価（外貨）
            String frnPositionPrice = request.getParameter().getFrnPositionPrice();
            if (!StringUtil.isNullOrEmpty(frnPositionPrice) && !checkRange(new BigDecimal(frnPositionPrice),
                    FRNPOSITIONPRICE_MIN_VALUE, FRNPOSITIONPRICE_MAX_VALUE)) {
                warnMsg = "FrnPositionPrice " + frnPositionPrice + " is out of this range!";
                break;
            }
            
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        // APIのURLを設定する
        String url = this.getUrl(CometApiUtil.getAcc_account_balance_positions_listDetails());
        // GET請求を送信する
        OkHttpResponse httpResp = this.get(url, request);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        ListMarginPositionsResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返します。
            resp = httpResp.getResponseData(ListMarginPositionsResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        //外部→内部コード変換
        int positions = resp.getPositions().size();
        for (int i = 0; i < positions; i++) {
            //増担保規制建玉フラグ
            //増担 = "0" (false)の場合
            if (false == resp.getPositions().get(i).getNeedAdditionalCollateral()) {
                resp.getPositions().get(i).setConvNeedAdditionalCollateral("0");
                //増担 = "1" (true)の場合    
                
            } else if (true == resp.getPositions().get(i).getNeedAdditionalCollateral()) {
                resp.getPositions().get(i).setConvNeedAdditionalCollateral("1");
            }
            
            //売買区分
            if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getBuySellCode())) {
                String buySellCode = codeListService.convertExtKeyToKey(POSITION_SELL_BUY_TYPE, ATHENA,
                        resp.getPositions().get(i).getBuySellCode());
                resp.getPositions().get(i).setBuySellCode(buySellCode);
            }
            
            //信用期日
            if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getMarginCloseLimitType())) {
                String marginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                        resp.getPositions().get(i).getMarginCloseLimitType());
                resp.getPositions().get(i).setMarginCloseLimitType(marginCloseLimitType);
            }
            
            //預り区分
            if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getSpecificAccountCode())) {
                String specificAccountCode = codeListService.convertExtKeyToKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                        resp.getPositions().get(i).getSpecificAccountCode());
                resp.getPositions().get(i).setSpecificAccountCode(specificAccountCode);
            }
        }
        
        // 結果を返します
        return resp;
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummary(
            GetMarginPositionSummaryReq getMarginPositionSummaryReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometMarginPositionServiceImpl.getMarginPositionSummary : {}", hashCode());
        }
        
        /* 内部コード→外部コード変換 */
        // 建区分
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getBuySellCode())) {
            String buySellCodeAthena = codeListService.convertKeyToExtKey(SELL_BUY_TYPE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getBuySellCode());
            getMarginPositionSummaryReq.getParameter().setBuySellCode(buySellCodeAthena);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType())) {
            String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType());
            getMarginPositionSummaryReq.getParameter().setMarginCloseLimitType(marginCloseLimitTypeAthena);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getSpecificAccountCode());
            getMarginPositionSummaryReq.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getMarginPositionSummaryReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「ticket」：必須入力チェック
            String ticket = getMarginPositionSummaryReq.getHeader().getTicket();
            if (StringUtil.isNullOrEmpty(ticket)) {
                warnMsg = "Ticket is null or empty!";
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            String countryCode = getMarginPositionSummaryReq.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            
            // 「銘柄コード」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            
            // 「信用期日」：必須入力チェック、存在チェック
            String marginCloseLimitType = getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType();
            if (StringUtil.isNullOrEmpty(marginCloseLimitType)) {
                warnMsg = "MarginCloseLimitType is null or empty!";
                break;
            }
            
            // 「預り区分」：必須入力チェック、存在チェック
            String specificAccountCode = getMarginPositionSummaryReq.getParameter().getSpecificAccountCode();
            if (StringUtil.isNullOrEmpty(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            
            // 「国内約定日」： フォーマットチェック
            String tradeDate = getMarginPositionSummaryReq.getParameter().getTradeDate();
            if (!StringUtil.isNullOrEmpty(tradeDate) && !DateUtil.isParsable(tradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "TradeDate " + tradeDate + " format is illegal!";
                break;
            }
            
            // 「現地約定日」： フォーマットチェック
            String frnTradeDate = getMarginPositionSummaryReq.getParameter().getFrnTradeDate();
            if (!StringUtil.isNullOrEmpty(frnTradeDate)
                    && !DateUtil.isParsable(frnTradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "FrnTradeDate " + frnTradeDate + " format is illegal!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getAcc_balance_positions_getSummary());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.get(url, getMarginPositionSummaryReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetMarginPositionSummaryResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetMarginPositionSummaryResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード　→ 内部コード:Athena転換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getPositionSummary().getBuySellCode());
            resp.getPositionSummary().setBuySellCode(convBuySellCode);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getPositionSummary().getMarginCloseLimitType());
            resp.getPositionSummary().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        if (CollectionUtils.isNotEmpty(resp.getPositions())) {
            for (int i = 0; i < resp.getPositions().size(); i++) {
                // 売買区分
                if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getBuySellCode())) {
                    String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                            resp.getPositions().get(i).getBuySellCode());
                    resp.getPositions().get(i).setBuySellCode(convBuySellCode);
                }
                // 信用期日
                if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getMarginCloseLimitType())) {
                    String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                            resp.getPositions().get(i).getMarginCloseLimitType());
                    resp.getPositions().get(i).setMarginCloseLimitType(convMarginCloseLimitType);
                }
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummaryForRepayOrderConfirm(
            GetMarginPositionSummaryReq getMarginPositionSummaryReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometMarginPositionServiceImpl.getMarginPositionSummaryForRepayOrderConfirm : {}", hashCode());
        }
        
        /* 内部コード→外部コード変換 */
        // 信用期日
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType())) {
            String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType());
            getMarginPositionSummaryReq.getParameter().setMarginCloseLimitType(marginCloseLimitTypeAthena);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getSpecificAccountCode());
            getMarginPositionSummaryReq.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getMarginPositionSummaryReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「ticket」：必須入力チェック
            String ticket = getMarginPositionSummaryReq.getHeader().getTicket();
            if (StringUtil.isNullOrEmpty(ticket)) {
                warnMsg = "Ticket is null or empty!";
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            String countryCode = getMarginPositionSummaryReq.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            
            // 「銘柄コード」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            
            // 「信用期日」：必須入力チェック、存在チェック
            String marginCloseLimitType = getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType();
            if (StringUtil.isNullOrEmpty(marginCloseLimitType)) {
                warnMsg = "MarginCloseLimitType is null or empty!";
                break;
            }
            
            // 「預り区分」：必須入力チェック、存在チェック
            String specificAccountCode = getMarginPositionSummaryReq.getParameter().getSpecificAccountCode();
            if (StringUtil.isNullOrEmpty(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            
            // 「国内約定日」： フォーマットチェック
            String tradeDate = getMarginPositionSummaryReq.getParameter().getTradeDate();
            if (!StringUtil.isNullOrEmpty(tradeDate) && !DateUtil.isParsable(tradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "TradeDate " + tradeDate + " format is illegal!";
                break;
            }
            
            // 「現地約定日」： フォーマットチェック
            String frnTradeDate = getMarginPositionSummaryReq.getParameter().getFrnTradeDate();
            if (!StringUtil.isNullOrEmpty(frnTradeDate)
                    && !DateUtil.isParsable(frnTradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "FrnTradeDate " + frnTradeDate + " format is illegal!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getAcc_balance_positions_getSummary());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.get(url, getMarginPositionSummaryReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetMarginPositionSummaryResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetMarginPositionSummaryResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード　→ 内部コード:Athena転換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                    resp.getPositionSummary().getBuySellCode());
            resp.getPositionSummary().setBuySellCode(convBuySellCode);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getPositionSummary().getMarginCloseLimitType());
            resp.getPositionSummary().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        
        if (CollectionUtils.isNotEmpty(resp.getPositions())) {
            for (int i = 0; i < resp.getPositions().size(); i++) {
                // 売買区分
                if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getBuySellCode())) {
                    String convBuySellCode = codeListService.convertExtKeyToKey(SELL_BUY_TYPE, ATHENA,
                            resp.getPositions().get(i).getBuySellCode());
                    resp.getPositions().get(i).setBuySellCode(convBuySellCode);
                }
                // 信用期日
                if (!StringUtil.isNullOrEmpty(resp.getPositions().get(i).getMarginCloseLimitType())) {
                    String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                            resp.getPositions().get(i).getMarginCloseLimitType());
                    resp.getPositions().get(i).setMarginCloseLimitType(convMarginCloseLimitType);
                }
            }
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
    
    @Override
    public GetMarginPositionSummaryResp getMarginPositionSummaryForPositionDetails(
            GetMarginPositionSummaryReq getMarginPositionSummaryReq) throws Exception {
        
        long start = System.currentTimeMillis();
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("CometMarginPositionServiceImpl.getMarginPositionSummaryForPositionDetails : {}", hashCode());
        }
        
        // パラメータチェックメッセージ
        String warnMsg = StringUtil.EMPTY_STRING;
        do {
            // 「リクエスト」：空のチェック
            if (null == getMarginPositionSummaryReq) {
                warnMsg = MSG_REQUEST_IS_NULL;
                break;
            }
            
            // 「Token」：必須入力チェック、正確性チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = "Token is null or empty!";
                break;
            }
            if (!checkToken(getMarginPositionSummaryReq.getHeader().getToken())) {
                warnMsg = MSG_TOKEN_FORMAT;
                break;
            }
            
            // 「国コード」：必須入力チェック、存在チェック
            String countryCode = getMarginPositionSummaryReq.getParameter().getCountryCode();
            if (StringUtil.isNullOrEmpty(countryCode)) {
                warnMsg = "CountryCode is null or empty!";
                break;
            }
            
            // 「銘柄コード」：必須入力チェック、桁数チェック
            if (StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSecuritiesCode())) {
                warnMsg = "SecuritiesCode is null or empty!";
                break;
            }
            
            // 「信用期日」：必須入力チェック、存在チェック
            String marginCloseLimitType = getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType();
            if (StringUtil.isNullOrEmpty(marginCloseLimitType)) {
                warnMsg = "MarginCloseLimitType is null or empty!";
                break;
            }
            
            // 「預り区分」：必須入力チェック、存在チェック
            String specificAccountCode = getMarginPositionSummaryReq.getParameter().getSpecificAccountCode();
            if (StringUtil.isNullOrEmpty(specificAccountCode)) {
                warnMsg = "SpecificAccountCode is null or empty!";
                break;
            }
            
            // 「国内約定日」： フォーマットチェック
            String tradeDate = getMarginPositionSummaryReq.getParameter().getTradeDate();
            if (!StringUtil.isNullOrEmpty(tradeDate) && !DateUtil.isParsable(tradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "TradeDate " + tradeDate + " format is illegal!";
                break;
            }
            
            // 「現地約定日」： フォーマットチェック
            String frnTradeDate = getMarginPositionSummaryReq.getParameter().getFrnTradeDate();
            if (!StringUtil.isNullOrEmpty(frnTradeDate)
                    && !DateUtil.isParsable(frnTradeDate, FORMAT_YEAR_MONTH_DAY_DASH)) {
                warnMsg = "FrnTradeDate " + frnTradeDate + " format is illegal!";
                break;
            }
        } while (false);
        if (!StringUtil.isNullOrEmpty(warnMsg)) {
            LOG.error("Comet Exception ： " + warnMsg);
            throw new AthenaException("Parameter verification failed!");
        }
        /* 内部コード→外部コード変換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getBuySellCode())) {
            String buySellCodeAthena = codeListService.convertKeyToExtKey(POSITION_SELL_BUY_TYPE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getBuySellCode());
            getMarginPositionSummaryReq.getParameter().setBuySellCode(buySellCodeAthena);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType())) {
            String marginCloseLimitTypeAthena = codeListService.convertKeyToExtKey(MARGIN_DUE_DATE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getMarginCloseLimitType());
            getMarginPositionSummaryReq.getParameter().setMarginCloseLimitType(marginCloseLimitTypeAthena);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(getMarginPositionSummaryReq.getParameter().getSpecificAccountCode())) {
            String specificAccountCodeAthena = codeListService.convertKeyToExtKey(FOREIGN_DEPOSIT_TYPE, ATHENA,
                    getMarginPositionSummaryReq.getParameter().getSpecificAccountCode());
            getMarginPositionSummaryReq.getParameter().setSpecificAccountCode(specificAccountCodeAthena);
        }
        // APIのURLを設定する。
        String url = this.getUrl(CometApiUtil.getAcc_balance_positions_getSummary());
        
        // POST請求を送信する。
        OkHttpResponse httpResp = this.get(url, getMarginPositionSummaryReq);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Comet response data => {}", httpResp.getResponsData());
        }
        
        // RESPONSEをインスタンスする。
        GetMarginPositionSummaryResp resp = null;
        try {
            // 文字列をエンティティーBeanに変換して返する。
            resp = httpResp.getResponseData(GetMarginPositionSummaryResp.class);
        } catch (Exception e) {
            LOG.warn("Comet response data deserialization exception:", e);
            throw e;
        }
        
        /* 外部コード　→ 内部コード:Athena転換 */
        // 売買区分
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getBuySellCode())) {
            String convBuySellCode = codeListService.convertExtKeyToKey(POSITION_SELL_BUY_TYPE, ATHENA,
                    resp.getPositionSummary().getBuySellCode());
            resp.getPositionSummary().setBuySellCode(convBuySellCode);
        }
        // 信用期日
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getMarginCloseLimitType())) {
            String convMarginCloseLimitType = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getPositionSummary().getMarginCloseLimitType());
            resp.getPositionSummary().setMarginCloseLimitType(convMarginCloseLimitType);
        }
        // 預り区分
        if (!StringUtil.isNullOrEmpty(resp.getPositionSummary().getSpecificAccountCode())) {
            String convSpecificAccountCode = codeListService.convertExtKeyToKey(MARGIN_DUE_DATE, ATHENA,
                    resp.getPositionSummary().getSpecificAccountCode());
            resp.getPositionSummary().setSpecificAccountCode(convSpecificAccountCode);
        }
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("cost -> {}", (System.currentTimeMillis() - start));
        }
        
        // 結果を返する。
        return resp;
    }
}
