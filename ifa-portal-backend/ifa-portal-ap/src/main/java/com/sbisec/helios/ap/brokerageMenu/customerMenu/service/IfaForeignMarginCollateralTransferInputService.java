package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA003DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaForeignMarginCollateralTransferInputA004DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0305-01_1
 * 画面名：米株信用代用振替入力
 *
 * @author SCSK川崎
 *
   2024/03/19 新規作成
 */
public interface IfaForeignMarginCollateralTransferInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA001DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA001DtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferInputA001RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferInputA001ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA001DtoResponse> initializeA001(
            IfaForeignMarginCollateralTransferInputA001DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：結果を表示
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA003DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA003DtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferInputA003RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferInputA003ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA003DtoResponse> displayResultA003(
            IfaForeignMarginCollateralTransferInputA003DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：振替確認
     * Dto リクエスト：IfaForeignMarginCollateralTransferInputA004DtoRequest
     * Dto レスポンス：IfaForeignMarginCollateralTransferInputA004DtoResponse
     * model リクエスト：IfaForeignMarginCollateralTransferInputA004RequestModel
     * model レスポンス：IfaForeignMarginCollateralTransferInputA004ResponseModel
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaForeignMarginCollateralTransferInputA004DtoResponse> transferConfirmA004(
            IfaForeignMarginCollateralTransferInputA004DtoRequest dtoReq) throws Exception;
    
}
