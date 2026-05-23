package com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql005RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel;



/**
 * 画面ID：SUB0204_01-04_1
 * 画面名：募集期間マスタ登録
 * 2024/03/26 新規作成
 *
 * @author SCSK 濱田
 */
@Mapper
public interface IfaSubscriptPeriodMasterRegisterInputMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：銘柄情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql001RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptPeriodMasterRegisterInputSql001ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql001(
            @Param("req") IfaSubscriptPeriodMasterRegisterInputSql001RequestModel req
    ) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：募集期間情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql002RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptPeriodMasterRegisterInputSql002ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql002(
            @Param("req") IfaSubscriptPeriodMasterRegisterInputSql002RequestModel req
    ) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：対面募集注文確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql003RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptPeriodMasterRegisterInputSql003ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql003(
            @Param("req") IfaSubscriptPeriodMasterRegisterInputSql003RequestModel req
    ) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：募集期間情報登録有無確認
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql004RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptPeriodMasterRegisterInputSql004ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql004(
            @Param("req") IfaSubscriptPeriodMasterRegisterInputSql004RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql005
     * SQL名：対面募集期間マスタ登録
     * SQLタイプ：insert
     *
     * @param req リクエストパラメータ
     * @return 登録件数
     * @exception Exception システムエラー
     */
    public int insertIfaSubscriptPeriodMasterRegisterInputSql005(
            @Param("req")  IfaSubscriptPeriodMasterRegisterInputSql005RequestModel req
    ) throws Exception;
    
    
    /**
     * SQLID：Sql006
     * SQL名：対面募集期間マスタ更新
     * SQLタイプ：update
     *
     * @param req リクエストパラメータ
     * @return 更新件数
     * @exception Exception システムエラー
     */
    public int updateIfaSubscriptPeriodMasterRegisterInputSql006(
            @Param("req")  IfaSubscriptPeriodMasterRegisterInputSql006RequestModel req
    ) throws Exception;
    
    /**
     * SQLID：Sql007
     * SQL名：営業日チェック
     * SQLタイプ：select
     * リクエストクラス：IfaSubscriptPeriodMasterRegisterInputSql007RequestModel
     * レスポンスクラス：IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel
     *
     * @param req リクエストパラメータ
     * @return 検索結果
     * @exception Exception システムエラー
     */
    public List<IfaSubscriptPeriodMasterRegisterInputSql007ResponseModel> selectIfaSubscriptPeriodMasterRegisterInputSql007(
            @Param("req") IfaSubscriptPeriodMasterRegisterInputSql007RequestModel req
    ) throws Exception;
    
}
