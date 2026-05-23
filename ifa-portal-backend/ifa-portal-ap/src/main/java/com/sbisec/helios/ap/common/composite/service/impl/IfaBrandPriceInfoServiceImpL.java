package com.sbisec.helios.ap.common.composite.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.extapi.ApiError;
import com.sbibits.earth.extapi.ApiIOException;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dao.IfaBrandPriceInfoDao;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoResponse_orderPriceUnit;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaBrandPriceInfoSql003ResponseModel;
import com.sbisec.helios.ap.common.composite.service.IfaBrandPriceInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.SelectMarket;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.HeracrossApiWrapper;

import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshot;
import jp.co.sbisec.pcenter.dto.heracross.RealQuoteSnapshotOut;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockBasePriceIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockBasePriceInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryStockBasePriceOutData;

/**
 * 画面ID：CC013
 * 画面名：銘柄時価情報（国内株）
 * 2023/08/24 新規作成
 *
 * @author SCSK
 */
@Component(value = "cmpIfaBrandPriceInfoService")
public class IfaBrandPriceInfoServiceImpL implements IfaBrandPriceInfoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrandPriceInfoServiceImpL.class);

    /**
     * API呼び出しクラス(heracross)
     */
    @Autowired
    private HeracrossApiWrapper heracrossApiWrapper;

    /**
     * API呼び出しクラス(NRI)
     */
    @Autowired
    private ApiWrapper apiWrapper;

    @Autowired
    private IfaBrandPriceInfoDao dao;

    /** 市場：PTS */
    private static final String MARKET_PTS = "7";

    /** TOPIX 500採用フラグ：1 */
    private static final String TOPIX_500_FLG_1 = "1";

    /** 呼値フラグ：01 */
    private static final String YOBINE_FLG_01 = "01";

    /** 呼値フラグ：03 */
    private static final String YOBINE_FLG_03 = "03";

    /**
     * アクションID：A002
     * アクション名：時価更新
     * Dto リクエスト：IfaBrandPriceInfoA002DtoRequest
     * Dto レスポンス：IfaBrandPriceInfoA002DtoResponse
     * model リクエスト：IfaBrandPriceInfoSql001RequestModel
     * model レスポンス：IfaBrandPriceInfoSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return 銘柄時価情報
     * @exception Exception システムエラー
     */
    public DataList<IfaBrandPriceInfoA002DtoResponse> updateMarketValueA002(IfaBrandPriceInfoA002DtoRequest dtoReq)
            throws Exception {

        DataList<IfaBrandPriceInfoA002DtoResponse> dtoRes = new DataList<IfaBrandPriceInfoA002DtoResponse>();
        List<IfaBrandPriceInfoA002DtoResponse> resList = new ArrayList<IfaBrandPriceInfoA002DtoResponse>();
        IfaBrandPriceInfoA002DtoResponse response = new IfaBrandPriceInfoA002DtoResponse();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBrandPriceInfoServiceImplL.updateMarketValueA002");
        }


        // SQL003
        // 指定銘柄呼値単位番号取得
        IfaBrandPriceInfoSql003RequestModel selSql003Req = new IfaBrandPriceInfoSql003RequestModel();
        selSql003Req.setBrandCode(dtoReq.getBrandCode());
        DataList<IfaBrandPriceInfoSql003ResponseModel> selSql003Res = dao.selectIfaBrandPriceInfoSql003(selSql003Req);

        String orderPriceUnitSql003 = "";
        if (selSql003Res.size() > 0) {
            IfaBrandPriceInfoSql003ResponseModel record = selSql003Res.get(0);
            if (StringUtils.equals(dtoReq.getMarket(), SelectMarket.TKY.getId())) {
                orderPriceUnitSql003 = record.getOrderPriceUnitTokyo();
            } else if (StringUtils.equals(dtoReq.getMarket(), SelectMarket.NGY.getId())) {
                orderPriceUnitSql003 = record.getOrderPriceUnitNagoya();
            } else if (StringUtils.equals(dtoReq.getMarket(), SelectMarket.FKO.getId())) {
                orderPriceUnitSql003 = record.getOrderPriceUnitFukuoka();
            } else if (StringUtils.equals(dtoReq.getMarket(), SelectMarket.SPR.getId())) {
                orderPriceUnitSql003 = record.getOrderPriceUnitSapporo();
            } 
        }

        // API001
        // 国内株時価情報を取得
        RealQuoteSnapshotOut api001Res = callGetRealQuoteSnapshot(dtoReq.getBrandCode(), dtoReq.getMarket());
        // API002
        // 株式基準価格を取得
        QueryStockBasePriceOutData api002Res = callQueryStockBasePrice(dtoReq.getBrandCode(), dtoReq.getMarket());

        // SQL002
        IfaBrandPriceInfoSql002RequestModel selSql002Req = new IfaBrandPriceInfoSql002RequestModel();
        DataList<IfaBrandPriceInfoSql002ResponseModel> selSql002Res = new DataList<IfaBrandPriceInfoSql002ResponseModel>();
        selSql002Req.setBrandCode(dtoReq.getBrandCode());
        selSql002Res = dao.selectIfaBrandPriceInfoSql002(selSql002Req);

        // SQL001
        IfaBrandPriceInfoSql001RequestModel selSql001Req = new IfaBrandPriceInfoSql001RequestModel();
        DataList<IfaBrandPriceInfoSql001ResponseModel> selSql001Res = new DataList<IfaBrandPriceInfoSql001ResponseModel>();
        // 市場=PTSの場合、SQL02の結果から呼び値フラグをセット
        if (MARKET_PTS.equals(dtoReq.getMarket())) {
            if (selSql002Res.getDataList().size() > 0 && TOPIX_500_FLG_1.equals(selSql002Res.getDataList().get(0).getTopix500Flg())) {
                selSql001Req.setOrderPriceUnit(YOBINE_FLG_03);
            } else {
                selSql001Req.setOrderPriceUnit(YOBINE_FLG_01);
            }
        } else { // 市場 != PTSの場合
            selSql001Req.setOrderPriceUnit(orderPriceUnitSql003);
        }

        selSql001Req.setMarket(dtoReq.getMarket());
        selSql001Res = dao.selectIfaBrandPriceInfoSql001(selSql001Req);

        // レスポンス項目の編集
        setResponse(response, dtoReq, api001Res, api002Res, selSql001Res, selSql002Res);
        resList.add(response);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

        return dtoRes;
    }

    /**
     * レスポンス項目編集
     *
     * @param response レスポンス情報
     * @param dtoReq リクエスト情報
     * @param api001Res API取得結果（国内株時価情報）
     * @param api002Res API取得結果（株式基準価格を取得）
     * @param selSql001Res SQL取得結果（呼値リスト）
     * @param selSql002Res SQL取得結果（売買単位）
     * @throws InvocationTargetException システムエラー
     * @throws IllegalAccessException システムエラー
     */
    private void setResponse(IfaBrandPriceInfoA002DtoResponse response, IfaBrandPriceInfoA002DtoRequest dtoReq,
            RealQuoteSnapshotOut api001Res, QueryStockBasePriceOutData api002Res,
            DataList<IfaBrandPriceInfoSql001ResponseModel> selSql001Res,
            DataList<IfaBrandPriceInfoSql002ResponseModel> selSql002Res) {

        RealQuoteSnapshot rqs = null;
        QueryStockBasePriceOutData qsbp = null;
        List<IfaBrandPriceInfoSql001ResponseModel> ibp1 = null;
        List<IfaBrandPriceInfoSql002ResponseModel> ibp2 = null;

        if (api001Res != null && api001Res.getFeedData() != null && api001Res.getFeedData().size() > 0) {
            rqs = api001Res.getFeedData().get(0);
        }
        if (api002Res != null) {
            qsbp = api002Res;
        }
        if (selSql001Res != null && selSql001Res.getDataList() != null && selSql001Res.getDataList().size() > 0) {
            ibp1 = selSql001Res.getDataList();
        }

        if (selSql002Res != null && selSql002Res.getDataList() != null && selSql002Res.getDataList().size() > 0) {
            ibp2 = selSql002Res.getDataList();
        }

        // 銘柄コード
        response.setBrandCode(dtoReq.getBrandCode());
        // 市場
        response.setMarket(dtoReq.getMarket());

        if (rqs != null) {
            // 現在値
            response.setCurrentPrice(rqs.getCurPrice());
            // 現在値ティック
            response.setTick(rqs.getCurPriceTick());
            // 現在値フラグ
            response.setCurrentFlag(rqs.getCurPriceTone());
            // 始値
            response.setStart(rqs.getOpenPrice());
            // 始値更新時刻
            response.setStartTime(rqs.getOpenPriceTime());
            // 高値
            response.setHigh(rqs.getHighPrice());
            // 高値更新時刻
            response.setHighTime(rqs.getHighPriceTime());
            // 安値
            response.setLow(rqs.getLowPrice());
            // 安値更新時刻
            response.setLowTime(rqs.getLowPriceTime());
            // 前日比
            response.setDiff(rqs.getPriceChange());
            // 前日比率
            response.setRatio(rqs.getPerChange());
            // 現在値日付
            response.setUpdateDate(rqs.getCurPriceDate());
            // 現在値更新時刻/前日比更新時刻
            response.setUpdateTime4(rqs.getCurPriceTime());
            // 前日終値
            response.setLast(rqs.getLastClosingPrice());
            // 前日終値日付
            response.setLastDate(rqs.getLastClosingPriceDate());
            // 売買代金
            response.setBuySellPrice(rqs.getTurnoverAmount());
            // 出来高
            response.setVolume(rqs.getVolume());
            // 出来高更新時刻
            response.setUpdateTime41(rqs.getVolTime());

        }
        if (qsbp != null && qsbp.getQueryBasePrice() != null) {
            // ストップ高(売)
            response.setSellStopHigh(qsbp.getQueryBasePrice().getSStopHigh());
            // ストップ安(売)
            response.setSellStopLow(qsbp.getQueryBasePrice().getSStopLow());
            // ストップ高(買)
            response.setBuyStopHigh(qsbp.getQueryBasePrice().getBStopHigh());
            // ストップ安(買)
            response.setBuyStopLow(qsbp.getQueryBasePrice().getBStopLow());
            // 値幅制限(年月日)
            response.setBaseDate(qsbp.getQueryBasePrice().getBaseDate());
            // 基準価格
            response.setBasePrice(qsbp.getQueryBasePrice().getBasePrice());
        }
        if (ibp1 != null) {
            List<IfaBrandPriceInfoA002DtoResponse_orderPriceUnit> resOrderPriceUnitList = new ArrayList<IfaBrandPriceInfoA002DtoResponse_orderPriceUnit>();
            for (IfaBrandPriceInfoSql001ResponseModel model : ibp1) {
                IfaBrandPriceInfoA002DtoResponse_orderPriceUnit OrderPriceUnit = new IfaBrandPriceInfoA002DtoResponse_orderPriceUnit();
                // 超
                OrderPriceUnit.setOver(model.getBeyondOrderPriceUnit());
                // 以内
                OrderPriceUnit.setWithin(model.getWithinOrderPriceUnit());
                // 呼値
                OrderPriceUnit.setOrderPriceUnit(model.getCallPriceOrderPriceUnit());

                resOrderPriceUnitList.add(OrderPriceUnit);
            }
            // 呼値リスト
            response.setOrderPriceUnit(resOrderPriceUnitList);
        }

        if (ibp2 != null) {
            // 売買単位
            response.setUnit(ibp2.get(0).getUnit());
        }

    }

    /**
     * API001呼び出し
     *
     * @param brandCode 銘柄コード
     * @param market 市場
     * @return API結果
     */
    private RealQuoteSnapshotOut callGetRealQuoteSnapshot(String brandCode, String market) {

        //市場と銘柄コードの配列を定義
        String [] beforeInputstringAray = {market,brandCode.trim()};
        //配列のリストを定義
        List<String[]> beforeInputstringArayList = new ArrayList<String[]>();
        //配列をリストに追加
        beforeInputstringArayList.add(beforeInputstringAray);

        RealQuoteSnapshotOut result = new RealQuoteSnapshotOut();

        try { // 国内株時価情報取得を取得する。
            //パラメータを変換
            String inputstring = heracrossApiWrapper.convertInputstring(beforeInputstringArayList);
            result = heracrossApiWrapper.getRealQuoteSnapshot(inputstring);
        } catch (ApiIOException e) {
            LOGGER.error("API Exception occured.");
            LOGGER.info("API Exception occured.", e);
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
        }
        return result;
    }

    /**
     * API002呼び出し
     *
     * @param brandCode 銘柄コード
     * @param market 市場
     * @return API結果
     */
    private QueryStockBasePriceOutData callQueryStockBasePrice(String brandCode, String market) {

        QueryStockBasePriceInData inData = new QueryStockBasePriceInData();
        QueryStockBasePriceIn input = new QueryStockBasePriceIn();
        QueryStockBasePriceOutData outData = new QueryStockBasePriceOutData();

        // 銘柄コード 銘柄コード先頭4桁+"△"
        inData.setSecCode(brandCode.substring(0, 4) + " ");
        // 新旧区分　銘柄コード末尾1桁
        inData.setNOId(brandCode.substring(brandCode.length() - 1));
        // 市場コード
        inData.setMarketId(market);
        // API input DTOにセット
        input.setIndata(inData);

        try {
            // NRI_APIから買付余力情報を取得する。
            outData = apiWrapper.queryStockBasePrice(input);
        } catch (ApiError e) {
            LOGGER.error("API Exception occured.");
            LOGGER.info("API Exception occured.", e);

        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
        }

        return outData;
    }

}
