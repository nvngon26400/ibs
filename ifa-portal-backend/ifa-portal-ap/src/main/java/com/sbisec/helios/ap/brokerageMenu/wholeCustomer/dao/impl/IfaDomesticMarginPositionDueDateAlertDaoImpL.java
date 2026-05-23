package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaDomesticMarginPositionDueDateAlertDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaDomesticMarginPositionDueDateAlertMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020301_02-01
 * 画面名：国内信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/19 新規作成
 */
@Component
public class IfaDomesticMarginPositionDueDateAlertDaoImpL extends RowSelectableDao
        implements IfaDomesticMarginPositionDueDateAlertDao {
    
    @Autowired
    private IfaDomesticMarginPositionDueDateAlertMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：決済期日顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMarginPositionDueDateAlertSql001RequestModel
     * レスポンスクラス：IfaDomesticMarginPositionDueDateAlertSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaDomesticMarginPositionDueDateAlertSql001ResponseModel> selectIfaDomesticMarginPositionDueDateAlertSql001(
            IfaDomesticMarginPositionDueDateAlertSql001RequestModel req) throws Exception {
        
        DataList<IfaDomesticMarginPositionDueDateAlertSql001ResponseModel> res = new DataList<IfaDomesticMarginPositionDueDateAlertSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDomesticMarginPositionDueDateAlertSql001(req));
        return res;
    }
    
    @Override
    public Integer selectIfaDomesticMarginPositionDueDateAlertSql005() throws Exception {
        
        return mapper.selectIfaDomesticMarginPositionDueDateAlertSql005();
    }
    
    @Override
    public String selectIfaDomesticMarginPositionDueDateAlertSql006() throws Exception {
        
        return mapper.selectIfaDomesticMarginPositionDueDateAlertSql006();
    }
    
}
