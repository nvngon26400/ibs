package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaSendReceiveStatusLookupDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaSendReceiveStatusLookupMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaSendReceiveStatusLookupSql004ResponseModel;


/**
 * 画面ID：SUB0202_0703-01
 * 画面名：受発信状況照会
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@Component
public class IfaSendReceiveStatusLookupDaoImpL extends RowSelectableDao implements IfaSendReceiveStatusLookupDao{
    
    @Autowired
    private IfaSendReceiveStatusLookupMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：書類一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql001RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql001RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */

    @Override
    public DataList<IfaSendReceiveStatusLookupSql001ResponseModel> selectIfaSendReceiveStatusLookupSql001(
            IfaSendReceiveStatusLookupSql001RequestModel selSql001Req) throws Exception {
        DataList<IfaSendReceiveStatusLookupSql001ResponseModel> res = new DataList<IfaSendReceiveStatusLookupSql001ResponseModel>();

        res.setDataList(mapper.selectIfaSendReceiveStatusLookupSql001(selSql001Req));
        return res;
    }
    /**
     * SQLID：Sql002
     * SQL名：受発信状況一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql002RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql002RequestModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSendReceiveStatusLookupSql002ResponseModel> selectIfaSendReceiveStatusLookupSql002(
            IfaSendReceiveStatusLookupSql002RequestModel selSql002Req) throws Exception{
        DataList<IfaSendReceiveStatusLookupSql002ResponseModel> res = new DataList<IfaSendReceiveStatusLookupSql002ResponseModel>();

        res.setDataList(mapper.selectIfaSendReceiveStatusLookupSql002(selSql002Req));
        return res;
    }
    /**
     * SQLID：Sql003
     * SQL名：書類請求付加情報詳細取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql003RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql003ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSendReceiveStatusLookupSql003ResponseModel> selectIfaSendReceiveStatusLookupSql003(
            IfaSendReceiveStatusLookupSql003RequestModel selSql003Req) throws Exception {
        DataList<IfaSendReceiveStatusLookupSql003ResponseModel> res = new DataList<IfaSendReceiveStatusLookupSql003ResponseModel>();

        res.setDataList(mapper.selectIfaSendReceiveStatusLookupSql003(selSql003Req));
        return res;
    }
    /**
     * SQLID：Sql004
     * SQL名：書類不備付加情報リス取得
     * SQLタイプ：select
     * リクエストクラス：IfaSendReceiveStatusLookupSql004RequestModel
     * レスポンスクラス：IfaSendReceiveStatusLookupSql004ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSendReceiveStatusLookupSql004ResponseModel> selectIfaSendReceiveStatusLookupSql004(
            IfaSendReceiveStatusLookupSql004RequestModel selSql004Req) throws Exception {
        DataList<IfaSendReceiveStatusLookupSql004ResponseModel> res = new DataList<IfaSendReceiveStatusLookupSql004ResponseModel>();

        res.setDataList(mapper.selectIfaSendReceiveStatusLookupSql004(selSql004Req));
        return res;
    }

}
