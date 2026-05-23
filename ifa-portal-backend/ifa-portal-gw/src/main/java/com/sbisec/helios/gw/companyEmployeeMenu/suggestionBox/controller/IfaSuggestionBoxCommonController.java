package com.sbisec.helios.gw.companyEmployeeMenu.suggestionBox.controller;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA001DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA002DtoResponse;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoRequest;
import com.sbisec.helios.ap.suggestionBox.dto.IfaSuggestionBoxCommonA006aDtoResponse;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.controller.IfaBbApplyListController;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA002ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA002ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA006aApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA001ApiRequest;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA001ApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA006aApiResponse;
import com.sbisec.helios.gw.suggestionBox.form.IfaSuggestionBoxCommonA006bApiRequest;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0511_02-01
 * 画面名：皆様からの要望
 * @author 林
 *
 * 2025/06/18 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0511_02-01", screenNumber = "")
public class IfaSuggestionBoxCommonController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyListController.class);
    
    /** ダウンロードファイルの接頭語 */
    private static final String SUGGESTION_COMMON_CSV_FILE_NAME = "皆様からの要望";
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonHeadofficerA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSuggestionBoxCommonA001ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA001ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA001DtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA001DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonHeadOfficerA001")
    public String initializeA001(@RequestBody IfaSuggestionBoxCommonA001ApiRequest apiReq) throws Exception {
        
    	IfaSuggestionBoxCommonA001DtoRequest appReq = new IfaSuggestionBoxCommonA001DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSuggestionBoxCommonA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonService", "initializeA001",
                new TypeReference<DataList<IfaSuggestionBoxCommonA001DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSuggestionBoxCommonA001ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonHeadofficerA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaSuggestionBoxCommonA002ApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA002ApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA002DtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonHeadOfficerA002")
    public String displayA002(@RequestBody IfaSuggestionBoxCommonA002ApiRequest apiReq) throws Exception {
        
    	IfaSuggestionBoxCommonA002DtoRequest appReq = new IfaSuggestionBoxCommonA002DtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSuggestionBoxCommonA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonService", "displayA002",
                new TypeReference<DataList<IfaSuggestionBoxCommonA002DtoResponse>>() {
                }, appReq);
        
        DataList<IfaSuggestionBoxCommonA002ApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonA006a
     * アクションID：A006
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaSuggestionBoxCommonA006aApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA006aApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA006aDtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA006aDtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonA006a")
    public String csvOutputA006a(@RequestBody IfaSuggestionBoxCommonA006aApiRequest apiReq) throws Exception {
        
    	IfaSuggestionBoxCommonA006aDtoRequest appReq = new IfaSuggestionBoxCommonA006aDtoRequest();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
     // フレームワークセッションIDを取得
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        
        DataList<IfaSuggestionBoxCommonA006aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSuggestionBoxCommonService", "csvOutputA006",
                new TypeReference<DataList<IfaSuggestionBoxCommonA006aDtoResponse>>() {
                }, appReq,fwSessionId);
        
        DataList<IfaSuggestionBoxCommonA006aApiResponse> apiRes = new DataList<IfaSuggestionBoxCommonA006aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    
    /**
     * 
     * アクセス：/suggestionBox/ifaSuggestionBoxCommonA006b
     * アクションID：A006
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaSuggestionBoxCommonA006bApiRequest
     * Apiレスポンス：IfaSuggestionBoxCommonA006bApiResponse
     * Dtoリクエスト：IfaSuggestionBoxCommonA006bDtoRequest
     * Dtoレスポンス：IfaSuggestionBoxCommonA006bDtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/suggestionBox/ifaSuggestionBoxCommonA006b")
    public void csvOutputA006b(@RequestBody IfaSuggestionBoxCommonA006bApiRequest apiReq, HttpServletResponse response) throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaSuggestionBoxCommonController.csvOutputA006b >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // suggestionBoxCommonA006a で作成したファイルをダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(SUGGESTION_COMMON_CSV_FILE_NAME), userAccount, apiReq.getPattern());

        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
        
    }
    

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
