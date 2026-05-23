package com.sbisec.helios.ap.wholePortal.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct007;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct007Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.wholePortal.dao.IfaCustomerAlertCsvOutputDao;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql001ResponseModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaCustomerAlertCsvOutputSql002ResponseModel;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputCustomerAlertNotification;
import com.sbisec.helios.ap.wholePortal.service.IfaCustomerAlertCsvOutputService;
import com.sbisec.helios.ap.wholePortal.util.IfaCustomerAlertCsvOutputCsvOut;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
@Component(value = "cmpIfaCustomerAlertCsvOutputService")
public class IfaCustomerAlertCsvOutputServiceImpL implements IfaCustomerAlertCsvOutputService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerAlertCsvOutputServiceImpL.class);
    
    /** エラーメッセージID: errors.cmn.ifaAgentCodes.notExist */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** エラーメッセージID: errors.dataList.notfound */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 警告メッセージID: warnings.dataList.csv.overMaxRownum */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB01-02";
    
    /** アラート分類ID 6:決済期日 */
    private static final int ALERT_CATEGORY_ID_SETTLEMENT_DUE_DATE = 6;
    
    /** アラート分類ID 7:外貨赤残 */
    private static final int ALERT_CATEGORY_ID_FOREIGN_CURRENCY_RED_RESIDUE = 7;
    
    /** アラート分類ID 12:米株信用決済期日 */
    private static final int ALERT_CATEGORY_ID_FOREIGN_SETTLEMENT_DUE_DATE = 12;
    
    /** アラート分類ID 16:国内満期償還 */
    private static final int ALERT_CATEGORY_ID_DOMESTIC_MATURITY_REDEMPTION = 16;
    
    /** アラート分類ID 17:外国満期償還 */
    private static final int ALERT_CATEGORY_ID_FOREIGN_MATURITY_REDEMPTION = 17;
    
    /** 11日 */
    private static final Integer ELEVEN_DAYS = 11;
    
    /** カレンダー区分: 0（証券営業日ベース） */
    private static final String CALENDAR_TYPE_0 = "0";
    
    @Autowired
    private IfaCustomerAlertCsvOutputDao dao;
    
    @Autowired
    private Fct007 fct007;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A002a
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerAlertCsvOutputA002aRequestDto
     * Dto レスポンス：IfaCustomerAlertCsvOutputA002aResponseDto
     * model リクエスト：IfaCustomerAlertCsvOutputSql002RequestModel
     * model レスポンス：IfaCustomerAlertCsvOutputSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerAlertCsvOutputA002aResponseDto> csvOutputA002a(
            IfaCustomerAlertCsvOutputA002aRequestDto dtoReq, String frameworkSessionId) throws Exception {
        
        DataList<IfaCustomerAlertCsvOutputA002aResponseDto> dtoRes = new DataList<IfaCustomerAlertCsvOutputA002aResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCustomerAlertCsvOutputServiceImplL.csvOutputA002a");
        }
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // 仲介業者営業員リスト
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。
        if (!PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())) {
            // FCT030呼出し
            InputFct030Dto fct030InData = new InputFct030Dto();
            OutputFct030Dto fct030Dto = new OutputFct030Dto();
            fct030Dto = fct030.getData(fct030InData);
            
            brokerChargeList = fct030Dto.getBrokerChargeList();
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (CollectionUtils.isEmpty(brokerChargeList)) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
            
        }
        
        // CSVダウンロードMAX件数を取得し、最大取得件数にセットする。
        InputFct038Dto fct038InData = new InputFct038Dto();
        fct038InData.setScreenId(SCREEN_ID);
        fct038InData.setUserAuthority(userAccount.getPrivId());
        
        OutputFct038Dto fct038Dto = fct038.getData(fct038InData);
        // CSVダウンロードMAX件数
        int maxCsvRowNum = fct038Dto.getCsvDownloadNum();
        
        // 取得対象となるアラート分類IDと年月日を取得する
        IfaCustomerAlertCsvOutputSql001RequestModel selSql001Req = new IfaCustomerAlertCsvOutputSql001RequestModel();
        selSql001Req.setUserId(userAccount.getUserId());
        selSql001Req.setPrivId(userAccount.getPrivId());
        
        DataList<IfaCustomerAlertCsvOutputSql001ResponseModel> selSql001Res = dao
                .selectIfaCustomerAlertCsvOutputSql001(selSql001Req);
        List<IfaCustomerAlertCsvOutputSql001ResponseModel> selSql001List = selSql001Res.getDataList();
        
        // SQL002抽出結果定義
        DataList<IfaCustomerAlertCsvOutputSql002ResponseModel> selSql002Res = new DataList<IfaCustomerAlertCsvOutputSql002ResponseModel>();
        
        if (!CollectionUtils.isEmpty(selSql001List)) {
            // SQL001.アラート分類IDをリストに変換
            List<Integer> sql001AlertClassIdList = new ArrayList<>();
            for (IfaCustomerAlertCsvOutputSql001ResponseModel sql001Item : selSql001List) {
                sql001AlertClassIdList.add(sql001Item.getAlertId());
            }
            // リクエスト.アラート分類リストをリスト型に変換
            List<Integer> requestAlertClassIdList = splitIntegerToList(dtoReq.getAlertClassList());
            
            // SQL001.アラート分類IDとリクエスト.アラート分類リストの両方に"16","17"のいずれかが含まれる場合、指定日を取得する
            boolean isListNotEmpty = !CollectionUtils.isEmpty(sql001AlertClassIdList)
                    && !CollectionUtils.isEmpty(requestAlertClassIdList);
            boolean isDomesticMaturityRedemptionInclude = isListNotEmpty
                    && sql001AlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_DOMESTIC_MATURITY_REDEMPTION == alertId)
                    && requestAlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_DOMESTIC_MATURITY_REDEMPTION == alertId);
            boolean isForeignMaturityRedemptionInclude = isListNotEmpty
                    && sql001AlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_MATURITY_REDEMPTION == alertId)
                    && requestAlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_MATURITY_REDEMPTION == alertId);
            
            boolean isSettlementDueDateInclude = isListNotEmpty
                    && sql001AlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_SETTLEMENT_DUE_DATE == alertId)
                    && requestAlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_SETTLEMENT_DUE_DATE == alertId);
            boolean isForeignCurrencyRedResidueInclude = isListNotEmpty
                    && sql001AlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_CURRENCY_RED_RESIDUE == alertId)
                    && requestAlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_CURRENCY_RED_RESIDUE == alertId);
            
            boolean isForeignSettlementDueDateInclude = isListNotEmpty
                    && sql001AlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_SETTLEMENT_DUE_DATE == alertId)
                    && requestAlertClassIdList.stream()
                            .anyMatch(alertId -> ALERT_CATEGORY_ID_FOREIGN_SETTLEMENT_DUE_DATE == alertId);
            // どちらかがtrueの場合、指定日を取得する
            String designatedDate = null;
            
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.YYYYMMDD);
            if (isDomesticMaturityRedemptionInclude || isForeignMaturityRedemptionInclude) {
                // SQL001.年月日はバッチで前営業日が設定されており、全て同じ日付となるため、リストの1件目を取得する
                Date dateYmd = sdf.parse(selSql001List.get(0).getDateYmd());
                
                InputFct007Dto fct007InData = new InputFct007Dto();
                fct007InData.setStandardDate(dateYmd);
                fct007InData.setCalendarType(CALENDAR_TYPE_0);
                fct007InData.setDay(ELEVEN_DAYS);
                
                OutputFct007Dto fct007OutData = fct007.getData(fct007InData);
                designatedDate = sdf.format(fct007OutData.getDesignatedDate());
                
            } else if (isSettlementDueDateInclude || isForeignCurrencyRedResidueInclude
                    || isForeignSettlementDueDateInclude) {
                Integer codeDays = dao.selectIfaCustomerAlertCsvOutputSql004();
                if (codeDays == 0) {
                    designatedDate = dao.selectIfaCustomerAlertCsvOutputSql005();
                } else {
                    InputFct007Dto fct007InData = new InputFct007Dto();
                    fct007InData.setStandardDate(ifaDateUtil.getCurrentDate());
                    fct007InData.setCalendarType(CALENDAR_TYPE_0);
                    fct007InData.setDay(codeDays);
                    OutputFct007Dto fct007OutData = fct007.getData(fct007InData);
                    designatedDate = sdf.format(fct007OutData.getDesignatedDate());
                }
                
            }
            // SQL001の取得件数が0件でない場合、入力された抽出条件をKEYとしてアラートデータの明細と件数を取得する。
            IfaCustomerAlertCsvOutputSql002RequestModel selSql002Req = new IfaCustomerAlertCsvOutputSql002RequestModel();
            selSql002Req.setUserId(userAccount.getUserId());
            selSql002Req.setPrivId(userAccount.getPrivId());
            selSql002Req.setBrokerChargeList(brokerChargeList);
            selSql002Req.setBrokerCodeList(dtoReq.getBrokerCodeArrayList());
            selSql002Req.setRequestAlertClassIdList(requestAlertClassIdList);
            selSql002Req.setSql001AlertClassIdList(sql001AlertClassIdList);
            selSql002Req.setTradeCourseList(splitStringToList(dtoReq.getTradeCourseList()));
            selSql002Req.setDesignatedDate(designatedDate);
            selSql002Req.setMaxCsvRowNum(maxCsvRowNum);
            
            selSql002Req.setDateYmdMarginDue(selSql001List.stream()
                    .filter(val -> val.getAlertId() == 6 || val.getAlertId() == 7 || val.getAlertId() == 12).findFirst()
                    .map(val -> val.getDateYmd()).orElse(null));
            
            selSql002Req.setDateYmdMutualFundPrice(selSql001List.stream().filter(val -> val.getAlertId() == 13)
                    .findFirst().map(val -> val.getDateYmd()).orElse(null));
            
            selSql002Req.setDateYmdMaturityRedemption(
                    selSql001List.stream().filter(val -> val.getAlertId() == 16 || val.getAlertId() == 17).findFirst()
                            .map(val -> val.getDateYmd()).orElse(null));
            
            selSql002Req.setDateYmdDepositWithdraw(selSql001List.stream().filter(val -> val.getAlertId() == 18)
                    .findFirst().map(val -> val.getDateYmd()).orElse(null));
            
            selSql002Req.setDateYmdDeliverInOut(selSql001List.stream().filter(val -> val.getAlertId() == 19).findFirst()
                    .map(val -> val.getDateYmd()).orElse(null));
            
            selSql002Res = dao.selectIfaCustomerAlertCsvOutputSql002(selSql002Req);
        }
        
        // 抽出結果によりメッセージを表示する。
        List<IfaCustomerAlertCsvOutputSql002ResponseModel> selSql002List = selSql002Res.getDataList();
        if (CollectionUtils.isEmpty(selSql002List)) {
            // 抽出結果が0件の場合：取得結果0件エラーを返す。
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            
        }
        
        // CSVを出力する。出力上限は最大取得件数とする。
        
        // CSVファイル用のデータを作成
        IfaCustomerAlertCsvOutputCsvOut csvOut = new IfaCustomerAlertCsvOutputCsvOut(complianceService);
        DataList<IfaCustomerAlertCsvOutputCustomerAlertNotification> exportList = new DataList<IfaCustomerAlertCsvOutputCustomerAlertNotification>();
        exportList.setDataList(setAlertNotificationList(selSql002List));
        
        int totalRow = selSql002List.get(0).getTotalCount();
        
        if (maxCsvRowNum < totalRow) {
            // 抽出結果が最大取得件数超過の場合：取得結果最大取得件数超メッセージを表示
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM, new String[] {
                            String.valueOf(maxCsvRowNum), String.valueOf(totalRow), String.valueOf(maxCsvRowNum) }));
        } else {
            dtoRes = IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        }
        // CSVファイル作成
        // レスポンスのタイトルにCSVファイル名を設定
        dtoRes.setTitle(csvOut.doCreateCsvFile(exportList, frameworkSessionId, userAccount.getUserId(), null));
        dtoRes.setTotalSize(totalRow);
        return dtoRes;
    }
    
    /**
     * 顧客アラートデータをセットする
     *
     * @param sql002List SQL002の結果リスト
     * @return 顧客アラートデータリスト
     */
    private List<IfaCustomerAlertCsvOutputCustomerAlertNotification> setAlertNotificationList(
            List<IfaCustomerAlertCsvOutputSql002ResponseModel> sql002List) {
        
        List<IfaCustomerAlertCsvOutputCustomerAlertNotification> alertNotificationList = new ArrayList<>();
        
        for (IfaCustomerAlertCsvOutputSql002ResponseModel sqlRes : sql002List) {
            IfaCustomerAlertCsvOutputCustomerAlertNotification alertNotification = new IfaCustomerAlertCsvOutputCustomerAlertNotification();
            
            alertNotification.setAlertCategory(sqlRes.getAlertCategory());
            alertNotification.setAlertTitle(sqlRes.getAlertTitle());
            alertNotification.setBrokerCode(sqlRes.getBrokerCode());
            alertNotification.setBrokerName(sqlRes.getBrokerName());
            alertNotification.setBranchCode(sqlRes.getBranchCode());
            alertNotification.setBranchName(sqlRes.getBranchName());
            alertNotification.setEmpCode(sqlRes.getEmpCode());
            alertNotification.setBrokerChargeName(sqlRes.getBrokerChargeName());
            alertNotification.setButenCode(sqlRes.getButenCode());
            alertNotification.setAccountNumber(sqlRes.getAccountNumber());
            alertNotification.setCourseName(sqlRes.getCourseName());
            alertNotification.setCustomerName(sqlRes.getCustomerName());
            
            alertNotificationList.add(alertNotification);
        }
        
        return alertNotificationList;
    }
    
    /**
     * カンマ区切りの文字列を文字列型のリストに変換する。<br/>
     * null、空文字の場合は空のリストを返却する
     *
     * @param list カンマ区切り文字列
     * @return リスト
     */
    public List<String> splitStringToList(String list) {
        
        if (list == null || list.isEmpty()) {
            return new ArrayList<String>();
        }
        List<String> retList = new ArrayList<String>();
        retList.addAll(Arrays.asList(list.split(",")));
        return retList;
    }
    
    /**
     * カンマ区切りの文字列を数値型のリストに変換する。<br/>
     * null、空文字の場合は空のリストを返却する
     *
     * @param list カンマ区切り文字列
     * @return リスト
     */
    public List<Integer> splitIntegerToList(String list) {
        
        List<Integer> retList = new ArrayList<Integer>();
        if (list == null || list.isEmpty()) {
            return new ArrayList<Integer>();
        }
        String[] items = list.split(",");
        
        for (String item : items) {
            try {
                Integer number = Integer.parseInt(item);
                retList.add(number);
            } catch (NumberFormatException e) {
                // 変換できないときは何もしない
            }
        }
        return retList;
    }
}
