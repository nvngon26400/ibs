package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0401_02-03
 * 画面名：自己点検記録簿回答確認・回答理由入力
 *
 * @author SCSK丹波
 2024/06/05 新規作成
 */
public interface IfaSelfInspectBlotterReplyConfirmReplyReasonInputService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：完了
     * Dto リクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto
     * model リクエスト：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestModel
     * model レスポンス：IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002ResponseDto> completeA002(
            IfaSelfInspectBlotterReplyConfirmReplyReasonInputA002RequestDto dtoReq) throws Exception;
    
}
