package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactCorrectHistoryA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID:SUB0202_0106-08
 * 画面名:接触履歴（入力）修正履歴
 *
 * @author SBI大連 夏
 * @date   2025/08/14
 */
public interface IfaContactCorrectHistoryService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaContactCorrectHistoryA001DtoRequest
     * レスポンス：IfaContactCorrectHistoryA001DtoResponse
    
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaContactCorrectHistoryA001ResponseDto>initializeA001(IfaContactCorrectHistoryA001RequestDto req) throws Exception;
    
}
