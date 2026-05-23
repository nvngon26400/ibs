package com.sbisec.helios.ap.suggestionBox.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.CsvOutPutUtil;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.CodeListService;

import com.sbisec.helios.ap.compliance.service.ComplianceService;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;

import com.sbisec.helios.ap.suggestionBox.dao.IfaSuggestionBoxPersonalDao;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql001ResponseModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql002RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql003RequestModel;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalSql002_3ResponseModel;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalA006CsvItem;
import com.sbisec.helios.ap.suggestionBox.service.IfaSuggestionBoxPersonalService;
import com.sbisec.helios.ap.suggestionBox.util.IfaSuggestionBoxPersonalCsvOut;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 * @author SCSK神木
 * 2025/06/12 新規作成
 */
@Component(value = "cmpIfaSuggestionBoxPersonalService")
public class IfaSuggestionBoxPersonalServiceImpL implements IfaSuggestionBoxPersonalService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 正常終了 */
    private static final String SUCCESS_MESSAGE = "正常終了";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。 */
    private static final String WARNINGS_DATALIST_OVERMAXROWNUM = "warnings.dataList.overMaxRownum";

    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のcsv出力を行います。*/
    private static final String WARNINGS_DATALIST_CSV_OVERMAXROWNUM = "warnings.dataList.csv.overMaxRownum";

    
    // --------------------------------
    // 変数定義
    // --------------------------------   
    /** 画面表示最大件数 */
    private static final int MAXIMUM_LIMIT_DISP = 5000;

    /** 画面ID */
    private static final String SCREEN_ID = "SUB0511_01-01";

    /** 区分.目安箱ステータス */
    private static final String SUG_BOX_STATUS = "SUG_BOX_STATUS";

    /** 区分.目安箱カテゴリ */
    private static final String SUG_BOX_CATEGORY = "SUG_BOX_CATEGORY";

    @Autowired
    private IfaSuggestionBoxPersonalDao dao;

    @Autowired
    private Fct038 fct038;

    @Autowired
    private ComplianceService complianceService;

    @Autowired
    private CodeListService codeListService;

    /**
     * アクションID：X001
     * アクション名：初期化
     * Dtoリクエスト：IfaSuggestionBoxPersonalInitializeX001RequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalInitializeX001ResponseDto
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto> initializeX001(IfaSuggestionBoxPersonalInitializeX001RequestDto dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalServiceImplL.initializeX001");
        }

        DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto> dtoResDataList = new DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto>();
        List<IfaSuggestionBoxPersonalInitializeX001ResponseDto> resDtoList = new ArrayList<IfaSuggestionBoxPersonalInitializeX001ResponseDto>();
        IfaSuggestionBoxPersonalInitializeX001ResponseDto responseDto = new IfaSuggestionBoxPersonalInitializeX001ResponseDto();


        // SQL001を実行し、画面コメントを取得する。
        List<IfaSuggestionBoxPersonalSql001ResponseModel> selSql001res = dao
                .selectIfaSuggestionBoxPersonalSql001().getDataList();


        // SQL001の結果を設定
        if (selSql001res.size() > 0 && selSql001res.get(0) != null) {
            responseDto.setScreenComment(selSql001res.get(0).getScreenComment());
        }


        // SQL002を実行し、要望一覧を取得する。
        // ユーザ共通情報を取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        // 共通のリクエストパラメータを設定
        String status = dtoReq.getStatus();
        String registerDateFrom = dtoReq.getRegisterDateFrom();
        String registerDateTo = dtoReq.getRegisterDateTo();
        String title = dtoReq.getTitle();

        // ステータが全ての場合、nullをセット
        if (StringUtils.equals(status, " ")) {
        	status = null;
        }
        // 値が空文字の場合、nullをセット
        title = StringUtil.emptyToNull(title);

        // ユーザ共通情報.権限コードが仲介業者（3～9）の場合
        if (StringUtils.equals(privId, PrivId.B_INNER_MANAGEMENT.getId())
        	    || StringUtils.equals(privId, PrivId.B_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.B_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_INNER_MANAGEMENT.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.RESPONSIBLE.getId())) {

            // SQL002の引数を設定
            IfaSuggestionBoxPersonalSql002RequestModel selSql002Req = new IfaSuggestionBoxPersonalSql002RequestModel();
            selSql002Req.setMaxRow(MAXIMUM_LIMIT_DISP);
            selSql002Req.setUserId(userId);
            selSql002Req.setStatus(status);
            selSql002Req.setRegisterDateFrom(registerDateFrom);
            selSql002Req.setRegisterDateTo(registerDateTo);
            selSql002Req.setTitle(title);

            List<IfaSuggestionBoxPersonalSql002_3ResponseModel> selSql002Res = dao
                    .selectIfaSuggestionBoxPersonalSql002(selSql002Req).getDataList();

            // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
            if (selSql002Res.isEmpty()) {
                // 画面infoコメント のみ出力
                resDtoList.add(responseDto);
                
                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.INFO,
                        ERRORS_DATA_LIST_NOTFOUND,
                        IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND)
                        );
                return dtoResDataList;
            }

            // SQLの実行結果が最大件数を超過する場合は警告メッセージセットし、次の処理へ
            if (selSql002Res.size() != 0 && Integer.valueOf(selSql002Res.get(0).getTotalRow()) > MAXIMUM_LIMIT_DISP) {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql002Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.WARNING,
                        WARNINGS_DATALIST_OVERMAXROWNUM
                        , IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                        new String[] { String.valueOf(MAXIMUM_LIMIT_DISP), String.valueOf(selSql002Res.get(0).getTotalRow()) }));
            } else {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql002Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                // 結果をレスポンスDTOに設定する
                dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
            }

         // ユーザ共通情報.権限コードが証券社員（1）の場合
        } else if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {

            // SQL003の引数を設定
            IfaSuggestionBoxPersonalSql003RequestModel selSql003Req = new IfaSuggestionBoxPersonalSql003RequestModel();
            selSql003Req.setMaxRow(MAXIMUM_LIMIT_DISP);
            selSql003Req.setStatus(status);
            selSql003Req.setRegisterDateFrom(registerDateFrom);
            selSql003Req.setRegisterDateTo(registerDateTo);
            selSql003Req.setTitle(title);

            List<IfaSuggestionBoxPersonalSql002_3ResponseModel> selSql003Res = dao
                    .selectIfaSuggestionBoxPersonalSql003(selSql003Req).getDataList();

            // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
            if (selSql003Res.isEmpty()) {
                // 画面infoコメント のみ出力
                resDtoList.add(responseDto);
                
                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.INFO,
                        ERRORS_DATA_LIST_NOTFOUND,
                        IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND)
                        );
                return dtoResDataList;
            }

            // SQLの実行結果が最大件数を超過する場合は警告メッセージセットし、次の処理へ
            if (selSql003Res.size() != 0 && Integer.valueOf(selSql003Res.get(0).getTotalRow()) > MAXIMUM_LIMIT_DISP) {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql003Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.WARNING,
                        WARNINGS_DATALIST_OVERMAXROWNUM
                        , IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                        new String[] { String.valueOf(MAXIMUM_LIMIT_DISP), String.valueOf(selSql003Res.get(0).getTotalRow()) }));
            } else {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql003Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                // 結果をレスポンスDTOに設定する
                dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
            }
        }
        return dtoResDataList;
    }


    /**
     * アクションID：A002
     * アクション名：表示
     * Dtoリクエスト：IfaSuggestionBoxPersonalDisplayA002RequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalDisplayA002ResponseDto
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto> displayA002(IfaSuggestionBoxPersonalDisplayA002RequestDto dtoReq)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalServiceImplL.displayA002");
        }

        DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto> dtoResDataList = new DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto>();
        List<IfaSuggestionBoxPersonalDisplayA002ResponseDto> resDtoList = new ArrayList<IfaSuggestionBoxPersonalDisplayA002ResponseDto>();
        IfaSuggestionBoxPersonalDisplayA002ResponseDto responseDto = new IfaSuggestionBoxPersonalDisplayA002ResponseDto();


        // SQL002を実行し、要望一覧を取得する。
        // ユーザ共通情報を取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        // 共通のリクエストパラメータを設定
        String brokerCode = dtoReq.getBrokerCode();
        String brokerName = dtoReq.getBrokerName();
        String status = dtoReq.getStatus();
        String registerDateFrom = dtoReq.getRegisterDateFrom();
        String registerDateTo = dtoReq.getRegisterDateTo();
        String title = dtoReq.getTitle();

        // ステータが全ての場合、nullをセット
        if (StringUtils.equals(status, " ")) {
            status = null;
        }
        // 値が空文字の場合、nullをセット
        brokerCode = StringUtil.emptyToNull(brokerCode);
        brokerName = StringUtil.emptyToNull(brokerName);
        title = StringUtil.emptyToNull(title);

        // ユーザ共通情報.権限コードが仲介業者（3～9）の場合
        if (StringUtils.equals(privId, PrivId.B_INNER_MANAGEMENT.getId())
                || StringUtils.equals(privId, PrivId.B_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.B_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_INNER_MANAGEMENT.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.RESPONSIBLE.getId())) {

            // SQL002の引数を設定
            IfaSuggestionBoxPersonalSql002RequestModel selSql002Req = new IfaSuggestionBoxPersonalSql002RequestModel();
            selSql002Req.setMaxRow(MAXIMUM_LIMIT_DISP);
            selSql002Req.setUserId(userId);
            selSql002Req.setStatus(status);
            selSql002Req.setRegisterDateFrom(registerDateFrom);
            selSql002Req.setRegisterDateTo(registerDateTo);
            selSql002Req.setTitle(title);

            List<IfaSuggestionBoxPersonalSql002_3ResponseModel> selSql002Res = dao
                    .selectIfaSuggestionBoxPersonalSql002(selSql002Req).getDataList();

            // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
            if (selSql002Res.isEmpty()) {
                dtoResDataList = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.INFO,
                        ERRORS_DATA_LIST_NOTFOUND,
                        IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND)
                        );
                return dtoResDataList;
            }

            // SQLの実行結果が最大件数を超過する場合は警告メッセージセットし、次の処理へ
            if (selSql002Res.size() != 0 && Integer.valueOf(selSql002Res.get(0).getTotalRow()) > MAXIMUM_LIMIT_DISP) {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql002Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.WARNING,
                        WARNINGS_DATALIST_OVERMAXROWNUM
                        , IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                        new String[] { String.valueOf(MAXIMUM_LIMIT_DISP), String.valueOf(selSql002Res.get(0).getTotalRow()) }));
            } else {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql002Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                // 結果をレスポンスDTOに設定する
                dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
            }


         // ユーザ共通情報.権限コードが証券社員（1）の場合
        } else if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {

            // SQL003の引数を設定
            IfaSuggestionBoxPersonalSql003RequestModel selSql003Req = new IfaSuggestionBoxPersonalSql003RequestModel();
            selSql003Req.setMaxRow(MAXIMUM_LIMIT_DISP);
            selSql003Req.setBrokerCode(brokerCode);
            selSql003Req.setBrokerName(brokerName);
            selSql003Req.setStatus(status);
            selSql003Req.setRegisterDateFrom(registerDateFrom);
            selSql003Req.setRegisterDateTo(registerDateTo);
            selSql003Req.setTitle(title);

            List<IfaSuggestionBoxPersonalSql002_3ResponseModel> selSql003Res = dao
                    .selectIfaSuggestionBoxPersonalSql003(selSql003Req).getDataList();

            // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
            if (selSql003Res.isEmpty()) {
                dtoResDataList = IfaCommonUtil.createDataList(
                        new ArrayList<>(),
                        ErrorLevel.INFO,
                        ERRORS_DATA_LIST_NOTFOUND,
                        IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND)
                        );
                return dtoResDataList;
            }

            // SQLの実行結果が最大件数を超過する場合は警告メッセージセットし、次の処理へ
            if (selSql003Res.size() != 0 && Integer.valueOf(selSql003Res.get(0).getTotalRow()) > MAXIMUM_LIMIT_DISP) {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql003Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                dtoResDataList = IfaCommonUtil.createDataList(
                        resDtoList,
                        ErrorLevel.WARNING,
                        WARNINGS_DATALIST_OVERMAXROWNUM
                        , IfaCommonUtil.getMessage(WARNINGS_DATALIST_OVERMAXROWNUM,
                        new String[] { String.valueOf(MAXIMUM_LIMIT_DISP), String.valueOf(selSql003Res.get(0).getTotalRow()) }));
            } else {
                // ResponseDTOにSQL結果を設定
                responseDto.setRequestList(selSql003Res);

                // ResponseDTOListへResponseDTOを格納
                resDtoList.add(responseDto);

                // 結果をレスポンスDTOに設定する
                dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
            }
        }
        return dtoResDataList;
    }


    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dtoリクエスト：IfaSuggestionBoxPersonalCsvOutputA006aRequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalCsvOutputA006aResponseDto
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto> csvOutputA006(IfaSuggestionBoxPersonalCsvOutputA006aRequestDto dtoReq, String fwSessionId)
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSuggestionBoxPersonalServiceImplL.csvOutputA006");
        }

        DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto> dtoResDataList = new DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto>();
        List<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto> resDtoList = new ArrayList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto>();
        IfaSuggestionBoxPersonalCsvOutputA006aResponseDto responseDto = new IfaSuggestionBoxPersonalCsvOutputA006aResponseDto();

        // CSVダウンロードMAX件数取得
        InputFct038Dto inputFct038Dto = new InputFct038Dto();
        inputFct038Dto.setScreenId(SCREEN_ID);
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        inputFct038Dto.setUserAuthority(userAccount.getPrivId());
        OutputFct038Dto outputFct038Dto = fct038.getData(inputFct038Dto);    
        int csvMaxLimitNum = outputFct038Dto.getCsvDownloadNum();

        // 共通のリクエストパラメータを設定
        String brokerCode = dtoReq.getBrokerCode();
        String brokerName = dtoReq.getBrokerName();
        String status = dtoReq.getStatus();
        String registerDateFrom = dtoReq.getRegisterDateFrom();
        String registerDateTo = dtoReq.getRegisterDateTo();
        String reqTitle = dtoReq.getTitle();

        // ステータが全ての場合、nullをセット
        if (StringUtils.equals(status, " ")) {
            status = null;
        }
        // 値が空文字の場合、nullをセット
        brokerCode = StringUtil.emptyToNull(brokerCode);
        brokerName = StringUtil.emptyToNull(brokerName);
        reqTitle = StringUtil.emptyToNull(reqTitle);

        // SQL003の引数を設定
        IfaSuggestionBoxPersonalSql003RequestModel selSql003Req = new IfaSuggestionBoxPersonalSql003RequestModel();
        selSql003Req.setMaxRow(csvMaxLimitNum);
        selSql003Req.setBrokerCode(brokerCode);
        selSql003Req.setBrokerName(brokerName);
        selSql003Req.setStatus(status);
        selSql003Req.setRegisterDateFrom(registerDateFrom);
        selSql003Req.setRegisterDateTo(registerDateTo);
        selSql003Req.setTitle(reqTitle);

        List<IfaSuggestionBoxPersonalSql002_3ResponseModel> selSql003Res = dao
                .selectIfaSuggestionBoxPersonalSql003(selSql003Req).getDataList();
    
        // SQLの実行結果が0件の場合はレスポンス結果を処理せず終了する
        if (selSql003Res.isEmpty()) {
            dtoResDataList = IfaCommonUtil.createDataList(
                    new ArrayList<>(),
                    ErrorLevel.INFO,
                    ERRORS_DATA_LIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND)
                    );
            return dtoResDataList;
        }

        // SQL呼び出し結果をレスポンスDTOに設定
        responseDto.setRequestList(selSql003Res);
        // レスポンスDTOをレスポンスDTOListに格納
        resDtoList.add(responseDto);
        // 総件数を取得
        int totalRow = selSql003Res.get(0).getTotalRow();

        // 総件数がCSV上限件以上の場合ワーニングを返す
        if (csvMaxLimitNum < totalRow) {
                    dtoResDataList = IfaCommonUtil.createDataList(
                            resDtoList,
                            ErrorLevel.WARNING,
                            WARNINGS_DATALIST_CSV_OVERMAXROWNUM
                            , IfaCommonUtil.getMessage(WARNINGS_DATALIST_CSV_OVERMAXROWNUM,
                            new String[] { String.valueOf(csvMaxLimitNum), String.valueOf(totalRow),String.valueOf(csvMaxLimitNum) }));

                    dtoResDataList.setMaxRownum(csvMaxLimitNum);
                    dtoResDataList.setTotalSize(totalRow);
        } else {
            // レスポンスDTOListからレスポンスDTODataListを作成
            dtoResDataList = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, SUCCESS_MESSAGE, null);
        }

        List<IfaSuggestionBoxPersonalA006CsvItem> csvItemList = new ArrayList<>();
        for(IfaSuggestionBoxPersonalSql002_3ResponseModel sql003Result : selSql003Res) {
            
            // SQLの結果をcsv用クラスへ振替え
            IfaSuggestionBoxPersonalA006CsvItem csvItem = setCsvItem(sql003Result);
            
            csvItemList.add(csvItem);
        }

        // CSV出力
        DataList<IfaSuggestionBoxPersonalA006CsvItem> csvDataList = new DataList<>();
        csvDataList.setDataList(csvItemList);

        CsvOutPutUtil csvOutPutUtil = new IfaSuggestionBoxPersonalCsvOut(complianceService);

        String title = csvOutPutUtil.doCreateCsvFile(csvDataList, fwSessionId,
                IfaCommonUtil.getUserAccount().getUserId(), null);

        dtoResDataList.setTitle(title);

        // 画面側へレスポンスDTODataListを返却
        return dtoResDataList;

    }

    private IfaSuggestionBoxPersonalA006CsvItem setCsvItem(IfaSuggestionBoxPersonalSql002_3ResponseModel sql003Result) {

        IfaSuggestionBoxPersonalA006CsvItem csvItem = new IfaSuggestionBoxPersonalA006CsvItem();

        csvItem.setSbpNo(sql003Result.getSbpNo());

        // 更新日の書式設定
        String ansUpTime = sql003Result.getAnsUpdateTime();
        String upDatePart = ansUpTime.split(" ")[0];
        String upFormattedDate = upDatePart.replace("-", "/");
        csvItem.setAnsUpdateTime(upFormattedDate);

        // 登録日の書式設定
        String registerDate = sql003Result.getRegisterDate();
        String regDatePart = registerDate.split(" ")[0];
        String regFormattedDate = regDatePart.replace("-", "/");
        csvItem.setRegisterDate(regFormattedDate);

        csvItem.setBrokerCode(sql003Result.getBrokerCode());
        csvItem.setBrokerName(sql003Result.getBrokerName());
        csvItem.setEmpCode(sql003Result.getEmpCode());
        csvItem.setEmpName(sql003Result.getEmpName());
        csvItem.setTitle(sql003Result.getTitle());

        // 区分.目安箱カテゴリ より表示パターン1の区分値を取得
        csvItem.setCategory(codeListService.getValue(SUG_BOX_CATEGORY, sql003Result.getCategory()));
        // 区分.目安箱ステータス より表示パターン1の区分値を取得
        csvItem.setStatus(codeListService.getValue(SUG_BOX_STATUS, sql003Result.getStatus()));

        csvItem.setSuggestion(sql003Result.getSuggestion());

        return csvItem;
        
    }

}
