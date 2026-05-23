package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA004ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dto.IfaBrokerCodeClosingMonthLoginA006ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0406-01_1
 * 画面名：仲介業者決算月設定
 *
 * @author SBI大連 夏
 * @date   2025/05/27
 */

public interface IfaBrokerCodeClosingMonthLoginService extends Service {

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA001RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerCodeClosingMonthLoginA001ResponseDto> initializeA001(
        IfaBrokerCodeClosingMonthLoginA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：仲介業者コード入力
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA004RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerCodeClosingMonthLoginA004ResponseDto> getBrokerNameClosingMonthA004(
        IfaBrokerCodeClosingMonthLoginA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：登録･更新
     * Dto リクエスト：IfaBrokerCodeClosingMonthLoginA006RequestDto
     * Dto レスポンス：IfaBrokerCodeClosingMonthLoginA006ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBrokerCodeClosingMonthLoginA006ResponseDto> updateBrokerCodeClosingMonthA006(
        IfaBrokerCodeClosingMonthLoginA006RequestDto dtoReq) throws Exception;
}
