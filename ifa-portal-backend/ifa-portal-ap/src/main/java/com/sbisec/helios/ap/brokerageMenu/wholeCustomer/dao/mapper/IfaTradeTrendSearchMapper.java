package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaTradeTrendSearchSql002ResponseModel;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
@Mapper
public interface IfaTradeTrendSearchMapper {
    
    /**
     * SQLID：Sql002
     * SQL名：取引動向検索一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaTradeTrendSearchSql002RequestModel
     * レスポンスクラス：IfaTradeTrendSearchSql002ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaTradeTrendSearchSql002ResponseModel> selectIfaTradeTrendSearchSql002(
            @Param("req") IfaTradeTrendSearchSql002RequestModel req) throws Exception;

}
