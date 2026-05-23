package com.sbisec.helios.gw.common.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchRequestDto;
import com.sbisec.helios.ap.common.dto.IfaBrandNameSearchResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.form.IfaBrandNameSearchRequestForm;
import com.sbisec.helios.gw.common.form.IfaBrandNameSearchResponseForm;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB07-03_1 画面名：投信銘柄検索ポップアップ
 *
 * @author xin.huang
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB07-03_1", screenNumber = "")
public class IfaBrandNameSearchController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    /**
     * アクセス：/common/ifaBrandNameSearch
     * アクションID：A002
     * アクション名：投信銘柄検索
     * APIリクエスト：IfaBrandNameSearchRequestForm
     * Apiレスポンス：IfaBrandNameSearchResponseForm
     * Dtoリクエスト：IfaBrandNameSearchRequestDto
     * Dtoレスポンス：IfaBrandNameSearchResponseDto
     *
     * @param apiReq リクエスト
     * @return リスポンス
     * @throws Exception 投信銘柄検索の際、例外が発生した場合
     */
    @PostMapping("/common/ifaBrandNameSearch")
    public String brandNameSearch(@RequestBody IfaBrandNameSearchRequestForm apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaBrandNameSearchRequestDto appReq = new IfaBrandNameSearchRequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaBrandNameSearchResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBrandNameSearchService",
                "brandNameSearch", new TypeReference<DataList<IfaBrandNameSearchResponseDto>>() {
                }, appReq);
        DataList<IfaBrandNameSearchResponseForm> apiRes = new DataList<IfaBrandNameSearchResponseForm>();
        BeanUtils.copyProperties(apiRes, appRes);
        return jc.toString(apiRes);
    }
}
