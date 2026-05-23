package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.IfaMonthCustomerNumDao;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.mapper.IfaMonthCustomerNumMapper;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dao.model.IfaMonthCustomerNumSql003ResponseModel;


/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Component
public class IfaMonthCustomerNumDaoImpl extends RowSelectableDao implements IfaMonthCustomerNumDao{
    
    @Autowired
    private IfaMonthCustomerNumMapper mapper;
    
    
    /**
     * SQLID：Sql002
     * SQL名：月末口座数リスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaMonthCustomerNumSql002RequestModel
     * レスポンスクラス：IfaMonthCustomerNumSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumSql002ResponseModel> selectIfaMonthCustomerNumSql002(
            IfaMonthCustomerNumSql002RequestModel req) throws Exception {
        
        DataList<IfaMonthCustomerNumSql002ResponseModel> res = new DataList<IfaMonthCustomerNumSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaMonthCustomerNumSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：仲介業者顧客情報リスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaMonthCustomerNumSql003RequestModel
     * レスポンスクラス：IfaMonthCustomerNumSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaMonthCustomerNumSql003ResponseModel> selectIfaMonthCustomerNumSql003(
            IfaMonthCustomerNumSql003RequestModel req) throws Exception {
        
        DataList<IfaMonthCustomerNumSql003ResponseModel> res = new DataList<IfaMonthCustomerNumSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaMonthCustomerNumSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：基準年月取得
     * SQLタイプ：select
     * リクエストクラス：String
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public String selectIfaMonthCustomerNumSql004() 
            throws Exception {

        return mapper.selectIfaMonthCustomerNumSql004();
    }
}
