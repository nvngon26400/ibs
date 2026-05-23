package com.sbisec.helios.ap.brokerageMenu.jointMarket.service.impl;

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
import org.springframework.util.ObjectUtils;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.IfaJointMarketTradeSearchDao;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dao.model.IfaJointMarketTradeSearchSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchCsvItems;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchDtoRequestSelected;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.dto.IfaJointMarketTradeSearchList;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.service.IfaJointMarketTradeSearchService;
import com.sbisec.helios.ap.brokerageMenu.jointMarket.util.IfaJointMarketTradeSearchCsvOut;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB0208_01-01
 * 画面名：共同店舗取引検索
 * 
 * @author lianhua.xia
 * 2024/12/06 新規作成
 */

@Component(value = "cmpIfaJointMarketTradeSearchService")
public class IfaJointMarketTradeSearchServiceImpl implements IfaJointMarketTradeSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointMarketTradeSearchServiceImpl.class);

    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** メッセージID:期間指定エラー */
    private static final String MESSAGE_INVALID_DATE_RANGE = "errors.dateRange";

    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** 検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** コードマスタテーブル.種別 （'99':画面コメント） */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードマスタテーブル.CODE_1 （'01':デフォルト） */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードマスタテーブル.CODE_2 （'02':取引履歴画面　コメント） */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_JIONT_TRADELIST = "02";

    /** 項目名:期間指定 */
    private static final String ITEM_NAME_DATE_RANGE = "期間指定";

    /** 期間指定範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "6ヶ月";

    /** 期間指定範囲月数 */
    private static final long DATE_RANGE_LIMIT_MONTH = 6L;

    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;

    /** 画面ID */
    private static final String SCREEN_ID = "SUB0208_01-01";

    @Autowired
    private MCodeService mcodeService;

    @Autowired
    private Fct030 fct030;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private IfaJointMarketTradeSearchDao dao;

    @Autowired
    private ComplianceService complianceService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointMarketTradeSearchA001RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA001ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchaA001RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA001ResponseDto> initializeA001(
        IfaJointMarketTradeSearchA001RequestDto dtoReq) throws Exception {
        DataList<IfaJointMarketTradeSearchA001ResponseDto> dtoRes =
            new DataList<IfaJointMarketTradeSearchA001ResponseDto>();
        List<IfaJointMarketTradeSearchA001ResponseDto> dtoResList = dtoRes.getDataList();
        IfaJointMarketTradeSearchA001ResponseDto a001Res = new IfaJointMarketTradeSearchA001ResponseDto();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTradeSearchServiceImpl.initializeA001");
        }

        // コードマスタより、共同店舗取引検索画面コメントを取得する。
        List<MCode> selSql002Res = mcodeService.getMCodeList(M_CODE_CODE_TYPE_SCREEN_COMMENT,
            M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT, M_CODE_CODE_2_SCREEN_COMMENT_JIONT_TRADELIST);
        
        if (selSql002Res != null && selSql002Res.size() > 0) {
            a001Res.setComment(selSql002Res.get(0).getName());
        }

        dtoResList.add(a001Res);
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointMarketTradeSearchA002RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA002ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchaA002RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA002ResponseDto> displayA002(
        IfaJointMarketTradeSearchA002RequestDto dtoReq) throws Exception {

        DataList<IfaJointMarketTradeSearchA002ResponseDto> dtoRes =
            new DataList<IfaJointMarketTradeSearchA002ResponseDto>();
        List<IfaJointMarketTradeSearchA002ResponseDto> resList =
            new ArrayList<IfaJointMarketTradeSearchA002ResponseDto>();
        List<IfaJointMarketTradeSearchList> jointMarketTradeSearchList =
            new ArrayList<IfaJointMarketTradeSearchList>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTradeSearchServiceImpl.displayA002");
        }

        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodDateTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
            .compareTo(LocalDate.parse(dtoReq.getPeriodDateFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が6ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE, IfaCommonUtil
                .getMessage(MESSAGE_INVALID_DATE_RANGE, new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }

        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }

        // SQL001のリクエスト値を設定
        IfaJointMarketTradeSearchSql001RequestModel selSql001Req = new IfaJointMarketTradeSearchSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setRownum(MAX_COUNT_DISPLAY);
        selSql001Req.setPrivId(privId);
        selSql001Req.setBrokerChargeList(brokerChargeList);
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;

        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaJointMarketTradeSearchDtoRequestSelected::getIsSelected))
            .orElse(false);
        selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        // リストを取得する
        DataList<IfaJointMarketTradeSearchSql001ResponseModel> selSql001ResList =
            new DataList<IfaJointMarketTradeSearchSql001ResponseModel>();
        selSql001ResList = dao.selectIfaJointMarketTradeSearchSql001(selSql001Req);
        List<IfaJointMarketTradeSearchSql001ResponseModel> sql001Res =
            new ArrayList<IfaJointMarketTradeSearchSql001ResponseModel>();
        IfaJointMarketTradeSearchA002ResponseDto res = new IfaJointMarketTradeSearchA002ResponseDto();

        // リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001ResList) || selSql001ResList.size() == 0) {
            return IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (selSql001ResList.get(0).getTotalRow() > MAX_COUNT_DISPLAY) {
            // SQL001.総件数が出力件数上限（5000件）を超過している場合、メッセージを表示し、5000件までの検索結果を明細に表示する。
            returnCode = WARNINGS_DATA_LIST_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                new String[] { Integer.toString(MAX_COUNT_DISPLAY), Integer.toString(selSql001ResList.get(0).getTotalRow()) });
            errorLevel = ErrorLevel.WARNING;
            sql001Res = selSql001ResList.getDataList().subList(0, 5000);
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            sql001Res = selSql001ResList.getDataList();
        }

        // レスポンスの設定
        for (IfaJointMarketTradeSearchSql001ResponseModel selSql001Res : sql001Res) {
            IfaJointMarketTradeSearchList jointMarketTradeSearch =
                new IfaJointMarketTradeSearchList();
            BeanUtils.copyProperties(jointMarketTradeSearch, selSql001Res);
            jointMarketTradeSearchList.add(jointMarketTradeSearch);
        }

        res.setJointMarketTradeSearchList(jointMarketTradeSearchList);
        resList.add(res);

        dtoRes = IfaCommonUtil.createDataList(resList, errorLevel, returnCode, message);

        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointMarketTradeSearchA004RequestDto
     * Dto レスポンス：IfaJointMarketTradeSearchA004ResponseDto
     * model リクエスト：IfaJointMarketTradeSearchA004RequestModel
     * model レスポンス：IfaJointMarketTradeSearchA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointMarketTradeSearchA004ResponseDto> csvOutputA004(
        IfaJointMarketTradeSearchA004RequestDto dtoReq, String fwSessionId) throws Exception {

        DataList<IfaJointMarketTradeSearchA004ResponseDto> dtoRes =
            new DataList<IfaJointMarketTradeSearchA004ResponseDto>();
        List<IfaJointMarketTradeSearchCsvItems> jointMarketTradeSearchCsvList =
            new ArrayList<IfaJointMarketTradeSearchCsvItems>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJointMarketTradeSearchServiceImpl.csvOutputA004");
        }

        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodDateTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
            .compareTo(LocalDate.parse(dtoReq.getPeriodDateFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が6ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE, IfaCommonUtil
                .getMessage(MESSAGE_INVALID_DATE_RANGE, new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }

        // ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
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

        // ４．検索条件入力項目で共同店舗取引検索情報を検索する。
        IfaJointMarketTradeSearchSql001RequestModel selSql001Req = new IfaJointMarketTradeSearchSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        selSql001Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        selSql001Req.setRownum(csvDownloadNum);
        selSql001Req.setPrivId(privId);
        selSql001Req.setBrokerChargeList(brokerChargeList);
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;

        // 証券種別が選択されているか否かを判定する。
        Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaJointMarketTradeSearchDtoRequestSelected::getIsSelected))
            .orElse(false);
        selSql001Req.setIsSecurityClassSelected(isSecurityClassSelected);

        DataList<IfaJointMarketTradeSearchSql001ResponseModel> selSql001Res =
            new DataList<IfaJointMarketTradeSearchSql001ResponseModel>();
        selSql001Res = dao.selectIfaJointMarketTradeSearchSql001(selSql001Req);
        List<IfaJointMarketTradeSearchSql001ResponseModel> sql001Res =
            new ArrayList<IfaJointMarketTradeSearchSql001ResponseModel>();

        // リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql001Res) || selSql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (selSql001Res.get(0).getTotalRow() > csvDownloadNum) {
            // SQL001.総件数が出力件数上限を超過している場合、メッセージを表示し、出力件数上限までの検索結果を明細に表示する。
            returnCode = WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                new String[] { Integer.toString(csvDownloadNum), Integer.toString(selSql001Res.get(0).getTotalRow()),
                    Integer.toString(csvDownloadNum) });
            errorLevel = ErrorLevel.WARNING;
            sql001Res = selSql001Res.getDataList().subList(0, csvDownloadNum);
        } else {
            errorLevel = ErrorLevel.SUCCESS;
            sql001Res = selSql001Res.getDataList();
        }

        // レスポンスの設定
        for (IfaJointMarketTradeSearchSql001ResponseModel Sql001ResData : sql001Res) {
            IfaJointMarketTradeSearchCsvItems jointMarketTradeSearchCsv = new IfaJointMarketTradeSearchCsvItems();
            BeanUtils.copyProperties(jointMarketTradeSearchCsv, Sql001ResData);
            jointMarketTradeSearchCsvList.add(jointMarketTradeSearchCsv);
        }

        // CSV出力
        DataList<IfaJointMarketTradeSearchCsvItems> csvDownList = new DataList<IfaJointMarketTradeSearchCsvItems>();
        csvDownList.setDataList(jointMarketTradeSearchCsvList);

        CsvOutPutUtil csvOutPutUtil = new IfaJointMarketTradeSearchCsvOut(complianceService);

        String csvFileName =
            csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null);
        List<IfaJointMarketTradeSearchA004ResponseDto> dtoResList = new ArrayList<>();

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
