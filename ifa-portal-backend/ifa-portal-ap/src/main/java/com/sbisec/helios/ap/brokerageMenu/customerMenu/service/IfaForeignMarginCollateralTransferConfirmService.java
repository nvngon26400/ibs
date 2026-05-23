package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferConfirmA001bDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0305-01_2
 * 画面名：米株信用代用振替確認
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
public interface IfaForeignMarginCollateralTransferConfirmService extends Service {
    
    /**
     * アクションID：A001a
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginCollateralTransferConfirmA001aDtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferConfirmA001aDtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferConfirmA001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferConfirmA001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferConfirmA001aDtoResponse> transferInstructionA001a(
            IfaForeignMarginCollateralTransferConfirmA001aDtoRequest dtoReq) throws Exception;

    /**
     * アクションID：A001b
     * アクション名：振替指示
     * Dto リクエスト：IfaForeignMarginCollateralTransferConfirmA001bDtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferConfirmA001bDtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferConfirmA001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferConfirmA001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferConfirmA001bDtoResponse> transferInstructionA001b(
            IfaForeignMarginCollateralTransferConfirmA001bDtoRequest dtoReq) throws Exception;
}
