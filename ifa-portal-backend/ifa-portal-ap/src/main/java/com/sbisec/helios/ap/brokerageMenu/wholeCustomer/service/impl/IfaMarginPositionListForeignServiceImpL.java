package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.athena.enums.CountryCode;
import com.sbisec.helios.ap.athena.ifa.ForeignAccountService;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.account.ListMarginPositionsResp;
import com.sbisec.helios.ap.athena.protocol.account.dto.Position;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.GetForeignStockSecuritiesResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.AmericaStockUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaMarginPositionListForeignDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001RequestModelCourseSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001RequestModel_BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaMarginPositionListForeignSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoRequestCourseSelected;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignDto_MarginPositionListForeign;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaMarginPositionListForeignService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMarginPositionListForeignCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 *
 * @author 島崎聡士 2023/11/30 新規作成
 */
@Component(value = "cmpIfaMarginPositionListForeignService")
public class IfaMarginPositionListForeignServiceImpL implements IfaMarginPositionListForeignService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaMarginPositionListForeignServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 正常終了 */
    private static final String SUCCESS_MESSAGE = "";
    
    /** 取引コースを選択してください。 */
    private static final String ERRORS_DMS_ACCOUNT_COURSE_INSUFFICIENT = "errors.dms.accountCourse.insufficient";
    
    /** {0}を正しく入力して下さい。 */
    private static final String ERRORS_ACCURATELY = "errors.accurately";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 仲介業者コード／営業員コードを正しく入力して下さい。 */
    private static final String ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE = "errors.dms.ifaAgentCodes.unavailable";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** アクセスが集中しているため口座情報が取得できませんでした。<br>部店コード:[{0}] 口座番号:[{1}] */
    private static final String W0001 = "W0001";
    
    /** 信用建玉件数が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String INFO_HOLD_MARGIN_LIST_NOT_FOUND = "info.holdMarginList.notfound";
    
    /** 外国株式信用建玉明細一覧例外 LOGGER.error */
    private static final String LISTMARGINPOSITIONS_EXCEPTION =
            "ForeignAccountServiceImpl.listMarginPositions"
            + " CometMarginPositionServiceImpl.listMarginPositions Exception[{}]";
    
    /** 外国株式信用建玉明細一覧例外 LOGGER.info */
    private static final String EXCEPTION_OCCURED = "Exception occured.";
    
    // --------------------------------
    // 変数定義
    // --------------------------------
    /** 権限コード：SBI証券本店 の場合 */
    private static final String AUTH_CODE_SBI = "1";

    /** 権限コード：仲介業者.内部管理責任者 の場合 */
    private static final String AUTH_CODE_BROKER_INTERNAL_ADMIN = "3";

    /** 権限コード：仲介業者.営業責任者 の場合 */
    private static final String AUTH_CODE_SALES_MANAGER = "4";

    /** 権限コード：仲介業者.外務員 の場合 */
    private static final String AUTH_CODE_SECURITIES_REPRESENTATIVE = "5";
    
    /** ハイフン */
    private static final String HYPHEN = "-";
    
    /** スラッシュ */
    private static final String SLASH = "/";
    
    /** チェック用日付パターン（yyyy-MM-dd） */
    private static final String DATE_PATTERN = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    
    /** 日付値「9999-12-31」 */
    private static final String INVALID_DATE = "9999-12-31";
    
    /** 日付値「9999-12-31」時の設定値 */
    private static final String DATE_HYPHEN = "----/--/--";
    
    /** 米国 */
    private static final String US = CountryCode.US.getId();
    
    /** 米国 */
    private static final String SCREEN_ID = "SUB020302_0303-01";
    
    /** ティッカー */
    private static final String TICKER = "ティッカー";
    
    /** 区分.契約締結前交付書面コード */
    private static final String PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";
    
    /** 区分.預り区分（外国） */
    private static final String FOREIGN_DEPOSIT_TYPE = "FOREIGN_DEPOSIT_TYPE";

    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * API呼び出しクラス
     */
    @Autowired
    private ForeignStockService foreignStockService;
    
    /**
     * API呼び出しクラス
     */
    @Autowired
    ForeignAccountService foreignAccountService;
    
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
    private IfaMarginPositionListForeignDao dao;
    
    /**
     * 区分値変換
     */
    @Autowired
    private CodeListService codeListService;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMarginPositionListForeignA002aDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA002aDtoResponse
     * model リクエスト：IfaMarginPositionListForeignSql001RequestModel
     * model レスポンス：IfaMarginPositionListForeignSql001ResponseModel
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA002aDtoResponse
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginPositionListForeignA002aDtoResponse> displayA002a(
            IfaMarginPositionListForeignA002aDtoRequest dtoReq) throws Exception {
        
        DataList<IfaMarginPositionListForeignA002aDtoResponse> dtoRes =
                new DataList<IfaMarginPositionListForeignA002aDtoResponse>();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPositionListForeignServiceImplL.displayA002ManagingContractList");
        }
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        //　ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // 仲介業者営業員リスト
        InputFct030Dto fct030InputDto = new InputFct030Dto();
        OutputFct030Dto fct030Result = fct030.getData(fct030InputDto);
        
        // 取引コースの判定
        if (isNullCourse(dtoReq.getCourse(), errorInfo)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        // ティッカーの存在判定
        if (!StringUtil.isNullOrEmpty(dtoReq.getTicker())) {
            // API002 GetForeignStockSecurities
            GetForeignStockSecuritiesResp resApi002 = new GetForeignStockSecuritiesResp();
            try {
                resApi002 = foreignStockService.getForeignStockSecurities(US, dtoReq.getTicker());
            } catch (Exception e) {
                AmericaStockUtil.getBussinessException(dtoRes, e);
                return dtoRes;
            }
            // 銘柄情報取得チェック 外国株式銘柄マスタ取得API
            if (Objects.isNull(resApi002.getSecurities())) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                        IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] {TICKER}));
            }
        }
        // FCT030 参照可能チェック
        if (canReferenceUserCheck(dtoReq.getBrokerCode(), dtoReq.getEmpCode(), errorInfo, userAccount, fct030Result)) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, errorInfo[0], errorInfo[1]);
        }
        // 顧客口座情報検索
        // CSVダウンロード件数取得
        int csvDownloadNum = callFct038(userAccount);
        
        // SQL001(顧客口座情報の検索)
        DataList<IfaMarginPositionListForeignSql001ResponseModel> selSql001Res = getManagingContractList(dtoReq, userAccount, fct030Result);
        // 取得件数チェック
        if (selSql001Res == null || selSql001Res.getDataList().size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO, ERRORS_DATA_LIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND));
        } else if (selSql001Res.getDataList().size() > csvDownloadNum) {
            // 顧客口座情報リストの件数がCSVダウンロードMAX件数より大きい場合：ワーニングメッセージを作成
            int totalRow = selSql001Res.getDataList().size();
            errorInfo[0] = WARNINGS_DATA_LIST_OVER_MAX_ROWNUM;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0],
                    new String[] { String.valueOf(csvDownloadNum), String.valueOf(totalRow) });
            // 顧客口座情報リストの前からCSVダウンロードMAX件数をレスポンス対象とする
            selSql001Res.setDataList(selSql001Res.getDataList().subList(0, csvDownloadNum));
        }
        List<IfaMarginPositionListForeignA002aDtoResponse> responseList = new ArrayList<>();
        for (IfaMarginPositionListForeignSql001ResponseModel model : selSql001Res.getDataList()) {
            IfaMarginPositionListForeignA002aDtoResponse response = new IfaMarginPositionListForeignA002aDtoResponse();
            BeanUtils.copyProperties(response, model);
            responseList.add(response);
        }
        // 顧客口座情報リストの返却
        if (!StringUtil.isNullOrEmpty(errorInfo[0])) {
            // 件数超過でワーニングの場合
            return IfaCommonUtil.createDataList(responseList, ErrorLevel.WARNING, errorInfo[0], errorInfo[1]);
        }
        return IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                SUCCESS_MESSAGE);
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMarginPositionListForeignA002bDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA002bDtoResponse
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA002bDtoResponse
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginPositionListForeignA002bDtoResponse> displayA002b(
            IfaMarginPositionListForeignA002bDtoRequest dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPositionListForeignServiceImplL.displayA002AppMarginPositionListForeign");
        }
        // エラー情報の初期化（[0]：エラーコード、[1]：エラーメッセージ）
        String[] errorInfo = new String[2];
        
        // 信用建玉情報検索
        String butenCode = dtoReq.getButenCode();
        String accountNumber = StringUtils.leftPad(dtoReq.getAccountNumber(), 7, "0");
        String countryCode = US;
        String ticker = dtoReq.getTicker();
        ListMarginPositionsResp apiRes = new ListMarginPositionsResp();
        // 外国株式信用建玉明細一覧取得(ListMarginPositions)を実行する
        try {
            apiRes = foreignAccountService.listMarginPositions(butenCode, accountNumber, countryCode, ticker, null,
                    null, null, null, null, null, null);
        } catch (Exception e) {
            LOGGER.error(LISTMARGINPOSITIONS_EXCEPTION, e.getMessage());
            LOGGER.error(EXCEPTION_OCCURED);
            LOGGER.info(EXCEPTION_OCCURED, e);
            if (e instanceof AthenaBusinessException) {
                errorInfo[0] = W0001;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0], new String[] { butenCode, accountNumber });
                return IfaCommonUtil.createDataList(null, ErrorLevel.WARNING, errorInfo[0], errorInfo[1]);
            }
        }
        // 信用建玉画面表示情報編集
        List<IfaMarginPositionListForeignA002bDtoResponse> dtoResList =
                editMarginPositionListForeign(dtoReq, apiRes);
        
        return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                SUCCESS_MESSAGE);
    }
    
    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaMarginPositionListForeignA006aDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA006aDtoResponse
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA006aDtoResponse
     * @exception Exception 例外が発生した場合
     */
    @Override
    public DataList<IfaMarginPositionListForeignA006aDtoResponse> csvOutputA006(
            IfaMarginPositionListForeignA006aDtoRequest dtoReq, String fwSessionId) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaMarginPositionListForeignServiceImplL.csvOutputA006");
        }
        //　ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        DataList<IfaMarginPositionListForeignDto_MarginPositionListForeign> exportList =
                new DataList<IfaMarginPositionListForeignDto_MarginPositionListForeign>();
        exportList.setDataList(convertCsvOutModel(dtoReq.getMarginPositionListForeignList()));
        
        List<IfaMarginPositionListForeignA006aDtoResponse> responseList =
                new ArrayList<IfaMarginPositionListForeignA006aDtoResponse>();
        IfaMarginPositionListForeignA006aDtoResponse response = new IfaMarginPositionListForeignA006aDtoResponse();
        IfaMarginPositionListForeignCsvOut csvOut = new IfaMarginPositionListForeignCsvOut(complianceService);
        String csvFileName = csvOut.doCreateCsvFile(exportList, fwSessionId, userAccount.getUserId(), null);
        responseList.add(response);
        DataList<IfaMarginPositionListForeignA006aDtoResponse> res = 
                IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                SUCCESS_MESSAGE);
        
        res.setTitle(csvFileName);
        return res;
    }
    
    /**
     * 取引コースが未設定の場合trueを返却する
     *
     * @param course 取引コース
     * @param errorInfo エラー情報
     * @return 取引コースの判定結果
     */
    private boolean isNullCourse(List<IfaMarginPositionListForeignA002aDtoRequestCourseSelected> course, 
            String[] errorInfo) {
        
        // 取引コースの判定
        if (CollectionUtils.isEmpty(course)) {
            errorInfo[0] = ERRORS_DMS_ACCOUNT_COURSE_INSUFFICIENT;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return true;
        }
        
        return false;
    }
    
    /**
     * 参照権限チェック処理
     *
     * @param brokerCode 仲介業者コード
     * @param empCode 営業員コード
     * @param errorInfo エラー情報
     * @param userAccount ユーザ共通情報
     * @param fct030Result 仲介業者営業員リスト
     * @throws Exception 例外
     * @throws InvocationTargetException 例外
     * @throws IllegalAccessException 例外
     */
    private boolean canReferenceUserCheck(String brokerCode, String empCode, 
            String[] errorInfo, UserAccount userAccount, OutputFct030Dto fct030Result) throws Exception {
        
        // 参照権限：SBI証券本店以外の場合のみチェックを行う
        if (StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            return false;
        }
        
        // 仲介業者営業員リストが取得できない場合はエラー
        if (fct030Result == null || fct030Result.getBrokerChargeList() == null
                || fct030Result.getBrokerChargeList().size() == 0) {
            errorInfo[0] = ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST;
            errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
            return true;
        }

        if (
                StringUtils.equals(AUTH_CODE_BROKER_INTERNAL_ADMIN, userAccount.getPrivId())
                || StringUtils.equals(AUTH_CODE_SALES_MANAGER, userAccount.getPrivId())
                || StringUtils.equals(AUTH_CODE_SECURITIES_REPRESENTATIVE, userAccount.getPrivId())
        ) {
            // 権限3, 4, 5の場合
            // 営業員リストにリクエストの仲介業者コードが存在しない場合はエラー
            if (!fct030Result.getBrokerChargeList().stream().filter(
                    s -> StringUtils.equals(s.getBrokerCode(), brokerCode))
                    .findFirst().isPresent()) {
                errorInfo[0] = ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
                return true;
            }


        } else {
            // 権限1, 3, 4, 5以外の場合
            // 営業員リストにリクエストの仲介業者コード/営業員コードの組み合わせが存在しない場合はエラー
            if (!fct030Result.getBrokerChargeList().stream().filter(
                    s -> StringUtils.equals(s.getBrokerCode(), brokerCode) && StringUtils.equals(s.getEmpCode(), empCode))
                    .findFirst().isPresent()) {
                errorInfo[0] = ERRORS_DMS_IFA_AGENT_CODES_UNAVAILABLE;
                errorInfo[1] = IfaCommonUtil.getMessage(errorInfo[0]);
                return true;
            }
        }
        return false;
    }
    
    /**
     * FCT038 CSVダウンロードMAX件数取得
     *
     * @param userAccount ユーザ共通情報
     * @return CSVダウンロードMAX件数
     */
    private int callFct038(UserAccount userAccount) {
        
        InputFct038Dto in038Dto = new InputFct038Dto();
        in038Dto.setScreenId(SCREEN_ID);
        in038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto out038Dto = new OutputFct038Dto();
        out038Dto = fct038.getData(in038Dto);
        return out038Dto.getCsvDownloadNum();
    }
    
    /**
     * SQL001処理
     *
     * @param dtoReq リクエスト情報
     * @param userAccount ユーザ共通情報
     * @param fct030Result 仲介業者営業員リスト
     * @return SQL処理結果
     * @throws Exception 例外
     */
    private DataList<IfaMarginPositionListForeignSql001ResponseModel> getManagingContractList(
            IfaMarginPositionListForeignA002aDtoRequest dtoReq, 
            UserAccount userAccount, OutputFct030Dto fct030Result) throws Exception {
        
        IfaMarginPositionListForeignSql001RequestModel selSql001Req =
                new IfaMarginPositionListForeignSql001RequestModel();
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        selSql001Req.setEmpCode(dtoReq.getEmpCode());
        selSql001Req.setButenCode(dtoReq.getButenCode());
        selSql001Req.setAccountNumber(dtoReq.getAccountNumber());
        List<IfaMarginPositionListForeignSql001RequestModelCourseSelected> course = new ArrayList<>();
        dtoReq.getCourse().forEach(e -> {
            IfaMarginPositionListForeignSql001RequestModelCourseSelected courseModel = new IfaMarginPositionListForeignSql001RequestModelCourseSelected();
            courseModel.setId(e.getId());
            courseModel.setIsSelected(e.getIsSelected());
            course.add(courseModel);
        });
        selSql001Req.setCourse(course);
        selSql001Req.setPrivId(userAccount.getPrivId());
        selSql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        selSql001Req.setCustomerNameKanjiKanaTerms(dtoReq.getCustomerNameKanjiKanaTerms());
        
        List<IfaMarginPositionListForeignSql001RequestModel_BrokerCharge> brokerChargeList =
                new ArrayList<IfaMarginPositionListForeignSql001RequestModel_BrokerCharge>();
        if (fct030Result != null && !CollectionUtils.isEmpty(fct030Result.getBrokerChargeList())) {
            for (BrokerCharge fctRes : fct030Result.getBrokerChargeList()) {
                IfaMarginPositionListForeignSql001RequestModel_BrokerCharge brokerCharge =
                        new IfaMarginPositionListForeignSql001RequestModel_BrokerCharge();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode());
                brokerCharge.setEmpCode(fctRes.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        selSql001Req.setBrokerChargeList(brokerChargeList);
        
        return dao.selectIfaMarginPositionListForeignSql001(selSql001Req);
    }
    
    /**
     * 信用建玉画面表示情報編集
     *
     * @param dtoReq 顧客口座情報
     * @param apiRes 余力サービス - 外国株式信用建玉明細一覧取得API レスポンス
     * @return dtoRes
     */
    private List<IfaMarginPositionListForeignA002bDtoResponse> editMarginPositionListForeign(
            IfaMarginPositionListForeignA002bDtoRequest dtoReq,
            ListMarginPositionsResp apiRes) {
        
        // 信用建玉一覧リスト
        List<IfaMarginPositionListForeignDto_MarginPositionListForeign> marginPositionList = new ArrayList<>();
        
        // 外国株式信用建玉明細一覧の件数分繰り返す
        for (Position position : apiRes.getPositions()) {
            // レスポンス.信用建玉情報
            IfaMarginPositionListForeignDto_MarginPositionListForeign marginPositionListForeign = new IfaMarginPositionListForeignDto_MarginPositionListForeign();
            // 部店
            marginPositionListForeign.setButen(dtoReq.getButenCode());
            // 口座
            marginPositionListForeign.setAccount(dtoReq.getAccountNumber());
            // 取引コース
            marginPositionListForeign.setCourse(dtoReq.getCustomerAttribute());
            // 顧客名(漢字)
            marginPositionListForeign.setCustomerNameKanji(dtoReq.getCustomerNameKanji());
            // 顧客名(カナ)
            marginPositionListForeign.setCustomerNameKana(dtoReq.getCustomerNameKana());
            // 銘柄名
            marginPositionListForeign.setBrandName(position.getSecurities().getSecuritiesName());
            //  ティッカー
            marginPositionListForeign.setTicker(position.getSecurities().getSecuritiesCode());
            // 市場
            marginPositionListForeign.setMarket(position.getMarket().getMarketShortName());
            // 取引
            marginPositionListForeign
                    .setOpenTradeKbn(editOpenTradeKbn(position.getBuySellCode(), position.getMarginCloseLimitType()));
            // 建日
            String tradeDate = position.getTradeDate();
            marginPositionListForeign.setOpenTradeDate(replaceDateFormat(tradeDate));
            // 返済期限
            String frnCloseLimitDate = position.getFrnCloseLimitDate();
            marginPositionListForeign.setLastTradeDate(editLastTradeDate(frnCloseLimitDate));
            // 預り区分
            marginPositionListForeign.setDepositType(position.getSpecificAccountCode());
            // 担保
            marginPositionListForeign.setSecurity(editSecurity(position));
            // 建株数
            marginPositionListForeign.setContPositionTotal(position.getQuantity());
            // 注文中
            marginPositionListForeign.setUnactualQuantity(position.getClosingQuantity());
            // 建単価
            marginPositionListForeign.setPositionPrice(position.getFrnPositionPrice());
            if (position.getPriceData() != null) {
                // 現在値
                marginPositionListForeign.setCurrentPrice(position.getPriceData().getLastToPrevClose());
            }
            // 建代金
            marginPositionListForeign.setOpenAmount(position.getFrnPositionAmount());
            // 諸経費等合計
            marginPositionListForeign.setTotalExpensesEtc(position.getFrnTotalExpenses());
            if (position.getEvaluationProfitLoss() != null) {
                // 評価損益
                marginPositionListForeign
                        .setCustomerListProfitAndLoss(position.getEvaluationProfitLoss().getFrnEvaluationProfitLoss());
            }
            // 国コード
            marginPositionListForeign.setCountryCode(position.getCountryCode());
            // 売買区分
            marginPositionListForeign.setTradeKbn(position.getBuySellCode());
            // 信用期日
            marginPositionListForeign.setMarginDueDate(position.getMarginCloseLimitType());
            // 国内新規約定日
            marginPositionListForeign.setDomesticTradeDate(position.getTradeDate());
            // 現地新規約定日 
            marginPositionListForeign.setForeignTradeDate(position.getFrnTradeDate());
            // 新規建単価（円貨）
            marginPositionListForeign.setJpyAmountNewPositionPrice(position.getPositionPrice());
            // 新規建代金（外貨）
            marginPositionListForeign.setForeignNewPositionAmount(position.getFrnPositionAmount());
            // 仲介業者コード
            marginPositionListForeign.setBrokerCode(dtoReq.getBrokerCode());
            // 仲介業者名
            marginPositionListForeign.setBrokerName(dtoReq.getBrokerName());
            // 支店コード
            marginPositionListForeign.setBranchCode(dtoReq.getBranchCode());
            // 支店名
            marginPositionListForeign.setBranchName(dtoReq.getBranchName());
            // 営業員コード
            marginPositionListForeign.setEmpCode(dtoReq.getEmpCode());
            // 営業員名
            marginPositionListForeign.setBrokerChargeName(dtoReq.getBrokerChargeName());
            
            // 信用建玉一覧リストに追加する
            marginPositionList.add(marginPositionListForeign);
        }
        
        // 信用建玉一覧リストが0件の場合はnullで返す
        // ※APIを繰り返しリクエストして実行するため、合計0件かどうかは画面側で判定しメッセージを出力する
        if (marginPositionList.size() == 0) {
            return null;
        }
        
        // レスポンスを設定
        IfaMarginPositionListForeignA002bDtoResponse dtoRes = new IfaMarginPositionListForeignA002bDtoResponse();
        dtoRes.setMarginPositionListForeignList(marginPositionList);
        List<IfaMarginPositionListForeignA002bDtoResponse> dtoResList = new ArrayList<>();
        dtoResList.add(dtoRes);
        return dtoResList;
    }
    
    /**
     * 信用建玉画面表示情報編集:取引
     *
     * @param buySellCode 売買区分
     * @param marginCloseLimitType 信用期日
     * @return 取引
     */
    private String editOpenTradeKbn(String buySellCode, String marginCloseLimitType) {
        // 売買区分＝'1'(売付)の場合
        String editBuysellCode = "1".equals(buySellCode) ? "売建" : "買建";
        // 信用期日＝'0'(無期限)の場合
        String editMarginCloseLimitType = "0".equals(marginCloseLimitType) ? "無期限" : "６ヶ月";
        
        return editBuysellCode + "(" + editMarginCloseLimitType + ")";
    }
    
    /**
     * ハイフンをスラッシュに置換する
     *
     * @param target 対象
     * @return 置換結果
     */
    private String replaceDateFormat(String target) {
        
        return target.replace(HYPHEN, SLASH);
    }
    
    /**
     * 対象が任意のパターンに合致するかチェックする
     *
     * @param target 対象
     * @return チェック結果
     */
    private boolean formatCheck(String target) {
        
        return target.matches(DATE_PATTERN);
    }
    
    /**
     * 信用建玉画面表示情報編集:返済期限
     *
     * @param lastTradeDate 現地決済期日
     * @return 編集結果
     */
    private String editLastTradeDate(String lastTradeDate) {
        
        if (formatCheck(lastTradeDate)) {
            if (!INVALID_DATE.equals(lastTradeDate)) {
                return replaceDateFormat(lastTradeDate);
            }
        }
        return DATE_HYPHEN;
    }
    
    /**
     * 信用建玉画面表示情報編集:
     *
     * @param position 外国株式信用建玉明細一覧
     * @return 編集結果
     */
    private String editSecurity(Position position) {
        
        // 増担保規制建玉フラグ
        boolean needAdditionalCollateral = position.getNeedAdditionalCollateral();
        // 建玉必要保証金率
        String positionMarginRate = position.getPositionMarginRate();
        return needAdditionalCollateral ? positionMarginRate + "%" + "(増担)" : positionMarginRate + "%";
    }
    
    /**
     * A006aリクエストをCSV出力形式に変換する
     *
     * @param req A006リクエスト
     * @return CSV出力データモデル
     */
    private List<IfaMarginPositionListForeignDto_MarginPositionListForeign> convertCsvOutModel(
            List<IfaMarginPositionListForeignDto_MarginPositionListForeign> req) {
        
        List<IfaMarginPositionListForeignDto_MarginPositionListForeign> csvList = 
                new ArrayList<IfaMarginPositionListForeignDto_MarginPositionListForeign>();
        DecimalFormat priceFormat = new DecimalFormat("##0.00");
        
        for (IfaMarginPositionListForeignDto_MarginPositionListForeign marginPosition : req) {
            IfaMarginPositionListForeignDto_MarginPositionListForeign csvData = marginPosition;
            
            // 取引コース
            csvData.setCourse(codeListService.getValue(PRE_CONTRACT_DOC_CODE, marginPosition.getCourse()));
            // 預り区分
            csvData.setDepositType(codeListService.getValue(FOREIGN_DEPOSIT_TYPE, marginPosition.getDepositType()));
            // 建単価
            csvData.setPositionPrice(formatNumber(marginPosition.getPositionPrice(), priceFormat));
            // 現在値
            csvData.setCurrentPrice(formatNumber(marginPosition.getCurrentPrice(), priceFormat));
            // 建代金
            csvData.setOpenAmount(formatNumber(marginPosition.getOpenAmount(), priceFormat));
            // 諸経費等合計
            csvData.setTotalExpensesEtc(formatNumber(marginPosition.getTotalExpensesEtc(), priceFormat));
            // 評価損益
            csvData.setCustomerListProfitAndLoss(
                    formatNumber(marginPosition.getCustomerListProfitAndLoss(), priceFormat));
            
            csvList.add(csvData);
        }
                
        return csvList;
    }
    
    /**
     * 数値を指定形式にフォーマットする
     *
     * @param value 文字列（数値）
     * @param format フォーマット形式
     * @return フォーマット後文字列
     */
    private String formatNumber(String value, DecimalFormat format) {
        
        if (StringUtil.isNullOrEmpty(value)) {
            return "-";
        }
        try {
            // 文字列を数値に変換してフォーマットする
            BigDecimal number = new BigDecimal(value);
            return format.format(number);
        } catch (NumberFormatException e) {
            // 数値に変換できない場合はハイフンを返す
            return "-";
        }
    }
    
}
