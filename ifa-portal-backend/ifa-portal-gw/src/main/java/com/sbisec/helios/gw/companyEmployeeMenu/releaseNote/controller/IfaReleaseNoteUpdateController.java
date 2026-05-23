package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
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
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteUpdateA009bResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA004aApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA004aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA004bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA008ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA008ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA009aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteUpdateA009bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteCheckUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteFileUploadUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SUB0512-03 リリースノート更新
 *
 * @author SBI大連 夏
 * @date 2025/10/24
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MIN05", id = "SUB0512-03", screenNumber = "")
public class IfaReleaseNoteUpdateController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteUpdateController.class);

    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";

    private static final String CONTENT_TYPE = "application/octet-stream;";

    private static final String ERRORS_CMN_FILE_NOTFOUND = "errors.cmn.file.notfound";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_UPDATE = "リリースノート";

    /** エラーメッセージ：{0}が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** アップロード異常エラーメッセージ引数 */
    private static final String FILE_UPLOAD = "ファイルのアップロード";

    /** 画面ID */
    private static final String SCREEN_ID = "SUB0512-03";

    @Autowired
    private IfaReleaseNoteCheckUtil releaseNoteCheckUtil;

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * Api リクエスト：IfaReleaseNoteUpdateA001ApiRequest
     * Api レスポンス：IfaReleaseNoteUpdateA001ApiResponse
     * Dto リクエスト：IfaReleaseNoteUpdateA001RequestDto
     * Dto レスポンス：IfaReleaseNoteUpdateA001ResponseDto
     * 
     * @param apiReq
     * @return apiRes
     * @throws Exception
     */
    @PostMapping("/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateInitializeA001")
    public String initializeA001(@RequestBody IfaReleaseNoteUpdateA001ApiRequest apiReq) throws Exception {

        IfaReleaseNoteUpdateA001RequestDto appReq = new IfaReleaseNoteUpdateA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaReleaseNoteUpdateA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService",
            "initializeA001", new TypeReference<DataList<IfaReleaseNoteUpdateA001ResponseDto>>() {
            }, appReq);

        DataList<IfaReleaseNoteUpdateA001ApiResponse> apiRes = new DataList<IfaReleaseNoteUpdateA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);

    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateDownloadA004a
     * アクションID：A004a
     * アクション名：ダウンロード
     * Api リクエスト：IfaReleaseNoteUpdateA004aApiRequest
     * Api レスポンス：IfaReleaseNoteUpdateA004aApiResponse
     * 
     * @param apiReq
     * @return
     * @throws Exception
     */
    @PostMapping("/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateDownloadA004a")
    public String downloadA004a(@RequestBody IfaReleaseNoteUpdateA004aApiRequest apiReq) throws Exception {

        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService", "downloadA004",
            new TypeReference<DataList<String>>() {
            });

        // ファイルディレクトリを取得できなかった場合
        if (fileDirectoryDataList.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        String fileName = apiReq.getDownloadFileName();

        // ファイルパス
        String filePath = fileDirectory + fileName;

        File file = new File(filePath);

        if (!file.exists() || !file.isFile()) {
            String errorMessage = getMessage(ERRORS_CMN_FILE_NOTFOUND, new String[] {});
            return jc
                .toString(IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_FILE_NOTFOUND, errorMessage));
        }

        IfaReleaseNoteUpdateA004aApiResponse apiRes = new IfaReleaseNoteUpdateA004aApiResponse(fileName);

        return jc.toString(
            IfaCommonUtil.createDataList(List.of(apiRes), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null));

    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateDownloadA004b
     * アクションID：A004b
     * アクション名：ダウンロード
     * Api リクエスト：IfaReleaseNoteUpdateA004bApiRequest
     * Api レスポンス：HttpServletResponse
     * 
     * @param apiReq
     * @param response
     * @throws Exception
     */
    @PostMapping("/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateDownloadA004b")
    public void downloadA004b(@RequestBody IfaReleaseNoteUpdateA004bApiRequest apiReq, HttpServletResponse response)
        throws Exception {

        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService", "downloadA004",
            new TypeReference<DataList<String>>() {
            });

        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        String fileName = new File(apiReq.getPdfFileName()).getName();

        // ファイルパス
        String fmFileName = fileDirectory + fileName;

        try {
            response.setContentType(CONTENT_TYPE);
            response.setHeader(HEADER_KEY_CONTENT_DISPOSITION,
                HEADER_VALUE_ATTACHMENT + UriUtils.encode(fileName, "UTF-8"));

            IOUtils.copy(new FileInputStream(fmFileName), response.getOutputStream());

        } catch (FileNotFoundException e) {
            // 例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(
                    "ifaReleaseNoteUpdateController,ifaReleaseNoteUpdateDownloadA004b File does not exist or is not a file: "
                        + apiReq.getPdfFileName());
            }
        }
    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateConfirmA008
     * アクションID：A008
     * アクション名：更新確認
     * Api リクエスト：IfaReleaseNoteUpdateA008ApiRequest
     * Api レスポンス：IfaReleaseNoteUpdateA008ApiResponse
     * 
     * @param apiReq
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateConfirmA008")
    public String checkA008(@RequestBody IfaReleaseNoteUpdateA008ApiRequest apiReq) throws Exception {

        DataList<IfaReleaseNoteUpdateA008ApiResponse> apiRes = new DataList<IfaReleaseNoteUpdateA008ApiResponse>();
        IfaReleaseNoteUpdateA008ApiResponse ifaReleaseNoteUpdateA008ApiResponse =
            new IfaReleaseNoteUpdateA008ApiResponse();

        ifaReleaseNoteUpdateA008ApiResponse
            .setContentList(releaseNoteCheckUtil.checkForSpecialCharacters(SCREEN_ID, apiReq.getContentItemList()));

        apiRes = IfaCommonUtil.createDataList(List.of(ifaReleaseNoteUpdateA008ApiResponse), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateUpdateA009a
     * アクションID：A009a
     * アクション名：更新
     * APIレスポンス：IfaReleaseNoteRegisterA007aApiResponse
     * 
     * @param releaseNoteNo
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateUpdateA009a")
    public String updateA009a(@RequestParam("releaseNoteNo") String releaseNoteNo,
        @RequestPart(value = "detailedFile", required = false) MultipartFile file) throws Exception {

        IfaReleaseNoteUpdateA009aApiResponse res = new IfaReleaseNoteUpdateA009aApiResponse();

        // ファイルディレクトリ情報を取得する。
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService",
            "getReleaseNoteFileDir", new TypeReference<DataList<String>>() {
            });

        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        // 詳細ファイルに入力がある場合、SQL001で取得したリリースノートNoを利用し、「別紙_詳細ファイル名」に記載のルールに従ってそれぞれ以下を行う。
        String fileDirectory = fileDirectoryDataList.getDataList().get(0);
        Path fileDirectoryPath = Paths.get(fileDirectory);

        // 顧客共通情報
        String userId = IfaCommonUtil.getUserAccount().getUserId();

        try {
            IfaReleaseNoteFileUploadUtil.saveAndRenameReleaseNote(fileDirectoryPath, userId, releaseNoteNo, file)
                .ifPresent(p -> res.setDetailedFileName(p.getFileName().toString()));
            DataList<IfaReleaseNoteUpdateA009aApiResponse> resDs =
                IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
            return jc.toString(resDs);
        } catch (Exception e) {
            LOGGER.info("Exception occured.", e);
            String errorMessage = IfaCommonUtil.getMessage(ERRORS_PROCESSINGFAILED, new String[] { FILE_UPLOAD });
            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                ERRORS_PROCESSINGFAILED, errorMessage));
        }
    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateUpdateA009b
     * アクションID：A009b
     * アクション名：更新
     * Api リクエスト：IfaReleaseNoteUpdateA009bApiRequest
     * 
     * @param apiReq
     * @return
     * @throws Exception
     */
    @PostMapping("/companyEmployeeMenu/releaseNote/ifaReleaseNoteUpdateUpdateA009b")
    public String updateA009b(@RequestBody IfaReleaseNoteUpdateA009bApiRequest apiReq) throws Exception {

        IfaReleaseNoteUpdateA009bRequestDto appReq = new IfaReleaseNoteUpdateA009bRequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // ファイルディレクトリ情報を取得する。
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService",
            "getReleaseNoteFileDir", new TypeReference<DataList<String>>() {
            });

        if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(fileDirectoryDataList);
        }

        // 詳細ファイルに入力がある場合、SQL001で取得したリリースノートNoを利用し、「別紙_詳細ファイル名」に記載のルールに従ってそれぞれ以下を行う。
        String fileDirectory = fileDirectoryDataList.getDataList().get(0);

        // --------------------------------
        // テーブルデータ更新, ファイル削除
        // --------------------------------

        DataList<IfaReleaseNoteUpdateA009bResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteUpdateService",
            "updateA009b", new TypeReference<DataList<IfaReleaseNoteUpdateA009bResponseDto>>() {
            }, appReq);

        // サーバ側処理が正常終了
        if (appRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
            // 登録ファイルが存在するかつ、登録ファイル削除フラグが'1'の場合、登録ファイルを削除する。
            if (StringUtils.isNotEmpty(apiReq.getRegisteredDetailedFileName())
                && "1".equals(apiReq.getRegisteredDetailedFileDeleteFlag())) {
                IfaReleaseNoteFileUploadUtil.deleteFromFileSyst(fileDirectory, apiReq.getRegisteredDetailedFileName());
            }
            // サーバ側でエラーが発生
        } else {
            // 登録済みファイルの削除（ロールバック処理）
            if (StringUtils.isNotEmpty(apiReq.getDetailedFileName())) {
                IfaReleaseNoteFileUploadUtil.deleteFromFileSyst(fileDirectory, apiReq.getDetailedFileName());
            }

            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                ERRORS_PROCESSINGFAILED, appRes.getMessage()));
        }

        String message = IfaCommonUtil.getMessage(INFO_UPDATE_COMPLETED, new String[] { MESSAGE_PARAM_UPDATE });
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_UPDATE_COMPLETED, message));
    }

}
