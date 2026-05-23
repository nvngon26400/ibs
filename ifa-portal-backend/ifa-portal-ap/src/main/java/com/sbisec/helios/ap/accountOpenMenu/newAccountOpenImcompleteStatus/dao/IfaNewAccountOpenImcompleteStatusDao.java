package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001ResponseModel;




/**
 * 画面ID：SUB020305-01.
 * 画面名：新規口座開設不備状況

 * @author 富永侑希子
    2023/10/27 新規作成
 *
 */
public interface IfaNewAccountOpenImcompleteStatusDao {
    
    /**
     * SQLID：Sql001.
     * SQL名：新規口座開設不備状況取得
     * SQLタイプ：select
     * リクエストクラス：IfaNewAccountOpenImcompleteStatusSql001RequestModel
     * レスポンスクラス：IfaNewAccountOpenImcompleteStatusSql001ResponseModel

     * @param req リクエスト
     * @return IfaNewAccountOpenImcompleteStatusSql001ResponseModel_newAccountOpenImcompleteStatus 
               レスポンス
     * @exception Exception SQLExceptionなど
     */
    public DataList
            <IfaNewAccountOpenImcompleteStatusSql001ResponseModel> 
            selectIfaNewAccountOpenImcompleteStatusSql001(
                  IfaNewAccountOpenImcompleteStatusSql001RequestModel req)
            throws Exception;
  
}
