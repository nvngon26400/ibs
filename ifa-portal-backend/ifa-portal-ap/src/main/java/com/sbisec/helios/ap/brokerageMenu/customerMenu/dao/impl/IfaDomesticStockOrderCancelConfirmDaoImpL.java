package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticStockOrderCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticStockOrderCancelConfirmMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubRequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql001SubResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticStockOrderCancelConfirmSql002RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Component
public class IfaDomesticStockOrderCancelConfirmDaoImpL extends RowSelectableDao implements IfaDomesticStockOrderCancelConfirmDao {
    
    @Autowired
    private IfaDomesticStockOrderCancelConfirmMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：発注前の注文登録
     * SQLタイプ：insert
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 登録成否
     * @exception Exception システムエラー
     */
    @Override
    public int insertIfaDomesticStockOrderCancelConfirmSql001(IfaDomesticStockOrderCancelConfirmSql001RequestModel req)
            throws Exception {

        return mapper.insertIfaDomesticStockOrderCancelConfirmSql001(req);
    }


    /**
     * SQLID：Sql001sub
     * SQL名：発注前の注文登録
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql001RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return EC受注番号が等しい最新のレコード
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> selectIfaDomesticStockOrderCancelConfirmSql001Sub(
            IfaDomesticStockOrderCancelConfirmSql001SubRequestModel req) throws Exception {
        
        DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel> res = new DataList<IfaDomesticStockOrderCancelConfirmSql001SubResponseModel>();
        
        res.setDataList(mapper.selectIfaDomesticStockOrderCancelConfirmSql001Sub(req));
        return res;
    }


    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql002ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新成否
     * @exception Exception システムエラー
     */
    @Override
    public int updateIfaDomesticStockOrderCancelConfirmSql002(IfaDomesticStockOrderCancelConfirmSql002RequestModel req)
            throws Exception {

        return mapper.updateIfaDomesticStockOrderCancelConfirmSql002(req);
    }

    /**
     * SQLID：Sql002
     * SQL名：発注後の注文更新発注がエラーの場合(API応答なし)
     * SQLタイプ：update
     * リクエストクラス：IfaDomesticStockOrderCancelConfirmSql002RequestModel
     * レスポンスクラス：IfaDomesticStockOrderCancelConfirmSql002ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 更新成否
     * @exception Exception システムエラー
     */
    @Override
    public int updateIfaDomesticStockOrderCancelConfirmSql002b(IfaDomesticStockOrderCancelConfirmSql002RequestModel req)
            throws Exception {

        return mapper.updateIfaDomesticStockOrderCancelConfirmSql002b(req);
    }

}
