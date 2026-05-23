package com.sbisec.helios.gw.companyEmployeeMenu.jointContract.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dto.IfaJointContractMasterNewRegisterA005ResponseDto;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterNewRegisterA004RequestApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterNewRegisterA004ResponseApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterNewRegisterA005RequestApi;
import com.sbisec.helios.gw.companyEmployeeMenu.jointContract.form.IfaJointContractMasterNewRegisterA005ResponseApi;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/27 新規作成
 *
 * @author 大連　苗萌
 * 
 */
 
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN05", id = "SUB0513_01-02", screenNumber = "")
public class IfaJointContractMasterNewRegisterController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    private static final String SERVICE_NAME = "cmpIfaJointContractMasterNewRegisterService";
    
    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterNewRegisterInsertConfirmA004
    * アクションID：A004
    * アクション名：登録確認
    * APIリクエスト：IfaJointContractMasterNewRegisterA004RequestApi
    * Apiレスポンス：IfaJointContractMasterNewRegisterA004ResponseApi
    * Dtoリクエスト：IfaJointContractMasterNewRegisterA004RequestDto
    * Dtoレスポンス：IfaJointContractMasterNewRegisterA004ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterNewRegisterInsertConfirmA004")
    public String insertConfirmA004(
            @Validated @RequestBody IfaJointContractMasterNewRegisterA004RequestApi apiReq) throws Exception {
        
        var appReq = new IfaJointContractMasterNewRegisterA004RequestDto();
        
        // 仲介業者コード
        appReq.setBrokerCode(apiReq.getBrokerCode());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "insertConfirmA004",
                new TypeReference<DataList<IfaJointContractMasterNewRegisterA004ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterNewRegisterA004ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }

    @Override
    protected String getFirstViewName() {
        
        return null;
    }

    /**
    * アクセス：/companyEmployeeMenu/jointContract/ifaJointContractMasterNewRegisterInsertA005
    * アクションID：A005
    * アクション名：登録
    * APIリクエスト：IfaJointContractMasterNewRegisterA005RequestApi
    * Apiレスポンス：IfaJointContractMasterNewRegisterA005ResponseApi
    * Dtoリクエスト：IfaJointContractMasterNewRegisterA005RequestDto
    * Dtoレスポンス：IfaJointContractMasterNewRegisterA005ResponseDto
    *
    * @param apiReq リクエスト
    * @return apiRes レスポンス
    * @exception exception システムエラー
    */
    @PostMapping(value = "/companyEmployeeMenu/jointContract/ifaJointContractMasterNewRegisterInsertA005")
    public String insertA005(
            @Validated @RequestBody IfaJointContractMasterNewRegisterA005RequestApi apiReq) throws Exception {
        
        var appReq = new IfaJointContractMasterNewRegisterA005RequestDto();
        
        // 仲介業者コード
        appReq.setBrokerCode(apiReq.getBrokerCode());
        // 共募契約
        appReq.setJointContract(apiReq.getJointContract());
        
        var appRes = ApiRequestUtil.invoke(SERVICE_NAME, "insertA005",
                new TypeReference<DataList<IfaJointContractMasterNewRegisterA005ResponseDto>>() {
                }, appReq);
        
        var apiRes = new DataList<IfaJointContractMasterNewRegisterA005ResponseApi>();
        
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
}
