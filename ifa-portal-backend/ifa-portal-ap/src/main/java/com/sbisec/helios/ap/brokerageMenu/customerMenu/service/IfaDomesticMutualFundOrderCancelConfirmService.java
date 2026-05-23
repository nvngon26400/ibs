package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0401-04_1
 * 画面名：国内投信注文取消確認
 *
 * @author SCSK
 *
 *     2023/11/27 新規作成
 */
public interface IfaDomesticMutualFundOrderCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundOrderCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderCancelConfirmA001DtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderCancelConfirmA001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA001ResponseDto> initializeA001(IfaDomesticMutualFundOrderCancelConfirmA001RequestDto dtoReq)
            throws Exception;


    /**
     * アクションID：A002
     * アクション名：注文取消（事前登録）
     * Dto リクエスト：IfaDomesticMutualFundOrderCancelConfirmA002DtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderCancelConfirmA002DtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 注文取消処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> orderCancellationA002a(IfaDomesticMutualFundOrderCancelConfirmA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文取消（本登録）
     * Dto リクエスト：IfaDomesticMutualFundOrderCancelConfirmA002DtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderCancelConfirmA002DtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderCancelConfirmA002RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderCancelConfirmA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 注文取消処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundOrderCancelConfirmA002ResponseDto> orderCancellationA002b(IfaDomesticMutualFundOrderCancelConfirmA002RequestDto dtoReq)
            throws Exception;
    
}
