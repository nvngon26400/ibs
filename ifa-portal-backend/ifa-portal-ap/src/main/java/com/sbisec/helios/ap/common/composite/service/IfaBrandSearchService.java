package com.sbisec.helios.ap.common.composite.service;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandSearchA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：CC014
 * 画面名：画面共通部品_銘柄検索
 * @author <author-name>
 *
 * 2023/08/21 新規作成
 */
public interface IfaBrandSearchService extends Service {

    /**
     * アクションID：A002
     * アクション名：銘柄検索
     * Dto リクエスト：IfaBrandSearchA002DtoRequest
     * Dto レスポンス：IfaBrandSearchA002DtoResponse
     * model リクエスト：IfaBrandSearchA002RequestModel
     * model レスポンス：IfaBrandSearchA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandSearchA002DtoResponse> searchBrandA002(IfaBrandSearchA002DtoRequest dtoReq)
            throws Exception;

}
