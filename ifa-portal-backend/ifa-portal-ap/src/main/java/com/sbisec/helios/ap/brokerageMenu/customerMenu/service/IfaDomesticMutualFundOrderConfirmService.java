package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bRequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaDomesticMutualFundOrderConfirmA001bResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0401-02_2
 * 画面名：国内投信注文確認
 * @author <author-name>
 *
 * 2024/03/26 新規作成
 */
public interface IfaDomesticMutualFundOrderConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticMutualFundOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderConfirmA001DtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderConfirmA001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderConfirmA001aResponseDto> orderPlacementA001a(IfaDomesticMutualFundOrderConfirmA001aRequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A001
     * アクション名：注文発注
     * Dto リクエスト：IfaDomesticMutualFundOrderConfirmA001DtoRequest
     * Dto レスポンス：IfaDomesticMutualFundOrderConfirmA001DtoResponse
     * model リクエスト：IfaDomesticMutualFundOrderConfirmA001RequestModel
     * model レスポンス：IfaDomesticMutualFundOrderConfirmA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaDomesticMutualFundOrderConfirmA001bResponseDto> orderPlacementA001b(IfaDomesticMutualFundOrderConfirmA001bRequestDto dtoReq)
            throws Exception;
}
