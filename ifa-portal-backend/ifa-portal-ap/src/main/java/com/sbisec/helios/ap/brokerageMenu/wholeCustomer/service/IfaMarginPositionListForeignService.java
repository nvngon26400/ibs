package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA002bDtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListForeignA006aDtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0303-01
 * 画面名：信用建玉一覧（米株）
 *
 * @author 島崎聡士 2023/11/30 新規作成
 */
public interface IfaMarginPositionListForeignService extends Service {

    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMarginPositionListForeignA002aDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA002aDtoResponse
     * model リクエスト：IfaMarginPositionListForeignSql001RequestModel
     * model レスポンス：IfaMarginPositionListForeignSql001ResponseModel
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA002aDtoResponse
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginPositionListForeignA002aDtoResponse> displayA002a(
            IfaMarginPositionListForeignA002aDtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaMarginPositionListForeignA002bDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA002bDtoResponse
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA002bDtoResponse
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginPositionListForeignA002bDtoResponse> displayA002b(
            IfaMarginPositionListForeignA002bDtoRequest dtoReq) throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaMarginPositionListForeignA006aDtoRequest
     * Dto レスポンス：IfaMarginPositionListForeignA006aDtoResponse
     *
     * @param dtoReq dtoリクエスト
     * @return IfaMarginPositionListForeignA006aDtoResponse
     * @exception Exception 例外が発生した場合
     */
    public DataList<IfaMarginPositionListForeignA006aDtoResponse> csvOutputA006(
            IfaMarginPositionListForeignA006aDtoRequest dtoReq, String fw) throws Exception;
    
}
