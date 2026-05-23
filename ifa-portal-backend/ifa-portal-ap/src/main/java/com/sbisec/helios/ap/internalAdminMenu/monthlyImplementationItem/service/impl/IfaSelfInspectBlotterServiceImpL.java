package com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.IfaSelfInspectBlotterDao;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql001ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql002ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql003ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql004ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005RequestModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dao.model.IfaSelfInspectBlotterSql005ResponseModel;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA001ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA002ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003RequestDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterA003ResponseDto;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterRegisterDate;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.dto.IfaSelfInspectBlotterSelfAssessment;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.service.IfaSelfInspectBlotterService;
import com.sbisec.helios.ap.internalAdminMenu.monthlyImplementationItem.util.IfaSelfInspectBlotterUtil;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0401_02-01
 * 画面名：自己点検記録簿
 *
 * @author SCSK丹波
 2024/05/31 新規作成
 */
@Component(value = "cmpIfaSelfInspectBlotterService")
public class IfaSelfInspectBlotterServiceImpL implements IfaSelfInspectBlotterService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaSelfInspectBlotterServiceImpL.class);
    
    @Autowired
    private IfaSelfInspectBlotterDao dao;
    
    @Autowired
    private IfaSelfInspectBlotterUtil util;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSelfInspectBlotterA001RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterA001ResponseDto
     * model リクエスト：IfaSelfInspectBlotterSql001RequestModel
     * model レスポンス：IfaSelfInspectBlotterSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterA001ResponseDto> initializeA001(IfaSelfInspectBlotterA001RequestDto dtoReq)
            throws Exception {
        
        IfaSelfInspectBlotterA001ResponseDto dtoResInner = new IfaSelfInspectBlotterA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectBlotterServiceImplL.initializeA001");
        }
        
        // 表示年月のdropDownLisTデータを取得する。
        List<IfaSelfInspectBlotterRegisterDate> registerDateList = callSql001();
        dtoResInner.setRegisterDateList(registerDateList);
        // 1件目を表示年月として保持
        String assignMonth = registerDateList.get(0).getCodeId();
        
        // 仲介業者コードの取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String brokerCode = util.editBrokerCode(userAccount);
        String privId = userAccount.getPrivId();
        
        // 最終更新日時を取得する。
        dtoResInner.setConfirmationDate(callSql002(privId, brokerCode));
        
        // 確認日を取得する。
        DataList<IfaSelfInspectBlotterSql003ResponseModel> selSql003Resp = callSql003(brokerCode);
        
        // 自己点検記録簿を取得する。
        List<IfaSelfInspectBlotterSelfAssessment> selfAssessmentList = new ArrayList<>();
        if (selSql003Resp == null || CollectionUtils.isEmpty(selSql003Resp.getDataList())) {
            // SQL003.確認日を取得できない場合
            selfAssessmentList = callSql004(assignMonth, brokerCode, privId);
        } else {
            // SQL003.確認日を取得できる場合
            selfAssessmentList = callSql005(assignMonth, brokerCode, privId);
        }
        dtoResInner.setSelfAssessmentList(selfAssessmentList);
        
        // レスポンス返却
        DataList<IfaSelfInspectBlotterA001ResponseDto> dtoRes = new DataList<IfaSelfInspectBlotterA001ResponseDto>();
        List<IfaSelfInspectBlotterA001ResponseDto> dtoResInnerList = new ArrayList<>();
        
        dtoResInnerList.add(dtoResInner);
        dtoRes = IfaCommonUtil.createDataList(dtoResInnerList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
    * アクションID：A002
    * アクション名：表示年月変更
    * Dto リクエスト：IfaSelfInspectBlotterA002RequestDto
    * Dto レスポンス：IfaSelfInspectBlotterA002ResponseDto
    * model リクエスト：IfaSelfInspectBlotterSql001RequestModel
    * model レスポンス：IfaSelfInspectBlotterSql001ResponseModel
    *
    * @param dtoReq リクエスト
    * @return res レスポンス
    * @exception exception システムエラー
    */
    public DataList<IfaSelfInspectBlotterA002ResponseDto> displayYearMonthChangeA002(
            IfaSelfInspectBlotterA002RequestDto dtoReq) throws Exception {
        
        IfaSelfInspectBlotterA002ResponseDto dtoResInner = new IfaSelfInspectBlotterA002ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectBlotterServiceImplL.displayYearMonthChangeA002");
        }
        
        // 表示年月のdropDownLisTデータを取得する。
        List<IfaSelfInspectBlotterRegisterDate> registerDateList = callSql001();
        dtoResInner.setRegisterDateList(registerDateList);
        
        // 仲介業者コードの取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String brokerCode = util.editBrokerCode(userAccount);
        String privId = userAccount.getPrivId();
        
        // 最終更新日時を取得する。
        dtoResInner.setConfirmationDate(callSql002(privId, brokerCode));
        
        // 自己点検記録簿を取得する。
        List<IfaSelfInspectBlotterSelfAssessment> selfAssessmentList = new ArrayList<>();
        selfAssessmentList = callSql005(dtoReq.getAssignMonth(), brokerCode, privId);
        
        dtoResInner.setSelfAssessmentList(selfAssessmentList);
        
        // レスポンス返却
        DataList<IfaSelfInspectBlotterA002ResponseDto> dtoRes = new DataList<IfaSelfInspectBlotterA002ResponseDto>();
        List<IfaSelfInspectBlotterA002ResponseDto> dtoResInnerList = new ArrayList<>();
        
        dtoResInnerList.add(dtoResInner);
        dtoRes = IfaCommonUtil.createDataList(dtoResInnerList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
     * アクションID：A003
     * アクション名：TOP遷移
     * Dto リクエスト：IfaSelfInspectBlotterA003RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterA003ResponseDto
     * model リクエスト：IfaSelfInspectBlotterSql001RequestModel
     * model レスポンス：IfaSelfInspectBlotterSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterA003ResponseDto> topTransitionA003(IfaSelfInspectBlotterA003RequestDto dtoReq)
            throws Exception {
        
        IfaSelfInspectBlotterA003ResponseDto dtoResInner = new IfaSelfInspectBlotterA003ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaSelfInspectBlotterServiceImplL.topTransitionA003");
        }
        
        // 表示年月のdropDownLisTデータを取得する。
        List<IfaSelfInspectBlotterRegisterDate> registerDateList = callSql001();
        dtoResInner.setRegisterDateList(registerDateList);
        
        // 仲介業者コードの取得
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String brokerCode = util.editBrokerCode(userAccount);
        String privId = userAccount.getPrivId();
        
        // 最終更新日時を取得する。
        dtoResInner.setConfirmationDate(callSql002(privId, brokerCode));
        
        // 仲介業者コードと表示年月で未登録の自己点検記録簿を取得する。
        dtoResInner.setSelfAssessmentList(callSql004(dtoReq.getAssignMonth(), brokerCode, privId));
        
        // レスポンス返却
        DataList<IfaSelfInspectBlotterA003ResponseDto> dtoRes = new DataList<IfaSelfInspectBlotterA003ResponseDto>();
        List<IfaSelfInspectBlotterA003ResponseDto> dtoResInnerList = new ArrayList<>();
        
        dtoResInnerList.add(dtoResInner);
        dtoRes = IfaCommonUtil.createDataList(dtoResInnerList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), "");
        return dtoRes;
    }
    
    /**
     * SQL001を呼び出し、表示年月のリストを取得する
     *
     * @return 表示年月リスト
     * @throws Exception 例外
     */
    private List<IfaSelfInspectBlotterRegisterDate> callSql001() throws Exception {
        
        // 表示年月のdropDownLisTデータを取得する。
        DataList<IfaSelfInspectBlotterSql001ResponseModel> selSql001Resp = dao.selectIfaSelfInspectBlotterSql001();
        List<IfaSelfInspectBlotterRegisterDate> registerDateList = new ArrayList<>();
        for (IfaSelfInspectBlotterSql001ResponseModel sql001RespItem : selSql001Resp.getDataList()) {
            IfaSelfInspectBlotterRegisterDate registerDate = new IfaSelfInspectBlotterRegisterDate();
            registerDate.setCodeId(sql001RespItem.getCodeId());
            registerDate.setCodeName(sql001RespItem.getCodeName());
            registerDateList.add(registerDate);
        }
        return registerDateList;
    }
    
    /**
     * SQL002を呼び出し、最終更新日時を取得する
     *
     * @param privId 権限コード
     * @param brokerCode 仲介業者コード
     * @return 最終更新日時<br/>SQL002の結果が取得できない場合はnull
     * @throws Exception 例外
     */
    private String callSql002(String privId, String brokerCode) throws Exception {
        
        IfaSelfInspectBlotterSql002RequestModel selSql002Req = new IfaSelfInspectBlotterSql002RequestModel();
        selSql002Req.setBrokerCode(brokerCode);
        DataList<IfaSelfInspectBlotterSql002ResponseModel> selSql002Resp = dao
                .selectIfaSelfInspectBlotterSql002(selSql002Req);
        
        // SQL002の結果が取得できない場合はnullを返却する
        if (selSql002Resp == null || CollectionUtils.isEmpty(selSql002Resp.getDataList())) {
            return null;
        }
        
        return selSql002Resp.getDataList().get(0).getLastUpdateTime();
    }
    
    /**
     * SQL003を呼び出す
     *
     * @param brokerCode 仲介業者コード
     * @return SQL003実行結果
     * @throws Exception 例外
     */
    private DataList<IfaSelfInspectBlotterSql003ResponseModel> callSql003(String brokerCode) throws Exception {
        
        IfaSelfInspectBlotterSql003RequestModel selSql003Req = new IfaSelfInspectBlotterSql003RequestModel();
        selSql003Req.setBrokerCode(brokerCode);
        DataList<IfaSelfInspectBlotterSql003ResponseModel> selSql003Resp = dao
                .selectIfaSelfInspectBlotterSql003(selSql003Req);
        return selSql003Resp;
    }
    
    /**
     * SQL004を呼び出し、自己点検記録簿リストを作成する
     *
     * @param assignMonth 表示年月
     * @param brokerCode 仲介業者コード
     * @param privId 権限コード
     * @return 自己点検記録簿リスト
     * @throws Exception 例外
     */
    private List<IfaSelfInspectBlotterSelfAssessment> callSql004(String assignMonth, String brokerCode, String privId)
            throws Exception {
        
        // 仲介業者コードと表示年月で未登録の自己点検記録簿を取得する。
        IfaSelfInspectBlotterSql004RequestModel selSql004Req = new IfaSelfInspectBlotterSql004RequestModel();
        selSql004Req.setAssignMonth(assignMonth);
        selSql004Req.setBrokerCode(brokerCode);
        selSql004Req.setPrivId(privId);
        DataList<IfaSelfInspectBlotterSql004ResponseModel> selSql004Resp = dao
                .selectIfaSelfInspectBlotterSql004(selSql004Req);
        
        // SQL004の結果から自己点検記録簿リストを生成する
        List<IfaSelfInspectBlotterSelfAssessment> selfAssessmentList = new ArrayList<>();
        for (IfaSelfInspectBlotterSql004ResponseModel selSql004RespItem : selSql004Resp.getDataList()) {
            IfaSelfInspectBlotterSelfAssessment selfAssessment = new IfaSelfInspectBlotterSelfAssessment();
            selfAssessment.setSelfCheckItemId(selSql004RespItem.getSelfCheckItemId().toString());
            selfAssessment.setSelfAssessmentItemName(selSql004RespItem.getSelfAssessmentItemName());
            selfAssessment.setReasonRequiredFlag(selSql004RespItem.getReasonRequiredFlag());
            selfAssessment.setAnswer(selSql004RespItem.getAnswer());
            selfAssessmentList.add(selfAssessment);
        }
        
        return selfAssessmentList;
    }
    
    /**
     * SQL005を呼び出し、自己点検記録簿リストを作成する<br/>
     * SQL005の結果が0件の場合は、SQL004を呼び出す
     *
     * @param assignMonth 表示年月
     * @param brokerCode 仲介業者コード
     * @param privId 権限コード
     * @return 自己点検記録簿リスト
     * @throws Exception 例外
     */
    private List<IfaSelfInspectBlotterSelfAssessment> callSql005(String assignMonth, String brokerCode, String privId)
            throws Exception {
        
        IfaSelfInspectBlotterSql005RequestModel selSql005Req = new IfaSelfInspectBlotterSql005RequestModel();
        selSql005Req.setAssignMonth(assignMonth);
        selSql005Req.setBrokerCode(brokerCode);
        DataList<IfaSelfInspectBlotterSql005ResponseModel> selSql005Resp = dao
                .selectIfaSelfInspectBlotterSql005(selSql005Req);
        
        if (selSql005Resp == null || CollectionUtils.isEmpty(selSql005Resp.getDataList())) {
            // 取得結果が0件であった場合、仲介業者コードと表示年月で未登録の自己点検記録簿を取得する。
            return callSql004(assignMonth, brokerCode, privId);
        }
        // SQL005の結果から自己点検記録簿リストを生成する
        List<IfaSelfInspectBlotterSelfAssessment> selfAssessmentList = new ArrayList<>();
        for (IfaSelfInspectBlotterSql005ResponseModel selSql005RespItem : selSql005Resp.getDataList()) {
            IfaSelfInspectBlotterSelfAssessment selfAssessment = new IfaSelfInspectBlotterSelfAssessment();
            selfAssessment.setSelfCheckItemId(selSql005RespItem.getSelfCheckItemId().toString());
            selfAssessment.setSelfAssessmentItemName(selSql005RespItem.getSelfAssessmentItemName());
            selfAssessment.setReasonRequiredFlag(selSql005RespItem.getReasonRequiredFlag());
            selfAssessment.setConfirmation(selSql005RespItem.getConfirmation());
            selfAssessment.setAnswer(selSql005RespItem.getAnswer());
            selfAssessment.setAnswerReason(selSql005RespItem.getAnswerReason());
            if (selSql005RespItem.getAnswerCount() != null) {
                selfAssessment.setAnswerCount(selSql005RespItem.getAnswerCount().toString());
            }
            selfAssessment.setAnswerResult(selSql005RespItem.getAnswerResult());
            selfAssessmentList.add(selfAssessment);
        }
        
        return selfAssessmentList;
        
    }
    
}
