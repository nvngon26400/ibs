package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyListDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aResponseDtoBbApplyList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002BbApplyListResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaBbApplyListService;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.util.IfaBbApplyListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0204_02-01
 * 画面名：BB申込一覧
 *
 * @author BASE李
 *
 2024/03/14 新規作成
 */
@Component(value = "cmpIfaBbApplyListService")
public class IfaBbApplyListServiceImpL implements IfaBbApplyListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyListServiceImpL.class);

    @Autowired
    private IfaBbApplyListDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    private static final String SCREEN_ID = "SUB0204_02-01";
    
    /** ユーザ共通情報.権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";

    private static final String ERRORS_SELECTED_PARAM = "取引コース";
    
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    private static final String IFA_BB_PRESENTATION = "IFA・BB申込期間";
    private static final String BB_CORRECT_CANCEL = "BB訂正・取消";
    
    private static final String URGENT_STOP_ON = "1";
    
    private static final String APPLICATION_PERIOD = "募集期間";
    private static final String APPLICATION_INPUT = "募集入力";
    private static final String PAYMENT_DATE = "入金予定日（募集最終日）";
    private static final String PRESENTATION_DATE = "上場日";
    private static final String DISCOUNT_BRAND_ISSUE_PRICE = "ディスカウント率の銘柄で発行価格";
    private static final String ISSUE_PRICE_TYPE_DISCOUNT = "2";
    private static final String ORDER_LAST_TIME = "16:00";
    private static final String LOTTERY_RESULT_0 = "0";
    private static final String LOTTERY_RESULT_1 = "1";
    private static final String LOTTERY_RESULT_3 = "3";
    private static final String LOTTERY_RESULT_4 = "4";
    private static final String EDELIV_ONLY_FLAG_1 = "1";
    
    private static final String DATE4 = "HH:mm";
    private static final String DATE12 = "yyyy-MM-dd HH:mm:ss";
    /** ワーニングなし */
    private static final String WARNING_APPLY_0 = "0";
    private static final String WARNING_APPLY_0_MESSAGE = "申請済チェックなし";
    /** ワーニングある */
    private static final String WARNING_APPLY_1 = "1";
    private static final String WARNING_APPLY_1_MESSAGE = "申請済チェックあり";
    /** ハイフン */
    private static final String HYPHEN = "-";
    /** 区分値表示パターン */
    private static final String DISPLAY_PATTERN_1 = "1";
    private static final String DISPLAY_PATTERN_2 = "2";
    
    /** 取引コース区分リスト */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 抽選結果区分リスト */
    private static final String SUBSCRIPT_BB_DRAWING_RESULT = "SUBSCRIPT_BB_DRAWING_RESULT";
    
    /** 注文状況区分リスト */
    private static final String FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS = "FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS";
    
    /** 預かり区分リスト */
    private static final String FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE = "FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE";
    
    /** 勧誘区分リスト */
    private static final String INVITATION_TYPE = "INVITATION_TYPE";
    
    /**
     *
     * @author BASE 李
     *
     */
    private enum MessageId {
        ERRORS_SELECTED("errors.selected"),
        ERRORS_CMN_IFAAGENTCODES_NOTEXIST("errors.cmn.ifaAgentCodes.notExist"),
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound"),
        WARNINGS_DATALIST_OVERMAXROWNUM("warnings.dataList.overMaxRownum"),
        ERRORS_EMPTYRECORD("errors.emptyRecord"),
        ERRORS_PERIODINPUTCHECK("errors.periodInputCheck"),
        ERRORS_APPLYTIME("errors.applyTime"),
        ERRORS_URGENTSTOPCHECK("errors.urgentStopCheck"),
        ERRORS_ORDERLASTTIME("errors.orderLastTime"),
        ERRORS_NOTLOTTERYCHECK("errors.notLotteryCheck"),
        ERRORS_LOTTERYRESULTCHECK("errors.lotteryResultCheck"),
        ERRORS_ORDERTIME("errors.orderTime"),
        ERRORS_PROSPECTUSCHECK("errors.prospectusCheck"),
        ERRORS_ONLYEDELIVAGREEMENTCHECK("errors.onlyEdelivAgreementCheck"),
        WARNINGS_DATALIST_CSV_OVERMAXROWNUM("warnings.dataList.csv.overMaxRownum"),
        ERRORS_EDELIVAGREEMENTCHECK("errors.edelivAgreementCheck");
        private String key;
        
        private MessageId(String key) {
            this.key = key;
        }
    }
    
    /**
     * アクションID：X002
     * アクション名：表示
     * Dto リクエスト：IfaBbApplyListX002RequestDto
     * Dto レスポンス：IfaBbApplyListX002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq IfaBbApplyListX002RequestDto
     * @return dtoRes IfaBbApplyListX002ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListX002ResponseDto> displayX002(IfaBbApplyListX002RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyListX002ResponseDto> dtoRes = new DataList<IfaBbApplyListX002ResponseDto>();
        IfaBbApplyListX002ResponseDto ifaBbApplyListX002ResponseDto = new IfaBbApplyListX002ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyListX002ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyListServiceImplL.displayX002");    
        }

        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ERRORS_SELECTED_PARAM}));
        }
        
        // ② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaBbApplyListSql001RequestModel ifaBbApplyListSql001RequestModel = new IfaBbApplyListSql001RequestModel();
        ifaBbApplyListSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaBbApplyListSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        // ③ BB申込一覧リストを取得する。
        BeanUtils.copyProperties(ifaBbApplyListSql001RequestModel, dtoReq);
        ifaBbApplyListSql001RequestModel.setQuerySizeLimit(QUERY_SIZE_LIMIT);
        ifaBbApplyListSql001RequestModel.setPrivId(privId);
        DataList<IfaBbApplyListSql001ResponseModel> sql001Res = dao.selectIfaBbApplyListSql001(ifaBbApplyListSql001RequestModel);
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        } else if (new BigDecimal(sql001Res.get(0).getTotalCount()).compareTo(new BigDecimal(QUERY_SIZE_LIMIT))  > 0) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                    new String[] {QUERY_SIZE_LIMIT, String.valueOf(sql001Res.get(0).getTotalCount())}));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        
        List<IfaBbApplyListSql001ResponseModel> dataList = sql001Res.getDataList();
        List<IfaBbApplyListX002BbApplyListResponseDto> list = 
                dataList.stream().map(sql001ResModel -> {
                    IfaBbApplyListX002BbApplyListResponseDto ifaBbApplyListX002BbApplyListResponseDto = new IfaBbApplyListX002BbApplyListResponseDto();
                    try {
                        BeanUtils.copyProperties(ifaBbApplyListX002BbApplyListResponseDto, sql001ResModel);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ifaBbApplyListX002BbApplyListResponseDto;
                }).collect(Collectors.toList());
        ifaBbApplyListX002ResponseDto.setBbApplyListList(list);
        
        return dtoRes;
    }

    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaBbApplyListA004aRequestDto
     * Dto レスポンス：IfaBbApplyListA004aResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq IfaBbApplyListA004aRequestDto
     * @return dtoRes IfaBbApplyListA004aResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA004aResponseDto> csvOutputA004(IfaBbApplyListA004aRequestDto dtoReq, String sessionId)
            throws Exception {
        DataList<IfaBbApplyListA004aResponseDto> dtoRes = new DataList<IfaBbApplyListA004aResponseDto>();
        IfaBbApplyListA004aResponseDto ifaBbApplyListA004aResponseDto = new IfaBbApplyListA004aResponseDto();
        ifaBbApplyListA004aResponseDto.setPattern(dtoReq.getEmpCodeAutoDispFlag());
        dtoRes.getDataList().add(ifaBbApplyListA004aResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyListServiceImplL.csvOutputA004a");
        }
        // ①   X002の①~②の処理を行う。
        // リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ERRORS_SELECTED_PARAM}));
        }
        
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaBbApplyListSql001RequestModel ifaBbApplyListSql001RequestModel = new IfaBbApplyListSql001RequestModel();
        ifaBbApplyListSql001RequestModel.setPrivId(privId);
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(new InputFct030Dto());
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaBbApplyListSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        
        // ② CSVダウンロードMAX件数を取得し、最大取得件数にセットする。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        inputFct038Dto.setUserAuthority(privId);
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);
        String csvDownloadNum = String.valueOf(outputFct038Dto.getCsvDownloadNum());
        BeanUtils.copyProperties(ifaBbApplyListSql001RequestModel, dtoReq);
        ifaBbApplyListSql001RequestModel.setQuerySizeLimit(String.valueOf(csvDownloadNum));
        DataList<IfaBbApplyListSql001ResponseModel> sql001Res = dao.selectIfaBbApplyListSql001(ifaBbApplyListSql001RequestModel);
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        } else if (new BigDecimal(sql001Res.get(0).getTotalCount()).compareTo(new BigDecimal(csvDownloadNum))  > 0) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key,
                    new String[] {csvDownloadNum, String.valueOf(sql001Res.get(0).getTotalCount()), csvDownloadNum}));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key);
        }
        // ④ CSVファイル用のデータを作成する。
        CsvOutPutUtil csvOut = new IfaBbApplyListCsvOut(complianceService);
        DataList<IfaBbApplyListA004aResponseDtoBbApplyList> csvDataList = new DataList<>();
        
        initIfaBbApplyListA004aResponseDtoBbApplyList(csvDataList.getDataList(), sql001Res);
        String filePath = csvOut.doCreateCsvFile(csvDataList, sessionId, userAccount.getUserId(), dtoReq.getEmpCodeAutoDispFlag());
        dtoRes.setTitle(filePath);
        dtoRes.setTotalSize(sql001Res.get(0).getTotalCount());
        return dtoRes;
    }
    
    /**
     * アクションID：A005
     * アクション名：BB取消訂正
     * Dto リクエスト：IfaBbApplyListA005RequestDto
     * Dto レスポンス：IfaBbApplyListA005ResponseDto
     * model リクエスト：ifaBbApplyListSql002RequestModel
     * model レスポンス：IfaBbApplyListSql002ResponseModel
     *
     * @param dtoReq IfaBbApplyListA005RequestDto
     * @return dtoRes IfaBbApplyListA005ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA005ResponseDto> bbCancellationCorrectionA005(IfaBbApplyListA005RequestDto dtoReq)
            throws Exception {
 
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyListServiceImplL.bbCancellationCorrectionA005");
        }
        // ② 存在チェックを行う。
        IfaBbApplyListSql002RequestModel ifaBbApplyListSql002RequestModel = new IfaBbApplyListSql002RequestModel();
        BeanUtils.copyProperties(ifaBbApplyListSql002RequestModel, dtoReq);
        DataList<IfaBbApplyListSql002ResponseModel> sql002Res = dao.selectIfaBbApplyListSql002(ifaBbApplyListSql002RequestModel);
        if (sql002Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_EMPTYRECORD.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_EMPTYRECORD.key, new String[] {""}));
        }
        // ③ ブックビルディング申込期間チェック
        IfaBbApplyListSql002ResponseModel sql002ResModel = sql002Res.get(0);
        if (ObjectUtils.isEmpty(sql002ResModel.getPresentationFrom()) || ObjectUtils.isEmpty(sql002ResModel.getPresentationTo())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_PERIODINPUTCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_PERIODINPUTCHECK.key, new String[] {IFA_BB_PRESENTATION, BB_CORRECT_CANCEL}));
        }
       
        DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE12);
        if (LocalDateTime.parse(sql002ResModel.getPresentationFrom(), format).isAfter(LocalDateTime.now())
                || LocalDateTime.now().isAfter(LocalDateTime.parse(sql002ResModel.getPresentationTo(), format))) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_APPLYTIME.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_APPLYTIME.key, new String[] {BB_CORRECT_CANCEL}));

        }
        // ④ 緊急入力停止チェック
        if (URGENT_STOP_ON.equals(sql002ResModel.getUrgentStop())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_URGENTSTOPCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_URGENTSTOPCHECK.key, new String[] {BB_CORRECT_CANCEL}));
        }
        
        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, "", "");
    }
    
    /**
     * アクションID：A006
     * アクション名：募集入力
     * Dto リクエスト：IfaBbApplyListA006RequestDto
     * Dto レスポンス：IfaBbApplyListA006ResponseDto
     * model リクエスト：ifaBbApplyListSql003RequestModel
     * model レスポンス：IfaBbApplyListSql003ResponseModel
     *
     * @param dtoReq IfaBbApplyListA006RequestDto
     * @return dtoRes IfaBbApplyListA006ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA006ResponseDto> subscriptInputA006(IfaBbApplyListA006RequestDto dtoReq)
            throws Exception {
                
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyListServiceImplL.subscriptInputA006");
        }

        // ② 存在チェックを行う。
        IfaBbApplyListSql003RequestModel ifaBbApplyListSql003RequestModel = new IfaBbApplyListSql003RequestModel();
        BeanUtils.copyProperties(ifaBbApplyListSql003RequestModel, dtoReq);
        DataList<IfaBbApplyListSql003ResponseModel> sql003Res = dao.selectIfaBbApplyListSql003(ifaBbApplyListSql003RequestModel);
        if (sql003Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_PERIODINPUTCHECK.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_PERIODINPUTCHECK.key, new String[] {APPLICATION_PERIOD, APPLICATION_INPUT}));

        }

        // ③ 募集期間チェックを行う。
        // ユーザ権限
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        // システム日付YYYYMMDD
        
        String sysDate = ifaDateUtil.format(IfaDateUtil.YYYYMMDD);
        IfaBbApplyListSql003ResponseModel sql003ResModel = sql003Res.get(0);
        // 　システム日時（YYYYMMDD） >= SQL003.募集期間FROM かつ システム日時（YYYYMMDD） <= SQL003.募集期間TOの場合
        if (sysDate.compareTo(sql003ResModel.getPeriodFrom()) >= 0 && sysDate.compareTo(sql003ResModel.getPeriodTo()) <= 0) {
            // 入金予定日（募集最終日）チェック
            if (ObjectUtils.isEmpty(sql003ResModel.getPaymentDate())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_PERIODINPUTCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_PERIODINPUTCHECK.key, new String[] {PAYMENT_DATE, APPLICATION_INPUT}));
            }
            // 上場日チェック
            if (ObjectUtils.isEmpty(sql003ResModel.getPresentationDate())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_PERIODINPUTCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_PERIODINPUTCHECK.key, new String[] {PRESENTATION_DATE, APPLICATION_INPUT}));
            }
            // ディスカウント率の銘柄で発行価格未登録チェック
            if (ISSUE_PRICE_TYPE_DISCOUNT.equals(sql003ResModel.getIssuePriceType()) && ObjectUtils.isEmpty(sql003ResModel.getIssueBbPrice())) {

                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_PERIODINPUTCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_PERIODINPUTCHECK.key, new String[] {DISCOUNT_BRAND_ISSUE_PRICE, APPLICATION_INPUT}));
            }
            // SQL003.募集期間TO＝ システム日時（YYYYMMDD）の場合
            if (sysDate.equals(sql003ResModel.getPeriodTo())) {
                IfaBbApplyListSql004ResponseModel sql004ResponseModel = dao.selectIfaBbApplyListSql004().get(0);
                String sysDateTime = ifaDateUtil.format(DATE4);
                // 募集終了時間チェック
                if (sysDateTime.compareTo(sql004ResponseModel.getName()) >= 0) {
                    return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                            MessageId.ERRORS_ORDERLASTTIME.key, 
                            IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERLASTTIME.key, new String[] {ORDER_LAST_TIME}));
                }
                // 抽選結果チェック
                // 本店権限（ユーザ共通情報.権限コード ＝ '1':SBI証券本店）の場合
                if (PRI_ID_1.equals(privId)) {
                    if (LOTTERY_RESULT_0.equals(sql003ResModel.getLotteryResult())) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                                MessageId.ERRORS_NOTLOTTERYCHECK.key, 
                                IfaCommonUtil.getMessage(MessageId.ERRORS_NOTLOTTERYCHECK.key));
                    }
                } else {
                    // 本店権限以外（ユーザ共通情報.権限コード ≠ '1':SBI証券本店）の場合
                    if (!LOTTERY_RESULT_1.equals(sql003ResModel.getLotteryResult())
                            && !LOTTERY_RESULT_3.equals(sql003ResModel.getLotteryResult())
                            && !LOTTERY_RESULT_4.equals(sql003ResModel.getLotteryResult())) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                                MessageId.ERRORS_LOTTERYRESULTCHECK.key, 
                                IfaCommonUtil.getMessage(MessageId.ERRORS_LOTTERYRESULTCHECK.key));
                    }
                }
            //　SQL003.募集期間TO＝ システム日時（YYYYMMDD）でない場合。
            } else {
                // 抽選結果チェック
                // 本店権限（ユーザ共通情報.権限コード ＝ '1':SBI証券本店）の場合
                if (PRI_ID_1.equals(privId)) {
                    if (LOTTERY_RESULT_0.equals(sql003ResModel.getLotteryResult())) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                                MessageId.ERRORS_NOTLOTTERYCHECK.key, 
                                IfaCommonUtil.getMessage(MessageId.ERRORS_NOTLOTTERYCHECK.key));
                    }
                } else {
                    // 本店権限以外（ユーザ共通情報.権限コード ≠ '1':SBI証券本店）の場合
                    if (!LOTTERY_RESULT_1.equals(sql003ResModel.getLotteryResult())
                            && !LOTTERY_RESULT_3.equals(sql003ResModel.getLotteryResult())
                            && !LOTTERY_RESULT_4.equals(sql003ResModel.getLotteryResult())) {
                        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                                MessageId.ERRORS_LOTTERYRESULTCHECK.key, 
                                IfaCommonUtil.getMessage(MessageId.ERRORS_LOTTERYRESULTCHECK.key));
                    }
                }
            }
            
            
        } else {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_ORDERTIME.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_ORDERTIME.key, new String[] { APPLICATION_INPUT }));
        }
        // ④ 目論見書の交付方法チェック
        if (!PRI_ID_1.equals(privId) && ObjectUtils.isEmpty(sql003ResModel.getQuantity())) {
            if (!ObjectUtils.isEmpty(sql003ResModel.getEdelivAgreementDate()) && ObjectUtils.isEmpty(sql003ResModel.getReadTime())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_PROSPECTUSCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_PROSPECTUSCHECK.key));
            }
            if (ObjectUtils.isEmpty(sql003ResModel.getEdelivAgreementDate()) && !ObjectUtils.isEmpty(sql003ResModel.getReadTime())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_EDELIVAGREEMENTCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_EDELIVAGREEMENTCHECK.key));
            }
        }
        
        // ⑤ 電子交付同意書の同意・目論見書の閲覧チェック
        if (!PRI_ID_1.equals(privId) && EDELIV_ONLY_FLAG_1.equals(sql003ResModel.getEdelivOnlyFlag())) {
            // SQL003.電子交付承諾日付が未設定の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(sql003ResModel.getEdelivAgreementDate())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_ONLYEDELIVAGREEMENTCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_ONLYEDELIVAGREEMENTCHECK.key));
            }
            // SQL003.閲覧日時が未設定の場合：メッセージを表示し、処理終了。
            if (ObjectUtils.isEmpty(sql003ResModel.getReadTime())) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_PROSPECTUSCHECK.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_PROSPECTUSCHECK.key));
            }
        }
        return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS, "", "");
    }
    
    /**
     *
     * @param target IfaBbApplyListA004aResponseDtoBbApplyList
     * @param source sql001Response　IfaBbApplyListSql001ResponseModel
     * @throws Exception システムエラー
     */
    private void initIfaBbApplyListA004aResponseDtoBbApplyList(
            List<IfaBbApplyListA004aResponseDtoBbApplyList> target,
            DataList<IfaBbApplyListSql001ResponseModel> source) throws Exception {
        for (IfaBbApplyListSql001ResponseModel sql001ResModel : source.getDataList()) {
            IfaBbApplyListA004aResponseDtoBbApplyList ifaBbApplyListA004aResponseDtoBbApplyList = new IfaBbApplyListA004aResponseDtoBbApplyList();
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrandCode(sql001ResModel.getBrandCode());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrandName(sql001ResModel.getBrandName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrokerCode(sql001ResModel.getBrokerCode());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrokerName(sql001ResModel.getBrokerName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrokerBranchCode(sql001ResModel.getBrokerBranchCode());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrokerBranchName(sql001ResModel.getBrokerBranchName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBrokerChargeCode(sql001ResModel.getBrokerChargeCode());
            ifaBbApplyListA004aResponseDtoBbApplyList.setEmployeeName(sql001ResModel.getEmployeeName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setButenCode(sql001ResModel.getButenCode());
            ifaBbApplyListA004aResponseDtoBbApplyList.setAccountNumber(sql001ResModel.getAccountNumber());
            ifaBbApplyListA004aResponseDtoBbApplyList.setCustomerNameKana(sql001ResModel.getCustomerNameKana());
            ifaBbApplyListA004aResponseDtoBbApplyList.setCustomerNameKanji(sql001ResModel.getCustomerNameKanji());
            // 取引コース区分値変換
            ifaBbApplyListA004aResponseDtoBbApplyList.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, sql001ResModel.getCourse(), DISPLAY_PATTERN_1));
            ifaBbApplyListA004aResponseDtoBbApplyList.setInvestorAttributeName(sql001ResModel.getInvestorAttributeName());
            // カンマ区切り
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbQuantity(numberWithComma(sql001ResModel.getBbQuantity()));
            
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbPrice(sql001ResModel.getBbPrice());
            ifaBbApplyListA004aResponseDtoBbApplyList.setQuantitySairyou(sql001ResModel.getQuantitySairyou());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbQuantityAlloc(numberWithComma(sql001ResModel.getBbQuantityAlloc()));
            
            // 抽選結果区分値変換
            ifaBbApplyListA004aResponseDtoBbApplyList.setLotteryResult(codeListService.getValue(SUBSCRIPT_BB_DRAWING_RESULT, sql001ResModel.getLotteryResult(), DISPLAY_PATTERN_1));
            
            // 注文状況区分値変換
            ifaBbApplyListA004aResponseDtoBbApplyList.setOrderStatus(codeListService.getValue(FACE_TO_FACE_SUBSCRIPT_ORDER_STATUS, sql001ResModel.getOrderStatus(), DISPLAY_PATTERN_2));
            
            ifaBbApplyListA004aResponseDtoBbApplyList.setOrderQuantity(sql001ResModel.getOrderQuantity());
            
            // 預り区分区分値変換
            ifaBbApplyListA004aResponseDtoBbApplyList.setDepositType(codeListService.getValue(FACE_TO_FACE_SUBSCRIPT_ORDER_DEPOSIT_TYPE, sql001ResModel.getDepositType(), DISPLAY_PATTERN_1));
            // 勧誘区分区分値変換
            ifaBbApplyListA004aResponseDtoBbApplyList.setKanyuKbn(codeListService.getValue(INVITATION_TYPE, sql001ResModel.getKanyuKbn(), DISPLAY_PATTERN_1));
            
            if (WARNING_APPLY_0.equals(sql001ResModel.getWarningApplyArranged())) {
                ifaBbApplyListA004aResponseDtoBbApplyList.setWarningApplyArranged(WARNING_APPLY_0_MESSAGE);
            } else if (WARNING_APPLY_1.equals(sql001ResModel.getWarningApplyArranged())) {
                ifaBbApplyListA004aResponseDtoBbApplyList.setWarningApplyArranged(WARNING_APPLY_1_MESSAGE);
            } else {
                ifaBbApplyListA004aResponseDtoBbApplyList.setWarningApplyArranged(HYPHEN);
            }
            
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbCreateDate(sql001ResModel.getBbCreateDate());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbCreateUserName(sql001ResModel.getBbCreateUserName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setSectionName(sql001ResModel.getSectionName());
            ifaBbApplyListA004aResponseDtoBbApplyList.setEdelivAgreement(sql001ResModel.getEdelivAgreementDate());
            ifaBbApplyListA004aResponseDtoBbApplyList.setReadTime(sql001ResModel.getReadTime());
            ifaBbApplyListA004aResponseDtoBbApplyList.setBbRemark(sql001ResModel.getBbRemark());           
        
            target.add(ifaBbApplyListA004aResponseDtoBbApplyList);
        }
    }
    
    /**
     *
     * @param number 整数
     * @return number with comma 例えば23123=>23,123
     */
    private String numberWithComma(String number) {
        if (ObjectUtils.isEmpty(number)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        
        for (int i = number.length() - 1, count = 1; i >= 0; i--, count++) {
            if (count % 3 == 0) {
                sb.append(number.charAt(i));
                sb.append(",");
            } else {
                sb.append(number.charAt(i));
            }
        }
        number = sb.reverse().toString();
        if (number.indexOf(",") == 0) {
            number = number.substring(1);
        }
        return number;
    }
    
}
