package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel;





/**
 * 
 * 画面ID：SUB020302_0101-03
 * 画面名：コンプラ項目詳細
 *
 * @author <author-name>
 2024/06/20 新規作成
 */
@Mapper
public interface IfaDomesticMutualFundOrderOtherInfoMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：国内投信注文その他情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaDomesticMutualFundOrderOtherInfoSql001RequestModel
     * レスポンスクラス：IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel
	 *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaDomesticMutualFundOrderOtherInfoSql001ResponseModel> selectIfaDomesticMutualFundOrderOtherInfoSql001(
        @Param("req") IfaDomesticMutualFundOrderOtherInfoSql001RequestModel req
        ) throws Exception;
    
    
    
    
}
