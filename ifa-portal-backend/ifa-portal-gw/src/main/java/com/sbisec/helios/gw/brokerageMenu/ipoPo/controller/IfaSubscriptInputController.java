package com.sbisec.helios.gw.brokerageMenu.ipoPo.controller;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003ResponseDto;
import com.sbisec.helios.ap.common.model.DataList;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA001ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA001ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA002ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA002ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA003ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA003ApiResponse;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA005ApiRequest;
import com.sbisec.helios.gw.brokerageMenu.ipoPo.form.IfaSubscriptInputA005ApiResponse;
import com.sbisec.helios.gw.common.controller.BaseController;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/02/02 新規作成
 *
 * @author SCSK 江口
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0204_02-04_1", screenNumber = "")
public class IfaSubscriptInputController extends BaseController {
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputInitializeA001
     * アクションID：A001
     * アクション名：初期化
     * APIリクエスト：IfaSubscriptInputA001ApiRequest
     * Apiレスポンス：IfaSubscriptInputA001ApiResponse
     * Dtoリクエスト：IfaSubscriptInputA001RequestDto
     * Dtoレスポンス：IfaSubscriptInputA001ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return Apiレスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputInitializeA001")
    public String inputInitializeA001(@RequestBody IfaSubscriptInputA001ApiRequest apiReq) throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputA001RequestDto appReq = new IfaSubscriptInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaSubscriptInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSubscriptInputService",
                "initializeA001", new TypeReference<DataList<IfaSubscriptInputA001ResponseDto>>() {
                }, appReq);
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputA001ApiResponse> apiRes = new DataList<IfaSubscriptInputA001ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmIntermediaryRegistrationAdminCorrectionA002
     * アクションID：A002
     * アクション名：確認（仲介業者登録・管理者訂正）
     * APIリクエスト：IfaSubscriptInputA002ApiRequest
     * Apiレスポンス：IfaSubscriptInputA002ApiResponse
     * Dtoリクエスト：IfaSubscriptInputA002RequestDto
     * Dtoレスポンス：IfaSubscriptInputA002ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return Apiレスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmIntermediaryRegistrationAdminCorrectionA002")
    public String confirmIntermediaryRegistrationAdminCorrectionA002(
            @RequestBody IfaSubscriptInputA002ApiRequest apiReq) throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputA002RequestDto appReq = new IfaSubscriptInputA002RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaSubscriptInputA002ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSubscriptInputService",
                "confirmIntermediaryRegistrationAdminCorrectionA002",
                new TypeReference<DataList<IfaSubscriptInputA002ResponseDto>>() {
                }, appReq);
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputA002ApiResponse> apiRes = new DataList<IfaSubscriptInputA002ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputConfirmIntermediaryCorrectionA003
     * アクションID：A003
     * アクション名：確認（仲介業者訂正）
     * APIリクエスト：IfaSubscriptInputA003ApiRequest
     * Apiレスポンス：IfaSubscriptInputA003ApiResponse
     * Dtoリクエスト：IfaSubscriptInputA003RequestDto
     * Dtoレスポンス：IfaSubscriptInputA003ResponseDto
     *
     * @param apiReq リクエストボディ(POST)
     * @return Apiレスポンス
     * @exception Exception システムエラー
     */
    @PostMapping("/brokerageMenu/ipoPo/ifaSubscriptInputConfirmIntermediaryCorrectionA003")
    public String confirmIntermediaryCorrectionA003(@RequestBody IfaSubscriptInputA003ApiRequest apiReq)
            throws Exception {
        
        // コントローラへのリクエストをサービスへのリクエストへコピー
        IfaSubscriptInputA003RequestDto appReq = new IfaSubscriptInputA003RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービスへ処理を移譲
        DataList<IfaSubscriptInputA003ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSubscriptInputService",
                "confirmIntermediaryCorrectionA003", new TypeReference<DataList<IfaSubscriptInputA003ResponseDto>>() {
                }, appReq);
        
        // サービスからのレスポンスをコントローラのレスポンスへコピー
        DataList<IfaSubscriptInputA003ApiResponse> apiRes = new DataList<IfaSubscriptInputA003ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        return jc.toString(apiRes);
    }
    
    /**
     * アクセス：/brokerageMenu/ipoPo/ifaSubscriptInputBackInitializeA005
     * アクションID：A005
     * アクション名：戻り初期化
     * APIリクエスト：IfaSubscriptInputA005ApiRequest
     * Apiレスポンス：IfaSubscriptInputA005ApiResponse
     * Dtoリクエスト：IfaSubscriptInputA005RequestDto
     * Dtoレスポンス：IfaSubscriptInputA005ResponseDto
     *
     * @param apiReq リクエスト
     * @return apiRes レスポンス
     * @exception exception システムエラー
     */
    @PostMapping(value = "/brokerageMenu/ipoPo/ifaSubscriptInputBackInitializeA005")
    public String backInitializeA005(@RequestBody IfaSubscriptInputA005ApiRequest apiReq) throws Exception {
        
        // コントローラへのリクエストをサービスA001へのリクエストへコピー
        IfaSubscriptInputA001RequestDto appReq = new IfaSubscriptInputA001RequestDto();
        BeanUtils.copyProperties(appReq, apiReq);
        
        // サービス(A001)へ処理を移譲
        DataList<IfaSubscriptInputA001ResponseDto> appRes = ApiRequestUtil.invoke("cmpIfaSubscriptInputService",
                "initializeA001", new TypeReference<DataList<IfaSubscriptInputA001ResponseDto>>() {
                }, appReq);
        
        // DataList以外コピー（DataListは後続で上書き）
        DataList<IfaSubscriptInputA005ApiResponse> apiRes = new DataList<IfaSubscriptInputA005ApiResponse>();
        BeanUtils.copyProperties(apiRes, appRes);
        
        // DataList内の各項目をコピー
        List<IfaSubscriptInputA001ResponseDto> appResDataList = appRes.getDataList();
        List<IfaSubscriptInputA005ApiResponse> resDataList = new ArrayList<IfaSubscriptInputA005ApiResponse>();
        
        for (IfaSubscriptInputA001ResponseDto sourceObject : appResDataList) {
            IfaSubscriptInputA005ApiResponse targetObject = new IfaSubscriptInputA005ApiResponse();
            BeanUtils.copyProperties(targetObject, sourceObject);
            
            // リクエストの値で上書きする
            targetObject.setBrandCode(apiReq.getBrandCode());
            targetObject.setButenCode(apiReq.getButenCode());
            targetObject.setAccountNumber(apiReq.getAccountNumber());
            targetObject.setBookBuildingPresentationFrom(apiReq.getBookBuildingPresentationFrom());
            targetObject.setDetailNumber(apiReq.getDetailNumber());
            targetObject.setLotteryResult(apiReq.getLotteryResult());
            targetObject.setBbQuantityAlloc(apiReq.getBbQuantityAlloc());
            targetObject.setOrderStatus(apiReq.getOrderStatus());
            targetObject.setQuantity(apiReq.getQuantity());
            targetObject.setDepositType(apiReq.getDepositType());
            targetObject.setKanyuKbn(apiReq.getKanyuKbn());
            targetObject.setJutyuKbn(apiReq.getJutyuKbn());
            targetObject.setProspectus(apiReq.getMokuromiKoufuKbn());
            targetObject.setImportantMatterType(apiReq.getImportantMatterType());
            targetObject.setBbRemark(apiReq.getBbRemark());
            
            // 返却するDataListに追加
            resDataList.add(targetObject);
        }
        
        apiRes.setDataList(resDataList);
        return jc.toString(apiRes);
    }
    
    @Override
    protected String getFirstViewName() {
        
        return null;
    }
}
