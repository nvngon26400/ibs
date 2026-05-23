package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaOrderStatusListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaOrderStatusListMapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004ResponseModel;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Component
public class IfaOrderStatusListDaoImpL extends RowSelectableDao implements IfaOrderStatusListDao {
    @Autowired
    private IfaOrderStatusListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：募集注文一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql001RequestModel
     * レスポンスクラス：IfaOrderStatusListSql001ResponseModel
     *
     * @param req IfaOrderStatusListSql001RequestModel
     * @return IfaOrderStatusListSql001ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListSql001ResponseModel> selectIfaOrderStatusListSql001(IfaOrderStatusListSql001RequestModel req)
            throws Exception {
        
        DataList<IfaOrderStatusListSql001ResponseModel> res = new DataList<IfaOrderStatusListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderStatusListSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：外株店頭注文取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql002RequestModel
     * レスポンスクラス：IfaOrderStatusListSql002ResponseModel
     *
     * @param req IfaOrderStatusListSql002RequestModel
     * @return IfaOrderStatusListSql002ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListSql002ResponseModel> selectIfaOrderStatusListSql002(IfaOrderStatusListSql002RequestModel req)
            throws Exception {
        
        DataList<IfaOrderStatusListSql002ResponseModel> res = new DataList<IfaOrderStatusListSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderStatusListSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：外株店頭注文存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql003RequestModel
     * レスポンスクラス：IfaOrderStatusListSql003ResponseModel
     *
     * @param req IfaOrderStatusListSql003RequestModel
     * @return IfaOrderStatusListSql003ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListSql003ResponseModel> selectIfaOrderStatusListSql003(IfaOrderStatusListSql003RequestModel req)
            throws Exception {
        
        DataList<IfaOrderStatusListSql003ResponseModel> res = new DataList<IfaOrderStatusListSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderStatusListSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：CCS認証ID取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql004RequestModel
     * レスポンスクラス：IfaOrderStatusListSql004ResponseModel
     *
     * @param req IfaOrderStatusListSql004RequestModel
     * @return IfaOrderStatusListSql004ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaOrderStatusListSql004ResponseModel> selectIfaOrderStatusListSql004(IfaOrderStatusListSql004RequestModel req)
            throws Exception {
        
        DataList<IfaOrderStatusListSql004ResponseModel> res = new DataList<IfaOrderStatusListSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaOrderStatusListSql004(req));
        return res;
    }
    
    
    
    
}
