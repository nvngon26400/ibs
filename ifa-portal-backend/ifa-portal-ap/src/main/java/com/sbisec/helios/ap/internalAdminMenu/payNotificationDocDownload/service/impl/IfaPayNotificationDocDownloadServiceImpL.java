package com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.IfaPayNotificationDocDownloadDao;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dao.model.IfaPayNotificationDocDownloadSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA001DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoDtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA002DtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoRequest;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.dto.IfaPayNotificationDocDownloadA003aDtoResponse;
import com.sbisec.helios.ap.internalAdminMenu.payNotificationDocDownload.service.IfaPayNotificationDocDownloadService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB0405-01
 * 画面名：支払通知書ダウンロード
 *
 * @author SCSK 仁井田
 2024/06/21 新規作成
 */
@Component(value = "cmpIfaPayNotificationDocDownloadService")
public class IfaPayNotificationDocDownloadServiceImpL implements IfaPayNotificationDocDownloadService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaPayNotificationDocDownloadServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------
    /** 支払通知書の格納先が取得できないため、当画面は利用できません。 */
    private static final String ERRORS_PAYMENT_NOTICE_STRAGE_NOT_FOUND = "errors.paymentNotice.storage.notFound";
    
    /** 参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** 支払通知書が更新されている可能性があります。再度検索して最新情報を取得して下さい。 */
    private static final String ERRORS_PAYMENT_NOTICE_NOT_FOUND = "errors.paymentNotice.notfound";
    
    /** サーバ側でエラーが発生しました。 */
    private static final String ERRORS_SERVERERROR = "errors.serverError";
    
    // --------------------------------
    // ファイル名
    // --------------------------------    
    private static final String PAYMENT_NOTICE = "/PAYMENT_NOTICE";
    
    private static final String EXTENSION = ".pdf";
    
    private static final String UNDERBER = "_";
    
    @Autowired
    private IfaPayNotificationDocDownloadDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * DTO リクエスト：IfaPayNotificationDocDownloadA001DtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA001DtoResponse
     * model レスポンス：IfaPayNotificationDocDownloadSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadA001DtoResponse> initializeA001(
            IfaPayNotificationDocDownloadA001DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPayNotificationDocDownloadServiceImplL.initializeA001");
        }
        
        DataList<IfaPayNotificationDocDownloadA001DtoResponse> dtoRes = new DataList<IfaPayNotificationDocDownloadA001DtoResponse>();
        List<IfaPayNotificationDocDownloadA001DtoResponse> resList = new ArrayList<IfaPayNotificationDocDownloadA001DtoResponse>();
        
        // 仲介業者支払通知書の格納先ファイルディレクトリを取得(SQL001)
        DataList<IfaPayNotificationDocDownloadSql001ResponseModel> selSql001Res = new DataList<IfaPayNotificationDocDownloadSql001ResponseModel>();
        selSql001Res = dao.selectIfaPayNotificationDocDownloadSql001();
        
        // SQL001の取得結果が0件の場合
        if (ObjectUtils.isEmpty(selSql001Res) || selSql001Res.size() < 1) {
            // エラーメッセージを返す
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_PAYMENT_NOTICE_STRAGE_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_PAYMENT_NOTICE_STRAGE_NOT_FOUND));
            return dtoRes;
        }
        
        // レスポンスを設定
        IfaPayNotificationDocDownloadA001DtoResponse res = new IfaPayNotificationDocDownloadA001DtoResponse();
        res.setFileDirectory(selSql001Res.getDataList().get(0).getFileDirectory());
        resList.add(res);
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * DTO リクエスト：IfaPayNotificationDocDownloadA002DtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA002DtoResponse
     * model リクエスト：IfaPayNotificationDocDownloadSql002RequestModel
     * model レスポンス：IfaPayNotificationDocDownloadSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaPayNotificationDocDownloadA002DtoResponse> displayA002(
            IfaPayNotificationDocDownloadA002DtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPayNotificationDocDownloadServiceImplL.displayA002");
        }
        
        DataList<IfaPayNotificationDocDownloadA002DtoResponse> dtoRes = new DataList<IfaPayNotificationDocDownloadA002DtoResponse>();
        List<IfaPayNotificationDocDownloadA002DtoResponse> resList = new ArrayList<IfaPayNotificationDocDownloadA002DtoResponse>();
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        
        List<BrokerCharge> brokerChargeList = new ArrayList<BrokerCharge>();
        
        // ユーザ共通情報.権限コード != SBI証券本店の場合
        if (!StringUtils.equals(privId, PrivId.HEAD_OFFICE.getId())) {
            // FCT030.仲介業者営業員リストを取得
            InputFct030Dto fct030InputDto = new InputFct030Dto();
            OutputFct030Dto fct030OutputDto = fct030.getData(fct030InputDto);
            
            // FCT030.仲介業者営業員リストの件数が0件の場合
            if (fct030OutputDto == null || fct030OutputDto.getBrokerChargeList().size() < 1) {
                // エラーメッセージを返す
                dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
                return dtoRes;
            }
            
            brokerChargeList = fct030OutputDto.getBrokerChargeList();
        }
        
        // SQL002のリクエスト値を設定
        IfaPayNotificationDocDownloadSql002RequestModel selSql002Req = new IfaPayNotificationDocDownloadSql002RequestModel();
        BeanUtils.copyProperties(selSql002Req, dtoReq);
        selSql002Req.setBrokerChargeList(brokerChargeList);
        selSql002Req.setPrivId(privId);
        
        // 仲介業者支払通知書情報リストを取得（SQL002）
        DataList<IfaPayNotificationDocDownloadSql002ResponseModel> selSql002ResList = new DataList<IfaPayNotificationDocDownloadSql002ResponseModel>();
        selSql002ResList = dao.selectIfaPayNotificationDocDownloadSql002(selSql002Req);
        
        // 仲介業者支払通知書情報リストの件数が0件の場合
        if (ObjectUtils.isEmpty(selSql002ResList) || selSql002ResList.size() < 1) {
            // メッセージを返す
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            return dtoRes;
        }
        
        List<IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoDtoResponse> payNotificationDocInfoList = 
                new ArrayList<IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoDtoResponse>();
        
        //　レスポンスを設定
        for (IfaPayNotificationDocDownloadSql002ResponseModel selSql002Res : selSql002ResList.getDataList()) {
            IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoDtoResponse payNotificationDocInfo = new IfaPayNotificationDocDownloadA002BrokerPayNotificationDocInfoDtoResponse();
            BeanUtils.copyProperties(payNotificationDocInfo, selSql002Res);
            payNotificationDocInfoList.add(payNotificationDocInfo);
            
        }
        IfaPayNotificationDocDownloadA002DtoResponse res = new IfaPayNotificationDocDownloadA002DtoResponse();
        res.setBrokerPayNotificationDocInfoDateList(payNotificationDocInfoList);
        if (dtoReq.getBrokerCodeList() != null && dtoReq.getBrokerCodeList().size() > 0) {
            res.setBeforeSearchBrokerCode(dtoReq.getBrokerCodeList().stream().collect(Collectors.joining(",")));            
        }
        res.setBeforeSearchChkBrokerCodeExclude(dtoReq.getChkBrokerCodeExclude());
        res.setBeforeSearchTargetDateYmFrom(dtoReq.getTargetDateYmFrom());
        res.setBeforeSearchTargetDateYmTo(dtoReq.getTargetDateYmTo());
        resList.add(res);
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
    /**
     * アクションID：A003a
     * アクション名：ダウンロード
     * DTO リクエスト：IfaPayNotificationDocDownloadA003aDtoRequest
     * DTO レスポンス：IfaPayNotificationDocDownloadA003aDtoResponse
     * model リクエスト：IfaPayNotificationDocDownloadSql003RequestModel
     * model レスポンス：IfaPayNotificationDocDownloadSql003ResponseModel
     * model リクエスト：IfaPayNotificationDocDownloadSql004RequestModel
     * model レスポンス：IfaPayNotificationDocDownloadSql004ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Transactional(rollbackFor = Throwable.class)
    public DataList<IfaPayNotificationDocDownloadA003aDtoResponse> pdfDownloadA003a(
            IfaPayNotificationDocDownloadA003aDtoRequest dtoReq) throws Exception {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaPayNotificationDocDownloadServiceImplL.pdfDownloadA003a");
        }
        
        DataList<IfaPayNotificationDocDownloadA003aDtoResponse> dtoRes = new DataList<IfaPayNotificationDocDownloadA003aDtoResponse>();
        List<IfaPayNotificationDocDownloadA003aDtoResponse> resList = new ArrayList<IfaPayNotificationDocDownloadA003aDtoResponse>();
        
        // SQL003のリクエスト値を設定
        IfaPayNotificationDocDownloadSql003RequestModel selSql003Req = new IfaPayNotificationDocDownloadSql003RequestModel();
        BeanUtils.copyProperties(selSql003Req, dtoReq);
        
        // 仲介業者支払通知書情報を取得（SQL003）
        DataList<IfaPayNotificationDocDownloadSql003ResponseModel> selSql003Res = new DataList<IfaPayNotificationDocDownloadSql003ResponseModel>();
        selSql003Res = dao.selectIfaPayNotificationDocDownloadSql003(selSql003Req);
        
        // 仲介業者支払通知書情報の件数が0件の場合
        if (ObjectUtils.isEmpty(selSql003Res) || selSql003Res.size() < 1) {
            // エラーメッセージをを返す
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_PAYMENT_NOTICE_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_PAYMENT_NOTICE_NOT_FOUND));
            return dtoRes;
        }
        
        // 保管ファイル名を算出
        String archiveFileName = StringUtil.EMPTY_STRING;
        if (StringUtils.equals(dtoReq.getVersionNumber(), "1")) {
            archiveFileName = dtoReq.getFileDirectory() + DateUtil.dateFormatMonth(dtoReq.getDateYm()) + PAYMENT_NOTICE
                    + UNDERBER + dtoReq.getBrokerCode() + UNDERBER + DateUtil.dateFormatMonth(dtoReq.getDateYm())
                    + EXTENSION;
        } else {
            archiveFileName = dtoReq.getFileDirectory() + DateUtil.dateFormatMonth(dtoReq.getDateYm()) + PAYMENT_NOTICE
                    + UNDERBER + dtoReq.getBrokerCode() + UNDERBER + DateUtil.dateFormatMonth(dtoReq.getDateYm())
                    + UNDERBER + dtoReq.getVersionNumber() + EXTENSION;
        }
        
        // 保管ファイル名の存在チェック
        File file = new File(archiveFileName);
        if (!file.exists()) {
            // エラーメッセージをを返す
            dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_SERVERERROR,
                    IfaCommonUtil.getMessage(ERRORS_SERVERERROR));
            return dtoRes;
        }
        
        // ユーザ共通情報.権限コードを取得
        String privId = IfaCommonUtil.getUserAccount().getPrivId();
        
        // ユーザ共通情報.権限コードか3,4,5,6,7,8,9,10のいずれか場合
        if (StringUtils.equals(privId, PrivId.B_INNER_MANAGEMENT.getId())
                || StringUtils.equals(privId, PrivId.B_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.B_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_INNER_MANAGEMENT.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_EXECUTIVE.getId())
                || StringUtils.equals(privId, PrivId.BB_SALES_REPRESENTATIVE.getId())
                || StringUtils.equals(privId, PrivId.RESPONSIBLE.getId())
                || StringUtils.equals(privId, PrivId.ALL_RESPONSIBLE.getId())) {
            
            // ユーザ共通情報.ユーザIDを取得
            String userId = IfaCommonUtil.getUserAccount().getUserId();
            
            // SQL004のリクエスト値を設定
            IfaPayNotificationDocDownloadSql004RequestModel updtSql004Req = new IfaPayNotificationDocDownloadSql004RequestModel();
            BeanUtils.copyProperties(updtSql004Req, dtoReq);
            updtSql004Req.setUserId(userId);
            
            // 仲介業者支払通知書確認日時を更新（SQL004）
            int updateCount = 0;
            updateCount = dao.updateIfaPayNotificationDocDownloadSql004(updtSql004Req);
            
            // 更新件数が0件の場合
            if (updateCount < 1) {
                // エラーメッセージをを返す
                dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.FATAL, ERRORS_PAYMENT_NOTICE_NOT_FOUND,
                        IfaCommonUtil.getMessage(ERRORS_PAYMENT_NOTICE_NOT_FOUND));
                return dtoRes;
            }
        }
        
        // ダウンロードファイル名
        String downloadFileName = selSql003Res.getDataList().get(0).getDownloadFileName();
        
        // レスポンスを設定
        IfaPayNotificationDocDownloadA003aDtoResponse res = new IfaPayNotificationDocDownloadA003aDtoResponse();
        res.setPdfFileName(archiveFileName);
        res.setPdfFileOutputName(downloadFileName);
        resList.add(res);
        
        dtoRes = IfaCommonUtil.createDataList(resList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        
        return dtoRes;
    }
    
}
