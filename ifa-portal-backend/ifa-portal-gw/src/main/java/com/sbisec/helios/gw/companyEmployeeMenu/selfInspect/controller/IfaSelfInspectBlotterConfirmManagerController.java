package com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerDisplayResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerQueryRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.util.IfaSelfInspectBlotterConfirmManagerCsvUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerA004ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerA004CsvDownloadApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerDisplayApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.selfInspect.form.IfaSelfInspectBlotterConfirmManagerQueryApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0506_01-01", screenNumber = "")
public class IfaSelfInspectBlotterConfirmManagerController extends BaseController {
    
    @Override
    protected String getCsvHeader() {
        
        return IfaSelfInspectBlotterConfirmManagerCsvUtil.HEADER;
    }
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String SERVICE_NAME = "cmpIfaSelfInspectBlotterConfirmManagerService";
    
    /**
    * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerSearchDisplayA003
    * アクションID：A003
    * アクション名：検索表示
    * APIリクエスト：IfaSelfInspectBlotterConfirmManagerQueryApiRequest
    * Apiレスポンス：IfaSelfInspectBlotterConfirmManagerDisplayApiResponse
    * Dtoリクエスト：IfaSelfInspectBlotterConfirmManagerQueryRequestDto
    * Dtoレスポンス：IfaSelfInspectBlotterConfirmManagerDisplayResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerSearchDisplayA003")
    public String searchDisplayA003(@Validated @RequestBody IfaSelfInspectBlotterConfirmManagerQueryApiRequest apiReq)
            throws Exception {
        
        var appReq = new IfaSelfInspectBlotterConfirmManagerQueryRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "searchDisplayA003",
                new TypeReference<DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaSelfInspectBlotterConfirmManagerDisplayApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerAllBrokerNameDisplayA002
    * アクションID：A002
    * アクション名：全仲介業者名表示
    * APIリクエスト：IfaSelfInspectBlotterConfirmManagerQueryApiRequest
    * Apiレスポンス：IfaSelfInspectBlotterConfirmManagerDisplayApiResponse
    * Dtoリクエスト：IfaSelfInspectBlotterConfirmManagerQueryRequestDto
    * Dtoレスポンス：IfaSelfInspectBlotterConfirmManagerDisplayResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerAllBrokerNameDisplayA002")
    public String allBrokerNameDisplayA002(
            @Validated @RequestBody IfaSelfInspectBlotterConfirmManagerQueryApiRequest apiReq) throws Exception {
        
        var appReq = new IfaSelfInspectBlotterConfirmManagerQueryRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "allBrokerNameDisplayA002",
                new TypeReference<DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaSelfInspectBlotterConfirmManagerDisplayApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerCsvOutputA004
    * アクションID：A001
    * アクション名：初期化
    * APIリクエスト：IfaSelfInspectBlotterConfirmManagerA001ApiRequest
    * Apiレスポンス：IfaSelfInspectBlotterConfirmManagerA001ApiResponse
    * Dtoリクエスト：IfaSelfInspectBlotterConfirmManagerA001RequestDto
    * Dtoレスポンス：IfaSelfInspectBlotterConfirmManagerA001ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerInitializeA001")
    public String initializeA001(@Validated @RequestBody IfaSelfInspectBlotterConfirmManagerA001ApiRequest apiReq)
            throws Exception {
        
        var appReq = new IfaSelfInspectBlotterConfirmManagerA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "initializeA001",
                new TypeReference<DataList<IfaSelfInspectBlotterConfirmManagerA001ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaSelfInspectBlotterConfirmManagerA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerCsvOutputA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaSelfInspectBlotterConfirmManagerQueryApiRequest
     * Apiレスポンス：IfaSelfInspectBlotterConfirmManagerA004ApiResponse
     * Dtoリクエスト：csvOutputA004RequestDto
     * Dtoレスポンス：csvOutputA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerCsvOutputA004")
    public String csvOutputA004(@Validated @RequestBody IfaSelfInspectBlotterConfirmManagerQueryApiRequest apiReq)
            throws Exception {
        
        var appReq = new IfaSelfInspectBlotterConfirmManagerQueryRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "csvOutputA004",
                new TypeReference<DataList<IfaSelfInspectBlotterConfirmManagerA004ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaSelfInspectBlotterConfirmManagerA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerCsvDownloadA004
     * アクションID：A004
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaSelfInspectBlotterConfirmManagerA004CsvDownloadApiRequest
     *
     * @param apiReq リクエストパラメータ
     * @param response 画面へのレスポンスデータ
     * @exception Exception 例外
     */
    @ResponseFile
    @PostMapping("/companyEmployeeMenu/selfInspect/ifaSelfInspectBlotterConfirmManagerCsvDownloadA004")
    public void csvDownloadA004(
            @Validated @RequestBody IfaSelfInspectBlotterConfirmManagerA004CsvDownloadApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("自己点検記録簿確認_管理者用"),
                IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
