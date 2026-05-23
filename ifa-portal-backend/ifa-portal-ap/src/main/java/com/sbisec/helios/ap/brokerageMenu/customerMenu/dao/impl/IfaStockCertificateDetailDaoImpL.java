package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaStockCertificateDetailDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaStockCertificateDetailMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaStockCertificateDetailSql001ResponseModel;



/**
 * 画面ID：SUB0202_0703-03
 * 画面名：株券詳細
 *
 * @author SBI大連 董
 *2025/03/20 新規作成
 */
@Component
public class IfaStockCertificateDetailDaoImpL extends RowSelectableDao implements IfaStockCertificateDetailDao {
    @Autowired
    private IfaStockCertificateDetailMapper mapper;

    /**
     * SQLID：Sql001 
     * SQL名：株券詳細覧取得 
     * SQLタイプ：select
     * リクエストクラス：IfaStockCertificateDetailSql001RequestModel
     * レスポンスクラス：IfaStockCertificateDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaStockCertificateDetailSql001ResponseModel> selectIfaStockCertificateDetailSql001(
            IfaStockCertificateDetailSql001RequestModel selSql001Req) throws Exception {
        DataList<IfaStockCertificateDetailSql001ResponseModel> res = new DataList<IfaStockCertificateDetailSql001ResponseModel>();

        res.setDataList(mapper.selectIfaStockCertificateDetailSql001(selSql001Req));
        return res;
    }

}
