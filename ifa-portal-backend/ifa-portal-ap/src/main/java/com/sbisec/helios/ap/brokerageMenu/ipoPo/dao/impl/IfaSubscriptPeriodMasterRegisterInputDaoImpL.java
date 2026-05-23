package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptPeriodMasterRegisterInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaSubscriptPeriodMasterRegisterInputMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel;



/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Component
public class IfaSubscriptPeriodMasterRegisterInputDaoImpL extends RowSelectableDao implements IfaSubscriptPeriodMasterRegisterInputDao {
    
    @Autowired
    private IfaSubscriptPeriodMasterRegisterInputMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql001RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql001(
            IfaSubscriptPeriodMasterRegisterInputSql001RequestModel req
    ) throws Exception {
        
        DataList<IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel> res = new DataList<IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptPeriodMasterRegisterInputSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：募集期間情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql002RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql002(
            IfaSubscriptPeriodMasterRegisterInputSql002RequestModel req
    ) throws Exception {
        
        DataList<IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel> res = new DataList<IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptPeriodMasterRegisterInputSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：対面募集注文確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql003RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql003(
            IfaSubscriptPeriodMasterRegisterInputSql003RequestModel req
    ) throws Exception {
        
        DataList<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel> res = new DataList<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptPeriodMasterRegisterInputSql003(req));
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：募集期間情報登録有無確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql004RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql004(
            IfaSubscriptPeriodMasterRegisterInputSql004RequestModel req
    ) throws Exception {
        
        DataList<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel> res = new DataList<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptPeriodMasterRegisterInputSql004(req));
        return res;
    }
    

    /**
     * SQLID：Sql005
     * SQL名：対面募集期間マスタ登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql005RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql005ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptPeriodMasterRegisterInputSql005(
            IfaSubscriptPeriodMasterRegisterInputSql005RequestModel req
    ) throws Exception {

        return mapper.insertIfaSubscriptPeriodMasterRegisterInputSql005(req);
    }
    
    
    /**
     * SQLID：Sql006
     * SQL名：対面募集期間マスタ更新
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql006RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql006ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptPeriodMasterRegisterInputSql006(
            IfaSubscriptPeriodMasterRegisterInputSql006RequestModel req
    ) throws Exception {

        return mapper.updateIfaSubscriptPeriodMasterRegisterInputSql006(req);
    }

    /**
     * SQLID：Sql007
     * SQL名：営業日チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql007RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql007(
            IfaSubscriptPeriodMasterRegisterInputSql007RequestModel req
    ) throws Exception {
        
        DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> res = new DataList<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptPeriodMasterRegisterInputSql007(req));
        return res;
    }
    
}
