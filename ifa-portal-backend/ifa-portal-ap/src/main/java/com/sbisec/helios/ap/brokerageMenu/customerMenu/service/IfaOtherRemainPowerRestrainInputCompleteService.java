package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0110-01_3
 * 画面名：その他余力拘束注文完了

 * @author 大連 えん
    2025/02/28 新規作成
 */
public interface IfaOtherRemainPowerRestrainInputCompleteService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputCompleteA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputCompleteA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainInputCompleteA001RequestDto dtoReq)
            throws Exception;
    
}
