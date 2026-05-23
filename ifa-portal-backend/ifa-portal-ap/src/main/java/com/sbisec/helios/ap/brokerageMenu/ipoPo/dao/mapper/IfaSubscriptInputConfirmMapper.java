package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_1RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql012_2RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptInputConfirmSql013RequestModel;




/**
 * 
 * 画面ID：SUB0204_02-04_2
 * 画面名：募集入力確認
 * @author SCSK濱田
 *
 * 2024/04/15 新規作成
 */
@Mapper
public interface IfaSubscriptInputConfirmMapper {
    
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
    public int insertIfaSubscriptInputConfirmSql002_1(
            @Param("req") IfaSubscriptInputConfirmSql002_1RequestModel req
        ) throws Exception;
        

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
    public int insertIfaSubscriptInputConfirmSql002_2(
        @Param("req") IfaSubscriptInputConfirmSql002_2RequestModel req
    ) throws Exception;
    
    
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
    public int updateIfaSubscriptInputConfirmSql003_1(
        @Param("req")  IfaSubscriptInputConfirmSql003_1RequestModel req
        ) throws Exception;
    
    
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
    public int updateIfaSubscriptInputConfirmSql003_2(
        @Param("req")  IfaSubscriptInputConfirmSql003_2RequestModel req
        ) throws Exception;
    

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
    public int updateIfaSubscriptInputConfirmSql004(
        @Param("req")  IfaSubscriptInputConfirmSql004RequestModel req
        ) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：利用時間チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputConfirmSql005RequestModel
     *
     * @param なし
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptInputConfirmSql005ResponseModel> selectIfaSubscriptInputConfirmSql005(
    ) throws Exception;

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
    public int updateIfaSubscriptInputConfirmSql006_1(
        @Param("req")  IfaSubscriptInputConfirmSql006_1RequestModel req
        ) throws Exception;

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
    public int insertIfaSubscriptInputConfirmSql006_2(
        @Param("req")  IfaSubscriptInputConfirmSql006_2RequestModel req
        ) throws Exception;
        
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
    public List<IfaSubscriptInputConfirmSql007ResponseModel> selectIfaSubscriptInputConfirmSql007(
        @Param("req")  IfaSubscriptInputConfirmSql007RequestModel req
        ) throws Exception;
    
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
    public List<IfaSubscriptInputConfirmSql008ResponseModel> selectIfaSubscriptInputConfirmSql008(
        @Param("req") IfaSubscriptInputConfirmSql008RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：セクション関連情報の取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptInputConfirmSql009RequestModel
     * レスポンスクラス：IfaSubscriptInputConfirmSql009ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptInputConfirmSql009ResponseModel> selectIfaSubscriptInputConfirmSql009(
        @Param("req") IfaSubscriptInputConfirmSql009RequestModel req
        ) throws Exception;
    
    
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
    public int insertIfaSubscriptInputConfirmSql010_1(
        @Param("req")  IfaSubscriptInputConfirmSql010_1RequestModel req
        ) throws Exception;

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
    public int insertIfaSubscriptInputConfirmSql010_2(
        @Param("req")  IfaSubscriptInputConfirmSql010_2RequestModel req
        ) throws Exception;

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
    public int updateIfaSubscriptInputConfirmSql012_1(
        @Param("req")  IfaSubscriptInputConfirmSql012_1RequestModel req
        ) throws Exception;

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
    public int updateIfaSubscriptInputConfirmSql012_2(
        @Param("req")  IfaSubscriptInputConfirmSql012_2RequestModel req
        ) throws Exception;
    
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
    public int updateIfaSubscriptInputConfirmSql013(
        @Param("req")  IfaSubscriptInputConfirmSql013RequestModel req
        ) throws Exception;
    
    
}
