package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDocRequestCancelConfirmSql002RequestModel;

/**
 * 画面ID：SUB0202_0704-03_1 画面名：書類請求取消
 * 
 * @author xin.huang
 *
 */
@Mapper
public interface IfaDocRequestCancelConfirmMapper {

    /**
     * SQLID：Sql001
     * SQL名：書類請求取消
     * SQLタイプ：update 
     * リクエスト：IfaDocRequestCancelConfirmSql001RequestModel
     *
     * @param req {@code IfaDocRequestCancelConfirmSql001RequestModel }
     * @return int
     * @throws Exception書類請求取消処理で例外が発生した場合
     */
    public int updateIfaDocRequestCancelConfirSql001(@Param("req") IfaDocRequestCancelConfirmSql001RequestModel req)
            throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：BM交付取消
     * SQLタイプ：update 
     * リクエスト：IfaDocRequestCancelConfirmSql002RequestModel
     *
     * @param req {@code IfaDocRequestCancelConfirmSql002RequestModel }
     * @return int
     * @throws Exception書類請求取消処理で例外が発生した場合
     */
    public int updateIfaDocRequestCancelConfirSql002(@Param("req") IfaDocRequestCancelConfirmSql002RequestModel req)
            throws Exception;
}
