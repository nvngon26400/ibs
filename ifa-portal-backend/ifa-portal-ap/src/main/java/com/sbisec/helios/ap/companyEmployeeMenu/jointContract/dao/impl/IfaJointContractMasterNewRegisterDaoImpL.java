package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.IfaJointContractMasterNewRegisterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.mapper.IfaJointContractMasterNewRegisterMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterNewRegisterSql003RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0513_01-02
 * 画面名：共同募集契約マスタ登録
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Component
public class IfaJointContractMasterNewRegisterDaoImpL extends RowSelectableDao implements IfaJointContractMasterNewRegisterDao {
    
    @Autowired
    private IfaJointContractMasterNewRegisterMapper mapper;
    
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
    public DataList<IfaJointContractMasterNewRegisterSql001ResponseModel> selectIfaJointContractMasterNewRegisterSql001(
            IfaJointContractMasterNewRegisterSql001RequestModel req) throws Exception {
        
        var res = new DataList<IfaJointContractMasterNewRegisterSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJointContractMasterNewRegisterSql001(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：共同募集契約先仲介業者登録
     * SQLタイプ：insert/update
     * リクエストクラス：IfaJointContractMasterNewRegisterSql004RequestModel
     * 
     * @param req リクエスト
     * @return 更新結果
     * @exception Exception SQLExceptionなど
     */
    public int updateIfaJointContractMasterNewRegisterSql002(
            IfaJointContractMasterNewRegisterSql002RequestModel req) throws Exception {

        return mapper.updateIfaJointContractMasterNewRegisterSql002(req);
    }

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
    public int selectIfaJointContractMasterNewRegisterSql003(IfaJointContractMasterNewRegisterSql003RequestModel req)
            throws Exception {

        return mapper.selectIfaJointContractMasterNewRegisterSql003(req);
    }
    
}
