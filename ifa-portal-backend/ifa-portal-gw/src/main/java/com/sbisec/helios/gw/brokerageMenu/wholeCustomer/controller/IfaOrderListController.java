package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaOrderListA005aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaOrderListCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA005aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA005aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaOrderListA005bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 *
 * @author BASE 李
 *
 2024/03/30 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0101-01", screenNumber = "42")
public class IfaOrderListController extends BaseController {

    private JsonConverter jc = JsonConverter.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOrderListController.class);
    /** ダウンロードファイルの接頭語 */
    private static final String STAR_UPLOAD_CSV_FILE_NAME = "注文一覧";
    
    @Override
    protected String getCsvHeader(String pattern) {
        return new IfaOrderListCsvOut(null).getCsvHeader(pattern);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaOrderListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaOrderListA001ApiRequest
     * Apiレスポンス：IfaOrderListA001ApiResponse
     * Dtoリクエスト：IfaOrderListA001RequestDto
     * Dtoレスポンス：IfaOrderListA001ResponseDto
     *
     * @param apiReq リクエストmodel
     * @return apiRes レスポンス
     * @exception e システムエラー
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaOrderListInitializeA001")
    public String initializeA001(@RequestBody IfaOrderListA001ApiRequest apiReq)throws Exception {

        IfaOrderListA001RequestDto appReq = new IfaOrderListA001RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOrderListA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOrderListService",
                "initializeA001", new TypeReference<DataList<IfaOrderListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOrderListA001ApiResponse> apiRes = new DataList<IfaOrderListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaOrderListDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaOrderListA002ApiRequest
     * Apiレスポンス：IfaOrderListA002ApiResponse
     * Dtoリクエスト：IfaOrderListA002RequestDto
     * Dtoレスポンス：IfaOrderListA002ResponseDto
     *
     * @param apiReq リクエストmodel
     * @return apiRes レスポンス
     * @exception e システムエラー
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaOrderListDisplayA002")
    public String displayA002(@RequestBody IfaOrderListA002ApiRequest apiReq)throws Exception {

        IfaOrderListA002RequestDto appReq = new IfaOrderListA002RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOrderListA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOrderListService",
                "displayA002", new TypeReference<DataList<IfaOrderListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaOrderListA002ApiResponse> apiRes = new DataList<IfaOrderListA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaOrderListCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaOrderListA005ApiRequest
     * Apiレスポンス：IfaOrderListA005ApiResponse
     * Dtoリクエスト：IfaOrderListA005RequestDto
     * Dtoレスポンス：IfaOrderListA005ResponseDto
     *
     * @param apiReq リクエストmodel
     * @return apiRes レスポンス
     * @exception e システムエラー
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaOrderListCsvOutputA005a")
    public String csvOutputA005a(@RequestBody IfaOrderListA005aApiRequest apiReq)throws Exception {

        IfaOrderListA005aRequestDto appReq = new IfaOrderListA005aRequestDto();
        
        String sessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaOrderListA005aResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaOrderListService",
                "csvOutputA005", new TypeReference<DataList<IfaOrderListA005aResponseDto>>() {
                }, appReq, sessionId);
        
        DataList<IfaOrderListA005aApiResponse> apiRes = new DataList<IfaOrderListA005aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaOrderListCsvOutputA005
     * アクションID：A005
     * アクション名：CSV出力
     * APIリクエスト：IfaOrderListA005ApiRequest
     * Apiレスポンス：IfaOrderListA005ApiResponse
     * Dtoリクエスト：IfaOrderListA005RequestDto
     * Dtoレスポンス：IfaOrderListA005ResponseDto
     *
     * @param apiReq リクエストmodel
     * @param response servletレスポンス
     * @exception e システムエラー
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaOrderListCsvOutputA005b")
    public void csvOutputA005b(@RequestBody IfaOrderListA005bApiRequest apiReq, HttpServletResponse response) throws Exception {

        long start = System.currentTimeMillis();
        LOGGER.debug("IfaBbApplyListController.csvOutputA004b >> {}", hashCode());

        UserAccount userAccount = IfaCommonUtil.getUserAccount();

        // Star ファイル ダウンロード CRLF
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName(STAR_UPLOAD_CSV_FILE_NAME), userAccount, apiReq.getPattern());

        LOGGER.debug("cost -> {}", (System.currentTimeMillis() - start));
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
}

