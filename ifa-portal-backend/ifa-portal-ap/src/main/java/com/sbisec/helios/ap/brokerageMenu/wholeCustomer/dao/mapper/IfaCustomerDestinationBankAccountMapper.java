package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaCustomerDestinationBankAccountSql001ResponseModel;

/**
 * 画面ID：SUB020303-01
 * 画面名：顧客振込先金融機関口座

 * @author 大崎 辰弥
    2023/10/27 新規作成
 */
@Mapper
public interface IfaCustomerDestinationBankAccountMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：顧客振込先金融機関口座一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCustomerDestinationBankAccountSql001RequestModel
     * レスポンスクラス：IfaCustomerDestinationBankAccountSql001ResponseModel

     * @return IfaCustomerDestinationBankAccountSql001ResponseModel レスポンス
     * @exception Exception SQLExceptionなど
     */
    public List<IfaCustomerDestinationBankAccountSql001ResponseModel> selectIfaCustomerDestinationBankAccountSql001(
            @Param("req") IfaCustomerDestinationBankAccountSql001RequestModel req
        ) throws Exception;

}
