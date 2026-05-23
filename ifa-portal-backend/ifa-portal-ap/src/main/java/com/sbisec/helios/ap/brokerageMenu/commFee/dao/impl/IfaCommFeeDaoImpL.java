package com.sbisec.helios.ap.brokerageMenu.commFee.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaCommFeeDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper.IfaCommFeeMapper;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql001To012ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaCommFeeSql015ResponseModel;


/**
 * 画面ID：SUB020502-01
 * 画面名：手数料・報酬
 * 2024/05/31 新規作成
 *
 * @author SCSK 江口
 */
@Component
public class IfaCommFeeDaoImpL extends RowSelectableDao implements IfaCommFeeDao {
    
    @Autowired
    private IfaCommFeeMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：手数料（日次-仲介業者毎-集計）一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql001To012RequestModel
     * レスポンスクラス：IfaCommFeeSql001To012ResponseModel
     *
     * @param req パラメータ
     * @return 手数料（日次-仲介業者毎-集計）一覧
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaCommFeeSql001To012ResponseModel> selectIfaCommFeeSql001To012(
            IfaCommFeeSql001To012RequestModel req
    ) throws Exception {
        
        DataList<IfaCommFeeSql001To012ResponseModel> res = new DataList<IfaCommFeeSql001To012ResponseModel>();
        
        res.setDataList(mapper.selectIfaCommFeeSql001To012(req));
        return res;
    }
    
    /**
     * SQLID：Sql015
     * SQL名：SBIラップ管理報酬サービス表示制御情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaCommFeeSql015RequestModel
     * レスポンスクラス：IfaCommFeeSql015ResponseModel
     *
     * @param req パラメータ
     * @return SBIラップ管理報酬サービス表示制御情報
     * @exception Exception システムエラー
     */
    @Override
    public DataList<IfaCommFeeSql015ResponseModel> selectIfaCommFeeSql015(
            IfaCommFeeSql015RequestModel req
    ) throws Exception {
        DataList<IfaCommFeeSql015ResponseModel> res = new DataList<IfaCommFeeSql015ResponseModel>();
        
        res.setDataList(mapper.selectIfaCommFeeSql015(req));
        return res;
    }
    
}
