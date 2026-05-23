package com.sbisec.helios.gw.companyEmployeeMenu.jointContract.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA006ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA008ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterA002RequestDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA002ResponseApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA006RequestApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA006ResponseApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA008RequestApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA008ResponseApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterA002RequestApi;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
 
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0513_01-01", screenNumber = "")
public class IfaJointContractMasterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String SERVICE_NAME = "cmpIfaJointContractMasterService";
    
    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterAllBrokerNameDisplayA002
    * アクションID：A002
    * アクション名：全仲介業者名表示
    * APIリクエスト：IfaJointContractMasterA002RequestApi
    * Apiレスポンス：IfaJointContractMasterA002ResponseApi
    * Dtoリクエスト：IfaJointContractMasterA002RequestDto
    * Dtoレスポンス：IfaJointContractMasterA002ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterAllBrokerNameDisplayA002")
    public String allBrokerNameDisplayA002(
            @Validated @RequestBody IfaJointContractMasterA002RequestApi apiReq) throws Exception {
        
        var appReq = new IfaJointContractMasterA002RequestDto();
        
        /** ①一覧の選択行目のデータにより、共同募集契約先仲介業者を取得する。 */
        // 仲介業者コード
        appReq.setBrokerCode(null);
        // 仲介業者名
        appReq.setBrokerName(null);
        // 共募契約
        appReq.setJointContract(apiReq.getJointContract());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "displayA002A003",
                new TypeReference<DataList<IfaJointContractMasterA002ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterA002ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterSearchDisplayA003
    * アクションID：A003
    * アクション名：表示
    * APIリクエスト：IfaJointContractMasterA002RequestApi
    * Apiレスポンス：IfaJointContractMasterA002ResponseApi
    * Dtoリクエスト：IfaJointContractMasterA002RequestDto
    * Dtoレスポンス：IfaJointContractMasterA002ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterSearchDisplayA003")
    public String searchDisplayA003(@Validated @RequestBody IfaJointContractMasterA002RequestApi apiReq)
            throws Exception {
        
        var appReq = new IfaJointContractMasterA002RequestDto();
        
        
        // 仲介業者コード
        appReq.setBrokerCode(apiReq.getBrokerCode());
        // 仲介業者名
        appReq.setBrokerName(apiReq.getBrokerName());
        // 共募契約
        appReq.setJointContract(apiReq.getJointContract());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "displayA002A003",
                new TypeReference<DataList<IfaJointContractMasterA002ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterA002ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterUpdateA006
    * アクションID：A006
    * アクション名：表示
    * APIリクエスト：IfaJointContractMasterA006RequestApi
    * Apiレスポンス：IfaJointContractMasterA006ResponseApi
    * Dtoリクエスト：IfaJointContractMasterA006RequestDto
    * Dtoレスポンス：IfaJointContractMasterA006ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterUpdateA006")
    public String updateA006(@Validated @RequestBody IfaJointContractMasterA006RequestApi apiReq)
            throws Exception {
        
        var appReq = new IfaJointContractMasterA006RequestDto();
        
        
        // 一覧.仲介業者コード
        appReq.setBrokerCodeParam(apiReq.getBrokerCodeParam());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "updateA006",
                new TypeReference<DataList<IfaJointContractMasterA006ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterA006ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterDeleteA008
    * アクションID：A008
    * アクション名：表示
    * APIリクエスト：IfaJointContractMasterA008RequestApi
    * Apiレスポンス：IfaJointContractMasterA008ResponseApi
    * Dtoリクエスト：IfaJointContractMasterA008RequestDto
    * Dtoレスポンス：IfaJointContractMasterA008ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterDeleteA008")
    public String deleteA008(@Validated @RequestBody IfaJointContractMasterA008RequestApi apiReq)
            throws Exception {
        
        var appReq = new IfaJointContractMasterA008RequestDto();

        // 一覧.仲介業者コード
        appReq.setBrokerCodeParam(apiReq.getBrokerCodeParam());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "deleteA008",
                new TypeReference<DataList<IfaJointContractMasterA008ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterA008ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        
        return null;
    }
    
}
