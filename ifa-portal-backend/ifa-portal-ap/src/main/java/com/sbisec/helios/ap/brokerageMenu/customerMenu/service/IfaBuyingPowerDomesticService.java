package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaBuyingPowerDomesticA003ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_010301-01
 * 画面名：買付余力（国内）

 * @author 松田
    2024/01/10 新規作成
 */
public interface IfaBuyingPowerDomesticService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaBuyingPowerDomesticA001DtoRequest
     * Dto レスポンス：IfaBuyingPowerDomesticA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaBuyingPowerDomesticA001ResponseDto> initializeA001(IfaBuyingPowerDomesticA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：再検索
     * Dto リクエスト：IfaBuyingPowerDomesticA003DtoRequest
     * Dto レスポンス：IfaBuyingPowerDomesticA003DtoResponse
     * model リクエスト：IfaFaqSql002RequestModel
     * model レスポンス：IfaFaqSql002ResponseModel

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaBuyingPowerDomesticA003ResponseDto> reSearchA003(IfaBuyingPowerDomesticA003RequestDto dtoReq)
            throws Exception;

}
