package com.sbisec.helios.ap.wholePortal.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.wholePortal.dao.IfaInfoDetailDao;
import com.sbisec.helios.ap.wholePortal.dao.mapper.IfaInfoDetailMapper;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql001RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002RequestModel;
import com.sbisec.helios.ap.wholePortal.dao.model.IfaInfoDetailSql002ResponseModel;


/**
 * 画面ID：SUB01-03
 * 画面名：Information詳細
 * 2025/01/20 新規作成
 *
 * @author SCSK 江口
 */
@Component
public class IfaInfoDetailDaoImpL extends RowSelectableDao implements IfaInfoDetailDao {

    @Autowired
    private IfaInfoDetailMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：お知らせ既読テーブル更新
     * SQLタイプ：update
     * リクエストクラス：InfoDetailSql001RequestModel
     *
     * @param req パラメータ
     * @return 更新行数
     * @exception Exception システムエラー
     */
    @Override
    public int updateIfaInfoDetailSql001(
            IfaInfoDetailSql001RequestModel req
    ) throws Exception {

        int affectedRows = mapper.updateIfaInfoDetailSql001(req);
        return affectedRows;

    }


    /**
     * SQLID：Sql002
     * SQL名：お知らせ情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaInfoDetailSql002RequestModel
     * レスポンスクラス：IfaInfoDetailSql002ResponseModel
     *
     * @param req パラメータ
     * @return お知らせ情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaInfoDetailSql002ResponseModel> selectIfaInfoDetailSql002(
            IfaInfoDetailSql002RequestModel req
    ) throws Exception {

        DataList<IfaInfoDetailSql002ResponseModel> res = new DataList<IfaInfoDetailSql002ResponseModel>();
        res.setDataList(mapper.selectIfaInfoDetailSql002(req));

        return res;

    }
}
