package com.sbisec.helios.gw.faq.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA002DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqA005DtoResponse;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoRequest;
import com.sbisec.helios.ap.faq.dto.IfaFaqX001DtoResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.faq.form.IfaFaqA002ApiRequest;
import com.sbisec.helios.gw.faq.form.IfaFaqA002ApiResponse;
import com.sbisec.helios.gw.faq.form.IfaFaqA005ApiRequest;
import com.sbisec.helios.gw.faq.form.IfaFaqA005ApiResponse;
import com.sbisec.helios.gw.faq.form.IfaFaqX001ApiRequest;
import com.sbisec.helios.gw.faq.form.IfaFaqX001ApiResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB00-05
 * 画面名：よくある質問
 *
 * @author SCSK 仁井田
 2024/05/30 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB00-05", screenNumber = "")
public class IfaFaqController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/faq/ifaFaqInitialDisplayX001
     * アクションID：X001
     * アクション名：初期表示
     * APIリクエスト：IfaFaqX001ApiRequest
     * Apiレスポンス：IfaFaqX001ApiResponse
     * Dtoリクエスト：IfaFaqX001DtoRequest
     * Dtoレスポンス：IfaFaqX001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/faq/ifaFaqInitialDisplayX001")
    public String initialDisplayX001(@RequestBody IfaFaqX001ApiRequest apiReq) throws Exception {
        
        IfaFaqX001DtoRequest appReq = new IfaFaqX001DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFaqX001DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFaqService", "initialDisplayX001",
                new TypeReference<DataList<IfaFaqX001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFaqX001ApiResponse> apiRes = new DataList<IfaFaqX001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/faq/ifaFaqFullTextSearchA002
     * アクションID：A002
     * アクション名：全文検索
     * APIリクエスト：IfaFaqA002ApiRequest
     * APIレスポンス：IfaFaqA002ApiResponse
     * DTOリクエスト：IfaFaqA002DtoRequest
     * DTOレスポンス：IfaFaqA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/faq/ifaFaqFullTextSearchA002")
    public String fullTextSearchA002(@RequestBody IfaFaqA002ApiRequest apiReq) throws Exception {
        
        IfaFaqA002DtoRequest appReq = new IfaFaqA002DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFaqA002DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFaqService", "fullTextSearchA002",
                new TypeReference<DataList<IfaFaqA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFaqA002ApiResponse> apiRes = new DataList<IfaFaqA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/faq/ifaFaqContentsDisplayA005
     * アクションID：A005
     * アクション名：コンテンツ表示
     * APIリクエスト：IfaFaqA005ApiRequest
     * APIレスポンス：IfaFaqA005ApiResponse
     * DTOリクエスト：IfaFaqA005DtoRequest
     * DTOレスポンス：IfaFaqA005DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/faq/ifaFaqContentsDisplayA005")
    public String contentsDisplayA005(@RequestBody IfaFaqA005ApiRequest apiReq) throws Exception {
        
        IfaFaqA005DtoRequest appReq = new IfaFaqA005DtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaFaqA005DtoResponse> appRes = ApiRequestUtil.invoke("cmpIfaFaqService", "contentsDisplayA005",
                new TypeReference<DataList<IfaFaqA005DtoResponse>>() {
                }, appReq);
        
        DataList<IfaFaqA005ApiResponse> apiRes = new DataList<IfaFaqA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
