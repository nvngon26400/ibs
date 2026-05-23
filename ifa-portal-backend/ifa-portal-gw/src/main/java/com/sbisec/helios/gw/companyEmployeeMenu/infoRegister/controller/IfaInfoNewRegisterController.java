package com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.controller;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008aRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoNewRegisterA008bResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoNewRegisterA001ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoNewRegisterA001ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoNewRegisterA008aApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoNewRegisterA008bApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.form.IfaInfoNewRegisterA008bApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.infoRegister.util.FileUploadUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 画面ID：SUB0501_01-02_1
 * 画面名：情報新規登録
 *
 * @author SCSK
 2024/05/17 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0501_01-02_1", screenNumber = "")
public class IfaInfoNewRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String SERVICE_NAME = "cmpIfaInfoNewRegisterService";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoNewRegisterController.class);
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoNewRegisterInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaInfoNewRegisterA001ApiRequest
     * Apiレスポンス：IfaInfoNewRegisterA001ApiResponse
     * Dtoリクエスト：IfaInfoNewRegisterA001RequestDto
     * Dtoレスポンス：IfaInfoNewRegisterA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    
    @PostMapping("/companyEmployeeMenu/infoRegister/ifaInfoNewRegisterInitializeA001")
    public String initializeA001(@RequestBody @Validated IfaInfoNewRegisterA001ApiRequest apiReq) throws Exception {
        
        IfaInfoNewRegisterA001RequestDto appReq = new IfaInfoNewRegisterA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoNewRegisterA001ResponseDto> appRes = ApiRequestUtil.invoke(SERVICE_NAME, "initializeA001",
                new TypeReference<DataList<IfaInfoNewRegisterA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaInfoNewRegisterA001ApiResponse> apiRes = new DataList<IfaInfoNewRegisterA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * A008a 更新（ファイルの更新）
    * Apiレスポンス：IfaInfoNewRegisterA008aApiResponse
    *
    * @param file1 {@code MultipartFile }
    * @param file2 {@code MultipartFile }
    * @param file3 {@code MultipartFile }
    * @return {@code String}
    * @throws Exception 更新（ファイルの更新）処理で例外が発生した場合
    */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoNewRegisterRegisterA008a")
    public String registerA008a(@RequestPart(value = "file1", required = false) MultipartFile file1,
            @RequestPart(value = "file2", required = false) MultipartFile file2,
            @RequestPart(value = "file3", required = false) MultipartFile file3) throws Exception {
        
        var directory = ApiRequestUtil.invoke(SERVICE_NAME, "registerA008a", //
                new TypeReference<DataList<String>>() {
                }, new IfaInfoNewRegisterA008aRequestDto()).getDataList().stream()//
                .findFirst()//
                .map(s -> Paths.get(s)) //
                .orElseThrow();
        
        //顧客共通情報
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        var userId = IfaCommonUtil.getUserAccount().getUserId();
        
        var res = new IfaInfoNewRegisterA008aApiResponse();
        try {
            FileUploadUtil.saveAndRename(directory, userId, file1) //
                    .ifPresent(p -> res.setRegisterFile1(p.getFileName().toString()));
            FileUploadUtil.saveAndRename(directory, userId, file2) //
                    .ifPresent(p -> res.setRegisterFile2(p.getFileName().toString()));
            FileUploadUtil.saveAndRename(directory, userId, file3) //
                    .ifPresent(p -> res.setRegisterFile3(p.getFileName().toString()));
            
            return jc.toString(IfaCommonUtil.createDataList(List.of(res), ErrorLevel.SUCCESS,
                    ErrorLevel.SUCCESS.toString(), null));
        } catch (IOException e) {
            LOGGER.info("Exception occured.", e);
            return jc.toString(IfaCommonUtil.createDataList(null, ErrorLevel.SUCCESS, "errors.processingFailed", null));
        }
        
    }
    
    /**
     * アクセス：/companyEmployeeMenu/infoRegister/ifaInfoNewRegisterRegisterA008b
     * アクションID：A008b
     * アクション名：登録
     * APIリクエスト：IfaInfoNewRegisterA008bApiRequest
     * Apiレスポンス：IfaInfoNewRegisterA008bApiResponse
     * Dtoリクエスト：IfaInfoNewRegisterA008bRequestDto
     * Dtoレスポンス：IfaInfoNewRegisterA008bResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/companyEmployeeMenu/infoRegister/ifaInfoNewRegisterRegisterA008b")
    public String registerA008b(@RequestBody @Validated IfaInfoNewRegisterA008bApiRequest apiReq) throws Exception {
        
        IfaInfoNewRegisterA008bRequestDto appReq = new IfaInfoNewRegisterA008bRequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaInfoNewRegisterA008bResponseDto> appRes = ApiRequestUtil.invoke(SERVICE_NAME, "registerA008b",
                new TypeReference<DataList<IfaInfoNewRegisterA008bResponseDto>>() {
                }, appReq);
        
        DataList<IfaInfoNewRegisterA008bApiResponse> apiRes = new DataList<IfaInfoNewRegisterA008bApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
