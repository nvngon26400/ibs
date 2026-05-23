package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.controller;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.util.IfaDepositWithdrawDetailCsvOut;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDepositWithdrawDetailA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDepositWithdrawDetailA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDepositWithdrawDetailA004ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form.IfaDepositWithdrawDetailA004ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 * @author
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0203-01", screenNumber = "45")
public class IfaDepositWithdrawDetailController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaDepositWithdrawDetailDisplayA002
     * アクションID：A002
     * アクション名：表示
     * APIリクエスト：IfaDepositWithdrawDetailA002ApiRequest
     * Apiレスポンス：IfaDepositWithdrawDetailA002ApiResponse
     * Dtoリクエスト：IfaDepositWithdrawDetailA002RequestDto
     * Dtoレスポンス：IfaDepositWithdrawDetailA002ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaDepositWithdrawDetailDisplayA002")
    public String displayA002(@RequestBody IfaDepositWithdrawDetailA002ApiRequest apiReq) throws Exception {
        
        IfaDepositWithdrawDetailA002RequestDto appReq = new IfaDepositWithdrawDetailA002RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        DataList<IfaDepositWithdrawDetailA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDepositWithdrawDetailService", "displayA002",
                new TypeReference<DataList<IfaDepositWithdrawDetailA002ResponseDto>>() {
                }, appReq);
        
        DataList<IfaDepositWithdrawDetailA002ApiResponse> apiRes = new DataList<IfaDepositWithdrawDetailA002ApiResponse>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaDepositWithdrawDetailCsvOutputA004
     * アクションID：A004
     * アクション名：CSV出力
     * APIリクエスト：IfaDepositWithdrawDetailA004ApiRequest
     * Apiレスポンス：IfaDepositWithdrawDetailA004ApiResponse
     * Dtoリクエスト：IfaDepositWithdrawDetailA004RequestDto
     * Dtoレスポンス：IfaDepositWithdrawDetailA004ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/wholeCustomer/ifaDepositWithdrawDetailCsvOutputA004")
    public String csvOutputA004(@RequestBody IfaDepositWithdrawDetailA004ApiRequest apiReq) throws Exception {
        
        IfaDepositWithdrawDetailA004RequestDto appReq = new IfaDepositWithdrawDetailA004RequestDto();
        
        // Beanコピー用共通部品。
        BeanUtils.copyProperties(appReq, apiReq);
        
        String fwSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
        DataList<IfaDepositWithdrawDetailA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaDepositWithdrawDetailService", "csvOutputA004",
                new TypeReference<DataList<IfaDepositWithdrawDetailA004ResponseDto>>() {
                }, appReq, fwSessionId);
        
        DataList<IfaDepositWithdrawDetailA004ApiResponse> apiRes = new DataList<IfaDepositWithdrawDetailA004ApiResponse>();
        
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
    @PostMapping("/brokerageMenu/wholeCustomer/ifaDepositWithdrawDetailCsvDownloadA004")
    public void csvDownloadA004(@RequestBody IfaDepositWithdrawDetailA004ApiRequest apiReq,
            HttpServletResponse response) throws Exception {
        
        // 顧客共通情報の取得(redisからの取得)
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // 共通のダウンロード処理を実施
        doDownLoadCsvFile(response, apiReq.getCsvDownloadFile(), getCsvFileName("入出金明細"),
                IfaCommonUtil.getUserAccount());
    }
    
    @Override
    protected String getCsvHeader() {
        
        return IfaDepositWithdrawDetailCsvOut.getHeaders();
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
