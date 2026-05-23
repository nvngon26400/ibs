package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaMutualFundPriceChangeBrandHoldingListCsvOut;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMutualFundPriceChangeBrandHoldingListA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMutualFundPriceChangeBrandHoldingListA004aApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMutualFundPriceChangeBrandHoldingListA004aApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaMutualFundPriceChangeBrandHoldingListA004bApiRequest;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020301_03-01
 * 画面名：投信基準価額変動の銘柄保有一覧
 * @author <author-name>
 *
 * 2024/04/12 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020301_03-01", screenNumber = "71")
public class IfaMutualFundPriceChangeBrandHoldingListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustome/ifaMutualFundPriceChangeBrandHoldingListDisplayA002ManagingContractList
     * アクションID：A002
     * アクション名：表示 顧客口座情報検索
     * APIリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002ApiRequest
     * Apiレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse
     * Dtoリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002DtoRequest
     * Dtoレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明のため現行から取得
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMutualFundPriceChangeBrandHoldingListDisplayA002")
    public String displayA002(@RequestBody IfaMutualFundPriceChangeBrandHoldingListA002ApiRequest apiReq)
            throws Exception {
        
        IfaMutualFundPriceChangeBrandHoldingListA002RequestDto appReq = new IfaMutualFundPriceChangeBrandHoldingListA002RequestDto();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 画面IDの取得
        ScreenId screenId = (ScreenId) this.getClass().getAnnotation(ScreenId.class);
        appReq.setScreenId(screenId.id());
        DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundPriceChangeBrandHoldingListService", "displayA002",
                new TypeReference<DataList<IfaMutualFundPriceChangeBrandHoldingListA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse> apiRes = new DataList<IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustomer/ifaMutualFundPriceChangeBrandHoldingListCsvOutputA004
     * アクションID：A004
     * アクション名：表示 CSV出力（CSV作成）
     * APIリクエスト：IfaMutualFundPriceChangeBrandHoldingListA004ApiRequest
     * APIレスポンス：IfaMutualFundPriceChangeBrandHoldingListA004ApiResponse
     * Dtoリクエスト：IfaMutualFundPriceChangeBrandHoldingListA004RequestDto
     * Dtoレスポンス：IfaMutualFundPriceChangeBrandHoldingListA004ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMutualFundPriceChangeBrandHoldingListCsvOutputA004a")
    public String csvOutputA004a(@RequestBody IfaMutualFundPriceChangeBrandHoldingListA004aApiRequest apiReq)
            throws Exception {
        
        IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto appReq = new IfaMutualFundPriceChangeBrandHoldingListA004aRequestDto();
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMutualFundPriceChangeBrandHoldingListService", "csvOutputA004a",
                new TypeReference<DataList<IfaMutualFundPriceChangeBrandHoldingListA004aResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaMutualFundPriceChangeBrandHoldingListA004aApiResponse> apiRes = new DataList<IfaMutualFundPriceChangeBrandHoldingListA004aApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/wholeCustome/ifaMutualFundPriceChangeBrandHoldingListDisplayA004
     * アクションID：A004
     * アクション名：表示 CSV出力（ダウンロード）
     * APIリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002ApiRequest
     * Apiレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002ApiResponse
     * Dtoリクエスト：IfaMutualFundPriceChangeBrandHoldingListA002DtoRequest
     * Dtoレスポンス：IfaMutualFundPriceChangeBrandHoldingListA002DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名不明
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMutualFundPriceChangeBrandHoldingListCsvOutputA004b")
    @ResponseFile
    public void csvOutputA004b(@RequestBody IfaMutualFundPriceChangeBrandHoldingListA004bApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("投信基準価額変動の銘柄保有一覧"),
                IfaCommonUtil.getUserAccount());
        
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaMutualFundPriceChangeBrandHoldingListCsvOut.getHeaders();
        
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
