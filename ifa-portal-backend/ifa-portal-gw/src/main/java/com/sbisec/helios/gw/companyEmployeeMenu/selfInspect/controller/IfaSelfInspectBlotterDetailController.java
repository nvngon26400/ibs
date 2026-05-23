package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterDetailA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterDetailA001ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細
 *
 * @author SCSK
 2024/06/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0506_01-02", screenNumber = "")
public class IfaSelfInspectBlotterDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterDetailInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSelfInspectBlotterDetailA001ApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterDetailA001ApiResponse
     * Dtoリクエスト：IfaSelfInspectBlotterDetailA001RequestDto
     * Dtoレスポンス：IfaSelfInspectBlotterDetailA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterDetailInitializeA001")
    public String initializeA001(@Validated @RequestBody IfaSelfInspectBlotterDetailA001ApiRequest apiReq)
            throws Exception {
        
        IfaSelfInspectBlotterDetailA001RequestDto appReq = new IfaSelfInspectBlotterDetailA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSelfInspectBlotterDetailA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaSelfInspectBlotterDetailService", "initializeA001",
                new TypeReference<DataList<IfaSelfInspectBlotterDetailA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSelfInspectBlotterDetailA001ApiResponse> apiRes = new DataList<IfaSelfInspectBlotterDetailA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
