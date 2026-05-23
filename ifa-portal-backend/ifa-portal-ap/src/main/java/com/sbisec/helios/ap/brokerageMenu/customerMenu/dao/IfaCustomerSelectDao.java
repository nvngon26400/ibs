package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaCustomerSelectSql002ResponseModel;

/**
 * 画面ID：SUB0202_00-01
 * 画面名：顧客選択
 *
 * @author SCSK
 */
public interface IfaCustomerSelectDao {
    
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
            IfaCustomerSelectSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：お気に入り登録・解除
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerSelectSql002RequestModel
     *
     * @param req リクエスト
     * @return 更新／挿入 行数
     * @exception Exception お気に入り登録・解除処理で例外が発生した場合
     */
    public int selectIfaCustomerSelectSql002(
            IfaCustomerSelectSql002RequestModel req) throws Exception;
    
}
