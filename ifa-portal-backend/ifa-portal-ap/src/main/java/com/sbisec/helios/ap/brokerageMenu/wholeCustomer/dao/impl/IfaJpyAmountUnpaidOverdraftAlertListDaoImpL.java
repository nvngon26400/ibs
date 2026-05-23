package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaJpyAmountUnpaidOverdraftAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaJpyAmountUnpaidOverdraftAlertListMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/05/23 新規作成
 */
@Component
public class IfaJpyAmountUnpaidOverdraftAlertListDaoImpL extends RowSelectableDao implements IfaJpyAmountUnpaidOverdraftAlertListDao {
    
    @Autowired
    private IfaJpyAmountUnpaidOverdraftAlertListMapper mapper;
    

    /**
     * SQLID：Sql001
     * SQL名：預り金赤残顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel
     * レスポンスクラス：IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel> selectIfaJpyAmountUnpaidOverdraftAlertListSql001(IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel> res = new DataList<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJpyAmountUnpaidOverdraftAlertListSql001(req));
        return res;
    }
    
    
    
    
}
