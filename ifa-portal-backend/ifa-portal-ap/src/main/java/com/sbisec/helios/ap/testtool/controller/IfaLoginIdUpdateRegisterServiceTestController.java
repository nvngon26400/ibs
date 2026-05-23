package com.sbisec.helios.ap.testtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA001DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA002DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA003DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA004DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA005DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA006DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdUpdateRegisterA014DtoResponse;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.impl.IfaLoginIdUpdateRegisterServiceImpL;

/**
 * 画面ID：SUB0601_01-03_1 画面名：ログインID更新登録 テスト用コントローラ
 * 
 * @author 齋藤
 *
 *         2023/11/06 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-03_1", screenNumber = "")
public class IfaLoginIdUpdateRegisterServiceTestController {
    
    // ①ユーザ共通情報のJSON電文をStringに格納
    private String userAccountTypeJson = "{" + "        \"medUsers\": {"
            + "            \"lastpwuptime\": 1683532706000," + "            \"privId\": \"1\","
            + "            \"branchId\": \"0000000000\"," + "            \"brokerId\": \"0000000000\","
            + "            \"subBrokerId\": \"0000000000\"," + "            \"employeeId\": \"0000000000\","
            + "            \"employeeName\": \"0000000000\"," + "            \"governorFlag\": \"0000000000\","
            + "            \"loginStatus\": \"0000000000\"," + "            \"partnerUserId\": \"0000000000\","
            + "            \"partnerUserPw\": \"0000000000\"," + "            \"ccsUserId\": \"0000000000\","
            + "            \"ccsUserPw\": \"0000000000\"," + "            \"createUser\": \"0000000000\","
            + "            \"createTime\": 1683532706000," + "            \"uptimestampUser\": \"0000000000\","
            + "            \"uptimestampTime\": 1683532706000," + "            \"mailAddress\": \"0000000000\","
            + "            \"userId\": \"555\"," + "            \"userNm\": \"0000000000\","
            + "            \"password\": \"ukw6EOPD0NNDHMTLn9nBvwrGvktJ4QsHr+T0+cBA8XM=\"" + "        },"
            + "        \"userPermissionInfo\": {" + "            \"accessibleViewList\": ["
            + "                \"0000000000\"," + "                \"CommonCode\"," + "                \"LinkUrl\","
            + "                \"SUB002\"," + "                \"0000000001\"," + "                \"SUB0601_01-03_1\","
            + "                \"MAIN02\"" + "            ]" + "        }," + "        \"branch\": {"
            + "            \"branchCode\": \"0000000000\"," + "            \"branchKind\": \"0000000000\","
            + "            \"branchName\": \"0000000000\"" + "        }," + "        \"broker\": {"
            + "            \"brokerCode\": \"0000000000\"," + "            \"brokerBranchCode\": \"000\","
            + "            \"brokerBranchKind\": \"0000000000\"" + "        }," + "        \"subBroker\": {"
            + "            \"brokerCode\": \"0000000000\"," + "            \"brokerBranchCode\": \"0000000000\","
            + "            \"brokerBranchKind\": \"0000000000\"" + "        }," + "        \"mediateChargeInfo\": {"
            + "            \"brokerCode\": \"0000000000\"," + "            \"brokerBranchCode\": \"0000000000\","
            + "            \"brokerChargeCode\": \"0000000000\"," + "            \"brokerChargeName\": \"0000000000\""
            + "        }," + "        \"organization\": null," + "        \"userNeedsToReadComplianceLetters\": null,"
            + "        \"userNeedsToReadMonthlySelfcheck\": null," + "        \"accControls\": [" + "            {"
            + "                \"menuId\": \"SUB005\"," + "                \"itemId\": \"brokerCode\","
            + "                \"userId\": \"111\"," + "                \"accControl\": 1" + "            },"
            + "            {" + "                \"menuId\": \"SUB005\","
            + "                \"itemId\": \"btnCsvDownload\"," + "                \"userId\": \"111\","
            + "                \"accControl\": 1" + "            }," + "            {"
            + "                \"menuId\": \"SUB005\"," + "                \"itemId\": \"empCode\","
            + "                \"userId\": \"111\"," + "                \"accControl\": 1" + "            },"
            + "            {" + "                \"menuId\": \"SUB005\","
            + "                \"itemId\": \"jointBranchCode\"," + "                \"userId\": \"111\","
            + "                \"accControl\": 1" + "            }" + "        ]" + "}";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private IfaLoginIdUpdateRegisterServiceImpL cmpIfaLoginIdUpdateRegisterService;
    
    // テストデータ生成
    private void init() throws Exception {
        
        // ②JsonConverter#toObjectでデシリアライズ
        UserAccount userAccount = jc.toObject(userAccountTypeJson, UserAccount.class);
        
        // ③デシリアライズしたユーザ共通情報のオブジェクトをリクエストスコープにセット
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterInitialDisplayA001
     * アクションID：A001 アクション名：初期表示 APIリクエスト：IfaLoginIdUpdateRegisterA001ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA001ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA001DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA001DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterInitialDisplayA001ServiceTest")
    public String initialDisplayA001(@RequestBody IfaLoginIdUpdateRegisterA001DtoRequest appReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA001DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA001DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.initialDisplayA001(appReq);
        
        return jc.toString(appRes);
        
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterAffiliationAuthorityUpdateA002
     * アクションID：A002 アクション名：所属権限更新 APIリクエスト：IfaLoginIdUpdateRegisterA002ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA002ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA002DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA002DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterAffiliationAuthorityUpdateA002ServiceTest")
    public String affiliationAuthorityUpdateA002(@RequestBody IfaLoginIdUpdateRegisterA002DtoRequest appReq)
            throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA002DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA002DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.affiliationAuthorityUpdateA002(appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterHeadOfficeNameUpdateA003
     * アクションID：A003 アクション名：本支名更新 APIリクエスト：IfaLoginIdUpdateRegisterA003ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA003ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA003DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA003DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterHeadOfficeNameUpdateA003ServiceTest")
    public String headOfficeNameUpdateA003(@RequestBody IfaLoginIdUpdateRegisterA003DtoRequest appReq)
            throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA003DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA003DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.headOfficeNameUpdateA003(appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerNameUpdateA004
     * アクションID：A004 アクション名：仲介業者名更新 APIリクエスト：IfaLoginIdUpdateRegisterA004ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA004ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA004DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA004DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerNameUpdateA004ServiceTest")
    public String brokerNameUpdateA004(@RequestBody IfaLoginIdUpdateRegisterA004DtoRequest appReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA004DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA004DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.brokerNameUpdateA004(appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerBranchNameUpdateA005
     * アクションID：A005 アクション名：仲介業者支店名更新 APIリクエスト：IfaLoginIdUpdateRegisterA005ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA005ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA005DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA005DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterBrokerBranchNameUpdateA005ServiceTest")
    public String brokerBranchNameUpdateA005(@RequestBody IfaLoginIdUpdateRegisterA005DtoRequest appReq)
            throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA005DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA005DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.brokerBranchNameUpdateA005(appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterResetA006
     * アクションID：A006 アクション名：リセット APIリクエスト：IfaLoginIdUpdateRegisterA006ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA006ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA006DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA006DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterResetA006ServiceTest")
    public String resetA006(@RequestBody IfaLoginIdUpdateRegisterA006DtoRequest appReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA006DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA006DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.resetA006(appReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterUpdateOkA014
     * アクションID：A014 アクション名：更新（OK） APIリクエスト：IfaLoginIdUpdateRegisterA014ApiRequest
     * Apiレスポンス：IfaLoginIdUpdateRegisterA014ApiResponse
     * Dtoリクエスト：IfaLoginIdUpdateRegisterA014DtoRequest
     * Dtoレスポンス：IfaLoginIdUpdateRegisterA014DtoResponse
     * 
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdUpdateRegisterUpdateOkA014ServiceTest")
    public String updateOkA014(@RequestBody IfaLoginIdUpdateRegisterA014DtoRequest appReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdUpdateRegisterA014DtoResponse> appRes = new DataList<IfaLoginIdUpdateRegisterA014DtoResponse>();
        appRes = cmpIfaLoginIdUpdateRegisterService.updateOkA014(appReq);
        
        return jc.toString(appRes);
    }
}
