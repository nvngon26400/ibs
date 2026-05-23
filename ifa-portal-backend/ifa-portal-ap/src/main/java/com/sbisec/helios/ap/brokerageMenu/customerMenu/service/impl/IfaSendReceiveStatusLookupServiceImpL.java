package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaSendReceiveStatusLookupDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001LookupBDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001LookupDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002LookupDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003LookupDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005docDeficencyDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupCsvItems;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaSendReceiveStatusLookupService;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaSendReceiveStatusLookupCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import io.micrometer.core.instrument.util.StringUtils;

/**
 * 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */

@Component(value = "cmpIfaSendReceiveStatusLookupService")
public class IfaSendReceiveStatusLookupServiceImpL implements IfaSendReceiveStatusLookupService{
    /** ロガー */
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSendReceiveStatusLookupServiceImpL.class);
 // --------------------------------
    // メッセージ
    // --------------------------------
    
    /** {0}が取得できません。 */
    private static final String ERRORS_CMN_PARAMETERS_NOT_EXIST = "errors.cmn.parameters.notExist";
    /** {0}: 受発信状況 */
    private static final String SEND_RECEIVE_STATUS = "受発信状況";
    
    /** "検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n{2}件のCSV出力を行います。 */
    private static final String WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM = "warnings.dataList.csv.overMaxRownum";
    
    /** ワーニング.{0}件のcsv出力を行います。 */
    private static final String WARNING_DATALIST_CSV_OUTPUT = "warnings.dataList.csv.output";
    
    /** 最小取得件数 */
    private static final int MIN_COUNT = 1;
    
    /** 最大取得件数（表示用） */
    private static final int MAX_COUNT_DISPLAY = 5000;
    
    /** 画面ID */
    private static final String SCREEN_ID = "SUB0202_0703-01";
    
    @Autowired
    private IfaSendReceiveStatusLookupDao dao;
    
    @Autowired
    private Fct038 fct038;
    
    @Autowired
    private ComplianceService complianceService;

    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaSendReceiveStatusLookupA001DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA001DtoResponse
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSendReceiveStatusLookupA001DtoResponse> initializeA001(
            IfaSendReceiveStatusLookupA001DtoRequest dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSendReceiveStatusLookupServiceImpL.initializeA001");
        }
        List<IfaSendReceiveStatusLookupA001DtoResponse> resList = new ArrayList<IfaSendReceiveStatusLookupA001DtoResponse>();
        DataList<IfaSendReceiveStatusLookupA001DtoResponse> dtoRes = new DataList<IfaSendReceiveStatusLookupA001DtoResponse>();
        List<IfaSendReceiveStatusLookupA001LookupDtoResponse> paperNameList = new ArrayList<IfaSendReceiveStatusLookupA001LookupDtoResponse>();
        List<IfaSendReceiveStatusLookupA001LookupBDtoResponse> statusLookupList = new ArrayList<IfaSendReceiveStatusLookupA001LookupBDtoResponse>();
        // 顧客IDを取得する
        String customerId = IfaCommonUtil.getCustomerCommon().getCustomerCode();
       
        // SQL001のリクエスト値を設定
        IfaSendReceiveStatusLookupSql001RequestModel selSql001Req = new IfaSendReceiveStatusLookupSql001RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql001Req);
        selSql001Req.setCustomerId(customerId);

        // 書類リストを取得する
        DataList<IfaSendReceiveStatusLookupSql001ResponseModel> selSql001ResList = new DataList<IfaSendReceiveStatusLookupSql001ResponseModel>();
        selSql001ResList = getBookKindList(selSql001Req);
        // レスポンスの設定
        IfaSendReceiveStatusLookupA001DtoResponse res = new IfaSendReceiveStatusLookupA001DtoResponse();
        // 書類リスト
        for (IfaSendReceiveStatusLookupSql001ResponseModel selSql001Res : selSql001ResList.getDataList()) {
            IfaSendReceiveStatusLookupA001LookupDtoResponse paperNameInfo = new IfaSendReceiveStatusLookupA001LookupDtoResponse();
            BeanUtils.copyProperties(selSql001Res, paperNameInfo);
            paperNameList.add(paperNameInfo);
        }
        res.setPaperNameList(paperNameList);
        resList.add(res);
        // SQL002のリクエスト値を設定
        IfaSendReceiveStatusLookupSql002RequestModel selSql002Req = new IfaSendReceiveStatusLookupSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        selSql002Req.setCustomerId(customerId);
        selSql002Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        
        // 受発信状況リストを取得する
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selSql002ResList = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();
        selSql002ResList = getIfaSendReceiveStatusLookupList(selSql002Req);
        // 受発信状況リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_CMN_PARAMETERS_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_CMN_PARAMETERS_NOT_EXIST, new String[] { SEND_RECEIVE_STATUS }));
            return dtoRes;
        }
       
        // レスポンスの設定
        // 受発信状況リスト
        for (IfaSendReceiveStatusLookupSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaSendReceiveStatusLookupA001LookupBDtoResponse statusLookUp = new IfaSendReceiveStatusLookupA001LookupBDtoResponse();
            BeanUtils.copyProperties(selSql002Res, statusLookUp);
            // 理由名称に含まれる文字列を置き換える
            String purposeNameFinal = replacePurposeName(selSql002Res.getPurposeName());
            statusLookUp.setPurposeName(purposeNameFinal);
            statusLookupList.add(statusLookUp);
        }
        res.setSendReceiveStatusLookupList(statusLookupList);
        resList.add(res);
        
        // 受発信状況リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql002ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_COUNT_DISPLAY),
                            String.valueOf(selSql002ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示　書類コード
     * DTO リクエスト：IfaSendReceiveStatusLookupA002DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA002DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSendReceiveStatusLookupA002DtoResponse> displayA002(
            IfaSendReceiveStatusLookupA002DtoRequest dtoReq) throws Exception {
        DataList<IfaSendReceiveStatusLookupA002DtoResponse> dtoRes = new DataList<IfaSendReceiveStatusLookupA002DtoResponse>();
        List<IfaSendReceiveStatusLookupA002DtoResponse> resList = new ArrayList<IfaSendReceiveStatusLookupA002DtoResponse>();
        List<IfaSendReceiveStatusLookupA002LookupDtoResponse> statusLookupList = new ArrayList<IfaSendReceiveStatusLookupA002LookupDtoResponse>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSendReceiveStatusLookupServiceImpL.displayA002");
        }

        // ユーザ共通情報.顧客コードを取得
        String customerId = IfaCommonUtil.getCustomerCommon().getCustomerCode();
        // SQL002のリクエスト値を設定
        IfaSendReceiveStatusLookupSql002RequestModel selSql002Req = new IfaSendReceiveStatusLookupSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        selSql002Req.setCustomerId(customerId);
        selSql002Req.setMaxRowNum(MAX_COUNT_DISPLAY);
        // 受発信状況リストを取得する
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selSql002ResList = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();
        selSql002ResList = getIfaSendReceiveStatusLookupList(selSql002Req);

        // 受発信状況リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_CMN_PARAMETERS_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_CMN_PARAMETERS_NOT_EXIST, new String[] { SEND_RECEIVE_STATUS }));
            return dtoRes;
        }

        IfaSendReceiveStatusLookupA002DtoResponse res = new IfaSendReceiveStatusLookupA002DtoResponse();

        // レスポンスの設定
        for (IfaSendReceiveStatusLookupSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaSendReceiveStatusLookupA002LookupDtoResponse statusLookUp = new IfaSendReceiveStatusLookupA002LookupDtoResponse();
            BeanUtils.copyProperties(selSql002Res, statusLookUp);
         // 理由名称に含まれる文字列を置き換える
            String purposeNameFinal = replacePurposeName(selSql002Res.getPurposeName());
            statusLookUp.setPurposeName(purposeNameFinal);
            statusLookupList.add(statusLookUp);
        }
        res.setSendReceiveStatusLookupList(statusLookupList);
        resList.add(res);
        
        // 受発信状況リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql002ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, new String[] {
                    String.valueOf(MAX_COUNT_DISPLAY), String.valueOf(selSql002ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }

        return dtoRes;
    }
    /**
     * アクションID：A003
     * アクション名：検索ボタン　検索(キーワード)
     * DTO リクエスト：IfaSendReceiveStatusLookupA003DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA003DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSendReceiveStatusLookupA003DtoResponse> displayA003(
            IfaSendReceiveStatusLookupA003DtoRequest dtoReq) throws Exception {
        DataList<IfaSendReceiveStatusLookupA003DtoResponse> dtoRes = new DataList<IfaSendReceiveStatusLookupA003DtoResponse>();
        List<IfaSendReceiveStatusLookupA003DtoResponse> resList = new ArrayList<IfaSendReceiveStatusLookupA003DtoResponse>();
        List<IfaSendReceiveStatusLookupA003LookupDtoResponse> statusLookupList = new ArrayList<IfaSendReceiveStatusLookupA003LookupDtoResponse>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSendReceiveStatusLookupServiceImpL.displayA003");
        }

        // ユーザ共通情報.顧客ID
        String customerId = IfaCommonUtil.getCustomerCommon().getCustomerCode();
        // SQL002のリクエスト値を設定
        IfaSendReceiveStatusLookupSql002RequestModel selSql002Req = new IfaSendReceiveStatusLookupSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        selSql002Req.setCustomerId(customerId);
        selSql002Req.setMaxRowNum(MAX_COUNT_DISPLAY);

        // 受発信状況リストを取得する
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selSql002ResList = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();
        selSql002ResList = getIfaSendReceiveStatusLookupList(selSql002Req);

        // 受発信状況リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_CMN_PARAMETERS_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_CMN_PARAMETERS_NOT_EXIST, new String[] { SEND_RECEIVE_STATUS }));
            return dtoRes;
        }

        IfaSendReceiveStatusLookupA003DtoResponse res = new IfaSendReceiveStatusLookupA003DtoResponse();

        // レスポンスの設定
        for (IfaSendReceiveStatusLookupSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaSendReceiveStatusLookupA003LookupDtoResponse statusLookUp = new IfaSendReceiveStatusLookupA003LookupDtoResponse();
            BeanUtils.copyProperties(selSql002Res, statusLookUp);
         // 理由名称に含まれる文字列を置き換える
            String purposeNameFinal = replacePurposeName(selSql002Res.getPurposeName());
            statusLookUp.setPurposeName(purposeNameFinal);
            statusLookupList.add(statusLookUp);
        }
        res.setSendReceiveStatusLookupList(statusLookupList);
        resList.add(res);

        // 受発信状況リストの総件数が最大取得件数を超過していた場合、件数超過メッセージをセットして終了
        if (selSql002ResList.get(0).getTotalCount() > MAX_COUNT_DISPLAY) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, new String[] {
                    String.valueOf(MAX_COUNT_DISPLAY), String.valueOf(selSql002ResList.get(0).getTotalCount()) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, msg);
        } else {
            // 正常終了
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        }

        return dtoRes;
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * DTOリクエスト：IfaSendReceiveStatusLookupA004aDtoRequest
     * DTOレスポンス：IfaSendReceiveStatusLookupA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004a")
    public DataList<IfaSendReceiveStatusLookupA004aDtoResponse> csvOutputA004a(@RequestBody IfaSendReceiveStatusLookupA004aDtoRequest dtoReq ,
            String frameworkSessionId) throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSendReceiveStatusLookupServiceImpL.csvOutputA004a");
        }

        DataList<IfaSendReceiveStatusLookupA004aDtoResponse> dtoRes = new DataList<IfaSendReceiveStatusLookupA004aDtoResponse>();
        List<IfaSendReceiveStatusLookupA004aDtoResponse> resList = new ArrayList<IfaSendReceiveStatusLookupA004aDtoResponse>();

        // ユーザ共通情報.顧客共通情報.顧客コード
        String customerId = IfaCommonUtil.getCustomerCommon().getCustomerCode();
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        // FCT038.CSVダウンロードMAX件数を取得
        InputFct038Dto fct038InputDto = new InputFct038Dto();
        fct038InputDto.setScreenId(SCREEN_ID);
        fct038InputDto.setUserAuthority(privId);
        OutputFct038Dto fct038OutputDto = fct038.getData(fct038InputDto);
        int maxCountCsv = fct038OutputDto.getCsvDownloadNum();
        // SQL002のリクエスト値を設定
        IfaSendReceiveStatusLookupSql002RequestModel selSql002Req = new IfaSendReceiveStatusLookupSql002RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql002Req);
        selSql002Req.setCustomerId(customerId);
        selSql002Req.setMaxRowNum(maxCountCsv);
        
        // 受発信状況リストを取得する
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selSql002ResList = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();
        selSql002ResList = getIfaSendReceiveStatusLookupList(selSql002Req);

        // 受発信状況リストの件数が0件の場合、0件メッセージをセットして終了
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < MIN_COUNT) {
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_CMN_PARAMETERS_NOT_EXIST,
                    IfaCommonUtil.getMessage(ERRORS_CMN_PARAMETERS_NOT_EXIST, new String[] { SEND_RECEIVE_STATUS }));
            return dtoRes;
        }
        // レスポンスの設定
        Integer totalRow = Integer.valueOf(selSql002ResList.get(0).getTotalCount());
        String msgTotalRow = Integer.toString(totalRow);
       IfaSendReceiveStatusLookupA004aDtoResponse res = new IfaSendReceiveStatusLookupA004aDtoResponse();
       // ファイル名を取得する
       String csvFileName = "受発信状況";
       res.setCsvDownloadFile(csvFileName);
       resList.add(res);
        if (selSql002ResList.get(0).getTotalCount() > maxCountCsv) {
            String msg = IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(maxCountCsv),
                            String.valueOf(selSql002ResList.get(0).getTotalCount()),
                            String.valueOf(maxCountCsv) });
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_CSV_OVER_MAX_ROWNUM,
                    msg);
        } else {
         // 上記以外の場合､対象メッセージを表示｡
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.WARNING, WARNING_DATALIST_CSV_OUTPUT,
                    IfaCommonUtil.getMessage(WARNING_DATALIST_CSV_OUTPUT, new String[] { msgTotalRow }));
        }
        // CSV出力の内容に受発信状況リストをセット
        List<IfaSendReceiveStatusLookupCsvItems> csvItemList = new ArrayList<IfaSendReceiveStatusLookupCsvItems>();
        for (IfaSendReceiveStatusLookupSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaSendReceiveStatusLookupCsvItems csvItem = new IfaSendReceiveStatusLookupCsvItems();
            BeanUtils.copyProperties(selSql002Res, csvItem);
         // 理由名称に含まれる文字列を置き換える
            String purposeNameFinal = replacePurposeName(selSql002Res.getPurposeName());
            csvItem.setPurposeName(purposeNameFinal);
            csvItemList.add(csvItem);
        }
        // ユーザ共通情報.ユーザIDを取得
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        // CSVファイルを作成
        IfaSendReceiveStatusLookupCsvOut csvOut = new IfaSendReceiveStatusLookupCsvOut(complianceService);
        DataList<IfaSendReceiveStatusLookupCsvItems> csvDataList = new DataList<IfaSendReceiveStatusLookupCsvItems>();
        csvDataList.setDataList(csvItemList);
        String csvFileNameTemp = csvOut.doCreateCsvFile(csvDataList, frameworkSessionId, userId, null);
        // ファイル名を決定
        dtoRes.setTitle(csvFileNameTemp + "," + csvFileName);
        return dtoRes;
    }
    /**
     * アクションID：A005
     * アクション名：popup書類請求付加情報詳細
     * DTO リクエスト：IfaSendReceiveStatusLookupA005DtoRequest
     * DTO レスポンス：IfaSendReceiveStatusLookupA005DtoResponse
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSendReceiveStatusLookupA005DtoResponse> selectdocRequestAddInfoA005(
            IfaSendReceiveStatusLookupA005DtoRequest dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSendReceiveStatusLookupServiceImpL.selectdocRequestAddInfoA005");
        }
        DataList<IfaSendReceiveStatusLookupA005DtoResponse> dtoRes = new DataList<IfaSendReceiveStatusLookupA005DtoResponse>();

        List<IfaSendReceiveStatusLookupA005DtoResponse> resList = new ArrayList<IfaSendReceiveStatusLookupA005DtoResponse>();
        // SQL003のリクエスト値を設定
        IfaSendReceiveStatusLookupSql003RequestModel selSql003Req = new IfaSendReceiveStatusLookupSql003RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql003Req);

        // 書類請求追加情報を取得する
        DataList<IfaSendReceiveStatusLookupSql003ResponseModel> selSql003ResList = new DataList<IfaSendReceiveStatusLookupSql003ResponseModel>();
        selSql003ResList = getIfaDocRequestAddInfo(selSql003Req);
        
        // SQL004のリクエスト値を設定
        IfaSendReceiveStatusLookupSql004RequestModel selSql004Req = new IfaSendReceiveStatusLookupSql004RequestModel();
        BeanUtils.copyProperties(dtoReq, selSql004Req);
        
        // 書類不備付加情報リストを取得する
        DataList<IfaSendReceiveStatusLookupSql004ResponseModel> selSql004ResList = new DataList<IfaSendReceiveStatusLookupSql004ResponseModel>();
        selSql004ResList = getDocDefencyInfoList(selSql004Req);
        // 以下はレスポンスの設定
        IfaSendReceiveStatusLookupA005DtoResponse res = new IfaSendReceiveStatusLookupA005DtoResponse();
        
        // 書類請求追加情報
        List<IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse> docRequestAddInfoList = new ArrayList<IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse>();
        // 正常レコードとデータともある場合
        if(!selSql003ResList.getDataList().isEmpty() && selSql003ResList.getDataList().get(0) != null) {
            for ( IfaSendReceiveStatusLookupSql003ResponseModel selSql003Res : selSql003ResList.getDataList()) {
                IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse docRequestAddInfo  = new IfaSendReceiveStatusLookupA005docRequestAddInfoDtoResponse();
                BeanUtils.copyProperties(selSql003Res, docRequestAddInfo);
                docRequestAddInfoList.add(docRequestAddInfo);
            }
        } else if (!selSql003ResList.getDataList().isEmpty() && selSql003ResList.getDataList().get(0) == null){
            // レコードがありますがデータは全てnull場合
            docRequestAddInfoList.add(null);

        } //　上記以外は新規リストで戻る
        
       res.setDocRequestAddInfo(docRequestAddInfoList);
        // 書類不備付加情報リスト
        List<IfaSendReceiveStatusLookupA005docDeficencyDtoResponse> docDeficiencyInfoList = new ArrayList<IfaSendReceiveStatusLookupA005docDeficencyDtoResponse>();
        for ( IfaSendReceiveStatusLookupSql004ResponseModel selSql004Res : selSql004ResList.getDataList()) {
            IfaSendReceiveStatusLookupA005docDeficencyDtoResponse docDeficiencyInfo  = new IfaSendReceiveStatusLookupA005docDeficencyDtoResponse();
            BeanUtils.copyProperties(selSql004Res, docDeficiencyInfo);
            docDeficiencyInfoList.add(docDeficiencyInfo);
        }
        res.setSelectDocDeficiencyInfoList(docDeficiencyInfoList);
        resList.add(res);

        // 正常終了
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");

        return dtoRes;
    }
    /**
     * 書類一覧取得
     *
     * @param selSql001Req SQL001 リクエスト
     * @return IfaSendReceiveStatusLookupSql001ResponseModel 書類一覧
     */
    private DataList<IfaSendReceiveStatusLookupSql001ResponseModel> getBookKindList(
            IfaSendReceiveStatusLookupSql001RequestModel selSql001Req) throws Exception {
        
        DataList<IfaSendReceiveStatusLookupSql001ResponseModel> selSql001ResList = new DataList<IfaSendReceiveStatusLookupSql001ResponseModel>();
     // 書類一覧リストを取得する
        selSql001ResList = dao.selectIfaSendReceiveStatusLookupSql001(selSql001Req); 
        
        return selSql001ResList;
    }
    
    /**
     * 受発信状況一覧取得
     *
     * @param selSql002Req SQL002 リクエスト
     * @return IfaSendReceiveStatusLookupSql002ResponseModel 受発信状況一覧
     */
    private DataList<IfaSendReceiveStatusLookupSql002ResponseModel> getIfaSendReceiveStatusLookupList(
            IfaSendReceiveStatusLookupSql002RequestModel selSql002Req) throws Exception {
        
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selSql002ResList = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();
     // 受発信状況一覧リストを取得する
        selSql002ResList = dao.selectIfaSendReceiveStatusLookupSql002(selSql002Req); 
        
        return selSql002ResList;
    }
    
    /**
     * 書類請求付加情報詳細
     *
     * @param selSql003Req SQL003 リクエスト
     * @return IfaSendReceiveStatusLookupSql003ResponseModel 書類請求付加情報詳細
     */
    private DataList<IfaSendReceiveStatusLookupSql003ResponseModel> getIfaDocRequestAddInfo(
            IfaSendReceiveStatusLookupSql003RequestModel selSql003Req) throws Exception {
        
        DataList<IfaSendReceiveStatusLookupSql003ResponseModel> selSql003ResList = new DataList<IfaSendReceiveStatusLookupSql003ResponseModel>();
     // 書類請求付加情報詳細を取得する
        selSql003ResList = dao.selectIfaSendReceiveStatusLookupSql003(selSql003Req); 
        
        return selSql003ResList;
    }
    
    /**
     * 書類不備付加情報リス
     *
     * @param selSql004Req SQL004 リクエスト
     * @return IfaSendReceiveStatusLookupSql004ResponseModel 書類請求付加情報詳細
     */
    private DataList<IfaSendReceiveStatusLookupSql004ResponseModel> getDocDefencyInfoList(
            IfaSendReceiveStatusLookupSql004RequestModel selSql004Req) throws Exception {
        
        DataList<IfaSendReceiveStatusLookupSql004ResponseModel> selSql004ResList = new DataList<IfaSendReceiveStatusLookupSql004ResponseModel>();
     // 書類請求付加情報詳細を取得する
        selSql004ResList = dao.selectIfaSendReceiveStatusLookupSql004(selSql004Req); 
        
        return selSql004ResList;
    }

    
    /**
     * 理由名称に含まれる文字列を置き換える
     *
     * @param purposeName 理由名称
     * @return 参照というのリンクがなしの理由名称
     */
    private String replacePurposeName(String purposeName) {
        String purposeNameStr = "";
        if (StringUtils.isNotEmpty(purposeName)) {

            String regex = "＜<a href.+?参照</a>＞";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(purposeName);

            purposeNameStr = matcher.replaceAll("");
        }

        return purposeNameStr;
    }
    
}
