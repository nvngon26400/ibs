package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticPositionListA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK 金志
 */
public interface IfaDomesticPositionListService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaDomesticPositionListA001RequestDto
     * Dto レスポンス：IfaDomesticPositionListA001ResponseDto
     * model リクエスト：brokerageMenu.customerMenuA001RequestModel
     * model レスポンス：brokerageMenu.customerMenuA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return レスポンス
     * @throws Exception 初期化の際、例外が発生した場合
     */
    public DataList<IfaDomesticPositionListA001ResponseDto> initializeA001(IfaDomesticPositionListA001RequestDto dtoReq)
            throws Exception;
    
}
