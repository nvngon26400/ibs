package com.sbisec.helios.ap.brokerageMenu.customerMenu.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaExternalLinkDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.service.IfaExternalLinkService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 外部リンク
 * 2025/05/14 新規作成
 *
 * @author 大連 葉
 */
@Component(value="cmpIfaExternalLinkService")
public class IfaExternalLinkServiceImpL implements IfaExternalLinkService{

    private static final Logger LOGGER = LoggerFactory.getLogger(IfaExternalLinkServiceImpL.class);

    @Autowired
    private IfaExternalLinkDao dao;

    /** 分類名 - 国内株式 */
    private static final String DOMESTIC_STOCK = "国内株式";

    /** 分類名 - 投資信託 */
    private static final String INVESTMENT_TRUST = "投資信託";

    /** 分類名 - 債券 */
    private static final String DOMESTIC_BONDS = "債券";

    /** 分類名 - ST（セキュリティトークン） */
    private static final String SECURITY_TOKEN = "ST(セキュリティ・トークン)";

    /** 分類名 - SBIラップ */
    private static final String SBI_WRAP = "SBIラップ";

    /** 分類名 - 口座管理 */
    private static final String ACCOUNT_MANAGEMENT = "口座管理";

    /** 分類名 - 入出金 */
    private static final String DEPOSIT_WITH_DRAW = "入出金・振替";

    /** 分類名 - 新生銀行関連サービス（社員・新生銀行用） */
    private static final String SHINSEIBANK_SERVICE = "新生銀行関連サービス(社員・新生銀行用)";

    /** 分類名 - 外貨建商品取引サイト */
    private static final String FOREIGN_SECURITY_TRADE_SITE = "外貨建商品取引サイト";

    /** 分類名 - その他 */
    private static final String OTHERS = "その他";

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaExternalLinkDtoRequest
     * Dto レスポンス：IfaExternalLinkDtoResponse
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaExternalLinkDtoResponse> initializeA001(IfaExternalLinkDtoRequest dtoReq) throws Exception {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaExternalLinkServiceImpL.initializeA001");
        }
        DataList<IfaExternalLinkDtoResponse> dtoRes = new DataList<IfaExternalLinkDtoResponse>();
        List<IfaExternalLinkDtoResponse> resList = new ArrayList<IfaExternalLinkDtoResponse>();
        IfaExternalLinkDtoResponse res = new IfaExternalLinkDtoResponse();

        // ①店群情報の取得を行う。
        // 顧客情報の取得
        CustomerCommon customerCommon = IfaCommonUtil.getCustomerCommon();
        String butenCode = customerCommon.getButenCode();
        String accountNumber = customerCommon.getAccountNumber();
        String customerAttribute = customerCommon.getCustomerAttribute();
        String brokerCode = customerCommon.getBrokerCode();
        // SQL001.店群情報取得
        String tenGunId = dao.selectIfaExternalLinkSql001(butenCode);

        // ②CCSログインIDとして設定する文字列の生成 （ユーザID用設定値）
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        String ccsOpId = userAccount.getUserId();
        if (StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId()) || StringUtils.equals(privId, PrivId.BRANCH.getId())) {
            ccsOpId = ccsOpId.length() >= 7 ? ccsOpId.substring(ccsOpId.length()-7) + "@" : ccsOpId + "@";
        } else {
            ccsOpId = ccsOpId.length() >= 8 ? ccsOpId.substring(ccsOpId.length()-8) : ccsOpId;
        }
        // 分類エリアの取得
        List<String> category = new ArrayList<String>();
        category.add(DOMESTIC_STOCK);
        category.add(INVESTMENT_TRUST);
        category.add(DOMESTIC_BONDS);
        if(!(customerAttribute.equals("A") || customerAttribute.equals("B") || customerAttribute.equals("D"))) {
            category.add(SECURITY_TOKEN);
            category.add(SBI_WRAP);
        }
        category.add(ACCOUNT_MANAGEMENT);
        category.add(DEPOSIT_WITH_DRAW);
        if("1".equals(privId) || "2".equals(privId) || "1133".equals(brokerCode)) {
            category.add(SHINSEIBANK_SERVICE);
        }
        if(!(customerAttribute.equals("A") || customerAttribute.equals("B") || customerAttribute.equals("D"))) {
            category.add(FOREIGN_SECURITY_TRADE_SITE);
        }
        category.add(OTHERS);
        // レスポンスの設定
        res.setCategoryList(category);
        res.setButenCode(butenCode);
        res.setAccountNumber(accountNumber);
        res.setTenGunId(tenGunId);
        res.setCcsOpId(ccsOpId);
        res.setCustomerAttribute(customerAttribute);
        resList.add(res);
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), StringUtil.EMPTY_STRING);
        return dtoRes;
    }

}
