package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptTradeListDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptTradeListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003CsvItem;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListDtoRequestSelected;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptTradeListService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptTradeListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0206_02-01
 * 画面名：共同募集　取引検索
 *
 * @author SBIチョウ
 2024/06/13 新規作成
 */
@Component(value = "cmpIfaJointSubscriptTradelistService")
public class IfaJointSubscriptTradeListServiceImpl implements IfaJointSubscriptTradeListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptTradeListServiceImpl.class);
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB0206_02-01";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** メッセージID:期間指定エラー */
    private static final String MESSAGE_INVALID_DATE_RANGE = "errors.dateRange";
    
    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";
    
    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";
    
    /** コードマスタテーブル.CODE_2 （'02':取引履歴画面コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_JIONT_TRADELIST = "02";
    
    /** 項目名:期間指定 */
    private static final String ITEM_NAME_DATE_RANGE = "期間指定";
    
    /** 期間指定範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "6ヶ月";
    
    /** 期間指定範囲月数 */
    private static final long DATE_RANGE_LIMIT_MONTH = 6L;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private IfaJointSubscriptTradeListDao dao;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private MCodeService mcodeService;


    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptTradeListA001RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA001ResponseDto> initializeA001(IfaJointSubscriptTradeListA001RequestDto dtoReq)
            throws Exception {
        
        DataList<IfaJointSubscriptTradeListA001ResponseDto> dtoRes = new DataList<IfaJointSubscriptTradeListA001ResponseDto>();
        List<IfaJointSubscriptTradeListA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaJointSubscriptTradeListA001ResponseDto a001Res = new IfaJointSubscriptTradeListA001ResponseDto();
        dtoResList.add(a001Res);
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTradeListServiceImpl.initializeA001");
        }
        
        List<MCode> selSql002Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_JIONT_TRADELIST);
        
        if (selSql002Res != null && selSql002Res.size() > 0) {
            a001Res.setCommComment(selSql002Res.get(0).getName());
        }
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointSubscriptTradeListA002RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA002ResponseDto
     * model リクエスト：IfaJointSubscriptTradeListSql001RequestModel
     * model レスポンス：IfaJointSubscriptTradeListSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA002ResponseDto> displayA002(IfaJointSubscriptTradeListA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaJointSubscriptTradeListA002ResponseDto> dtoRes = new DataList<IfaJointSubscriptTradeListA002ResponseDto>();
        List<IfaJointSubscriptTradeListA002ResponseDto> dtoResList = dtoRes.getDataList();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTradeListServiceImpl.displayA002");
        }
        
        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodDateTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
                .compareTo(LocalDate.parse(dtoReq.getPeriodDateFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が12ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_INVALID_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }

        // 権限コード
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        //ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // ３．検索条件入力項目で共募取引情報を検索する。
        IfaJointSubscriptTradeListSql001RequestModel selSql001Req = new IfaJointSubscriptTradeListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setRownum(5000);
        selSql001Req.setPrivId(privId);
        selSql001Req.setUserId(userAccount.getUserId());
        
        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
                .map(securityClassList -> securityClassList.stream().anyMatch(IfaJointSubscriptTradeListDtoRequestSelected::getIsSelected))
                .orElse(false);
            selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        DataList<IfaJointSubscriptTradeListSql001ResponseModel> selSql001Res = dao.selectIfaJointSubscriptTradeListSql001(selSql001Req);
        List<IfaJointSubscriptTradeListSql001ResponseModel> sql001Res = new ArrayList<IfaJointSubscriptTradeListSql001ResponseModel>();
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
        
        for (IfaJointSubscriptTradeListSql001ResponseModel sql001ResData : sql001Res) {
            IfaJointSubscriptTradeListA002ResponseDto a002Res = new IfaJointSubscriptTradeListA002ResponseDto();
            BeanUtils.copyProperties(a002Res, sql001ResData);
            dtoResList.add(a002Res);
        }
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointSubscriptTradeListA003RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA003ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA003ResponseDto> csvOutputA003(IfaJointSubscriptTradeListA003RequestDto dtoReq,
            String fwSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointSubscriptTradeListServiceImpl.csvOutputA003");
        }
        
        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodDateTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
                .compareTo(LocalDate.parse(dtoReq.getPeriodDateFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が12ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_INVALID_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }
        
        // 権限コード
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        //ユーザ共通情報の取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // ３．CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする。
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        
        // 検索条件入力項目で取引履歴情報を検索する。
        IfaJointSubscriptTradeListSql001RequestModel selSql001Req = new IfaJointSubscriptTradeListSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setRownum(csvDownloadNum);
        selSql001Req.setPrivId(privId);
        selSql001Req.setUserId(userAccount.getUserId());
        
        
        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
                .map(securityClassList -> securityClassList.stream().anyMatch(IfaJointSubscriptTradeListDtoRequestSelected::getIsSelected))
                .orElse(false);
        selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        DataList<IfaJointSubscriptTradeListSql001ResponseModel> selSql001Res = dao.selectIfaJointSubscriptTradeListSql001(selSql001Req);
        List<IfaJointSubscriptTradeListSql001ResponseModel> sql001Res = new ArrayList<IfaJointSubscriptTradeListSql001ResponseModel>();
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
        
        List<IfaJointSubscriptTradeListA003CsvItem> csvItemList = new ArrayList<>();
        for (IfaJointSubscriptTradeListSql001ResponseModel sql001ResData : sql001Res) {
            IfaJointSubscriptTradeListA003CsvItem csvItem = new IfaJointSubscriptTradeListA003CsvItem();
            BeanUtils.copyProperties(csvItem, sql001ResData);
            csvItemList.add(csvItem);
        }
        
        // CSV出力
        DataList<IfaJointSubscriptTradeListA003CsvItem> csvDownList = new DataList<>();
        csvDownList.setDataList(csvItemList);
        CsvOutPutUtil csvOutPutUtil = new IfaJointSubscriptTradeListCsvOut(complianceService);
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), null);
        DataList<IfaJointSubscriptTradeListA003ResponseDto> dtoRes = new DataList<IfaJointSubscriptTradeListA003ResponseDto>();
        List<IfaJointSubscriptTradeListA003ResponseDto> dtoResList = new ArrayList<>();
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
}
