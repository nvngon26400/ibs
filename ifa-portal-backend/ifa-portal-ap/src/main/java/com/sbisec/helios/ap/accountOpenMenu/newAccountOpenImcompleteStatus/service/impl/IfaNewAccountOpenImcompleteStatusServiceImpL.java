package com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.service.impl;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.IfaNewAccountOpenImcompleteStatusDao;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001RequestModel;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dao.model.IfaNewAccountOpenImcompleteStatusSql001ResponseModel;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoRequest;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoResponse;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.dto.IfaNewAccountOpenImcompleteStatusA002DtoResponseNewAccountOpenImcompleteStatus;
import com.sbisec.helios.ap.accountOpenMenu.newAccountOpenImcompleteStatus.service.IfaNewAccountOpenImcompleteStatusService;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020305-01.
 * 画面名：新規口座開設不備状況

 * @author 富永侑希子、大崎辰弥
 　 2023/10/27 新規作成
 */

@Component(value = "cmpIfaNewAccountOpenImcompleteStatusService")
public class IfaNewAccountOpenImcompleteStatusServiceImpL 
        implements IfaNewAccountOpenImcompleteStatusService {
    
    private static final Logger LOGGER = 
            LoggerFactory.getLogger(IfaNewAccountOpenImcompleteStatusServiceImpL.class);
    
    // --------------------------------
    // メッセージ
    // --------------------------------

    /** 参照可能な仲介業者コード／営業員コードが存在しません。. */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST 
            = "errors.cmn.ifaAgentCodes.notExist";
    
    /** 検索結果が0件です。\n条件を設定して再度検索して下さい。. */
    private static final String ERRORS_DATA_LIST_NOTFOUND = "errors.dataList.notfound";
    
    /** 検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。. */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM 
            = "warnings.dataList.overMaxRownum";

    /** 最大データリストサイズ. */
    private static final int MAX_ROW_NUM = 5000;
    
    /** 権限コード：SBI証券本店 の場合. */
    private static final String AUTH_CODE_SBI = "1";

    @Autowired
    private IfaNewAccountOpenImcompleteStatusDao dao;
    
    @Autowired
    private Fct030 brokerCodeChargeListAcquire;

    /**
     * アクションID：A002.
     * アクション名：表示
     * Dto リクエスト：IfaNewAccountOpenImcompleteStatusA002DtoRequest
     * Dto レスポンス：IfaNewAccountOpenImcompleteStatusA002DtoResponse
     * model リクエスト：IfaNewAccountOpenImcompleteStatusSql001RequestModel
     * model レスポンス：IfaNewAccountOpenImcompleteStatusSql001ResponseModel

     * @param dtoReq リクエスト
     * @return IfaNewAccountOpenImcompleteStatusA002DtoResponse
     * @exception Exception SQLExceptionなど
     */
    public DataList<IfaNewAccountOpenImcompleteStatusA002DtoResponse> displayA002(
            IfaNewAccountOpenImcompleteStatusA002DtoRequest dtoReq)throws Exception {        
        DataList<IfaNewAccountOpenImcompleteStatusA002DtoResponse> dtoRes  = new  DataList<IfaNewAccountOpenImcompleteStatusA002DtoResponse>();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNewAccountOpenImcompleteStatusServiceImplL.displayA002");
        }

        //==============================
        // 権限チェック
        //==============================
        // 仲介業者営業員リスト
        OutputFct030Dto outputBrokerCodeChargeListAcquireDto = new OutputFct030Dto();
        
        // ユーザ共通情報
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        if (! StringUtils.equals(AUTH_CODE_SBI, userAccount.getPrivId())) {
            // 参照可能な仲介業者コードと営業員コードを取得する。
            InputFct030Dto fct030InData = new InputFct030Dto();
            outputBrokerCodeChargeListAcquireDto = brokerCodeChargeListAcquire.getData(fct030InData);

            if (CollectionUtils.isEmpty(outputBrokerCodeChargeListAcquireDto.getBrokerChargeList()) 
                    || outputBrokerCodeChargeListAcquireDto.getBrokerChargeList().size() == 0) {
                return IfaCommonUtil.createDataList(null, ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST, IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        
        //==============================
        // 新規口座開設不備状況情報を取得する。
        //==============================
        // SQL001(顧客口座情報の検索)
        DataList<IfaNewAccountOpenImcompleteStatusSql001ResponseModel> selSql001Res = getNewAccountOpenImcompleteStatusList(dtoReq, userAccount.getPrivId(), outputBrokerCodeChargeListAcquireDto);

        // 件数が0件の場合
        if (selSql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(null, ErrorLevel.INFO,
              ERRORS_DATA_LIST_NOTFOUND, IfaCommonUtil.getMessage(ERRORS_DATA_LIST_NOTFOUND));
        }
        
        // SQlResの内容をDtoResに格納
        List<IfaNewAccountOpenImcompleteStatusA002DtoResponse> responseList = new ArrayList<IfaNewAccountOpenImcompleteStatusA002DtoResponse>();
        IfaNewAccountOpenImcompleteStatusA002DtoResponse response = new IfaNewAccountOpenImcompleteStatusA002DtoResponse();
        
        response.setNewAccountOpenImcompleteStatusList(setNewAccountOpenImcompleteStatusList(selSql001Res));
        responseList.add(response);
        
        //　総件数を取得
        int totalRow = selSql001Res.get(0).getTotalRow();

        // 件数が5001件以上の場合ワーニングを返す
        if (MAX_ROW_NUM < totalRow) {
            dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    new String[] { String.valueOf(MAX_ROW_NUM), String.valueOf(totalRow) }));
        } else {
            dtoRes = IfaCommonUtil.createDataList(responseList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
        }
        return dtoRes;
    }
           
    /**
     * SQL001処理.

     * @param dtoReq リクエスト情報
     * @return SQL処理結果
     * @throws Exception SQLExceptionなど
     */
    private DataList<IfaNewAccountOpenImcompleteStatusSql001ResponseModel> getNewAccountOpenImcompleteStatusList(
            IfaNewAccountOpenImcompleteStatusA002DtoRequest dtoReq, String privId, OutputFct030Dto outputBrokerCodeChargeListAcquireDto)  throws Exception {
          
        IfaNewAccountOpenImcompleteStatusSql001RequestModel selSql001Req = new IfaNewAccountOpenImcompleteStatusSql001RequestModel();
        BeanUtils.copyProperties(selSql001Req, dtoReq);
        
        // FCT030仲介業者営業員リストをSqlReqに格納
        List<IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus> 
                brokerChargeList = new ArrayList<IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus>();
        if (outputBrokerCodeChargeListAcquireDto != null && !CollectionUtils.isEmpty(
                outputBrokerCodeChargeListAcquireDto.getBrokerChargeList())) {
            for (BrokerCharge fctRes : 
                    outputBrokerCodeChargeListAcquireDto.getBrokerChargeList()) {
                IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus brokerCharge 
                        = new IfaNewAccountOpenImcompleteStatusSql001RequestModelNewAccountOpenImcompleteStatus();
                brokerCharge.setBrokerCode(fctRes.getBrokerCode());
                brokerCharge.setEmpCode(fctRes.getEmpCode());
                brokerChargeList.add(brokerCharge);
            }
        }
        
        // 仲介業者除外フラグ
        if ("".equals(dtoReq.getChkBrokerCodeExclude())) {
            selSql001Req.setChkBrokerCodeExclude("0");
        } else {
            selSql001Req.setChkBrokerCodeExclude("1");
        }
        
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setPrivId(privId);
        selSql001Req.setMaxRow(MAX_ROW_NUM);
        
        return dao.selectIfaNewAccountOpenImcompleteStatusSql001(selSql001Req);
    }
    
    /**
     * 新規口座開設不備状況リストの設定
     *
     * @param sql001Res SQL001結果
     * @return 新規口座開設不備状況リスト
     */
    
    private List<IfaNewAccountOpenImcompleteStatusA002DtoResponseNewAccountOpenImcompleteStatus> setNewAccountOpenImcompleteStatusList(
            DataList<IfaNewAccountOpenImcompleteStatusSql001ResponseModel> sql001Res) throws Exception {
        List<IfaNewAccountOpenImcompleteStatusA002DtoResponseNewAccountOpenImcompleteStatus> newAccountOpenImcompleteStatusList = new ArrayList<>();
        
        for (IfaNewAccountOpenImcompleteStatusSql001ResponseModel sqlRes : sql001Res.getDataList()) {
            IfaNewAccountOpenImcompleteStatusA002DtoResponseNewAccountOpenImcompleteStatus 
                            newAccountOpenImcompleteStatus = new 
                            IfaNewAccountOpenImcompleteStatusA002DtoResponseNewAccountOpenImcompleteStatus();
    
            BeanUtils.copyProperties(newAccountOpenImcompleteStatus, sqlRes);
            newAccountOpenImcompleteStatusList.add(newAccountOpenImcompleteStatus);
        }
        return newAccountOpenImcompleteStatusList;
    }
}
