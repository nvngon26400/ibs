package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql003RequestModel;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
public interface IfaJointContractMasterNewRegisterDao {

    /**
     * SQLID：Sql001
     * SQL名：共同募集契約マスタ情報の検索
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterNewRegisterSql001RequestModel
     * レスポンスクラス：IfaJointContractMasterNewRegisterSql001ResponseModel
     *
     * @return 共同募集契約マスタ
     * @exception Exception システムエラー
     */
    public DataList<IfaJointContractMasterNewRegisterSql001ResponseModel> selectIfaJointContractMasterNewRegisterSql001(
            IfaJointContractMasterNewRegisterSql001RequestModel req) throws Exception;
    
    
    /**
     * SQLID：Sql002
     * SQL名：共同募集契約先仲介業者削除
     * SQLタイプ：insert/update
     * リクエストクラス：IfaJointContractMasterNewRegisterSql002RequestModel
     * 
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointContractMasterNewRegisterSql002(
            IfaJointContractMasterNewRegisterSql002RequestModel req) throws Exception;
    
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
            IfaJointContractMasterNewRegisterSql003RequestModel req) throws Exception;
}
