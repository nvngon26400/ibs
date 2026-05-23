package com.sbisec.helios.gw.internalAdminMenu.formAcquire.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001ReponseDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.formAcquire.dto.IfaBrokerageSubLedgerAcquireA002ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA001ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA001ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA003aApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA003aApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.formAcquire.form.IfaBrokerageSubLedgerAcquireA003bApiRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0402_01-01
 * 画面名：仲介業補助簿取得
 *
 * @author <author-name>
 2024/06/21 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0402_01-01", screenNumber = "")
public class IfaBrokerageSubLedgerAcquireController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBrokerageSubLedgerAcquireController.class);

	
    /**
     * 
     * アクセス：/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDisplayA002
     * アクションID：A001
     * アクション名：表示
     * APIリクエスト：IfaBrokerageSubLedgerAcquireA001ApiRequest
     * Apiレスポンス：IfaBrokerageSubLedgerAcquireA001ApiResponse
     * Dtoリクエスト：IfaBrokerageSubLedgerAcquireA001RequestDto
     * Dtoレスポンス：IfaBrokerageSubLedgerAcquireA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireinitializeA001")
    public String initializeA001(@RequestBody IfaBrokerageSubLedgerAcquireA001ApiRequest apiReq) throws Exception {

        IfaBrokerageSubLedgerAcquireA001RequestDto appReq = new IfaBrokerageSubLedgerAcquireA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBrokerageSubLedgerAcquireService",
                "initializeA001", new TypeReference<DataList<IfaBrokerageSubLedgerAcquireA001ReponseDto>>() {
                }, appReq);
        
        DataList<IfaBrokerageSubLedgerAcquireA001ApiResponse> apiRes = new DataList<IfaBrokerageSubLedgerAcquireA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaBrokerageSubLedgerAcquireA002ApiRequest
     * Apiレスポンス：IfaBrokerageSubLedgerAcquireA002ApiResponse
     * Dtoリクエスト：IfaBrokerageSubLedgerAcquireA002RequestDto
     * Dtoレスポンス：IfaBrokerageSubLedgerAcquireA002ResponseDto
	 *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDisplayA002")
    public String displayA002(@RequestBody IfaBrokerageSubLedgerAcquireA002ApiRequest apiReq) throws Exception {

        IfaBrokerageSubLedgerAcquireA002RequestDto appReq = new IfaBrokerageSubLedgerAcquireA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaBrokerageSubLedgerAcquireService",
                "displayA002", new TypeReference<DataList<IfaBrokerageSubLedgerAcquireA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaBrokerageSubLedgerAcquireA002ApiResponse> apiRes = new DataList<IfaBrokerageSubLedgerAcquireA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDownloadA003a
     * アクションID：A003a
     * アクション名：表示
     * APIリクエスト：IfaBrokerageSubLedgerAcquireA003aApiRequest
     * Apiレスポンス：IfaBrokerageSubLedgerAcquireA003aApiResponse
     * Dtoリクエスト：IfaBrokerageSubLedgerAcquireA003aRequestDto
     * Dtoレスポンス：IfaBrokerageSubLedgerAcquireA003aResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDownloadA003a")
    public String createPdf(@RequestBody IfaBrokerageSubLedgerAcquireA003aApiRequest apiReq, HttpServletRequest request) throws Exception {

        
        String fileName = apiReq.getFileDirectory() + apiReq.getCreateDate() + "/" + apiReq.getFileName();
        
        DataList<IfaBrokerageSubLedgerAcquireA003aApiResponse> apiRes = new DataList<>();
        apiRes.setRequestedTime(getFormattedRequestedTime(request));

        File tmp = new File(fileName);
        // ファイルが見つからなかった場合
        if (!tmp.exists()) {
            LOGGER.error("Requested file [" + fileName + "] not found. "
                    + "End user will not be able to check compliance information.");
            String errorMessage = getMessage(ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS, new String[] {});
            apiRes.setMessage(errorMessage);
            apiRes.setErrorLevel(ErrorLevel.FATAL.getId());
            apiRes.setReturnCode(ERRORS_VERIFY_COMPLIANCE_FILE_EXISTS);
        } else {
            apiRes.setTotalSize(1);
            IfaBrokerageSubLedgerAcquireA003aApiResponse ifaBrokerageSubLedgerAcquireA003aApiResponse = new IfaBrokerageSubLedgerAcquireA003aApiResponse();
            ifaBrokerageSubLedgerAcquireA003aApiResponse.setPdfFileName(fileName);
            apiRes.getDataList().add(ifaBrokerageSubLedgerAcquireA003aApiResponse);
        }
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDownloadA003a
     * アクションID：A003a
     * アクション名：表示
     * APIリクエスト：IfaBrokerageSubLedgerAcquireA003aApiRequest
     * Apiレスポンス：IfaBrokerageSubLedgerAcquireA003aApiResponse
     * Dtoリクエスト：IfaBrokerageSubLedgerAcquireA003aRequestDto
     * Dtoレスポンス：IfaBrokerageSubLedgerAcquireA003aResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/internalAdminMenu/formAcquire/ifaBrokerageSubLedgerAcquireDownloadA003b")
    public void outputPdf(@RequestBody IfaBrokerageSubLedgerAcquireA003bApiRequest apiReq, HttpServletResponse response) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + apiReq.getPdfFileName().substring(apiReq.getPdfFileName().lastIndexOf("/") + 1) + "\"");
            fileInputStream = new FileInputStream(apiReq.getPdfFileName());
            IOUtils.copy(fileInputStream, response.getOutputStream());
        }  catch (FileNotFoundException e) {
            //例外処理
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("{IfaBrokerageSubLedgerAcquireController,ifaBrokerageSubLedgerAcquireDownloadA003b,FileNotFoundException}");
            }
            throw e;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        
    }

    @Override
    protected String getFirstViewName() {
        return null;
    }
}

