package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaStructuredBondBrandInfoDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper.IfaStructuredBondBrandInfoMapper;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaStructuredBondBrandInfoSql007ResponseModel;

/**
 * 画面ID：SUB020302_0104-02
 * 画面名：仕組債銘柄情報
 *
 * @author SCSK川崎
 2024/06/11 新規作成
 */
@Component
public class IfaStructuredBondBrandInfoDaoImpL extends RowSelectableDao implements IfaStructuredBondBrandInfoDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaStructuredBondBrandInfoDaoImpL.class);
    
    @Autowired
    private IfaStructuredBondBrandInfoMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：仕組債マスタ取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql001RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql001ResponseModel> selectIfaStructuredBondBrandInfoSql001(
            IfaStructuredBondBrandInfoSql001RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql001ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：参照営業日カレンダー取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql002RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql002ResponseModel> selectIfaStructuredBondBrandInfoSql002(
            IfaStructuredBondBrandInfoSql002RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql002ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：参照取引所カレンダー取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql003RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql003ResponseModel> selectIfaStructuredBondBrandInfoSql003(
            IfaStructuredBondBrandInfoSql003RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql003ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：コード値の表示用文言取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql004RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql004ResponseModel> selectIfaStructuredBondBrandInfoSql004(
            IfaStructuredBondBrandInfoSql004RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql004ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql004(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：早期償還判定日取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql005RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql005ResponseModel> selectIfaStructuredBondBrandInfoSql005(
            IfaStructuredBondBrandInfoSql005RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql005ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：仕組債判定条件マスタ取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql006RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql006ResponseModel> selectIfaStructuredBondBrandInfoSql006(
            IfaStructuredBondBrandInfoSql006RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql006ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql006(req));
        return res;
    }
    
    /**
     * SQLID：Sql007
     * SQL名：参照銘柄リスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaStructuredBondBrandInfoSql007RequestModel
     * レスポンスクラス：IfaStructuredBondBrandInfoSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaStructuredBondBrandInfoSql007ResponseModel> selectIfaStructuredBondBrandInfoSql007(
            IfaStructuredBondBrandInfoSql007RequestModel req) throws Exception {
        
        DataList<IfaStructuredBondBrandInfoSql007ResponseModel> res = 
                new DataList<IfaStructuredBondBrandInfoSql007ResponseModel>();
        
        res.setDataList(mapper.selectIfaStructuredBondBrandInfoSql007(req));
        return res;
    }
    
}
