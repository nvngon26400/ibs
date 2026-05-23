package com.sbisec.helios.gw.testtool.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
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
import com.sbisec.helios.gw.common.controller.BaseController;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.testtool.dto.DaoTestServiceRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * postmanでのテスト用のコントローラ
 *
 * @author 青山
 *
 */
@RestController
@ScreenId(groupId = "COMMON", id = "Postman", ignoreCheck = true)
public class PostmanTestController extends BaseController {
    
    @Override
    
    /**
     *@inheritDoc
     * 
     *  */
    protected String getFirstViewName() {
        
        return null;
    }
    
    /**
     * POSTMAN　引数テスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/postman/driver")
    public Object postmanDriverTest(@RequestBody DaoTestServiceRequest posRequest, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doDaoTest", new TypeReference<Object>() {
        }, posRequest);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL002　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql002Test")
    public Object doIfaBbApplyCsvMassRegisterSql002Test(@RequestBody IfaBbApplyCsvMassRegisterSql002Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql002Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL003　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql003Test")
    public Object doIfaBbApplyCsvMassRegisterSql003Test(@RequestBody IfaBbApplyCsvMassRegisterSql003Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql003Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL004　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql004Test")
    public Object doIfaBbApplyCsvMassRegisterSql004Test(@RequestBody IfaBbApplyCsvMassRegisterSql004Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql004Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL005　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql005Test")
    public Object doIfaBbApplyCsvMassRegisterSql005Test(@RequestBody IfaBbApplyCsvMassRegisterSql005Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql005Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL006 SQL007 SQL008　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql006Test")
    public Object doIfaBbApplyCsvMassRegisterSql006Test(@RequestBody IfaBbApplyCsvMassRegisterSql006Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql006Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL009　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql009Test")
    public Object doIfaBbApplyCsvMassRegisterSql009Test(@RequestBody IfaBbApplyCsvMassRegisterSql009Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql009Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL0011　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql011Test")
    public Object doIfaBbApplyCsvMassRegisterSql011Test(@RequestBody IfaBbApplyCsvMassRegisterSql011Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql011Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL0012　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql012Test")
    public Object doIfaBbApplyCsvMassRegisterSql012Test(@RequestBody IfaBbApplyCsvMassRegisterSql012Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql012Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL0013 SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql013Test")
    public Object doIfaBbApplyCsvMassRegisterSql013Test(@RequestBody IfaBbApplyCsvMassRegisterSql013Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql013Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * BB申込(一括登録)SQL0014　SQLテスト用メソッド。
     * @param posRequest
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaBbApplyCsvMassRegisterSql014Test")
    public Object doIfaBbApplyCsvMassRegisterSql014Test(@RequestBody IfaBbApplyCsvMassRegisterSql014Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaBbApplyCsvMassRegisterSql014Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL001　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql001Test")
    public Object doIfaComplianceReportSql001Test(@RequestBody IfaComplianceReportSql001Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql001Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL002　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql002Test")
    public Object doIfaComplianceReportSql002Test(@RequestBody IfaComplianceReportSql002Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql002Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL003　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql003Test")
    public Object doIfaComplianceReportSql003Test(@RequestBody IfaComplianceReportSql003Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql003Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL004　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql004Test")
    public Object doIfaComplianceReportSql004Test(@RequestBody IfaComplianceReportSql004Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql004Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL005　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql005Test")
    public Object doIfaComplianceReportSql005Test(@RequestBody IfaComplianceReportSql005Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql005Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL006　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql006Test")
    public Object doIfaComplianceReportSql006Test(@RequestBody IfaComplianceReportSql006Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql006Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL007　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql007Test")
    public Object doIfaComplianceReportSql007Test(@RequestBody IfaComplianceReportSql007Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql007Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL008　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql008Test")
    public Object doIfaComplianceReportSql008Test(@RequestBody IfaComplianceReportSql008Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql008Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
    /**
     * コンプライアンス通信 SQL009　SQLテスト用メソッド。
     * @param input
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/postman/ifaComplianceReportSql009Test")
    public Object doIfaComplianceReportSql009Test(@RequestBody IfaComplianceReportSql009Input input, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        IfaGwCommonUtil.setCustomerCommonToRequestScope();
        // Daoテスト用サービスの呼び出し
        Object respons = ApiRequestUtil.invoke("daoTestDriverService", "doIfaComplianceReportSql009Test", new TypeReference<Object>() {
        }, input);
        
        return respons;
    }
    
}
