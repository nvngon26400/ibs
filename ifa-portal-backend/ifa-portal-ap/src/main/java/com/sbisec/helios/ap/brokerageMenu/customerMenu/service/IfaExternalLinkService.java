package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaExternalLinkDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 外部リンク
 * 2025/05/14 新規作成
 *
 * @author 大連 葉
 */
public interface IfaExternalLinkService extends Service{

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaExternalLinkDtoRequest
     * Dto レスポンス：IfaExternalLinkDtoResponse
     *
     * @param dtoReq {@code IfaExternalLinkDtoRequest }
     * @return {@code DataList <IfaExternalLinkDtoResponse>}
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaExternalLinkDtoResponse> initializeA001(IfaExternalLinkDtoRequest dtoReq)
            throws Exception;

}
