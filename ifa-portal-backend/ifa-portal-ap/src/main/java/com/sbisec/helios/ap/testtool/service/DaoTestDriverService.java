package com.sbisec.helios.ap.testtool.service;

import com.sbisec.helios.ap.service.Service;
import com.sbisec.helios.ap.testtool.service.dto.DaoTestServiceRequest;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql002Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql003Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql004Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql005Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql006Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql009Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql011Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql012Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql013Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql014Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql001Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql002Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql003Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql004Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql005Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql006Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql007Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql008Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql009Input;

/**
 * DAO　テスト用サービス
 *
 * @author 青山
 */
public interface DaoTestDriverService extends Service {
    
    public Object doDaoTest(DaoTestServiceRequest request) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql002Test(IfaBbApplyCsvMassRegisterSql002Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql003Test(IfaBbApplyCsvMassRegisterSql003Input input) throws Exception;

    public Object doIfaBbApplyCsvMassRegisterSql004Test(IfaBbApplyCsvMassRegisterSql004Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql005Test(IfaBbApplyCsvMassRegisterSql005Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql006Test(IfaBbApplyCsvMassRegisterSql006Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql009Test(IfaBbApplyCsvMassRegisterSql009Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql011Test(IfaBbApplyCsvMassRegisterSql011Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql012Test(IfaBbApplyCsvMassRegisterSql012Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql013Test(IfaBbApplyCsvMassRegisterSql013Input input) throws Exception;
    
    public Object doIfaBbApplyCsvMassRegisterSql014Test(IfaBbApplyCsvMassRegisterSql014Input input) throws Exception;
    
    public Object doIfaComplianceReportSql001Test(IfaComplianceReportSql001Input input) throws Exception;
    
    public Object doIfaComplianceReportSql002Test(IfaComplianceReportSql002Input input) throws Exception;
    
    public Object doIfaComplianceReportSql003Test(IfaComplianceReportSql003Input input) throws Exception;
    
    public Object doIfaComplianceReportSql004Test(IfaComplianceReportSql004Input input) throws Exception;
    
    public Object doIfaComplianceReportSql005Test(IfaComplianceReportSql005Input input) throws Exception;
    
    public Object doIfaComplianceReportSql006Test(IfaComplianceReportSql006Input input) throws Exception;
    
    public Object doIfaComplianceReportSql007Test(IfaComplianceReportSql007Input input) throws Exception;
    
    public Object doIfaComplianceReportSql008Test(IfaComplianceReportSql008Input input) throws Exception;
    
    public Object doIfaComplianceReportSql009Test(IfaComplianceReportSql009Input input) throws Exception;
}
