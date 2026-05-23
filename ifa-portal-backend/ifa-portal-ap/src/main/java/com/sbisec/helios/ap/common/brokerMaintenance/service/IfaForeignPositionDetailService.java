package com.sbisec.helios.ap.common.brokerMaintenance.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001RequestDto;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaForeignPositionDetailX001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB07-06
 * 画面名：建玉詳細(米株)
 * @author <author-name>
 *
 * 2023/11/24 新規作成
 */
public interface IfaForeignPositionDetailService extends Service {
    
    /**
     * アクションID：X001
     * アクション名：初期化
     * Dto リクエスト：IfaForeignPositionDetailX001DtoRequest
     * Dto レスポンス：IfaForeignPositionDetailX001DtoResponse
     * model リクエスト：IfaForeignPositionDetailX001RequestModel
     * model レスポンス：IfaForeignPositionDetailX001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaForeignPositionDetailX001ResponseDto> initializeX001(
            IfaForeignPositionDetailX001RequestDto dtoReq) throws Exception;
    
}
