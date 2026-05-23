package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCorrectCancelInputA005ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-02_1
 * 画面名：BB申込訂正・取消入力
 *
 * @author BASE 李
 2024/04/15 新規作成
 */
public interface IfaBbApplyCorrectCancelInputService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA001RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA001ResponseDto
     * model リクエスト：IfaBbApplyCorrectCancelInputA001RequestModel
     * model レスポンス：IfaBbApplyCorrectCancelInputA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA001ResponseDto> initializeA001(IfaBbApplyCorrectCancelInputA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：訂正
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA002RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA002ResponseDto
     * model リクエスト：IfaBbApplyCorrectCancelInputA002RequestModel
     * model レスポンス：IfaBbApplyCorrectCancelInputA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA002ResponseDto> correctA002(IfaBbApplyCorrectCancelInputA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：取消
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA003RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA003ResponseDto
     * model リクエスト：IfaBbApplyCorrectCancelInputA003RequestModel
     * model レスポンス：IfaBbApplyCorrectCancelInputA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA003ResponseDto> cancelA003(IfaBbApplyCorrectCancelInputA003RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：戻り初期化
     * Dto リクエスト：IfaBbApplyCorrectCancelInputA005RequestDto
     * Dto レスポンス：IfaBbApplyCorrectCancelInputA005ResponseDto
     * model リクエスト：IfaBbApplyCorrectCancelInputA005RequestModel
     * model レスポンス：IfaBbApplyCorrectCancelInputA005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputA005ResponseDto> backInitializeA005(IfaBbApplyCorrectCancelInputA005RequestDto dtoReq)
            throws Exception;

}
