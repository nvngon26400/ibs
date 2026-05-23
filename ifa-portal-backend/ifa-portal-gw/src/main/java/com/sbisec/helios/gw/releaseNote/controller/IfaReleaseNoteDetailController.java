package com.sbisec.helios.gw.releaseNote.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteDetailA001ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteDetailA001ApiRequest;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteDetailA001ApiResponse;

/**
 * 画面ID：SUB00-07_2
 * 画面名：リリースノート詳細
 * 2025/11/04 新規作成
 *
 * @author 大連 葉
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00-07_2", screenNumber = "")
public class IfaReleaseNoteDetailController extends BaseController{

    JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/releaseNote/ifaReleaseNoteDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaReleaseNoteDetailA001ApiRequest
     * Apiレスポンス：IfaReleaseNoteDetailA001ApiResponse
     * Dtoリクエスト：IfaReleaseNoteDetailA001RequestDto
     * Dtoレスポンス：IfaReleaseNoteDetailA001ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteDetailA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/releaseNote/ifaReleaseNoteDetailInitializeA001")
    public String initializeA001(@RequestBody IfaReleaseNoteDetailA001ApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReleaseNoteDetailA001RequestDto appReq = new IfaReleaseNoteDetailA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteDetailA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteDetailService", "initializeA001",
                new TypeReference<DataList<IfaReleaseNoteDetailA001ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteDetailA001ApiResponse> apiRes = new DataList<IfaReleaseNoteDetailA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
