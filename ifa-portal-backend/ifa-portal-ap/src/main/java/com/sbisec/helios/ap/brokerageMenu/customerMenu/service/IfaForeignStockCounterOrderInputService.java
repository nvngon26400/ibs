package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignStockCounterOrderInputA008ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0302-02_1
 * 画面名：外国株式店頭注文入力
 * 2024/05/07 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaForeignStockCounterOrderInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignStockCounterOrderInputA001RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA001ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputA001RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputA001ResponseDto> initializeA001(
            IfaForeignStockCounterOrderInputA001RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：更新
     * Dto リクエスト：IfaForeignStockCounterOrderInputA002RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA002ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputA002RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 更新に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputA002ResponseDto> updateA002(
            IfaForeignStockCounterOrderInputA002RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A008
     * アクション名：注文確認
     * Dto リクエスト：IfaForeignStockCounterOrderInputA008RequestDto
     * Dto レスポンス：IfaForeignStockCounterOrderInputA008ResponseDto
     * model リクエスト：IfaForeignStockCounterOrderInputA008RequestModel
     * model レスポンス：IfaForeignStockCounterOrderInputA008ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return 注文確認に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaForeignStockCounterOrderInputA008ResponseDto> orderConfirmA008(
            IfaForeignStockCounterOrderInputA008RequestDto dtoReq
    ) throws Exception;

}
