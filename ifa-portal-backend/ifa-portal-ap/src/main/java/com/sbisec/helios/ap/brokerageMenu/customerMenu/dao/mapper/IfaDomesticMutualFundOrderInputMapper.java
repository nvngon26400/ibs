package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaDomesticMutualFundOrderInputSql003RequestModel;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 
 * 画面ID：SUB0202_0401-02_1
 * 画面名：国内投信注文入力
 *
 * @author SCSK
 */
@Mapper
public interface IfaDomesticMutualFundOrderInputMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：短期売却確認の期間取得
     * SQLタイプ：select
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql001ResponseModel
     *
     * @return レスポンス
     * @exception Exception 償還前売却確認の期間取得で例外が発生した場合
     */
    public IfaDomesticMutualFundOrderInputSql001ResponseModel selectIfaDomesticMutualFundOrderInputSql001()
            throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：償還前売却確認の期間取得
     * SQLタイプ：select
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql002ResponseModel
     *
     * @return レスポンス
     * @exception Exception 償還前売却確認の期間取得で例外が発生した場合
     */
    public IfaDomesticMutualFundOrderInputSql002ResponseModel selectIfaDomesticMutualFundOrderInputSql002()
            throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundOrderInputSql003RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderInputSql003ResponseModel
     *
     * @param req リクエスト
     * @return レスポンス
     * @exception Exception 実行時例外
     */
    public List<IfaDomesticMutualFundOrderInputSql003ResponseModel> selectIfaDomesticMutualFundOrderInputSql003(
            @Param("req") IfaDomesticMutualFundOrderInputSql003RequestModel req) throws Exception;
    
    
}
