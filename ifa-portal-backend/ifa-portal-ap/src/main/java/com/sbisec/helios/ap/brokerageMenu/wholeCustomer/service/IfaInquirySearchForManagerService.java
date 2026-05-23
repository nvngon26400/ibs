package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerA006ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */
public interface IfaInquirySearchForManagerService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaInquirySearchForManagerA001RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA001ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaInquirySearchForManagerA001ResponseDto> initializeA001(IfaInquirySearchForManagerA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：カテゴリ（中）取得
     * Dto リクエスト：IfaInquirySearchForManagerA002RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA002ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaInquirySearchForManagerA002ResponseDto> categoryChangeA002(IfaInquirySearchForManagerA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：カテゴリ（小）取得
     * Dto リクエスト：IfaInquirySearchForManagerA003RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA003ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaInquirySearchForManagerA003ResponseDto> categoryChangeA003(IfaInquirySearchForManagerA003RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：表示
     * Dto リクエスト：IfaInquirySearchForManagerA004RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA004ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    public DataList<IfaInquirySearchForManagerA004ResponseDto> displayA004(IfaInquirySearchForManagerA004RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A006
     * アクション名：CSV出力
     * Dto リクエスト：IfaInquirySearchForManagerA006RequestDto
     * Dto レスポンス：IfaInquirySearchForManagerA006ResponseDto
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param dtoReq リクエスト
     * @return レスポンス
     * @exception Exception
     */
    DataList<IfaInquirySearchForManagerA006ResponseDto> csvOutputA006(IfaInquirySearchForManagerA006RequestDto dtoReq, String fwSessionId) throws Exception;
}
