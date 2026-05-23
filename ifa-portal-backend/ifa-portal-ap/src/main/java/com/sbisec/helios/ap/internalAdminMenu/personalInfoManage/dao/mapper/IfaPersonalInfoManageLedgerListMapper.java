package com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql001RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql001ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql003ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql004ResponseModel;

import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.personalInfoManage.dao.model.IfaPersonalInfoManageLedgerListSql005ResponseModel;



/**
 * 画面ID：SUB0403-01
 * 画面名：個人情報管理台帳一覧

 * @author 大崎辰弥
    2023/12/19 新規作成
 */
@Mapper
public interface IfaPersonalInfoManageLedgerListMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：個人情報管理台帳作成ディレクトリ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql001RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql001ResponseModel

     * @return IfaPersonalInfoManageLedgerListSql001ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaPersonalInfoManageLedgerListSql001ResponseModel> selectIfaPersonalInfoManageLedgerListSql001() throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：個人情報管理台帳一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql003RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql003ResponseModel

     * @param req リクエスト
     * @return IfaPersonalInfoManageLedgerListSql003ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaPersonalInfoManageLedgerListSql003ResponseModel> selectIfaPersonalInfoManageLedgerListSql003(
            @Param("req") IfaPersonalInfoManageLedgerListSql003RequestModel req
        ) throws Exception;
    
    
    
    /**
     * SQLID：Sql004
     * SQL名：個人情報管理台帳更新
     * SQLタイプ：update

     * @param req リクエスト
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaPersonalInfoManageLedgerListSql004(
            @Param("req")  IfaPersonalInfoManageLedgerListSql004RequestModel req
        ) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：個人情報管理台帳一覧件数取得
     * SQLタイプ：select
     * リクエストクラス：IfaPersonalInfoManageLedgerListSql005RequestModel
     * レスポンスクラス：IfaPersonalInfoManageLedgerListSql005ResponseModel

     * @param req リクエスト
     * @return IfaPersonalInfoManageLedgerListSql005ResponseModel
     * @exception Exception SQLExceptionなど
     */
    public List<IfaPersonalInfoManageLedgerListSql005ResponseModel> selectIfaPersonalInfoManageLedgerListSql005(
            @Param("req") IfaPersonalInfoManageLedgerListSql005RequestModel req
        ) throws Exception;
    
}
