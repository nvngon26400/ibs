package com.sbisec.helios.ap.common.brokerMaintenance.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.athena.ifa.ForeignStockService;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ForeignStock;
import com.sbisec.helios.ap.athena.protocol.fstock.dto.ShortableStock;
import com.sbisec.helios.ap.athena.protocol.fstock.order.ListShortableStocksResp;
import com.sbisec.helios.ap.athena.protocol.fstock.securities.ListForeignStockSecuritiesResp;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002ResponseDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting;
import com.sbisec.helios.ap.common.brokerMaintenance.service.IfaForeignStockBrandSearchPopupService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.RtnCdEnum;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB07-04
 * 画面名：外株銘柄検索ポップアッ
 *
 * @author SCSK
    2024/01/09 新規作成
 */
@Component(value = "cmpIfaForeignStockBrandSearchPopupService")
public class IfaForeignStockBrandSearchPopupServiceImpL implements IfaForeignStockBrandSearchPopupService {
    
    @Autowired
    private ForeignStockService foreignStockService;
    
    // 信用新規売
    private static final String FOREIGN_STOCK_TRADE_CLASS_3 = "3";
    
    // 信用新規買
    private static final String FOREIGN_STOCK_TRADE_CLASS_2 = "2";
    
    // 買建可能
    private static final String CAN_BUY = "CAN_BUY";
    
    private static final String NOT_FOUND = "errors.dataList.notfound";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignStockBrandSearchPopupServiceImpL.class);
    
    /**
     * アクションID：A002
     * アクション名：検索
     * Dto リクエスト：IfaForeignStockBrandSearchPopupA002DtoRequest
     * Dto レスポンス：IfaForeignStockBrandSearchPopupA002DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     *
     * @param dtoReq {@code IfaForeignStockBrandSearchPopupA002RequestDto}
     * @return dtoRes
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignStockBrandSearchPopupA002ResponseDto> searchA002(
            IfaForeignStockBrandSearchPopupA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignStockBrandSearchPopupA002ResponseDto> dtoRes = new DataList<IfaForeignStockBrandSearchPopupA002ResponseDto>();
        List<IfaForeignStockBrandSearchPopupA002ResponseDto> dtoResList = new ArrayList<IfaForeignStockBrandSearchPopupA002ResponseDto>();
        IfaForeignStockBrandSearchPopupA002ResponseDto a002Dto = new IfaForeignStockBrandSearchPopupA002ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignStockBrandSearchPopupServiceImplL.searchA002");
        }
        
        // 遷移元画面.取引種別="新規売"の場合
        if (FOREIGN_STOCK_TRADE_CLASS_3.equals(dtoReq.getTradeCd())) {
            // API002 「外国株式信用売建可能銘柄一覧取得API 」を利用して、銘柄一覧および在庫株数を取得する。
            ListShortableStocksResp api002Res = foreignStockService.listShortableStocks(dtoReq.getCountryCode(),
                    dtoReq.getBrandNameBrandCode(), null, null);
            
            if (api002Res.getShortableStocks().isEmpty()) {
                // 検索結果が0件の場合、エラーメッセージを表示する
                dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, NOT_FOUND,
                        IfaCommonUtil.getMessage(NOT_FOUND));
                return dtoRes;
            }
            
            List<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell> marginNewSellList = new ArrayList<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell>();
            
            for (ShortableStock shortableStock : api002Res.getShortableStocks()) {
                
                IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell marginNewSellResDto = new IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSell();
                
                marginNewSellResDto.setBrandCode(shortableStock.getForeignStock().getSecurities().getSecuritiesCode());
                marginNewSellResDto.setBrandName(shortableStock.getForeignStock().getSecurities().getSecuritiesName());
                marginNewSellResDto.setCountryCode(shortableStock.getForeignStock().getSecurities().getCountryCode());
                marginNewSellResDto.setMarketCode(shortableStock.getForeignStock().getMarket().getMarketCode());
                marginNewSellResDto
                        .setMarketAbbreviatedName(shortableStock.getForeignStock().getMarket().getMarketShortName());
                marginNewSellResDto.setPositionQuantity(shortableStock.getPositionQuantity());
                
                marginNewSellList.add(marginNewSellResDto);
            }
            
            a002Dto.setMarginNewSellList(marginNewSellList);
            dtoResList.add(a002Dto);
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
            
            return dtoRes;
        } else {
            // 遷移元画面.取引種別="新規売"以外の場合
            String marginCode = null;
            if (FOREIGN_STOCK_TRADE_CLASS_2.equals(dtoReq.getTradeCd())) {
                marginCode = CAN_BUY;
            }
            // API001 「外国株式銘柄検索API」を利用して、銘柄検索情報を取得する。
            ListForeignStockSecuritiesResp api001Res = foreignStockService.listForeignStockSecurities(
                    dtoReq.getCountryCode(), dtoReq.getBrandNameBrandCode(), dtoReq.getSearchOptions(),
                    dtoReq.getMarket(), null, marginCode);
            
            if (api001Res.getForeignStocks().isEmpty()) {
                // 検索結果が0件の場合、エラーメッセージを表示する
                dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.FATAL, NOT_FOUND,
                        IfaCommonUtil.getMessage(NOT_FOUND));
                return dtoRes;
            }
            
            List<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting> marginNewSellExceptingList = new ArrayList<IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting>();
            
            for (ForeignStock foreignStock : api001Res.getForeignStocks()) {
                IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting marginNewSellExceptingResDto = new IfaForeignStockBrandSearchPopupA002ResponseDto_MarginNewSellExcepting();
                
                marginNewSellExceptingResDto.setBrandCode(foreignStock.getSecurities().getSecuritiesCode());
                marginNewSellExceptingResDto.setBrandName(foreignStock.getSecurities().getSecuritiesName());
                marginNewSellExceptingResDto.setCountryCode(foreignStock.getSecurities().getCountryCode());
                marginNewSellExceptingResDto.setMarketCode(foreignStock.getMarket().getMarketCode());
                marginNewSellExceptingResDto.setMarketAbbreviatedName(foreignStock.getMarket().getMarketShortName());
                
                marginNewSellExceptingList.add(marginNewSellExceptingResDto);
            }
            
            a002Dto.setMarginNewSellExceptingList(marginNewSellExceptingList);
            dtoResList.add(a002Dto);
            dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, RtnCdEnum.SUCCESS.getText(), "");
            
            return dtoRes;
        }
    }
}
