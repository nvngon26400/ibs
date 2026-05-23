package com.sbisec.helios.gw.suggestionBox.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA002aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA002aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA002bApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDetailFromBrokerA006ApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB0511_01-02
 * 画面名：仲介業者からの要望詳細
 *
 * @author SCSK山岸
 2025/07/25 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0511_01-02", screenNumber = "")
public class IfaSuggestionBoxPersonalDetailFromBrokerController extends BaseController {

    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 選択されたファイルを取得できませんでした。 */
    private static final String ERROS_CMN_SUGBOX_FILE_NOT_FOUND = "errors.cmn.SugBoxFile.notfound";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** カテゴリID */
    private static final String SUG_BOX_PERSONAL_CAT_ID = "0";

    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";
    
    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";
    
    private static final String CONTENT_TYPE = "application/octet-stream;";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxPersonalDetailFromBrokerController.class);

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001ApiRequest
     * Apiレスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest
     * Dtoレスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerInitializeA001")
    @ResponseJson
    @ResponseBody
    public String initializeA001(@RequestBody IfaSuggestionBoxPersonalDetailFromBrokerA001ApiRequest apiReq) throws Exception {

        IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest appReq = new IfaSuggestionBoxPersonalDetailFromBrokerA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxPersonalDetailFromBrokerService", "initializeA001",
                new TypeReference<DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001DtoResponse>>() {
                }, appReq);

        DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxPersonalDetailFromBrokerA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerDownloadA002a
     * アクションID：A002
     * アクション名：ダウンロード
     * APIリクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA002aApiRequest
     * Apiレスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA002aApiResponse
     * Dtoリクエスト：
     *
     * @param apiReq リクエスト
     * @return JSON文字列
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerDownloadA002a")
    public String downloadA002a(@RequestBody IfaSuggestionBoxPersonalDetailFromBrokerA002aApiRequest apiReq) throws Exception {

        // ファイルディレクトリを取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);

        // ファイルディレクトリを取得できなかった場合
        if (fileDirectoryDataList.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        String fileName = apiReq.getAttachFile();

        // ファイルパス
        String filePath = fileDirectory + fileName;

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            String errorMessage = getMessage(ERROS_CMN_SUGBOX_FILE_NOT_FOUND, new String[] {});
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL,
                    ERROS_CMN_SUGBOX_FILE_NOT_FOUND, errorMessage));
        }

        var apiRes = new IfaSuggestionBoxPersonalDetailFromBrokerA002aApiResponse(fileName);

        return jc.toString(
                IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerDownloadA002b
     * アクションID：A002
     * アクション名：ダウンロード
     * APIリクエスト：ifaSuggestionBoxPersonalDetailFromBrokerA002bApiRequest
     *
     * @param apiReq リクエスト
     * @param response HTTPレスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerDownloadA002b")
    public void downloadA002b(@RequestBody IfaSuggestionBoxPersonalDetailFromBrokerA002bApiRequest apiReq, HttpServletResponse response)
            throws Exception {

        //==============================
        // ファイルダウンロード
        //==============================

        // ファイルディレクトリを取得
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
                }, SUG_BOX_PERSONAL_CAT_ID);

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        try {
            String fileName = new File(apiReq.getPdfFileName()).getName();

            // ファイルパス
            String fmFileName = fileDirectory + fileName;

            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION,
                    HEADER_VALUE_ATTACHMENT + UriUtils.encode(fileName, "UTF-8"));
            
            IOUtils.copy(new FileInputStream(fmFileName), response.getOutputStream());
            
        } catch (FileNotFoundException e) {
            //例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("IfaSuggestionBoxPersonalDetailFromBrokerController,ifaSuggestionBoxPersonalDetailFromBrokerDownloadA002b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerRegisterA006
     * アクションID：A006
     * アクション名：登録
     * APIリクエスト：IfaSuggestionBoxPersonalDetailFromBrokerA006ApiRequest
     * Apiレスポンス：IfaSuggestionBoxPersonalDetailFromBrokerA006ApiResponse
     * Dtoリクエスト：
     *
     * @param apiReq リクエスト
     * @return JSON文字列
     * @exception exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDetailFromBrokerRegisterA006")
    public String registerA006(@RequestBody IfaSuggestionBoxPersonalDetailFromBrokerA006ApiRequest apiReq) throws Exception {

        IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest appReq = new IfaSuggestionBoxPersonalDetailFromBrokerA006DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<String> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalDetailFromBrokerService",
                "registerA006", new TypeReference<DataList<String>>() {
                }, appReq);

        return jc.toString(appRes);
    }
}
