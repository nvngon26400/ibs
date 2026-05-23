package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaForeignStockCounterOrderConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaForeignStockCounterOrderConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql007ResponseModel;


/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Component
public class IfaForeignStockCounterOrderConfirmDaoImpL extends RowSelectableDao implements IfaForeignStockCounterOrderConfirmDao {

    @Autowired
    private IfaForeignStockCounterOrderConfirmMapper mapper;

    /**
     * SQLID：Sql001
     * SQL名：項目チェック情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql001ResponseModel
     *
     * @param req パラメータ
     * @return 項目チェック情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql001ResponseModel> selectIfaForeignStockCounterOrderConfirmSql001(
            IfaForeignStockCounterOrderConfirmSql001RequestModel req
    ) throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql001ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql001ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql001(req));
        return res;
    }

    /**
     * SQLID：Sql002
     * SQL名：店頭取引販売上限数量取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql002ResponseModel
     *
     * @param req パラメータ
     * @return 店頭取引販売上限数量
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql002ResponseModel> selectIfaForeignStockCounterOrderConfirmSql002(
            IfaForeignStockCounterOrderConfirmSql002RequestModel req
    ) throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql002ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql002ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql002(req));
        return res;
    }

    /**
     * SQLID：Sql003
     * SQL名：ワーニング約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql003ResponseModel
     *
     * @return ワーニング約定金額
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql003ResponseModel> selectIfaForeignStockCounterOrderConfirmSql003() throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql003ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql003ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql003());
        return res;
    }

    /**
     * SQLID：Sql004
     * SQL名：上限約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql004ResponseModel
     *
     * @return 上限約定金額
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql004ResponseModel> selectIfaForeignStockCounterOrderConfirmSql004() throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql004ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql004ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql004());
        return res;
    }

    /**
     * SQLID：Sql005
     * SQL名：店頭取引注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaForeignStockCounterOrderConfirmSql005RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql005ResponseModel
     *
     * @param req パラメータ
     * @return 挿入行数
     * @exception Exception システムエラー
     */
    @Override
    public int insertIfaForeignStockCounterOrderConfirmSql005(
            IfaForeignStockCounterOrderConfirmSql005RequestModel req
    ) throws Exception {

        return mapper.insertIfaForeignStockCounterOrderConfirmSql005(req);
    }

    /**
     * SQLID：Sql006
     * SQL名：上限注文数量取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql006ResponseModel
     *
     * @return 上限注文数量
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql006ResponseModel> selectIfaForeignStockCounterOrderConfirmSql006() throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql006ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql006ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql006());
        return res;
    }

    /**
     * SQLID：Sql007
     * SQL名：営業日チェック情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql007ResponseModel
     *
     * @return 営業日チェック情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaForeignStockCounterOrderConfirmSql007ResponseModel> selectIfaForeignStockCounterOrderConfirmSql007() throws Exception {

        DataList<IfaForeignStockCounterOrderConfirmSql007ResponseModel> res = new DataList<IfaForeignStockCounterOrderConfirmSql007ResponseModel>();

        res.setDataList(mapper.selectIfaForeignStockCounterOrderConfirmSql007());
        return res;
    }

}
