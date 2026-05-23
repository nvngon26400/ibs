package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignAmountUnpaidOverdraftAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaForeignAmountUnpaidOverdraftAlertListMapper;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel;





/**
 * 画面ID：SUB020301_01-03
 * 画面名：外貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/06/12 新規作成
 */
@Component
public class IfaForeignAmountUnpaidOverdraftAlertListDaoImpL extends RowSelectableDao implements IfaForeignAmountUnpaidOverdraftAlertListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignAmountUnpaidOverdraftAlertListDaoImpL.class);
    @Autowired
    private IfaForeignAmountUnpaidOverdraftAlertListMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：外貨預り金赤残顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel
     * レスポンスクラス：IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel> selectIfaForeignAmountUnpaidOverdraftAlertListSql001(IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel> res = new DataList<IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaForeignAmountUnpaidOverdraftAlertListSql001(req));
        return res;
    }
    
    
    
    
}
