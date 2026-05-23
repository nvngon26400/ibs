package com.sbisec.helios.gw.common.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.dto.IfaAddressSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaAddressSearchResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.form.IfaAddressSearchRequestForm;
import com.sbisec.helios.gw.common.form.IfaAddressSearchResponseForm;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB07-01 画面名：住所検索ポップアップ
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB07-01", screenNumber = "")
public class IfaAddressSearchController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/common/ifaAddressSearch
     * アクションID：A001
     * アクション名：住所検索
     * APIリクエスト：IfaAddressSearchRequestForm
     * Apiレスポンス：IfaAddressSearchResponseForm
     * Dtoリクエスト：IfaAddressSearchRequestDto
     * Dtoレスポンス：IfaAddressSearchResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 住所検索の際、例外が発生した場合
     */
    @PostMapping("/common/ifaAddressSearch")
    public String addressSearch(@RequestBody IfaAddressSearchRequestForm apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaAddressSearchRequestDto appReq = new IfaAddressSearchRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaAddressSearchResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaAddressSearchService",
                "addressSearch", new TypeReference<DataList<IfaAddressSearchResponseDto>>() {
                }, appReq);
        DataList<IfaAddressSearchResponseForm> apiRes = new DataList<IfaAddressSearchResponseForm>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
