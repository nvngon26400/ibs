package com.sbisec.helios.gw.suggestionBox.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalInitializeX001ResponseDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002RequestDto;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxPersonalDisplayA002ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalInitializeX001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalInitializeX001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDisplayA002ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxPersonalDisplayA002ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB00_01-06_1
 * 画面名：あなたの要望
 *
 * @author SCSK神木
 2025/06/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00_01-06_1", screenNumber = "")
public class IfaSuggestionBoxPersonalController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

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
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalInitializeX001")
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
    @PostMapping(value = "/suggestionBox/ifaSuggestionBoxPersonalDisplayA002")
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

}
