package com.sbisec.helios.gw.common.brokerMaintenance.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaForeignStockBrandSearchPopupA002ApiRequest;
import com.sbisec.helios.gw.common.brokerMaintenance.form.IfaForeignStockBrandSearchPopupA002ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB07-04
 * 画面名：外株銘柄検索ポップアップ
 * @author SCSK
 *
 * 2024/01/09 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "COMMON", id = "SUB07-04", screenNumber = "", ignoreCheck = true)
public class IfaForeignStockBrandSearchPopupController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * 
     * アクセス：/common/brokerMaintenance/ifaForeignStockBrandSearchPopupSearchA002
     * アクションID：A002
     * アクション名：検索
     * APIリクエスト：IfaForeignStockBrandSearchPopupA002ApiRequest
     * APIレスポンス：IfaForeignStockBrandSearchPopupA002ApiResponse
     * DTOリクエスト：IfaForeignStockBrandSearchPopupA002RequestDto
     * DTOレスポンス：IfaForeignStockBrandSearchPopupA002ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/common/brokerMaintenance/ifaForeignStockBrandSearchPopupSearchA002")
    public String searchA002(@RequestBody IfaForeignStockBrandSearchPopupA002ApiRequest apiReq) throws Exception {
        
        IfaForeignStockBrandSearchPopupA002RequestDto appReq = new IfaForeignStockBrandSearchPopupA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaForeignStockBrandSearchPopupA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaForeignStockBrandSearchPopupService", "searchA002",
                new TypeReference<DataList<IfaForeignStockBrandSearchPopupA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaForeignStockBrandSearchPopupA002ApiResponse> apiRes = new DataList<IfaForeignStockBrandSearchPopupA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
