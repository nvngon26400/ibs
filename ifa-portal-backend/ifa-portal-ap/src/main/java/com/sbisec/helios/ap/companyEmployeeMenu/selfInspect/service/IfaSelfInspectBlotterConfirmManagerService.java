package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerDisplayResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerQueryRequestDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
public interface IfaSelfInspectBlotterConfirmManagerService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：全仲介業者名表示
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto> allBrokerNameDisplayA002(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：検索表示
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto> searchDisplayA003(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerA001ResponseDto> initializeA001(
            IfaSelfInspectBlotterConfirmManagerA001RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerA004ResponseDto> csvOutputA004(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception;
    
}
