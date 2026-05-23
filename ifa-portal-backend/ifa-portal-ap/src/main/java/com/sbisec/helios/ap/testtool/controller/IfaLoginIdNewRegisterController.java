package com.sbisec.helios.ap.testtool.controller;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA001ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA002ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA006ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011RequestDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dto.IfaLoginIdNewRegisterA011ResponseDto;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.service.IfaLoginIdNewRegisterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 *
 * 2023/11/09 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN06", id = "SUB0601_01-02_1", screenNumber = "")
public class IfaLoginIdNewRegisterController {
    
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
            + "                \"SUB002\"," + "                \"0000000001\"," + "                \"SUB0201_01-01\","
            + "                \"MAIN02\"" + "            ]" + "        }," + "        \"branch\": {"
            + "            \"branchCode\": \"0000000000\"," + "            \"branchKind\": \"0000000000\","
            + "            \"branchName\": \"0000000000\"" + "        }," + "        \"broker\": {"
            + "            \"brokerCode\": \"0000000000\"," + "            \"brokerBranchCode\": \"000\","
            + "            \"brokerBranchKind\": \"0000000000\"," + "            \"brokerName\": \"0000000000\""
            + "        }," + "        \"subBroker\": {" + "            \"brokerCode\": \"0000000000\","
            + "            \"brokerBranchCode\": \"0000000000\"," + "            \"brokerBranchKind\": \"0000000000\","
            + "            \"brokerName\": \"0000000000\"" + "        }," + "        \"mediateChargeInfo\": {"
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
    private IfaLoginIdNewRegisterService ifaLoginIdNewRegisterService;
    
    // テストデータ生成
    private void init() throws Exception {
        
        // ②JsonConverter#toObjectでデシリアライズ
        UserAccount userAccount = jc.toObject(userAccountTypeJson, UserAccount.class);
        
        // ③デシリアライズしたユーザ共通情報のオブジェクトをリクエストスコープにセット
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterInitialDisplayA001
     * アクションID：A001
     * アクション名：初期表示
     * APIリクエスト：IfaLoginIdNewRegisterA001ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA001ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA001RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA001ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterTestInitialDisplayA001")
    public String initialDisplayA001(@RequestBody IfaLoginIdNewRegisterA001RequestDto apiReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdNewRegisterA001ResponseDto> appRes = new DataList<IfaLoginIdNewRegisterA001ResponseDto>();
        appRes = ifaLoginIdNewRegisterService.initialDisplayA001(apiReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterPersonInChargeNameUpdateA013
     * アクションID：A002
     * アクション名：所属権限更新
     * APIリクエスト：IfaLoginIdNewRegisterA002ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA002ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA002RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA002ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterTestAffiliationAuthorityUpdateA002")
    public String affiliationAuthorityUpdateA002(@RequestBody IfaLoginIdNewRegisterA002RequestDto apiReq)
            throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdNewRegisterA002ResponseDto> appRes = new DataList<IfaLoginIdNewRegisterA002ResponseDto>();
        appRes = ifaLoginIdNewRegisterService.affiliationAuthorityUpdateA002(apiReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterResetA006
     * アクションID：A006
     * アクション名：リセット
     * APIリクエスト：IfaLoginIdNewRegisterA006ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA006ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA006RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA006ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterTestResetA006")
    public String resetA006(@RequestBody IfaLoginIdNewRegisterA006RequestDto apiReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdNewRegisterA006ResponseDto> appRes = new DataList<IfaLoginIdNewRegisterA006ResponseDto>();
        appRes = ifaLoginIdNewRegisterService.resetA006(apiReq);
        
        return jc.toString(appRes);
    }
    
    /**
     * 
     * アクセス：/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterRegistrationOkA011
     * アクションID：A011
     * アクション名：登録（OK）
     * APIリクエスト：IfaLoginIdNewRegisterA011ApiRequest
     * Apiレスポンス：IfaLoginIdNewRegisterA011ApiResponse
     * Dtoリクエスト：IfaLoginIdNewRegisterA011RequestDto
     * Dtoレスポンス：IfaLoginIdNewRegisterA011ResponseDto
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/systemManageMenu/loginUserManage/ifaLoginIdNewRegisterTestRegistrationOkA011")
    public String registrationOkA011(@RequestBody IfaLoginIdNewRegisterA011RequestDto apiReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaLoginIdNewRegisterA011ResponseDto> appRes = new DataList<IfaLoginIdNewRegisterA011ResponseDto>();
        appRes = ifaLoginIdNewRegisterService.registrationOkA011(apiReq);
        
        return jc.toString(appRes);
    }
}
