package com.sbisec.helios.ap.common.composite.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sbibits.earth.model.DataList;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.bizcommon.component.Fct001;
import com.sbisec.helios.ap.bizcommon.model.InputFct001Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct001Dto;
import com.sbisec.helios.ap.common.composite.dao.IfaNoticeInfoDao;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoResponse;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoResponseNoticeInfoList;
import com.sbisec.helios.ap.common.composite.dto.IfaNoticeInfoA002DtoResponseTradeRestrictionList;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql001ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql002ResponseModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003RequestModel;
import com.sbisec.helios.ap.common.composite.model.IfaNoticeInfoSql003ResponseModel;
import com.sbisec.helios.ap.common.composite.service.IfaNoticeInfoService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：CC016
 * 画面名：注意情報
 *
 * @author SCSK
 2024/06/20 新規作成
 */
@Component(value = "cmpIfaNoticeInfoService")
public class IfaNoticeInfoServiceImpL implements IfaNoticeInfoService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaNoticeInfoServiceImpL.class);
    
    /** 権限なしエラー */
    private static final String NO_AUTHORIZED = "0";
    
    /** 入力した部店口座は存在しません */
    private static final String ERRORS_BUTENACCOUNTNOTEXIST = "errors.butenAccountNotExist";
    
    @Autowired
    private Fct001 fct001;
    
    @Autowired
    private IfaNoticeInfoDao dao;
    
    /**
     * アクションID：A002
     * アクション名：注意情報表示
     * Dto リクエスト：IfaNoticeInfoA002DtoRequest
     * Dto レスポンス：IfaNoticeInfoA002DtoResponse
     *
     * @param dtoReq リクエストDto
     * @return レスポンスDto
     * @exception exception システムエラー
     */
    public DataList<IfaNoticeInfoA002DtoResponse> displayA002(IfaNoticeInfoA002DtoRequest dtoReq)
            throws Exception {
        
        DataList<IfaNoticeInfoA002DtoResponse> dtoRes = new DataList<IfaNoticeInfoA002DtoResponse>();
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("IfaNoticeInfoServiceImplL.displayA002");
        }
        
        // 1.利用者の口座に対する権限チェックを行う。
        InputFct001Dto inputFct001Dto = new InputFct001Dto();
        inputFct001Dto.setAccountNumber(dtoReq.getAccountNumber());
        inputFct001Dto.setButenCode(dtoReq.getButenCode());
        OutputFct001Dto outputFct001Dto = fct001.doCheck(inputFct001Dto);
        if (NO_AUTHORIZED.equals(outputFct001Dto.getTargetCustomerRefAuthFlag())) {
            dtoRes = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.FATAL,
                    ERRORS_BUTENACCOUNTNOTEXIST, IfaCommonUtil.getMessage(ERRORS_BUTENACCOUNTNOTEXIST,
                            new String[] { dtoReq.getButenCode(), dtoReq.getAccountNumber() }));
            return dtoRes;
        }
        
        // 2.注意情報を取得する。(SQL001)
        IfaNoticeInfoSql001RequestModel selSql001Req = new IfaNoticeInfoSql001RequestModel();
        selSql001Req.setCustomerCode(dtoReq.getCustomerCode());
        DataList<IfaNoticeInfoSql001ResponseModel> selSql001Res = dao.selectIfaNoticeInfoSql001(selSql001Req);
        
        // 3.注意情報一覧を編集する。
        List<IfaNoticeInfoSql001ResponseModel> sql001Res = selSql001Res.getDataList();
        sql001Res.removeIf(item -> item.getDeleteTime() != null && !item.getDeleteTime().isEmpty());
        
        // 4.注意情報一覧.取扱者を編集する。
        List<IfaNoticeInfoA002DtoResponseNoticeInfoList> noticeInfoList = new ArrayList<>();
        for (IfaNoticeInfoSql001ResponseModel sql001 : sql001Res) {
            IfaNoticeInfoA002DtoResponseNoticeInfoList noticeInfo = new IfaNoticeInfoA002DtoResponseNoticeInfoList();
            BeanUtils.copyProperties(noticeInfo, sql001);
            if (sql001.getDealtPerson() != null) {
                noticeInfo.setDealtPerson(sql001.getDealtPerson());
            } else {
                noticeInfo.setDealtPerson("");
            }
            noticeInfoList.add(noticeInfo);
        }
        IfaNoticeInfoA002DtoResponse a002Res = new IfaNoticeInfoA002DtoResponse();
        a002Res.setNoticeInfoList(noticeInfoList);
        
        // 5.未読の重要なお知らせによる取引制限を取得する。(SQL002)
        IfaNoticeInfoSql002RequestModel selSql002Req = new IfaNoticeInfoSql002RequestModel();
        selSql002Req.setAccountNumber(dtoReq.getAccountNumber());
        selSql002Req.setButenCode(dtoReq.getButenCode());
        DataList<IfaNoticeInfoSql002ResponseModel> selSql002Res = dao.selectIfaNoticeInfoSql002(selSql002Req);
        
        // 6.SQL002の取得結果から制限内容マップを編集する。
        List<IfaNoticeInfoSql002ResponseModel> sql002ResList = selSql002Res.getDataList();
        Map<String, String> limitContentMapOld = new HashMap<>();
        for (IfaNoticeInfoSql002ResponseModel sql002Res : sql002ResList) {
            List<String> codeList = getCodeList(sql002Res.getRestRaint());
            for (String id : codeList) {
                if (limitContentMapOld.containsKey(id)) {
                    if (limitContentMapOld.get(id) != null) {
                        limitContentMapOld.put(id, sql002Res.getRestRaintDate());
                    }
                } else {
                    limitContentMapOld.put(id, sql002Res.getRestRaintDate());
                }
            }
        }
        
        Map<String, String> limitContentMap = new HashMap<>();
        for (Map.Entry<String, String> entry : limitContentMapOld.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String newKey = StringUtil.fillLeft(key, '0', 3);
            limitContentMap.put(newKey, value);
        }
        
        // 7.編集した制限内容マップを元に制限内容表示文言を取得する。(SQL003)
        List<String> limitCode = new ArrayList<>(limitContentMap.keySet());
        IfaNoticeInfoSql003RequestModel selSql003Req = new IfaNoticeInfoSql003RequestModel();
        selSql003Req.setLimitCode(limitCode);
        DataList<IfaNoticeInfoSql003ResponseModel> selSql003Res = dao.selectIfaNoticeInfoSql003(selSql003Req);
        
        // 8.取引制限一覧を以下のとおり編集し、制限番号の昇順にソートする。
        List<IfaNoticeInfoA002DtoResponseTradeRestrictionList> tradeRestrictionList = new ArrayList<>();
        for (Map.Entry<String, String> entry : limitContentMap.entrySet()) {
            IfaNoticeInfoA002DtoResponseTradeRestrictionList tradeRestriction = new IfaNoticeInfoA002DtoResponseTradeRestrictionList();
            tradeRestriction.setRestrictionCode(entry.getKey());
            tradeRestriction.setConfirmDeadline(entry.getValue());
            tradeRestrictionList.add(tradeRestriction);
        }
        a002Res.setTradeRestrictionList(tradeRestrictionList);
        
        for (IfaNoticeInfoA002DtoResponseTradeRestrictionList obj : tradeRestrictionList) {
            for (IfaNoticeInfoSql003ResponseModel sql003 : selSql003Res.getDataList()) {
                if (obj.getRestrictionCode().equals(StringUtil.fillLeft(sql003.getCode1(), '0', 3))) {
                    obj.setRestrictionContents(sql003.getValue());
                }
            }
        }
        Collections.sort(tradeRestrictionList,
                Comparator.comparing(IfaNoticeInfoA002DtoResponseTradeRestrictionList::getRestrictionCode));

        List<IfaNoticeInfoA002DtoResponse> dtoResList = dtoRes.getDataList();
        dtoResList.add(a002Res);
        dtoRes = IfaCommonUtil.createDataList(dtoResList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }
    
    private List<String> getCodeList(String content) {
        
        List<String> codeList = new ArrayList<>();
        for (int i = 0; i < content.length(); i++) {
            if (content.charAt(i) == '1') {
                codeList.add(String.valueOf(i + 1));
            }
        }
        return codeList;
    }
    
}
