package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaSubscriptInputConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaSubscriptInputConfirmEtintraMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper.IfaSubscriptInputConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql002_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql002_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql003_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql003_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql006_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql006_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql010_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql010_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql013RequestModel;


/**
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * @author SCSK濱田
 *
 * 2024/04/15 新規作成
 */
@Component
public class IfaSubscriptInputConfirmDaoImpL extends RowSelectableDao implements IfaSubscriptInputConfirmDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSubscriptInputConfirmDaoImpL.class);
    @Autowired
    private IfaSubscriptInputConfirmMapper mapper;
    @Autowired
    private IfaSubscriptInputConfirmEtintraMapper etintraMapper;
    
    /**
     * SQLID：Sql002_1
     * SQL名：対面募集注文テーブルの登録（管理者権限）
     * insert
     * リクエストクラス：IfaSubscriptInputConfirmSql002_1RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptInputConfirmSql002_1(IfaSubscriptInputConfirmSql002_1RequestModel req)
            throws Exception {

        return mapper.insertIfaSubscriptInputConfirmSql002_1(req);
    }
        
    /**
     * SQLID：Sql002_2
     * SQL名：対面募集注文明細テーブルの登録（管理者権限）
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputConfirmSql002_2RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptInputConfirmSql002_2(IfaSubscriptInputConfirmSql002_2RequestModel req)
            throws Exception {
        
        return mapper.insertIfaSubscriptInputConfirmSql002_2(req);
    }
    
    /**
     * SQLID：Sql003_1
     * SQL名：対面募集注文テーブルの更新（管理者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql003_1RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql003_1(IfaSubscriptInputConfirmSql003_1RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql003_1(req);
    }

    /**
     * SQLID：Sql003_2
     * SQL名：対面募集注文明細テーブルの更新（管理者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql003_2RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql003_2(IfaSubscriptInputConfirmSql003_2RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql003_2(req);
    }

    /**
     * SQLID：Sql004
     * SQL名：対面募集注文明細テーブルの更新（管理者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql004RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql004(IfaSubscriptInputConfirmSql004RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql004(req);
    }

    /**
     * SQLID：Sql005
     * SQL名：利用時間チェック
     * SQLタイプ：select
     * レスポンスクラス：IfaSubscriptInputConfirmSql005ResponseModel
     *
     * @param なし
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmSql005ResponseModel> selectIfaSubscriptInputConfirmSql005()
            throws Exception {
        
        DataList<IfaSubscriptInputConfirmSql005ResponseModel> res = new DataList<IfaSubscriptInputConfirmSql005ResponseModel>();
        
        res.setDataList(mapper.selectIfaSubscriptInputConfirmSql005());
        return res;
    }
   
    /**
     * SQLID：Sql006_1
     * SQL名：対面募集注文明細テーブルの更新
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql006_1RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql006_1(IfaSubscriptInputConfirmSql006_1RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql006_1(req);
    }
       
    /**
     * SQLID：Sql006_2
     * SQL名：対面募集注文明細テーブルの登録
     * SQLタイプ：insert
     * リクエストクラス：IfaSubscriptInputConfirmSql006_2RequestModel
     * レスポンスクラス：int
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptInputConfirmSql006_2(IfaSubscriptInputConfirmSql006_2RequestModel req)
            throws Exception {

        return mapper.insertIfaSubscriptInputConfirmSql006_2(req);
    }

    /**
     * SQLID：Sql007
     * SQL名：口座種別チェック情報取得
     * select
     * リクエストクラス：IfaSubscriptInputConfirmSql007RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql007ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmSql007ResponseModel> selectIfaSubscriptInputConfirmSql007(IfaSubscriptInputConfirmSql007RequestModel req)
            throws Exception {

        DataList<IfaSubscriptInputConfirmSql007ResponseModel> res = new DataList<IfaSubscriptInputConfirmSql007ResponseModel>();
        res.setDataList(mapper.selectIfaSubscriptInputConfirmSql007(req));
        return res;
    }
        
    /**
     * SQLID：Sql008
     * SQL名：重要事項の説明確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputConfirmSql008RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql008ResponseModel
     * 
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public DataList<IfaSubscriptInputConfirmSql008ResponseModel> selectIfaSubscriptInputConfirmSql008(IfaSubscriptInputConfirmSql008RequestModel req)
            throws Exception {
        
        DataList<IfaSubscriptInputConfirmSql008ResponseModel> res = new DataList<IfaSubscriptInputConfirmSql008ResponseModel>();
        res.setDataList(mapper.selectIfaSubscriptInputConfirmSql008(req));
        return res;
    }

    /**
     * SQLID：Sql009
     * SQL名：セクション関連情報の取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputConfirmSql009RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql009ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaSubscriptInputConfirmSql009ResponseModel> selectIfaSubscriptInputConfirmSql009(IfaSubscriptInputConfirmSql009RequestModel req)
            throws Exception {
        
        DataList<IfaSubscriptInputConfirmSql009ResponseModel> res = new DataList<IfaSubscriptInputConfirmSql009ResponseModel>();
        res.setDataList(mapper.selectIfaSubscriptInputConfirmSql009(req));
        return res;
    }
    
    /**
     * SQLID：Sql010_1
     * SQL名：対面募集注文テーブルの登録（仲介業者権限）
     * SQLタイプ：insert
     * リクエストクラス：IfaSubscriptInputConfirmSql010_1RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql010_1ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptInputConfirmSql010_1(IfaSubscriptInputConfirmSql010_1RequestModel req)
            throws Exception {

        return mapper.insertIfaSubscriptInputConfirmSql010_1(req);
    }

    /**
     * SQLID：Sql010_2
     * SQL名：対面募集注文明細テーブルの登録（仲介業者権限）
     * SQLタイプ：insert
     * リクエストクラス：IfaSubscriptInputConfirmSql010_2RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql010_2ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptInputConfirmSql010_2(IfaSubscriptInputConfirmSql010_2RequestModel req)
            throws Exception {

        return mapper.insertIfaSubscriptInputConfirmSql010_2(req);
    }

    /**
     * SQLID：Sql011
     * SQL名：ブックビルディング受付テーブルの更新
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql011RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql011ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql011(IfaSubscriptInputConfirmSql011RequestModel req)
            throws Exception {

        return etintraMapper.updateIfaSubscriptInputConfirmSql011(req);
    }
    
    /**
     * SQLID：Sql012_1
     * SQL名：対面募集注文テーブルの更新（仲介業者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql012_1RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql012_1ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql012_1(IfaSubscriptInputConfirmSql012_1RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql012_1(req);
    }

    /**
     * SQLID：Sql012_2
     * SQL名：対面募集注文明細テーブルの更新（仲介業者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql012_2RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql012_2ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql012_2(IfaSubscriptInputConfirmSql012_2RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql012_2(req);
    }
    
    /**
     * SQLID：Sql013
     * SQL名：対面募集注文明細テーブルの更新（仲介業者権限）
     * SQLタイプ：update
     * リクエストクラス：IfaSubscriptInputConfirmSql013RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql013ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptInputConfirmSql013(IfaSubscriptInputConfirmSql013RequestModel req)
            throws Exception {

        return mapper.updateIfaSubscriptInputConfirmSql013(req);
    }
}
