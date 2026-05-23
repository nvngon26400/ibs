package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA002bResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaMarginPositionListDomesticA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0302-01
 * 画面名：信用建玉一覧（国内）
 * @author 松田
 * TODO メソッド名QA待ち
 * 2023/09/07 新規作成
 */
public interface IfaMarginPositionListDomesticService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示 顧客口座情報検索
     * Dto リクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * model リクエスト：IfaMarginPositionListDomesticA002RequestModel
     * model レスポンス：IfaMarginPositionListDomesticA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名は仮
     */
    public DataList<IfaMarginPositionListDomesticA002aResponseDto> displayA002a(
            IfaMarginPositionListDomesticA002aRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示 信用建玉情報検索
     * Dto リクエスト：IfaMarginPositionListDomesticA002DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA002DtoResponse
     * model リクエスト：IfaMarginPositionListDomesticA002RequestModel
     * model レスポンス：IfaMarginPositionListDomesticA002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名は仮
     */
    public DataList<IfaMarginPositionListDomesticA002bResponseDto> displayA002b(
            IfaMarginPositionListDomesticA002bRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力(CSV作成)
     * Dto リクエスト：IfaMarginPositionListDomesticA004DtoRequest
     * Dto レスポンス：IfaMarginPositionListDomesticA004DtoResponse
     * model リクエスト：IfaMarginPositionListDomesticA004RequestModel
     * model レスポンス：IfaMarginPositionListDomesticA004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     * TODO メソッド名は仮
     */
    public DataList<IfaMarginPositionListDomesticA004aResponseDto> csvOutputA004a(
            IfaMarginPositionListDomesticA004aRequestDto dtoReq, String fwSessionId) throws Exception;
}
