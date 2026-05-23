package com.sbisec.helios.ap.brokerageMenu.customerList.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerList.dao.model.IfaCustomerListSql004ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0201_01-01
 * 画面名：顧客一覧・基本
 *
 * @author SCSK池田
 *
   2023/09/13 新規作成
 */
@Mapper
public interface IfaCustomerListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql001RequestModel
     * レスポンスクラス：IfaCustomerListSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return IfaCustomerListSql001ResponseModel
     * @exception Exception 例外
     */
    public List<IfaCustomerListSql001ResponseModel> selectIfaCustomerListSql001(
            @Param("req") IfaCustomerListSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql002RequestModel
     * レスポンスクラス：IfaCustomerListSql002ResponseModel
     *
     * @param req リクエストパラメータ
     * @return IfaCustomerListA002ResponseDto
     * @exception Exception 例外
     */
    public List<IfaCustomerListSql002ResponseModel> selectIfaCustomerListSql002(
            @Param("req") IfaCustomerListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql003RequestModel
     * レスポンスクラス：IfaCustomerListSql003ResponseModel
     *
     * @param req リクエストパラメータ
     * @return IfaCustomerListSql003ResponseModel
     * @exception Exception 例外
     */
    public List<IfaCustomerListSql003ResponseModel> selectIfaCustomerListSql003(
            @Param("req") IfaCustomerListSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerListSql004RequestModel
     * レスポンスクラス：IfaCustomerListSql004ResponseModel
     *
     * @return IfaCustomerListSql004ResponseModel
     * @exception Exception 例外
     */
    public List<IfaCustomerListSql004ResponseModel> selectIfaCustomerListSql004() throws Exception;
    
}
