package com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct038;
import com.sbisec.helios.ap.bizcommon.model.InputFct038Dto;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.IfaSelfInspectBlotterConfirmManagerDao;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002RequestModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dao.model.IfaSelfInspectBlotterConfirmManagerSql002RequestModel.IfaSelfInspectBlotterConfirmManagerSql002RequestModelBuilder;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001RequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA001ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA004CsvModel;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerA004ResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerDisplayResponseDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.dto.IfaSelfInspectBlotterConfirmManagerQueryRequestDto;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.service.IfaSelfInspectBlotterConfirmManagerService;
import com.sbisec.helios.ap.companyEmployeeMenu.selfInspect.util.IfaSelfInspectBlotterConfirmManagerCsvUtil;
import com.sbisec.helios.ap.compliance.service.ComplianceService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：SUB0506_01-01
 * 画面名：自己点検記録簿確認（管理者用）
 *
 * @author SCSK
 2024/06/10 新規作成
 */
@Component(value = "cmpIfaSelfInspectBlotterConfirmManagerService")
public class IfaSelfInspectBlotterConfirmManagerServiceImpl implements IfaSelfInspectBlotterConfirmManagerService {
    
    @Autowired
    private IfaSelfInspectBlotterConfirmManagerDao dao;
    
    @Autowired
    private Fct038 fct038;
    
    private static final String MSGID_NOT_FOUND = "errors.dataList.notfound";
    
    private static final String MSGID_OVER_MAX_ROW = "warnings.dataList.overMaxRownum";
    
    private static final String MSGID_CSV_OVER_MAX_ROW = "warnings.dataList.csv.overMaxRownum";
    
    private static final int MAX_DISPLAY_ROWS = 5000;
    
    /**
     * ComplianceService(CSV証跡登録用)クラス
     */
    @Autowired
    private ComplianceService complianceService;
    
    /**
     * アクションID：A004
     * アクション名：CSV出力
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    public DataList<IfaSelfInspectBlotterConfirmManagerA004ResponseDto> csvOutputA004(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception {
        
        var builder = IfaSelfInspectBlotterConfirmManagerSql002RequestModel.builder();
        buildParameters(dtoReq, builder);
        var csvMaxRows = getCsvMaxRows();
        builder.maxRows(csvMaxRows);
        var sqlResList = dao.selectIfaSelfInspectBlotterConfirmManagerSql002(builder.build()).getDataList();
        if (sqlResList.isEmpty()) {
            return IfaCommonUtil.createDtaList(null, ErrorLevel.INFO, MSGID_NOT_FOUND);
        }
        long totalRows = sqlResList.get(0).getTotalRows();
        var dtoList = sqlResList.stream().map(s -> IfaSelfInspectBlotterConfirmManagerA004CsvModel.builder() //
                .brokerCode(s.getBrokerCode()) //
                .brokerName(s.getBrokerName()) //
                .registerDate(s.getCorEnforceDate()) //
                .confirmationDate(s.getCorConfirmationDate()) //
                .confirm1(s.getConfirmFlg1()) //
                .answerReason1(s.getAnswerReason1()) //
                .confirm2(s.getConfirmFlg2()) //
                .answerReason2(s.getAnswerReason2()) //
                .confirm3(s.getConfirmFlg3()) //
                .answerReason3(s.getAnswerReason3()) //
                .confirm4(s.getConfirmFlg4()) //
                .answerReason4(s.getAnswerReason4()) //
                .confirm5(s.getConfirmFlg5()) //
                .answerReason5(s.getAnswerReason5()) //
                .confirm6(s.getConfirmFlg6()) //
                .answerReason6(s.getAnswerReason6()) //
                .confirm7(s.getConfirmFlg7()) //
                .answerReason7(s.getAnswerReason7()) //
                .confirm8(s.getConfirmFlg8()) //
                .answerReason8(s.getAnswerReason8()) //
                .confirm9(s.getConfirmFlg9()) //
                .answerReason9(s.getAnswerReason9()) //
                .confirm10(s.getConfirmFlg10()) //
                .answerReason10(s.getAnswerReason10()) //
                .confirm11(s.getConfirmFlg11()) //
                .answerReason11(s.getAnswerReason11()) //
                .confirm12(s.getConfirmFlg12()) //
                .answerReason12(s.getAnswerReason12()) //
                .confirm13(s.getConfirmFlg13()) //
                .answerReason13(s.getAnswerReason13()) //
                .confirm14(s.getConfirmFlg14()) //
                .answerReason14(s.getAnswerReason14()) //
                .confirm15(s.getConfirmFlg15()) //
                .answerReason15(s.getAnswerReason15()) //
                .confirm16(s.getConfirmFlg16()) //
                .answerReason16(s.getAnswerReason16()) //
                .confirm17(s.getConfirmFlg17()) //
                .answerReason17(s.getAnswerReason17()) //
                .confirm18(s.getConfirmFlg18()) //
                .answerReason18(s.getAnswerReason18()) //
                .confirm19(s.getConfirmFlg19()) //
                .answerReason19(s.getAnswerReason19()) //
                .confirm20(s.getConfirmFlg20()) //
                .answerReason20(s.getAnswerReason20()) //
                .build()).collect(Collectors.toList());
        var csvUtil = new IfaSelfInspectBlotterConfirmManagerCsvUtil(complianceService);
        // CSVの生成
        var fileName = csvUtil.doCreateCsvFile(dtoList);
        DataList<IfaSelfInspectBlotterConfirmManagerA004ResponseDto> resDataList;
        
        if (totalRows > csvMaxRows) {
            resDataList = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.WARNING,
                    MSGID_CSV_OVER_MAX_ROW, IfaCommonUtil.getMessage(MSGID_CSV_OVER_MAX_ROW,
                            new String[] { csvMaxRows + "", totalRows + "", csvMaxRows + "" }));
        } else {
            resDataList = IfaCommonUtil.createDataList(Collections.emptyList(), ErrorLevel.SUCCESS,
                    ErrorLevel.SUCCESS.toString(), null);
        }
        resDataList.setTitle(fileName);
        resDataList.setTotalSize(Math.toIntExact(totalRows));
        resDataList.setMaxRownum(csvMaxRows);
        resDataList.setOverMaxRownum(true);
        return resDataList;
    }
    
    /**
     * アクションID：A002
     * アクション名：全仲介業者名表示
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto> allBrokerNameDisplayA002(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception {
        
        var builder = IfaSelfInspectBlotterConfirmManagerSql002RequestModel.builder();
        buildParameters(dtoReq, builder);
        builder.maxRows(MAX_DISPLAY_ROWS);
        var sqlResList = dao.selectIfaSelfInspectBlotterConfirmManagerSql002(builder.build()).getDataList();
        if (sqlResList.isEmpty()) {
            return IfaCommonUtil.createDtaList(null, ErrorLevel.INFO, MSGID_NOT_FOUND);
        }
        long totalRows = sqlResList.get(0).getTotalRows();
        var dtoList = sqlResList.stream().map(s -> IfaSelfInspectBlotterConfirmManagerDisplayResponseDto.builder() //
                .brokerCode(s.getBrokerCode()) //
                .brokerName(s.getBrokerName()) //
                .registerDate(s.getCorEnforceDate()) //
                .confirmationDate(s.getCorConfirmationDate()) //
                .confirm1(s.getConfirmFlg1()) //
                .answerReason1(s.getAnswerReason1()) //
                .answerResult1(s.getAnswerResult1()) //
                .confirm2(s.getConfirmFlg2()) //
                .answerReason2(s.getAnswerReason2()) //
                .answerResult2(s.getAnswerResult2()) //
                .confirm3(s.getConfirmFlg3()) //
                .answerReason3(s.getAnswerReason3()) //
                .answerResult3(s.getAnswerResult3()) //
                .confirm4(s.getConfirmFlg4()) //
                .answerReason4(s.getAnswerReason4()) //
                .answerResult4(s.getAnswerResult4()) //
                .confirm5(s.getConfirmFlg5()) //
                .answerReason5(s.getAnswerReason5()) //
                .answerResult5(s.getAnswerResult5()) //
                .confirm6(s.getConfirmFlg6()) //
                .answerReason6(s.getAnswerReason6()) //
                .answerResult6(s.getAnswerResult6()) //
                .confirm7(s.getConfirmFlg7()) //
                .answerReason7(s.getAnswerReason7()) //
                .answerResult7(s.getAnswerResult7()) //
                .confirm8(s.getConfirmFlg8()) //
                .answerReason8(s.getAnswerReason8()) //
                .answerResult8(s.getAnswerResult8()) //
                .confirm9(s.getConfirmFlg9()) //
                .answerReason9(s.getAnswerReason9()) //
                .answerResult9(s.getAnswerResult9()) //
                .confirm10(s.getConfirmFlg10()) //
                .answerReason10(s.getAnswerReason10()) //
                .answerResult10(s.getAnswerResult10()) //
                .confirm11(s.getConfirmFlg11()) //
                .answerReason11(s.getAnswerReason11()) //
                .answerResult11(s.getAnswerResult11()) //
                .confirm12(s.getConfirmFlg12()) //
                .answerReason12(s.getAnswerReason12()) //
                .answerResult12(s.getAnswerResult12()) //
                .confirm13(s.getConfirmFlg13()) //
                .answerReason13(s.getAnswerReason13()) //
                .answerResult13(s.getAnswerResult13()) //
                .confirm14(s.getConfirmFlg14()) //
                .answerReason14(s.getAnswerReason14()) //
                .answerResult14(s.getAnswerResult14()) //
                .confirm15(s.getConfirmFlg15()) //
                .answerReason15(s.getAnswerReason15()) //
                .answerResult15(s.getAnswerResult15()) //
                .confirm16(s.getConfirmFlg16()) //
                .answerReason16(s.getAnswerReason16()) //
                .answerResult16(s.getAnswerResult16()) //
                .confirm17(s.getConfirmFlg17()) //
                .answerReason17(s.getAnswerReason17()) //
                .answerResult17(s.getAnswerResult17()) //
                .confirm18(s.getConfirmFlg18()) //
                .answerReason18(s.getAnswerReason18()) //
                .answerResult18(s.getAnswerResult18()) //
                .confirm19(s.getConfirmFlg19()) //
                .answerReason19(s.getAnswerReason19()) //
                .answerResult19(s.getAnswerResult19()) //
                .confirm20(s.getConfirmFlg20()) //
                .answerReason20(s.getAnswerReason20()) //
                .answerResult20(s.getAnswerResult20()) //
                .build()).collect(Collectors.toList());
        if (totalRows > MAX_DISPLAY_ROWS) {
            return IfaCommonUtil.createDataList(dtoList, ErrorLevel.WARNING, MSGID_OVER_MAX_ROW, IfaCommonUtil
                    .getMessage(MSGID_OVER_MAX_ROW, new String[] { MAX_DISPLAY_ROWS + "", totalRows + "" }));
        }
        return IfaCommonUtil.createDataList(dtoList, ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    /**
     * アクションID：A003
     * アクション名：検索表示
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterConfirmManagerDisplayResponseDto> searchDisplayA003(
            IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq) throws Exception {
        
        return allBrokerNameDisplayA002(dtoReq);
    }
    
    private void buildParameters(IfaSelfInspectBlotterConfirmManagerQueryRequestDto dtoReq,
            IfaSelfInspectBlotterConfirmManagerSql002RequestModelBuilder builder) {
        
        builder.brokerCode(dtoReq.getBrokerCode()) //
                .answerResult(dtoReq.getAnswerResult()) //
                .answerStatus(dtoReq.getAnswerStatus()) //
                .registerDate(dtoReq.getRegisterDate()) //
        ;
        if (StringUtils.isNotBlank(dtoReq.getBrokerName())) {
            builder.brokerName("%" + dtoReq.getBrokerName().replaceAll("%", "") + "%");
        }
    }
    
    /**
     * アクションID：A001
     * アクション名：初期化
     *
     * @param dtoReq リクエスト
     * @return res レスポンス
     * @exception exception システムエラー
     */
    @Override
    public DataList<IfaSelfInspectBlotterConfirmManagerA001ResponseDto> initializeA001(
            IfaSelfInspectBlotterConfirmManagerA001RequestDto dtoReq) throws Exception {
        
        var regList = dao.selectIfaSelfInspectBlotterConfirmManagerSql001().getDataList().stream() //
                .map(sqlRes -> new IfaSelfInspectBlotterConfirmManagerA001ResponseDto.RegisterDate(sqlRes.getCodeId(),
                        sqlRes.getCodeName())) //
                .collect(Collectors.toList());
        return IfaCommonUtil.createDataList(List.of(new IfaSelfInspectBlotterConfirmManagerA001ResponseDto(regList)),
                ErrorLevel.SUCCESS, ErrorLevel.SUCCESS.toString(), null);
    }
    
    private int getCsvMaxRows() {
        
        var input = new InputFct038Dto();
        input.setScreenId("SUB0506_01-01");
        input.setUserAuthority(IfaCommonUtil.getUserAccount().getPrivId());
        return fct038.getData(input).getCsvDownloadNum();
    }
    
}
