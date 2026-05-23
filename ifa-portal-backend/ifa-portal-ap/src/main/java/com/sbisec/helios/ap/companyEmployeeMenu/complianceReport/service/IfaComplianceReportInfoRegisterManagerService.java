package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoResponse;
import com.sbisec.helios.ap.service.Service;

/**
 * 画面ID：SUB0505_01-01
 * 画面名：コンプライアンス通信情報登録（管理者用）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
public interface IfaComplianceReportInfoRegisterManagerService extends Service {

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA001DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA001DtoResponse
     * model リクエスト：IfaComplianceReportInfoRegisterManagerA001RequestModel
     * model レスポンス：IfaComplianceReportInfoRegisterManagerA001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse> initializeA001(IfaComplianceReportInfoRegisterManagerA001DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A003
     * アクション名：状態
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA003DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA003DtoResponse
     * model リクエスト：IfaComplianceReportInfoRegisterManagerA003RequestModel
     * model レスポンス：IfaComplianceReportInfoRegisterManagerA003ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse> stateA003(IfaComplianceReportInfoRegisterManagerA003DtoRequest dtoReq)
            throws Exception;
    
    /**
     * アクションID：A010
     * アクション名：削除
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA010DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA010DtoResponse
     * model リクエスト：IfaComplianceReportInfoRegisterManagerA010RequestModel
     * model レスポンス：IfaComplianceReportInfoRegisterManagerA010ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse> deleteA010(IfaComplianceReportInfoRegisterManagerA010DtoRequest dtoReq)
            throws Exception;
}
