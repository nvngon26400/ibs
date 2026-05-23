package com.sbisec.helios.ap.common.composite.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：CC005
 * 画面名：検索条件（一覧画面）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
public interface IfaCommonSearchService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCommonSearchA001DtoRequest
     * Dto レスポンス：IfaCommonSearchA001DtoResponse
     * model リクエスト：IfaCommonSearchA001RequestModel
     * model レスポンス：IfaCommonSearchA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCommonSearchA001DtoResponse> initializeA001(IfaCommonSearchA001DtoRequest dtoReq)
            throws Exception;

}
