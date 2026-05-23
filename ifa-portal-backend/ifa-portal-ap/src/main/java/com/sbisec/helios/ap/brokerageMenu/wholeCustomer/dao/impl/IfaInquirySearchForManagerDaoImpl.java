package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaInquirySearchForManagerDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaInquirySearchForManagerMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaInquirySearchForManagerSql007ResponseModel;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Component
public class IfaInquirySearchForManagerDaoImpl extends RowSelectableDao implements IfaInquirySearchForManagerDao {

    @Autowired
    private IfaInquirySearchForManagerMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：メッセージ取得取得
     * SQLタイプ：select
     * レスポンスクラス：IfaInquirySearchForManagerSql001ResponseModel
     * 
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public IfaInquirySearchForManagerSql001ResponseModel selectIfaInquirySearchForManagerSql001() throws Exception {
        
        IfaInquirySearchForManagerSql001ResponseModel res = new IfaInquirySearchForManagerSql001ResponseModel();
        res =  mapper.selectIfaInquirySearchForManagerSql001();
        
        return res;
    }

    /**
     * SQLID:Sql002
     * SQL名:カテゴリ（大）リスト取得
     * SQLタイプ:select
     * レスポンスクラス:IfaContactInputSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public List<IfaInquirySearchForManagerSql002ResponseModel> selectIfaInquirySearchForManagerSql002()
        throws Exception {
        
        List<IfaInquirySearchForManagerSql002ResponseModel> res = new ArrayList<IfaInquirySearchForManagerSql002ResponseModel>();
        res = mapper.selectIfaInquirySearchForManagerSql002();
        
        return res;
    }

    /**
     * SQLID:Sql003
     * SQL名:カテゴリ（中）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql003RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql003ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public List<IfaInquirySearchForManagerSql003ResponseModel> selectIfaInquirySearchForManagerSql003(
        IfaInquirySearchForManagerSql003RequestModel req) throws Exception {
        
        List<IfaInquirySearchForManagerSql003ResponseModel> res = new ArrayList<IfaInquirySearchForManagerSql003ResponseModel>();
        res = mapper.selectIfaInquirySearchForManagerSql003(req);
        
        return res;
    }

    /**
     * SQLID:Sql004
     * SQL名:カテゴリ（小）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql004RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql004ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public List<IfaInquirySearchForManagerSql004ResponseModel> selectIfaInquirySearchForManagerSql004(
        IfaInquirySearchForManagerSql004RequestModel req) throws Exception {
        
        List<IfaInquirySearchForManagerSql004ResponseModel> res = new ArrayList<IfaInquirySearchForManagerSql004ResponseModel>();
        res = mapper.selectIfaInquirySearchForManagerSql004(req);
        
        return res;
        
    }

    /**
     * SQLID:Sql005
     * SQL名:接触履歴（入力）リスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql005RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql005ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public List<IfaInquirySearchForManagerSql005ResponseModel> selectIfaInquirySearchForManagerSql005(
        IfaInquirySearchForManagerSql005RequestModel req) throws Exception {
        
        List<IfaInquirySearchForManagerSql005ResponseModel> res = new ArrayList<IfaInquirySearchForManagerSql005ResponseModel>();
        res = mapper.selectIfaInquirySearchForManagerSql005(req);
        
        return res;
    }

    /**
     * SQLID:Sql006
     * SQL名:明細件数取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql006RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql006ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public IfaInquirySearchForManagerSql006ResponseModel selectIfaInquirySearchForManagerSql006(
        IfaInquirySearchForManagerSql006RequestModel req) throws Exception {
        
        IfaInquirySearchForManagerSql006ResponseModel res = new IfaInquirySearchForManagerSql006ResponseModel();
        res = mapper.selectIfaInquirySearchForManagerSql006(req);
        
        return res;
    }

    /**
     * SQLID:Sql007
     * SQL名:明細件数取得
     * SQLタイプ:select
     * リクエストクラス:IfaInquirySearchForManagerSql007RequestModel
     * レスポンスクラス:IfaInquirySearchForManagerSql007ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    @Override
    public IfaInquirySearchForManagerSql007ResponseModel selectIfaInquirySearchForManagerSql007(
        IfaInquirySearchForManagerSql007RequestModel req) throws Exception {
        
        IfaInquirySearchForManagerSql007ResponseModel res = new IfaInquirySearchForManagerSql007ResponseModel();
        res = mapper.selectIfaInquirySearchForManagerSql007(req);
        
        return res;
    }
    
}
