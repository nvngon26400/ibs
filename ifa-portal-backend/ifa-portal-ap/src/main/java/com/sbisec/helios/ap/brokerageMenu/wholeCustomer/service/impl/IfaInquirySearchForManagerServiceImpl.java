package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactToiawaseListDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaInquirySearchForManagerDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquiryDetailListDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerCsvItems;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerRequestSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchListDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaInquirySearchForManagerService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaInquirySearchForManagerCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.DateUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Component(value = "cmpIfaInquirySearchForManagerService")
public class IfaInquirySearchForManagerServiceImpl implements IfaInquirySearchForManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInquirySearchForManagerServiceImpl.class);
    
    /** 項目名:期間指定 */
    private static final String ITEM_NAME_DATE_RANGE = "問合せ日Ftom～Toの期間指定";

    /** 期間指定範囲 */
    private static final String DATE_RANGE_LIMIT_TEXT = "6ヶ月";

    /** 期間指定範囲月数 */
    private static final long DATE_RANGE_LIMIT_MONTH = 6L;
    
    /** 作成日 */
    private static final String QUESTION_DATE = "問合せ日";
    
    /** メッセージID:期間指定エラー */
    private static final String MESSAGE_INVALID_DATE_RANGE = "errors.dateRange";
    
    /** 表示期間チェックエラーfromTo  */
    private static final String ERRORS_DATE_SPECIFYFROMTO = "errors.date.specifyFromTo";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** 検索結果が5000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** ワーニング.{0}件のcsv出力を行います。 */
    private static final String WARNINGS_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB020304-01";
    
    @Autowired
    private Fct030 fct030;

    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private CodeListService codeListService;
    
    @Autowired
    private IfaInquirySearchForManagerDao dao;
    
    @Autowired
    private ComplianceService complianceService;
    
    /** 重要度 */
    private static final String JUUYOUDO = "JUUYOUDO";
    
    /** 入電方向 */
    private static final String NYUUDEN_HOUKOU = "NYUUDEN_HOUKOU";
    
    /** 対応ステータス */
    private static final String TAIOU_STS = "TAIOU_STS";
    
    /** 表示パターン */
    private static final String DISPLAY_PATTERN_1 = "1";
    private static final String DISPLAY_PATTERN_2 = "2";
    
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInquirySearchForManagerA001RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws Exception API取得時に例外が発生した場合
     */
    public DataList<IfaInquirySearchForManagerA001ResponseDto> initializeA001(IfaInquirySearchForManagerA001RequestDto dtoReq)
        throws Exception{
        
        DataList<IfaInquirySearchForManagerA001ResponseDto> dtoRes = new DataList<IfaInquirySearchForManagerA001ResponseDto>();
        List<IfaInquirySearchForManagerA001ResponseDto> a001ListRes = new ArrayList<IfaInquirySearchForManagerA001ResponseDto>();
        IfaInquirySearchForManagerA001ResponseDto a001Res = new IfaInquirySearchForManagerA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInquirySearchForManagerServiceImpl.initializeA001");
        }
        
        // コードマスタより、接触履歴（入力）検索画面コメントを取得する。
        IfaInquirySearchForManagerSql001ResponseModel sql001Res = new IfaInquirySearchForManagerSql001ResponseModel();
        sql001Res = dao.selectIfaInquirySearchForManagerSql001();
        
        if (sql001Res != null &&StringUtils.isNotEmpty(sql001Res.getIfaInquirySearchForManagerComment())) {
            a001Res.setComment(sql001Res.getIfaInquirySearchForManagerComment());
        }
        
        // カテゴリ（大）リストを取得する。
        List<IfaInquirySearchForManagerSql002ResponseModel> sql002Res = new ArrayList<IfaInquirySearchForManagerSql002ResponseModel>();
        sql002Res = dao.selectIfaInquirySearchForManagerSql002();
        
        if (sql002Res != null && sql002Res.size() > 0) {
            List<IfaContactToiawaseListDto> toiawaseDList = new ArrayList<IfaContactToiawaseListDto>();
            for (IfaInquirySearchForManagerSql002ResponseModel res : sql002Res) {
                IfaContactToiawaseListDto toiawaseD = new IfaContactToiawaseListDto();
                BeanUtils.copyProperties(res, toiawaseD);
                toiawaseDList.add(toiawaseD);
            }
            a001Res.setToiawaseDList(toiawaseDList);
        }
        
        a001ListRes.add(a001Res);
        
        dtoRes = IfaCommonUtil.createDataList(a001ListRes, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }

    /**
     * アクションID：A002
     * アクション名：カテゴリ（中）取得
     * Dto リクエスト：IfaInquirySearchForManagerA002RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA002ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @Override
    public DataList<IfaInquirySearchForManagerA002ResponseDto> categoryChangeA002(
        IfaInquirySearchForManagerA002RequestDto dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInquirySearchForManagerServiceImpl.categoryChangeA002");
        }
        
        DataList<IfaInquirySearchForManagerA002ResponseDto> dtoRes = new DataList<IfaInquirySearchForManagerA002ResponseDto>();
        List<IfaInquirySearchForManagerA002ResponseDto> a002ListRes = new ArrayList<IfaInquirySearchForManagerA002ResponseDto>();
        IfaInquirySearchForManagerA002ResponseDto a002Res = new IfaInquirySearchForManagerA002ResponseDto();
        
        // カテゴリ（中）取得
        IfaInquirySearchForManagerSql003RequestModel sql003Req = new IfaInquirySearchForManagerSql003RequestModel();
        sql003Req.setToiawaseDCd(dtoReq.getToiawaseDCd());
        
        List<IfaInquirySearchForManagerSql003ResponseModel> sql003Res = new ArrayList<IfaInquirySearchForManagerSql003ResponseModel>();
        sql003Res = dao.selectIfaInquirySearchForManagerSql003(sql003Req);
        
        if (sql003Res != null && sql003Res.size() > 0) {
            List<IfaContactToiawaseListDto> toiawaseDList = new ArrayList<IfaContactToiawaseListDto>();
            for (IfaInquirySearchForManagerSql003ResponseModel res : sql003Res) {
                IfaContactToiawaseListDto toiawase = new IfaContactToiawaseListDto();
                BeanUtils.copyProperties(res, toiawase);
                toiawaseDList.add(toiawase);
            }
            a002Res.setToiawaseList(toiawaseDList);
        }
        a002ListRes.add(a002Res);
        
        dtoRes = IfaCommonUtil.createDataList(a002ListRes, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }

    /**
     * アクションID：A003
     * アクション名：カテゴリ（小）取得
     * Dto リクエスト：IfaInquirySearchForManagerA003RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA003ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @Override
    public DataList<IfaInquirySearchForManagerA003ResponseDto> categoryChangeA003(
        IfaInquirySearchForManagerA003RequestDto dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInquirySearchForManagerServiceImpl.categoryChangeA002");
        }
        
        DataList<IfaInquirySearchForManagerA003ResponseDto> dtoRes = new DataList<IfaInquirySearchForManagerA003ResponseDto>();
        List<IfaInquirySearchForManagerA003ResponseDto> a003ListRes = new ArrayList<IfaInquirySearchForManagerA003ResponseDto>();
        IfaInquirySearchForManagerA003ResponseDto a002Res = new IfaInquirySearchForManagerA003ResponseDto();
        
        // カテゴリ（小）取得
        IfaInquirySearchForManagerSql004RequestModel sql004Req = new IfaInquirySearchForManagerSql004RequestModel();
        sql004Req.setToiawaseDCd(dtoReq.getToiawaseDCd());
        sql004Req.setToiawaseCd(dtoReq.getToiawaseCd());
        
        List<IfaInquirySearchForManagerSql004ResponseModel> sql004Res = new ArrayList<IfaInquirySearchForManagerSql004ResponseModel>();
        sql004Res = dao.selectIfaInquirySearchForManagerSql004(sql004Req);
        
        if (sql004Res != null && sql004Res.size() > 0) {
            List<IfaContactToiawaseListDto> toiawaseDList = new ArrayList<IfaContactToiawaseListDto>();
            for (IfaInquirySearchForManagerSql004ResponseModel res : sql004Res) {
                IfaContactToiawaseListDto toiawaseS = new IfaContactToiawaseListDto();
                BeanUtils.copyProperties(res, toiawaseS);
                toiawaseDList.add(toiawaseS);
            }
            a002Res.setToiawaseSList(toiawaseDList);
        }
        a003ListRes.add(a002Res);
        
        dtoRes = IfaCommonUtil.createDataList(a003ListRes, ErrorLevel.SUCCESS, "", "");
        
        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：表示
     * Dto リクエスト：IfaInquirySearchForManagerA004RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA004ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @Override
    public DataList<IfaInquirySearchForManagerA004ResponseDto> displayA004(
        IfaInquirySearchForManagerA004RequestDto dtoReq) throws Exception {
        DataList<IfaInquirySearchForManagerA004ResponseDto> dtoRes = new DataList<IfaInquirySearchForManagerA004ResponseDto>();
        List<IfaInquirySearchForManagerA004ResponseDto> a004ListRes = new ArrayList<IfaInquirySearchForManagerA004ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInquirySearchForManagerServiceImpl.displayA004");
        }
        // 1.画面.問合せ日From～問合せ日Toの期間が6ヶ月を超えていないかチェックを行う
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD);
        if (LocalDate.parse(dtoReq.getInquiryDateYmTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
            .compareTo(LocalDate.parse(dtoReq.getInquiryDateYmFrom(), dateFormatter)) > 0) {
            // 6ヶ月を超えた場合：メッセージを表示し処理終了
            return IfaCommonUtil.createDataList(a004ListRes, ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE, IfaCommonUtil
                .getMessage(MESSAGE_INVALID_DATE_RANGE, new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }
        
        // 2.画面.問合せ日（To） ＜ 画面.問合せ日（From）の場合、メッセージを表示し、処理終了。
        if (LocalDate.parse(dtoReq.getInquiryDateYmTo(), dateFormatter).compareTo(LocalDate.parse(dtoReq.getInquiryDateYmFrom(), dateFormatter)) < 0) {
            return IfaCommonUtil.createDataList(a004ListRes, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYFROMTO, IfaCommonUtil
                .getMessage(ERRORS_DATE_SPECIFYFROMTO, new String[] { QUESTION_DATE }));
        }
        
        // 3.ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(a004ListRes, ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        List<IfaInquirySearchForManagerRequestSelected> juuyoudoList = dtoReq.getJuuyoudo().stream()
            .filter(obj -> Boolean.TRUE.equals(obj.getIsSelected()))
            .collect(Collectors.toList());
        
        List<IfaInquirySearchForManagerRequestSelected> taiouStsList = dtoReq.getTaiouSts().stream()
            .filter(obj -> Boolean.TRUE.equals(obj.getIsSelected()))
            .collect(Collectors.toList());
        
        // 4.画面に入力された検索条件で画面表示明細件数を取得する
        IfaInquirySearchForManagerSql006RequestModel sql006Req = new IfaInquirySearchForManagerSql006RequestModel();
        BeanUtils.copyProperties(dtoReq, sql006Req);
        // 仲介業者コード
        sql006Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 支店コード
        sql006Req.setBranchCodeList(splitBranchCode(dtoReq.getBranchCode()));
        // 営業員コード
        sql006Req.setEmpCodeList(splitEmpCode(dtoReq.getEmpCode()));
        // 顧客名（全角半角）
        sql006Req.setCustomerName(dtoReq.getCustomerNameKanjiKana());
        // 顧客名検索オプション
        sql006Req.setCustomerNameSearchType(dtoReq.getCustomerNameKanjiKanaTerms());
        // クレーム/リクエスト
        switch (dtoReq.getCr()) {
            // 画面.クレーム/リクエスト＝'C'の場合
            case ONE : {
                sql006Req.setCream(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'R'の場合
            case TWO : {
                sql006Req.setRequest(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'CR'の場合
            case THREE : {
                sql006Req.setCream(ONE);
                sql006Req.setRequest(ONE);
                break;
            }
            default : break;
        }
        // 重要度
        sql006Req.setJuuyoudo(juuyoudoList);
        // 対応ステータス
        sql006Req.setTaiouSts(taiouStsList);
        // 権限コード
        sql006Req.setPrivId(privId);
        // 仲介業者営業員リスト
        sql006Req.setBrokerChargeList(brokerChargeList);
        
        IfaInquirySearchForManagerSql006ResponseModel sql006Res = new IfaInquirySearchForManagerSql006ResponseModel();
        sql006Res = dao.selectIfaInquirySearchForManagerSql006(sql006Req);
        
        // SQL006.総件数が0件の場合、エラーメッセージを表示し、処理終了
        if (sql006Res == null || sql006Res.getTotalRow() == 0) {
            return IfaCommonUtil.createDataList(a004ListRes, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (sql006Res.getTotalRow() > MAX_COUNT_DISPLAY) {
            // 検索結果が1000000件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 {1}：SQL006.総件数
            returnCode = WARNINGS_DATA_LIST_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                new String[] { Integer.toString(MAX_COUNT_DISPLAY), Integer.toString(sql006Res.getTotalRow()) });
            errorLevel = ErrorLevel.WARNING;
        } else {
            errorLevel = ErrorLevel.SUCCESS;
        }
        
        // 5.画面に入力された検索条件で接触履歴（入力）リストを取得する
        IfaInquirySearchForManagerSql005RequestModel sql005Req = new IfaInquirySearchForManagerSql005RequestModel();
        BeanUtils.copyProperties(dtoReq, sql005Req);
        // 仲介業者コード
        sql005Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 支店コード
        sql005Req.setBranchCodeList(splitBranchCode(dtoReq.getBranchCode()));
        // 営業員コード
        sql005Req.setEmpCodeList(splitEmpCode(dtoReq.getEmpCode()));
        // 顧客名（全角半角）
        sql005Req.setCustomerName(dtoReq.getCustomerNameKanjiKana());
        // 顧客名検索オプション
        sql005Req.setCustomerNameSearchType(dtoReq.getCustomerNameKanjiKanaTerms());
        // クレーム/リクエスト
        switch (dtoReq.getCr()) {
            // 画面.クレーム/リクエスト＝'C'の場合
            case ONE : {
                sql005Req.setCream(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'R'の場合
            case TWO : {
                sql005Req.setRequest(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'CR'の場合
            case THREE : {
                sql005Req.setCream(ONE);
                sql005Req.setRequest(ONE);
                break;
            }
            default : break;
        }
        // 重要度
        sql005Req.setJuuyoudo(juuyoudoList);
        // 対応ステータス
        sql005Req.setTaiouSts(taiouStsList);
        // 権限コード
        sql005Req.setPrivId(privId);
        // 取得最大件数
        sql005Req.setRownum(MAX_COUNT_DISPLAY);
        // 仲介業者営業員リスト
        sql005Req.setBrokerChargeList(brokerChargeList);
        
        List<IfaInquirySearchForManagerSql005ResponseModel> sql005Res = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();
        sql005Res = dao.selectIfaInquirySearchForManagerSql005(sql005Req);
        if (sql005Res != null && CollectionUtils.isNotEmpty(sql005Res)) {
            
            List<IfaInquirySearchForManagerSql005ResponseModel> ifaInquiryList =
                new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();

            ifaInquiryList = sql005Res.stream()
                .collect(Collectors.groupingBy(model -> new GroupKey(model.getIfaToiawaseNo(), model.getTourokuGroup()),
                    LinkedHashMap::new, Collectors.collectingAndThen(Collectors.toList(), list -> {
                        try {
                            IfaInquirySearchForManagerSql005ResponseModel merged = new IfaInquirySearchForManagerSql005ResponseModel();
                            // 1.最初の要素の共通フィールド（例えば、部店コード、口座番号など）をコピーします。                        
                            BeanUtils.copyProperties(list.get(0), merged);

                            // 2.逆順に回答内容を連結する
                            List<String> contentList = new ArrayList<>(list.size());
                            String minTime = null;
                            String minUser = null;

                            // 3.最も早い回答時間に対応する記録を見つける
                            for (IfaInquirySearchForManagerSql005ResponseModel item : list) {
                                // 回答内容を収集する
                                if (item.getKaitouNaiyou() != null) {
                                    contentList.add(checkValue(item.getKaitouNaiyou()));
                                }
                                
                                // 最も早い回答時間を探す
                                String currentTime = item.getKaitouNichiji();
                                if (currentTime != null) {
                                    if (minTime == null || currentTime.compareTo(minTime) < 0) {
                                        minTime = currentTime;
                                        minUser = item.getKaitouUserName();
                                    }
                                }
                            }

                            // 4.時間とユーザー名を設定する
                            // 倒序で内容を結合する
                            Collections.reverse(contentList);
                            merged.setKaitouNaiyou(String.join("", contentList));

                            // 時間とユーザーを設定する
                            merged.setKaitouNichiji(minTime);
                            merged.setKaitouUserName(minUser);

                            return merged;
                        } catch (Exception e) {
                            throw new RuntimeException("Merge error", e);
                        }
                    })))
                .values().stream().collect(Collectors.toList());
            
            List<IfaInquirySearchForManagerSql005ResponseModel> ifaInquirySearchModelList = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();
            for (int i = 0; i < ifaInquiryList.size(); i++) {
                // 現在の要素をターゲットリストに追加する
                IfaInquirySearchForManagerSql005ResponseModel current = ifaInquiryList.get(i);
                ifaInquirySearchModelList.add(current);

                // 現在のグループの最後の要素かどうかを判断する
                boolean isLastInGroup = false;
                if (i == ifaInquiryList.size() - 1) {
                    // 現在の要素はリストの最後の要素です
                    isLastInGroup = true;
                } else {
                    // 次の要素が異なるグループに属しているかどうかをチェックする
                    IfaInquirySearchForManagerSql005ResponseModel next = ifaInquiryList.get(i + 1);
                    isLastInGroup = !StringUtils.equals(current.getIfaToiawaseNo(), next.getIfaToiawaseNo());
                }

                // 条件を満たすときにマークオブジェクトを追加する
                if (StringUtils.equals(current.getTourokuGroup(), "1") && isLastInGroup) {
                    ifaInquirySearchModelList.add(createMarker(current));
                }
            }
            
            List<IfaInquirySearchListDto> ifaInquirySearchList = new ArrayList<IfaInquirySearchListDto>();
            IfaInquirySearchListDto ifaInquirySearchListDto = null;
            List<IfaInquiryDetailListDto> ifaInquiryDetailList = new ArrayList<IfaInquiryDetailListDto>();
            // ローカル変数に現在と前の要素を格納し、get呼び出し回数を削減
            List<IfaInquirySearchForManagerSql005ResponseModel> modelList = ifaInquirySearchModelList;
            for (int j = 0; j < modelList.size(); j++) {
                IfaInquirySearchForManagerSql005ResponseModel currentModel = modelList.get(j);
                IfaInquirySearchForManagerSql005ResponseModel previousModel = (j > 0) ? modelList.get(j - 1) : null;

                // 新規問合せ番号の判定
                if (j == 0 || !StringUtils.equals(currentModel.getIfaToiawaseNo(), previousModel.getIfaToiawaseNo())) {
                    ifaInquirySearchListDto = createNewDto(currentModel); // DTO生成処理をメソッド化
                    ifaInquirySearchList.add(ifaInquirySearchListDto);
                    ifaInquiryDetailList = ifaInquirySearchListDto.getInquiryDetailList(); // 新規作成されたリストを直接使用
                }

                // 問合せ詳細情報を作成しリストに追加
                IfaInquiryDetailListDto detail = createDetailDto(currentModel); // 詳細生成処理をメソッド化
                ifaInquiryDetailList.add(detail);
            }
            
            IfaInquirySearchForManagerA004ResponseDto res = new IfaInquirySearchForManagerA004ResponseDto();
            
            res.setInquirySearchList(ifaInquirySearchList);
            a004ListRes.add(res);
        }

        dtoRes = IfaCommonUtil.createDataList(a004ListRes, errorLevel, returnCode, message);
        
        return dtoRes;
    }

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaInquirySearchForManagerA006RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA006ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    @Override
    public DataList<IfaInquirySearchForManagerA006ResponseDto> csvOutputA006(
        IfaInquirySearchForManagerA006RequestDto dtoReq, String fwSessionId) throws Exception {
        
        DataList<IfaInquirySearchForManagerA006ResponseDto> dtoRes = new DataList<IfaInquirySearchForManagerA006ResponseDto>();
        List<IfaInquirySearchForManagerA006ResponseDto> dtoResList = new ArrayList<IfaInquirySearchForManagerA006ResponseDto>();
        List<IfaInquirySearchForManagerCsvItems> csvList = new ArrayList<IfaInquirySearchForManagerCsvItems>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInquirySearchForManagerServiceImpl.csvOutputA006");
        }
        
        // 1.画面.問合せ日From～問合せ日Toの期間が6ヶ月を超えていないかチェックを行う
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD);
        if (LocalDate.parse(dtoReq.getInquiryDateYmTo(), dateFormatter).minusMonths(DATE_RANGE_LIMIT_MONTH)
            .compareTo(LocalDate.parse(dtoReq.getInquiryDateYmFrom(), dateFormatter)) > 0) {
            // 6ヶ月を超えた場合：メッセージを表示し処理終了
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, MESSAGE_INVALID_DATE_RANGE, IfaCommonUtil
                .getMessage(MESSAGE_INVALID_DATE_RANGE, new String[] { ITEM_NAME_DATE_RANGE, DATE_RANGE_LIMIT_TEXT }));
        }
        
        // 2.画面.問合せ日（To） ＜ 画面.問合せ日（From）の場合、メッセージを表示し、処理終了。
        if (LocalDate.parse(dtoReq.getInquiryDateYmTo(), dateFormatter).compareTo(LocalDate.parse(dtoReq.getInquiryDateYmFrom(), dateFormatter)) < 0) {
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, ERRORS_DATE_SPECIFYFROMTO, IfaCommonUtil
                .getMessage(ERRORS_DATE_SPECIFYFROMTO, new String[] { QUESTION_DATE }));
        }
        
        // 3.ユーザ共通情報.権限コード != SBI証券本店の場合、FCT030.仲介業者営業員リストを取得
        String privId = IfaCommonUtil.getUserAccount().getMedUsers().getPrivId();
        List<BrokerCharge> brokerChargeList = null;
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            InputFct030Dto fct030In = new InputFct030Dto();
            OutputFct030Dto fct030Out = fct030.getData(fct030In);
            brokerChargeList = fct030Out.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL,
                    ERRORS_CMN_IFAAGENTCODES_NOTEXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
            }
        }
        
        // ３．CSVダウンロードMAX件数を取得し、取得最大件数（閾値）にセットする。
        InputFct038Dto fct038In = new InputFct038Dto();
        fct038In.setScreenId(SCREEN_ID);
        fct038In.setUserAuthority(privId);
        OutputFct038Dto fct038Out = fct038.getData(fct038In);
        int csvDownloadNum = fct038Out.getCsvDownloadNum();
        
        String returnCode = "";
        String message = "";
        ErrorLevel errorLevel = null;
        
        // 重要度リストを取得する
        List<IfaInquirySearchForManagerRequestSelected> juuyoudoList = dtoReq.getJuuyoudo().stream()
            .filter(obj -> Boolean.TRUE.equals(obj.getIsSelected()))
            .collect(Collectors.toList());
        
        // 対応ステータスリストを取得する
        List<IfaInquirySearchForManagerRequestSelected> taiouStsList = dtoReq.getTaiouSts().stream()
            .filter(obj -> Boolean.TRUE.equals(obj.getIsSelected()))
            .collect(Collectors.toList());
        
        // 4.画面に入力された検索条件で画面表示明細件数を取得する
        IfaInquirySearchForManagerSql007RequestModel sql007Req = new IfaInquirySearchForManagerSql007RequestModel();
        BeanUtils.copyProperties(dtoReq, sql007Req);
        // 仲介業者コード
        sql007Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 支店コード
        sql007Req.setBranchCodeList(splitBranchCode(dtoReq.getBranchCode()));
        // 営業員コード
        sql007Req.setEmpCodeList(splitEmpCode(dtoReq.getEmpCode()));
        // 顧客名（全角半角）
        sql007Req.setCustomerName(dtoReq.getCustomerNameKanjiKana());
        // 顧客名検索オプション
        sql007Req.setCustomerNameSearchType(dtoReq.getCustomerNameKanjiKanaTerms());
        // クレーム/リクエスト
        switch (dtoReq.getCr()) {
            // 画面.クレーム/リクエスト＝'C'の場合
            case ONE : {
                sql007Req.setCream(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'R'の場合
            case TWO : {
                sql007Req.setRequest(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'CR'の場合
            case THREE : {
                sql007Req.setCream(ONE);
                sql007Req.setRequest(ONE);
                break;
            }
            default : break;
        }
        // 重要度
        sql007Req.setJuuyoudo(juuyoudoList);
        // 対応ステータス
        sql007Req.setTaiouSts(taiouStsList);
        // 権限コード
        sql007Req.setPrivId(privId);
        // 仲介業者営業員リスト
        sql007Req.setBrokerChargeList(brokerChargeList);
        
        IfaInquirySearchForManagerSql007ResponseModel sql007Res = new IfaInquirySearchForManagerSql007ResponseModel();
        sql007Res = dao.selectIfaInquirySearchForManagerSql007(sql007Req);
        
        // SQL007.総件数が0件の場合、エラーメッセージを表示し、処理終了
        if (sql007Res == null || sql007Res.getTotalRow() == 0) {
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
        } else if (sql007Res.getTotalRow() > csvDownloadNum) {
            // 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 {1}：SQL007.総件数
            returnCode = WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM;
            message = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                new String[] { Integer.toString(csvDownloadNum), Integer.toString(sql007Res.getTotalRow()),
                    Integer.toString(csvDownloadNum)});
            errorLevel = ErrorLevel.WARNING;
        } else {
            // 検索結果が{0}件のcsv出力を行います。 {0}：SQL007.総件数
            returnCode = WARNINGS_DATALIST_CSV_OUTPUT;
            message = IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OUTPUT,
                new String[] {Integer.toString(sql007Res.getTotalRow())});
            errorLevel = ErrorLevel.WARNING;
        }
        
        // 5.画面に入力された検索条件で接触履歴（入力）リストを取得する
        IfaInquirySearchForManagerSql005RequestModel sql005Req = new IfaInquirySearchForManagerSql005RequestModel();
        BeanUtils.copyProperties(dtoReq, sql005Req);
        // 仲介業者コード
        sql005Req.setBrokerCodeList(splitBrokerCode(dtoReq.getBrokerCode()));
        // 支店コード
        sql005Req.setBranchCodeList(splitBranchCode(dtoReq.getBranchCode()));
        // 営業員コード
        sql005Req.setEmpCodeList(splitEmpCode(dtoReq.getEmpCode()));
        // 顧客名（全角半角）
        sql005Req.setCustomerName(dtoReq.getCustomerNameKanjiKana());
        // 顧客名検索オプション
        sql005Req.setCustomerNameSearchType(dtoReq.getCustomerNameKanjiKanaTerms());
        // クレーム/リクエスト
        switch (dtoReq.getCr()) {
            // 画面.クレーム/リクエスト＝'C'の場合
            case ONE : {
                sql005Req.setCream(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'R'の場合
            case TWO : {
                sql005Req.setRequest(ONE);
                break;
            }
            // 画面.クレーム/リクエスト＝'CR'の場合
            case THREE : {
                sql005Req.setCream(ONE);
                sql005Req.setRequest(ONE);
                break;
            }
            default : break;
        }
        // 重要度
        sql005Req.setJuuyoudo(juuyoudoList);
        // 対応ステータス
        sql005Req.setTaiouSts(taiouStsList);
        // 権限コード
        sql005Req.setPrivId(privId);
        // 取得最大件数
        sql005Req.setRownum(csvDownloadNum);
        // 仲介業者営業員リスト
        sql005Req.setBrokerChargeList(brokerChargeList);
        
        List<IfaInquirySearchForManagerSql005ResponseModel> sql005Res = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();
        
        sql005Res = dao.selectIfaInquirySearchForManagerSql005(sql005Req);
        if (sql005Res != null && CollectionUtils.isNotEmpty(sql005Res)) {
            
            List<IfaInquirySearchForManagerSql005ResponseModel> ifaInquiryList = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();

            ifaInquiryList = sql005Res.stream()
                .collect(Collectors.groupingBy(model -> new GroupKey(model.getIfaToiawaseNo(), model.getTourokuGroup()),
                    LinkedHashMap::new, Collectors.collectingAndThen(Collectors.toList(), list -> {
                        try {
                            IfaInquirySearchForManagerSql005ResponseModel merged = new IfaInquirySearchForManagerSql005ResponseModel();
                            // 1.最初の要素の共通フィールド（例えば、部店コード、口座番号など）をコピーします。
                            BeanUtils.copyProperties(list.get(0), merged);

                            // 2.逆順に回答内容を連結する
                            List<String> contentList = new ArrayList<>(list.size());
                            String minTime = null;
                            String minUser = null;

                            // 3.最も早い回答時間に対応する記録を見つける
                            for (IfaInquirySearchForManagerSql005ResponseModel item : list) {
                                // 回答内容を収集する
                                if (item.getKaitouNaiyou() != null) {
                                    contentList.add(checkValue(item.getKaitouNaiyou()));
                                }
                                
                                // 最も早い回答時間を探す
                                String currentTime = item.getKaitouNichiji();
                                if (currentTime != null) {
                                    if (minTime == null || currentTime.compareTo(minTime) < 0) {
                                        minTime = currentTime;
                                        minUser = item.getKaitouUserName();
                                    }
                                }
                            }

                            // 4.時間とユーザー名を設定する
                            // 倒序で内容を結合する
                            Collections.reverse(contentList);
                            merged.setKaitouNaiyou(String.join("", contentList));

                            // 時間とユーザーを設定する
                            merged.setKaitouNichiji(minTime);
                            merged.setKaitouUserName(minUser);

                            return merged;
                        } catch (Exception e) {
                            throw new RuntimeException("Merge error", e);
                        }
                    })))
                .values().stream().collect(Collectors.toList());
            
            List<IfaInquirySearchForManagerSql005ResponseModel> ifaInquirySearchModelList = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();
            for (int i = 0; i < ifaInquiryList.size(); i++) {
                // 現在の要素をターゲットリストに追加する
                IfaInquirySearchForManagerSql005ResponseModel current = ifaInquiryList.get(i);
                ifaInquirySearchModelList.add(current);

                // 現在のグループの最後の要素かどうかを判断する
                boolean isLastInGroup = false;
                if (i == ifaInquiryList.size() - 1) {
                    // 現在の要素はリストの最後の要素です
                    isLastInGroup = true;
                } else {
                    // 次の要素が異なるグループに属しているかどうかをチェックする
                    IfaInquirySearchForManagerSql005ResponseModel next = ifaInquiryList.get(i + 1);
                    isLastInGroup = !StringUtils.equals(current.getIfaToiawaseNo(), next.getIfaToiawaseNo());
                }

                // 条件を満たすときにマークオブジェクトを追加する
                if (StringUtils.equals(current.getTourokuGroup(), "1") && isLastInGroup) {
                    ifaInquirySearchModelList.add(createMarker(current));
                }
            }
            
            for (IfaInquirySearchForManagerSql005ResponseModel inquirySearch : ifaInquirySearchModelList) {
                IfaInquirySearchForManagerCsvItems csvItems = new IfaInquirySearchForManagerCsvItems();
                BeanUtils.copyProperties(inquirySearch, csvItems);
                // カテゴリー名称（大）+"　"(全角ブランク1文字)+カテゴリー名称（中）+"　"(全角ブランク1文字)+カテゴリー名称（小）
                csvItems.setToiawaseMei(buildCategory(inquirySearch.getToiawaseDMei(), inquirySearch.getToiawaseMei(), inquirySearch.getToiawaseSMei()));
                // 重要度   @表示パターン:1
                csvItems.setJuuyoudo(codeListService.getValue(JUUYOUDO, inquirySearch.getJuuyoudo(), DISPLAY_PATTERN_1));
                // 方向   @表示パターン:2
                csvItems.setHoukou(codeListService.getValue(NYUUDEN_HOUKOU, inquirySearch.getHoukou(), DISPLAY_PATTERN_2));
                // 対応ステータス   @表示パターン:2
                csvItems.setTaiouSts(codeListService.getValue(TAIOU_STS, inquirySearch.getTaiouSts(), DISPLAY_PATTERN_2));
                // クレーム/リクエスト判定ロジック
                boolean isCream = StringUtils.equals(inquirySearch.getCream(), "1");
                boolean isRequest = StringUtils.equals(inquirySearch.getRequest(), "1");
                if (isCream && isRequest) {
                    csvItems.setCr("クレーム/リクエスト");
                } else if (isCream || isRequest) {
                    csvItems.setCr(isCream ? "クレーム" : "リクエスト");
                }
                if (StringUtils.isEmpty(inquirySearch.getTourokuGroup())) {
                    csvItems.setNyuuryokuNichiji(parseToIsoDate(inquirySearch.getToiawaseNichiji()));
                    csvItems.setNaiyou(inquirySearch.getToiawaseNaiyou());
                    csvItems.setNyuuryokuSha(inquirySearch.getToiawaseUserName());
                } else if (StringUtils.equals(inquirySearch.getTourokuGroup(), "0")) {
                    csvItems.setNyuuryokuNichiji(parseToIsoDate(inquirySearch.getToiawaseNichiji()));
                    csvItems.setNaiyou(checkValue(inquirySearch.getToiawaseNaiyou()) + checkValue(inquirySearch.getKaitouNaiyou()));
                    csvItems.setNyuuryokuSha(inquirySearch.getToiawaseUserName());
                } else {
                    csvItems.setNyuuryokuNichiji(parseToIsoDate(inquirySearch.getKaitouNichiji()));
                    csvItems.setNaiyou(inquirySearch.getKaitouNaiyou());
                    csvItems.setNyuuryokuSha(inquirySearch.getKaitouUserName());
                }
                csvList.add(csvItems);
                if (csvList.size() >= csvDownloadNum) break;
            }
            
            
        }
        
        // CSV出力
        DataList<IfaInquirySearchForManagerCsvItems> csvDownList = new DataList<IfaInquirySearchForManagerCsvItems>();
        csvDownList.setDataList(csvList);
        
        
        CsvOutPutUtil csvOutPutUtil = new IfaInquirySearchForManagerCsvOut(complianceService);
        
        String csvFileName = csvOutPutUtil.doCreateCsvFile(csvDownList, fwSessionId, IfaCommonUtil.getUserAccount().getUserId(), null);
        
        dtoRes = IfaCommonUtil.createDataList(dtoResList, errorLevel, returnCode, message);

        dtoRes.setTitle(csvFileName);

        dtoRes.setTotalSize(csvDownloadNum);

        return dtoRes;
    }
    
    /**
     * カテゴリー名称（大）+"　"(全角ブランク1文字)+カテゴリー名称（中）+"　"(全角ブランク1文字)+カテゴリー名称（小）
     * @param large カテゴリー名称（大）
     * @param medium カテゴリー名称（中）
     * @param small カテゴリー名称（小）
     * @return String カテゴリ
     */
    private String buildCategory(String large, String medium, String small) {
        
        List<String> categories = new ArrayList<>(3);
        
        if (large != null && !large.isEmpty()) categories.add(large);
        if (medium != null && !medium.isEmpty()) categories.add(medium);
        if (small != null && !small.isEmpty()) categories.add(small);
        
        return String.join("　", categories);
        
    }
    
    /** 仲介業者コード編集 */
    private List<String> splitBrokerCode(String brokerCode) {

        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(brokerCode)) {
            brokerCodeList.addAll(Arrays.asList(brokerCode.split(",")));
            return brokerCodeList;
        }
        return null;
    }
    
    /** 支店コード編集 */
    private List<String> splitBranchCode(String branchCode) {

        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(branchCode)) {
            brokerCodeList.addAll(Arrays.asList(branchCode.split(",")));
            return brokerCodeList;
        }
        return null;
    }
    
    /** 営業員コード編集 */
    private List<String> splitEmpCode(String empCode) {

        List<String> brokerCodeList = new ArrayList<String>();
        if (!StringUtil.isNullOrEmpty(empCode)) {
            brokerCodeList.addAll(Arrays.asList(empCode.split(",")));
            return brokerCodeList;
        }
        return null;
    }
    
    /**
     * "-"がNULLを処理する
     * @param value
     * @return "-" -> ""
     */
    private static String checkValue(String value) {
        return "-".equals(value) || value == null ? "" : value;
    }
    
    /**
     * 日付の変換する
     * @param String YYYY-MM-DD HH:MM:SS
     * @return String YYYY/MM/DD HH:MM:SS
     */
    private static String parseToIsoDate(String dateTimeStr) {
        if (StringUtils.isNotEmpty(dateTimeStr)) {
            return dateTimeStr.replaceAll("-", "/");
        }
        return null;
    }
    
    /**
     * GroupKey 複合主キー、グループ化用
     */
    private static class GroupKey {
        private final String ifaToiawaseNo;
        private final String tourokuGroup;

        GroupKey(String ifaToiawaseNo, String tourokuGroup) {
            this.ifaToiawaseNo = ifaToiawaseNo;
            this.tourokuGroup = tourokuGroup;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GroupKey)) return false;
            GroupKey that = (GroupKey) o;
            return Objects.equals(ifaToiawaseNo, that.ifaToiawaseNo) &&
                   Objects.equals(tourokuGroup, that.tourokuGroup);
        }

        @Override
        public int hashCode() {
            return Objects.hash(ifaToiawaseNo, tourokuGroup);
        }
    }
    
    /**
     * 登録グループ=0を追加する
     * @param source
     * @return IfaInquirySearchForManagerSql005ResponseModel
     */
    private IfaInquirySearchForManagerSql005ResponseModel createMarker(IfaInquirySearchForManagerSql005ResponseModel source) {
        IfaInquirySearchForManagerSql005ResponseModel marker = new IfaInquirySearchForManagerSql005ResponseModel();
        BeanUtils.copyProperties(source, marker);
        marker.setKaitouNaiyou(StringUtil.EMPTY_STRING);
        marker.setTourokuGroup(ZERO);
        return marker;
    }
    
    /** 新規SearchListDtoを作成しプロパティを設定 */
    private IfaInquirySearchListDto createNewDto(IfaInquirySearchForManagerSql005ResponseModel model) {
        IfaInquirySearchListDto dto = new IfaInquirySearchListDto();
        BeanUtils.copyProperties(model, dto);
        
        // 詳細リスト初期化
        List<IfaInquiryDetailListDto> detailList = new ArrayList<>();
        dto.setInquiryDetailList(detailList);

        // カテゴリ名称を結合設定（大+中+小）
        dto.setToiawaseMei(buildCategory(model.getToiawaseDMei(), model.getToiawaseMei(), model.getToiawaseSMei()));
        
        // コードリストから表示値を取得
        dto.setJuuyoudo(codeListService.getValue(JUUYOUDO, model.getJuuyoudo(), DISPLAY_PATTERN_1));
        dto.setHoukou(codeListService.getValue(NYUUDEN_HOUKOU, model.getHoukou(), DISPLAY_PATTERN_2));
        dto.setTaiouSts(codeListService.getValue(TAIOU_STS, model.getTaiouSts(), DISPLAY_PATTERN_2));

        // クレーム/リクエスト判定ロジック
        boolean isCream = StringUtils.equals(model.getCream(), ONE);
        boolean isRequest = StringUtils.equals(model.getRequest(), ONE);
        if (isCream && isRequest) {
            dto.setCr("クレーム/リクエスト");
        } else if (isCream || isRequest) {
            dto.setCr(isCream ? "クレーム" : "リクエスト");
        }
        
        return dto;
    }

    /** 問合せ詳細情報Dtoを作成 */
    private IfaInquiryDetailListDto createDetailDto(IfaInquirySearchForManagerSql005ResponseModel model) {
        IfaInquiryDetailListDto detail = new IfaInquiryDetailListDto();
        String tourokuGroup = model.getTourokuGroup();
        
        // 登録グループに応じて設定値を切り替え
        if (StringUtils.isEmpty(tourokuGroup)) {
            detail.setNyuuryokuNichiji(model.getToiawaseNichiji());
            detail.setNaiyou(model.getToiawaseNaiyou());
            detail.setNyuuryokuSha(model.getToiawaseUserName());
        } else if (StringUtils.equals(tourokuGroup, ZERO)) {
            String combinedNaiyou = checkValue(model.getToiawaseNaiyou()) + checkValue(model.getKaitouNaiyou());
            detail.setNyuuryokuNichiji(model.getToiawaseNichiji());
            detail.setNaiyou(combinedNaiyou);
            detail.setNyuuryokuSha(model.getToiawaseUserName());
        } else {
            detail.setNyuuryokuNichiji(model.getKaitouNichiji());
            detail.setNaiyou(model.getKaitouNaiyou());
            detail.setNyuuryokuSha(model.getKaitouUserName());
        }
        
        return detail;
    }
}
