package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaContactDetailSql002ResponseModel;

/**
 * SQLマッパーのインターフェース
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Mapper
public interface IfaContactDetailMapper {

    /**
     * SQLID:Sql001
     * SQL名:問合せ情報取得
     * SQLタイプ:select
     * リクエストクラス：IfaContactDetailSql001RequestModel
     * レスポンスクラス：IfaContactDetailSql001ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactDetailSql001ResponseModel> selectIfaContactDetailSql001(
            @Param("req") IfaContactDetailSql001RequestModel x_req) throws Exception;

    /**
     * SQLID:Sql002
     * SQL名:問合せ回答内容情報取得
     * SQLタイプ:select
     * リクエストクラス：IfaContactDetailSql002RequestModel
     * レスポンスクラス：IfaContactDetailSql002ResponseModel
     *
     * @param x_req prepared statement
     * @return res レスポンス
     * @exception Exception システムエラー
     */
    public List<IfaContactDetailSql002ResponseModel> selectIfaContactDetailSql002(
            @Param("req") IfaContactDetailSql002RequestModel x_req) throws Exception;

}
