package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCustomerDestinationBankAccountA004aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */
public interface IfaCustomerDestinationBankAccountService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCustomerDestinationBankAccountA002DtoRequest
     * Dto レスポンス：IfaCustomerDestinationBankAccountA002DtoResponse
     * model リクエスト：IfaCustomerDestinationBankAccountA002RequestModel
     * model レスポンス：IfaCustomerDestinationBankAccountA002ResponseModel

     * @param dtoReq リクエスト
     * @return IfaCustomerDestinationBankAccountA002DtoResponse
     * @exception Exception SQLExceptionなど 
     */
    public DataList<IfaCustomerDestinationBankAccountA002DtoResponse> displayA002(
            IfaCustomerDestinationBankAccountA002DtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerDestinationBankAccountA004DtoRequest
     * Dto レスポンス：IfaCustomerDestinationBankAccountA004DtoResponse
     * model リクエスト：IfaCustomerDestinationBankAccountA004RequestModel
     * model レスポンス：IfaCustomerDestinationBankAccountA004ResponseModel

     * @param dtoReq リクエスト
     * @return IfaCustomerDestinationBankAccountA004aDtoResponse
     * @exception Exception SQLExceptionなど 
     */
    public DataList<IfaCustomerDestinationBankAccountA004aDtoResponse> csvOutputA004a(
            IfaCustomerDestinationBankAccountA004aDtoRequest dtoReq, String fwSessionId) throws Exception;
    
}
