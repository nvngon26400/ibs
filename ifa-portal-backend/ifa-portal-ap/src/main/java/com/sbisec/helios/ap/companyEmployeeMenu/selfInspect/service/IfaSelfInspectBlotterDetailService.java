package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細
 *
 * @author SCSK
 2024/06/12 新規作成
 */
public interface IfaSelfInspectBlotterDetailService extends Service {
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSelfInspectBlotterDetailA001RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterDetailA001ResponseDto
     * model リクエスト：IfaSelfInspectBlotterDetailA001RequestModel
     * model レスポンス：IfaSelfInspectBlotterDetailA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterDetailA001ResponseDto> initializeA001(
            IfaSelfInspectBlotterDetailA001RequestDto dtoReq) throws Exception;
    
}
