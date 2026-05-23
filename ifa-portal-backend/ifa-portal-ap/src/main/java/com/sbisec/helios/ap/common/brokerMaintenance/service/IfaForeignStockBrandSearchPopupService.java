package com.sbisec.helios.ap.common.brokerMaintenance.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignStockBrandSearchPopupA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB07-04
 * 画面名：外株銘柄検索ポップアップ
 *
 * @author SCSK
 */
public interface IfaForeignStockBrandSearchPopupService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：検索
     * Dto リクエスト：IfaForeignStockBrandSearchPopupA002DtoRequest
     * Dto レスポンス：IfaForeignStockBrandSearchPopupA002DtoResponse
     * model リクエスト：IfaForeignStockBrandSearchPopupA002RequestModel
     * model レスポンス：IfaForeignStockBrandSearchPopupA002ResponseModel
     *
     * @param dtoReq {@code IfaForeignStockBrandSearchPopupA002RequestDto}
     * @return dtoRes
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignStockBrandSearchPopupA002ResponseDto> searchA002(
            IfaForeignStockBrandSearchPopupA002RequestDto dtoReq) throws Exception;
    
}
