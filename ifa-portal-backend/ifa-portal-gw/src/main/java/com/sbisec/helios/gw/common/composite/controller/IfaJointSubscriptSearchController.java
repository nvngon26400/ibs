package com.sbisec.helios.gw.common.composite.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaJointSubscriptSearchA001DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.composite.form.IfaJointSubscriptSearchA001ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaJointSubscriptSearchA001ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
* コントローラー
 * 画面ID：CC020
 * 画面名：共同募集検索条件（一覧画面）
 * 2024/12/12 新規作成
 *
 * @author 大連 王永宝
 */

@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CC020", screenNumber = "", ignoreCheck = true)
public class IfaJointSubscriptSearchController extends BaseController{

    private JsonConverter g_jc = JsonConverter.getInstance();

    /**
     * 
     * アクセス：/common/composite/ifaJointSubscriptSearchInitializeA001
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
    @PostMapping("/common/composite/ifaJointSubscriptSearchInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptSearchA001ApiRequest x_apiReq) throws Exception {

        IfaJointSubscriptSearchA001DtoRequest p_appReq = new IfaJointSubscriptSearchA001DtoRequest();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(p_appReq, x_apiReq);
        DataList<IfaJointSubscriptSearchA001DtoResponse> p_appRes = ApiRequestUtil.invoke(
                "cmpIfaJointSubscriptSearchService", "initializeA001",
                new TypeReference<DataList<IfaJointSubscriptSearchA001DtoResponse>>() {
                }, p_appReq);
        DataList<IfaJointSubscriptSearchA001ApiResponse> p_apiRes = new DataList<IfaJointSubscriptSearchA001ApiResponse>();
        BeanUtils.copyProperties(p_apiRes, p_appRes);

        return g_jc.toString(p_apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

