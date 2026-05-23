package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerPortalDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaCustomerPortalMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003InsertRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectExclusiveResquestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003SelectResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql003UpdateRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerPortalSqlStbResponseModel;

/**
 * 画面ID：SUB0202_00-02
 * 画面名：顧客ポータル_顧客情報
 * @author <author-name>
 *
 * 2023/11/30 新規作成
 */
@Component
public class IfaCustomerPortalDaoImpL extends RowSelectableDao implements IfaCustomerPortalDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerPortalDaoImpL.class);
    
    @Autowired
    private IfaCustomerPortalMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql002RequestModel
     * レスポンスクラス：IfaCustomerPortalSql002ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql002ResponseModel> selectIfaCustomerPortalSql002(
            IfaCustomerPortalSql002RequestModel req) throws Exception {
        
        DataList<IfaCustomerPortalSql002ResponseModel> res = new DataList<IfaCustomerPortalSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：CCS顧客メモ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql004RequestModel
     * レスポンスクラス：IfaCustomerPortalSql004ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql004ResponseModel> selectIfaCustomerPortalSql004(
            IfaCustomerPortalSql004RequestModel req) throws Exception {
        
        DataList<IfaCustomerPortalSql004ResponseModel> res = new DataList<IfaCustomerPortalSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSql004(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：コース名情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql005RequestModel
     * レスポンスクラス：IfaCustomerPortalSql005ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql005ResponseModel> selectIfaCustomerPortalSql005(
            IfaCustomerPortalSql005RequestModel req) throws Exception {
        
        DataList<IfaCustomerPortalSql005ResponseModel> res = new DataList<IfaCustomerPortalSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql003SelectRequestModel
     * レスポンスクラス：IfaCustomerPortalSql003SelectResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql003SelectResponseModel> selectIfaCustomerPortalSql003(
            IfaCustomerPortalSql003SelectRequestModel req) throws Exception {
        
        DataList<IfaCustomerPortalSql003SelectResponseModel> res = new DataList<IfaCustomerPortalSql003SelectResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得(排他制御)
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql003SelectExclusiveRequestModel
     * レスポンスクラス：IfaCustomerPortalSql003SelectExclusiveResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @throws Exception 
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql003SelectExclusiveResponseModel> selectExclusiveIfaCustomerPortalSql003(IfaCustomerPortalSql003SelectExclusiveResquestModel req) throws Exception{
        DataList<IfaCustomerPortalSql003SelectExclusiveResponseModel> res = new DataList<IfaCustomerPortalSql003SelectExclusiveResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSql003Exclusive(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：insert
     * リクエストクラス：IfaCustomerPortalSql003InsertRequestModel
     * レスポンスクラス：IfaCustomerPortalSql003InsertResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int insertIfaCustomerPortalSql003(IfaCustomerPortalSql003InsertRequestModel req) throws Exception {
        
        return mapper.insertIfaCustomerPortalSql003(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：メモ(IFA専用)取得
     * SQLタイプ：update
     * リクエストクラス：IfaCustomerPortalSql003RequestModel
     * レスポンスクラス：IfaCustomerPortalSql003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public int updateIfaCustomerPortalSql003(IfaCustomerPortalSql003UpdateRequestModel req) throws Exception {
        
        return mapper.updateIfaCustomerPortalSql003(req);
    }
    
    /**
     * SQLID：SqlStb
     * SQL名：コース名情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSqlStbRequestModel
     * レスポンスクラス：IfaCustomerPortalSqlStbResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSqlStbResponseModel> selectIfaCustomerPortalSqlStb(
            IfaCustomerPortalSqlStbRequestModel req) throws Exception {
        
        DataList<IfaCustomerPortalSqlStbResponseModel> res = new DataList<IfaCustomerPortalSqlStbResponseModel>();
        
        res.setDataList(mapper.selectIfaCustomerPortalSqlStb(req));
        return res;
    }

    /**
     * SQLID:Sql007
     * SQL名：仲介業者顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql007RequestModel
     * レスポンスクラス：IfaCustomerPortalSql007ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql007ResponseModel> selectIfaCustomerPortalSql007(
        IfaCustomerPortalSql007RequestModel req) throws Exception {
        DataList<IfaCustomerPortalSql007ResponseModel> res = new DataList<IfaCustomerPortalSql007ResponseModel>();
        res.setDataList(mapper.selectIfaCustomerPortalSql007(req));
        return res;
    }

    /**
     * SQLID:Sql008
     * SQL名：顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql008RequestModel
     * レスポンスクラス：IfaCustomerPortalSql008ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql008ResponseModel> selectIfaCustomerPortalSql008(
        IfaCustomerPortalSql008RequestModel req) throws Exception {
        DataList<IfaCustomerPortalSql008ResponseModel> res = new DataList<IfaCustomerPortalSql008ResponseModel>();
        res.setDataList(mapper.selectIfaCustomerPortalSql008(req));
        return res;
    }

    /**
     * SQLID:Sql009
     * SQL名：仲介業者顧客口座属性情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql009RequestModel
     * レスポンスクラス：IfaCustomerPortalSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql009ResponseModel> selectIfaCustomerPortalSql009(
        IfaCustomerPortalSql009RequestModel req) throws Exception {
        DataList<IfaCustomerPortalSql009ResponseModel> res = new DataList<IfaCustomerPortalSql009ResponseModel>();
        res.setDataList(mapper.selectIfaCustomerPortalSql009(req));
        return res;
    }

    /**
     * SQLID:Sql010
     * SQL名：EC顧客マスタビュー情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerPortalSql010RequestModel
     * レスポンスクラス：IfaCustomerPortalSql010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCustomerPortalSql010ResponseModel> selectIfaCustomerPortalSql010(
        IfaCustomerPortalSql010RequestModel req) throws Exception {
        DataList<IfaCustomerPortalSql010ResponseModel> res = new DataList<IfaCustomerPortalSql010ResponseModel>();
        res.setDataList(mapper.selectIfaCustomerPortalSql010(req));
        return res;
    }

    /**
     * SQLID：Sql011
     * SQL名：店群情報取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return String レスポンス
     * @throws Exception 初期化処理で例外が発生した場合
     */
    @Override
    public String selectIfaCustomerPortalSql011(String req) throws Exception {
        return mapper.selectIfaCustomerPortalSql011(req);
    }
    
}
