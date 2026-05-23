package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignMarginPositionDueDateAlertDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaForeignMarginPositionDueDateAlertMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020301_02-02
 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author BASE 李
 2024/06/21 新規作成
 */
@Component
public class IfaForeignMarginPositionDueDateAlertDaoImpL extends RowSelectableDao
        implements IfaForeignMarginPositionDueDateAlertDao {
    
    @Autowired
    private IfaForeignMarginPositionDueDateAlertMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：決済期日顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginPositionDueDateAlertSql001RequestModel
     * レスポンスクラス：IfaForeignMarginPositionDueDateAlertSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginPositionDueDateAlertSql001ResponseModel> selectIfaForeignMarginPositionDueDateAlertSql001(
            IfaForeignMarginPositionDueDateAlertSql001RequestModel req) throws Exception {
        
        DataList<IfaForeignMarginPositionDueDateAlertSql001ResponseModel> res = new DataList<IfaForeignMarginPositionDueDateAlertSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignMarginPositionDueDateAlertSql001(req));
        return res;
    }
    
    @Override
    public Integer selectIfaForeignMarginPositionDueDateAlertSql005() throws Exception {
        
        return mapper.selectIfaForeignMarginPositionDueDateAlertSql005();
    }
    
    @Override
    public String selectIfaForeignMarginPositionDueDateAlertSql006() throws Exception {
        
        return mapper.selectIfaForeignMarginPositionDueDateAlertSql006();
    }
    
}
