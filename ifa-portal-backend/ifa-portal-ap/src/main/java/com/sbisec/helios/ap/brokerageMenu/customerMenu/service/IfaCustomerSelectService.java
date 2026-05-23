package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaCustomerSelectX003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 *
 * @author SCSK
 */
public interface IfaCustomerSelectService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerSelectA001RequestDto
     * Dto レスポンス：IfaCustomerSelectA001ResponseDto
     * model リクエスト：IfaCustomerSelectA001RequestModel
     * model レスポンス：IfaCustomerSelectA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaCustomerSelectA001ResponseDto> initializeA001(IfaCustomerSelectA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：X003
     * アクション名：検索
     * Dto リクエスト：IfaCustomerSelectX003RequestDto
     * Dto レスポンス：IfaCustomerSelectX003ResponseDto
     * model リクエスト：IfaCustomerSelectX003RequestModel
     * model レスポンス：IfaCustomerSelectX003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 検索処理で例外が発生した場合
     */
    public DataList<IfaCustomerSelectX003ResponseDto> searchX003(IfaCustomerSelectX003RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：お気に入り登録・解除
     * Dto リクエスト：IfaCustomerSelectA004RequestDto
     * Dto レスポンス：IfaCustomerSelectA004ResponseDto
     * model リクエスト：IfaCustomerSelectA004RequestModel
     * model レスポンス：IfaCustomerSelectA004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public DataList<Object> favoriteRegisterUnregisterA004(IfaCustomerSelectA004RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A005
     * アクション名：顧客選択
     * Dto リクエスト：IfaCustomerSelectA005DtoRequest
     * Dto レスポンス：IfaCustomerSelectA005DtoResponse
     * model リクエスト：IfaCustomerSelectA005RequestModel
     * model レスポンス：IfaCustomerSelectA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception 顧客選択処理で例外が発生した場合
     */
    public DataList<Object> customerSelectA005(IfaCustomerSelectA005RequestDto dtoReq)
            throws Exception;

}
