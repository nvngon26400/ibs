package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaStockDetailInfoDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockDetailInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaStockDetailInfoA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaStockDetailInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.HeracrossApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshot;
import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;

/**
 * 画面ID：SUB0202_0208-02 画面名：株式詳細情報 アクションID：A001 アクション名：初期化
 * 
 * @author <author-name>
 *
 *         2023/07/31 新規作成
 */
@Component(value = "cmpIfaStockDetailInfoService")
public class IfaStockDetailInfoServiceImpL implements IfaStockDetailInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaStockDetailInfoServiceImpL.class);
    
    /**
     * DAOクラス
     */
    @Autowired
    private IfaStockDetailInfoDao dao;
    
    /**
     * APIラッパークラス(Heracross)
     */
    @Autowired
    private HeracrossApiWrapper heracrossApiWrapper;
    
    /** エラーログ:指定した銘柄の銘柄名が取得できませんでした。 */
    private static final String ERRORS_CMN_SELECTBRAND_NOBRANDNAME = "errors.cmn.selectedBrand.noBrandName";
    
    /** エラーログ:指定した銘柄の銘柄属性が取得できませんでした。 */
    private static final String ERRORS_CMN_SELECTBRAND_NOATTRIBUTE = "errors.cmn.selectedBrand.noAttribute";
    
    /** エラーログ:指定した銘柄の時価情報が取得できませんでした。 */
    private static final String ERRORS_CMN_SELECTBRAND_NOPRICE = "errors.cmn.selectedBrand.noPrice";
    
    /** SQLの取得データ数が0 */
    private static final int SQL_NODATA = 0;
    
    /**
     * 
     * Dto リクエスト：IfaStockDetailInfoA001DtoRequest Dto
     * レスポンス：IfaStockDetailInfoA001DtoResponse model
     * リクエスト：IfaStockDetailInfoSql002RequestModel model
     * レスポンス：IfaStockDetailInfoSql002ResponseModel
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaStockDetailInfoA001DtoResponse> initializeA001(IfaStockDetailInfoA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaStockDetailInfoA001DtoResponse> dtoRes = new DataList<IfaStockDetailInfoA001DtoResponse>();
        List<IfaStockDetailInfoA001DtoResponse> resDto = new ArrayList<IfaStockDetailInfoA001DtoResponse>();
        DataList<IfaStockDetailInfoSql001ResponseModel> selSql001Res = new DataList<IfaStockDetailInfoSql001ResponseModel>();
        DataList<IfaStockDetailInfoSql002ResponseModel> selSql002Res = new DataList<IfaStockDetailInfoSql002ResponseModel>();
        // 銘柄名の取得
        selSql001Res = getBrandName(dtoReq.getBrandCode());
        if (selSql001Res.size() == SQL_NODATA) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTBRAND_NOBRANDNAME,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOBRANDNAME));
            return dtoRes;
        }
        // 株式詳細の取得
        selSql002Res = getBrandAttribute(dtoReq.getBrandCode());
        if (selSql002Res.size() == SQL_NODATA) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTBRAND_NOATTRIBUTE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOATTRIBUTE));
            return dtoRes;
        }
        
        // 国内株時価情報を取得
        RealQuoteSnapshotOut api001Res = new RealQuoteSnapshotOut();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            api001Res = callRealQueryAccountBalance(dtoReq.getBrandCode(), dtoReq.getMarket());
            apiErrorUtil.getHeracrossApiResponseResult(api001Res.getResponseStatus().getReturnCode(),
                    api001Res.getResponseStatus().getMessageCode(), api001Res.getResponseStatus().getMessageText());
        } catch (Exception e) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                        "{}, {}, {}",
                        "IfaStockDetailInfoServiceImpL",
                        "initializeA001",
                        e
                );
            }

            dtoRes = IfaCommonUtil.createDataList(
                resDto,
                ErrorLevel.FATAL,
                ERRORS_CMN_SELECTBRAND_NOPRICE,
                IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOPRICE)
            );
            return dtoRes;
        }
        
        //response設定
        resDto = setResponseA001(dtoReq.getBrandCode(), dtoReq.getMarket(), selSql001Res.get(0), selSql002Res.get(0),
                api001Res);
        return apiErrorUtil.createDataList(resDto, null);
        
    }
    
    /**
     * 銘柄名の取得
     * 
     * @param brandCode
     * @return　SQL001の出力結果
     * @throws Exception
     */
    private DataList<IfaStockDetailInfoSql001ResponseModel> getBrandName(String brandCode) throws Exception {
        
        IfaStockDetailInfoSql001RequestModel sql001Req = new IfaStockDetailInfoSql001RequestModel();
        sql001Req.setBrandCode(brandCode);
        
        return dao.selectIfaStockDetailInfoSql001(sql001Req);
    }
    
    /**
     * 株式詳細情報の取得
     * 
     * @param brandCode
     * @return SQL002の出力結果
     * @throws Exception
     */
    private DataList<IfaStockDetailInfoSql002ResponseModel> getBrandAttribute(String brandCode) throws Exception {
        
        IfaStockDetailInfoSql002RequestModel sql002Req = new IfaStockDetailInfoSql002RequestModel();
        sql002Req.setBrandCode(brandCode);
        
        return dao.selectIfaStockDetailInfoSql002(sql002Req);
    }
    
    /**
     * 
     * Dto リクエスト：IfaStockDetailInfoA002DtoRequest Dto
     * レスポンス：IfaStockDetailInfoA002DtoResponse model
     * リクエスト：IfaStockDetailInfoSql002RequestModel model
     * レスポンス：IfaStockDetailInfoSql002ResponseModel
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaStockDetailInfoA002DtoResponse> updateA002(IfaStockDetailInfoA002DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaStockDetailInfoA002DtoResponse> dtoRes = new DataList<IfaStockDetailInfoA002DtoResponse>();
        DataList<IfaStockDetailInfoSql001ResponseModel> selSql001Res = new DataList<IfaStockDetailInfoSql001ResponseModel>();
        DataList<IfaStockDetailInfoSql002ResponseModel> selSql002Res = new DataList<IfaStockDetailInfoSql002ResponseModel>();
        List<IfaStockDetailInfoA002DtoResponse> resDto = new ArrayList<IfaStockDetailInfoA002DtoResponse>();
        // 銘柄名の取得
        selSql001Res = getBrandName(dtoReq.getBrandCode());
        if (selSql001Res.size() == SQL_NODATA) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTBRAND_NOBRANDNAME,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOBRANDNAME));
            return dtoRes;
        }
        // 株式詳細の取得
        selSql002Res = getBrandAttribute(dtoReq.getBrandCode());
        if (selSql002Res.size() == SQL_NODATA) {
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, ERRORS_CMN_SELECTBRAND_NOATTRIBUTE,
                    IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOATTRIBUTE));
            return dtoRes;
        }
        
        // 国内株時価情報を取得
        RealQuoteSnapshotOut api001Res = new RealQuoteSnapshotOut();
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {
            api001Res = callRealQueryAccountBalance(dtoReq.getBrandCode(), dtoReq.getMarket());
            apiErrorUtil.getHeracrossApiResponseResult(api001Res.getResponseStatus().getReturnCode(),
                    api001Res.getResponseStatus().getMessageCode(), api001Res.getResponseStatus().getMessageText());
        } catch (Exception e) {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                        "{}, {}, {}",
                        "IfaStockDetailInfoServiceImpL",
                        "updateA002",
                        e
                );
            }

            dtoRes = IfaCommonUtil.createDataList(
                resDto,
                ErrorLevel.FATAL,
                ERRORS_CMN_SELECTBRAND_NOPRICE,
                IfaCommonUtil.getMessage(ERRORS_CMN_SELECTBRAND_NOPRICE)
            );
            return dtoRes;
        }
        
        //response設定
        resDto = setResponseA002(dtoReq.getBrandCode(), dtoReq.getMarket(), selSql001Res.get(0), selSql002Res.get(0),
                api001Res);
        return apiErrorUtil.createDataList(resDto, null);
        
    }
    
    /**
     * Response設定
     * 
     * @param brandCode 銘柄コード
     * @param market　市場
     * @param selSql001Res　SQL001の出力結果
     * @param selSql002Res　SQL002の出力結果
     * @param api001Res　API001の出力結果
     */
    private List<IfaStockDetailInfoA001DtoResponse> setResponseA001(String brandCode, String market,
            IfaStockDetailInfoSql001ResponseModel selSql001Res, IfaStockDetailInfoSql002ResponseModel selSql002Res,
            RealQuoteSnapshotOut api001Res) {
        
        List<IfaStockDetailInfoA001DtoResponse> resDto = new ArrayList<IfaStockDetailInfoA001DtoResponse>();
        IfaStockDetailInfoA001DtoResponse res = new IfaStockDetailInfoA001DtoResponse();
        //銘柄コード
        res.setBrandCode(brandCode);
        //銘柄名
        res.setBrandName(selSql001Res.getBrandName());
        //市場
        res.setMarket(market);
        //一日信用売建区分
        res.setOnedayCreditSellCategory(selSql002Res.getDayMgBuyKbn());
        //一日信用買建区分
        res.setOnedayCreditBuyCategory(selSql002Res.getDayMgSellKbn());
        //プレミアム空売り区分
        res.setPremiumShortSaleCcategory(selSql002Res.getPremiumShortSellingKbn());
        //更新日時
        res.setUpdateTime(DateUtil.format(DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS));
        
        //API返却値の設定
        if (api001Res.getFeedData() != null) {
            RealQuoteSnapshot api001Value = api001Res.getFeedData().get(0);
                    
            //現在値（数値(小数)
            res.setCurrentPrice(api001Value.getCurPrice());
            
            // 現在値ティック
            res.setTick(api001Value.getCurPriceTick());
            
            //現在値フラグ
            res.setCurrentFlag(api001Value.getCurPriceTone());
            
            //前日比
            res.setDiff(api001Value.getPriceChange());
            
            //前日比率
            res.setRatio(api001Value.getPerChange());
            
            //現在値日付
            res.setUpdateDate(api001Value.getCurPriceDate());
            
            //現在値更新時刻/前日比更新時刻
            res.setUpdateTime4(api001Value.getCurPriceTime());
            
            //始値（数値(小数)）
            res.setStart(api001Value.getOpenPrice());
            
            //始値更新時刻
            res.setStartTime(api001Value.getOpenPriceTime());
            
            //高値（数値(小数)）
            res.setHigh(api001Value.getHighPrice());
            
            //高値更新時刻
            res.setHighTime(api001Value.getHighPriceTime());
            
            //安値（数値(小数)）
            res.setLow(api001Value.getLowPrice());
            
            //安値更新時刻
            res.setLowTime(api001Value.getLowPriceTime());
            
            //前日終値（数値(小数)）
            res.setLast(api001Value.getLastClosingPrice());
            
            //前日終値日付
            res.setLastDate(api001Value.getLastClosingPriceDate());
            
            //出来高（数字）
            res.setVolume(api001Value.getVolume());
            
            //売買代金(数字)
            res.setBuySellPrice(api001Value.getTurnoverAmount());
            
            //成行売注文数量     
            res.setExecutionSellOrderQuantity(api001Value.getImpAskSize());
            
            //売り気配数Over,10~1
            res.setSellSignVolumeOver(api001Value.getCombineAskSize());
            res.setSellSignVolume10(api001Value.getAskSize10());
            res.setSellSignVolume9(api001Value.getAskSize9());
            res.setSellSignVolume8(api001Value.getAskSize8());
            res.setSellSignVolume7(api001Value.getAskSize7());
            res.setSellSignVolume6(api001Value.getAskSize6());
            res.setSellSignVolume5(api001Value.getAskSize5());
            res.setSellSignVolume4(api001Value.getAskSize4());
            res.setSellSignVolume3(api001Value.getAskSize3());
            res.setSellSignVolume2(api001Value.getAskSize2());
            res.setSellSignVolume1(api001Value.getAskSize1());
            
            //売気配値10~1
            res.setSellSignPrice10(api001Value.getAskPrice10());
            res.setSellSignPrice9(api001Value.getAskPrice9());
            res.setSellSignPrice8(api001Value.getAskPrice8());
            res.setSellSignPrice7(api001Value.getAskPrice7());
            res.setSellSignPrice6(api001Value.getAskPrice6());
            res.setSellSignPrice5(api001Value.getAskPrice5());
            res.setSellSignPrice4(api001Value.getAskPrice4());
            res.setSellSignPrice3(api001Value.getAskPrice3());
            res.setSellSignPrice2(api001Value.getAskPrice2());
            res.setSellSignPrice1(api001Value.getAskPrice1());
            
            //買気配値10~1
            res.setBuySignPrice10(api001Value.getBidPrice10());
            res.setBuySignPrice9(api001Value.getBidPrice9());
            res.setBuySignPrice8(api001Value.getBidPrice8());
            res.setBuySignPrice7(api001Value.getBidPrice7());
            res.setBuySignPrice6(api001Value.getBidPrice6());
            res.setBuySignPrice5(api001Value.getBidPrice5());
            res.setBuySignPrice4(api001Value.getBidPrice4());
            res.setBuySignPrice3(api001Value.getBidPrice3());
            res.setBuySignPrice2(api001Value.getBidPrice2());
            res.setBuySignPrice1(api001Value.getBidPrice1());
            
            //複数売気配値フラグ
            res.setSellSignPriceFlg(api001Value.getMAskTone());
            
            //複数買気配値フラグ
            res.setBuySignPriceFlg(api001Value.getMBidTone());
            
            //成行買注文数量
            res.setExecutionBuyOrderQuantity(api001Value.getImpBidSize());
            
            //買気配株数under,10~1
            res.setBuySignVolumeUnder(api001Value.getCombineBidSize());
            res.setBuySignVolume10(api001Value.getBidSize10());
            res.setBuySignVolume9(api001Value.getBidSize9());
            res.setBuySignVolume8(api001Value.getBidSize8());
            res.setBuySignVolume7(api001Value.getBidSize7());
            res.setBuySignVolume6(api001Value.getBidSize6());
            res.setBuySignVolume5(api001Value.getBidSize5());
            res.setBuySignVolume4(api001Value.getBidSize4());
            res.setBuySignVolume3(api001Value.getBidSize3());
            res.setBuySignVolume2(api001Value.getBidSize2());
            res.setBuySignVolume1(api001Value.getBidSize1());
            
            //年初来高値
            res.setYearToDateHigh(api001Value.getYtdHighPrice());
            
            //信用売残
            res.setMarginSellBalance(api001Value.getSellMargin());
            
            //信用買残
            res.setCreditBuyStock(api001Value.getBuyMargin());
            
            //年初来安値
            res.setYearToDateLow(api001Value.getYtdLowPrice());
            
            //信用買残前週比
            res.setCreditBuyWeekDiff(api001Value.getBuyMarginNc());
            
            //信用売残前週比
            res.setCreditSellWeekDiff(api001Value.getSellMarginNc());
            
            // 貸借倍率
            String loanMagnification = setLoanMagnification(api001Value);
            res.setLoanMagnification(loanMagnification);
        }
        
        // 信用貸借
        String creditLoan = setCreaditLoan(market, selSql002Res);
        res.setCreditLoan(creditLoan);
        
        resDto.add(res);
        return resDto;
    }
    
    /**
     * Response設定
     * 
     * @param brandCode　銘柄コード
     * @param market 市場
     * @param selSql001Res SQL001の出力結果
     * @param selSql002Res SQL002の出力結果
     * @param api001Res　API001の出力結果
     */
    private List<IfaStockDetailInfoA002DtoResponse> setResponseA002(String brandCode, String market,
            IfaStockDetailInfoSql001ResponseModel selSql001Res, IfaStockDetailInfoSql002ResponseModel selSql002Res,
            RealQuoteSnapshotOut api001Res) {
        
        List<IfaStockDetailInfoA002DtoResponse> resDto = new ArrayList<IfaStockDetailInfoA002DtoResponse>();
        IfaStockDetailInfoA002DtoResponse res = new IfaStockDetailInfoA002DtoResponse();
        //銘柄コード
        res.setBrandCode(brandCode);
        //銘柄名
        res.setBrandName(selSql001Res.getBrandName());
        //市場
        res.setMarket(market);
        //一日信用売建区分
        res.setOnedayCreditSellCategory(selSql002Res.getDayMgBuyKbn());
        //一日信用買建区分
        res.setOnedayCreditBuyCategory(selSql002Res.getDayMgSellKbn());
        //プレミアム空売り区分
        res.setPremiumShortSaleCcategory(selSql002Res.getPremiumShortSellingKbn());
        //更新日時
        res.setUpdateTime(DateUtil.format(DateUtil.NOT_SEPARATED_YYYYMMDD_HHMMSS));
        
        //API返却値の設定
        if (api001Res.getFeedData() != null) {
            RealQuoteSnapshot api001Value = api001Res.getFeedData().get(0);
            
            //現在値（数値(小数)
            res.setCurrentPrice(api001Value.getCurPrice());
            
            // 現在値ティック
            res.setTick(api001Value.getCurPriceTick());
            
            //現在地フラグ
            res.setCurrentFlag(api001Value.getCurPriceTone());
            
            //前日比
            res.setDiff(api001Value.getPriceChange());
            
            //前日比率
            res.setRatio(api001Value.getPerChange());
            
            //現在値日付
            res.setUpdateDate(api001Value.getCurPriceDate());
            
            //現在値更新時刻/前日比更新時刻
            res.setUpdateTime4(api001Value.getCurPriceTime());
            
            //始値（数値(小数)）
            res.setStart(api001Value.getOpenPrice());
            
            //始値更新時刻
            res.setStartTime(api001Value.getOpenPriceTime());
            
            //高値（数値(小数)）
            res.setHigh(api001Value.getHighPrice());
            
            //高値更新時刻
            res.setHighTime(api001Value.getHighPriceTime());
            
            //安値（数値(小数)）
            res.setLow(api001Value.getLowPrice());
            
            //安値更新時刻
            res.setLowTime(api001Value.getLowPriceTime());
            
            //前日終値（数値(小数)）
            res.setLast(api001Value.getLastClosingPrice());
            
            //前日終値日付
            res.setLastDate(api001Value.getLastClosingPriceDate());
            
            //出来高（数字）
            res.setVolume(api001Value.getVolume());
            
            //売買代金(数字)
            res.setBuySellPrice(api001Value.getTurnoverAmount());
            
            //成行売注文数量     
            res.setExecutionSellOrderQuantity(api001Value.getImpAskSize());
            
            //売り気配数Over,10~1
            res.setSellSignVolumeOver(api001Value.getCombineAskSize());
            res.setSellSignVolume10(api001Value.getAskSize10());
            res.setSellSignVolume9(api001Value.getAskSize9());
            res.setSellSignVolume8(api001Value.getAskSize8());
            res.setSellSignVolume7(api001Value.getAskSize7());
            res.setSellSignVolume6(api001Value.getAskSize6());
            res.setSellSignVolume5(api001Value.getAskSize5());
            res.setSellSignVolume4(api001Value.getAskSize4());
            res.setSellSignVolume3(api001Value.getAskSize3());
            res.setSellSignVolume2(api001Value.getAskSize2());
            res.setSellSignVolume1(api001Value.getAskSize1());
            
            //売気配値10~1
            res.setSellSignPrice10(api001Value.getAskPrice10());
            res.setSellSignPrice9(api001Value.getAskPrice9());
            res.setSellSignPrice8(api001Value.getAskPrice8());
            res.setSellSignPrice7(api001Value.getAskPrice7());
            res.setSellSignPrice6(api001Value.getAskPrice6());
            res.setSellSignPrice5(api001Value.getAskPrice5());
            res.setSellSignPrice4(api001Value.getAskPrice4());
            res.setSellSignPrice3(api001Value.getAskPrice3());
            res.setSellSignPrice2(api001Value.getAskPrice2());
            res.setSellSignPrice1(api001Value.getAskPrice1());
            
            //買気配値10~1
            res.setBuySignPrice10(api001Value.getBidPrice10());
            res.setBuySignPrice9(api001Value.getBidPrice9());
            res.setBuySignPrice8(api001Value.getBidPrice8());
            res.setBuySignPrice7(api001Value.getBidPrice7());
            res.setBuySignPrice6(api001Value.getBidPrice6());
            res.setBuySignPrice5(api001Value.getBidPrice5());
            res.setBuySignPrice4(api001Value.getBidPrice4());
            res.setBuySignPrice3(api001Value.getBidPrice3());
            res.setBuySignPrice2(api001Value.getBidPrice2());
            res.setBuySignPrice1(api001Value.getBidPrice1());
            
            //複数売気配値フラグ
            res.setSellSignPriceFlg(api001Value.getMAskTone());
            
            //複数買気配値フラグ
            res.setBuySignPriceFlg(api001Value.getMBidTone());
            
            //成行買注文数量
            res.setExecutionBuyOrderQuantity(api001Value.getImpBidSize());
            
            //買気配株数under,10~1
            res.setBuySignVolumeUnder(api001Value.getCombineBidSize());
            res.setBuySignVolume10(api001Value.getBidSize10());
            res.setBuySignVolume9(api001Value.getBidSize9());
            res.setBuySignVolume8(api001Value.getBidSize8());
            res.setBuySignVolume7(api001Value.getBidSize7());
            res.setBuySignVolume6(api001Value.getBidSize6());
            res.setBuySignVolume5(api001Value.getBidSize5());
            res.setBuySignVolume4(api001Value.getBidSize4());
            res.setBuySignVolume3(api001Value.getBidSize3());
            res.setBuySignVolume2(api001Value.getBidSize2());
            res.setBuySignVolume1(api001Value.getBidSize1());
            
            //年初来高値
            res.setYearToDateHigh(api001Value.getYtdHighPrice());
            
            //信用売残
            res.setMarginSellBalance(api001Value.getSellMargin());
            
            //信用買残
            res.setCreditBuyStock(api001Value.getBuyMargin());
            
            //年初来安値
            res.setYearToDateLow(api001Value.getYtdLowPrice());
            
            //信用買残前週比
            res.setCreditBuyWeekDiff(api001Value.getBuyMarginNc());
            
            //信用売残前週比
            res.setCreditSellWeekDiff(api001Value.getSellMarginNc());
            
            // 貸借倍率
            String loanMagnification = setLoanMagnification(api001Value);
            res.setLoanMagnification(loanMagnification);
        }
        
        // 信用貸借
        String creditLoan = setCreaditLoan(market, selSql002Res);
        res.setCreditLoan(creditLoan);
        
        resDto.add(res);
        return resDto;
    }
    
    /**
     * 貸借倍率計算
     * 
     * @param api001Value　API001の出力結果
     * @return
     */
    private String setLoanMagnification(RealQuoteSnapshot api001Value) {
        
        String loanMagnificationStr = null;
        BigDecimal loanMagnification = null;
        if (StringUtils.isNotBlank(api001Value.getBuyMargin()) && StringUtils.isNotBlank(api001Value.getSellMargin())) {
            BigDecimal buyMargin = StringUtil.parseBigDecimal(api001Value.getBuyMargin());
            BigDecimal sellMargin = StringUtil.parseBigDecimal(api001Value.getSellMargin());
            if (sellMargin.longValue() != 0) {
                loanMagnification = buyMargin.divide(sellMargin, 2, RoundingMode.HALF_UP);
            }
        }
        if (loanMagnification != null) {
            loanMagnificationStr = loanMagnification.toString();
        }
        
        return loanMagnificationStr;
    }
    
    /**
     * 信用貸借設定
     * 
     * @param market　市場
     * @param selSql002Res　SQL002の出力結果
     * @return
     */
    private String setCreaditLoan(String market, IfaStockDetailInfoSql002ResponseModel selSql002Res) {
        
        String creditLoan = " ";
        if (StringUtil.isNullOrEmpty(market)) {
            return creditLoan;
        }
        
        SelectMarket selected = SelectMarket.valueOfId(market);
        if (Objects.isNull(selected)) {
            return creditLoan;
        }
        switch (selected) {
        case TKY:
            creditLoan = selSql002Res.getMktLoanKbnTky();
            break;
        case NGY:
            creditLoan = selSql002Res.getMktLoanKbnNgy();
            break;
        case FKO:
            creditLoan = selSql002Res.getMktLoanKbnFko();
            break;
        case PTS:
            creditLoan = selSql002Res.getMktLoanKbnPts();
            break;
        case SPR:
            creditLoan = selSql002Res.getMktLoanKbnSpr();
            break;
        default:
            creditLoan = " ";
        }
        return creditLoan;
    }
    
    /**
     * API001呼び出し
     * 
     * @param brandCode　銘柄コード
     * @param market　市場
     * @return
     */
    private RealQuoteSnapshotOut callRealQueryAccountBalance(
        String brandCode, String market
    ) throws Exception {
        
        String[] inputStringArray = { market, brandCode.trim() };
        List<String[]> inputStringList = new ArrayList<String[]>();
        inputStringList.add(inputStringArray);
        String inputString = heracrossApiWrapper.convertInputstring(inputStringList);
        
        RealQuoteSnapshotOut result = new RealQuoteSnapshotOut();
        result = heracrossApiWrapper.getRealQuoteSnapshot(inputString);

        return result;
    }
    
}
