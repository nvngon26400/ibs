package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaSubscriptInputConfirmA006RequestDto;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * 2024/04/15 新規作成
 *
 * @author SCSK濱田
 */

public interface IfaSubscriptInputConfirmEtintraService extends Service {

    /**
     * アクションID：A001（ETINTRAトランザクション制御用）
     * アクション名：注文（仲介業者新規）
     * Dto リクエスト：IfaSubscriptInputConfirmA001DtoRequest
     *
     * @param dtoReq リクエストボディ
     * @exception Exception システムエラー
     */
    public void orderPlacementBrokerA001Etintra(
            IfaSubscriptInputConfirmA001RequestDto dtoReq, ApiErrorUtil apiErrorUtil) throws Exception;
    
    /**
     * アクションID：A005（ETINTRAトランザクション制御用）
     * アクション名：訂正（仲介業者更新）
     * Dto リクエスト：IfaSubscriptInputConfirmA005RequestDto
     *
     * @param dtoReq リクエストボディ
     * @throws Exception SQLエラー、API002エラー
     */
    public void orderUpdateBrokerA005Etintra(
            IfaSubscriptInputConfirmA005RequestDto dtoReq, ApiErrorUtil apiErrorUtil) throws Exception;
    
    /**
     * アクションID：A006（ETINTRAトランザクション制御用）
     * アクション名：訂正（仲介業者訂正）
     * Dto リクエスト：IfaSubscriptInputConfirmA006RequestDto
     *
     * @param dtoReq リクエストボディ
     * @throws Exception SQLエラー
     */
    public void orderCorrectionBrokerA006Etintra(
            IfaSubscriptInputConfirmA006RequestDto dtoReq) throws Exception;

}
