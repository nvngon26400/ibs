package com.sbisec.helios.ap.bizcommon.component;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.dao.Fct030Dao;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql001ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql002ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql003ResponseModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004RequestModel;
import com.sbisec.helios.ap.bizcommon.dao.model.Fct030Sql004ResponseModel;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * 共通関数：FCT030
 * 仲介業者コード営業員リスト取得
 * @author　SCSK
 */

@Component
public class Fct030 {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Fct032.class);
    
    @Autowired
    private Fct030Dao dao;
    
    /** SBI証券支店*/
    private static final String SBI_BRANCH = "2";
    
    /** 仲介業者　-　内部管理責任者*/
    private static final String INTERNAL_ADMINISTRATOR = "3";
    
    /** 仲介業者　-　営業責任者*/
    private static final String RESPONSIBILITY = "4";
    
    /** 仲介業者　-　外務員*/
    private static final String FOREIGN_AFFAIRSMEMBER = "5";
    
    /** 仲介業者 - 支店 - 内部管理責任者*/
    private static final String BRANCH_INTERNALADMINISTRATOR = "6";
    
    /** 仲介業者 - 支店 - 営業責任者*/
    private static final String BRANCH_RESPONSIBILITY = "7";
    
    /** 仲介業者 - 支店 - 外務員*/
    private static final String BRANCH_FOREIGNAFFAIRSMEMBER = "8";
    
    /** 営業員担当*/
    private static final String SALESMAN = "9";
    
    /** 権限コードがNullの際に代替する値*/
    private static final String BLANK = " ";
    
    /**
     * FCT030　仲介業者コード営業員リスト取得
     * @param input リクエスト
     * @return outputFct030Dto
     */
    public OutputFct030Dto getData(InputFct030Dto input) {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Fct030.getData");
        OutputFct030Dto response = new OutputFct030Dto();
        List<BrokerCharge> brokerChargeList = new ArrayList<>();
        BrokerCharge brokerCharge = null;
        //顧客共通情報
        //ユーザ共通情報
        UserAccount cc = IfaCommonUtil.getUserAccount();
        try {
            //SBI支店コード
            String branchCode = cc.getBranchCode();
            //仲介業者コード
            String brokerCode = cc.getBrokerCode();
            //権限コード
            String privId = cc.getPrivId();
            //仲介業者支店コード
            String subBrokerCode = cc.getSubBrokerCode();
            //ユーザ共通情報.ユーザID
            String userId = cc.getUserId();
            if (StringUtil.isNullOrEmpty(privId)) {
                privId = BLANK;
            }
            switch (privId) {
            case (SBI_BRANCH):
                DataList<Fct030Sql001ResponseModel> sql001Res = getSbiCode(branchCode, privId);
                for (Fct030Sql001ResponseModel res : sql001Res.getDataList()) {
                    brokerCharge = setSqlResponse(res.getBrokerCode(), res.getBrokerChargeCode());
                    brokerChargeList.add(brokerCharge);
                }
                break;
            case (BRANCH_INTERNALADMINISTRATOR):
            case (BRANCH_RESPONSIBILITY):
            case (BRANCH_FOREIGNAFFAIRSMEMBER):
                DataList<Fct030Sql002ResponseModel> sql002Res = getBranchCode(brokerCode, privId, subBrokerCode,
                        userId);
                for (Fct030Sql002ResponseModel res : sql002Res.getDataList()) {
                    brokerCharge = setSqlResponse(res.getBrokerCode(), res.getBrokerChargeCode());
                    brokerChargeList.add(brokerCharge);
                }
                break;
            case (INTERNAL_ADMINISTRATOR):
            case (RESPONSIBILITY):
            case (FOREIGN_AFFAIRSMEMBER):
                DataList<Fct030Sql003ResponseModel> sql003Res = getResponsibilityCode(brokerCode, privId, userId);
                for (Fct030Sql003ResponseModel res : sql003Res.getDataList()) {
                    brokerCharge = setSqlResponse(res.getBrokerCode(), res.getBrokerChargeCode());
                    brokerChargeList.add(brokerCharge);
                }
                break;
            case (SALESMAN):
                DataList<Fct030Sql004ResponseModel> sql004Res = getSalesmanCode(brokerCode, privId, subBrokerCode,
                        userId);
                for (Fct030Sql004ResponseModel res : sql004Res.getDataList()) {
                    brokerCharge = setSqlResponse(res.getBrokerCode(), res.getBrokerChargeCode());
                    brokerChargeList.add(brokerCharge);
                }
                break;
            default:
                response.setReturnCode(OutputFct030Dto.RETURN_CODE_E001);
            }
        } catch (Exception e) {
            LOGGER.error("Fct030.doCheck Exception[{}]", e.getMessage());
            e.printStackTrace();
        }
        
        //SQLで取得した項目をセット
        response.setBrokerChargeList(brokerChargeList);
        return response;
    }
    
    /**
     * 仲介業者コードと営業員コードを設定した仲介業者営業員リストを作成
     * @param brokerCode 仲介業者コード
     * @param brorkerChargeCode 営業員コード
     * @return BC　仲介業者営業員リスト
     */
    private BrokerCharge setSqlResponse(String brokerCode, String brorkerChargeCode) {
        
        BrokerCharge brokerCharge = new BrokerCharge();
        brokerCharge.setBrokerCode(brokerCode);
        brokerCharge.setEmpCode(brorkerChargeCode);
        return brokerCharge;
    }
    
    /**
     * SQL001を発行する
     * @param branchCode ユーザ共通情報.SBI証券支店コード
     * @param privId ユーザ共通情報.権限コード
     * @return dao.selectFct030Sql001(sql001Req)
     */
    private DataList<Fct030Sql001ResponseModel> getSbiCode(String branchCode, String privId) throws Exception {
        
        Fct030Sql001RequestModel sql001Req = new Fct030Sql001RequestModel();
        sql001Req.setBranchCode(branchCode);
        sql001Req.setPrivId(privId);
        return dao.selectFct030Sql001(sql001Req);
    }
    
    /**
     * SQL002を発行する
     * @param privId ユーザ共通情報.権限コード
     * @param userId ユーザ共通情報.ユーザID
     * @param brokerCode ユーザ共通情報.仲介業者コード
     * @param subBrokerCode ユーザ共通情報.仲介業者支店コード
     * @return dao.selectFct030Sql002(sql001Req)　
     */
    private DataList<Fct030Sql002ResponseModel> getBranchCode(String brokerCode, String privId, String subBrokerCode,
            String userId) throws Exception {
        
        Fct030Sql002RequestModel sql002Req = new Fct030Sql002RequestModel();
        sql002Req.setBrokerCode(brokerCode);
        sql002Req.setPrivId(privId);
        sql002Req.setSubBrokerCode(subBrokerCode);
        sql002Req.setUserId(userId);
        return dao.selectFct030Sql002(sql002Req);
    }
    
    /**
     * SQL003を発行する
     * @param privId ユーザ共通情報.権限コード
     * @param userId ユーザ共通情報.ユーザID
     * @param brokerCode ユーザ共通情報.仲介業者コード
     * @return dao.selectFct030Sql003(sql001Req)
     */
    private DataList<Fct030Sql003ResponseModel> getResponsibilityCode(String brokerCode, String privId, String userId)
            throws Exception {
        
        Fct030Sql003RequestModel sql003Req = new Fct030Sql003RequestModel();
        sql003Req.setBrokerCode(brokerCode);
        sql003Req.setPrivId(privId);
        sql003Req.setUserId(userId);
        return dao.selectFct030Sql003(sql003Req);
    }
    
    /**
     * SQL004を発行する
     * @param privId ユーザ共通情報.権限コード
     * @param userId ユーザ共通情報.ユーザID
     * @param brokerCode ユーザ共通情報.仲介業者コード
     * @param subBrokerCode ユーザ共通情報.仲介業者支店コード
     * @return dao.selectFct030Sql004(sql001Req)　
     */
    private DataList<Fct030Sql004ResponseModel> getSalesmanCode(String brokerCode, String privId, String subBrokerCode,
            String userId) throws Exception {
        
        Fct030Sql004RequestModel sql004Req = new Fct030Sql004RequestModel();
        sql004Req.setBrokerCode(brokerCode);
        sql004Req.setPrivId(privId);
        sql004Req.setSubBrokerCode(subBrokerCode);
        sql004Req.setUserId(userId);
        return dao.selectFct030Sql004(sql004Req);
    }
}
