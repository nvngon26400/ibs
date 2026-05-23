package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.sbisec.helios.ap.common.service.CodeListService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptCustomerManageDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005Model;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006CsvOutModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007Model;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA007ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageA008ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptCustomerManageCommModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.service.IfaJointSubscriptCustomerManageService;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerException;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageCsvOut;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptCustomerManageUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.MCode;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

/**
 * サービスインターフェースの実装クラス
 * 画面ID：SUB0206_01-01
 * 画面名：共同募集 顧客管理
 * 2024/12/05 新規作成
 *
 * @author 大連 王永宝
 */
@Component(value = "cmpIfaJointSubscriptCustomerManageService")
public class IfaJointSubscriptCustomerManageServiceImpL implements IfaJointSubscriptCustomerManageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptCustomerManageServiceImpL.class);

    /** 区分値:自動解約理由　*/
    private static final String JOINT_USER_REASON_CODE = "JOINT_USER_REASON_CODE";

    /** 表示パターン:1　*/
    private static final String JOINT_USER_REASON_CODE_DISP_PATTERN = "1";

    /** コンプライアンス */
    @Autowired
    private ComplianceService g_complianceService;

    /** 共通関数：FCT038 CSVダウンロードMAX件数取得 */
    @Autowired
    private Fct038 g_fct038;

    /** コードマスタサービス */
    @Autowired
    private MCodeService g_mcodeService;

    @Autowired
    private IfaJointSubscriptCustomerManageDao g_dao;

    @Autowired
    private IfaJointSubscriptCustomerManageUtil g_util;

    /** 区分定義公開機能サービス */
    @Autowired
    private CodeListService codeListService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptCustomerManageA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA001ResponseDto> initializeA001(
            IfaJointSubscriptCustomerManageA001RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.initializeA001");

        IfaJointSubscriptCustomerManageA001ResponseDto p_dtoRes = new IfaJointSubscriptCustomerManageA001ResponseDto();

        /* ①共同募集 顧客管理検索部コメントを取得する。(MCodeServiceを利用) */
        List<MCode> p_sql009Res = g_mcodeService.getMCodeList(
                IfaJointSubscriptCustomerManageUtil.MCodeArgs.M_CODE_CODE_TYPE_99.key,
                IfaJointSubscriptCustomerManageUtil.MCodeArgs.M_CODE_CODE_1_01.key, 
                IfaJointSubscriptCustomerManageUtil.MCodeArgs.M_CODE_CODE_2_08.key);
        // 共同募集 顧客管理検索部コメントをレスポンスにセットする
        if (p_sql009Res != null && 0 < p_sql009Res.size()) {
            p_dtoRes.setCommComment(p_sql009Res.get(0).getName());
        }
        // レスポンスを返却する
        return IfaCommonUtil.createDataList(Arrays.asList(p_dtoRes), ErrorLevel.SUCCESS, StringUtils.EMPTY, StringUtils.EMPTY);
    }

    /**
     * アクションID：A002
     * アクション名：検索(表示)
     * Dto リクエスト：IfaJointSubscriptCustomerManageA001RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA001ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA002ResponseDto> searchDisplayA002(
            IfaJointSubscriptCustomerManageA002RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.searchDisplayA002");

        UserAccount p_userAccount = IfaCommonUtil.getUserAccount();
        String p_privId = p_userAccount.getPrivId();
        /* 共同募集 顧客管理一覧を取得する */
        // SQLリクエストを作成する
        IfaJointSubscriptCustomerManageSql001RequestModel p_sql001Req = new IfaJointSubscriptCustomerManageSql001RequestModel();
        BeanUtils.copyProperties(p_sql001Req, x_dtoReq);
        p_sql001Req.setEditStatus(StringUtil.trim(p_sql001Req.getEditStatus()));                     // 手続状況
        p_sql001Req.setQuerySizeLimit(g_util.getQuerySizeLimit());                                   // 最大表示レコード数
        p_sql001Req.setPrivId(p_privId);                                                             // 権限コード
        p_sql001Req.setBroker0509(IfaJointSubscriptCustomerManageUtil.BrokerCode.MONEYPLAZA.key);    // SBIマネープラザ
        p_sql001Req.setLoginId(p_userAccount.getUserId());                                           // ログインユーザ
        // SQL実行
        DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> p_sql001Res = g_dao.selectIfaJointSubscriptCustomerManageSql001(p_sql001Req);
        // 取得データは0件の場合、エラーメッセージを表示し、処理終了。
        if (CollectionUtils.isEmpty(p_sql001Res.getDataList())) {
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(),
                        ErrorLevel.INFO,
                        IfaJointSubscriptCustomerManageUtil.Message.ERRORS_DATALIST_NOTFOUND.key,
                        IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.ERRORS_DATALIST_NOTFOUND.key)
                    );
        }
        // 取得したデータをレスポンスリストに代入する
        List<IfaJointSubscriptCustomerManageA002ResponseDto> p_dtolist = convertSql001Res2A002ResList(p_sql001Res);
        // 判定用データ総件数を取得する
        BigDecimal p_totalCount = new BigDecimal(p_sql001Res.get(0).getTotalCount());
        // 判定用検索最大件数を取得する
        BigDecimal p_querySizeLt = new BigDecimal(g_util.getQuerySizeLimit());

        // 戻りレスポンスモデルを初期化する
        DataList<IfaJointSubscriptCustomerManageA002ResponseDto> p_dtoRes = null;

        // 総件数が5000件超過の場合、ワーニングメッセージを表示する
        if (p_totalCount.compareTo(p_querySizeLt) > 0) {
            p_dtoRes = IfaCommonUtil.createDataList(
                        p_dtolist, 
                        ErrorLevel.WARNING, 
                        IfaJointSubscriptCustomerManageUtil.Message.WARNINGS_DATALIST_OVERMAXROWNUM.key, 
                        IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                                new String[] {
                                        String.valueOf(g_util.getQuerySizeLimit()), 
                                        String.valueOf(p_totalCount.toString())}
                        )
                    );
            p_dtoRes.setOverMaxRownum(true);
        // 正常件数の場合、成功メッセージを表示する。
        } else {
            p_dtoRes = IfaCommonUtil.createDataList(
                    p_dtolist, 
                    ErrorLevel.SUCCESS, 
                    StringUtil.EMPTY_STRING, 
                    StringUtil.EMPTY_STRING
                );
            p_dtoRes.setOverMaxRownum(false);
        }
        // 検索制限の最大件数をセットする
        p_dtoRes.setMaxRownum(p_querySizeLt.intValue());
        // データ総件数をセットする
        p_dtoRes.setTotalSize(p_totalCount.intValue());

        // レスポンスを返却する
        return p_dtoRes;
    }

    /**
     * アクションID：A005
     * アクション名：承認確認
     * Dto リクエスト：IfaJointSubscriptCustomerManageA005RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA005ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA005ResponseDto> confirmApproveA005(
            IfaJointSubscriptCustomerManageA005RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.confirmApproveA005");

        List<IfaJointSubscriptCustomerManageA005Model> p_a005list = x_dtoReq.getA005Model();
        // 1件以外のデータを選択した場合、エラーメッセージを表示し、処理を終了。
        if (p_a005list.size() < 1) {
            // レスポンスにエラーを設定する
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(), 
                        ErrorLevel.FATAL, 
                        IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_REQUIRED.key,
                        IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_REQUIRED.key,
                                new String[] {"1件以上"})
                    );
        }
        // 選択されたデータの中に、「手続状況」が「承認」であるデータがある場合、エラーメッセージを表示し、処理を終了
        boolean p_hasApproved = p_a005list.stream().anyMatch(
                model -> IfaJointSubscriptCustomerManageUtil.EditStats.APPROVE.key.equals(model.getEditStatus()));
        if (p_hasApproved) {
            // レスポンスにエラーを設定する
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(), 
                        ErrorLevel.FATAL, 
                        IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_APPROVED.key,
                        IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_APPROVED.key)
                    );
        }
        
        // ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~3)権限以外の場合、エラーメッセージを表示し、処理を終了
        String[] p_chkRtn = g_util.checkPrivId(
                IfaCommonUtil.getUserAccount().getPrivId(), 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_APPROVE.key);
        if (p_chkRtn != null) {
            // レスポンスにエラーを設定する
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }
        

        IfaJointSubscriptCustomerManageCommModel p_commModel = null;
        String p_dbResult = StringUtil.EMPTY_STRING;
        // A005.3、4、5 複数のデータが選択された場合、以下の処理を繰り返し実行する
        for (IfaJointSubscriptCustomerManageA005Model p_a005Model : p_a005list) {
            p_commModel = new IfaJointSubscriptCustomerManageCommModel();
            BeanUtils.copyProperties(p_commModel, p_a005Model);
            p_commModel.setEditStatus(IfaJointSubscriptCustomerManageUtil.EditStats.APPROVE.key);
            // DBの共通処理部を行う。
            try {
                p_dbResult = g_util.execDBcommonLogic(g_dao, p_commModel);
            } catch (IfaJointSubscriptCustomerException ex) {
                p_dbResult = ex.getMessageId();
            }
            // 各データの処理が成功した後、次のデータの処理を行います。
            if (IfaJointSubscriptCustomerManageUtil.DBexecRtn.SUCCESS.key.equals(p_dbResult)) {
                continue;
            } else {
                return g_util.creatDataList(p_dbResult, 
                        IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_APPROVE.key);
            }
        }
        // エラーがなければ、正常を戻す
        return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.SUCCESS, 
                    IfaJointSubscriptCustomerManageUtil.Message.INFO_ENDCOMPLETED.key,
                    IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.INFO_ENDCOMPLETED.key, 
                            new String[] {IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_APPROVE.key})
                );
    }

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointSubscriptCustomerManageA006RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA006ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @param x_sessionId セクションID
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA006ResponseDto> csvOutputA006(
            IfaJointSubscriptCustomerManageA006RequestDto x_dtoReq, String x_sessionId) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.csvOutputA006");

        // ユーザ共通情報の取得
        UserAccount p_userAccount = IfaCommonUtil.getUserAccount();
        String p_privId = p_userAccount.getPrivId();
        //CSV出力パターン (ディフォルト：普通)
        String CsvOutputPattern = "1";
        if("1".equals(p_privId) || "2".equals(p_privId) ){
            //権限: 1:本店, 2:支店場合、自動解約理由を出力する
            CsvOutputPattern = "2";
        }

        // CSVダウンロード件数取得
        InputFct038Dto p_fct038Dto = new InputFct038Dto();
        p_fct038Dto.setScreenId(IfaJointSubscriptCustomerManageUtil.ScreenId.SUB0206_01_01.key);
        p_fct038Dto.setUserAuthority(p_privId);
        int p_maxCsvRow = g_fct038.getData(p_fct038Dto).getCsvDownloadNum();    // 上限件数

        DataList<IfaJointSubscriptCustomerManageA006ResponseDto> p_dtoRes = new DataList<IfaJointSubscriptCustomerManageA006ResponseDto>();
        List<IfaJointSubscriptCustomerManageA006ResponseDto> p_dtoResList = new ArrayList<IfaJointSubscriptCustomerManageA006ResponseDto>();

        /* 共同募集 顧客管理一覧を取得する */
        // SQLリクエストを作成する
        IfaJointSubscriptCustomerManageSql001RequestModel p_sql001Req = new IfaJointSubscriptCustomerManageSql001RequestModel();
        BeanUtils.copyProperties(p_sql001Req, x_dtoReq);
        p_sql001Req.setEditStatus(StringUtil.trim(p_sql001Req.getEditStatus()));                    // 手続状況
        p_sql001Req.setQuerySizeLimit(p_maxCsvRow);                                                 // 最大出力レコード数
        p_sql001Req.setPrivId(p_privId);                                                            // 権限コード
        p_sql001Req.setBroker0509(IfaJointSubscriptCustomerManageUtil.BrokerCode.MONEYPLAZA.key);   // SBIマネープラザ
        p_sql001Req.setLoginId(p_userAccount.getUserId());                                          // ログインユーザ
        
        // SQL実行
        DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> p_sql001Res = g_dao.selectIfaJointSubscriptCustomerManageSql001(p_sql001Req);
        // SQLの実行結果が0件の場合はCSVファイルを出力せず終了する
        if (CollectionUtils.isEmpty(p_sql001Res.getDataList())) {
            // 0件の場合、SQL実行結果を返却する（エラーレベル、エラーメッセージはコントローラにて設定）
            return IfaCommonUtil.createDataList(
                    Collections.emptyList(),
                    ErrorLevel.INFO,
                    IfaJointSubscriptCustomerManageUtil.Message.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.ERRORS_DATALIST_NOTFOUND.key)
                );
        }
        // SQL総件数を取得する
        String p_totalCount = p_sql001Res.get(0).getTotalCount();
        // SQL総件数＞CSVダウンロードMAX件数の場合、メッセージを表示する
        if (new BigDecimal(p_totalCount).compareTo(new BigDecimal(p_maxCsvRow))  > 0) {
            p_dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            p_dtoRes.setMessage(IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key,
                    new String[] {
                            String.valueOf(p_maxCsvRow), 
                            p_totalCount,
                            String.valueOf(p_maxCsvRow)
                    }
            ));
            p_dtoRes.setReturnCode(IfaJointSubscriptCustomerManageUtil.Message.WARNINGS_DATALIST_CSV_OVERMAXROWNUM.key);
        }
        /* CSVファイル用のデータを作成する。 */
        // CSVユーティリティを呼出す
        CsvOutPutUtil p_csvOut = new IfaJointSubscriptCustomerManageCsvOut(g_complianceService);
        // CSV出力を行うためのDTO定義でDataListを定義する
        DataList<IfaJointSubscriptCustomerManageA006CsvOutModel> p_csvDataList = new DataList<>();
        // SQLの実行結果をCSV出力が出来るように詰め替え処理を行う
        p_csvDataList.setDataList(convertSql001Res2A006List(p_sql001Res));
        // DataListに最終的に設定するエラーレベルなどはController側で設定する(BaseController.setStatusAndMessageToDataList()で使用)る
        p_dtoRes.setDataList(p_dtoResList);
        p_dtoRes.setErrorLevel(ErrorLevel.SUCCESS.getId());
        p_dtoRes.setMaxRownum(p_maxCsvRow);
        p_dtoRes.setOverMaxRownum(true);
        // titleにCSVファイルのフルパス名をセット
        p_dtoRes.setTitle(p_csvOut.doCreateCsvFile(p_csvDataList, x_sessionId, p_userAccount.getUserId(), CsvOutputPattern));
        // ここでは分析関数で取得した件数をTotalsizeに設定
        p_dtoRes.setTotalSize(Integer.valueOf(p_totalCount));
        return p_dtoRes;
    }

    /**
     * アクションID：A007
     * アクション名：削除確認
     * Dto リクエスト：IfaJointSubscriptCustomerManageA007RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA007ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA007ResponseDto> confirmDeleteA007(
            IfaJointSubscriptCustomerManageA007RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.confirmDeleteA007");

        // 1件以外のデータを選択した場合、エラーメッセージを表示し、処理を終了。
        if (x_dtoReq.getA007Model().size() != 1) {
            // レスポンスにエラーを設定する
            return IfaCommonUtil.createDataList(
                        Collections.emptyList(), 
                        ErrorLevel.FATAL, 
                        IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_REQUIRED.key,
                        IfaCommonUtil.getMessage(IfaJointSubscriptCustomerManageUtil.Message.ERRORS_JOINT_DATA_SELECTION_REQUIRED.key,
                                new String[] {"1件"})
                    );
        }
        // ユーザ共通情報.権限コードは本店、支店、仲介業者内管 (1~3)権限以外の場合、エラーメッセージを表示し、処理を終了
        String[] p_chkRtn = g_util.checkPrivId(
                IfaCommonUtil.getUserAccount().getPrivId(), 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_DELETE.key);
        if (p_chkRtn != null) {
            // レスポンスにエラーを設定する
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, p_chkRtn[0], p_chkRtn[1]);
        }

        // A007.3、4、5 を実行する
        IfaJointSubscriptCustomerManageA007Model p_a007Model = x_dtoReq.getA007Model().get(0);
        IfaJointSubscriptCustomerManageCommModel p_commModel = new IfaJointSubscriptCustomerManageCommModel();
        BeanUtils.copyProperties(p_commModel, p_a007Model);
        p_commModel.setEditStatus(IfaJointSubscriptCustomerManageUtil.EditStats.DELETE.key);
        // DBの共通処理部を行う。
        String p_dbResult = StringUtil.EMPTY_STRING;
        try {
            p_dbResult = g_util.execDBcommonLogic(g_dao, p_commModel);
        } catch (IfaJointSubscriptCustomerException ex) {
            p_dbResult = ex.getMessageId();
        }
        // 戻る
        return g_util.creatDataList(p_dbResult, 
                IfaJointSubscriptCustomerManageUtil.Message.MSG_PARAMETER_DELETE.key);
    }

    /**
     * アクションID：A008
     * アクション名：顧客情報詳細
     * Dto リクエスト：IfaJointSubscriptCustomerManageA008RequestDto
     * Dto レスポンス：IfaJointSubscriptCustomerManageA008ResponseDto
     *
     * @param x_dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaJointSubscriptCustomerManageA008ResponseDto> customerDetailA008(
            IfaJointSubscriptCustomerManageA008RequestDto x_dtoReq) throws Exception {

        if (LOGGER.isDebugEnabled()) LOGGER.debug("IfaJointSubscriptCustomerManageServiceImpL.customerDetailA008");

        /* 顧客情報　詳細を取得する */
        IfaJointSubscriptCustomerManageA008ResponseDto p_dto = new IfaJointSubscriptCustomerManageA008ResponseDto();
        // 検索条件をセットする
        IfaJointSubscriptCustomerManageSql002RequestModel p_sqlReq = new IfaJointSubscriptCustomerManageSql002RequestModel();
        BeanUtils.copyProperties(p_sqlReq, x_dtoReq);
        // SQL実行
        DataList<IfaJointSubscriptCustomerManageSql002ResponseModel> p_sql002Res = g_dao.selectIfaJointSubscriptCustomerManageSql002(p_sqlReq);
        if (p_sql002Res.getDataList().size() >= 1) {
            BeanUtils.copyProperties(p_dto, p_sql002Res.getDataList().get(0));
        }
        // 取得したデータをレスポンスモデルに代入し、レスポンスを返却する
        return IfaCommonUtil.createDataList(
                            Arrays.asList(p_dto),
                            ErrorLevel.SUCCESS,
                            StringUtil.EMPTY_STRING,
                            StringUtil.EMPTY_STRING
                        );
    }

    /**
     * SQL実行結果を指定したモデル型に変換する.
     *
     * @param x_sql001Res SQL実行結果のDataList
     * @return 変換後のデータリスト
     */
    private List<IfaJointSubscriptCustomerManageA002ResponseDto> convertSql001Res2A002ResList(
            DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> x_sql001Res) {

        // SQL実行結果のDataListを取得する
        List<IfaJointSubscriptCustomerManageSql001ResponseModel> p_sql001List = x_sql001Res.getDataList();
        // データリストをストリームに変換し、各要素を指定したクラス型に変換する
        List<IfaJointSubscriptCustomerManageA002ResponseDto> p_a002ResList = p_sql001List.stream().map(p_sql001Model -> {
            IfaJointSubscriptCustomerManageA002ResponseDto p_a002dto = new IfaJointSubscriptCustomerManageA002ResponseDto();
            p_a002dto.setTotalCount(p_sql001Model.getTotalCount());
            p_a002dto.setBrokerCode(p_sql001Model.getBrokerCode());
            p_a002dto.setBrokerName(p_sql001Model.getBrokerName());
            p_a002dto.setButenCode(p_sql001Model.getButenCode());
            p_a002dto.setAccountNumber(p_sql001Model.getAccountNumber());
            p_a002dto.setCustomerAttributeName(p_sql001Model.getCustomerAttributeName());
            p_a002dto.setNameKanji(p_sql001Model.getNameKanji());
            p_a002dto.setJointBranchCode(p_sql001Model.getJointBranchCode());
            p_a002dto.setJointBranchName(p_sql001Model.getJointBranchName());
            p_a002dto.setContractDate(p_sql001Model.getContractDate());
            p_a002dto.setStartDate(p_sql001Model.getStartDate());
            p_a002dto.setEndDate(p_sql001Model.getEndDate());
            p_a002dto.setJointRewardRate(p_sql001Model.getJointRewardRate());
            p_a002dto.setEditStatus(p_sql001Model.getEditStatus());
            p_a002dto.setEditStatusName(p_sql001Model.getEditStatusName());
            p_a002dto.setBrokerChargeCode(p_sql001Model.getBrokerChargeCode());
            p_a002dto.setBrokerChargeName(p_sql001Model.getBrokerChargeName());
            p_a002dto.setAutoCancellationReason(p_sql001Model.getJointUserDispKbn());
            return p_a002dto;
        }).filter(Objects::nonNull).collect(Collectors.toList()); // nullでない要素だけをリストに収集する
        return p_a002ResList; // 変換後のデータリストを返す
    }

    /**
     * SQL実行結果を指定したモデル型に変換する.
     *
     * @param x_sql001Res SQL実行結果のDataList
     * @return 変換後のデータリスト
     */
    private List<IfaJointSubscriptCustomerManageA006CsvOutModel> convertSql001Res2A006List(
            DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> x_sql001Res) {

        // SQL実行結果のDataListを取得する
        List<IfaJointSubscriptCustomerManageSql001ResponseModel> p_sql001List = x_sql001Res.getDataList();
        // データリストをストリームに変換し、各要素を指定したクラス型に変換する
        List<IfaJointSubscriptCustomerManageA006CsvOutModel> p_a006List = p_sql001List.stream().map(p_sql001Model -> {
            IfaJointSubscriptCustomerManageA006CsvOutModel p_a002mod = new IfaJointSubscriptCustomerManageA006CsvOutModel();
            p_a002mod.setTotalCount(p_sql001Model.getTotalCount());
            p_a002mod.setBrokerCode(p_sql001Model.getBrokerCode());
            p_a002mod.setBrokerName(p_sql001Model.getBrokerName());
            p_a002mod.setButenCode(p_sql001Model.getButenCode());
            p_a002mod.setAccountNumber(p_sql001Model.getAccountNumber());
            p_a002mod.setCustomerAttributeName(p_sql001Model.getCustomerAttributeName());
            p_a002mod.setNameKanji(p_sql001Model.getNameKanji());
            p_a002mod.setJointBranchCode(p_sql001Model.getJointBranchCode());
            p_a002mod.setJointBranchName(p_sql001Model.getJointBranchName());
            p_a002mod.setContractDate(p_sql001Model.getContractDate());
            p_a002mod.setStartDate(p_sql001Model.getStartDate());
            p_a002mod.setEndDate(p_sql001Model.getEndDate());
            p_a002mod.setJointRewardRate(p_sql001Model.getJointRewardRate());
            p_a002mod.setEditStatus(p_sql001Model.getEditStatus());
            p_a002mod.setEditStatusName(p_sql001Model.getEditStatusName());
            p_a002mod.setBrokerChargeCode(p_sql001Model.getBrokerChargeCode());
            p_a002mod.setBrokerChargeName(p_sql001Model.getBrokerChargeName());
            p_a002mod.setJointUserDispKbn(codeListService.getValue(
            		JOINT_USER_REASON_CODE,p_sql001Model.getJointUserDispKbn(),JOINT_USER_REASON_CODE_DISP_PATTERN));
            return p_a002mod;
        }).filter(Objects::nonNull).collect(Collectors.toList()); // nullでない要素だけをリストに収集する
        return p_a006List; // 変換後のデータリストを返す
    }
}