package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA003ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA007ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * 2024/04/15 新規作成
 *
 * @author SCSK濱田
 */

public interface IfaSubscriptInputConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：注文（仲介業者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA001RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA001ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA001RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA001ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 注文に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA001ResponseDto> orderPlacementBrokerA001(
            IfaSubscriptInputConfirmA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：更新（管理者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA002RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA002ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA002RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA002ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者新規）に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA002ResponseDto> orderPlacementManagerA002(
            IfaSubscriptInputConfirmA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：更新（管理者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA003RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA003ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA003RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA003ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者更新）に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA003ResponseDto> orderUpdateMnagerA003(
            IfaSubscriptInputConfirmA003RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：更新（管理者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA004RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA004ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA004RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA004ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 更新（管理者訂正）に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA004ResponseDto> orderCorrectionManagerA004(
            IfaSubscriptInputConfirmA004RequestDto dtoReq)
            throws Exception;
    
    
    /**
     * アクションID：A005
     * アクション名：訂正（仲介業者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA005RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA005ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA005RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA005ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 訂正（仲介業者更新）に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA005ResponseDto> orderUpdateBrokerA005(
            IfaSubscriptInputConfirmA005RequestDto dtoReq)
            throws Exception;

    /**
     * アクションID：A006
     * アクション名：訂正（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA006RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA006ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA006RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA006ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 訂正（仲介業者訂正）に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA006ResponseDto> orderCorrectionBrokerA006(
            IfaSubscriptInputConfirmA006RequestDto dtoReq)
            throws Exception;
    
    
    /**
     * アクションID：A007
     * アクション名：取消
     * Dto リクエスト：IfaSubscriptInputConfirmA007RequestDto
     * Dto レスポンス：IfaSubscriptInputConfirmA007ResponseDto
     * model リクエスト：IfaSubscriptInputConfirmA007RequestModel
     * model レスポンス：IfaSubscriptInputConfirmA007ResponseModel
     *
     * @param dtoReq リクエストボディ
     * @return 取消に必要な情報
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmA007ResponseDto> orderCancellationA007(
            IfaSubscriptInputConfirmA007RequestDto dtoReq)
            throws Exception;

}
