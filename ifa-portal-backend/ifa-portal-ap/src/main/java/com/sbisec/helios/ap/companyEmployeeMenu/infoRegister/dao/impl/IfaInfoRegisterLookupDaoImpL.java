package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoRegisterLookupDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper.IfaInfoRegisterLookupMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterLookupSql006ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0501_01-01
 * 画面名：情報登録照会
 *
 * @author SCSK今井
 2024/05/23 新規作成
 */
@Component
public class IfaInfoRegisterLookupDaoImpL extends RowSelectableDao implements IfaInfoRegisterLookupDao {
    
    @Autowired
    private IfaInfoRegisterLookupMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：資料種別一覧
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql001RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupSql001ResponseModel> selectIfaInfoRegisterLookupSql001(
            IfaInfoRegisterLookupSql001RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterLookupSql001ResponseModel> res = new DataList<IfaInfoRegisterLookupSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterLookupSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：情報明細取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql002RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupSql002ResponseModel> selectIfaInfoRegisterLookupSql002(
            IfaInfoRegisterLookupSql002RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterLookupSql002ResponseModel> res = new DataList<IfaInfoRegisterLookupSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterLookupSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：お知らせテーブル情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaInfoRegisterLookupSql003RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql003(IfaInfoRegisterLookupSql003RequestModel req) throws Exception {
        
        return mapper.deleteIfaInfoRegisterLookupSql003(req);
    }
    
    /**
     * SQLID：Sql004
     * SQL名：お知らせ既読テーブル情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaInfoRegisterLookupSql004RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql004(IfaInfoRegisterLookupSql004RequestModel req) throws Exception {
        
        return mapper.deleteIfaInfoRegisterLookupSql004(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：お知らせ参照権限テーブル情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaInfoRegisterLookupSql005RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public int deleteIfaInfoRegisterLookupSql005(IfaInfoRegisterLookupSql005RequestModel req) throws Exception {
        
        return mapper.deleteIfaInfoRegisterLookupSql005(req);
    }
    
    /**
     * SQLID：Sql006
     * SQL名：添付ファイルディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterLookupSql006RequestModel
     * レスポンスクラス：IfaInfoRegisterLookupSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterLookupSql006ResponseModel> selectIfaInfoRegisterLookupSql006(
            IfaInfoRegisterLookupSql006RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterLookupSql006ResponseModel> res = new DataList<IfaInfoRegisterLookupSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterLookupSql006(req));
        return res;
    }
    
}
