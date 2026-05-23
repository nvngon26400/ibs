package com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaLevelFeeSql001To006ResponseModel;


/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Mapper
public interface IfaLevelFeeMapper {
    
    /**
     * SQLID：SQL001～SQL006
     * SQL名：残高連動手数料・報酬 一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaLevelFeeSql001To006RequestModel
     * レスポンスクラス：IfaLevelFeeSql001To006ResponseModel
     *
     * @param req パラメータ
     * @return 残高連動手数料・報酬 一覧
     * @exception Exception システムエラー
     */
    public List<IfaLevelFeeSql001To006ResponseModel> selectIfaLevelFeeSql001To006(
            @Param("req") IfaLevelFeeSql001To006RequestModel req
    ) throws Exception;
}
