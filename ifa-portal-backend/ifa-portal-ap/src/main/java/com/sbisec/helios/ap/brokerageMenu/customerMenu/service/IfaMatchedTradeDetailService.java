package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaMatchedTradeDetailA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0105-02
 * 画面名：出来明細
 * @author <author-name>
 *
 * 2023/09/14 新規作成
 */
public interface IfaMatchedTradeDetailService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：2DtoRequest
     * Dto レスポンス：2DtoResponse
     * model リクエスト：2RequestModel
     * model レスポンス：2ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMatchedTradeDetailA001ResponseDto> initializeA001(IfaMatchedTradeDetailA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A003
     * アクション名：更新
     * Dto リクエスト：2DtoRequest
     * Dto レスポンス：2DtoResponse
     * model リクエスト：2RequestModel
     * model レスポンス：2ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaMatchedTradeDetailA003ResponseDto> updateA003(IfaMatchedTradeDetailA003RequestDto dtoReq)
            throws Exception;

}
