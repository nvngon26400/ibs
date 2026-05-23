package com.sbisec.helios.gw.wholePortal.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaInfoDetailA003aResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.wholePortal.form.IfaInfoDetailA001ApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaInfoDetailA001ApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaInfoDetailA003aApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaInfoDetailA003aApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaInfoDetailA003bApiRequest;


/**
 * 画面ID：SUB01-03
 * 画面名：総合ポータル_ホーム
 * 2025/01/17 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN01", id = "SUB01-03", screenNumber = "")
public class IfaInfoDetailController extends BaseController {

    private static final String HEADER_KEY_CONTENT_DISPOSITION = "Content-Disposition";

    private static final String HEADER_VALUE_ATTACHMENT = "attachment; filename=";

    private static final String CONTENT_TYPE = "application/octet-stream;";

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoDetailController.class);

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/wholePortal/ifaInfoDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaInfoDetailA001ApiRequest
     * Apiレスポンス：IfaInfoDetailA001ApiResponse
     * Dtoリクエスト：IfaInfoDetailA001RequestDto
     * Dtoレスポンス：IfaInfoDetailA001ResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    @PostMapping("/wholePortal/ifaInfoDetailInitializeA001")
    public String initializeA001(
            @RequestBody IfaInfoDetailA001ApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaInfoDetailA001RequestDto appReq = new IfaInfoDetailA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        DataList<IfaInfoDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoDetailService",
                "initializeA001",
                new TypeReference<DataList<IfaInfoDetailA001ResponseDto>>() { },
                appReq
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaInfoDetailA001ApiResponse> apiRes = new DataList<IfaInfoDetailA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);

    }


    /**
     * アクセス：/wholePortal/ifaInfoDetailDownloadA003a
     * アクションID：A003a
     * アクション名：ダウンロード
     * APIリクエスト：IfaInfoDetailA003aApiRequest
     * Apiレスポンス：IfaInfoDetailA003aApiResponse
     * Dtoリクエスト：IfaInfoDetailA003aRequestDto
     * Dtoレスポンス：IfaInfoDetailA003aResponseDto
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル名
     * @exception Exception システムエラー
     */
    @PostMapping("/wholePortal/ifaInfoDetailDownloadA003a")
    public String downloadA003a(
            @RequestBody IfaInfoDetailA003aApiRequest apiReq
    ) throws Exception {

        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaInfoDetailA003aRequestDto appReq = new IfaInfoDetailA003aRequestDto();
        BeanUtils.copyProperties(appReq, apiReq);

        // サービスへ処理を移譲
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaInfoDetailA003aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaInfoDetailService",
                "downloadA003a",
                new TypeReference<DataList<IfaInfoDetailA003aResponseDto>>() { },
                appReq,
                fwSessionId
        );

        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaInfoDetailA003aApiResponse> apiRes = new DataList<IfaInfoDetailA003aApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        // 画面にレスポンスを返却
        return jc.toString(apiRes);

    }


    /**
     * アクセス：/wholePortal/ifaInfoDetailDownloadA003b
     * アクションID：A003b
     * アクション名：ダウンロード
     * APIリクエスト：IfaInfoDetailA003bApiRequest
     * Apiレスポンス：IfaInfoDetailA003bApiResponse
     *
     * @param apiReq リクエストパラメータ
     * @return ダウンロードファイル
     * @exception Exception システムエラー
     */
    @PostMapping("/wholePortal/ifaInfoDetailDownloadA003b")
    public void downloadA003b(
            @RequestBody IfaInfoDetailA003bApiRequest apiReq, HttpServletResponse response
    ) throws Exception {

        try(FileInputStream fileInputStream = new FileInputStream(apiReq.getPdfFileName());) {

            // ファイル名を取得
            Path path = Paths.get(apiReq.getPdfFileName());
            String fmFileName = path.getFileName().toString();

            // レスポンスのヘッダーをセット
            response.setContentType(CONTENT_TYPE);
            response.setHeader(
                    HEADER_KEY_CONTENT_DISPOSITION,
                    HEADER_VALUE_ATTACHMENT + UriUtils.encode(fmFileName, "UTF-8")
            );

            // ダウンロードファイルを出力
            IOUtils.copy(fileInputStream, response.getOutputStream());

        } catch (FileNotFoundException e) {

            //例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaInfoDetailController,ifaInfoDetailDownloadA003b,FileNotFoundException}");
            }
            throw e;

        }
    }
    
    @Override
    protected String getFirstViewName() {

        return null;

    }
}
