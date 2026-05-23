package com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.IfaComplianceReportInfoRegisterManagerDao;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql001ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dao.model.IfaComplianceReportInfoRegisterManagerSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA003DtoResponseComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.dto.IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport;
import com.sbisec.helios.ap.companyEmployeeMenu.complianceReport.service.IfaComplianceReportInfoRegisterManagerService;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0505_01-01
 * 画面名：コンプライアンス通信情報登録（管理者用）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@Component(value = "cmpIfaComplianceReportInfoRegisterManagerService")
public class IfaComplianceReportInfoRegisterManagerServiceImpL implements IfaComplianceReportInfoRegisterManagerService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaComplianceReportInfoRegisterManagerServiceImpL.class);
	
    @Autowired
    private IfaComplianceReportInfoRegisterManagerDao dao;
    
    /** コンプライアンス通信情報を更新しました。 */
    private static final String COMPLIANCE_REPORT_UPDATE_SUCCESS = "コンプライアンス通信情報を更新しました。";
    
    /** コンプライアンス通信情報を更新できませんでした。*/
    private static final String COMPLIANCE_REPORT_UPDATE_ERROR = "コンプライアンス通信情報を更新できませんでした。";
    
    /** コンプライアンス通信情報を削除しました。*/
    private static final String COMPLIANCE_REPORT_DELETE_SUCCESS = "コンプライアンス通信情報を削除しました。";
    
    /** コンプライアンス通信情報を削除できませんでした。*/
    private static final String COMPLIANCE_REPORT_DELETE_ERROR = "コンプライアンス通信情報を削除できませんでした。";
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA001DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA001DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse> initializeA001(IfaComplianceReportInfoRegisterManagerA001DtoRequest dtoReq)
            throws Exception {
        DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse> dtoRes = new DataList<IfaComplianceReportInfoRegisterManagerA001DtoResponse>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportInfoRegisterManagerServiceImplL.initializeA001");
        
        List<IfaComplianceReportInfoRegisterManagerA001DtoResponse> resDtoList = new ArrayList<IfaComplianceReportInfoRegisterManagerA001DtoResponse>();
        
        IfaComplianceReportInfoRegisterManagerA001DtoResponse a001DtoResponse = new IfaComplianceReportInfoRegisterManagerA001DtoResponse();
        
        // ① コンプライアンス通信の登録一覧を表示する。
        IfaComplianceReportInfoRegisterManagerSql001RequestModel sql001Req = new IfaComplianceReportInfoRegisterManagerSql001RequestModel();
        DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> sql001Res = dao.selectIfaComplianceReportInfoRegisterManagerSql001(sql001Req);
        
        List<IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport>();
        
        for(IfaComplianceReportInfoRegisterManagerSql001ResponseModel sql001ResponseModel : sql001Res.getDataList()) {
            IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport a001DtoResponseComplianceReport = new IfaComplianceReportInfoRegisterManagerA001DtoResponseComplianceReport();
            BeanUtils.copyProperties(a001DtoResponseComplianceReport, sql001ResponseModel);
            complianceReportList.add(a001DtoResponseComplianceReport);
        }
        
        a001DtoResponse.setComplianceReportList(complianceReportList);
        resDtoList.add(a001DtoResponse);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：状態
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA003DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA003DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse> stateA003(IfaComplianceReportInfoRegisterManagerA003DtoRequest dtoReq)
            throws Exception {
        DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse> dtoRes = new DataList<IfaComplianceReportInfoRegisterManagerA003DtoResponse>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportInfoRegisterManagerServiceImplL.stateA003");
        
        List<IfaComplianceReportInfoRegisterManagerA003DtoResponse> resDtoList = new ArrayList<IfaComplianceReportInfoRegisterManagerA003DtoResponse>();
        
        IfaComplianceReportInfoRegisterManagerA003DtoResponse a003DtoResponse= new IfaComplianceReportInfoRegisterManagerA003DtoResponse();
        
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        //① 新規登録したコンプライアンス通信の状態を変更する（本番⇔仮登録）。
        IfaComplianceReportInfoRegisterManagerSql002RequestModel sql002Req = new IfaComplianceReportInfoRegisterManagerSql002RequestModel();
        sql002Req.setLectureId(dtoReq.getLectureId());
        sql002Req.setDisclosureFlag(dtoReq.getDisclosureFlag());
        sql002Req.setUpdater(userAccount.getMedUsers().getUserId());
        int  sql002Res = dao.updateIfaComplianceReportInfoRegisterManagerSql002(sql002Req);
        String msg = "";
        //③ 処理結果により、変更メッセージを設定する。
        if(sql002Res == 1) {
            //正常終了：完了メッセージを設定する。
            msg = COMPLIANCE_REPORT_UPDATE_SUCCESS;
        }else {
            //異常終了：エラーメッセージを設定する。
            msg = COMPLIANCE_REPORT_UPDATE_ERROR;
        }
        
        //② 最新の一覧を取得する。
        IfaComplianceReportInfoRegisterManagerSql001RequestModel sql001Req = new IfaComplianceReportInfoRegisterManagerSql001RequestModel();
        DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> sql001Res = dao.selectIfaComplianceReportInfoRegisterManagerSql001(sql001Req);
        List<IfaComplianceReportInfoRegisterManagerA003DtoResponseComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportInfoRegisterManagerA003DtoResponseComplianceReport>();
        for (IfaComplianceReportInfoRegisterManagerSql001ResponseModel sql001ResponseModel : sql001Res.getDataList()) {
            IfaComplianceReportInfoRegisterManagerA003DtoResponseComplianceReport a003DtoResponseComplianceReport = new IfaComplianceReportInfoRegisterManagerA003DtoResponseComplianceReport();
            BeanUtils.copyProperties(a003DtoResponseComplianceReport, sql001ResponseModel);
            complianceReportList.add(a003DtoResponseComplianceReport);
        }
        a003DtoResponse.setComplianceReportList(complianceReportList);
        
        resDtoList.add(a003DtoResponse);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", msg);
    
        return dtoRes;
    }
    
    /**
     * アクションID：A010
     * アクション名：削除
     * Dto リクエスト：IfaComplianceReportInfoRegisterManagerA010DtoRequest
     * Dto レスポンス：IfaComplianceReportInfoRegisterManagerA010DtoResponse
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse> deleteA010(IfaComplianceReportInfoRegisterManagerA010DtoRequest dtoReq)
            throws Exception {
        DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse> dtoRes = new DataList<IfaComplianceReportInfoRegisterManagerA010DtoResponse>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaComplianceReportInfoRegisterManagerServiceImplL.deleteA010");
        
        List<IfaComplianceReportInfoRegisterManagerA010DtoResponse> resDtoList = new ArrayList<IfaComplianceReportInfoRegisterManagerA010DtoResponse>();
        
        IfaComplianceReportInfoRegisterManagerA010DtoResponse a010DtoResponse = new IfaComplianceReportInfoRegisterManagerA010DtoResponse();
        
        String msg = "";
        
        //① ファイルディレクトリで指定した添付ファイルを削除する。
        //ファイルディレクトリパスを取得する。
        IfaComplianceReportInfoRegisterManagerSql003RequestModel sql003RequestModel = new IfaComplianceReportInfoRegisterManagerSql003RequestModel();
        sql003RequestModel.setFunctionId(dtoReq.getFunctionId());
        sql003RequestModel.setT9nInfoCat(dtoReq.getT9nInfoCat());
        DataList<IfaComplianceReportInfoRegisterManagerSql003ResponseModel> sql003Res= dao.selectIfaComplianceReportInfoRegisterManagerSql003(sql003RequestModel);
        
        if(null != sql003Res.getDataList() && sql003Res.getDataList().size() == 1) {
           //取得したパスでファイル名1を削除する。
           File file1 = new File(sql003Res.getDataList().get(0).getFileDirectoryPath() + dtoReq.getFileName1());
           if (file1 != null && file1.exists()) {
               if (!file1.delete()) {
                   dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                           Integer.toString(ErrorLevel.FATAL.getId()),
                           COMPLIANCE_REPORT_DELETE_ERROR);
                   return dtoRes;
               }
           }
           //取得したパスでファイル名2を削除する。
           File file2 = new File(sql003Res.getDataList().get(0).getFileDirectoryPath() + dtoReq.getFileName2());
           if (file2 != null && file2.exists()) {
               if(!file2.delete()) {
                   dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                           Integer.toString(ErrorLevel.FATAL.getId()),
                           COMPLIANCE_REPORT_DELETE_ERROR);
                   return dtoRes;
               }
           }
           //取得したパスでファイル名3を削除する。
           File file3 = new File(sql003Res.getDataList().get(0).getFileDirectoryPath() + dtoReq.getFileName3());
           if (file3 != null && file3.exists()) {
               if(!file3.delete()) {
                   dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                           Integer.toString(ErrorLevel.FATAL.getId()),
                           COMPLIANCE_REPORT_DELETE_ERROR);
                   return dtoRes;
               }
           }
           //取得したパスでコンテンツファイル名を削除する。
           File fileContents = new File(sql003Res.getDataList().get(0).getFileDirectoryPath() + dtoReq.getContentsFileName());
           if (fileContents != null && fileContents.exists()) {
               if(!fileContents.delete()) {
                   dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL,
                           Integer.toString(ErrorLevel.FATAL.getId()),
                           COMPLIANCE_REPORT_DELETE_ERROR);
                   return dtoRes;
               }
           }
           //② コンプライアンス通信を削除する。
           IfaComplianceReportInfoRegisterManagerSql004RequestModel sql004RequestMode = new IfaComplianceReportInfoRegisterManagerSql004RequestModel();
           sql004RequestMode.setLectureId(dtoReq.getLectureId());
           int sql004Res= dao.deleteIfaComplianceReportInfoRegisterManagerSql004(sql004RequestMode);
           if(sql004Res == 1) {
               msg = COMPLIANCE_REPORT_DELETE_SUCCESS;
           }else {
               msg = COMPLIANCE_REPORT_DELETE_ERROR;
           }
        }else {
            // コンプライアンス通信情報を削除できませんでした。
            msg = COMPLIANCE_REPORT_DELETE_ERROR;
        }
        //③　最新の一覧を取得する。
        IfaComplianceReportInfoRegisterManagerSql001RequestModel sql001Req = new IfaComplianceReportInfoRegisterManagerSql001RequestModel();
        DataList<IfaComplianceReportInfoRegisterManagerSql001ResponseModel> sql001Res = dao.selectIfaComplianceReportInfoRegisterManagerSql001(sql001Req);
        
        List<IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport> complianceReportList = new ArrayList<IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport>();
        
        for(IfaComplianceReportInfoRegisterManagerSql001ResponseModel sql001ResponseModel : sql001Res.getDataList()) {
            IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport a010DtoResponseComplianceReport = new IfaComplianceReportInfoRegisterManagerA010DtoResponseComplianceReport();
            BeanUtils.copyProperties(a010DtoResponseComplianceReport, sql001ResponseModel);
            complianceReportList.add(a010DtoResponseComplianceReport);
        }
        
        a010DtoResponse.setComplianceReportList(complianceReportList);
        resDtoList.add(a010DtoResponse);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "0", msg);
        
        return dtoRes;
    }

}
