package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaResponseStatusUpdateA006ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020301_03-01_1
 * 画面名：対応状況更新
 *
 * @author BASE 李
 2024/05/28 新規作成
 */
public interface IfaResponseStatusUpdateService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaResponseStatusUpdateA001RequestDto
     * Dto レスポンス：IfaResponseStatusUpdateA001ResponseDto
     * model リクエスト：IfaResponseStatusUpdateA001RequestModel
     * model レスポンス：IfaResponseStatusUpdateA001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateA001ResponseDto> initializeA001(IfaResponseStatusUpdateA001RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：対応状況更新確認OK
     * Dto リクエスト：IfaResponseStatusUpdateA006RequestDto
     * Dto レスポンス：IfaResponseStatusUpdateA006ResponseDto
     * model リクエスト：IfaResponseStatusUpdateA006RequestModel
     * model レスポンス：IfaResponseStatusUpdateA006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaResponseStatusUpdateA006ResponseDto> responseStatusUpdateConfirmOkA006(IfaResponseStatusUpdateA006RequestDto dtoReq)
            throws Exception;
}
