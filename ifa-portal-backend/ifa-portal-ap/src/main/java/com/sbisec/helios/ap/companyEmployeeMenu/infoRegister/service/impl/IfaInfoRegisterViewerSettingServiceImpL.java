package com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.IfaInfoRegisterViewerSettingDao;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql002ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql003ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql004ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql005ResponseModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dao.model.IfaInfoRegisterViewerSettingSql006ResponseModel;

import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA002DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA009DtoResponseBroker;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA012DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoRequest;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingA016DtoResponse;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.dto.IfaInfoRegisterViewerSettingDtoResponseIndividualRep;
import com.sbisec.helios.ap.companyEmployeeMenu.infoRegister.service.IfaInfoRegisterViewerSettingService;

/**
 * 画面ID：SUB0501_01-04
 * 画面名：情報登録閲覧者設定
 *
 * @author SCSK 矢口
 2024/05/24 新規作成
 */
@Component(value = "cmpIfaInfoRegisterViewerSettingService")
public class IfaInfoRegisterViewerSettingServiceImpL implements IfaInfoRegisterViewerSettingService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaInfoRegisterViewerSettingServiceImpL.class);
    
    @Autowired
    private IfaInfoRegisterViewerSettingDao dao;
    
    /** "選択されたエントリーを見つけることができませんでした。" */
    private static final String ERRORS_EMP_NOTIFICATION_NOT_FOUND = "errors.emp.notification.notFound";
    
    /** "{0}を正しく入力して下さい。" */
    private static final String ERRORS_ACCURATELY = "errors.accurately";
    
    /** 検索結果0件エラー　"検索結果が0件です。\n条件を設定して再度検索して下さい。" */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";
    
    /** エラーメッセージ用　"仲介業者" */
    private static final String ERROR_MESSAGE_BROKER = "仲介業者";
    
    /** エラーメッセージ用　"担当者" */
    private static final String ERROR_MESSAGE_CHARGE = "担当者";
    
    /** 参照範囲 （2:権限担当者）*/
    private static final String REFERENCE_CONDITION_PRIV = "2";
    
    /** 参照範囲 （3:個別担当者）*/
    private static final String REFERENCE_CONDITION_INDIVIDUAL = "3";
    
    /**
     * アクションID：A002
     * アクション名：初期化（情報更新）
     * Dto リクエスト：IfaInfoRegisterViewerSettingA002DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA002DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingSql002RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingSql002ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA002DtoResponse> initializeInfoUpdateA002(
            IfaInfoRegisterViewerSettingA002DtoRequest dtoReq) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingA002DtoResponse> dtoRes = new DataList<IfaInfoRegisterViewerSettingA002DtoResponse>();
        List<IfaInfoRegisterViewerSettingA002DtoResponse> resDtoList = new ArrayList<IfaInfoRegisterViewerSettingA002DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoRegisterViewerSettingServiceImpL.initializeInfoUpdateA002");
        }
        
        /* ===================================================== */
        /* ①お知らせ情報を検索する                                    */
        /* ===================================================== */
        
        // SQL002の実行
        IfaInfoRegisterViewerSettingSql002RequestModel selSql002Req = new IfaInfoRegisterViewerSettingSql002RequestModel();
        selSql002Req.setNotificationId(dtoReq.getNotificationId());
        DataList<IfaInfoRegisterViewerSettingSql002ResponseModel> selSql002Res = dao
                .selectIfaInfoRegisterViewerSettingSql002(selSql002Req);
        
        /* 取得結果の件数 ＝ 0の場合、初期化は行わずにエラーメッセージを表示し、処理を終了する。 */
        if (selSql002Res == null || selSql002Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_EMP_NOTIFICATION_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_EMP_NOTIFICATION_NOT_FOUND));
            return dtoRes;
        }
        
        /* ===================================================== */
        /* ②閲覧者に関連情報を取得する。                               */
        /* ===================================================== */
        
        IfaInfoRegisterViewerSettingA002DtoResponse resDto = new IfaInfoRegisterViewerSettingA002DtoResponse();
        
        /* ①で取得した「お知らせ.参照範囲」 = 2(権限担当者)の場合 */
        if (StringUtils.equals(selSql002Res.getDataList().get(0).getCorReferenceCondition(),
                REFERENCE_CONDITION_PRIV)) {
            
            // SQL003の実行
            IfaInfoRegisterViewerSettingSql003RequestModel selSql003Req = new IfaInfoRegisterViewerSettingSql003RequestModel();
            selSql003Req.setNotificationId(dtoReq.getNotificationId());
            DataList<IfaInfoRegisterViewerSettingSql003ResponseModel> selSql003Res = dao
                    .selectIfaInfoRegisterViewerSettingSql003(selSql003Req);
            
            // 結果の格納
            List<String> notificationReferenceAuthorityList = new ArrayList<>();
            for (IfaInfoRegisterViewerSettingSql003ResponseModel sqlRes : selSql003Res.getDataList()) {
                notificationReferenceAuthorityList.add(sqlRes.getPrivId());
            }
            resDto.setRegisteredNotificationReferenceAuthorityList(notificationReferenceAuthorityList);
        }
        
        /* ①で取得した「お知らせ.参照範囲」 = 3(個別担当者)の場合 */
        if (StringUtils.equals(selSql002Res.getDataList().get(0).getCorReferenceCondition(),
                REFERENCE_CONDITION_INDIVIDUAL)) {
            
            // SQL004の実行
            IfaInfoRegisterViewerSettingSql004RequestModel selSql004Req = new IfaInfoRegisterViewerSettingSql004RequestModel();
            selSql004Req.setNotificationId(dtoReq.getNotificationId());
            DataList<IfaInfoRegisterViewerSettingSql004ResponseModel> selSql004Res = dao
                    .selectIfaInfoRegisterViewerSettingSql004(selSql004Req);
            
            // 結果の格納
            List<IfaInfoRegisterViewerSettingDtoResponseIndividualRep> individualRepList = new ArrayList<>();
            for (IfaInfoRegisterViewerSettingSql004ResponseModel sqlRes : selSql004Res.getDataList()) {
                IfaInfoRegisterViewerSettingDtoResponseIndividualRep individualRep = new IfaInfoRegisterViewerSettingDtoResponseIndividualRep();
                individualRep.setUserId(sqlRes.getUserId());
                individualRep.setEmployeeName(sqlRes.getUserName());
                individualRepList.add(individualRep);
            }
            resDto.setRegisteredIndividualRepList(individualRepList);
        }
        
        resDto.setViewerSetting(dtoReq.getCorReferenceCondition());
        resDto.setNotificationReferenceAuthorityList(dtoReq.getNotificationReferenceAuthorityList());
        resDto.setIndividualRepList(dtoReq.getIndividualRepList());
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A009
     * アクション名：仲介業者検索
     * Dto リクエスト：IfaInfoRegisterViewerSettingA009DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA009DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingSql005RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingSql005ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA009DtoResponse> intermediarySearchA009(
            IfaInfoRegisterViewerSettingA009DtoRequest dtoReq) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingA009DtoResponse> dtoRes = new DataList<IfaInfoRegisterViewerSettingA009DtoResponse>();
        List<IfaInfoRegisterViewerSettingA009DtoResponse> resDtoList = new ArrayList<IfaInfoRegisterViewerSettingA009DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoRegisterViewerSettingServiceImpL.intermediarySearchA009");
        }
        
        /* ===================================================== */
        /* ①仲介業者（検索）が空でない場合、下記処理を行う。                 */
        /* ===================================================== */
        
        String modifiedBrokerCodeFuzzySearch = dtoReq.getBrokerCodeFuzzySearch();
        
        if (!StringUtils.isEmpty(dtoReq.getBrokerCodeFuzzySearch())) {
            /* 「!」⇒「!!」に置き換える */
            modifiedBrokerCodeFuzzySearch = modifiedBrokerCodeFuzzySearch.replace("!", "!!");
            /* 「%」⇒「!%」に置き換える */
            modifiedBrokerCodeFuzzySearch = modifiedBrokerCodeFuzzySearch.replace("%", "!%");
            /* 「_」⇒「!_」に置き換える */
            modifiedBrokerCodeFuzzySearch = modifiedBrokerCodeFuzzySearch.replace("_", "!_");
            /* 先頭、末尾に一つ「%」を加える */
            modifiedBrokerCodeFuzzySearch = "%" + modifiedBrokerCodeFuzzySearch + "%";
        }
        
        /* ===================================================== */
        /* ②仲介業者一覧を取得する。                                  */
        /* ===================================================== */
        
        // SQL005の実行
        IfaInfoRegisterViewerSettingSql005RequestModel selSql005Req = new IfaInfoRegisterViewerSettingSql005RequestModel();
        selSql005Req.setBrokerCodeFuzzySearch(modifiedBrokerCodeFuzzySearch);
        DataList<IfaInfoRegisterViewerSettingSql005ResponseModel> selSql005Res = dao
                .selectIfaInfoRegisterViewerSettingSql005(selSql005Req);
        
        /* 取得結果が0件の場合、エラーメッセージを表示する */
        if (selSql005Res == null || selSql005Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { ERROR_MESSAGE_BROKER }));
            return dtoRes;
        }
        
        // 結果の格納
        IfaInfoRegisterViewerSettingA009DtoResponse resDto = new IfaInfoRegisterViewerSettingA009DtoResponse();
        
        List<IfaInfoRegisterViewerSettingA009DtoResponseBroker> brokerList = new ArrayList<>();
        for (IfaInfoRegisterViewerSettingSql005ResponseModel sqlRes : selSql005Res.getDataList()) {
            IfaInfoRegisterViewerSettingA009DtoResponseBroker broker = new IfaInfoRegisterViewerSettingA009DtoResponseBroker();
            broker.setBrokerCode(sqlRes.getBrokerCode());
            broker.setBrokerName(sqlRes.getBrokerName());
            brokerList.add(broker);
        }
        resDto.setBrokerList(brokerList);
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A012
     * アクション名：担当者一覧表示
     * Dto リクエスト：IfaInfoRegisterViewerSettingA012DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA012DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingSql006RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingSql006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA012DtoResponse> personInChargeListDisplayA012(
            IfaInfoRegisterViewerSettingA012DtoRequest dtoReq) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingA012DtoResponse> dtoRes = new DataList<IfaInfoRegisterViewerSettingA012DtoResponse>();
        List<IfaInfoRegisterViewerSettingA012DtoResponse> resDtoList = new ArrayList<IfaInfoRegisterViewerSettingA012DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoRegisterViewerSettingServiceImpL.personInChargeListDisplayA012");
        }
        
        /* ===================================================== */
        /* ①選択した仲介業者の担当者一覧を取得する。                      */
        /* ===================================================== */
        
        // SQL006の実行
        IfaInfoRegisterViewerSettingSql006RequestModel selSql006Req = new IfaInfoRegisterViewerSettingSql006RequestModel();
        selSql006Req.setBrokerSelect(dtoReq.getBrokerSelect());
        DataList<IfaInfoRegisterViewerSettingSql006ResponseModel> selSql006Res = dao
                .selectIfaInfoRegisterViewerSettingSql006(selSql006Req);
        
        /* データなし：検索結果０件エラーを返す。 */
        if (selSql006Res == null || selSql006Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.INFO, ERRORS_DATA_LIST_NOTFOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND));
            return dtoRes;
        }
        
        // 結果の格納
        IfaInfoRegisterViewerSettingA012DtoResponse resDto = new IfaInfoRegisterViewerSettingA012DtoResponse();
        
        List<IfaInfoRegisterViewerSettingDtoResponseIndividualRep> individualRepList = new ArrayList<>();
        for (IfaInfoRegisterViewerSettingSql006ResponseModel sqlRes : selSql006Res.getDataList()) {
            IfaInfoRegisterViewerSettingDtoResponseIndividualRep individualRep = new IfaInfoRegisterViewerSettingDtoResponseIndividualRep();
            individualRep.setUserId(sqlRes.getUserId());
            individualRep.setEmployeeName(sqlRes.getUserName());
            individualRepList.add(individualRep);
        }
        resDto.setIndividualRepList(individualRepList);
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A016
     * アクション名：担当者検索
     * Dto リクエスト：IfaInfoRegisterViewerSettingA016DtoRequest
     * Dto レスポンス：IfaInfoRegisterViewerSettingA016DtoResponse
     * model リクエスト：IfaInfoRegisterViewerSettingSql006RequestModel
     * model レスポンス：IfaInfoRegisterViewerSettingSql006ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaInfoRegisterViewerSettingA016DtoResponse> personInChargeSearchA016(
            IfaInfoRegisterViewerSettingA016DtoRequest dtoReq) throws Exception {
        
        DataList<IfaInfoRegisterViewerSettingA016DtoResponse> dtoRes = new DataList<IfaInfoRegisterViewerSettingA016DtoResponse>();
        List<IfaInfoRegisterViewerSettingA016DtoResponse> resDtoList = new ArrayList<IfaInfoRegisterViewerSettingA016DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaInfoRegisterViewerSettingServiceImpL.personInChargeSearchA016");
        }
        
        /* ===================================================== */
        /* ①仲介業者（検索）が空でない場合、下記処理を行う。                 */
        /* ===================================================== */
        
        String modifiedRepSearch = dtoReq.getRepSearch();
        
        if (!StringUtils.isEmpty(dtoReq.getRepSearch())) {
            /* 「!」⇒「!!」に置き換える */
            modifiedRepSearch = modifiedRepSearch.replace("!", "!!");
            /* 「%」⇒「!%」に置き換える */
            modifiedRepSearch = modifiedRepSearch.replace("%", "!%");
            /* 「_」⇒「!_」に置き換える */
            modifiedRepSearch = modifiedRepSearch.replace("_", "!_");
            /* 先頭、末尾に一つ「%」を加える */
            modifiedRepSearch = "%" + modifiedRepSearch + "%";
        }
        
        /* ===================================================== */
        /* ②担当者の情報を取得する。                                   */
        /* ===================================================== */
        
        // SQL006の実行
        IfaInfoRegisterViewerSettingSql006RequestModel selSql006Req = new IfaInfoRegisterViewerSettingSql006RequestModel();
        selSql006Req.setRepSearch(modifiedRepSearch);
        DataList<IfaInfoRegisterViewerSettingSql006ResponseModel> selSql006Res = dao
                .selectIfaInfoRegisterViewerSettingSql006(selSql006Req);
        
        /* 取得結果が0件の場合、エラーメッセージを表示する */
        if (selSql006Res == null || selSql006Res.getDataList().size() == 0) {
            dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.FATAL, ERRORS_ACCURATELY,
                    IfaCommonUtil.getMessage(ERRORS_ACCURATELY, new String[] { ERROR_MESSAGE_CHARGE }));
            return dtoRes;
        }
        
        // 結果の格納
        IfaInfoRegisterViewerSettingA016DtoResponse resDto = new IfaInfoRegisterViewerSettingA016DtoResponse();
        
        List<IfaInfoRegisterViewerSettingDtoResponseIndividualRep> individualRepList = new ArrayList<>();
        for (IfaInfoRegisterViewerSettingSql006ResponseModel sqlRes : selSql006Res.getDataList()) {
            IfaInfoRegisterViewerSettingDtoResponseIndividualRep individualRep = new IfaInfoRegisterViewerSettingDtoResponseIndividualRep();
            individualRep.setUserId(sqlRes.getUserId());
            individualRep.setEmployeeName(sqlRes.getUserName());
            individualRepList.add(individualRep);
        }
        resDto.setIndividualRepList(individualRepList);
        resDtoList.add(resDto);
        
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.name(), "");
        return dtoRes;
    }
    
}
