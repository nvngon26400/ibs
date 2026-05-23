package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.IfaComplianceReportBrokerBlockViewExcludeSettingDao;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.mapper.IfaComplianceReportBrokerBlockViewExcludeSettingMapper;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel;

/**
 * 画面ID：SUB0505_03-01
 * 画面名：コンプライアンス通信仲介業者一括閲覧不要設定
 * 2023/11/08 新規作成
 *
 * @author SCSK 江口
 *
 */
@Component
public class IfaComplianceReportBrokerBlockViewExcludeSettingDaoImpL extends RowSelectableDao
        implements IfaComplianceReportBrokerBlockViewExcludeSettingDao {
    
    @Autowired
    private IfaComplianceReportBrokerBlockViewExcludeSettingMapper mapper;
    
    /**
     * SQLID：Sql001
     * SQL名：コンプライアンス通信の閲覧要否管理情報取得
     * SQLタイプ：select
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel
     *
     * @param req リクエストパラメータ
     * @return コンプライアンス通信要否管理情報一覧
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql001RequestModel req) throws Exception {
        
        // 結果の格納先を準備
        DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> res
                = new DataList<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel>();
        List<IfaComplianceReportBrokerBlockViewExcludeSettingSql001ResponseModel> dataList = res.getDataList();
        
        // SQLを実行し結果を格納
        mapper.selectIfaComplianceReportBrokerBlockViewExcludeSettingSql001(req).forEach(record -> {
            dataList.add(record);
        });
        
        return res;
        
    }
    
    /**
     * SQLID：Sql002
     * SQL名：コンプライアンス通信の閲覧要否管理情報登録
     * SQLタイプ：insert
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql002ResponseModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int insertIfaComplianceReportBrokerBlockViewExcludeSettingSql002(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql002RequestModel req) throws Exception {
        
        return mapper.insertIfaComplianceReportBrokerBlockViewExcludeSettingSql002(req);
        
    }
    
    /**
     * SQLID：Sql003
     * SQL名：コンプライアンス通信の閲覧要否管理情報削除
     * SQLタイプ：delete
     * リクエストクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel
     * レスポンスクラス：IfaComplianceReportBrokerBlockViewExcludeSettingSql003ResponseModel
     *
     * @param req 登録するコンプライアンス通信閲覧要否情報
     * @return SUCCESS:0以上、FAIL：0
     * @exception Exception SQLExceptionなど
     */
    public int deleteIfaComplianceReportBrokerBlockViewExcludeSettingSql003(
            IfaComplianceReportBrokerBlockViewExcludeSettingSql003RequestModel req) throws Exception {
        
        return mapper.deleteIfaComplianceReportBrokerBlockViewExcludeSettingSql003(req);
        
    }
    
}
