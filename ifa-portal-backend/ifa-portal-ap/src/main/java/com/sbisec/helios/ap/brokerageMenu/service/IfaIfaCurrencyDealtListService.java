package com.sbisec.helios.ap.brokerageMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListA001RequestDto;
import com.sbisec.helios.ap.dto.IfaIfaCurrencyDealtListA001ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0503-01
 * 画面名：【IFA】取扱通貨一覧
 * アクションID：A001
 * アクション名：初期化

 * @author 松尾
 * 　　　　2023/08/18 新規作成
 */
public interface IfaIfaCurrencyDealtListService extends Service {

    /**

     * Dto リクエスト：IfaIfaCurrencyDealtListA001DtoRequest
     * Dto レスポンス：IfaIfaCurrencyDealtListA001DtoResponse
     * model リクエスト：IfaIfaCurrencyDealtListA001RequestModel
     * model レスポンス：IfaIfaCurrencyDealtListA001ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     */
    public DataList<IfaIfaCurrencyDealtListA001ResponseDto> initializeA001(IfaIfaCurrencyDealtListA001RequestDto dtoReq)
            throws Exception;

}
