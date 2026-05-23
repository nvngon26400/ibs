package com.sbisec.helios.ap.brokerageMenu.commFee.dao.impl;

import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaRepCustomerCommListDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.mapper.IfaRepCustomerCommListMapper;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaRepCustomerCommListSql002ResponseModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020501-01
 * 画面名：担当顧客別手数料一覧
 * 2024/06/10 新規作成
 *
 * @author 宇田川達弥
 */
@Component
public class IfaRepCustomerCommListDaoImpl extends RowSelectableDao implements IfaRepCustomerCommListDao {
    
    @Autowired
    private IfaRepCustomerCommListMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：担当顧客別手数料一覧取得
     * SQLタイプ：select
     * リクエストクラス：IfaRepCustomerCommListSql001RequestModel
     * レスポンスクラス：IfaRepCustomerCommListSql001ResponseModel
     *
     * @param req リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public List<IfaRepCustomerCommListSql001ResponseModel> selectIfaRepCustomerCommListSql001(
            IfaRepCustomerCommListSql001RequestModel req) throws Exception {
        
        return mapper.selectIfaRepCustomerCommListSql001(req);
    }
    
    /**
     * SQLID：Sql002
     * SQL名：現株ポイント参照可能仲介業者取得
     * SQLタイプ：select
     * レスポンスクラス：IfaRepCustomerCommListSql002ResponseModel
     *
     * @return res 現株ポイント参照可能仲介業者
     * @exception exception システムエラー
     */
    public List<IfaRepCustomerCommListSql002ResponseModel> selectIfaRepCustomerCommListSql002() throws Exception {
        
        return mapper.selectIfaRepCustomerCommListSql002();
    }
    
    /**
     * SQLID：Sql003
     * SQL名：担当顧客別手数料コメント取得
     * SQLタイプ：select
     *
     * @return res 担当顧客別手数料コメント
     * @exception exception システムエラー
     */
    public String selectIfaRepCustomerCommListSql003() throws Exception {
        
        return mapper.selectIfaRepCustomerCommListSql003();
    }
    
}
