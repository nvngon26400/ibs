package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaDepositWithdrawDetailA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0203-01
 * 画面名：入出金明細
 *
 */
public interface IfaDepositWithdrawDetailService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaDepositWithdrawDetailA002RequestDto
     * Dto レスポンス：IfaDepositWithdrawDetailA002ResponseDto
     * model リクエスト：IfaDepositWithdrawDetailSql001RequestModel
     * model レスポンス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDepositWithdrawDetailA002ResponseDto> displayA002(IfaDepositWithdrawDetailA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaDepositWithdrawDetailA004RequestDto
     * Dto レスポンス：IfaDepositWithdrawDetailA004ResponseDto
     * model リクエスト：IfaDepositWithdrawDetailSql001RequestModel
     * model レスポンス：IfaDepositWithdrawDetailSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return dtoRes レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDepositWithdrawDetailA004ResponseDto> csvOutputA004(
            IfaDepositWithdrawDetailA004RequestDto dtoReq, String fwSessionId) throws Exception;
    
}
