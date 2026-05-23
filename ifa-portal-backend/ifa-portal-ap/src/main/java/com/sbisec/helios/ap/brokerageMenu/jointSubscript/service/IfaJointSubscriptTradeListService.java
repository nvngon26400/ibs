package com.sbisec.helios.ap.brokerageMenu.jointSubscript.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dto.IfaJointSubscriptTradeListA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0206_02-01
 * 画面名：共同募集　取引検索
 *
 * @author SBIチョウ
   2024/12/10 新規作成
 */
public interface IfaJointSubscriptTradeListService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaJointSubscriptTradeListA001RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA001ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA001ResponseDto> initializeA001(IfaJointSubscriptTradeListA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaJointSubscriptTradeListA002RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA002ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA002ResponseDto> displayA002(IfaJointSubscriptTradeListA002RequestDto dtoReq) throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaJointSubscriptTradeListA003RequestDto
     * Dto レスポンス：IfaJointSubscriptTradeListA003ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJointSubscriptTradeListA003ResponseDto> csvOutputA003(IfaJointSubscriptTradeListA003RequestDto dtoReq,
            String fwSessionId) throws Exception;

}
