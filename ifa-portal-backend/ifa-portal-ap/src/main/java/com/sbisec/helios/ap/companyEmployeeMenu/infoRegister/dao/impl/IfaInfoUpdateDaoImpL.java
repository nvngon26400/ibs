package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoUpdateDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper.IfaInfoUpdateMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql001ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql002ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql008ResponseModel;


import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql003RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql004RequestModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoUpdateSql005RequestModel;


/**
 * 画面ID：SUB0501_01-03_1
 * 画面名：情報更新
 *
 * @author SCSK 大崎
 2024/05/23 新規作成
 */
@Component
public class IfaInfoUpdateDaoImpL extends RowSelectableDao implements IfaInfoUpdateDao {
    
    @Autowired
    private IfaInfoUpdateMapper mapper;
    
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql001RequestModel
     * レスポンスクラス：IfaInfoUpdateSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateSql001ResponseModel> selectIfaInfoUpdateSql001(IfaInfoUpdateSql001RequestModel req)
            throws Exception {
        
        DataList<IfaInfoUpdateSql001ResponseModel> res = new DataList<IfaInfoUpdateSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoUpdateSql001(req));
        return res;
    }
    
    
    /**
     * SQLID：Sql002
     * SQL名：情報明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql002RequestModel
     * レスポンスクラス：IfaInfoUpdateSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateSql002ResponseModel> selectIfaInfoUpdateSql002(IfaInfoUpdateSql002RequestModel req)
            throws Exception {
        
        DataList<IfaInfoUpdateSql002ResponseModel> res = new DataList<IfaInfoUpdateSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoUpdateSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql008
     * SQL名：登録ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoUpdateSql008RequestModel
     * レスポンスクラス：IfaInfoUpdateSql008ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoUpdateSql008ResponseModel> selectIfaInfoUpdateSql008(IfaInfoUpdateSql008RequestModel req)
            throws Exception {
        
        DataList<IfaInfoUpdateSql008ResponseModel> res = new DataList<IfaInfoUpdateSql008ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoUpdateSql008(req));
        return res;
    }
    
    
    
    /**
     * SQLID：Sql003
     * SQL名：新お知らせテーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaInfoUpdateSql003RequestModel
     * レスポンスクラス：IfaInfoUpdateSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaInfoUpdateSql003(IfaInfoUpdateSql003RequestModel req)
            throws Exception {

        return mapper.updateIfaInfoUpdateSql003(req);
    }
    
    
    
    /**
     * SQLID：Sql004
     * SQL名：新お知らせ参照権限テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaInfoUpdateSql004RequestModel
     * レスポンスクラス：IfaInfoUpdateSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoUpdateSql004(IfaInfoUpdateSql004RequestModel req)
            throws Exception {

        return mapper.insertIfaInfoUpdateSql004(req);
    }
    
    
    /**
     * SQLID：Sql005
     * SQL名：新お知らせ既読テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaInfoUpdateSql005RequestModel
     * レスポンスクラス：IfaInfoUpdateSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaInfoUpdateSql005(IfaInfoUpdateSql005RequestModel req)
            throws Exception {

        return mapper.insertIfaInfoUpdateSql005(req);
    }
    
    
}
