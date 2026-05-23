package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaDomesticPositionListDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaDomesticPositionListMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticPositionListSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0202_010202-01
 * 画面名：国内建玉一覧
 *
 * @author SCSK 金志
 */
@Component
public class IfaDomesticPositionListDaoImpL extends RowSelectableDao implements IfaDomesticPositionListDao {
    
    @Autowired
    private IfaDomesticPositionListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：銘柄名取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticPositionListSql001RequestModel
     * レスポンスクラス：IfaDomesticPositionListSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @throws Exception 銘柄名取得の際、例外が発生した場合
     */
    public DataList<IfaDomesticPositionListSql001ResponseModel> selectIfaDomesticPositionListSql001(
            IfaDomesticPositionListSql001RequestModel req) throws Exception {
        
        DataList<IfaDomesticPositionListSql001ResponseModel> res = new DataList<IfaDomesticPositionListSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaDomesticPositionListSql001(req));
        return res;
    }
    
}
