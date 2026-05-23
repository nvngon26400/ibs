package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.IfaPersonalInfoManageLedgerListDao;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.mapper.IfaPersonalInfoManageLedgerListMapper;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql001ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql004RequestModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005ResponseModel;


/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */
@Component
public class IfaPersonalInfoManageLedgerListDaoImpL extends RowSelectableDao implements IfaPersonalInfoManageLedgerListDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPersonalInfoManageLedgerListDaoImpL.class);
    @Autowired
    private IfaPersonalInfoManageLedgerListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：個人情報管理台帳作成ディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql001RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql001ResponseModel

     * @return IfaPersonalInfoManageLedgerListSql001ResponseModel
     * @exception Exception SQLExeptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListSql001ResponseModel> selectIfaPersonalInfoManageLedgerListSql001()
            throws Exception {
        
        DataList<IfaPersonalInfoManageLedgerListSql001ResponseModel> res = new DataList<IfaPersonalInfoManageLedgerListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPersonalInfoManageLedgerListSql001());
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：個人情報管理台帳一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql003RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql003ResponseModel

     * @param req リクエスト
     * @return IfaPersonalInfoManageLedgerListSql003ResponseModel
     * @exception Exception SQLExeptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListSql003ResponseModel> selectIfaPersonalInfoManageLedgerListSql003(IfaPersonalInfoManageLedgerListSql003RequestModel req)
            throws Exception {
        
        DataList<IfaPersonalInfoManageLedgerListSql003ResponseModel> res = new DataList<IfaPersonalInfoManageLedgerListSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaPersonalInfoManageLedgerListSql003(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql004
     * SQL名：個人情報管理台帳更新
     * SQLタイプ：update
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql004RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql004ResponseModel

     * @param req リクエスト
     * @return IfaPersonalInfoManageLedgerListSql004ResponseModel
     * @exception Exception SQLExeptionなど
     */
    public int updateIfaPersonalInfoManageLedgerListSql004(IfaPersonalInfoManageLedgerListSql004RequestModel req)
            throws Exception {

        return mapper.updateIfaPersonalInfoManageLedgerListSql004(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：個人情報管理台帳一覧件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql005RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql005ResponseModel

     * @param req リクエスト
     * @return IfaPersonalInfoManageLedgerListSql005ResponseModel
     * @exception Exception SQLExeptionなど
     */
    public DataList<IfaPersonalInfoManageLedgerListSql005ResponseModel> selectIfaPersonalInfoManageLedgerListSql005(IfaPersonalInfoManageLedgerListSql005RequestModel req)
            throws Exception {
        
        DataList<IfaPersonalInfoManageLedgerListSql005ResponseModel> res = new DataList<IfaPersonalInfoManageLedgerListSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaPersonalInfoManageLedgerListSql005(req));
        return res;
    }
    
    
    
}
