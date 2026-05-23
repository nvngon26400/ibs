package com.sbisec.helios.ap.testtool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaMarginPositionListForeignService;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 * テスト用
 * @author 島崎聡士 2023/11/30 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB020302_0303-01", screenNumber = "72", ignoreCheck = true)
public class IfaMarginPositionListForeignController {
    
    // fwSessionId
    private String fwSessionId = "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXXXX";
    
    // ①ユーザ共通情報のJSON電文をStringに格納
    private String userAccountTypeJson = "{" 
            + "        \"medUsers\": {"
            + "            \"lastpwuptime\": 1683532706000," 
            + "            \"privId\": \"1\","
            + "            \"branchId\": \"0000000000\"," 
            + "            \"brokerId\": \"0000000000\","
            + "            \"subBrokerId\": \"0000000000\"," 
            + "            \"employeeId\": \"0000000000\","
            + "            \"employeeName\": \"0000000000\"," 
            + "            \"governorFlag\": \"0000000000\","
            + "            \"loginStatus\": \"0000000000\"," 
            + "            \"partnerUserId\": \"0000000000\","
            + "            \"partnerUserPw\": \"0000000000\"," 
            + "            \"ccsUserId\": \"0000000000\","
            + "            \"ccsUserPw\": \"0000000000\"," 
            + "            \"createUser\": \"0000000000\","
            + "            \"createTime\": 1683532706000," 
            + "            \"uptimestampUser\": \"0000000000\","
            + "            \"uptimestampTime\": 1683532706000," 
            + "            \"mailAddress\": \"0000000000\","
            + "            \"userId\": \"555\"," 
            + "            \"userNm\": \"0000000000\","
            + "            \"password\": \"ukw6EOPD0NNDHMTLn9nBvwrGvktJ4QsHr+T0+cBA8XM=\"" 
            + "        },"
            + "        \"userPermissionInfo\": {" 
            + "            \"accessibleViewList\": ["
            + "                \"0000000000\"," 
            + "                \"CommonCode\"," 
            + "                \"LinkUrl\","
            + "                \"SUB002\"," + "                \"0000000001\"," 
            + "                \"SUB0201_01-01\","
            + "                \"MAIN02\"" + "            ]" 
            + "        }," 
//            + "        \"branch\": {"
//            + "            \"branchCode\": \"0000000000\"," 
//            + "            \"branchKind\": \"0000000000\","
//            + "            \"branchName\": \"0000000000\"" 
//            + "        },"
//            + "        \"broker\": {"
//            + "            \"brokerCode\": \"0000000000\"," 
//            + "            \"brokerBranchCode\": \"000\","
//            + "            \"brokerBranchKind\": \"0000000000\"," 
//            + "            \"branchName\": \"0000000000\""
//            + "        }," 
//            + "        \"subBroker\": {" 
//            + "            \"brokerCode\": \"0000000000\","
//            + "            \"brokerBranchCode\": \"0000000000\"," 
//            + "            \"brokerBranchKind\": \"0000000000\","
//            + "            \"branchName\": \"0000000000\"" 
//            + "        }," 
            + "        \"mediateChargeInfo\": {"
            + "            \"brokerCode\": \"0000000000\","
            + "            \"brokerBranchCode\": \"0000000000\","
            + "            \"brokerChargeCode\": \"0000000000\"," 
            + "            \"brokerChargeName\": \"0000000000\""
            + "        }," 
            + "        \"organization\": null," 
            + "        \"userNeedsToReadComplianceLetters\": null,"
            + "        \"userNeedsToReadMonthlySelfcheck\": null," 
            + "        \"accControls\": ["
            + "            {"
            + "                \"menuId\": \"SUB005\"," 
            + "                \"itemId\": \"brokerCode\","
            + "                \"userId\": \"111\"," 
            + "                \"accControl\": 1" 
            + "            },"
            + "            {" 
            + "                \"menuId\": \"SUB005\","
            + "                \"itemId\": \"btnCsvDownload\","
            + "                \"userId\": \"111\","
            + "                \"accControl\": 1" 
            + "            }," 
            + "            {"
            + "                \"menuId\": \"SUB005\"," 
            + "                \"itemId\": \"empCode\","
            + "                \"userId\": \"111\"," 
            + "                \"accControl\": 1" + "            },"
            + "            {" 
            + "                \"menuId\": \"SUB005\","
            + "                \"itemId\": \"jointBranchCode\"," 
            + "                \"userId\": \"111\","
            + "                \"accControl\": 1" 
            + "            }" 
            + "        ]" 
            + "}";
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private IfaMarginPositionListForeignService ifaMarginPositionListForeignService;
    
    // テストデータ生成
    private void init() throws Exception {
        
        // ②JsonConverter#toObjectでデシリアライズ
        UserAccount userAccount = jc.toObject(userAccountTypeJson, UserAccount.class);
        
        // ③デシリアライズしたユーザ共通情報のオブジェクトをリクエストスコープにセット
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
    }
    
    /**
     * アクセス：/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006a
     * アクションID：A006
     * アクション名：CSV出力
     * APIリクエスト：IfaMarginPositionListForeignA006aApiRequest
     * Apiレスポンス：IfaMarginPositionListForeignA006ApiResponse
     * Dtoリクエスト：IfaMarginPositionListForeignA006DtoRequest
     * Dtoレスポンス：IfaMarginPositionListForeignA006DtoResponse
     *
     * @param apiReq IfaMarginPositionListForeignA006aApiRequest
     * @return apiRes
     * @exception Exception 例外が発生した場合
     */
    @PostMapping("/brokerageMenu/wholeCustomer/ifaMarginPositionListForeignCsvOutputA006a")
    public String csvOutputA006a(@RequestBody IfaMarginPositionListForeignA006aDtoRequest appReq) throws Exception {
        
        // テストデータ設定
        init();
        
        DataList<IfaMarginPositionListForeignA006aDtoResponse> appRes = new DataList<IfaMarginPositionListForeignA006aDtoResponse>();
        appRes = ifaMarginPositionListForeignService.csvOutputA006(appReq, fwSessionId);
        
        return jc.toString(appRes);
        
    }
    
}
