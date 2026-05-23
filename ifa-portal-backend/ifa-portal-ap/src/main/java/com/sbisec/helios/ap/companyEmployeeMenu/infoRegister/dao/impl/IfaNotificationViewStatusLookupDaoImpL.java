package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaNotificationViewStatusLookupDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper.IfaNotificationViewStatusLookupMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaNotificationViewStatusLookupSql007RequestModel;


/**
 * 画面ID：SUB0501_01-05
 * 画面名：お知らせ閲覧状況照会
 *
 */
@Component
public class IfaNotificationViewStatusLookupDaoImpL extends RowSelectableDao implements IfaNotificationViewStatusLookupDao {
    
    @Autowired
    private IfaNotificationViewStatusLookupMapper mapper;
    
	
    /**
     * SQLID：Sql001
     * SQL名：お知らせ閲覧状況の情報取得（全担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql001RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql001ResponseModel> selectIfaNotificationViewStatusLookupSql001(IfaNotificationViewStatusLookupSql001RequestModel req)
            throws Exception {
        
        DataList<IfaNotificationViewStatusLookupSql001ResponseModel> res = new DataList<IfaNotificationViewStatusLookupSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaNotificationViewStatusLookupSql001(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql002
     * SQL名：お知らせ閲覧状況の情報取得（権限担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql002RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql002ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql002ResponseModel> selectIfaNotificationViewStatusLookupSql002(IfaNotificationViewStatusLookupSql002RequestModel req)
            throws Exception {
        
        DataList<IfaNotificationViewStatusLookupSql002ResponseModel> res = new DataList<IfaNotificationViewStatusLookupSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaNotificationViewStatusLookupSql002(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql003
     * SQL名：お知らせ閲覧状況の情報取得（個別担当者）
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql003RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql003ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql003ResponseModel> selectIfaNotificationViewStatusLookupSql003(IfaNotificationViewStatusLookupSql003RequestModel req)
            throws Exception {
        
        DataList<IfaNotificationViewStatusLookupSql003ResponseModel> res = new DataList<IfaNotificationViewStatusLookupSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaNotificationViewStatusLookupSql003(req));
        return res;
    }
    
	
    /**
     * SQLID：Sql004
     * SQL名：ログイン者に紐づく既読情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaNotificationViewStatusLookupSql004RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql004ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaNotificationViewStatusLookupSql004ResponseModel> selectIfaNotificationViewStatusLookupSql004(IfaNotificationViewStatusLookupSql004RequestModel req)
            throws Exception {
        
        DataList<IfaNotificationViewStatusLookupSql004ResponseModel> res = new DataList<IfaNotificationViewStatusLookupSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaNotificationViewStatusLookupSql004(req));
        return res;
    }
    
    
	
    /**
     * SQLID：Sql005
     * SQL名：既読情報の追加
     * SQLタイプ：insert
     * リクエストクラス：IfaNotificationViewStatusLookupSql005RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int insertIfaNotificationViewStatusLookupSql005(IfaNotificationViewStatusLookupSql005RequestModel req)
            throws Exception {

        return mapper.insertIfaNotificationViewStatusLookupSql005(req);
    }
    
    
    
    /**
     * SQLID：Sql006
     * SQL名：既読情報の更新
     * SQLタイプ：update
     * リクエストクラス：IfaNotificationViewStatusLookupSql006RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql006ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int updateIfaNotificationViewStatusLookupSql006(IfaNotificationViewStatusLookupSql006RequestModel req)
            throws Exception {

        return mapper.updateIfaNotificationViewStatusLookupSql006(req);
    }
    
    
	
    /**
     * SQLID：Sql007
     * SQL名：既読情報の削除
     * SQLタイプ：delete
     * リクエストクラス：IfaNotificationViewStatusLookupSql007RequestModel
     * レスポンスクラス：IfaNotificationViewStatusLookupSql007ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaNotificationViewStatusLookupSql007(IfaNotificationViewStatusLookupSql007RequestModel req)
            throws Exception {

        return mapper.deleteIfaNotificationViewStatusLookupSql007(req);
    }
    
}
