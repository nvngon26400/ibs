package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA004aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaSendReceiveStatusLookupA005DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.util.IfaSendReceiveStatusLookupCsvOut;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA004bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaSendReceiveStatusLookupA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

/**
 * 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0703-01", screenNumber = "")
public class IfaSendReceiveStatusLookupController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();

    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupInitializeA001
     * アクションID：A001
     * アクション名：初期化 
     * APIリクエスト：IfaSendReceiveStatusLookupA001ApiRequest
     * APIレスポンス：IfaSendReceiveStatusLookupA001ApiResponse
     * DTOリクエスト：IfaSendReceiveStatusLookupA001DtoRequest
     * DTOレスポンス：IfaSendReceiveStatusLookupA001DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupInitializeA001")
    public String initializeA001(@RequestBody IfaSendReceiveStatusLookupA001ApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaSendReceiveStatusLookupA001DtoRequest appReq = new IfaSendReceiveStatusLookupA001DtoRequest();
        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSendReceiveStatusLookupA001DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSendReceiveStatusLookupService", "initializeA001",
                new TypeReference<DataList<IfaSendReceiveStatusLookupA001DtoResponse>>() {
                }, appReq);

        DataList<IfaSendReceiveStatusLookupA001ApiResponse> apiRes = new DataList<IfaSendReceiveStatusLookupA001ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupDisplayA002
     * アクションID：A002
     * アクション名：表示　書類コード
     * APIリクエスト：IfaSendReceiveStatusLookupA002ApiRequest
     * APIレスポンス：IfaSendReceiveStatusLookupA002ApiResponse
     * DTOリクエスト：IfaSendReceiveStatusLookupA002DtoRequest
     * DTOレスポンス：IfaSendReceiveStatusLookupA002DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupDisplayA002")
    public String displayA002(@RequestBody IfaSendReceiveStatusLookupA002ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaSendReceiveStatusLookupA002ApiResponse> apiRes = new DataList<IfaSendReceiveStatusLookupA002ApiResponse>();

        IfaSendReceiveStatusLookupA002DtoRequest appReq = new IfaSendReceiveStatusLookupA002DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSendReceiveStatusLookupA002DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSendReceiveStatusLookupService", "displayA002",
                new TypeReference<DataList<IfaSendReceiveStatusLookupA002DtoResponse>>() {
                }, appReq);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupDisplayA003
     * アクションID：A003
     * アクション名：検索ボタン　検索(キーワード)
     * APIリクエスト：IfaSendReceiveStatusLookupA003ApiRequest
     * APIレスポンス：IfaSendReceiveStatusLookupA003ApiResponse
     * DTOリクエスト：IfaSendReceiveStatusLookupA003DtoRequest
     * DTOレスポンス：IfaSendReceiveStatusLookupA003DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupDisplayA003")
    public String displayA003(@RequestBody IfaSendReceiveStatusLookupA003ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaSendReceiveStatusLookupA003ApiResponse> apiRes = new DataList<IfaSendReceiveStatusLookupA003ApiResponse>();

        IfaSendReceiveStatusLookupA003DtoRequest appReq = new IfaSendReceiveStatusLookupA003DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSendReceiveStatusLookupA003DtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSendReceiveStatusLookupService", "displayA003",
                new TypeReference<DataList<IfaSendReceiveStatusLookupA003DtoResponse>>() {
                }, appReq);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004a
     * アクションID：A004a
     * アクション名：CSV出力
     * APIリクエスト：ifaSendReceiveStatusLookupA004aApiRequest
     * APIレスポンス：ifaSendReceiveStatusLookupA004aApiResponse
     * DTOリクエスト：ifaSendReceiveStatusLookupA004aDtoRequest
     * DTOレスポンス：ifaSendReceiveStatusLookupA004aDtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaSendReceiveStatusLookupA004aApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaSendReceiveStatusLookupA004aApiResponse> apiRes = new DataList<IfaSendReceiveStatusLookupA004aApiResponse>();

        IfaSendReceiveStatusLookupA004aDtoRequest appReq = new IfaSendReceiveStatusLookupA004aDtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);

        DataList<IfaSendReceiveStatusLookupA004aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSendReceiveStatusLookupService", "csvOutputA004a",
                new TypeReference<DataList<IfaSendReceiveStatusLookupA004aDtoResponse>>() {
                }, appReq, frameworkSessionId);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004b
     * アクションID：A004b
     * アクション名：CSV出力
     * リクエスト：ifaSendReceiveStatusLookupA004bApiRequest
     * レスポンス：HttpServletResponse
     *
     * @param apiReq リクエスト
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaSendReceiveStatusLookupA004bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        String[] parts = StringUtils.split(apiReq.getCsvDownloadFile(), ",");
        String csvTmpFileName = parts[0]; // CSV一時ファイル名
        String fileName = parts[1]; // ファイル名

        // リクエスト.ファイル名を、ダウンロードファイル名とCSV一時ファイル名に分離
        doDownLoadCsvFile(response, csvTmpFileName, getCsvFileName(fileName), userAccount);

    }

    /**
     * アクセス：/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupSelectDocRequestAddInfoA005
     * アクションID：A005
     * アクション名：popup書類請求付加情報詳細
     * APIリクエスト：IfaSendReceiveStatusLookupA005ApiRequest
     * APIレスポンス：IfaSendReceiveStatusLookupA005ApiResponse
     * DTOリクエスト：IfaSendReceiveStatusLookupA005DtoRequest
     * DTOレスポンス：IfaSendReceiveStatusLookupA005DtoResponse
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @throws Exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaSendReceiveStatusLookupSelectDocRequestAddInfoA005")
    public String selectdocRequestAddInfoA005(@RequestBody IfaSendReceiveStatusLookupA005ApiRequest apiReq) throws Exception {
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaSendReceiveStatusLookupA005ApiResponse> apiRes = new DataList<IfaSendReceiveStatusLookupA005ApiResponse>();

        IfaSendReceiveStatusLookupA005DtoRequest appReq = new IfaSendReceiveStatusLookupA005DtoRequest();

        // APIリクエストをDTOリクエストにコピー
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaSendReceiveStatusLookupA005ApiResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaSendReceiveStatusLookupService", "selectdocRequestAddInfoA005",
                new TypeReference<DataList<IfaSendReceiveStatusLookupA005ApiResponse>>() {
                }, appReq);

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }
    
    @Override
    protected String getCsvHeader() {

        return IfaSendReceiveStatusLookupCsvOut.getHeaders();
    }
    
    @Override
    protected String getFirstViewName() {

        return null;
    }

}
