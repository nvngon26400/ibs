package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactConfirmSql011RequestModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaContactConfirmDaoImpl extends RowSelectableDao 
    implements IfaContactConfirmDao {

    @Autowired
    private IfaContactConfirmMapper g_mapper;

    /**
     * SQLID:Sql001
     * SQL名:問合せカテゴリリスト取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql001ResponseModel
     * レスポンスクラス:IfaContactConfirmSql001RequestModel
     *
     * @param x_req prepared statement
     * @return p_res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactConfirmSql001ResponseModel> selectIfaContactConfirmSql001(
            IfaContactConfirmSql001RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactConfirmSql001ResponseModel> p_res = new DataList<IfaContactConfirmSql001ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactConfirmSql001(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql002
     * SQL名:問合せ情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql002RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql002(IfaContactConfirmSql002RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactConfirmSql002(x_req);
    }

    /**
     * SQLID:Sql003
     * SQL名:問合せ情報更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql003RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql003(IfaContactConfirmSql003RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactConfirmSql003(x_req);
    }

    /**
     * SQLID:Sql004
     * SQL名:回答情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql004RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql004(IfaContactConfirmSql004RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactConfirmSql004(x_req);
    }

    /**
     * SQLID:Sql005
     * SQL名:問合せNO更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql005RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql005(IfaContactConfirmSql005RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactConfirmSql005(x_req);
    }

    /**
     * SQLID:Sql006
     * SQL名:回答NO更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactConfirmSql006RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactConfirmSql006(IfaContactConfirmSql006RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactConfirmSql006(x_req);
    }

    /**
     * SQLID:Sql008
     * SQL名:CCSログイン用IDの取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql008ResponseModel
     * レスポンスクラス:IfaContactConfirmSql008RequestModel
     *
     * @param x_req prepared statement
     * @return p_res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactConfirmSql008ResponseModel> selectIfaContactConfirmSql008(
            IfaContactConfirmSql008RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactConfirmSql008ResponseModel> p_res = new DataList<IfaContactConfirmSql008ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactConfirmSql008(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql009
     * SQL名:IFA問合せ・回答エラーテーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql009RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql009(IfaContactConfirmSql009RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactConfirmSql009(x_req);
    }

    /**
     * SQLID:Sql010
     * SQL名:登録グループ取得
     * SQLタイプ:select
     * リクエストクラス:IfaContactConfirmSql010ResponseModel
     * レスポンスクラス:IfaContactConfirmSql010RequestModel
     *
     * @param x_req prepared statement
     * @return p_res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactConfirmSql010ResponseModel> selectIfaContactConfirmSql010(
            IfaContactConfirmSql010RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactConfirmSql010ResponseModel> p_res = new DataList<IfaContactConfirmSql010ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactConfirmSql010(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql011
     * SQL名:IFA接触履歴修正履歴テーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactConfirmSql011RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactConfirmSql011(IfaContactConfirmSql011RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactConfirmSql011(x_req);
    }
}
