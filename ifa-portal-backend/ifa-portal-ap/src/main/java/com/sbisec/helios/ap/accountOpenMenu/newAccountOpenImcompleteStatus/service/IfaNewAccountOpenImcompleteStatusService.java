package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoRequest;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 富永侑希子
　　　　2023/10/27 新規作成
 */
public interface IfaNewAccountOpenImcompleteStatusService extends Service {
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaNewAccountOpenImcompleteStatusA002DtoRequest
     * Dto レスポンス：IfaNewAccountOpenImcompleteStatusA002DtoResponse
     * model リクエスト：IfaNewAccountOpenImcompleteStatusA002RequestModel
     * model レスポンス：IfaNewAccountOpenImcompleteStatusA002ResponseModel

     * @param dtoReq リクエスト
     * @return IfaNewAccountOpenImcompleteStatusA002DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaNewAccountOpenImcompleteStatusA002DtoResponse> displayA002(IfaNewAccountOpenImcompleteStatusA002DtoRequest dtoReq)
            throws Exception;

}
