package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * 外部リンク
 * 2025/05/21 新規作成
 *
 * @author 大連 葉
 */
@Mapper
public interface IfaExternalLinkMapper {

    /**
     * SQLID：Sql001
     * SQL名：店群情報取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return String レスポンス
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public String selectIfaExternalLinkSql001(String req) throws Exception;
}
