package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginPositionDueDateAlertSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB020301_02-02

 * 画面名：米株信用建玉期日アラート一覧
 *
 * @author SCSK
 2024/06/21 新規作成
 */
@Mapper
public interface IfaForeignMarginPositionDueDateAlertMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：決済期日顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignMarginPositionDueDateAlertSql001RequestModel
     * レスポンスクラス：IfaForeignMarginPositionDueDateAlertSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaForeignMarginPositionDueDateAlertSql001ResponseModel> selectIfaForeignMarginPositionDueDateAlertSql001(
            @Param("req") IfaForeignMarginPositionDueDateAlertSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql005
     * SQL名：IFA顧客アラート(日数)取得
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public Integer selectIfaForeignMarginPositionDueDateAlertSql005() throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：営業日取得
     * SQLタイプ：select
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public String selectIfaForeignMarginPositionDueDateAlertSql006() throws Exception;
    
}
