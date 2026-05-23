
package com.sbisec.helios.ap.admin.dao;

public interface AdminDao {


//    public DataList<GetTradeHistoryByCodeModel> GetTradeHistoryByCode(
//    String CODE_TYPE,String CODE_1,String CODE_2
//    ) throws Exception;
//
//
//
//
//    public DataList<GetAllTradeHistoryListModel> getAllTradeHistoryList(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String noProdutCd,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE, String empCode, String branchCode, String privId, List<String> chargeCodeList,boolean unlimit, String chkBrokerCodeExclude, List<String> brokerCodeExcludeList, List<String> customerAttributeList, String sessionId, String userId , String pattern
//    ) throws Exception;
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerCodeModel> GetAllTradeHistoryByBrokerCode(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerChargeCodeModel> GetAllTradeHistoryByBrokerChargeCode(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String BROKER_CHARGE_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetAllTradeHistoryByBrokerInChargeModel> GetAllTradeHistoryByBrokerInCharge(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String PRODUT_CD,String BRAND_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetKawaseTradeHistoryByPersonInChargeModel> GetKawaseTradeHistoryByPersonInCharge(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY, boolean unlimited, String privId,List<String> chargeCodeList, String sessionId, String userId
//    ) throws Exception;
//
//    public DataList<GetKawaseTradeHistoryBySubordinateBrokerModel> GetKawaseTradeHistoryBySubordinateBroker(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String BROKER_CHARGE_CODE,String TRADE_DATE_FROM,String TRADE_DATE_TO,String CURRENCY,List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetJointTradeHistoryModel> getJointTradeHistory(
//    String butenCode,String accountNumber,String brokerCode,String jointBranchCode,String produtCd,String brandCode,String dateFrom,String dateTo,String subBrokerId,String employeeId, String privId, boolean unlimit, String sessionId, String userId
//    ) throws Exception;
//
//
//    public DataList<GetJointBalanceForTotalModel> GetJointBalanceForTotal(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetJointBalanceForMeiSaiModel> GetJointBalanceForMeiSai(
//    String BUTEN_CODE,String ACCOUNT_NUMBER,String BROKER_CODE,String JOINT_BRANCH_CODE,String DATE_YMD,String PRODUT_CD,String BRAND_CODE,String OWN_BRANCH_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetJointBalanceModel> GetJointBalance(
//    List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetBrokerAuxiliaryBookModel> GetBrokerAuxiliaryBook(
//            String CREATEDATE,String FILENAME,String PRODUCTCODE,String BROKERCODE
//        ) throws Exception;
//
//
//
//
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(
//            String USERID,String SORTKEY
//        ) throws Exception;
//
//
//
//
//    public DataList<GetAuxiliaryDataModel> GetAuxiliaryData(
//            String USERID,String privId,String branchId,String brokerId,String pathRoot,String dateFrom,String dateTo,String brokerCd
//        ) throws Exception;
//
//
//
//
//    public Integer DeleteAuxiliaryData(
//            String USERID
//        ) throws Exception;
//
//
//
//
//    public Integer InsertAuxiliaryValues(
//            String USERID,String CREATEDATE,String BROKERCODE,String BROKERNAME,String PRODUCT,String DL,String FILENAME
//        ) throws Exception;
//
//
//
//
//    public DataList<GetCodeNameAndIdByMonthcountModel> GetCodeNameAndIdByMonthcount(
//    String monthCount
//    ) throws Exception;
//
//
//
//
//    public DataList<GetT4InfoByMonthIfMainOrBranchStoreModel> GetT4InfoByMonthIfMainOrBranchStore(
//    String t4CreateAtMonth
//    ) throws Exception;
//
//
//
//
//    public DataList<GetT4InfoByMonthIfNotMainOrBranchStoreModel> GetT4InfoByMonthIfNotMainOrBranchStore(
//    String t4CreateAtMonth,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception;
//
//
//
//
//    public DataList<GetInfoIfNotMainOrBranchModel> GetInfoIfNotMainOrBranch(
//    String t4CreateDate,String COR_REFERENCE_CONDITION_1,String COR_REFERENCE_CONDITION_2,String privId,String COR_REFERENCE_CONDITION_3,String loginId
//    ) throws Exception;
//
//
//
//
//    public DataList<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> GetCountIfPersonInChargeFromPsiteT4InfoPriv(
//    String privId,String T4ID
//    ) throws Exception;
//
//
//
//
//    public DataList<GetCountFromPsiteT5InfoReadModel> GetCountFromPsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public DataList<InsertIntoPsiteT5InfoReadModel> InsertIntoPsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public DataList<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> GetCountIfNotPersonInChargeFromPsiteT5InfoRead(
//    String T5ID,String loginId
//    ) throws Exception;
//
//
//
//
//    public Integer UpdatePsiteT5InfoRead(
//    String T5_INFO_ID,String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public DataList<GetNameAndIdTodayModel> GetNameAndIdToday(
//    String today
//    ) throws Exception;
//
//
//
//
//    public DataList<GetConfirmationDateModel> GetConfirmationDate(
//    String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateModel> GetSelfCheckInfoByEnforceDate(
//    String COR_ENFORCE_DATE
//    ) throws Exception;
//
//
//
//
//    public DataList<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> GetSelfCheckInfoByEnforceDateAndBrokerCode(
//    String COR_ENFORCE_DATE,String COR_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public DataList<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> CustMgmtGetBrokerCCodeAndCNameFromMCI(
//        String brokerCode, String brokerBranchCode
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetBranchDataByTypeModel> CustMgmtGetBranchDataByType(
//        String branchKind
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetUserDetailByNameOrIdOrBranchModel> CustMgmtGetUserDetailByNameOrIdOrBranch(
//        String userId,String brokerOrBranchName,String employeeName
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> CustMgmtGetBrokerCodeAndKindAndNameFromMB(
//        String branchCode
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(
//        String branchCode, String brokerCode
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(
//        String userId
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetLeafMenuItemsWithPrivIdModel> CustMgmtGetLeafMenuItemsWithPrivId(
//        String privId
//    ) throws Exception;
//
//
//
//    public DataList<CustMgmtGetLeafMenuItemsModel> CustMgmtGetLeafMenuItems(
//
//    ) throws Exception;
//
//
//
//    public Integer CustMgmtUpdateTbMedUsersByUserId(
//            String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String ccsUserId,String ccsUserPw,String uptimestampUser,String mailAddress
//        ) throws Exception;
//
//
//
//    public Integer CustMgmtInsertIntoTbMedUsers(
//        String userId,String userNm,String password,String privId,String branchId,String brokerId,String subBrokerId,String employeeId,String employeeName,String governorFlag,String loginStatus,String partnerUserId,String partnerUserPw,String ccsUserId,String ccsUserPw,String createUser,String uptimestampUser,String mailAddress
//	) throws Exception;
//
//
//
//    public Integer CustMgmtDeleteUserById(
//        String userId
//    ) throws Exception;
//
//
//
//    public Integer CustMgmtDeleteFromTbMedGovMenuByUserId(
//        String userId
//    ) throws Exception;
//
//
//
//
//    public Integer CustMgmtInsertIntoTbMedGovMenu(
//        String userId,String menuId,String createUser,String uptimestampUser
//    ) throws Exception;
//
//
//
//    public Integer CustMgmtUpdateCcsData(
//        String userId, String ccsUserId, String ccsUserPw
//	) throws Exception;
//
//
//    public DataList<GetAlertNoticeTrustModel> GetAlertNoticeTrust(
//            String userId,
//            String fromDate,
//            String toDate
//        ) throws Exception;
//
//    public DataList<GetAlertNoticeTrustFileModel>GetAlertNoticeTrustFile(
//    		String createDate,
//    		String fileName,
//    		String brokerCode
//    		) throws Exception;
//
//    public Integer DeleteAlertNoticeTrust(
//       		String userId
//    		) throws Exception;
//
//    public Integer InsertAlertNoticeTrust(
//       		String userId,
//    		String createDate,
//    		String brokerCode,
//    		String brokerName,
//    		String reserv,
//    		String dl,
//    		String fileName
//    		) throws Exception;
//
//    public DataList<GetInfoRegistrationTypesModel> getInfoRegistrationTypes(
//            String functionId
//    		) throws Exception;
//
//    public DataList<GetTradeCommissionTotalModel> getTradeCommissionTotal(
//    		String UNIT_DETAIL,
//    		String BUTEN_CODE,
//    		String ACCOUNT_NUMBER,
//    		List<String> PRODUT_CD_LIST,
//    		String NO_PRODUT_CD,
//    		String BRAND_CODE,
//    		String EXCHANGE_FLG,
//    		String TRADE_DATE_FROM,
//    		String TRADE_DATE_TO,
//    		String BROKER_CODE,
//    		String EMP_CODE,
//    		String BRANCH_CODE,
//    		String privId,
//    		List<String> chargeCodeList,
//    		boolean unlimit,
//    		String sessionId,
//    		String userId,
//    		String pattern
//    ) throws Exception;

    public int countMediateBranchByBrokerCode(String brokerCode) throws Exception;

//    public DataList<CustomerAttributeMasterModel> getAllCustomerAttributeMaster() throws Exception;
}
