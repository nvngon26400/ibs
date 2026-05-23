package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBrandPositionListA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010202-03
 * 画面名：銘柄別建玉一覧
 *
 * @author SCSK
 */
public interface IfaBrandPositionListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBrandPositionListA001RequestDto
     * Dto レスポンス：IfaBrandPositionListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaBrandPositionListA001ResponseDto> initializeA001(IfaBrandPositionListA001RequestDto dtoReq)
            throws Exception;
    
}
