package com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.IfaJointSubscriptCustomerManageDao;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.mapper.IfaJointSubscriptCustomerManageMapper;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.jointSubscript.dao.model.IfaJointSubscriptCustomerManageSql012RequestModel;

/**
 * DB処理インターフェースの実装クラス
 * 画面ID：SUB0206_01
 * 画面名：共同募集 顧客管理
 * 2024/12/04 新規作成
 *
 * @author 大連 王永宝
 */
@Component
public class IfaJointSubscriptCustomerManageDaoImpL extends RowSelectableDao 
    implements IfaJointSubscriptCustomerManageDao {

    @Autowired
    private IfaJointSubscriptCustomerManageMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：共同募集 顧客管理一覧表示情報を取得する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql001RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql001ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> selectIfaJointSubscriptCustomerManageSql001(
            IfaJointSubscriptCustomerManageSql001RequestModel req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaJointSubscriptCustomerManageSql001ResponseModel> res = new DataList<IfaJointSubscriptCustomerManageSql001ResponseModel>();
        // SQLを実行し結果を格納    
        res.setDataList(mapper.selectIfaJointSubscriptCustomerManageSql001(req));

        return res;
    }

    /**
     * SQLID：Sql002
     * SQL名：顧客情報 詳細を取得する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql002RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql002ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaJointSubscriptCustomerManageSql002ResponseModel> selectIfaJointSubscriptCustomerManageSql002(
            IfaJointSubscriptCustomerManageSql002RequestModel req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaJointSubscriptCustomerManageSql002ResponseModel> res = new DataList<IfaJointSubscriptCustomerManageSql002ResponseModel>();
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaJointSubscriptCustomerManageSql002(req));

        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：共同募集顧客情報を取得する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql003RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql003ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaJointSubscriptCustomerManageSql003ResponseModel> selectIfaJointSubscriptCustomerManageSql003(
            IfaJointSubscriptCustomerManageSql003RequestModel req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaJointSubscriptCustomerManageSql003ResponseModel> res = new DataList<IfaJointSubscriptCustomerManageSql003ResponseModel>();
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaJointSubscriptCustomerManageSql003(req));

        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：指定共同募集顧客情報の編集履歴を更新する。
     * SQLタイプ：update
     * リクエストクラス：IfaJointSubscriptCustomerManageSql004RequestModel
     * 
     * @param req リクエスト
     * @return int 更新件数
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointSubscriptCustomerManageSql004(IfaJointSubscriptCustomerManageSql004RequestModel req)
            throws Exception {

        return mapper.updateIfaJointSubscriptCustomerManageSql004(req);
    }

    /**
     * SQLID：Sql005
     * SQL名：新しい顧客情報を共同募集顧客マスタに新規登録する。
     * SQLタイプ：insert
     * リクエストクラス：IfaJointSubscriptCustomerManageSql005RequestModel
     *
     * @param req リクエスト
     * @return int 新規登録件数
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaJointSubscriptCustomerManageSql005(IfaJointSubscriptCustomerManageSql005RequestModel req)
            throws Exception {
        
        return mapper.insertIfaJointSubscriptCustomerManageSql005(req);
    }

    /**
     * SQLID：Sql006
     * SQL名：仲介業者コードと名を取得する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql006RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql006ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaJointSubscriptCustomerManageSql006ResponseModel> selectIfaJointSubscriptCustomerManageSql006(
            IfaJointSubscriptCustomerManageSql006RequestModel req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaJointSubscriptCustomerManageSql006ResponseModel> res = new DataList<IfaJointSubscriptCustomerManageSql006ResponseModel>();
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaJointSubscriptCustomerManageSql006(req));

        return res;
    }

    /**
     * SQLID：Sql007
     * SQL名：仲介業者支店情報を取得する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql007RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql007ResponseModel
     *
     * @param req prepared statement
     * @return res レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaJointSubscriptCustomerManageSql007ResponseModel> selectIfaJointSubscriptCustomerManageSql007(
            IfaJointSubscriptCustomerManageSql007RequestModel req) throws Exception {

        // 結果の格納先を準備
        DataList<IfaJointSubscriptCustomerManageSql007ResponseModel> res = new DataList<IfaJointSubscriptCustomerManageSql007ResponseModel>();
        // SQLを実行し結果を格納
        res.setDataList(mapper.selectIfaJointSubscriptCustomerManageSql007(req));

        return res;
    }

    /**
     * SQLID：Sql008
     * SQL名：共通募集顧客テーブルに、指定の条件で口座が存在するか判定する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql008RequestModel
     * レスポンスクラス：IfaJointSubscriptCustomerManageSql008ResponseModel
     *
     * @param req prepared statement
     * @return int 件数
     * @exception Exception SQLExceptionなど
     */
    public Integer selectIfaJointSubscriptCustomerManageSql008(
            IfaJointSubscriptCustomerManageSql008RequestModel req) throws Exception {

        return mapper.selectIfaJointSubscriptCustomerManageSql008(req);
    }

    /**
     * SQLID：Sql010
     * SQL名：新しい共同募集顧客情報を共同募集顧客仲介業者情報に新規登録する。。
     * SQLタイプ：insert
     * リクエストクラス：IfaJointSubscriptCustomerManageSql010RequestModel
     *
     * @param req リクエスト
     * @return int 新規登録件数
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaJointSubscriptCustomerManageSql010(IfaJointSubscriptCustomerManageSql010RequestModel req)
            throws Exception{
        return mapper.insertIfaJointSubscriptCustomerManageSql010(req);
    }

    /**
     * SQLID：Sql011
     * SQL名：IFA顧客属性テーブルに、指定の部店、口座番号が存在するか確認する。。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql011RequestModel
     *
     * @param req prepared statement
     * @return int データ件数
     * @exception Exception SQLExceptionなど
     */
    public Integer selectIfaJointSubscriptCustomerManageSql011(
            IfaJointSubscriptCustomerManageSql011RequestModel req) throws Exception{
        return mapper.selectIfaJointSubscriptCustomerManageSql011(req);
    }

    /**
     * SQLID：Sql012
     * SQL名：IFA顧客属性テーブルに、指定の部店、口座番号が契約先仲介業者に紐づいているか確認する。
     * SQLタイプ：select
     * リクエストクラス：IfaJointSubscriptCustomerManageSql012RequestModel
     *
     * @param req prepared statement
     * @return int データ件数
     * @exception Exception SQLExceptionなど
     */
    public Integer selectIfaJointSubscriptCustomerManageSql012(
            IfaJointSubscriptCustomerManageSql012RequestModel req) throws Exception{
        return mapper.selectIfaJointSubscriptCustomerManageSql012(req);
    }
}
