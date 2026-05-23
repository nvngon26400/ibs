package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0403-04_1
 * 画面名：投信積立設定解除確認
 *
 * @author WJL
 *
 *     2025/04/10 新規作成
 */
public interface IfaMutualFundAccumulateSettingCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaMutualFundAccumulateSettingCancelConfirmA001DtoRequest
     * Dto レスポンス：IfaMutualFundAccumulateSettingCancelConfirmA001DtoResponse
     * model リクエスト：IfaMutualFundAccumulateSettingCancelConfirmA001RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingCancelConfirmA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingCancelConfirmA001ResponseDto> initializeA001(IfaMutualFundAccumulateSettingCancelConfirmA001RequestDto dtoReq)
            throws Exception;


    /**
     * アクションID：A003
     * アクション名：設定解除
     * Dto リクエスト：IfaMutualFundAccumulateSettingCancelConfirmA003DtoRequest
     * Dto レスポンス：IfaMutualFundAccumulateSettingCancelConfirmA003DtoResponse
     * model リクエスト：IfaMutualFundAccumulateSettingCancelConfirmA003RequestModel
     * model レスポンス：IfaMutualFundAccumulateSettingCancelConfirmA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンスパラメータ
     * @exception Exception 注文取消処理で例外が発生した場合
     */
    public DataList<IfaMutualFundAccumulateSettingCancelConfirmA003ResponseDto> accumulateSettingCancelA003(IfaMutualFundAccumulateSettingCancelConfirmA003RequestDto dtoReq)
            throws Exception;

}
