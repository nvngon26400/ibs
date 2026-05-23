package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.constants.ServiceNameConstants;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA001ResponseDto;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA001ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA003ResponseDto;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA003ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA005ResponseDto;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA005ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA005ApiResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaNotificationViewStatusLookupA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.util.IfaNotificationViewStatusLookupManagerCsvOut;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA006ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaNotificationViewStatusLookupA006ApiResponse;

/**
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 * @author <author-name>
 2024/06/13 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_01-05", screenNumber = "")
public class IfaNotificationViewStatusLookupController extends BaseController {

    @Override
    protected String getCsvHeader() {
        // ダウンロードする際に呼び出される
        return IfaNotificationViewStatusLookupManagerCsvOut.getHeaders();
    }


    private JsonConverter jc = JsonConverter.getInstance();
    /** ダウンロードファイルの接頭語 */
    private static final String PREFIX_CSV_FILE_NAME = "お知らせ閲覧状況照会";
	
    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaNotificationViewStatusLookupA001ApiRequest
     * Apiレスポンス：IfaNotificationViewStatusLookupA001ApiResponse
     * Dtoリクエスト：IfaNotificationViewStatusLookupA001RequestDto
     * Dtoレスポンス：IfaNotificationViewStatusLookupA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupInitializeA001")
    public String initializeA001(@RequestBody IfaNotificationViewStatusLookupA001ApiRequest apiReq) throws Exception {

        IfaNotificationViewStatusLookupA001RequestDto appReq = new IfaNotificationViewStatusLookupA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaNotificationViewStatusLookupA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaNotificationViewStatusLookupService",
                "initializeA001", new TypeReference<DataList<IfaNotificationViewStatusLookupA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaNotificationViewStatusLookupA001ApiResponse> apiRes = new DataList<IfaNotificationViewStatusLookupA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupInitializeA003
     * アクションID：A003
     * アクション名：代理開封OK
     * APIリクエスト：IfaNotificationViewStatusLookupA003ApiRequest
     * Apiレスポンス：IfaNotificationViewStatusLookupA003ApiResponse
     * Dtoリクエスト：IfaNotificationViewStatusLookupA003RequestDto
     * Dtoレスポンス：IfaNotificationViewStatusLookupA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupProxyOpeningOkA003")
    public String proxyOpeningOkA003(@RequestBody IfaNotificationViewStatusLookupA003ApiRequest apiReq) throws Exception {

        IfaNotificationViewStatusLookupA003RequestDto appReq = new IfaNotificationViewStatusLookupA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaNotificationViewStatusLookupA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaNotificationViewStatusLookupService",
                "proxyOpeningOkA003", new TypeReference<DataList<IfaNotificationViewStatusLookupA003ResponseDto>>() {
                }, appReq);
        
        DataList<IfaNotificationViewStatusLookupA003ApiResponse> apiRes = new DataList<IfaNotificationViewStatusLookupA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupDeleteOkA005
     * アクションID：A005
     * アクション名：削除OK
     * APIリクエスト：IfaNotificationViewStatusLookupA005ApiRequest
     * Apiレスポンス：IfaNotificationViewStatusLookupA005ApiResponse
     * Dtoリクエスト：IfaNotificationViewStatusLookupA005RequestDto
     * Dtoレスポンス：IfaNotificationViewStatusLookupA005ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupDeleteOkA005")
    public String deleteOkA005(@RequestBody IfaNotificationViewStatusLookupA005ApiRequest apiReq) throws Exception {

        IfaNotificationViewStatusLookupA005RequestDto appReq = new IfaNotificationViewStatusLookupA005RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaNotificationViewStatusLookupA005ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaNotificationViewStatusLookupService",
                "deleteOkA005", new TypeReference<DataList<IfaNotificationViewStatusLookupA005ResponseDto>>() {
                }, appReq);
        
        DataList<IfaNotificationViewStatusLookupA005ApiResponse> apiRes = new DataList<IfaNotificationViewStatusLookupA005ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupCsvDownloadA006a
     * アクションID：A006
     * アクション名：CSVダウンロード
     * APIリクエスト：ifaNotificationViewStatusLookupA006ApiRequest
     * Apiレスポンス：ifaNotificationViewStatusLookupA006ApiResponse
     * Dtoリクエスト：ifaNotificationViewStatusLookupA006RequestDto
     * Dtoレスポンス：ifaNotificationViewStatusLookupA006ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupCsvDownloadA006a")
    public String csvDownloadA006a(@RequestBody IfaNotificationViewStatusLookupA006ApiRequest apiReq) throws Exception {

        IfaNotificationViewStatusLookupA006RequestDto appReq = new IfaNotificationViewStatusLookupA006RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaNotificationViewStatusLookupA006ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaNotificationViewStatusLookupService",
                "csvDownloadA006a", new TypeReference<DataList<IfaNotificationViewStatusLookupA006ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaNotificationViewStatusLookupA006ApiResponse> apiRes = new DataList<IfaNotificationViewStatusLookupA006ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupCsvDownloadA006b
     * アクションID：A006
     * アクション名：CSVダウンロード
     * APIリクエスト：ifaNotificationViewStatusLookupA006ApiRequest
     * Apiレスポンス：ifaNotificationViewStatusLookupA006ApiResponse
     * Dtoリクエスト：ifaNotificationViewStatusLookupA006RequestDto
     * Dtoレスポンス：ifaNotificationViewStatusLookupA006ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaNotificationViewStatusLookupCsvDownloadA006b")
    public void csvDownloadA006b(@RequestBody IfaNotificationViewStatusLookupA006ApiRequest apiReq, HttpServletResponse response)
            throws Exception {

        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        // Star ファイル ダウンロード
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(PREFIX_CSV_FILE_NAME), userAccount);
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

