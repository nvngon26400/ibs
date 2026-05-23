package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaSelfInspectBlotterRegisterConfirmDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.mapper.IfaSelfInspectBlotterRegisterConfirmMapper;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0401_02-02
 * 画面名：自己点検記録簿登録確認
 *
 * @author SCSK丹波
 2024/06/04 新規作成
 */
@Component
public class IfaSelfInspectBlotterRegisterConfirmDaoImpL extends RowSelectableDao
        implements IfaSelfInspectBlotterRegisterConfirmDao {
    
    @Autowired
    private IfaSelfInspectBlotterRegisterConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：シーケンス取得
     * SQLタイプ：select
     * リクエストクラス：なし
     * レスポンスクラス：IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql001()
            throws Exception {
        
        DataList<IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel> res = new DataList<IfaSelfInspectBlotterRegisterConfirmSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSelfInspectBlotterRegisterConfirmSql001());
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：自己点検テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSelfInspectBlotterRegisterConfirmSql002RequestModel
     * レスポンスクラス：なし
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public int insertIfaSelfInspectBlotterRegisterConfirmSql002(
            IfaSelfInspectBlotterRegisterConfirmSql002RequestModel req) throws Exception {
        
        return mapper.insertIfaSelfInspectBlotterRegisterConfirmSql002(req);
    }
    
    /**
     * SQLID：Sql003
     * SQL名：自己点検確認テーブル登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSelfInspectBlotterRegisterConfirmSql003RequestModel
     * レスポンスクラス：なし
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public int insertIfaSelfInspectBlotterRegisterConfirmSql003(
            IfaSelfInspectBlotterRegisterConfirmSql003RequestModel req) throws Exception {
        
        return mapper.insertIfaSelfInspectBlotterRegisterConfirmSql003(req);
    }
    
    /**
     * SQLID：Sql004
     * SQL名：自己点検確認テーブル更新
     * SQLタイプ：update
     * リクエストクラス：IfaSelfInspectBlotterRegisterConfirmSql004RequestModel
     * レスポンスクラス：なし
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public int updateIfaSelfInspectBlotterRegisterConfirmSql004(
            IfaSelfInspectBlotterRegisterConfirmSql004RequestModel req) throws Exception {
        
        return mapper.updateIfaSelfInspectBlotterRegisterConfirmSql004(req);
    }
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業システム値情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterRegisterConfirmSql005RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql005(
            IfaSelfInspectBlotterRegisterConfirmSql005RequestModel req) throws Exception {
        
        DataList<IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel> res = new DataList<IfaSelfInspectBlotterRegisterConfirmSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaSelfInspectBlotterRegisterConfirmSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：自己点検記録簿今月未読件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterRegisterConfirmSql006RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel> selectIfaSelfInspectBlotterRegisterConfirmSql006(
            IfaSelfInspectBlotterRegisterConfirmSql006RequestModel req) throws Exception {
        
        DataList<IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel> res = new DataList<IfaSelfInspectBlotterRegisterConfirmSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaSelfInspectBlotterRegisterConfirmSql006(req));
        return res;
    }
    
}
