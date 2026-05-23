package com.sbisec.helios.gw.common.composite.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.composite.form.IfaNoticeInfoA002ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaNoticeInfoA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author <author-name>
 2024/06/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CC016", screenNumber = "", ignoreCheck = true)
public class IfaNoticeInfoController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
	
    /**
     * 
     * アクセス：/common/composite/ifaNoticeInfoDisplayA002
     * アクションID：A002
     * アクション名：注意情報表示
     * APIリクエスト：IfaNoticeInfoDisplayA002ApiRequest
     * Apiレスポンス：IfaNoticeInfoDisplayA002ApiResponse
     * Dtoリクエスト：IfaNoticeInfoDisplayA002RequestDto
     * Dtoレスポンス：IfaNoticeInfoDisplayA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/common/composite/ifaNoticeInfoDisplayA002")
    public String displayA002(@RequestBody IfaNoticeInfoA002ApiRequest apiReq) throws Exception {

        IfaNoticeInfoA002DtoRequest appReq = new IfaNoticeInfoA002DtoRequest();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaNoticeInfoA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaNoticeInfoService", "displayA002",
                new TypeReference<DataList<IfaNoticeInfoA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaNoticeInfoA002ApiResponse> apiRes = new DataList<IfaNoticeInfoA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

