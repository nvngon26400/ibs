package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql005ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql007ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql008ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql009ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql010ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaHoldingSecurityListSql011ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0202_010201-01
 * 画面名：保有商品一覧
 *
 * @author SCSK
 *     2023/10/17 新規作成
 */
@Mapper
public interface IfaHoldingSecurityListMapper {
    
    /**
     * SQLID：Sql002
     * SQL名：基準価額単位取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql002RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql002ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 基準価額単位取得時に例外が発生した場合
     */
    public List<IfaHoldingSecurityListSql002ResponseModel> selectIfaHoldingSecurityListSql002(
            @Param("req") IfaHoldingSecurityListSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：優先市場取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql005RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql005ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public List<IfaHoldingSecurityListSql005ResponseModel> selectIfaHoldingSecurityListSql005(
            @Param("req") IfaHoldingSecurityListSql005RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：ISA買付可能判定区分取得
     * SQLタイプ：select
     * リクエストクラス：IfaHoldingSecurityListSql006RequestModel
     * レスポンスクラス：IfaHoldingSecurityListSql006ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 優先市場取得時に例外が発生した場合
     */
    public IfaHoldingSecurityListSql006ResponseModel selectIfaHoldingSecurityListSql006(
            @Param("req") IfaHoldingSecurityListSql006RequestModel req) throws Exception;
    
    
    public IfaHoldingSecurityListSql007ResponseModel selectIfaHoldingSecurityListSql007(
            @Param("req") IfaHoldingSecurityListSql007RequestModel req) throws Exception;
    
    
    public IfaHoldingSecurityListSql008ResponseModel selectIfaHoldingSecurityListSql008(
            @Param("req") IfaHoldingSecurityListSql008RequestModel req) throws Exception;
    
    
    public List<IfaHoldingSecurityListSql009ResponseModel> selectIfaHoldingSecurityListSql009(
            @Param("req") IfaHoldingSecurityListSql009RequestModel req) throws Exception;
    
    
    public List<IfaHoldingSecurityListSql010ResponseModel> selectIfaHoldingSecurityListSql010(
            @Param("req") IfaHoldingSecurityListSql010RequestModel req) throws Exception;
        
    public List<IfaHoldingSecurityListSql011ResponseModel> selectIfaHoldingSecurityListSql011(
            @Param("req") IfaHoldingSecurityListSql011RequestModel req) throws Exception;
    
}
