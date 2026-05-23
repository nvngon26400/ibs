package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginRepayOrderInputA016ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA004ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA016ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputA016ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginRepayOrderInputApiRepayPositionDetail;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 2024/04/08 新規作成
 *
 * @author 池亀緑
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-04_1", screenNumber = "")
public class IfaMarginRepayOrderInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginRepayOrderInputA001ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderInputA001ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderInputA001RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderInputA001ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderInputInitializeA001")
    public String initializeA001(@RequestBody IfaMarginRepayOrderInputA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginRepayOrderInputA001RequestDto appReq = new IfaMarginRepayOrderInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginRepayOrderInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderInputService", "initializeA001",
                new TypeReference<DataList<IfaMarginRepayOrderInputA001ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginRepayOrderInputA001ApiResponse> apiRes = new DataList<IfaMarginRepayOrderInputA001ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderInputA001ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0).setRepayPositionDetailList(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRepayPositionDetailList()),
                            new TypeReference<List<IfaMarginRepayOrderInputApiRepayPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderInputMarketSelectionA002
     * アクションID：A002
     * アクション名：市場選択
     * APIリクエスト：IfaMarginRepayOrderInputA002ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderInputA002ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderInputA002RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderInputA002ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderInputMarketSelectionA002")
    public String marketSelectionA002(@RequestBody IfaMarginRepayOrderInputA002ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginRepayOrderInputA002RequestDto appReq = new IfaMarginRepayOrderInputA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginRepayOrderInputA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderInputService", "marketSelectionA002",
                new TypeReference<DataList<IfaMarginRepayOrderInputA002ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginRepayOrderInputA002ApiResponse> apiRes = new DataList<IfaMarginRepayOrderInputA002ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderInputA002ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0).setRepayPositionDetailList(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRepayPositionDetailList()),
                            new TypeReference<List<IfaMarginRepayOrderInputApiRepayPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderInputUpdateA004
     * アクションID：A004
     * アクション名：更新
     * APIリクエスト：IfaMarginRepayOrderInputA004ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderInputA004ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderInputA004RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderInputA004ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderInputUpdateA004")
    public String updateA004(@RequestBody IfaMarginRepayOrderInputA004ApiRequest apiReq) throws Exception {
        
        IfaMarginRepayOrderInputA004RequestDto appReq = new IfaMarginRepayOrderInputA004RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginRepayOrderInputA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderInputService", "updateA004",
                new TypeReference<DataList<IfaMarginRepayOrderInputA004ResponseDto>>() {
                }, appReq);
        
        DataList<IfaMarginRepayOrderInputA004ApiResponse> apiRes = new DataList<IfaMarginRepayOrderInputA004ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderInputA004ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0).setRepayPositionDetailList(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRepayPositionDetailList()),
                            new TypeReference<List<IfaMarginRepayOrderInputApiRepayPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginRepayOrderInputOrderConfirmA016
     * アクションID：A016
     * アクション名：注文確認
     * APIリクエスト：IfaMarginRepayOrderInputA016ApiRequest
     * Apiレスポンス：IfaMarginRepayOrderInputA016ApiResponse
     * Dtoリクエスト：IfaMarginRepayOrderInputA016RequestDto
     * Dtoレスポンス：IfaMarginRepayOrderInputA016ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping(value = "/brokerageMenu/customerMenu/ifaMarginRepayOrderInputOrderConfirmA016")
    public String orderConfirmA016(@RequestBody IfaMarginRepayOrderInputA016ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginRepayOrderInputA016RequestDto appReq = new IfaMarginRepayOrderInputA016RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginRepayOrderInputA016ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginRepayOrderInputService", "orderConfirmA016",
                new TypeReference<DataList<IfaMarginRepayOrderInputA016ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginRepayOrderInputA016ApiResponse> apiRes = new DataList<IfaMarginRepayOrderInputA016ApiResponse>();
        
        // 共通項目をコピー
        apiRes.setErrorLevel(appRes.getErrorLevel());
        apiRes.setMessage(appRes.getMessage());
        apiRes.setRequestedTime(appRes.getRequestedTime());
        apiRes.setMaxRownum(appRes.getMaxRownum());
        apiRes.setTitle(appRes.getTitle());
        apiRes.setTotalSize(appRes.getTotalSize());
        apiRes.setReturnCode(appRes.getReturnCode());
        
        // DtoレスポンスのDataListが空でない場合は取得し、JSONを経由して型パラメータを指定したListをApiレスポンスのDataListに設定する
        if (CollectionUtils.isNotEmpty(appRes.getDataList())) {
            apiRes.setDataList(jc.toObject(jc.toString(appRes.getDataList()),
                    new TypeReference<List<IfaMarginRepayOrderInputA016ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0).getRequest().setRepayPositionDetail(
                    jc.toObject(jc.toString(appRes.getDataList().get(0).getRequest().getRepayPositionDetail()),
                            new TypeReference<List<IfaMarginRepayOrderInputApiRepayPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
}
