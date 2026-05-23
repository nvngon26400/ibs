package com.sbisec.helios.ap.wholePortal.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aRequestDto;
import com.sbisec.helios.ap.wholePortal.dto.IfaCustomerAlertCsvOutputA002aResponseDto;

/**
 * 画面ID：SUB01-02
 * 画面名：顧客アラートCSV出力
 *
 * @author SCSK丹波
 2024/05/16 新規作成
 */
public interface IfaCustomerAlertCsvOutputService extends Service {
    
    /**
     * アクションID：A002a
     * アクション名：CSV出力
     * Dto リクエスト：IfaCustomerAlertCsvOutputA002aRequestDto
     * Dto レスポンス：IfaCustomerAlertCsvOutputA002aResponseDto
     * model リクエスト：IfaCustomerAlertCsvOutputA002aRequestModel
     * model レスポンス：IfaCustomerAlertCsvOutputA002aResponseModel
     *
     * @param dtoReq リクエスト
     * @param frameworkSessionId フレームワークセッションID
     * @return レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCustomerAlertCsvOutputA002aResponseDto> csvOutputA002a(
            IfaCustomerAlertCsvOutputA002aRequestDto dtoReq, String frameworkSessionId) throws Exception;
    
}
