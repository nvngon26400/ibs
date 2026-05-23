package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptPeriodMasterRegisterInputA004ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
public interface IfaSubscriptPeriodMasterRegisterInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSubscriptPeriodMasterRegisterInputA001RequestDto
     * Dto レスポンス：IfaSubscriptPeriodMasterRegisterInputA001ResponseDto
     * model リクエスト：IfaSubscriptPeriodMasterRegisterInputA001RequestModel
     * model レスポンス：IfaSubscriptPeriodMasterRegisterInputA001ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 画面の初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputA001ResponseDto> initializeA001(
            IfaSubscriptPeriodMasterRegisterInputA001RequestDto dtoReq
    ) throws Exception;

    /**
     * アクションID：A004
     * アクション名：OK
     * Dto リクエスト：IfaSubscriptPeriodMasterRegisterInputA004RequestDto
     * Dto レスポンス：IfaSubscriptPeriodMasterRegisterInputA004ResponseDto
     * model リクエスト：IfaSubscriptPeriodMasterRegisterInputA004RequestModel
     * model レスポンス：IfaSubscriptPeriodMasterRegisterInputA004ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 登録の成否
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputA004ResponseDto> okA004(
            IfaSubscriptPeriodMasterRegisterInputA004RequestDto dtoReq
    ) throws Exception;

}
