package com.sbisec.helios.gw.brokerageMenu.customerMenu.controller;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMarginMassRepayInputA010ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA010ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputA010ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.customerMenu.form.IfaMarginMassRepayInputApiPositionDetail;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0212-05_1
 * 画面名：信用一括返済入力
 * 2024/04/15 新規作成
 *
 * @author 池亀緑
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0212-05_1", screenNumber = "")
public class IfaMarginMassRepayInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginMassRepayInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaMarginMassRepayInputA001ApiRequest
     * Apiレスポンス：IfaMarginMassRepayInputA001ApiResponse
     * Dtoリクエスト：IfaMarginMassRepayInputA001RequestDto
     * Dtoレスポンス：IfaMarginMassRepayInputA001ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginMassRepayInputInitializeA001")
    public String initializeA001(@RequestBody IfaMarginMassRepayInputA001ApiRequest apiReq) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        IfaMarginMassRepayInputA001RequestDto appReq = new IfaMarginMassRepayInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        DataList<IfaMarginMassRepayInputA001ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginMassRepayInputService", "initializeA001",
                new TypeReference<DataList<IfaMarginMassRepayInputA001ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginMassRepayInputA001ApiResponse> apiRes = new DataList<IfaMarginMassRepayInputA001ApiResponse>();
        
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
                    new TypeReference<List<IfaMarginMassRepayInputA001ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0)
                    .setPositionDetailList(jc.toObject(jc.toString(appRes.getDataList().get(0).getPositionDetailList()),
                            new TypeReference<List<IfaMarginMassRepayInputApiPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginMassRepayInputRepaymentOrderA003
     * アクションID：A003
     * アクション名：返済順序
     * APIリクエスト：IfaMarginMassRepayInputA003ApiRequest
     * Apiレスポンス：IfaMarginMassRepayInputA003ApiResponse
     * Dtoリクエスト：IfaMarginMassRepayInputA003RequestDto
     * Dtoレスポンス：IfaMarginMassRepayInputA003ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginMassRepayInputRepaymentOrderA003")
    public String repaymentOrderA003(@RequestBody IfaMarginMassRepayInputA003ApiRequest apiReq) throws Exception {
        
        IfaMarginMassRepayInputA003RequestDto appReq = new IfaMarginMassRepayInputA003RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginMassRepayInputA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginMassRepayInputService", "repaymentOrderA003",
                new TypeReference<DataList<IfaMarginMassRepayInputA003ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginMassRepayInputA003ApiResponse> apiRes = new DataList<IfaMarginMassRepayInputA003ApiResponse>();
        
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
                    new TypeReference<List<IfaMarginMassRepayInputA003ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0)
                    .setPositionDetailList(jc.toObject(jc.toString(appRes.getDataList().get(0).getPositionDetailList()),
                            new TypeReference<List<IfaMarginMassRepayInputApiPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/customerMenu/ifaMarginMassRepayInputinitializeReturnA010
     * アクションID：A010
     * アクション名：初期化（戻り）
     * APIリクエスト：IfaMarginMassRepayInputA010ApiRequest
     * Apiレスポンス：IfaMarginMassRepayInputA010ApiResponse
     * Dtoリクエスト：IfaMarginMassRepayInputA010RequestDto
     * Dtoレスポンス：IfaMarginMassRepayInputA010ResponseDto
     *
     * @param apiReq リクエストDto
     * @return APIレスポンス
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaMarginMassRepayInputInitializeReturnA010")
    public String initializeReturnA010(@RequestBody IfaMarginMassRepayInputA010ApiRequest apiReq) throws Exception {
        
        IfaMarginMassRepayInputA010RequestDto appReq = new IfaMarginMassRepayInputA010RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        DataList<IfaMarginMassRepayInputA010ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaMarginMassRepayInputService", "initializeReturnA010",
                new TypeReference<DataList<IfaMarginMassRepayInputA010ResponseDto>>() {
                }, appReq);
        DataList<IfaMarginMassRepayInputA010ApiResponse> apiRes = new DataList<IfaMarginMassRepayInputA010ApiResponse>();
        
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
                    new TypeReference<List<IfaMarginMassRepayInputA010ApiResponse>>() {
                    }));
            
            // 返済建玉明細もListであるため、同様にJSONを経由して型パラメータを指定したListを設定する
            apiRes.getDataList().get(0)
                    .setPositionDetailList(jc.toObject(jc.toString(appRes.getDataList().get(0).getPositionDetailList()),
                            new TypeReference<List<IfaMarginMassRepayInputApiPositionDetail>>() {
                            }));
        }
        
        return jc.toString(apiRes);
    }
}
