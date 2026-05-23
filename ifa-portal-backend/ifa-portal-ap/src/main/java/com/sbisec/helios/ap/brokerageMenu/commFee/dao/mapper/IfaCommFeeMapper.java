package com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015ResponseModel;


/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Mapper
public interface IfaCommFeeMapper {
    
    /**
     * SQLID：SQL001～SQL012
     * SQL名：手数料・報酬一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql001To012RequestModel
     * レスポンスクラス：IfaCommFeeSql001To012ResponseModel
     *
     * @param req パラメータ
     * @return 手数料・報酬一覧
     * @exception Exception システムエラー
     */
    public List<IfaCommFeeSql001To012ResponseModel> selectIfaCommFeeSql001To012(
            @Param("req") IfaCommFeeSql001To012RequestModel req
    ) throws Exception;

    /**
     * SQLID：Sql015
     * SQL名：SBIラップ管理報酬サービス表示制御情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql015RequestModel
     * レスポンスクラス：IfaCommFeeSql015ResponseModel
     *
     * @param req パラメータ
     * @return SBIラップ管理報酬サービス表示制御情報
     * @exception Exception システムエラー
     */
    public List<IfaCommFeeSql015ResponseModel> selectIfaCommFeeSql015(
            @Param("req") IfaCommFeeSql015RequestModel req
    ) throws Exception;
    
}
