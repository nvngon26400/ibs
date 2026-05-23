package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaExternalLinkDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaExternalLinkMapper;

/**
 * 外部リンク
 * 2025/05/21 新規作成
 *
 * @author 大連 葉
 */
@Component
public class IfaExternalLinkDaoImpL extends RowSelectableDao implements IfaExternalLinkDao{

    @Autowired
    private IfaExternalLinkMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：店群情報取得
     * SQLタイプ：select
     *
     * @param req リクエスト
     * @return String レスポンス
     * @throws Exception 初期化処理で例外が発生した場合
     */
    public String selectIfaExternalLinkSql001(String req) throws Exception {
        return mapper.selectIfaExternalLinkSql001(req);
    }
}
