package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.IfaJpyAmountUnpaidOverdraftAlertListDao;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model.IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.service.IfaJpyAmountUnpaidOverdraftAlertListService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiErrorUtil;
import com.sbisec.helios.ap.common.util.ApiWrapper;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityIn;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityInData;
import jp.co.sbisec.pcenter.dto.yanap.QueryMgEstimateCapabilityOutData;

/**
 * 画面ID：SUB020301_01-01
 * 画面名：円貨未入金・赤残アラート一覧
 *
 * @author BASE 李
 2024/05/23 新規作成
 */
@Component(value = "cmpIfaJpyAmountUnpaidOverdraftAlertListService")
public class IfaJpyAmountUnpaidOverdraftAlertListServiceImpL implements IfaJpyAmountUnpaidOverdraftAlertListService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaJpyAmountUnpaidOverdraftAlertListServiceImpL.class);
    
    @Autowired
    private IfaJpyAmountUnpaidOverdraftAlertListDao dao;
    
    @Autowired
    private Fct030 fct030;
    
    @Autowired
    private ApiWrapper apiWrapper;
    
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
     * Dto リクエスト：IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto
     * Dto レスポンス：IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto
     * model リクエスト：IfaLoginSql001RequestModel
     * model レスポンス：IfaLoginSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto> displayA002(IfaJpyAmountUnpaidOverdraftAlertListA002RequestDto dtoReq)
            throws Exception {
        DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto> dtoRes = new DataList<IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaJpyAmountUnpaidOverdraftAlertListServiceImplL.displayA002");
        }
        
        // ① リクエスト.取引コースのチェックを行う。
        if (ObjectUtils.isEmpty(dtoReq.getCourse())) {
            return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    MessageId.ERRORS_SELECTED.key, IfaCommonUtil.getMessage(MessageId.ERRORS_SELECTED.key, new String[] {ERRORS_SELECTED_PARAM}));
        }
        
        // ② ユーザ共通情報.権限コード ≠ '1':SBI証券本店 の場合：参照可能な仲介業者コードをと営業員コードを取得する。
        UserAccount userAccount = IfaCommonUtil.getUserAccount();
        String privId = userAccount.getPrivId();
        IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel = new IfaJpyAmountUnpaidOverdraftAlertListSql001RequestModel();
        ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel.setBrokerChargeList(new ArrayList<BrokerCharge>());
        if (!PRI_ID_1.equals(privId)) {
            OutputFct030Dto outputFct030Dto = fct030.getData(null);
            List<BrokerCharge> brokerChargeList = outputFct030Dto.getBrokerChargeList();
            if (brokerChargeList.size() == 0) {
                return IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                        MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key, 
                        IfaCommonUtil.getMessage(MessageId.ERRORS_CMN_IFAAGENTCODES_NOTEXIST.key));
            } else {
                ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel.setBrokerChargeList(brokerChargeList);
            }
        }
        BeanUtils.copyProperties(ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel, dtoReq);
        ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel.setPrivId(privId);
        // ③ 赤残警告データリスト情報を取得する。
        DataList<IfaJpyAmountUnpaidOverdraftAlertListSql001ResponseModel> sql001Res = dao.selectIfaJpyAmountUnpaidOverdraftAlertListSql001(ifaJpyAmountUnpaidOverdraftAlertListSql001RequestModel);
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
        
        dtoRes.setDataList(
                sql001Res.getDataList()
                .stream()
                .map(IfaJpyAmountUnpaidOverdraftAlertListA002ResponseDto::new)
                .collect(Collectors.toList())
        );
        return dtoRes;
    }

    /**
     * アクションID：A004
     * アクション名：画面遷移
     * Dto リクエスト：IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto
     * Dto レスポンス：IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto> transitionA004(IfaJpyAmountUnpaidOverdraftAlertListA004RequestDto dtoReq)
            throws Exception {
        // ① 選択した顧客の信用口座開設情報を取得する
        ApiErrorUtil apiErrorUtil = new ApiErrorUtil();
        QueryMgEstimateCapabilityIn api001Input = new QueryMgEstimateCapabilityIn();
        
        QueryMgEstimateCapabilityInData indata = new QueryMgEstimateCapabilityInData();
        String butenCode = dtoReq.getButenCode();
        String accountNumber = dtoReq.getAccountNumber();
        indata.setButenCd(butenCode);
        indata.setKozaNo(accountNumber);
        indata.setRequestKbn1("1");
        indata.setRequestKbn2("N");;
        api001Input.setIndata(indata);
        QueryMgEstimateCapabilityOutData api001Output = apiWrapper.queryMgEstimateCapability(api001Input);
        apiErrorUtil.checkApiResponse(api001Output.getShubetu(), api001Output.getCode(), api001Output.getMessage());
        IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto a004ResDto = new IfaJpyAmountUnpaidOverdraftAlertListA004ResponseDto();
        a004ResDto.setCreditAccountKbn(api001Output.getCreditAccountKbn());
        a004ResDto.setButenCode(butenCode);
        a004ResDto.setAccountNumber(accountNumber);
        return apiErrorUtil.createDataList(List.of(a004ResDto), "");
    }
}
