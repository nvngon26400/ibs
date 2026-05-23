package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql011ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql013RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyInputSql013ResponseModel;





/**
 * 画面ID：SUB0204_01-02_1
 * 画面名：BB申込入力
 *
 * @author BASE李
 * 
   2024/02/08 新規作成
 *
 */
public interface IfaBbApplyInputDao {
    
    /**
     * SQLID：Sql001
     * SQL名：申込期間内銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql001RequestModel
     * レスポンスクラス：IfaBbApplyInputSql001ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputA001ResponseDto
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql001ResponseModel> selectIfaBbApplyInputSql001(IfaBbApplyInputSql001RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：投資家属性プルダウン取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql002RequestModel
     * レスポンスクラス：IfaBbApplyInputSql002ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql002ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql002ResponseModel> selectIfaBbApplyInputSql002(IfaBbApplyInputSql002RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql003RequestModel
     * レスポンスクラス：IfaBbApplyInputSql003ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql003ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql003ResponseModel> selectIfaBbApplyInputSql003(IfaBbApplyInputSql003RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：年間裁量配分割当回数情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql004RequestModel
     * レスポンスクラス：IfaBbApplyInputSql004ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql004ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql004ResponseModel> selectIfaBbApplyInputSql004(IfaBbApplyInputSql004RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：裁量配分割当回数(未抽選)情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql005RequestModel
     * レスポンスクラス：IfaBbApplyInputSql005ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql005ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql005ResponseModel> selectIfaBbApplyInputSql005(IfaBbApplyInputSql005RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：移管前の裁量配分割当回数(未抽選)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql006RequestModel
     * レスポンスクラス：IfaBbApplyInputSql006ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql006ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql006ResponseModel> selectIfaBbApplyInputSql006(IfaBbApplyInputSql006RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：預り資産額取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql007RequestModel
     * レスポンスクラス：IfaBbApplyInputSql007ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql007ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql007ResponseModel> selectIfaBbApplyInputSql007(IfaBbApplyInputSql007RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：目論見書閲覧状況情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql008RequestModel
     * レスポンスクラス：IfaBbApplyInputSql008ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql008ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql008ResponseModel> selectIfaBbApplyInputSql008(IfaBbApplyInputSql008RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql009
     * SQL名：銘柄コード存在件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql009RequestModel
     * レスポンスクラス：IfaBbApplyInputSql009ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql009ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql009ResponseModel> selectIfaBbApplyInputSql009(IfaBbApplyInputSql009RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql010
     * SQL名：入力データ件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql010RequestModel
     * レスポンスクラス：IfaBbApplyInputSql010ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql010ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql010ResponseModel> selectIfaBbApplyInputSql010(IfaBbApplyInputSql010RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql011
     * SQL名：銘柄プルダウン取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql011RequestModel
     * レスポンスクラス：IfaBbApplyInputSql011ResponseModel
     *
     * @return IfaBbApplyInputSql011ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql011ResponseModel> selectIfaBbApplyInputSql011()
            throws Exception;
    
    /**
     * SQLID：Sql012
     * SQL名：最良配分あるかフラグ取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql012RequestModel
     * レスポンスクラス：IfaBbApplyInputSql012ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql012ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql012ResponseModel> selectIfaBbApplyInputSql012(IfaBbApplyInputSql012RequestModel req)
            throws Exception;
    
    /**
     * SQLID：Sql013
     * SQL名：上限値(売買単位*上限単元数量)取得
     * SQLタイプ：select
     * リクエストクラス：IfaBbApplyInputSql013RequestModel
     * レスポンスクラス：IfaBbApplyInputSql013ResponseModel
     *
     * @param req リクエストモデル
     * @return IfaBbApplyInputSql013ResponseModel
     * @exception Exception 例外
     */
    public DataList<IfaBbApplyInputSql013ResponseModel> selectIfaBbApplyInputSql013(IfaBbApplyInputSql013RequestModel req)
            throws Exception;
    
    
    
    
}
