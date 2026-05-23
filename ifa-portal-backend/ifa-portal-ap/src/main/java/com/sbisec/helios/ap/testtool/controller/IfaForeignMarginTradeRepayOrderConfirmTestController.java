package com.sbisec.helios.ap.testtool.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeRepayOrderConfirmService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.common.dto.GwApSharedInfo;

/**
 * 画面ID：SUB0202_0303-04_2
 * 画面名：米株信用取引返済注文確認
 * @author 松田
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-04_2", screenNumber = "145")
public class IfaForeignMarginTradeRepayOrderConfirmTestController {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginTradeRepayOrderConfirmTestController.class);
    
    // CustomerCommon情報
    private String customerCommon = "{"
            + "        \"customerCode\": \"111\","
            + "        \"butenCode\": \"112\","
            + "        \"accountNumber\": \"113\","
            + "        \"customerNameKanji\": \"114\","
            + "        \"customerNameKana\": \"115\","
            + "        \"complianceLank\": \"116\","
            + "        \"customerAttribute\": \"117\","
            + "        \"customerAttributeName\": \"118\","
            + "        \"dealerNumber\": \"1\","
            + "        \"corBirthFlg\": \"120\","
            + "        \"age\": \"121\","
            + "        \"brokerCode\": \"122\","
            + "        \"brokerChargeCode\": \"123\","
            + "        \"corporationType\": \"124\","
            + "        \"homePhoneNumbar\": \"125\","
            + "        \"phoneNumber2\": \"126\","
            + "        \"representativeName\": \"127\","
            + "        \"representativeNameKana\": \"128\","
            + "        \"agentName\": \"129\","
            + "        \"agentNameKana\": \"130\","
            + "        \"titleOfAgent\": \"131\","
            + "        \"specificAccountType\": \"1\","
            + "        \"dividendReceiptType\": \"1\","
            + "        \"taxExemptQualifiedPersonType\": \"1\","
            + "        \"isaContractType\": \"1\","
            + "        \"jrIsaContractType\": \"1\","
            + "        \"jrNisaAccountNumber\": \"137\","
            + "        \"jrTokuteiKouzaKbn\": \"1\","
            + "        \"brokerName\": \"139\","
            + "        \"brokerBranchName\": \"140\","
            + "        \"brokerChargeName\": \"141\","
            + "        \"noticeInfoLevel\": \"142\","
            + "        \"ifaMemoUpdateDateTime\": \"143\","
            + "        \"ifaMemoContent\": \"144\","
            + "        \"isaBuyLimitYear\": \"145\","
            + "        \"isaBuyLimitYearNext\": \"146\","
            + "        \"isaBuyAbleYear\": \"147\","
            + "        \"isaBuyAbleYearNext\": \"148\","
            + "        \"nisaType\": \"1\","
            + "        \"nisaTypeYearNext\": \"1\","
            + "        \"isaBuyLimitYearJuniorNisa\": \"151\","
            + "        \"isaBuyLimitYearNextJuniorNisa\": \"152\","
            + "        \"isaBuyAbleYearJuniorNisa\": \"153\","
            + "        \"isaBuyAbleYearNextJuniorNisa\": \"154\","
            + "        \"withdrawalRestrictionCancelFlag\": \"1\","
            + "        \"yenBuyingPower\": \"156\","
            + "        \"domesticMarginAccountType\": \"1\","
            + "        \"newBuildingCapacity\": \"158\","
            + "        \"yenCustomerInfoDetentionRate\": \"159\","
            + "        \"foreignBuyPowerFlag\": \"160\","
            + "        \"foreignBuyingPowerCountryCount\": \"161\","
            + "        \"foreignNewBuildingCapacity\": \"162\","
            + "        \"foreignCustomerInfoDetentionRate\": \"163\","
            + "        \"foreignStockTradeAccountOpenStatus\": \"1\","
            + "        \"foreignSecurityTradeAccountOpenStatus\": \"1\","
            + "        \"foreignMarginAccountType\": \"1\","
            + "        \"ccsMemo\": \"167\","
            + "        \"customerInfoCourse\": \"168\""
            + "}";
    
    // スタブデータ
    private String tempStubData = "["
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.bizcommon.component.Fct001.doCheck\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.bizcommon.component.Fct003.doCheck\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.bizcommon.component.Fct021.doCheck\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockSecurities\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockAttentionSecurities\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\"" + "}," + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.listMarketPrices\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.closeForeignStockMarginOrder\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockCreatedMarginOrderInitialization\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignAccountServiceImpl.getMarginPositionSummary\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.createMarketPriceTicket\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "},"
            + "{"
            + "    \"testCase\": \"testCase-001\","
            + "    \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.getMarketPriceHash\","
            + "    \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderConfirm/%s\""
            + "}"
            + "]";
    
    // userAccount
    private String userAccountTypeJson = "{" 
            + "        \"medUsers\": {"
            + "            \"lastpwuptime\": 1683532706000," 
            + "            \"privId\": \"%s\","
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
            + "                \"SUB002\"," + "\"0000000001\","
            + "                \"SUB0201_01-01\","
            + "                \"MAIN02\"" + "            ]" 
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
            + "                \"accControl\": 1" + "            },"
            + "            {" 
            + "                \"menuId\": \"SUB005\","
            + "                \"itemId\": \"jointBranchCode\"," 
            + "                \"userId\": \"111\","
            + "                \"accControl\": 1" 
            + "            }" 
            + "        ]" 
            + "}";
    
    // デフォルト
    private String[] test_case_default = new String[] { 
            "testCase-FCT001.json" // FCT001[0]
            ,"testCase-FCT003.json" // FCT003[1]
            , "testCase-FCT021.json" // FCT021[2]
            , "api001-getForeignStockSecurities.json" // API001[3]
            , "api002-getForeignStockAttentionSecurities.json" // API002[4]
            , "api003-listMarketPrices.json" // API003[5]
            , "api004-closeForeignStockMarginOrder.json" // API004[6]
            , "api005-getForeignStockCreatedMarginOrderInitialization.json" // API005[7]
            , "api006-getMarginPositionSummary.json" // API006[8]
            , "api007-createMarketPriceTicket.json" // API007[9]
            , "api008-GetMarketPriceHash.json" // API008[10]
            };
    
    // テストケース1
    private static String[] test_case_1 = new String[] {
            "testCase-FCT001.json" // FCT001[0]
            , "testCase-FCT003.json" // FCT003[1]
            , "testCase-FCT021.json" // FCT021[2]
            , "api001-getForeignStockSecurities_maxLimit0.json" // API001[3]
            , "api002-getForeignStockAttentionSecurities.json" // API002[4]
            , "api003-listMarketPrices.json" // API003[5]
            , "api004-closeForeignStockMarginOrder.json" // API004[6]
            , "api005-getForeignStockCreatedMarginOrderInitialization.json" // API005[7]
            , "api006-getMarginPositionSummary.json" // API006[8]
            , "api007-createMarketPriceTicket.json" // API007[9]
            , "api008-GetMarketPriceHash.json" // API008[10]
            };
    

        
    /**
     * テストケースに合わせて（銘柄コード,スタブデータセット,権限コード）　をセットする
     *
     */
    private enum TestCaseEnum {
        
        CASE_1("111", test_case_1, "3");
        
        private final String brandCode;
        
        private final String[] stubData;
        
        private final String privId;
        
        private TestCaseEnum(String brandCode, String[] stubData, String privId) {
            
            this.brandCode = brandCode;
            this.stubData = stubData;
            this.privId = privId;
        }
        
        public String getBrandCode() {
            
            return brandCode;
        }
        
        public String[] getStubData() {
            
            return stubData;
        }
        
        public String getPrivId() {
            
            return privId;
        }
        
        public static TestCaseEnum valueOfBrandCode(String brandCode) {
            
            TestCaseEnum[] enums = values();
            
            for (int i = 0; i < enums.length; i++) {
                if (StringUtils.equals(enums[i].getBrandCode(), brandCode))
                    return enums[i];
            }
            
            return null;
        }
    }
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private IfaForeignMarginTradeRepayOrderConfirmService ifaForeignMarginTradeRepayOrderConfirmService;
    
    // テストデータ生成
    @SuppressWarnings("unchecked")
    private void init(String brandCode) throws Exception {
        
        // ②JsonConverter#toObjectでデシリアライズ
        CustomerCommon model = new CustomerCommon();
        GwApSharedInfo info = new GwApSharedInfo();
        List<Map<String, String>> stubList = new ArrayList<Map<String, String>>();
        String[] stbList = null;
        String privId = null;
        // 取得できなかった場合はデフォルトのセットを設定する
        TestCaseEnum tc = TestCaseEnum.valueOfBrandCode(brandCode);
        if (tc == null) {
            stbList = test_case_default;
            privId = "3";
            LOGGER.debug("デフォルトのスタブデータセットを使用======================================");
        } else {
            stbList = tc.getStubData();
            privId = tc.getPrivId();
        }
        String stbData = String.format(tempStubData, stbList[0], stbList[1], stbList[2], stbList[3], stbList[4],
                stbList[5], stbList[6], stbList[7], stbList[8], stbList[9], stbList[10]);
        LOGGER.debug("スタブデータ登録json start======================================");
        LOGGER.debug(stbData);
        LOGGER.debug("スタブデータ登録json end========================================");
        
        String userAccountData = String.format(userAccountTypeJson, privId);
        UserAccount userAccount = jc.toObject(userAccountData, UserAccount.class);
        
        try {
            model = jc.toObject(customerCommon, CustomerCommon.class);
            stubList = jc.toObject(stbData, List.class);
        } catch (Exception e) {
            // エラー
            e.printStackTrace();
            throw e;
        }
        // ユーザ共通情報のオブジェクトをリクエストスコープにセット
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
        // 顧客共通情報をリクエストスコープに設定する
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_CUSTOMER_COMMON, model);
        // スタブデータをリクエストスコープに設定する
        info.setApiStubTestDataList(stubList);
        info.setApiStubTargetTestCase("testCase-001");
        IfaCommonUtil.setRequestAttribute(IfaCommonUtil.ATTR_KEY_GW_AP_SHARED_INFO, info);
    }
    
    /**
     * 
     * アクセス：/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputOrderConfirmA012
     * アクションID：A012
     * アクション名：注文確認
     * APIリクエスト：IfaForeignMarginTradeRepayOrderInputA012ApiRequest
     * Apiレスポンス：IfaForeignMarginTradeRepayOrderInputA012ApiResponse
     * Dtoリクエスト：IfaForeignMarginTradeRepayOrderInputA012DtoRequest
     * Dtoレスポンス：IfaForeignMarginTradeRepayOrderInputA012DtoResponse
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderConfirmOrderPlacementA010")
    public String orderPlacementA010(@RequestBody IfaForeignMarginTradeRepayOrderConfirmA010aRequestDto appReq)
            throws Exception {
        
        // 銘柄コードで使用するスタブデータを変更する
        init(appReq.getBrandCode());
        
        IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto appReqB = new IfaForeignMarginTradeRepayOrderConfirmA010bRequestDto();
        BeanUtils.copyProperties(appReqB, appReq);
        
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto> appResA = new DataList<IfaForeignMarginTradeRepayOrderConfirmA010aResponseDto>();
        appResA = ifaForeignMarginTradeRepayOrderConfirmService.orderPlacementA010a(appReq);
        
        if (appResA.getErrorLevel() != ErrorLevel.SUCCESS.getId()) {
            return jc.toString(appResA);
        }
        appReqB.setIfaOrderNo(appResA.getDataList().get(0).getIfaOrderNo());
        appReqB.setTradeLimit(appResA.getDataList().get(0).getTradeLimit());
        appReqB.setBrandClass(appResA.getDataList().get(0).getBrandClass());
        
        DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto> appResB = new DataList<IfaForeignMarginTradeRepayOrderConfirmA010bResponseDto>();
        appResB = ifaForeignMarginTradeRepayOrderConfirmService.orderPlacementA010b(appReqB);
        
        return jc.toString(appResB);
    }
    
}
