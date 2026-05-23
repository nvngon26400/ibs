package com.sbisec.helios.gw.brokerageMenu.commFee.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.util.IfaSbiWrapManageFeeCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaSbiWrapManageFeeA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaSbiWrapManageFeeA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaSbiWrapManageFeeA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.commFee.form.IfaSbiWrapManageFeeA003ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020504-01
 * 画面名：SBIラップ管理報酬
 *
 * @author 松尾
 2024/06/18 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020504-01", screenNumber = "")
public class IfaSbiWrapManageFeeController extends BaseController {
    
    @Override
    protected String getCsvHeader() {
        
        // 権限3でダウンロードする際に呼び出される
        return IfaSbiWrapManageFeeCsvOut.getHeaders();
    }
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaSbiWrapManageFeeDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaSbiWrapManageFeeA002ApiRequest
     * Apiレスポンス：IfaSbiWrapManageFeeA002ApiResponse
     * Dtoリクエスト：IfaSbiWrapManageFeeA002RequestDto
     * Dtoレスポンス：IfaSbiWrapManageFeeA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaSbiWrapManageFeeDisplayA002")
    public String displayA002(@RequestBody IfaSbiWrapManageFeeA002ApiRequest apiReq) throws Exception {
        
        IfaSbiWrapManageFeeA002RequestDto appReq = new IfaSbiWrapManageFeeA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaSbiWrapManageFeeA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSbiWrapManageFeeService",
                "displayA002", new TypeReference<DataList<IfaSbiWrapManageFeeA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaSbiWrapManageFeeA002ApiResponse> apiRes = new DataList<IfaSbiWrapManageFeeA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        if (apiRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
            setStatusAndMessageToDataList(apiRes, false);
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaSbiWrapManageFeeCsvOutputA003
     * アクションID：A003
     * アクション名：CSV出力
     * APIリクエスト：IfaSbiWrapManageFeeA003ApiRequest
     * Apiレスポンス：IfaSbiWrapManageFeeA003ApiResponse
     * Dtoリクエスト：IfaSbiWrapManageFeeA003RequestDto
     * Dtoレスポンス：IfaSbiWrapManageFeeA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/commFee/ifaSbiWrapManageFeeCsvOutputA003")
    public String csvOutputA003(@RequestBody IfaSbiWrapManageFeeA003ApiRequest apiReq) throws Exception {
        
        IfaSbiWrapManageFeeA003RequestDto appReq = new IfaSbiWrapManageFeeA003RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaSbiWrapManageFeeA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSbiWrapManageFeeService",
                "csvOutputA003", new TypeReference<DataList<IfaSbiWrapManageFeeA003ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaSbiWrapManageFeeA003ApiResponse> apiRes = new DataList<IfaSbiWrapManageFeeA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        if (apiRes.getErrorLevel() != ErrorLevel.FATAL.getId()) {
            setStatusAndMessageToDataList(apiRes, true);
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/commFee/ifaSbiWrapManageFeeCsvDownloadA003
     * アクションID：A003
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaSbiWrapManageFeeA003ApiRequest
     * Apiレスポンス：IfaSbiWrapManageFeeA003ApiResponse
     * Dtoリクエスト：IfaSbiWrapManageFeeA003RequestDto
     * Dtoレスポンス：IfaSbiWrapManageFeeA003ResponseDto
     *
     * @param apiReq リクエスト
     * @exception exception システムエラー
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/commFee/ifaSbiWrapManageFeeCsvDownloadA003")
    public void csvDownloadA003(@RequestBody IfaSbiWrapManageFeeA003ApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("SBIラップ管理報酬"),
                IfaCommonUtil.getUserAccount());
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
