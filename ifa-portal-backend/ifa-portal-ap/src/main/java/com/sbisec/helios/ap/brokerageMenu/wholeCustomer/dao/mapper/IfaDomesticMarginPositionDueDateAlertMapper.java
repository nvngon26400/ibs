package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMarginPositionDueDateAlertSql001ResponseModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB020301_02-01
 * 画面名：国内信用建玉期日アラート一覧
 *
 * @author SCSK
 2024/06/19 新規作成
 */
@Mapper
public interface IfaDomesticMarginPositionDueDateAlertMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：決済期日顧客情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMarginPositionDueDateAlertSql001RequestModel
     * レスポンスクラス：IfaDomesticMarginPositionDueDateAlertSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaDomesticMarginPositionDueDateAlertSql001ResponseModel> selectIfaDomesticMarginPositionDueDateAlertSql001(
            @Param("req") IfaDomesticMarginPositionDueDateAlertSql001RequestModel req) throws Exception;
    
    public Integer selectIfaDomesticMarginPositionDueDateAlertSql005() throws Exception;
    
    /**
     * SQLID：Sql006
     * SQL名：営業日取得
     * SQLタイプ：select
     *
     * @return 営業日
     * @throws Exception システムエラー
     */
    public String selectIfaDomesticMarginPositionDueDateAlertSql006() throws Exception;
}
