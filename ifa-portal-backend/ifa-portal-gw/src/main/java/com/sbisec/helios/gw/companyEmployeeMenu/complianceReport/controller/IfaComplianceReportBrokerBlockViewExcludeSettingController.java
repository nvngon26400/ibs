package com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.beanutils.BeanUtils;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA002ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA002ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA003ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA003ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiResponse;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA005ApiRequest;
import com.sbisec.helios.gw.companyEmployeeMenu.complianceReport.form.IfaComplianceReportBrokerBlockViewExcludeSettingA005ApiResponse;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2024/03/01 新規作成
 *
 * @author SCSK 江口
 * 
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0505_03-01", screenNumber = "")
public class IfaComplianceReportBrokerBlockViewExcludeSettingController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingAllBrokerNameDisplayA002
     * アクションID：A002
     * アクション名：全仲介業者名表示
     * APIリクエスト：companyEmployeeMenu.complianceReportA002ApiRequest
     * Apiレスポンス：companyEmployeeMenu.complianceReportA002ApiResponse
     * Dtoリクエスト：companyEmployeeMenu.complianceReportA002RequestDto
     * Dtoレスポンス：companyEmployeeMenu.complianceReportA002ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 全仲介業者名と対応する閲覧要否情報(JSON)
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingAllBrokerNameDisplayA002")
    public String allBrokerNameDisplayA002(
            @RequestBody IfaComplianceReportBrokerBlockViewExcludeSettingA002ApiRequest apiReq) throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto appReq = new IfaComplianceReportBrokerBlockViewExcludeSettingA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportBrokerBlockViewExcludeSettingService",
                "allBrokerNameDisplayA002",
                new TypeReference<DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ApiResponse> apiRes
                = new DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
        
    }
    
    /**
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingSearchDisplayA003
     * アクションID：A003
     * アクション名：検索表示
     * APIリクエスト：complianceReportA003ApiRequest
     * Apiレスポンス：complianceReportA003ApiResponse
     * Dtoリクエスト：complianceReportA003RequestDto
     * Dtoレスポンス：complianceReportA003ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 検索条件を満たす、仲介業者名と対応する閲覧要否情報(JSON)
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingSearchDisplayA003")
    public String searchDisplayA003(@RequestBody IfaComplianceReportBrokerBlockViewExcludeSettingA003ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto appReq = new IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportBrokerBlockViewExcludeSettingService",
                "searchDisplayA003",
                new TypeReference<DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ApiResponse> apiRes = new DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
        
    }
    
    /**
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingRegisterA004
     * アクションID：A004
     * アクション名：登録
     * APIリクエスト：complianceReportA004ApiRequest
     * Apiレスポンス：complianceReportA004ApiResponse
     * Dtoリクエスト：complianceReportA004RequestDto
     * Dtoレスポンス：complianceReportA004ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 登録処理の成否(JSON)
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingRegisterA004")
    public String registerA004(@RequestBody IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto appReq = new IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportBrokerBlockViewExcludeSettingService",
                "registerA004",
                new TypeReference<DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiResponse> apiRes = new DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA004ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
        
    }
    
    /**
     * アクセス：/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingRegistrationCancellationA005
     * アクションID：A005
     * アクション名：登録解除
     * APIリクエスト：complianceReportA005ApiRequest
     * Apiレスポンス：complianceReportA005ApiResponse
     * Dtoリクエスト：complianceReportA005RequestDto
     * Dtoレスポンス：complianceReportA005ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return 削除処理の成否(JSON)
     * @exception Exception SQLExceptionやIFARuntimeExceptionなど
     */
    @PostMapping("/companyEmployeeMenu/complianceReport/ifaComplianceReportBrokerBlockViewExcludeSettingRegistrationCancellationA005")
    public String registrationCancellationA005(
            @RequestBody IfaComplianceReportBrokerBlockViewExcludeSettingA005ApiRequest apiReq) throws Exception {
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto appReq = new IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto> appRes = ApiRequestUtil.invoke(
                "cmpIfaComplianceReportBrokerBlockViewExcludeSettingService",
                "registrationCancellationA005",
                new TypeReference<DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ResponseDto>>() { },
                appReq
        );
        
        // サービスのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ApiResponse> apiRes = new DataList<IfaComplianceReportBrokerBlockViewExcludeSettingA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
        
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
        
    }
}
