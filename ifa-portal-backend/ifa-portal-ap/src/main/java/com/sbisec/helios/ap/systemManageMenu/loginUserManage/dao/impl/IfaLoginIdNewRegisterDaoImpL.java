package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.IfaLoginIdNewRegisterDao;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.mapper.IfaLoginIdNewRegisterMapper;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql002ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql004ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql005ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql006ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql007ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql008RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql009RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql010RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql011RequestModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql011ResponseModel;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao.model.IfaLoginIdNewRegisterSql012RequestModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 画面ID：SUB0601_01-02_1
 * 画面名：ログインID新規登録
 * @author 布施佑太
 *
 * 2023/11/09 新規作成
 */
@Component
public class IfaLoginIdNewRegisterDaoImpL extends RowSelectableDao implements IfaLoginIdNewRegisterDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaLoginIdNewRegisterDaoImpL.class);
    @Autowired
    private IfaLoginIdNewRegisterMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：メニュー一覧取得（権限毎）
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql002RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql002ResponseModel> selectIfaLoginIdNewRegisterSql002(IfaLoginIdNewRegisterSql002RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql002ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：本支名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql004RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql004ResponseModel> selectIfaLoginIdNewRegisterSql004(IfaLoginIdNewRegisterSql004RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql004ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql004(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql005RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql005ResponseModel> selectIfaLoginIdNewRegisterSql005(IfaLoginIdNewRegisterSql005RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql005ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：仲介業者支店名取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql006RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql006ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql006ResponseModel> selectIfaLoginIdNewRegisterSql006(IfaLoginIdNewRegisterSql006RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql006ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql006(req));
        return res;
    }
    
    /**
     * SQLID：Sql007
     * SQL名：担当者名dropDownList取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql007RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql007ResponseModel> selectIfaLoginIdNewRegisterSql007(IfaLoginIdNewRegisterSql007RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql007ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql007ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql007(req));
        return res;
    }
    
    /**
     * SQLID：Sql011
     * SQL名：Horusユーザー情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaLoginIdNewRegisterSql011RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql011ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaLoginIdNewRegisterSql011ResponseModel> selectIfaLoginIdNewRegisterSql011(IfaLoginIdNewRegisterSql011RequestModel req)
            throws Exception {
        
        DataList<IfaLoginIdNewRegisterSql011ResponseModel> res = new DataList<IfaLoginIdNewRegisterSql011ResponseModel>();
        
        res.setDataList(mapper.selectIfaLoginIdNewRegisterSql011(req));
        return res;
    }
    
    
    
    /**
     * SQLID：Sql008
     * SQL名：ユーザ情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql008RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql008(IfaLoginIdNewRegisterSql008RequestModel req)
            throws Exception {

        return mapper.insertIfaLoginIdNewRegisterSql008(req);
    }
    
    /**
     * SQLID：Sql010
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql010RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql010(IfaLoginIdNewRegisterSql010RequestModel req)
            throws Exception {

        return mapper.insertIfaLoginIdNewRegisterSql010(req);
    }
    
    /**
     * SQLID：Sql012
     * SQL名：認証ユーザー情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaLoginIdNewRegisterSql012RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql012ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaLoginIdNewRegisterSql012(IfaLoginIdNewRegisterSql012RequestModel req)
            throws Exception {

        return mapper.insertIfaLoginIdNewRegisterSql012(req);
    }
    
    
    /**
     * SQLID：Sql009
     * SQL名：Cordysユーザーとメニューマッピング情報_新テーブル削除
     * SQLタイプ：delete
     * リクエストクラス：IfaLoginIdNewRegisterSql009RequestModel
     * レスポンスクラス：IfaLoginIdNewRegisterSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int deleteIfaLoginIdNewRegisterSql009(IfaLoginIdNewRegisterSql009RequestModel req)
            throws Exception {

        return mapper.deleteIfaLoginIdNewRegisterSql009(req);
    }
    
}
