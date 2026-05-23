package com.sbisec.helios.ap.brokerageMenu.ipoPo.service.impl;


import java.util.Collections;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.component.Fct003;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.InputFct003Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct003Dto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.IfaBbApplyCancelConfirmDao;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dao.model.IfaBbApplyCancelConfirmSql015RequestModel;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.dto.IfaBbApplyCancelConfirmA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.ipoPo.service.IfaBbApplyCancelConfirmService;
import com.sbisec.helios.ap.common.annotation.dao.EtintraTransactional;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.MediateAbleTradeFlag;
import com.sbisec.helios.ap.common.service.CodeListService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0204_02-03_2
 * 画面名：BB申込取消確認
 *
 * @author BASE 李
 2024/05/14 新規作成
 */
@Component(value = "cmpIfaBbApplyCancelConfirmService")
public class IfaBbApplyCancelConfirmServiceImpL implements IfaBbApplyCancelConfirmService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaBbApplyCancelConfirmServiceImpL.class);

    @Autowired
    private IfaBbApplyCancelConfirmDao dao;

    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private Fct003 fct003;
    
    @Autowired
    private CodeListService codeListService;
    
    /** 権限なし */
    private static final String NO_AUTHORIZED = "0";
    
    /** 取引停止フラグ＝"1 */
    private static final String TRADE_SUSPEND_FLAG_1 = "1";
    
    /** FCT003パラメータ 証券金銭種別  国内*/
    private static final String PRODUCT_CD_DOMESTIC = "01";
    /** FCT003パラメータ 取引種別 BB申込*/
    private static final String TRADE_CD_DOMESTIC = "9";

    private static final String MSG_DISPLAY_TARGET_TRADE = "MSG_DISPLAY_TARGET_TRADE";
    private static final String MSG_DISPLAY_TARGET_TRADE_PARRTEN = "7";
    private static final String BB_APPLY_INFO = "BB申込情報";
    
    private enum MessageId {
        ERRORS_BUTENACCOUNTNOTEXIST("errors.butenAccountNotExist"),
        ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE("errors.cmn.selectedAccount.outOfService"),
        ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE("errors.cmn.selectedAccountCourse.unavailable"),
        ERRORS_DELETEDATAEXIST("errors.deleteDataExist");
        private String key;
        
        MessageId(String key) {
            this.key = key;
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：申込取消
     * Dto リクエスト：IfaBbApplyCancelConfirmA001RequestDto
     * Dto レスポンス：IfaBbApplyCancelConfirmA001ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @EtintraTransactional
    public DataList<IfaBbApplyCancelConfirmA001ResponseDto> applicationCancellationA001(IfaBbApplyCancelConfirmA001RequestDto dtoReq)
            throws Exception {
        DataList<IfaBbApplyCancelConfirmA001ResponseDto> dtoRes = new DataList<IfaBbApplyCancelConfirmA001ResponseDto>();
        IfaBbApplyCancelConfirmA001ResponseDto ifaBbApplyCancelConfirmA001ResponseDto = new IfaBbApplyCancelConfirmA001ResponseDto();
        dtoRes.getDataList().add(ifaBbApplyCancelConfirmA001ResponseDto);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaBbApplyCancelConfirmServiceImplL.applicationCancellationA001");
        }
        String butenCodeReq = dtoReq.getButenCode();
        String accountNumberReq = dtoReq.getAccountNumber();
        // 1 利用者の口座に対する権限チェックを行う。
        // 対象顧客参照権限有無＝"0"（権限なし）：権限なしエラーを返す。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setButenCode(butenCodeReq);
        inputFct001Dto.setAccountNumber(accountNumberReq);
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_BUTENACCOUNTNOTEXIST.key,
                            new String[] {butenCodeReq, accountNumberReq}));
            return dtoRes;
        }
        // 取引停止フラグ＝"1"（取引停止口座）：取引停止口座エラーを返す。
        if (TRADE_SUSPEND_FLAG_1.equals(outputFct001Dto.getTradeSuspendFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNT_OUTOFSERVICE.key));
            return dtoRes;
        }
        // 2　取引コース媒介可否チェックを行う。
        InputFct003Dto inputFct003Dto = new InputFct003Dto();
        inputFct003Dto.setAccountNumber(accountNumberReq);
        inputFct003Dto.setButenCode(butenCodeReq);
        inputFct003Dto.setProductCd(PRODUCT_CD_DOMESTIC);
        inputFct003Dto.setTradeCd(TRADE_CD_DOMESTIC);
        OutputFct003Dto outputFct003Dto = fct003.doCheck(inputFct003Dto);
        boolean mediateFlag = outputFct003Dto.getMediateProprietyList().stream()
                .anyMatch(mediatePropriety -> mediatePropriety.getMediatePropriety().equals(MediateAbleTradeFlag.NASHI.getId())); 
        if (mediateFlag) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.FATAL, MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_SELECTEDACCOUNTCOURSE_UNAVAILABLE.key, 
                            new String[] {codeListService.getValue(MSG_DISPLAY_TARGET_TRADE, MSG_DISPLAY_TARGET_TRADE_PARRTEN)})
                );
            return dtoRes;
        }
        
        // ③ BB申込情報を取消す。
        IfaBbApplyCancelConfirmSql001RequestModel ifaBbApplyCancelConfirmSql001RequestModel = new IfaBbApplyCancelConfirmSql001RequestModel();
        BeanUtils.copyProperties(ifaBbApplyCancelConfirmSql001RequestModel, dtoReq);
        
        int result = dao.deleteIfaBbApplyCancelConfirmSql001(ifaBbApplyCancelConfirmSql001RequestModel);
        
        if (result == 0) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(),
                    ErrorLevel.INFO, MessageId.ERRORS_DELETEDATAEXIST.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DELETEDATAEXIST.key, new String[] {BB_APPLY_INFO})
                );
            return dtoRes;
        } else {
            IfaBbApplyCancelConfirmSql015RequestModel ifaBbApplyCancelConfirmSql015RequestModel = new IfaBbApplyCancelConfirmSql015RequestModel();
            BeanUtils.copyProperties(ifaBbApplyCancelConfirmSql015RequestModel, dtoReq);
            
            dao.deleteIfaBbApplyCancelConfirmSql015(ifaBbApplyCancelConfirmSql015RequestModel);
        }
        
        BeanUtils.copyProperties(ifaBbApplyCancelConfirmA001ResponseDto, dtoReq);

        return dtoRes;
    }

}
