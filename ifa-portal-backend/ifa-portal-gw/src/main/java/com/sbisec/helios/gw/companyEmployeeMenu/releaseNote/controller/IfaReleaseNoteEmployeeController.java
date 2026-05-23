package com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.controller;

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
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.releaseNote.dto.IfaReleaseNoteEmployeeA002ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteEmployeeA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteEmployeeA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteEmployeeA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.releaseNote.form.IfaReleaseNoteEmployeeA002ApiResponse;

/**
 * 画面ID：SUB0512-01
 * 画面名：リリースノート(社員用)
 * 2025/11/06 新規作成
 *
 * @author 大連 葉
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0512-01", screenNumber = "")
public class IfaReleaseNoteEmployeeController extends BaseController{

    JsonConverter jc = JsonConverter.getInstance();
    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteEmployeeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaReleaseNoteEmployeeA001ApiRequest
     * Apiレスポンス：IfaReleaseNoteEmployeeA001ApiResponse
     * Dtoリクエスト：IfaReleaseNoteEmployeeA001RequestDto
     * Dtoレスポンス：IfaReleaseNoteEmployeeA001ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteEmployeeA001ApiRequest}
     * @return {@code String}
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteEmployeeInitializeA001")
    public String initializeA001(@RequestBody IfaReleaseNoteEmployeeA001ApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReleaseNoteEmployeeA001RequestDto appReq = new IfaReleaseNoteEmployeeA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteEmployeeA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteEmployeeService", "initializeA001",
                new TypeReference<DataList<IfaReleaseNoteEmployeeA001ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteEmployeeA001ApiResponse> apiRes = new DataList<IfaReleaseNoteEmployeeA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/companyEmployeeMenu/releaseNote/ifaReleaseNoteEmployeeSelectDisplayYearA002
     * アクションID：A002
     * アクション名：表示対象年選択
     * APIリクエスト：IfaReleaseNoteEmployeeA002ApiRequest
     * Apiレスポンス：IfaReleaseNoteEmployeeA002ApiResponse
     * Dtoリクエスト：IfaReleaseNoteEmployeeA002RequestDto
     * Dtoレスポンス：IfaReleaseNoteEmployeeA002ResponseDto
     * 
     * @param apiReq {@code IfaReleaseNoteEmployeeA002ApiRequest}
     * @return {@code String}
     * @exception Exception 表示対象年選択処理で例外が発生した場合
     */
    @PostMapping(value = "/companyEmployeeMenu/releaseNote/ifaReleaseNoteEmployeeSelectDisplayYearA002")
    public String selectDisplayYearA002(@RequestBody IfaReleaseNoteEmployeeA002ApiRequest apiReq) throws Exception{
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaReleaseNoteEmployeeA002RequestDto appReq = new IfaReleaseNoteEmployeeA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaReleaseNoteEmployeeA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaReleaseNoteEmployeeService", "selectDisplayYearA002",
                new TypeReference<DataList<IfaReleaseNoteEmployeeA002ResponseDto>>() {
                }, appReq);
        DataList<IfaReleaseNoteEmployeeA002ApiResponse> apiRes = new DataList<IfaReleaseNoteEmployeeA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}
