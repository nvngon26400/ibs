package com.sbisec.helios.gw.brokerageMenu.jointSubscript.controller;

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
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.util.IfaJointSubscriptTradeListCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.jointSubscript.form.IfaJointSubscriptTradeListA003bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

/**
 * 画面ID：SUB0206_02-01
 * 画面名：共同募集　取引検索
 *
 * @author SBIチョウ
   2024/12/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0206_02-01", screenNumber = "16")
public class IfaJointSubscriptTradeListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJointSubscriptTradeListController.class);
    
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "共募取引検索";
    
    @Override
    protected String getCsvHeader() {
        
        return IfaJointSubscriptTradeListCsvOut.getHeaders();
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaJointSubscriptTradeListA001ApiRequest
     * Apiレスポンス：IfaJointSubscriptTradeListA001ApiResponse
     * Dtoリクエスト：IfaJointSubscriptTradeListA001RequestDto
     * Dtoレスポンス：IfaJointSubscriptTradeListA001ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListInitializeA001")
    public String initializeA001(@RequestBody IfaJointSubscriptTradeListA001ApiRequest apiReq) throws Exception {
        
        IfaJointSubscriptTradeListA001RequestDto appReq = new IfaJointSubscriptTradeListA001RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaJointSubscriptTradeListA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTradelistService",
                "initializeA001", new TypeReference<DataList<IfaJointSubscriptTradeListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaJointSubscriptTradeListA001ApiResponse> apiRes = new DataList<IfaJointSubscriptTradeListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaJointSubscriptTradeListA002ApiRequest
     * Apiレスポンス：IfaJointSubscriptTradeListA002ApiResponse
     * Dtoリクエスト：IfaTradeHistoryA002RequestDto
     * Dtoレスポンス：IfaJointSubscriptTradeListA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListDisplayA002")
    public String displayA002(@RequestBody IfaJointSubscriptTradeListA002ApiRequest apiReq) throws Exception {
        
        IfaJointSubscriptTradeListA002RequestDto appReq = new IfaJointSubscriptTradeListA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaJointSubscriptTradeListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTradelistService",
                "displayA002", new TypeReference<DataList<IfaJointSubscriptTradeListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaJointSubscriptTradeListA002ApiResponse> apiRes = new DataList<IfaJointSubscriptTradeListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListCsvOutputA003
     * アクションID：A003
     * アクション名：CSV出力
     * APIリクエスト：IfaJointSubscriptTradeListA003ApiRequest
     * Apiレスポンス：IfaJointSubscriptTradeListA003ApiResponse
     * Dtoリクエスト：IfaJointSubscriptTradeListA003RequestDto
     * Dtoレスポンス：IfaJointSubscriptTradeListA003ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListCsvOutputA003")
    public String csvOutputA003(@RequestBody IfaJointSubscriptTradeListA003ApiRequest apiReq) throws Exception {

        IfaJointSubscriptTradeListA003RequestDto appReq = new IfaJointSubscriptTradeListA003RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaJointSubscriptTradeListA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaJointSubscriptTradelistService",
                "csvOutputA003", new TypeReference<DataList<IfaJointSubscriptTradeListA003ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaJointSubscriptTradeListA003ApiResponse> apiRes = new DataList<IfaJointSubscriptTradeListA003ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * 
     * アクセス：/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListCsvDownloadA003
     * アクションID：A003
     * アクション名：CSV出力
     * APIリクエスト：IfaJointSubscriptTradeListA003bApiRequest
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/jointSubscript/ifaJointSubscriptTradeListCsvDownloadA003")
    public void csvOutputA003b(@RequestBody IfaJointSubscriptTradeListA003bApiRequest apiReq, HttpServletResponse response)
            throws Exception {
        
        long start = System.currentTimeMillis();
        LOGGER.debug("IfaJointSubscriptTradeListController.csvOutputA003b >> {}", hashCode());
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        // Star ファイル ダウンロード CRLF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME),
                userAccount, null);
        
        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

