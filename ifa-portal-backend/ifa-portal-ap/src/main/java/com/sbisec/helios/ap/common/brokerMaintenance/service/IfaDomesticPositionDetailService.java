package com.sbisec.helios.ap.common.brokerMaintenance.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoRequest;
import com.sbisec.helios.ap.common.brokerMaintenance.dto.IfaDomesticPositionDetailX001DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB07-05
 * 画面名：建玉詳細(国内)
 * アクションID：X001
 * アクション名：初期化
 * @author <author-name>
 *
 * 2023/08/14 新規作成
 */
public interface IfaDomesticPositionDetailService extends Service {

    /**
     * 
     * Dto リクエスト：IfaDomesticPositionDetailX001DtoRequest
     * Dto レスポンス：IfaDomesticPositionDetailX001DtoResponse
     * model リクエスト：IfaDomesticPositionDetailX001RequestModel
     * model レスポンス：IfaDomesticPositionDetailX001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticPositionDetailX001DtoResponse> initializeX001(IfaDomesticPositionDetailX001DtoRequest dtoReq)
            throws Exception;

}
