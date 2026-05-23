package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
public interface IfaSelfInspectBlotterService extends Service {
    
    /**
    * アクションID：A001
    * アクション名：初期化
    * Dto リクエスト：IfaSelfInspectBlotterA001RequestDto
    * Dto レスポンス：IfaSelfInspectBlotterA001ResponseDto
    * model リクエスト：IfaSelfInspectBlotterA001RequestModel
    * model レスポンス：IfaSelfInspectBlotterA001ResponseModel
    *
    * @param dtoReq リクエスト
    * @return res レスポンス
    * @exception exception システムエラー
    */
    public DataList<IfaSelfInspectBlotterA001ResponseDto> initializeA001(IfaSelfInspectBlotterA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示年月変更
     * Dto リクエスト：IfaSelfInspectBlotterA002RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterA002ResponseDto
     * model リクエスト：IfaSelfInspectBlotterA002RequestModel
     * model レスポンス：IfaSelfInspectBlotterA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterA002ResponseDto> displayYearMonthChangeA002(
            IfaSelfInspectBlotterA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：TOP遷移
     * Dto リクエスト：IfaSelfInspectBlotterA003RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterA003ResponseDto
     * model リクエスト：IfaSelfInspectBlotterA003RequestModel
     * model レスポンス：IfaSelfInspectBlotterA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterA003ResponseDto> topTransitionA003(IfaSelfInspectBlotterA003RequestDto dtoReq)
            throws Exception;
    
}
