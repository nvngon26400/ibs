package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA007aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA007bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoUpdateA007bApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 画面ID:SUB0505_01-04_1
 *　画面名：コンプライアンス通信情報更新
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0505_01-04_1")
public class IfaComplianceReportInfoUpdateController extends BaseController {
    
    private static final Logger logger = LoggerFactory.getLogger(IfaComplianceReportInfoUpdateController.class);
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    public static final String ERROR_SIZE= "errors.size";
    
    public static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";
    
    public static final String UPLOAD_ERROR_MESSAGE = "コンプライアンス通信情報の更新";
    
    /**
    * A001 初期化
    *
    * @param apiReq {@code IfaComplianceReportInfoUpdateA001ApiRequest }
    * @return {@code String}
    * @throws Exception 初期化処理で例外が発生した場合
    */
    @PostMapping(value = "/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoUpdateInitializeA001")
    @ResponseBody
    @ResponseJson
    public String initialize(@Validated @RequestBody IfaComplianceReportInfoUpdateA001ApiRequest apiReq)
            throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<getCorComplianceCommunicationObjectModel> dataList = new DataList<getCorComplianceCommunicationObjectModel>();
        DataList<IfaComplianceReportInfoUpdateA001ApiResponse> apiDataList = new DataList<IfaComplianceReportInfoUpdateA001ApiResponse>();
        
        dataList = ApiRequestUtil.invoke("complianceService", "getCorComplianceCommunicationObject",
                new TypeReference<DataList<getCorComplianceCommunicationObjectModel>>() {
                }, apiReq.getCorLecId());
        
        if (dataList.size() == 0) {
            String message = getMessage(ERRORS_CMN_PARAMETERS_NOT_EXIST, new String[] { "コンプライアンス通信情報" });
            apiDataList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CMN_PARAMETERS_NOT_EXIST, message);
            return jc.toString(apiDataList);
        }
        
        // APIレスポンスにデータを設定
        setApiResponseA001(apiDataList, dataList);
        
        return jc.toString(apiDataList);
    }
    
    /**
    * A003 更新確認
    *
    * @param apiReq {@code IfaComplianceReportInfoUpdateA003ApiRequest }
    * @return {@code String}
    * @throws Exception 更新確認処理で例外が発生した場合
    */
    @PostMapping(value = "/companyEmployeeMenu/complianceReport/checkSpecialCharactersA003")
    @ResponseJson
    @ResponseBody
    public String updateConfirm(@RequestBody IfaComplianceReportInfoUpdateA003ApiRequest apiReq) throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaComplianceReportInfoUpdateController.updateConfirm >> {}", hashCode());
        
        // 戻り結果
        DataList<IfaComplianceReportInfoUpdateA003ApiResponse> apiDataList = new DataList<IfaComplianceReportInfoUpdateA003ApiResponse>();
        
        
        // 環境依存文字チェックを行う
        DataList<Object> checkResult = checkSpecialCharacters(apiReq);
        
        if (checkResult != null) {
            // エラーががある場合、

            return jc.toString(checkResult);
        }
        
        // APIレスポンスにデータを設定
        List<IfaComplianceReportInfoUpdateA003ApiResponse> apiList = new ArrayList<IfaComplianceReportInfoUpdateA003ApiResponse>();
        IfaComplianceReportInfoUpdateA003ApiResponse apiRes = new IfaComplianceReportInfoUpdateA003ApiResponse();
        BeanUtils.copyProperties(apiRes, apiReq);
        apiList.add(apiRes);
        
        // エラーがなければ
        apiDataList = IfaCommonUtil.createDataList(apiList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING);
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 戻り結果をJsonで戻す
        return jc.toString(apiDataList);
    }
    
    /**
    * A007a 更新（ファイルの更新）
    *
    * @param file1 {@code MultipartFile }
    * @param file2 {@code MultipartFile }
    * @param file3 {@code MultipartFile }
    * @param contentsFile {@code MultipartFile }
    * @param request {@code HttpServletRequest }
    * @param response {@code HttpServletResponse }
    * @return {@code String}
    * @throws Exception 更新（ファイルの更新）処理で例外が発生した場合
    */
    @PostMapping(value = "/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoUpdateUpdateA007a")
    public String updateA(@RequestPart(value = "file1", required = false) MultipartFile file1,
            @RequestPart(value = "file2", required = false) MultipartFile file2,
            @RequestPart(value = "file3", required = false) MultipartFile file3,
            @RequestPart(value = "contentsFile", required = false) MultipartFile contentsFile) throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportInfoUpdateA007aApiResponse res = new IfaComplianceReportInfoUpdateA007aApiResponse();
        List<IfaComplianceReportInfoUpdateA007aApiResponse> resApi = new ArrayList<IfaComplianceReportInfoUpdateA007aApiResponse>();
        DataList<IfaComplianceReportInfoUpdateA007aApiResponse> apiRes = new DataList<IfaComplianceReportInfoUpdateA007aApiResponse>();
        
        String filename = "";
        String path = "";
        try {
            DataList<GetIdAndDirectoryModel> directory = ApiRequestUtil.invoke("complianceService", "GetIdAndDirectory",
                    new TypeReference<DataList<GetIdAndDirectoryModel>>() {
                    }, "M2", "0");
            
            path = directory.get(0).getFileDir();
            
            List<MultipartFile> list = new ArrayList<>();
            if (file1 != null && !file1.isEmpty()) {
                list.add(file1);
            }
            if (file2 != null && !file2.isEmpty()) {
                list.add(file2);
            }
            if (file3 != null && !file3.isEmpty()) {
                list.add(file3);
            }
            if (contentsFile != null && !contentsFile.isEmpty()) {
                list.add(contentsFile);
            }
            
            for (MultipartFile file : list) {
                filename = file.getOriginalFilename();
                
                if ((filename != null) && (!filename.equals(""))) {
                    // ファイル名に関する処理
                    filename = (new File(filename)).getName();
                    if (filename.contains("\\")) {
                        filename = filename.substring(filename.lastIndexOf('\\') + 1);
                    }
                    // タイムスタンプ
                    LocalDateTime timesTamp = LocalDateTime.now();
                    // ユーザーアカウント
                    UserAccount userAccount = IfaCommonUtil.getUserAccount();
                    // 一時ファイル名※拡張子なし(yyyyMMdd_HHmmssSSS_ユーザーアカウント)
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmssSSS");
                    String tempFileNameExceptSuffix = dtf.format(timesTamp) + "_" + userAccount.getUserId();
                    // ユーザーアップロードファイル名を設定する(ファイル名.拡張子)
                    String userUploadFileName = filename;
                    // 一時ファイル名を取得する※拡張子あり(yyyyMMdd_HHmmssSSS_ユーザーアカウント.拡張子)
                    filename = fileNameConverter(filename, tempFileNameExceptSuffix);
                    // 一時ファイル(URL + yyyyMMdd_HHmmssSSS_ユーザーアカウント.拡張子)
                    File fileOld = new File(path + filename);
                    file.transferTo(fileOld);
                    
                    // リネームフラグ
                    boolean renameFlg = true;
                    // 採番
                    int num = 1;
                    // 拡張子しすう
                    int suffixIndex = userUploadFileName.lastIndexOf(".");
                    // ファイル名(拡張子を含まない)
                    String fileNameExceptSuffix = StringUtil.EMPTY_STRING;
                    // ファイル名(拡張子を含まない)を設定する
                    if (suffixIndex >= 0) {
                        // ファイル名と拡張子ともある場合、(例：「公開資料.xls」⇒「公開資料」)
                        // 拡張子のみの場合、ファイル名が空(例：「.xls」⇒「""」)
                        fileNameExceptSuffix = userUploadFileName.substring(0, suffixIndex);
                    } else {
                        // ファイル名のみの場合、(例：「公開資料」⇒「公開資料」)
                        fileNameExceptSuffix = userUploadFileName;
                    }
                    
                    while (renameFlg) {
                        // 最終ファイル名を作成する(ファイル名_採番)
                        String finalFileNameExceptSuffix = fileNameExceptSuffix + "_" + String.valueOf(num);
                        // 最終ファイル名を取得(ファイル名_採番.拡張子)
                        String finalFileName = fileNameConverter(userUploadFileName, finalFileNameExceptSuffix);
                        // 最終ファイル(URL + ファイル名_採番.拡張子)
                        File fileNew = new File(path + finalFileName);
                        if (moveFile(fileOld, fileNew)) {
                            // リネーム成功の場合、
                            if (file.equals(file1)) {
                                res.setCorFileName1(finalFileName);
                            } else if (file.equals(file2)) {
                                res.setCorFileName2(finalFileName);
                            } else if (file.equals(file3)) {
                                res.setCorFileName3(finalFileName);
                            } else if (file.equals(contentsFile)) {
                                res.setCorContentsFileName(finalFileName);
                            }
                            // リネームフラグ = false
                            renameFlg = false;
                            
                        } else {
                            // リネーム失敗の場合、新しいファィル名を取得して、リネームを再実行
                            num = num + 1;
                        }
                    }
                }
            }
            resApi.add(res);
            apiRes = IfaCommonUtil.createDataList(resApi, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                    StringUtil.EMPTY_STRING);
            
        } catch (Exception e) {
            logger.info("updateA/ FAILED. File [" + filename + "] path: [" + path + "]");
            logger.info("Exception occured.", e);
            apiRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {UPLOAD_ERROR_MESSAGE}));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
    * A007b 更新（ファイルの更新）
    *
    * @param apiReq {@code IfaComplianceReportInfoUpdateA007bApiRequest }
    * @return {@code String}
    * @throws Exception 更新（ファイルの更新）処理で例外が発生した場合
    */
    @PostMapping(value = "/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoUpdateUpdateA007b")
    @ResponseBody
    @ResponseJson
    public String updateB(@Validated @RequestBody IfaComplianceReportInfoUpdateA007bApiRequest apiReq)
            throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        int ret = 0;
        String fileName1 = apiReq.getCorFileName1();
        String fileName2 = apiReq.getCorFileName2();
        String fileName3 = apiReq.getCorFileName3();
        String fileName4 = apiReq.getCorContentsFileName();
        
        if (!ObjectUtils.isEmpty(apiReq.getCorFileName1BeforeChange())) {
            if (!ObjectUtils.isEmpty(apiReq.getCorFileName1())) {
                fileName1 = apiReq.getCorFileName1();
                ret += deleteFromFileSyst(apiReq.getCorFileName1BeforeChange());
            } else if (ObjectUtils.isEmpty(apiReq.getCorFileName1Delete())) {
                fileName1 = "";
                ret += deleteFromFileSyst(apiReq.getCorFileName1BeforeChange());
            } else {
                fileName1 = apiReq.getCorFileName1BeforeChange();
            }
        }
        
        if (!ObjectUtils.isEmpty(apiReq.getCorFileName2BeforeChange())) {
            if (!ObjectUtils.isEmpty(apiReq.getCorFileName2())) {
                fileName2 = apiReq.getCorFileName2();
                ret += deleteFromFileSyst(apiReq.getCorFileName2BeforeChange());
            } else if (ObjectUtils.isEmpty(apiReq.getCorFileName2Delete())) {
                fileName2 = "";
                ret += deleteFromFileSyst(apiReq.getCorFileName2BeforeChange());
            } else {
                fileName2 = apiReq.getCorFileName2BeforeChange();
            }
        }
        
        if (!ObjectUtils.isEmpty(apiReq.getCorFileName3BeforeChange())) {
            if (!ObjectUtils.isEmpty(apiReq.getCorFileName3())) {
                fileName3 = apiReq.getCorFileName3();
                ret += deleteFromFileSyst(apiReq.getCorFileName3BeforeChange());
            } else if (ObjectUtils.isEmpty(apiReq.getCorFileName3Delete())) {
                fileName3 = "";
                ret += deleteFromFileSyst(apiReq.getCorFileName3BeforeChange());
            } else {
                fileName3 = apiReq.getCorFileName3BeforeChange();
            }
        }
        
        if (!ObjectUtils.isEmpty(apiReq.getCorContentsFileNameBeforeChange())) {
            if (!ObjectUtils.isEmpty(apiReq.getCorContentsFileName())) {
                fileName4 = apiReq.getCorContentsFileName();
                ret += deleteFromFileSyst(apiReq.getCorContentsFileNameBeforeChange());
            } else if (ObjectUtils.isEmpty(apiReq.getCorContentsFileNameDelete())) {
                fileName4 = "";
                ret += deleteFromFileSyst(apiReq.getCorContentsFileNameBeforeChange());
            } else {
                fileName4 = apiReq.getCorContentsFileNameBeforeChange();
            }
        }
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String communicationDate = DateUtil.dateFormat(apiReq.getCorCommunicationDate(), DateUtil.YYYYMM,
                DateUtil.SEPARATED_YYYYMM);
        
        // 更新
        ret = ApiRequestUtil.invoke("complianceService", "UpdComplianceContentsFullByLectId",
                new TypeReference<Integer>() {
                }, communicationDate, apiReq.getCorTitle(), apiReq.getCorBrief(), fileName1, fileName2, fileName3,
                apiReq.getCorFileDesc1(), apiReq.getCorFileDesc2(), apiReq.getCorFileDesc3(), apiReq.getCorContents(),
                fileName4, userAccount.getUserId(), apiReq.getCorLecId());
        
        DataList<IfaComplianceReportInfoUpdateA007bApiResponse> apiResList = new DataList<IfaComplianceReportInfoUpdateA007bApiResponse>();
        
        if (ret == 0) {
            String message = getMessage(ERRORS_EXCLUSIVE_COUNTER_ALLMESSAGE, new String[] { "コンプライアンス通信情報" });
            apiResList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_EXCLUSIVE_COUNTER_ALLMESSAGE, message);
        } else {
            String message = getMessage(INFO_UPDATE_COMPLETED, new String[] { "コンプライアンス通信情報" });
            apiResList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.INFO, INFO_UPDATE_COMPLETED,
                    message);
        }
        
        return jc.toString(apiResList);
    }
    
    /**
    * 環境依存文字チェック.
    *
    * @param apiReq {@code IfaComplianceReportInfoUpdateA003ApiRequest }
    * @return {@code String}
    */
    private DataList<Object> checkSpecialCharacters(IfaComplianceReportInfoUpdateA003ApiRequest apiReq) {
        
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // タイトルを取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorTitle(), "タイトル", "");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // 概要を取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorBrief(), "概要", "");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル1のファイル名を取得して環境依存チェックを行う
        errorMessage =  checkFileNameLength(apiReq.getCorFileName1(), "ファイル名1");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERROR_SIZE, errorMessage);
        }
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileName1(), "ファイル1", "ファイル名が");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル1のコメントを取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileDesc1(), "ファイル1", "コメントが");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル2のファイル名がを取得して環境依存チェックを行う
        errorMessage =  checkFileNameLength(apiReq.getCorFileName2(), "ファイル名2");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERROR_SIZE, errorMessage);
        }
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileName2(), "ファイル2", "ファイル名が");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル2のコメントを取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileDesc2(), "ファイル2", "コメントが");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル3のファイル名がを取得して環境依存チェックを行う
        errorMessage =  checkFileNameLength(apiReq.getCorFileName3(), "ファイル名3");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERROR_SIZE, errorMessage);
        }
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileName3(), "ファイル3", "ファイル名が");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // ファイル3のコメントを取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorFileDesc3(), "ファイル3", "コメントが");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // コンテンツファイルのファイル名がを取得して環境依存チェックを行う
        errorMessage =  checkFileNameLength(apiReq.getCorContentsFileName(), "コンテンツファイル名");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERROR_SIZE, errorMessage);
        }
        errorMessage = checkForSpecialCharacters(apiReq.getCorContentsFileName(), "コンテンツファイル", "ファイル名が");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        // コンテンツファイルのコメントを取得して環境依存チェックを行う
        errorMessage = checkForSpecialCharacters(apiReq.getCorContents(), "コンテンツファイル", "コメントが");
        if (!StringUtil.isNullOrEmpty(errorMessage)) {
            return IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_CONTEXTDEPENDENTWORDS, errorMessage);
        }
        
        return null;
    }
    
    /**
    * 環境依存文字チェック.
    *
    * @param columnContent {@code String }
    * @param buriedWords1 {@code String }
    * @param buriedWords2 {@code String }
    * @return {@code String}
    */
    private String checkForSpecialCharacters(String columnContent, String buriedWords1, String buriedWords2) {
        
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        
        if (!StringUtil.isNullOrEmpty(columnContent)) {
            // 画面の項目内容が入力場合、環境依存チェックを行う
            
            // 環境依存文字チェック
            String utf8Str = UnicodeCheckUtil.getErrorUtf8Str(columnContent);
            if (!StringUtil.isNullOrEmpty(utf8Str)) {
                // 環境依存文字がある場合、エラーメッセージを戻す
                errorMessage = getMessage(ERRORS_CONTEXTDEPENDENTWORDS,
                        new String[] { buriedWords1, buriedWords2, utf8Str });
                return errorMessage;
            }
        }
        
        return errorMessage;
    }
    
    private String checkFileNameLength(String fileName, String fileLabel) {
        String errorMessage = StringUtil.EMPTY_STRING;
        if (!ObjectUtils.isEmpty(fileName)) {
            if (fileName.length() > 127 || fileName.length() < 1) {
                errorMessage = getMessage(ERROR_SIZE,
                        new String[] { fileLabel, "1", "127" });
            }
        }
        return errorMessage;
    }
    /**
    * ファイル名変換.
    *
    * @param origFileNameWithSuffix {@code String }
    * @param destFileNameExceptSuffix {@code String }
    * @return {@code String}
    */
    private String fileNameConverter(String origFileNameWithSuffix, String destFileNameExceptSuffix) {
        
        // 変換後のファイル名
        String convertedFileName = StringUtil.EMPTY_STRING;
        
        if (!StringUtil.isNullOrEmpty(origFileNameWithSuffix)) {
            // ファイル名元が空でない場合、ファイル名変換を行う
            
            // 拡張子しすう
            int suffixIndex = origFileNameWithSuffix.lastIndexOf(".");
            if (suffixIndex >= 0) {
                // 拡張子あり場合、変換後のファイル名 = ターゲットファイル名 + ファイル名元の拡張子
                convertedFileName = destFileNameExceptSuffix + origFileNameWithSuffix.substring(suffixIndex);
            } else {
                // 拡張子なし場合、変換後のファイル名 = ターゲットファイル名
                convertedFileName = destFileNameExceptSuffix;
            }
        }
        return convertedFileName;
    }
    
    /**
    * ファイルを移動する.
    *
    * @param srcFile {@code String }
    * @param destFile {@code String }
    * @return {@code String}
    * @throws Exception ファイル操作処理で例外が発生した場合
    */
    private boolean moveFile(File srcFile, File destFile) throws Exception {
        
        try {
            // Linuxシステムでrenameメソッドが正しく動かないバグを修正するために、下記処理を追加する。
            synchronized (this) {
                // ファイルを移動する。
                FileUtils.moveFile(srcFile, destFile);
            }
        } catch (FileExistsException e) {
            return false;
        } catch (Exception e) {
            logger.error("Exception occured.", e.getMessage());
            throw e;
        }
        return true;
    }
    
    /**
    * 元のファイルを削除する
    *
    * @param fileName {@code String }
    * @return {@code Integer}
    * @throws Exception ファイル操作処理で例外が発生した場合
    */
    private int deleteFromFileSyst(String fileName) {
        
        int ret = 0;
        DataList<GetIdAndDirectoryModel> directory;
        try {
            directory = ApiRequestUtil.invoke("complianceService", "GetIdAndDirectory",
                    new TypeReference<DataList<GetIdAndDirectoryModel>>() {
                    }, "M2", "0");
            if (directory.size() >= 1) {
                String filePath = directory.get(0).getFileDir();
                File f = new File(filePath + fileName);
                if (f.exists() && !f.isDirectory()) {
                    if (!f.delete()) {
                        throw new Exception();
                    } else {
                        ret = 1;
                    }
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            logger.error("Could not delete the file " + fileName + "from the server");
            return 0;
        }
        return ret;
    }
    
    /**
    * A001のレスポンスを設定する
    *
    * @param apiDataList {@code DataList<IfaComplianceReportInfoUpdateA001ApiResponse> }
    * @param dataList {@code DataList<getCorComplianceCommunicationObjectModel> }
    */
    void setApiResponseA001(DataList<IfaComplianceReportInfoUpdateA001ApiResponse> apiDataList,
            DataList<getCorComplianceCommunicationObjectModel> dataList) {
        
        IfaComplianceReportInfoUpdateA001ApiResponse apiRes = new IfaComplianceReportInfoUpdateA001ApiResponse();
        
        getCorComplianceCommunicationObjectModel data = dataList.get(0);
        
        apiRes.setCorCommunicationDate(data.getCorCommunicationDate());
        apiRes.setCorTitle(data.getCorTitle());
        apiRes.setCorBrief(data.getCorBrief());
        apiRes.setCorFileName1(data.getCorFileName1());
        apiRes.setCorFileDesc1(data.getCorFileDesc1());
        apiRes.setCorFileName2(data.getCorFileName2());
        apiRes.setCorFileDesc2(data.getCorFileDesc2());
        apiRes.setCorFileName3(data.getCorFileName3());
        apiRes.setCorFileDesc3(data.getCorFileDesc3());
        apiRes.setCorContentsFileName(data.getCorContentsFileName());
        apiRes.setCorContents(data.getCorContents());
        
        List<IfaComplianceReportInfoUpdateA001ApiResponse> apiList = new ArrayList<IfaComplianceReportInfoUpdateA001ApiResponse>();
        apiList.add(apiRes);
        
        apiDataList.setTotalSize(dataList.getTotalSize());
        apiDataList.setMaxRownum(dataList.getMaxRownum());
        apiDataList.setOverMaxRownum(dataList.getTotalSize() > dataList.getMaxRownum());
        apiDataList.setDataList(apiList);
        apiDataList.setMessage(dataList.getMessage());
        apiDataList.setErrorLevel(dataList.getErrorLevel());
        apiDataList.setRequestedTime(dataList.getRequestedTime());
        apiDataList.setTitle(dataList.getTitle());
        apiDataList.setReturnCode(dataList.getReturnCode());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
