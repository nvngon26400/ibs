package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTradeHistoryDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeHistorySql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005CsvItem;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTradeHistoryDtoRequestSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaTradeHistoryService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTradeHistoryCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020302_0201-01
 * 画面名：取引履歴
 *
 * @author SCSK
 2024/06/13 新規作成
 */
@Component(value = "cmpIfaTradeHistoryService")
public class IfaTradeHistoryServiceImpL implements IfaTradeHistoryService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaTradeHistoryServiceImpL.class);
    
    private static final String PRIVID_1 = "1";
    
    private static final String PRIVID_2 = "2";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020302_0201-01";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    private static final String STRUCTURED_BOND_TYPE = "STRUCTURED_BOND_TYPE";

    /** メッセージID:期間指定エラー */
    private static final String MESSAGE_INVALID_DATE_RANGE = "errors.dateRange";

    /** 項目名:期間指定 */
    private static final String ITEM_NAME_DATE_RANGE = "期間指定";
    
    /** 期間指定範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "6ヶ月";
    
    /** 期間指定範囲月数 */
    private static final long DATE_RANGE_LIMIT_MONTH = 6L;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private IfaTradeHistoryDao dao;
    
    @Autowired
    private ComplianceService complianceService;

    @Autowired
    private CodeListService codeListService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaTradeHistoryA001RequestDto
     * Dto レスポンス：IfaTradeHistoryA001ResponseDto
     * model リクエスト：IfaTradeHistorySql002RequestModel
     * model レスポンス：IfaTradeHistorySql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA001ResponseDto> initializeA001(IfaTradeHistoryA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaTradeHistoryA001ResponseDto> dtoRes = new DataList<IfaTradeHistoryA001ResponseDto>();
        List<IfaTradeHistoryA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaTradeHistoryA001ResponseDto a001Res = new IfaTradeHistoryA001ResponseDto();
        dtoResList.add(a001Res);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeHistoryServiceImplL.initializeA001");
        }
        
        // １．コードマスタより、取引履歴画面コメントを取得する。
        DataList<IfaTradeHistorySql002ResponseModel> selSql002Res = dao.selectIfaTradeHistorySql002();
        if (selSql002Res.size() != 0) {
            a001Res.setComment(selSql002Res.get(0).getComment());
        }
        
        // ２．（ユーザ共通情報.権限コード ＝ '1':SBI証券本店 または '2':SBI証券支店）以外の場合、証券種別の現株ポイントが参照可能な仲介業者かどうかをチェックする。
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        String brokerCode = IfaCommonUtil.getUserAccount().getBrokerId();
        if (!PRIVID_1.equals(privId) && !PRIVID_2.equals(privId)) {
            IfaTradeHistorySql003RequestModel selSql003Req = new IfaTradeHistorySql003RequestModel();
            selSql003Req.setBrokerCode(brokerCode);
            DataList<IfaTradeHistorySql003ResponseModel> selSql003Res = dao.selectIfaTradeHistorySql003(selSql003Req);
            if (selSql003Res.size() != 0) {
                a001Res.setCount(selSql003Res.get(0).getCount());
            }
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaTradeHistoryA002RequestDto
     * Dto レスポンス：IfaTradeHistoryA002ResponseDto
     * model リクエスト：IfaTradeHistorySql001RequestModel
     * model レスポンス：IfaTradeHistorySql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA002ResponseDto> displayA002(IfaTradeHistoryA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaTradeHistoryA002ResponseDto> dtoRes = new DataList<IfaTradeHistoryA002ResponseDto>();
        List<IfaTradeHistoryA002ResponseDto> dtoResList = dtoRes.getDataList();
        

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeHistoryServiceImplL.displayA002");
        }

        // １．入力チェックを行う。
        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
        if (LocalDate.parse(dtoReq.getPeriodDateTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
                .compareTo(LocalDate.parse(dtoReq.getPeriodDateFrom(), dateFormatter)) > 0) {
            // 期間指定（From）と期間指定（To）の差が6ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_INVALID_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }
        
        // ２．ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PRIVID_1.equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        // ３．検索条件入力項目で取引履歴情報を検索する。
        IfaTradeHistorySql001RequestModel selSql001Req = new IfaTradeHistorySql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setVisibleButenCodeList(splitButenCodeArray(dtoReq.getButenCodeArray()));
        selSql001Req.setRownum(5000);
        selSql001Req.setPrivId(privId);
        selSql001Req.setBrokerChargeList(brokerChargeList);

        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaTradeHistoryDtoRequestSelected::getIsSelected))
            .orElse(false);
        selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        DataList<IfaTradeHistorySql001ResponseModel> selSql001Res = dao.selectIfaTradeHistorySql001(selSql001Req);
        List<IfaTradeHistorySql001ResponseModel> sql001Res = new ArrayList<IfaTradeHistorySql001ResponseModel>();
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        if (selSql001Res.getDataList().size() == 0 || selSql001Res.get(0).getTotalRow() == 0) {
            // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
        } else if (selSql001Res.get(0).getTotalRow() > 5000) {
            // SQL001.総件数が出力件数上限（5000件）を超過している場合、メッセージを表示し、5000件までの検索結果を明細に表示する。
            returnCode = WARNINGS_DATALIST_OVERMAXROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                    new String[] { Integer.toString(5000), Integer.toString(selSql001Res.get(0).getTotalRow()) });
            errorLevel = ErrorLevel.WARNING;
            sql001Res = selSql001Res.getDataList().subList(0, 5000);
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            sql001Res = selSql001Res.getDataList();
        }
        
        for (IfaTradeHistorySql001ResponseModel sql001ResData : sql001Res) {
            IfaTradeHistoryA002ResponseDto a002Res = new IfaTradeHistoryA002ResponseDto();
            BeanUtils.copyProperties(a002Res, sql001ResData);
            dtoResList.add(a002Res);
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaTradeHistoryA005RequestDto
     * Dto レスポンス：IfaTradeHistoryA005ResponseDto
     * model リクエスト：IfaTradeHistorySql001RequestModel
     * model レスポンス：IfaTradeHistorySql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaTradeHistoryA005ResponseDto> csvOutputA005(IfaTradeHistoryA005RequestDto dtoReq,
            String fwSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTradeHistoryServiceImplL.csvOutputA005");
        }
        
        // ２．ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PRIVID_1.equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        // ３．CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする。
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        
        // ４．検索条件入力項目で取引履歴情報を検索する。
        IfaTradeHistorySql001RequestModel selSql001Req = new IfaTradeHistorySql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setVisibleButenCodeList(splitButenCodeArray(dtoReq.getButenCodeArray()));
        selSql001Req.setRownum(csvDownloadNum);
        selSql001Req.setPrivId(privId);
        selSql001Req.setBrokerChargeList(brokerChargeList);

        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaTradeHistoryDtoRequestSelected::getIsSelected))
            .orElse(false);
        selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        DataList<IfaTradeHistorySql001ResponseModel> selSql001Res = dao.selectIfaTradeHistorySql001(selSql001Req);
        List<IfaTradeHistorySql001ResponseModel> sql001Res = new ArrayList<IfaTradeHistorySql001ResponseModel>();
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;

        if (selSql001Res.getDataList().size() == 0 || selSql001Res.get(0).getTotalRow() == 0) {
            // SQL001.総件数が0件の場合、エラーメッセージを表示し、処理終了。
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
        } else if (selSql001Res.get(0).getTotalRow() > csvDownloadNum) {
            // SQL001.総件数がCSVダウンロードMAX件数を超える場合、メッセージを表示し、CSVダウンロードMAX件数までの検索結果をCSV出力する。
            returnCode = WARNINGS_DATALIST_CSV_OVERMAXROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                    new String[] { Integer.toString(csvDownloadNum),
                            Integer.toString(selSql001Res.get(0).getTotalRow()), Integer.toString(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
            sql001Res = selSql001Res.getDataList().subList(0, csvDownloadNum);
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            sql001Res = selSql001Res.getDataList();
        }
        
        List<IfaTradeHistoryA005CsvItem> csvItemList = new ArrayList<>();
        for (IfaTradeHistorySql001ResponseModel sql001ResData : sql001Res) {
            IfaTradeHistoryA005CsvItem csvItem = new IfaTradeHistoryA005CsvItem();
            BeanUtils.copyProperties(csvItem, sql001ResData);
            csvItem.setStructuredBondClassification(
                    codeListService.getValue(STRUCTURED_BOND_TYPE, csvItem.getStructuredBondClassification(), "1"));
            csvItemList.add(csvItem);
        }
        
        // CSV出力
        DataList<IfaTradeHistoryA005CsvItem> csvDownList = new DataList<>();
        csvDownList.setDataList(csvItemList);
        
        // 「証券種別」に全商品、もしくは「外国債券（外貨建）」を選択した場合のみ出力。 それ以外の場合項目未出力。
        String pattern = "0";
        Boolean isId14Selected = dtoReq.getSecurityClass().stream()
                .anyMatch(obj -> "14".equals(obj.getId()) && obj.getIsSelected());
        if (isId14Selected) {
            pattern = "1";
        }
        
        CsvOutPutUtil csvOutPutUtil = new IfaTradeHistoryCsvOut(complianceService);
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), pattern);
        
        DataList<IfaTradeHistoryA005ResponseDto> dtoRes = new DataList<IfaTradeHistoryA005ResponseDto>();
        IfaTradeHistoryA005ResponseDto a005Res = new IfaTradeHistoryA005ResponseDto();
        a005Res.setPattern(pattern);
        List<IfaTradeHistoryA005ResponseDto> dtoResList = new ArrayList<>();
        dtoResList.add(a005Res);
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);
        dtoRes.setTitle(csvFileName);
        dtoRes.setTotalSize(csvDownloadNum);
        
        return dtoRes;
    }

    /** 仲介業者コード編集 */
    private List<String> splitBrokerCode(String brokerCode) {
        
        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
        }
        return brokerCodeList;
    }
    
    /** 閲覧可能部店編集 */
    private List<String> splitButenCodeArray(String butenCodeArray) {
        
        List<String> visibleButenCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(butenCodeArray)) {
            visibleButenCodeList.addAll(Arrays.asList(butenCodeArray.split(",")));
        }
        return visibleButenCodeList;
    }
}
