package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/05/22
 */

public interface IfaByYearAccountQuantityFeeAmountLookupService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA001RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA001ResponseDto> initializeA001(
        IfaByYearAccountQuantityFeeAmountLookupA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：年度別口座数・報酬額情報取得
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA002RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA002ResponseDto> displayA002(
        IfaByYearAccountQuantityFeeAmountLookupA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：年度別口座数・報酬額CSV出力
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA004RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql003RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA004ResponseDto> csvOutputA004(
        IfaByYearAccountQuantityFeeAmountLookupA004RequestDto dtoReq, String fwSessionId) throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：仲介業者決算月情報取得
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA005RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql002RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA005ResponseDto> getClosingMonthA005(
        IfaByYearAccountQuantityFeeAmountLookupA005RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A007
     * アクション名：当期末口座明細
     * Dto リクエスト：IfaByYearAccountQuantityFeeAmountLookupA007RequestDto
     * Dto レスポンス：IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto
     * model リクエスト：IfaByYearAccountQuantityFeeAmountLookupSql004RequestModel
     * model レスポンス：IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaByYearAccountQuantityFeeAmountLookupA007ResponseDto> csvOutputA007(
        IfaByYearAccountQuantityFeeAmountLookupA007RequestDto dtoReq, String fwSessionId) throws Exception;
}
