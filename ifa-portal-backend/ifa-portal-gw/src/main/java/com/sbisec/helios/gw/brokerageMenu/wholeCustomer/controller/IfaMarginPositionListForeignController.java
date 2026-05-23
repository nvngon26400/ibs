package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMarginPositionListForeignCsvOut;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA002aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA002aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA002bApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA002bApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA006aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA006aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMarginPositionListForeignA006bApiRequest;

/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 *
 * @author 島崎聡士 2023/11/30 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0303-01", screenNumber = "72", ignoreCheck = true)
public class IfaMarginPositionListForeignController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignDisplayA002a
     * アクションID：A002
     * アクション名：表示 顧客口座情報検索
     * APIリクエスト：IfaMarginPositionListForeignA002aApiRequest
     * Apiレスポンス：IfaMarginPositionListForeignA002ApiResponse
     * Dtoリクエスト：IfaMarginPositionListForeignA002aDtoRequest
     * Dtoレスポンス：IfaMarginPositionListForeignA002aDtoResponse
     *
     * @param apiReq IfaMarginPositionListForeignA002aApiRequest
     * @return apiRes
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignDisplayA002a")
    public String displayA002a(@RequestBody IfaMarginPositionListForeignA002aApiRequest apiReq)
            throws Exception {
        
        IfaMarginPositionListForeignA002aDtoRequest appReq = new IfaMarginPositionListForeignA002aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginPositionListForeignA002aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListForeignService", "displayA002a",
                new TypeReference<DataList<IfaMarginPositionListForeignA002aDtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginPositionListForeignA002aApiResponse> apiRes =
                new DataList<IfaMarginPositionListForeignA002aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignDisplayA002b
     * アクションID：A002
     * アクション名：表示 信用建玉情報検索
     * APIリクエスト：IfaMarginPositionListForeignA002bApiRequest
     * Apiレスポンス：IfaMarginPositionListForeignA002bApiResponse
     * Dtoリクエスト：IfaMarginPositionListForeignA002bDtoRequest
     * Dtoレスポンス：IfaMarginPositionListForeignA002bDtoResponse
     *
     * @param apiReq IfaMarginPositionListDomesticA002bApiRequest
     * @return apiRes
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignDisplayA002b")
    public String displayA002b(@RequestBody IfaMarginPositionListForeignA002bApiRequest apiReq)
            throws Exception {
        
        IfaMarginPositionListForeignA002bDtoRequest appReq = new IfaMarginPositionListForeignA002bDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginPositionListForeignA002bDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListForeignService", "displayA002b",
                new TypeReference<DataList<IfaMarginPositionListForeignA002bDtoResponse>>() {
                }, appReq);
        
        DataList<IfaMarginPositionListForeignA002bApiResponse> apiRes =
                new DataList<IfaMarginPositionListForeignA002bApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006a
     * アクションID：A006
     * アクション名：CSV出力
     * APIリクエスト：IfaMarginPositionListForeignA006aApiRequest
     * Apiレスポンス：IfaMarginPositionListForeignA006ApiResponse
     * Dtoリクエスト：IfaMarginPositionListForeignA006DtoRequest
     * Dtoレスポンス：IfaMarginPositionListForeignA006DtoResponse
     *
     * @param apiReq IfaMarginPositionListForeignA006aApiRequest
     * @return apiRes
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006a")
    public String csvOutputA006a(@RequestBody IfaMarginPositionListForeignA006aApiRequest apiReq) throws Exception {
        
        IfaMarginPositionListForeignA006aDtoRequest appReq = new IfaMarginPositionListForeignA006aDtoRequest();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaMarginPositionListForeignA006aDtoResponse> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginPositionListForeignService", "csvOutputA006",
                new TypeReference<DataList<IfaMarginPositionListForeignA006aDtoResponse>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaMarginPositionListForeignA006aApiResponse> apiRes =
                new DataList<IfaMarginPositionListForeignA006aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006b
     * アクションID：A006
     * アクション名：表示 CSV出力（ダウンロード）
     * APIリクエスト：IfaMarginPositionListForeignA006bApiRequest
     *
     * @param apiReq IfaMarginPositionListForeignA006bApiRequest
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006b")
    @ResponseFile
    public void csvOutputA006b(@RequestBody IfaMarginPositionListForeignA006bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(),
                getCsvFileName("信用建玉一覧（米株）"), IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaMarginPositionListForeignCsvOut.getHeaders();
        
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
