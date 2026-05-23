package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectBlotterConfirmManagerDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.mapper.IfaSelfInspectBlotterConfirmManagerMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
@Component
public class IfaSelfInspectBlotterConfirmManagerDaoImpl extends RowSelectableDao
        implements IfaSelfInspectBlotterConfirmManagerDao {
    
    @Autowired
    private IfaSelfInspectBlotterConfirmManagerMapper mapper;
    
    /**
     * SQLID：Sql002
     * SQL名：自己点検記録簿情報取得
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerSql002ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql002(
            IfaSelfInspectBlotterConfirmManagerSql002RequestModel req) throws Exception {
        
        var res = new DataList<IfaSelfInspectBlotterConfirmManagerSql002ResponseModel>();
        
        res.setDataList(mapper.selectIfaSelfInspectBlotterConfirmManagerSql002(req));
        return res;
    }
    
    /**
     * SQLID：Sql001
     * SQL名：登録年月dropDownLisTデータ取得
     *
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterConfirmManagerSql001ResponseModel> selectIfaSelfInspectBlotterConfirmManagerSql001()
            throws Exception {
        
        var res = new DataList<IfaSelfInspectBlotterConfirmManagerSql001ResponseModel>();
        res.setDataList(mapper.selectIfaSelfInspectBlotterConfirmManagerSql001());
        return res;
    }
    
}
