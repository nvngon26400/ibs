package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectBlotterDetailDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterDetailSql001RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterDetailA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.IfaSelfInspectBlotterDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0506_01-02
 * 画面名：自己点検記録簿詳細
 *
 * @author SCSK
 2024/06/12 新規作成
 */
@Component(value = "cmpIfaSelfInspectBlotterDetailService")
public class IfaSelfInspectBlotterDetailServiceImpl implements IfaSelfInspectBlotterDetailService {
    
    @Autowired
    private IfaSelfInspectBlotterDetailDao dao;
    
    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaSelfInspectBlotterDetailA001RequestDto
     * Dto レスポンス：IfaSelfInspectBlotterDetailA001ResponseDto
     * model リクエスト：IfaSelfInspectBlotterDetailA001RequestModel
     * model レスポンス：IfaSelfInspectBlotterDetailA001ResponseModel
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterDetailA001ResponseDto> initializeA001(
            IfaSelfInspectBlotterDetailA001RequestDto dtoReq) throws Exception {
        
        var resList = dao
                .selectIfaSelfInspectBlotterDetailSql001(new IfaSelfInspectBlotterDetailSql001RequestModel(
                        dtoReq.getRegisterDate(), dtoReq.getBrokerCode())) //
                .getDataList();
        
        var selfAssessmentList = resList.stream() //
                .map(res -> IfaSelfInspectBlotterDetailA001ResponseDto.SelfAssessment.builder() //
                        .selfInspectItemName(res.getCorSelfCheckItemName()) //
                        .confirmation(res.getCorConfirmationFlg()) //
                        .answerResult(res.getCorAnswerResult()) //
                        .answerReason(res.getCorAnswerReason()) //
                        .build()) //
                .collect(Collectors.toList());
        var resDtoBuilder = IfaSelfInspectBlotterDetailA001ResponseDto.builder() //
                .registerDate(dtoReq.getRegisterDate()) //
                .brokerCode(dtoReq.getBrokerCode()) //
                .brokerName(dtoReq.getBrokerName()) //
                .selfAssessmentList(selfAssessmentList) //
        ;
        if (!resList.isEmpty()) {
            // メモはDB上1件なので、1件目をセット
            resDtoBuilder.memo(resList.get(0).getCorMemo());
        }
        return IfaCommonUtil.createDataList(List.of(resDtoBuilder.build()), ErrorLevel.SUCCESS,
                ErrorLevel.SUCCESS.toString(), null);
    }
    
}
