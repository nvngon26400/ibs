package com.sbisec.helios.gw.testtool.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct020Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct024Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct027Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct028Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct039Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct004Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct006Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct031Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct033Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct034Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct037Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct038Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct039Dto;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Postmanで共通関数スタブをテスト用コントローラー
 * 
 * @author base 熊
 */
@RestController
@ScreenId(groupId = "COMMON", id = "UnitTest", ignoreCheck = true)
public class FunctionTestController extends BaseController {
    
    @Override
    /** {@inheritDoc} */
    protected String getFirstViewName() {
        
        return null;
    }

    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct001")
    public Object doFct001(@RequestBody InputFct001Dto posRequest) throws Exception {

        return ApiRequestUtil.invoke("functionTestService", "doFct001", new TypeReference<OutputFct001Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct004")
    public Object doFct004(@RequestBody InputFct004Dto posRequest) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct004", new TypeReference<OutputFct004Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/fct/020")
    public Object funtionTestFct020(@RequestBody InputFct020Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct020", new TypeReference<Object>() {
        }, input);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct024")
    public Object funtionTestFct024(@RequestBody InputFct024Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct024", new TypeReference<Object>() {
        }, input);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/fct/027")
    public Object funtionTestFct027(@RequestBody InputFct027Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct027", new TypeReference<Object>() {
        }, input);
    }
    
        /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct028")
    public Object doFct028(@RequestBody InputFct028Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct028", new TypeReference<Object>() {
        }, input);
    }

    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct030")
    public Object doFct030(@RequestBody InputFct030Dto posRequest) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct030", new TypeReference<OutputFct030Dto>() {
        }, posRequest);
    }

    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct033")
    public Object doFct033(@RequestBody InputFct033Dto posRequest) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        return ApiRequestUtil.invoke("functionTestService", "doFct033", new TypeReference<OutputFct033Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct034")
    public Object doFct034(@RequestBody InputFct034Dto posRequest) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        return ApiRequestUtil.invoke("functionTestService", "doFct034", new TypeReference<OutputFct034Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/035")
    public Object doFct035(@RequestBody InputFct035Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct035", new TypeReference<Object>() {
        }, input);
        
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct032")
    public Object doFct032(@RequestBody InputFct032Dto posRequest) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct032", new TypeReference<OutputFct032Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct031")
    public Object doFct031(@RequestBody InputFct031Dto posRequest) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct031", new TypeReference<OutputFct031Dto>() {
        }, posRequest);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct037")
    public Object doFct037(@RequestBody InputFct037Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct037", new TypeReference<OutputFct037Dto>() {
        }, input);
    }
    
    /**
     * 単体テスト用メソッド
     * @param request
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct038")
    public Object doFct038(@RequestBody InputFct038Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct038", new TypeReference<OutputFct038Dto>() {
        }, input);
    }
    
    /**
     * 単体テスト用メソッド
     * @param input
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct039")
    public Object doFct039(@RequestBody InputFct039Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct039", new TypeReference<OutputFct039Dto>() {
        }, input);
    }

    /**
     * 単体テスト用メソッド
     * @param input
     * @return response
     * @throws Exception
     */
    @PostMapping("/unittest/fct006")
    public Object doFct006(@RequestBody InputFct006Dto input) throws Exception {
        
        return ApiRequestUtil.invoke("functionTestService", "doFct006", new TypeReference<OutputFct006Dto>() {
        }, input);
    }
    
}
