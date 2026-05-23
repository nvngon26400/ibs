package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql002ResponseModel;





/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 2024/06/06 新規作成
 *
 */
public interface IfaCouponRedemptionPaymentScheduleListDao {
    
	
    /**
     * SQLID：Sql001
     * SQL名：利金償還金支払予定一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCouponRedemptionPaymentScheduleListSql001RequestModel
     * レスポンスクラス：IfaCouponRedemptionPaymentScheduleListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selectIfaCouponRedemptionPaymentScheduleListSql001(IfaCouponRedemptionPaymentScheduleListSql001RequestModel req)
            throws Exception;
    
	
    /**
     * SQLID：Sql002
     * SQL名：利金・償還金支払予定一覧コメント取得
     * SQLタイプ：select
     * レスポンスクラス：IfaCouponRedemptionPaymentScheduleListSql002ResponseModel
	 *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel> selectIfaCouponRedemptionPaymentScheduleListSql002()
            throws Exception;

}
