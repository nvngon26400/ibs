package com.sbisec.helios.ap.testtool.service.impl;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl.IpopoBBApplyNewDaoImpl;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.impl.IpopoBBApplyUploadDaoImpl;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.model.IpopoBBApplyUploadModel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.compliance.dao.impl.ComplianceDaoImpl;
import com.sbisec.helios.ap.testtool.service.DaoTestDriverService;
import com.sbisec.helios.ap.testtool.service.dto.DaoTestServiceRequest;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql002Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql003Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql004Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql004Output;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql005Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql006Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql009Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql011Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql012Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql013Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSql014Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaBbApplyCsvMassRegisterSqlNumOutput;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql001Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql002Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql003Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql004Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql005Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql006Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql007Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql008Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSql009Input;
import com.sbisec.helios.ap.testtool.service.dto.IfaComplianceReportSqlNumOutput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * DAOテスト用のサービスの実装クラス
 *
 * @author 青山
 *
 */
@Component(value = "daoTestDriverService")
public class DaoTestDriverServiceImp implements DaoTestDriverService {
    
    @Autowired
    private ApplicationContext appContext;
    
    @Override
    public Object doDaoTest(DaoTestServiceRequest request) throws Exception {
        
        //DAOクラス名の取得
        String daoName = request.getTestClassName();
        //DAOクラス名のテスト対象メソッド名の取得
        String methodName = request.getTestMethodName();
        //DAOクラス名のテスト対象メソッド　に指定するパラメタDTO名の取得
        String paramDtoName = request.getParamDtoType();
        
        //DAOクラス名のテスト対象メソッド　に指定するパラメタDTOについて
        //設定するパラメタ名リストの取得
        List<String> paramNameList = request.getParamNameList();
        //DAOクラス名のテスト対象メソッド　に指定するパラメタDTOについて
        //設定するパラメタ値リストの取得
        List<String> paramTypeList = request.getParamTypeList();
        //DAOクラス名のテスト対象メソッド　に指定するパラメタDTOについて
        //設定するパラメタ値リストの取得
        List<Object> paramValueList = request.getParamValueList();
        
        //DAOクラスのクラス型の取得
        Class<?> daoClass = Class.forName(daoName);
        //DAOクラスのインスタンス生成
        Object daoInstance = IfaCommonUtil.getWebApplicationContext().getBean(daoClass);
        
        if (ObjectUtils.isEmpty(paramDtoName)) {
            Method testMethodObj = daoInstance.getClass().getMethod(methodName);
            return testMethodObj.invoke(daoInstance);
            
        }
        //DAOクラスのパラメタ（DTOクラス）型の取得
        //        Class<?> daoParamDtoClass = Class.forName("com.sbisec.helios.ap.common.dto.DaoTestRequest");
        Class<?> daoParamDtoClass = Class.forName(paramDtoName);
        
        //DAOクラスのパラメタ（DTOクラス）型からDTOインスタンスの作成
        Object daoParamDtoInstance = daoParamDtoClass.getDeclaredConstructor().newInstance();
        
        {
            //
            // DAOクラス名のテスト対象メソッド　に指定するパラメタDTOについて
            // setterメソッドを生成し、setter関数を呼び出す。
            //
            for (int index = 0; index < paramNameList.size(); index++) {
                String name = paramNameList.get(index);
                String type = paramTypeList.get(index);
                
                //引数型の作成
                Class<?> paramClass = null;
                if (type.equals("int")) {
                    paramClass = int.class;
                } else if (type.equals("boolean")) {
                    paramClass = boolean.class;
                } else {
                    paramClass = Class.forName(type);
                }
                
                //引数名の先頭一文字を大文字化　
                char[] firstUpperName = name.toCharArray();
                firstUpperName[0] = Character.toUpperCase(firstUpperName[0]);
                // "set" + 引数名（先頭一文字を大文字化） で setｔer関数名を作成
                String setMethodName = "set" + (new String(firstUpperName));
                
                // setterメソッドを生成
                Method setParamMethod = daoParamDtoClass.getMethod(setMethodName, paramClass);
                // setterメソッドの呼び出し
                if (type.equals("java.util.Date")) {
                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    Date paramDate = sdFormat.parse((String) paramValueList.get(index));
                    setParamMethod.invoke(daoParamDtoInstance, paramDate);
                } else {
                    setParamMethod.invoke(daoParamDtoInstance, paramValueList.get(index));
                }
            }
        }
        
        //DAOクラスのテスト対象メソッドクラスの取得
        Method testMethodObj = daoInstance.getClass().getMethod(methodName, daoParamDtoClass);
        
        //DAOクラスのテスト対象メソッドの呼び出し
        Object response = testMethodObj.invoke(daoInstance, daoParamDtoInstance);
        
        return response;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql002Test(IfaBbApplyCsvMassRegisterSql002Input input) throws Exception {
        
        IpopoBBApplyNewDaoImpl stub = appContext.getBean(IpopoBBApplyNewDaoImpl.class);
        return stub.getIpopoBBCustomerInfo(input.getButenCode(), input.getAccountNumber());
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql003Test(IfaBbApplyCsvMassRegisterSql003Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        return stub.getSectionInfo(input.getButenCode(), input.getAccountNumber());
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql004Test(IfaBbApplyCsvMassRegisterSql004Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        
        IfaBbApplyCsvMassRegisterSql004Output output = new IfaBbApplyCsvMassRegisterSql004Output();
        IpopoBBApplyUploadModel model = stub.getIpopoBBBrandInfo(input.getBrandCode());
        output.setBrandCode(model.getBrandCode());
        output.setBrandName(model.getBrandName());
        output.setBbGestureValue(model.getBbGestureValue());
        output.setBbPriceFrom(model.getBbPriceFrom());
        output.setBbPriceTo(model.getBbPriceTo());
        output.setBbPriceCut(model.getBbPriceCut());
        output.setBbDiscountFrom(model.getBbDiscountFrom());
        output.setBbDiscountTo(model.getBbDiscountTo());
        output.setBbDiscountCut(model.getBbDiscountCut());
        output.setBbStrikePrice(model.getBbStrikePrice());
        output.setBbStock(model.getBbStock());
        output.setMaxAllocationUnits(model.getMaxAllocationUnits());
        output.setPaymentDate(model.getPaymentDate());
        output.setBbSeq(model.getBbSeq());
        output.setBbInvestorAttName(model.getBbInvestorAttName());
        output.setBbIpoPoKbn(model.getBbIpoPoKbn());
        output.setBbUnitKbn(model.getBbUnitKbn());
        output.setBbUrgentStop(model.getBbUrgentStop());
        output.setEdelivOnlyFlag(model.getEdelivOnlyFlag());
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        if (!ObjectUtils.isEmpty(model.getBbPresentationFrom())) {
            output.setBbPresentationFrom(sdFormat.format(model.getBbPresentationFrom()));
        }
        if (!ObjectUtils.isEmpty(model.getBbPresentationTo())) {
            output.setBbPresentationTo(sdFormat.format(model.getBbPresentationTo()));
        }
        if (!ObjectUtils.isEmpty(model.getIfaBbPresentationFrom())) {
            output.setIfaBbPresentationFrom(sdFormat.format(model.getIfaBbPresentationFrom()));
        }
        if (!ObjectUtils.isEmpty(model.getIfaBbPresentationTo())) {
            output.setIfaBbPresentationTo(sdFormat.format(model.getIfaBbPresentationTo()));
        }
        
        return output;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql005Test(IfaBbApplyCsvMassRegisterSql005Input input) throws Exception {
        
        IpopoBBApplyNewDaoImpl stub = appContext.getBean(IpopoBBApplyNewDaoImpl.class);
        
        int num = stub.getBBAcceptInfoCount(input.getBbProductCode(), input.getBbPresentationFrom(),
                input.getButenCode(), input.getAccountNumber());
        IfaBbApplyCsvMassRegisterSqlNumOutput output = new IfaBbApplyCsvMassRegisterSqlNumOutput();
        output.setNum(num);
        return output;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql006Test(IfaBbApplyCsvMassRegisterSql006Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        return stub.getMaybeSairyouCount(input.getButenCode(), input.getAccountNumber());
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql009Test(IfaBbApplyCsvMassRegisterSql009Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        
        int num = stub.getUnSelectionCount(input.getButenCode(), input.getAccountNumber(), input.getBrandCode());
        IfaBbApplyCsvMassRegisterSqlNumOutput output = new IfaBbApplyCsvMassRegisterSqlNumOutput();
        output.setNum(num);
        return output;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql011Test(IfaBbApplyCsvMassRegisterSql011Input input) throws Exception {
        
        IpopoBBApplyNewDaoImpl stub = appContext.getBean(IpopoBBApplyNewDaoImpl.class);
        return stub.getBBInvestorAttInfoList(input.getBbProductCode(), input.getBbPresentationFrom());
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql012Test(IfaBbApplyCsvMassRegisterSql012Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        
        int num = stub.insertUploadIpopoBBApplyInfoToBBAccept(input.getInsertList());
        IfaBbApplyCsvMassRegisterSqlNumOutput output = new IfaBbApplyCsvMassRegisterSqlNumOutput();
        output.setNum(num);
        return output;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql013Test(IfaBbApplyCsvMassRegisterSql013Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        
        int num = stub.insertUploadIpopoBBApplyInfoToSrAccept(input.getInsertList());
        IfaBbApplyCsvMassRegisterSqlNumOutput output = new IfaBbApplyCsvMassRegisterSqlNumOutput();
        output.setNum(num);
        return output;
    }
    
    @Override
    public Object doIfaBbApplyCsvMassRegisterSql014Test(IfaBbApplyCsvMassRegisterSql014Input input) throws Exception {
        
        IpopoBBApplyUploadDaoImpl stub = appContext.getBean(IpopoBBApplyUploadDaoImpl.class);
        return stub.getEdelivAgreementInfo(input.getButenCode(), input.getAccountNumber());
    }
    
    @Override
    public Object doIfaComplianceReportSql001Test(IfaComplianceReportSql001Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.GetIdAndDirectory(input.getFuncId(), input.getCatId());
    }
    
    @Override
    public Object doIfaComplianceReportSql002Test(IfaComplianceReportSql002Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.getComplianceLetterListOfMustReadDates(input.getUserId(), input.getBrokerCode(),
                input.getReminderComplianceStartYm(), input.getCurrentMonth());
    }
    
    @Override
    public Object doIfaComplianceReportSql003Test(IfaComplianceReportSql003Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.getCommunicationDateforSelect(input.getFromDate(), input.getToDate());
    }
    
    @Override
    public Object doIfaComplianceReportSql004Test(IfaComplianceReportSql004Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.getCorComplianceCommunicationObject(input.getCorLecId());
    }
    
    @Override
    public Object doIfaComplianceReportSql005Test(IfaComplianceReportSql005Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.GetCorComplianceConfirmationObject(input.getCorLecId(), input.getCorUserId(),
                input.getBrokerCode());
    }
    
    @Override
    public Object doIfaComplianceReportSql006Test(IfaComplianceReportSql006Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        
        int count = 0;
        count = stub.InsertCorComplianceConfirmation(input.getCorLecId(), input.getCorUserId(),
                input.getCorConfirmationFlg(), input.getCorCreateBy(), input.getCorUpdateBy());
        
        IfaComplianceReportSqlNumOutput output = new IfaComplianceReportSqlNumOutput();
        output.setNum(count);
        return output;
    }
    
    @Override
    public Object doIfaComplianceReportSql007Test(IfaComplianceReportSql007Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        int count = 0;
        count = stub.updateCorComplianceConfirmation(input.getCorLecId(), input.getCorUserId(),
                input.getCorConfirmationFlg(), input.getCorUpdateBy());
        
        IfaComplianceReportSqlNumOutput output = new IfaComplianceReportSqlNumOutput();
        output.setNum(count);
        return output;
    }
    
    @Override
    public Object doIfaComplianceReportSql008Test(IfaComplianceReportSql008Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.countMissedComplianceForPreviousMonths(input.getUserId(), input.getBrokerCode(),
                input.getReminderComplianceStartYm(), input.getCurrentMonth());
    }
    
    @Override
    public Object doIfaComplianceReportSql009Test(IfaComplianceReportSql009Input input) throws Exception {
        
        ComplianceDaoImpl stub = appContext.getBean(ComplianceDaoImpl.class);
        return stub.countMissedComplianceForTargetMonth(input.getUserId(), input.getBrokerCode(), input.getYearMonth());
    }
    
}
