package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002TradeHistoryResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryCommonRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerTradeHistoryCsvItems;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaCustomerTradeHistoryService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaCustomerTradeHistoryCsvOut;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0202_0109-01
 * 画面名：取引履歴（顧客別）
 * 2025/07/24 新規作成
 *
 * @author SCSK
 */
@Component(value = "cmpIfaCustomerTradeHistoryService")
public class IfaCustomerTradeHistoryServiceImpL implements IfaCustomerTradeHistoryService {

    /** エラー.入力した部店口座は存在しません。<br>部店: [{0}]、口座: [{1}] */
    public static final String ERRORS_BUTEN_ACCOUNT_NOTEXISTS = "errors.butenAccountNotExist";

    /** エラー.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** ワーニング."検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** 権限チェックエラー値 「権限なし」 */
    public static final String TARGET_CUSTOMER_REF_AUTH_FLAG = "0";

    /** 取引区分：信用取引系（"D"） */
    public static final String TRADE_SEARCH_CODE_MARGIN_TRADE = "D";

    /** 実行パターン：1 */
    public static final String EX_PATTERN_1 = "1";

    /** 実行パターン：2 */
    public static final String EX_PATTERN_2 = "2";

    /** 実行パターン：3 */
    public static final String EX_PATTERN_3 = "3";

    /** FCT038.画面ID */
    private static final String FCT038_SCREEN_ID = "SUB0202_0109-01";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerTradeHistoryServiceImpL.class);

    @Autowired
    private Fct001 fct001;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private IfaCustomerTradeHistoryDao dao;

    @Autowired
    private ComplianceService complianceService;


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerTradeHistoryA001RequestDto
     * Dto レスポンス：IfaCustomerTradeHistoryA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA001ResponseDto> initializeA001(
            IfaCustomerTradeHistoryA001RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerTradeHistoryServiceImplL.initializeA001");
        }
        
        /* =============================================================================== */
        /* 1. 利用者の口座に対する権限チェックを行う。
        /* =============================================================================== */

        // 顧客共通情報
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        // Fct001
        InputFct001Dto inptFct001Dto = new InputFct001Dto();
        inptFct001Dto.setButenCode(cc.getButenCode());
        inptFct001Dto.setAccountNumber(cc.getAccountNumber());
        OutputFct001Dto outFct001Dto = fct001.doCheck(inptFct001Dto);
        
        // Fct001　利用者の口座に対する権限チェックを行う
        if (outFct001Dto == null || StringUtils.equals(outFct001Dto.getTargetCustomerRefAuthFlag(), TARGET_CUSTOMER_REF_AUTH_FLAG)) {
            // 権限なし：権限なしエラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_BUTEN_ACCOUNT_NOTEXISTS,
                    IfaCommonUtil.getMessage(ERRORS_BUTEN_ACCOUNT_NOTEXISTS, new String[] { cc.getButenCode(), cc.getAccountNumber() }));
        }

        return IfaCommonUtil.createDataList(List.of(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
    }

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerTradeHistoryA002RequestDto
     * Dto レスポンス：IfaCustomerTradeHistoryA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA002ResponseDto> displayA002(
            IfaCustomerTradeHistoryA002RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerTradeHistoryServiceImplL.displayA002");
        }

        /* =============================================================================== */
        /* SQLリクエストパラメータを設定する。
        /* =============================================================================== */
        IfaCustomerTradeHistorySql001RequestModel sql001RequestModel = new IfaCustomerTradeHistorySql001RequestModel();
        
        setSql001RequestModel(sql001RequestModel, dtoReq);

        // 最大取得件数に5,000件をセット
        sql001RequestModel.setMaxRowNum(5000);

        /* =============================================================================== */
        /* 取引履歴明細情報を検索する。
        /* =============================================================================== */

        Pair<DataList<IfaCustomerTradeHistoryA002ResponseDto>, List<IfaCustomerTradeHistorySql001ResponseModel>> sql001Res
                = getTradeHistoryList(sql001RequestModel, false);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaCustomerTradeHistoryA002ResponseDto> checkSql001Result = sql001Res.getLeft();
        if (checkSql001Result != null && StringUtils.equals(checkSql001Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)) {
            return checkSql001Result;
        }

        // レスポンスに取引履歴明細をセット
        List<IfaCustomerTradeHistoryA002TradeHistoryResponseDto> tradeHistoryList = new ArrayList<>();
        for (IfaCustomerTradeHistorySql001ResponseModel record : sql001Res.getRight()) {
            IfaCustomerTradeHistoryA002TradeHistoryResponseDto tradeHistory = new IfaCustomerTradeHistoryA002TradeHistoryResponseDto();
            BeanUtils.copyProperties(tradeHistory, record);
            tradeHistoryList.add(tradeHistory);
        }
        IfaCustomerTradeHistoryA002ResponseDto innerData = new IfaCustomerTradeHistoryA002ResponseDto();
        innerData.setTradeHistoryList(tradeHistoryList);
        
        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */
        DataList<IfaCustomerTradeHistoryA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        // SQL001でワーニングが設定されていればレスポンスにセット
        if (checkSql001Result != null) {
            dtoRes.setErrorLevel(checkSql001Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001Result.getReturnCode());
            dtoRes.setMessage(checkSql001Result.getMessage());
        }

        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerTradeHistoryA004RequestDto
     * Dto レスポンス：IfaCustomerTradeHistoryA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCustomerTradeHistoryA004aResponseDto> csvOutputA004a(
            IfaCustomerTradeHistoryA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerTradeHistoryServiceImplL.csvOutputA004");
        }

        /* =============================================================================== */
        /* SQLリクエストパラメータを設定する。
        /* =============================================================================== */
        IfaCustomerTradeHistorySql001RequestModel sql001RequestModel = new IfaCustomerTradeHistorySql001RequestModel();
        
        setSql001RequestModel(sql001RequestModel, dtoReq);
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        final int csvDownloadMaxRowNum =  getCsvDownloadMaxRowNum(userAccount.getPrivId());

        // 最大取得件数に5,000件をセット
        sql001RequestModel.setMaxRowNum(csvDownloadMaxRowNum);

        /* =============================================================================== */
        /* 取引履歴明細情報を検索する。
        /* =============================================================================== */

        Pair<DataList<IfaCustomerTradeHistoryA004aResponseDto>, List<IfaCustomerTradeHistorySql001ResponseModel>> sql001Res
                = getTradeHistoryList(sql001RequestModel, false);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaCustomerTradeHistoryA004aResponseDto> checkSql001Result = sql001Res.getLeft();
        if (checkSql001Result != null && StringUtils.equals(checkSql001Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)) {
            return checkSql001Result;
        }

        // CSV出力の内容に取引履歴明細をセット
        List<IfaCustomerTradeHistoryCsvItems> csvItemList = new ArrayList<>();
        for (IfaCustomerTradeHistorySql001ResponseModel record : sql001Res.getRight()) {
            IfaCustomerTradeHistoryCsvItems csvItem = new IfaCustomerTradeHistoryCsvItems();
            BeanUtils.copyProperties(csvItem, record);
            csvItemList.add(csvItem);
        }
        DataList<IfaCustomerTradeHistoryCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);

        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */

        // CSVファイルを作成
        String userId = userAccount.getUserId();
        IfaCustomerTradeHistoryCsvOut csvOut = new IfaCustomerTradeHistoryCsvOut(complianceService);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, userId, null);
        
        // レスポンスをセット
        DataList<IfaCustomerTradeHistoryA004aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        dtoRes.setTitle(csvTmpFileName);
        dtoRes.setTotalSize(sql001Res.getRight().get(0).getTotalRow());
        dtoRes.setMaxRownum(csvDataList.size());

        // SQL001でワーニングが設定されていればレスポンスにセット
        if (checkSql001Result != null) {
            dtoRes.setErrorLevel(checkSql001Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001Result.getReturnCode());
            dtoRes.setMessage(checkSql001Result.getMessage());
            dtoRes.setOverMaxRownum(checkSql001Result.isOverMaxRownum());
        }

        return dtoRes;

    }

    /**
     * FCT038
     * CSVダウンロードMAX件数を取得する
     *
     * @return Pair<エラー判定, FCT038レスポンス>
     * @exception Exception システムエラー
     */
    private int getCsvDownloadMaxRowNum(String privId) {

        InputFct038Dto fct038InputDto = new InputFct038Dto();
        fct038InputDto.setScreenId(FCT038_SCREEN_ID);
        fct038InputDto.setUserAuthority(privId);
        OutputFct038Dto fct038OutputDto = fct038.getData(fct038InputDto);

        return fct038OutputDto.getCsvDownloadNum();
    }

    /**
     * SQL001
     * SQLリクエストパラメータをセットする。
     *
     * @param sql001RequestModel SQL001リクエストDto
     * @param dtoReq A002,A004リクエストDto
     * @exception Exception システムエラー
     */
    private void setSql001RequestModel(
        IfaCustomerTradeHistorySql001RequestModel sql001RequestModel,
        IfaCustomerTradeHistoryCommonRequestDto dtoReq
    ) throws Exception {

        BeanUtils.copyProperties(sql001RequestModel, dtoReq);

        /* =============================================================================== */
        /* SQLの銘柄コードの条件を構成する。
        /* =============================================================================== */

        String brandCode = "";

        // リクエスト.会社コードが設定済みの場合：銘柄コード＝リクエスト.会社コード
        // 空白文字を許容する
        if (dtoReq.getCompanyCode() != null && !dtoReq.getCompanyCode().isEmpty()) {
             brandCode = dtoReq.getCompanyCode();
        }

        // リクエスト.回数が設定済みの場合：リクエスト.回数の桁数は4以下の場合：先頭に" "を追加して、4桁まで補足する。
        if (!StringUtil.isNullOrEmpty(dtoReq.getTimes())) {
             brandCode += "." + StringUtil.fillLeft(dtoReq.getTimes(), ' ', 4);
        }

        // リクエスト.号が設定済みの場合：銘柄コード　+＝　”.”+　リクエスト.号
        if (!StringUtil.isNullOrEmpty(dtoReq.getIssue())) {
             brandCode += "." + dtoReq.getIssue();
        }

        sql001RequestModel.setBrandCode(brandCode);
        
        /* =============================================================================== */
        /* SQLの商品検索ｺｰﾄﾞの条件を構成する。
        /* =============================================================================== */

        // リクエスト.商品区分が設定済みの場合：
        if (!StringUtil.isNullOrEmpty(dtoReq.getSecurityType())) {
             if (dtoReq.getSecurityType().length() == 1) { // リクエスト.商品区分の桁数＝1　の場合
                // 商品検索ｺｰﾄﾞ(大分類)＝リクエスト.商品区分
                sql001RequestModel.setSecuritySearchCodeBigClass(dtoReq.getSecurityType());
             } else if(dtoReq.getSecurityType().length() == 3) { // リクエスト.商品区分の桁数＝3　の場合
                // 商品検索ｺｰﾄﾞ(中分類)＝リクエスト.商品区分
                sql001RequestModel.setSecuritySearchCodeMidClass(dtoReq.getSecurityType());
             } else if(dtoReq.getSecurityType().length() == 5) { // リクエスト.商品区分の桁数＝5　の場合
                // 商品検索ｺｰﾄﾞ(小分類)＝リクエスト.商品区分
                sql001RequestModel.setSecuritySearchCodeSmallClass(dtoReq.getSecurityType());
             }
        }

        /* =============================================================================== */
        /* SQLの取引検索ｺｰﾄﾞの条件を構成する。
        /* =============================================================================== */

        // リクエスト.取引区分が設定済みの場合：
        if (!StringUtil.isNullOrEmpty(dtoReq.getTradeType())) {
             if (dtoReq.getTradeType().length() == 1) { // リクエスト.取引区分の桁数＝1　の場合
                // 取引検索ｺｰﾄﾞ(大分類)＝リクエスト.取引区分
                sql001RequestModel.setTradeSearchCodeBigClass(dtoReq.getTradeType());
             } else if(dtoReq.getTradeType().length() == 2) { // リクエスト.取引区分の桁数＝2　の場合
                // 取引検索ｺｰﾄﾞ(中分類)＝リクエスト.取引区分
                sql001RequestModel.setTradeSearchCodeMidClass(dtoReq.getTradeType());
             } else if(dtoReq.getTradeType().length() == 3) { // リクエスト.取引区分の桁数＝3　の場合
                // 取引検索ｺｰﾄﾞ(小分類)＝リクエスト.取引区分
                sql001RequestModel.setTradeSearchCodeSmallClass(dtoReq.getTradeType());
             }
        }

        /* =============================================================================== */
        /* SQLの実行パターンを設定する。
        /* =============================================================================== */

        if (StringUtil.isNullOrEmpty(dtoReq.getTradeType())) { // リクエスト.取引区分が未設定の場合
            // 実行パターン：3
            sql001RequestModel.setExPattern(EX_PATTERN_3);
        } else { // リクエスト.取引区分が設定済みの場合
            if (dtoReq.getTradeType().startsWith(TRADE_SEARCH_CODE_MARGIN_TRADE)) { // リクエスト.取引区分の1桁目が”D”の場合
                // 実行パターン：2
                sql001RequestModel.setExPattern(EX_PATTERN_2);
            } else { // リクエスト.取引区分の1桁目が”D”以外の場合
                // 実行パターン：1
                sql001RequestModel.setExPattern(EX_PATTERN_1);
            }
        }
    }

    /**
     * SQL001
     * 取引履歴明細情報を取得する。
     *
     * @param selSql001Req SQL001リクエスト
     * @param isCsvMode A002表示：false, A004 CSV出力：true
     * @return 取引履歴明細情報
     * @exception Exception システムエラー
     */
    private <T> Pair<DataList<T>, List<IfaCustomerTradeHistorySql001ResponseModel>> getTradeHistoryList(
            IfaCustomerTradeHistorySql001RequestModel selSql001Req,
            boolean isCsvMode
    ) throws Exception {

        // SQL001の呼び出し
        DataList<IfaCustomerTradeHistorySql001ResponseModel> selSql001Res = dao.selectIfaCustomerTradeHistorySql001(selSql001Req);
        
        // 結果が0件の場合、0件エラー
        if (selSql001Res == null || selSql001Res.size() == 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND)
            );

            return ImmutablePair.of(dtoRes, null);
        }

        // 総件数が5000件を超過している場合ワーニング
        DataList<T> exceedMaxRow = null;
        if (selSql001Req.getMaxRowNum() < selSql001Res.get(0).getTotalRow()) {
            if (isCsvMode == false) { // A002 表示の場合
                exceedMaxRow = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.WARNING,
                        WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                        IfaCommonUtil.getMessage(
                                WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                                new String[] {
                                    String.format("%,d", selSql001Req.getMaxRowNum()),
                                    String.format("%,d", selSql001Res.get(0).getTotalRow())
                                }
                        )
                );
            } else {  // A004 CSV出力の場合
                exceedMaxRow = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.WARNING,
                    WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                            new String[] {
                                String.format("%,d", selSql001Req.getMaxRowNum()),
                                String.format("%,d", selSql001Res.get(0).getTotalRow()),
                                String.format("%,d", selSql001Req.getMaxRowNum())
                            }
                    )
                );
                exceedMaxRow.setOverMaxRownum(true);
            }
        }

        return ImmutablePair.of(exceedMaxRow, selSql001Res.getDataList());
    }
}
