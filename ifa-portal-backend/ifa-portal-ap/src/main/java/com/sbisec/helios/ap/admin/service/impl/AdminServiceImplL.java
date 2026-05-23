package com.sbisec.helios.ap.admin.service.impl;

import com.sbisec.helios.ap.admin.dao.AdminDao;
import com.sbisec.helios.ap.admin.service.AdminService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// @Service
@Component(value = "adminService")
public class AdminServiceImplL implements AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminServiceImplL.class);
    @Autowired
    private AdminDao dao;

        /**
         * Setting GetTradeHistoryByCodeDao.<br>
         * DI from spring.
         *
         * @param dao
         */
        public void setAdminDao(AdminDao dao) {
            this.dao = dao;
            logger.debug("DI: setGetTradeHistoryByCodeDao:[" + dao + "] of GetTradeHistoryByCodeServiceImplL:[" + this + "]");
        }




//    public DataList<GetTradeHistoryByCodeModel> GetTradeHistoryByCode(
//    String CODE_TYPE,String CODE_1,String CODE_2
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetTradeHistoryByCodeList");
//
//        return dao.GetTradeHistoryByCode(
//        CODE_TYPE,CODE_1,CODE_2
//        );
//    }
//
//
//
//
//
//    public DataList<GetAllTradeHistoryListModel> getAllTradeHistoryList(
//      String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String noProdutCd,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String empCode, String branchCode, String privId, List<String> chargeCodeList,boolean unlimit, String chkBrokerCodeExclude, List<String> brokerCodeExcludeList, List<String> customerAttributeList, String sessionId, String userId , String pattern
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getAllTradeHistoryListList");
//
//        return dao.getAllTradeHistoryList(
//          BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,noProdutCd,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE, empCode, branchCode, privId,chargeCodeList,unlimit, chkBrokerCodeExclude, brokerCodeExcludeList, customerAttributeList, sessionId, userId , pattern
//        );
//    }
//
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerCodeModel> GetAllTradeHistoryByBrokerCode(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetAllTradeHistoryByBrokerCodeList");
//
//        return dao.GetAllTradeHistoryByBrokerCode(
//        BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerChargeCodeModel> GetAllTradeHistoryByBrokerChargeCode(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CHARGE_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetAllTradeHistoryByBrokerChargeCodeList");
//
//        return dao.GetAllTradeHistoryByBrokerChargeCode(
//        BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,BROKER_CHARGE_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerInChargeModel> GetAllTradeHistoryByBrokerInCharge(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetAllTradeHistoryByBrokerInChargeList");
//
//        return dao.GetAllTradeHistoryByBrokerInCharge(
//        BUTEN_CODE,ACCOUNT_NUMBER,PRODUT_CD,BRAND_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,LIST_BROKER_CODE
//        );
//    }
//
//    public DataList<GetAlertNoticeTrustModel> GetAlertNoticeTrust(String userId,String fromDate,String toDate) throws Exception {
//    	if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.GetAlertNoticeTrust");
//        return dao.GetAlertNoticeTrust( userId,fromDate,toDate);
//    }
//
//    public DataList<GetAlertNoticeTrustFileModel> GetAlertNoticeTrustFile(String createDate,String fileName,String brokerCode) throws Exception {
//    	if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.GetAlertNoticeTrustFile");
//        return dao.GetAlertNoticeTrustFile( createDate,fileName,brokerCode);
//    }
//    public Integer DeleteAlertNoticeTrust(String userId) throws Exception{
//    	if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.DeleteAlertNoticeTrust");
//        return dao.DeleteAlertNoticeTrust(userId);
//    }
//
//    public Integer InsertAlertNoticeTrust(String userId, String createDate, String brokerCode, String brokerName, String reserv, String dl, String fileName) throws Exception {
//    	if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.InsertAlertNoticeTrust");
//        return dao.InsertAlertNoticeTrust(userId,createDate, brokerCode, brokerName,reserv,dl,fileName);
//    }
//
//    public DataList<GetKawaseTradeHistoryByPersonInChargeModel> GetKawaseTradeHistoryByPersonInCharge(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY, boolean unlimited, String privId,List<String> chargeCodeList, String sessionId, String userId
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetKawaseTradeHistoryByPersonInChargeList");
//
//        return dao.GetKawaseTradeHistoryByPersonInCharge(
//        BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY, unlimited, privId, chargeCodeList, sessionId, userId
//        );
//    }
//
//
//    public DataList<GetKawaseTradeHistoryBySubordinateBrokerModel> GetKawaseTradeHistoryBySubordinateBroker(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY,List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetKawaseTradeHistoryBySubordinateBrokerList");
//
//        return dao.GetKawaseTradeHistoryBySubordinateBroker(
//        BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,BROKER_CHARGE_CODE,TRADE_DATE_FROM,TRADE_DATE_TO,CURRENCY,LIST_BROKER_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetJointTradeHistoryModel> getJointTradeHistory(
//    String butenCode,String accountNumber,String brokerCode,String jointBranchCode,String produtCd,String brandCode,String dateFrom,String dateTo,String subBrokerId,String employeeId, String privId, boolean unlimit, String sessionId, String userId
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getJointTradeHistory");
//
//        return dao.getJointTradeHistory(
//         butenCode,accountNumber,brokerCode,jointBranchCode,produtCd,brandCode,dateFrom,dateTo,subBrokerId,employeeId,privId,unlimit, sessionId, userId
//        );
//    }
//
//
//
//
//    public DataList<GetJointBalanceForTotalModel> GetJointBalanceForTotal(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetJointBalanceForTotalList");
//
//        return dao.GetJointBalanceForTotal(
//        BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetJointBalanceForMeiSaiModel> GetJointBalanceForMeiSai(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetJointBalanceForMeiSaiList");
//
//        return dao.GetJointBalanceForMeiSai(
//        BUTEN_CODE,ACCOUNT_NUMBER,BROKER_CODE,JOINT_BRANCH_CODE,DATE_YMD,PRODUT_CD,BRAND_CODE,OWN_BRANCH_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetJointBalanceModel> GetJointBalance(
//    List<String> LIST_BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetJointBalanceList");
//
//        return dao.GetJointBalance(
//        LIST_BROKER_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetBrokerAuxiliaryBookModel> GetBrokerAuxiliaryBook(
//    String CREATEDATE,String FILENAME,String PRODUCTCODE,String BROKERCODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetBrokerAuxiliaryBookList");
//
//        return dao.GetBrokerAuxiliaryBook(
//        CREATEDATE,FILENAME,PRODUCTCODE,BROKERCODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(
//    String USERID,String SORTKEY
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetAuxiliaryDataList");
//
//        return dao.GetAuxiliaryData(
//        USERID,SORTKEY
//        );
//    }
//
//
//
//
//
//    @Transactional(rollbackFor = Throwable.class)
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(
//    String USERID,String privId,String branchId,String brokerId,String pathRoot,String dateFrom,String dateTo,String brokerCd
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetAuxiliaryDataList");
//
//        return dao.GetAuxiliaryData(
//        USERID,privId,branchId,brokerId,pathRoot,dateFrom,dateTo,brokerCd
//        );
//    }
//
//
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//    public Integer DeleteAuxiliaryData(
//    String USERID
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getDeleteAuxiliaryDataList");
//
//        return dao.DeleteAuxiliaryData(
//        USERID
//        );
//    }
//
//
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//    public Integer InsertAuxiliaryValues(
//    String USERID,String CREATEDATE,String BROKERCODE,String BROKERNAME,String PRODUCT,String DL,String FILENAME
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getInsertAuxiliaryValuesList");
//
//        return dao.InsertAuxiliaryValues(
//        USERID,CREATEDATE,BROKERCODE,BROKERNAME,PRODUCT,DL,FILENAME
//        );
//    }
//
//
//
//
//
//    public DataList<GetCodeNameAndIdByMonthcountModel> GetCodeNameAndIdByMonthcount(
//    String monthCount
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetCodeNameAndIdByMonthcountList");
//
//        return dao.GetCodeNameAndIdByMonthcount(
//        monthCount
//        );
//    }
//
//
//
//
//
//    public DataList<GetT4InfoByMonthIfMainOrBranchStoreModel> GetT4InfoByMonthIfMainOrBranchStore(
//    String t4CreateAtMonth
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetT4InfoByMonthIfMainOrBranchStoreList");
//
//        return dao.GetT4InfoByMonthIfMainOrBranchStore(
//        t4CreateAtMonth
//        );
//    }
//
//
//
//
//
//    public DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel> GetT4InfoByMonthIfNotMainOrBranchStore(
//    String t4CreateAtMonth,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetT4InfoByMonthIfNotMainOrBranchStoreList");
//
//        return dao.GetT4InfoByMonthIfNotMainOrBranchStore(
//        t4CreateAtMonth,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId
//        );
//    }
//
//
//
//
//
//    public DataList<GetInfoIfNotMainOrBranchModel> GetInfoIfNotMainOrBranch(
//    String t4CreateDate,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetInfoIfNotMainOrBranchList");
//
//        return dao.GetInfoIfNotMainOrBranch(
//        t4CreateDate,COR_REFERENCE_CONDITION_1,COR_REFERENCE_CONDITION_2,privId,COR_REFERENCE_CONDITION_3,loginId
//        );
//    }
//
//
//
//
//
//    public DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> GetCountIfPersonInChargeFromPsiteT4InfoPriv(
//    String privId,String T4ID
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetCountIfPersonInChargeFromPsiteT4InfoPrivList");
//
//        return dao.GetCountIfPersonInChargeFromPsiteT4InfoPriv(
//        privId,T4ID
//        );
//    }
//
//
//
//
//
//    public DataList<GetCountFromPsiteT5InfoReadModel> GetCountFromPsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetCountFromPsiteT5InfoReadList");
//
//        return dao.GetCountFromPsiteT5InfoRead(
//        T5_INFO_ID,T5_LOGIN
//        );
//    }
//
//
//
//
//
//    public DataList<InsertIntoPsiteT5InfoReadModel> InsertIntoPsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getInsertIntoPsiteT5InfoReadList");
//
//        return dao.InsertIntoPsiteT5InfoRead(
//        T5_INFO_ID,T5_LOGIN
//        );
//    }
//
//
//
//
//
//    public DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> GetCountIfNotPersonInChargeFromPsiteT5InfoRead(
//    String T5ID,String loginId
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetCountIfNotPersonInChargeFromPsiteT5InfoReadList");
//
//        return dao.GetCountIfNotPersonInChargeFromPsiteT5InfoRead(
//        T5ID,loginId
//        );
//    }
//
//
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//    public Integer UpdatePsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getUpdatePsiteT5InfoReadList");
//
//        return dao.UpdatePsiteT5InfoRead(
//        T5_INFO_ID,T5_LOGIN
//        );
//    }
//
//
//
//
//
//    public DataList<GetNameAndIdTodayModel> GetNameAndIdToday(
//    String today
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetNameAndIdTodayList");
//
//        return dao.GetNameAndIdToday(
//        today
//        );
//    }
//
//
//
//
//
//    public DataList<GetConfirmationDateModel> GetConfirmationDate(
//    String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetConfirmationDateList");
//
//        return dao.GetConfirmationDate(
//        COR_ENFORCE_DATE,COR_BROKER_CODE
//        );
//    }
//
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateModel> GetSelfCheckInfoByEnforceDate(
//    String COR_ENFORCE_DATE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetSelfCheckInfoByEnforceDateList");
//
//        return dao.GetSelfCheckInfoByEnforceDate(
//        COR_ENFORCE_DATE
//        );
//    }
//
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> GetSelfCheckInfoByEnforceDateAndBrokerCode(
//    String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception {
//
//        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetSelfCheckInfoByEnforceDateAndBrokerCodeList");
//
//        return dao.GetSelfCheckInfoByEnforceDateAndBrokerCode(
//        COR_ENFORCE_DATE,COR_BROKER_CODE
//        );
//    }
//
//
//
//
//
//	public DataList<CustMgmtGetBranchDataByTypeModel> CustMgmtGetBranchDataByType(
//			String branchKind
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetBranchDataByTypeList");
//
//		return dao.CustMgmtGetBranchDataByType(
//				branchKind
//				);
//	}
//
//
//
//
//
//	public DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel> CustMgmtGetUserDetailByNameOrIdOrBranch(
//		    String userId,String brokerOrBranchName,String employeeName
//		    ) throws Exception {
//
//		        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetUserDetailByNameOrIdOrBranchList");
//
//		        return dao.CustMgmtGetUserDetailByNameOrIdOrBranch(
//		        userId,brokerOrBranchName,employeeName
//		        );
//		    }
//
//
//
//
//
//	public DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> CustMgmtGetBrokerCodeAndKindAndNameFromMB(
//			String branchCode
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetBrokerCodeAndKindAndNameFromMBList");
//
//		return dao.CustMgmtGetBrokerCodeAndKindAndNameFromMB(
//				branchCode
//				);
//	}
//
//	@Override
//	public DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(
//			String branchCode, String brokerCode
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode");
//
//		return dao.CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(
//				branchCode, brokerCode);
//	}
//
//
//
//
//
//	public DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> CustMgmtGetBrokerCCodeAndCNameFromMCI(
//			String brokerCode, String brokerBranchCode
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetBrokerCCodeAndCNameFromMCIList");
//
//		return dao.CustMgmtGetBrokerCCodeAndCNameFromMCI(
//				brokerCode, brokerBranchCode
//				);
//	}
//
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(
//			String userId
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuList");
//
//		return dao.CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(
//				userId
//				);
//	}
//
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel> CustMgmtGetLeafMenuItemsWithPrivId(
//			String privId
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetLeafMenuItemsWithPrivIdList");
//
//		return dao.CustMgmtGetLeafMenuItemsWithPrivId(
//				privId
//				);
//	}
//
//
//
//
//
//	public DataList<CustMgmtGetLeafMenuItemsModel> CustMgmtGetLeafMenuItems(
//
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtGetLeafMenuItemsList");
//
//		return dao.CustMgmtGetLeafMenuItems(
//
//				);
//	}
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtUpdateTbMedUsersByUserId(
//			String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String ccsUserId,String ccsUserPw,String uptimestampUser,String mailAddress
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtUpdateTbMedUsersByUserIdList");
//
//		return dao.CustMgmtUpdateTbMedUsersByUserId(
//				userId,userNm,password,privId,
//				branchId,brokerId,subBrokerId,employeeId,employeeName,
//				governorFlag,ccsUserId,ccsUserPw,uptimestampUser,
//				mailAddress
//				);
//	}
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtInsertIntoTbMedUsers(
//			String userId,         String userNm,      String password,   String privId,
//			String branchId,       String brokerId,    String subBrokerId,String employeeId,
//			String employeeName,   String governorFlag,String loginStatus,String partnerUserId,
//			String partnerUserPw,  String ccsUserId,   String ccsUserPw,  String createUser,
//			String uptimestampUser,String mailAddress
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtInsertIntoTbMedUsersList");
//
//		return dao.CustMgmtInsertIntoTbMedUsers(
//				userId,userNm,password,privId,branchId,brokerId,subBrokerId,employeeId,employeeName,governorFlag,loginStatus,partnerUserId,partnerUserPw,ccsUserId,ccsUserPw,createUser,uptimestampUser,mailAddress
//				);
//	}
//
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtDeleteUserById(String userId) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtDeleteUserByIdList");
//
//		return dao.CustMgmtDeleteUserById(userId);
//	}
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtDeleteFromTbMedGovMenuByUserId(
//			String userId
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtDeleteFromTbMedGovMenuByUserIdList");
//
//		return dao.CustMgmtDeleteFromTbMedGovMenuByUserId(
//				userId
//				);
//	}
//
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtInsertIntoTbMedGovMenu(
//			String userId,String menuId,String createUser,String uptimestampUser
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getCustMgmtInsertIntoTbMedGovMenuList");
//
//		return dao.CustMgmtInsertIntoTbMedGovMenu(
//				userId,menuId,createUser,uptimestampUser
//				);
//	}
//
//
//	@Transactional(rollbackFor = Throwable.class)
//	public Integer CustMgmtUpdateCcsData(
//			String userId, String ccsUserId, String ccsUserPw
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.CustMgmtUpdateCcsData");
//
//		return dao.CustMgmtUpdateCcsData(userId,ccsUserId,ccsUserPw);
//	}
//
//	public DataList<GetInfoRegistrationTypesModel> getInfoRegistrationTypes(
//			String functionId
//			) throws Exception {
//
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getGetInfoRegistrationTypesList");
//
//		return dao.getInfoRegistrationTypes(
//				functionId
//				);
//	}
//
//
//	/**
//	 * 手数料合計検索
//	 */
//    public DataList<GetTradeCommissionTotalModel> getTradeCommissionTotal(
//    		String UNIT_DETAIL,
//			String BUTEN_CODE,
//			String ACCOUNT_NUMBER,
//			List<String> PRODUT_CD_LIST,
//			String NO_PRODUT_CD,
//			String BRAND_CODE,
//			String EXCHANGE_FLG,
//			String TRADE_DATE_FROM,
//			String TRADE_DATE_TO,
//			String BROKER_CODE,
//			String EMP_CODE,
//			String BRANCH_CODE,
//			String privId,
//			List<String> chargeCodeList,
//			boolean unlimit,
//			String sessionId,
//			String userId,
//			String pattern
//    	    ) throws Exception {
//
//    	        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getTradeCommissionTotal");
//
//    	        return dao.getTradeCommissionTotal(
//    	          UNIT_DETAIL,			//集計単位 '0':営業員毎、'1'：顧客毎
//    	          BUTEN_CODE,					//部店コード
//    	          ACCOUNT_NUMBER,				//口座番号
//    	          PRODUT_CD_LIST,				//証券種別--複数可能
//    	          NO_PRODUT_CD,					//対象外証券種別
//    	          BRAND_CODE,					//銘柄コード
//    	          EXCHANGE_FLG,					//為替を選択フラグ  0：為替のみ, 1：為替あり, 2：為替なし
//    	          TRADE_DATE_FROM,				//期間指定From
//    	          TRADE_DATE_TO,				//期間指定To
//    	          BROKER_CODE,					//仲介業者コード
//    	          EMP_CODE,						//営業員コード
//    	          BRANCH_CODE,					//支店コード
//    	          privId,						//権限コード
//    	          chargeCodeList,				//管理対象営業員リスト
//    	          unlimit,						//件数制限あるフラグ--true:制限なし、flase制限あり
//    	          sessionId,
//    	          userId,
//    	          pattern
//    	        );
//    }

    public int countMediateBranchByBrokerCode(String brokerCode) throws Exception {
        if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.countMediateBranchByBrokerCode");

        return dao.countMediateBranchByBrokerCode(brokerCode);
    }

//    @Override
//	public DataList<CustomerAttributeMasterModel> getAllCustomerAttributeMaster() throws Exception {
//		if (logger.isDebugEnabled()) logger.debug("AdminServiceImplL.getAllCustomerAttributeMaster");
//
//		return dao.getAllCustomerAttributeMaster();
//	}
}
