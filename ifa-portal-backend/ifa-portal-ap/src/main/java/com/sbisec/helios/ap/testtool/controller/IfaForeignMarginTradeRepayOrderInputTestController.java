package com.sbisec.helios.ap.testtool.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbibits.earth.util.json.JsonConverter;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginTradeRepayOrderInputA012ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaForeignMarginTradeRepayOrderInputService;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.common.dto.GwApSharedInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 画面ID：SUB0202_0303-04_1
 * 画面名：米株信用取引返済注文入力
 * @author 松田
 *
 * 2023/11/01 新規作成
 */
@RestController
@SessionCheckTarget(type = "httpSession")
@ScreenId(groupId = "MAIN02", id = "SUB0202_0303-04_1", screenNumber = "")
public class IfaForeignMarginTradeRepayOrderInputTestController {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignMarginTradeRepayOrderInputTestController.class);
    
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
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.bizcommon.component.Fct001.doCheck\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.bizcommon.component.Fct003.doCheck\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockSecurities\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockCreatedMarginOrderInitialization\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.getForeignStockAttentionSecurities\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.createMarketPriceTicket\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.getMarketPriceHash\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignInformationServiceImpl.listMarketPrices\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignAccountServiceImpl.getMarginPositionSummary\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    },"
            + "    {"
            + "        \"testCase\": \"testCase-001\","
            + "        \"methodSignature\": \"com.sbisec.helios.ap.athena.ifa.impl.ForeignStockServiceImpl.confirmForeignStockClosedMarginOrder\","
            + "        \"testData\": \"/work/stubdata/IfaForeignMarginTradeRepayOrderInput/%s\""
            + "    }"
            + "]";
    
    // デフォルト
    private String[] test_case_default = new String[] { "testCase-FCT001.json", "testCase-FCT003.json",
            "testCase-getForeignStockSecurities.json", "testCase-getForeignStockCreatedMarginOrderInitialization.json",
            "testCase-getForeignStockAttentionSecurities.json", "testCase-createMarketPriceTicket.json",
            "testCase-API005.json", "testCase-API006.json", "testCase-API007-getMarginPositionSummary.json",
            "testCase-confirmForeignStockClosedMarginOrder.json" };
    
    // テストケース1
    private String[] test_case_1 = new String[] { 
            "testCase-FCT001.json" // FCT001[0]
            , "testCase-FCT003.json" // FCT003[1]
            , "testCase-getForeignStockSecurities.json" // API001[2]
            , "testCase-getForeignStockCreatedMarginOrderInitialization.json" // API002[3]
            , "testCase-getForeignStockAttentionSecurities.json" // API003[4]
            , "testCase-createMarketPriceTicket.json" // API004[5]
            , "testCase-API005.json" // API005[6]
            , "testCase-API006.json" // API006[7]
            , "testCase-API007-getMarginPositionSummary.json" // API007[8]
            , "testCase-confirmForeignStockClosedMarginOrder.json" // API008[9]
            };
    
            // テストケース2
            private String[] test_case_2 = new String[] { "testCase-FCT001.json" // FCT001[0]
                    , "testCase-FCT003.json" // FCT003[1]
                    , "testCase-getForeignStockSecurities_tradeUnit140.json" // API001[2]
                    , "testCase-getForeignStockCreatedMarginOrderInitialization.json" // API002[3]
                    , "testCase-getForeignStockAttentionSecurities.json" // API003[4]
                    , "testCase-createMarketPriceTicket.json" // API004[5]
                    , "testCase-API005.json" // API005[6]
                    , "testCase-API006.json" // API006[7]
                    , "testCase-API007-getMarginPositionSummary.json" // API007[8]
                    , "testCase-confirmForeignStockClosedMarginOrder.json" // API008[9]
            };
            
    // 銘柄コードとテストケースのMap
    @SuppressWarnings("serial")
    Map<String, String[]> testCaseMap = new HashMap<String, String[]>() {
        
        {
            put("111", test_case_1);
            put("123", test_case_2);
        }
    };
    
    private JsonConverter jc = JsonConverter.getInstance();
    
    @Autowired
    private IfaForeignMarginTradeRepayOrderInputService ifaForeignMarginTradeRepayOrderInputService;
    
    // テストデータ生成
    @SuppressWarnings("unchecked")
    private void init(String brandCode) throws Exception {
        
        // ②JsonConverter#toObjectでデシリアライズ
        CustomerCommon model = new CustomerCommon();
        GwApSharedInfo info = new GwApSharedInfo();
        List<Map<String, String>> stubList = new ArrayList<Map<String, String>>();
        
        String[] stbList = testCaseMap.get(brandCode);
        // 取得できなかった場合はデフォルトのセットを設定する
        if(stbList == null) {
            stbList = test_case_default;
            LOGGER.debug("デフォルトのスタブデータセットを使用======================================");
        }
        
        String stbData = String.format(tempStubData, stbList[0], stbList[1], stbList[2], stbList[3], stbList[4],
                stbList[5], stbList[6], stbList[7], stbList[8], stbList[9]);
        LOGGER.debug("スタブデータ登録json start======================================");
        LOGGER.debug(stbData);
        LOGGER.debug("スタブデータ登録json end========================================");
        
        ObjectMapper mapper = new ObjectMapper();
        try {
            model = mapper.readValue(customerCommon, CustomerCommon.class);
            stubList = mapper.readValue(stbData, List.class);
        } catch (Exception e) {
            // エラー
            e.printStackTrace();
            throw e;
        }
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
    @PostMapping("/brokerageMenu/customerMenu/ifaForeignMarginTradeRepayOrderInputOrderConfirmA012")
    public String orderConfirmA012(@RequestBody IfaForeignMarginTradeRepayOrderInputA012RequestDto appReq)
            throws Exception {
        
        // 銘柄コードで使用するスタブデータを変更する
        init(appReq.getBrandCode());
        
        DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto> appRes = new DataList<IfaForeignMarginTradeRepayOrderInputA012ResponseDto>();
        appRes = ifaForeignMarginTradeRepayOrderInputService.orderConfirmA012(appReq);
        
        return jc.toString(appRes);
    }
    
}
