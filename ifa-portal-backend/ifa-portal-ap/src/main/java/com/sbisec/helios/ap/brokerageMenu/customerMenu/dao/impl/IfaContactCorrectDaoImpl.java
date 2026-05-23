package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaContactCorrectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaContactCorrectMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactCorrectSql011RequestModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaContactCorrectDaoImpl extends RowSelectableDao 
    implements IfaContactCorrectDao {

    @Autowired
    private IfaContactCorrectMapper g_mapper;

    /**
     * SQLID：Sql003
     * SQL名：問合せ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectSql003ResponseModel
     * レスポンスクラス：IfaContactCorrectSql003RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactCorrectSql003ResponseModel> selectIfaContactCorrectSql003(
            IfaContactCorrectSql003RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactCorrectSql003ResponseModel> p_res = new DataList<IfaContactCorrectSql003ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactCorrectSql003(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql004
     * SQL名:IFA問合せテーブルへの更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactCorrectSql004RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql004(IfaContactCorrectSql004RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactCorrectSql004(x_req);
    }

    /**
     * SQLID：Sql005
     * SQL名：IFA回答テーブルへの取得
     * SQLタイプ：select
     * リクエストクラス：IfaContactCorrectSql005ResponseModel
     * レスポンスクラス：IfaContactCorrectSql005RequestModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public DataList<IfaContactCorrectSql005ResponseModel> selectIfaContactCorrectSql005(
            IfaContactCorrectSql005RequestModel x_req) throws Exception {
        // 結果の格納先を準備
        DataList<IfaContactCorrectSql005ResponseModel> p_res = new DataList<IfaContactCorrectSql005ResponseModel>();
        // SQLを実行し結果を格納    
        p_res.setDataList(g_mapper.selectIfaContactCorrectSql005(x_req));
        return p_res;
    }

    /**
     * SQLID:Sql006
     * SQL名:IFA回答テーブルへの更新
     * SQLタイプ:update
     * リクエストクラス：IfaContactCorrectSql006RequestModel
     * 
     * @param x_req リクエスト
     * @return int 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql006(IfaContactCorrectSql006RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactCorrectSql006(x_req);
    }

    /**
     * SQLID:Sql008
     * SQL名:IFA問合せ・回答エラーテーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql008RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql008(IfaContactCorrectSql008RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactCorrectSql008(x_req);
    }

    /**
     * SQLID:Sql009
     * SQL名:回答情報登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql009RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql009(IfaContactCorrectSql009RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactCorrectSql009(x_req);
    }

    /**
     * SQLID:Sql010
     * SQL名:回答NO更新
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql010RequestModel
     *
     * @param x_req リクエスト
     * @return int 更新件数件数
     * @exception Exception システムエラー
     */
    public int updateIfaContactCorrectSql010(IfaContactCorrectSql010RequestModel x_req) throws Exception {
        return g_mapper.updateIfaContactCorrectSql010(x_req);
    }

    /**
     * SQLID:Sql011
     * SQL名:IFA接触履歴修正履歴テーブル登録
     * SQLタイプ:insert
     * リクエストクラス：IfaContactCorrectSql011RequestModel
     *
     * @param x_req リクエスト
     * @return int 新規登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaContactCorrectSql011(IfaContactCorrectSql011RequestModel x_req) throws Exception {
        return g_mapper.insertIfaContactCorrectSql011(x_req);
    }
}
