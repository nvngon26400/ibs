package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0110-01_1
 * 画面名：その他余力拘束注文入力

 * @author 大連 えん
    2025/02/28 新規作成
 */
public interface IfaOtherRemainPowerRestrainInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputA001ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainInputA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文確認
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputOrderConfirmA002ResponseDto> orderConfirmA002(IfaOtherRemainPowerRestrainInputOrderConfirmA002RequestDto dtoReq)
            throws Exception;

}
