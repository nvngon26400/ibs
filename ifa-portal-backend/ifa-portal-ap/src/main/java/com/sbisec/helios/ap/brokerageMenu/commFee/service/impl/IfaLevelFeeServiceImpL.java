package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

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
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaLevelFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002LevelFeeResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeCsvItems;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaLevelFeeService;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaLevelFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;


/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Component(value = "cmpIfaLevelFeeService")
public class IfaLevelFeeServiceImpL implements IfaLevelFeeService {

    /** エラー.参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";

    /** エラー.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";

    /** ワーニング."検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";

    /** ワーニング.検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";

    /** コードテーブル.種別.画面コメント */
    private static final String M_CODE_CODE_TYPE_SCREEN_COMMENT = "99";

    /** コードテーブル.CODE_1.画面コメント.デフォルト */
    private static final String M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT = "01";

    /** コードテーブル.CODE_2.画面コメント.残高連動手数料・報酬画面 コメント */
    private static final String M_CODE_CODE_2_SCREEN_COMMENT_COMM_FEE = "27";

    /** FCT038.画面ID */
    private static final String FCT038_SCREEN_ID = "SUB020505-01";

    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaLevelFeeServiceImpL.class);


    @Autowired
    Fct030 fct030;

    @Autowired
    Fct038 fct038;

    @Autowired
    MCodeService mcodeService;
    
    @Autowired
    private IfaLevelFeeDao dao;

    @Autowired
    private ComplianceService complianceService;

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaLevelFeeA001RequestDto
     * Dto レスポンス：IfaLevelFeeA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA001ResponseDto> initializeA001(
            IfaLevelFeeA001RequestDto dtoReq
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaLevelFeeServiceImplL.initializeA001");
        }
        
        /* =============================================================================== */
        /* 1. 残高連動手数料コメントを取得する
        /* =============================================================================== */

        IfaLevelFeeA001ResponseDto innerData = new IfaLevelFeeA001ResponseDto();

        // SQL007を呼び出す(MCodeServiceを利用)
        List<MCode> selSql007Res = mcodeService.getMCodeList(
                M_CODE_CODE_TYPE_SCREEN_COMMENT,
                M_CODE_CODE_1_SCREEN_COMMENT_DEFAULT,
                M_CODE_CODE_2_SCREEN_COMMENT_COMM_FEE
        );
        
        // 残高連動手数料コメントをレスポンスにセットする
        if (selSql007Res != null && 0 < selSql007Res.size()) {
            innerData.setLevelFeeComment(selSql007Res.get(0).getName());
        }

        DataList<IfaLevelFeeA001ResponseDto> dtoRes = IfaCommonUtil.createDataList(
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
     * Dto リクエスト：IfaLevelFeeA002RequestDto
     * Dto レスポンス：IfaLevelFeeA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA002ResponseDto> displayA002(
            IfaLevelFeeA002RequestDto dtoReq
    ) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaLevelFeeServiceImplL.displayA002");
        }

        IfaLevelFeeA002ResponseDto innerData = new IfaLevelFeeA002ResponseDto();
        
        /* =============================================================================== */
        /* 2. ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。
        /* =============================================================================== */

        Pair<DataList<IfaLevelFeeA002ResponseDto>, OutputFct030Dto> checkFct030Res = checkFct030();
        DataList<IfaLevelFeeA002ResponseDto> checkFct030DtoRes = checkFct030Res.getLeft();
        if (checkFct030DtoRes != null) {
            return checkFct030DtoRes;
        }

        /* =============================================================================== */
        /* 3. 残高連動手数料リストを取得する。                                               */
        /* =============================================================================== */

        IfaLevelFeeSql001To006RequestModel Sql001To006RequestModel = new IfaLevelFeeSql001To006RequestModel();

        // SQL001～006のリクエストにDTOリクエスト項目をセット
        BeanUtils.copyProperties(Sql001To006RequestModel, dtoReq);
        Sql001To006RequestModel.setBrokerCodeArray(dtoReq.getBrokerCodeArray());

        // SQL001～006のリクエストにFCT030.仲介業者営業員リストをセット(SBI証券本店の場合はNULL)
        OutputFct030Dto fct030OutputDto = checkFct030Res.getRight();
        if (fct030OutputDto != null) {
            Sql001To006RequestModel.setBrokerChargeList(fct030OutputDto.getBrokerChargeList());
        }

        // 最大取得件数に5,000件をセット
        Sql001To006RequestModel.setMaxRowNum(5000);

        // SQL001～006のいずれかの結果を取得
        Pair<DataList<IfaLevelFeeA002ResponseDto>, List<IfaLevelFeeSql001To006ResponseModel>> Sql001To006Res
                = getLevelFeeList(Sql001To006RequestModel, false);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaLevelFeeA002ResponseDto> checkSql001To006Result = Sql001To006Res.getLeft();
        if (checkSql001To006Result != null && StringUtils.equals(checkSql001To006Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)) {
            return checkSql001To006Result;
        }

        // レスポンスに残高連動手数料・報酬リストをセット
        List<IfaLevelFeeA002LevelFeeResponseDto> LevelFeeList = new ArrayList<>();
        for (IfaLevelFeeSql001To006ResponseModel record : Sql001To006Res.getRight()) {
            IfaLevelFeeA002LevelFeeResponseDto LevelFee = new IfaLevelFeeA002LevelFeeResponseDto();
            BeanUtils.copyProperties(LevelFee, record);
            LevelFeeList.add(LevelFee);
        }
        innerData.setLevelFeeList(LevelFeeList);
        
        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */
        DataList<IfaLevelFeeA002ResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        // SQL001～006でワーニングが設定されていればレスポンスにセット
        if (checkSql001To006Result != null) {
            dtoRes.setErrorLevel(checkSql001To006Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001To006Result.getReturnCode());
            dtoRes.setMessage(checkSql001To006Result.getMessage());
        }

        return dtoRes;
    }
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaLevelFeeA004RequestDto
     * Dto レスポンス：IfaLevelFeeA004ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA004aResponseDto> csvOutputA004a(
            IfaLevelFeeA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaLevelFeeServiceImplL.csvOutputA004");
        }
        
        /* =============================================================================== */
        /* A002-2 ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードと営業員コードを取得する。
        /* =============================================================================== */

        Pair<DataList<IfaLevelFeeA004aResponseDto>, OutputFct030Dto> checkFct030Res = checkFct030();
        DataList<IfaLevelFeeA004aResponseDto> checkFct030DtoRes = checkFct030Res.getLeft();
        if (checkFct030DtoRes != null) {
            return checkFct030DtoRes;
        }

        /* =============================================================================== */
        /* 2. CSVダウンロードMAX件数を取得する。
        /* =============================================================================== */

        final int csvDownloadMaxRowNum =  getCsvDownloadMaxRowNum();

        /* =============================================================================== */
        /* 3. 残高連動手数料リストを取得する。
        /* =============================================================================== */

        IfaLevelFeeSql001To006RequestModel Sql001To006RequestModel = new IfaLevelFeeSql001To006RequestModel();

        // SQL001～006のリクエストにDTOリクエスト項目をセット
        BeanUtils.copyProperties(Sql001To006RequestModel, dtoReq);
        Sql001To006RequestModel.setBrokerCodeArray(dtoReq.getBrokerCodeArray());

        // SQL001～006のリクエストにFCT030.仲介業者営業員リストをセット(SBI証券本店の場合はNULL)
        OutputFct030Dto fct030OutputDto = checkFct030Res.getRight();
        if (fct030OutputDto != null) {
            Sql001To006RequestModel.setBrokerChargeList(fct030OutputDto.getBrokerChargeList());
        }

        // 最大取得件数にCSVダウンロードMAX件数をセット
        Sql001To006RequestModel.setMaxRowNum(csvDownloadMaxRowNum);

        // SQL001～006のいずれかの結果を取得
        Pair<DataList<IfaLevelFeeA004aResponseDto>, List<IfaLevelFeeSql001To006ResponseModel>> Sql001To006Res
                = getLevelFeeList(Sql001To006RequestModel, true);

        // 0件エラーがセットされていれば処理を終了
        DataList<IfaLevelFeeA004aResponseDto> checkSql001To006Result = Sql001To006Res.getLeft();
        if (checkSql001To006Result != null && StringUtils.equals(checkSql001To006Result.getReturnCode(), ERRORS_DATALIST_NOT_FOUND)) {
            return checkSql001To006Result;
        }

        // CSV出力の内容に残高連動手数料・報酬リストをセット
        List<IfaLevelFeeCsvItems> csvItemList = new ArrayList<>();
        for (IfaLevelFeeSql001To006ResponseModel record : Sql001To006Res.getRight()) {
            IfaLevelFeeCsvItems csvItem = new IfaLevelFeeCsvItems();
            BeanUtils.copyProperties(csvItem, record);

            csvItemList.add(csvItem);
        }
        DataList<IfaLevelFeeCsvItems> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);
        
       // 検索条件からパターンを設定する（リクエスト.集計単位(日次/月次) + リクエスト.集計単位(仲介業者/営業員/顧客)）
        String pattern = dtoReq.getDailyMonthlyCountingUnit() + dtoReq.getBrokerChargeBranchCountingUnit();
        
        /* =============================================================================== */
        /* レスポンスを返却する。                                                           */
        /* =============================================================================== */

        // CSVファイルを作成
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String userId = userAccount.getUserId();
        IfaLevelFeeCsvOut csvOut = new IfaLevelFeeCsvOut(complianceService);
        String csvTmpFileName = csvOut.doCreateCsvFile(csvDataList, fwSessionId, userId, pattern);

        IfaLevelFeeA004aResponseDto innerData = new IfaLevelFeeA004aResponseDto();
        innerData.setPattern(pattern);

        // レスポンスをセット
        DataList<IfaLevelFeeA004aResponseDto> dtoRes = IfaCommonUtil.createDataList(
                Arrays.asList(innerData),
                ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING
        );

        String title = csvTmpFileName;
        dtoRes.setTitle(title);
        dtoRes.setTotalSize(Sql001To006Res.getRight().get(0).getTotalRow());
        dtoRes.setMaxRownum(csvDataList.size());

        // SQL001～006でワーニングが設定されていればレスポンスにセット
        if (checkSql001To006Result != null) {
            dtoRes.setErrorLevel(checkSql001To006Result.getErrorLevel());
            dtoRes.setReturnCode(checkSql001To006Result.getReturnCode());
            dtoRes.setMessage(checkSql001To006Result.getMessage());
            dtoRes.setOverMaxRownum(checkSql001To006Result.isOverMaxRownum());
        }

        return dtoRes;

    }
    
    /**
     * FCT030
     * 下記のいずれかを満たすことを確認する
     * 1. ユーザ共通情報.権限コード = '1':SBI証券本店
     * 2. 参照可能な仲介業者コードと営業員コードが存在する
     *
     * @return Pair<エラー判定, FCT030レスポンス>;
     * @exception Exception システムエラー
     */
    private <T> Pair<DataList<T>, OutputFct030Dto> checkFct030() {
         
        String privId = IfaCommonUtil.getUserAccount().getPrivId();

        if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) { // ユーザ共通情報.権限コード = '1':SBI証券本店の場合
            return ImmutablePair.of(null, null);
        } else { // ユーザ共通情報.権限コード != '1':SBI証券本店の場合
            // FCT030を呼び出す
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() == 0) { // FCT030.仲介業者営業員リストの件数が0件の場合エラー
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
     * SQL001～006のいずれか
     * 残高連動手数料・報酬リストを取得する。
     *
     * @param selSql001To006Req SQL001～006リクエスト
     * @param isCsvMode A002表示：false, A004 CSV出力：true
     * @return 残高連動手数料・報酬リスト
     * @exception Exception システムエラー
     */
    private <T> Pair<DataList<T>, List<IfaLevelFeeSql001To006ResponseModel>> getLevelFeeList(
            IfaLevelFeeSql001To006RequestModel selSql001To006Req,
            boolean isCsvMode
    ) throws Exception {

        // リクエストに権限コードをセット
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        selSql001To006Req.setPrivId(privId);

        // SQL001～006の呼び出し
        DataList<IfaLevelFeeSql001To006ResponseModel> selSql001To006Res = dao.selectIfaLevelFeeSql001To006(selSql001To006Req);
        
        // 結果が0件の場合、0件エラー
        if (selSql001To006Res == null || selSql001To006Res.size() == 0) {
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
        if (selSql001To006Req.getMaxRowNum() < selSql001To006Res.get(0).getTotalRow()) {
            if (isCsvMode == false) { // A002 表示の場合
                exceedMaxRow = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.WARNING,
                        WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                        IfaCommonUtil.getMessage(
                                WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                                new String[] {
                                    String.format("%,d", selSql001To006Req.getMaxRowNum()),
                                    String.format("%,d", selSql001To006Res.get(0).getTotalRow())
                                }
                        )
                );
            } else {  // A004 CSV出力の場合
                exceedMaxRow = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.WARNING,
                    WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(
                            WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                            new String[] {
                                String.format("%,d", selSql001To006Req.getMaxRowNum()),
                                String.format("%,d", selSql001To006Res.get(0).getTotalRow()),
                                String.format("%,d", selSql001To006Req.getMaxRowNum())
                            }
                    )
                );
                exceedMaxRow.setOverMaxRownum(true);
            }
        }

        return ImmutablePair.of(exceedMaxRow, selSql001To006Res.getDataList());
    }
}
