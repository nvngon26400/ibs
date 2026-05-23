package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaForeignStockCounterOrderConfirmService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaForeignStockCounterOrderConfirmA001RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderConfirmA001ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderConfirmA001RequestModel
     * model レスポンス：IfaForeignStockCounterOrderConfirmA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 注文発注に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderConfirmA001ResponseDto> orderPlacementA001(
            IfaForeignStockCounterOrderConfirmA001RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaForeignStockCounterOrderConfirmA002RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderConfirmA002ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderConfirmA002RequestModel
     * model レスポンス：IfaForeignStockCounterOrderConfirmA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderConfirmA002ResponseDto> updateA002(
            IfaForeignStockCounterOrderConfirmA002RequestDto dtoReq
    ) throws Exception;

}
