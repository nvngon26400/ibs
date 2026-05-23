package com.sbisec.helios.ap.bizcommon.component;

import com.sbisec.helios.ap.bizcommon.dao.Fct023Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct023Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct023Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct023Dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 共通関数：FCT023 国内投信銘柄マスタ取得
 *
 * @author 陳
 */
@Component
public class Fct023 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct023.class);
    
    private static final String SPACE = " ";
    
    @Autowired
    private Fct023Dao fct023Dao;
    
    /**
     * 投信銘柄マスタから、指定銘柄の販売や解約にかかわる各種情報を取得する。
     *
     * @param input リクエストDTO
     * @return レスポンスDTO
     */
    public OutputFct023Dto getData(InputFct023Dto input) {
        
        // ログ出力
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Fct023.getData");
        }
        
        // レスポンスDTO
        OutputFct023Dto output = new OutputFct023Dto();
        
        // 必須項目について、入力値の有無チェック、ないの場合は空DTOをリターンする
        if (input == null || input.getNriCd() == null) {
            return output;
        }
        
        // SQL001リクエストモデル
        Fct023Sql001RequestModel sql001Req = new Fct023Sql001RequestModel();
        sql001Req.setNriCd(input.getNriCd() + SPACE);
        
        // 投信銘柄マスタから、指定銘柄の販売や解約にかかわる各種情報を取得する。
        Fct023Sql001ResponseModel sql001Res = fct023Dao.getFct023Sql001(sql001Req);
        
        // リクエスト.NRIコードをレスポンスにセットする。
        output.setNriCd(input.getNriCd());
        
        if (sql001Res != null) {
            // 取得し各種情報をレスポンスにセットする。
            output.setFundOfficalName(sql001Res.getFundOfficalName());
            output.setBasePriceUnit(sql001Res.getBasePriceUnit());
            output.setNumberOfSalesMinimum(sql001Res.getNumberOfSalesMinimum());
            output.setSalesMinimunUnitPriceAfter2nd(sql001Res.getSalesMinimunUnitPriceAfter2nd());
            output.setNumberOfSalesUnit(sql001Res.getNumberOfSalesUnit());
            output.setSalesTradingUnitPriceAfter2nd(sql001Res.getSalesTradingUnitPriceAfter2nd());
            output.setNumberOfCancelTradingUnit(sql001Res.getNumberOfCancelTradingUnit());
            output.setCancelTradingUnitPriceAfter2nd(sql001Res.getCancelTradingUnitPriceAfter2nd());
            output.setOrderDeadlineTime(sql001Res.getOrderDeadlineTime());
            output.setSaleMethod(sql001Res.getSaleMethod());
            output.setFundType(sql001Res.getFundType());
            output.setReinvestmentDivison(sql001Res.getReinvestmentDivison());
            output.setSpecialType(sql001Res.getSpecialType());
        }
        
        return output;
    }
}
