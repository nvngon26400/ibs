package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaForeignMarginCollateralDeficientAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaForeignMarginCollateralDeficientAlertListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaForeignMarginCollateralDeficientAlertListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 画面ID：SUB020301_01-04
 * 画面名：米株信用担保不足アラート一覧
 *
 * @author BASE 李
 2024/06/17 新規作成
 */
@Component(value = "cmpIfaForeignMarginCollateralDeficientAlertListService")
public class IfaForeignMarginCollateralDeficientAlertListServiceImpL implements IfaForeignMarginCollateralDeficientAlertListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaForeignMarginCollateralDeficientAlertListServiceImpL.class);
	
    @Autowired
    private IfaForeignMarginCollateralDeficientAlertListDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    private static final String ERRORS_SELECTED_PARAM = "取引コース";
    
    private static final String QUERY_SIZE_LIMIT = "5000";
    
    /** ユーザ権限コード '1':SBI証券本店 */
    private static final String PRI_ID_1 = "1";
    
    private enum MessageId {
        ERRORS_SELECTED("errors.selected"),
        ERRORS_CMN_IFAAGENTCODES_NOTEXIST("errors.cmn.ifaAgentCodes.notExist"),
        ERRORS_DATALIST_NOTFOUND("errors.dataList.notfound"),
        WARNINGS_DATALIST_OVERMAXROWNUM("warnings.dataList.overMaxRownum"),
        ;
        private String key;
        
        private MessageId(String key) {
            this.key = key;
        }
    }


    /**
     * アクションID：A002
     * アクション名：表示
     * Dto リクエスト：IfaForeignMarginCollateralDeficientAlertListA002RequestDto
     * Dto レスポンス：IfaForeignMarginCollateralDeficientAlertListA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
	 *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto> displayA002(IfaForeignMarginCollateralDeficientAlertListA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto> dtoRes = new DataList<IfaForeignMarginCollateralDeficientAlertListA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaForeignMarginCollateralDeficientAlertListServiceImplL.displayA002");
        }
        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ERRORS_SELECTED_PARAM}));
        }
        
        // ② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaForeignMarginCollateralDeficientAlertListSql001RequestModel ifaForeignMarginCollateralDeficientAlertListSql001RequestModel = new IfaForeignMarginCollateralDeficientAlertListSql001RequestModel();
        ifaForeignMarginCollateralDeficientAlertListSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaForeignMarginCollateralDeficientAlertListSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        BeanUtils.copyProperties(ifaForeignMarginCollateralDeficientAlertListSql001RequestModel, dtoReq);
        ifaForeignMarginCollateralDeficientAlertListSql001RequestModel.setPrivId(privId);
        // ③ 赤残警告データリスト情報を取得する。
        DataList<IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel> sql001Res = dao.selectIfaForeignMarginCollateralDeficientAlertListSql001(ifaForeignMarginCollateralDeficientAlertListSql001RequestModel);
        if (sql001Res.size() == 0) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.INFO,
                    MessageId.ERRORS_DATALIST_NOTFOUND.key, 
                    IfaCommonUtil.getMessage(MessageId.ERRORS_DATALIST_NOTFOUND.key));
        }
        if (sql001Res.get(0).getTotalCount() > 5000) {
            dtoRes.setErrorLevel(ErrorLevel.WARNING.getId());
            dtoRes.setMessage(IfaCommonUtil.getMessage(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key,
                    new String[] {QUERY_SIZE_LIMIT, String.valueOf(sql001Res.get(0).getTotalCount())}));
            dtoRes.setReturnCode(MessageId.WARNINGS_DATALIST_OVERMAXROWNUM.key);
        }
        for (IfaForeignMarginCollateralDeficientAlertListSql001ResponseModel sql001ResModel : sql001Res.getDataList()) {
            IfaForeignMarginCollateralDeficientAlertListA002ResponseDto a002ResDto = new IfaForeignMarginCollateralDeficientAlertListA002ResponseDto();
            BeanUtils.copyProperties(a002ResDto, sql001ResModel);
            dtoRes.getDataList().add(a002ResDto);
        }
        
        return dtoRes;
    }

}
