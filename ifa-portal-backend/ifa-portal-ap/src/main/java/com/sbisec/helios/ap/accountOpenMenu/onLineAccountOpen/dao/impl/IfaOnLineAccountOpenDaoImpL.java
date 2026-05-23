package com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.IfaOnLineAccountOpenDao;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.mapper.IfaOnLineAccountOpenMapper;

import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.onLineAccountOpen.dao.model.IfaOnLineAccountOpenSql001ResponseModel;


/**
 * 画面ID：SUB0207_0201
 * 画面名：オンライン口座開設
 *
 * @author SCSK 木村
 2025/02/06 新規作成
 */
@Component
public class IfaOnLineAccountOpenDaoImpL extends RowSelectableDao implements IfaOnLineAccountOpenDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOnLineAccountOpenDaoImpL.class);
    @Autowired
    private IfaOnLineAccountOpenMapper mapper;
    

    /**
     * SQLID：Sql001
     * SQL名：Kintone口座開設URL取得
     * SQLタイプ：select
     * リクエストクラス：IfaOnLineAccountOpenSql001RequestModel
     * レスポンスクラス：IfaOnLineAccountOpenSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOnLineAccountOpenSql001ResponseModel> selectIfaOnLineAccountOpenSql001(
            IfaOnLineAccountOpenSql001RequestModel req
    ) throws Exception {

        DataList<IfaOnLineAccountOpenSql001ResponseModel> res = new DataList<IfaOnLineAccountOpenSql001ResponseModel>();
        res.setDataList(mapper.selectIfaOnLineAccountOpenSql001(req));

        return res;

    }
}