package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001ResponseModel;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK 金志
 */
public interface IfaDomesticPositionListDao {
    
    /**
     * SQLID：Sql001
     * SQL名：銘柄名取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticPositionListSql001RequestModel
     * レスポンスクラス：IfaDomesticPositionListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄名取得の際、例外が発生した場合
     */
    public DataList<IfaDomesticPositionListSql001ResponseModel> selectIfaDomesticPositionListSql001(
            IfaDomesticPositionListSql001RequestModel req) throws Exception;
    
}
