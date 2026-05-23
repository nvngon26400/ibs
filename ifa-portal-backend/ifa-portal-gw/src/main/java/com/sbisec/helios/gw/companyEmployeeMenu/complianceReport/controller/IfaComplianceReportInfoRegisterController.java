package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.controller;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
import com.sbisec.helios.ap.compliance.model.GetSelectCountModel;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.UnicodeCheckUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterA006aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterA006bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportInfoRegisterA006bApiResponse;

/**
 * 画面ID：SUB0505_01-03_1
 * 画面名：コンプライアンス通信情報登録
 *
 * @author SCSK
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0505_01-03_1")
public class IfaComplianceReportInfoRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger logger = LoggerFactory.getLogger(IfaComplianceReportInfoRegisterController.class);
    public static final String ERROR_SIZE= "errors.size";
    
    public static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";
    
    public static final String UPLOAD_ERROR_MESSAGE = "コンプライアンス通信情報の登録";
    
    /**
    * A002 登録確認
    *
    * @param apiReq {@code IfaComplianceReportInfoRegisterA002ApiRequest }
    * @return {@code String}
    * @throws Exception 登録確認処理で例外が発生した場合
    */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterRegistrationConfirmA002")
    @ResponseJson
    @ResponseBody
    public String registrationConfirm(@Validated @RequestBody IfaComplianceReportInfoRegisterA002ApiRequest apiReq)
            throws Exception {
        
        final long start = System.currentTimeMillis();
        logger.debug("IfaComplianceReportInfoRegisterController.registrationConfirm >> {}", hashCode());
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // 戻り結果
        DataList<IfaComplianceReportInfoRegisterA002ApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterA002ApiResponse>();
        
        // エラーメッセージ
        String errorMessage = StringUtil.EMPTY_STRING;
        
        // 環境依存文字チェックを行う
        DataList<Object> checkResult = checkSpecialCharacters(apiReq);
        
        if (checkResult != null) {
            // エラーががある場合、

            return jc.toString(checkResult);
        }
        
        //重複チェックを行う
        DataList<GetSelectCountModel> dataList = new DataList<GetSelectCountModel>();
        String date = apiReq.getCorCommunicationDate().replaceAll("/", "");
        
        //通信年月の確認
        dataList = ApiRequestUtil.invoke("complianceService", "GetSelectCount",
                new TypeReference<DataList<GetSelectCountModel>>() {
                }, date);
        
        //検索結果が０件以外の場合(重複あり)
        if (!dataList.get(0).getCnt().equals("0")) {
            errorMessage = getMessage(ERRORS_TIMESTAMP_DUPLICATE, new String[] {});
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL, ERRORS_TIMESTAMP_DUPLICATE,
                    errorMessage);
            return jc.toString(apiRes);
        }
        
        // エラーがなければ
        apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(),
                StringUtil.EMPTY_STRING);
        
        // 戻り値をJsonのStringに変換
        String resultJson = jc.toString(apiRes);
        
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));
        
        // 戻り結果をJsonで戻す
        return resultJson;
    }
    
    /**
    * A006 登録（ファイルアップロード）
    *
    * @param file1 {@code MultipartFile }
    * @param file2 {@code MultipartFile }
    * @param file3 {@code MultipartFile }
    * @param contentsFile {@code MultipartFile }
    * @return {@code String}
    * @throws Exception 登録（ファイルアップロード）処理で例外が発生した場合
    */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterRegisterA006a")
    public String registerA006a(@RequestPart(value = "file1", required = false) MultipartFile file1,
            @RequestPart(value = "file2", required = false) MultipartFile file2,
            @RequestPart(value = "file3", required = false) MultipartFile file3,
            @RequestPart(value = "contentsFile") MultipartFile contentsFile) throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaComplianceReportInfoRegisterA006aApiResponse res = new IfaComplianceReportInfoRegisterA006aApiResponse();
        List<IfaComplianceReportInfoRegisterA006aApiResponse> resApi = new ArrayList<IfaComplianceReportInfoRegisterA006aApiResponse>();
        DataList<IfaComplianceReportInfoRegisterA006aApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterA006aApiResponse>();
        
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
            
            // ファイルインデックスリスト
            List<Integer> fileIndexList = new ArrayList<Integer>();
            if (file1 != null && !file1.isEmpty()) {
                // ファイル1入力の場合
                fileIndexList.add(1);
            }
            if (file2 != null && !file2.isEmpty()) {
                // ファイル2入力の場合
                fileIndexList.add(2);
            }
            if (file3 != null && !file3.isEmpty()) {
                // ファイル3入力の場合
                fileIndexList.add(3);
            }
            if (contentsFile != null && !contentsFile.isEmpty()) {
                // コンテンツファイル入力の場合
                fileIndexList.add(4);
            }
            
            for (MultipartFile file : list) {
                filename = file.getOriginalFilename();
                
                if ((filename != null) && (!filename.equals(""))) {
                    // ファイル名に関する処理
                    filename = (new File(filename)).getName();
                    //20160118, Windows passes the whole directory structure, in case it exists, remove it from filename
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
                    
                    //item.write(fileOld);
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
            logger.info("registerA006a/ FAILED. File [" + filename + "] path: [" + path + "]");
            logger.info("Exception occured.", e);
            apiRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_PROCESSINGFAILED,
                    IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {UPLOAD_ERROR_MESSAGE}));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
    * A006 登録（DB）
    *
    * @param apiReq {@code IfaComplianceReportInfoRegisterA006bApiRequest }
    * @return {@code String}
    * @throws Exception 登録（DB）処理で例外が発生した場合
    */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportInfoRegisterRegisterA006b")
    @ResponseBody
    @ResponseJson
    public String registerA006b(@Validated @RequestBody IfaComplianceReportInfoRegisterA006bApiRequest apiReq)
            throws Exception {
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        DataList<IfaComplianceReportInfoRegisterA006bApiResponse> apiRes = new DataList<IfaComplianceReportInfoRegisterA006bApiResponse>();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        String communicationDate = DateUtil.dateFormat(apiReq.getCorCommunicationDate(), DateUtil.YYYYMM,
                DateUtil.SEPARATED_YYYYMM);
        //登録    
        int ret = ApiRequestUtil.invoke("complianceService", "InsertIntoCOR_COMPLIANCE_COMMUNICATION",
                new TypeReference<Integer>() {
                }, communicationDate, apiReq.getCorTitle(), apiReq.getCorBrief(), apiReq.getCorFileName1(),
                apiReq.getCorFileName2(), apiReq.getCorFileName3(), apiReq.getCorFileDesc1(), apiReq.getCorFileDesc2(),
                apiReq.getCorFileDesc3(), apiReq.getCorContents(), "0", userAccount.getUserId(),
                userAccount.getUserId(), apiReq.getCorContentsFileName());
        
        // メッセージ
        String message = StringUtil.EMPTY_STRING;
        
        if (ret == 0) {
            apiRes = IfaCommonUtil.createDtaList(new ArrayList<>(), ErrorLevel.SYSTEM_ERROR, "errors.systemError");
        } else {
            message = getMessage(INFO_INSERT_COMPLETED, new String[] { "コンプライアンス通信情報" });
            apiRes = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.INFO, INFO_INSERT_COMPLETED, message);
        }
        
        return jc.toString(apiRes);
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
    * 環境依存文字チェック.
    *
    * @param apiReq {@code InpNewEntComplianceA002ApiRequest }
    * @return {@code String}
    */
    private DataList<Object> checkSpecialCharacters(IfaComplianceReportInfoRegisterA002ApiRequest apiReq) {
        
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
    * @param srcFile {@code File }
    * @param destFile {@code File }
    * @return {@code boolean}
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
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
