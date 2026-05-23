package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTodayTradeListSql002ResponseModel;
import com.sbisec.helios.ap.common.annotation.dao.MariadbMapper;




/**
 * 
 * 画面ID：SUB020302_0102-01
 * 画面名：国内株当日約定一覧
 * @author SCSK 江口
 *
 * 2024/09/26 新規作成
 */
@MariadbMapper
public interface IfaTodayTradeListMapperMariaDb {

    /**
     * SQLID：Sql002
     * SQL名：銘柄コード存在チェック
     * SQLタイプ：select
     * リクエストクラス：IfaTodayTradeListSql002RequestModel
     * レスポンスクラス：IfaTodayTradeListSql002ResponseModel
     * @param req 銘柄コード
     * @return 銘柄情報のリスト
     * @exception Exception システムエラー
     */
    public List<IfaTodayTradeListSql002ResponseModel> selectIfaTodayTradeListSql002(
        @Param("req") IfaTodayTradeListSql002RequestModel req
        ) throws Exception;

}
