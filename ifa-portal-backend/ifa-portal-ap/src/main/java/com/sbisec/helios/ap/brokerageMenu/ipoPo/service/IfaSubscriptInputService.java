package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/02/02 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaSubscriptInputService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSubscriptInputA001RequestDto
     * Dto レスポンス：IfaSubscriptInputA001ResponseDto
     * model リクエスト：IfaSubscriptInputA001RequestModel
     * model レスポンス：IfaSubscriptInputA001ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA001ResponseDto> initializeA001(IfaSubscriptInputA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：確認（仲介業者登録・管理者訂正）
     * Dto リクエスト：IfaSubscriptInputA002RequestDto
     * Dto レスポンス：IfaSubscriptInputA002ResponseDto
     * model リクエスト：IfaSubscriptInputA002RequestModel
     * model レスポンス：IfaSubscriptInputA002ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA002ResponseDto> confirmIntermediaryRegistrationAdminCorrectionA002(
            IfaSubscriptInputA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：確認（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputA003RequestDto
     * Dto レスポンス：IfaSubscriptInputA003ResponseDto
     * model リクエスト：IfaSubscriptInputA003RequestModel
     * model レスポンス：IfaSubscriptInputA003ResponseModel
     *
     * @param dtoReq リクエストパラメータ
     * @return レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputA003ResponseDto> confirmIntermediaryCorrectionA003(
            IfaSubscriptInputA003RequestDto dtoReq) throws Exception;
    
}
