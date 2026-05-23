package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql002ResponseModel;

/**
 * 
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 2024/06/06 新規作成
 */
@Mapper
public interface IfaCouponRedemptionPaymentScheduleListMapper {
    
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
    public List<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> selectIfaCouponRedemptionPaymentScheduleListSql001(
        @Param("req") IfaCouponRedemptionPaymentScheduleListSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：利金・償還金支払予定一覧コメント取得
     * SQLタイプ：select
     * レスポンスクラス：IfaCouponRedemptionPaymentScheduleListSql002ResponseModel
	 *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel> selectIfaCouponRedemptionPaymentScheduleListSql002() throws Exception;
    
}
