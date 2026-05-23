package com.sbisec.helios.ap.brokerageMenu.customerMenu.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dto.IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0202_0110-01_2
 * 画面名：その他余力拘束注文確認

 * @author 大連 えん
    2025/02/28 新規作成
 */
public interface IfaOtherRemainPowerRestrainInputConfirmService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputConfirmA001RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @exception Exception 初期化処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputConfirmA001ResponseDto> initializeA001(IfaOtherRemainPowerRestrainInputConfirmA001RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A002
     * アクション名：注文発注
     * Dto リクエスト：IfaOtherRemainPowerRestrainInputConfirmA002RequestDto
     * Dto レスポンス：IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto

     * @param dtoReq リクエストDTO
     * @return dtoRes レスポンスDTO
     * @throws exception 再検索処理で例外が発生した場合
     */
    public DataList<IfaOtherRemainPowerRestrainInputConfirmA002ResponseDto> orderPlacementA002(IfaOtherRemainPowerRestrainInputConfirmA002RequestDto dtoReq)
            throws Exception;

}
