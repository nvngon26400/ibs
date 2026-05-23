package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDeliverInOutDetailA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaDeliverInOutDetailCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDeliverInOutDetailA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDeliverInOutDetailA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDeliverInOutDetailA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDeliverInOutDetailA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDeliverInOutDetailA004DownloadApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0204-01
 * 画面名：入出庫明細
 * @author <author-name>
 *
 * 2024/04/03 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0204-01", screenNumber = "34")
public class IfaDeliverInOutDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaDeliverInOutDetailA002ApiRequest
     * Apiレスポンス：IfaDeliverInOutDetailA002ApiResponse
     * Dtoリクエスト：IfaDeliverInOutDetailA002DtoRequest
     * Dtoレスポンス：IfaDeliverInOutDetailA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailDisplayA002")
    public String displayA002(@RequestBody IfaDeliverInOutDetailA002ApiRequest apiReq) throws Exception {
        
        IfaDeliverInOutDetailA002RequestDto appReq = new IfaDeliverInOutDetailA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDeliverInOutDetailA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDeliverInOutDetailService",
                "displayA002", new TypeReference<DataList<IfaDeliverInOutDetailA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDeliverInOutDetailA002ApiResponse> apiRes = new DataList<IfaDeliverInOutDetailA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailCsvOutputA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaDeliverInOutDetailA004ApiRequest
     * Apiレスポンス：IfaDeliverInOutDetailA004ApiResponse
     * Dtoリクエスト：IfaDeliverInOutDetailA004DtoRequest
     * Dtoレスポンス：IfaDeliverInOutDetailA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailCsvOutputA004")
    public String csvOutputA004(@RequestBody IfaDeliverInOutDetailA004ApiRequest apiReq) throws Exception {
        
        IfaDeliverInOutDetailA004RequestDto appReq = new IfaDeliverInOutDetailA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaDeliverInOutDetailA004ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaDeliverInOutDetailService",
                "csvOutputA004", new TypeReference<DataList<IfaDeliverInOutDetailA004ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaDeliverInOutDetailA004ApiResponse> apiRes = new DataList<IfaDeliverInOutDetailA004ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailCsvDownloadA004
     * アクションID：A004
     * アクション名：CSVダウンロード
     * APIリクエスト：IfaDeliverInOutDetailA004ApiRequest
     * Apiレスポンス：IfaDeliverInOutDetailA004ApiResponse
     * Dtoリクエスト：IfaDeliverInOutDetailA004DtoRequest
     * Dtoレスポンス：IfaDeliverInOutDetailA004DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @ResponseFile
    @PostMapping("/brokerageMenu/wholeCustomer/ifaDeliverInOutDetailCsvDownloadA004")
    public void csvDownloadA004(@RequestBody IfaDeliverInOutDetailA004DownloadApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("入出庫明細"),
                IfaCommonUtil.getUserAccount());
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaDeliverInOutDetailCsvOut.getHeaders();
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
