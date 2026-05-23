package com.sbisec.helios.gw.suggestionBox.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailA001ResponseDto;

import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;

import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA002aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA002aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA002bApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA007aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailA007bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.util.UriUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 画面ID：SUB00_01-06_3
 * 画面名：要望事項 詳細
 *
 * @author SCSK神木
 2025/06/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00_01-06_3", screenNumber = "")
public class IfaSuggestionBoxPersonalDetailController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(IfaSuggestionBoxPersonalDetailController.class);

    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";

    private static final String CONTENT_TYPE = "application/octet-stream;";


    /** カテゴリID */
    private static final String SUG_BOX_PERSONAL_CAT_ID = "0";

    /** {0}を登録しました。 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_REGISTER = "あなたの要望";

    /** エラーメッセージ：選択されたファイルを取得できませんでした。 */
    private static final String ERROS_CMN_SUGBOX_FILE_NOT_FOUND = "errors.cmn.SugBoxFile.notfound";

    /** エラーメッセージ：{0}が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "あなたの要望の登録";


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxPersonalDetailA001ApiRequest
     * APIレスポンス：IfaSuggestionBoxPersonalDetailA001ApiResponse
     * Dtoリクエスト：共通dto
     * Dtoレスポンス：共通dto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailInitializeA001")
    public String InitializeA001(@RequestBody IfaSuggestionBoxPersonalDetailA001ApiRequest apiReq) throws Exception {

        IfaSuggestionBoxPersonalDetailA001RequestDto appReq = new IfaSuggestionBoxPersonalDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
 
        DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalDetailService",
                "initializeA001", new TypeReference<DataList<IfaSuggestionBoxPersonalDetailA001ResponseDto>>() {
                }, appReq);

        DataList<IfaSuggestionBoxPersonalDetailA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxPersonalDetailA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA007a
     * アクションID：A002a
     * アクション名：ダウンロード
     * APIリクエスト：IfaSuggestionBoxPersonalDetailA002aApiRequest
     * APIレスポンス：IfaSuggestionBoxPersonalDetailA002aApiResponse
     * Dtoリクエスト：共通dto
     * Dtoレスポンス：共通dto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailDownloadA002a")
    public String downloadA002a(@RequestBody IfaSuggestionBoxPersonalDetailA002aApiRequest apiReq) throws Exception {

        IfaSuggestionBoxPersonalDetailA002aApiResponse res = new IfaSuggestionBoxPersonalDetailA002aApiResponse();
 
        // --------------------------------
        // ファイルダウンロード
        // --------------------------------
        // ダウンロード元のファイルディレクトリの取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);
 
        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        String fileName = apiReq.getPdfFileName();
        String filePath = fileDirectory + fileName;
        File file = new File(filePath);
 
        if (!file.exists() || !file.isFile()) {
            String errorMessage = getMessage(ERROS_CMN_SUGBOX_FILE_NOT_FOUND, new String[] {});
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                    ERROS_CMN_SUGBOX_FILE_NOT_FOUND, errorMessage));
        }

        res.setPdfFileName(fileName);

        return jc.toString(IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));

    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailDownloadA002b
     * アクションID：A002b
     * アクション名：ダウンロード
     * APIリクエスト：IfaSuggestionBoxPersonalDetailA002bApiRequest
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailDownloadA002b")
    public void downloadA002b(@RequestBody IfaSuggestionBoxPersonalDetailA002bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        //==============================
        // ファイルダウンロード
        //==============================
        // ダウンロード元のファイルディレクトリの取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        String fileName = new File(apiReq.getPdfFileName()).getName();
        String fmFileName = fileDirectory + fileName;


        try {
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION,
                    HEADER_VALUE_ATTACHMENT + UriUtils.encode(fileName, "UTF-8"));
            
            IOUtils.copy(new FileInputStream(fmFileName), response.getOutputStream());
            
        } catch (FileNotFoundException e) {
            //例外処理
            if (logger.isDebugEnabled()) {
                logger.debug("IfaSuggestionBoxPersonalDetailController,ifaSuggestionBoxPersonalDetailDownloadA002b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA007a
     * アクションID：A007
     * アクション名：登録
     * APIレスポンス：IfaSuggestionBoxPersonalDetailA007aApiResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA007a")
    public String RegisterA007a(@RequestParam("sbpNo") String sbpNo,
            @RequestPart(value = "attachFile1", required = false) MultipartFile file1,
            @RequestPart(value = "attachFile2", required = false) MultipartFile file2,
            @RequestPart(value = "attachFile3", required = false) MultipartFile file3) throws Exception {

        IfaSuggestionBoxPersonalDetailA007aApiResponse res = new IfaSuggestionBoxPersonalDetailA007aApiResponse(); 

        // --------------------------------
        // ファイルアップロード
        // --------------------------------
        // アップロード先のファイルディレクトリの取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);

        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        Path fileDirectoryPath = Paths.get(fileDirectory);

        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        String sbNo = sbpNo;

        try {
            FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbNo, file1) //
                    .ifPresent(p -> res.setRegisterFileName1(p.getFileName().toString()));
            FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbNo, file2) //
                    .ifPresent(p -> res.setRegisterFileName2(p.getFileName().toString()));
            FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbNo, file3) //
                    .ifPresent(p -> res.setRegisterFileName3(p.getFileName().toString()));

            return jc.toString(IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
        } catch (IOException e) {
            logger.info("Exception occured.", e);
            String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {REGISTERED_ERROR});
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED, errorMessage));
        }
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA007b
     * アクションID：A007
     * アクション名：登録
     * APIリクエスト：IfaSuggestionBoxPersonalDetailA007bApiRequest
     * APIレスポンス：IfaSuggestionBoxPersonalDetailA007bApiResponse
     * Dtoリクエスト：IfaSuggestionBoxPersonalDetailA007bDtoRequest
     * Dtoレスポンス：IfaSuggestionBoxPersonalDetailA007bDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailRegisterA007b")
    public String RegisterA007b(@RequestBody IfaSuggestionBoxPersonalDetailA007bApiRequest apiReq) throws Exception {

        IfaSuggestionBoxPersonalDetailA007bApiRequest appReq = new IfaSuggestionBoxPersonalDetailA007bApiRequest();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // --------------------------------
        // ファイル削除
        // --------------------------------
        
        // ファイルディレクトリパスを取得する
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);

        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }
            
        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        
        // テーブルデータ更新
        DataList<String> registerA007bRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalDetailService",
                "registerA007b", new TypeReference<DataList<String>>() {
                }, appReq); 

        // サーバ側処理が正常終了
        if (registerA007bRes.getErrorLevel() == ErrorLevel.SUCCESS.getId()) {
            // 登録済みファイルがnullかemptyの場合はnullとしてリストへセット
            List<String> registeredAttachFileList = Stream.of(apiReq.getRegisteredAttachFile1(),
                    apiReq.getRegisteredAttachFile2(),
                    apiReq.getRegisteredAttachFile3())
                    .map(registeredAttachFile -> (registeredAttachFile == null || registeredAttachFile.isEmpty()) ? null : registeredAttachFile)
                    .collect(Collectors.toList());

            // 登録済添付ファイル削除フラグをリストへセット    
            List<String> registeredAttachFileDeleteFlag = Stream.of(apiReq.getRegisteredAttachFile1DeleteFlag(),
                    apiReq.getRegisteredAttachFile2DeleteFlag(),
                    apiReq.getRegisteredAttachFile3DeleteFlag())
                    .collect(Collectors.toList());

            // 登録ファイルが存在するかつ、登録ファイル削除フラグが'1'の場合、登録ファイルを削除する。
            for (int i = 0; i < 3; i++) {
                if (!StringUtil.isNullOrEmpty(registeredAttachFileList.get(i)) && "1".equals(registeredAttachFileDeleteFlag.get(i))) {
                    FileUploadUtil.deleteFromFileSyst(fileDirectory, registeredAttachFileList.get(i));
                }
            }
        // サーバ側でエラーが発生
        } else {
            logger.error("Exception occured.");

            // 登録済みファイルがnullかemptyの場合はnullとしてリストへセット
            List<String> registerFileNameList = Stream.of(apiReq.getRegisterFileName1(),
                    apiReq.getRegisterFileName2(),
                    apiReq.getRegisterFileName3())
                    .map(registeredAttachFile -> (registeredAttachFile == null || registeredAttachFile.isEmpty()) ? null : registeredAttachFile)
                    .collect(Collectors.toList());

            // 新規で登録済みのファイルが存在する場合は、そのファイルを削除
            for (int i = 0; i < 3; i++) {
                if (!StringUtil.isNullOrEmpty(registerFileNameList.get(i))) {
                    FileUploadUtil.deleteFromFileSyst(fileDirectory, registerFileNameList.get(i));
                }
            }
            String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {REGISTERED_ERROR});
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED, errorMessage));
        }

        String message = IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { MESSAGE_PARAM_REGISTER });
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_INSERT_COMPLETED, message));
    }

}
