package com.sbisec.helios.ap.compliance.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sbibits.earth.model.RowCount;
import com.sbisec.helios.ap.compliance.model.GetSelectCountModel;
import com.sbisec.helios.ap.compliance.model.getCommunicationDateforSelectModel;
import com.sbisec.helios.ap.compliance.model.getCorComplianceCommunicationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetComplianceLetterListOfMustReadDates;
import com.sbisec.helios.ap.compliance.model.GetCorComplianceConfirmationObjectModel;
import com.sbisec.helios.ap.compliance.model.GetIdAndDirectoryModel;

/**
 * コンプライアンス
 *
 * @author SCSK
 *
 */
@Mapper
public interface ComplianceMapper {
    
    public List<RowCount> countMissedComplianceForPreviousMonths(@Param("userId") String userId,
            @Param("brokerCode") String brokerCode,
            @Param("reminderComplianceStartYm") String reminderComplianceStartYm,
            @Param("currentMonth") String currentMonth) throws Exception;
    
    public List<RowCount> countMissedComplianceForTargetMonth(@Param("userId") String userId,
            @Param("brokerCode") String brokerCode, @Param("yearMonth") String yearMonth) throws Exception;
    
    public List<RowCount> countUnreadSelfcheckLettersForMonth(@Param("brokerCode") String brokerCode,
            @Param("yearMonth") String yearMonth) throws Exception;
    
    public List<GetIdAndDirectoryModel> GetIdAndDirectory(@Param("FUNC_ID") String FUNC_ID,
            @Param("CAT_ID") String CAT_ID) throws Exception;
    
    public Integer insertPrivateInformationDownloadRecord(@Param("userId") String userId,
            @Param("nameFlag") String nameFlag, @Param("addressFlag") String addressFlag,
            @Param("telnoFlag") String telnoFlag, @Param("officeFlag") String officeFlag,
            @Param("birthFlag") String birthFlag, @Param("sexFlag") String sexFlag,
            @Param("employmentFlag") String employmentFlag, @Param("occupationFlag") String occupationFlag,
            @Param("butenAccountFlag") String butenAccountFlag, @Param("emailFlag") String emailFlag,
            @Param("drawersAccountFlag") String drawersAccountFlag, @Param("document") String document,
            @Param("dataCount") String dataCount, @Param("transactionDesc") String transactionDesc,
            @Param("transferCode") String transferCode, @Param("deposit") String deposit,
            @Param("donation") String donation, @Param("depository") String depository,
            @Param("depositoryTerm") String depositoryTerm, @Param("diposalMethod") String diposalMethod,
            @Param("diposalFlag") String diposalFlag, @Param("diposalDate") String diposalDate,
            @Param("depositOutline") String depositOutline, @Param("donationOutline") String donationOutline,
            @Param("depositoryOutline") String depositoryOutline, @Param("createBy") String createBy,
            @Param("brokerId") String brokerId, @Param("initialGetInfoUserName") String initialGetInfoUserName)
            throws Exception;
    
    public List<GetSelectCountModel> GetSelectCount(@Param("COMDATE") String COMDATE) throws Exception;
    
    public Integer InsertIntoCOR_COMPLIANCE_COMMUNICATION(@Param("COMMUNICATIONDATE") String COMMUNICATIONDATE,
            @Param("TITLE") String TITLE, @Param("BRIEF") String BRIEF, @Param("FILENAME1") String FILENAME1,
            @Param("FILENAME2") String FILENAME2, @Param("FILENAME3") String FILENAME3,
            @Param("FILEDESC1") String FILEDESC1, @Param("FILEDESC2") String FILEDESC2,
            @Param("FILEDESC3") String FILEDESC3, @Param("CONTENTS") String CONTENTS,
            @Param("HONBANFLG") String HONBANFLG, @Param("USERID") String USERID, @Param("USERID1") String USERID1,
            @Param("CONTENTSFILE") String CONTENTSFILE) throws Exception;
    
    public List<getCorComplianceCommunicationObjectModel> getCorComplianceCommunicationObject(
            @Param("COR_LEC_ID") String COR_LEC_ID) throws Exception;
    
    public Integer UpdComplianceContentsFullByLectId(@Param("COMMUNICATIONDATE") String COMMUNICATIONDATE,
            @Param("TITLE") String TITLE, @Param("BRIEF") String BRIEF, @Param("FILENAME1") String FILENAME1,
            @Param("FILENAME2") String FILENAME2, @Param("FILENAME3") String FILENAME3,
            @Param("FILEDESC1") String FILEDESC1, @Param("FILEDESC2") String FILEDESC2,
            @Param("FILEDESC3") String FILEDESC3, @Param("CONTENTS") String CONTENTS,
            @Param("CONTENTSFILE") String CONTENTSFILE, @Param("USERID") String USERID,
            @Param("LECTUREID") String LECTUREID) throws Exception;
    
    public List<GetCorComplianceConfirmationObjectModel> GetCorComplianceConfirmationObject(
            @Param("COR_LEC_ID") String COR_LEC_ID, @Param("COR_USER_ID") String COR_USER_ID,
            @Param("brokerCode") String brokerCode) throws Exception;
    
    public List<getCommunicationDateforSelectModel> getCommunicationDateforSelect(@Param("FROMDATE") String FROMDATE,
            @Param("TODATE") String TODATE) throws Exception;
    
    public List<GetComplianceLetterListOfMustReadDates> getComplianceLetterListOfMustReadDates(
            @Param("userId") String userId, @Param("brokerCode") String brokerCode,
            @Param("reminderComplianceStartYm") String reminderComplianceStartYm,
            @Param("currentMonth") String currentMonth) throws Exception;
    
    public Integer InsertCorComplianceConfirmation(@Param("COR_LEC_ID") String COR_LEC_ID,
            @Param("COR_USER_ID") String COR_USER_ID, @Param("COR_CONFIRMATION_FLG") String COR_CONFIRMATION_FLG,
            @Param("COR_CREATE_BY") String COR_CREATE_BY, @Param("COR_UPDATE_BY") String COR_UPDATE_BY)
            throws Exception;
    
    public Integer updateCorComplianceConfirmation(@Param("COR_LEC_ID") String COR_LEC_ID,
            @Param("COR_USER_ID") String COR_USER_ID, @Param("COR_CONFIRMATION_FLG") String COR_CONFIRMATION_FLG,
            @Param("COR_UPDATE_BY") String COR_UPDATE_BY) throws Exception;
}
