package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql004RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Mapper
public interface IfaJointContractMasterMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：共同募集契約マスタ情報の検索
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterSql001RequestModel
     * レスポンスクラス：IfaJointContractMasterSql001ResponseModel
     *
     * @return 共同募集契約マスタ
     * @exception Exception システムエラー
     */
    public List<IfaJointContractMasterSql001ResponseModel> selectIfaJointContractMasterSql001(
            @Param("req") IfaJointContractMasterSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：共同募集契約先仲介業者取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterSql002RequestModel
     *
     * @return 契約状態
     * @exception Exception システムエラー
     */
    public String selectIfaJointContractMasterSql002(
            @Param("req") IfaJointContractMasterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：共同募集契約先仲介業者変更
     * SQLタイプ：update
     * リクエストクラス：IfaJointContractMasterSql003RequestModel
     * 
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointContractMasterSql003(
            @Param("req") IfaJointContractMasterSql003RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql004
     * SQL名：共同募集契約先仲介業者削除
     * SQLタイプ：update
     * リクエストクラス：IfaJointContractMasterSql004RequestModel
     * 
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointContractMasterSql004(
            @Param("req") IfaJointContractMasterSql004RequestModel req) throws Exception;
    
}
