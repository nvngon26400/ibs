package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaShortPositionDividendAdjustDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010302-02
 * 画面名：売建配当調整金明細
 * 2024/06/21 新規作成
 *
 * @author SCSK 笹倉秀行
 */
public interface IfaShortPositionDividendAdjustDetailService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaShortPositionDividendAdjustDetailA001RequestDto
     * Dto レスポンス：IfaShortPositionDividendAdjustDetailA001ResponseDto
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaShortPositionDividendAdjustDetailA001ResponseDto> initializeA001(
            IfaShortPositionDividendAdjustDetailA001RequestDto dtoReq) throws Exception;
    
}
