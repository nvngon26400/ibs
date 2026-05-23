package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA001DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA002DtoResponse;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoRequest;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaCouponRedemptionPaymentScheduleListA004DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 * 2024/06/06 新規作成
 */
public interface IfaCouponRedemptionPaymentScheduleListService extends Service {
	
	 /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA001DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA001DtoResponse
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA001DtoResponse> initializeA001(IfaCouponRedemptionPaymentScheduleListA001DtoRequest dtoReq)
            throws Exception;
	
	 /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA002DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA002DtoResponse
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA002DtoResponse> displayA002(IfaCouponRedemptionPaymentScheduleListA002DtoRequest dtoReq)
            throws Exception;

	 /**
     * アクションID：A004
     * アクション名：CSV出力
     * Dto リクエスト：IfaCouponRedemptionPaymentScheduleListA004DtoRequest
     * Dto レスポンス：IfaCouponRedemptionPaymentScheduleListA004DtoResponse
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListA004DtoResponse> csvOutputA004(
            IfaCouponRedemptionPaymentScheduleListA004DtoRequest dtoReq,
            String fwSessionId) throws Exception;

}
