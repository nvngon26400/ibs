package com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.tradeDataManage.dto.IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0504_04-02_1 
 * 画面名：外債買付代金入力情報登録
 *
 * @author SBIFPT 2026/04/13 CuongHD
 */
public interface IfaForeignBondBuyAmountInputInfoRegisterService extends Service
{
    
    /**
     * アクションID：A003
     * アクション名：検索処理
     * Dto リクエスト：IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputInfoRegisterSql001RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputInfoRegisterSql001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception  検索処理で例外が発生した場合
     */
    public DataList<IfaForeignBondBuyAmountInputInfoRegisterA003ResponseDto> nameKanjiInputA003(
            IfaForeignBondBuyAmountInputInfoRegisterA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：挿入処理
     * Dto リクエスト：IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto
     * Dto レスポンス：IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto
     * model リクエスト：IfaForeignBondBuyAmountInputInfoRegisterSql003RequestModel
     * model レスポンス：IfaForeignBondBuyAmountInputInfoRegisterSql003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception データ挿入処理中に例外が発生した場合
     */
    public DataList<IfaForeignBondBuyAmountInputInfoRegisterA005ResponseDto> insertA005(
            IfaForeignBondBuyAmountInputInfoRegisterA005RequestDto dtoReq) throws Exception;
}
