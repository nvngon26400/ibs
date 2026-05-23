package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaCommFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql013ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002CommFeeResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002SbiRapManageFeeDisplayControlResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002SpotStockPointReferenceAbleListResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaCommFeeService;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaCommFeeCsvOut;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaCommFeePattern;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Component(value = "cmpIfaCommFeeService")
public class IfaCommFeeServiceImpL implements IfaCommFeeService {

    /** エラー.参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** エラー.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** ワーニング."検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** コードテーブル.種別.現株ポイント */
    private static final String M_CODE_CODE_TYPE_SPOT_STOCK_POINT = "19";

    /** コードテーブル.CODE_1.原株ポイント.仲介者情報 */
    private static final String M_CODE_CODE_1_SPOT_STOCK_POINT_BROKER_INFO = "2";

    /** コードテーブル.種別.画面コメント */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードテーブル.CODE_1.画面コメント.デフォルト */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードテーブル.CODE_2.画面コメント.手数料・報酬画面　コメント */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_COMM_FEE = "01";

    /** 表示内容（手数料/報酬).手数料 */
    private static final String FEE_COMM_DISPLAY_CONTENT_FEE = "0";

    /** 表示内容（手数料/報酬).報酬 */
    private static final String FEE_COMM_DISPLAY_CONTENT_COMM = "1";

    /** FCT038.画面ID */
    private static final String FCT038_SCREEN_ID = "SUB020502-01";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCommFeeServiceImpL.class);

    @Autowired
    Fct030 fct030;

    @Autowired
    Fct038 fct038;

    @Autowired
    MCodeService mcodeService;
    
    @Autowired
    private IfaCommFeeDao dao;

    @Autowired
    private ComplianceService complianceService;

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCommFeeA001RequestDto
     * Dto レスポンス：IfaCommFeeA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA001ResponseDto> initializeA001(
            IfaCommFeeA001RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCommFeeServiceImplL.initializeA001");
        }
        
        /* =============================================================================== */
        /* 1. 手数料報酬コメントを取得する                                                   */
        /* =============================================================================== */

        IfaCommFeeA001ResponseDto innerData = new IfaCommFeeA001ResponseDto();

        // SQL014を呼び出す(MCodeServiceを利用)
        List<MCode> selSql014Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT,
                M_CODE_CODE_2_SCREEN_COMMENT_COMM_FEE
        );
        
        // 手数料報酬コメントをレスポンスにセットする
        if (selSql014Res != null && 0 < selSql014Res.size()) {
            innerData.setCommFeeComment(selSql014Res.get(0).getName());
        }

        DataList<IfaCommFeeA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCommFeeA002RequestDto
     * Dto レスポンス：IfaCommFeeA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA002ResponseDto> displayA002(
            IfaCommFeeA002RequestDto dtoReq
    ) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCommFeeServiceImplL.displayA002");
        }

        IfaCommFeeA002ResponseDto innerData = new IfaCommFeeA002ResponseDto();
        
        /* =============================================================================== */
        /* 2. 下記のいずれかを満たすことを確認する                                           */
        /*    a. ユーザ共通情報.権限コード = SBI証券本店                                     */
        /*    b. 参照可能な仲介業者コードと営業員コードが存在する。                           */
        /* =============================================================================== */

        Pair<DataList<IfaCommFeeA002ResponseDto>, OutputFct030Dto> checkFct030Res = checkFct030();
        DataList<IfaCommFeeA002ResponseDto> checkFct030DtoRes = checkFct030Res.getLeft();
        if (checkFct030DtoRes != null) {
            return checkFct030DtoRes;
        }

        /* =============================================================================== */
        /* 3. 現株ポイント項目を表示する仲介業者を取得する。                                  */
        /* =============================================================================== */

        List<IfaCommFeeSql013ResponseModel> sql013Res
                = getSpotStockPointReferenceAbleList();

        // 結果を格納する
        List<IfaCommFeeA002SpotStockPointReferenceAbleListResponseDto> spotStockPointReferenceAbleList = new ArrayList<>();
        for (IfaCommFeeSql013ResponseModel record : sql013Res) {
            IfaCommFeeA002SpotStockPointReferenceAbleListResponseDto spotStockPointReferenceAble
                    = new IfaCommFeeA002SpotStockPointReferenceAbleListResponseDto();
            BeanUtils.copyProperties(spotStockPointReferenceAble, record);

            spotStockPointReferenceAbleList.add(spotStockPointReferenceAble);
        }
        innerData.setSpotStockPointReferenceAbleList(spotStockPointReferenceAbleList);

        /* =============================================================================== */
        /* 4. SBIラップ管理報酬のサービス表示制御情報を取得する。                             */
        /* =============================================================================== */

        List<IfaCommFeeSql015ResponseModel> sql015Res = getSbiRapManageFeeDisplayControlList();

        // 結果を格納する
        List<IfaCommFeeA002SbiRapManageFeeDisplayControlResponseDto> sbiRapManageFeeDisplayControlList = new ArrayList<>();
        for (IfaCommFeeSql015ResponseModel record : sql015Res) {
            IfaCommFeeA002SbiRapManageFeeDisplayControlResponseDto sbiRapManageFeeDisplayControl
                    = new IfaCommFeeA002SbiRapManageFeeDisplayControlResponseDto();
            BeanUtils.copyProperties(sbiRapManageFeeDisplayControl, record);

            sbiRapManageFeeDisplayControlList.add(sbiRapManageFeeDisplayControl);
        }
        innerData.setSbiRapManageFeeDisplayControlList(sbiRapManageFeeDisplayControlList);

        /* =============================================================================== */
        /* パターンを判定                                                                   */
        /* =============================================================================== */

        // SQL001～012のいずれを呼び出すかを判定
        IfaCommFeePattern pattern = new IfaCommFeePattern();
        pattern.setPatternWithCondition(
                dtoReq.getDisplayContent(),
                dtoReq.getDailyMonthlyCountingUnit(),
                dtoReq.getBrokerChargeBranchCountingUnit(),
                dtoReq.getAggregatorHandlerCountingUnit()
        );

        // 各項目表示 / 非表示のフラグを設定
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        String brokerCode = userAccount.getBrokerCode();
        pattern.setVisibilityFlags(
                privId,
                brokerCode,
                sql013Res,
                sql015Res
        );

        /* =============================================================================== */
        /* 5. 手数料報酬リストを取得する                                                     */
        /* =============================================================================== */

        IfaCommFeeSql001To012RequestModel sql001To012RequestModel = new IfaCommFeeSql001To012RequestModel();

        // SQL001～012のリクエストにDTOリクエスト項目をセット
        BeanUtils.copyProperties(sql001To012RequestModel, dtoReq);
        sql001To012RequestModel.setBrokerCodeArray(dtoReq.getBrokerCodeArray());

        // SQL001～012のリクエストにFCT030.仲介業者営業員リストをセット(SBI証券本店の場合はNULL)
        OutputFct030Dto fct030OutputDto = checkFct030Res.getRight();
        if (fct030OutputDto != null) {
            sql001To012RequestModel.setBrokerChargeList(fct030OutputDto.getBrokerChargeList());
        }

        // 権限コードをセット
        sql001To012RequestModel.setPrivId(privId);

        // SQL001～012のリクエストにパターンをセット
        sql001To012RequestModel.setPattern(pattern.getPatternNo());

        // 最大取得件数に5,000件をセット
        sql001To012RequestModel.setMaxRowNum(5000);

        // SQL001～012のいずれかの結果を取得
        Pair<DataList<IfaCommFeeA002ResponseDto>, List<IfaCommFeeSql001To012ResponseModel>> sql001To012Res
                = getCommFeeList(sql001To012RequestModel, false);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaCommFeeA002ResponseDto> checkSql001To012Result = sql001To012Res.getLeft();
        if (
                checkSql001To012Result != null
                && StringUtils.equals(checkSql001To012Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)
        ) {
            return checkSql001To012Result;
        }

        // SBIラップ報酬のうち、非表示分を合計などから減算
        subtractHiddenSbiRapManagementRewardFromTotalAndSubTotal(sql001To012Res.getRight(), pattern);


        // レスポンスに手数料・報酬リストをセット
        List<IfaCommFeeA002CommFeeResponseDto> commFeeList = new ArrayList<>();
        for (IfaCommFeeSql001To012ResponseModel record : sql001To012Res.getRight()) {
            IfaCommFeeA002CommFeeResponseDto commFee = new IfaCommFeeA002CommFeeResponseDto();
            BeanUtils.copyProperties(commFee, record);
            commFeeList.add(commFee);
        }
        innerData.setCommFeeList(commFeeList);
        
        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */

        // リクエストの折り返し項目をセット
        innerData.setDisplayContent(dtoReq.getDisplayContent());

        DataList<IfaCommFeeA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        // SQL001～012でワーニングが設定されていればレスポンスにセット
        if (checkSql001To012Result != null) {
            dtoRes.setErrorLevel(checkSql001To012Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001To012Result.getReturnCode());
            dtoRes.setMessage(checkSql001To012Result.getMessage());
        }

        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaCommFeeA004RequestDto
     * Dto レスポンス：IfaCommFeeA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA004aResponseDto> csvOutputA004a(
            IfaCommFeeA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCommFeeServiceImplL.csvOutputA004");
        }
        
        /* =============================================================================== */
        /* 1-2. 下記のいずれかを満たすことを確認する                                         */
        /*    a. ユーザ共通情報.権限コード = SBI証券本店                                     */
        /*    b. 参照可能な仲介業者コードと営業員コードが存在する。                           */
        /* =============================================================================== */

        Pair<DataList<IfaCommFeeA004aResponseDto>, OutputFct030Dto> checkFct030Res = checkFct030();
        DataList<IfaCommFeeA004aResponseDto> checkFct030DtoRes = checkFct030Res.getLeft();
        if (checkFct030DtoRes != null) {
            return checkFct030DtoRes;
        }

        /* =============================================================================== */
        /* 1-3. 現株ポイント項目を表示する仲介業者を取得する。                                */
        /* =============================================================================== */

        final List<IfaCommFeeSql013ResponseModel> spotStockPointReferenceAbleList
                = getSpotStockPointReferenceAbleList();

        /* =============================================================================== */
        /* 1-4. SBIラップ管理報酬のサービス表示制御情報を取得する。                           */
        /* =============================================================================== */

        final List<IfaCommFeeSql015ResponseModel> sbiRapManageFeeDisplayControlList = getSbiRapManageFeeDisplayControlList();

        /* =============================================================================== */
        /* 2. CSVダウンロードMAX件数を取得する                                              */
        /* =============================================================================== */

        final int csvDownloadMaxRowNum =  getCsvDownloadMaxRowNum();

        /* =============================================================================== */
        /* パターンを判定                                                                   */
        /* =============================================================================== */

        // SQL001～012のいずれを呼び出すかを判定
        IfaCommFeePattern pattern = new IfaCommFeePattern();
        pattern.setPatternWithCondition(
                dtoReq.getDisplayContent(),
                dtoReq.getDailyMonthlyCountingUnit(),
                dtoReq.getBrokerChargeBranchCountingUnit(),
                dtoReq.getAggregatorHandlerCountingUnit()
        );

        // 各項目表示 / 非表示のフラグを設定
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        String brokerCode = userAccount.getBrokerCode();
        pattern.setVisibilityFlags(
                privId,
                brokerCode,
                spotStockPointReferenceAbleList,
                sbiRapManageFeeDisplayControlList
        );

        // サービス名を設定
        pattern.setServiceNames(sbiRapManageFeeDisplayControlList);

        /* =============================================================================== */
        /* 3. 手数料報酬リストを取得する                                                     */
        /* =============================================================================== */

        IfaCommFeeSql001To012RequestModel sql001To012RequestModel = new IfaCommFeeSql001To012RequestModel();

        // SQL001～012のリクエストにDTOリクエスト項目をセット
        BeanUtils.copyProperties(sql001To012RequestModel, dtoReq);
        sql001To012RequestModel.setBrokerCodeArray(dtoReq.getBrokerCodeArray());

        // SQL001～012のリクエストにFCT030.仲介業者営業員リストをセット(SBI証券本店の場合はNULL)
        OutputFct030Dto fct030OutputDto = checkFct030Res.getRight();
        if (fct030OutputDto != null) {
            sql001To012RequestModel.setBrokerChargeList(fct030OutputDto.getBrokerChargeList());
        }

        // SQL001～012のリクエストにパターンをセット
        sql001To012RequestModel.setPattern(pattern.getPatternNo());

        // 最大取得件数にCSVダウンロードMAX件数をセット
        sql001To012RequestModel.setMaxRowNum(csvDownloadMaxRowNum);

        // SQL001～012のいずれかの結果を取得
        Pair<DataList<IfaCommFeeA004aResponseDto>, List<IfaCommFeeSql001To012ResponseModel>> sql001To012Res
                = getCommFeeList(sql001To012RequestModel, true);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaCommFeeA004aResponseDto> checkSql001To012Result = sql001To012Res.getLeft();
        if (
                checkSql001To012Result != null
                && StringUtils.equals(checkSql001To012Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)
        ) {
            return checkSql001To012Result;
        }

        // SBIラップ報酬のうち、非表示分を合計などから減算し、非表示のSBIラップコースの一覧を取得
        subtractHiddenSbiRapManagementRewardFromTotalAndSubTotal(sql001To012Res.getRight(), pattern);

        // CSV出力の内容に手数料・報酬リストをセット
        List<IfaCommFeeCsvItems> csvItemList = new ArrayList<>();
        for (IfaCommFeeSql001To012ResponseModel record : sql001To012Res.getRight()) {
            IfaCommFeeCsvItems csvItem = new IfaCommFeeCsvItems();
            BeanUtils.copyProperties(csvItem, record);

            csvItemList.add(csvItem);
        }
        DataList<IfaCommFeeCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);
        
        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */

        // CSVファイルを作成
        String userId = userAccount.getUserId();
        IfaCommFeeCsvOut csvOut = new IfaCommFeeCsvOut(complianceService);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, userId, pattern);

        IfaCommFeeA004aResponseDto innerData = new IfaCommFeeA004aResponseDto();
        innerData.setPattern(pattern.serialize());

        // レスポンスをセット
        DataList<IfaCommFeeA004aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        String title = csvTmpFileName;
        dtoRes.setTitle(title);
        dtoRes.setTotalSize(sql001To012Res.getRight().get(0).getTotalRow());
        dtoRes.setMaxRownum(csvDataList.size());

        // SQL001～012でワーニングが設定されていればレスポンスにセット
        if (checkSql001To012Result != null) {
            dtoRes.setErrorLevel(checkSql001To012Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001To012Result.getReturnCode());
            dtoRes.setMessage(checkSql001To012Result.getMessage());
            dtoRes.setOverMaxRownum(checkSql001To012Result.isOverMaxRownum());
        }

        return dtoRes;

    }
    
    /**
     * FCT030
     * 下記のいずれかを満たすことを確認する
     * 1. ユーザ共通情報.権限コード = SBI証券本店
     * 2. 参照可能な仲介業者コードと営業員コードが存在する
     *
     * @return Pair&lt;エラー判定, FCT030レスポンス&gt;
     * @exception Exception システムエラー
     */
    private <T> Pair<DataList<T>, OutputFct030Dto> checkFct030() {
         
        String privId = IfaCommonUtil.getUserAccount().getPrivId();


        // ユーザ共通情報.権限コード = SBI証券本店の場合
        if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            
            return ImmutablePair.of(null, null);

        } else { // ユーザ共通情報.権限コード != SBI証券本店の場合

            // FCT030を呼び出す
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合エラー
            if (
                    fct030OutputDto == null
                    || fct030OutputDto.getBrokerChargeList().size() == 0
            ) {
                DataList<T> resDto = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.FATAL,
                        ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST)
                );

                return ImmutablePair.of(resDto, null);

            } else { // FCT030.仲介業者営業員リストの件数が1件以上の場合OK
                
                return ImmutablePair.of(null, fct030OutputDto);

            }
        }
    }

    /**
     * FCT038
     * CSVダウンロードMAX件数を取得する
     *
     * @return Pair&lt;エラー判定, FCT030レスポンス&gt;
     * @exception Exception システムエラー
     */
    private int getCsvDownloadMaxRowNum() {

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        
        InputFct038Dto fct038InputDto = new InputFct038Dto();
        fct038InputDto.setScreenId(FCT038_SCREEN_ID);
        fct038InputDto.setUserAuthority(privId);
        OutputFct038Dto fct038OutputDto = fct038.getData(fct038InputDto);

        return fct038OutputDto.getCsvDownloadNum();
    }

    /**
     * SQL013
     * 現株ポイント項目を表示する仲介業者を取得する
     *
     * @return 現株ポイント項目を表示する仲介業者リスト
     * @exception Exception システムエラー
     */
    private List<IfaCommFeeSql013ResponseModel> getSpotStockPointReferenceAbleList() throws Exception {

        // SQL013を呼び出す(MCodeServiceを利用)
        List<MCode> selSql013Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_SPOT_STOCK_POINT,
                M_CODE_CODE_1_SPOT_STOCK_POINT_BROKER_INFO
        );

        // 結果を格納する
        List<IfaCommFeeSql013ResponseModel> result = Optional.ofNullable(selSql013Res)
                .orElse(Collections.emptyList())
                .stream()
                .map(val -> {
                    IfaCommFeeSql013ResponseModel newValue = new IfaCommFeeSql013ResponseModel();
                    newValue.setBrokerCode(val.getCode2());
                    newValue.setBrokerName(val.getName());
                    return newValue;
                })
                .collect(Collectors.toList());

        return result;

    }

    /**
     * SQL015
     * SBIラップ管理報酬のサービス表示制御情報を取得する
     *
     * @return SBIラップ管理報酬のサービス表示制御情報のリスト
     * @exception Exception システムエラー
     */
    private List<IfaCommFeeSql015ResponseModel> getSbiRapManageFeeDisplayControlList() throws Exception {

        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String brokerCode = userAccount.getBrokerCode();
        
        // SQL015の呼び出し
        IfaCommFeeSql015RequestModel selSql015Req = new IfaCommFeeSql015RequestModel();
        selSql015Req.setBrokerCode(brokerCode);
        DataList<IfaCommFeeSql015ResponseModel> selSql015Res = dao.selectIfaCommFeeSql015(selSql015Req);

        return selSql015Res.getDataList();

    }

    /**
     * SQL001～012のいずれか
     * 手数料報酬リストを取得する。
     *
     * @param selSql001To012Req SQL001～012リクエスト
     * @param isCsvMode A002表示：false, A004 CSV出力：true
     * @return 手数料報酬リスト
     * @exception Exception システムエラー
     */
    private <T> Pair<DataList<T>, List<IfaCommFeeSql001To012ResponseModel>> getCommFeeList(
            IfaCommFeeSql001To012RequestModel selSql001To012Req,
            boolean isCsvMode
    ) throws Exception {

        // リクエストに権限コードをセット
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        selSql001To012Req.setPrivId(privId);

        // SQL001～012の呼び出し
        DataList<IfaCommFeeSql001To012ResponseModel> selSql001To012Res = dao.selectIfaCommFeeSql001To012(selSql001To012Req);
        
        // 結果が0件の場合、0件エラー
        if (selSql001To012Res == null || selSql001To012Res.size() == 0) {
            DataList<T> dtoRes = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND)
            );

            return ImmutablePair.of(dtoRes, null);
        }

        // 総件数が5000件を超過している場合ワーニング
        DataList<T> exceedMaxRow = null;
        if (selSql001To012Req.getMaxRowNum() < selSql001To012Res.get(0).getTotalRow()) {
            // A002 表示の場合
            if (isCsvMode == false) {
                exceedMaxRow = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.WARNING,
                        WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                        IfaCommonUtil.getMessage(
                                WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                                new String[] {
                                    String.format("%,d", selSql001To012Req.getMaxRowNum()),
                                    String.format("%,d", selSql001To012Res.get(0).getTotalRow())
                                }
                        )
                );
            } else { // A004 CSV出力の場合
                exceedMaxRow = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.WARNING,
                    WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                            new String[] {
                                String.format("%,d", selSql001To012Req.getMaxRowNum()),
                                String.format("%,d", selSql001To012Res.get(0).getTotalRow()),
                                String.format("%,d", selSql001To012Req.getMaxRowNum())
                            }
                    )
                );
                exceedMaxRow.setOverMaxRownum(true);
            }
        }

        return ImmutablePair.of(exceedMaxRow, selSql001To012Res.getDataList());
    }

    /**
     * SBIラップコース01～SBIラップコース17のうち、画面非表示になった項目の金額を集計項目から減算する
     *
     * @param sql001To012Response SQL001～012のレスポンス
     * @param pattern 表示パターン
     */
    private void subtractHiddenSbiRapManagementRewardFromTotalAndSubTotal(
            List<IfaCommFeeSql001To012ResponseModel> sql001To012Response,
            IfaCommFeePattern pattern
    ) {

        

        for (IfaCommFeeSql001To012ResponseModel record : sql001To012Response) {
            /* =============================================================================== */
            /* 減算合計額を算出する                                                             */
            /* =============================================================================== */

            // 画面非表示になったSBIラップコースの値の合計を取得する
            BigDecimal hiddenSbiRapsTotalPrice = BigDecimal.ZERO;

            // SBIラップ管理報酬(ネット)が非表示の場合
            if (!pattern.isShowSbiRapCourse01()) {
                String sbiRapManagementFeeNetStr = record.getSbiRap01();

                if (StringUtils.isNotEmpty(sbiRapManagementFeeNetStr)) {
                    BigDecimal sbiRapManagementFeeNet = new BigDecimal(record.getSbiRap01());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManagementFeeNet);
                }
            }

            // SBIラップ管理報酬(店頭)が非表示の場合
            if (!pattern.isShowSbiRapCourse02()) {
                String sbiRapManagementFeeStoreStr = record.getSbiRap02();

                if (StringUtils.isNotEmpty(sbiRapManagementFeeStoreStr)) {
                    BigDecimal sbiRapManagementFeeStore = new BigDecimal(record.getSbiRap02());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManagementFeeStore);
                }
            }

            // SBIラップ管理報酬(匠)が非表示の場合
            if (!pattern.isShowSbiRapCourse03()) {
                String sbiRapManagementFeeTakumiStr = record.getSbiRap03();

                if (StringUtils.isNotEmpty(sbiRapManagementFeeTakumiStr)) {
                    BigDecimal sbiRapManagementFeeTakumi = new BigDecimal(record.getSbiRap03());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManagementFeeTakumi);
                }
            }

            // SBIラップ管理報酬(x投資)が非表示の場合
            if (!pattern.isShowSbiRapCourse04()) {
                String sbiRapManagementFeeInvestmentStr = record.getSbiRap04();

                if (StringUtils.isNotEmpty(sbiRapManagementFeeInvestmentStr)) {
                    BigDecimal sbiRapManagementFeeInvestment = new BigDecimal(record.getSbiRap04());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManagementFeeInvestment);
                }
            }

            // SBIラップコース05が非表示の場合
            if (!pattern.isShowSbiRapCourse05()) {
                String sbiRapCourse05Str = record.getSbiRap05();

                if (StringUtils.isNotEmpty(sbiRapCourse05Str)) {
                    BigDecimal sbiRapManaCourse05 = new BigDecimal(record.getSbiRap05());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse05);
                }
            }

            // SBIラップコース06が非表示の場合
            if (!pattern.isShowSbiRapCourse06()) {
                String sbiRapCourse06Str = record.getSbiRap06();

                if (StringUtils.isNotEmpty(sbiRapCourse06Str)) {
                    BigDecimal sbiRapManaCourse06 = new BigDecimal(record.getSbiRap06());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse06);
                }
            }

            // SBIラップコース07が非表示の場合
            if (!pattern.isShowSbiRapCourse07()) {
                String sbiRapCourse07Str = record.getSbiRap07();

                if (StringUtils.isNotEmpty(sbiRapCourse07Str)) {
                    BigDecimal sbiRapManaCourse07 = new BigDecimal(record.getSbiRap07());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse07);
                }
            }

            // SBIラップコース08が非表示の場合
            if (!pattern.isShowSbiRapCourse08()) {
                String sbiRapCourse08Str = record.getSbiRap08();

                if (StringUtils.isNotEmpty(sbiRapCourse08Str)) {
                    BigDecimal sbiRapManaCourse08 = new BigDecimal(record.getSbiRap08());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse08);
                }
            }

            // SBIラップコース09が非表示の場合
            if (!pattern.isShowSbiRapCourse09()) {
                String sbiRapCourse09Str = record.getSbiRap09();

                if (StringUtils.isNotEmpty(sbiRapCourse09Str)) {
                    BigDecimal sbiRapManaCourse09 = new BigDecimal(record.getSbiRap09());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse09);
                }
            }

            // SBIラップコース10が非表示の場合
            if (!pattern.isShowSbiRapCourse10()) {
                String sbiRapCourse10Str = record.getSbiRap10();

                if (StringUtils.isNotEmpty(sbiRapCourse10Str)) {
                    BigDecimal sbiRapManaCourse10 = new BigDecimal(record.getSbiRap10());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse10);
                }
            }

            // SBIラップコース11が非表示の場合
            if (!pattern.isShowSbiRapCourse11()) {
                String sbiRapCourse11Str = record.getSbiRap11();

                if (StringUtils.isNotEmpty(sbiRapCourse11Str)) {
                    BigDecimal sbiRapManaCourse11 = new BigDecimal(record.getSbiRap11());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse11);
                }
            }

            // SBIラップコース12が非表示の場合
            if (!pattern.isShowSbiRapCourse12()) {
                String sbiRapCourse12Str = record.getSbiRap12();

                if (StringUtils.isNotEmpty(sbiRapCourse12Str)) {
                    BigDecimal sbiRapManaCourse12 = new BigDecimal(record.getSbiRap12());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse12);
                }
            }

            // SBIラップコース13が非表示の場合
            if (!pattern.isShowSbiRapCourse13()) {
                String sbiRapCourse13Str = record.getSbiRap13();

                if (StringUtils.isNotEmpty(sbiRapCourse13Str)) {
                    BigDecimal sbiRapManaCourse13 = new BigDecimal(record.getSbiRap13());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse13);
                }
            }

            // SBIラップコース14が非表示の場合
            if (!pattern.isShowSbiRapCourse14()) {
                String sbiRapCourse14Str = record.getSbiRap14();

                if (StringUtils.isNotEmpty(sbiRapCourse14Str)) {
                    BigDecimal sbiRapManaCourse14 = new BigDecimal(record.getSbiRap14());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse14);
                }
            }

            // SBIラップコース15が非表示の場合
            if (!pattern.isShowSbiRapCourse15()) {
                String sbiRapCourse15Str = record.getSbiRap15();

                if (StringUtils.isNotEmpty(sbiRapCourse15Str)) {
                    BigDecimal sbiRapManaCourse15 = new BigDecimal(record.getSbiRap15());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse15);
                }
            }

            // SBIラップコース16が非表示の場合
            if (!pattern.isShowSbiRapCourse16()) {
                String sbiRapCourse16Str = record.getSbiRap16();

                if (StringUtils.isNotEmpty(sbiRapCourse16Str)) {
                    BigDecimal sbiRapManaCourse16 = new BigDecimal(record.getSbiRap16());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse16);
                }
            }

            // SBIラップコース17が非表示の場合
            if (!pattern.isShowSbiRapCourse17()) {
                String sbiRapCourse17Str = record.getSbiRap17();

                if (StringUtils.isNotEmpty(sbiRapCourse17Str)) {
                    BigDecimal sbiRapManaCourse17 = new BigDecimal(record.getSbiRap17());
                    hiddenSbiRapsTotalPrice = hiddenSbiRapsTotalPrice.add(sbiRapManaCourse17);
                }
            }

            /* =============================================================================== */
            /* 減算処理を実行する。                                                             */
            /* =============================================================================== */

            // 表示内容 = 手数料の場合、手数料合計から非表示分を減算
            if (StringUtils.equals(pattern.getDisplayContent(), FEE_COMM_DISPLAY_CONTENT_FEE)) {
                BigDecimal commTatal = new BigDecimal(record.getCommTotal());
                commTatal = commTatal.subtract(hiddenSbiRapsTotalPrice);
                record.setCommTotal(commTatal.toPlainString());

            // 表示内容 = 報酬の場合、合計、小計から非表示分を減算
            } else if (StringUtils.equals(pattern.getDisplayContent(), FEE_COMM_DISPLAY_CONTENT_COMM)) {
                BigDecimal total = new BigDecimal(record.getTotal());
                BigDecimal rewardSubtotal = new BigDecimal(record.getRewardSubtotal());
                
                total = total.subtract(hiddenSbiRapsTotalPrice);
                rewardSubtotal = rewardSubtotal.subtract(hiddenSbiRapsTotalPrice);

                record.setTotal(total.toPlainString());
                record.setRewardSubtotal(rewardSubtotal.toPlainString());
            }
        }
    }

}
