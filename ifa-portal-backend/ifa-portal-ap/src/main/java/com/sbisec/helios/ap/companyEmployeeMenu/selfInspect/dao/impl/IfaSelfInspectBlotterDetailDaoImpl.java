package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.impl;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectBlotterDetailDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.mapper.IfaSelfInspectBlotterDetailMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細
 *
 * @author SCSK
 2024/06/12 新規作成
 */
@Component
public class IfaSelfInspectBlotterDetailDaoImpl extends RowSelectableDao implements IfaSelfInspectBlotterDetailDao {
    
    @Autowired
    private IfaSelfInspectBlotterDetailMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：自己点検記録簿情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaSelfInspectBlotterDetailSql001RequestModel
     * レスポンスクラス：IfaSelfInspectBlotterDetailSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterDetailSql001ResponseModel> selectIfaSelfInspectBlotterDetailSql001(
            IfaSelfInspectBlotterDetailSql001RequestModel req) throws Exception {
        
        var res = new DataList<IfaSelfInspectBlotterDetailSql001ResponseModel>();
        
        res.setDataList(mapper.selectIfaSelfInspectBlotterDetailSql001(req));
        
        return res;
    }
    
}
