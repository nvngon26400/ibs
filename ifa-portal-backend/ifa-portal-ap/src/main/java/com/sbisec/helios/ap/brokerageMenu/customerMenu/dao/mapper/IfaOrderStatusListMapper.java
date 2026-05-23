package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaOrderStatusListSql004ResponseModel;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Mapper
public interface IfaOrderStatusListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：募集注文一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql001RequestModel
     * レスポンスクラス：IfaOrderStatusListSql001ResponseModel
     *
     * @param req IfaOrderStatusListSql001RequestModel
     * @return IfaOrderStatusListSql001ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaOrderStatusListSql001ResponseModel> selectIfaOrderStatusListSql001(
            @Param("req") IfaOrderStatusListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：外株店頭注文取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql002RequestModel
     * レスポンスクラス：IfaOrderStatusListSql002ResponseModel
     *
     * @param req IfaOrderStatusListSql002RequestModel
     * @return IfaOrderStatusListSql002ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaOrderStatusListSql002ResponseModel> selectIfaOrderStatusListSql002(
            @Param("req") IfaOrderStatusListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：外株店頭注文存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql003RequestModel
     * レスポンスクラス：IfaOrderStatusListSql003ResponseModel
     *
     * @param req IfaOrderStatusListSql003RequestModel
     * @return IfaOrderStatusListSql003ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaOrderStatusListSql003ResponseModel> selectIfaOrderStatusListSql003(
            @Param("req") IfaOrderStatusListSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：CCS認証ID取得
     * SQLタイプ：select
     * リクエストクラス：IfaOrderStatusListSql004RequestModel
     * レスポンスクラス：IfaOrderStatusListSql004ResponseModel
     *
     * @param req IfaOrderStatusListSql004RequestModel
     * @return IfaOrderStatusListSql004ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaOrderStatusListSql004ResponseModel> selectIfaOrderStatusListSql004(
            @Param("req") IfaOrderStatusListSql004RequestModel req) throws Exception;
    
}
