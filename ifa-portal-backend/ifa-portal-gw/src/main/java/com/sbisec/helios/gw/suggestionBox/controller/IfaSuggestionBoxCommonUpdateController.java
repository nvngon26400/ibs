package com.sbisec.helios.gw.suggestionBox.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonUpdateA009bRequestDto;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA003aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA003aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA003bApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA009aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonUpdateA009bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB0511_02-03
 * 画面名：皆様からの要望更新
 *
 2025/06/23 新規作成
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0511_02-03", screenNumber = "")
public class IfaSuggestionBoxCommonUpdateController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonUpdateController.class);
    
    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";

    private static final String CONTENT_TYPE = "application/octet-stream;";
    
    private static final String ERROS_CMN_SUGBOX_FILE_NOT_FOUND = "errors.cmn.SugBoxFile.notfound";
    
    private static final String SUG_BOX_COMMON_CAT_ID = "1";

    /** エラーメッセージ：{0}が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "ファイルのアップロード";

    /**
     * アクセス：
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxCommonUpdateA001ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonUpdateA001ApiResponse
     * Dtoリクエスト：
     * Dtoレスポンス：
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonUpdateInitializeA001")
    public String initializeA001(@RequestBody IfaSuggestionBoxCommonUpdateA001ApiRequest apiReq) throws Exception {
        
        IfaSuggestionBoxCommonUpdateA001RequestDto appReq = new IfaSuggestionBoxCommonUpdateA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSuggestionBoxCommonUpdateA001ResponseDto> appRes = ApiRequestUtil.invoke(
        		"cmpIfaSuggestionBoxCommonUpdateService", "initializeA001",
                new TypeReference<DataList<IfaSuggestionBoxCommonUpdateA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSuggestionBoxCommonUpdateA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonUpdateA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
        /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonUpdateDownloadA003a
     * アクションID：A003a
     * アクション名：ダウンロード
     * APIリクエスト：ifaSuggestionBoxCommonUpdateA003aApiRequest
     * Apiレスポンス：ifaSuggestionBoxCommonUpdateA003aApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル名
     * @exception Exception システムエラー
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonUpdateDownloadA003a")
    public String downloadA003a(
            @RequestBody IfaSuggestionBoxCommonUpdateA003aApiRequest apiReq
    ) throws Exception {
    	
        // --------------------------------
        // ディレクトリパス取得
        // --------------------------------
        
        // 共通処理の呼び出し
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_COMMON_CAT_ID);

        // ファイルディレクトリを取得できなかった場合
        if (fileDirectoryDataList.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        // --------------------------------
        // ファイルの存在チェック
        // --------------------------------
        String fileName = apiReq.getAttachFile();

        // ファイルパス
        String filePath = fileDirectory + fileName;

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            String errorMessage = getMessage(ERROS_CMN_SUGBOX_FILE_NOT_FOUND, new String[] {});
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                    ERROS_CMN_SUGBOX_FILE_NOT_FOUND, errorMessage));
        }

        var apiRes = new IfaSuggestionBoxCommonUpdateA003aApiResponse(fileName);

        return jc.toString(
                IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
        
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonUpdateDownloadA003b
     * アクションID：A003b
     * アクション名：ダウンロード
     * APIリクエスト：ifaSuggestionBoxCommonUpdateA003bApiRequest
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル
     * @exception Exception システムエラー
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonUpdateDownloadA003b")
    public void downloadA003b(
            @RequestBody IfaSuggestionBoxCommonUpdateA003bApiRequest apiReq, HttpServletResponse response
    ) throws Exception {
        
        // --------------------------------
        // ディレクトリパス取得
        // --------------------------------
        
        // 共通処理の呼び出し
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_COMMON_CAT_ID);

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        // --------------------------------
        // ファイルダウンロード
        // --------------------------------
        String fileName = new File(apiReq.getPdfFileName()).getName();

        // ファイルパス
        String fmFileName = fileDirectory + fileName;

        try {

            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION,
                    HEADER_VALUE_ATTACHMENT + UriUtils.encode(fileName, "UTF-8"));
            
            IOUtils.copy(new FileInputStream(fmFileName), response.getOutputStream());
            
        } catch (FileNotFoundException e) {
            //例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("ifaSuggestionBoxCommonUpdateController,ifaSuggestionBoxCommonUpdateDownloadA003b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }


   /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonUpdateUpdateA009a
     * アクションID：A009a
     * アクション名：更新
     * APIリクエスト：ifaSuggestionBoxCommonUpdateA009aApiRequest
     * APIレスポンス：ifaSuggestionBoxCommonUpdateA009aApiResponse
     * Dtoリクエスト：共通dto
     * Dtoレスポンス：共通dto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxCommonUpdateUpdateA009a")
    public String updateA009a(@RequestParam("sbcNo") String sbcNo,
    		@RequestPart(value = "attachFile1", required = false) MultipartFile file1,
            @RequestPart(value = "attachFile2", required = false) MultipartFile file2,
            @RequestPart(value = "attachFile3", required = false) MultipartFile file3) throws Exception {

    	IfaSuggestionBoxCommonUpdateA009aApiResponse res = new IfaSuggestionBoxCommonUpdateA009aApiResponse(); 

        // --------------------------------
        // ディレクトリパス取得
        // --------------------------------

        // 共通処理の呼び出し
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_COMMON_CAT_ID);
        
        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        Path fileDirectoryPath = Paths.get(fileDirectory);

        // --------------------------------
        // ファイルアップロード
        // --------------------------------

        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        String userId = IfaCommonUtil.getUserAccount().getUserId();
        
        try {
        	FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file1) //
                    .ifPresent(p -> res.setRegisterFileName1(p.getFileName().toString()));
        	FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file2) //
                    .ifPresent(p -> res.setRegisterFileName2(p.getFileName().toString()));
        	FileUploadUtil.saveAndRenameSuggestion(fileDirectoryPath, userId, sbcNo, file3) //
                    .ifPresent(p -> res.setRegisterFileName3(p.getFileName().toString()));
            
            var resDs = IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(),
                    null);
            return jc.toString(resDs);
        } catch (IOException e) {
            LOGGER.info("Exception occured.", e);
        	String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {REGISTERED_ERROR});
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED, errorMessage));
        }
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonUpdateUpdateA009b
     * アクションID：A009b
     * アクション名：更新
     * APIリクエスト：ifaSuggestionBoxCommonUpdateA009bApiRequest
     * APIレスポンス：ifaSuggestionBoxCommonUpdateA009bApiResponse
     * Dtoリクエスト：ifaSuggestionBoxCommonUpdateA009bDtoRequest
     * Dtoレスポンス：ifaSuggestionBoxCommonUpdateA009bDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxCommonUpdateUpdateA009b")
    public String updateA009b(@RequestBody IfaSuggestionBoxCommonUpdateA009bApiRequest apiReq) throws Exception {

        IfaSuggestionBoxCommonUpdateA009bRequestDto appReq = new IfaSuggestionBoxCommonUpdateA009bRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // --------------------------------
        // ディレクトリパス取得
        // --------------------------------
        
        // 共通処理の呼び出し
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_COMMON_CAT_ID);

        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }
        
        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        // --------------------------------
        // テーブルデータ更新, ファイル削除
        // --------------------------------

        DataList<String> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxCommonUpdateService",
                "updateA009b", new TypeReference<DataList<String>>() {
                }, appReq); 

        // サーバ側処理が正常終了
        if (appRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
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
            // 登録済みファイルの削除（ロールバック処理）
            List<String> registerFileNameList = Stream.of(apiReq.getRegisterFileName1(),
                    apiReq.getRegisterFileName2(),
                    apiReq.getRegisterFileName3())
                    .map(registeredAttachFile -> (registeredAttachFile == null || registeredAttachFile.isEmpty()) ? null : registeredAttachFile)
                    .collect(Collectors.toList());

            for (int i = 0; i < 3; i++) {
                if (!StringUtil.isNullOrEmpty(registerFileNameList.get(i))) {
                    FileUploadUtil.deleteFromFileSyst(fileDirectory, registerFileNameList.get(i));
                }
            }
        }
        
        return jc.toString(appRes);
    }

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
