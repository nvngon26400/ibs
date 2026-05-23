package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.controller;

import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.enums.SearchType;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.authMailAddressChange.model.ModifyEmailAddressForCertifyModel;
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressChangeListA002ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressChangeListA002ApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressChangeListA003ApiRequest;
import com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form.IfaAuthMailAddressChangeListA003ApiResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.util.StringUtil;
import com.sbibits.earth.util.json.JsonConverter;

/**
 * 画面ID：SUB0404-01
 * 画面名：認証用メールアドレス一覧
 *
 * @author SCSK
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN04", id = "SUB0404-01", screenNumber = "")
public class IfaAuthMailAddressChangeListController extends BaseController {
     
    private static final Logger logger = LoggerFactory.getLogger(IfaAuthMailAddressChangeListController.class);

    private JsonConverter jc = JsonConverter.getInstance();
           
    /**
    * A002 表示
    *
    * @param apiReq {@code IfaAuthMailAddressChangeListA002ApiRequest }
    * @return {@code String}
    * @throws Exception 表示処理で例外が発生した場合
    */
    @PostMapping("/internalAdminMenu/authMailAddressChange/ifaAuthMailAddressChangeListDisplayA002")
    @ResponseBody
    @ResponseJson
    public String displayA002(@RequestBody @Validated IfaAuthMailAddressChangeListA002ApiRequest apiReq) 
            throws Exception {

        final long start = System.currentTimeMillis();
        logger.debug("displayA002 >> {}", hashCode());
        
        DataList<IfaAuthMailAddressChangeListA002ApiResponse> apiRes = 
                new DataList<IfaAuthMailAddressChangeListA002ApiResponse>();
        DataList<ModifyEmailAddressForCertifyModel> dataList = new DataList<ModifyEmailAddressForCertifyModel>();
        
        // 顧客共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        
        OutputFct030Dto outputFct030 = new OutputFct030Dto();
        
        // ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        if (!PrivId.HEAD_OFFICE.getId().equals(privId)) {
            
            String dammy = null;
            outputFct030 = ApiRequestUtil.invoke("cmpModifyEmailAddressForCertifyService", "getFct030",
                    new TypeReference<OutputFct030Dto>() {
                    }, dammy);
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (null == outputFct030 || outputFct030.getBrokerChargeList().size() == 0) {
                List<IfaAuthMailAddressChangeListA002ApiResponse> resList = null;
                apiRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL,
                        ERRORS_CMN_IFAAGENTCODES_NOTEXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFAAGENTCODES_NOTEXIST));
                return jc.toString(apiRes);
            }
        }      
      
        String userId = toSearchString(apiReq.getLoginId(), SearchType.INCLUDING.getId());
        String brokerOrBranchName = toSearchString(apiReq.getBrokerOrBranchName(), SearchType.INCLUDING.getId());
        String employeeName = toSearchString(apiReq.getEmployeeName(), SearchType.INCLUDING.getId());
        
        // 認証用メールアドレス一覧情報を検索する。
        dataList = ApiRequestUtil.invoke("cmpModifyEmailAddressForCertifyService", "getCertifyInfoList",
                new TypeReference<DataList<ModifyEmailAddressForCertifyModel>>() {
                }, privId, userId, brokerOrBranchName, employeeName, outputFct030.getBrokerChargeList());
        
        setStatusAndMessageToDataList(dataList, false);
        
        // レスポンス内容を設定する。
        setApiResponse(apiRes, dataList);

        String resultJson = jc.toString(apiRes);

        logger.debug("resultJson -> {}", resultJson);
        logger.debug("cost -> {}", (System.currentTimeMillis() - start));

        return resultJson;
    }
    
    /**
    * A003 登録（チェック）
    *
    * @param apiReq {@code IfaAuthMailAddressChangeListA003ApiRequest }
    * @return {@code String}
    * @throws Exception 登録（チェック）処理で例外が発生した場合
    */
    @PostMapping("/internalAdminMenu/authMailAddressChange/ifaAuthMailAddressChangeListUpdateA003")
    @ResponseBody
    @ResponseJson
    public String updateA003(
            @RequestBody @Validated IfaAuthMailAddressChangeListA003ApiRequest apiReq) throws Exception {
        
        logger.debug("updateA003()");

        DataList<IfaAuthMailAddressChangeListA003ApiResponse> dataList = 
                new DataList<IfaAuthMailAddressChangeListA003ApiResponse>();

        int cnt = ApiRequestUtil.invoke("cmpModifyEmailAddressForCertifyService", "existCnt",
                new TypeReference<Integer>() {}, apiReq.getLoginId());

        if (cnt == 0) {
            // Deleting failed
            String msg = IfaCommonUtil.getMessage(ERRORS_DELETE_DATA_EXIST, new String[] {"ログインID"});
            dataList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.FATAL,
                    ERRORS_DELETE_DATA_EXIST, msg);
        } else {
            // Deleting successful
            dataList = IfaCommonUtil.createDataList(new ArrayList<>(), ErrorLevel.SUCCESS,
                    ErrorLevel.SUCCESS.name(), "");
        }

        return jc.toString(dataList);
    }
    
    @Override
    protected String getFirstViewName() {
        return null;
    }
    
    /**
    * 曖昧検索事前処理
    *
    * @param src {@code String }
    * @param searchType {@code Integer }
    * @return {@code String}
    */
    private String toSearchString(String src, int searchType) {
        if (!StringUtil.isNullOrEmpty(src)) {
            if (searchType != SearchType.EQUAL.getId()) {
                src = addEscape(src);
                if (searchType == SearchType.INCLUDING.getId()) {
                    src = String.format("%%%s%%", src);
                } else if (searchType == SearchType.BEGINNING.getId()) {
                    src = String.format("%s%%", src);
                }
            }
        }
        return src;
    }
    
    /**
    * エスケープ文字設定
    *
    * @param src {@code String }
    * @return {@code String}
    */
    private String addEscape(String src) {
        src = src.replaceAll("!", "!!");
        src = src.replaceAll("%", "!%");
        src = src.replaceAll("_", "!_");
        return src;
    }
    
    /**
    * レスポンス内容を設定
    *
    * @param apiReq {@code DataList<IfaAuthMailAddressChangeListA002ApiResponse> }
    * @param modelList {@code DataList<ModifyEmailAddressForCertifyModel> }
    * @return {@code void}
    * @throws Exception レスポンス設定処理で例外が発生した場合
    */
    private void setApiResponse(DataList<IfaAuthMailAddressChangeListA002ApiResponse> apiRes,
            DataList<ModifyEmailAddressForCertifyModel> modelList) throws Exception {
        
        List<IfaAuthMailAddressChangeListA002ApiResponse> resList = 
                new ArrayList<IfaAuthMailAddressChangeListA002ApiResponse>();
        IfaAuthMailAddressChangeListA002ApiResponse res = new IfaAuthMailAddressChangeListA002ApiResponse();
        List<IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse> authMailAddressList = 
                new ArrayList<IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse>();
        
        for (ModifyEmailAddressForCertifyModel model : modelList.getDataList()) {
            
            IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse authMailAddress = 
                    new IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse();
            
            // レスポンスにSQLで取得したレコードを設定。
            authMailAddress.setBranchName(model.getBranchName());
            authMailAddress.setBrokerName(model.getBrokerName()); 
            authMailAddress.setMailAddress(model.getMailAddress());        
            authMailAddress.setUserId(model.getUserId());
            authMailAddress.setUserName(model.getUserNm());
            authMailAddressList.add(authMailAddress);
            
            // レコード数がMaxRownumと同じになったら終了
            if (authMailAddressList.size() >= modelList.getMaxRownum()) {
                break;
            }
        }
        res.setAuthMailAddressList(authMailAddressList);
        resList.add(res);
        
        // レスポンス内容を設定する。
        apiRes.setTotalSize(modelList.getTotalSize());
        apiRes.setMaxRownum(modelList.getMaxRownum());
        apiRes.setOverMaxRownum(modelList.getTotalSize() > modelList.getMaxRownum());
        apiRes.setDataList(resList);
        apiRes.setMessage(modelList.getMessage());
        apiRes.setErrorLevel(modelList.getErrorLevel());
        apiRes.setRequestedTime(modelList.getRequestedTime());
        apiRes.setTitle(modelList.getTitle());
        apiRes.setReturnCode(modelList.getReturnCode());
    }

}