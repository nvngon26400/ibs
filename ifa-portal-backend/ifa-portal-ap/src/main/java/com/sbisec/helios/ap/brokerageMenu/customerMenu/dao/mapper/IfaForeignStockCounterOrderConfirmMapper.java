package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql006ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model.IfaForeignStockCounterOrderConfirmSql007ResponseModel;


/**
 * 画面ID：SUB0202_0302-02_2
 * 画面名：外国株式店頭注文確認
 * 2024/05/10 新規作成
 *
 * @author SCSK 江口
 */
@Mapper
public interface IfaForeignStockCounterOrderConfirmMapper {

    /**
     * SQLID：Sql001
     * SQL名：項目チェック情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderConfirmSql001RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql001ResponseModel
     *
     * @param req パラメータ
     * @return 項目チェック情報
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql001ResponseModel> selectIfaForeignStockCounterOrderConfirmSql001(
            @Param("req") IfaForeignStockCounterOrderConfirmSql001RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql002
     * SQL名：店頭取引販売上限数量取得
     * SQLタイプ：select
     * リクエストクラス：IfaForeignStockCounterOrderConfirmSql002RequestModel
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql002ResponseModel
     *
     * @param req パラメータ
     * @return 店頭取引販売上限数量
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql002ResponseModel> selectIfaForeignStockCounterOrderConfirmSql002(
            @Param("req") IfaForeignStockCounterOrderConfirmSql002RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql003
     * SQL名：ワーニング約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql003ResponseModel
     *
     * @return ワーニング約定金額
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql003ResponseModel> selectIfaForeignStockCounterOrderConfirmSql003() throws Exception;

    /**
     * SQLID：Sql004
     * SQL名：上限約定金額取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql004ResponseModel
     *
     * @return 上限約定金額
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql004ResponseModel> selectIfaForeignStockCounterOrderConfirmSql004() throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：店頭取引注文登録
     * SQLタイプ：insert
     *
     * @param req パラメータ
     * @return 挿入行数
     * @exception Exception システムエラー
     */
    public int insertIfaForeignStockCounterOrderConfirmSql005(
            @Param("req")  IfaForeignStockCounterOrderConfirmSql005RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql006
     * SQL名：上限注文数量取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql006ResponseModel
     *
     * @return 上限注文数量
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql006ResponseModel> selectIfaForeignStockCounterOrderConfirmSql006() throws Exception;

    /**
     * SQLID：Sql007
     * SQL名：営業日チェック情報取得
     * SQLタイプ：select
     * レスポンスクラス：IfaForeignStockCounterOrderConfirmSql007ResponseModel
     *
     * @return 営業日チェック情報
     * @exception Exception システムエラー
     */
    public List<IfaForeignStockCounterOrderConfirmSql007ResponseModel> selectIfaForeignStockCounterOrderConfirmSql007() throws Exception;

}
