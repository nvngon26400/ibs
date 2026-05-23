package com.sbisec.helios.ap.compliance.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.RowCount;
import com.sbisec.helios.ap.common.util.IfaDateUtil;
import com.sbisec.helios.ap.compliance.dao.ComplianceDao;
import com.sbisec.helios.ap.compliance.model.GetComplianceLetterListOfMustReadDates;
import com.sbisec.helios.ap.compliance.model.GetCorComplianceConfirmationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;
import com.sbisec.helios.ap.compliance.model.GetSelectCountModel;
import com.sbisec.helios.ap.compliance.model.getCommunicationDateforSelectModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * コンプライアンス
 *
 * @author SCSK
 *
 */
@Component(value = "complianceService")
public class ComplianceServiceImpl implements ComplianceService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComplianceServiceImpl.class);
    
    @Autowired
    private ComplianceDao dao;
    
    @Autowired
    private IfaDateUtil ifaDateUtil;
    
    @Override
    public DataList<RowCount> countMissedComplianceForPreviousMonths(String userId, String brokerCode,
            String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.countMissedComplianceForPreviousMonths");
        }
        currentMonth = ifaDateUtil.format("yyyyMM");
        return dao.countMissedComplianceForPreviousMonths(userId, brokerCode, reminderComplianceStartYm, currentMonth);
        
    }
    
    @Override
    public DataList<RowCount> countMissedComplianceForTargetMonth(String userId, String brokerCode, String yearMonth)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.countMissedComplianceForTargetMonth");
        }
        yearMonth = ifaDateUtil.format("yyyyMM");
        return dao.countMissedComplianceForTargetMonth(userId, brokerCode, yearMonth);
    }
    
    @Override
    public DataList<RowCount> countUnreadSelfcheckLettersForMonth(String brokerCode, String yearMonth)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.countUnreadSelfcheckLettersForMonth");
        }
        
        return dao.countUnreadSelfcheckLettersForMonth(brokerCode, yearMonth);
    }
    
    public DataList<GetIdAndDirectoryModel> GetIdAndDirectory(String FUNC_ID, String CAT_ID) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.GetIdAndDirectory");
        }
        
        return dao.GetIdAndDirectory(FUNC_ID, CAT_ID);
    }
    
    public Integer insertPrivateInformationDownloadRecord(String userId, String nameFlag, String addressFlag,
            String telnoFlag, String officeFlag, String birthFlag, String sexFlag, String employmentFlag,
            String occupationFlag, String butenAccountFlag, String emailFlag, String drawersAccountFlag,
            String document, String dataCount, String transactionDesc, String transferCode, String deposit,
            String donation, String depository, String depositoryTerm, String diposalMethod, String diposalFlag,
            String diposalDate, String depositOutline, String donationOutline, String depositoryOutline,
            String createBy, String brokerId, String initialGetInfoUserName) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.insertPrivateInformationDownloadRecord");
        }
        
        return dao.insertPrivateInformationDownloadRecord(userId, nameFlag, addressFlag, telnoFlag, officeFlag,
                birthFlag, sexFlag, employmentFlag, occupationFlag, butenAccountFlag, emailFlag, drawersAccountFlag,
                document, dataCount, transactionDesc, transferCode, deposit, donation, depository, depositoryTerm,
                diposalMethod, diposalFlag, diposalDate, depositOutline, donationOutline, depositoryOutline, createBy,
                brokerId, initialGetInfoUserName);
    }
    
    public DataList<GetSelectCountModel> GetSelectCount(String COMDATE) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.GetSelectCount");
        }
        
        return dao.GetSelectCount(COMDATE);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public Integer InsertIntoCOR_COMPLIANCE_COMMUNICATION(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String HONBANFLG, String USERID, String USERID1, String CONTENTSFILE) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.InsertIntoCOR_COMPLIANCE_COMMUNICATION");
        }
        
        return dao.InsertIntoCOR_COMPLIANCE_COMMUNICATION(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1, FILENAME2,
                FILENAME3, FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, HONBANFLG, USERID, USERID1, CONTENTSFILE);
    }
    
    public DataList<getCorComplianceCommunicationObjectModel> getCorComplianceCommunicationObject(String COR_LEC_ID)
            throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImpl.getCorComplianceCommunicationObject");
        }
        
        return dao.getCorComplianceCommunicationObject(COR_LEC_ID);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public Integer UpdComplianceContentsFullByLectId(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String CONTENTSFILE, String USERID, String LECTUREID) throws Exception {
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("ComplianceServiceImplL.getUpdComplianceContentsFullByLectIdList");
        
        return dao.UpdComplianceContentsFullByLectId(COMMUNICATIONDATE, TITLE, BRIEF, FILENAME1, FILENAME2, FILENAME3,
                FILEDESC1, FILEDESC2, FILEDESC3, CONTENTS, CONTENTSFILE, USERID, LECTUREID);
    }
    
    public DataList<GetCorComplianceConfirmationObjectModel> GetCorComplianceConfirmationObject(String COR_LEC_ID,
            String COR_USER_ID, String brokerCode) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.getGetCorComplianceConfirmationObjectList");
        }
        DataList<GetCorComplianceConfirmationObjectModel> dataList = dao.GetCorComplianceConfirmationObject(COR_LEC_ID, COR_USER_ID, brokerCode);
        if (dataList.get(0) == null) {
            dataList.getDataList().remove(0);
            dataList.getDataList().add(new GetCorComplianceConfirmationObjectModel());
        }
        return dataList;
    }
    
    public DataList<getCommunicationDateforSelectModel> getCommunicationDateforSelect(String FROMDATE, String TODATE) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.getgetCommunicationDateforSelectList");
        }
        
        return dao.getCommunicationDateforSelect(FROMDATE, TODATE);
    }
    
    @Override
    public DataList<GetComplianceLetterListOfMustReadDates> getComplianceLetterListOfMustReadDates(String userId,
            String brokerCode, String reminderComplianceStartYm, String currentMonth) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServicesImplL.getComplianceLetterListOfMustReadDates");
        }
        
        return dao.getComplianceLetterListOfMustReadDates(userId, brokerCode, reminderComplianceStartYm, currentMonth);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public Integer InsertCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_CREATE_BY, String COR_UPDATE_BY) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.getInsertCorComplianceConfirmationList");
        }
        
        return dao.InsertCorComplianceConfirmation(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG, COR_CREATE_BY,
                COR_UPDATE_BY);
    }
    
    @Transactional(rollbackFor = Throwable.class)
    public Integer updateCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_UPDATE_BY) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("ComplianceServiceImplL.getupdateCorComplianceConfirmationList");
        }
        
        return dao.updateCorComplianceConfirmation(COR_LEC_ID, COR_USER_ID, COR_CONFIRMATION_FLG, COR_UPDATE_BY);
    }
}
