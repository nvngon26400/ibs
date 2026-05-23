package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactAcceptDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0106-02
 * 画面名：接触履歴受付詳細
 * 
 * @author 趙韫慧 2025/04/22 新規作成
 */
public interface IfaContactAcceptDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaContactAcceptDetailA001DtoRequest
     * レスポンス：IfaContactAcceptDetailA001DtoResponse
     * 
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaContactAcceptDetailA001ResponseDto> initializeA001(IfaContactAcceptDetailA001RequestDto dtoReq)
            throws Exception;

}
