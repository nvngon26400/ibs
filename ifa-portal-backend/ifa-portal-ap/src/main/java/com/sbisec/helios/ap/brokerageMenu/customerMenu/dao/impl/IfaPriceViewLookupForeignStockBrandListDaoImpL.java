package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaPriceViewLookupForeignStockBrandListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaPriceViewLookupForeignStockBrandListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaPriceViewLookupForeignStockBrandListSql003ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_0302-01
 * 画面名：単価表照会（外国株式銘柄一覧）
 * 2024/03/27 新規作成
 *
 * @author SCSK今井
 */
@Component
public class IfaPriceViewLookupForeignStockBrandListDaoImpL extends RowSelectableDao
        implements IfaPriceViewLookupForeignStockBrandListDao {
    
    @Autowired
    private IfaPriceViewLookupForeignStockBrandListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：全体メッセージ取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql001RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql001ResponseModel
     *
     * @param req リクエスト
     * @return 全体メッセージのリスト
     * @exception exception システムエラー
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql001(
            IfaPriceViewLookupForeignStockBrandListSql001RequestModel req) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel> res = new DataList<IfaPriceViewLookupForeignStockBrandListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaPriceViewLookupForeignStockBrandListSql001(req));
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：単価表照会情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql002RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql002ResponseModel
     *
     * @param req リクエスト
     * @return 単価表照会情報のリスト
     * @exception exception システムエラー
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql002(
            IfaPriceViewLookupForeignStockBrandListSql002RequestModel req) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel> res = new DataList<IfaPriceViewLookupForeignStockBrandListSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaPriceViewLookupForeignStockBrandListSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql003
     * SQL名：外部リンクURL取得
     * SQLタイプ：select
     * リクエストクラス：IfaPriceViewLookupForeignStockBrandListSql003RequestModel
     * レスポンスクラス：IfaPriceViewLookupForeignStockBrandListSql003ResponseModel
     *
     * @param req リクエスト
     * @return 外部リンクURLのリスト
     * @throws Exception SQL実行時に発生した例外
     */
    public DataList<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel> selectIfaPriceViewLookupForeignStockBrandListSql003(
            IfaPriceViewLookupForeignStockBrandListSql003RequestModel req) throws Exception {
        
        DataList<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel> res = new DataList<IfaPriceViewLookupForeignStockBrandListSql003ResponseModel>();
        
        res.setDataList(mapper.selectIfaPriceViewLookupForeignStockBrandListSql003(req));
        return res;
    }
    
}
