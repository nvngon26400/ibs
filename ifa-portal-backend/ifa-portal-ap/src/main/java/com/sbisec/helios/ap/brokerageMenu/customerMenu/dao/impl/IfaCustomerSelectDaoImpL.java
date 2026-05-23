package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.IfaCustomerSelectDao;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper.IfaCustomerSelectMapper;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001SubQueryResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002ResponseModel;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 * @author <author-name>
 *
 * 2023/09/15 新規作成
 */
@Component
public class IfaCustomerSelectDaoImpL extends RowSelectableDao implements IfaCustomerSelectDao {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCustomerSelectDaoImpL.class);
    
    @Autowired
    private IfaCustomerSelectMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：顧客一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql001RequestModel
     * レスポンスクラス：IfaCustomerSelectSql001ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 顧客一覧取得処理で例外が発生した場合
     */
    public DataList<IfaCustomerSelectSql001ResponseModel> selectIfaCustomerSelectSql001(
            IfaCustomerSelectSql001RequestModel req) throws Exception {
        
        DataList<IfaCustomerSelectSql001ResponseModel> res = new DataList<IfaCustomerSelectSql001ResponseModel>();
        
        try {
            List<IfaCustomerSelectSql001ResponseModel> resList = mapper.selectIfaCustomerSelectSql001(req);
            res.setDataList(resList);
        } catch (Exception e) {
            throw e;
        }
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：お気に入り登録・解除
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql002RequestModel
     * レスポンスクラス：IfaCustomerSelectSql002ResponseModel
     *
     * @param req リクエスト
     * @return 更新／挿入 行数
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public int selectIfaCustomerSelectSql002(
            IfaCustomerSelectSql002RequestModel req) throws Exception {
        
        List<IfaCustomerSelectSql002ResponseModel> resList = mapper.selectIfaCustomerSelectSql002(req);
        int rtnCount = 0;
        
        try {
            if (resList.size() > 0) {
                //抽出した顧客コードをリクエストに設定
                req.setCustomerCode(resList.get(0).getCustomerId());
    
                rtnCount = mapper.selectIfaCustomerSelectSql00201(req);
            } else {
                rtnCount = mapper.selectIfaCustomerSelectSql00202(req);
            }
        } catch (Exception e) {
            rtnCount = 0;
        }
        
        return rtnCount;
    }
    
}
