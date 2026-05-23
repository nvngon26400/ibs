package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactInputDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactInputMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactInputSql005ResponseModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaContactInputDaoImpl extends RowSelectableDao 
    implements IfaContactInputDao {

    @Autowired
    private IfaContactInputMapper g_mapper;

    /**
     * SQLID:Sql001
     * SQL名:問合せカテゴリリスト（大）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql001RequestModel
     * レスポンスクラス:IfaContactInputSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputSql001ResponseModel> selectIfaContactInputSql001(
            IfaContactInputSql001RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactInputSql001ResponseModel> p_res = new DataList<IfaContactInputSql001ResponseModel>();
        // SQLを実行し結果を格納
        p_res.setDataList(g_mapper.selectIfaContactInputSql001(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql002
     * SQL名:問合せ情報取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql002RequestModel
     * レスポンスクラス:IfaContactInputSql002ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputSql002ResponseModel> selectIfaContactInputSql002(
            IfaContactInputSql002RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactInputSql002ResponseModel> p_res = new DataList<IfaContactInputSql002ResponseModel>();
        // SQLを実行し結果を格納
        p_res.setDataList(g_mapper.selectIfaContactInputSql002(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql003
     * SQL名:問合せカテゴリリスト（中）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql003RequestModel
     * レスポンスクラス:IfaContactInputSql003ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputSql003ResponseModel> selectIfaContactInputSql003(
            IfaContactInputSql003RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactInputSql003ResponseModel> p_res = new DataList<IfaContactInputSql003ResponseModel>();
        // SQLを実行し結果を格納
        p_res.setDataList(g_mapper.selectIfaContactInputSql003(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql004
     * SQL名:問合せカテゴリリスト（小）取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql004RequestModel
     * レスポンスクラス:IfaContactInputSql004ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputSql004ResponseModel> selectIfaContactInputSql004(
            IfaContactInputSql004RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactInputSql004ResponseModel> p_res = new DataList<IfaContactInputSql004ResponseModel>();
        // SQLを実行し結果を格納
        p_res.setDataList(g_mapper.selectIfaContactInputSql004(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql005
     * SQL名:回答情報取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactInputSql005RequestModel
     * レスポンスクラス:IfaContactInputSql005ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactInputSql005ResponseModel> selectIfaContactInputSql005(
            IfaContactInputSql005RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactInputSql005ResponseModel> p_res = new DataList<IfaContactInputSql005ResponseModel>();
        // SQLを実行し結果を格納
        p_res.setDataList(g_mapper.selectIfaContactInputSql005(x_req));
        return p_res;
    }
}
