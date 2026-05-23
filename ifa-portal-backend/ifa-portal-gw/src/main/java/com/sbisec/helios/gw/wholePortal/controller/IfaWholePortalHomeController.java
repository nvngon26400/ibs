package com.sbisec.helios.gw.wholePortal.controller;

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
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA011ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA018ResponseDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA021ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA001ApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA001ApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA011ApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA011ApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA018ApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA018ApiResponse;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA021ApiRequest;
import com.sbisec.helios.gw.wholePortal.form.IfaWholePortalHomeA021ApiResponse;


/**
 * 画面ID：SUB01-01
 * 画面名：総合ポータル_ホーム

 * @author 池亀緑
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN01", id = "SUB01-01", screenNumber = "", ignoreCheck = true)
public class IfaWholePortalHomeController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/wholePortal/ifaWholePortalHomeInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：wholePortalA001ApiRequest
     * Apiレスポンス：wholePortalA001ApiResponse
     * Dtoリクエスト：wholePortalA001DtoRequest
     * Dtoレスポンス：wholePortalA001DtoResponse
     */
    @PostMapping("/wholePortal/ifaWholePortalHomeInitializeA001")
    public String initializeA001(@RequestBody IfaWholePortalHomeA001ApiRequest apiReq) throws Exception {

        IfaWholePortalHomeA001RequestDto appReq = new IfaWholePortalHomeA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaWholePortalHomeA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWholePortalHomeService",
                "initializeA001", new TypeReference<DataList<IfaWholePortalHomeA001ResponseDto>>() {
                }, appReq);

        DataList<IfaWholePortalHomeA001ApiResponse> apiRes = new DataList<IfaWholePortalHomeA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/wholePortal/ifaWholePortalHomeBondMaturityRedemptionA011
     * アクションID：A011
     * アクション名：債券満期償還
     * APIリクエスト：IfaWholePortalHomeA011ApiRequest
     * Apiレスポンス：IfaWholePortalHomeA001ApiResponse
     * Dtoリクエスト：IfaWholePortalHomeA011RequestDto
     * Dtoレスポンス：IfaWholePortalHomeA011ResponseDto
     */
    @PostMapping("/wholePortal/ifaWholePortalHomeBondMaturityRedemptionA011")
    public String bondMaturityRedemptionA011(@RequestBody IfaWholePortalHomeA011ApiRequest apiReq) throws Exception {

        IfaWholePortalHomeA011RequestDto appReq = new IfaWholePortalHomeA011RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaWholePortalHomeA011ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWholePortalHomeService",
                "bondMaturityRedemptionA011", new TypeReference<DataList<IfaWholePortalHomeA011ResponseDto>>() {
                }, appReq);

        DataList<IfaWholePortalHomeA011ApiResponse> apiRes = new DataList<IfaWholePortalHomeA011ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }


    /**
     * アクセス：/wholePortal/ifaWholePortalHomeUpdateA018
     * アクションID：A018
     * アクション名：更新
     * APIリクエスト：IfaWholePortalHomeA018ApiRequest
     * Apiレスポンス：IfaWholePortalHomeA018ApiResponse
     * Dtoリクエスト：IfaWholePortalHomeA018RequestDto
     * Dtoレスポンス：IfaWholePortalHomeA018ResponseDto
     */
    @PostMapping("/wholePortal/ifaWholePortalHomeUpdateA018")
    public String updateA018(@RequestBody IfaWholePortalHomeA018ApiRequest apiReq) throws Exception {

        IfaWholePortalHomeA018RequestDto appReq = new IfaWholePortalHomeA018RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaWholePortalHomeA018ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWholePortalHomeService",
                "updateA018", new TypeReference<DataList<IfaWholePortalHomeA018ResponseDto>>() {
                }, appReq);

        DataList<IfaWholePortalHomeA018ApiResponse> apiRes = new DataList<IfaWholePortalHomeA018ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/wholePortal/ifaWholePortalHomeSearchDisplayA021
     * アクションID：A021
     * アクション名：検索表示
     * APIリクエスト：IfaWholePortalHomeA021ApiRequest
     * Apiレスポンス：IfaWholePortalHomeA021ApiResponse
     * Dtoリクエスト：IfaWholePortalHomeA021RequestDto
     * Dtoレスポンス：IfaWholePortalHomeA021ResponseDto
     */
    @PostMapping("/wholePortal/ifaWholePortalHomeSearchDisplayA021")
    public String searchDisplayA021(@RequestBody IfaWholePortalHomeA021ApiRequest apiReq) throws Exception {

        IfaWholePortalHomeA021RequestDto appReq = new IfaWholePortalHomeA021RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaWholePortalHomeA021ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaWholePortalHomeService",
                "searchDisplayA021", new TypeReference<DataList<IfaWholePortalHomeA021ResponseDto>>() {
                }, appReq);

        DataList<IfaWholePortalHomeA021ApiResponse> apiRes = new DataList<IfaWholePortalHomeA021ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }


    @Override
    protected String getFirstViewName() {

        return null;
    }
}
