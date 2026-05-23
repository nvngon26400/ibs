package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDepositBalanceDetailA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010201-04
 * 画面名：預り残高詳細

 * @author 秋山
 */
public interface IfaDepositBalanceDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDepositBalanceDetailA001DtoRequest
     * Dto レスポンス：IfaDepositBalanceDetailA001DtoResponse
     * model リクエスト：IfaDepositBalanceDetailA001RequestModel
     * model レスポンス：IfaDepositBalanceDetailA001ResponseModel

     * @param dtoReq リクエスト情報
     * @return IfaDepositBalanceDetailA001ResponseDto レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailA001ResponseDto> initializeA001(IfaDepositBalanceDetailA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A004
     * アクション名：更新
     * Dto リクエスト：IfaDepositBalanceDetailA004DtoRequest
     * Dto レスポンス：IfaDepositBalanceDetailA004DtoResponse
     * model リクエスト：IfaDepositBalanceDetailA004RequestModel
     * model レスポンス：IfaDepositBalanceDetailA004ResponseModel

     * @param dtoReq リクエスト情報
     * @return IfaDepositBalanceDetailA004ResponseDto レスポンス情報
     * @exception Exception 例外発生時
     */
    public DataList<IfaDepositBalanceDetailA004ResponseDto> updateA004(IfaDepositBalanceDetailA004RequestDto dtoReq)
            throws Exception;

}
