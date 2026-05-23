package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaLevelFeeA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
public interface IfaLevelFeeService extends Service {

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto レスポンス：IfaLevelFeeA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA001ResponseDto> initializeA001(
                IfaLevelFeeA001RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaLevelFeeA002RequestDto
     * Dto レスポンス：IfaLevelFeeA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA002ResponseDto> displayA002(
            IfaLevelFeeA002RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * Dto リクエスト：IfaLevelFeeA004aRequestDto
     * Dto レスポンス：IfaLevelFeeA004aResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaLevelFeeA004aResponseDto> csvOutputA004a(
            IfaLevelFeeA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception;

}
