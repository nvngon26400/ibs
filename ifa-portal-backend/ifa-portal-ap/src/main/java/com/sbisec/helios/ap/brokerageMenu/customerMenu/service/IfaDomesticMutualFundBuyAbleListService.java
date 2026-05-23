package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;


import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundBuyAbleListA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0401-01
 * 画面名：国内投信購入可能一覧
 * アクションID：A001
 * アクション名：初期化
 * アクションID：A002
 * アクション名：購入（直接入力）
 * アクションID：A004
 * アクション名：積立（直接入力）
 *
 * @author SCSK浦田
 *
 * 2023/10/27 新規作成
 */
public interface IfaDomesticMutualFundBuyAbleListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA001RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA001ResponseDto
     * model リクエスト：IfaDomesticMutualFundBuyAbleListA001RequestModel
     * model レスポンス：IfaDomesticMutualFundBuyAbleListA001ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA001ResponseDto> initializeA001(
            IfaDomesticMutualFundBuyAbleListA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：購入（直接入力）
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA002RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA002ResponseDto
     * model リクエスト：IfaDomesticMutualFundBuyAbleListA002RequestModel
     * model レスポンス：IfaDomesticMutualFundBuyAbleListA002ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA002ResponseDto> purchaseDirectInputA002(
            IfaDomesticMutualFundBuyAbleListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：積立（直接入力）
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA004RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA004ResponseDto
     * model リクエスト：IfaDomesticMutualFundBuyAbleListA004RequestModel
     * model レスポンス：IfaDomesticMutualFundBuyAbleListA004ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA004ResponseDto> accumulateDirectInputA004(
    		IfaDomesticMutualFundBuyAbleListA004RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A007
     * アクション名：投信銘柄情報取得
     * Dto リクエスト：IfaDomesticMutualFundBuyAbleListA007RequestDto
     * Dto レスポンス：IfaDomesticMutualFundBuyAbleListA007ResponseDto
     * model リクエスト：IfaDomesticMutualFundBuyAbleListA007RequestModel
     * model レスポンス：IfaDomesticMutualFundBuyAbleListA007ResponseModel
     *
     * @param dtoReq リクエストDTO
     * @return レスポンスデータ
     * @exception Exception 例外処理
     */
    public DataList<IfaDomesticMutualFundBuyAbleListA007ResponseDto> directInputSelectMFNameA007(
    		IfaDomesticMutualFundBuyAbleListA007RequestDto dtoReq) throws Exception;
    
}
