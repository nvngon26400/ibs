package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaCommFeeA004aResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
public interface IfaCommFeeService extends Service {

    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto レスポンス：IfaCommFeeA001ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 初期化に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA001ResponseDto> initializeA001(
                IfaCommFeeA001RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCommFeeA002RequestDto
     * Dto レスポンス：IfaCommFeeA002ResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return 表示に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA002ResponseDto> displayA002(
            IfaCommFeeA002RequestDto dtoReq
    ) throws Exception;
    
    /**
     * アクションID：A004a
     * アクション名：CSV出力
     * Dto リクエスト：IfaCommFeeA004aRequestDto
     * Dto レスポンス：IfaCommFeeA004aResponseDto
     *
     * @param dtoReq リクエストパラメータ
     * @return CSV出力に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaCommFeeA004aResponseDto> csvOutputA004a(
            IfaCommFeeA004aRequestDto dtoReq,
            String fwSessionId
    ) throws Exception;

}
