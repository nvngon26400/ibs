package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaRegisterInfoDtoResponse;
import com.sbisec.helios.ap.service.Service;


/**
 * 登録情報
 * 2025/02/21 新規作成
 *
 * @author 大連 苗
 */
public interface IfaRegisterInfoService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaRegisterInfoDtoRequest
     * Dto レスポンス：IfaRegisterInfoDtoResponse
     *
     * @param dtoReq {@code IfaRegisterInfoDtoRequest }
     * @return {@code DataList <IfaRegisterInfoDtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaRegisterInfoDtoResponse> initializeA001(IfaRegisterInfoDtoRequest dtoReq)
            throws Exception;
    
}
