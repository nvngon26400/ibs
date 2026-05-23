package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyCorrectCancelInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaBbApplyCorrectCancelInputMapper;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql004ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql005ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql006ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql007ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql008ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql009ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql010ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql012ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql013ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCorrectCancelInputSql014ResponseModel;





/**
 * 画面ID：SUB0204_02-02_1
 * 画面名：BB申込訂正・取消入力
 *
 * @author BASE 李
 2024/04/15 新規作成
 */
@Component
public class IfaBbApplyCorrectCancelInputDaoImpL extends RowSelectableDao implements IfaBbApplyCorrectCancelInputDao {
    
    @Autowired
    private IfaBbApplyCorrectCancelInputMapper mapper;
    
    
    /**
     * SQLID：Sql001
     * SQL名：申込期間内銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql001RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel> selectIfaBbApplyCorrectCancelInputSql001(IfaBbApplyCorrectCancelInputSql001RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql001(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql002
     * SQL名：投資家属性のプルダウンリスト取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql002RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql002ResponseModel> selectIfaBbApplyCorrectCancelInputSql002(IfaBbApplyCorrectCancelInputSql002RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql002ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql002(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql003
     * SQL名：顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql003RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel> selectIfaBbApplyCorrectCancelInputSql003(IfaBbApplyCorrectCancelInputSql003RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql003(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql004
     * SQL名：年間裁量配分割当回数情報を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql004RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel> selectIfaBbApplyCorrectCancelInputSql004(IfaBbApplyCorrectCancelInputSql004RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql004(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql005
     * SQL名：裁量配分割当回数(未抽選)情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql005RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel> selectIfaBbApplyCorrectCancelInputSql005(IfaBbApplyCorrectCancelInputSql005RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql005(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql006
     * SQL名：移管前の裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql006RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel> selectIfaBbApplyCorrectCancelInputSql006(IfaBbApplyCorrectCancelInputSql006RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql006(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql007
     * SQL名：預り資産額を取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql007RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql007ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql007ResponseModel> selectIfaBbApplyCorrectCancelInputSql007(IfaBbApplyCorrectCancelInputSql007RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql007ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql007ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql007(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql008
     * SQL名：目論見書閲覧状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql008RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql008ResponseModel> selectIfaBbApplyCorrectCancelInputSql008(IfaBbApplyCorrectCancelInputSql008RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql008ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql008ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql008(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql009
     * SQL名：銘柄コード存在件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql009RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql009ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql009ResponseModel> selectIfaBbApplyCorrectCancelInputSql009(IfaBbApplyCorrectCancelInputSql009RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql009ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql009ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql009(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql010
     * SQL名：入力データ件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql010RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql010ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql010ResponseModel> selectIfaBbApplyCorrectCancelInputSql010(IfaBbApplyCorrectCancelInputSql010RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql010ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql010ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql010(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql012
     * SQL名：最良配分あるかフラグ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql012RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql012ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql012ResponseModel> selectIfaBbApplyCorrectCancelInputSql012(IfaBbApplyCorrectCancelInputSql012RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql012ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql012ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql012(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql013
     * SQL名：上限値(売買単位*上限単元数量)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql013RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql013ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql013ResponseModel> selectIfaBbApplyCorrectCancelInputSql013(IfaBbApplyCorrectCancelInputSql013RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql013ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql013ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql013(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql014
     * SQL名：BB申込内容取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyCorrectCancelInputSql014RequestModel
     * レスポンスクラス：IfaBbApplyCorrectCancelInputSql014ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaBbApplyCorrectCancelInputSql014ResponseModel> selectIfaBbApplyCorrectCancelInputSql014(IfaBbApplyCorrectCancelInputSql014RequestModel req)
            throws Exception {
        
        DataList<IfaBbApplyCorrectCancelInputSql014ResponseModel> res = new DataList<IfaBbApplyCorrectCancelInputSql014ResponseModel>();
        
        res.setDataList(mapper.selectIfaBbApplyCorrectCancelInputSql014(req));
        return res;
    }
    
    
    
    
}
