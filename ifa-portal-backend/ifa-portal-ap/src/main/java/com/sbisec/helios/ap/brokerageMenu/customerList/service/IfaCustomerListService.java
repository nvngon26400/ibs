package com.sbisec.helios.ap.brokerageMenu.customerList.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaCustomerListA006ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本
 *
 * @author SCSK池田
 *
   2023/09/13 新規作成
 */
public interface IfaCustomerListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCustomerListA001DtoRequest
     * Dto レスポンス：IfaCustomerListA001DtoResponse
     * model リク エスト：IfaCustomerListA001RequestModel
     * model レスポンス：IfaCustomerListA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListSql004ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListSql004ResponseModel> initializeA001(IfaCustomerListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerListA002DtoRequest
     * Dto レスポンス：IfaCustomerListA002DtoResponse
     * model リクエスト：IfaCustomerListA002RequestModel
     * model レスポンス：IfaCustomerListA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA002ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA002ResponseDto> displayA002(IfaCustomerListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerListA005DtoRequest
     * Dto レスポンス：IfaCustomerListA005DtoResponse
     * model リクエスト：IfaCustomerListA005RequestModel
     * model レスポンス：IfaCustomerListA005ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA005ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA005ResponseDto> csvOutputA005(IfaCustomerListA005RequestDto dtoReq, String sessionId)
            throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：BB申込
     * Dto リクエスト：IfaCustomerListA006DtoRequest
     * Dto レスポンス：IfaCustomerListA006DtoResponse
     * model リクエスト：IfaCustomerListA006RequestModel
     * model レスポンス：IfaCustomerListA006ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaCustomerListA006ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaCustomerListA006ResponseDto> bbApplyA006(IfaCustomerListA006RequestDto dtoReq) throws Exception;
    
}
