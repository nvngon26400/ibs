package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignAmountUnpaidOverdraftAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaForeignAmountUnpaidOverdraftAlertListService;
import com.sbisec.helios.ap.common.dto.PreviousBusinessDateDtoRequest;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.service.SystemDateService;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020301_01-03
 * 画面名：外貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/06/12 新規作成
 */
@Component(value = "cmpIfaForeignAmountUnpaidOverdraftAlertListService")
public class IfaForeignAmountUnpaidOverdraftAlertListServiceImpL
        implements IfaForeignAmountUnpaidOverdraftAlertListService {
    
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IfaForeignAmountUnpaidOverdraftAlertListServiceImpL.class);
    
    @Autowired
    private IfaForeignAmountUnpaidOverdraftAlertListDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private SystemDateService systemDateService;
    
    private static final String ERRORS_SELECTED_PARAM = "取引コース";
    
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    /** ユーザ権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";
    
    private enum MessageId {
        
        ERRORS_SELECTED("errors.selected"), ERRORS_CMN_IFAAGENTCODES_NOTEXIST(
                "errors.cmn.ifaAgentCodes.notExist"), ERRORS_DATALIST_NOTFOUND(
                        "errors.dataList.notfound"), WARNINGS_DATALIST_OVERMAXROWNUM(
                                "warnings.dataList.overMaxRownum"),;
        
        private String key;
        
        private MessageId(String key) {
            
            this.key = key;
        }
    }
    
    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto
     * Dto レスポンス：IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto> displayA002(
            IfaForeignAmountUnpaidOverdraftAlertListA002RequestDto dtoReq) throws Exception {
        
        DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto> dtoRes = new DataList<IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignAmountUnpaidOverdraftAlertListServiceImplL.displayA002");
        }
        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] { ERRORS_SELECTED_PARAM }));
        }
        
        // ② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel = new IfaForeignAmountUnpaidOverdraftAlertListSql001RequestModel();
        ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key,
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        BeanUtils.copyProperties(ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel, dtoReq);
        ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel.setPrivId(privId);
        ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel
                .setDateYmd(systemDateService.getPreviousBusinessDate(new PreviousBusinessDateDtoRequest()));
        
        //③ 外貨預り金赤残リスト情報を取得する。
        DataList<IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel> sql001Res = dao
                .selectIfaForeignAmountUnpaidOverdraftAlertListSql001(
                        ifaForeignAmountUnpaidOverdraftAlertListSql001RequestModel);
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key,
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        }
        if (sql001Res.get(0).getTotalCount() > 5000) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                    new String[] { QUERY_SIZE_LIMIT, String.valueOf(sql001Res.get(0).getTotalCount()) }));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        for (IfaForeignAmountUnpaidOverdraftAlertListSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
            IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto a002ResDto = new IfaForeignAmountUnpaidOverdraftAlertListA002ResponseDto();
            BeanUtils.copyProperties(a002ResDto, sql001ResModel);
            dtoRes.getDataList().add(a002ResDto);
        }
        
        return dtoRes;
    }
    
}
