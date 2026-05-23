package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterConfirmA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
public interface IfaSelfInspectBlotterRegisterConfirmService extends Service {
    
    /**
     * アクションID：A003
     * アクション名：登録
     * Dto リクエスト：IfaSelfInspectBlotterRegisterConfirmA003RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterRegisterConfirmA003ResponseDto
     * model リクエスト：IfaSelfInspectBlotterRegisterConfirmA003RequestModel
     * model レスポンス：IfaSelfInspectBlotterRegisterConfirmA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterRegisterConfirmA003ResponseDto> registerA003(
            IfaSelfInspectBlotterRegisterConfirmA003RequestDto dtoReq) throws Exception;
    
}
