package com.sbisec.helios.gw.infoMenu.complianceReport.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.model.GetComplianceLetterListOfMustReadDates;
import com.sbisec.helios.ap.compliance.model.GetCorComplianceConfirmationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;
import com.sbisec.helios.ap.compliance.model.getCommunicationDateforSelectModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.controller.ComplianceReminderController;
import com.sbisec.helios.gw.common.util.DropDownList;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.ComplianceLetterQueryForm;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA001ApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA001ApiResponse;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA001SlctYmListApiResponse;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA003ApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA003ApiResponse;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA004aApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA004aApiResponse;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA004bApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA005aApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA005aApiResponse;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportA005bApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportX001ApiRequest;
import com.sbisec.helios.gw.infoMenu.complianceReport.form.IfaComplianceReportX001ApiResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB0302-01
 * 画面名：コンプライアンス通信
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN03", id = "SUB0302-01", screenNumber = "")
public class IfaComplianceReportController extends BaseController {
    
    private static final Logger logger = LoggerFactory.getLogger(IfaComplianceReportController.class);
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CONTENT_TYPE = "application/pdf";
    
    @Autowired
    private ComplianceReminderController complianceReminderController;
    
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMM");
    
    /**
     * A001　初期化
     *
     * @param apiReq {@code IfaComplianceReportA001ApiRequest }
     * @return {@code String}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportInitializeA001")
    public String initializeA001(@RequestBody IfaComplianceReportA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        String resultJson = StringUtil.EMPTY_STRING;
        long start = System.currentTimeMillis();
        
        DataList<IfaComplianceReportA001ApiResponse> apiResDataList = new DataList<IfaComplianceReportA001ApiResponse>();
        List<IfaComplianceReportA001ApiResponse> apiResList = new ArrayList<IfaComplianceReportA001ApiResponse>();
        
        //コンプライアンス通信リスト作成
        ComplianceLetterQueryForm complianceLetterQueryForm = getNewComplianceLetterQueryForm();
        
        //初期化LECTURE_IDの設定
        GetComplianceLetterListOfMustReadDates complianceInitial = complianceLetterGetInitialDisplayId();
        String complianceInitialId = complianceInitial.getLecId();
        String complianceInitialData = complianceInitial.getCommunicationDate();
        
        //complianceInitialIdがnull（SQL002結果の件数が＜１)の場合、初期LECTURE_IDにコンプライアンス通信リストの１件目のLECTURE_IDを設定）
        List<DropDownList> slctYmRes = complianceLetterQueryForm.getSlctYMList();
        if (complianceInitialId == null) {
            complianceInitialId = slctYmRes.get(0).getValue();
            complianceInitialData = slctYmRes.get(0).getKey();
        }
        
        //画面のファイルディレクトリを取得する
        DataList<GetIdAndDirectoryModel> directoryList = new DataList<GetIdAndDirectoryModel>();
        directoryList = ApiRequestUtil.invoke("complianceService", "GetIdAndDirectory",
                new TypeReference<DataList<GetIdAndDirectoryModel>>() {
                }, "M2", "0");
        
        //データなしの場合取得結果0件エラーを設定する
        if (directoryList.size() == 0) {
            String msg = getMessage(INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND, new String[] {});
            apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.INFO,
                    INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND, msg);
            resultJson = jc.toString(apiResDataList);
            
            logger.debug("cost -> {}", (System.currentTimeMillis() - start));
            
            return resultJson;
        }
        
        //表示年月を算出する
        String slctYmDate = null;
        for (DropDownList item : slctYmRes) {
            if (item.getValue().equals(complianceInitialId)) {
                slctYmDate = item.getKey();
                break;
            }
        }
        
        //コンプライアンス通信情報を取得する
        DataList<getCorComplianceCommunicationObjectModel> dataList = new DataList<getCorComplianceCommunicationObjectModel>();
        
        //コンプライアンス通信情報を取得
        dataList = ApiRequestUtil.invoke("complianceService", "getCorComplianceCommunicationObject",
                new TypeReference<DataList<getCorComplianceCommunicationObjectModel>>() {
                }, complianceInitialId);
        
        DataList<GetCorComplianceConfirmationObjectModel> corComplianceConfirmationObjectModel = new DataList<GetCorComplianceConfirmationObjectModel>();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //確認状態を取得
        corComplianceConfirmationObjectModel = ApiRequestUtil.invoke("complianceService",
                "GetCorComplianceConfirmationObject",
                new TypeReference<DataList<GetCorComplianceConfirmationObjectModel>>() {
                }, complianceInitialId, userAccount.getUserId(), userAccount.getBrokerCode());
        
        //画面の確認ボタン状態を設定する
        String confirmBtn = getConfirmBtn(corComplianceConfirmationObjectModel);
        
        IfaComplianceReportA001ApiResponse apiRes = new IfaComplianceReportA001ApiResponse();
        
        //レスポンス内容を設定する
        setApiResponseA001(apiRes, directoryList, complianceInitialData, complianceLetterQueryForm, slctYmDate,
                dataList, corComplianceConfirmationObjectModel, confirmBtn);
        apiResList.add(apiRes);
        apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        // 戻り値をJsonのStringに変換
        resultJson = jc.toString(apiResDataList);
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 検索結果をJsonで戻す
        return resultJson;
    }
    
    /**
     * X001　表示年月変更
     *
     * @param apiReq {@code IfaComplianceReportX001ApiRequest }
     * @return {@code String}
     * @throws Exception 表示年月変更処理で例外が発生した場合
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportDisplayYearMonthChangeX001")
    public String displayYearMonthChangeX001(@Validated @RequestBody IfaComplianceReportX001ApiRequest apiReq)
            throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportX001ApiResponse apiRes = new IfaComplianceReportX001ApiResponse();
        
        DataList<getCorComplianceCommunicationObjectModel> dataList = new DataList<getCorComplianceCommunicationObjectModel>();

        //画面のファイルディレクトリを取得する
        DataList<GetIdAndDirectoryModel> directoryList = new DataList<GetIdAndDirectoryModel>();
        directoryList = ApiRequestUtil.invoke("complianceService", "GetIdAndDirectory",
                new TypeReference<DataList<GetIdAndDirectoryModel>>() {
                }, "M2", "0");
        
        //データなしの場合取得結果0件エラーを設定する
        if (directoryList.size() == 0) {
            String msg = getMessage(INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND, new String[] {});
            DataList<IfaComplianceReportX001ApiResponse> apiResDataList = IfaCommonUtil.createDataList(
                new ArrayList<>(),
                ErrorLevel.INFO,
                INFO_ITA_BROKERAUXILIARYBOOKDIRECTORY_NOTFOUND,
                msg
            );
            
            return jc.toString(apiResDataList);
        }
        
        String slctYm = apiReq.getLecId();
        //コンプライアンス通信情報を取得
        dataList = ApiRequestUtil.invoke("complianceService", "getCorComplianceCommunicationObject",
                new TypeReference<DataList<getCorComplianceCommunicationObjectModel>>() {
                }, slctYm);
        
        final long start = System.currentTimeMillis();
        
        DataList<GetCorComplianceConfirmationObjectModel> corComplianceConfirmationObjectModel = new DataList<GetCorComplianceConfirmationObjectModel>();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //確認状態を取得
        corComplianceConfirmationObjectModel = ApiRequestUtil.invoke("complianceService",
                "GetCorComplianceConfirmationObject",
                new TypeReference<DataList<GetCorComplianceConfirmationObjectModel>>() {
                }, slctYm, userAccount.getUserId(), userAccount.getBrokerCode());
        
        //確認ボタン状態を設定する
        String confirmBtn = getConfirmBtn(corComplianceConfirmationObjectModel);
        
        DataList<IfaComplianceReportX001ApiResponse> apiResDataList = new DataList<IfaComplianceReportX001ApiResponse>();
        List<IfaComplianceReportX001ApiResponse> apiResList = new ArrayList<IfaComplianceReportX001ApiResponse>();
        
        //レスポンス内容を設定する
        setApiResponseX001(apiRes, directoryList, dataList, corComplianceConfirmationObjectModel, confirmBtn);
        apiResList.add(apiRes);
        apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        // 戻り値をJsonのStringに変換
        String resultJson = "";
        resultJson = jc.toString(apiResDataList);
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 検索結果をJsonで戻す
        return resultJson;
    }
    
    /**
     * A003 確認ボタン押下
     *
     * @param apiReq {@code IfaComplianceReportA003ApiRequest }
     * @return {@code String}
     * @throws Exception 確認ボタン押下処理で例外が発生した場合
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportConfirmButtonPressA003")
    public String confirmButtonPressA003(@Validated @RequestBody IfaComplianceReportA003ApiRequest apiReq, Model model,
            HttpServletRequest request) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        final long start = System.currentTimeMillis();
        
        IfaComplianceReportA003ApiResponse apiRes = new IfaComplianceReportA003ApiResponse();
        
        String confirmFlag = null;
        if ("0".equals(apiReq.getConfirmFlg())) {
            //Confirm_Flgが0の場合、INSERT
            ApiRequestUtil.invoke("complianceService", "InsertCorComplianceConfirmation", new TypeReference<Integer>() {
            }, apiReq.getLecId(), userAccount.getUserId(), "1", userAccount.getUserId(), userAccount.getUserId());
            confirmFlag = "1";
        } else {
            //Confirm_Flgが0以外の場合、UPDATE
            ApiRequestUtil.invoke("complianceService", "updateCorComplianceConfirmation", new TypeReference<Integer>() {
            }, apiReq.getLecId(), userAccount.getUserId(), "1", userAccount.getUserId());
            confirmFlag = "1";
        }
        
        complianceReminderController.updateNeedForComplianceReminder(model, request);
        
        DataList<IfaComplianceReportA003ApiResponse> apiResDataList = new DataList<IfaComplianceReportA003ApiResponse>();
        List<IfaComplianceReportA003ApiResponse> apiResList = new ArrayList<IfaComplianceReportA003ApiResponse>();
        
        //レスポンス内容を設定する
        apiRes.setConfirmFlg(confirmFlag);
        apiResList.add(apiRes);
        apiResDataList = IfaCommonUtil.createDataList(apiResList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        // 戻り値をJsonのStringに変換
        String resultJson = "";
        resultJson = jc.toString(apiResDataList);
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 検索結果をJsonで戻す
        return resultJson;
    }
    
    /**
     * A004a コンテンツファイル（ファイル存在事前チェック）
     *
     * @param apiReq {@code IfaComplianceReportA004aApiRequest }
     * @return {@code String}
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportContentFileA004a")
    public String contentFileA004a(@Validated @RequestBody IfaComplianceReportA004aApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        String fileName = apiReq.getDirectory() + apiReq.getFilename();
        DataList<IfaComplianceReportA004aApiResponse> apiRes = new DataList<IfaComplianceReportA004aApiResponse>();
        List<IfaComplianceReportA004aApiResponse> resList = new ArrayList<IfaComplianceReportA004aApiResponse>();
        IfaComplianceReportA004aApiResponse res = new IfaComplianceReportA004aApiResponse();
        
        File tmp = new File(fileName);
        // ファイルが見つからなかった場合
        if (!tmp.exists()) {
            logger.error("Requested file [" + fileName + "] not found. "
                    + "End user will not be able to check compliance information.");
            String errorMessage = getMessage(ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS, new String[] {});
            apiRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS,
                    errorMessage);
            return jc.toString(apiRes);
        }
        
        res.setPdfFileName(fileName);
        resList.add(res);
        apiRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return jc.toString(apiRes);
        
    }
    
    /**
     * A004b コンテンツファイル（ダウンロード）
     *
     * @param apiReq {@code IfaComplianceReportA004bApiRequest }
     * @return {@code String}
     * @throws Exception コンテンツファイル処理で例外が発生した場合
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportContentFileA004b")
    public void contentFileA004b(@Validated @RequestBody IfaComplianceReportA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = null;
        
        try {
            Path path = Paths.get(apiReq.getPdfFileName());
            String fmFileName = path.getFileName().toString();
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + UriUtils.encode(fmFileName, "UTF-8"));
            fileInputStream = new FileInputStream(apiReq.getPdfFileName());
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (FileNotFoundException e) {
            //例外処理
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
    
    /**
     * A004a コンテンツファイル（ファイル存在事前チェック）
     *
     * @param apiReq {@code IfaComplianceReportA005aApiRequest }
     * @return {@code String}
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportAttachFileA005a")
    public String attachFileA005a(@Validated @RequestBody IfaComplianceReportA005aApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        String fileName = apiReq.getDirectory() + apiReq.getFilename();
        DataList<IfaComplianceReportA005aApiResponse> apiRes = new DataList<IfaComplianceReportA005aApiResponse>();
        List<IfaComplianceReportA005aApiResponse> resList = new ArrayList<IfaComplianceReportA005aApiResponse>();
        IfaComplianceReportA005aApiResponse res = new IfaComplianceReportA005aApiResponse();
        
        File tmp = new File(fileName);
        // ファイルが見つからなかった場合
        if (!tmp.exists()) {
            logger.error("Requested file [" + fileName + "] not found. "
                    + "End user will not be able to check compliance information.");
            String errorMessage = getMessage(ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS, new String[] {});
            apiRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS,
                    errorMessage);
            return jc.toString(apiRes);
        }
        
        res.setPdfFileName(fileName);
        resList.add(res);
        apiRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return jc.toString(apiRes);
        
    }
    
    
    /**
     * A004b コンテンツファイル（ダウンロード）
     *
     * @param apiReq {@code IfaComplianceReportA004bApiRequest }
     * @return {@code String}
     * @throws Exception コンテンツファイル処理で例外が発生した場合
     */
    @PostMapping(value = "/infoMenu/ComplianceReport/ifaComplianceReportAttachFileA005b")
    public void attachFileA005b(@Validated @RequestBody IfaComplianceReportA005bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = null;
        
        try {
            Path path = Paths.get(apiReq.getPdfFileName());
            String fmFileName = path.getFileName().toString();
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION, HEADER_VALUE_ATTACHMENT + UriUtils.encode(fmFileName, "UTF-8"));
            fileInputStream = new FileInputStream(apiReq.getPdfFileName());
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (FileNotFoundException e) {
            //例外処理
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
    }
    
    private GetComplianceLetterListOfMustReadDates complianceLetterGetInitialDisplayId() throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Find out what items are must-read and unread, the earliest one is the default display
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String reminderComplianceStartYm = ApiRequestUtil.invoke("medSystemVariableService", "getMedSystemVariable",
                new TypeReference<String>() {
                }, "REMINDER_COMPLIANCE_START_YM");
        
        // RT2484 - コンプライアンス通信の未読通知の仕様変更
        if (userAccount.getCreateTime() != null) {
            String userCreateMonth = new SimpleDateFormat("yyyyMM").format(userAccount.getCreateTime());
            if (reminderComplianceStartYm.compareTo(userCreateMonth) < 0) {
                reminderComplianceStartYm = userCreateMonth;
            }
        }
        String currentMonth = new SimpleDateFormat("yyyyMM").format(new Date());
        
        GetComplianceLetterListOfMustReadDates mustReadMonthsdata = new GetComplianceLetterListOfMustReadDates();
        DataList<GetComplianceLetterListOfMustReadDates> mustReadMonthsdataList = ApiRequestUtil.invoke(
                "complianceService", "getComplianceLetterListOfMustReadDates",
                new TypeReference<DataList<GetComplianceLetterListOfMustReadDates>>() {
                }, userAccount.getUserId(), userAccount.getBrokerCode(), reminderComplianceStartYm, currentMonth);
        
        if (mustReadMonthsdataList.size() < 1) {
            mustReadMonthsdata.setLecId(null);
            mustReadMonthsdata.setCommunicationDate(null);
            return mustReadMonthsdata;
        }
        mustReadMonthsdata.setLecId(mustReadMonthsdataList.get(0).getLecId());
        mustReadMonthsdata.setCommunicationDate(mustReadMonthsdataList.get(0).getCommunicationDate());
        return mustReadMonthsdata;
    }
    
    /**
     * 表示年月ドロップダウンリストを取得.
     *
     * @return {@code ComplianceLetterQueryForm}
     * @throws Exception 表示年月ドロップダウンリスト取得処理で例外が発生した場合
     */
    private ComplianceLetterQueryForm getNewComplianceLetterQueryForm() throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        ComplianceLetterQueryForm from = new ComplianceLetterQueryForm();
        DataList<getCommunicationDateforSelectModel> dataList = new DataList<getCommunicationDateforSelectModel>();
        
        LocalDate today = LocalDate.now();
        String toDate = dtf.format(today);
        String fromDate = dtf.format(today.minus(5, ChronoUnit.YEARS));
        
        dataList = ApiRequestUtil.invoke("complianceService", "getCommunicationDateforSelect",
                new TypeReference<DataList<getCommunicationDateforSelectModel>>() {
                }, fromDate, toDate);
        
        List<DropDownList> slctYmList = new ArrayList<DropDownList>();
        for (int i = 0; i < dataList.size(); i++) {
            DropDownList list = new DropDownList();
            String communicationDate = dataList.get(i).getCorCommunicationDate();
            list.setKey(communicationDate);
            String lecId = dataList.get(i).getCorLecId();
            list.setValue(lecId);
            slctYmList.add(i, list);
        }
        
        from.setSlctYMList(slctYmList);
        return from;
    }
    
    /**
    * レスポンス内容を設定する。
    *
    * @param directoryList {@code DataList<GetIdAndDirectoryModel> }
    * @param complianceInitialData {@code String }
    * @param complianceLetterQueryForm {@code ComplianceLetterQueryForm }
    * @param slctYm {@code String }
    * @param dataList {@code DataList<getCorComplianceCommunicationObjectModel> }
    * @param objectModel {@code DataList<GetCorComplianceConfirmationObjectModel> }
    * @param confirmBtn {@code String }
    * @return  {@code void }
    */
    private void setApiResponseA001(IfaComplianceReportA001ApiResponse apiRes,
            DataList<GetIdAndDirectoryModel> directoryList, String complianceInitialData,
            ComplianceLetterQueryForm complianceLetterQueryForm, String slctYm,
            DataList<getCorComplianceCommunicationObjectModel> dataList,
            DataList<GetCorComplianceConfirmationObjectModel> objectModel, String confirmBtn) {
        
        List<IfaComplianceReportA001SlctYmListApiResponse> slctYmList = new ArrayList<IfaComplianceReportA001SlctYmListApiResponse>();
        
        //レスポンスをセットする
        apiRes.setFileDir(directoryList.get(0).getFileDir());
        apiRes.setCorCommunicationDate(complianceInitialData);
        apiRes.setSlctYm(slctYm);
        apiRes.setCorTitle(dataList.get(0).getCorTitle());
        apiRes.setCorBrief(dataList.get(0).getCorBrief());
        apiRes.setCorContentsFileName(dataList.get(0).getCorContentsFileName());
        apiRes.setCorFileName1(dataList.get(0).getCorFileName1());
        apiRes.setCorFileName2(dataList.get(0).getCorFileName2());
        apiRes.setCorFileName3(dataList.get(0).getCorFileName3());
        apiRes.setCorFileDesc1(dataList.get(0).getCorFileDesc1());
        apiRes.setCorFileDesc2(dataList.get(0).getCorFileDesc2());
        apiRes.setCorFileDesc3(dataList.get(0).getCorFileDesc3());
        apiRes.setCorContents(dataList.get(0).getCorContents());
        apiRes.setConfirmFlg(objectModel.get(0).getCorConfirmationFlg());
        apiRes.setCorBrowseFlagInd(objectModel.get(0).getCorBrowseFlagInd());
        apiRes.setCorBrowseFlagBul(objectModel.get(0).getCorBrowseFlagBul());
        apiRes.setConfirmBtn(confirmBtn);
        
        for (DropDownList slct : complianceLetterQueryForm.getSlctYMList()) {
            IfaComplianceReportA001SlctYmListApiResponse slctYmRes = new IfaComplianceReportA001SlctYmListApiResponse();
            slctYmRes.setCommunicationDate(slct.getKey());
            slctYmRes.setLecId(slct.getValue());
            slctYmList.add(slctYmRes);
        }
        apiRes.setSlctYmList(slctYmList);
    }
    
    /**
    * レスポンス内容を設定する。
    *
    * @param apiRes {@code IfaComplianceReportX001ApiResponse }
    * @param dataList {@code DataList<getCorComplianceCommunicationObjectModel> }
    * @param corComplianceConfirmationObjectModel {@code DataList<GetCorComplianceConfirmationObjectModel> }
    * @param confirmBtn {@code String }
    * @return {@code void }
    */
    private void setApiResponseX001(IfaComplianceReportX001ApiResponse apiRes,
            DataList<GetIdAndDirectoryModel> directoryList,
            DataList<getCorComplianceCommunicationObjectModel> dataList,
            DataList<GetCorComplianceConfirmationObjectModel> corComplianceConfirmationObjectModel, String confirmBtn) {
        
        //レスポンスをセットする
        apiRes.setFileDir(directoryList.get(0).getFileDir());
        apiRes.setCorTitle(dataList.get(0).getCorTitle());
        apiRes.setCorBrief(dataList.get(0).getCorBrief());
        apiRes.setCorContentsFileName(dataList.get(0).getCorContentsFileName());
        apiRes.setCorFileName1(dataList.get(0).getCorFileName1());
        apiRes.setCorFileName2(dataList.get(0).getCorFileName2());
        apiRes.setCorFileName3(dataList.get(0).getCorFileName3());
        apiRes.setCorFileDesc1(dataList.get(0).getCorFileDesc1());
        apiRes.setCorFileDesc2(dataList.get(0).getCorFileDesc2());
        apiRes.setCorFileDesc3(dataList.get(0).getCorFileDesc3());
        apiRes.setCorContents(dataList.get(0).getCorContents());
        apiRes.setConfirmFlg(corComplianceConfirmationObjectModel.get(0).getCorConfirmationFlg());
        apiRes.setCorBrowseFlagInd(corComplianceConfirmationObjectModel.get(0).getCorBrowseFlagInd());
        apiRes.setCorBrowseFlagBul(corComplianceConfirmationObjectModel.get(0).getCorBrowseFlagBul());
        apiRes.setConfirmBtn(confirmBtn);
    }
    
    /**
    * 確認ボタン状態を算出する。
    *
    * @param corComplianceConfirmationObjectModel {@code DataList<GetCorComplianceConfirmationObjectModel> }
    * @return {@code String }
    */
    String getConfirmBtn(DataList<GetCorComplianceConfirmationObjectModel> corComplianceConfirmationObjectModel) {
        
        String corConfirmationFlg = null;
        GetCorComplianceConfirmationObjectModel getCorComplianceConfirmationObjectModel = corComplianceConfirmationObjectModel.get(0);
        //コンプライアンス通信確認.確認フラグ=1の場合
        if (StringUtils.equals(getCorComplianceConfirmationObjectModel.getCorConfirmationFlg(),"1")) {
            corConfirmationFlg = "1";
            //コンプライアンス通信確認.確認フラグ=1以外の場合
        } else if (!StringUtils.equals(getCorComplianceConfirmationObjectModel.getCorConfirmationFlg(), "1")) {
            corConfirmationFlg = "0";
        }
        
        return corConfirmationFlg;
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
