package com.sbisec.helios.ap.common.composite.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaBrandPriceInfoA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：CC013
 * 画面名：銘柄時価情報（国内株）
 * @author <author-name>
 *
 * 2023/08/24 新規作成
 */
public interface IfaBrandPriceInfoService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：時価更新
     * Dto リクエスト：IfaBrandPriceInfoA002DtoRequest
     * Dto レスポンス：IfaBrandPriceInfoA002DtoResponse
     * model リクエスト：IfaBrandPriceInfoA002RequestModel
     * model レスポンス：IfaBrandPriceInfoA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaBrandPriceInfoA002DtoResponse> updateMarketValueA002(IfaBrandPriceInfoA002DtoRequest dtoReq)
            throws Exception;
    
}
