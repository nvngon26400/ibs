package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerPortalA011ResponseDto;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerPortalA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerPortalA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerPortalA011ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaCustomerPortalA011ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * @author 松田
 *
 * 2023/11/30 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_00-02", screenNumber = "")
public class IfaCustomerPortalController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /** Redisグループ名：顧客ポータル情報 */
    public static final String ATTR_REDIS_GROUP_CUSTOMER_PORTAL = "customerPortal";
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerPortalInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCustomerPortalA001ApiRequest
     * Apiレスポンス：IfaCustomerPortalA001ApiResponse
     * Dtoリクエスト：IfaCustomerPortalA001DtoRequest
     * Dtoレスポンス：IfaCustomerPortalA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerPortalInitializeA001Stb")
    public String initializeA001Stb(@RequestBody IfaCustomerPortalA001ApiRequest apiReq) throws Exception {
        
        IfaCustomerPortalA001RequestDto appReq = new IfaCustomerPortalA001RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCustomerPortalA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerPortalService",
                "initializeA001Stb", new TypeReference<DataList<IfaCustomerPortalA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerPortalA001ApiResponse> apiRes = new DataList<IfaCustomerPortalA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        // 取得した顧客共通情報のキャッシュ登録
        if (appRes != null && !CollectionUtils.isEmpty(appRes.getDataList())) {
            IfaCustomerPortalA001ResponseDto result = appRes.getDataList().get(0);
            CustomerCommon cc = new CustomerCommon();
            BeanUtils.copyProperties(cc, result);
            
            // Redisに顧客共通情報を登録する
            IfaGwCommonUtil.putDataToRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_CUSTOMER_COMMON, true,
                    IfaGwCommonUtil.ATTR_REDIS_KEY_CUSTOMER_COMMON, cc, CustomerCommon.class);
            
            // 顧客共通情報をリクエストスコープに設定する
            IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON, cc);
            
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerPortalInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaCustomerPortalA001ApiRequest
     * Apiレスポンス：IfaCustomerPortalA001ApiResponse
     * Dtoリクエスト：IfaCustomerPortalA001DtoRequest
     * Dtoレスポンス：IfaCustomerPortalA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerPortalInitializeA001")
    public String initializeA001(@RequestBody IfaCustomerPortalA001ApiRequest apiReq) throws Exception {
        
        IfaCustomerPortalA001RequestDto appReq = new IfaCustomerPortalA001RequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCustomerPortalA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerPortalService",
                "initializeA001", new TypeReference<DataList<IfaCustomerPortalA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerPortalA001ApiResponse> apiRes = new DataList<IfaCustomerPortalA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        // 取得した顧客共通情報のキャッシュ登録
        if (appRes != null && !CollectionUtils.isEmpty(appRes.getDataList())) {
            IfaCustomerPortalA001ResponseDto result = appRes.getDataList().get(0);
            CustomerCommon cc = new CustomerCommon();
            BeanUtils.copyProperties(cc, result);
            
            // Redisに顧客共通情報を登録する
            IfaGwCommonUtil.putDataToRedis(IfaGwCommonUtil.ATTR_REDIS_GROUP_CUSTOMER_COMMON, true,
                    IfaGwCommonUtil.ATTR_REDIS_KEY_CUSTOMER_COMMON, cc, CustomerCommon.class);
            
            // 顧客共通情報をリクエストスコープに設定する
            IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON, cc);
            
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaCustomerPortalMemoIFAUpdateA011
     * アクションID：A011
     * アクション名：メモ(IFA専用)更新
     * APIリクエスト：IfaCustomerPortalA011ApiRequest
     * Apiレスポンス：IfaCustomerPortalA011ApiResponse
     * Dtoリクエスト：IfaCustomerPortalA011DtoRequest
     * Dtoレスポンス：IfaCustomerPortalA011DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaCustomerPortalMemoIFAUpdateA011")
    public String memoIFAUpdateA011(@RequestBody IfaCustomerPortalA011ApiRequest apiReq) throws Exception {
        
        IfaCustomerPortalA011RequestDto appReq = new IfaCustomerPortalA011RequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaCustomerPortalA011ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaCustomerPortalService",
                "memoIFAUpdateA011", new TypeReference<DataList<IfaCustomerPortalA011ResponseDto>>() {
                }, appReq);
        
        DataList<IfaCustomerPortalA011ApiResponse> apiRes = new DataList<IfaCustomerPortalA011ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
