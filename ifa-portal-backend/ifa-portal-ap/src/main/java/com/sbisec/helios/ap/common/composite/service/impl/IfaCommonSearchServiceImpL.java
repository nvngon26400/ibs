package com.sbisec.helios.ap.common.composite.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.bizcommon.component.Fct032;
import com.sbisec.helios.ap.bizcommon.model.InputFct032Dto;
import com.sbisec.helios.ap.bizcommon.model.OutputFct032Dto;
import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoRequest;
import com.sbisec.helios.ap.common.composite.dto.IfaCommonSearchA001DtoResponse;
import com.sbisec.helios.ap.common.composite.service.IfaCommonSearchService;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 画面ID：CC005
 * 画面名：検索条件（一覧画面）
 * @author <author-name>
 *
 * 2023/12/12 新規作成
 */
@Component(value = "cmpIfaCommonSearchService")
public class IfaCommonSearchServiceImpL implements IfaCommonSearchService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(IfaCommonSearchServiceImpL.class);
    
    @Autowired
    private Fct032 fct032;

    /**
     * アクションID：A001
     * アクション名：初期化
     * Dto リクエスト：IfaCommonSearchA001DtoRequest
     * Dto レスポンス：IfaCommonSearchA001DtoResponse
     * model リクエスト：RequestModel
     * model レスポンス：ResponseModel
     * @param <paramName> <description of param value>
     * @return <description of return value>
     * @exception <exceptionName> <description>
     * @see <reference item>
     */
    public DataList<IfaCommonSearchA001DtoResponse> initializeA001(IfaCommonSearchA001DtoRequest dtoReq)
            throws Exception {
        DataList<IfaCommonSearchA001DtoResponse> dtoRes = new DataList<IfaCommonSearchA001DtoResponse>();
        List<IfaCommonSearchA001DtoResponse> resDtoList = new ArrayList<IfaCommonSearchA001DtoResponse>();
        
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("IfaCommonSearchServiceImplL.initializeA001");
        
        //入力項目（仲介業者コード、支店コード、営業員コード）の自動入力フラグを取得する。
        //FCT032を呼び出す
        InputFct032Dto fct032Req = new InputFct032Dto();
        OutputFct032Dto fct032Res = new OutputFct032Dto();
        fct032Res = fct032.getData(fct032Req);
        IfaCommonSearchA001DtoResponse resDto = new IfaCommonSearchA001DtoResponse();
        //仲介業者コード自動表示フラグ
        resDto.setBrokerCodeAutoDispFlag(fct032Res.getBrokerCodeAutoSettingJudge());
        //支店コード自動表示フラグ
        resDto.setBranchCodeAutoDispFlag(fct032Res.getBrokerBranchCodeSettingJudge());
        //営業員コード自動表示フラグ
        resDto.setEmpCodeAutoDispFlag(fct032Res.getEmpCodeCodeSettingJudge()); 
        
        resDtoList.add(resDto);
        dtoRes = IfaCommonUtil.createDataList(resDtoList, ErrorLevel.SUCCESS, "", "");
        return dtoRes;
    }

}
