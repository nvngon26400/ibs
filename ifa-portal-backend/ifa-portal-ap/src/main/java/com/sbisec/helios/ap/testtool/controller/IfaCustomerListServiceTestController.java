package com.sbisec.helios.ap.testtool.controller;

import com.sbibits.earth.util.json.JsonConverter;

import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.service.impl.IfaCustomerListServiceImpL;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 顧客一覧_テスト用
 *
 * @author scsk 池田
 */
@RestController
public class IfaCustomerListServiceTestController {
    
    @Autowired
    private IfaCustomerListServiceImpL cmpIfaCustomerListService;
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    /**
     * テスト用
     *
     * @param appReq リクエスト
     * @return 戻り値
     * @throws Exception 例外
     */
    @PostMapping("/brokerageMenu/customerList/ifaCustomerListServiceTestDisplayA002")
    public String displayA002(@RequestBody IfaCustomerListA002RequestDto appReq) throws Exception {
        
        // ①ユーザ共通情報のJSON電文をStringに格納
        String userAccountTypeJson = "{"
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
                + "                \"SUB002\","
                + "                \"0000000001\","
                + "                \"SUB0201_01-01\","
                + "                \"MAIN02\""
                + "            ]"
                + "        },"
                + "        \"branch\": {"
                + "            \"branchCode\": \"0000000000\","
                + "            \"branchKind\": \"0000000000\","
                + "            \"branchName\": \"0000000000\""
                + "        },"
                + "        \"broker\": {"
                + "            \"brokerCode\": \"0000000000\","
                + "            \"brokerBranchCode\": \"000\","
                + "            \"brokerBranchKind\": \"0000000000\","
                + "            \"branchName\": \"0000000000\""
                + "        },"
                + "        \"subBroker\": {"
                + "            \"brokerCode\": \"0000000000\","
                + "            \"brokerBranchCode\": \"0000000000\","
                + "            \"brokerBranchKind\": \"0000000000\","
                + "            \"branchName\": \"0000000000\""
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
        
        DataList<IfaCustomerListA002ResponseDto> appRes = new DataList<IfaCustomerListA002ResponseDto>();
        appRes = cmpIfaCustomerListService.displayA002(appReq);
        
        return jc.toString(appRes);
        
    }
    
}
