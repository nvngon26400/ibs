package com.sbisec.helios.gw.suggestionBox.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonDetailA001ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonDetailA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonDetailA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonDetailA002aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonDetailA002aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonDetailA002bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

/**
 * 画面ID：SUB00_02-06_1
 * 画面名：皆様からの要望詳細
 *
 * @author SCSK
 2025/06/19 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "", id = "SUB00_02-06_1", screenNumber = "")
public class IfaSuggestionBoxCommonDetailController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSuggestionBoxCommonDetailController.class);

    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";

    private static final String CONTENT_TYPE = "application/octet-stream;";

    private static final String ERROS_CMN_SUGBOX_FILE_NOT_FOUND = "errors.cmn.SugBoxFile.notfound";

    private static final String SUG_BOX_COMMON_CAT_ID = "1";


    /**
     * アクセス：
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxCommonDetailA001ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonDetailA001ApiResponse
     * Dtoリクエスト：
     * Dtoレスポンス：
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */

    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonDetailInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaSuggestionBoxCommonDetailA001ApiRequest apiReq) throws Exception {

        IfaSuggestionBoxCommonDetailA001RequestDto appReq = new IfaSuggestionBoxCommonDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSuggestionBoxCommonDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonDetailService", "initializeA001",
                new TypeReference<DataList<IfaSuggestionBoxCommonDetailA001ResponseDto>>() {
                }, appReq);

        DataList<IfaSuggestionBoxCommonDetailA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonDetailA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonDetailDownloadA002a
     * アクションID：A002a
     * アクション名：ダウンロード
     * APIリクエスト：IfaSuggestionBoxCommonDetailA002aApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonDetailA002aApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル名
     * @exception Exception システムエラー
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonDetailDownloadA002a")
    public String downloadA002a(
            @RequestBody IfaSuggestionBoxCommonDetailA002aApiRequest apiReq
            ) throws Exception {

        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("ifaSuggestionBoxService",
                "getSugBoxFileDir", new TypeReference<DataList<String>>() {
        }, SUG_BOX_COMMON_CAT_ID);

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

        var apiRes = new IfaSuggestionBoxCommonDetailA002aApiResponse(fileName);

        return jc.toString(
                IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));

    }


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonDetailDownloadA002b
     * アクションID：A002b
     * アクション名：ダウンロード
     * APIリクエスト：IfaSuggestionBoxCommonDetailA002bApiRequest
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル
     * @exception Exception システムエラー
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonDetailDownloadA002b")
    public void downloadA002b(
            @RequestBody IfaSuggestionBoxCommonDetailA002bApiRequest apiReq, HttpServletResponse response
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
                LOGGER.debug("IfaSuggestionBoxCommonDetailController,ifaSuggestionBoxCommonDetailDownloadA002b File does not exist or is not a file: " + apiReq.getPdfFileName());
            }
        }
    }

    @Override
    protected String getFirstViewName() {

        return null;
    }

}
