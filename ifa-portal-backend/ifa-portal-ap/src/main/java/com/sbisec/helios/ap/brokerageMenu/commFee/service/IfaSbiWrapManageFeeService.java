package com.sbisec.helios.ap.brokerageMenu.commFee.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaSbiWrapManageFeeA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020504-01
 * 画面名：SBIラップ管理報酬
 *
 * @author 松尾
 2024/06/18 新規作成
 */
public interface IfaSbiWrapManageFeeService extends Service {
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaSbiWrapManageFeeA002RequestDto
     * Dto レスポンス：IfaSbiWrapManageFeeA002ResponseDto
     * model リクエスト：IfaSbiWrapManageFeeA002RequestModel
     * model レスポンス：IfaSbiWrapManageFeeA002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSbiWrapManageFeeA002ResponseDto> displayA002(IfaSbiWrapManageFeeA002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：CSV出力
     * Dto リクエスト：IfaSbiWrapManageFeeA003RequestDto
     * Dto レスポンス：IfaSbiWrapManageFeeA003ResponseDto
     * model リクエスト：IfaSbiWrapManageFeeA003RequestModel
     * model レスポンス：IfaSbiWrapManageFeeA003ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSbiWrapManageFeeA003ResponseDto> csvOutputA003(IfaSbiWrapManageFeeA003RequestDto dtoReq,
            String fwSessionId) throws Exception;
}
