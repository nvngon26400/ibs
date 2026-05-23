package com.sbisec.helios.gw.common.composite.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.composite.form.IfaBrandPriceInfoA002ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaBrandPriceInfoA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：CC013
 * 画面名：銘柄時価情報（国内株）
 * @author <author-name>
 *
 * 2023/08/24 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CC013", ignoreCheck = true)
public class IfaBrandPriceInfoController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/common/composite/IfaBrandPriceInfo
     * アクションID：A002
     * アクション名：時価更新
     * APIリクエスト：IfaBrandPriceInfoA002ApiRequest
     * Apiレスポンス：IfaBrandPriceInfoA002ApiResponse
     * Dtoリクエスト：IfaBrandPriceInfoA002DtoRequest
     * Dtoレスポンス：IfaBrandPriceInfoA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaBrandPriceInfoUpdateMarketValueA002")
    public String updateMarketValueA002(@RequestBody IfaBrandPriceInfoA002ApiRequest apiReq) throws Exception {
        
        IfaBrandPriceInfoA002DtoRequest appReq = new IfaBrandPriceInfoA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandPriceInfoA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBrandPriceInfoService",
                "updateMarketValueA002", new TypeReference<DataList<IfaBrandPriceInfoA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBrandPriceInfoA002ApiResponse> apiRes = new DataList<IfaBrandPriceInfoA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
