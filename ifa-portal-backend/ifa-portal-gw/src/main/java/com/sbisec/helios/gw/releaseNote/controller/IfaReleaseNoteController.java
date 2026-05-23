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
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA001ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA002ResponseDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003RequestDto;
import com.sbisec.helios.ap.releaseNote.dto.IfaReleaseNoteA003ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA001ApiRequest;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA001ApiResponse;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA002ApiRequest;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA002ApiResponse;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA003ApiRequest;
import com.sbisec.helios.gw.releaseNote.form.IfaReleaseNoteA003ApiResponse;

/**
 * 画面ID：SUB00-07_1
 * 画面名：リリースノート
 * 2025/10/27 新規作成
 *
 * @author 大連 葉
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00-07_1", screenNumber = "")
public class IfaReleaseNoteController extends BaseController{

    JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/releaseNote/ifaReleaseNoteInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaReleaseNoteA001ApiRequest
     * Apiレスポンス：IfaReleaseNoteA001ApiResponse
     * Dtoリクエスト：IfaReleaseNoteA001RequestDto
     * Dtoレスポンス：IfaReleaseNoteA001ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/releaseNote/ifaReleaseNoteInitializeA001")
    public String initializeA001(@RequestBody IfaReleaseNoteA001ApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReleaseNoteA001RequestDto appReq = new IfaReleaseNoteA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteService", "initializeA001",
                new TypeReference<DataList<IfaReleaseNoteA001ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteA001ApiResponse> apiRes = new DataList<IfaReleaseNoteA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/releaseNote/ifaReleaseNoteSelectDisplayYearA002
     * アクションID：A002
     * アクション名：表示対象年選択
     * APIリクエスト：IfaReleaseNoteA002ApiRequest
     * Apiレスポンス：IfaReleaseNoteA002ApiResponse
     * Dtoリクエスト：IfaReleaseNoteA002RequestDto
     * Dtoレスポンス：IfaReleaseNoteA002ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteA002ApiRequest}
     * @return {@code String}
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    @PostMapping(value = "/releaseNote/ifaReleaseNoteSelectDisplayYearA002")
    public String selectDisplayYearA002(@RequestBody IfaReleaseNoteA002ApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReleaseNoteA002RequestDto appReq = new IfaReleaseNoteA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteService", "selectDisplayYearA002",
                new TypeReference<DataList<IfaReleaseNoteA002ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteA002ApiResponse> apiRes = new DataList<IfaReleaseNoteA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/releaseNote/ifaReleaseNoteUpdateNextDispFlgA003
     * アクションID：A003
     * アクション名：次回表示フラグ更新
     * APIリクエスト：IfaReleaseNoteA003ApiRequest
     * Apiレスポンス：IfaReleaseNoteA003ApiResponse
     * Dtoリクエスト：IfaReleaseNoteA003RequestDto
     * Dtoレスポンス：IfaReleaseNoteA003ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteA003ApiRequest}
     * @return {@code String}
     * @exception Exception 次回表示フラグ更新処理で例外が発生した場合
     */
    @PostMapping(value = "/releaseNote/ifaReleaseNoteUpdateNextDispFlgA003")
    public String updateNextDispFlgA003(@RequestBody IfaReleaseNoteA003ApiRequest apiReq) throws Exception{
        IfaReleaseNoteA003RequestDto appReq = new IfaReleaseNoteA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteService",
                "updateNextDispFlgA003", new TypeReference<DataList<IfaReleaseNoteA003ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteA003ApiResponse> apiRes = new DataList<IfaReleaseNoteA003ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
