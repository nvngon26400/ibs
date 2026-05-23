package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql001ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql002ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql003ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql004ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql005ResponseModel;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaOrderListSql008ResponseModel;





/**
 * 画面ID：SUB020302_0101-01
 * 画面名：注文一覧
 *
 * @author BASE李
 *
 2024/03/30 新規作成
 */
@Mapper
public interface IfaOrderListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：国内株式一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql001RequestModel
     * レスポンスクラス：IfaOrderListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql001ResponseModel> selectIfaOrderListSql001(
            @Param("req") IfaOrderListSql001RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：国内投資信託一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql002RequestModel
     * レスポンスクラス：IfaOrderListSql002ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql002ResponseModel> selectIfaOrderListSql002(
            @Param("req") IfaOrderListSql002RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：募集注文一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql003RequestModel
     * レスポンスクラス：IfaOrderListSql003ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql003ResponseModel> selectIfaOrderListSql003(
            @Param("req") IfaOrderListSql003RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：外国株式（委託注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql004RequestModel
     * レスポンスクラス：IfaOrderListSql004ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql004ResponseModel> selectIfaOrderListSql004(
            @Param("req") IfaOrderListSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：外国株式（店頭注文）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql005RequestModel
     * レスポンスクラス：IfaOrderListSql005ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql005ResponseModel> selectIfaOrderListSql005(
            @Param("req") IfaOrderListSql005RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：注文一覧コメント取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql007RequestModel
     * レスポンスクラス：IfaOrderListSql007ResponseModel
     *
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql007ResponseModel> selectIfaOrderListSql007() throws Exception;
    
    /**
     * SQLID：Sql008
     * SQL名：国内投資信託（定期積立）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderListSql008RequestModel
     * レスポンスクラス：IfaOrderListSql008ResponseModel
     *
     * @param req リクエスト
     * @return res
     * @exception e システムエラー
     */
    public List<IfaOrderListSql008ResponseModel> selectIfaOrderListSql008(
            @Param("req") IfaOrderListSql008RequestModel req
        ) throws Exception;
    
    
}
