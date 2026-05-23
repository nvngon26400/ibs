package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.controller;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007aResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteRegisterA007bResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteRegisterA006ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteRegisterA006ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteRegisterA007aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteRegisterA007bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteCheckUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.util.IfaReleaseNoteFileUploadUtil;

/**
 * SUB0512-02 リリースノート登録
 *
 * @author SBI大連 夏
 * @date 2025/10/24
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0512-02", screenNumber = "")
public class IfaReleaseNoteRegisterController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaReleaseNoteRegisterController.class);

    /** アップロード異常エラーメッセージ引数 */
    private static final String FILE_UPLOAD = "ファイルのアップロード";

    /** {0}を登録しました。 */
    private static final String INFO_INSERT_COMPLETED = "info.insertCompleted";

    /** 完了メッセージ引数 */
    private static final String MESSAGE_PARAM_REGISTER = "リリースノート";

    /** エラーメッセージ：{0}が失敗しました。 */
    private static final String ERRORS_PROCESSINGFAILED = "errors.processingFailed";

    /** 画面ID */
    private static final String SCREEN_ID = "SUB0512-02";

    @Autowired
    private IfaReleaseNoteCheckUtil releaseNoteCheckUtil;

    /**
     * 
     * アクセス：/companyEmployeeMenu/ifaReleaseNoteRegisterConfirmA006
     * アクションID：A006
     * アクション名：登録確認
     * Api リクエスト：IfaReleaseNoteRegisterA006ApiRequest
     * Api レスポンス：IfaReleaseNoteRegisterA006ApiResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteRegisterConfirmA006")
    public String checkA006(@RequestBody IfaReleaseNoteRegisterA006ApiRequest apiReq) throws Exception {

        DataList<IfaReleaseNoteRegisterA006ApiResponse> apiRes = new DataList<IfaReleaseNoteRegisterA006ApiResponse>();
        IfaReleaseNoteRegisterA006ApiResponse ifaReleaseNoteRegisterA006ApiResponse =
            new IfaReleaseNoteRegisterA006ApiResponse();

        ifaReleaseNoteRegisterA006ApiResponse
            .setContentList(releaseNoteCheckUtil.checkForSpecialCharacters(SCREEN_ID, apiReq.getContentItemList()));

        apiRes = IfaCommonUtil.createDataList(List.of(ifaReleaseNoteRegisterA006ApiResponse), ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNoteRegisterA007a
     * アクションID：A007a
     * アクション名：登録
     * APIレスポンス：IfaReleaseNoteRegisterA007aApiResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteRegisterA007a")
    public String insertA007a(@RequestPart(value = "detailedFile", required = false) MultipartFile file)
        throws Exception {

        IfaReleaseNoteRegisterA007aApiResponse res = new IfaReleaseNoteRegisterA007aApiResponse();
        // リリースノートNoを取得する。
        DataList<IfaReleaseNoteRegisterA007aResponseDto> releaseNoteNoList =
            ApiRequestUtil.invoke("cmpIfaReleaseNoteRegisterService", "selectA007a",
                new TypeReference<DataList<IfaReleaseNoteRegisterA007aResponseDto>>() {
                });

        String releaseNoteNo = releaseNoteNoList.getDataList().get(0).getReleaseNoteNo();

        // ファイルディレクトリ情報を取得する。
        DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteRegisterService",
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
            res.setReleaseNoteNo(releaseNoteNo);
            DataList<IfaReleaseNoteRegisterA007aApiResponse> resDs =
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
     * アクセス：/companyEmployeeMenu/releaseNoteRegisterA007b
     * アクションID：A007b
     * アクション名：登録
     * Api リクエスト：IfaReleaseNoteRegisterA007bApiRequest
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteRegisterA007b")
    public String insertA007b(@RequestBody IfaReleaseNoteRegisterA007bApiRequest apiReq) throws Exception {

        IfaReleaseNoteRegisterA007bRequestDto appReq = new IfaReleaseNoteRegisterA007bRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // リリースノートNo(添付ファイル)無しの場合、リリースノートNoを取得しリクエストに設定する。
        if (StringUtils.isEmpty(apiReq.getReleaseNoteNo())) {
            DataList<IfaReleaseNoteRegisterA007aResponseDto> releaseNoteNoList =
                ApiRequestUtil.invoke("cmpIfaReleaseNoteRegisterService", "selectA007a",
                    new TypeReference<DataList<IfaReleaseNoteRegisterA007aResponseDto>>() {
                    });

            String releaseNoteNo = releaseNoteNoList.getDataList().get(0).getReleaseNoteNo();
            appReq.setReleaseNoteNo(releaseNoteNo);
        }

        // --------------------------------
        // テーブルデータ登録
        // --------------------------------
        DataList<IfaReleaseNoteRegisterA007bResponseDto> appRes =
            ApiRequestUtil.invoke("cmpIfaReleaseNoteRegisterService", "registerA007b",
                new TypeReference<DataList<IfaReleaseNoteRegisterA007bResponseDto>>() {
                }, appReq);

        // テーブルデータ登録失敗時ファイル削除
        if (appRes.getErrorLevel() == ErrorLevel.FATAL.getId()) {
            // --------------------------------
            // ファイル削除
            // --------------------------------
            // ファイルディレクトリ情報を取得する。
            DataList<String> fileDirectoryDataList = ApiRequestUtil.invoke("cmpIfaReleaseNoteRegisterService",
                "getReleaseNoteFileDir", new TypeReference<DataList<String>>() {
                });

            if (fileDirectoryDataList.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
                return jc.toString(fileDirectoryDataList);
            }

            String fileDirectory = fileDirectoryDataList.getDataList().get(0);

            if (StringUtils.isNotEmpty(apiReq.getDetailedFileName())) {
                IfaReleaseNoteFileUploadUtil.deleteFromFileSyst(fileDirectory, apiReq.getDetailedFileName());
            }

            return jc.toString(IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                ERRORS_PROCESSINGFAILED, appRes.getMessage()));
        }

        String message = IfaCommonUtil.getMessage(INFO_INSERT_COMPLETED, new String[] { MESSAGE_PARAM_REGISTER });
        return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.INFO, INFO_INSERT_COMPLETED, message));
    }

}
