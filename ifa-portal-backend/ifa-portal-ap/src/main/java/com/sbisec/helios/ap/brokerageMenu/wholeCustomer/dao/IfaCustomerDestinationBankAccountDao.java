package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001ResponseModel;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 *
 */
public interface IfaCustomerDestinationBankAccountDao {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客振込先金融機関口座一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerDestinationBankAccountSql001RequestModel
     * レスポンスクラス：IfaCustomerDestinationBankAccountSql001ResponseModel

     * @param req リクエスト
     * @return IfaCustomerDestinationBankAccountSql001RequestModel レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaCustomerDestinationBankAccountSql001ResponseModel> selectIfaCustomerDestinationBankAccountSql001(IfaCustomerDestinationBankAccountSql001RequestModel req)
            throws Exception;

}
