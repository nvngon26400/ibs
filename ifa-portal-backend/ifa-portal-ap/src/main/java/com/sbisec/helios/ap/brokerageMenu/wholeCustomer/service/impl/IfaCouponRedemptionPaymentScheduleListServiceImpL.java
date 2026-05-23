package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaCouponRedemptionPaymentScheduleListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaCouponRedemptionPaymentScheduleListService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaCouponRedemptionPaymentScheduleListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.DateFormatUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 * 2024/06/06 新規作成
 */
@Component(value = "cmpIfaCouponRedemptionPaymentScheduleListService")
public class IfaCouponRedemptionPaymentScheduleListServiceImpL
        implements IfaCouponRedemptionPaymentScheduleListService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaCouponRedemptionPaymentScheduleListServiceImpL.class);
    
    @Autowired
    private IfaCouponRedemptionPaymentScheduleListDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    
    
    /** エラーメッセージID：参照可能な仲介業者コード／営業員コードが存在しない */
    private static final String ERRORS_NO_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** エラーメッセージID：取得結果が0件（NULL）エラー */
    private static final String ERROES_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** エラーメッセージID：取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** ワーニングメッセージID：CSV取得件数上限ワーニング */
    private static final String WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    private static final String WARNING_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    /** SBI証券本店*/
    private static final String PRIVE_ID_SBI_HEADOFFICE = "1";
    
    /** 顧客名検索オプション：  "1"（前方一致） */
    private static final String CUSTOMER_NAME_SERCH_TYPE_PREFIX = "1";
    
    /** 顧客名検索オプション：  "2"（含む） */
    private static final String CUSTOMER_NAME_SERCH_TYPE_CONTAINS = "2";
    
    /** 取得件数上限 */
    private static final Integer MAX_ROWNUM = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0104-01";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA001DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA001DtoResponse
     * model リクエスト：IfaCouponRedemptionPaymentScheduleListA001RequestModel
     * model レスポンス：IfaCouponRedemptionPaymentScheduleListA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse> initializeA001(
            IfaCouponRedemptionPaymentScheduleListA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCouponRedemptionPaymentScheduleListServiceImpL.initializeA001");
        }
        
        DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse> dtoRes = new DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse>();
        IfaCouponRedemptionPaymentScheduleListA001DtoResponse dtoResData = new IfaCouponRedemptionPaymentScheduleListA001DtoResponse();
        
        // コードマスタより、利金・償還金支払予定一覧画面コメントを取得する。
        DataList<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel> selSql002Res = dao
                .selectIfaCouponRedemptionPaymentScheduleListSql002();
        if (selSql002Res != null && selSql002Res.getDataList().size() != 0) {
            dtoResData.setOneMonth10PercentDeclineBrandListComment(
                    selSql002Res.get(0).getOneMonth10PercentDeclineBrandListComment());
        } else {
            dtoResData.setOneMonth10PercentDeclineBrandListComment(null);
        }
        
        dtoRes = IfaCommonUtil.createDataList(Arrays.asList(dtoResData), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA002DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> displayA002(
            IfaCouponRedemptionPaymentScheduleListA002DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCouponRedemptionPaymentScheduleListServiceImpL.displayA002");
        }
        
        DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> dtoRes = new DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse>();
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        String privId = ua.getPrivId();
        OutputFct030Dto fct030Dto = null;
        
        // 参照可能な仲介業者コードチェック
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：
        if (!PRIVE_ID_SBI_HEADOFFICE.equals(privId)) {
            
            // FCT030を呼び出して、参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了
            if (fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_NO_EXIST));
                return dtoRes;
            }
        }
        
        // 利金償還金予定一覧取得（SQL001実行）
        DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selSql001Res = getPaymentList(dtoReq,
                privId, fct030Dto, MAX_ROWNUM);
        
        // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
        if (selSql001Res != null && selSql001Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERROES_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERROES_DATALIST_NOTFOUND));
            return dtoRes;
        }
        
        // SQL001.総件数が5000件より大きい（件数＞5000）の場合：メッセージを表示し、次の処理へ。
        if (selSql001Res != null && Integer.valueOf(selSql001Res.get(0).getTotalRow()) > MAX_ROWNUM) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.WARNING,
                    WARNINGS_DATALIST_OVER_MAX_ROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVER_MAX_ROWNUM,
                            new String[] { Integer.toString(MAX_ROWNUM), selSql001Res.get(0).getTotalRow() }));
        }
        
        // データをレスポンスにコピー
        List<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> dtoResDataList = setDtoResDataList(selSql001Res, MAX_ROWNUM);
        
        // 警告メッセージが未設定であればSUCCESSをセット
        if (dtoRes.getReturnCode() == null) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                    "");
        }
        
        dtoRes.setDataList(dtoResDataList);
        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA004DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA004DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse> csvOutputA004(
            IfaCouponRedemptionPaymentScheduleListA004DtoRequest dtoReq, String fwSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCouponRedemptionPaymentScheduleListServiceImpL.csvOutputA004");
        }
        
        DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse> dtoRes = new DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse>();
        
        // ユーザアカウント情報取得
        UserAccount ua = IfaCommonUtil.getUserAccount();
        String privId = ua.getPrivId();
        OutputFct030Dto fct030Dto = null;
        
        // 参照可能な仲介業者コードチェック
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：
        if (!PRIVE_ID_SBI_HEADOFFICE.equals(privId)) {
            
            // FCT030を呼び出して、参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了
            if (fct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_NO_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_NO_EXIST));
                return dtoRes;
            }
        }
        
        // CSVダウンロードMAX件数を取得する。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(privId);
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        // 利金償還金予定一覧取得（SQL001実行）
        DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selSql001Res = getPaymentList(dtoReq,
                privId, fct030Dto, maxCsvRowNum);
        
        // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
        if (selSql001Res != null && selSql001Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERROES_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERROES_DATALIST_NOTFOUND));
            return dtoRes;
        }
        
        Integer totalRow = Integer.valueOf(selSql001Res.get(0).getTotalRow());
        String msgTotalRow = Integer.toString(totalRow);
        
        // SQL001.総件数がCSVダウンロードMAX件数より大きい（件数＞CSVダウンロードMAX件数）場合：メッセージを表示し、次の処理へ
        if (selSql001Res != null && totalRow > maxCsvRowNum) {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.WARNING,
                    WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVER_MAX_ROWNUM, new String[] {
                            Integer.toString(maxCsvRowNum), msgTotalRow, Integer.toString(maxCsvRowNum) }));
            
            // 上記以外の場合：メッセージを表示し、次の処理へ。
        } else {
            dtoRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.WARNING, WARNING_DATALIST_CSV_OUTPUT,
                    IfaCommonUtil.getMessage(WARNING_DATALIST_CSV_OUTPUT, new String[] { msgTotalRow }));
        }
        
        // CSV出力する
        List<IfaCouponRedemptionPaymentScheduleListCsvItems> csvItemList = new ArrayList<>();
        
        for (IfaCouponRedemptionPaymentScheduleListSql001ResponseModel selSql001ResData : selSql001Res.getDataList()) {
            IfaCouponRedemptionPaymentScheduleListCsvItems csvItem = new IfaCouponRedemptionPaymentScheduleListCsvItems();
            BeanUtils.copyProperties(csvItem, selSql001ResData);
            
            // フォーマット変換
            // 予定利率 - 数値を ######.0000 形式に
            DecimalFormat decimalFormat = new DecimalFormat("######.0000");
            if (selSql001ResData.getNextInterest() != null) {
                BigDecimal nextInterestBigDecimal = new BigDecimal(selSql001ResData.getNextInterest());
                csvItem.setNextInterest(decimalFormat.format(nextInterestBigDecimal));
            }
            // 予定概算金額 - 数値を ######.0000 形式に
            if (selSql001ResData.getSchedulePrice() != null) {
                BigDecimal schedulePriceBigDecimal = new BigDecimal(selSql001ResData.getSchedulePrice());
                csvItem.setSchedulePrice(decimalFormat.format(schedulePriceBigDecimal));
            }
            // クーポン判定日 - 年月日をYYYY/MM/DD形式に
            if (selSql001ResData.getCouponDeterminationDate() != null) {
                csvItem.setCouponDeterminationDate(
                        DateFormatUtil.convertDateString(selSql001ResData.getCouponDeterminationDate()));
            }
            // KI有無設定
            if (Integer.valueOf(selSql001ResData.getKiCount()) > 0) {
                csvItem.setKiCount("●");
            } else {
                csvItem.setKiCount("");
            }

            // リストにセット
            csvItemList.add(csvItem);
            
            // 要素数が上限に達したらループを抜ける
            if (csvItemList.size() == maxCsvRowNum) {
                break;
            }
        }
        
        DataList<IfaCouponRedemptionPaymentScheduleListCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);
        
        // CSVファイル作成
        IfaCouponRedemptionPaymentScheduleListCsvOut csvOut = new IfaCouponRedemptionPaymentScheduleListCsvOut(complianceService);
        String csvFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, ua.getUserId(), null);

        // レスポンスにファイル名、取得件数を設定
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(totalRow);
        
        return dtoRes;
    }
    
    /**
     * 利金償還金予定一覧取得
     * SQL001リクエストを設定し、SQL001を実行。
     * レスポンスとしてSQL001の実行結果を返す。
     * 
     * @param <T>
     * @param dtoReq DTOリクエスト
     * @param privId 権限コード
     * @param fct030Dto FCT030の取得結果
     * @param rownum 最大取得件数
     * @return SQL001の実行結果
     * @throws Exception SQL001実行時の例外
     */
    private <T> DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> getPaymentList(T dtoReq,
            String privId, OutputFct030Dto fct030Dto, Integer rownum) throws Exception {
        
        // リクエストを設定
        IfaCouponRedemptionPaymentScheduleListSql001RequestModel selSql001Req = new IfaCouponRedemptionPaymentScheduleListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        
        // 権限コード
        selSql001Req.setPrivId(privId);
        
        // 顧客名にワイルドカードを設定
        if (CUSTOMER_NAME_SERCH_TYPE_PREFIX.equals(selSql001Req.getCustomerNameKanjiKanaTerms())) {
            selSql001Req.setCustomerNameKanjiKana(selSql001Req.getCustomerNameKanjiKana() + "%");
        } else if (CUSTOMER_NAME_SERCH_TYPE_CONTAINS.equals(selSql001Req.getCustomerNameKanjiKanaTerms())) {
            selSql001Req.setCustomerNameKanjiKana("%" + selSql001Req.getCustomerNameKanjiKana() + "%");
        }
        
        // 仲介業者・営業員コードリスト
        List<IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge> brokerChargeList = new ArrayList<IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge>();
        if (fct030Dto != null) {
            for (BrokerCharge fct030BrokerCharge : fct030Dto.getBrokerChargeList()) {
                IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge sql001BrokerCharge = new IfaCouponRedemptionPaymentScheduleListSql001RequestModelBrokerCharge();
                BeanUtils.copyProperties(sql001BrokerCharge, fct030BrokerCharge);
                brokerChargeList.add(sql001BrokerCharge);
            }
        }
        selSql001Req.setBrokerChargeList(brokerChargeList);
        
        // 上限件数
        selSql001Req.setRownum(Integer.toString(rownum));
        
        DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selSql001Res = dao
                .selectIfaCouponRedemptionPaymentScheduleListSql001(selSql001Req);
        
        return selSql001Res;
    }
    
    /**
     * SQL001の戻りをレスポンスにセット
     *
     * @param selSql001Res SQL001の実行結果
     * @param maxRowNum 最大取得件数
     * @return レスポンスのリスト
     * @throws Exception データコピー時の例外
     */
    private List<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> setDtoResDataList(
            DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selSql001Res, Integer maxRowNum) throws Exception {
     // データをレスポンスにコピー
        List<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> dtoResDataList = new ArrayList<>();
        
        
        for (IfaCouponRedemptionPaymentScheduleListSql001ResponseModel sql001ResData : selSql001Res.getDataList()) {
            
            IfaCouponRedemptionPaymentScheduleListA002DtoResponse dtoResData = new IfaCouponRedemptionPaymentScheduleListA002DtoResponse();
            
            BeanUtils.copyProperties(dtoResData, sql001ResData);
            dtoResDataList.add(dtoResData);
            
            // 要素数が上限に達したらループを抜ける
            if (dtoResDataList.size() == maxRowNum) {
                break;
            }
            
        }
        return dtoResDataList;
    }
}
