package com.sbisec.helios.gw.companyEmployeeMenu.suggestionBox.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aRequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalCsvOutputA006aResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalInitializeX001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalInitializeX001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDisplayA002ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDisplayA002ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalCsvOutputA006aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalCsvOutputA006aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalCsvOutputA006bApiRequest;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0511_01-01
 * 画面名：仲介業者からの要望確認
 *
 * @author SCSK神木
 2025/06/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0511_01-01", screenNumber = "")
public class IfaSuggestionBoxPersonalFromBrokerController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /** ダウンロードファイルの接頭語 */
    private static final String SUGGESTION_BROKER_CSV_FILE_NAME = "仲介業者からの要望確認";


    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalInitializeX001
     * アクションID：X001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxPersonalInitializeX001ApiRequest
     * APIレスポンス：IfaSuggestionBoxPersonalInitializeX001ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxPersonalInitializeX001RequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalInitializeX001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalFromBrokerInitializeX001")
    public String initializeX001(@RequestBody IfaSuggestionBoxPersonalInitializeX001ApiRequest apiReq) throws Exception {

    	IfaSuggestionBoxPersonalInitializeX001RequestDto appReq = new IfaSuggestionBoxPersonalInitializeX001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalService",
                "initializeX001", new TypeReference<DataList<IfaSuggestionBoxPersonalInitializeX001ResponseDto>>() {
                }, appReq);

        DataList<IfaSuggestionBoxPersonalInitializeX001ApiResponse> apiRes = new DataList<IfaSuggestionBoxPersonalInitializeX001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaSuggestionBoxPersonaldisplayA002ApiRequest
     * APIレスポンス：IfaSuggestionBoxPersonalDisplayA002ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxPersonalDisplayA002RequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalDisplayA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalFromBrokerDisplayA002")
    public String displayA002(@RequestBody IfaSuggestionBoxPersonalDisplayA002ApiRequest apiReq) throws Exception {

    	IfaSuggestionBoxPersonalDisplayA002RequestDto appReq = new IfaSuggestionBoxPersonalDisplayA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalService",
                "displayA002", new TypeReference<DataList<IfaSuggestionBoxPersonalDisplayA002ResponseDto>>() {
                }, appReq);

        DataList<IfaSuggestionBoxPersonalDisplayA002ApiResponse> apiRes = new DataList<IfaSuggestionBoxPersonalDisplayA002ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalCsvOutputA006a
     * アクションID：A006a
     * アクション名：CSV出力(サーバでファイル生成)
     * APIリクエスト：IfaSuggestionBoxPersonalCsvOutputA006aApiRequest
     * Apiレスポンス：IfaSuggestionBoxPersonalCsvOutputA006aApiResponse
     * Dtoリクエスト：IfaSuggestionBoxPersonalCsvOutputA006aRequestDto
     * Dtoレスポンス：IfaSuggestionBoxPersonalCsvOutputA006aResponseDto
     *
     * @param apiReq リクエストBody
     * @return レスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalCsvOutputA006a")
    public String csvOutputA006a(@RequestBody IfaSuggestionBoxPersonalCsvOutputA006aApiRequest apiReq)throws Exception {

    	IfaSuggestionBoxPersonalCsvOutputA006aRequestDto appReq = new IfaSuggestionBoxPersonalCsvOutputA006aRequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        // フレームワークセッションIDを取得
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);

        DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSuggestionBoxPersonalService",
                "csvOutputA006", new TypeReference<DataList<IfaSuggestionBoxPersonalCsvOutputA006aResponseDto>>() {
                }, appReq, fwSessionId);

        DataList<IfaSuggestionBoxPersonalCsvOutputA006aApiResponse> apiRes = new DataList<IfaSuggestionBoxPersonalCsvOutputA006aApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/suggestionBox/ifaSuggestionBoxPersonalCsvOutputA006b
     * アクションID：A006b
     * アクション名：CSV出力(プラウザでダウンロード)
     * APIリクエスト：IfaSuggestionBoxPersonalCsvOutputA006bApiRequest
     * Apiレスポンス：IfaSuggestionBoxPersonalCsvOutputA006bApiResponse
     *
     * @param apiReq リクエストBody
     * @param response httpレスポンス
     * @exception Exception システムエラー
     */
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalCsvOutputA006b")
    public void csvOutputA006b(@RequestBody IfaSuggestionBoxPersonalCsvOutputA006bApiRequest apiReq, HttpServletResponse response) throws Exception {

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // ifaSuggestionBoxPersonalCsvOutputA006a で作成したファイルをダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(SUGGESTION_BROKER_CSV_FILE_NAME), userAccount, apiReq.getPattern());
        
    }

}
