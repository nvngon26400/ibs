package com.sbisec.helios.ap.brokerageMenu.commFee.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct030;
import com.sbisec.helios.ap.bizcommon.model.InputFct030Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.IfaOtherFeeDetailDao;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001RequestModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dao.model.IfaOtherFeeDetailSql001ResponseModel;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001RequestDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001ResponseDto;
import com.sbisec.helios.ap.brokerageMenu.commFee.dto.IfaOtherFeeDetailA001ResponseDtoOtherFeeDetailList;
import com.sbisec.helios.ap.brokerageMenu.commFee.service.IfaOtherFeeDetailService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.PrivId;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB020502-02
 * 画面名：その他報酬詳細
 * 2024/06/19 新規作成
 *
 */
@Component(value = "cmpIfaOtherFeeDetailService")
public class IfaOtherFeeDetailServiceImpL implements IfaOtherFeeDetailService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaOtherFeeDetailServiceImpL.class);
    
    /** エラー.参照可能な仲介業者コード／営業員コードが存在しません。 */
    private static final String ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST = "errors.cmn.ifaAgentCodes.notExist";
    
    /** エラー.検索結果が0件です。\n条件を設定して再度検索して下さい。 */
    private static final String ERRORS_DATALIST_NOT_FOUND = "errors.dataList.notfound";
    
    /** ワーニング."検索結果が{0}件を超過しています（{1}件ヒット）。\n条件を詳細に設定して再度検索して下さい。" */
    private static final String WARNINGS_DATA_LIST_OVER_MAX_ROWNUM = "warnings.dataList.overMaxRownum";
    
    /** 最大表示件数(画面) */
    private static final int SCREEN_SEARCH_LIMIT_ROW_NUM = 5000;
    
    @Autowired
    Fct030 fct030;
    
    @Autowired
    private IfaOtherFeeDetailDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期表示
     * Dto リクエスト：IfaOtherFeeDetailA001RequestDto
     * Dto レスポンス：IfaOtherFeeDetailA001ResponseDto
     * model リクエスト：IfaOtherFeeDetailSql001RequestModel
     * model レスポンス：IfaOtherFeeDetailSql001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaOtherFeeDetailA001ResponseDto> initialDisplayA001(IfaOtherFeeDetailA001RequestDto dtoReq)
            throws Exception {
        
        IfaOtherFeeDetailA001ResponseDto resDto = new IfaOtherFeeDetailA001ResponseDto();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaOtherFeeDetailServiceImplL.initialDisplayA001");
        }
        
        // ユーザ共通情報の取得
        final UserAccount userAccount = IfaCommonUtil.getUserAccount();
        
        List<OutputFct030Dto.BrokerCharge> brokerChargeList = List.of();
        
        // 1.ユーザ共通情報.権限コードが「SBI証券本店」以外の場合、参照可能な仲介業者コードをと営業員コードを取得する
        if (!PrivId.HEAD_OFFICE.getId().equals(userAccount.getPrivId())) {
            // FCT30実行
            OutputFct030Dto fct030Res = fct030.getData(new InputFct030Dto());
            brokerChargeList = fct030Res.getBrokerChargeList();
            
            // FCT030.仲介業者営業員リストの件数が０件の場合：メッセージを表示し、処理終了。
            if (CollectionUtils.isEmpty(brokerChargeList)) {
                return IfaCommonUtil.createDataList(List.of(), ErrorLevel.FATAL, ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST,
                        IfaCommonUtil.getMessage(ERRORS_CMN_IFA_AGENT_CODES_NOT_EXIST));
            }
        }
        
        // 2.その他報酬詳細リストを取得する。
        IfaOtherFeeDetailSql001RequestModel selSql001Req = new IfaOtherFeeDetailSql001RequestModel();
        selSql001Req.setBrokerCode(dtoReq.getBrokerCode());
        selSql001Req.setTargetDateYm(dtoReq.getTargetDateYm());
        selSql001Req.setBrokerChargeList(brokerChargeList);
        selSql001Req.setPrivId(userAccount.getPrivId());
        // 検索上限件数に画面の検索条件件数(5000)を設定する
        selSql001Req.setSearchLimitRow(Integer.toString(SCREEN_SEARCH_LIMIT_ROW_NUM));
        
        // SQL001実行
        DataList<IfaOtherFeeDetailSql001ResponseModel> selSql001Res = dao.selectIfaOtherFeeDetailSql001(selSql001Req);
        
        List<IfaOtherFeeDetailA001ResponseDtoOtherFeeDetailList> otherFeeDetailList = new ArrayList<>();
        
        // SQL001の結果をレスポンスDTOに設定する
        for (IfaOtherFeeDetailSql001ResponseModel model : selSql001Res.getDataList()) {
            IfaOtherFeeDetailA001ResponseDtoOtherFeeDetailList otherFeeDetail = new IfaOtherFeeDetailA001ResponseDtoOtherFeeDetailList();
            BeanUtils.copyProperties(otherFeeDetail, model);
            otherFeeDetailList.add(otherFeeDetail);
        }
        
        if (CollectionUtils.isEmpty(selSql001Res.getDataList())) {
            // 検索結果が0件の場合
            return IfaCommonUtil.createDataList(List.of(), ErrorLevel.INFO, ERRORS_DATALIST_NOT_FOUND,
                    IfaCommonUtil.getMessage(ERRORS_DATALIST_NOT_FOUND));
            
        }
        
        List<IfaOtherFeeDetailA001ResponseDto> dtoResList = new ArrayList<IfaOtherFeeDetailA001ResponseDto>();
        // レスポンスDTOに設定する
        resDto.setOtherFeeDetailList(otherFeeDetailList);
        dtoResList.add(resDto);
        int totalCount = Integer.parseInt(selSql001Res.get(0).getTotalCount());
        if (totalCount > SCREEN_SEARCH_LIMIT_ROW_NUM) {
            // 検索上限件数を超える場合
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.WARNING, WARNINGS_DATA_LIST_OVER_MAX_ROWNUM,
                    IfaCommonUtil.getMessage(WARNINGS_DATA_LIST_OVER_MAX_ROWNUM, new String[] {
                            Integer.toString(SCREEN_SEARCH_LIMIT_ROW_NUM), Integer.toString(totalCount) }));
        } else {
            return IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        }
        
    }
    
}
