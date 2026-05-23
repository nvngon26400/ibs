package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.sbibits.earth.extapi.ApiConnectionException;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaTodayTradeListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA002bTodayTradeDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaTodayTradeListA005aTodayTradeDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaTodayTradeListService;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaTodayTradeListCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1ExecSumData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1In;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1InData;
import jp.co.sbisec.pcenter.dto.yanap.QueryExecutionSummary1OutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderExecutionIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderExecutionInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderExecutionOutData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgRDOrderExecutionOutVec;

/**
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * 2023/11/21 新規作成
 *
 * @author 鄒
 */
@Component(value = "cmpIfaTodayTradeListService")
public class IfaTodayTradeListServiceImpL implements IfaTodayTradeListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaTodayTradeListServiceImpL.class);

    @Autowired
    private IfaTodayTradeListDao dao;

    /**
     * FCT030仲介業者コード営業員リスト取得
     */
    @Autowired
    private Fct030 fct030;

    /**
     * FCT038CSVダウンロードMAX件数取得
     */
    @Autowired
    private Fct038 fct038;

    /**
     * 区分値変換
     */
    @Autowired
    private CodeListService codeListService;

    /**
     * NRI-API 呼び出し
     */
    @Autowired
    private ApiWrapper apiwrapper;

    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;

    /** "{0}を選択してください。" {0}："取引コース"*/
    private static final String ERRORS_SELECTED = "errors.selected";

    /** "取引コース"*/
    private static final String COURSE = "取引コース";

    /** "{0}を正しく入力して下さい。"*/
    private static final String ERRORS_ACCURATELY = "errors.accurately";

    /** "銘柄コード"*/
    private static final String BRAND_CODE = "銘柄コード";

    /** "参照可能な仲介業者コード／営業員コードが存在しません。"*/
    private static final String ERRORS_CMN_IFAAGENTCODES_NOTEXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** "仲介業者コード／営業員コードを正しく入力して下さい。"*/
    private static final String ERRORS_DMS_IFAAGENTCODES_UNAVAILABLE = "errors.dms.ifaAgentCodes.unavailable";

    /** FCT038 画面ID*/
    private static final String SCREEN_ID = "SUB020302_0102-01";

    /** ユーザ共通情報.権限コード SBI証券本店 */
    private static final String PRIV_ID_1 = "1";

    /** ユーザ共通情報.権限コード 仲介業者 内部管理責任者 */
    private static final String PRIV_ID_3 = "3";

    /** ユーザ共通情報.権限コード 仲介業者 営業責任者 */
    private static final String PRIV_ID_4 = "4";

    /** ユーザ共通情報.権限コード 仲介業者 外務員 */
    private static final String PRIV_ID_5 = "5";

    /** "検索結果が0件です。\n条件を設定して再度検索して下さい。"*/
    private static final String ERRORS_DATALIST_NOTFOUND = "errors.dataList.notfound";

    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。"*/
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";

    /** 正常終了 */
    private static final String SUCCESS_FINISH_STR = "";

    /** API001 商品区分 "S"：株式 */
    private static final String API001_SEC_ID_S = "S";

    /** API001 取引種別 "A"：全て */
    private static final String API001_TRADE_TYPE_A = "A";

    /** API001 半角スペース8つ */
    private static final String API001_REQUEST_DATE = String.format("%8s", "");

    /** API001 検索番号指定ＦＲＯＭ */
    private static final  String API001_REF_FROM = "1";

    /** API001 検索番号指定ＴＯ */
    private static final  String API001_REF_TO = "100";

    /** ジュニアISA契約区分  "1":契約 */
    private static final String JR_ISA_CONTRACT_TYPE_1 = "1";

    /** JrNISA口座(第一、第二口座両方) */
    private static final String JRNISA_2 = "2";

    /** 通常口座およびJrNISA口座の第一口座*/
    private static final String JRNISA_SPACE1 = String.format("%1s", "");

    /** "アクセスが集中しているため口座情報が取得できませんでした。<br>部店コード:[{0}] 口座番号:[{1}]" */
    private static final String W0001 = "W0001";

    /** "当日約定情報の取得ができませんでした。<br>部店コード:[{0}] 口座番号:[{1}]" */
    private static final String WARNINGS_LISTSREVICE_GETEXECUTIONLIST = "warnings.listService.getExecutionList";

    /** 信用属性 */
    private static final String MARGIN = "MARGIN";

    /** api002 "G"：現引現渡 */
    private static final String API002_ORDER_TYPE = "G";

    /** api002 "2"：約定済み */
    private static final String API002_REQUEST_TYPE = "2";

    /** api002 検索番号指定ＦＲＯＭ */
    private static final  String API002_REF_FROM = "1";

    /** api002 検索番号指定ＴＯ */
    private static final  String API002_REF_TO = "100";

    /** 決済方法 = "1":現渡  */
    private static final String SETTLEMENT_ID_1 = "1";

    /** 決済方法 = "5":現引  */
    private static final String SETTLEMENT_ID_5 = "5";

    /** "7"：現渡  */
    private static final String OPEN_TRADEKBN_7 = "7";

    /** "8"：現引  */
    private static final String OPEN_TRADEKBN_8 = "8";

    /** "約定件数が0件です。\n条件を設定して再度検索して下さい。"  */
    private static final String INFO_CMN_CONTRACT_NOTFOUND = "info.cmn.contract.notFound";

    /** CSV出力：改行コード */
    private static final String LF = "\n";

    /** CSV出力BeanのCSV出力対象外項目 */
    private static final List<String> NOT_OUTPUT_COLUMNS = Arrays.asList(new String[] { "serialVersionUID" });

    /** "S"：株式 */
    private static final String COMMODITY_S = "S";

    /** "G"：現引現渡 */
    private static final String COMMODITY_G = "G";

    /** 左側に追加する文字: '0' */
    private static final char CHAR_ZERO = '0';

    /** 表示パターン = "1" */
    private static final String DISP_PATTERN_1 = "1";

    /** 表示パターン = "3" */
    private static final String DISP_PATTERN_3 = "3";

    /** 表示パターン = "5" */
    private static final String DISP_PATTERN_5 = "5";

    /** 区分.契約締結前交付書面コード */
    private static final String CODE_NAME_PRE_CONTRACT_DOC_CODE = "PRE_CONTRACT_DOC_CODE";

    /** 区分.取引種別（国内株式） */
    private static final String CODE_NAME_DOMESTIC_STOCK_TRADE_CLASS = "DOMESTIC_STOCK_TRADE_CLASS";

    /** 区分.預り区分 */
    private static final String CODE_NAME_DOMESTIC_DEPOSIT_TYPE = "DOMESTIC_DEPOSIT_TYPE";

    /**
     * アクションID：A002a
     * アクション名：表示
     * Dto リクエスト：IfaTodayTradeListA002DtoRequest
     * Dto レスポンス：IfaTodayTradeListA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 画面に返却する値
     * @exception Exception システムエラー
     */
    public DataList<IfaTodayTradeListA002aDtoResponse> displayA002a(IfaTodayTradeListA002aDtoRequest dtoReq)
            throws Exception {

        DataList<IfaTodayTradeListA002aDtoResponse> dtoRes = new DataList<IfaTodayTradeListA002aDtoResponse>();

        List<IfaTodayTradeListA002aDtoResponse> resDtoList = new ArrayList<IfaTodayTradeListA002aDtoResponse>();


        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTodayTradeListServiceImplL.displayA002a");
        }

        boolean warningFlag = false;

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        //① リクエスト.取引コースのチェックを行う。
        if (StringUtil.isNullOrEmpty(dtoReq.getCourse())) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()),
                    IfaCommonUtil.getMessage(ERRORS_SELECTED, new String[] { COURSE }));
            return dtoRes;
        }

        //② リクエスト.銘柄コードに値がある場合、該当の銘柄コードが存在するかチェックを行う。
        if (!StringUtil.isNullOrEmpty(dtoReq.getBrandCode())) {
            IfaTodayTradeListSql002RequestModel sql002Req = new IfaTodayTradeListSql002RequestModel();
            sql002Req.setBrandCode(dtoReq.getBrandCode());
            DataList<IfaTodayTradeListSql002ResponseModel> sql002ResList = dao.selectIfaTodayTradeListSql002(sql002Req);
            if (null == sql002ResList || sql002ResList.size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { BRAND_CODE }));
                return dtoRes;
            }
        }

        //③ ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：仲介業者コードと営業員コードの参照可能チェックを行う。
        String privId = userAccount.getPrivId();
        OutputFct030Dto outputFct030Dto = new OutputFct030Dto();
        if (!PRIV_ID_1.equals(privId)) {
            outputFct030Dto = fct030.getData(new InputFct030Dto());
            //FCT030.仲介業者担当者リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (null == outputFct030Dto || outputFct030Dto.getBrokerChargeList().size() == 0) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()),
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return dtoRes;
            }
            
            if (
                    StringUtils.equals(privId, PRIV_ID_3)
                    || StringUtils.equals(privId, PRIV_ID_4)
                    || StringUtils.equals(privId, PRIV_ID_5)
            ) {
                // 権限3, 4, 5の場合
                // FCT030.仲介業者担当者リスト に リクエスト.仲介業者コード が存在しない場合：メッセージを表示し、処理終了。
                if (!outputFct030Dto.getBrokerChargeList().stream()
                        .filter(s -> StringUtils.equals(s.getBrokerCode(), dtoReq.getBrokerCode()))
                        .findFirst().isPresent()) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            Integer.toString(ErrorLevel.FATAL.getId()),
                            IfaCommonUtil.getMessage(ERRORS_DMS_IFAAGENTCODES_UNAVAILABLE));
                    return dtoRes;
                }
            } else {
                // 権限1, 3, 4, 5以外の場合
                // FCT030.仲介業者担当者リスト に リクエスト.仲介業者コード と リクエスト.営業員コード の組み合わせが存在しない場合：メッセージを表示し、処理終了。
                if (!outputFct030Dto.getBrokerChargeList().stream()
                        .filter(s -> StringUtils.equals(s.getBrokerCode(), dtoReq.getBrokerCode())
                                && StringUtils.equals(s.getEmpCode(), dtoReq.getEmpCode()))
                        .findFirst().isPresent()) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            Integer.toString(ErrorLevel.FATAL.getId()),
                            IfaCommonUtil.getMessage(ERRORS_DMS_IFAAGENTCODES_UNAVAILABLE));
                    return dtoRes;
                }
            }
        }

        //④ CSVダウンロードMAX件数を取得し、顧客口座情報MAX件数に設定する。
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);

        //⑤ 顧客口座情報リストを取得する。
        IfaTodayTradeListSql001RequestModel sql001Req = new IfaTodayTradeListSql001RequestModel();
        sql001Req.setBrokerCode(dtoReq.getBrokerCode());
        sql001Req.setEmpCode(dtoReq.getEmpCode());
        sql001Req.setButenCode(dtoReq.getButenCode());
        sql001Req.setAccountNumber(dtoReq.getAccountNumber());
        sql001Req.setCustomerNameKanjiKana(dtoReq.getCustomerNameKanjiKana());
        sql001Req.setCustomerNameSearchType(dtoReq.getCustomerNameKanjiKanaTerms());
        sql001Req.setCourse(dtoReq.getCourse());
        sql001Req.setPrivId(userAccount.getPrivId());
        if (!PRIV_ID_1.equals(userAccount.getPrivId())) {
            sql001Req.setFct030Code(outputFct030Dto.getBrokerChargeList());
        }

        DataList<IfaTodayTradeListSql001ResponseModel> sql001ResList = dao.selectIfaTodayTradeListSql001(sql001Req);
        //顧客口座情報リストの件数が0件の場合：メッセージを表示し、処理終了。
        if (null == sql001ResList || sql001ResList.size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                    Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(ERRORS_DATALIST_NOTFOUND));
            return dtoRes;
        }
        //顧客口座情報リストの件数が顧客口座情報MAX件数より大きい場合：メッセージを表示し、次の処理へ。
        if (sql001ResList.getDataList().size() > outputFct038Dto.getCsvDownloadNum()) {
            warningFlag = true;
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                    Integer.toString(ErrorLevel.WARNING.getId()),
                    IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                            new String[] { String.valueOf(outputFct038Dto.getCsvDownloadNum()),
                                    String.valueOf(sql001ResList.getDataList().size()) }));
        }

        List<IfaTodayTradeListA002aDtoResponse> customerAccountInfoList = new ArrayList<IfaTodayTradeListA002aDtoResponse>();
        for (IfaTodayTradeListSql001ResponseModel model : sql001ResList.getDataList()) {
            IfaTodayTradeListA002aDtoResponse customerAccountInfo = new IfaTodayTradeListA002aDtoResponse();
            BeanUtils.copyProperties(customerAccountInfo, model);
            customerAccountInfo.setBrandCode(dtoReq.getBrandCode());
            customerAccountInfo.setCsvDownloadMaxCount(String.valueOf(outputFct038Dto.getCsvDownloadNum()));
            customerAccountInfoList.add(customerAccountInfo);
        }

        if (!warningFlag) {
            dtoRes = IfaCommonUtil.createDataList(customerAccountInfoList, ErrorLevel.SUCCESS, "0", SUCCESS_FINISH_STR);
        }
        return dtoRes;
    }

    /**
     * アクションID：A002b
     * アクション名：表示
     * Dto リクエスト：IfaTodayTradeListA002DtoRequest
     * Dto レスポンス：IfaTodayTradeListA002DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 画面に返却する値
     * @exception Exception システムエラー
     */
    public DataList<IfaTodayTradeListA002bDtoResponse> displayA002b(IfaTodayTradeListA002bDtoRequest dtoReq)
            throws Exception {

        DataList<IfaTodayTradeListA002bDtoResponse> dtoRes = new DataList<IfaTodayTradeListA002bDtoResponse>();

        List<IfaTodayTradeListA002bDtoResponse> resDtoList = new ArrayList<IfaTodayTradeListA002bDtoResponse>();

        IfaTodayTradeListA002bDtoResponse a002aDtoResponse = new IfaTodayTradeListA002bDtoResponse();

        List<IfaTodayTradeListA002bTodayTradeDtoResponse> todayTradeList = new ArrayList<IfaTodayTradeListA002bTodayTradeDtoResponse>();
        a002aDtoResponse.setTodayTradeList(todayTradeList);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTodayTradeListServiceImplL.displayA002a");
        }

        //⑦ SQL001.総件数分以下の処理を行う。　※上限は顧客口座情報MAX件数
        //API001呼出し
        int maxCount = 0;

        if (Integer.valueOf(dtoReq.getTotalCount()) > Integer.valueOf(dtoReq.getCsvDownloadMaxCount())) {
            maxCount = Integer.valueOf(dtoReq.getCsvDownloadMaxCount());
        } else {
            maxCount = Integer.valueOf(dtoReq.getTotalCount());
        }
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        try {

            QueryExecutionSummary1InData api001InDto = new QueryExecutionSummary1InData();
            api001InDto.setButenCd(dtoReq.getButenCode());
            api001InDto.setKozaNo(StringUtil.fillLeft(dtoReq.getAccountNumber(), CHAR_ZERO, 7));
            api001InDto.setSecId(API001_SEC_ID_S);
            api001InDto.setTradeType(API001_TRADE_TYPE_A);
            api001InDto.setRequestDate(API001_REQUEST_DATE);
            api001InDto.setRefFrom(API001_REF_FROM);
            api001InDto.setRefTo(API001_REF_TO);
            api001InDto.setAccountGetKbn(JRNISA_2);


            QueryExecutionSummary1In api001Req = new QueryExecutionSummary1In();
            api001Req.setIndata(api001InDto);

            //APIより対象顧客口座の現物信用注文の当日約定一覧を取得する。
            QueryExecutionSummary1OutData api001Res = apiwrapper.queryExecutionSummary1(api001Req, maxCount);
            if (apiErrorUtil.isError(api001Res.getShubetu(), api001Res.getCode(), api001Res.getMessage())) {
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                        Integer.toString(ErrorLevel.WARNING.getId()),
                        IfaCommonUtil.getMessage(WARNINGS_LISTSREVICE_GETEXECUTIONLIST,
                                new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                return dtoRes;
            }

            // リクエスト.銘柄コードに値がある場合
            // リクエスト.銘柄コード = API001.約定部.銘柄コード　を満たさないデータをAPI001.約定部から除外する。
            if (!StringUtil.isNullOrEmpty(dtoReq.getBrandCode())) {
                Iterator<QueryExecutionSummary1ExecSumData> iterator = api001Res.getExecSumData().iterator();
                while (iterator.hasNext()) {
                    QueryExecutionSummary1ExecSumData queryExecutionSummary1ExecSumData = iterator.next();
                    if (!equalsIgnoreSpace(dtoReq.getBrandCode(), queryExecutionSummary1ExecSumData.getSecCode())) {
                        iterator.remove();
                    }
                }
            }

            //当日約定リストに対象顧客口座のデータとAPI001のレスポンス値を追加する。
            a002aDtoResponse.setTodayTradeList(setTodayTradeListApi001(a002aDtoResponse.getTodayTradeList(),
                    api001Res.getExecSumData(), dtoReq));

        } catch (Exception e) {
            if (e instanceof ApiConnectionException) {
                LOGGER.info("ApiConnectionException occured.", e);
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                        Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(W0001,
                                new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                return dtoRes;
            } else {
                LOGGER.info("Exception occured.", e);
                dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                        Integer.toString(ErrorLevel.WARNING.getId()),
                        IfaCommonUtil.getMessage(WARNINGS_LISTSREVICE_GETEXECUTIONLIST,
                                new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                return dtoRes;
            }
        }
        //SQL001.信用属性 = "MARGIN"の場合のみ、以下の処理を繰り返し行う。
        //APIより対象顧客口座の現引現渡注文の当日約定一覧を取得する。
        if (MARGIN.equals(dtoReq.getMarginProfile())) {
            try {
                QueryMgRDOrderExecutionInData api002InDto = new QueryMgRDOrderExecutionInData();
                api002InDto.setButenCd(dtoReq.getButenCode());
                api002InDto.setKozaNo(StringUtil.fillLeft(dtoReq.getAccountNumber(), CHAR_ZERO, 7));
                api002InDto.setSecId(API002_ORDER_TYPE);
                api002InDto.setRequestType(API002_REQUEST_TYPE);
                api002InDto.setRefFrom(API002_REF_FROM);
                api002InDto.setRefTo(API002_REF_TO);

                QueryMgRDOrderExecutionIn api002Req = new QueryMgRDOrderExecutionIn();
                api002Req.setIndata(api002InDto);

                //APIより対象顧客口座の現物信用注文の当日約定一覧を取得する。
                QueryMgRDOrderExecutionOutData api002Res = apiwrapper.queryMgRDOrderExecution(api002Req, maxCount);
                if (apiErrorUtil.isError(api002Res.getShubetu(), api002Res.getCode(), api002Res.getMessage())) {
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                            Integer.toString(ErrorLevel.WARNING.getId()),
                            IfaCommonUtil.getMessage(WARNINGS_LISTSREVICE_GETEXECUTIONLIST,
                                    new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                    return dtoRes;
                }
                //リクエスト.銘柄コードに値がある場合、リクエスト.銘柄コード = API002.約定部.銘柄・個有名コード　を満たさないデータをAPI002.約定部から除外する。
                if (!StringUtil.isNullOrEmpty(dtoReq.getBrandCode())) {
                    Iterator<QueryMgRDOrderExecutionOutVec> iterator = api002Res.getQueryMgrdOrderData().iterator();
                    while (iterator.hasNext()) {
                        QueryMgRDOrderExecutionOutVec queryMgRDOrderExecutionOutVec = iterator.next();
                        if (!equalsIgnoreSpace(dtoReq.getBrandCode(), queryMgRDOrderExecutionOutVec.getSecCode())) {
                            iterator.remove();
                        }
                    }
                }

                //当日約定リストに対象顧客口座のデータとAPI001のレスポンス値を追加する。
                a002aDtoResponse.setTodayTradeList(setTodayTradeListApi002(a002aDtoResponse.getTodayTradeList(),
                        api002Res.getQueryMgrdOrderData(), dtoReq));

            } catch (Exception e) {
                if (e instanceof ApiConnectionException) {
                    LOGGER.info("ApiConnectionException occured.", e);
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                            Integer.toString(ErrorLevel.FATAL.getId()), IfaCommonUtil.getMessage(W0001,
                                    new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                    return dtoRes;
                } else {
                    LOGGER.info("Exception occured.", e);
                    dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.WARNING,
                            Integer.toString(ErrorLevel.WARNING.getId()),
                            IfaCommonUtil.getMessage(WARNINGS_LISTSREVICE_GETEXECUTIONLIST,
                                    new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
                    return dtoRes;
                }
            }
        }

        resDtoList.add(a002aDtoResponse);

        dtoRes = apiErrorUtil.createDataList(resDtoList, "");

        return dtoRes;
    }

    /**
     * 当日約定リストに対象顧客口座のデータとAPI001のレスポンス値を追加する。
     *
     * @param todayTradeList
     * @param execSumData
     * @return 対象顧客口座データとAPI001レスポンスを追加した当日約定リスト
     */
    private List<IfaTodayTradeListA002bTodayTradeDtoResponse> setTodayTradeListApi001(
            List<IfaTodayTradeListA002bTodayTradeDtoResponse> todayTradeList,
            List<QueryExecutionSummary1ExecSumData> execSumData, IfaTodayTradeListA002bDtoRequest dtoReq) {

        for (QueryExecutionSummary1ExecSumData sumData : execSumData) {

            IfaTodayTradeListA002bTodayTradeDtoResponse todayTrade = new IfaTodayTradeListA002bTodayTradeDtoResponse();

            todayTrade.setCommodity(COMMODITY_S);
            todayTrade.setButen(dtoReq.getButenCode());
            todayTrade.setAccountNumber(dtoReq.getAccountNumber());
            todayTrade.setCourse(dtoReq.getCourse());
            todayTrade.setCustomerNameKanji(dtoReq.getCustomerNameKanji());
            todayTrade.setCustomerNameKana(dtoReq.getCustomerNameKana());
            //銘柄名
            todayTrade.setBrandName(trimFullSpace(sumData.getSecName()));
            //銘柄コード
            todayTrade.setBrandCode(sumData.getSecCode());
            //取引
            todayTrade.setOpenTradeKbn(sumData.getTradeId());
            //預り区分
            todayTrade.setDepositType(sumData.getHitokuteiTradeKbn());
            //約定日
            todayTrade.setTradeDate(sumData.getTradeDate());
            //受渡日
            todayTrade.setSettlementDate(sumData.getSettlementDate());
            //約定株数
            todayTrade.setContractStock(sumData.getQuantityTotal());
            //平均約定単価
            todayTrade.setAverageTradePrice(sumData.getAveragePrice());

            todayTrade.setEmpCode(dtoReq.getEmpCode());
            todayTrade.setBrokerChargeName(dtoReq.getBrokerChargeName());
            todayTrade.setBrokerCode(dtoReq.getBrokerCode());
            todayTrade.setBrokerName(dtoReq.getBrokerName());
            todayTrade.setBranchCode(dtoReq.getBranchCode());
            todayTrade.setBranchName(dtoReq.getBranchName());

            todayTradeList.add(todayTrade);
        }

        return todayTradeList;
    }

    /**
     * APIより対象顧客口座の現引現渡注文の当日約定一覧を取得する。
     *
     * @param todayTradeList
     * @param execSumData
     * @return 対象顧客口座の現引現渡注文の当日約定一覧
     */
    private List<IfaTodayTradeListA002bTodayTradeDtoResponse> setTodayTradeListApi002(
            List<IfaTodayTradeListA002bTodayTradeDtoResponse> todayTradeList,
            List<QueryMgRDOrderExecutionOutVec> queryMgrdOrderData, IfaTodayTradeListA002bDtoRequest dtoReq) {

        for (QueryMgRDOrderExecutionOutVec orderData : queryMgrdOrderData) {

            IfaTodayTradeListA002bTodayTradeDtoResponse todayTrade = new IfaTodayTradeListA002bTodayTradeDtoResponse();

            todayTrade.setCommodity(COMMODITY_G);
            todayTrade.setButen(dtoReq.getButenCode());
            todayTrade.setAccountNumber(dtoReq.getAccountNumber());
            todayTrade.setCourse(dtoReq.getCourse());
            todayTrade.setCustomerNameKanji(dtoReq.getCustomerNameKanji());
            todayTrade.setCustomerNameKana(dtoReq.getCustomerNameKana());
            //銘柄名
            todayTrade.setBrandName(trimFullSpace(orderData.getSecName()));
            //銘柄コード
            todayTrade.setBrandCode(orderData.getSecCode());
            //取引
            if (SETTLEMENT_ID_1.equals(orderData.getSettlementId())) {
                todayTrade.setOpenTradeKbn(OPEN_TRADEKBN_7);
            } else if (SETTLEMENT_ID_5.equals(orderData.getSettlementId())) {
                todayTrade.setOpenTradeKbn(OPEN_TRADEKBN_8);
            }

            //預り区分
            todayTrade.setDepositType(orderData.getHitokuteiTradeKbn());
            //約定日
            todayTrade.setTradeDate(orderData.getTradeDate());
            //受渡日
            todayTrade.setSettlementDate("-");
            //約定株数
            todayTrade.setContractStock(orderData.getQuantity());
            //平均約定単価
            todayTrade.setAverageTradePrice(
                    String.valueOf(new BigDecimal(orderData.getOpenPrice().trim()).divide(new BigDecimal(100))));
            todayTrade.setEmpCode(dtoReq.getEmpCode());
            todayTrade.setBrokerChargeName(dtoReq.getBrokerChargeName());
            todayTrade.setBrokerCode(dtoReq.getBrokerCode());
            todayTrade.setBrokerName(dtoReq.getBrokerName());
            todayTrade.setBranchCode(dtoReq.getBranchCode());
            todayTrade.setBranchName(dtoReq.getBranchName());

            todayTradeList.add(todayTrade);

        }

        return todayTradeList;
    }

    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaTodayTradeListA005DtoRequest
     * Dto レスポンス：IfaTodayTradeListA005DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 画面に返却する値
     * @exception Exception システムエラー
     */
    public DataList<IfaTodayTradeListA005aDtoResponse> csvOutputA005(IfaTodayTradeListA005aDtoRequest dtoReq)
            throws Exception {

        DataList<IfaTodayTradeListA005aDtoResponse> dtoRes = new DataList<IfaTodayTradeListA005aDtoResponse>();

        List<IfaTodayTradeListA005aDtoResponse> resDtoList = new ArrayList<IfaTodayTradeListA005aDtoResponse>();

        IfaTodayTradeListA005aDtoResponse a005aDtoResponse = new IfaTodayTradeListA005aDtoResponse();

        IfaTodayTradeListCsvOut csvOut = new IfaTodayTradeListCsvOut(complianceService);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaTodayTradeListServiceImplL.csvOutputA005");
        }

        List<IfaTodayTradeListA005aTodayTradeDtoRequest> csvDataList = dtoReq.getCsvData();
        for (IfaTodayTradeListA005aTodayTradeDtoRequest csvData : csvDataList) {
            csvData.setCourse(codeListService.getValue(CODE_NAME_PRE_CONTRACT_DOC_CODE, csvData.getCourse(), DISP_PATTERN_1));
            csvData.setOpenTradeKbn(codeListService.getValue(CODE_NAME_DOMESTIC_STOCK_TRADE_CLASS, csvData.getOpenTradeKbn(), DISP_PATTERN_3));
            csvData.setDepositType(codeListService.getValue(CODE_NAME_DOMESTIC_DEPOSIT_TYPE, csvData.getDepositType(), DISP_PATTERN_5));
        }

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        DataList<IfaTodayTradeListA005aTodayTradeDtoRequest> exportList =
                new DataList<IfaTodayTradeListA005aTodayTradeDtoRequest>();
        exportList.setDataList(csvDataList);

        String sessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);

        resDtoList.add(a005aDtoResponse);

        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                SUCCESS_FINISH_STR);

        dtoRes.setTitle(
                csvOut.doCreateCsvFile(exportList, sessionId, userAccount.getUserId(), null));

        return dtoRes;
    }


    private String trimFullSpace(String str) {
        if (str == null) {
            return str;
        }
        return str.replaceAll("^[\\u3000]+|[\\u3000]+$", "");
    }

    private boolean equalsIgnoreSpace(String str1, String str2) {
        if (str1 == str2) {
            return true;
        }
        if (str1 == null || str2 == null) {
            return false;
        }
        str1 = str1.trim();
        str2 = str2.trim();
        return str1.equals(str2);
    }
}
