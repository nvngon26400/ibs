package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto.IfaMonthCustomerNumA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
public interface IfaMonthCustomerNumService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaMonthCustomerNumA001RequestDto
     * DTO レスポンス：IfaMonthCustomerNumA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA001ResponseDto> initializeA001(
            IfaMonthCustomerNumA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaMonthCustomerNumA002RequestDto
     * DTO レスポンス：IfaMonthCustomerNumA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA002ResponseDto> displayA002(
            IfaMonthCustomerNumA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：月末口座数CSV出力
     * DTO リクエスト：IfaMonthCustomerNumA004RequestDto
     * DTO レスポンス：IfaMonthCustomerNumA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA004ResponseDto> csvOutputA004(
            IfaMonthCustomerNumA004RequestDto dtoReq,String fwSessionId) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：仲介業者顧客CSV出力
     * DTO リクエスト：IfaMonthCustomerNumA005RequestDto
     * DTO レスポンス：IfaMonthCustomerNumA005ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumA005ResponseDto> csvOutputA005(
            IfaMonthCustomerNumA005RequestDto dtoReq,String fwSessionId) throws Exception;
}
