package com.sbisec.helios.ap.admin.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.admin.dao.AdminDao;
import com.sbisec.helios.ap.admin.dao.mapper.AdminMapper;

@Component
public class AdminDaoImpl extends RowSelectableDao implements AdminDao {

        private static final Logger logger = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Autowired
	private AdminMapper mapper;
    //    @Autowired
    //private BrokerMapper brokermapper;


//    public DataList<GetTradeHistoryByCodeModel> GetTradeHistoryByCode(String CODE_TYPE,String CODE_1,String CODE_2
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetTradeHistoryByCode: CODE_TYPE,CODE_1,CODE_2");
//
//        return GetTradeHistoryByCodeInternal(CODE_TYPE,CODE_1,CODE_2);
//    }
//
//    private DataList<GetTradeHistoryByCodeModel> GetTradeHistoryByCodeInternal(String CODE_TYPE,String CODE_1,String CODE_2) throws Exception {
//	    CODE_TYPE = StringUtil.emptyToNull(CODE_TYPE);
//		CODE_1 = StringUtil.emptyToNull(CODE_1);
//		CODE_2 = StringUtil.emptyToNull(CODE_2);
//
//        DataList<GetTradeHistoryByCodeModel> dataList = new DataList<GetTradeHistoryByCodeModel>();
//        dataList.setDataList(mapper.GetTradeHistoryByCode(CODE_TYPE,CODE_1,CODE_2));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetAllTradeHistoryListModel> getAllTradeHistoryList(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String noProdutCd,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String empCode, String branchCode, String privId, List<String> chargeCodeList, boolean unlimit, String chkBrokerCodeExclude, List<String> brokerCodeExcludeList, List<String> customerAttributeList, String sessionId, String userId , String pattern
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.getAllTradeHistoryList: BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,noProdutCd,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE,empCode,branchCode,privId,chargeCodeList,unlimit,chkBrokerCodeExclude,brokerCodeExcludeList,customerAttributeList,sessionId,userId,pattern");
//
//        return getAllTradeHistoryListInternal(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,noProdutCd,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, empCode, branchCode, privId, chargeCodeList, unlimit, chkBrokerCodeExclude, brokerCodeExcludeList, customerAttributeList, sessionId, userId, pattern);
//    }
//
//    private DataList<GetAllTradeHistoryListModel> getAllTradeHistoryListInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String noProdutCd,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String empCode, String branchCode, String privId, List<String> chargeCodeList, boolean unlimit, String chkBrokerCodeExclude, List<String> brokerCodeExcludeList, List<String> customerAttributeList, String sessionId, String userId , String pattern) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		noProdutCd = StringUtil.emptyToNull(noProdutCd);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		empCode = StringUtil.emptyToNull(empCode);
//		branchCode = StringUtil.emptyToNull(branchCode);
//		privId = StringUtil.emptyToNull(privId);
//		sessionId = StringUtil.emptyToNull(sessionId);
//		userId = StringUtil.emptyToNull(userId);
//		pattern = StringUtil.emptyToNull(pattern);
//
//		String rownum = null;
//		int iRownum = maxRownum;
//		int count=0;
//
//		if (! unlimit) {
//			rownum = String.valueOf(maxRownum);
//		} else {
//            // ↓↓↓ duc.ho - MAX件数　50万（本店のみ） - 27/02/2018
// 			if("1".equals(privId)) {
// 				rownum = String.valueOf(hardLimitAllTradeHistoryList);
// 	            iRownum = hardLimitAllTradeHistoryList;
// 			} else {
// 				rownum = String.valueOf(hardLimit);
// 				iRownum = hardLimit;
// 			}
// 			// ↑↑↑ duc.ho - MAX件数　50万（本店のみ） - 27/02/2018
//		}
//
//		DataList<GetAllTradeHistoryListModel> dataList = new DataList<GetAllTradeHistoryListModel>();
//
//		dataList.setDataList(mapper.getAllTradeHistoryList(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,noProdutCd,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, empCode, branchCode, privId, chargeCodeList, rownum, chkBrokerCodeExclude, brokerCodeExcludeList, customerAttributeList));
//
//		if (dataList.getDataList().size() > 0) {
//			count = dataList.getDataList().get(0).getTotalRow();
//		}
//
//		dataList.setMaxRownum(iRownum);
//		dataList.setTotalSize(count);
//		if(maxRownum<count){
//			dataList.setOverMaxRownum(true);
//		}
//		if(unlimit){
//			GetAllTradeHistoryListCsvOut csvOut = new GetAllTradeHistoryListCsvOut();
//			String tmpCsv = csvOut.doCreateCsvFile(dataList, sessionId, userId, pattern);
//			dataList.getDataList().clear();
//			dataList.setTitle(tmpCsv);
//		}
//
//		return dataList;
//    }
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerCodeModel> GetAllTradeHistoryByBrokerCode(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAllTradeHistoryByBrokerCode: BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE");
//
//        return GetAllTradeHistoryByBrokerCodeInternal(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE);
//    }
//
//    private DataList<GetAllTradeHistoryByBrokerCodeModel> GetAllTradeHistoryByBrokerCodeInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//
//        DataList<GetAllTradeHistoryByBrokerCodeModel> dataList = new DataList<GetAllTradeHistoryByBrokerCodeModel>();
//        dataList.setDataList(mapper.GetAllTradeHistoryByBrokerCode(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerChargeCodeModel> GetAllTradeHistoryByBrokerChargeCode(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CHARGE_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAllTradeHistoryByBrokerChargeCode: BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CHARGE_CODE");
//
//        return GetAllTradeHistoryByBrokerChargeCodeInternal(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CHARGE_CODE);
//    }
//
//    private DataList<GetAllTradeHistoryByBrokerChargeCodeModel> GetAllTradeHistoryByBrokerChargeCodeInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CHARGE_CODE) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		BROKER_CHARGE_CODE = StringUtil.emptyToNull(BROKER_CHARGE_CODE);
//
//        DataList<GetAllTradeHistoryByBrokerChargeCodeModel> dataList = new DataList<GetAllTradeHistoryByBrokerChargeCodeModel>();
//        dataList.setDataList(mapper.GetAllTradeHistoryByBrokerChargeCode(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CHARGE_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerInChargeModel> GetAllTradeHistoryByBrokerInCharge(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAllTradeHistoryByBrokerInCharge: BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,LIST_BROKER_CODE");
//
//        return GetAllTradeHistoryByBrokerInChargeInternal(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,LIST_BROKER_CODE);
//    }
//
//    private DataList<GetAllTradeHistoryByBrokerInChargeModel> GetAllTradeHistoryByBrokerInChargeInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,List<String> LIST_BROKER_CODE) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		for (int i = 0; i< LIST_BROKER_CODE.size(); i++) {
//		    String s = LIST_BROKER_CODE.get(i);
//		    s = StringUtil.emptyToNull(s);
//		    LIST_BROKER_CODE.set(i,s);
//		 }
//
//        DataList<GetAllTradeHistoryByBrokerInChargeModel> dataList = new DataList<GetAllTradeHistoryByBrokerInChargeModel>();
//        dataList.setDataList(mapper.GetAllTradeHistoryByBrokerInCharge(BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,LIST_BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetKawaseTradeHistoryByPersonInChargeModel> GetKawaseTradeHistoryByPersonInCharge(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY, boolean unlimited, String privId, List<String> chargeCodeList, String sessionId, String userId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetKawaseTradeHistoryByPersonInCharge: BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,unlimited,chargeCodeList, sessionId, userId");
//
//        return GetKawaseTradeHistoryByPersonInChargeInternal(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,unlimited, privId,chargeCodeList, sessionId, userId);
//    }
//
//    private DataList<GetKawaseTradeHistoryByPersonInChargeModel> GetKawaseTradeHistoryByPersonInChargeInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY, boolean unlimited, String privId,List<String> chargeCodeList, String sessionId, String userId ) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		BROKER_CHARGE_CODE = StringUtil.emptyToNull(BROKER_CHARGE_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		CURRENCY = StringUtil.emptyToNull(CURRENCY);
//		sessionId = StringUtil.emptyToNull(sessionId);
//		userId = StringUtil.emptyToNull(userId);
//
//		String rownum = null;
//		int iRownum = maxRownum;
//		int count = 0;
//
//		if (! unlimited) {
//			rownum = String.valueOf(maxRownum);
//		} else {
//			rownum = String.valueOf(hardLimit);
//            iRownum = hardLimit;
//		}
//
//        DataList<GetKawaseTradeHistoryByPersonInChargeModel> dataList = new DataList<GetKawaseTradeHistoryByPersonInChargeModel>();
//
//        dataList.setDataList(mapper.GetKawaseTradeHistoryByPersonInCharge(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY, rownum, privId, chargeCodeList));
//
//		if (dataList.getDataList().size() > 0) {
//			count = dataList.getDataList().get(0).getTotalRow();
//		}
//
//		dataList.setMaxRownum(iRownum);
//		dataList.setTotalSize(count);
//		if (maxRownum < count) {
//			dataList.setOverMaxRownum(true);
//		}
//
//		if(unlimited){
//			GetKawaseTradeHistoryByPersonInChargeCsvOut csvOut = new GetKawaseTradeHistoryByPersonInChargeCsvOut();
//			String tmpCsv = csvOut.doCreateCsvFile(dataList, sessionId, userId, null);
//			dataList.getDataList().clear();
//			dataList.setTitle(tmpCsv);
//		}
//
//        return dataList;
//    }
//
//    public DataList<GetKawaseTradeHistoryBySubordinateBrokerModel> GetKawaseTradeHistoryBySubordinateBroker(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY,List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetKawaseTradeHistoryBySubordinateBroker: BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,LIST_BROKER_CODE");
//
//        return GetKawaseTradeHistoryBySubordinateBrokerInternal(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,LIST_BROKER_CODE);
//    }
//
//    private DataList<GetKawaseTradeHistoryBySubordinateBrokerModel> GetKawaseTradeHistoryBySubordinateBrokerInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY,List<String> LIST_BROKER_CODE) throws Exception {
//    	BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		BROKER_CHARGE_CODE = StringUtil.emptyToNull(BROKER_CHARGE_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		CURRENCY = StringUtil.emptyToNull(CURRENCY);
//		for (int i = 0; i< LIST_BROKER_CODE.size(); i++) {
//		    String s = LIST_BROKER_CODE.get(i);
//		    s = StringUtil.emptyToNull(s);
//		    LIST_BROKER_CODE.set(i,s);
//		 }
//
//        DataList<GetKawaseTradeHistoryBySubordinateBrokerModel> dataList = new DataList<GetKawaseTradeHistoryBySubordinateBrokerModel>();
//        dataList.setDataList(mapper.GetKawaseTradeHistoryBySubordinateBroker(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,LIST_BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetJointTradeHistoryModel> getJointTradeHistory(String butenCode,String accountNumber,String brokerCode,String jointBranchCode,String produtCd,String brandCode,String dateFrom,String dateTo,String subBrokerId,String employeeId, String privId, boolean unlimit, String sessionId, String userId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.getJointTradeHistory: BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,PRODUT_CD,BRAND_CODE,dateFrom,dateTo,subBrokerId,employeeId,privId,unlimit, sessionId, userId");
//
//        return getJointTradeHistoryInternal(butenCode,accountNumber,brokerCode,jointBranchCode,produtCd,brandCode,dateFrom,dateTo,subBrokerId,employeeId,privId,unlimit, sessionId, userId);
//    }
//
//    private DataList<GetJointTradeHistoryModel> getJointTradeHistoryInternal(String butenCode,String accountNumber,String brokerCode,String jointBranchCode,String produtCd,String brandCode,String dateFrom,String dateTo,String subBrokerId,String employeeId, String privId, boolean unlimit, String sessionId, String userId) throws Exception {
//        butenCode = StringUtil.emptyToNull(butenCode);
//        accountNumber = StringUtil.emptyToNull(accountNumber);
//        brokerCode = StringUtil.emptyToNull(brokerCode);
//        jointBranchCode = StringUtil.emptyToNull(jointBranchCode);
//        produtCd = StringUtil.emptyToNull(produtCd);
//        brandCode = StringUtil.emptyToNull(brandCode);
//        dateFrom = StringUtil.emptyToNull(dateFrom);
//        dateTo = StringUtil.emptyToNull(dateTo);
//        subBrokerId = StringUtil.emptyToNull(subBrokerId);
//        employeeId = StringUtil.emptyToNull(employeeId);
//        privId = StringUtil.emptyToNull(privId);
//		sessionId = StringUtil.emptyToNull(sessionId);
//		userId = StringUtil.emptyToNull(userId);
//
//		String rownum = null;
//		int iRownum = maxRownum;
//		int count = 0;
//
//		if (! unlimit) {
//			rownum = String.valueOf(maxRownum);
//		} else {
//			rownum = String.valueOf(hardLimit);
//            iRownum = hardLimit;
//		}
//
//        DataList<GetJointTradeHistoryModel> dataList = new DataList<GetJointTradeHistoryModel>();
//
//        dataList.setDataList(mapper.getJointTradeHistory(butenCode,accountNumber,brokerCode,jointBranchCode,produtCd,brandCode,dateFrom,dateTo,privId,subBrokerId,employeeId,rownum));
//
//        if (dataList.getDataList().size() > 0) {
//			count = dataList.getDataList().get(0).getTotalRow();
//		}
//
//        dataList.setMaxRownum(iRownum);
//        dataList.setTotalSize(count);
//		if(maxRownum<count){
//			dataList.setOverMaxRownum(true);
//		}
//
//		if(unlimit){
//			GetJointTradeHistoryCsvOut csvOut = new GetJointTradeHistoryCsvOut();
//			String tmpCsv = csvOut.doCreateCsvFile(dataList, sessionId, userId, null);
//			dataList.getDataList().clear();
//			dataList.setTitle(tmpCsv);
//		}
//
//        return dataList;
//    }
//
//
//
//    public DataList<GetJointBalanceForTotalModel> GetJointBalanceForTotal(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetJointBalanceForTotal: BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE");
//
//        return GetJointBalanceForTotalInternal(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE);
//    }
//
//    private DataList<GetJointBalanceForTotalModel> GetJointBalanceForTotalInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		JOINT_BRANCH_CODE = StringUtil.emptyToNull(JOINT_BRANCH_CODE);
//		DATE_YMD = StringUtil.emptyToNull(DATE_YMD);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		OWN_BRANCH_CODE = StringUtil.emptyToNull(OWN_BRANCH_CODE);
//
//        DataList<GetJointBalanceForTotalModel> dataList = new DataList<GetJointBalanceForTotalModel>();
//        dataList.setDataList(mapper.GetJointBalanceForTotal(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetJointBalanceForMeiSaiModel> GetJointBalanceForMeiSai(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetJointBalanceForMeiSai: BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE");
//
//        return GetJointBalanceForMeiSaiInternal(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE);
//    }
//
//    private DataList<GetJointBalanceForMeiSaiModel> GetJointBalanceForMeiSaiInternal(String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE) throws Exception {
//	    BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//		ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		JOINT_BRANCH_CODE = StringUtil.emptyToNull(JOINT_BRANCH_CODE);
//		DATE_YMD = StringUtil.emptyToNull(DATE_YMD);
//		PRODUT_CD = StringUtil.emptyToNull(PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		OWN_BRANCH_CODE = StringUtil.emptyToNull(OWN_BRANCH_CODE);
//
//        DataList<GetJointBalanceForMeiSaiModel> dataList = new DataList<GetJointBalanceForMeiSaiModel>();
//        dataList.setDataList(mapper.GetJointBalanceForMeiSai(BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetJointBalanceModel> GetJointBalance(List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetJointBalance: LIST_BROKER_CODE");
//
//        return GetJointBalanceInternal(LIST_BROKER_CODE);
//    }
//
//    private DataList<GetJointBalanceModel> GetJointBalanceInternal(List<String> LIST_BROKER_CODE) throws Exception {
//	    for (int i = 0; i< LIST_BROKER_CODE.size(); i++) {
//		    String s = LIST_BROKER_CODE.get(i);
//		    s = StringUtil.emptyToNull(s);
//		    LIST_BROKER_CODE.set(i,s);
//	    }
//
//        DataList<GetJointBalanceModel> dataList = new DataList<GetJointBalanceModel>();
//        dataList.setDataList(mapper.GetJointBalance(LIST_BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetBrokerAuxiliaryBookModel> GetBrokerAuxiliaryBook(String CREATEDATE,String FILENAME,String PRODUCTCODE,String BROKERCODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetBrokerAuxiliaryBook: CREATEDATE,FILENAME,PRODUCTCODE,BROKERCODE");
//
//        return GetBrokerAuxiliaryBookInternal(CREATEDATE,FILENAME,PRODUCTCODE,BROKERCODE);
//    }
//
//    private DataList<GetBrokerAuxiliaryBookModel> GetBrokerAuxiliaryBookInternal(String CREATEDATE,String FILENAME,String PRODUCTCODE,String BROKERCODE) throws Exception {
//        CREATEDATE = StringUtil.emptyToNull(CREATEDATE);
//		FILENAME = StringUtil.emptyToNull(FILENAME);
//		PRODUCTCODE = StringUtil.emptyToNull(PRODUCTCODE);
//		BROKERCODE = StringUtil.emptyToNull(BROKERCODE);
//
//        DataList<GetBrokerAuxiliaryBookModel> sqlResult = new DataList<GetBrokerAuxiliaryBookModel>();
//        sqlResult.setDataList(mapper.GetBrokerAuxiliaryBook(CREATEDATE,FILENAME,PRODUCTCODE,BROKERCODE));
//
//        return sqlResult;
//    }
//
//
//
//
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(String USERID,String SORTKEY
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAuxiliaryData: USERID,SORTKEY");
//
//        return GetAuxiliaryDataInternal(USERID,SORTKEY);
//    }
//
//    private DataList<GetAuxiliaryDataModel> GetAuxiliaryDataInternal(String USERID,String SORTKEY) throws Exception {
//        USERID = StringUtil.emptyToNull(USERID);
//        SORTKEY = StringUtil.emptyToNull(SORTKEY);
//
//        DataList<GetAuxiliaryDataModel> sqlResult = new DataList<GetAuxiliaryDataModel>();
//        sqlResult.setDataList(mapper.GetAuxiliaryData(USERID,SORTKEY));
//
//        return sqlResult;
//    }
//
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(String USERID
//															,String privId
//															,String branchId
//															,String brokerId
//															,String pathRoot
//															,String dateFrom
//															,String dateTo
//															,String brokerCd) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAuxiliaryData: USERID,privId,branchId,brokerId,pathRoot,dateFrom,dateTo,brokerCd");
//
//        return GetAuxiliaryDataInternal(USERID
//										, privId
//										, branchId
//										, brokerId
//										, pathRoot
//										, dateFrom
//										, dateTo
//										, brokerCd);
//    }
//
//    private DataList<GetAuxiliaryDataModel> GetAuxiliaryDataInternal(String USERID
//    																,String privId
//    																,String branchId
//    																,String brokerId
//    																,String pathRoot
//    																,String dateFrom
//    																,String dateTo
//    																,String brokerCd) throws Exception {
//        USERID = StringUtil.emptyToNull(USERID);
//        DataList<GetAuxiliaryDataModel> sqlResult = new DataList<GetAuxiliaryDataModel>();
//
//		// 仲介業者リスト
//		List<String> brokerCodeVector = new Vector<String>();
//
//		if ("2".equals(privId)) {
//			// SBI支店コード
//			brokerCodeVector = brokermapper.getCodeFromBranchMediateInfo(branchId);
//			if (!StringUtil.isNullOrEmpty(brokerCd) && !brokerCodeVector.contains(brokerCd)) {
//				sqlResult.setErrorLevel(ErrorLevel.INFO.getId());
//				return sqlResult;
//			}
//		}
//
//		//TMPテーブルデータを削除
//		DeleteAuxiliaryData(USERID);
//
//		Properties prop = new Properties(System.getProperties());
//		String sep = prop.getProperty("file.separator");
//
//		File dir = new File(pathRoot);
//		File[] files = dir.listFiles();
//
//		if(null == files) {
//			sqlResult.setErrorLevel(ErrorLevel.INFO.getId());
//			return sqlResult;
//		}
//
//		for (int i = 0; i < files.length; i++) {
//			File file = files[i];
//			// 変数初期化
//			String currrentFolderName = "";
//			// ファイル名
//			String currentFileName = null;
//			// 商品コード
//			String productCode = "";
//			// 仲介業者コード
//			String brokerCode = "";
//
//			if (file.isDirectory()) {
//				currrentFolderName = file.getName();
//				File currentPath = new File(pathRoot + sep
//						+ currrentFolderName);
//				File[] currenFileList = currentPath.listFiles();
//
//				int k = 0;
//				for (k = 0; k < currenFileList.length; k++) {
//					if (currenFileList.length > 0&& currenFileList[k].isFile()) {
//						currentFileName = currenFileList[k].getName();
//						if (currentFileName != null
//								&& currentFileName.length() >= 15) {
//							productCode = currentFileName.substring(13, 16);
//						}
//						if (currentFileName != null
//								&& currentFileName.length() >= 22) {
//							brokerCode = currentFileName.substring(17, 21);
//						}
//					} else {
//						continue;
//					}
//
//					// SBI支店
//					if ("2".equals(privId)) {
//						if (!brokerCodeVector.contains(brokerCode) || (!StringUtil.isNullOrEmpty(brokerCd) && !brokerCode.equals(brokerCd))) {
//							// もし、vectorに含まれてなければ
//							continue;
//						}
//
//					} else if ("3".equals(privId) || "4".equals(privId) || "6".equals(privId)) {
//						if (!brokerCode.equals(brokerId)) {
//							continue;
//						} 
//					} else if (!StringUtil.isNullOrEmpty(brokerCd) && !brokerCode.equals(brokerCd)){
//						// SBI本店
//						continue;
//					}
//
//					String from = DateUtil.dateFormat(dateFrom);
//					String to = DateUtil.dateFormat(dateTo);
//
//					if (Integer.parseInt(from) <= Integer.parseInt(currrentFolderName)
//							&& Integer.parseInt(currrentFolderName) <= Integer.parseInt(to)) {
//					
//						//TMPテーブル登録データを取得
//						DataList<GetBrokerAuxiliaryBookModel> auxiliaryList = new DataList<GetBrokerAuxiliaryBookModel>();
//						auxiliaryList = GetBrokerAuxiliaryBook(currrentFolderName, currentFileName, productCode, brokerCode);
//
//						//TMPテーブル登録
//						InsertAuxiliaryValues(
//								USERID,
//								auxiliaryList.get(0).getCreateDate(),
//								auxiliaryList.get(0).getBrokerCode(),
//								auxiliaryList.get(0).getBrokerName(),
//								auxiliaryList.get(0).getProduct(),
//								auxiliaryList.get(0).getDownload(),
//								auxiliaryList.get(0).getFileName());
//					}
//				}
//			}
//		}
//
//
//        sqlResult.setDataList(mapper.GetAuxiliaryData(USERID,null));
//
//        return sqlResult;
//    }
//
//
//
//
//    public Integer DeleteAuxiliaryData(String USERID
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.DeleteAuxiliaryData: USERID");
//
//        return DeleteAuxiliaryDataInternal(USERID);
//    }
//
//    private Integer DeleteAuxiliaryDataInternal(String USERID) throws Exception {
//        USERID = StringUtil.emptyToNull(USERID);
//
//        int sqlResult = mapper.DeleteAuxiliaryData(USERID);
//
//        return sqlResult;
//    }
//
//
//
//
//    public Integer InsertAuxiliaryValues(String USERID,String CREATEDATE,String BROKERCODE,String BROKERNAME,String PRODUCT,String DL,String FILENAME
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.InsertAuxiliaryValues: USERID,CREATEDATE,BROKERCODE,BROKERNAME,PRODUCT,DL,FILENAME");
//
//        return InsertAuxiliaryValuesInternal(USERID,CREATEDATE,BROKERCODE,BROKERNAME,PRODUCT,DL,FILENAME);
//    }
//
//    private Integer InsertAuxiliaryValuesInternal(String USERID,String CREATEDATE,String BROKERCODE,String BROKERNAME,String PRODUCT,String DL,String FILENAME) throws Exception {
//        USERID = StringUtil.emptyToNull(USERID);
//		CREATEDATE = StringUtil.emptyToNull(CREATEDATE);
//		BROKERCODE = StringUtil.emptyToNull(BROKERCODE);
//		BROKERNAME = StringUtil.emptyToNull(BROKERNAME);
//		PRODUCT = StringUtil.emptyToNull(PRODUCT);
//		DL = StringUtil.emptyToNull(DL);
//		FILENAME = StringUtil.emptyToNull(FILENAME);
//
//        int sqlResult = mapper.InsertAuxiliaryValues(USERID,CREATEDATE,BROKERCODE,BROKERNAME,PRODUCT,DL,FILENAME);
//
//        return sqlResult;
//    }
//
//
//
//
//    public DataList<GetCodeNameAndIdByMonthcountModel> GetCodeNameAndIdByMonthcount(String monthCount
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetCodeNameAndIdByMonthcount: monthCount");
//
//        return GetCodeNameAndIdByMonthcountInternal(monthCount);
//    }
//
//	    private DataList<GetCodeNameAndIdByMonthcountModel> GetCodeNameAndIdByMonthcountInternal(String monthCount) throws Exception {
//	    monthCount = StringUtil.emptyToNull(monthCount);
//
//        DataList<GetCodeNameAndIdByMonthcountModel> dataList = new DataList<GetCodeNameAndIdByMonthcountModel>();
//        dataList.setDataList(mapper.GetCodeNameAndIdByMonthcount(monthCount));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetT4InfoByMonthIfMainOrBranchStoreModel> GetT4InfoByMonthIfMainOrBranchStore(String t4CreateAtMonth
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetT4InfoByMonthIfMainOrBranchStore: t4CreateAtMonth");
//
//        return GetT4InfoByMonthIfMainOrBranchStoreInternal(t4CreateAtMonth);
//    }
//
//	    private DataList<GetT4InfoByMonthIfMainOrBranchStoreModel> GetT4InfoByMonthIfMainOrBranchStoreInternal(String t4CreateAtMonth) throws Exception {
//	    t4CreateAtMonth = StringUtil.emptyToNull(t4CreateAtMonth);
//
//        DataList<GetT4InfoByMonthIfMainOrBranchStoreModel> dataList = new DataList<GetT4InfoByMonthIfMainOrBranchStoreModel>();
//        dataList.setDataList(mapper.GetT4InfoByMonthIfMainOrBranchStore(t4CreateAtMonth));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel> GetT4InfoByMonthIfNotMainOrBranchStore(String t4CreateAtMonth,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetT4InfoByMonthIfNotMainOrBranchStore: t4CreateAtMonth,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId");
//
//        return GetT4InfoByMonthIfNotMainOrBranchStoreInternal(t4CreateAtMonth,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId);
//    }
//
//    private DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel> GetT4InfoByMonthIfNotMainOrBranchStoreInternal(String t4CreateAtMonth,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId) throws Exception {
//    	t4CreateAtMonth = StringUtil.emptyToNull(t4CreateAtMonth);
//		COR_REFERENCE_CONDITION_1 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_1);
//		COR_REFERENCE_CONDITION_2 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_2);
//		privId = StringUtil.emptyToNull(privId);
//		COR_REFERENCE_CONDITION_3 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_3);
//		loginId = StringUtil.emptyToNull(loginId);
//
//        DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel> dataList = new DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel>();
//        dataList.setDataList(mapper.GetT4InfoByMonthIfNotMainOrBranchStore(t4CreateAtMonth,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetInfoIfNotMainOrBranchModel> GetInfoIfNotMainOrBranch(String t4CreateDate,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetInfoIfNotMainOrBranch: t4CreateDate,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId");
//
//        return GetInfoIfNotMainOrBranchInternal(t4CreateDate,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId);
//    }
//
//    private DataList<GetInfoIfNotMainOrBranchModel> GetInfoIfNotMainOrBranchInternal(String t4CreateDate,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId) throws Exception {
//	    t4CreateDate = StringUtil.emptyToNull(t4CreateDate);
//		COR_REFERENCE_CONDITION_1 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_1);
//		COR_REFERENCE_CONDITION_2 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_2);
//		privId = StringUtil.emptyToNull(privId);
//		COR_REFERENCE_CONDITION_3 = StringUtil.emptyToNull(COR_REFERENCE_CONDITION_3);
//		loginId = StringUtil.emptyToNull(loginId);
//
//        DataList<GetInfoIfNotMainOrBranchModel> dataList = new DataList<GetInfoIfNotMainOrBranchModel>();
//        dataList.setDataList(mapper.GetInfoIfNotMainOrBranch(t4CreateDate,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> GetCountIfPersonInChargeFromPsiteT4InfoPriv(String privId,String T4ID
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetCountIfPersonInChargeFromPsiteT4InfoPriv: privId,T4ID");
//
//        return GetCountIfPersonInChargeFromPsiteT4InfoPrivInternal(privId,T4ID);
//    }
//
//    private DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> GetCountIfPersonInChargeFromPsiteT4InfoPrivInternal(String privId,String T4ID) throws Exception {
//	    privId = StringUtil.emptyToNull(privId);
//	    T4ID = StringUtil.emptyToNull(T4ID);
//
//        DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> dataList = new DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel>();
//        dataList.setDataList(mapper.GetCountIfPersonInChargeFromPsiteT4InfoPriv(privId,T4ID));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetCountFromPsiteT5InfoReadModel> GetCountFromPsiteT5InfoRead(String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetCountFromPsiteT5InfoRead: T5_INFO_ID,T5_LOGIN");
//
//        return GetCountFromPsiteT5InfoReadInternal(T5_INFO_ID,T5_LOGIN);
//    }
//
//    private DataList<GetCountFromPsiteT5InfoReadModel> GetCountFromPsiteT5InfoReadInternal(String T5_INFO_ID,String T5_LOGIN) throws Exception {
//	    T5_INFO_ID = StringUtil.emptyToNull(T5_INFO_ID);
//	    T5_LOGIN = StringUtil.emptyToNull(T5_LOGIN);
//
//        DataList<GetCountFromPsiteT5InfoReadModel> dataList = new DataList<GetCountFromPsiteT5InfoReadModel>();
//        dataList.setDataList(mapper.GetCountFromPsiteT5InfoRead(T5_INFO_ID,T5_LOGIN));
//        return dataList;
//    }
//
//
//
//
//    public DataList<InsertIntoPsiteT5InfoReadModel> InsertIntoPsiteT5InfoRead(String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.InsertIntoPsiteT5InfoRead: T5_INFO_ID,T5_LOGIN");
//
//        return InsertIntoPsiteT5InfoReadInternal(T5_INFO_ID,T5_LOGIN);
//    }
//
//    private DataList<InsertIntoPsiteT5InfoReadModel> InsertIntoPsiteT5InfoReadInternal(String T5_INFO_ID,String T5_LOGIN) throws Exception {
//	    T5_INFO_ID = StringUtil.emptyToNull(T5_INFO_ID);
//	    T5_LOGIN = StringUtil.emptyToNull(T5_LOGIN);
//
//        Integer count = mapper.InsertIntoPsiteT5InfoRead(T5_INFO_ID,T5_LOGIN);
//
//        DataList<InsertIntoPsiteT5InfoReadModel> dataList = new DataList<InsertIntoPsiteT5InfoReadModel>();
//        dataList.setTotalSize(count);
//
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> GetCountIfNotPersonInChargeFromPsiteT5InfoRead(String T5ID,String loginId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetCountIfNotPersonInChargeFromPsiteT5InfoRead: T5ID,loginId");
//
//        return GetCountIfNotPersonInChargeFromPsiteT5InfoReadInternal(T5ID,loginId);
//    }
//
//    private DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> GetCountIfNotPersonInChargeFromPsiteT5InfoReadInternal(String T5ID,String loginId) throws Exception {
//	    T5ID = StringUtil.emptyToNull(T5ID);
//	    loginId = StringUtil.emptyToNull(loginId);
//
//        DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> dataList = new DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel>();
//        dataList.setDataList(mapper.GetCountIfNotPersonInChargeFromPsiteT5InfoRead(T5ID,loginId));
//        return dataList;
//    }
//
//
//
//
//	public Integer UpdatePsiteT5InfoRead(String T5_INFO_ID,String T5_LOGIN) throws Exception {
//
//		logger.debug("AdminDaoImpl.UpdatePsiteT5InfoRead: T5_INFO_ID,T5_LOGIN");
//
//		return UpdatePsiteT5InfoReadInternal(T5_INFO_ID,T5_LOGIN);
//	}
//
//    private Integer UpdatePsiteT5InfoReadInternal(String T5_INFO_ID,String T5_LOGIN) throws Exception {
//	    T5_INFO_ID = StringUtil.emptyToNull(T5_INFO_ID);
//	    T5_LOGIN = StringUtil.emptyToNull(T5_LOGIN);
//
//
//	    int sqlResult = mapper.UpdatePsiteT5InfoRead(T5_INFO_ID,T5_LOGIN);
//        return sqlResult;
//    }
//
//
//
//
//    public DataList<GetNameAndIdTodayModel> GetNameAndIdToday(String today
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetNameAndIdToday: today");
//
//        return GetNameAndIdTodayInternal(today);
//    }
//
//    private DataList<GetNameAndIdTodayModel> GetNameAndIdTodayInternal(String today) throws Exception {
//    	today = StringUtil.emptyToNull(today);
//
//        DataList<GetNameAndIdTodayModel> dataList = new DataList<GetNameAndIdTodayModel>();
//        dataList.setDataList(mapper.GetNameAndIdToday(today));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetConfirmationDateModel> GetConfirmationDate(String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetConfirmationDate: COR_ENFORCE_DATE,COR_BROKER_CODE");
//
//        return GetConfirmationDateInternal(COR_ENFORCE_DATE,COR_BROKER_CODE);
//    }
//
//    private DataList<GetConfirmationDateModel> GetConfirmationDateInternal(String COR_ENFORCE_DATE,String COR_BROKER_CODE) throws Exception {
//	    COR_ENFORCE_DATE = StringUtil.emptyToNull(COR_ENFORCE_DATE);
//	    COR_BROKER_CODE = StringUtil.emptyToNull(COR_BROKER_CODE);
//
//        DataList<GetConfirmationDateModel> dataList = new DataList<GetConfirmationDateModel>();
//        dataList.setDataList(mapper.GetConfirmationDate(COR_ENFORCE_DATE,COR_BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateModel> GetSelfCheckInfoByEnforceDate(String COR_ENFORCE_DATE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetSelfCheckInfoByEnforceDate: COR_ENFORCE_DATE");
//
//        return GetSelfCheckInfoByEnforceDateInternal(COR_ENFORCE_DATE);
//    }
//
//    private DataList<GetSelfCheckInfoByEnforceDateModel> GetSelfCheckInfoByEnforceDateInternal(String COR_ENFORCE_DATE) throws Exception {
//    	COR_ENFORCE_DATE = StringUtil.emptyToNull(COR_ENFORCE_DATE);
//
//        DataList<GetSelfCheckInfoByEnforceDateModel> dataList = new DataList<GetSelfCheckInfoByEnforceDateModel>();
//        dataList.setDataList(mapper.GetSelfCheckInfoByEnforceDate(COR_ENFORCE_DATE));
//        return dataList;
//    }
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> GetSelfCheckInfoByEnforceDateAndBrokerCode(String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetSelfCheckInfoByEnforceDateAndBrokerCode: COR_ENFORCE_DATE,COR_BROKER_CODE");
//
//        return GetSelfCheckInfoByEnforceDateAndBrokerCodeInternal(COR_ENFORCE_DATE,COR_BROKER_CODE);
//    }
//
//    private DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> GetSelfCheckInfoByEnforceDateAndBrokerCodeInternal(String COR_ENFORCE_DATE,String COR_BROKER_CODE) throws Exception {
//	    COR_ENFORCE_DATE = StringUtil.emptyToNull(COR_ENFORCE_DATE);
//	    COR_BROKER_CODE = StringUtil.emptyToNull(COR_BROKER_CODE);
//
//        DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> dataList = new DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel>();
//        dataList.setDataList(mapper.GetSelfCheckInfoByEnforceDateAndBrokerCode(COR_ENFORCE_DATE,COR_BROKER_CODE));
//        return dataList;
//    }
//
//
//
//
//	public DataList<CustMgmtGetBranchDataByTypeModel> CustMgmtGetBranchDataByType(String branchKind
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetBranchDataByType: branchKind");
//
//		return CustMgmtGetBranchDataByTypeInternal(branchKind);
//	}
//
//	private DataList<CustMgmtGetBranchDataByTypeModel> CustMgmtGetBranchDataByTypeInternal(String branchKind) throws Exception {
//		branchKind = StringUtil.emptyToNull(branchKind);
//
//		DataList<CustMgmtGetBranchDataByTypeModel> sqlResult = new DataList<CustMgmtGetBranchDataByTypeModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetBranchDataByType(branchKind));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel> CustMgmtGetUserDetailByNameOrIdOrBranch(String userId,String brokerOrBranchName,String employeeName
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetUserDetailByNameOrIdOrBranch: userId,brokerOrBranchName,employeeName");
//
//		return CustMgmtGetUserDetailByNameOrIdOrBranchInternal(userId,brokerOrBranchName,employeeName);
//	}
//
//	private DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel> CustMgmtGetUserDetailByNameOrIdOrBranchInternal(String userId,String brokerOrBranchName,String employeeName) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//		brokerOrBranchName = StringUtil.emptyToNull(brokerOrBranchName);
//		employeeName = StringUtil.emptyToNull(employeeName);
//
//		DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel> sqlResult = new DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetUserDetailByNameOrIdOrBranch(userId,brokerOrBranchName,employeeName));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> CustMgmtGetBrokerCodeAndKindAndNameFromMB(String branchCode
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetBrokerCodeAndKindAndNameFromMB: branchCode");
//
//		return CustMgmtGetBrokerCodeAndKindAndNameFromMBInternal(branchCode);
//	}
//
//	private DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> CustMgmtGetBrokerCodeAndKindAndNameFromMBInternal(String branchCode) throws Exception {
//		branchCode = StringUtil.emptyToNull(branchCode);
//
//		DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> sqlResult = new DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetBrokerCodeAndKindAndNameFromMB(branchCode));
//
//		return sqlResult;
//	}
//
//
//
//	@Override
//	public DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(String branchCode, String brokerCode
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode: branchCode,brokerCode");
//
//		return CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeInternal(branchCode, brokerCode);
//	}
//
//	private DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeInternal(String branchCode, String brokerCode) throws Exception {
//		branchCode = StringUtil.emptyToNull(branchCode);
//		brokerCode = StringUtil.emptyToNull(brokerCode);
//
//		DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> sqlResult = new DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(branchCode, brokerCode));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> CustMgmtGetBrokerCCodeAndCNameFromMCI(String brokerCode, String brokerBranchCode
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetBrokerCCodeAndCNameFromMCI: brokerCode, brokerBranchCode");
//
//		return CustMgmtGetBrokerCCodeAndCNameFromMCIInternal(brokerCode, brokerBranchCode);
//	}
//
//	private DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> CustMgmtGetBrokerCCodeAndCNameFromMCIInternal(String brokerCode, String brokerBranchCode) throws Exception {
//		brokerCode = StringUtil.emptyToNull(brokerCode);
//		brokerBranchCode = StringUtil.emptyToNull(brokerBranchCode);
//
//		DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> sqlResult = new DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetBrokerCCodeAndCNameFromMCI(brokerCode, brokerBranchCode));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(String userId
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu: userId");
//
//		return CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuInternal(userId);
//	}
//
//	private DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuInternal(String userId) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//
//		DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> sqlResult = new DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(userId));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel> CustMgmtGetLeafMenuItemsWithPrivId(String privId
//			) throws Exception {
//
//		logger.debug("BrokerDaoImpl.CustMgmtGetLeafMenuItemsWithPrivId: privId");
//
//		return CustMgmtGetLeafMenuItemsWithPrivIdInternal(privId);
//	}
//
//	private DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel> CustMgmtGetLeafMenuItemsWithPrivIdInternal(String privId) throws Exception {
//		privId = StringUtil.emptyToNull(privId);
//
//		DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel> sqlResult = new DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetLeafMenuItemsWithPrivId(privId));
//
//		return sqlResult;
//	}
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsModel> CustMgmtGetLeafMenuItems(
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtGetLeafMenuItems: ");
//
//		return CustMgmtGetLeafMenuItemsInternal();
//	}
//
//	private DataList<CustMgmtGetLeafMenuItemsModel> CustMgmtGetLeafMenuItemsInternal() throws Exception {
//
//		DataList<CustMgmtGetLeafMenuItemsModel> sqlResult = new DataList<CustMgmtGetLeafMenuItemsModel>();
//		sqlResult.setDataList(mapper.CustMgmtGetLeafMenuItems());
//
//		return sqlResult;
//	}
//
//
//	public Integer CustMgmtUpdateTbMedUsersByUserId(String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String ccsUserId,String ccsUserPw,String uptimestampUser,String mailAddress
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtUpdateTbMedUsersByUserId: userId,userNm,password,privId,branchId,brokerId,subBrokerId,subBrokerId,employeeId,employeeName,governorFlag,ccsUserId,ccsUserPw,uptimestampUser,mailAddress");
//
//		return CustMgmtUpdateTbMedUsersByUserIdInternal(userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,ccsUserId,ccsUserPw,uptimestampUser,mailAddress);
//	}
//
//	private Integer CustMgmtUpdateTbMedUsersByUserIdInternal(String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String ccsUserId,String ccsUserPw,String uptimestampUser,String mailAddress) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//		userNm = StringUtil.emptyToNull(userNm);
//		password = StringUtil.emptyToNull(password);
//		privId = StringUtil.emptyToNull(privId);
//		branchId = StringUtil.emptyToNull(branchId);
//		brokerId = StringUtil.emptyToNull(brokerId);
//		subBrokerId = StringUtil.emptyToNull(subBrokerId);
//		employeeId = StringUtil.emptyToNull(employeeId);
//		employeeName = StringUtil.emptyToNull(employeeName);
//		governorFlag = StringUtil.emptyToNull(governorFlag);
//		ccsUserId = StringUtil.emptyToNull(ccsUserId);
//		ccsUserPw = StringUtil.emptyToNull(ccsUserPw);
//		uptimestampUser = StringUtil.emptyToNull(uptimestampUser);
//		mailAddress = StringUtil.emptyToNull(mailAddress);
//
//		int sqlResult = mapper.CustMgmtUpdateTbMedUsersByUserId(userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,ccsUserId,ccsUserPw,uptimestampUser,mailAddress);
//
//		return sqlResult;
//	}
//
//
//
//
//
//	public Integer CustMgmtInsertIntoTbMedUsers(String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String loginStatus,String partnerUserId,String partnerUserPw,String ccsUserId,String ccsUserPw,String createUser,String uptimestampUser,String mailAddress
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtInsertIntoTbMedUsers: userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,loginStatus,partnerUserId,partnerUserPw,ccsUserId,ccsUserPw,createUser,uptimestampUser,mailAddress");
//
//		return CustMgmtInsertIntoTbMedUsersInternal(userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,loginStatus,partnerUserId,partnerUserPw,ccsUserId,ccsUserPw,createUser,uptimestampUser,mailAddress);
//	}
//
//	private Integer CustMgmtInsertIntoTbMedUsersInternal(String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String loginStatus,String partnerUserId,String partnerUserPw,String ccsUserId,String ccsUserPw,String createUser,String uptimestampUser,String mailAddress) throws Exception {
//		userId   = StringUtil.emptyToNull(userId);
//		userNm   = StringUtil.emptyToNull(userNm);
//		password = StringUtil.emptyToNull(password);
//		privId   = StringUtil.emptyToNull(privId);
//		branchId = StringUtil.emptyToNull(branchId);
//		brokerId = StringUtil.emptyToNull(brokerId);
//		subBrokerId  = StringUtil.emptyToNull(subBrokerId);
//		employeeId   = StringUtil.emptyToNull(employeeId);
//		employeeName = StringUtil.emptyToNull(employeeName);
//		governorFlag = StringUtil.emptyToNull(governorFlag);
//		loginStatus  = StringUtil.emptyToNull(loginStatus);
//		partnerUserId = StringUtil.emptyToNull(partnerUserId);
//		partnerUserPw = StringUtil.emptyToNull(partnerUserPw);
//		ccsUserId  = StringUtil.emptyToNull(ccsUserId);
//		ccsUserPw  = StringUtil.emptyToNull(ccsUserPw);
//		createUser = StringUtil.emptyToNull(createUser);
//		uptimestampUser = StringUtil.emptyToNull(uptimestampUser);
//		mailAddress     = StringUtil.emptyToNull(mailAddress);
//
//		int sqlResult = mapper.CustMgmtInsertIntoTbMedUsers(userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,loginStatus,partnerUserId,partnerUserPw,ccsUserId,ccsUserPw,createUser,uptimestampUser,mailAddress);
//		
//		sqlResult = mapper.CustMgmtInsertIntoTbMedVerifyUsers(userId,createUser,null, privId);
//
//		return sqlResult;
//	}
//
//
//
//
//	public Integer CustMgmtDeleteUserById(String userId
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtDeleteUserById: userId");
//
//		return CustMgmtDeleteUserByIdInternal(userId);
//	}
//
//	private Integer CustMgmtDeleteUserByIdInternal(String userId) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//
////		int sqlResult = mapper.CustMgmtDeleteUserById(userId);
//
//		// First delete all lines in the menu government table
//		int sqlResult = mapper.CustMgmtDeleteUserFromGovMenuById(userId);
//
//		// Then delete the user itself
//		sqlResult += mapper.CustMgmtDeleteUserFromUserById(userId);
//		
//		// Then delete the user itself
//		sqlResult += mapper.CustMgmtDeleteUserFromVerifyUsersById(userId);
//
//		return sqlResult;
//	}
//
//
//
//	public Integer CustMgmtDeleteFromTbMedGovMenuByUserId(String userId
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtDeleteFromTbMedGovMenuByUserId: userId");
//
//		return CustMgmtDeleteFromTbMedGovMenuByUserIdInternal(userId);
//	}
//
//	private Integer CustMgmtDeleteFromTbMedGovMenuByUserIdInternal(String userId) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//
//		int sqlResult = mapper.CustMgmtDeleteFromTbMedGovMenuByUserId(userId);
//
//		return sqlResult;
//	}
//
//
//
//
//	public Integer CustMgmtInsertIntoTbMedGovMenu(String userId,String menuId,String createUser,String uptimestampUser
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtInsertIntoTbMedGovMenu: userId,menuId,createUser,uptimestampUser");
//
//		return CustMgmtInsertIntoTbMedGovMenuInternal(userId,menuId,createUser,uptimestampUser);
//	}
//
//	private Integer CustMgmtInsertIntoTbMedGovMenuInternal(String userId,String menuId,String createUser,String uptimestampUser) throws Exception {
//		userId = StringUtil.emptyToNull(userId);
//		menuId = StringUtil.emptyToNull(menuId);
//		createUser = StringUtil.emptyToNull(createUser);
//		uptimestampUser = StringUtil.emptyToNull(uptimestampUser);
//
//		int sqlResult = mapper.CustMgmtInsertIntoTbMedGovMenu(userId,menuId,createUser,uptimestampUser);
//
//		return sqlResult;
//	}
//
//
//
//	public Integer CustMgmtUpdateCcsData(String userId, String ccsUserId, String ccsUserPw
//			) throws Exception {
//
//		logger.debug("AdminDaoImpl.CustMgmtUpdateCcsData: userId,ccsUserId,ccsUserPw");
//
//		return CustMgmtUpdateCcsDataInternal(userId,ccsUserId,ccsUserPw);
//	}
//
//	private Integer CustMgmtUpdateCcsDataInternal(String userId, String ccsUserId, String ccsUserPw) throws Exception {
//		userId   = StringUtil.emptyToNull(userId);
//		ccsUserId  = StringUtil.emptyToNull(ccsUserId);
//		ccsUserPw  = StringUtil.emptyToNull(ccsUserPw);
//
//		int sqlResult = mapper.CustMgmtUpdateCcsData(userId,ccsUserId,ccsUserPw);
//
//		return sqlResult;
//	}
//
//
//	public DataList<GetAlertNoticeTrustModel> GetAlertNoticeTrust( String userId, String fromDate, String toDate) throws Exception {
//		logger.debug("AdminDaoImpl.GetAlertNoticeTrustModel: ");
//		return GetAlertNoticeTrustInternal(userId, fromDate, toDate);
//	}
//
//	private DataList<GetAlertNoticeTrustModel> GetAlertNoticeTrustInternal(String userId, String fromDate, String toDate) throws Exception {
//
//		userId   = StringUtil.emptyToNull(userId);
//		fromDate  = StringUtil.emptyToNull(fromDate);
//		toDate  = StringUtil.emptyToNull(toDate);
//		DataList<GetAlertNoticeTrustModel> sqlResult = new DataList<GetAlertNoticeTrustModel>();
//		sqlResult.setDataList(mapper.GetAlertNoticeTrust(userId, fromDate, toDate));
//		return sqlResult;
//
//	}
//
//    public DataList<GetAlertNoticeTrustFileModel> GetAlertNoticeTrustFile(String createDate,String fileName, String brokerCode
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.GetAlertNoticeTrustFile: createDate,fileName,brokerCode");
//
//        return GetAlertNoticeTrustFileInternal(createDate,fileName,brokerCode);
//    }
//
//    private DataList<GetAlertNoticeTrustFileModel> GetAlertNoticeTrustFileInternal(String createDate,String fileName, String brokerCode) throws Exception {
//    	createDate = StringUtil.emptyToNull(createDate);
//    	fileName = StringUtil.emptyToNull(fileName);
//		brokerCode = StringUtil.emptyToNull(brokerCode);
//
//        DataList<GetAlertNoticeTrustFileModel> sqlResult = new DataList<GetAlertNoticeTrustFileModel>();
//        sqlResult.setDataList(mapper.GetAlertNoticeTrustFile(createDate,fileName,brokerCode));
//
//        return sqlResult;
//    }
//
//    public Integer DeleteAlertNoticeTrust(String userId
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.DeleteAlertNoticeTrust: userId");
//        return DeleteAlertNoticeTrustInternal(userId);
//    }
//
//    private Integer DeleteAlertNoticeTrustInternal(String userId) throws Exception {
//    	userId = StringUtil.emptyToNull(userId);
//        int sqlResult = mapper.DeleteAlertNoticeTrust(userId);
//        return sqlResult;
//    }
//
//
//    public Integer InsertAlertNoticeTrust(String userId,String createDate,String brokerCode,String brokerName,String reserv,String dl,String fileName
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.InsertAlertNoticeTrust: userId,createDate,brokerCode,brokerName,reserv,dl,fileName");
//        return InsertAlertNoticeTrustInternal(userId,createDate,brokerCode,brokerName,reserv,dl,fileName);
//    }
//
//    private Integer InsertAlertNoticeTrustInternal(String userId,String createDate,String brokerCode,String brokerName,String reserv,String dl,String fileName) throws Exception {
//    	userId = StringUtil.emptyToNull(userId);
//    	createDate = StringUtil.emptyToNull(createDate);
//    	brokerCode = StringUtil.emptyToNull(brokerCode);
//    	brokerName = StringUtil.emptyToNull(brokerName);
//    	reserv = StringUtil.emptyToNull(reserv);
//    	dl = StringUtil.emptyToNull(dl);
//    	fileName = StringUtil.emptyToNull(fileName);
//
//        int sqlResult = mapper.InsertAlertNoticeTrust( userId, createDate, brokerCode, brokerName, reserv, dl, fileName);
//
//        return sqlResult;
//    }
//
//    public DataList<GetInfoRegistrationTypesModel> getInfoRegistrationTypes(String functionId
//    		) throws Exception {
//
//    	logger.debug("AdminDaoImpl.getInfoRegistrationTypes: String functionId ");
//
//    	return getInfoRegistrationTypesInternal(functionId);
//    }
//
//    private DataList<GetInfoRegistrationTypesModel> getInfoRegistrationTypesInternal(String functionId) throws Exception {
//    	functionId = StringUtil.emptyToNull(functionId);
//
//    	DataList<GetInfoRegistrationTypesModel> sqlResult = new DataList<GetInfoRegistrationTypesModel>();
//    	sqlResult.setDataList(mapper.getInfoRegistrationTypes(functionId));
//
//    	return sqlResult;
//    }
//
//    public DataList<GetTradeCommissionTotalModel> getTradeCommissionTotal(String UNIT_DETAIL,String BUTEN_CODE,String ACCOUNT_NUMBER,List<String> PRODUT_CD_LIST,String NO_PRODUT_CD, String BRAND_CODE,String EXCHANGE_FLG,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String EMP_CODE, String BRANCH_CODE, String privId, List<String> chargeCodeList, boolean unlimit, String sessionId, String userId, String pattern
//    ) throws Exception {
//
//        logger.debug("AdminDaoImpl.getTradeCommissionTotal: UNIT_DETAIL,BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD_LIST,NO_PRODUT_CD,BRAND_CODE,EXCHANGE_FLG,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, EMP_CODE, BRANCH_CODE, privId, chargeCodeList, unlimit, sessionId, userId, pattern");
//
//        return getTradeCommissionTotalInternal(UNIT_DETAIL, BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD_LIST,NO_PRODUT_CD,BRAND_CODE,EXCHANGE_FLG,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, EMP_CODE, BRANCH_CODE, privId, chargeCodeList, unlimit, sessionId, userId, pattern);
//    }
//
//    private DataList<GetTradeCommissionTotalModel> getTradeCommissionTotalInternal(String UNIT_DETAIL, String BUTEN_CODE,String ACCOUNT_NUMBER,List<String> PRODUT_CD_LIST,String NO_PRODUT_CD, String BRAND_CODE,String EXCHANGE_FLG, String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String EMP_CODE, String BRANCH_CODE, String privId, List<String> chargeCodeList, boolean unlimit, String sessionId, String userId, String pattern) throws Exception {
//    	BUTEN_CODE = StringUtil.emptyToNull(BUTEN_CODE);
//	    ACCOUNT_NUMBER = StringUtil.emptyToNull(ACCOUNT_NUMBER);
//	    NO_PRODUT_CD = StringUtil.emptyToNull(NO_PRODUT_CD);
//		BRAND_CODE = StringUtil.emptyToNull(BRAND_CODE);
//		TRADE_DATE_FROM = StringUtil.emptyToNull(TRADE_DATE_FROM);
//		TRADE_DATE_TO = StringUtil.emptyToNull(TRADE_DATE_TO);
//		BROKER_CODE = StringUtil.emptyToNull(BROKER_CODE);
//		EMP_CODE = StringUtil.emptyToNull(EMP_CODE);
//		BRANCH_CODE = StringUtil.emptyToNull(BRANCH_CODE);
//		privId = StringUtil.emptyToNull(privId);
//		sessionId = StringUtil.emptyToNull(sessionId);
//		userId = StringUtil.emptyToNull(userId);
//		pattern = StringUtil.emptyToNull(pattern);
//
//		String rownum = null;
//		int iRownum = maxRownum;
//		int count=0;
//
//		if (!unlimit) {
//			rownum = String.valueOf(maxRownum);
//		} else {
//			rownum = String.valueOf(hardLimit);
//			iRownum = hardLimit;
//		}
//
//
//		DataList<GetTradeCommissionTotalModel> dataList = new DataList<GetTradeCommissionTotalModel>();
//		dataList.setDataList(mapper.getTradeCommissionTotal(UNIT_DETAIL,BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD_LIST,NO_PRODUT_CD,BRAND_CODE,EXCHANGE_FLG,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, EMP_CODE, BRANCH_CODE, privId, chargeCodeList, rownum));
//
//		if(dataList.getDataList().size() > 0){
//			count = dataList.getDataList().get(0).getTotalRow();
//		}
//		dataList.setMaxRownum(iRownum);
//		dataList.setTotalSize(count);
//		if(maxRownum<count){
//			dataList.setOverMaxRownum(true);
//		}
//		if(unlimit){
//			GetTradeCommissionTotalCsvOut csvOut = new GetTradeCommissionTotalCsvOut();
//			String tmpCsv = csvOut.doCreateCsvFile(dataList, sessionId, userId, pattern);
//			dataList.getDataList().clear();
//			dataList.setTitle(tmpCsv);
//		}
//		return dataList;
//    }

    public int countMediateBranchByBrokerCode(String brokerCode) throws Exception {

        logger.debug("AdminDaoImpl.countMediateBranchByBrokerCode: brokerCode");

        return countMediateBranchByBrokerCodeInternal(brokerCode);
    }

    public int countMediateBranchByBrokerCodeInternal(String brokerCode) throws Exception {

    	brokerCode = StringUtil.emptyToNull(brokerCode);

    	int rowCount = 0;
    	rowCount = mapper.countMediateBranchByBrokerCode(brokerCode);

		return rowCount;
    }
//
//    @Override
//	public DataList<CustomerAttributeMasterModel> getAllCustomerAttributeMaster() throws Exception {
//		logger.debug("AdminDaoImpl.getAllCustomerAttributeMaster");
//		return getAllCustomerAttributeMasterInternal();
//	}
//
//    private DataList<CustomerAttributeMasterModel> getAllCustomerAttributeMasterInternal() throws Exception {
//
//		DataList<CustomerAttributeMasterModel> dataList = new DataList<CustomerAttributeMasterModel>();
//		List<CustomerAttributeMasterModel> customerAttributeMasterList = dataList.getDataList();
//		customerAttributeMasterList = mapper.getAllCustomerAttributeMaster();
//		int count = customerAttributeMasterList.size();
//		boolean overMaxRownum = false;
//		dataList.setDataList(customerAttributeMasterList);
//		dataList.setMaxRownum(maxRownum);
//		dataList.setTotalSize(count);
//		dataList.setOverMaxRownum(overMaxRownum);
//		return dataList;
//	}
//
//    private int hardLimitAllTradeHistoryList = hardLimit;
//	public void setHardLimitAllTradeHistoryList(int hardLimitAllTradeHistoryList) {
//		if (hardLimitAllTradeHistoryList <= 0) return;
//
//		this.hardLimitAllTradeHistoryList = hardLimitAllTradeHistoryList;
//
//		logger.debug("DI: setHardLimitAllTradeHistoryList:[{}] of AdminDaoImpl:[{}]", hardLimitAllTradeHistoryList, this);
//	}

}
