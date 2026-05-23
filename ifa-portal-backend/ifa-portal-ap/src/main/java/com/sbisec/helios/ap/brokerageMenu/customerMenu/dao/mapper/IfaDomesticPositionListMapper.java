package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001ResponseModel;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK 金志
 */
@Mapper
public interface IfaDomesticPositionListMapper {
    
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
    public List<IfaDomesticPositionListSql001ResponseModel> selectIfaDomesticPositionListSql001(
            @Param("req") IfaDomesticPositionListSql001RequestModel req) throws Exception;
    
}
