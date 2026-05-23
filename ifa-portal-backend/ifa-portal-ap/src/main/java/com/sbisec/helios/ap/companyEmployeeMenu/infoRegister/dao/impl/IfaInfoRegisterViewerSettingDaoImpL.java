package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoRegisterViewerSettingDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.mapper.IfaInfoRegisterViewerSettingMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006ResponseModel;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@Component
public class IfaInfoRegisterViewerSettingDaoImpL extends RowSelectableDao implements IfaInfoRegisterViewerSettingDao {
    
    @Autowired
    private IfaInfoRegisterViewerSettingMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：全担当者設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql002RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql002ResponseModel> selectIfaInfoRegisterViewerSettingSql002(
            IfaInfoRegisterViewerSettingSql002RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingSql002ResponseModel> res = new DataList<IfaInfoRegisterViewerSettingSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterViewerSettingSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：権限設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql003RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql003ResponseModel> selectIfaInfoRegisterViewerSettingSql003(
            IfaInfoRegisterViewerSettingSql003RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingSql003ResponseModel> res = new DataList<IfaInfoRegisterViewerSettingSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterViewerSettingSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：個別担当者設定閲覧者情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql004RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql004ResponseModel> selectIfaInfoRegisterViewerSettingSql004(
            IfaInfoRegisterViewerSettingSql004RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingSql004ResponseModel> res = new DataList<IfaInfoRegisterViewerSettingSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterViewerSettingSql004(req));
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：仲介業者リスト検索
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql005RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql005ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql005ResponseModel> selectIfaInfoRegisterViewerSettingSql005(
            IfaInfoRegisterViewerSettingSql005RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingSql005ResponseModel> res = new DataList<IfaInfoRegisterViewerSettingSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterViewerSettingSql005(req));
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：担当者リスト検索
     * SQLタイプ：select
     * リクエストクラス：IfaInfoRegisterViewerSettingSql006RequestModel
     * レスポンスクラス：IfaInfoRegisterViewerSettingSql006ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingSql006ResponseModel> selectIfaInfoRegisterViewerSettingSql006(
            IfaInfoRegisterViewerSettingSql006RequestModel req) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingSql006ResponseModel> res = new DataList<IfaInfoRegisterViewerSettingSql006ResponseModel>();
        
        res.setDataList(mapper.selectIfaInfoRegisterViewerSettingSql006(req));
        return res;
    }
    
}
