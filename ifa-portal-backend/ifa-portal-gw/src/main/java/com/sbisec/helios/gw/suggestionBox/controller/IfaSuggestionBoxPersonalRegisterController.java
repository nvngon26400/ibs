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

import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalRegisterA006bRequestDto;
import com.sbisec.helios.ap.suggestionBox.dao.model.IfaSuggestionBoxPersonalRegisterSql001ResponseModel;

import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;

import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalRegisterA006aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalRegisterA006bApiRequest;

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
 * 画面ID：SUB00_01-06_2
 * 画面名：要望事項 新規登録
 *
 * @author SCSK神木
 2025/06/29 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00_01-06_2", screenNumber = "")
public class IfaSuggestionBoxPersonalRegisterController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalRegisterController.class);

    /** カテゴリID */
    private static final String SUG_BOX_PERSONAL_CAT_ID = "0";

    /** {0}を登録しました。 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_REGISTER = "あなたの要望";

    /** エラーメッセージ：{0}が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** エラーメッセージ引数 */
    private static final String REGISTERED_ERROR = "あなたの要望の登録";


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalRegisterRegisterA006a
     * アクションID：A006
     * アクション名：登録
     * APIレスポンス：IfaSuggestionBoxPersonalRegisterA006aApiResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalRegisterRegisterA006a")
    public String registerA006a(@RequestPart(value = "attachFile1", required = false) MultipartFile file1,
            @RequestPart(value = "attachFile2", required = false) MultipartFile file2,
            @RequestPart(value = "attachFile3", required = false) MultipartFile file3) throws Exception {

        IfaSuggestionBoxPersonalRegisterA006aApiResponse res = new IfaSuggestionBoxPersonalRegisterA006aApiResponse(); 

        // --------------------------------
        // 要望No取得
        // --------------------------------
        DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> sbpNoDataList = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalRegisterService",
                "selectA006a", new TypeReference<DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel>>() {
        }, new IfaSuggestionBoxPersonalRegisterA006aRequestDto()); 

        String sbpNo = sbpNoDataList.getDataList().get(0).getSbpNo();

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

            res.setSbpNo(sbNo);
            var resDs = IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);

            return jc.toString(resDs);

        } catch (IOException e) {
        	LOGGER.info("Exception occured.", e);
            String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] {REGISTERED_ERROR});
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL, ERRORS_PROCESSINGFAILED, errorMessage));
        }
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalRegisterRegisterA006b
     * アクションID：A006
     * アクション名：登録
     * APIリクエスト：IfaSuggestionBoxPersonalRegisterA006bApiRequest
     * Dtoリクエスト：IfaSuggestionBoxPersonalRegisterA006bDtoRequest
     * Dtoレスポンス：IfaSuggestionBoxPersonalRegisterA006bDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalRegisterRegisterA006b")
    public String registerA006b(@RequestBody IfaSuggestionBoxPersonalRegisterA006bApiRequest apiReq) throws Exception {

    	IfaSuggestionBoxPersonalRegisterA006bRequestDto appReq = new IfaSuggestionBoxPersonalRegisterA006bRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // 要望No(添付ファイル)無しの場合、要望Noを取得しリクエストに設定する。
        if(StringUtil.isNullOrEmpty(apiReq.getSbpNo())) {

            DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel> sbpNoDataList = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalRegisterService",
                    "selectA006a", new TypeReference<DataList<IfaSuggestionBoxPersonalRegisterSql001ResponseModel>>() {
            }, new IfaSuggestionBoxPersonalRegisterA006aRequestDto()); 

            String sbpNo = sbpNoDataList.getDataList().get(0).getSbpNo();
            appReq.setSbpNo(sbpNo);

        }

        // --------------------------------
        // テーブルデータ登録
        // --------------------------------
        DataList<String> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalRegisterService",
                "insertA006b", new TypeReference<DataList<String>>() {
                }, appReq); 

        // テーブルデータ登録失敗時ファイル削除
        if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {

            // --------------------------------
            // ファイル削除
            // --------------------------------
            // アップロード先のファイルディレクトリの取得
            DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                    "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                    }, SUG_BOX_PERSONAL_CAT_ID);

            if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return jc.toString(fileDirectoryDataList);
            }

            String fileDirectory = fileDirectoryDataList.getDataList().get(0);

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
