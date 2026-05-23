
package com.sbisec.helios.ap.admin.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


//@Component
@Mapper
public interface AdminMapper {


//    public List<GetTradeHistoryByCodeModel> GetTradeHistoryByCode(
//        @Param("CODE_TYPE")  String CODE_TYPE,
//		@Param("CODE_1")  String CODE_1,
//		@Param("CODE_2")  String CODE_2
//    ) throws Exception;
//
//
//
//
//    public List<GetAllTradeHistoryListModel> getAllTradeHistoryList(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//        @Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//        @Param("PRODUT_CD")  String PRODUT_CD,
//        @Param("noProdutCd")  String noProdutCd,
//        @Param("BRAND_CODE")  String BRAND_CODE,
//        @Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//        @Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//        @Param("BROKER_CODE")  String BROKER_CODE,
//        @Param("empCode")  String empCode,
//        @Param("branchCode")  String branchCode,
//        @Param("privId")  String privId,
//        @Param("chargeCodeList")  List<String> chargeCodeList,
//        @Param("ROWNUM")  String ROWNUM,
//        @Param("chkBrokerCodeExclude")  String chkBrokerCodeExclude,
//        @Param("brokerCodeExcludeList")  List<String> brokerCodeExcludeList,
//        @Param("customerAttributeList")  List<String> customerAttributeList
//    ) throws Exception;
//
//
//
//
//    public List<GetAllTradeHistoryByBrokerCodeModel> GetAllTradeHistoryByBrokerCode(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("PRODUT_CD")  String PRODUT_CD,
//@Param("BRAND_CODE")  String BRAND_CODE,
//@Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//@Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//@Param("BROKER_CODE")  String BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetAllTradeHistoryByBrokerChargeCodeModel> GetAllTradeHistoryByBrokerChargeCode(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("PRODUT_CD")  String PRODUT_CD,
//@Param("BRAND_CODE")  String BRAND_CODE,
//@Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//@Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//@Param("BROKER_CHARGE_CODE")  String BROKER_CHARGE_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetAllTradeHistoryByBrokerInChargeModel> GetAllTradeHistoryByBrokerInCharge(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("PRODUT_CD")  String PRODUT_CD,
//@Param("BRAND_CODE")  String BRAND_CODE,
//@Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//@Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//@Param("LIST_BROKER_CODE")  List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetKawaseTradeHistoryByPersonInChargeModel> GetKawaseTradeHistoryByPersonInCharge(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("BROKER_CODE")  String BROKER_CODE,
//@Param("BROKER_CHARGE_CODE")  String BROKER_CHARGE_CODE,
//@Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//@Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//@Param("CURRENCY")  String CURRENCY,
//@Param("rownum")  String rownum,
//@Param("privId") String privId,
//@Param("chargeCodeList") List<String> chargeCodeList
//    ) throws Exception;
//
//
//    public List<GetKawaseTradeHistoryBySubordinateBrokerModel> GetKawaseTradeHistoryBySubordinateBroker(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("BROKER_CODE")  String BROKER_CODE,
//@Param("BROKER_CHARGE_CODE")  String BROKER_CHARGE_CODE,
//@Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//@Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//@Param("CURRENCY")  String CURRENCY,
//@Param("LIST_BROKER_CODE")  List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetJointTradeHistoryModel> getJointTradeHistory(
//            @Param("butenCode")  String butenCode,
//            @Param("accountNumber")  String accountNumber,
//            @Param("brokerCode")  String brokerCode,
//            @Param("jointBranchCode")  String jointBranchCode,
//            @Param("produtCd")  String produtCd,
//            @Param("brandCode")  String brandCode,
//            @Param("dateFrom")  String dateFrom,
//            @Param("dateTo")  String dateTo,
//            @Param("privId")  String privId,
//            @Param("subBrokerId")  String subBrokerId,
//            @Param("employeeId")  String employeeId,
//            @Param("rownum")  String rownum
//    ) throws Exception;
//
//
//
//
//    public List<GetJointBalanceForTotalModel> GetJointBalanceForTotal(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("BROKER_CODE")  String BROKER_CODE,
//@Param("JOINT_BRANCH_CODE")  String JOINT_BRANCH_CODE,
//@Param("DATE_YMD")  String DATE_YMD,
//@Param("PRODUT_CD")  String PRODUT_CD,
//@Param("BRAND_CODE")  String BRAND_CODE,
//@Param("OWN_BRANCH_CODE")  String OWN_BRANCH_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetJointBalanceForMeiSaiModel> GetJointBalanceForMeiSai(
//        @Param("BUTEN_CODE")  String BUTEN_CODE,
//@Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//@Param("BROKER_CODE")  String BROKER_CODE,
//@Param("JOINT_BRANCH_CODE")  String JOINT_BRANCH_CODE,
//@Param("DATE_YMD")  String DATE_YMD,
//@Param("PRODUT_CD")  String PRODUT_CD,
//@Param("BRAND_CODE")  String BRAND_CODE,
//@Param("OWN_BRANCH_CODE")  String OWN_BRANCH_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetJointBalanceModel> GetJointBalance(
//        @Param("LIST_BROKER_CODE")  List<String> LIST_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetBrokerAuxiliaryBookModel> GetBrokerAuxiliaryBook(
//            @Param("CREATEDATE")  String CREATEDATE,
//    @Param("FILENAME")  String FILENAME,
//    @Param("PRODUCTCODE")  String PRODUCTCODE,
//    @Param("BROKERCODE")  String BROKERCODE
//        ) throws Exception;
//
//
//
//
//    public List<GetAuxiliaryDataModel> GetAuxiliaryData(
//            @Param("USERID")  String USERID,
//    @Param("SORTKEY")  String SORTKEY
//        ) throws Exception;
//
//
//
//
//    public Integer DeleteAuxiliaryData(
//            @Param("USERID")  String USERID
//        ) throws Exception;
//
//
//
//
//    public Integer InsertAuxiliaryValues(
//            @Param("USERID")  String USERID,
//    @Param("CREATEDATE")  String CREATEDATE,
//    @Param("BROKERCODE")  String BROKERCODE,
//    @Param("BROKERNAME")  String BROKERNAME,
//    @Param("PRODUCT")  String PRODUCT,
//    @Param("DL")  String DL,
//    @Param("FILENAME")  String FILENAME
//        ) throws Exception;
//
//
//
//
//    public List<GetCodeNameAndIdByMonthcountModel> GetCodeNameAndIdByMonthcount(
//        @Param("monthCount")  String monthCount
//    ) throws Exception;
//
//
//
//
//    public List<GetT4InfoByMonthIfMainOrBranchStoreModel> GetT4InfoByMonthIfMainOrBranchStore(
//        @Param("t4CreateAtMonth")  String t4CreateAtMonth
//    ) throws Exception;
//
//
//
//
//    public List<GetT4InfoByMonthIfNotMainOrBranchStoreModel> GetT4InfoByMonthIfNotMainOrBranchStore(
//        @Param("t4CreateAtMonth")  String t4CreateAtMonth,
//@Param("COR_REFERENCE_CONDITION_1")  String COR_REFERENCE_CONDITION_1,
//@Param("COR_REFERENCE_CONDITION_2")  String COR_REFERENCE_CONDITION_2,
//@Param("privId")  String privId,
//@Param("COR_REFERENCE_CONDITION_3")  String COR_REFERENCE_CONDITION_3,
//@Param("loginId")  String loginId
//    ) throws Exception;
//
//
//
//
//    public List<GetInfoIfNotMainOrBranchModel> GetInfoIfNotMainOrBranch(
//        @Param("t4CreateDate")  String t4CreateDate,
//@Param("COR_REFERENCE_CONDITION_1")  String COR_REFERENCE_CONDITION_1,
//@Param("COR_REFERENCE_CONDITION_2")  String COR_REFERENCE_CONDITION_2,
//@Param("privId")  String privId,
//@Param("COR_REFERENCE_CONDITION_3")  String COR_REFERENCE_CONDITION_3,
//@Param("loginId")  String loginId
//    ) throws Exception;
//
//
//
//
//    public List<GetCountIfPersonInChargeFromPsiteT4InfoPrivModel> GetCountIfPersonInChargeFromPsiteT4InfoPriv(
//        @Param("privId")  String privId,
//@Param("T4ID")  String T4ID
//    ) throws Exception;
//
//
//
//
//    public List<GetCountFromPsiteT5InfoReadModel> GetCountFromPsiteT5InfoRead(
//        @Param("T5_INFO_ID")  String T5_INFO_ID,
//@Param("T5_LOGIN")  String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public Integer InsertIntoPsiteT5InfoRead(
//        @Param("T5_INFO_ID")  String T5_INFO_ID,
//@Param("T5_LOGIN")  String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public List<GetCountIfNotPersonInChargeFromPsiteT5InfoReadModel> GetCountIfNotPersonInChargeFromPsiteT5InfoRead(
//        @Param("T5ID")  String T5ID,
//@Param("loginId")  String loginId
//    ) throws Exception;
//
//
//
//
//	public Integer UpdatePsiteT5InfoRead(
//        @Param("T5_INFO_ID")  String T5_INFO_ID,
//        @Param("T5_LOGIN")  String T5_LOGIN
//    ) throws Exception;
//
//
//
//
//    public List<GetNameAndIdTodayModel> GetNameAndIdToday(
//        @Param("today")  String today
//    ) throws Exception;
//
//
//
//
//    public List<GetConfirmationDateModel> GetConfirmationDate(
//        @Param("COR_ENFORCE_DATE")  String COR_ENFORCE_DATE,
//@Param("COR_BROKER_CODE")  String COR_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//    public List<GetSelfCheckInfoByEnforceDateModel> GetSelfCheckInfoByEnforceDate(
//        @Param("COR_ENFORCE_DATE")  String COR_ENFORCE_DATE
//    ) throws Exception;
//
//
//
//
//    public List<GetSelfCheckInfoByEnforceDateAndBrokerCodeModel> GetSelfCheckInfoByEnforceDateAndBrokerCode(
//        @Param("COR_ENFORCE_DATE")  String COR_ENFORCE_DATE,
//@Param("COR_BROKER_CODE")  String COR_BROKER_CODE
//    ) throws Exception;
//
//
//
//
//	public List<CustMgmtGetBranchDataByTypeModel> CustMgmtGetBranchDataByType(
//			@Param("branchKind")  String branchKind
//			) throws Exception;
//
//
//
//
//	public List<CustMgmtGetUserDetailByNameOrIdOrBranchModel> CustMgmtGetUserDetailByNameOrIdOrBranch(
//			@Param("userId")  String userId,
//			@Param("brokerOrBranchName")  String brokerOrBranchName,
//			@Param("employeeName")  String employeeName
//			) throws Exception;
//
//
//
//
//	public List<CustMgmtGetBrokerCodeAndKindAndNameFromMBModel> CustMgmtGetBrokerCodeAndKindAndNameFromMB(
//			@Param("branchCode")  String branchCode
//			) throws Exception;
//
//
//
//	public List<CustMgmtGetBrokerBranchCodeAndNameFromBrokerCodeModel> CustMgmtGetBrokerBranchCodeAndNameFromBrokerCode(
//			@Param("branchCode")  String branchCode,
//			@Param("brokerCode")  String brokerCode
//			) throws Exception;
//
//
//
//	public List<CustMgmtGetBrokerCCodeAndCNameFromMCIModel> CustMgmtGetBrokerCCodeAndCNameFromMCI(
//			@Param("brokerCode")  String brokerCode,
//			@Param("brokerBranchCode") String brokerBranchCode
//			) throws Exception;
//
//
//
//	public List<CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenuModel> CustMgmtGetLeafMenuItemsWithUserIdFromTbMedGovMenu(
//			@Param("userId")  String userId
//			) throws Exception;
//
//
//
//	public List<CustMgmtGetLeafMenuItemsWithPrivIdModel> CustMgmtGetLeafMenuItemsWithPrivId(
//			@Param("privId")  String privId
//			) throws Exception;
//
//
//
//	public List<CustMgmtGetLeafMenuItemsModel> CustMgmtGetLeafMenuItems(
//			) throws Exception;
//
//
//
//    public Integer CustMgmtUpdateTbMedUsersByUserId(
//    		@Param("userId")  String userId,
//    		@Param("userNm")  String userNm,
//    		@Param("password")  String password,
//    		@Param("privId")  String privId,
//    		@Param("branchId")  String branchId,
//    		@Param("brokerId")  String brokerId,
//    		@Param("subBrokerId")  String subBrokerId,
//    		@Param("employeeId")  String employeeId,
//    		@Param("employeeName")  String employeeName,
//    		@Param("governorFlag")  String governorFlag,
//    		@Param("ccsUserId")  String ccsUserId,
//    		@Param("ccsUserPw")  String ccsUserPw,
//    		@Param("uptimestampUser")  String uptimestampUser,
//    		@Param("mailAddress")  String mailAddress
//    		) throws Exception;
//
//
//
//    public Integer CustMgmtInsertIntoTbMedUsers(
//    		@Param("userId")  String userId,
//    		@Param("userNm")  String userNm,
//    		@Param("password")  String password,
//    		@Param("privId")  String privId,
//    		@Param("branchId")  String branchId,
//    		@Param("brokerId")  String brokerId,
//    		@Param("subBrokerId")  String subBrokerId,
//    		@Param("employeeId")  String employeeId,
//    		@Param("employeeName")  String employeeName,
//    		@Param("governorFlag")  String governorFlag,
//    		@Param("loginStatus")  String loginStatus,
//    		@Param("partnerUserId")  String partnerUserId,
//    		@Param("partnerUserPw")  String partnerUserPw,
//    		@Param("ccsUserId")  String ccsUserId,
//    		@Param("ccsUserPw")  String ccsUserPw,
//    		@Param("createUser")  String createUser,
//    		@Param("uptimestampUser")  String uptimestampUser,
//    		@Param("mailAddress")  String mailAddress
//    		) throws Exception;
//    
//    public Integer CustMgmtInsertIntoTbMedVerifyUsers(
//    		@Param("userId")  String userId,
//    		@Param("uptimestampUser")  String uptimestampUser,
//    		@Param("mailAddress")  String mailAddress,
//    		@Param("privId")  String privId
//    		) throws Exception;
//
//
//
//	public Integer CustMgmtDeleteFromTbMedGovMenuByUserId(
//			@Param("userId")  String userId
//			) throws Exception;
//
//
//	public Integer CustMgmtDeleteUserFromUserById(
//			@Param("userId")  String userId
//			) throws Exception;
//
//	public Integer CustMgmtDeleteUserFromVerifyUsersById(
//			@Param("userId")  String userId
//			) throws Exception;
//
//
//	public Integer CustMgmtDeleteUserFromGovMenuById(
//			@Param("userId")  String userId
//			) throws Exception;
//
//
//	public Integer CustMgmtInsertIntoTbMedGovMenu(
//			@Param("userId")  String userId,
//			@Param("menuId")  String menuId,
//			@Param("createUser")  String createUser,
//			@Param("uptimestampUser")  String uptimestampUser
//			) throws Exception;
//
//
//	public Integer CustMgmtUpdateCcsData(
//			@Param("userId")  String userId,
//			@Param("ccsUserId")  String ccsUserId,
//			@Param("ccsUserPw")  String ccsUserPw
//			) throws Exception;
//
//
//    public List<GetAlertNoticeTrustModel> GetAlertNoticeTrust(
//            @Param("userId")  String userId,
//            @Param("fromDate") String fromDate,
//            @Param("toDate")   String toDate
//        ) throws Exception;
//
//
//    public List<GetAlertNoticeTrustFileModel>GetAlertNoticeTrustFile(
//    		@Param("createDate") String createDate,
//    		@Param("fileName") String fileName,
//    		@Param("brokerCode") String brokerCode
//    		) throws Exception;
//
//    public Integer DeleteAlertNoticeTrust(
//       		@Param("userId") String userId
//    		) throws Exception;
//
//    public Integer InsertAlertNoticeTrust(
//       		@Param("userId") String userId,
//    		@Param("createDate") String createDate,
//    		@Param("brokerCode") String brokerCode,
//    		@Param("brokerName") String brokerName,
//    		@Param("reserv") String reserv,
//    		@Param("dl") String dl,
//    		@Param("fileName") String fileName
//    		) throws Exception;
//
//    public List<GetInfoRegistrationTypesModel> getInfoRegistrationTypes(
//            @Param("functionId")  String functionId
//    		) throws Exception;
//
//    public List<GetTradeCommissionTotalModel> getTradeCommissionTotal(
//            @Param("UNIT_DETAIL")  String UNIT_DETAIL,
//            @Param("BUTEN_CODE")  String BUTEN_CODE,
//            @Param("ACCOUNT_NUMBER")  String ACCOUNT_NUMBER,
//            @Param("PRODUT_CD_LIST")  List<String> PRODUT_CD_LIST,
//            @Param("NO_PRODUT_CD")  String NO_PRODUT_CD,
//            @Param("BRAND_CODE")  String BRAND_CODE,
//            @Param("EXCHANGE_FLG")  String EXCHANGE_FLG,
//            @Param("TRADE_DATE_FROM")  String TRADE_DATE_FROM,
//            @Param("TRADE_DATE_TO")  String TRADE_DATE_TO,
//            @Param("BROKER_CODE")  String BROKER_CODE,
//            @Param("EMP_CODE")  String EMP_CODE,
//            @Param("BRANCH_CODE")  String BRANCH_CODE,
//            @Param("privId")  String privId,
//            @Param("chargeCodeList")  List<String> chargeCodeList,
//            @Param("ROWNUM")  String ROWNUM
//        ) throws Exception;

    public int countMediateBranchByBrokerCode(
            @Param("brokerCode")  String brokerCode
    		) throws Exception;

//    public List<CustomerAttributeMasterModel> getAllCustomerAttributeMaster() throws Exception;
}
