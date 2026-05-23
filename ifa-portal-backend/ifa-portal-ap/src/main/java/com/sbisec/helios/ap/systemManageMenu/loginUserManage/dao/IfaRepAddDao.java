package com.sbisec.helios.ap.systemManageMenu.loginUserManage.dao;

import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql001ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql001ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql002ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql002ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql003ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql003ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql004ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql004ModelResponse;

import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql005ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql006ModelRequest;
import com.sbisec.helios.ap.systemManageMenu.loginUserManage.model.IfaRepAddSql006ModelResponse;





/**
 * 画面ID：SUB0601_01-06_1
 * 画面名：担当者追加
 *
 * @author 島崎 聡士 2023/09/08 新規作成
 */
public interface IfaRepAddDao {
    
    /**
     * SQLID：Sql001
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaRepAddSql001ModelRequest
     * レスポンスクラス：IfaRepAddSql001ModelResponse
     *
     * @param req リクエストクラス：IfaRepAddSql001ModelRequest
     * @return レスポンスクラス：IfaRepAddSql001ModelResponse
     * @exception Exception SQL実行例外
     */
    public DataList<IfaRepAddSql001ModelResponse> selectIfaRepAddSql001(IfaRepAddSql001ModelRequest req)
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaRepAddSql002ModelRequest
     * レスポンスクラス：IfaRepAddSql002ModelResponse
     *
     * @param req リクエストクラス：IfaRepAddSql002ModelRequest
     * @return レスポンスクラス：IfaRepAddSql002ModelResponse
     * @exception Exception SQL実行例外
     */
    public DataList<IfaRepAddSql002ModelResponse> selectIfaRepAddSql002(IfaRepAddSql002ModelRequest req)
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：検索
     * SQLタイプ：select
     * リクエストクラス：IfaRepAddSql003ModelRequest
     * レスポンスクラス：IfaRepAddSql003ModelResponse
     *
     * @param req リクエストクラス：IfaRepAddSql003ModelRequest
     * @return レスポンスクラス：IfaRepAddSql003ModelResponse
     * @exception Exception SQL実行例外
     */
    public DataList<IfaRepAddSql003ModelResponse> selectIfaRepAddSql003(IfaRepAddSql003ModelRequest req)
            throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：検索
     * SQLタイプ：select
     *
     * @param req リクエストクラス：IfaRepAddSql004ModelRequest
     * @return レスポンスクラス：IfaRepAddSql004ModelResponse
     * @exception Exception SQL実行例外
     */
    public DataList<IfaRepAddSql004ModelResponse> selectIfaRepAddSql004(IfaRepAddSql004ModelRequest req)
            throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：登録
     * SQLタイプ：insert
     * リクエストクラス：IfaRepAddSql005RequestModel
     *
     * @param req リクエストクラス：IfaRepAddSql005ModelRequest
     * @return SQL登録結果
     * @exception Exception SQL実行例外
     */
    public int insertIfaRepAddSql005(IfaRepAddSql005ModelRequest req)
            throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：検索
     * SQLタイプ：select
     * レスポンスクラス：IfaRepAddSql006ModelResponse
     *
     * @param req IfaRepAddSql006ModelRequest
     * @return レスポンスクラス：IfaRepAddSql006ModelResponse
     * @exception Exception SQL実行例外
     */
    public DataList<IfaRepAddSql006ModelResponse> selectIfaRepAddSql006(IfaRepAddSql006ModelRequest req)
            throws Exception;
}
