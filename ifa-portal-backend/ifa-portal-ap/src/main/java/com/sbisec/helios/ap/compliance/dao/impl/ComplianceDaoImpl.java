package com.sbisec.helios.ap.compliance.dao.impl;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.sbibits.earth.dao.RowSelectableDao;
import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.RowCount;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.compliance.dao.ComplianceDao;
import com.sbisec.helios.ap.compliance.dao.mapper.ComplianceMapper;
import com.sbisec.helios.ap.compliance.model.GetComplianceLetterListOfMustReadDates;
import com.sbisec.helios.ap.compliance.model.GetCorComplianceConfirmationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;
import com.sbisec.helios.ap.compliance.model.GetSelectCountModel;
import com.sbisec.helios.ap.compliance.model.getCommunicationDateforSelectModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;

@Component
public class ComplianceDaoImpl extends RowSelectableDao implements ComplianceDao {
    
    private static final Logger logger = LoggerFactory.getLogger(ComplianceDaoImpl.class);
    
    @Autowired
    private ComplianceMapper mapper;
    
    public DataList<GetSelectCountModel> GetSelectCount(String COMDATE) throws Exception {
        
        logger.debug("ComplianceDaoImpl.GetSelectCount: COMDATE");
        
        return GetSelectCountInternal(COMDATE);
    }
    
    private DataList<GetSelectCountModel> GetSelectCountInternal(String COMDATE) throws Exception {
        
        COMDATE = StringUtil.emptyToNull(COMDATE);
        
        DataList<GetSelectCountModel> sqlResult = new DataList<GetSelectCountModel>();
        sqlResult.setDataList(mapper.GetSelectCount(COMDATE));
        
        return sqlResult;
    }
    
    public Integer InsertIntoCOR_COMPLIANCE_COMMUNICATION(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String HONBANFLG, String USERID, String USERID1, String CONTENTSFILE) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.InsertIntoCOR_COMPLIANCE_COMMUNICATION: COMMUNICATIONDATE,TITLE,BRIEF,FILENAME1,FILENAME2,FILENAME3,FILEDESC1,FILEDESC2,FILEDESC3,CONTENTS,HONBANFLG,USERID,USERID1,CONTENTSFILE");
        
        return InsertIntoCOR_COMPLIANCE_COMMUNICATIONInternal(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1, FILENAME2,
                FILENAME3, FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, HONBANFLG, USERID, USERID1, CONTENTSFILE);
    }
    
    private Integer InsertIntoCOR_COMPLIANCE_COMMUNICATIONInternal(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String HONBANFLG, String USERID, String USERID1, String CONTENTSFILE) throws Exception {
        
        COMMUNICATIONDATE = StringUtil.emptyToNull(COMMUNICATIONDATE);
        TITLE = StringUtil.emptyToNull(TITLE);
        BRIEF = StringUtil.emptyToNull(BRIEF);
        FILENAME1 = StringUtil.emptyToNull(FILENAME1);
        FILENAME2 = StringUtil.emptyToNull(FILENAME2);
        FILENAME3 = StringUtil.emptyToNull(FILENAME3);
        FILEDESC1 = StringUtil.emptyToNull(FILEDESC1);
        FILEDESC2 = StringUtil.emptyToNull(FILEDESC2);
        FILEDESC3 = StringUtil.emptyToNull(FILEDESC3);
        CONTENTS = StringUtil.emptyToNull(CONTENTS);
        HONBANFLG = StringUtil.emptyToNull(HONBANFLG);
        USERID = StringUtil.emptyToNull(USERID);
        USERID1 = StringUtil.emptyToNull(USERID1);
        CONTENTSFILE = StringUtil.emptyToNull(CONTENTSFILE);
        
        int sqlResult = mapper.InsertIntoCOR_COMPLIANCE_COMMUNICATION(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1,
                FILENAME2, FILENAME3, FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, HONBANFLG, USERID, USERID1,
                CONTENTSFILE);
        
        return sqlResult;
    }
    
    public DataList<GetIdAndDirectoryModel> GetIdAndDirectory(String FUNC_ID, String CAT_ID) throws Exception {
        
        logger.debug("ComplianceDaoImpl.GetIdAndDirectory: FUNC_ID,CAT_ID");
        
        return GetIdAndDirectoryInternal(FUNC_ID, CAT_ID);
    }
    
    private DataList<GetIdAndDirectoryModel> GetIdAndDirectoryInternal(String FUNC_ID, String CAT_ID) throws Exception {
        
        FUNC_ID = StringUtil.emptyToNull(FUNC_ID);
        CAT_ID = StringUtil.emptyToNull(CAT_ID);
        
        DataList<GetIdAndDirectoryModel> sqlResult = new DataList<GetIdAndDirectoryModel>();
        sqlResult.setDataList(mapper.GetIdAndDirectory(FUNC_ID, CAT_ID));
        
        return sqlResult;
    }
    
    public Integer insertPrivateInformationDownloadRecord(String userId, String nameFlag, String addressFlag,
            String telnoFlag, String officeFlag, String birthFlag, String sexFlag, String employmentFlag,
            String occupationFlag, String butenAccountFlag, String emailFlag, String drawersAccountFlag,
            String document, String dataCount, String transactionDesc, String transferCode, String deposit,
            String donation, String depository, String depositoryTerm, String diposalMethod, String diposalFlag,
            String diposalDate, String depositOutline, String donationOutline, String depositoryOutline,
            String createBy, String brokerId, String initialGetInfoUserName) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.insertPrivateInformationDownloadRecord: String userId,String nameFlag,String addressFlag,String telnoFlag,String officeFlag,String birthFlag,String sexFlag,String employmentFlag,String occupationFlag,String butenAccountFlag,String emailFlag,String drawersAccountFlag,String document,String dataCount,String transactionDesc,String transferCode,String deposit,String donation,String depository,String depositoryTerm,String diposalMethod,String diposalFlag,String diposalDate,String depositOutline,String donationOutline,String depositoryOutline,String createBy,String brokerId,String initialGetInfoUserName");
        
        return insertPrivateInformationDownloadRecordInternal(userId, nameFlag, addressFlag, telnoFlag, officeFlag,
                birthFlag, sexFlag, employmentFlag, occupationFlag, butenAccountFlag, emailFlag, drawersAccountFlag,
                document, dataCount, transactionDesc, transferCode, deposit, donation, depository, depositoryTerm,
                diposalMethod, diposalFlag, diposalDate, depositOutline, donationOutline, depositoryOutline, createBy,
                brokerId, initialGetInfoUserName);
    }
    
    private Integer insertPrivateInformationDownloadRecordInternal(String userId, String nameFlag, String addressFlag,
            String telnoFlag, String officeFlag, String birthFlag, String sexFlag, String employmentFlag,
            String occupationFlag, String butenAccountFlag, String emailFlag, String drawersAccountFlag,
            String document, String dataCount, String transactionDesc, String transferCode, String deposit,
            String donation, String depository, String depositoryTerm, String diposalMethod, String diposalFlag,
            String diposalDate, String depositOutline, String donationOutline, String depositoryOutline,
            String createBy, String brokerId, String initialGetInfoUserName) throws Exception {
        
        nameFlag = StringUtil.emptyToNull(nameFlag);
        addressFlag = StringUtil.emptyToNull(addressFlag);
        telnoFlag = StringUtil.emptyToNull(telnoFlag);
        officeFlag = StringUtil.emptyToNull(officeFlag);
        birthFlag = StringUtil.emptyToNull(birthFlag);
        sexFlag = StringUtil.emptyToNull(sexFlag);
        employmentFlag = StringUtil.emptyToNull(employmentFlag);
        occupationFlag = StringUtil.emptyToNull(occupationFlag);
        butenAccountFlag = StringUtil.emptyToNull(butenAccountFlag);
        emailFlag = StringUtil.emptyToNull(emailFlag);
        drawersAccountFlag = StringUtil.emptyToNull(drawersAccountFlag);
        dataCount = StringUtil.emptyToNull(dataCount);
        transferCode = StringUtil.emptyToNull(transferCode);
        deposit = StringUtil.emptyToNull(deposit);
        donation = StringUtil.emptyToNull(donation);
        depository = StringUtil.emptyToNull(depository);
        depositoryTerm = StringUtil.emptyToNull(depositoryTerm);
        diposalMethod = StringUtil.emptyToNull(diposalMethod);
        diposalFlag = StringUtil.emptyToNull(diposalFlag);
        diposalDate = StringUtil.emptyToNull(diposalDate);
        depositOutline = StringUtil.emptyToNull(depositOutline);
        donationOutline = StringUtil.emptyToNull(donationOutline);
        depositoryOutline = StringUtil.emptyToNull(depositoryOutline);
        createBy = StringUtil.emptyToNull(createBy);
        brokerId = StringUtil.emptyToNull(brokerId);
        initialGetInfoUserName = StringUtil.emptyToNull(initialGetInfoUserName);
        int updateCount = 0;
        updateCount = mapper.insertPrivateInformationDownloadRecord(userId, nameFlag, addressFlag, telnoFlag,
                officeFlag, birthFlag, sexFlag, employmentFlag, occupationFlag, butenAccountFlag, emailFlag,
                drawersAccountFlag, document, dataCount, transactionDesc, transferCode, deposit, donation, depository,
                depositoryTerm, diposalMethod, diposalFlag, diposalDate, depositOutline, donationOutline,
                depositoryOutline, createBy, brokerId, initialGetInfoUserName);
        return updateCount;
    }
    
    @Override
    public DataList<RowCount> countMissedComplianceForPreviousMonths(String userId, String brokerCode,
            String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.countMissedComplianceForPreviousMonths: userId, brokerCode, reminderComplianceStartYm, currentMonth");
        
        return countMissedComplianceForPreviousMonthsInternal(userId, brokerCode, reminderComplianceStartYm,
                currentMonth);
    }
    
    private DataList<RowCount> countMissedComplianceForPreviousMonthsInternal(String userId, String brokerCode,
            String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        userId = StringUtil.emptyToNull(userId);
        reminderComplianceStartYm = StringUtil.emptyToNull(reminderComplianceStartYm);
        currentMonth = StringUtil.emptyToNull(currentMonth);
        
        DataList<RowCount> dataList = new DataList<RowCount>();
        dataList.setDataList(mapper.countMissedComplianceForPreviousMonths(userId, brokerCode,
                reminderComplianceStartYm, currentMonth));
        
        return dataList;
    }
    
    @Override
    public DataList<RowCount> countMissedComplianceForTargetMonth(String userId, String brokerCode, String yearMonth)
            throws Exception {
        
        logger.debug("ComplianceDaoImpl.countMissedComplianceForTargetMonth: userId, brokerCode, yearMonth");
        
        return countMissedComplianceForTargetMonthInternal(userId, brokerCode, yearMonth);
    }
    
    private DataList<RowCount> countMissedComplianceForTargetMonthInternal(String userId, String brokerCode,
            String yearMonth) throws Exception {
        
        userId = StringUtil.emptyToNull(userId);
        yearMonth = StringUtil.emptyToNull(yearMonth);
        
        DataList<RowCount> dataList = new DataList<RowCount>();
        dataList.setDataList(mapper.countMissedComplianceForTargetMonth(userId, brokerCode, yearMonth));
        
        return dataList;
    }
    
    @Override
    public DataList<RowCount> countUnreadSelfcheckLettersForMonth(String brokerCode, String yearMonth)
            throws Exception {
        
        logger.debug("ComplianceDaoImpl.countUnreadSelfcheckLettersForMonth: brokerCode, yearMonth");
        
        return countUnreadSelfcheckLettersForMonthInternal(brokerCode, yearMonth);
    }
    
    private DataList<RowCount> countUnreadSelfcheckLettersForMonthInternal(String brokerCode, String yearMonth)
            throws Exception {
        
        brokerCode = StringUtil.emptyToNull(brokerCode);
        yearMonth = StringUtil.emptyToNull(yearMonth);
        
        DataList<RowCount> dataList = new DataList<RowCount>();
        dataList.setDataList(mapper.countUnreadSelfcheckLettersForMonth(brokerCode, yearMonth));
        
        return dataList;
    }
    
    public DataList<getCorComplianceCommunicationObjectModel> getCorComplianceCommunicationObject(String COR_LEC_ID)
            throws Exception {
        
        logger.debug("ComplianceDaoImpl.getCorComplianceCommunicationObject: COR_LEC_ID");
        
        return getCorComplianceCommunicationObjectInternal(COR_LEC_ID);
    }
    
    private DataList<getCorComplianceCommunicationObjectModel> getCorComplianceCommunicationObjectInternal(
            String COR_LEC_ID) throws Exception {
        
        COR_LEC_ID = StringUtil.emptyToNull(COR_LEC_ID);
        
        DataList<getCorComplianceCommunicationObjectModel> sqlResult = new DataList<getCorComplianceCommunicationObjectModel>();
        sqlResult.setDataList(mapper.getCorComplianceCommunicationObject(COR_LEC_ID));
        
        return sqlResult;
    }
    
    public Integer UpdComplianceContentsFullByLectId(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String CONTENTSFILE, String USERID, String LECTUREID) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.UpdComplianceContentsFullByLectId: COMMUNICATIONDATE,TITLE,BRIEF,FILENAME1,FILENAME2,FILENAME3,FILEDESC1,FILEDESC2,FILEDESC3,CONTENTS,CONTENTSFILE,USERID,LECTUREID");
        
        return UpdComplianceContentsFullByLectIdInternal(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1, FILENAME2,
                FILENAME3, FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, CONTENTSFILE, USERID, LECTUREID);
    }
    
    private Integer UpdComplianceContentsFullByLectIdInternal(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String CONTENTSFILE, String USERID, String LECTUREID) throws Exception {
        
        COMMUNICATIONDATE = StringUtil.emptyToNull(COMMUNICATIONDATE);
        TITLE = StringUtil.emptyToNull(TITLE);
        BRIEF = StringUtil.emptyToNull(BRIEF);
        FILENAME1 = StringUtil.emptyToNull(FILENAME1);
        FILENAME2 = StringUtil.emptyToNull(FILENAME2);
        FILENAME3 = StringUtil.emptyToNull(FILENAME3);
        FILEDESC1 = StringUtil.emptyToNull(FILEDESC1);
        FILEDESC2 = StringUtil.emptyToNull(FILEDESC2);
        FILEDESC3 = StringUtil.emptyToNull(FILEDESC3);
        CONTENTS = StringUtil.emptyToNull(CONTENTS);
        CONTENTSFILE = StringUtil.emptyToNull(CONTENTSFILE);
        USERID = StringUtil.emptyToNull(USERID);
        LECTUREID = StringUtil.emptyToNull(LECTUREID);
        
        int sqlResult = mapper.UpdComplianceContentsFullByLectId(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1, FILENAME2,
                FILENAME3, FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, CONTENTSFILE, USERID, LECTUREID);
        
        return sqlResult;
    }
    
    public DataList<GetCorComplianceConfirmationObjectModel> GetCorComplianceConfirmationObject(String COR_LEC_ID,
            String COR_USER_ID, String brokerCode) throws Exception {
        
        logger.debug("ComplianceDaoImpl.GetCorComplianceConfirmationObject: COR_LEC_ID,COR_USER_ID,brokerCode");
        
        return GetCorComplianceConfirmationObjectInternal(COR_LEC_ID, COR_USER_ID, brokerCode);
    }
    
    private DataList<GetCorComplianceConfirmationObjectModel> GetCorComplianceConfirmationObjectInternal(
            String COR_LEC_ID, String COR_USER_ID, String brokerCode) throws Exception {
        
        COR_LEC_ID = StringUtil.emptyToNull(COR_LEC_ID);
        COR_USER_ID = StringUtil.emptyToNull(COR_USER_ID);
        brokerCode = StringUtil.emptyToNull(brokerCode);
        
        DataList<GetCorComplianceConfirmationObjectModel> sqlResult = new DataList<GetCorComplianceConfirmationObjectModel>();
        sqlResult.setDataList(mapper.GetCorComplianceConfirmationObject(COR_LEC_ID, COR_USER_ID, brokerCode));
        
        return sqlResult;
    }
    
    public DataList<getCommunicationDateforSelectModel> getCommunicationDateforSelect(String FROMDATE, String TODATE) throws Exception {
        
        logger.debug("ComplianceDaoImpl.getCommunicationDateforSelect: FROMDATE,TODATE,whereFlg");
        
        return getCommunicationDateforSelectInternal(FROMDATE, TODATE);
    }
    
    private DataList<getCommunicationDateforSelectModel> getCommunicationDateforSelectInternal(String FROMDATE,
            String TODATE) throws Exception {
        
        FROMDATE = StringUtil.emptyToNull(FROMDATE);
        TODATE = StringUtil.emptyToNull(TODATE);
        
        DataList<getCommunicationDateforSelectModel> sqlResult = new DataList<getCommunicationDateforSelectModel>();
        sqlResult.setDataList(mapper.getCommunicationDateforSelect(FROMDATE, TODATE));
        
        return sqlResult;
    }
    
    @Override
    public DataList<GetComplianceLetterListOfMustReadDates> getComplianceLetterListOfMustReadDates(String userId,
            String brokerCode, String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.getComplianceLetterListOfMustReadDates: userId, brokerCode reminderComplianceStartYm, currentMonth");
        
        return getComplianceLetterListOfMustReadDatesInternal(userId, brokerCode, reminderComplianceStartYm,
                currentMonth);
    }
    
    private DataList<GetComplianceLetterListOfMustReadDates> getComplianceLetterListOfMustReadDatesInternal(
            String userId, String brokerCode, String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        userId = StringUtil.emptyToNull(userId);
        brokerCode = StringUtil.emptyToNull(brokerCode);
        reminderComplianceStartYm = StringUtil.emptyToNull(reminderComplianceStartYm);
        currentMonth = StringUtil.emptyToNull(currentMonth);
        
        DataList<GetComplianceLetterListOfMustReadDates> dataList = new DataList<GetComplianceLetterListOfMustReadDates>();
        dataList.setDataList(mapper.getComplianceLetterListOfMustReadDates(userId, brokerCode,
                reminderComplianceStartYm, currentMonth));
        
        return dataList;
    }
    
    public Integer InsertCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_CREATE_BY, String COR_UPDATE_BY) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.InsertCorComplianceConfirmation: COR_LEC_ID,COR_USER_ID,COR_CONFIRMATION_FLG,COR_CREATE_BY,COR_UPDATE_BY");
        
        return InsertCorComplianceConfirmationInternal(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG, COR_CREATE_BY,
                COR_UPDATE_BY);
    }
    
    private Integer InsertCorComplianceConfirmationInternal(String COR_LEC_ID, String COR_USER_ID,
            String COR_CONFIRMATION_FLG, String COR_CREATE_BY, String COR_UPDATE_BY) throws Exception {
        
        COR_LEC_ID = StringUtil.emptyToNull(COR_LEC_ID);
        COR_USER_ID = StringUtil.emptyToNull(COR_USER_ID);
        COR_CONFIRMATION_FLG = StringUtil.emptyToNull(COR_CONFIRMATION_FLG);
        COR_CREATE_BY = StringUtil.emptyToNull(COR_CREATE_BY);
        COR_UPDATE_BY = StringUtil.emptyToNull(COR_UPDATE_BY);
        
        int sqlResult = 0;
        try {
            sqlResult = mapper.InsertCorComplianceConfirmation(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG,
                    COR_CREATE_BY, COR_UPDATE_BY);
        } catch (Exception e) {
            try {
                throw e.getCause();
            } catch (SQLIntegrityConstraintViolationException sqlEx) {
                logger.debug("ComplianceDaoImpl.InsertCorComplianceConfirmationInternal: found the key duplication");
            } catch (Exception ex) {
                throw e;
            } catch (Throwable th) {
                throw e;
            }
        }
        
        return sqlResult;
    }
    
    public Integer updateCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_UPDATE_BY) throws Exception {
        
        logger.debug(
                "ComplianceDaoImpl.updateCorComplianceConfirmation: COR_LEC_ID,COR_USER_ID,COR_CONFIRMATION_FLG,COR_UPDATE_BY");
        
        return updateCorComplianceConfirmationInternal(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG, COR_UPDATE_BY);
    }
    
    private Integer updateCorComplianceConfirmationInternal(String COR_LEC_ID, String COR_USER_ID,
            String COR_CONFIRMATION_FLG, String COR_UPDATE_BY) throws Exception {
        
        COR_LEC_ID = StringUtil.emptyToNull(COR_LEC_ID);
        COR_USER_ID = StringUtil.emptyToNull(COR_USER_ID);
        COR_CONFIRMATION_FLG = StringUtil.emptyToNull(COR_CONFIRMATION_FLG);
        COR_UPDATE_BY = StringUtil.emptyToNull(COR_UPDATE_BY);
        
        int sqlResult = mapper.updateCorComplianceConfirmation(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG,
                COR_UPDATE_BY);
        
        return sqlResult;
    }
}
