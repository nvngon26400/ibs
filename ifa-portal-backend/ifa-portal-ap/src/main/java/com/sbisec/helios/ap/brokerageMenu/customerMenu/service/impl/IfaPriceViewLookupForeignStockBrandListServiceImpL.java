package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.component.Fct033;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaPriceViewLookupForeignStockBrandListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaPriceViewLookupForeignStockBrandListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.ForeignStockTradeAccountOpenStatus;
import com.sbisec.helios.ap.common.enums.TargetCustomerReferenceAuthorityFlag;
import com.sbisec.helios.ap.common.enums.TradeSuspendFlag;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.common.service.CodeListService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Component(value = "cmpIfaPriceViewLookupForeignStockBrandListService")
public class IfaPriceViewLookupForeignStockBrandListServiceImpL
        implements IfaPriceViewLookupForeignStockBrandListService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaPriceViewLookupForeignStockBrandListServiceImpL.class);
    
    /** FCT001 利用者顧客参照権限チェック */
    @Autowired
    private Fct001 fct001;
    
    /** FCT003 取引コース媒介可否チェック */
    @Autowired
    private Fct003 fct003;
    
    /** FCT033 営業日チェック */
    @Autowired
    private Fct033 fct033;
    
    /** 区分値マスタ取得クラス */
    @Autowired
    private CodeListService codeListService;
    
    /** DAO */
    @Autowired
    private IfaPriceViewLookupForeignStockBrandListDao dao;

    /** IfaDateUtil */
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    /** 取引停止口座のため処理を進めることができません。 */
    private static final String ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE = "errors.frs.selectedAccount.outOfService";
    
    /** {0}ができないコースです。　*/
    private static final String ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE = "errors.cmn.selectedAccountCourse.unavailable";
    
    /** 外国株式口座が未開設です。 */
    private static final String ERRORS_FOREIGNSTOCKACCOUNTCHECK = "errors.foreignStockAccountCheck";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** 証券金銭種別 "外国株式" */
    private static final String FOREIGN_STOCK = "15";
    
    /** 国籍コード "US" */
    private static final String COUNTRY_CODE = "US";
    
    /** 通貨コード "999" */
    private static final String CURRENCY_CODE_999 = "999";
    
    /** 媒介可否(可) */
    private static final String MEDIATE_PROPRIETY_VALUE_1 = "1";
    
    /** 販売状態 */
    private static final String SALE_STATUS = "1";
    
    /** A001_SQL003_1 */
    private static final String FOR_SALE_URL = "FOR_SALE_URL";
    
    /** A001_SQL003_2 */
    private static final String FOR_TRADING_MANUAL_URL = "FOR_TRADING_MANUAL_URL";
    
    /** A001_SQL003_3 */
    private static final String FOR_FOREIGN_SECURITIES_URL = "FOR_FOREIGN_SECURITIES_URL";
    
    /** 店頭買の区分値 */
    private static final String TRADE_CLASS_SHOP_BUY = "11";
    
    /** 店頭売の区分値 */
    private static final String TRADE_CLASS_SHOP_SELL = "12";
    
    /** SQL002_ティッカー選択フラグ_ティッカー */
    private static final String TICKERKEY = "0";
    
    /** SQL002_ティッカー選択フラグ_名称 */
    private static final String BRANDNAMEKEY = "1";
    
    /** SQL002_パーセント */
    private static final String PERCENT = "%";
    
    /** 取引停止 */
    private static final String TRADESUSPEND = "取引停止";
    
    /** HTMLタグ */
    private static final String HTML_TAG_PATTERN = ".*<.*>.*";
    
    /** 独自改行タグ */
    private static final String TAG_RN = "<rn>";
    
    /** 独自改行タグ */
    private static final String TAG_BR = "<br>";
    
    /** LocalDate型変換フォーマット:時分 */
    private static final String HOUR_MINUTE = "HH:mm";
    
    /** 売却 */
    private static final String SELL = "SELL";
    
    /** 買付 */
    private static final String BUY = "BUY";
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /** エスケープ文字 */
    private static final String ESCAPE_CHAR = "!";
    
    /** パーセント記号 */
    private static final String PERCENT_SIGN = "%";
    
    /** アンダースコア */
    private static final String UNDERSCORE = "_";

    /** 区分ID:対象取引（メッセージ表示用）　*/
    private static final String CODE_ID_MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";

    /** 区分値:対象取引（メッセージ表示用）.米株店頭取引　*/
    private static final String CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_COUNTER = "6B";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * このアクションは、初期表示時に必要なデータを取得し、Dtoに設定して返す。
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA001RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA001ResponseDto
     *
     * @param dtoReq クライアントからのリクエストデータが含まれるDto
     * @return 初期化処理後の応答データを含むDataList
     * @throws Exception 例外が発生した場合にスローされる
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto> initializeA001(
            IfaPriceViewLookupForeignStockBrandListA001RequestDto dtoReq) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto> dtoRes = new DataList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto>();
        List<IfaPriceViewLookupForeignStockBrandListA001ResponseDto> resDto = new ArrayList<IfaPriceViewLookupForeignStockBrandListA001ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPriceViewLookupForeignStockBrandListServiceImplL.initializeA001");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // SQL001を実行してデータを取得
        IfaPriceViewLookupForeignStockBrandListSql001RequestModel sql001Req = new IfaPriceViewLookupForeignStockBrandListSql001RequestModel();
        DataList<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel> sql001Res = dao
                .selectIfaPriceViewLookupForeignStockBrandListSql001(sql001Req);
        
        // SQL003: URL取得処理を実行する
        String foreignSecuritiesUpdateUrl = getSql003Url(FOR_SALE_URL);
        String domesticTradingManualUrl = getSql003Url(FOR_TRADING_MANUAL_URL);
        String latestForeignSecuritiesUrl = getSql003Url(FOR_FOREIGN_SECURITIES_URL);
        
        // 処理結果をDtoに設定
        IfaPriceViewLookupForeignStockBrandListA001ResponseDto responseDto = new IfaPriceViewLookupForeignStockBrandListA001ResponseDto();
        responseDto.setForeignSecuritiesInfoUpdateHistoryUrl(foreignSecuritiesUpdateUrl);
        responseDto.setDomesticOverTheCounterTradingManualUrl(domesticTradingManualUrl);
        responseDto.setLatestForeignSecuritiesInfoListUrl(latestForeignSecuritiesUrl);
        
        if (sql001Res != null && !CollectionUtils.isEmpty(sql001Res.getDataList())
                && sql001Res.getDataList().size() != 0) {
            // 各応答モデルからデータを取得
            IfaPriceViewLookupForeignStockBrandListSql001ResponseModel sql001Response = sql001Res.get(0);
            
            // お知らせと注意事項を取得して処理する
            StringBuilder notification = new StringBuilder();
            notification.append(StringUtils.defaultString(sql001Response.getNotice()));
            notification.append(StringUtils.defaultString(sql001Response.getNotice2()));
            notification.append(StringUtils.defaultString(sql001Response.getNotice3()));
            notification.append(StringUtils.defaultString(sql001Response.getNotice4()));
            notification.append(StringUtils.defaultString(sql001Response.getNotice5()));
            notification.append(StringUtils.defaultString(sql001Response.getNotice6()));
            
            StringBuilder noticeNote = new StringBuilder();
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention()));
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention2()));
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention3()));
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention4()));
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention5()));
            noticeNote.append(StringUtils.defaultString(sql001Response.getAttention6()));
            
            // お知らせ・注意事項でそれぞれ独自の改行タグを置換しレスポンスDTOにセットする       
            responseDto.setNotification(replaceCustomNewlineTag(notification.toString()));
            responseDto.setNoticeNote(replaceCustomNewlineTag(noticeNote.toString()));
        }
        
        resDto.add(responseDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * このアクションは、ユーザーが指定した検索条件に基づいて外国株式の価格情報を取得し、表示用のデータを設定して返す。
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA002RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA002ResponseDto
     *
     * @param dtoReq クライアントからのリクエストデータが含まれるDto
     * @return 外国株式の価格情報を含む応答データを含むDataList
     * @throws Exception 例外が発生した場合にスローされる
     */
    @Override
    public DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto> displayA002(
            IfaPriceViewLookupForeignStockBrandListA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto> dtoRes = new DataList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto>();
        List<IfaPriceViewLookupForeignStockBrandListA002ResponseDto> resDto = new ArrayList<IfaPriceViewLookupForeignStockBrandListA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPriceViewLookupForeignStockBrandListServiceImplL.displayA002");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // SQL002を実行してデータを取得するリクエストモデルのインスタンスを生成
        IfaPriceViewLookupForeignStockBrandListSql002RequestModel sql002Req = new IfaPriceViewLookupForeignStockBrandListSql002RequestModel();
        
        // 検索条件の設定
        sql002Req.setBrokerCode(cc.getBrokerCode());
        if (TICKERKEY.equals(dtoReq.getTickerSelectFlag())) {
            sql002Req.setTickerSelectFlag(dtoReq.getTickerSelectFlag()); // 検索条件フラグを設定
            sql002Req.setTickerKey(dtoReq.getBrandCodeTicker()); // ティッカーキーを設定
        } else if (BRANDNAMEKEY.equals(dtoReq.getTickerSelectFlag()) && dtoReq.getBrandName() != null
                && !dtoReq.getBrandName().isEmpty()) {
            sql002Req.setTickerSelectFlag(dtoReq.getTickerSelectFlag()); // 検索条件フラグを設定
            sql002Req.setBrandNameKey(PERCENT + addEscape(dtoReq.getBrandName()) + PERCENT); // 名称キーを設定
        }
        // SQL002を実行してデータを取得
        DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> sql002Res = dao
                .selectIfaPriceViewLookupForeignStockBrandListSql002(sql002Req);
        
        // 取得件数が0件の場合、エラーメッセージを出して例外をスロー
        String errorCode;
        String errorMessage;
        String[] errorInfoSql002;
        
        if (sql002Res == null || CollectionUtils.isEmpty(sql002Res.getDataList())
                || sql002Res.getDataList().size() == 0) {
            errorCode = ERRORS_DATALIST_NOTFOUND;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            errorInfoSql002 = new String[] { errorCode, errorMessage };
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.INFO, errorInfoSql002[0], errorInfoSql002[1]);
        }
        
        // 価格情報の編集および最新の更新時間の取得
        // 最新の更新時間を保持する変数を宣言
        String latestUpdateTime = null;
        
        // 価格情報の編集および最新の更新時間の取得
        IfaPriceViewLookupForeignStockBrandListA002ResponseDto responseDto = new IfaPriceViewLookupForeignStockBrandListA002ResponseDto();
        List<IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem> brandListItems = new ArrayList<IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem>();
        
        boolean isBusinessDay = isBusinessDay();
        for (IfaPriceViewLookupForeignStockBrandListSql002ResponseModel data : sql002Res.getDataList()) {
            // 売却価格と買付価格の編集
            String salePriceEdited = editPrice(data.getBasePrice(), data.getTradeState(),
                    data.getSellTradeQuotaRemaining(), data.getTradeStartTime(), data.getTradeEndTime(),
                    data.getSellingPrice(), SELL, isBusinessDay);
            String purchasePriceEdited = editPrice(data.getBasePrice(), data.getTradeState(),
                    data.getBuyTradeQuotaRemaining(), data.getTradeStartTime(), data.getTradeEndTime(),
                    data.getBuyingPrice(), BUY, isBusinessDay);
            
            // BrandListItemオブジェクトの作成と設定
            IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem item = new IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem();
            item = convertBrandList(data);
            
            // 売却価格と買付価格をセット
            item.setSellPrice(salePriceEdited);
            item.setBuyPrice(purchasePriceEdited);
            
            // リストに追加
            brandListItems.add(item);
            
            // より最新な更新時間を保持する
            if (StringUtils.compare(data.getUpdateTime(), latestUpdateTime) >= 0) {
                latestUpdateTime = data.getUpdateTime();
            }
        }
        
        // 応答DTOに最新の更新時間をセット
        if (latestUpdateTime != null) {
            responseDto.setUpdateTime(latestUpdateTime);
        }
        
        // 応答DTOにbrandListItemsをセット
        responseDto.setBrandList(brandListItems);
        
        // responseDtoをDataListにセットして返却
        resDto.add(responseDto);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A008
     * アクション名：戻るボタン再表示
     * このアクションは、「戻る」ボタンを再度表示した際に必要なデータを取得し、Dtoに設定して返す。
     * Dto リクエスト：IfaPriceViewLookupForeignStockBrandListA008RequestDto
     * Dto レスポンス：IfaPriceViewLookupForeignStockBrandListA008ResponseDto
     *
     * @param dtoReq クライアントからのリクエストデータが含まれるDto
     * @return 「戻る」ボタン再表示に必要な応答データを含むDataList
     * @throws Exception 例外が発生した場合にスローされる
     */
    @Override
    public DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto> backButtonRedisplayA008(
            IfaPriceViewLookupForeignStockBrandListA008RequestDto dtoReq) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto> dtoRes = new DataList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto>();
        List<IfaPriceViewLookupForeignStockBrandListA008ResponseDto> resDto = new ArrayList<IfaPriceViewLookupForeignStockBrandListA008ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPriceViewLookupForeignStockBrandListServiceImplL.backButtonRedisplayA008");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // action内チェック処理
        errorInfo = checkValidation(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // SQL002を実行してデータを取得するリクエストモデルのインスタンスを生成
        IfaPriceViewLookupForeignStockBrandListSql002RequestModel sql002Req = new IfaPriceViewLookupForeignStockBrandListSql002RequestModel();
        
        // 検索条件の設定
        sql002Req.setBrokerCode(cc.getBrokerCode());
        if (TICKERKEY.equals(dtoReq.getTickerSelectFlag())) {
            sql002Req.setTickerSelectFlag(dtoReq.getTickerSelectFlag()); // 検索条件フラグを設定
            sql002Req.setTickerKey(dtoReq.getBrandCodeTicker()); // ティッカーキーを設定
        } else if (BRANDNAMEKEY.equals(dtoReq.getTickerSelectFlag()) && dtoReq.getBrandName() != null
                && !dtoReq.getBrandName().isEmpty()) {
            sql002Req.setTickerSelectFlag(dtoReq.getTickerSelectFlag()); // 検索条件フラグを設定
            sql002Req.setBrandNameKey(PERCENT + addEscape(dtoReq.getBrandName()) + PERCENT); // 名称キーを設定
        }
        
        // SQL002を実行してデータを取得
        DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> sql002Res = dao
                .selectIfaPriceViewLookupForeignStockBrandListSql002(sql002Req);
        
        // 取得件数が0件の場合、エラーメッセージを出して例外をスロー
        String errorCode;
        String errorMessage;
        String[] errorInfoSql002;
        
        if (sql002Res == null || CollectionUtils.isEmpty(sql002Res.getDataList())
                || sql002Res.getDataList().size() == 0) {
            errorCode = ERRORS_DATALIST_NOTFOUND;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            errorInfoSql002 = new String[] { errorCode, errorMessage };
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.INFO, errorInfoSql002[0], errorInfoSql002[1]);
        }
        
        // 価格情報の編集および最新の更新時間の取得
        // 最新の更新時間を保持する変数を宣言
        String latestUpdateTime = null;
        
        // 価格情報の編集および最新の更新時間の取得
        IfaPriceViewLookupForeignStockBrandListA008ResponseDto responseDto = new IfaPriceViewLookupForeignStockBrandListA008ResponseDto();
        List<IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem> brandListItems = new ArrayList<IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem>();
        
        boolean isBusinessDay = isBusinessDay();
        for (IfaPriceViewLookupForeignStockBrandListSql002ResponseModel data : sql002Res.getDataList()) {
            // 売却価格と買付価格の編集
            String salePriceEdited = editPrice(data.getBasePrice(), data.getTradeState(),
                    data.getSellTradeQuotaRemaining(), data.getTradeStartTime(), data.getTradeEndTime(),
                    data.getSellingPrice(), SELL, isBusinessDay);
            String purchasePriceEdited = editPrice(data.getBasePrice(), data.getTradeState(),
                    data.getBuyTradeQuotaRemaining(), data.getTradeStartTime(), data.getTradeEndTime(),
                    data.getBuyingPrice(), BUY, isBusinessDay);
            
            // BrandListItemオブジェクトの作成と設定
            IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem item = new IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem();
            item = convertBrandList(data);
            
            // 売却価格と買付価格をセット
            item.setSellPrice(salePriceEdited);
            item.setBuyPrice(purchasePriceEdited);
            
            // リストに追加
            brandListItems.add(item);
            
            // より最新な更新時間を保持する
            if (StringUtils.compare(data.getUpdateTime(), latestUpdateTime) >= 0) {
                latestUpdateTime = data.getUpdateTime();
            }
        }
        
        // 応答DTOに最新の更新時間をセット
        if (latestUpdateTime != null) {
            responseDto.setUpdateTime(latestUpdateTime);
        }
        
        // 応答DTOにbrandListItemsをセット
        responseDto.setBrandList(brandListItems);
        
        // responseDtoをDataListにセットして返却
        resDto.add(responseDto);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * FCT001チェック
     *
     * @param cc 顧客共通情報
     * @return エラー情報
     */
    private String[] callFct001(CustomerCommon cc) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        InputFct001Dto input = new InputFct001Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        
        OutputFct001Dto output = fct001.doCheck(input);
        if (output != null) {
            if (StringUtils.equals(output.getTargetCustomerRefAuthFlag(),
                    TargetCustomerReferenceAuthorityFlag.KENGEN_NASHI.getId())) {
                // 権限なしの場合
                errorCode = ERRORS_BUTENACCOUNTNOTEXIST;
                errorMessage = IfaCommonUtil.getMessage(errorCode,
                        new String[] { cc.getButenCode(), cc.getAccountNumber() });
                return new String[] { errorCode, errorMessage };
            }
            if (StringUtils.equals(output.getTradeSuspendFlag(), TradeSuspendFlag.SUSPEND.getId())) {
                // 取引停止口座の場合
                errorCode = ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE;
                errorMessage = IfaCommonUtil.getMessage(errorCode);
                return new String[] { errorCode, errorMessage };
            }
        }
        return null;
    }
    
    /**
     * FCT003チェック
     *
     * @param cc 顧客共通情報
     * @return エラー情報
     */
    private String[] callFct003(CustomerCommon cc) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // 店頭買と店頭売のチェックを行うための取引種別コード
        final String TRADE_CLASS_OVER_THE_COUNTER_BUY = TRADE_CLASS_SHOP_BUY;
        final String TRADE_CLASS_OVER_THE_COUNTER_SELL = TRADE_CLASS_SHOP_SELL;
        
        // 店頭買と店頭売の媒介可否をチェック
        boolean mediationPossibleForBuy = checkMediationPropriety(cc, TRADE_CLASS_OVER_THE_COUNTER_BUY);
        boolean mediationPossibleForSell = checkMediationPropriety(cc, TRADE_CLASS_OVER_THE_COUNTER_SELL);
        
        // 両方とも不可の場合、媒介不可エラーを返す
        if (!mediationPossibleForBuy && !mediationPossibleForSell) {
            errorCode = ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE;
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { codeListService
                            .getValue(CODE_ID_MSG_DISPLAY_TARGET_TRADE, CODE_VAL_MSG_DISPLAY_TARGET_TRADE_FSTOCK_COUNTER) }  );
            return new String[] { errorCode, errorMessage };
        }
        
        return null;
    }
    
    /**
     * 媒介可否のチェックを行う共通メソッド
     *
     * @param cc 顧客共通情報
     * @param tradeClass 取引種別
     * @return 媒介可能かどうか
     */
    private boolean checkMediationPropriety(CustomerCommon cc, String tradeClass) {
        
        InputFct003Dto input = new InputFct003Dto();
        input.setAccountNumber(cc.getAccountNumber());
        input.setButenCode(cc.getButenCode());
        input.setProductCd(FOREIGN_STOCK);
        input.setTradeCd(tradeClass);
        input.setCountryCd(COUNTRY_CODE);
        input.setCurrencyCode(CURRENCY_CODE_999);
        
        OutputFct003Dto output = fct003.doCheck(input);
        // 媒介可取引有無＝"1"（あり）をチェック
        if (MEDIATE_PROPRIETY_VALUE_1.equals(output.getMediateAbleTradeFlag())) {
            return true;
        }
        return false;
    }
    
    /**
     * 売却・買付価格の編集
     *
     * @param referencePrice 基準価格
     * @param saleStatus 販売状態
     * @param stockQuantity 売枠
     * @param startTradeTime 取引開始時間
     * @param endTradeTime 取引終了時間
     * @param price 編集前価格
     * @param priceFlag 売却/買付価格フラグ
     * @return 取引停止：取引停止　取引停止でない：編集された価格
     */
    private String editPrice(BigDecimal referencePrice, String saleStatus, Long stockQuantity, String startTradeTime,
            String endTradeTime, BigDecimal price, String priceFlag, boolean isBusinessDay) {
        
        String now = DateUtil.format(HOUR_MINUTE);
        if (referencePrice == null || SALE_STATUS.equals(saleStatus) || stockQuantity <= 0
                || StringUtils.compare(startTradeTime, now) >= 0 || StringUtils.compare(endTradeTime, now) < 0
                || !isBusinessDay) {
            // 取引停止を示す
            return TRADESUSPEND;
        } else {
            if (SELL.equals(priceFlag)) {
                // 売却価格の場合、小数点4桁目を切り上げて文字列に変換
                return (price.setScale(3, RoundingMode.UP).toString());
            } else {
                // 買付価格の場合、小数点4桁目を切り下げて文字列に変換
                return (price.setScale(3, RoundingMode.DOWN).toString());
            }
        }
    }
    
    private boolean isBusinessDay() throws Exception {
        
        // FCT033を呼び出して営業日かどうかをチェック
        try {
            InputFct033Dto inputDto = new InputFct033Dto();
            inputDto.setDate(ifaDateUtil.getCurrentDate()); // 本日の日付を設定
            OutputFct033Dto outputDto = fct033.doCheck(inputDto);
            
            // 営業日チェックフラグが'1'(営業日)の場合はtrue、そうでなければfalse
            return "1".equals(outputDto.getBusinessDayCheckFlag());
        } catch (Exception e) {
            LOGGER.error("Error checking business day status", e);
            return false; // エラーが発生した場合は非営業日として扱う
        }
    }
    
    /**
     * action内チェック処理
     *
     * @param cc 顧客共通情報
     * @param collateralSecurityType
     * @return エラー情報
     */
    
    private String[] checkValidation(CustomerCommon cc) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        String[] errorInfo = null;
        
        // FCT001
        errorInfo = callFct001(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        // FCT003
        errorInfo = callFct003(cc);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return errorInfo;
        }
        
        //外国株式口座取引開設状況のチェック
        if (StringUtils.equals(cc.getForeignStockTradeAccountOpenStatus(),
                ForeignStockTradeAccountOpenStatus.CLOSED.getId())) {
            errorCode = ERRORS_FOREIGNSTOCKACCOUNTCHECK;
            errorMessage = IfaCommonUtil.getMessage(errorCode);
            return new String[] { errorCode, errorMessage };
        }
        
        return null;
    }
    
    /**
     * 独自の改行タグ「<rn>」を適切なHTMLタグに置換する。
     * 
     * @param text 置換する文字列
     * @return 置換された文字列
     */
    private String replaceCustomNewlineTag(String text) {
        
        if (text == null || text.isEmpty()) {
            return text;
        }
        String matchText = text.replaceAll(TAG_RN, "");
        if (matchText.matches(HTML_TAG_PATTERN)) {
            // 独自の改行タグ以外にタグが含まれている場合
            return text.replace(TAG_RN, "");
        } else {
            return text.replace(TAG_RN, TAG_BR);
        }
    }
    
    /**
     * 検索用特殊文字があればエスケープ文字を付与する
     *
     * @param text 置換する文字列
     * @return 置換された文字列
     */
    private String addEscape(String text) {
        
        text = text.replaceAll(ESCAPE_CHAR, ESCAPE_CHAR + ESCAPE_CHAR);
        text = text.replaceAll(PERCENT_SIGN, ESCAPE_CHAR + PERCENT_SIGN);
        text = text.replaceAll(UNDERSCORE, ESCAPE_CHAR + UNDERSCORE);
        return text;
    }
    
    /**
     * SQL002のレスポンスをDtoの銘柄リストに詰め替える
     *
     * @param data SQL002のレスポンス
     * @return Dtoの銘柄リスト
     */
    private IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem convertBrandList(
            IfaPriceViewLookupForeignStockBrandListSql002ResponseModel data) {
        
        IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem item = new IfaPriceViewLookupForeignStockBrandListResponseDtoBrandListItem();
        
        // ティッカー
        item.setTicker(data.getBrandCode());
        // 名称
        item.setName(data.getBrandName());
        // 基準価格
        item.setBasePrice8(data.getBasePrice().toString());
        // 前日終値
        item.setLast(data.getPrevDayClosingPrice().toString());
        // 前日比
        item.setDiff(data.getPrevDayRatio().toString());
        // 取引停止/価格変更理由
        item.setTradeSuspendPriceChangeReason(data.getMessage());
        
        return item;
    }
    
    /**
     * SQL003の実行
     *
     * @param key URL取得に使用するキー
     * @return 取得したURL
     */
    private String getSql003Url(String key) throws Exception {
        
        IfaPriceViewLookupForeignStockBrandListSql003RequestModel reqModel = new IfaPriceViewLookupForeignStockBrandListSql003RequestModel();
        reqModel.setBrandNameKey(key);
        DataList<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel> resModel = dao
                .selectIfaPriceViewLookupForeignStockBrandListSql003(reqModel);
        
        if (resModel == null || CollectionUtils.isEmpty(resModel.getDataList()) || resModel.getDataList().size() == 0) {
            return ""; // URL取得が0件の場合は空文字を返す
        }
        return resModel.getDataList().get(0).getVarValue();
    }
}
