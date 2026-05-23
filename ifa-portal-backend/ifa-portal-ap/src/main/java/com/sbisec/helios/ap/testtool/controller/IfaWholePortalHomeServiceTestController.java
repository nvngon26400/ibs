package com.sbisec.helios.ap.testtool.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001RequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaWholePortalHomeA001ResponseDto;
import com.sbisec.helios.ap.wholePortal.service.impl.IfaWholePortalHomeServiceImpL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 総合ポータル_ホーム_テスト用
 *
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN01", id = "SUB01-01", screenNumber = "", ignoreCheck = true)
public class IfaWholePortalHomeServiceTestController {
    
    @Autowired
    private IfaWholePortalHomeServiceImpL cmpIfaWholePortalHomeService;
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * テスト用
     *
     * @param appReq リクエスト
     * @return 戻り値
     * @throws Exception 例外
     */
    @PostMapping("/wholePortal/ifaWholePortalHomeInitializeA001")
    public String initializeA001(@RequestBody IfaWholePortalHomeA001RequestDto appReq) throws Exception {        
        // ①ユーザ共通情報のJSON電文をStringに格納
        String userAccountTypeJson = "{"
                + "         \"medUsers\": {"
                + "            \"lastpwuptime\": 1683532706000,"
                + "            \"privId\": \"6\","
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
                + "            \"userId\": \"6SCSK01A\","
                + "            \"userNm\": \"0000000000\","
                + "            \"password\": \"eNsx4HythiJAGFoBc29JIDDk4jpooqMo2ORw4Nkkmdw=\""
                + "        },"
                + "        \"userPermissionInfo\": {"
                + "            \"accessibleViewList\": ["
                + "                \"0000000000\","
                + "                \"CommonCode\","
                + "                \"LinkUrl\","
                + "                \"SUB002\","
                + "                \"0000000001\""
                + "            ]"
                + "        },"
                + "        \"branch\": {"
                + "            \"branchCode\": \"0000000000\","
                + "            \"branchKind\": \"0000000000\","
                + "            \"branchName\": \"0000000000\""
                + "        },"
                + "        \"broker\": {"
                + "            \"brokerCode\": \"2220\","
                + "            \"brokerBranchCode\": \"000\","
                + "            \"brokerBranchKind\": \"0000000000\","
                + "            \"brokerName\": \"0000000000\""
                + "        },"
                + "        \"subBroker\": {"
                + "            \"brokerCode\": \"0000000000\","
                + "            \"brokerBranchCode\": \"552\","
                + "            \"brokerBranchKind\": \"0000000000\","
                + "            \"brokerName\": \"0000000000\""
                + "        },"
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
                + "                \"accControl\": 1"
                + "            },"
                + "            {"
                + "                \"menuId\": \"SUB005\","
                + "                \"itemId\": \"jointBranchCode\","
                + "                \"userId\": \"111\","
                + "                \"accControl\": 1"
                + "            }"
                + "        ]"      
                + "}";        
        // ②JsonConverter#toObjectでデシリアライズ
        UserAccount userAccount = jc.toObject(userAccountTypeJson, UserAccount.class);
        
        // ③デシリアライズしたユーザ共通情報のオブジェクトをリクエストスコープにセット
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        
        DataList<IfaWholePortalHomeA001ResponseDto> appRes = new DataList<IfaWholePortalHomeA001ResponseDto>();
        appRes = cmpIfaWholePortalHomeService.initializeA001(appReq);
        
        return jc.toString(appRes);
        
    }
    
}
