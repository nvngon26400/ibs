package com.sbisec.helios.ap.brokerageMenu.ipoPo.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aRequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA004aResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA005ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListA006ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyListX002ResponseDto;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0204_02-01
 * 画面名：BB申込一覧
 *
 * @author BASE李
 *
 2024/03/14 新規作成
 */
public interface IfaBbApplyListService extends Service {

    /**
     * アクションID：X002
     * アクション名：表示
     * Dto リクエスト：IfaBbApplyListX002RequestDto
     * Dto レスポンス：IfaBbApplyListX002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq IfaBbApplyListX002RequestDto
     * @return dtoRes IfaBbApplyListX002ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListX002ResponseDto> displayX002(IfaBbApplyListX002RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaBbApplyListA004aRequestDto
     * Dto レスポンス：IfaBbApplyListA004aResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq IfaBbApplyListA004aRequestDto
     * @return dtoRes IfaBbApplyListA004aResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA004aResponseDto> csvOutputA004(IfaBbApplyListA004aRequestDto dtoReq, String sessionId)
            throws Exception;
    
    /**
     * アクションID：A005
     * アクション名：BB取消訂正
     * Dto リクエスト：IfaBbApplyListA005RequestDto
     * Dto レスポンス：IfaBbApplyListA005ResponseDto
     * model リクエスト：ifaBbApplyListSql002RequestModel
     * model レスポンス：IfaBbApplyListSql002ResponseModel
     *
     * @param dtoReq IfaBbApplyListA005RequestDto
     * @return dtoRes IfaBbApplyListA005ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA005ResponseDto> bbCancellationCorrectionA005(IfaBbApplyListA005RequestDto dtoReq)
            throws Exception;
    
    /**
     * アクションID：A006
     * アクション名：募集入力
     * Dto リクエスト：IfaBbApplyListA006RequestDto
     * Dto レスポンス：IfaBbApplyListA006ResponseDto
     * model リクエスト：ifaBbApplyListSql003RequestModel
     * model レスポンス：IfaBbApplyListSql003ResponseModel
     *
     * @param dtoReq IfaBbApplyListA006RequestDto
     * @return dtoRes IfaBbApplyListA006ResponseDto
     * @exception Exception システムエラー
     */
    public DataList<IfaBbApplyListA006ResponseDto> subscriptInputA006(IfaBbApplyListA006RequestDto dtoReq)
            throws Exception;
    
    

}
