package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.mapper;

import java.util.List;

import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql003RequestModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Mapper
public interface IfaJointContractMasterNewRegisterMapper {
    
    /**
     * SQLID：Sql001
     * SQL名：契約先仲介業者の存在チェックを行う。
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterNewRegisterSql001RequestModel
     * レスポンスクラス：IfaJointContractMasterNewRegisterSql001ResponseModel
     *
     * @return 契約先仲介業者
     * @exception Exception システムエラー
     */
    public List<IfaJointContractMasterNewRegisterSql001ResponseModel> selectIfaJointContractMasterNewRegisterSql001(
            @Param("req") IfaJointContractMasterNewRegisterSql001RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql002
     * SQL名：共同募集契約先仲介業者登録
     * SQLタイプ：insert/update
     * リクエストクラス：IfaJointContractMasterNewRegisterSql002RequestModel
     * 
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointContractMasterNewRegisterSql002(
            @Param("req") IfaJointContractMasterNewRegisterSql002RequestModel req) throws Exception;
    
    /**
     * SQLID：Sql003
     * SQL名：集計グループマスタの仲介業者コードの存在チェックを行う。
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterNewRegisterSql003RequestModel
     * 
     * @param req リクエスト
     * @return 検索件数
     * @exception Exception SQLExceptionなど
     */
    public int selectIfaJointContractMasterNewRegisterSql003(
            @Param("req") IfaJointContractMasterNewRegisterSql003RequestModel req) throws Exception;
    
}
