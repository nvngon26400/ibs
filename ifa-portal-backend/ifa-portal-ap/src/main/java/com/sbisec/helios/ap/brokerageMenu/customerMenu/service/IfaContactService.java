package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaContactA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0106-01
 * 画面名：接触履歴

 * @author 趙韫慧
 * 2025/02/21 新規作成
 */
public interface IfaContactService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * リクエスト：IfaContactA001DtoRequest
     * レスポンス：IfaContactA001DtoResponse
    
     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaContactA001ResponseDto> initializeA001(IfaContactA001RequestDto dtoReq) throws Exception;

    /**
     * アクションID：A002
     * アクション名：大分類変更
     * Dto リクエスト：IfaBbApplyInputA002RequestDto
     * Dto レスポンス：IfaBbApplyInputA002ResponseDto
     *
     * @param dtoReq dtoリクエスト
     * @return 接触履歴情報(大分類別)
     * @exception Exception 異常
     */
    public DataList<IfaContactA002ResponseDto> bigClassChangeA002(IfaContactA002RequestDto dtoReq) throws Exception;

}
