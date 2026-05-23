package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001ResponseModel;


/**
 * 画面ID：SUB020305-01
 * 画面名：新規口座開設不備状況

 * @author 富永侑希子
　　　　2023/10/27 新規作成
 */
@Mapper
public interface IfaNewAccountOpenImcompleteStatusMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：新規口座開設不備状況取得
     * SQLタイプ：select
     * リクエストクラス：IfaNewAccountOpenImcompleteStatusSql001RequestModel
     * レスポンスクラス：IfaNewAccountOpenImcompleteStatusSql001ResponseModel

     * @param req リクエスト
     * @return IfaNewAccountOpenImcompleteStatusSql001ResponseModel_newAccountOpenImcompleteStatus
     * @exception Exception SQLExceptionなど
     */
    public List<IfaNewAccountOpenImcompleteStatusSql001ResponseModel> selectIfaNewAccountOpenImcompleteStatusSql001(
            @Param("req") IfaNewAccountOpenImcompleteStatusSql001RequestModel req
            ) throws Exception;   
}
