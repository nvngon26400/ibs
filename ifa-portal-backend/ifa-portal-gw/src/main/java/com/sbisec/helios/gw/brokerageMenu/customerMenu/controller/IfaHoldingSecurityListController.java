package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA013ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaHoldingSecurityListA019RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegularAccumulationA019RequestDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA012ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA012ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA013ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA013ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA019ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaHoldingSecurityListA019ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *
 *     2023/10/11 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_010201-01", screenNumber = "")
public class IfaHoldingSecurityListController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaHoldingSecurityListInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：brokerageMenu.customerMenuA001ApiRequest
     * Apiレスポンス：brokerageMenu.customerMenuA001ApiResponse
     * Dtoリクエスト：brokerageMenu.customerMenuA001DtoRequest
     * Dtoレスポンス：brokerageMenu.customerMenuA001DtoResponse
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaHoldingSecurityListInitializeA001")
    public String initializeA001(@RequestBody IfaHoldingSecurityListA001ApiRequest apiReq) throws Exception {
        
        IfaHoldingSecurityListA001RequestDto appReq = new IfaHoldingSecurityListA001RequestDto();
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaHoldingSecurityListA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaHoldingSecurityListService", "initializeA001",
                new TypeReference<DataList<IfaHoldingSecurityListA001ResponseDto>>() {
                }, appReq);
        
        DataList<IfaHoldingSecurityListA001ApiResponse> apiRes = new DataList<IfaHoldingSecurityListA001ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    @PostMapping("/brokerageMenu/customerMenu/ifaHoldingSecurityListDomesticStockTradingA003")
    public String domesticStockTradingA003(@RequestBody IfaHoldingSecurityListA003ApiRequest apiReq) throws Exception {
        
        IfaHoldingSecurityListA003RequestDto appReq = new IfaHoldingSecurityListA003RequestDto();
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<?> appRes = ApiRequestUtil.invoke(
                "cmpIfaHoldingSecurityListService", "domesticStockTradingA003",
                new TypeReference<DataList<?>>() {
                }, appReq);
        
        DataList<?> apiRes = new DataList<>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaHoldingSecurityListAccountSelectionA012
     * アクションID：A012
     * アクション名：口座選択
     * APIリクエスト：brokerageMenu.customerMenuA012ApiRequest
     * Apiレスポンス：brokerageMenu.customerMenuA012ApiResponse
     * Dtoリクエスト：brokerageMenu.customerMenuA012DtoRequest
     * Dtoレスポンス：brokerageMenu.customerMenuA012DtoResponse
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 口座選択処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaHoldingSecurityListAccountSelectionA012")
    public String accountSelectionA012(@RequestBody IfaHoldingSecurityListA012ApiRequest apiReq) throws Exception {
        
        IfaHoldingSecurityListA012RequestDto appReq = new IfaHoldingSecurityListA012RequestDto();
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaHoldingSecurityListA012ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaHoldingSecurityListService", "accountSelectionA012",
                new TypeReference<DataList<IfaHoldingSecurityListA012ResponseDto>>() {
                }, appReq);
        
        DataList<IfaHoldingSecurityListA012ApiResponse> apiRes = new DataList<IfaHoldingSecurityListA012ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/cbrokerageMenu.customerMenu/IfaHoldingSecurityListproductselection
     * アクションID：A013
     * アクション名：商品選択
     * APIリクエスト：IfaHoldingSecurityListA013ApiRequest
     * Apiレスポンス：IfaHoldingSecurityListA013ApiResponse
     * Dtoリクエスト：IfaHoldingSecurityListA013DtoRequest
     * Dtoレスポンス：IfaHoldingSecurityListA013DtoResponse
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 商品選択処理で例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaHoldingSecurityListProductSelectionA013")
    public String productSelectionA013(@RequestBody IfaHoldingSecurityListA013ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        
        IfaHoldingSecurityListA013RequestDto appReq = new IfaHoldingSecurityListA013RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaHoldingSecurityListA013ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaHoldingSecurityListService", "productSelectionA013",
                new TypeReference<DataList<IfaHoldingSecurityListA013ResponseDto>>() {
                }, appReq);
        
        DataList<IfaHoldingSecurityListA013ApiResponse> apiRes = new DataList<IfaHoldingSecurityListA013ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
     * アクセス：/brokerageMenu.customerMenu/IfaDomesticMutualFundDocAccumulationSettingsA019
     * アクションID：A019
     * アクション名：投信積立設定入力
     * APIリクエスト：IfaHoldingSecurityListA019ApiRequest
     * Apiレスポンス：IfaHoldingSecurityListA019ApiResponse
     * Dtoリクエスト：IfaHoldingSecurityListA019DtoRequest
     * Dtoレスポンス：IfaHoldingSecurityListA019DtoResponse
     *
     * @param apiReq リクエストパラメタ
     * @return レスポンスパラメタ
     * @exception Exception 選択された投信銘柄の目論見書未閲覧した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaDomesticMutualFundDocAccumulationSettingsA019")
    public String IfaDomesticMutualFundDocAccumulationSettingsA019(@RequestBody IfaHoldingSecurityListA019ApiRequest apiReq) throws Exception {

        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaHoldingSecurityListA019RequestDto appReq = new IfaHoldingSecurityListA019RequestDto();

        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);

        DataList<IfaRegularAccumulationA019RequestDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaHoldingSecurityListService", "fundAccumulationA019",
                new TypeReference<DataList<IfaRegularAccumulationA019RequestDto>>() {
                }, appReq);

        DataList<IfaHoldingSecurityListA019ApiResponse> apiRes = new DataList<IfaHoldingSecurityListA019ApiResponse>();

        BeanUtils.copyProperties(apiRes, appRes);

        return jc.toString(apiRes);
    }


    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
