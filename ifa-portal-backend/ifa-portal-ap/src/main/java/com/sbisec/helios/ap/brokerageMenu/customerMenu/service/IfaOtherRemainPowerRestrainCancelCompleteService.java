package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0110-02_1
 * 画面名：その他余力拘束注文取消完了

 * @author 大連 えん
    2025/02/28 新規作成
 */
public interface IfaOtherRemainPowerRestrainCancelCompleteService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainCancelCompleteA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainCancelCompleteA001RequestDto dtoReq)
            throws Exception;
    
}
