package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMutualFundPriceChangeBrandHoldingListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListDtoRequestCourseSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaMutualFundPriceChangeBrandHoldingListService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMutualFundPriceChangeBrandHoldingListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020301_03-01
 * 画面名：投信基準価額変動の銘柄保有一覧
 * @author <author-name>
 *
 * 2024/04/12 新規作成
 */
@Component(value = "cmpIfaMutualFundPriceChangeBrandHoldingListService")
public class IfaMutualFundPriceChangeBrandHoldingListServiceImpL
        implements IfaMutualFundPriceChangeBrandHoldingListService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaMutualFundPriceChangeBrandHoldingListServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 取引コースを選択してください。 */
    private static final String ERRORS_SELECTED = "errors.selected";
    
    /** {0}は{1}以内を正しく入力して下さい。 */
    private static final String ERRORS_DATERANGE = "errors.dateRange";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020301_03-01";

    /** A002_SELECTED_0 */
    private static final String A002_SELECTED = "取引コース";
    
    /** A002_DATERANGE_0 */
    private static final String A002_DATERANGE_0 = "期間指定";
    
    /** A002_DATERANGE_1 */
    private static final String A002_DATERANGE_1 = "6ヶ月";
    
    /** A002_MAXROWNUM_0 */
    private static final String A002_MAXROWNUM_0 = "5,000";
    
    /** A002_SQL001_0 */
    private static final int MAX_ROW_NUM = 5000;
    
    /** 円 */
    private static final String YEN = "円";
    
    /** % */
    private static final String PERCENT = "%";
    
    /** 権限コード：SBI証券本店 の場合 */
    private static final String AUTH_CODE_SBI = "1";
    
    /** 区分.下落率区分 */
    private static final String DECLINE_RATE_TYPE = "DECLINE_RATE_TYPE";
    
    /** 区分.対応ステータス区分 */
    private static final String COMPLIANT_STATUS_TYPE = "COMPLIANT_STATUS_TYPE";
    
    /** 区分.手段区分 */
    private static final String MEANS_TYPE = "MEANS_TYPE";
    
    /** 区分.対応方法その他内容 */
    private static final String METHOD_OTHER_CONTENTS = "METHOD_OTHER_CONTENTS";
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 日付フォーマット */
    private static final String DATE_FORM = "yyyy/MM/dd";
    
    // --------------------------------
    // メソッド
    // --------------------------------
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * FCT030処理
     */
    @Autowired
    private Fct030 fct030;
    
    /**
     * FCT038処理
     */
    @Autowired
    private Fct038 fct038;
    
    /**
     * SQL001
     */
    @Autowired
    private IfaMutualFundPriceChangeBrandHoldingListDao dao;
    
    /**
     * 区分値変換
     */
    @Autowired
    private CodeListService codeListService;

    /**
     * 日付取得UTIL
     */
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMutualFundPriceChangeBrandHoldingListCsvOutputA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002ApiRequest
     * APIレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse
     * Dtoリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002RequestDto
     * Dtoレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto
     *
     * 投信基準価額変動の銘柄保有一覧を表示するためのデータを取得する。
     *
     * @param apiReq 投信基準価額変動の銘柄保有一覧表示リクエスト情報
     * @return APIレスポンスデータ(JSON形式)
     * @throws Exception 例外発生時
     */
    @Override
    public DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto> displayA002(
            IfaMutualFundPriceChangeBrandHoldingListA002RequestDto dtoReq) throws Exception {
        
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto> dtoRes = new DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMutualFundPriceChangeBrandHoldingListServiceImplL.displayA002");
        }

        // エラーメッセージ,エラーコード
        String errorMessage = null;
        String errorCode = null;

        // ① リクエスト.取引コースのチェックを行う。
        if (!validationCheck(dtoReq.getCourse())) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { A002_SELECTED });
            errorCode = ERRORS_SELECTED;
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        // ② リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        LocalDate periodFrom = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern(DATE_FORM));
        LocalDate periodTo = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern(DATE_FORM));
        LocalDate sixMonthsAgo = ifaDateUtil.getCurrentLocalDate(ZoneId.of("UTC+09:00")).minusMonths(6);
        
        if (periodTo.minusMonths(6).compareTo(periodFrom) > 0 || sixMonthsAgo.compareTo(periodFrom) > 0
                || sixMonthsAgo.compareTo(periodTo) > 0) {
            // 期間指定のエラーメッセージとコードを設定
            errorMessage = IfaCommonUtil.getMessage(ERRORS_DATERANGE,
                    new String[] { A002_DATERANGE_0, A002_DATERANGE_1 });
            errorCode = ERRORS_DATERANGE;
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        // ③ ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // FCT030 参照可能チェック
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())
                    || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST, new String[] {}));
            }
        }
        
        // ④ 投信基準価額変動情報リストを取得する: SQL001       
        // 検索条件を設定
        IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel selSql001Req = new IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setMaxRow(MAX_ROW_NUM);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        if ("$NULL".equals(dtoReq.getStatus())) {
            selSql001Req.setStatus("");
        }
        
        // 仲介業者除外フラグ
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        
        // 仲介業者コード
        if (!StringUtil.isNullOrEmpty(dtoReq.getBrokerCode())) {
            List<String> brokerCodeList = new ArrayList<String>();
            brokerCodeList.addAll(Arrays.asList(dtoReq.getBrokerCode().split(",")));
            selSql001Req.setBrokerCodeList(brokerCodeList);
        }
        
        //　SQL001実行
        DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> selSql001Res = dao
                .selectIfaMutualFundPriceChangeBrandHoldingListSql001(selSql001Req);
        
        // 取得件数チェック
        // 投信基準価額変動情報リストの件数が0件の場合：メッセージを表示し、処理終了。
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            // 投信基準価額変動情報リストの件数が5000件より大きい場合：メッセージを表示し、次の処理へ。    
        } else if (selSql001Res.get(0).getTotalRow() > MAX_ROW_NUM) {
            // SQL001から取得した総数を取得
            int totalRow = selSql001Res.get(0).getTotalRow();
            errorMessage = IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                    new String[] { A002_MAXROWNUM_0, String.valueOf(totalRow) });
            errorCode = WARNINGS_DATALIST_OVERMAXROWNUM;
        }
        
        // ⑤ 投信基準価額変動情報リストを日比5%下落銘柄リストと1ヶ月10%下落銘柄リストに分ける。
        List<IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList> daily5PercentDeclineDtoList = new ArrayList<>();
        List<IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList> monthly10PercentDeclineDtoList = new ArrayList<>();
        
        for (IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel item : selSql001Res.getDataList()) {
            if ("0".equals(item.getDeclineRateKbn())) { // 前日比5%下落
                daily5PercentDeclineDtoList.add(convertToCompare5PercentDeclineDto(item));
            } else if ("1".equals(item.getDeclineRateKbn())) { // 1か月10%下落
                monthly10PercentDeclineDtoList.add(convertToOneMonth10PercentDeclineDto(item));
            }
        }
        
        // 応答DTOを初期化
        IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto responseDto = new IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto();
        // 応答DTOにリストをセット
        responseDto.setCompare5ParcentDeclineBrandList(daily5PercentDeclineDtoList);
        responseDto.setOneMonth10PercentDeclineBrandList(monthly10PercentDeclineDtoList);
        
        if (errorMessage != null) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, errorCode, errorMessage);
        } else {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }
        dtoRes.setDataList(Arrays.asList(responseDto));
        
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto
     * Dto レスポンス：IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto
     * model リクエスト：IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel
     * model レスポンス：IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel
     * @param dtoReq 投信基準価額変動の銘柄保有一覧CSVリクエスト情報
     * @param fwSessionId フレームワークセッションID
     * @return 投信基準価額変動の銘柄保有一覧CSVレスポンス情報
     * @throws Exception 例外発生時
     */
    @Override
    public DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto> csvOutputA004a(
            IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto> dtoRes = new DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaMutualFundPriceChangeBrandHoldingListServiceImplL.csvOutputA004a");

        // エラーメッセージ,エラーコード
        String errorMessage = null;
        String errorCode = null;
        
        // ① A002の①～③の処理を行う。
        // A002_① リクエスト.取引コースのチェックを行う。
        if (!validationCheck(dtoReq.getCourse())) {
            errorMessage = IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { A002_SELECTED });
            errorCode = ERRORS_SELECTED;
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        // A002_② リクエスト.期間指定（From）、リクエスト.期間指定（To）のチェックを行う。
        LocalDate periodFrom = LocalDate.parse(dtoReq.getPeriodYmFrom(), DateTimeFormatter.ofPattern(DATE_FORM));
        LocalDate periodTo = LocalDate.parse(dtoReq.getPeriodYmTo(), DateTimeFormatter.ofPattern(DATE_FORM));
        LocalDate sixMonthsAgo = ifaDateUtil.getCurrentLocalDate(ZoneId.of("UTC+09:00")).minusMonths(6);
        
        if (periodTo.minusMonths(6).compareTo(periodFrom) > 0 || sixMonthsAgo.compareTo(periodFrom) > 0
                || sixMonthsAgo.compareTo(periodTo) > 0) {
            // 期間指定のエラーメッセージとコードを設定
            errorMessage = IfaCommonUtil.getMessage(ERRORS_DATERANGE,
                    new String[] { A002_DATERANGE_0, A002_DATERANGE_1 });
            errorCode = ERRORS_DATERANGE;
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorCode, errorMessage);
        }
        
        // A002_③ ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        // 仲介業者営業員リスト
        OutputFct030Dto fct030Dto = new OutputFct030Dto();
        // ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // FCT030 参照可能チェック
        if (!StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            if (CollectionUtils.isEmpty(fct030Dto.getBrokerChargeList())
                    || fct030Dto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST, new String[] {}));
            }
        }
        
        // ② CSVダウンロードMAX件数を取得する。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        // ③ 投信基準価額変動情報リストを取得する。
        // SQL001
        IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel selSql001Req = new IfaMutualFundPriceChangeBrandHoldingListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setMaxRow(maxCsvRowNum);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setBrokerChargeList(fct030Dto.getBrokerChargeList());
        if ("$NULL".equals(dtoReq.getStatus())) {
            selSql001Req.setStatus("");
        }

        // 仲介業者除外フラグ
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        
        // 仲介業者コード
        if (!StringUtil.isNullOrEmpty(dtoReq.getBrokerCode())) {
            List<String> brokerCodeList = new ArrayList<String>();
            brokerCodeList.addAll(Arrays.asList(dtoReq.getBrokerCode().split(",")));
            selSql001Req.setBrokerCodeList(brokerCodeList);
        }
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> selSql001Res = dao
                .selectIfaMutualFundPriceChangeBrandHoldingListSql001(selSql001Req);
        
        // 件数チェック
        // 投信基準価額変動情報リストの件数が0件の場合：メッセージを表示し、処理終了。
        // 投信基準価額変動情報リストの件数がCSVダウンロードMAX件数より大きい場合：メッセージを表示し、次の処理へ。
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
        }
        
        //　④ CSVファイルを作成する。
        // CSVファイル用のデータを作成
        IfaMutualFundPriceChangeBrandHoldingListCsvOut csvOut = new IfaMutualFundPriceChangeBrandHoldingListCsvOut(
                complianceService);
        DataList<IfaMutualFundPriceChangeBrandHoldingListCsvItems> exportList = new DataList<IfaMutualFundPriceChangeBrandHoldingListCsvItems>();
        exportList.setDataList(setCsvItems(selSql001Res));
        
        int totalRow = selSql001Res.get(0).getTotalRow();
        
        if (maxCsvRowNum < totalRow) {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM, new String[] {
                            String.valueOf(maxCsvRowNum), String.valueOf(totalRow), String.valueOf(maxCsvRowNum) }));
        } else {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }
        
        // CSVファイル作成
        // レスポンスのタイトルにCSVファイル名を設定
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null));
        dtoRes.setTotalSize(totalRow);
        return dtoRes;
    }
    
    /**
     * 入力チェック
     * 
     * @param list 取引コード
     * @throws Exception 
     */
    private boolean validationCheck(List<IfaMutualFundPriceChangeBrandHoldingListDtoRequestCourseSelected> courseList) {
        
        // 取引コースの判定
        if (!courseList.isEmpty() && courseList.size() > 0) {
            for (IfaMutualFundPriceChangeBrandHoldingListDtoRequestCourseSelected course : courseList) {
                if (course.getIsSelected()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * CSV用データ項目編集
     * @param sql001Res 投信基準価額変動情報リスト
     * @return CSV用データ項目リスト
     * @throws Exception 例外発生時
     */
    private List<IfaMutualFundPriceChangeBrandHoldingListCsvItems> setCsvItems(
            DataList<IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel> sql001Res) throws Exception {
        
        List<IfaMutualFundPriceChangeBrandHoldingListCsvItems> csvItemsList = new ArrayList<>();
        DecimalFormat priceFormat = new DecimalFormat("###,##0");
        DecimalFormat percentageFormat = new DecimalFormat("###,##0.##");
        
        for (IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel sqlRes : sql001Res.getDataList()) {
            IfaMutualFundPriceChangeBrandHoldingListCsvItems csvItems = new IfaMutualFundPriceChangeBrandHoldingListCsvItems();
            
            // 下落率区分 (全角半角)
            csvItems.setDeclineRateKbn(codeListService.getValue(DECLINE_RATE_TYPE, sqlRes.getDeclineRateKbn()));
            
            // ステータス (全角半角)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.ステータス区分 を設定、それ以外は "-" を設定
            csvItems.setStatusKbn("0".equals(sqlRes.getDeclineRateKbn())
                    ? codeListService.getValue(COMPLIANT_STATUS_TYPE, sqlRes.getStatusKbn())
                    : "-");
            
            // 対応方法 (全角半角)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.対応方法区分 を設定、それ以外は "-" を設定
            csvItems.setMethodsKbn("0".equals(sqlRes.getDeclineRateKbn())
                    ? codeListService.getValue(MEANS_TYPE, sqlRes.getMethodsKbn())
                    : "-");
            
            // 「その他」の内容 (全角半角)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.その他内容区分 を設定、それ以外は "-" を設定
            csvItems.setOtherContentsKbn("0".equals(sqlRes.getDeclineRateKbn())
                    ? codeListService.getValue(METHOD_OTHER_CONTENTS, sqlRes.getOtherContentsKbn())
                    : "-");
            
            // 「その他」の詳細（自由記入） (全角半角)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.その他詳細 を設定、それ以外は "-" を設定
            csvItems.setOtherDetails(
                    "0".equals(sqlRes.getDeclineRateKbn()) ? StringUtils.defaultString(sqlRes.getOtherDetails()) : "-");
            
            // 顧客対応日 (YYYY/MM/DD)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.顧客対応日 を設定、それ以外は "-" を設定
            csvItems.setCustomerSupportDate(
                    "0".equals(sqlRes.getDeclineRateKbn()) && sqlRes.getCustomerSupportDate() != null
                            ? DateFormatUtil.dateFormatToSlash(sqlRes.getCustomerSupportDate())
                            : "-");
            
            // 対応完了確認日 (YYYY/MM/DD)
            // 条件: SQL001.下落率区分が '0' の場合は SQL001.対応完了確認日 を設定、それ以外は "-" を設定
            csvItems.setCompletionConfirmationDate(
                    "0".equals(sqlRes.getDeclineRateKbn()) && sqlRes.getCompletionConfirmationDate() != null
                            ? DateFormatUtil.dateFormatToSlash(sqlRes.getCompletionConfirmationDate())
                            : "-");
            
            // 部店 (半角英数字のみ)
            csvItems.setButenCode(sqlRes.getButenCode());
            // 口座番号 (半角英数字のみ)
            csvItems.setAccountNumber(sqlRes.getAccountNumber());
            // 取引コース　区分.契約締結前交付書面コード
            csvItems.setCustomerAttribute(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sqlRes.getCustomerAttribute()));
            // 顧客名_姓名(漢字)
            csvItems.setNameKanji(sqlRes.getNameKanji());
            // 顧客名_姓名(カナ) */
            csvItems.setNameKana(sqlRes.getNameKana());
            // 扱者コード
            csvItems.setDealerNumber(sqlRes.getDealerNumber());
            // 仲介業者コード 
            csvItems.setBrokerCode(sqlRes.getBrokerCode());
            // 仲介業者名             
            csvItems.setBranchName(sqlRes.getBranchName());
            // 仲介業支店コード            
            csvItems.setBrokerBranchCode(sqlRes.getBrokerBranchCode());
            // 仲介業者支店名            
            csvItems.setBrokerBranchName(sqlRes.getBrokerBranchName());
            // 仲介業者営業員コード             
            csvItems.setBrokerEmployeeCode(sqlRes.getBrokerEmployeeCode());
            // 仲介業者担当者名              
            csvItems.setBrokerChargeName(sqlRes.getBrokerChargeName());
            // 投信協会コード               
            csvItems.setToushinKyoukaiCode(sqlRes.getToushinKyoukaiCode());
            // 銘柄名                    
            csvItems.setToushinBrandName(sqlRes.getToushinBrandName());
            // 基準日（From）
            csvItems.setBaseDateFrom(DateFormatUtil.dateFormatToSlash(sqlRes.getBaseDateFrom()));
            // 基準価額（From）：フォーマットをカンマ編集なし
            csvItems.setBasePriceFrom(sqlRes.getBasePriceFrom());
            // 基準日（T）
            csvItems.setBaseDateTo(DateFormatUtil.dateFormatToSlash(sqlRes.getBaseDateTo()));
            // 基準価額（To）：フォーマットをカンマ編集なし
            csvItems.setBasePriceTo(sqlRes.getBasePriceTo());
            // 前日比：フォーマットをカンマ編集なし、単位"円"を付ける
            csvItems.setZenjitsuHi(sqlRes.getZenjitsuHi() + YEN);
            // 下落率：フォーマットをカンマ編集なし、単位"％"を付ける
            csvItems.setDeclineRate(sqlRes.getDeclineRate() + PERCENT);
            
            csvItemsList.add(csvItems);
        }
        return csvItemsList;
    }
    
    /**
     * 取得したデータリストをレスポンスDTOのリストに変換
     * @param 
     * @return 
     * @throws Exception 例外発生時
     */
    private IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList convertToCompare5PercentDeclineDto(
            IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel item) {
        
        IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList dto = new IfaMutualFundPriceChangeBrandHoldingList_compare5ParcentDeclineBrandList();
        
        // 下落率区分
        dto.setDeclineRateKbn(item.getDeclineRateKbn());
        // ステータス区分
        dto.setStatusClassification(item.getStatusKbn());
        // 対応方法区分
        dto.setResponseMethodClassification(item.getMethodsKbn());
        // その他内容区分
        dto.setOtherContentsClassification(item.getOtherContentsKbn());
        // その他詳細
        dto.setOtherDetail(item.getOtherDetails());
        // 顧客対応日
        dto.setCustomerResponseDate(item.getCustomerSupportDate());
        // 対応完了確認日
        dto.setResponseFinishConfirmDate(item.getCompletionConfirmationDate());
        // 部店コード
        dto.setButenCode(item.getButenCode());
        // 口座番号
        dto.setAccountNumber(item.getAccountNumber());
        // 契約締結前交付書面コード
        dto.setCustomerAttribute(item.getCustomerAttribute());
        // 顧客名_姓名(漢字)
        dto.setCustomerNameKanji(item.getNameKanji());
        // 顧客名_姓名(カナ)
        dto.setCustomerNameKana(item.getNameKana());
        // 扱者コード
        dto.setDealerNumber(item.getDealerNumber());
        // 仲介業者コード
        dto.setBrokerCode(item.getBrokerCode());
        // 仲介業者名
        dto.setBrokerName(item.getBranchName());
        // 仲介業支店コード
        dto.setBrokerageBranchCode(item.getBrokerBranchCode());
        // 仲介業者支店名
        dto.setBrokerBranchName(item.getBrokerBranchName());
        // 仲介業者営業員コード
        dto.setBrokerChargeCode(item.getBrokerEmployeeCode());
        // 仲介業者担当者名
        dto.setEmployeeName(item.getBrokerChargeName());
        // 投信協会コード
        dto.setInvestmentTrustAssociationCode(item.getToushinKyoukaiCode());
        // 投信銘柄名
        dto.setMutualFundBrandName(item.getToushinBrandName());
        // 基準日（To）
        dto.setStandardDateTo(item.getBaseDateTo());
        // 基準価額（To）
        dto.setBasePriceTo(item.getBasePriceTo());
        // 前日比
        dto.setDiff(item.getZenjitsuHi());
        // 下落率
        dto.setRateOfDecline(item.getDeclineRate());
        return dto;
    }
    
    /**
     * 取得したデータリストをレスポンスDTOのリストに変換
     * @param 
     * @return 
     * @throws Exception 例外発生時
     */
    private IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList convertToOneMonth10PercentDeclineDto(
            IfaMutualFundPriceChangeBrandHoldingListSql001ResponseModel item) {
        
        IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList dto = new IfaMutualFundPriceChangeBrandHoldingList_oneMonth10PercentDeclineBrandList();
        
        // 下落率区分
        dto.setDeclineRateKbn(item.getDeclineRateKbn());
        // 部店コード
        dto.setButenCode(item.getButenCode());
        // 口座番号
        dto.setAccountNumber(item.getAccountNumber());
        // 契約締結前交付書面コード
        dto.setCustomerAttribute(item.getCustomerAttribute());
        // 顧客名_姓名(漢字)
        dto.setCustomerNameKanji(item.getNameKanji());
        // 顧客名_姓名(カナ)
        dto.setCustomerNameKana(item.getNameKana());
        // 扱者コード
        dto.setDealerNumber(item.getDealerNumber());
        // 仲介業者コード
        dto.setBrokerCode(item.getBrokerCode());
        // 仲介業者名
        dto.setBrokerName(item.getBranchName());
        // 仲介業支店コード
        dto.setBrokerageBranchCode(item.getBrokerBranchCode());
        // 仲介業者支店名
        dto.setBrokerBranchName(item.getBrokerBranchName());
        // 仲介業者営業員コード
        dto.setBrokerChargeCode(item.getBrokerEmployeeCode());
        // 仲介業者担当者名
        dto.setEmployeeName(item.getBrokerChargeName());
        // 投信協会コード
        dto.setInvestmentTrustAssociationCode(item.getToushinKyoukaiCode());
        // 投信銘柄名
        dto.setMutualFundBrandName(item.getToushinBrandName());
        // 基準日（From）
        dto.setStandardDateFrom(item.getBaseDateFrom());
        // 基準価額（From）
        dto.setBasePriceFrom(item.getBasePriceFrom());
        // 基準日（To）
        dto.setStandardDateTo(item.getBaseDateTo());
        // 基準価額（To）
        dto.setBasePriceTo(item.getBasePriceTo());
        // 前日比
        dto.setDiff(item.getZenjitsuHi());
        // 下落率
        dto.setRateOfDecline(item.getDeclineRate());
        return dto;
    }
}
