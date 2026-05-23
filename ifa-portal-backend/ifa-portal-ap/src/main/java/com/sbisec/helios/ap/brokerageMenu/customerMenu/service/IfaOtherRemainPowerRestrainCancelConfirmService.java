package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0110-02_1
 * 画面名：その他余力拘束注文取消確認

 * @author 大連 えん
    2025/02/28 新規作成
 */
public interface IfaOtherRemainPowerRestrainCancelConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelConfirmA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainCancelConfirmA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文取消
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelConfirmA002ResponseDto> cancelConfirmA002(IfaOtherRemainPowerRestrainCancelConfirmA002RequestDto dtoReq)
            throws Exception;

}
