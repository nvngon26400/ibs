package com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.IfaJointContractMasterDao;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.mapper.IfaJointContractMasterMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.jointContract.dao.model.IfaJointContractMasterSql004RequestModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0513_01-01
 * 画面名：共同募集契約マスタ
 * 2025/11/25 新規作成
 *
 * @author 大連　苗萌
 * 
 */
@Component
public class IfaJointContractMasterDaoImpL extends RowSelectableDao implements IfaJointContractMasterDao {
    
    @Autowired
    private IfaJointContractMasterMapper mapper;
    
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
    public DataList<IfaJointContractMasterSql001ResponseModel> selectIfaJointContractMasterSql001(
            IfaJointContractMasterSql001RequestModel req) throws Exception {
        
        var res = new DataList<IfaJointContractMasterSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaJointContractMasterSql001(req));
        
        return res;
    }
    
    /**
     * SQLID：Sql002
     * SQL名：共同募集契約先仲介業者取得
     * SQLタイプ：select
     * リクエストクラス：IfaJointContractMasterSql002RequestModel
     * レスポンスクラス：契約状態
     *
     * @return 契約状態
     * @exception Exception システムエラー
     */
    public String selectIfaJointContractMasterSql002(
            IfaJointContractMasterSql002RequestModel req) throws Exception {

        return mapper.selectIfaJointContractMasterSql002(req);
    }
    
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
            IfaJointContractMasterSql003RequestModel req) throws Exception {

        return mapper.updateIfaJointContractMasterSql003(req);
    }

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
            IfaJointContractMasterSql004RequestModel req) throws Exception {

        return mapper.updateIfaJointContractMasterSql004(req);
    }
    
}
