package com.sbisec.helios.ap.compliance.service;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.model.RowCount;
import com.sbisec.helios.ap.compliance.model.GetComplianceLetterListOfMustReadDates;
import com.sbisec.helios.ap.compliance.model.GetCorComplianceConfirmationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;
import com.sbisec.helios.ap.compliance.model.GetSelectCountModel;
import com.sbisec.helios.ap.compliance.model.getCommunicationDateforSelectModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;
import com.sbisec.helios.ap.service.Service;

/**
 * コンプライアンス
 *
 * @author SCSK
 *
 */
public interface ComplianceService extends Service {
    
    public DataList<RowCount> countMissedComplianceForPreviousMonths(String userId, String brokerCode,
            String reminderComplianceStartYm, String currentMonth) throws Exception;
    
    public DataList<RowCount> countMissedComplianceForTargetMonth(String userId, String brokerCode, String yearMonth)
            throws Exception;
    
    public DataList<RowCount> countUnreadSelfcheckLettersForMonth(String brokerCode, String yearMonth) throws Exception;
    
    public DataList<GetIdAndDirectoryModel> GetIdAndDirectory(String FUNC_ID, String CAT_ID) throws Exception;
    
    public Integer insertPrivateInformationDownloadRecord(String userId, String nameFlag, String addressFlag,
            String telnoFlag, String officeFlag, String birthFlag, String sexFlag, String employmentFlag,
            String occupationFlag, String butenAccountFlag, String emailFlag, String drawersAccountFlag,
            String document, String dataCount, String transactionDesc, String transferCode, String deposit,
            String donation, String depository, String depositoryTerm, String diposalMethod, String diposalFlag,
            String diposalDate, String depositOutline, String donationOutline, String depositoryOutline,
            String createBy, String brokerId, String initialGetInfoUserName) throws Exception;
    
    public DataList<GetSelectCountModel> GetSelectCount(String COMDATE) throws Exception;
    
    public Integer InsertIntoCOR_COMPLIANCE_COMMUNICATION(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String HONBANFLG, String USERID, String USERID1, String CONTENTSFILE) throws Exception;
    
    public DataList<getCorComplianceCommunicationObjectModel> getCorComplianceCommunicationObject(String COR_LEC_ID)
            throws Exception;
    
    public Integer UpdComplianceContentsFullByLectId(String COMMUNICATIONDATE, String TITLE, String BRIEF,
            String FILENAME1, String FILENAME2, String FILENAME3, String FILEDESC1, String FILEDESC2, String FILEDESC3,
            String CONTENTS, String CONTENTSFILE, String USERID, String LECTUREID) throws Exception;
    
    public DataList<getCommunicationDateforSelectModel> getCommunicationDateforSelect(String FROMDATE, String TODATE)
            throws Exception;
    
    public DataList<GetComplianceLetterListOfMustReadDates> getComplianceLetterListOfMustReadDates(String userId,
            String brokerCode, String reminderComplianceStartYm, String currentMonth) throws Exception;
    
    public DataList<GetCorComplianceConfirmationObjectModel> GetCorComplianceConfirmationObject(String COR_LEC_ID,
            String COR_USER_ID, String brokerCode) throws Exception;
    
    public Integer InsertCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_CREATE_BY, String COR_UPDATE_BY) throws Exception;
    
    public Integer updateCorComplianceConfirmation(String COR_LEC_ID, String COR_USER_ID, String COR_CONFIRMATION_FLG,
            String COR_UPDATE_BY) throws Exception;
}
