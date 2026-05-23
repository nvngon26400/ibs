package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputListA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0504_04-01 
 * 画面名：外債買付代金入力一覧
 * 2026/04/14 新規作成
 *
 * @author CuongHD
 *
 */
public interface IfaForeignBondBuyAmountInputListService extends Service
{
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignBondBuyAmountInputListA001RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputListA001ResponseDto
     * model レスポンス：IfaForeignBondBuyAmountInputListSql001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaForeignBondBuyAmountInputListA001ResponseDto> initializeA001(
            IfaForeignBondBuyAmountInputListA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：削除 
     * Dto リクエスト：IfaForeignBondBuyAmountInputListA004RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputListA004ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputListSql002RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputListSql001ResponseModel
     * 
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception Exception データ削除処理で例外が発生した場合
     */
    public DataList<IfaForeignBondBuyAmountInputListA004ResponseDto> deleteA004(
            IfaForeignBondBuyAmountInputListA004RequestDto dtoReq) throws Exception;
}
