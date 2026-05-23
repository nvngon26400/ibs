package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptInputDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaSubscriptInputMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputSql008ResponseModel;

/**
 * 画面ID：SUB0204_02-04_1
 * 画面名：募集入力
 * 2024/02/02 新規作成
 *
 * @author SCSK 江口
 */
@Component
public class IfaSubscriptInputDaoImpL extends RowSelectableDao implements IfaSubscriptInputDao {
    
    @Autowired
    private IfaSubscriptInputMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：画面情報の取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql001RequestModel
     * レスポンスクラス：IfaSubscriptInputSql001ResponseModel
     *
     * @param req prepared statement
     * @return 画面情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql001ResponseModel> selectIfaSubscriptInputSql001(
            IfaSubscriptInputSql001RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql001ResponseModel> res = new DataList<IfaSubscriptInputSql001ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql001(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：年間裁量配分割当回数情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql002RequestModel
     * レスポンスクラス：IfaSubscriptInputSql002ResponseModel
     *
     * @param req prepared statement
     * @return 年間裁量配分割当回数情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql002ResponseModel> selectIfaSubscriptInputSql002(
            IfaSubscriptInputSql002RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql002ResponseModel> res = new DataList<IfaSubscriptInputSql002ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql002(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql003RequestModel
     * レスポンスクラス：IfaSubscriptInputSql003ResponseModel
     *
     * @param req prepared statement
     * @return 裁量配分割当回数(未抽選)
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql003ResponseModel> selectIfaSubscriptInputSql003(
            IfaSubscriptInputSql003RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql003ResponseModel> res = new DataList<IfaSubscriptInputSql003ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql003(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql004
     * SQL名：対面募集注文情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql004RequestModel
     * レスポンスクラス：IfaSubscriptInputSql004ResponseModel
     *
     * @param req prepared statement
     * @return 対面募集注文情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql004ResponseModel> selectIfaSubscriptInputSql004(
            IfaSubscriptInputSql004RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql004ResponseModel> res = new DataList<IfaSubscriptInputSql004ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql004(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql005
     * SQL名：利用時間チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql005RequestModel
     * レスポンスクラス：IfaSubscriptInputSql005ResponseModel
     *
     * @return 利用時間チェック
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql005ResponseModel> selectIfaSubscriptInputSql005() throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql005ResponseModel> res = new DataList<IfaSubscriptInputSql005ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql005());
        
        return res;
    }
    
    /**
     * SQLID：Sql006
     * SQL名：口座種別チェック情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql006RequestModel
     * レスポンスクラス：IfaSubscriptInputSql006ResponseModel
     *
     * @param req prepared statement
     * @return 口座種別チェック情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql006ResponseModel> selectIfaSubscriptInputSql006(
            IfaSubscriptInputSql006RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql006ResponseModel> res = new DataList<IfaSubscriptInputSql006ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql006(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql007
     * SQL名：重要事項の説明確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql007RequestModel
     * レスポンスクラス：IfaSubscriptInputSql007ResponseModel
     *
     * @param req prepared statement
     * @return 重要事項の説明確認
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql007ResponseModel> selectIfaSubscriptInputSql007(
            IfaSubscriptInputSql007RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql007ResponseModel> res = new DataList<IfaSubscriptInputSql007ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql007(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql008
     * SQL名：セクション関連情報の取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputSql008RequestModel
     * レスポンスクラス：IfaSubscriptInputSql008ResponseModel
     *
     * @param req prepared statement
     * @return セクション関連情報
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaSubscriptInputSql008ResponseModel> selectIfaSubscriptInputSql008(
            IfaSubscriptInputSql008RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaSubscriptInputSql008ResponseModel> res = new DataList<IfaSubscriptInputSql008ResponseModel>();
        
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaSubscriptInputSql008(req));
        
        return res;
    }
    
}
