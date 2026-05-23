package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaFxTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaFxTradeHistorySql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryCurrencyCode;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaFxTradeHistoryFxTradeHistory;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaFxTradeHistoryService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaFxTradeHistoryCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB020302_0202-01
 * 画面名：為替取引履歴
 *
 * @author SCSK川崎
 2024/05/09 新規作成
 */
@Component(value = "cmpIfaFxTradeHistoryService")
public class IfaFxTradeHistoryServiceImpL implements IfaFxTradeHistoryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaFxTradeHistoryServiceImpL.class);
    
    /** DAO */
    @Autowired
    private IfaFxTradeHistoryDao dao;
    
    /** FCT030 仲介業者コード営業員リスト取得 */
    @Autowired
    private Fct030 fct030;
    
    /** FCT038 CSVダウンロードMAX件数取得 */
    @Autowired
    private Fct038 fct038;
    
    /** ComplianceService(CSV証跡登録用)クラス */
    @Autowired
    private ComplianceService complianceService;
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** {0}は{1}以内を正しく入力して下さい。 \{0}：期間指定 {1}：過去1年間の範囲で6ヶ月 */
    private static final String ERRORS_DATERANGE = "errors.dateRange";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 \{0}："5,000" {1}：SQL001.総件数 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 \{0}：CSVダウンロードMAX件数 {1}：SQL001.総件数 {2}：CSVダウンロードMAX件数 */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    // --------------------------------
    // 設定値
    // --------------------------------
    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB020302_0202-01";
    
    /** 日付フォーマット */
    private static final String DATE_FORM = "yyyy/MM/dd";
    
    /** ERRORS_DATERANGE：プレースホルダ0 */
    private static final String DATERANGE_0 = "期間指定";
    
    /** ERRORS_DATERANGE：プレースホルダ1 */
    private static final String DATERANGE_1 = "6ヶ月";
    
    /** 権限コード 1：SBI証券本店 */
    private static final String AUTH_CODE_SBI = "1";
    
    /** A002 SQL001 上限件数 */
    private static final int MAX_ROW_NUM = 5000;
    
    /** A002 WARNINGS_DATALIST_OVERMAXROWNUM　{0} */
    private static final String MAX_ROW_NUM_STR = "5,000";
    
    /** 正常終了メッセージコード */
    private static final String SUCCESS_MESSAGE_CODE = "SUCCESS";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaFxTradeHistoryA001DtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA001DtoResponse
     * model リクエスト：IfaFxTradeHistorySql002RequestModel
     * model レスポンス：IfaFxTradeHistorySql002ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA001DtoResponse> initializeA001(IfaFxTradeHistoryA001DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxTradeHistoryA001DtoResponse> dtoRes = new DataList<IfaFxTradeHistoryA001DtoResponse>();
        List<IfaFxTradeHistoryA001DtoResponse> resDto = new ArrayList<IfaFxTradeHistoryA001DtoResponse>();
        IfaFxTradeHistoryA001DtoResponse res = new IfaFxTradeHistoryA001DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeHistoryServiceImplL.initializeA001");
        }
        
        // SQL002：通貨リスト取得
        IfaFxTradeHistorySql002RequestModel selSql002Req = new IfaFxTradeHistorySql002RequestModel();
        DataList<IfaFxTradeHistorySql002ResponseModel> selSql002Res = dao.selectIfaFxTradeHistorySql002(selSql002Req);
        
        List<IfaFxTradeHistoryCurrencyCode> currencyCodeList = new ArrayList<>();
        for (IfaFxTradeHistorySql002ResponseModel item : selSql002Res.getDataList()) {
            IfaFxTradeHistoryCurrencyCode currencyCode = new IfaFxTradeHistoryCurrencyCode();
            currencyCode.setKey(item.getKey());
            currencyCode.setName(item.getName());
            currencyCodeList.add(currencyCode);
        }
        res.setCurrencyCodeList(currencyCodeList);
        
        // SQL003：為替取引履歴コメント取得
        IfaFxTradeHistorySql003RequestModel selSql003Req = new IfaFxTradeHistorySql003RequestModel();
        DataList<IfaFxTradeHistorySql003ResponseModel> selSql003Res = dao.selectIfaFxTradeHistorySql003(selSql003Req);
        if (selSql003Res.getDataList().size() != 0) {
            res.setFxTradeHistoryComment(selSql003Res.getDataList().get(0).getFxTradeHistoryComment());            
        }
        
        resDto.add(res);
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaFxTradeHistoryA002DtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA002DtoResponse
     * model リクエスト：IfaFxTradeHistorySql001RequestModel
     * model レスポンス：IfaFxTradeHistorySql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA002DtoResponse> displayA002(IfaFxTradeHistoryA002DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaFxTradeHistoryA002DtoResponse> dtoRes = new DataList<IfaFxTradeHistoryA002DtoResponse>();
        List<IfaFxTradeHistoryA002DtoResponse> resDto = new ArrayList<IfaFxTradeHistoryA002DtoResponse>();
        IfaFxTradeHistoryA002DtoResponse res = new IfaFxTradeHistoryA002DtoResponse();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeHistoryServiceImplL.displayA002");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // action内チェック処理
        errorInfo = checkValidation(dtoReq.getPeriodYmFrom(), dtoReq.getPeriodYmTo(), userAccount);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // SQL001：為替取引履歴リストを取得する
        IfaFxTradeHistorySql001RequestModel selSql001Req = new IfaFxTradeHistorySql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setMaxRow(MAX_ROW_NUM);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 仲介業者除外フラグ
        if ("".equals(dtoReq.getChkBrokerCodeExclude())) {
            // チェックなしのとき
            selSql001Req.setChkBrokerCodeExclude("false");
        }
        
        // FCT030：仲介業者営業員リスト取得
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        InputFct030Dto fct030InData = new InputFct030Dto();
        fct030Dto = fct030.getData(fct030InData);
        selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        
        // SQL001実行
        DataList<IfaFxTradeHistorySql001ResponseModel> selSql001Res = dao.selectIfaFxTradeHistorySql001(selSql001Req);
        
        // 取得件数チェック
        errorInfo = new String[2];
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            // 為替取引履歴リストの件数が0件の場合：メッセージを表示し、処理終了
            errorInfo[0] = ERRORS_DATALIST_NOTFOUND;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.INFO, errorInfo[0], errorInfo[1]);
            
        } else if (selSql001Res.getDataList().get(0).getTotalCount() > MAX_ROW_NUM) {
            // 為替取引履歴リストの件数が5000件より大きい場合：ワーニングメッセージを作成
            int totalRow = selSql001Res.getDataList().get(0).getTotalCount();
            errorInfo[0] = WARNINGS_DATALIST_OVERMAXROWNUM;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                    new String[] { MAX_ROW_NUM_STR, String.valueOf(totalRow) });
            // 為替取引履歴リストの前から5000件をレスポンス対象とする
            selSql001Res.setDataList(selSql001Res.getDataList().subList(0, MAX_ROW_NUM));
            
        }
        
        // レスポンスDTOの作成
        List<IfaFxTradeHistoryFxTradeHistory> fxTradeHistoryList = new ArrayList<>();
        for (IfaFxTradeHistorySql001ResponseModel item : selSql001Res.getDataList()) {
            fxTradeHistoryList.add(convertSql001ResToDto(item));
        }
        res.setFxTradeHistoryList(fxTradeHistoryList);
        resDto.add(res);
        
        // ワーニングありの場合
        if (!ObjectUtils.isEmpty(errorInfo[0])) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.WARNING, errorInfo[0], errorInfo[1]);
        }
        
        dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        return dtoRes;
    }
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力（CSV作成）
     * Dto リクエスト：IfaFxTradeHistoryA004aDtoRequest
     * Dto レスポンス：IfaFxTradeHistoryA004aDtoResponse
     * model リクエスト：IfaFxTradeHistorySql001RequestModel
     * model レスポンス：IfaFxTradeHistorySql001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @param fwSessionId フレームワークセッションID
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaFxTradeHistoryA004aDtoResponse> csvOutputA004a(IfaFxTradeHistoryA004aDtoRequest dtoReq,
            String fwSessionId) throws Exception {
        
        DataList<IfaFxTradeHistoryA004aDtoResponse> dtoRes = new DataList<IfaFxTradeHistoryA004aDtoResponse>();
        List<IfaFxTradeHistoryA004aDtoResponse> resDto = new ArrayList<IfaFxTradeHistoryA004aDtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaFxTradeHistoryServiceImplL.csvOutputA004");
        }
        
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = null;
        
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // action内チェック処理
        errorInfo = checkValidation(dtoReq.getPeriodYmFrom(), dtoReq.getPeriodYmTo(), userAccount);
        if (!ObjectUtils.isEmpty(errorInfo)) {
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        
        // FCT038：CSVダウンロードMAX件数を取得する
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        // SQL001：為替取引履歴リストを取得する
        IfaFxTradeHistorySql001RequestModel selSql001Req = new IfaFxTradeHistorySql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setMaxRow(maxCsvRowNum);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 仲介業者除外フラグ
        if ("".equals(dtoReq.getChkBrokerCodeExclude())) {
            // チェックなしのとき
            selSql001Req.setChkBrokerCodeExclude("false");
        }
        
        // FCT030：仲介業者営業員リスト取得
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        InputFct030Dto fct030InData = new InputFct030Dto();
        fct030Dto = fct030.getData(fct030InData);
        selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        
        // SQL001実行
        DataList<IfaFxTradeHistorySql001ResponseModel> selSql001Res = dao.selectIfaFxTradeHistorySql001(selSql001Req);
        
        // 取得件数チェック
        errorInfo = new String[2];
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            // 為替取引履歴リストの件数が0件の場合：メッセージを表示し、処理終了
            errorInfo[0] = ERRORS_DATALIST_NOTFOUND;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return IfaCommonUtil.createDataList(resDto, ErrorLevel.INFO, errorInfo[0], errorInfo[1]);
            
        } else if (selSql001Res.getDataList().get(0).getTotalCount() > maxCsvRowNum) {
            // 為替取引履歴リストの件数がCSVダウンロードMAX件数より大きい場合：ワーニングメッセージを作成
            int totalRow = selSql001Res.getDataList().get(0).getTotalCount();
            errorInfo[0] = WARNINGS_DATALIST_CSV_OVERMAXROWNUM;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { String.valueOf(maxCsvRowNum),
                    String.valueOf(totalRow), String.valueOf(maxCsvRowNum) });
            // 為替取引履歴リストの前からCSVダウンロードMAX件数分をレスポンス対象とする
            selSql001Res.setDataList(selSql001Res.getDataList().subList(0, maxCsvRowNum));
            
        }
        
        if (!ObjectUtils.isEmpty(errorInfo[0])) {
            // ワーニングありの場合
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.WARNING, errorInfo[0], errorInfo[1]);
        } else {
            // ワーニングなしの場合
            dtoRes = IfaCommonUtil.createDataList(resDto, ErrorLevel.SUCCESS, SUCCESS_MESSAGE_CODE, null);
        }
        
        // CSVファイル用のデータを作成する
        IfaFxTradeHistoryCsvOut csvOut = new IfaFxTradeHistoryCsvOut(complianceService);
        DataList<IfaFxTradeHistoryCsvItems> exportList = new DataList<IfaFxTradeHistoryCsvItems>();
        exportList.setDataList(setCsvItems(selSql001Res));
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null));
        dtoRes.setTotalSize(selSql001Res.getDataList().size());

        return dtoRes;
    }
    
    /**
     * action内チェック処理
     *
     * @param periodYmFrom 期間指定（From）
     * @param periodYmTo 期間指定（To）
     * @param userAccount ユーザ共通情報
     * @return エラー情報
     */
    private String[] checkValidation(String periodYmFrom, String periodYmTo, UserAccount userAccount) {
        
        String errorCode = StringUtil.EMPTY_STRING;
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う
        LocalDate periodFrom = LocalDate.parse(periodYmFrom, DateTimeFormatter.ofPattern(DATE_FORM));
        LocalDate periodTo = LocalDate.parse(periodYmTo, DateTimeFormatter.ofPattern(DATE_FORM));
        
        if (periodTo.minusMonths(6).compareTo(periodFrom) > 0) {
            // 期間指定のエラーメッセージとコードを設定
            errorCode = ERRORS_DATERANGE;
            errorMessage = IfaCommonUtil.getMessage(errorCode, new String[] { DATERANGE_0, DATERANGE_1 });
            return new String[] { errorCode, errorMessage };
        }
        
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合　：　FCT030チェック
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())
                    || fct030Dto.getBrokerChargeList().size() == 0) {
                errorCode = ERRORS_CMN_IFAAGENTCODES_NOTEXIST;
                errorMessage = IfaCommonUtil.getMessage(errorCode);
                return new String[] { errorCode, errorMessage };
            }
        }
        
        // エラーなしの場合はnullを返却
        return null;
    }
    
    /**
     * SQL001の取得データをレスポンスDTOに詰め替える
     *
     * @param item SQL001取得データ
     * @return レスポンスDTOの為替取引履歴データ
     */
    private IfaFxTradeHistoryFxTradeHistory convertSql001ResToDto(IfaFxTradeHistorySql001ResponseModel item) {
        
        IfaFxTradeHistoryFxTradeHistory fxTradeHistory = new IfaFxTradeHistoryFxTradeHistory();
        
        // 仲介業者コード
        fxTradeHistory.setBrokerCode(item.getBrokerCode());
        // 仲介業者名
        fxTradeHistory.setBrokerName(item.getBrokerName());
        // 営業員コード
        fxTradeHistory.setEmpCode(item.getBrokerChargeCode());
        // 営業員名
        fxTradeHistory.setBrokerChargeName(item.getBrokerChargeName());
        // 部店
        fxTradeHistory.setButen(item.getButenCode());
        // 口座番号
        fxTradeHistory.setAccountNumber(item.getAccountNumber());
        // 扱者コード
        fxTradeHistory.setDealerNumber(item.getDealerNumber());
        // コース名
        fxTradeHistory.setCourseName(item.getCustomerAttributeName());
        // 顧客名(漢字)
        fxTradeHistory.setCustomerNameKanji(item.getNameKanji());
        // 顧客名(カナ)
        fxTradeHistory.setCustomerNameKana(item.getNameKana());
        // 約定日
        fxTradeHistory.setTradeDate(item.getTradeDate());
        // 受渡日
        fxTradeHistory.setSettlementDate(item.getGetDate());
        // 取引種別名
        fxTradeHistory.setTradeTypeName(item.getTradeName());
        // 通貨
        fxTradeHistory.setCurrency(item.getCurrency());
        // 為替レート
        fxTradeHistory.setFxRate(item.getExchangeRate());
        // 為替スプレッド
        fxTradeHistory.setExchangeSpread(item.getExchangeSpreadPrice());
        // 円額
        fxTradeHistory.setYenAmount(item.getYenAmount());
        // 外貨額
        fxTradeHistory.setForeignAmount(item.getForeignAmount());
        // 為替手数料
        fxTradeHistory.setExchangeFee(item.getExchangeSpread());
        // 仲介業支店コード
        fxTradeHistory.setBrokerageBranchCode(item.getBrokerBranchCode());
        // 仲介業者支店名
        fxTradeHistory.setBrokerBranchName(item.getBranchName());
        
        return fxTradeHistory;
    }
    
    /**
     * CSV用データ項目編集
     *
     * @param sql001Res 為替取引履歴リスト
     * @return CSV用データ項目リスト
     * @throws Exception 例外発生時
     */
    private List<IfaFxTradeHistoryCsvItems> setCsvItems(DataList<IfaFxTradeHistorySql001ResponseModel> sql001Res)
            throws Exception {
        
        List<IfaFxTradeHistoryCsvItems> csvItemList = new ArrayList<>();
        
        for (IfaFxTradeHistorySql001ResponseModel sqlRes : sql001Res.getDataList()) {
            IfaFxTradeHistoryCsvItems csvItems = new IfaFxTradeHistoryCsvItems();
            
            // 仲介業者コード
            csvItems.setBrokerCode(sqlRes.getBrokerCode());
            // 仲介業者名
            csvItems.setBrokerName(sqlRes.getBrokerName());
            // 営業員コード
            csvItems.setEmpCode(sqlRes.getBrokerChargeCode());
            // 営業員名
            csvItems.setBrokerChargeName(sqlRes.getBrokerChargeName());
            // 部店
            csvItems.setButen(sqlRes.getButenCode());
            // 口座番号
            csvItems.setAccountNumber(sqlRes.getAccountNumber());
            // 扱者コード
            csvItems.setDealerNumber(sqlRes.getDealerNumber());
            // 取引コース
            csvItems.setCourseName(sqlRes.getCustomerAttributeName());
            // 顧客名(漢字)
            csvItems.setCustomerNameKanji(sqlRes.getNameKanji());
            // 顧客名(カナ)
            csvItems.setCustomerNameKana(sqlRes.getNameKana());
            // 約定日
            csvItems.setTradeDate(DateFormatUtil.convertDateString(sqlRes.getTradeDate()));
            // 受渡日
            csvItems.setSettlementDate(DateFormatUtil.convertDateString(sqlRes.getGetDate()));
            // 取引種別
            csvItems.setTradeTypeName(sqlRes.getTradeName());
            // 通貨
            csvItems.setCurrency(sqlRes.getCurrency());
            // 為替レート
            csvItems.setFxRate(sqlRes.getExchangeRate());
            // 為替スプレッド
            csvItems.setExchangeSpread(sqlRes.getExchangeSpreadPrice());
            // 円額
            csvItems.setYenAmount(sqlRes.getYenAmount());
            // 外貨額
            csvItems.setForeignAmount(sqlRes.getForeignAmount());
            // 為替手数料
            csvItems.setExchangeFee(sqlRes.getExchangeSpread());
            // 支店コード
            csvItems.setBrokerageBranchCode(sqlRes.getBrokerBranchCode());
            // 支店名
            csvItems.setBrokerBranchName(sqlRes.getBranchName());
            
            csvItemList.add(csvItems);
        }
        
        return csvItemList;
    }
    
    /**
     * 仲介業者コード編集
     *
     * @param brokerCode 仲介業者コード（複数指定の場合あり）
     * @return 仲介業者コードリスト
     */
    private List<String> splitBrokerCode(String brokerCode) {
        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
        }
        return brokerCodeList;
    }
}
