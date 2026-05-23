package com.sbisec.helios.ap.brokerageMenu.customerList.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerList.dto.IfaImprintInquiryA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0201_01-02
 * 画面名：印影照会
 *
 * @author SCSK
    2024/03/22 新規作成
 */
public interface IfaImprintInquiryService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaImprintInquiryA001DtoRequest
     * Dto レスポンス：IfaImprintInquiryA001DtoResponse
     * model リクエスト：IfaImprintInquiryA001RequestModel
     * model レスポンス：IfaImprintInquiryA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return IfaImprintInquiryA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaImprintInquiryA001ResponseDto> initializeA001(IfaImprintInquiryA001RequestDto dtoReq)
            throws Exception;
    
}
