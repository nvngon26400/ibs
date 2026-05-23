package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Optional;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaRepCustomerCommListDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListMultiSelectItem;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaRepCustomerCommListModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaRepCustomerCommListService;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaRepCustomerCommListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020501-01
 * 画面名：担当顧客別手数料一覧
 * 2024/06/10 新規作成
 *
 * @author 
 */
@Component(value = "cmpIfaRepCustomerCommListService")
public class IfaRepCustomerCommListServiceImpl implements IfaRepCustomerCommListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaRepCustomerCommListServiceImpl.class);
    
    /** メッセージID:選択必須エラー */
    private static final String MESSAGE_ITEM_SELECT_REQUIRED = "errors.selected";
    
    /** メッセージID:期間指定エラー */
    private static final String MESSAGE_INVALID_DATE_RANGE = "errors.dateRange";
    
    /** メッセージID:参照可能仲介業者／営業員不存在エラー */
    private static final String MESSAGE_AGENT_NOT_EXISTS = "errors.cmn.ifaAgentCodes.notExist";
    
    /** メッセージID:検索結果0件 */
    private static final String MESSAGE_LIST_IS_EMPTY = "errors.dataList.notfound";
    
    /** メッセージID:検索結果画面表示件数超過ワーニング */
    private static final String MESSAGE_LIST_MAX_LIMIT_WARNING = "warnings.dataList.overMaxRownum";
    
    /** メッセージID:検索結果CSV出力件数超過ワーニング */
    private static final String MESSAGE_CSV_OUTPUT_MAX_LIMIT_WARNING = "warnings.dataList.csv.overMaxRownum";
    
    /** 項目名:取引コース */
    private static final String ITEM_NAME_COURSE = "取引コース";
    
    /** 項目名:証券種別 */
    private static final String ITEM_NAME_SECURITY_CLASS = "証券種別";
    
    /** 項目名:期間指定 */
    private static final String ITEM_NAME_DATE_RANGE = "期間指定";
    
    /** 期間指定範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "6ヶ月";
    
    /** 期間指定範囲月数 */
    private static final long DATE_RANGE_LIMIT_MONTH = 6L;
    
    /** 最大表示件数(画面) */
    private static final int SCREEN_SEARCH_LIMIT_ROW_NUM = 5000;
    
    /** 画面ID(CSVダウンロードMAX件数取得用) */
    private static final String SCREEN_ID = "SUB020501-01";
    
    /** 区分値:証券種別（手数料一覧）.国内株式全般 */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_GENERAL = "98";
    
    /** 区分値:証券種別（手数料一覧）.為替取引 */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_EXCHANGE = "99";
    
    /** 区分値:証券種別（手数料一覧）.国内株式現物 */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_STOCK = "01";
    
    /** 区分値:証券種別（手数料一覧）.国内株式信用 */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_MARGIN = "02";
    
    /** 区分値:証券種別（手数料一覧）.国内株式IPO・PO */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_IPO_PO = "03";
    
    /** 区分値:証券種別（手数料一覧）.国内株式(単元未満) */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_S_KABU = "24";
    
    /** 区分値:証券種別（手数料一覧）.現株ポイント */
    private static final String CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_POINT = "27";
    
    /** 集計単位:営業員毎 */
    private static final String CHARGE_CUSTOMER_COUNTING_UNIT_CHARGE = "0";
    
    /** 集計単位:顧客毎 */
    private static final String CHARGE_CUSTOMER_COUNTING_UNIT_CUSTOMER = "1";
    
    @Autowired
    private IfaRepCustomerCommListDao dao;
    
    /** FCT030:仲介業者コード営業員リスト取得 */
    @Autowired
    private Fct030 fct030;
    
    /** FCT038CSVダウンロードMAX件数取得 */
    @Autowired
    private Fct038 fct038;
    
    /** コンプライアンスService */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRepCustomerCommListA001RequestDto
     * Dto レスポンス：IfaRepCustomerCommListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA001ResponseDto> initializeA001(IfaRepCustomerCommListA001RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepCustomerCommListServiceImpl.initializeA001");
        }
        
        final IfaRepCustomerCommListA001ResponseDto dtoRes = new IfaRepCustomerCommListA001ResponseDto();
        
        // 現株ポイント参照可能仲介業者のリストを取得する
        final List<IfaRepCustomerCommListSql002ResponseModel> referenceAbleBrokerList = dao
                .selectIfaRepCustomerCommListSql002();
        
        dtoRes.setSpotStockPointReferenceAbleList(referenceAbleBrokerList.stream()
                .map(referenceAbleBroker -> referenceAbleBroker.getBrokerCode()).collect(Collectors.toList()));
        
        // 担当顧客別手数料コメントを取得する
        dtoRes.setRepCustomerCommComment(dao.selectIfaRepCustomerCommListSql003());
        
        // レスポンスを返却する 
        return IfaCommonUtil.createDataList(List.of(dtoRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaRepCustomerCommListA002RequestDto
     * Dto レスポンス：IfaRepCustomerCommListA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA002ResponseDto> displayA002(IfaRepCustomerCommListA002RequestDto dtoReq)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepCustomerCommListServiceImpl.displayA002");
        }
        
        Boolean isCourseSelected = Optional.ofNullable(dtoReq.getCourse())
            .map(courseList -> courseList.stream().anyMatch(IfaRepCustomerCommListMultiSelectItem::getIsSelected))
            .orElse(false);

        if (!isCourseSelected) {
            // 取引コースが設定されていない場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ITEM_SELECT_REQUIRED,
                    IfaCommonUtil.getMessage(MESSAGE_ITEM_SELECT_REQUIRED, new String[] { ITEM_NAME_COURSE }));
        }
        
        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodYmTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
                .compareTo(LocalDate.parse(dtoReq.getPeriodYmFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が6ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_INVALID_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }

		Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaRepCustomerCommListMultiSelectItem::getIsSelected))
            .orElse(false);

        if (!isSecurityClassSelected) {
            // 証券種別が設定されていない場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ITEM_SELECT_REQUIRED,
                    IfaCommonUtil.getMessage(MESSAGE_ITEM_SELECT_REQUIRED, new String[] { ITEM_NAME_SECURITY_CLASS }));
        }
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        List<OutputFct030Dto.BrokerCharge> brokerChargeList = List.of();
        if (!PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())) {
            // ユーザ共通情報.権限コードが「SBI証券本店」以外の場合、参照可能な仲介業者コードをと営業員コードを取得する
            OutputFct030Dto fct030Res = fct030.getData(new InputFct030Dto());
            brokerChargeList = fct030Res.getBrokerChargeList();
            if (CollectionUtils.isEmpty(brokerChargeList)) {
                // 仲介業者営業員リストが空の場合、エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_AGENT_NOT_EXISTS,
                        IfaCommonUtil.getMessage(MESSAGE_AGENT_NOT_EXISTS));
            }
        }
        
        // 選択されている証券種別を取得し、国内株式全般が存在する場合、現物、信用、IPO・PO、単元未満を対象に追加する
        final SortedSet<String> selectedSecurityClassSet = dtoReq.getSecurityClass().stream()
                .filter(securityClass -> securityClass.getIsSelected())
                .map(securityClass -> securityClass.getId()).collect(Collectors.toCollection(TreeSet::new));
        if (selectedSecurityClassSet.contains(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_GENERAL)) {
            selectedSecurityClassSet.addAll(Set.of(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_STOCK,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_MARGIN,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_IPO_PO,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_S_KABU));
        }
        
        // 現株ポイント参照可能仲介業者のリストを取得する
        final List<IfaRepCustomerCommListSql002ResponseModel> referenceAbleBrokerList = dao
                .selectIfaRepCustomerCommListSql002();

        // 対象外証券種別を設定する
        IfaRepCustomerCommListSql001RequestModel selSql001Req = new IfaRepCustomerCommListSql001RequestModel();
        if (referenceAbleBrokerList.stream().map(referenceAbleBroker -> referenceAbleBroker.getBrokerCode())
                .anyMatch(item -> StringUtils.equals(item, userAccount.getBrokerId()))
                || StringUtils.equalsAny(userAccount.getPrivId(),
                        new String[] { PrivId.HEAD_OFFICE.getId(), PrivId.BRANCH.getId() })) {
            /*
             * 仲介業者コードが現株ポイント対象可能仲介業者に該当する場合、
             * もしくはユーザ共通情報.権限コードが「SBI証券本店」「SBI証券支店」のいずれかの場合、対象外証券種別を設定しない
             */
            selSql001Req.setExcludeSecurityClass(null);
        } else {
            // それ以外の場合、対象外証券種別に現株ポイントを設定する
            selSql001Req.setExcludeSecurityClass(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_POINT);
        }
        
        // 検索上限件数に画面の検索条件件数(5000)を設定する
        selSql001Req.setSearchLimitRow(Integer.toString(SCREEN_SEARCH_LIMIT_ROW_NUM));
        
        // 担当顧客別手数料一覧取得リクエストパラメータを設定する
        selSql001Req.setBrokerCodeList(dtoReq.getBrokerCode());
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setBranchCode(dtoReq.getBranchCode());
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        selSql001Req.setButenCode(dtoReq.getButenCode());
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());
        selSql001Req.setCourseList(
                dtoReq.getCourse().stream().filter(course -> course.getIsSelected())
                        .map(course -> course.getId()).collect(Collectors.toList()));
        selSql001Req.setBrandCode(dtoReq.getBrandCode());
        selSql001Req.setChargeCustomerCountingUnit(dtoReq.getChargeCustomerCountingUnit());
        selSql001Req.setPeriodYmFrom(dtoReq.getPeriodYmFrom());
        selSql001Req.setPeriodYmTo(dtoReq.getPeriodYmTo());
        List<String> securityClassList = new ArrayList<>(List.copyOf(selectedSecurityClassSet));
        selSql001Req.setSecurityClassList(securityClassList);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setIsContainTrade(List.copyOf(selectedSecurityClassSet).stream().anyMatch(value -> !value.equals(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_EXCHANGE)));
        
        // 顧客別手数料一覧を取得する
        DataList<IfaRepCustomerCommListModel> sql001DataList = selectIfaRepCustomerCommListSql001(selSql001Req);
        
        // レスポンスを作成する
        IfaRepCustomerCommListA002ResponseDto dtoRes = new IfaRepCustomerCommListA002ResponseDto();
        dtoRes.setRepCustomerCommList(sql001DataList.getDataList());
        
        if (CollectionUtils.isEmpty(sql001DataList.getDataList())) {
            // 検索結果が0件の場合
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.INFO, MESSAGE_LIST_IS_EMPTY,
                    IfaCommonUtil.getMessage(MESSAGE_LIST_IS_EMPTY));
        } else if (sql001DataList.isOverMaxRownum()) {
            // 検索上限件数を超える場合
            return IfaCommonUtil.createDataList(List.of(dtoRes), ErrorLevel.WARNING, MESSAGE_LIST_MAX_LIMIT_WARNING,
                    IfaCommonUtil.getMessage(MESSAGE_LIST_MAX_LIMIT_WARNING, new String[] { Integer.toString(SCREEN_SEARCH_LIMIT_ROW_NUM),
                            Integer.toString(sql001DataList.getTotalSize())}));
        }
        
        // レスポンスを返却する 
        return IfaCommonUtil.createDataList(List.of(dtoRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtils.EMPTY);
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaRepCustomerCommListA004aRequestDto
     * Dto レスポンス：IfaRepCustomerCommListA004aResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaRepCustomerCommListA004aResponseDto> csvOutputA004(IfaRepCustomerCommListA004aRequestDto dtoReq, String fwSessionId)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaRepCustomerCommListServiceImpl.csvOutputA004");
        }

		Boolean isCourseSelected = Optional.ofNullable(dtoReq.getCourse())
            .map(courseList -> courseList.stream().anyMatch(IfaRepCustomerCommListMultiSelectItem::getIsSelected))
            .orElse(false);

        if (!isCourseSelected) {
            // 取引コースが設定されていない場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ITEM_SELECT_REQUIRED,
                    IfaCommonUtil.getMessage(MESSAGE_ITEM_SELECT_REQUIRED, new String[] { ITEM_NAME_COURSE }));
        }
        
        // 期間指定のチェック
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD);
        if (LocalDate.parse(dtoReq.getPeriodYmTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
                .compareTo(LocalDate.parse(dtoReq.getPeriodYmFrom(), dateFormatter)) >= 0) {
            // 期間指定（From）と期間指定（To）の差が6ヶ月より大きい場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE,
                    IfaCommonUtil.getMessage(MESSAGE_INVALID_DATE_RANGE,
                            new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }
        
		Boolean isSecurityClassSelected = Optional.ofNullable(dtoReq.getSecurityClass())
            .map(securityClassList -> securityClassList.stream().anyMatch(IfaRepCustomerCommListMultiSelectItem::getIsSelected))
            .orElse(false);

        if (!isSecurityClassSelected) {
            // 証券種別が設定されていない場合、エラーを返す
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_ITEM_SELECT_REQUIRED,
                    IfaCommonUtil.getMessage(MESSAGE_ITEM_SELECT_REQUIRED, new String[] { ITEM_NAME_SECURITY_CLASS }));
        }
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        List<OutputFct030Dto.BrokerCharge> brokerChargeList = List.of();
        if (!PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())) {
            // ユーザ共通情報.権限コードが「SBI証券本店」以外の場合、参照可能な仲介業者コードをと営業員コードを取得する
            OutputFct030Dto fct030Res = fct030.getData(new InputFct030Dto());
            brokerChargeList = fct030Res.getBrokerChargeList();
            if (CollectionUtils.isEmpty(brokerChargeList)) {
                // 仲介業者営業員リストが空の場合、エラーを返す
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, MESSAGE_AGENT_NOT_EXISTS,
                        IfaCommonUtil.getMessage(MESSAGE_AGENT_NOT_EXISTS));
            }
        }
        
        // 選択されている証券種別を取得し、国内株式全般が存在する場合、現物、信用、IPO・PO、単元未満を対象に追加する
        final SortedSet<String> selectedSecurityClassSet = dtoReq.getSecurityClass().stream()
                .filter(securityClass -> securityClass.getIsSelected())
                .map(securityClass -> securityClass.getId()).collect(Collectors.toCollection(TreeSet::new));
        if (selectedSecurityClassSet.contains(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_GENERAL)) {
            selectedSecurityClassSet.addAll(Set.of(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_STOCK,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DOMESTIC_MARGIN,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_IPO_PO,
                    CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_S_KABU));
        }
        
        // 現株ポイント参照可能仲介業者のリストを取得する
        final List<IfaRepCustomerCommListSql002ResponseModel> referenceAbleBrokerList = dao
                .selectIfaRepCustomerCommListSql002();

        // 対象外証券種別を設定する
        IfaRepCustomerCommListSql001RequestModel selSql001Req = new IfaRepCustomerCommListSql001RequestModel();
        if (referenceAbleBrokerList.stream().map(referenceAbleBroker -> referenceAbleBroker.getBrokerCode())
                .anyMatch(brokerCode -> StringUtils.equals(brokerCode,userAccount.getBrokerId()))
                || StringUtils.equalsAny(userAccount.getPrivId(),
                        new String[] { PrivId.HEAD_OFFICE.getId(), PrivId.BRANCH.getId() })) {
            /*
             * 仲介業者コードが現株ポイント対象可能仲介業者に該当する場合、
             * もしくはユーザ共通情報.権限コードが「SBI証券本店」「SBI証券支店」のいずれかの場合、対象外証券種別を設定しない
             */
            selSql001Req.setExcludeSecurityClass(null);
        } else {
            // それ以外の場合、対象外証券種別に現株ポイントを設定する
            selSql001Req.setExcludeSecurityClass(CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_DSTOCK_POINT);
        }
        
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);

        // 検索上限件数にCSVダウンロード上限件数を設定する
        int csvDownloadNum = outputFct038Dto.getCsvDownloadNum();
        selSql001Req.setSearchLimitRow(Integer.toString(csvDownloadNum));
        
        // 担当顧客別手数料一覧取得リクエストパラメータを設定する
        selSql001Req.setBrokerCodeList(dtoReq.getBrokerCode());
        selSql001Req.setChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setBranchCode(dtoReq.getBranchCode());
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        selSql001Req.setButenCode(dtoReq.getButenCode());
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());
        selSql001Req.setCourseList(
                dtoReq.getCourse().stream().filter(course -> course.getIsSelected())
                        .map(course -> course.getId()).collect(Collectors.toList()));
        selSql001Req.setBrandCode(dtoReq.getBrandCode());
        selSql001Req.setChargeCustomerCountingUnit(dtoReq.getChargeCustomerCountingUnit());
        selSql001Req.setPeriodYmFrom(dtoReq.getPeriodYmFrom());
        selSql001Req.setPeriodYmTo(dtoReq.getPeriodYmTo());
        List<String> securityClassList = new ArrayList<>(List.copyOf(selectedSecurityClassSet));
        selSql001Req.setSecurityClassList(securityClassList);
        selSql001Req.setPrivId(userAccount.getPrivId());
        
        // 顧客別手数料一覧を取得する
        DataList<IfaRepCustomerCommListModel> sql001DataList = selectIfaRepCustomerCommListSql001(selSql001Req);

        // レスポンスを作成する
        IfaRepCustomerCommListA004aResponseDto responseData = new IfaRepCustomerCommListA004aResponseDto();
        DataList<IfaRepCustomerCommListA004aResponseDto> dtoRes = null;
        
        if (CollectionUtils.isEmpty(sql001DataList.getDataList())) {
            // 検索結果が0件の場合
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.INFO, MESSAGE_LIST_IS_EMPTY,
                    IfaCommonUtil.getMessage(MESSAGE_LIST_IS_EMPTY));
        } else if (sql001DataList.isOverMaxRownum()) {
            // 検索上限件数を超える場合
            responseData.setRepCustomerCommList(sql001DataList.getDataList().subList(0, csvDownloadNum));
            dtoRes = IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.WARNING,
                    MESSAGE_CSV_OUTPUT_MAX_LIMIT_WARNING,
                    IfaCommonUtil.getMessage(MESSAGE_CSV_OUTPUT_MAX_LIMIT_WARNING,
                            new String[] { Integer.toString(csvDownloadNum),
                                    Integer.toString(sql001DataList.getTotalSize()),
                                    Integer.toString(csvDownloadNum) }));
            dtoRes.setOverMaxRownum(true);
        } else {
            // 検索上限件数以下の場合
            responseData.setRepCustomerCommList(sql001DataList.getDataList());
            dtoRes = IfaCommonUtil.createDataList(List.of(responseData), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                    StringUtils.EMPTY);
        }
        
        // CSVファイル用のデータを作成する。
        CsvOutPutUtil csvOut = new IfaRepCustomerCommListCsvOut(complianceService);
        String filePath = csvOut.doCreateCsvFile(
			sql001DataList,
			fwSessionId,
            userAccount.getUserId(),
			dtoReq.getChargeCustomerCountingUnit()
		);
		
		// タイトルに"集計単位,一時ファイルパス"をセット
		String title = dtoReq.getChargeCustomerCountingUnit() + "," + filePath;
        dtoRes.setTitle(title);
        dtoRes.setTotalSize(sql001DataList.getTotalSize());
        
        // レスポンスを返却する 
        return dtoRes;
    }
    
    /**
     * 担当顧客別手数料一覧取得
     *
     * @param req リクエスト
     * @return ifaRepCustomerCommList 担当顧客別手数料一覧
     * @exception Exception システムエラー
     */
    private DataList<IfaRepCustomerCommListModel> selectIfaRepCustomerCommListSql001(
            final IfaRepCustomerCommListSql001RequestModel req) throws Exception {
        
        // パラメータの証券種別リストに99:為替以外の設定があるか判定した結果を設定する
        req.setIsContainTrade(req.getSecurityClassList().stream()
                .anyMatch(securityClass -> !CODE_VAL_COMMISSION_LIST_SECURITY_CLASS_EXCHANGE.equals(securityClass)));
        
        // SQL001：担当顧客別手数料一覧取得
        final List<IfaRepCustomerCommListSql001ResponseModel> selSql001Res = dao
                .selectIfaRepCustomerCommListSql001(req);
        
        final List<IfaRepCustomerCommListModel> ifaRepCustomerCommList = selSql001Res.stream().map(sql001Record -> {
            IfaRepCustomerCommListModel ifaRepCustomerComm = new IfaRepCustomerCommListModel();
            ifaRepCustomerComm.setChargeCustomerCountingUnit(req.getChargeCustomerCountingUnit());
            ifaRepCustomerComm.setBrokerCode(sql001Record.getBrokerCode());
            ifaRepCustomerComm.setBrokerName(sql001Record.getBrokerName());
            ifaRepCustomerComm.setEmpCode(sql001Record.getEmpCode());
            ifaRepCustomerComm.setBrokerChargeName(sql001Record.getBrokerChargeName());
            ifaRepCustomerComm.setBrokerageBranchCode(sql001Record.getBrokerageBranchCode());
            ifaRepCustomerComm.setBrokerBranchName(sql001Record.getBrokerBranchName());
            ifaRepCustomerComm.setCommTotal(sql001Record.getCommTotal());
            ifaRepCustomerComm.setButenCode(sql001Record.getButenCode());
            ifaRepCustomerComm.setAccountNumber(sql001Record.getAccountNumber());
            ifaRepCustomerComm.setCustomerNameKanji(sql001Record.getCustomerNameKanji());
            ifaRepCustomerComm.setCustomerNameKana(sql001Record.getCustomerNameKana());
            ifaRepCustomerComm.setDealerNumber(sql001Record.getDealerNumber());
            ifaRepCustomerComm.setCourseName(sql001Record.getCourseName());
            return ifaRepCustomerComm;
        }).collect(Collectors.toList());
        
        // 返却するDataListに値を設定する
        final DataList<IfaRepCustomerCommListModel> dataList = IfaCommonUtil.createDataList(ifaRepCustomerCommList,
                ErrorLevel.SUCCESS, null, null);
        dataList.setMaxRownum(Integer.valueOf(req.getSearchLimitRow()).intValue());
        
        if (CollectionUtils.isNotEmpty(selSql001Res)) {
            dataList.setTotalSize(Integer.valueOf(selSql001Res.get(0).getTotalRow()).intValue());
            if (dataList.getTotalSize() > dataList.getMaxRownum()) {
                // 総件数が検索上限件数を超える場合
                dataList.setOverMaxRownum(true);
            }
        }
        
        return dataList;
    }
}
