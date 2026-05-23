package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.model.AthenaErrorMessageModel;
import com.sbisec.helios.ap.athena.protocol.fstock.order.GetMarginAccountAutoTransferSettingResp;
import com.sbisec.helios.ap.athena.utils.AthenaBusinessException;
import com.sbisec.helios.ap.bizcommon.model.InputFct035Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct035Dto;
import com.sbisec.helios.ap.common.model.CustomerCommon;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.service.CometCommonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT035 自動振替設定取得
 *
 * @author SCSK
 */

@Component
public class Fct035 {
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    @Autowired
    private CometCommonService cometCommonService;

    private static final Logger LOGGER = LoggerFactory.getLogger(Fct035.class);
    
    /**
     * 自動振替設定取得
     *
     * @param input リクエストDto
     * @return レスポンスDto
     * @throws Exception 自動振替設定取得時に例外が発生した場合
     */
    public OutputFct035Dto doCheck(InputFct035Dto input) throws Exception  {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct035.doCheck");
        }
        
        // 顧客共通情報の取得
        CustomerCommon cc = IfaCommonUtil.getCustomerCommon();
        
        //リクエストの必須パラメタチェック
        if (input.getButenCode() == null || input.getAccountNumber() == null) {
            return new OutputFct035Dto();
        }
        
        //返却想定データの初期化
        OutputFct035Dto resDto = new OutputFct035Dto();
        
        //API001 Response
        GetMarginAccountAutoTransferSettingResp result = new GetMarginAccountAutoTransferSettingResp();
        
        try {
            result = foreignStockService.getMarginAccountAutoTransferSetting(cc.getButenCode(), cc.getAccountNumber());
        } catch (Exception e) {
            LOGGER.error("Exception occured.");
            LOGGER.info("Exception occured.", e);
            if (e instanceof AthenaBusinessException) {
                String errorCode = ((AthenaBusinessException) e).getErrorCode();
                AthenaErrorMessageModel apiStatusModel = cometCommonService.getAthenaErrorCodeAndMessage(errorCode);
                ((AthenaBusinessException) e).setMessage(apiStatusModel.getErrorMessage());
            }
            throw e;
        }
        
        //② ①で取得した内容をレスポンス項目に設定する。
        //レスポンス.建余力不足_自動振替設定（米ドル）フラグ　←　①で取得した　建余力不足 自動振替設定(米ドル)
        resDto.setCapacityShortageAutoTransferSettingUSDFlag(result.getConvMarginBuyingPowerShortfallCash());
        
        //レスポンス.建余力不足_自動振替設定（米国株式）フラグ←　①で取得した　建余力不足_自動振替設定（米国株式）
        resDto.setCapacityShortageAutoTransferSettingUSStocksFlag(result.getConvMarginBuyingPowerShortfallSecurities());
        
        return resDto;
    }
}
