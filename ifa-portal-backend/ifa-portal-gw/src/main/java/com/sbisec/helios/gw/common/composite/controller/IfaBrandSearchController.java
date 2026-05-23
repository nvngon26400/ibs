package com.sbisec.helios.gw.common.composite.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA001DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA003DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA003DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA006DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA006DtoResponse;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA001ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA001ApiResponse;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA002ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA002ApiResponse;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA003ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA003ApiResponse;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA006ApiRequest;
import com.sbisec.helios.gw.common.composite.form.IfaBrandSearchA006ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：CC014
 * 画面名：画面共通部品_銘柄検索
 * @author <author-name>
 *
 * 2023/08/21 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "CC014", ignoreCheck = true)
public class IfaBrandSearchController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/common/composite/IfaBrandSearchInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaBrandSearchA001ApiRequest
     * Apiレスポンス：IfaBrandSearchA001ApiResponse
     * Dtoリクエスト：IfaBrandSearchA001DtoRequest
     * Dtoレスポンス：IfaBrandSearchA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaBrandSearchInitializeA001")
    public String initializeA001(@RequestBody IfaBrandSearchA001ApiRequest apiReq) throws Exception {
        
        IfaBrandSearchA001DtoRequest appReq = new IfaBrandSearchA001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandSearchA001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBrandSearchService",
                "initializeA001", new TypeReference<DataList<IfaBrandSearchA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBrandSearchA001ApiResponse> apiRes = new DataList<IfaBrandSearchA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/common/composite/IfaBrandSearchSearchBrandA002
     * アクションID：A002
     * アクション名：銘柄検索
     * APIリクエスト：IfaBrandSearchA002ApiRequest
     * Apiレスポンス：IfaBrandSearchA002ApiResponse
     * Dtoリクエスト：IfaBrandSearchA002DtoRequest
     * Dtoレスポンス：IfaBrandSearchA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaBrandSearchSearchBrandA002")
    public String searchBrandA002(@RequestBody IfaBrandSearchA002ApiRequest apiReq) throws Exception {
        
        IfaBrandSearchA002DtoRequest appReq = new IfaBrandSearchA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandSearchA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBrandSearchService",
                "searchBrandA002", new TypeReference<DataList<IfaBrandSearchA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBrandSearchA002ApiResponse> apiRes = new DataList<IfaBrandSearchA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/common/composite/IfaBrandSearchSearchBrandA003
     * アクションID：A003
     * アクション名：銘柄検索
     * APIリクエスト：IfaBrandSearchA003ApiRequest
     * Apiレスポンス：IfaBrandSearchA003ApiResponse
     * Dtoリクエスト：IfaBrandSearchA003DtoRequest
     * Dtoレスポンス：IfaBrandSearchA003DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaBrandSearchSearchBrandA003")
    public String searchBrandA003(@RequestBody IfaBrandSearchA003ApiRequest apiReq) throws Exception {
        
        IfaBrandSearchA003DtoRequest appReq = new IfaBrandSearchA003DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandSearchA003DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBrandSearchService",
                "searchBrandA003", new TypeReference<DataList<IfaBrandSearchA003DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBrandSearchA003ApiResponse> apiRes = new DataList<IfaBrandSearchA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/common/composite/IfaBrandSearchSelectBrandA006
     * アクションID：A006
     * アクション名：銘柄選択
     * APIリクエスト：IfaBrandSearchA006ApiRequest
     * Apiレスポンス：IfaBrandSearchA006ApiResponse
     * Dtoリクエスト：IfaBrandSearchA006DtoRequest
     * Dtoレスポンス：IfaBrandSearchA006DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/composite/ifaBrandSearchSelectBrandA006")
    public String selectBrandA006(@RequestBody IfaBrandSearchA006ApiRequest apiReq) throws Exception {
        
        IfaBrandSearchA006DtoRequest appReq = new IfaBrandSearchA006DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaBrandSearchA006DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaBrandSearchService",
                "selectBrandA006", new TypeReference<DataList<IfaBrandSearchA006DtoResponse>>() {
                }, appReq);
        
        DataList<IfaBrandSearchA006ApiResponse> apiRes = new DataList<IfaBrandSearchA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
