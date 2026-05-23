package com.sbisec.helios.gw.common.composite.controller;

import java.util.ArrayList;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoResponse;
import com.sbisec.helios.gw.common.composite.form.IfaCommonSearchA001ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaCommonSearchA001ApiResponse;

/**
 * 画面ID：CC005
 * 画面名：検索条件（一覧画面）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CC005", screenNumber = "", ignoreCheck = true)
public class IfaCommonSearchController extends BaseController{

    private JsonConverter jc = JsonConverter.getInstance();
    /**
     * 
     * アクセス：/common/composite/ifaCommonSearchInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCommonSearchA001ApiRequest
     * Apiレスポンス：IfaCommonSearchA001ApiResponse
     * Dtoリクエスト：IfaCommonSearchA001DtoRequest
     * Dtoレスポンス：IfaCommonSearchA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaCommonSearchInitializeA001")
    public String initializeA001(@RequestBody IfaCommonSearchA001ApiRequest apiReq)throws Exception
    {

        IfaCommonSearchA001DtoRequest appReq = new IfaCommonSearchA001DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaCommonSearchA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaCommonSearchService",
                "initializeA001", new TypeReference<DataList<IfaCommonSearchA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaCommonSearchA001ApiResponse> apiRes = new DataList<IfaCommonSearchA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

