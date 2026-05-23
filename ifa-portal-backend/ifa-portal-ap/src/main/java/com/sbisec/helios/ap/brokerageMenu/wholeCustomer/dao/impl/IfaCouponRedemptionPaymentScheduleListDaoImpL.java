package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaCouponRedemptionPaymentScheduleListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaCouponRedemptionPaymentScheduleListMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCouponRedemptionPaymentScheduleListSql002ResponseModel;





/**
 * 画面ID：SUB020302_0104-01
 * 画面名：利金・償還金支払予定一覧
 *
 * @author SCSK濱田
 2024/06/06 新規作成
 */
@Component
public class IfaCouponRedemptionPaymentScheduleListDaoImpL extends RowSelectableDao implements IfaCouponRedemptionPaymentScheduleListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCouponRedemptionPaymentScheduleListDaoImpL.class);
    @Autowired
    private IfaCouponRedemptionPaymentScheduleListMapper mapper;
    
	
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
            throws Exception {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCouponRedemptionPaymentScheduleListDaoImpL.selectIfaCouponRedemptionPaymentScheduleListSql001");
        }
        
        DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel> res = new DataList<IfaCouponRedemptionPaymentScheduleListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaCouponRedemptionPaymentScheduleListSql001(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql002
     * SQL名：利金・償還金支払予定一覧コメント取得
     * SQLタイプ：select
     * レスポンスクラス：IfaCouponRedemptionPaymentScheduleListSql002ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel> selectIfaCouponRedemptionPaymentScheduleListSql002()
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaCouponRedemptionPaymentScheduleListDaoImpL.selectIfaCouponRedemptionPaymentScheduleListSql002");
        }
        DataList<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel> res = new DataList<IfaCouponRedemptionPaymentScheduleListSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaCouponRedemptionPaymentScheduleListSql002());
        return res;
    }
}
